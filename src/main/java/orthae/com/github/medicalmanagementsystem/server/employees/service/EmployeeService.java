package orthae.com.github.medicalmanagementsystem.server.employees.service;

import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeChangePasswordDto;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDetailsDto;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDto;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.WorkdayDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDetailsDto get(int id);
    List<EmployeeDto> search(String name, String surname, String username, String email, Boolean active, Boolean enabled);
    void create(EmployeeDetailsDto createEmployee);
    void enable(int id);
    void disable(int id);
    void changePassword(int id, EmployeeChangePasswordDto dto);
    void addWorkday(int id, WorkdayDto... dto);
    void delete(int id);
    void update(EmployeeDetailsDto updateEmployee);



}
