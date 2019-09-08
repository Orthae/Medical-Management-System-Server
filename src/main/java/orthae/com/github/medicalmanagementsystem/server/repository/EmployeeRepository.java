package orthae.com.github.medicalmanagementsystem.server.repository;

import orthae.com.github.medicalmanagementsystem.server.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeRepository {
    List<Employee> find();
    List<Employee> find(Map<String, String> params);
    Employee find(int id);
    Employee find(String username);
    boolean isEmailUnique(int id, String email);
    boolean isUsernameUnique(int id, String username);
    void save(Employee employee);
    void delete(Employee employee);
}

