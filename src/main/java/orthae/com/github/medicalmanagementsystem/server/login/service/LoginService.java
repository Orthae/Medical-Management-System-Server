package orthae.com.github.medicalmanagementsystem.server.login.service;

import orthae.com.github.medicalmanagementsystem.server.login.dto.LoginDTO;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    String login(LoginDTO dto);
    void logout(HttpServletRequest request);

}
