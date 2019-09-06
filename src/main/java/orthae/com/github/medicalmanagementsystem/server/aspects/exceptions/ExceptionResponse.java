package orthae.com.github.medicalmanagementsystem.server.aspects.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
    String message;
    String[] errors;
}
