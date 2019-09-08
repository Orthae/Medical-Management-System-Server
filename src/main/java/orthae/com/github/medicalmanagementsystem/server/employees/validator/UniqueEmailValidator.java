package orthae.com.github.medicalmanagementsystem.server.employees.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDetailsDto;
import orthae.com.github.medicalmanagementsystem.server.repository.EmployeeRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, EmployeeDetailsDto> {

    private EmployeeRepository employeeRepository;

    @Autowired
    public UniqueEmailValidator(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean isValid(EmployeeDetailsDto value, ConstraintValidatorContext context) {
            return employeeRepository.isEmailUnique(value.getId(), value.getEmail());
    }
}
