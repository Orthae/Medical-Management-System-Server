package orthae.com.github.medicalmanagementsystem.server.aspects.security.session;

import org.springframework.security.core.Authentication;
import orthae.com.github.medicalmanagementsystem.server.entity.Session;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SessionService {
    String createSession(Authentication auth);
    void deleteSession(Session session);
    void deleteSession(String token);
    List<Session> getSessions(int employeeId);
    String extractToken(HttpServletRequest request);
    Authentication validate(HttpServletRequest request);

}
