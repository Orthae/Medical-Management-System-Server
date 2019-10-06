package orthae.com.github.medicalmanagementsystem.server.management.sessions.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties
public class SessionDto {
    private int id;
    private String username;
    private String sessionToken;
    private String ipAddress;
    private Date sessionCreation;
    private Date sessionExpiry;
    private boolean active;
}
