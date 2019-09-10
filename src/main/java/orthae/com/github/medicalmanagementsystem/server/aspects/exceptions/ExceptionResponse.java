package orthae.com.github.medicalmanagementsystem.server.aspects.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExceptionResponse {
    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
