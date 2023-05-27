package kz.careerguidance.applicationapi.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginDto {

  @NotNull(message = "Username should not be empty")
  private String username;

  @NotNull(message = "Password should not be empty")
  private String password;

}
