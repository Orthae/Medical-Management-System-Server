package orthae.com.github.medicalmanagementsystem.server.employees.validator;

import org.springframework.beans.factory.annotation.Autowired;
import orthae.com.github.medicalmanagementsystem.server.repository.EmployeeRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private EmployeeRepository employeeRepository;

    @Autowired
    public UniqueEmailValidator(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if(email != null){
            return employeeRepository.isEmailUnique(email);
        }
        return true;
    }
}
