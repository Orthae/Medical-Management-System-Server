package orthae.com.github.medicalmanagementsystem.server.patient.service;

import orthae.com.github.medicalmanagementsystem.server.entity.Patient;
import orthae.com.github.medicalmanagementsystem.server.patient.dto.CreatePatientDTO;

import java.util.List;

public interface PatientService {

    List<Patient> findAll();
    void createPatient(CreatePatientDTO dto);
    Patient findById(int id);
}
