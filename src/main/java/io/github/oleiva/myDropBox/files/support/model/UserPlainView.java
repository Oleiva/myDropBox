package io.github.oleiva.myDropBox.files.support.model;

import io.github.oleiva.myDropBox.users.UsersEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by Ivasoft on 9/15/14.
 */
public class UserPlainView implements Serializable {

  private static final long serialVersionUID = -3666615704501014313L;

  private Long id;
  private String username;

  public UserPlainView(UsersEntity usersEntity) {
    this.id = usersEntity.getId();
    this.username = usersEntity.getUsername();
  }

  public static Collection<UserPlainView> of(Collection<UsersEntity> usersEntities) {
    Collection<UserPlainView> userPlainViews = new ArrayList<>(usersEntities.size());
    userPlainViews.addAll(usersEntities.stream()
        .map(UserPlainView::new)
        .collect(Collectors.toList()));
    return userPlainViews;
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }
}
