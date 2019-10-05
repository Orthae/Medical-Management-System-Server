package orthae.com.github.medicalmanagementsystem.server.repository;

import orthae.com.github.medicalmanagementsystem.server.entity.employee.Workday;

import java.util.List;

public interface WorkdayRepository {

    List<Workday> getAll();
    List<Workday> getByEmployeeId(int employeeId);
    void save(Workday workday);
}
