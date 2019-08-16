package orthae.com.github.medicalmanagementsystem.server.employees.service;

import orthae.com.github.medicalmanagementsystem.server.employees.dto.CreateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.UpdateEmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO find    (int id);
    List<EmployeeDTO> find(String name, String surname);
    void create(CreateEmployeeDTO createEmployee);
    void delete(int id);
    void update(UpdateEmployeeDTO updateEmployee);



}
