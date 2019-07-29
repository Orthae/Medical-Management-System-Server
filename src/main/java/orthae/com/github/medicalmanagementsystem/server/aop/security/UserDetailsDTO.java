package orthae.com.github.medicalmanagementsystem.server.aop.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import orthae.com.github.medicalmanagementsystem.server.employee.entity.EmployeeEntity;
import orthae.com.github.medicalmanagementsystem.server.employee.entity.EmployeeRoleEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class UserDetailsDTO implements UserDetails {

    private int id;
    private List<GrantedAuthority> employeeRoles;
    private String username;
    private String password;
    private boolean isAccountNotExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    UserDetailsDTO(EmployeeEntity employee){
        this.id = employee.getId();
        this.username = employee.getUsername();
        this.password = employee.getPassword();
        this.isAccountNotExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
        this.employeeRoles = new ArrayList<>();
         for(EmployeeRoleEntity e : employee.getEmployeeRoles()){
            employeeRoles.add(new SimpleGrantedAuthority( "ROLE_" + e.getRole()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return employeeRoles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNotExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
