package orthae.com.github.medicalmanagementsystem.server.employees.dto;

import lombok.Getter;
import lombok.Setter;
import orthae.com.github.medicalmanagementsystem.server.entity.Authority;

import java.util.List;

@Getter
@Setter
public class EmployeeDetailsDto {
    private int id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private List<Authority> authorities;
}
