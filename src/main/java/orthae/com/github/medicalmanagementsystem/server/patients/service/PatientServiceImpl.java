package orthae.com.github.medicalmanagementsystem.server.patients.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.aop.Utility;
import orthae.com.github.medicalmanagementsystem.server.patients.dto.CreatePatientDTO;
import orthae.com.github.medicalmanagementsystem.server.patients.dto.PatientDTO;
import orthae.com.github.medicalmanagementsystem.server.repository.PatientRepository;

import java.util.List;

@Component
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private Utility utility;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, Utility utility){
        this.patientRepository = patientRepository;
        this.utility = utility;
    }

    @Transactional
    @Override
    public List<PatientDTO> findAll(){
        return utility.mapAll(patientRepository.findAll(), PatientDTO.class);
    }

    @Transactional
    @Override
    public PatientDTO findById(int id){
        return utility.map(patientRepository.findById(id), PatientDTO.class);
    }

    @Override
    public void createPatient(CreatePatientDTO dto) {

    }

}
