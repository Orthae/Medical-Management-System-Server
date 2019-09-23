package orthae.com.github.medicalmanagementsystem.server.aspects;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDetailsDto;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDto;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.SessionDto;
import orthae.com.github.medicalmanagementsystem.server.entity.Employee;
import orthae.com.github.medicalmanagementsystem.server.entity.EmployeeAuthority;
import orthae.com.github.medicalmanagementsystem.server.entity.EmployeeSession;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Utility {

    private ModelMapper modelMapper;
    public Utility(){
        this.modelMapper = new ModelMapper();
    }

    @PostConstruct
    private void config(){
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    }

    public void map(Object source, Object destination){
        modelMapper.map(source, destination);
    }

    public void map(EmployeeDetailsDto source, Employee destination){
        if(source.getName() != null && !source.getName().isEmpty())
            destination.setName(source.getName());
        if(source.getSurname() != null && !source.getSurname().isEmpty())
            destination.setSurname(source.getSurname());
        if(source.getUsername() != null && !source.getUsername().isEmpty())
            destination.setUsername(source.getUsername());
        if(source.getEmail() != null && !source.getEmail().isEmpty())
            destination.setEmail(source.getEmail());
        destination.setEnabled(source.isEnabled());
        for(EmployeeAuthority authority : source.getAuthorities()){
            authority.setEmployee(destination);
        }
        if(source.getAuthorities().isEmpty()){
            destination.getAuthorities().clear();
        }
        for(EmployeeAuthority authority : source.getAuthorities()){
            destination.addAuthority(authority);
        }
    }

    public <T> T map(Object source, Class<T> destinationType){
        return modelMapper.map(source, destinationType);
    }

    public <T, D> List<D> mapAll(List<T> source, Class<D> destinationType){
        List<D> list = new ArrayList<>();
        for(T t : source){
            list.add(map(t, destinationType));
        }
        return list;
    }

    public List<EmployeeDto> mapListEmployeeDto(List<Employee> source){
        List<EmployeeDto> list = new ArrayList<>();
        for(Employee e : source){
            EmployeeDto dto = map(e, EmployeeDto.class);
            if(e.getSessions() != null && !e.getSessions().isEmpty()){
                for(EmployeeSession session : e.getSessions()){
                    if(session.isValid()){
                    dto.setActive(true);
                    break;
                    }
                    dto.setActive(false);
                }
            }
            list.add(dto);
        }
        return list;
    }

    public List<SessionDto> mapListSessionDto(List<EmployeeSession> source){
        List<SessionDto> list = new ArrayList<>();
        for(EmployeeSession session : source){
            SessionDto dto = map(session, SessionDto.class);
            dto.setUsername(session.getEmployee().getUsername());
            dto.setActive(session.isValid());
            list.add(dto);
        }
        return list;
    }

}
