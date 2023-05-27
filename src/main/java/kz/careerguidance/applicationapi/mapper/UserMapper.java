package kz.careerguidance.applicationapi.mapper;

import kz.careerguidance.applicationapi.dto.*;
import kz.careerguidance.applicationapi.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(UserEntity user);

    UpdateUserDto toUpdateUserDto(UserEntity user);

    UserEntity toUserEntity(UserDto userDto);

    UserEntity fromCreateUserDTOtoUserEntity(CreateUserDto createUserDto);

    LoginDto toLoginDto(RegistrationDto registrationDto);
}
