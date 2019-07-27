package orthae.com.github.medicalmanagementsystem.core;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployee();
    List<Employee> getEmployee(String name, String surname);
    Employee getEmployee(int id);
}
