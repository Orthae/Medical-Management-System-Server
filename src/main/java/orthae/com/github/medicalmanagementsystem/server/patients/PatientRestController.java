package orthae.com.github.medicalmanagementsystem.server.patients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import orthae.com.github.medicalmanagementsystem.server.entity.Patient;
import orthae.com.github.medicalmanagementsystem.server.entity.Visit;
import orthae.com.github.medicalmanagementsystem.server.patients.dto.CreatePatientDTO;
import orthae.com.github.medicalmanagementsystem.server.patients.service.PatientService;
import orthae.com.github.medicalmanagementsystem.server.visits.service.VisitService;

import java.util.List;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping("${rest.endpoint.path}")
public class PatientRestController {

    private PatientService patientService;
    private VisitService visitService;

    @Autowired
    public PatientRestController(PatientService patientService, VisitService visitService){
        this.patientService = patientService;
        this.visitService = visitService;
    }

    @GetMapping("${rest.endpoint.patients}")
    public List<Patient> findAllPatients(){
        return patientService.findAll();
    }

    @PostMapping("${rest.endpoint.patients}")
    public void createPatient(CreatePatientDTO dto){
        patientService.createPatient(dto);
    }

    @GetMapping("${rest.endpoint.patients}/{patientId}/visits")
    public List<Visit> findAllPatientVisits(@PathVariable int patientId){
        return visitService.findByPatientId(patientId);
    }

}
