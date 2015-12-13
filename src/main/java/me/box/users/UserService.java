package me.box.users;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Ivasoft on 9/13/14.
 */
public interface UserService {
  Serializable create(UsersEntity user);

  UsersEntity read(Long id);

  void update(UsersEntity user);

  Collection<UsersEntity> findAll();

  void delete(Long id);

  UsersEntity findByUsername(String username);

  void lock(String username);

  void unlock(String username);

  boolean checkKeyword(String username, String keyword);

  void credentialsNonExpired(String username, boolean value);
}
