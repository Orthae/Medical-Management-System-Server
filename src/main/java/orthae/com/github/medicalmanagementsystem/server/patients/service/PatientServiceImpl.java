package orthae.com.github.medicalmanagementsystem.server.patients.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.aspects.Utility;
import orthae.com.github.medicalmanagementsystem.server.entity.Patient;
import orthae.com.github.medicalmanagementsystem.server.patients.dto.CreatePatientDTO;
import orthae.com.github.medicalmanagementsystem.server.patients.dto.PatientDto;
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
    public List<PatientDto> searchPatients(String name, String surname, String birthdate, String email, String socialSecurity){
        List<Patient> list = patientRepository.search(name, surname, birthdate, email, socialSecurity);
        return utility.mapAll(list, PatientDto.class);
    }

    @Transactional
    @Override
    public PatientDto findById(int id){
        return utility.map(patientRepository.findById(id), PatientDto.class);
    }

    @Override
    public void createPatient(CreatePatientDTO dto) {

    }

}
