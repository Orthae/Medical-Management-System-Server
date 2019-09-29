package orthae.com.github.medicalmanagementsystem.server.patients.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PatientDto {

    private int id;
    private Date birthDate;
    private String socialSecurity;
    private String name;
    private String surname;

}
