package me.box.users.authentication.support.listeners;

import me.box.users.UserService;
import me.box.users.authentication.UserLoginAttemptsEntity;
import me.box.users.authentication.UserLoginAttemptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;

public class LoginFailureEventHandler extends ExceptionMappingAuthenticationFailureHandler implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

  @Autowired
  private UserLoginAttemptsService userLoginAttemptsService;

  @Autowired
  private UserService userService;

  @Value("${loginAttemptsThreshold}")
  private int loginAttemptsThreshold;

  @Override
  public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
    if (event.getException().getClass().equals(UsernameNotFoundException.class)) {
      return;
    }

    String username = event.getAuthentication().getName();
    userLoginAttemptsService.recordLoginAttempt(username);

    UserLoginAttemptsEntity userLoginAttemptsEntity = userLoginAttemptsService.getAttempts(username);
    if (userLoginAttemptsEntity.getAttempts() >= loginAttemptsThreshold) {
      userService.lock(username);
    }
  }
}
