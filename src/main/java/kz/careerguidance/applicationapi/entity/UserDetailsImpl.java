package kz.careerguidance.applicationapi.entity;

import kz.careerguidance.applicationapi.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public record UserDetailsImpl(UserDto userDto) implements UserDetails {

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(
        new SimpleGrantedAuthority(userDto.getRole())); // доступные возможности
  }

  @Override
  public String getPassword() {
    return this.userDto.getPassword();
  }

  @Override
  public String getUsername() {
    return this.userDto.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true; // аккаунт не просрочен
  }

  @Override
  public boolean isAccountNonLocked() {
    return true; // аккаунт не заблокирован
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true; // роль не просрочена
  }

  @Override
  public boolean isEnabled() {
    return true; // аккаунт доступен и работает
  }
}
