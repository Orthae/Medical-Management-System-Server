package orthae.com.github.medicalmanagementsystem.server.management.workdays.service;

import orthae.com.github.medicalmanagementsystem.server.entity.employee.Workday;
import orthae.com.github.medicalmanagementsystem.server.management.workdays.dto.WorkdayDto;

import java.util.List;

public interface WorkdayService {

    List<WorkdayDto> getByEmployeeIdAndMonth(int employeeId, int month, int year);
    List<Workday> getByParams(String username, String fromDate, String toDate);
    void createWorkday(int employeeId, WorkdayDto dto);
    void updateWorkday(WorkdayDto dto);
    void deleteWorkday(int workdayId);
}
