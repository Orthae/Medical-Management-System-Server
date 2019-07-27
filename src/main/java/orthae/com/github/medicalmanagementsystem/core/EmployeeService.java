package orthae.com.github.medicalmanagementsystem.core;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployee(String name, String surname);
    Employee getEmployee(int id);
    void createEmployee(Employee employee);
    void deleteEmployee(int id);
    void updateEmployee(Employee employee);
}
