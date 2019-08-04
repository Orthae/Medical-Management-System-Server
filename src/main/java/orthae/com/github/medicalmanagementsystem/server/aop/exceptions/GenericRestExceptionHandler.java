package orthae.com.github.medicalmanagementsystem.server.aop.exceptions;

import org.hibernate.StaleObjectStateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class GenericRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<GenericRequestResponse> handleException(MethodArgumentNotValidException exc) {
        GenericRequestResponse response = new GenericRequestResponse();
        response.setMessage("Request is not valid");
        ArrayList<String> errorList = new ArrayList<>();
        for (FieldError error : exc.getBindingResult().getFieldErrors()) {
            errorList.add(error.getField() + " " + error.getDefaultMessage());
        }
        String[] errorArray = errorList.toArray(new String[0]);
        response.setErrors(errorArray);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<GenericRequestResponse> handleException(StaleObjectStateException exc){
        GenericRequestResponse response = new GenericRequestResponse();
        response.setMessage(exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
