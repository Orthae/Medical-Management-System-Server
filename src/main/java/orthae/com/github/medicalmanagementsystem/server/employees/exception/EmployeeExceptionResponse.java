package orthae.com.github.medicalmanagementsystem.server.employees.exception;

import lombok.Getter;

@Getter
class EmployeeExceptionResponse {

    private long timestamp;
    private String exceptionType;
    private String message;

    EmployeeExceptionResponse(RuntimeException e){
        this.timestamp = System.currentTimeMillis() / 1000;
        this.exceptionType = e.getClass().getSimpleName();
        this.message = e.getMessage();
    }
}
