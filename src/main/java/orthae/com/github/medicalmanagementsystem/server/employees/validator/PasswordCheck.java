package orthae.com.github.medicalmanagementsystem.server.employees.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SuppressWarnings("unused")
@Constraint(validatedBy = PasswordCheckValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordCheck  {
    String message() default "Password field can't be empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
