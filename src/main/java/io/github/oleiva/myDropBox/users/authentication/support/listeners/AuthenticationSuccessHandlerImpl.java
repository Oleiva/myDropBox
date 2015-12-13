package io.github.oleiva.myDropBox.users.authentication.support.listeners;

import io.github.oleiva.myDropBox.users.authentication.UserLoginAttemptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Ivasoft on 9/28/14.
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

  @Autowired
  private UserLoginAttemptsService userLoginAttemptsService;

  @Value("${keywordRequestInterval}")
  private int keywordRequestInterval;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

    // schedule keyword verification
    HttpSession session = request.getSession();
    Timestamp timestamp = new Timestamp(new Date().getTime() + keywordRequestInterval * 1000);
    session.setAttribute("scheduledKeywordVerification", timestamp);

    // reset login attempts
    String username = authentication.getName();
    userLoginAttemptsService.resetAttempts(username);

    httpServletResponse.sendRedirect("/files/list");
  }
}
