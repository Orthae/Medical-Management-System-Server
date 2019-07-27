package orthae.com.github.medicalmanagementsystem.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import orthae.com.github.medicalmanagementsystem.core.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employees")
public class EmployeeDatabaseEntity implements Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @SuppressWarnings("unused")
    public void setPassword(String password){
    // TODO BCrypt
        this.password = password;
    }

}
