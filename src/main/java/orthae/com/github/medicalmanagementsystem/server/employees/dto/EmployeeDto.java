package orthae.com.github.medicalmanagementsystem.server.employees.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    private int id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private boolean active;
}
