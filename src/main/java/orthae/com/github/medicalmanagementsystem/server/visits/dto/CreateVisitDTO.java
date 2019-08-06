package orthae.com.github.medicalmanagementsystem.server.visits.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateVisitDTO {

    @JsonIgnore
    private long date;

    @Min(1)
    private int patientId;
    @NotBlank
    private String visitType;

    public CreateVisitDTO(){
        this.date = System.currentTimeMillis() / 1000;
    }


}



