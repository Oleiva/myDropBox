package me.box.users.support.model.adapter;

import me.box.users.UsersEntity;
import me.box.users.authentication.support.Authorities;
import me.box.users.support.model.UserAddForm;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserAddFormAdapter {
  public void updateUserEntityFromUserAddForm(UsersEntity usersEntity, UserAddForm userAddForm) {
    usersEntity.setUsername(userAddForm.getUsername());
    usersEntity.setPassword(BCrypt.hashpw(usersEntity.getPassword(), BCrypt.gensalt()));
    usersEntity.setEmail(usersEntity.getEmail());
    usersEntity.setAuthority(Authorities.ROLE_USER.getValue());
    usersEntity.setSecretQuestion(usersEntity.getSecretQuestion());
    usersEntity.setSecretAnswer(usersEntity.getSecretAnswer());
  }
}
