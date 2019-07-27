package orthae.com.github.medicalmanagementsystem.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import orthae.com.github.medicalmanagementsystem.core.Employee;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employees")
public class EmployeeDatabase implements Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column (name = "surname")
    private String surname;

    @Column (name = "username")
    private String username;

    @Column (name = "password")
    private String password;

    public EmployeeDatabase(String name, String surname, String username, String password){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

}
