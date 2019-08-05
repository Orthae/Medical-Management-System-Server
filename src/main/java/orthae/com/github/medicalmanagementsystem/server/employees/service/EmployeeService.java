package orthae.com.github.medicalmanagementsystem.server.employees.service;

import orthae.com.github.medicalmanagementsystem.server.employees.dto.CreateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.UpdateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
    Employee findById(int id);
    List<Employee> findByName(String name);
    List<Employee> findBySurname(String surname);
    List<Employee> findByNameAndSurname(String name, String surname);
    void create(CreateEmployeeDTO createEmployee);
    void delete(int id);
    void update(UpdateEmployeeDTO updateEmployee);



}
