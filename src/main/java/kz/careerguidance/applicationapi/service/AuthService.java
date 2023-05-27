package kz.careerguidance.applicationapi.service;


import kz.careerguidance.applicationapi.dto.LoginDto;
import kz.careerguidance.applicationapi.dto.RegistrationDto;

public interface AuthService {

  String login(LoginDto login);
  String register(RegistrationDto registrationDto);

}
