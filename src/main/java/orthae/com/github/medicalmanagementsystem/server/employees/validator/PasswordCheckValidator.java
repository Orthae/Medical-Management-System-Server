package orthae.com.github.medicalmanagementsystem.server.employees.validator;

import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDetailsDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordCheckValidator implements ConstraintValidator<PasswordCheck, EmployeeDetailsDto> {
    @Override
    public boolean isValid(EmployeeDetailsDto value, ConstraintValidatorContext context) {
        return value.getId() != 0 || !value.getPassword().isEmpty();
    }
}
