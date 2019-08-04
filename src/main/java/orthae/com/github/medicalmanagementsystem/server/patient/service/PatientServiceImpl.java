package orthae.com.github.medicalmanagementsystem.server.patient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.entity.Patient;
import orthae.com.github.medicalmanagementsystem.server.patient.dto.CreatePatientDTO;
import orthae.com.github.medicalmanagementsystem.server.repository.PatientRepository;

import java.util.List;

@Component
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    @Transactional
    @Override
    public List<Patient> findAll(){
        return patientRepository.findAll();
    }


    @Transactional
    @Override
    public Patient findById(int id){
        return patientRepository.findById(id);
    }

    @Override
    public void createPatient(CreatePatientDTO dto) {

    }

}
