package orthae.com.github.medicalmanagementsystem.server.management.workdays.dto;

import lombok.Getter;
import lombok.Setter;
import orthae.com.github.medicalmanagementsystem.server.management.workdays.validator.HourRange;
import orthae.com.github.medicalmanagementsystem.server.management.workdays.validator.MinDuration;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@MinDuration(min = 2, message = "Can't work less than 2 hours.")
public class WorkdayDto {

    private int id;

    @NotNull(message = "Date can't be empty.")
    private LocalDate date;

    @HourRange(open = 8, closed = 20, message = "Start hour not in range of 8 to 20")
    private LocalTime startHour;

    @HourRange(open = 8, closed = 20, message = "End hour not in range of 8 to 20")
    private LocalTime endHour;
}
