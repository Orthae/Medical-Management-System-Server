package orthae.com.github.medicalmanagementsystem.server.employee.service;

import orthae.com.github.medicalmanagementsystem.server.employee.dto.CreateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employee.dto.UpdateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployees();
    Employee findEmployeeById(int id);
    List<Employee> findEmployeeByName(String name);
    List<Employee> findEmployeeBySurname(String surname);
    List<Employee> findEmployeeByNameAndSurname(String name, String surname);
    void createEmployee(CreateEmployeeDTO createEmployee);
    void deleteEmployee(int id);
    void updateEmployee(UpdateEmployeeDTO updateEmployee);



}
