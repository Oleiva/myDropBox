package me.box.users.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String login() {
    return "login";
  }

  @RequestMapping(value = "/badCredentials", method = RequestMethod.GET)
  public String badCredentials(ModelMap model) {
    model.addAttribute("message", "Bad credentials");
    return "login";
  }

  @RequestMapping(value = "/credentialsExpired", method = RequestMethod.GET)
  public String credentialsExpired(ModelMap model) {
    model.addAttribute("message", "Credentials expired");
    return "login";
  }

  @RequestMapping(value = "/accountLocked", method = RequestMethod.GET)
  public String accountLocked(ModelMap model) {
    model.addAttribute("message", "Account locked");
    return "login";
  }

  @RequestMapping(value = "/accountDisabled", method = RequestMethod.GET)
  public String accountDisabled(ModelMap model) {
    model.addAttribute("message", "Account disabled");
    return "login";
  }
}