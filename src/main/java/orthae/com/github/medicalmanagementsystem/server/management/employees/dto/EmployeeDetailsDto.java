package orthae.com.github.medicalmanagementsystem.server.management.employees.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import orthae.com.github.medicalmanagementsystem.server.entity.employee.Authority;
import orthae.com.github.medicalmanagementsystem.server.management.employees.validator.PasswordField;
import orthae.com.github.medicalmanagementsystem.server.management.employees.validator.UniqueEmail;
import orthae.com.github.medicalmanagementsystem.server.management.employees.validator.UniqueUsername;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@UniqueEmail
@UniqueUsername
@PasswordField
public class EmployeeDetailsDto {

    private int id;

    @NotBlank(message = "Name field can't be empty")
    @Length(max = 24, message = "Name field can't be longer than 32 characters")
    private String name;

    @NotBlank(message = "Surname field can't be empty")
    @Length(max = 24, message = "Name field can't be longer than 32 characters")
    private String surname;

    @NotBlank(message = "Username field can't be empty")
    @Length(max = 24, message = "Name field can't be longer than 32 characters")
    private String username;

    @NotBlank(message = "Email field can't be empty")
    @Length(max = 32, message = "Name field can't be longer than 32 characters")
    private String email;

    @Getter(onMethod_ = {@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)})
    private String password;

    private boolean enabled;
    private List<Authority> authorities;



}
