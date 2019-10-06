package orthae.com.github.medicalmanagementsystem.server.management.workdays.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalTime;

public class HourRangeValidator implements ConstraintValidator<HourRange, LocalTime> {

    private int open;
    private int closed;

    @Override
    public void initialize(HourRange constraintAnnotation) {
        this.open = constraintAnnotation.open();
        this.closed = constraintAnnotation.closed();
    }

    @Override
    public boolean isValid(LocalTime value, ConstraintValidatorContext context) {
        int hour = value.getHour();
        return (hour >= open && hour <= closed);
    }

}
