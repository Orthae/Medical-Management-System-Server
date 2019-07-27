package orthae.com.github.medicalmanagementsystem.core;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> getEmployee();
    List<Employee> getEmployee(String name, String surname);
    Employee getEmployee(int id);
    void saveEmployee(Employee employee);
    void deleteEmployee(int id);

}
