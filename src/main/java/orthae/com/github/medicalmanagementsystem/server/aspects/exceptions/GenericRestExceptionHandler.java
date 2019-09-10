package orthae.com.github.medicalmanagementsystem.server.aspects.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class GenericRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException exc, HttpServletRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        StringBuilder builder = new StringBuilder("Validation of request failed:\n");
        for (ObjectError error : exc.getBindingResult().getAllErrors()) {
            builder.append((error.getDefaultMessage()));
            builder.append("\n");
        }
        response.setTimestamp(new Date());
        response.setMessage(builder.toString().trim());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setPath(request.getServletPath());
        response.setError(exc.getClass().getSimpleName());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
