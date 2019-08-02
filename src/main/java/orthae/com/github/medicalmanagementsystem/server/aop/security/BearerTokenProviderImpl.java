package orthae.com.github.medicalmanagementsystem.server.aop.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class BearerTokenProviderImpl implements BearerTokenProvider {

    @Value("${security.token.expirationTime}")
    private int expirationTime;

    private UserDetailsService userDetailsService;

    @Autowired
    public BearerTokenProviderImpl(@Qualifier("userDetailsServiceImp") UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean validate(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey("test").parseClaimsJws(token);
        if(claims.getBody().getExpiration().before(new Date())){
            return false;
        }
        return true;
    }

    @Override
    public String extractToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer"))
            return token.substring(7);
        return null;
    }

    @Override
    public String generateToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        Date created = new Date();
        Date expired = new Date(created.getTime() + expirationTime);
        return Jwts.builder().addClaims(claims).setIssuedAt(created).setExpiration(expired).signWith(SignatureAlgorithm.HS256,"test").compact();
    }

    @Override
    public Authentication getAuthentication(String token) {
        String username = Jwts.parser().setSigningKey("test").parseClaimsJws(token).getBody().getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
