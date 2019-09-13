package orthae.com.github.medicalmanagementsystem.server.employees.service;

import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeChangePasswordDto;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDetailsDto;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDetailsDto find(int id);
    List<EmployeeDto> find(String name, String surname, String username, String email);
    void create(EmployeeDetailsDto createEmployee);
    void activate(int id);
    void deactivate(int id);
    void changePassword(int id, EmployeeChangePasswordDto dto);
    void delete(int id);
    void update(EmployeeDetailsDto updateEmployee);



}
