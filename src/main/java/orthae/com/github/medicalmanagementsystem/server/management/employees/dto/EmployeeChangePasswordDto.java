package orthae.com.github.medicalmanagementsystem.server.management.employees.dto;

// TODO Add validator

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeChangePasswordDto {
    private String password;
    private String passwordCheck;
}
