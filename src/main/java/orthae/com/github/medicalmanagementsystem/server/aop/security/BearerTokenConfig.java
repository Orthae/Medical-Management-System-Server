package orthae.com.github.medicalmanagementsystem.server.aop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


@Component
public class BearerTokenConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private BearerTokenFilter tokenFilter;

    @Autowired
    public BearerTokenConfig(BearerTokenFilter tokenFilter){
        this.tokenFilter = tokenFilter;
    }

    @Override
    public void configure(HttpSecurity http) {
    http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
