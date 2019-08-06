package orthae.com.github.medicalmanagementsystem.server.patients.service;

import orthae.com.github.medicalmanagementsystem.server.patients.dto.CreatePatientDTO;
import orthae.com.github.medicalmanagementsystem.server.patients.dto.PatientDTO;

import java.util.List;

public interface PatientService {
    List<PatientDTO> findAll();
    void createPatient(CreatePatientDTO dto);
    PatientDTO findById(int id);
}
