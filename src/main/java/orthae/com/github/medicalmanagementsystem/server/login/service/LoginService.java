package orthae.com.github.medicalmanagementsystem.server.login.service;

import orthae.com.github.medicalmanagementsystem.server.login.dto.LoginDTO;
import orthae.com.github.medicalmanagementsystem.server.management.employees.dto.EmployeeDetailsDto;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    EmployeeDetailsDto getCurrent();
    String login(LoginDTO dto, HttpServletRequest request);
    void logout(HttpServletRequest request);

}
