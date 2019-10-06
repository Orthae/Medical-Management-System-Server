package orthae.com.github.medicalmanagementsystem.server.repository;

import orthae.com.github.medicalmanagementsystem.server.entity.employee.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> search(String name, String surname, String username, String email, Boolean active, Boolean enabled);
    Employee getById(int id);
    Employee getByUsername(String username);
    boolean isEmailUnique(int id, String email);
    boolean isUsernameUnique(int id, String username);
    void enable(int id);
    void disable(int id);
    void changePassword(int id, String password);
    void save(Employee employee);
    void delete(Employee employee);
}

