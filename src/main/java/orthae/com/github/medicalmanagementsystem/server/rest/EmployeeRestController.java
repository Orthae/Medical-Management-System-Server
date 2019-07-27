package orthae.com.github.medicalmanagementsystem.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import orthae.com.github.medicalmanagementsystem.core.Employee;
import orthae.com.github.medicalmanagementsystem.core.EmployeeDAO;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeRestController {
//  TODO Move calls from DAO to Service
    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeRestController(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    @GetMapping("employee/")
    public List<Employee> getEmployees(){
        return employeeDAO.getEmployee();
    }

    @GetMapping("employee")
    public List<Employee> getEmployeesQuery(@RequestParam(required = false) String name, @RequestParam(required = false) String surname){
        return employeeDAO.getEmployee(name, surname);
    }


    @PostMapping("employee/")
    public void addEmployee(){



    }

}
