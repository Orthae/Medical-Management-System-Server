package orthae.com.github.medicalmanagementsystem.server.security.bearertoken;

import io.jsonwebtoken.*;
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

    @Value("${security.token.expiration-time}")
    private int expirationTime;

    @Value("${security.token.sign-key}")
    private String signKey;

    private UserDetailsService userDetailsService;

    @Autowired
    public BearerTokenProviderImpl(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean validate(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey("test").parseClaimsJws(token);
            return claims.getBody().getExpiration().after(new Date());
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    @Override
    public String extractToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer"))
            return token.substring(7);
        return null;
    }

    @Override
    public String generateToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        Date created = new Date();
        Date expired = new Date(created.getTime() + expirationTime);
        return Jwts.builder().addClaims(claims).setIssuedAt(created).setExpiration(expired).signWith(SignatureAlgorithm.HS256, "test").compact();
    }

    @Override
    public Authentication getAuthentication(String token) {
        String username = Jwts.parser().setSigningKey("test").parseClaimsJws(token).getBody().getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
