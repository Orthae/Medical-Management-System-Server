package orthae.com.github.medicalmanagementsystem.server.employees.validator;

import org.springframework.beans.factory.annotation.Autowired;
import orthae.com.github.medicalmanagementsystem.server.repository.EmployeeRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private EmployeeRepository employeeRepository;

    @Autowired
    public UniqueUsernameValidator(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;

    }


    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if(username != null)
            return employeeRepository.isUsernameUnique(0,username);
        return true;
    }
}
