package orthae.com.github.medicalmanagementsystem.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employee_roles")
public class EmployeeRole {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "role")
    private String role;

}
