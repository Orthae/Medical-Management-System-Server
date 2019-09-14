package orthae.com.github.medicalmanagementsystem.server.employees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.aspects.Utility;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeChangePasswordDto;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDetailsDto;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDto;
import orthae.com.github.medicalmanagementsystem.server.employees.exception.type.EmployeeNotFound;
import orthae.com.github.medicalmanagementsystem.server.entity.Employee;
import orthae.com.github.medicalmanagementsystem.server.repository.EmployeeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;
    private Utility utility;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder,  Utility utility) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.utility = utility;
    }

    @Transactional
    @Override
    public EmployeeDetailsDto find(int id) {
        return utility.map(employeeRepository.find(id), EmployeeDetailsDto.class);
    }


    @Transactional
    @Override
    public List<EmployeeDto> find(String name, String surname, String username, String email) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("surname", surname);
        params.put("username", username);
        params.put("email", email);
        return utility.mapListEmployeeDto(employeeRepository.find(params), EmployeeDto.class);
    }

    @Transactional
    @Override
    public void create(EmployeeDetailsDto employeeDTO) {
        Employee employee = utility.map(employeeDTO, Employee.class);
        employee.setId(0);
        employee.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
        employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public void delete(int id) {
        Employee employee = employeeRepository.find(id);
        if(employee == null)
            throw new EmployeeNotFound("Couldn't find employee with id = " + id);
        employeeRepository.delete(employee);
    }

    @Transactional
    @Override
    public void update(EmployeeDetailsDto dto) {
        Employee employee = employeeRepository.find(dto.getId());
        utility.map(dto, employee);
        employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public void enable(int id) {
        employeeRepository.activate(id);
    }

    @Transactional
    @Override
    public void disable(int id) {
        employeeRepository.deactivate(id);
    }

    @Transactional
    @Override
    public void changePassword(int id, EmployeeChangePasswordDto dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        employeeRepository.changePassword(id, encodedPassword);
    }

}

