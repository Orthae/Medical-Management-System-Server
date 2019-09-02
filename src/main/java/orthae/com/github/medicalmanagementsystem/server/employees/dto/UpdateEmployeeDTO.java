package orthae.com.github.medicalmanagementsystem.server.employees.dto;

import lombok.Getter;
import lombok.Setter;
import orthae.com.github.medicalmanagementsystem.server.entity.Authority;

import javax.validation.constraints.Min;
import java.util.Set;

// TODO add class validator

@Getter
@Setter
public class UpdateEmployeeDTO {

    @Min(value = 1, message = "can't be lower than 1")
    private int id;
    private String name;
    private String surname;

    private String username;


    private String email;
    private String password;

    private Set<Authority> authorities;
}

