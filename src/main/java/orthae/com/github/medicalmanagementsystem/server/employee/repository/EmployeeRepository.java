package orthae.com.github.medicalmanagementsystem.server.employee.repository;

import orthae.com.github.medicalmanagementsystem.server.entity.Employee;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> findAllEmployees();
    List<Employee> findEmployeesByName(String name);
    List<Employee> findEmployeesBySurname(String surname);
    List<Employee> findEmployeesByNameAndSurname(String name, String surname);
    Employee findEmployeeById(int id);
    Employee findEmployeeByUsername(String username);
    void saveEmployee(Employee employee);
    void deleteEmployee(Employee employee);
}
