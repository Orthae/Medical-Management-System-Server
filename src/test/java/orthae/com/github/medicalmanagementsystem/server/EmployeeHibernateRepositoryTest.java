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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, String> params = new HashMap<>();
        params.put("name", "There is no such name");
        List<Employee> list = employeeRepository.find(params);
        assertEquals(0, list.size());
    }

    @Test
    void findByNameOneMatch(){

        Map<String, String> params = new HashMap<>();
        params.put("name", "Chelsy");
        List<Employee> list = employeeRepository.find(params);
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
        Map<String, String> params = new HashMap<>();
        params.put("name", "Oliver");
        List<Employee> list = employeeRepository.find(params);
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
        Map<String, String> params = new HashMap<>();
        params.put("surname", "There is no such surname");
        List<Employee> list = employeeRepository.find(params);
        assertEquals(list.size(), 0);
    }

    @Test
    void findBySurnameOneMatch(){
        Map<String, String> params = new HashMap<>();
        params.put("surname", "Salinas");
        List<Employee> list = employeeRepository.find(params);
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
        Map<String, String> params = new HashMap<>();
        params.put("surname", "Meyers");
        List<Employee> list = employeeRepository.find(params);
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
        Map<String, String> params = new HashMap<>();
        params.put("name", "No such name test");
        params.put("surname", "No such surname test");
        List<Employee> list = employeeRepository.find(params);
        assertEquals(list.size(), 0);
    }

    @Test
    void findByNameAndSurnameOneMatch(){
        Map<String, String> params = new HashMap<>();
        params.put("name", "Hania");
        params.put("surname", "Black");
        List<Employee> list = employeeRepository.find(params);
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
        Map<String, String> params = new HashMap<>();
        params.put("name", "Heather");
        params.put("surname", "Paterson");
        List<Employee> list = employeeRepository.find(params);
        assertEquals(list.size(), 2);

        Employee employee = list.get(0);
        assertEquals("Heather", employee.getName());
        assertEquals("Paterson", employee.getSurname());
        assertEquals("pathea", employee.getUsername());
        assertTrue(passwordEncoder.matches("pathea", employee.getPassword()));
        assertEquals(0, employee.getAuthorities().size());

        employee = list.get(1);
        assertEquals("Heather", employee.getName());
        assertEquals("Paterson", employee.getSurname());
        assertEquals("patheat", employee.getUsername());
        assertTrue(passwordEncoder.matches("patheat", employee.getPassword()));
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
    void uniqueEmail(){
        assertTrue(employeeRepository.isEmailUnique("There is no such email test"));
    }

    @Test
    void notUniqueEmail(){
        assertFalse(employeeRepository.isEmailUnique("daniel.bayne@company.com"));
    }

    @Test
    void uniqueUsername(){
        assertTrue(employeeRepository.isUsernameUnique("There is no such username test"));
    }

    @Test
    void notUniqueUsername(){
        assertFalse(employeeRepository.isUsernameUnique("bonabi"));
    }

    @Test
    void createEmployee(){
        Employee employee = new Employee();
        employee.setName("TestName");
        employee.setSurname("TestSurname");
        employee.setUsername("TestUsername");
        employee.setEmail("TestEmail");
        employee.setPassword("TestPassword");
        employeeRepository.save(employee);

        Map<String, String> params = new HashMap<>();
        params.put("name", "TestName");
        params.put("surname", "TestSurname");
        List<Employee> list = employeeRepository.find(params);
        assertEquals(1, list.size());
    }

    @Test
    void updateEmployee(){
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("TestName");
        employee.setSurname("TestSurname");
        employee.setUsername("TestUsername");
        employee.setPassword("TestPassword");
        employeeRepository.save(employee);
        employee = employeeRepository.find(1);
        assertEquals(1,employee.getId());
        assertEquals("TestName", employee.getName());
        assertEquals("TestSurname", employee.getSurname());
        assertEquals("TestUsername", employee.getUsername());
        assertEquals("TestPassword", employee.getPassword());
    }

    @Test
    void deleteEmployee(){
        Employee employee = employeeRepository.find(1);
        employeeRepository.delete(employee);
        List<Employee> list = employeeRepository.find();
        assertFalse(list.contains(employee));
        assertEquals(24, list.size());
    }

}
