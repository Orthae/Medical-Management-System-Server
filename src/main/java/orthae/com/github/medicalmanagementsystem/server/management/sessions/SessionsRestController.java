package orthae.com.github.medicalmanagementsystem.server.management.sessions;

import org.springframework.web.bind.annotation.*;
import orthae.com.github.medicalmanagementsystem.server.management.sessions.dto.SessionDto;
import orthae.com.github.medicalmanagementsystem.server.management.sessions.service.SessionService;

import java.util.List;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping("${rest.endpoint.path}")
public class SessionsRestController {

    private SessionService sessionService;

    public SessionsRestController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("${rest.endpoint.employees}/sessions")
    public List<SessionDto> getSessions(@RequestParam(required = false) String username, @RequestParam(required = false) String ipAddress,
                                        @RequestParam(required = false) Boolean active, @RequestParam(required = false) String created,
                                        @RequestParam(required = false) String expiring){
        return sessionService.getSessions(username, ipAddress, active, created, expiring);
    }

    @GetMapping("${rest.endpoint.employees}/{employeeId}/sessions")
    public List<SessionDto> getEmployeeSessions(@PathVariable int employeeId){
        return sessionService.getSessions(employeeId);
    }

    @DeleteMapping("${rest.endpoint.employees}/sessions/{sessionId}")
    public void invalidateSession(@PathVariable int sessionId){
        sessionService.invalidateSession(sessionId);
    }

    @DeleteMapping("${rest.endpoint.employees}/{employeeId}/sessions")
    public void invalidateEmployeeSessions(@PathVariable int employeeId){
        sessionService.invalidateEmployeeSessions(employeeId);
    }

}
