package orthae.com.github.medicalmanagementsystem.server.employees.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import orthae.com.github.medicalmanagementsystem.server.employees.validator.UniqueEmail;
import orthae.com.github.medicalmanagementsystem.server.employees.validator.UniqueUsername;
import orthae.com.github.medicalmanagementsystem.server.entity.Authority;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateEmployeeDto {

    @NotBlank(message = "Name field can't be empty")
    @Length(max = 24, message = "Name field can't be longer than 32 characters")
    private String name;

    @NotBlank(message = "Surname field can't be empty")
    @Length(max = 24, message = "Name field can't be longer than 32 characters")
    private String surname;

    @UniqueUsername
    @NotBlank(message = "Username field can't be empty")
    @Length(max = 24, message = "Name field can't be longer than 32 characters")
    private String username;

    @UniqueEmail
    @NotBlank(message = "Email field can't be empty")
    @Length(max = 32, message = "Name field can't be longer than 32 characters")
    private String email;

    @NotBlank(message = "Password field can't be empty")
    private String password;

    private List<Authority> authorities;
}
