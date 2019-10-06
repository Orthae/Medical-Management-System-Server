package orthae.com.github.medicalmanagementsystem.server.management.workdays.dto;

import lombok.Getter;
import lombok.Setter;
import orthae.com.github.medicalmanagementsystem.server.management.workdays.validator.MaxHour;
import orthae.com.github.medicalmanagementsystem.server.management.workdays.validator.MinHour;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class WorkdayDto {

    private int id;

    @NotNull(message = "Date can't be empty")
    private LocalDate date;

    @MaxHour(max = 16, message = "Can't start after 16")
    @MinHour(min = 8, message = "Can't before after 8")
    private LocalTime startHour;

    @MaxHour(max = 20, message = "Can't end after 20")
    @MinHour(min = 12, message = "Can't end before 12")
    private LocalTime endHour;
}
