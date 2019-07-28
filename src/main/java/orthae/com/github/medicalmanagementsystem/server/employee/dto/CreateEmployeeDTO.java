package orthae.com.github.medicalmanagementsystem.server.employee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CreateEmployeeDTO {

    @NotBlank(message = "field can't be empty")
    private String name;

    @NotBlank(message = "field can't be empty")
    private String surname;

    @NotBlank(message = "field can't be empty")
    private String username;

    @NotBlank(message = "field can't be empty")
    private String password;
}
