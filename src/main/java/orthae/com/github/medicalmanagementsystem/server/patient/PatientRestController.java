package orthae.com.github.medicalmanagementsystem.server.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orthae.com.github.medicalmanagementsystem.server.entity.Patient;
import orthae.com.github.medicalmanagementsystem.server.patient.dto.CreatePatientDTO;
import orthae.com.github.medicalmanagementsystem.server.patient.service.PatientService;

import java.util.List;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping("${rest.endpoint.path}")
public class PatientRestController {

    private PatientService patientService;

    @Autowired
    public PatientRestController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("/patient")
    public List<Patient> findAllPatients(){
        return patientService.findAll();
    }

    @PostMapping("/patient")
    public void createPatient(CreatePatientDTO dto){
        patientService.createPatient(dto);
    }

}
