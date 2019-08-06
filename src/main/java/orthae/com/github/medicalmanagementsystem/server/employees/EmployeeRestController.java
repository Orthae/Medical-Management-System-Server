package orthae.com.github.medicalmanagementsystem.server.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.CreateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.UpdateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employees.service.EmployeeService;
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

    @GetMapping("${rest.endpoint.employees}")
    public List<Employee> findAllEmployees(){
        return employeeService.findAll();
    }

    @GetMapping(value = "${rest.endpoint.employees}", params = {"name"})
    public List<Employee> findEmployeesByName(@RequestParam String name){
        return employeeService.findByName(name);
    }

    @GetMapping(value = "${rest.endpoint.employees}", params = {"surname"})
    public List<Employee> findEmployeesBySurname(@RequestParam String surname){
        return employeeService.findBySurname(surname);
    }

    @GetMapping(value = "${rest.endpoint.employees}", params = {"name","surname"})
    public List<Employee> findEmployeesByNameAndSurname(@RequestParam String name, @RequestParam String surname){
        return employeeService.findByNameAndSurname(name, surname);
    }

    @GetMapping("${rest.endpoint.employees}/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        return employeeService.findById(employeeId);
    }

    @PostMapping("${rest.endpoint.employees}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@Valid @RequestBody CreateEmployeeDTO employee){
        employeeService.create(employee);
    }

    @DeleteMapping("${rest.endpoint.employees}/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable int employeeId){
        employeeService.delete(employeeId);
    }

    @PutMapping("employees")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployee(@Valid @RequestBody UpdateEmployeeDTO employee){
        employeeService.update(employee);
    }
}
