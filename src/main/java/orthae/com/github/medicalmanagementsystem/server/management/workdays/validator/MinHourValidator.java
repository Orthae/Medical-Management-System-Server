package orthae.com.github.medicalmanagementsystem.server.management.workdays.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalTime;

public class MinHourValidator implements ConstraintValidator<MinHour, LocalTime> {

    private LocalTime minHour;

    @Override
    public void initialize(MinHour constraintAnnotation) {
    this.minHour = LocalTime.of(constraintAnnotation.min(), 0);
    }

    @Override
    public boolean isValid(LocalTime value, ConstraintValidatorContext context) {
        return minHour.isBefore(value) || minHour.equals(value) ;
    }
}
