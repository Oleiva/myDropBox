package io.github.oleiva.myDropBox.users.support.model;

import io.github.oleiva.myDropBox.users.UsersEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class UserEditForm implements Serializable {

  private static final long serialVersionUID = 3778827638216862820L;
  private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");

  private int authority;

  @Size(min = 3, max = 40)
  private String username;

  @Size(min = 3, max = 40)
  private String oldPassword;

  @Size(min = 3, max = 40)
  private String newPassword;

  @Email
  @Size(min = 3, max = 40)
  private String email;

  @Size(min = 3, max = 40)
  private String firstName;

  @Size(min = 3, max = 40)
  private String lastName;

  @Size(min = 3, max = 40)
  private String secretQuestion;

  @Size(min = 3, max = 40)
  private String secretAnswer;

  @Range(min = 0, max = 1)
  private byte enabled;

  @Range(min = 0, max = 1)
  private byte accountNonExpired;

  @Range(min = 0, max = 1)
  private byte accountNonLocked;

  @Range(min = 0, max = 1)
  private byte credentialsNonExpired;

  private Timestamp lastUpdate;

  public UserEditForm() {
  }

  public UserEditForm(UsersEntity usersEntity) {
    this.username = usersEntity.getUsername();
    this.email = usersEntity.getEmail();
    this.firstName = usersEntity.getFirstName();
    this.lastName = usersEntity.getLastName();
    this.authority = usersEntity.getAuthority();
    this.secretQuestion = usersEntity.getSecretQuestion();
    this.secretAnswer = usersEntity.getSecretAnswer();
    this.enabled = (byte) (usersEntity.isEnabled() ? 1 : 0);
    this.accountNonExpired = (byte) (usersEntity.isAccountNonExpired() ? 1 : 0);
    this.accountNonLocked = (byte) (usersEntity.isAccountNonLocked() ? 1 : 0);
    this.credentialsNonExpired = (byte) (usersEntity.isCredentialsNonExpired() ? 1 : 0);
    this.oldPassword = "";
    this.newPassword = "";
    this.lastUpdate = usersEntity.getLastUpdate();
  }


  public int getAuthority() {
    return authority;
  }

  public void setAuthority(int authority) {
    this.authority = authority;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getSecretQuestion() {
    return secretQuestion;
  }

  public void setSecretQuestion(String secretQuestion) {
    this.secretQuestion = secretQuestion;
  }

  public String getSecretAnswer() {
    return secretAnswer;
  }

  public void setSecretAnswer(String secretAnswer) {
    this.secretAnswer = secretAnswer;
  }

  public byte getEnabled() {
    return enabled;
  }

  public void setEnabled(byte enabled) {
    this.enabled = enabled;
  }

  public byte getAccountNonExpired() {
    return accountNonExpired;
  }

  public void setAccountNonExpired(byte accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  public byte getAccountNonLocked() {
    return accountNonLocked;
  }

  public void setAccountNonLocked(byte accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  public byte getCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  public void setCredentialsNonExpired(byte credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }

  public String getLastUpdate() {
    return SIMPLE_DATE_FORMAT.format(lastUpdate);
  }

  public void setLastUpdate(Timestamp lastUpdate) {
    this.lastUpdate = lastUpdate;
  }
}
