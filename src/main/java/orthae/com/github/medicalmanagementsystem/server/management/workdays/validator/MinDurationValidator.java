package orthae.com.github.medicalmanagementsystem.server.management.workdays.validator;

import orthae.com.github.medicalmanagementsystem.server.management.workdays.dto.WorkdayDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinDurationValidator implements ConstraintValidator<MinDuration, WorkdayDto> {

    private int minDuration;

    @Override
    public void initialize(MinDuration constraintAnnotation) {
        this.minDuration = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(WorkdayDto value, ConstraintValidatorContext context) {
        int startHour = value.getStartHour().getHour();
        int endHour = value.getEndHour().getHour();
        return endHour - startHour >= minDuration;
    }
}
