package orthae.com.github.medicalmanagementsystem.server.patients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import orthae.com.github.medicalmanagementsystem.server.entity.Visit;
import orthae.com.github.medicalmanagementsystem.server.patients.dto.CreatePatientDTO;
import orthae.com.github.medicalmanagementsystem.server.patients.dto.PatientDto;
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
    public List<PatientDto> searchPatients(@RequestParam(required = false) String name, @RequestParam(required = false) String surname,
                                           @RequestParam(required = false) String birthdate, @RequestParam(required = false) String email,
                                           @RequestParam(required = false) String socialSecurity){
        return patientService.searchPatients(name, surname, birthdate, email, socialSecurity);
    }

    @GetMapping("${rest.endpoint.patients}/{id}")
    public PatientDto findPatient(@PathVariable int id){
        return patientService.findById(id);
    }

    @PostMapping("${rest.endpoint.patients}")
    public void createPatient(CreatePatientDTO dto){
        patientService.createPatient(dto);
    }

    @GetMapping("${rest.endpoint.patients}/{patientId}/${rest.endpoint.visits}")
    public List<Visit> findAllPatientVisits(@PathVariable int patientId){
        return visitService.findByPatientId(patientId);
    }

}
