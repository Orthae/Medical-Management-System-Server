package orthae.com.github.medicalmanagementsystem.server.aspects.security.session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.entity.Employee;
import orthae.com.github.medicalmanagementsystem.server.entity.Session;
import orthae.com.github.medicalmanagementsystem.server.repository.SessionRepository;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Component
public class SessionServiceImpl implements SessionService {

    @Value("${security.token.expiration-time}")
    private long sessionLength;

    private SessionRepository sessionRepository;
    private SecureRandom secureRandom;

    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
        this.secureRandom = new SecureRandom();
    }

    @Transactional
    @Override
    public void deleteSession(Session session) {
        sessionRepository.delete(session);
    }

    @Override
    @Transactional
    public void deleteSession(String token) {
        Session session = sessionRepository.find(token);
        deleteSession(session);
    }

    @Transactional
    @Override
    public Authentication validate(HttpServletRequest request) {
        String token = extractToken(request);
        Session session = sessionRepository.find(token);
        if (session != null) {
            if (session.getSessionExpiry().after(new Date())) {
                extendToken(session);
                return new UsernamePasswordAuthenticationToken(session.getEmployee(), null, session.getEmployee().getAuthorities());
            } else {
                deleteSession(session);
                return null;
            }
        } else
            return null;
    }


    @Transactional
    @Override
    public String createSession(Authentication auth) {
        Session session = new Session();
        Employee employee = (Employee) auth.getPrincipal();
        session.setEmployee(employee);
        session.setSessionToken(generateToken());
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
