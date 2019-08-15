package orthae.com.github.medicalmanagementsystem.server.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import orthae.com.github.medicalmanagementsystem.server.login.dto.LoginDTO;
import orthae.com.github.medicalmanagementsystem.server.login.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping("${rest.endpoint.path}")
public class SessionRestController {
    private LoginService loginService;

    @Autowired
    public SessionRestController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping("${rest.endpoint.login}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String login(@Valid @RequestBody LoginDTO loginDTO){
        return loginService.login(loginDTO);
    }

    @DeleteMapping("${rest.endpoint.login}")
    public void logout(HttpServletRequest request){
        loginService.logout(request);
    }
}
