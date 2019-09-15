package orthae.com.github.medicalmanagementsystem.server.login.service;

import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDetailsDto;
import orthae.com.github.medicalmanagementsystem.server.login.dto.LoginDTO;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    EmployeeDetailsDto getCurrent();
    String login(LoginDTO dto, HttpServletRequest request);
    void logout(HttpServletRequest request);

}
