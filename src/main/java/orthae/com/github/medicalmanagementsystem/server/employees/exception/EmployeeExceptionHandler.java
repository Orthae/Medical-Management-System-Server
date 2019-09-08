package orthae.com.github.medicalmanagementsystem.server.employees.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import orthae.com.github.medicalmanagementsystem.server.employees.exception.type.EmployeeNotFound;

@ControllerAdvice
public class EmployeeExceptionHandler {

//  TODO Rewrite to utilize ExceptionResponse class
    @ExceptionHandler
    public ResponseEntity<EmployeeExceptionResponse> handleException(EmployeeNotFound e){
     return new ResponseEntity<>(new EmployeeExceptionResponse(e), HttpStatus.NOT_FOUND);
    }

}
