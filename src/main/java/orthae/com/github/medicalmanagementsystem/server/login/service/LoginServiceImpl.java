package orthae.com.github.medicalmanagementsystem.server.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import orthae.com.github.medicalmanagementsystem.server.aspects.security.session.SessionService;
import orthae.com.github.medicalmanagementsystem.server.login.dto.LoginDTO;

import javax.servlet.http.HttpServletRequest;


@Component
public class LoginServiceImpl implements LoginService {

    private AuthenticationManager authManager;
    private SessionService sessionService;

    @Autowired
    public LoginServiceImpl(AuthenticationManager authenticationManager, SessionService sessionService){
        this.authManager = authenticationManager;
        this.sessionService = sessionService;
    }

    @Override
    public String login(LoginDTO dto) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        return sessionService.createSession(authentication);
    }

    @Override
    public void logout(HttpServletRequest request) {
        String token = sessionService.extractToken(request);
        sessionService.deleteSession(token);
    }


}
