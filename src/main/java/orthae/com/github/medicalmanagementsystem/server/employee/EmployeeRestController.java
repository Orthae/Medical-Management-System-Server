package orthae.com.github.medicalmanagementsystem.server.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orthae.com.github.medicalmanagementsystem.server.employee.dto.CreateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employee.dto.UpdateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employee.entity.EmployeeEntity;
import orthae.com.github.medicalmanagementsystem.server.employee.service.EmployeeServerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeRestController {

    private EmployeeServerService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeServerService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("employee")
    public List<EmployeeEntity> getEmployees(@RequestParam(required = false) String name, @RequestParam(required = false) String surname){
        return employeeService.getEmployee(name, surname);
    }

    @GetMapping("employee/{id}")
    public EmployeeEntity getEmployee(@PathVariable int id){
        return employeeService.getEmployee(id);
    }

    @PostMapping("employee")
    public ResponseEntity createEmployee(@Valid @RequestBody CreateEmployeeDTO employee){
        employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("employee")
    public ResponseEntity updateEmployee(@Valid @RequestBody UpdateEmployeeDTO employee){
        employeeService.updateEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
