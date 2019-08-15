package orthae.com.github.medicalmanagementsystem.server.aspects;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
