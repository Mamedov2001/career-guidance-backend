package kz.careerguidance.applicationapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class RegistrationDto {

    @NotNull(message = "Username shouldn't be empty")
    @Size(min = 3, max = 20, message = "Username must contain between 3 and 20 characters")
    private String username;

    @NotNull(message = "Email address should not be empty")
    @Email(message = "Invalid email address")
    private String email;

    @NotNull(message = "Password should not be empty")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[!@#$%^&*]).{6,}$", message = "Password should contain 1 Uppercase letter 1 digit and 1 special symbol and more than 6 characters")
    @Size(min = 6, max = 32, message = "Password must contain between 6 and 32 characters")
    private String password;

    @NotNull(message = "Location should not be empty")
    private String location;

}
