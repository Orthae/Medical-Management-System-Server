package orthae.com.github.medicalmanagementsystem.server.management.workdays.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import orthae.com.github.medicalmanagementsystem.server.aspects.exceptions.ExceptionResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class WorkdayExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(WorkdayHourCollision e, HttpServletRequest request){
        ExceptionResponse response = new ExceptionResponse();
        response.setTimestamp(new Date());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setError(e.getClass().getSimpleName());
        response.setMessage(e.getMessage());
        response.setPath(request.getServletPath());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
