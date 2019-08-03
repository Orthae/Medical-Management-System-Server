package orthae.com.github.medicalmanagementsystem.server.employee.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.employee.dto.CreateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employee.dto.UpdateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.entity.Employee;
import orthae.com.github.medicalmanagementsystem.server.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional
    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    @Override
    public List<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Transactional
    @Override
    public List<Employee> findBySurname(String surname) {
        return employeeRepository.findBySurname(surname);
    }

    @Transactional
    @Override
    public List<Employee> findByNameAndSurname(String name, String surname) {
        return employeeRepository.findByNameAndSurname(name, surname);
    }

    @Transactional
    @Override
    public void create(CreateEmployeeDTO employeeDTO) {
        ModelMapper mapper = new ModelMapper();
        Employee employee = mapper.map(employeeDTO, Employee.class);
        employee.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
        employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public void delete(int id) {
        Employee employee = employeeRepository.findById(id);
        if(employee == null)
//  TODO Proper exception
            throw new RuntimeException("Not found");
        employeeRepository.delete(employee);
    }

    @Transactional
    public void update(UpdateEmployeeDTO dto) {
        ModelMapper mapper = new ModelMapper();
        String password;
        Employee employee = findById(dto.getId());
        if(dto.getPassword() == null)
            password = employee.getPassword();
        else
            password = passwordEncoder.encode(dto.getPassword());
        mapper.map(dto, employee);
        employee.setPassword(password);
        employeeRepository.save(employee);
    }

}
