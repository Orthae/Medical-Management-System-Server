package orthae.com.github.medicalmanagementsystem.server.management.workdays;

import org.springframework.web.bind.annotation.*;
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

    @GetMapping("${rest.endpoint.employees}/{employeeId}/${rest.endpoint.workdays}")
    public List<WorkdayDto> getEmployeeWorkdays(@PathVariable int employeeId){
        return workdayService.getByEmployeeId(employeeId);
    }

    @PostMapping("${rest.endpoint.employees}/{employeeId}/${rest.endpoint.workdays}")
    public void createWorkday(@PathVariable int employeeId, @RequestBody @Valid WorkdayDto dto){
        workdayService.createWorkday(employeeId, dto);
    }
}
