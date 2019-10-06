package orthae.com.github.medicalmanagementsystem.server.management.employees;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import orthae.com.github.medicalmanagementsystem.server.management.employees.dto.EmployeeChangePasswordDto;
import orthae.com.github.medicalmanagementsystem.server.management.employees.dto.EmployeeDetailsDto;
import orthae.com.github.medicalmanagementsystem.server.management.employees.dto.EmployeeDto;
import orthae.com.github.medicalmanagementsystem.server.management.employees.service.EmployeeService;

import javax.validation.Valid;
import java.util.List;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping("${rest.endpoint.path}")
public class EmployeeRestController {

    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "${rest.endpoint.employees}")
    public List<EmployeeDto> searchEmployees(@RequestParam(required = false) String name, @RequestParam(required = false) String surname,
                                             @RequestParam(required = false) String username, @RequestParam(required = false) String email,
                                             @RequestParam(required = false) Boolean active, @RequestParam(required = false) Boolean enabled) {
            return employeeService.search(name, surname, username, email, active, enabled);
    }

    @GetMapping("${rest.endpoint.employees}/{employeeId}")
    public EmployeeDetailsDto getEmployee(@PathVariable int employeeId) {
        return employeeService.get(employeeId);
    }

    @PostMapping("${rest.endpoint.employees}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@Valid @RequestBody EmployeeDetailsDto employee) {
        employeeService.create(employee);
    }

    @DeleteMapping("${rest.endpoint.employees}/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId) {
        employeeService.delete(employeeId);
    }

    @PutMapping("${rest.endpoint.employees}")
    public void updateEmployee(@Valid @RequestBody EmployeeDetailsDto employee) {
        employeeService.update(employee);
    }

    @PostMapping("${rest.endpoint.employees}/{employeeId}/active")
    public void enableEmployee(@PathVariable int employeeId){
        employeeService.enable(employeeId);
    }

    @DeleteMapping("${rest.endpoint.employees}/{employeeId}/active")
    public void disableEmployee(@PathVariable int employeeId){
        employeeService.disable(employeeId);
    }

    @PutMapping("${rest.endpoint.employees}/{employeeId}/password")
    public void changeEmployeePassword(@PathVariable int employeeId, @Valid @RequestBody EmployeeChangePasswordDto dto){
        employeeService.changePassword(employeeId, dto);
    }



}
