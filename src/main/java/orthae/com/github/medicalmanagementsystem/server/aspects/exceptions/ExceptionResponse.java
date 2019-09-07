package orthae.com.github.medicalmanagementsystem.server.aspects.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
    private long timestamp;
    private int statusCode;
    private String requestType;
    private String message;
    private String path;
}
