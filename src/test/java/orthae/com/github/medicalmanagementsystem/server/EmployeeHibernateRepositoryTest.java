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
        assertEquals("Daniel", employee.getName());
        assertEquals("Bayne", employee.getSurname());
        assertEquals("baydan", employee.getUsername());
        assertTrue(passwordEncoder.matches("baydan", employee.getPassword()));
        assertEquals(2, employee.getAuthorities().size());
        List<GrantedAuthority> baydanAuth = new ArrayList<>(employee.getAuthorities());
        assertEquals("ROLE_ADMIN", baydanAuth.get(0).getAuthority());
        assertEquals("ROLE_USER", baydanAuth.get(1).getAuthority());

        employee = list.get(9);
        assertEquals("Jana", employee.getName());
        assertEquals("Williamson", employee.getSurname());
        assertEquals("wiljan", employee.getUsername());
        assertTrue(passwordEncoder.matches("wiljan", employee.getPassword()));
        assertEquals("$2y$05$fYs1xCi72853IBiDnz/veOsqtnGa/OlDjP0zYbQMq5vfEZsaiEJGi", employee.getPassword());
        assertEquals(0, employee.getAuthorities().size());

        employee = list.get(22);
        assertEquals("Rocco", employee.getName());
        assertEquals("Bowden", employee.getSurname());
        assertEquals("bowroc", employee.getUsername());
        assertTrue(passwordEncoder.matches("bowroc", employee.getPassword()));
        assertEquals(0, employee.getAuthorities().size());


