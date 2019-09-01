package orthae.com.github.medicalmanagementsystem.server.aspects.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import orthae.com.github.medicalmanagementsystem.server.aspects.security.session.SessionTokenConfig;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private SessionTokenConfig tokenConfig;

    @Autowired
    public SecurityConfig(SessionTokenConfig tokenConfig){
        this.tokenConfig = tokenConfig;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.httpBasic().disable();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/v1/sessiom").permitAll();
        http.authorizeRequests().antMatchers("/api/v1/employee", "/api/v1/employee*", "/api/v1/employee*/", "/api/v1/employee/*").hasAuthority("ADMIN").and()
                .apply(tokenConfig);
//        http.authorizeRequests().antMatchers("/api/v1/employee/*").hasAnyRole("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
