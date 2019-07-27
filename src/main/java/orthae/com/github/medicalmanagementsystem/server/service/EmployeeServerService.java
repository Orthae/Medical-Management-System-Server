package orthae.com.github.medicalmanagementsystem.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.core.Employee;
import orthae.com.github.medicalmanagementsystem.core.EmployeeDAO;
import orthae.com.github.medicalmanagementsystem.core.EmployeeService;

import java.util.List;


@Service
public class EmployeeServerService implements EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServerService(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    @Transactional
    @Override
    public List<Employee> getEmployee(){
        return employeeDAO.getEmployee();
    }

    @Transactional
    @Override
    public List<Employee> getEmployee(String name, String surname) {
        return employeeDAO.getEmployee(name, surname);
    }

    @Transactional
    @Override
    public Employee getEmployee(int id) {
        return employeeDAO.getEmployee(id);
    }
}
