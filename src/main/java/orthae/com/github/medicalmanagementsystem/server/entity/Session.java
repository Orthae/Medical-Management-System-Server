package orthae.com.github.medicalmanagementsystem.server.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employees_sessions")
@Getter
@Setter
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Employee employee;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "session_token")
    private String sessionToken;

    @Column(name = "session_creation")
    private Date sessionCreation;

    @Column(name = "session_expiry")
    private Date sessionExpiry;

    public boolean isValid() {
        return sessionExpiry.after(new Date());
    }

}
