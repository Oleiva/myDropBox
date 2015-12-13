package io.github.oleiva.myDropBox.users.support.model;

import io.github.oleiva.myDropBox.users.UsersEntity;
import io.github.oleiva.myDropBox.users.authentication.support.Authorities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class UserListView implements Serializable {

  private static final long serialVersionUID = 3778827638216862820L;

  private final Long id;

  private final String authority;

  private final String username;

  private final String email;

  private final String firstName;

  private final String lastName;

  private final boolean enabled;

  public UserListView(UsersEntity usersEntity) {
    this.id = usersEntity.getId();
    this.authority = Authorities.values()[usersEntity.getAuthority()].toString();
    this.username = usersEntity.getUsername();
    this.email = usersEntity.getEmail();
    this.firstName = usersEntity.getFirstName();
    this.lastName = usersEntity.getLastName();
    this.enabled = usersEntity.isEnabled();
  }

  public static Collection<UserListView> of(Collection<UsersEntity> usersEntities) {
    Collection<UserListView> userPlainViews = new ArrayList<>(usersEntities.size());
    userPlainViews.addAll(usersEntities.stream()
        .map(UserListView::new)
        .collect(Collectors.toList()));
    return userPlainViews;
  }

  public Long getId() {
    return id;
  }

  public String getAuthority() {
    return authority;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public boolean getEnabled() {
    return enabled;
  }

}