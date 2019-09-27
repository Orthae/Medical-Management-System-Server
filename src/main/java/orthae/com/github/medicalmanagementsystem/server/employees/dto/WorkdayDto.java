package orthae.com.github.medicalmanagementsystem.server.employees.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
public class WorkdayDto {

    private Date workdayDate;
    private LocalTime startHour;
    private LocalTime endHour;

}
