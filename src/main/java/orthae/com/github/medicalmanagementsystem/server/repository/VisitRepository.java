package orthae.com.github.medicalmanagementsystem.server.repository;

import orthae.com.github.medicalmanagementsystem.server.entity.Visit;

import java.util.List;

public interface VisitRepository {

    Visit findById(int id);
    List<Visit> findByPatientId(int id);
    void save(Visit visit);

}
