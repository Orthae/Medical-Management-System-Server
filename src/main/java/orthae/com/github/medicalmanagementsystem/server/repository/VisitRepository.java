package orthae.com.github.medicalmanagementsystem.server.repository;

import orthae.com.github.medicalmanagementsystem.server.entity.Visit;

public interface VisitRepository {

    Visit findById(int id);
    void save(Visit visit);

}
