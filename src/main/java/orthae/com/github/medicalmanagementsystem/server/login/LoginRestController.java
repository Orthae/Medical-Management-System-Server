package orthae.com.github.medicalmanagementsystem.server.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import orthae.com.github.medicalmanagementsystem.server.login.dto.LoginDTO;
import orthae.com.github.medicalmanagementsystem.server.login.service.LoginService;
import orthae.com.github.medicalmanagementsystem.server.management.employees.dto.EmployeeDetailsDto;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping("${rest.endpoint.path}")
public class LoginRestController {
    private LoginService loginService;

    @Autowired
    public LoginRestController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("${rest.endpoint.login}")
    public EmployeeDetailsDto getCurrentEmployee() {
        return loginService.getCurrent();
    }

    @PostMapping("${rest.endpoint.login}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String login(@Valid @RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        return loginService.login(loginDTO, request);
    }

    @DeleteMapping("${rest.endpoint.login}")
    public void logout(HttpServletRequest request) {
        loginService.logout(request);
    }
}
