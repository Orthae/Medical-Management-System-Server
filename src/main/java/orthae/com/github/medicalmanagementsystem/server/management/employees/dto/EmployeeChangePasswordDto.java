package orthae.com.github.medicalmanagementsystem.server.management.employees.dto;

// TODO Add validator

import lombok.Getter;
import lombok.Setter;
import orthae.com.github.medicalmanagementsystem.server.management.employees.validator.PasswordChange;

@Getter
@Setter
@PasswordChange
public class EmployeeChangePasswordDto {
    private String password;
    private String rePassword;
}
