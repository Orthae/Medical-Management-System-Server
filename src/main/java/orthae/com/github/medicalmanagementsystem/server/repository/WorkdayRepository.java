package orthae.com.github.medicalmanagementsystem.server.repository;

import orthae.com.github.medicalmanagementsystem.server.entity.employee.Workday;

import java.util.List;

public interface WorkdayRepository {
//    Workday getById();
    List<Workday> getAll();
    List<Workday> search(int employeeId);

}
