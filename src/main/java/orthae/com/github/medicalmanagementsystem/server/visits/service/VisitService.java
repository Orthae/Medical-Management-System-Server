package orthae.com.github.medicalmanagementsystem.server.visits.service;

import orthae.com.github.medicalmanagementsystem.server.entity.Visit;
import orthae.com.github.medicalmanagementsystem.server.visits.dto.CreateVisitDTO;
import orthae.com.github.medicalmanagementsystem.server.visits.dto.UpdateVisitDTO;

import java.util.List;

public interface VisitService {

    Visit findById(int id);
    void createVisit(CreateVisitDTO dto);
    void updateVisit(UpdateVisitDTO dto);
    List<Visit> findByPatientId(int id);

}
