package io.github.oleiva.myDropBox.users.authentication.support.listeners;

import io.github.oleiva.myDropBox.users.UserService;
import io.github.oleiva.myDropBox.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;

public class KeywordInterceptor extends HandlerInterceptorAdapter {

  @Autowired
  private UserService userService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.isAuthenticated()) {

      // if user is not anon
      String username = authentication.getName();
      UsersEntity usersEntity = userService.findByUsername(username);
      Timestamp scheduledKeywordVerification;
      if (usersEntity != null &&
          (scheduledKeywordVerification = (Timestamp) request.getSession().getAttribute
              ("scheduledKeywordVerification")) != null) {
        Timestamp now = new Timestamp(new Date().getTime());

        // if time has passed
        if (now.getTime() >= scheduledKeywordVerification.getTime()) {
          request.getSession().removeAttribute("scheduledKeywordVerification");

          // save requested url
          String requestURL = request.getRequestURL().toString();
          request.getSession().setAttribute("requestURL", requestURL);

          // request verification
          response.sendRedirect("/checkKeyword");
          return false;
        }
      }
    }
    return true;
  }
}
