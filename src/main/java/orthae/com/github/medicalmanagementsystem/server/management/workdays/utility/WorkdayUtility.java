package orthae.com.github.medicalmanagementsystem.server.management.workdays.utility;

import org.springframework.stereotype.Component;
import orthae.com.github.medicalmanagementsystem.server.entity.employee.Workday;
import orthae.com.github.medicalmanagementsystem.server.management.workdays.dto.WorkdayDto;

import java.time.LocalTime;

@Component
public class WorkdayUtility {

    public WorkdayDto map(Workday workday) {
        WorkdayDto dto = new WorkdayDto();
        dto.setId(workday.getId());
        dto.setStartHour(workday.getStartHour());
        dto.setEndHour(workday.getEndHour());
        dto.setDate(workday.getDate());
        return dto;
    }

    public Workday map(WorkdayDto dto) {
        Workday workday = new Workday();
        workday.setId(dto.getId());
        workday.setStartHour(dto.getStartHour());
        workday.setEndHour(dto.getEndHour());
        workday.setDate(dto.getDate());
        return workday;
    }

    public boolean hourCollision(Workday database, WorkdayDto dto) {
        LocalTime startHourDb = database.getStartHour();
        LocalTime startHourDto = dto.getStartHour();
        LocalTime endHourDb = database.getEndHour();
        LocalTime endHourDto = dto.getEndHour();
        return (startHourDb.isAfter(startHourDto) && endHourDb.isBefore(endHourDto)) || startHourDb.equals(startHourDto) || startHourDb.equals(endHourDto) ||
                endHourDb.equals(startHourDto) || endHourDb.equals(endHourDto);
    }
}
