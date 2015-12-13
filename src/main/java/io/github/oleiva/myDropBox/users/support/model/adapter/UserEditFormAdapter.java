package io.github.oleiva.myDropBox.users.support.model.adapter;

import io.github.oleiva.myDropBox.users.support.model.UserEditForm;
import io.github.oleiva.myDropBox.users.UsersEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserEditFormAdapter {
  public void updateUserEntityFromUserEditForm(UsersEntity usersEntity, UserEditForm userEditForm) {
    usersEntity.setUsername(userEditForm.getUsername());
    usersEntity.setEmail(userEditForm.getEmail());
    usersEntity.setFirstName(userEditForm.getFirstName());
    usersEntity.setLastName(userEditForm.getLastName());
    usersEntity.setAuthority(userEditForm.getAuthority());
    usersEntity.setEnabled(userEditForm.getEnabled() != 0);
    usersEntity.setSecretQuestion(userEditForm.getSecretQuestion());
    usersEntity.setSecretAnswer(userEditForm.getSecretAnswer());
    usersEntity.setAccountNonExpired(userEditForm.getAccountNonExpired() != 0);
    usersEntity.setAccountNonLocked(userEditForm.getAccountNonLocked() != 0);
    usersEntity.setCredentialsNonExpired(userEditForm.getCredentialsNonExpired() != 0);
    usersEntity.setPassword(BCrypt.hashpw(userEditForm.getNewPassword(), BCrypt.gensalt()));
  }
}
