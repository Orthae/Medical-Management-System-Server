package orthae.com.github.medicalmanagementsystem.server.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import orthae.com.github.medicalmanagementsystem.server.aspects.exceptions.ExceptionResponse;

@ControllerAdvice
public class LoginExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException e){
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage("Incorrect credentials");
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

}
