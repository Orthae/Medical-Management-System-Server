package orthae.com.github.medicalmanagementsystem.server.employees;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import orthae.com.github.medicalmanagementsystem.server.aspects.security.session.SessionService;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeChangePasswordDto;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDetailsDto;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDto;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.SessionDto;
import orthae.com.github.medicalmanagementsystem.server.employees.service.EmployeeService;

import javax.validation.Valid;
import java.util.List;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping("${rest.endpoint.path}")
public class EmployeeRestController {

    private EmployeeService employeeService;
    private SessionService sessionService;

    public EmployeeRestController(EmployeeService employeeService, SessionService sessionService) {
        this.employeeService = employeeService;
        this.sessionService = sessionService;
    }

    @GetMapping(value = "${rest.endpoint.employees}")
    public List<EmployeeDto> findEmployeesByNameAndSurname(@RequestParam(required = false) String name, @RequestParam(required = false) String surname,
                                                           @RequestParam(required = false) String username, @RequestParam(required = false) String email) {
        return employeeService.find(name, surname, username, email);
    }

    @GetMapping("${rest.endpoint.employees}/{employeeId}")
    public EmployeeDetailsDto getEmployee(@PathVariable int employeeId) {
        return employeeService.find(employeeId);
    }

    @PostMapping("${rest.endpoint.employees}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@Valid @RequestBody EmployeeDetailsDto employee) {
        employeeService.create(employee);
    }

    @DeleteMapping("${rest.endpoint.employees}/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable int employeeId) {
        employeeService.delete(employeeId);
    }

    @PutMapping("${rest.endpoint.employees}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployee(@Valid @RequestBody EmployeeDetailsDto employee) {
        employeeService.update(employee);
    }

    @PostMapping("${rest.endpoint.employees}/{employeeId}/active")
    @ResponseStatus(HttpStatus.OK)
    public void enableEmployee(@PathVariable int employeeId){
        employeeService.enable(employeeId);
    }

    @DeleteMapping("${rest.endpoint.employees}/{employeeId}/active")
    @ResponseStatus(HttpStatus.OK)
    public void disableEmployee(@PathVariable int employeeId){
        employeeService.disable(employeeId);
    }

    @PutMapping("${rest.endpoint.employees}/{employeeId}/password")
    public void changeEmployeePassword(@PathVariable int employeeId, @Valid @RequestBody EmployeeChangePasswordDto dto){
        employeeService.changePassword(employeeId, dto);
    }

    @GetMapping("${rest.endpoint.employees}/{employeeId}/sessions")
    public List<SessionDto> getEmployeeSessions(@PathVariable int employeeId){
        return sessionService.getSessions(employeeId);
    }

}
