package orthae.com.github.medicalmanagementsystem.server.aop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.employee.repository.EmployeeRepository;
import orthae.com.github.medicalmanagementsystem.server.entity.Employee;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private EmployeeRepository employeeRepository;

    @Autowired
    UserDetailsServiceImp(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findEmployeeByUsername(username);
        if(employee == null)
            throw new UsernameNotFoundException(username);
        employee.getAuthorities().size();
        return employee;
    }
}
