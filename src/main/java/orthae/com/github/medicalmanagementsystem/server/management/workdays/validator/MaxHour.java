package orthae.com.github.medicalmanagementsystem.server.management.workdays.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MaxHourValidator.class)
@Target( ElementType.FIELD )
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxHour {
    String message() default "Invalid hour";
    int max();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
