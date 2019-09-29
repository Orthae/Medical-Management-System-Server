package orthae.com.github.medicalmanagementsystem.server.repository;

import orthae.com.github.medicalmanagementsystem.server.entity.Patient;

import java.util.List;

public interface PatientRepository {

    List<Patient> search(String name, String surname, String birthdate, String email, String socialSecurity);
    Patient findById(int id);
    void save(Patient patient);


}
