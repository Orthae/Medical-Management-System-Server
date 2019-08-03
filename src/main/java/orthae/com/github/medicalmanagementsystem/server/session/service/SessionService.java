package orthae.com.github.medicalmanagementsystem.server.session.service;

import orthae.com.github.medicalmanagementsystem.server.session.dto.LoginDTO;

public interface SessionService {
    String login(LoginDTO dto);
}
