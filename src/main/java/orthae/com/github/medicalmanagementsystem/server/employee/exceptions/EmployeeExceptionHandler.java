package orthae.com.github.medicalmanagementsystem.server.employee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EmployeeExceptionResponse> handleException(EmployeeNotFound e){
     return new ResponseEntity<>(new EmployeeExceptionResponse(e), HttpStatus.NOT_FOUND);
    }

}
