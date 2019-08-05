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

    @GetMapping("employees")
    public List<Employee> findAllEmployees(){
        return employeeService.findAll();
    }

    @GetMapping(value = "employees", params = {"name"})
    public List<Employee> findEmployeesByName(@RequestParam String name){
        return employeeService.findByName(name);
    }

    @GetMapping(value = "employees", params = {"surname"})
    public List<Employee> findEmployeesBySurname(@RequestParam String surname){
        return employeeService.findBySurname(surname);
    }

    @GetMapping(value = "employees", params = {"name","surname"})
    public List<Employee> findEmployeesByNameAndSurname(@RequestParam String name, @RequestParam String surname){
        return employeeService.findByNameAndSurname(name, surname);
    }

    @GetMapping("employees/{id}")
    public Employee getEmployee(@PathVariable int id){
        return employeeService.findById(id);
    }

    @PostMapping("employees")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@Valid @RequestBody CreateEmployeeDTO employee){
        employeeService.create(employee);
    }

    @DeleteMapping("employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable int id){
        employeeService.delete(id);
    }

    @PutMapping("employees")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployee(@Valid @RequestBody UpdateEmployeeDTO employee){
        employeeService.update(employee);
    }
}
