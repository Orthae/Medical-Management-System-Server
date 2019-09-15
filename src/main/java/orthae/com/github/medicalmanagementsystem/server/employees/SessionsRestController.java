package orthae.com.github.medicalmanagementsystem.server.employees;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orthae.com.github.medicalmanagementsystem.server.aspects.security.session.SessionService;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.SessionDto;

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
    public List<SessionDto> getSessions(){
        return sessionService.getSessions();
    }
}
