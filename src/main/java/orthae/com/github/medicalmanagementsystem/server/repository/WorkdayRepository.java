package orthae.com.github.medicalmanagementsystem.server.repository;

import orthae.com.github.medicalmanagementsystem.server.entity.employee.Workday;

import java.time.LocalDate;
import java.util.List;

public interface WorkdayRepository {

    List<Workday> getAll();
    List<Workday> getByEmployeeId(int employeeId);
    List<Workday> getByEmployeeIdAndDate(int employeeId, LocalDate date);
    void save(Workday workday);
}
