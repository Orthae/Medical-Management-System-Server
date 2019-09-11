package orthae.com.github.medicalmanagementsystem.server.employees.service;

import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDetailsDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDetailsDto find(int id);
    List<EmployeeDTO> find(String name, String surname, String username, String email);
    void create(EmployeeDetailsDto createEmployee);
    void activate(int id);
    void deactivate(int id);
    void delete(int id);
    void update(EmployeeDetailsDto updateEmployee);



}
