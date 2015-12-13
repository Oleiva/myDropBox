package io.github.oleiva.myDropBox.users.authentication;

import io.github.oleiva.myDropBox.users.UserService;
import io.github.oleiva.myDropBox.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userAuthenticationService")
public class UserAuthenticationService implements UserDetailsService {

  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UsersEntity user = userService.findByUsername(username);
    if (user == null) throw new UsernameNotFoundException("no " + username);

    return new UserDetailsImpl(user);
  }
}
