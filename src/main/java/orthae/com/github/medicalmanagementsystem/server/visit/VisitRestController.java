package orthae.com.github.medicalmanagementsystem.server.visit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import orthae.com.github.medicalmanagementsystem.server.entity.Visit;
import orthae.com.github.medicalmanagementsystem.server.visit.dto.CreateVisitDTO;
import orthae.com.github.medicalmanagementsystem.server.visit.dto.UpdateVisitDTO;
import orthae.com.github.medicalmanagementsystem.server.visit.service.VisitService;

import javax.validation.Valid;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping("${rest.endpoint.path}")
public class VisitRestController {

    private VisitService visitService;

    @Autowired
    public VisitRestController(VisitService visitService){
        this.visitService =visitService;
    }

    @GetMapping("visit/{id}")
    public Visit getById(int id){
        return visitService.findById(id);
    }

    @PostMapping("visit")
    public void createVisit(@Valid @RequestBody CreateVisitDTO dto){
        visitService.createVisit(dto);
    }

    @PutMapping("visit")
    public void updateVisit(@RequestBody UpdateVisitDTO dto){
        visitService.updateVisit(dto);
    }


}
