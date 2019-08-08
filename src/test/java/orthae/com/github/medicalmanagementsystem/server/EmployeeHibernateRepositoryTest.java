package orthae.com.github.medicalmanagementsystem.server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    PasswordEncoder passwordEncoder;

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

        employee = list.get(22);
        assertEquals(employee.getName(), "Rocco");
        assertEquals(employee.getSurname(), "Bowden");
        assertEquals(employee.getUsername(), "bowroc");
        assertEquals(employee.getPassword(), "$2y$05$6dg1EmZ0as6IatdCyAJNpuIgcOdDCKcfYtc7EGkKviA4RBqX/zE06");
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

    @Test
    void findBySurnameNoMatch(){
        List<Employee> list = employeeRepository.find(null, "there is no such surname");
        assertEquals(list.size(), 0);
    }

    @Test
    void findBySurnameOneMatch(){
        List<Employee> list = employeeRepository.find(null, "Salinas");
        assertEquals(list.size(), 1);
        Employee employee = list.get(0);
        assertEquals(employee.getName(), "Caitlin");
        assertEquals(employee.getSurname(), "Salinas");
        assertEquals(employee.getUsername(), "salcai");
        assertEquals(employee.getPassword(), "$2y$05$2QfAwCUZaQD7LKrSgzN1.OREG2ksBkOXGmdHVPsVUQILjCsAXYrzC");
        assertEquals(employee.getAuthorities().size(), 0);

    }

    @Test
    void findBySurnameMultiMatch(){
        List<Employee> list = employeeRepository.find(null, "Meyers");
        assertEquals(list.size(), 2);

        Employee employee = list.get(0);
        assertEquals(employee.getName(), "Leon");
        assertEquals(employee.getSurname(), "Meyers");
        assertEquals(employee.getUsername(), "meyleo");
        assertEquals(employee.getPassword(), "$2y$05$TjdhULMjfV1bwjx67pzrjuGc7S1gAmtMQtY1pMosjoWqmslCJmPy2");
        assertEquals(employee.getAuthorities().size(), 2);

        employee = list.get(1);
        assertEquals(employee.getName(), "Nicholas");
        assertEquals(employee.getSurname(), "Meyers");
        assertEquals(employee.getUsername(), "meynic");
        assertEquals(employee.getPassword(), "$2y$05$fyNfpRs.P8C4g64E6ba9DeCvU8jXpKxycUxVEGTqB52RT0WIr6.5q");
        assertEquals(employee.getAuthorities().size(), 0);
    }

    @Test
    void findByNameAndSurnameNoMatch(){
        List<Employee> list = employeeRepository.find("No such name test", "No such surname test");
        assertEquals(list.size(), 0);
    }

    @Test
    void findByNameAndSurnameOneMatch(){
        List<Employee> list = employeeRepository.find("Hania", "Black");
        assertEquals(list.size(), 1);

        Employee employee = list.get(0);
        assertEquals(employee.getName(), "Hania");
        assertEquals(employee.getSurname(), "Black");
        assertEquals(employee.getUsername(), "blahan");
        assertEquals(employee.getPassword(), "$2y$05$xqjHh3NpsRcWEYesvkiWueSLU7ARMQl2OTHR6lDOkbPCawEkVhYSy");
        assertEquals(employee.getAuthorities().size(), 0);

    }

    @Test
    void findByNameAndSurnameMultiMatch(){
        List<Employee> list = employeeRepository.find("Heather", "Paterson");
        assertEquals(list.size(), 2);

        Employee employee = list.get(0);
        assertEquals(employee.getName(), "Heather");
        assertEquals(employee.getSurname(), "Paterson");
        assertEquals(employee.getUsername(), "patheat");
        assertEquals(employee.getPassword(), "$2y$05$TUfqZkJQ9hNpn6cVpwbnOOICCkji82jK4kFuVQXexkcsC60pH4ROq");
        assertEquals(employee.getAuthorities().size(), 0);

        employee = list.get(1);
        assertEquals(employee.getName(), "Heather");
        assertEquals(employee.getSurname(), "Paterson");
        assertEquals(employee.getUsername(), "pathea");
        assertEquals(employee.getPassword(), "$2y$05$nOV.TLjdsSgLVKjFytgm4.L9f/lem06UxTabB9B6NnBVgoox8nn/.");
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

    @Test
    void createEmployee(){
//  TODO

    }

    @Test
    void createEmployeeNoUniqueUsername(){
//  TODO

    }

    @Test
    void updateEmployee(){
//  TODO
    }

    @Test
    void updateEmployeeNoUniqueUsername(){
//  TODO
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
    void deleteEmployeeWrongId(){
//  TODO

    }

}
