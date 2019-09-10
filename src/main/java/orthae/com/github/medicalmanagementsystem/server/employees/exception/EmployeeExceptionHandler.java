package orthae.com.github.medicalmanagementsystem.server.employees.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import orthae.com.github.medicalmanagementsystem.server.aspects.exceptions.ExceptionResponse;
import orthae.com.github.medicalmanagementsystem.server.employees.exception.type.EmployeeNotFound;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class EmployeeExceptionHandler {

//  TODO Rewrite to utilize ExceptionResponse class
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(EmployeeNotFound e, HttpServletRequest request){
     ExceptionResponse response = new ExceptionResponse();
     response.setTimestamp(new Date());
     response.setStatus(HttpStatus.NOT_FOUND.value());
     response.setError(e.getClass().getSimpleName());
     response.setMessage(e.getMessage());
     response.setPath(request.getServletPath());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
