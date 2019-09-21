package orthae.com.github.medicalmanagementsystem.server.aspects.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.entity.Employee;
import orthae.com.github.medicalmanagementsystem.server.repository.EmployeeRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private EmployeeRepository employeeRepository;

    @Autowired
    UserDetailsServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.get(username);
        if(employee == null)
            throw new UsernameNotFoundException(username);
        employee.getAuthorities().size();
        return employee;
    }
}
