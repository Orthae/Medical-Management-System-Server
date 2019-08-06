package orthae.com.github.medicalmanagementsystem.server.employees.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;


@Getter
@Setter
public class UpdateEmployeeDTO {

    @Min(value = 1, message = "can't be lower than 1")
    private int id;
    private String name;
    private String surname;
    private String username;
    private String password;
}

