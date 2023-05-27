package kz.careerguidance.applicationapi.dto;

import kz.careerguidance.applicationapi.entity.History;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static kz.careerguidance.applicationapi.utils.AuthoritiesConstants.USER;

@Data
@Accessors(chain = true)
public class UserDto {

  private Long id;

  @NotNull(message = "Username shouldn't be empty")
  @Size(min = 3, max = 20, message = "Username must contain between 3 and 20 characters")
  private String username;

  @NotNull(message = "Email address should not be empty")
  @Email(message = "Invalid email address")
  private String email;

  @NotNull(message = "Password shouldn't be empty")
  private String password;

  private String role = USER;

  @NotNull(message = "Location should not be empty")
  private String location;

  private List<History> history;

}
