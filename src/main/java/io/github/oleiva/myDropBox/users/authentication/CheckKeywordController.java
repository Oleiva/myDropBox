package io.github.oleiva.myDropBox.users.authentication;

import io.github.oleiva.myDropBox.users.UserService;
import io.github.oleiva.myDropBox.users.authentication.support.model.KeywordForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping(value = "/checkKeyword")
public class CheckKeywordController {

  @Autowired
  private UserService userService;

  @Value("${keywordRequestInterval}")
  private int keywordRequestInterval;

  @RequestMapping(method = RequestMethod.GET)
  public String checkKeyword(ModelMap model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    KeywordForm keywordForm = new KeywordForm(userService.findByUsername(authentication.getName()));

    model.addAttribute("keywordForm", keywordForm);
    return "check_keyword";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String checkKey(@ModelAttribute("keywordForm") @Valid KeywordForm form,
                         HttpServletRequest request, HttpServletResponse response) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (!userService.checkKeyword(authentication.getName(), form.getAnswer())) {
      // logout and redirect
      new SecurityContextLogoutHandler().logout(request, response, authentication);
      SecurityContextHolder.getContext().setAuthentication(null);
      return "redirect:/login";
    }

    // update scheduledKeywordVerification
    HttpSession session = request.getSession();
    Timestamp timestamp = new Timestamp(new Date().getTime() + keywordRequestInterval * 1000);
    session.setAttribute("scheduledKeywordVerification", timestamp);

    // redirect to previously requested url
    String requestURL = (String) session.getAttribute("requestURL");
    return "redirect:" + requestURL;
  }

  @ModelAttribute("keywordForm")
  public KeywordForm keywordForm() {
    return new KeywordForm();
  }
}
