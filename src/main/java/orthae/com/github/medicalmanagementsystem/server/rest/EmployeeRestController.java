package orthae.com.github.medicalmanagementsystem.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import orthae.com.github.medicalmanagementsystem.core.Employee;
import orthae.com.github.medicalmanagementsystem.core.EmployeeDAO;
import orthae.com.github.medicalmanagementsystem.core.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("employee/")
    public List<Employee> getEmployee(){
        return employeeService.getEmployee();
    }

    @GetMapping("employee")
    public List<Employee> getEmployeesQuery(@RequestParam(required = false) String name, @RequestParam(required = false) String surname){
        return employeeService.getEmployee(name, surname);
    }

    @GetMapping("employee/{id}")
    public Employee getEmployee(@PathVariable int id){
        return employeeService.getEmployee(id);
    }


}
