package orthae.com.github.medicalmanagementsystem.server.management.workdays.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MinDurationValidator.class)
@Target( ElementType.TYPE )
@Retention(RetentionPolicy.RUNTIME)
public @interface MinDuration {
    String message() default "Duration not in range";
    int min();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
