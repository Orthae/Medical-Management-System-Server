package orthae.com.github.medicalmanagementsystem.server.visits.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateVisitDTO {

    private int id;
    private long date;
    private int patientId;
    private String visitType;



}
