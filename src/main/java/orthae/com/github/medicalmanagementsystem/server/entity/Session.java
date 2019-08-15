package orthae.com.github.medicalmanagementsystem.server.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sessions")
@Getter
@Setter
public class Session {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "employee_id")
        private Employee employee;

        @Column(name = "session_token")
        private String sessionToken;

        @Column(name = "session_expiry")
        private Date sessionExpiry;


}
