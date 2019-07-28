package orthae.com.github.medicalmanagementsystem.server.employee.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.core.Employee;
import orthae.com.github.medicalmanagementsystem.core.EmployeeDAO;
import orthae.com.github.medicalmanagementsystem.core.EmployeeService;
import orthae.com.github.medicalmanagementsystem.server.employee.dto.CreateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employee.dto.UpdateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employee.entity.EmployeeDatabaseEntity;

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
    public void createEmployee(CreateEmployeeDTO employeeDTO) {
        ModelMapper mapper = new ModelMapper();
        EmployeeDatabaseEntity employeeEntity = mapper.map(employeeDTO, EmployeeDatabaseEntity.class);
        employeeDAO.saveEmployee(employeeEntity);
    }

    @Transactional
    @Override
    public void deleteEmployee(int id) {
        employeeDAO.deleteEmployee(id);
    }

    @Transactional
    @Override
    public void updateEmployee(UpdateEmployeeDTO employeeDTO) {
        ModelMapper mapper = new ModelMapper();
        EmployeeDatabaseEntity employeeEntity = mapper.map(employeeDTO, EmployeeDatabaseEntity.class);
        employeeDAO.saveEmployee(employeeEntity);
    }
}
