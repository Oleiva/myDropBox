package io.github.oleiva.myDropBox.users.authentication;

import io.github.oleiva.myDropBox.entity.UsersEntity;
import io.github.oleiva.myDropBox.users.authentication.support.Authorities;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

  private static final long serialVersionUID = 179190597689985316L;

  private static final Authorities[] AUTHORITIES = Authorities.values();
  private final String username;
  private final String password;
  private final boolean enabled;

  private boolean accountNonExpired;
  private boolean accountNonLocked;
  private boolean credentialsNonExpired;

  private transient Collection<GrantedAuthority> authorities;

  public UserDetailsImpl(final UsersEntity user) {
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.enabled = user.isEnabled();
    this.accountNonExpired = user.isAccountNonExpired();
    this.accountNonLocked = user.isAccountNonLocked();
    this.credentialsNonExpired = user.isCredentialsNonExpired();

    List<GrantedAuthority> authorityList = new ArrayList<>();
    authorityList.add(new SimpleGrantedAuthority(AUTHORITIES[user.getAuthority()].toString()));
    authorities = authorityList;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.accountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.accountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.credentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }
}
