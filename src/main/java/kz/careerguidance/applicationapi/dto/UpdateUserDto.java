package kz.careerguidance.applicationapi.dto;


import kz.careerguidance.applicationapi.entity.History;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

import static kz.careerguidance.applicationapi.utils.AuthoritiesConstants.USER;


@Data
@AllArgsConstructor
public class UpdateUserDto {

  @Size(min = 3, max = 20, message = "Username should contain between 3 and 20 characters")
  private String username;

  @Email(message = "Invalid email address")
  private String email;

  @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[!@#$%^&*]).{6,}$", message = "Password should contain 1 Uppercase letter 1 digit and 1 special symbol and more than 6 characters")
  @Size(min = 5, max = 32, message = "Password should contain between 6 and 32 characters")
  private String password;

  private String location;

  private String role = USER;

  private List<History> history;

}
