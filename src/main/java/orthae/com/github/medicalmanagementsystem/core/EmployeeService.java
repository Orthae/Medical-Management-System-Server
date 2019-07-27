package orthae.com.github.medicalmanagementsystem.core;

import orthae.com.github.medicalmanagementsystem.server.entity.EmployeeDatabaseEntity;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployee(String name, String surname);
    Employee getEmployee(int id);
    void createEmployee(EmployeeDatabaseEntity employee);
}
