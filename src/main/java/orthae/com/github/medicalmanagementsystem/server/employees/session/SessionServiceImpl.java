package orthae.com.github.medicalmanagementsystem.server.employees.session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.aspects.Utility;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.SessionDto;
import orthae.com.github.medicalmanagementsystem.server.entity.Employee;
import orthae.com.github.medicalmanagementsystem.server.entity.Session;
import orthae.com.github.medicalmanagementsystem.server.repository.SessionRepository;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class SessionServiceImpl implements SessionService {

    @Value("${security.token.expiration-time}")
    private long sessionLength;

    private SessionRepository sessionRepository;
    private SecureRandom secureRandom;
    private Utility utility;

    public SessionServiceImpl(SessionRepository sessionRepository, Utility utility) {
        this.sessionRepository = sessionRepository;
        this.secureRandom = new SecureRandom();
        this.utility = utility;
    }

    @Transactional
    @Override
    public List<SessionDto> getSessions(String username, String ipAddress, Boolean active, String created, String expiring) {
        List<Session> list = sessionRepository.find(username, ipAddress, active, created, expiring);
        return utility.mapListSessionDto(list);
    }

    @Override
    public List<SessionDto> getSessions(int employeeId) {
        List<Session> list = sessionRepository.findEmployeeSessions(employeeId);
        return utility.mapListSessionDto(list);
    }

    @Transactional
    @Override
    public Authentication validate(HttpServletRequest request) {
        String token = extractToken(request);
        Session session = sessionRepository.find(token);
        if (session != null) {
            if (session.isValid()) {
                extendToken(session);
                return new UsernamePasswordAuthenticationToken(session.getEmployee(), null, session.getEmployee().getAuthorities());
            } else {
                return null;
            }
        } else
            return null;
    }

    @Transactional
    @Override
    public void invalidateSession(int sessionId) {
        sessionRepository.invalidateSession(sessionId);
    }

    @Transactional
    @Override
    public void invalidateEmployeeSessions(int employeeId) {
        sessionRepository.invalidateEmployeeSessions(employeeId);
    }


    @Transactional
    @Override
    public String createSession(Authentication auth, HttpServletRequest request) {
        Session session = new Session();
        Employee employee = (Employee) auth.getPrincipal();
        session.setEmployee(employee);
        session.setSessionToken(generateToken());
        session.setSessionCreation(new Date());
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null)
            session.setIpAddress(request.getRemoteAddr());
        else
            session.setIpAddress(ipAddress);
        extendToken(session);
        sessionRepository.save(session);
        return session.getSessionToken();
    }

    @Override
    public String extractToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer"))
            return token.substring(7);
        return null;
    }

    private void extendToken(Session session) {
        long tokenExpiry = new Date().getTime() + sessionLength;
        session.setSessionExpiry(new Date(tokenExpiry));
    }

    private String generateToken() {
        byte[] bytes = new byte[96];
        secureRandom.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }
}
