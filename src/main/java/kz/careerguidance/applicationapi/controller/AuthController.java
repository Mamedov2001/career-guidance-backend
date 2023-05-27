package kz.careerguidance.applicationapi.controller;

import kz.careerguidance.applicationapi.config.jwt.JwtFilter;
import kz.careerguidance.applicationapi.dto.JwtToken;
import kz.careerguidance.applicationapi.dto.LoginDto;
import kz.careerguidance.applicationapi.dto.RegistrationDto;
import kz.careerguidance.applicationapi.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginDto login) {
    String jwt = authService.login(login);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
    return new ResponseEntity<>(new JwtToken(jwt), httpHeaders, HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody @Valid RegistrationDto registrationDto) {
    String jwt = authService.register(registrationDto);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
    return new ResponseEntity<>(new JwtToken(jwt), httpHeaders, HttpStatus.OK);
  }

}
