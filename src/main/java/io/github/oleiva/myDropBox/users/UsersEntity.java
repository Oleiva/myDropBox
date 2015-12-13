package io.github.oleiva.myDropBox.users;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class UsersEntity implements Serializable {

  private static final long serialVersionUID = 2640722441945422970L;

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "authority", nullable = false)
  private int authority;

  @Column(name = "username", length = 255, nullable = false, unique = true)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "last_update", insertable = false, updatable = false)
  private Timestamp lastUpdate;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "first_name", nullable = false, length = 255, insertable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 255, insertable = false)
  private String lastName;

  @Column(name = "enabled", nullable = false, insertable = false)
  private boolean enabled;

  @Column(name = "account_non_expired", nullable = false, insertable = false)
  private boolean accountNonExpired;

  @Column(name = "account_non_locked", nullable = false, insertable = false)
  private boolean accountNonLocked;

  @Column(name = "credentials_non_expired", nullable = false, insertable = false)
  private boolean credentialsNonExpired;

  @Column(name = "secret_question", nullable = false)
  private String secretQuestion;

  @Column(name = "secret_answer", nullable = false)
  private String secretAnswer;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Timestamp getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(Timestamp lastUpdate) {
    this.lastUpdate = lastUpdate;
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

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  public void setAccountNonExpired(boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  public void setAccountNonLocked(boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  public void setCredentialsNonExpired(boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UsersEntity that = (UsersEntity) o;

    if (authority != that.authority) return false;
    if (enabled != that.enabled) return false;
    if (id != that.id) return false;
    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
    if (password != null ? !password.equals(that.password) : that.password != null) return false;
    if (username != null ? !username.equals(that.username) : that.username != null) return false;
    if (secretAnswer != null ? !secretAnswer.equals(that.secretAnswer) : that.secretAnswer != null) return false;
    if (secretQuestion != null ? !secretQuestion.equals(that.secretQuestion) : that.secretQuestion != null)
      return false;
    if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = (id != null ? id.intValue() : 0);
    result = 31 * result + authority;
    result = 31 * result + (username != null ? username.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (enabled ? 1 : 0);
    result = 31 * result + (secretAnswer != null ? secretAnswer.hashCode() : 0);
    result = 31 * result + (secretQuestion != null ? secretQuestion.hashCode() : 0);
    result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);

    return result;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("authority", authority)
        .append("username", username)
        .append("password", password)
        .append("email", email)
        .append("firstName", firstName)
        .append("lastName", lastName)
        .append("enabled", enabled)
        .append("secretQuestion", secretQuestion)
        .append("secretAnswer", secretAnswer)
        .append("lastPasswordUpdate", lastUpdate)
        .toString();
  }
}
