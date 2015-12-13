package me.box.users.support;

import me.box.users.UserRepository;
import me.box.users.UserService;
import me.box.users.UsersEntity;
import me.box.users.authentication.UserLoginAttemptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

@Service("userService")
public class UserServiceImpl implements UserService {

  @Autowired
  private UserLoginAttemptsService userLoginAttemptsService;

  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional
  public Serializable create(UsersEntity user) {
    return userRepository.create(user);
  }

  @Override
  @Transactional
  public UsersEntity read(Long id) {
    return userRepository.read(id);
  }

  @Override
  @Transactional
  public void update(UsersEntity user) {
    userRepository.update(user);
  }

  @Override
  @Transactional
  public Collection<UsersEntity> findAll() {
    return userRepository.findAll();
  }

  @Override
  @Transactional
  public void delete(Long id) {
    userRepository.deleteById(id);
  }

  @Override
  @Transactional
  public UsersEntity findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public void lock(String username) {
    UsersEntity user = findByUsername(username);
    user.setAccountNonLocked(false);
    userRepository.update(user);
  }

  @Override
  public void unlock(String username) {
    UsersEntity user = findByUsername(username);
    user.setAccountNonLocked(true);
    userRepository.update(user);
    userLoginAttemptsService.resetAttempts(username);
  }

  @Override
  public boolean checkKeyword(String username, String keyword) {
    UsersEntity user = findByUsername(username);
    return user.getSecretAnswer().equals(keyword);
  }

  @Override
  public void credentialsNonExpired(String username, boolean value) {
    UsersEntity user = findByUsername(username);
    user.setCredentialsNonExpired(value);
    userRepository.update(user);
  }
}
