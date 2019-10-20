package orthae.com.github.medicalmanagementsystem.server.repository;

import orthae.com.github.medicalmanagementsystem.server.entity.employee.Workday;

import java.time.LocalDate;
import java.util.List;

public interface WorkdayRepository {

    Workday getById(int workdayId);
    List<Workday> getByEmployeeIdAndDate(int employeeId, LocalDate date);
    List<Workday> getByEmployeeIdAndMonth(int employeeId, int month, int year);
    List<Workday> searchByParams(String username, String from, String to);
    void save(Workday workday);
    void delete(Workday workday);
}
