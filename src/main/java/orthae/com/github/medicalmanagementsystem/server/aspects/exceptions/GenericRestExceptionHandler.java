package orthae.com.github.medicalmanagementsystem.server.aspects.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

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
        response.setTimestamp(System.currentTimeMillis());
        response.setMessage(builder.toString().trim());
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setPath(request.getServletPath());
        response.setRequestType(request.getMethod());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler
//    public ResponseEntity<ExceptionResponse> handleException(StaleObjectStateException exc){
//        ExceptionResponse response = new ExceptionResponse();
//        response.setMessage(exc.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }

}
