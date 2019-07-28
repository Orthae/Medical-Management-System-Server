package orthae.com.github.medicalmanagementsystem.server.aop.exceptions;

import org.hibernate.StaleObjectStateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericRestExceptionHandler {

//  TODO Make good looking message
    @ExceptionHandler
    public ResponseEntity<BadRequestResponse> handleException(MethodArgumentNotValidException exc){
        BadRequestResponse response = new BadRequestResponse();
        StringBuilder builder = new StringBuilder(50);
        for(FieldError error : exc.getBindingResult().getFieldErrors()){
            builder.append(error.getField());
            builder.append(" is not valid");
        }
        response.setMessage(builder.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<BadRequestResponse> handleException(StaleObjectStateException exc){
        BadRequestResponse response = new BadRequestResponse();
        response.setMessage(exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
