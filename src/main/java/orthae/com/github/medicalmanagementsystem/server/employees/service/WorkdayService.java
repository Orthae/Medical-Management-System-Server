package orthae.com.github.medicalmanagementsystem.server.employees.service;

import orthae.com.github.medicalmanagementsystem.server.employees.dto.WorkdayDto;

import java.util.List;

public interface WorkdayService {
    List<WorkdayDto> getAllByEmployeeId(int employeeId);

}
