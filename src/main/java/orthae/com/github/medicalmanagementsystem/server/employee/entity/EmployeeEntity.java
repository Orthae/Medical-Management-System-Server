package orthae.com.github.medicalmanagementsystem.server.employee.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @NotNull
    @Column (name = "surname")
    private String surname;

    @NotBlank
    @NotNull
    @Column (name = "username")
    private String username;

    @NotBlank
    @NotNull
    @Column (name = "password")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private List<EmployeeRoleEntity> employeeRoles;

    public List<EmployeeRoleEntity> getEmployeeRoles(){
          return employeeRoles;
    }

    @SuppressWarnings("unused")
    public void setPassword(String password){
        this.password = password;
    }

}
