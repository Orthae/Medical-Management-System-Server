package orthae.com.github.medicalmanagementsystem.server.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.core.Employee;
import orthae.com.github.medicalmanagementsystem.core.EmployeeDAO;
import orthae.com.github.medicalmanagementsystem.core.EmployeeService;

import java.util.List;

// TODO Add validation

@Service
public class EmployeeServerService implements EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServerService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Transactional
    @Override
    public List<Employee> getEmployee(String name, String surname) {
        if (name == null && surname == null)
            return employeeDAO.getEmployee();
        else
            return employeeDAO.getEmployee(name, surname);
    }

    @Transactional
    @Override
    public Employee getEmployee(int id) {
        return employeeDAO.getEmployee(id);
    }

    @Transactional
    @Override
    public void createEmployee(Employee employee) {
        employee.setId(0);
        employeeDAO.saveEmployee(employee);
    }

    @Transactional
    @Override
    public void deleteEmployee(int id) {
        employeeDAO.deleteEmployee(id);
    }

    @Transactional
    @Override
    public void updateEmployee(Employee employee) {
        employeeDAO.saveEmployee(employee);
    }
}
