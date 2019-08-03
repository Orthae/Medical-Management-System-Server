package orthae.com.github.medicalmanagementsystem.server.session.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import orthae.com.github.medicalmanagementsystem.server.security.bearertoken.BearerTokenProvider;
import orthae.com.github.medicalmanagementsystem.server.session.dto.LoginDTO;

@Component
public class SessionServiceImpl implements SessionService {

    private AuthenticationManager authManager;
    private BearerTokenProvider tokenProvider;

    @Autowired
    public SessionServiceImpl(AuthenticationManager authenticationManager, BearerTokenProvider tokenProvider){
        this.authManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public String login(LoginDTO dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return tokenProvider.generateToken(username);
    }
}
