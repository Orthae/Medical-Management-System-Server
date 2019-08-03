package orthae.com.github.medicalmanagementsystem.server.repository;

import orthae.com.github.medicalmanagementsystem.server.entity.Employee;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> findAll();
    List<Employee> findByName(String name);
    List<Employee> findBySurname(String surname);
    List<Employee> findByNameAndSurname(String name, String surname);
    Employee findById(int id);
    Employee findByUsername(String username);
    void save(Employee employee);
    void delete(Employee employee);
}

