package orthae.com.github.medicalmanagementsystem.server.management.workdays.service;

import orthae.com.github.medicalmanagementsystem.server.management.workdays.dto.WorkdayDto;

import java.util.List;

public interface WorkdayService {

    List<WorkdayDto> getAll();
    List<WorkdayDto> getByEmployeeId(int employeeId);
    void createWorkday(int employeeId, WorkdayDto dto);
    void updateWorkday(WorkdayDto dto);

}
