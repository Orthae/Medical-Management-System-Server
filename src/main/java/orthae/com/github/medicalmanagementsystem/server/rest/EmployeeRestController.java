package orthae.com.github.medicalmanagementsystem.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orthae.com.github.medicalmanagementsystem.core.Employee;
import orthae.com.github.medicalmanagementsystem.core.EmployeeDAO;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeRestController {

    @Autowired
    EmployeeDAO employeeDAO;

    @GetMapping("employee/")
    public List<Employee> getEmployees(){
        return employeeDAO.getEmployee();
    }

}
