package orthae.com.github.medicalmanagementsystem.server.employees.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdateEmployeeDTO {

    @Min(value = 1, message = "can't be lower than 1")
    private int id;

    @NotBlank(message = "field can't be empty")
    private String name;

    @NotBlank(message = "field can't be empty")
    private String surname;

    @NotBlank(message = "field can't be empty")
    private String username;

    private String password;
}

