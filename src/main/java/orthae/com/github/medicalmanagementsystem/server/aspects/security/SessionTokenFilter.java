package orthae.com.github.medicalmanagementsystem.server.aspects.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import orthae.com.github.medicalmanagementsystem.server.employees.service.SessionService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class SessionTokenFilter extends GenericFilterBean {

    private SessionService sessionService;

    @Autowired
    public SessionTokenFilter(SessionService sessionService){
        this.sessionService = sessionService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication auth = sessionService.validate((HttpServletRequest) request);
        if(auth != null){
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(request, response) ;

    }
}
