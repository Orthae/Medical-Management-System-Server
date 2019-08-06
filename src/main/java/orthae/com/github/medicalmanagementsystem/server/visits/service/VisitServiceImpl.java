package orthae.com.github.medicalmanagementsystem.server.visits.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.entity.Visit;
import orthae.com.github.medicalmanagementsystem.server.repository.VisitRepository;
import orthae.com.github.medicalmanagementsystem.server.visits.dto.CreateVisitDTO;
import orthae.com.github.medicalmanagementsystem.server.visits.dto.UpdateVisitDTO;

import java.util.List;

@Component
public class VisitServiceImpl implements VisitService {

    private VisitRepository visitRepository;

    @Autowired
    VisitServiceImpl(VisitRepository visitRepository){
        this.visitRepository = visitRepository;
    }

    @Override
    public Visit findById(int id) {
        return visitRepository.findById(id);
    }

    @Transactional
    @Override
    public void createVisit(CreateVisitDTO dto) {
        ModelMapper mapper = new ModelMapper();
        Visit visit = mapper.map(dto, Visit.class);
        visit.setId(0);
        visitRepository.save(visit);
    }

    @Transactional
    @Override
    public void updateVisit(UpdateVisitDTO dto) {
        ModelMapper mapper = new ModelMapper();
        Visit visit = mapper.map(dto, Visit.class);
        visitRepository.save(visit);
    }

    @Override
    public List<Visit> findByPatientId(int id) {
        return visitRepository.findByPatientId(id);
    }
}
