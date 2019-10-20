package orthae.com.github.medicalmanagementsystem.server.management.workdays;

import org.springframework.web.bind.annotation.*;
import orthae.com.github.medicalmanagementsystem.server.entity.employee.Workday;
import orthae.com.github.medicalmanagementsystem.server.management.workdays.dto.WorkdayDto;
import orthae.com.github.medicalmanagementsystem.server.management.workdays.service.WorkdayService;

import javax.validation.Valid;
import java.util.List;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping("${rest.endpoint.path}")
public class WorkdaysRestController {

 private WorkdayService workdayService;

    public WorkdaysRestController(WorkdayService workdayService) {
        this.workdayService = workdayService;
    }

    @GetMapping("${rest.endpoint.employees}/{employeeId}/${rest.endpoint.workdays}/{month}/{year}")
    public List<WorkdayDto> getEmployeeWorkdaysForMonth(@PathVariable int employeeId, @PathVariable int month, @PathVariable int year){
        return workdayService.getByEmployeeIdAndMonth(employeeId, month, year);
    }

    @GetMapping("${rest.endpoint.employees}/${rest.endpoint.workdays}")
    public List<Workday> getByParams(@RequestParam(required = false) String username, String fromDate, String toDate){
        return workdayService.getByParams(username, fromDate, toDate);
    }

    @PostMapping("${rest.endpoint.employees}/{employeeId}/${rest.endpoint.workdays}")
    public void createWorkday(@PathVariable int employeeId, @Valid @RequestBody WorkdayDto dto){
        workdayService.createWorkday(employeeId, dto);
    }

    @PutMapping("${rest.endpoint.employees}/${rest.endpoint.workdays}")
    public void updateWorkday(@Valid @RequestBody WorkdayDto dto){

    }

    @DeleteMapping("${rest.endpoint.employees}/${rest.endpoint.workdays}/{workdayId}")
    public void deleteWorkday(@PathVariable int workdayId){
        workdayService.deleteWorkday(workdayId);
    }
}