//  TODO ADD ONE MORE

    }

    @Test
    void findEmployeeById() {
        Employee employee = employeeRepository.find(1);
        assertEquals("Daniel", employee.getName());
        assertEquals("Bayne", employee.getSurname());
        assertEquals("baydan", employee.getUsername());
        assertTrue(passwordEncoder.matches("baydan", employee.getPassword()));
        assertEquals(2, employee.getAuthorities().size());
        List<GrantedAuthority> baydanAuth = new ArrayList<>(employee.getAuthorities());
        assertEquals("ROLE_ADMIN", baydanAuth.get(0).getAuthority());
        assertEquals("ROLE_USER", baydanAuth.get(1).getAuthority());
    }

    @Test
    void findEmployeeByIdTwo() {
        Employee employee = employeeRepository.find(2);
        assertEquals("Richard", employee.getName());
        assertEquals("Morris", employee.getSurname());
        assertEquals("morric", employee.getUsername());
        assertTrue(passwordEncoder.matches("morric", employee.getPassword()));
    }

    @Test
    void findEmployeeByIdZero() {
        Employee employee = employeeRepository.find(0);
        assertNull(employee);
    }

    @Test
    void findByNameNoMatch(){
        List<Employee> list = employeeRepository.find("There is no such name", null);
        assertEquals(0, list.size());
    }

    @Test
    void findByNameOneMatch(){
        List<Employee> list = employeeRepository.find("Chelsy", null);
        assertEquals(1, list.size());

        Employee employee = list.get(0);
        assertEquals("Chelsy", employee.getName());
        assertEquals("Lacey", employee.getSurname());
        assertEquals("lacche", employee.getUsername());
        assertTrue(passwordEncoder.matches("lacche", employee.getPassword()));
        assertEquals(0, employee.getAuthorities().size());
    }

    @Test
    void findByNameMultiMatch(){
        List<Employee> list = employeeRepository.find("Oliver", null);
        assertEquals(2, list.size());

        Employee employee = list.get(0);
        assertEquals("Oliver", employee.getName());
        assertEquals("Weaver", employee.getSurname());
        assertEquals("weaoli", employee.getUsername());
        assertTrue(passwordEncoder.matches("weaoli", employee.getPassword()));
        assertEquals(0, employee.getAuthorities().size());

        employee = list.get(1);
        assertEquals("Oliver", employee.getName());
        assertEquals("Kunal", employee.getSurname());
        assertEquals("kunoli", employee.getUsername());
        assertTrue(passwordEncoder.matches("kunoli", employee.getPassword()));
        assertEquals(0, employee.getAuthorities().size());
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
        assertEquals("Caitlin", employee.getName());
        assertEquals("Salinas", employee.getSurname());
        assertEquals("salcai", employee.getUsername());
        assertTrue(passwordEncoder.matches("salcai", employee.getPassword()));
        assertEquals(0, employee.getAuthorities().size());

    }

    @Test
    void findBySurnameMultiMatch(){
        List<Employee> list = employeeRepository.find(null, "Meyers");
        assertEquals(list.size(), 2);

        Employee employee = list.get(0);
        assertEquals("Leon", employee.getName());
        assertEquals("Meyers", employee.getSurname());
        assertEquals("meyleo", employee.getUsername());
        assertTrue(passwordEncoder.matches("meyleo", employee.getPassword()));
        assertEquals(2, employee.getAuthorities().size());

        employee = list.get(1);
        assertEquals("Nicholas", employee.getName());
        assertEquals("Meyers", employee.getSurname());
        assertEquals("meynic", employee.getUsername());
        assertTrue(passwordEncoder.matches("meynic", employee.getPassword()));
        assertEquals(0, employee.getAuthorities().size());
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
        assertEquals("Hania", employee.getName());
        assertEquals("Black", employee.getSurname());
        assertEquals("blahan", employee.getUsername());
        assertTrue(passwordEncoder.matches("blahan", employee.getPassword()));
        assertEquals(0, employee.getAuthorities().size());

    }

    @Test
    void findByNameAndSurnameMultiMatch(){
        List<Employee> list = employeeRepository.find("Heather", "Paterson");
        assertEquals(list.size(), 2);

        Employee employee = list.get(0);
        assertEquals("Heather", employee.getName());
        assertEquals("Paterson", employee.getSurname());
        assertEquals("patheat", employee.getUsername());
        assertTrue(passwordEncoder.matches("patheat", employee.getPassword()));
        assertEquals(0, employee.getAuthorities().size());

        employee = list.get(1);
        assertEquals("Heather", employee.getName());
        assertEquals("Paterson", employee.getSurname());
        assertEquals("pathea", employee.getUsername());
        assertTrue(passwordEncoder.matches("pathea", employee.getPassword()));
        assertEquals(0, employee.getAuthorities().size());


    }

//  TODO change to different employee
    @Test
    void findByUsername(){
        Employee employee = employeeRepository.find("baydan");
        assertNotNull(employee);
        assertEquals("Daniel", employee.getName());
        assertEquals("Bayne", employee.getSurname());
        assertEquals("baydan", employee.getUsername());
        assertTrue(passwordEncoder.matches("baydan", employee.getPassword()));
        assertEquals(2, employee.getAuthorities().size());
        List<GrantedAuthority> baydanAuth = new ArrayList<>(employee.getAuthorities());
        assertEquals("ROLE_ADMIN", baydanAuth.get(0).getAuthority());
        assertEquals("ROLE_USER", baydanAuth.get(1).getAuthority());
    }

    @Test
    void findByUsernameNoMatch(){
        Employee employee = employeeRepository.find("there is no such username test");
        assertNull(employee);
    }

    @Test
    void createEmployee(){
        Employee employee = new Employee();
        employee.setName("TestName");
        employee.setSurname("TestSurname");
        employee.setUsername("TestUsername");
        employee.setPassword("TestPassword");
        employeeRepository.save(employee);
        System.out.println(employee.getId());
        List<Employee> list = employeeRepository.find("TestName", "TestSurname");
        assertEquals(1, list.size());
    }

    @Test
    void createEmployeeNoUniqueUsername(){

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
        assertEquals(24, list.size());
    }

    @Test
    void deleteEmployeeWrongId(){
//  TODO

    }

}
