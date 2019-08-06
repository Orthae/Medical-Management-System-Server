package orthae.com.github.medicalmanagementsystem.server.employees.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.aop.Utility;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.CreateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.UpdateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employees.exceptions.type.EmployeeNotFound;
import orthae.com.github.medicalmanagementsystem.server.entity.Employee;
import orthae.com.github.medicalmanagementsystem.server.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;
    private Utility utility;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, Utility utility) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.utility = utility;
    }

    @Transactional
    @Override
    public List<EmployeeDTO> findAll() {
        return utility.mapAll(employeeRepository.findAll(), EmployeeDTO.class);
    }

    @Transactional
    @Override
    public EmployeeDTO findById(int id) {
        return utility.map(employeeRepository.findById(id), EmployeeDTO.class);
    }

    @Transactional
    @Override
    public List<EmployeeDTO> findByName(String name) {
        return utility.mapAll(employeeRepository.findByName(name), EmployeeDTO.class);
    }

    @Transactional
    @Override
    public List<EmployeeDTO> findBySurname(String surname) {
        return utility.mapAll(employeeRepository.findBySurname(surname), EmployeeDTO.class);
    }

    @Transactional
    @Override
    public List<EmployeeDTO> findByNameAndSurname(String name, String surname) {
        return utility.mapAll(employeeRepository.findByNameAndSurname(name, surname), EmployeeDTO.class);
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
            throw new EmployeeNotFound("Couldn't find employee with id = " + id);
        employeeRepository.delete(employee);
    }

    @Transactional
    @Override
    public void update(UpdateEmployeeDTO dto) {
        ModelMapper mapper = new ModelMapper();
        String password;
        Employee employee = employeeRepository.findById(dto.getId());
        if(dto.getPassword() == null)
            password = employee.getPassword();
        else
            password = passwordEncoder.encode(dto.getPassword());
        mapper.map(dto, employee);
        employee.setPassword(password);
        employeeRepository.save(employee);
    }

}
