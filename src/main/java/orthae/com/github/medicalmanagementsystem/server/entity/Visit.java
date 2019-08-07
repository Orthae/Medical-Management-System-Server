package orthae.com.github.medicalmanagementsystem.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "patients_visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private int date;

    @Column(name = "patient_id")
    private int patientId;

    @Column(name = "visit_type")
    private String visitType;
}
