package orthae.com.github.medicalmanagementsystem.server.employees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.aspects.Utility;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.CreateEmployeeDto;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDetailsDto;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.UpdateEmployeeDTO;
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
    public List<EmployeeDTO> find(String name, String surname, String username, String email) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("surname", surname);
        params.put("username", username);
        params.put("email", email);
        return utility.mapAll(employeeRepository.find(params), EmployeeDTO.class);
    }

    @Transactional
    @Override
    public void create(CreateEmployeeDto employeeDTO) {
        Employee employee = utility.map(employeeDTO, Employee.class);
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
    public void update(UpdateEmployeeDTO dto) {
        Employee employee = employeeRepository.find(dto.getId());
        utility.map(dto, employee);
        employeeRepository.save(employee);
    }

}
