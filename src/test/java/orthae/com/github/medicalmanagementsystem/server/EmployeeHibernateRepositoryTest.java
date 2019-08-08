package orthae.com.github.medicalmanagementsystem.server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.entity.Employee;
import orthae.com.github.medicalmanagementsystem.server.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@DisplayName("Employee Repository Tests")
class EmployeeHibernateRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void findAllEmployeesTest() {
        List<Employee> list = employeeRepository.find();
        assertNotNull(list);
        assertEquals(list.size(), 25);

        Employee employee = list.get(0);
        assertEquals(employee.getName(), "Daniel");
        assertEquals(employee.getSurname(), "Bayne");
        assertEquals(employee.getUsername(), "baydan");
        assertEquals(employee.getPassword(), "$2y$05$L8IQDO993A5f/G/z7VjHm.XFwg4rdPCdUkTr/oTa0HaHSMPFQf9fu");
        assertEquals(employee.getAuthorities().size(), 2);
        List<GrantedAuthority> baydanAuth = new ArrayList<>(employee.getAuthorities());
        assertEquals(baydanAuth.get(0).getAuthority(), "ROLE_ADMIN");
        assertEquals(baydanAuth.get(1).getAuthority(), "ROLE_USER");

        employee = list.get(9);
        assertEquals(employee.getName(), "Jana");
        assertEquals(employee.getSurname(), "Williamson");
        assertEquals(employee.getUsername(), "wiljan");
        assertEquals(employee.getPassword(), "$2y$05$fYs1xCi72853IBiDnz/veOsqtnGa/OlDjP0zYbQMq5vfEZsaiEJGi");
        assertEquals(employee.getAuthorities().size(), 0);

//  TODO ADD ONE MORE

    }

    @Test
    void findEmployeeById() {
        Employee employee = employeeRepository.find(1);
        assertEquals(employee.getName(), "Daniel");
        assertEquals(employee.getSurname(), "Bayne");
        assertEquals(employee.getUsername(), "baydan");
        assertEquals(employee.getPassword(), "$2y$05$L8IQDO993A5f/G/z7VjHm.XFwg4rdPCdUkTr/oTa0HaHSMPFQf9fu");
        assertEquals(employee.getAuthorities().size(), 2);
        List<GrantedAuthority> baydanAuth = new ArrayList<>(employee.getAuthorities());
        assertEquals(baydanAuth.get(0).getAuthority(), "ROLE_ADMIN");
        assertEquals(baydanAuth.get(1).getAuthority(), "ROLE_USER");
    }

    @Test
    void findEmployeeByIdTwo() {
        Employee employee = employeeRepository.find(2);
        assertEquals(employee.getName(), "Richard");
        assertEquals(employee.getSurname(), "Morris");
        assertEquals(employee.getUsername(), "morric");
        assertEquals(employee.getPassword(), "$2y$05$dDh/KjkFg7qBdiYUq.haEuH0dmnlIoGg3d7hGaZklEk.BETns0Ma6");
    }

    @Test
    void findEmployeeByIdZero() {
        Employee employee = employeeRepository.find(0);
        assertNull(employee);
    }

    @Test
    void deleteEmployee(){
        Employee employee = employeeRepository.find(1);
        employeeRepository.delete(employee);
        List<Employee> list = employeeRepository.find();
        assertFalse(list.contains(employee));
        assertEquals(list.size(), 24);
    }

    @Test
    void findByNameNoMatch(){
        List<Employee> list = employeeRepository.find("There is no such name", null);
        assertEquals(list.size(), 0);
    }

    @Test
    void findByNameOneMatch(){
        List<Employee> list = employeeRepository.find("Chelsy", null);
        assertEquals(list.size(), 1);

        Employee employee = list.get(0);
        assertEquals(employee.getName(), "Chelsy");
        assertEquals(employee.getSurname(), "Lacey");
        assertEquals(employee.getUsername(), "lacche");
        assertEquals(employee.getPassword(), "$2y$05$MQXAEsD48oQPNiwTx1l8S.tI8sfLdeInk2eym2MnIVHzukRXdJxK2");
        assertEquals(employee.getAuthorities().size(), 0);
    }

    @Test
    void findByNameMultiMatch(){
        List<Employee> list = employeeRepository.find("Oliver", null);
        assertEquals(list.size(), 2);

        Employee employee = list.get(0);
        assertEquals(employee.getName(), "Oliver");
        assertEquals(employee.getSurname(), "Weaver");
        assertEquals(employee.getUsername(), "weaoli");
        assertEquals(employee.getPassword(), "$2y$05$TmdTZBHyZzLVSHJHiSRntuHGnJMBkbLjsNKR1UIlKJKZ.XWMXwiBO");
        assertEquals(employee.getAuthorities().size(), 0);

        employee = list.get(1);
        assertEquals(employee.getName(), "Oliver");
        assertEquals(employee.getSurname(), "Kunal");
        assertEquals(employee.getUsername(), "kunoli");
        assertEquals(employee.getPassword(), "$2y$05$AyxkcHxUFMa.1J4my92OLOQlIJDxWly1CYo8vOtK1QqT6Wa1bg5CG");
        assertEquals(employee.getAuthorities().size(), 0);
    }

//  TODO change to different employee
    @Test
    void findByUsername(){
        Employee employee = employeeRepository.find("baydan");
        assertNotNull(employee);
        assertEquals(employee.getName(), "Daniel");
        assertEquals(employee.getSurname(), "Bayne");
        assertEquals(employee.getUsername(), "baydan");
        assertEquals(employee.getPassword(), "$2y$05$L8IQDO993A5f/G/z7VjHm.XFwg4rdPCdUkTr/oTa0HaHSMPFQf9fu");
        assertEquals(employee.getAuthorities().size(), 2);
        List<GrantedAuthority> baydanAuth = new ArrayList<>(employee.getAuthorities());
        assertEquals(baydanAuth.get(0).getAuthority(), "ROLE_ADMIN");
        assertEquals(baydanAuth.get(1).getAuthority(), "ROLE_USER");
    }

    @Test
    void findByUsernameNoMatch(){
        Employee employee = employeeRepository.find("there is no such username test");
        assertNull(employee);
    }

//  TODO add find by surname, name and surname, save tests.

//    @Test
//    void findByNameAndSurname(){
//        List<Employee> list = employeeRepository.find("Ellouise", "Mullins");
//        assertEquals(list.size(), 0);
//    }

}
