package orthae.com.github.medicalmanagementsystem.server.repository;

import orthae.com.github.medicalmanagementsystem.server.entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> find();
    List<Employee> find(String name, String surname);
    Employee find(int id);
    Employee find(String username);
    void save(Employee employee);
    void delete(Employee employee);
}

