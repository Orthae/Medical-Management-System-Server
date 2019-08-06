package orthae.com.github.medicalmanagementsystem.server.patients.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTO {

    private int id;
    private String socialSecurity;
    private String name;
    private String surname;
}
