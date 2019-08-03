package orthae.com.github.medicalmanagementsystem.server.security.bearertoken;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface BearerTokenProvider {
    boolean validate(String token);
    String extractToken (HttpServletRequest request);
    String generateToken(String username);
    Authentication getAuthentication(String token);
}
