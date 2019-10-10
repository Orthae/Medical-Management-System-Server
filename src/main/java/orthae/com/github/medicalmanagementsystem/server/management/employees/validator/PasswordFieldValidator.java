package orthae.com.github.medicalmanagementsystem.server.management.employees.validator;

import orthae.com.github.medicalmanagementsystem.server.management.employees.dto.EmployeeDetailsDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordFieldValidator implements ConstraintValidator<PasswordField, EmployeeDetailsDto> {
    @Override
    public boolean isValid(EmployeeDetailsDto value, ConstraintValidatorContext context) {
        return value.getId() != 0 || !value.getPassword().isEmpty();
    }
}
