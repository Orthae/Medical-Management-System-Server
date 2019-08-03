package orthae.com.github.medicalmanagementsystem.server.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import orthae.com.github.medicalmanagementsystem.server.employee.dto.CreateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employee.dto.UpdateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employee.service.EmployeeService;
import orthae.com.github.medicalmanagementsystem.server.entity.Employee;

import javax.validation.Valid;
import java.util.List;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping("${rest.endpoint.path}")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("employee")
    public List<Employee> findAllEmployees(){
        return employeeService.findAll();
    }

    @GetMapping(value = "employee", params = {"name"})
    public List<Employee> findEmployeesByName(@RequestParam String name){
        return employeeService.findByName(name);
    }

    @GetMapping(value = "employee", params = {"surname"})
    public List<Employee> findEmployeesBySurname(@RequestParam String surname){
        return employeeService.findBySurname(surname);
    }

    @GetMapping(value = "employee", params = {"name","surname"})
    public List<Employee> findEmployeesByNameAndSurname(@RequestParam String name, @RequestParam String surname){
        return employeeService.findByNameAndSurname(name, surname);
    }

    @GetMapping("employee/{id}")
    public Employee getEmployee(@PathVariable int id){
        return employeeService.findById(id);
    }

    @PostMapping("employee")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@Valid @RequestBody CreateEmployeeDTO employee){
        employeeService.create(employee);
    }

    @DeleteMapping("employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable int id){
        employeeService.delete(id);
    }

    @PutMapping("employee")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployee(@Valid @RequestBody UpdateEmployeeDTO employee){
        employeeService.update(employee);
    }
}
