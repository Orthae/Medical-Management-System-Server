package orthae.com.github.medicalmanagementsystem.server.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import orthae.com.github.medicalmanagementsystem.server.session.dto.LoginDTO;
import orthae.com.github.medicalmanagementsystem.server.session.service.SessionService;

import javax.validation.Valid;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping("${rest.endpoint.path}")
public class SessionRestController {
    private SessionService sessionService;

    @Autowired
    public SessionRestController(SessionService sessionService){
        this.sessionService = sessionService;
    }

    @PostMapping("/session")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String login(@Valid @RequestBody LoginDTO loginDTO){
        return sessionService.login(loginDTO);
    }

}
