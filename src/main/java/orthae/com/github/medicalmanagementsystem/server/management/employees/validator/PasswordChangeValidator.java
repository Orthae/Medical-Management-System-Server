package orthae.com.github.medicalmanagementsystem.server.management.employees.validator;

import orthae.com.github.medicalmanagementsystem.server.management.employees.dto.EmployeeChangePasswordDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordChangeValidator implements ConstraintValidator<PasswordChange, EmployeeChangePasswordDto> {
    @Override
    public boolean isValid(EmployeeChangePasswordDto value, ConstraintValidatorContext context) {
        String password = value.getPassword();
        String rePassword = value.getRePassword();
        return password.equals(rePassword);
    }
}
