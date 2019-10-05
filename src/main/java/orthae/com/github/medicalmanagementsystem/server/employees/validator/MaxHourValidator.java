package orthae.com.github.medicalmanagementsystem.server.employees.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalTime;

public class MaxHourValidator implements ConstraintValidator<MaxHour, LocalTime> {

    private LocalTime maxHour;

    @Override
    public void initialize(MaxHour constraintAnnotation) {
        this.maxHour = LocalTime.of(constraintAnnotation.max(), 0);
    }

    @Override
    public boolean isValid(LocalTime value, ConstraintValidatorContext context) {
        return  maxHour.isAfter(value)|| maxHour.equals(value);
    }
}
