package me.box.users.support.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserAddForm implements Serializable {

  private static final long serialVersionUID = 6298661680128608693L;

  @Size(min = 3, max = 40)
  private String username;

  @Size(min = 3, max = 40)
  private String password;

  @Size(min = 3, max = 40)
  private String confirmPassword;

  @NotEmpty
  @Email
  private String email;

  @Size(min = 3, max = 40)
  private String secretQuestion;

  @Size(min = 3, max = 40)
  private String secretAnswer;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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
}
