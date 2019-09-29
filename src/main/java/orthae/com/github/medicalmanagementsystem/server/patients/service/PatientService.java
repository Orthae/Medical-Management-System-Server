package orthae.com.github.medicalmanagementsystem.server.patients.service;

import orthae.com.github.medicalmanagementsystem.server.patients.dto.CreatePatientDTO;
import orthae.com.github.medicalmanagementsystem.server.patients.dto.PatientDto;

import java.util.List;

public interface PatientService {
    List<PatientDto> searchPatients(String name, String surname, String birthdate, String email, String socialSecurity);
    void createPatient(CreatePatientDTO dto);
    PatientDto findById(int id);
}
