package orthae.com.github.medicalmanagementsystem.server.entity.employee;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "month")
    private int month;

    @Column(name = "year")
    private int year;

    @ManyToOne
    private Employee employee;

    @OneToMany
    @JoinColumn(name = "schedule_id")
    private List<Workday> workdays;
}
