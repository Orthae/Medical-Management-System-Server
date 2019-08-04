package orthae.com.github.medicalmanagementsystem.server.repository;

import orthae.com.github.medicalmanagementsystem.server.entity.Patient;

import java.util.List;

public interface PatientRepository {

    List<Patient> findAll();
    Patient findById(int id);
    void save(Patient patient);


}
