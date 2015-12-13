package io.github.oleiva.myDropBox.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Ivasoft on 9/15/14.
 */
@Entity
@Table(name = "user_login_attempts")
public class UserLoginAttemptsEntity implements Serializable {

  private static final long serialVersionUID = 7444841942682638816L;

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id")
  @NotFound(action = NotFoundAction.IGNORE)
  private UsersEntity user;

  @Column(name = "attempts", insertable = false)
  private int attempts;

  @Column(name = "last_modified", insertable = false, updatable = false)
  private Timestamp lastModified;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UsersEntity getUser() {
    return user;
  }

  public void setUser(UsersEntity user) {
    this.user = user;
  }

  public int getAttempts() {
    return attempts;
  }

  public void setAttempts(int attempts) {
    this.attempts = attempts;
  }

  public Timestamp getLastModified() {
    return lastModified;
  }

  public void setLastModified(Timestamp lastModified) {
    this.lastModified = lastModified;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserLoginAttemptsEntity that = (UserLoginAttemptsEntity) o;

    if (attempts != that.attempts) return false;
    if (id != that.id) return false;
    if (user != null ? !user.equals(that.user) : that.user != null) return false;
    if (lastModified != null ? !lastModified.equals(that.lastModified) : that.lastModified != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id.intValue();
    result = 31 * result + (user != null ? user.hashCode() : 0);
    ;
    result = 31 * result + attempts;
    result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
    return result;
  }
}
