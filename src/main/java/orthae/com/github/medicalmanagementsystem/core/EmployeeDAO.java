package orthae.com.github.medicalmanagementsystem.core;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> getEmployee();
    List<Employee> getEmployee(String name, String Surname);
    Employee getEmployee(int id);

}
