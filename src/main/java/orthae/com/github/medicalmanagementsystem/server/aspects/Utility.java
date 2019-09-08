package orthae.com.github.medicalmanagementsystem.server.aspects;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDetailsDto;
import orthae.com.github.medicalmanagementsystem.server.entity.Authority;
import orthae.com.github.medicalmanagementsystem.server.entity.Employee;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Utility {

    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public Utility(PasswordEncoder passwordEncoder){
        this.modelMapper = new ModelMapper();
        this.passwordEncoder = passwordEncoder;
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
        if(source.getPassword() != null && !source.getPassword().isEmpty()){
            destination.setPassword(passwordEncoder.encode(source.getPassword()));

        }
        for(Authority authority : source.getAuthorities()){
            authority.setEmployee(destination);
        }
        if(source.getAuthorities().isEmpty()){
            destination.getAuthorities().clear();
        }
        for(Authority authority : source.getAuthorities()){
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

}
