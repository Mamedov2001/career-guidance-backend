package kz.careerguidance.applicationapi.service;


import kz.careerguidance.applicationapi.dto.CreateUserDto;
import kz.careerguidance.applicationapi.dto.UpdateUserDto;
import kz.careerguidance.applicationapi.dto.UserDto;

import java.util.List;

public interface UserService {
  UserDto findByUsername(String username);
  void delete(Long id);
  UserDto create(CreateUserDto createUserDto);
  List<UserDto> findAll();
  UserDto update(Long id, UpdateUserDto updateUserDto);
  UserDto findById(Long id);
}
