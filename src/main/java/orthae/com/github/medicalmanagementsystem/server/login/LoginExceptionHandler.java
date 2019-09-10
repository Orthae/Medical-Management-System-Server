package orthae.com.github.medicalmanagementsystem.server.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import orthae.com.github.medicalmanagementsystem.server.aspects.exceptions.ExceptionResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class LoginExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException exception, HttpServletRequest request){
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage("Username or password incorrect.");
        response.setError(exception.getClass().getSimpleName());
        response.setPath(request.getServletPath());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setTimestamp(new Date());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

}
