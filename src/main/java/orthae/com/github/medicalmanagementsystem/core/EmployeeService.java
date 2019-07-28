package orthae.com.github.medicalmanagementsystem.core;

import orthae.com.github.medicalmanagementsystem.server.employee.dto.CreateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employee.dto.UpdateEmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployee(String name, String surname);
    Employee getEmployee(int id);
    void createEmployee(CreateEmployeeDTO employee);
    void deleteEmployee(int id);
    void updateEmployee(UpdateEmployeeDTO employee);
}
