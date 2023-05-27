package kz.careerguidance.applicationapi.service;


import kz.careerguidance.applicationapi.dto.UserDto;
import kz.careerguidance.applicationapi.entity.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

  private final UserService userService;

  public UserDetailService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDto user = userService.findByUsername(username);
    if (user != null) {
      return new UserDetailsImpl(user);
    } else {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }
  }
}