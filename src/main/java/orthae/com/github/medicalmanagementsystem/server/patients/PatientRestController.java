package orthae.com.github.medicalmanagementsystem.server.patients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import orthae.com.github.medicalmanagementsystem.server.aop.Utility;
import orthae.com.github.medicalmanagementsystem.server.entity.Visit;
import orthae.com.github.medicalmanagementsystem.server.patients.dto.CreatePatientDTO;
import orthae.com.github.medicalmanagementsystem.server.patients.dto.PatientDTO;
import orthae.com.github.medicalmanagementsystem.server.patients.service.PatientService;
import orthae.com.github.medicalmanagementsystem.server.visits.service.VisitService;

import java.util.List;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping("${rest.endpoint.path}")
public class PatientRestController {

    private PatientService patientService;
    private VisitService visitService;
    private Utility utility;

    @Autowired
    public PatientRestController(PatientService patientService, VisitService visitService, Utility utility){
        this.patientService = patientService;
        this.visitService = visitService;
        this.utility = utility;
    }

    @GetMapping("${rest.endpoint.patients}")
    public List<PatientDTO> findAllPatients(){
        return patientService.findAll();
    }

    @GetMapping("${rest.endpoint.patients}/{id}")
    public PatientDTO findPatient(@PathVariable int id){
        return patientService.findById(id);
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
