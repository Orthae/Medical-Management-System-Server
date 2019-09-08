package orthae.com.github.medicalmanagementsystem.server.employees.validator;

import org.springframework.beans.factory.annotation.Autowired;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDetailsDto;
import orthae.com.github.medicalmanagementsystem.server.repository.EmployeeRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, EmployeeDetailsDto> {

    private EmployeeRepository employeeRepository;

    @Autowired
    public UniqueUsernameValidator(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean isValid(EmployeeDetailsDto value, ConstraintValidatorContext context) {
        return employeeRepository.isUsernameUnique(value.getId(), value.getUsername());
    }
}
