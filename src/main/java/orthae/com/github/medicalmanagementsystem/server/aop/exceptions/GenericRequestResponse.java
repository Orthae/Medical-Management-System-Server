package orthae.com.github.medicalmanagementsystem.server.aop.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericRequestResponse {
    String message;
    String[] errors;
}
