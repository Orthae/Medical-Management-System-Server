package orthae.com.github.medicalmanagementsystem.server.management.employees.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private Boolean active;
    private Boolean enabled;

}
