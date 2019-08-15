package orthae.com.github.medicalmanagementsystem.server.aspects.security.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


@Component
public class SessionTokenConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private SessionTokenFilter tokenFilter;

    @Autowired
    public SessionTokenConfig(SessionTokenFilter tokenFilter){
        this.tokenFilter = tokenFilter;
    }

    @Override
    public void configure(HttpSecurity http) {
    http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
