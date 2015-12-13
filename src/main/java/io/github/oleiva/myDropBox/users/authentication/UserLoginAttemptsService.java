package io.github.oleiva.myDropBox.users.authentication;

import io.github.oleiva.myDropBox.users.UserService;
import io.github.oleiva.myDropBox.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginAttemptsService {
  @Autowired
  private UserService userService;

  @Autowired
  private UserLoginAttemptsRepository userLoginAttemptsRepository;

  public void recordLoginAttempt(String username) {
    UserLoginAttemptsEntity userLoginAttemptsEntity = getAttempts(username);
    if (userLoginAttemptsEntity == null) {
      UsersEntity usersEntity = userService.findByUsername(username);
      userLoginAttemptsEntity = new UserLoginAttemptsEntity();
      userLoginAttemptsEntity.setUser(usersEntity);
      userLoginAttemptsRepository.create(userLoginAttemptsEntity);
    } else {
      userLoginAttemptsEntity.setAttempts(userLoginAttemptsEntity.getAttempts() + 1);
      userLoginAttemptsRepository.update(userLoginAttemptsEntity);
    }
  }

  public void resetAttempts(String username) {
    UserLoginAttemptsEntity userLoginAttemptsEntity = getAttempts(username);
    if (userLoginAttemptsEntity == null) {
      UsersEntity usersEntity = userService.findByUsername(username);
      userLoginAttemptsEntity = new UserLoginAttemptsEntity();
      userLoginAttemptsEntity.setUser(usersEntity);
      userLoginAttemptsRepository.create(userLoginAttemptsEntity);
    } else {
      userLoginAttemptsEntity.setAttempts(0);
      userLoginAttemptsRepository.update(userLoginAttemptsEntity);
    }
  }

  public UserLoginAttemptsEntity getAttempts(String username) {
    UsersEntity usersEntity = userService.findByUsername(username);
    return userLoginAttemptsRepository.findByUser(usersEntity);
  }
}
