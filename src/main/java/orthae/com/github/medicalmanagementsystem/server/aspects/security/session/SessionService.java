package orthae.com.github.medicalmanagementsystem.server.aspects.security.session;

import org.springframework.security.core.Authentication;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.SessionDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SessionService {
    String createSession(Authentication auth, HttpServletRequest request);
    List<SessionDto> getSessions(String username, String ipAddress, Boolean active, String created, String expiring);
    List<SessionDto> getSessions(int employeeId);
    String extractToken(HttpServletRequest request);
    Authentication validate(HttpServletRequest request);

}
