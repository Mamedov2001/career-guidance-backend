package kz.careerguidance.applicationapi.service.impl;

import kz.careerguidance.applicationapi.config.jwt.TokenProvider;
import kz.careerguidance.applicationapi.dto.LoginDto;
import kz.careerguidance.applicationapi.dto.RegistrationDto;
import kz.careerguidance.applicationapi.entity.UserEntity;
import kz.careerguidance.applicationapi.exceptions.NotFoundException;
import kz.careerguidance.applicationapi.exceptions.NotUniqueUsernameException;
import kz.careerguidance.applicationapi.mapper.UserMapper;
import kz.careerguidance.applicationapi.repository.UserRepository;
import kz.careerguidance.applicationapi.service.AuthService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

import static kz.careerguidance.applicationapi.utils.AuthoritiesConstants.USER;


@Service
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final TokenProvider tokenProvider;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;

  public AuthServiceImpl(AuthenticationManagerBuilder authenticationManagerBuilder,
                         TokenProvider tokenProvider,
                         UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
    this.authenticationManagerBuilder = authenticationManagerBuilder;
    this.tokenProvider = tokenProvider;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.userMapper = userMapper;
  }


  @Override
  public String login(LoginDto login) {

    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        login.getUsername(),
        login.getPassword()
    );

    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = tokenProvider.createToken(authentication, false);
    if (token == null) {
      throw new RuntimeException();
    }
    return token;
  }

  @Override
  public String register(RegistrationDto registrationDto) {
    Optional<UserEntity> userName = userRepository.findByUsername(registrationDto.getUsername());
    Optional<UserEntity> userEmail = userRepository.findByEmail(registrationDto.getEmail());

    if (userName.isPresent()) {
      throw new NotUniqueUsernameException("User with username " + registrationDto.getUsername() + " already exists");
    }

    if (userEmail.isPresent()) {
      throw new NotUniqueUsernameException("User with email " + registrationDto.getEmail() + " already exists");
    }

    var user = UserEntity.builder()
            .username(registrationDto.getUsername())
            .email(registrationDto.getEmail())
            .password(passwordEncoder.encode(registrationDto.getPassword()))
            .location(registrationDto.getLocation())
            .role(USER)
            .history(Collections.emptyList())
            .build();
    userRepository.save(user);
    return login(userMapper.toLoginDto(registrationDto));
  }
}
