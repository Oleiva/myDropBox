package io.github.oleiva.myDropBox.users.authentication.support;

/**
 * Created by Ivasoft on 9/7/14.
 */
public enum Authorities {
  ROLE_USER(0),
  ROLE_ADMIN(1);

  private final int value;

  private Authorities(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
