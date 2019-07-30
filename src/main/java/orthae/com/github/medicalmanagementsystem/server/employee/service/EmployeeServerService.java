package orthae.com.github.medicalmanagementsystem.server.employee.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.employee.dto.CreateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employee.dto.UpdateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employee.repository.EmployeeRepository;
import orthae.com.github.medicalmanagementsystem.server.entity.Employee;

import java.util.List;

@Service
public class EmployeeServerService implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServerService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAllEmployees();
    }

    @Transactional
    @Override
    public Employee findEmployeeById(int id) {
        return employeeRepository.findEmployeeById(id);
    }

    @Transactional
    @Override
    public List<Employee> findEmployeeByName(String name) {
        return employeeRepository.findEmployeesByName(name);
    }

    @Transactional
    @Override
    public List<Employee> findEmployeeBySurname(String surname) {
        return employeeRepository.findEmployeesBySurname(surname);
    }

    @Transactional
    @Override
    public List<Employee> findEmployeeByNameAndSurname(String name, String surname) {
        return employeeRepository.findEmployeesByNameAndSurname(name, surname);
    }

    @Transactional
    @Override
    public void createEmployee(CreateEmployeeDTO employeeDTO) {
        ModelMapper mapper = new ModelMapper();
        Employee employee = mapper.map(employeeDTO, Employee.class);
        employeeRepository.saveEmployee(employee);
    }

    @Transactional
    @Override
    public void deleteEmployee(int id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        if(employee == null)
//  TODO Proper exception
            throw new RuntimeException("Not found");
        employeeRepository.deleteEmployee(employee);
    }

    @Transactional
    public void updateEmployee(UpdateEmployeeDTO employeeDTO) {
        ModelMapper mapper = new ModelMapper();
        Employee employee = mapper.map(employeeDTO, Employee.class);
        employeeRepository.saveEmployee(employee);
    }

}
