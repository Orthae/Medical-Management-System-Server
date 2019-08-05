package orthae.com.github.medicalmanagementsystem.server.employees.exceptions.type;


public class EmployeeNotFound extends RuntimeException {

    private String message;

    public EmployeeNotFound(String message){
        super();
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }

}
