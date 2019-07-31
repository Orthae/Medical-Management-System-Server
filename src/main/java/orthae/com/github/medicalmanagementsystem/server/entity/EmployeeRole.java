package orthae.com.github.medicalmanagementsystem.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employee_roles")
public class EmployeeRole implements GrantedAuthority {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "role")
    private String authority;

    @Override
    public String getAuthority() {
        return "ROLE_" + authority;
    }
}
