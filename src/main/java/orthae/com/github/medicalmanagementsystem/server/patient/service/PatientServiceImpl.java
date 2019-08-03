package orthae.com.github.medicalmanagementsystem.server.patient.service;

import org.springframework.stereotype.Component;
import orthae.com.github.medicalmanagementsystem.server.entity.Patient;
import orthae.com.github.medicalmanagementsystem.server.patient.dto.CreatePatientDTO;

import java.util.List;

@Component
public class PatientServiceImpl implements PatientService {

    @Override
    public List<Patient> findAll(){
        return null;
    }

    @Override
    public void createPatient(CreatePatientDTO dto) {

    }

}
