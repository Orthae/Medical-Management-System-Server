package orthae.com.github.medicalmanagementsystem.server.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.CreateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDetailsDto;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.UpdateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employees.service.EmployeeService;

import javax.validation.Valid;
import java.util.List;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping("${rest.endpoint.path}")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "${rest.endpoint.employees}")
    public List<EmployeeDTO> findEmployeesByNameAndSurname(@RequestParam(required = false) String name, @RequestParam(required = false) String surname,
                                                           @RequestParam(required = false) String username, @RequestParam(required = false) String email) {
        return employeeService.find(name, surname, username, email);
    }

    @GetMapping("${rest.endpoint.employees}/{employeeId}")
    public EmployeeDetailsDto getEmployee(@PathVariable int employeeId) {
        return employeeService.find(employeeId);
    }

    @PostMapping("${rest.endpoint.employees}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@Valid @RequestBody CreateEmployeeDTO employee) {
        employeeService.create(employee);
    }

    @DeleteMapping("${rest.endpoint.employees}/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable int employeeId) {
        employeeService.delete(employeeId);
    }

    @PutMapping("${rest.endpoint.employees}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployee(@Valid @RequestBody UpdateEmployeeDTO employee) {
        employeeService.update(employee);
    }
}
