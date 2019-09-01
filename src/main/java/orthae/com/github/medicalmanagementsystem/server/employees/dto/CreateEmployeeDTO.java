package orthae.com.github.medicalmanagementsystem.server.employees.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import orthae.com.github.medicalmanagementsystem.server.employees.validator.UniqueEmail;
import orthae.com.github.medicalmanagementsystem.server.employees.validator.UniqueUsername;
import orthae.com.github.medicalmanagementsystem.server.entity.Authority;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateEmployeeDTO {

    @NotBlank(message = "field can't be empty")
    private String name;

    @NotBlank(message = "field can't be empty")
    private String surname;

    @UniqueUsername
    @NotBlank(message = "field can't be empty")
    private String username;

    @UniqueEmail
    @NotBlank(message = "field can't be empty")
    private String email;

    @NotBlank(message = "field can't be empty")
    private String password;

    private List<Authority> authorities;
}
