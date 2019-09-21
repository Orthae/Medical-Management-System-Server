package orthae.com.github.medicalmanagementsystem.server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
@DisplayName("Employee Hibernate Repository Tests")
class EmployeeHibernateRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Nested
    @DisplayName("Unique Email Tests")
    class UniqueEmailTests {

        @Nested
        @Transactional
        @SpringBootTest
        @DisplayName("On Creating")
        class OnCreate {
            @Test
            void uniqueEmailCreate(){
                assertTrue(employeeRepository.isEmailUnique(0,"There is no such email test"));
            }

            @Test
            void notUniqueEmailCreate(){
                assertFalse(employeeRepository.isEmailUnique(0,"daniel.bayne@company.com"));
            }
        }

        @Nested
        @Transactional
        @SpringBootTest
        @DisplayName("On Updating")
        class OnUpdate {
            @Test
            void uniqueEmailUpdate() {
                assertTrue(employeeRepository.isEmailUnique(1,"daniel.bayne@company.com"));
            }

            @Test
            void notUniqueEmailUpdate() {
                assertFalse(employeeRepository.isEmailUnique(0,"daniel.bayne@company.com"));
            }

        }

    }

    @Nested
    @DisplayName("Unique Username Tests")
    class UniqueUsernameTests{

        @Nested
        @Transactional
        @SpringBootTest
        @DisplayName("On Creating")
        class onCreate {
            @Test
            void uniqueUsernameCreate(){
                assertTrue(employeeRepository.isUsernameUnique(0,"There is no such username test"));
            }

            @Test
            void notUniqueUsernameCreate(){
                assertFalse(employeeRepository.isUsernameUnique(0,"bonabi"));
            }

        }

        @Nested
        @Transactional
        @SpringBootTest
        @DisplayName("On Updating")
        class onUpdate {
            @Test
            void uniqueUsernameUpdate(){
                assertTrue(employeeRepository.isUsernameUnique(16,"bonabi"));
            }

            @Test
            void notUniqueUsernameUpdate(){
                assertFalse(employeeRepository.isUsernameUnique(0,"bonabi"));
            }
        }

    }

    @Nested
    @Transactional
    @SpringBootTest
    @DisplayName("Basic CRUD Tests")
    class BasicCrud {

        @Test
        void findAllEmployeesTest() {
            List<Employee> list = employeeRepository.search(null, null, null, null, null, null);
            assertNotNull(list);
            assertEquals(25, list.size());

            Employee employee = list.get(0);
            assertEquals("Daniel", employee.getName());
            assertEquals("Bayne", employee.getSurname());
            assertEquals("baydan", employee.getUsername());
            assertTrue(passwordEncoder.matches("baydan", employee.getPassword()));
            assertEquals(1, employee.getAuthorities().size());
            List<GrantedAuthority> baydanAuth = new ArrayList<>(employee.getAuthorities());
            assertEquals("MANAGEMENT", baydanAuth.get(0).getAuthority());

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
            Employee employee = employeeRepository.get(1);
            assertEquals("Daniel", employee.getName());
            assertEquals("Bayne", employee.getSurname());
            assertEquals("baydan", employee.getUsername());
            assertTrue(passwordEncoder.matches("baydan", employee.getPassword()));
            assertEquals(1, employee.getAuthorities().size());
            List<GrantedAuthority> baydanAuth = new ArrayList<>(employee.getAuthorities());
            assertEquals("MANAGEMENT", baydanAuth.get(0).getAuthority());
        }

        @Test
        void findEmployeeByIdTwo() {
            Employee employee = employeeRepository.get(2);
            assertEquals("Richard", employee.getName());
            assertEquals("Morris", employee.getSurname());
            assertEquals("morric", employee.getUsername());
            assertTrue(passwordEncoder.matches("morric", employee.getPassword()));
        }

        @Test
        void findEmployeeByIdZero() {
            Employee employee = employeeRepository.get(0);
            assertNull(employee);
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

            List<Employee> list = employeeRepository.search("TestName", "TestSurname", null, null, null, null);
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
            employee = employeeRepository.get(1);
            assertEquals(1,employee.getId());
            assertEquals("TestName", employee.getName());
            assertEquals("TestSurname", employee.getSurname());
            assertEquals("TestUsername", employee.getUsername());
            assertEquals("TestPassword", employee.getPassword());
        }

        @Test
        void deleteEmployee(){
            Employee employee = employeeRepository.get(1);
            employeeRepository.delete(employee);
            List<Employee> list = employeeRepository.search(null, null, null, null, null, null);
            assertFalse(list.contains(employee));
            assertEquals(24, list.size());
        }
    }

    @Nested
    @Transactional
    @SpringBootTest
    @DisplayName("Searching Repository Tests")
    class SearchingEmployee {
        @Test
        void findByNameNoMatch(){
            List<Employee> list = employeeRepository.search("There is no such name", null, null, null, null, null);
            assertEquals(0, list.size());
        }

        @Test
        void findByNameOneMatch(){
            List<Employee> list = employeeRepository.search("Chelsy", null, null, null, null, null);
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
            List<Employee> list = employeeRepository.search("Oliver", null, null, null, null, null);
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
            List<Employee> list = employeeRepository.search(null, "There is no such surname", null, null, null, null);
            assertEquals(list.size(), 0);
        }

        @Test
        void findBySurnameOneMatch(){
            List<Employee> list = employeeRepository.search(null, "Salinas", null, null, null, null);
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
            List<Employee> list = employeeRepository.search(null, "Meyers", null, null, null, null);
            assertEquals(list.size(), 2);

            Employee employee = list.get(0);
            assertEquals("Leon", employee.getName());
            assertEquals("Meyers", employee.getSurname());
            assertEquals("meyleo", employee.getUsername());
            assertTrue(passwordEncoder.matches("meyleo", employee.getPassword()));

            employee = list.get(1);
            assertEquals("Nicholas", employee.getName());
            assertEquals("Meyers", employee.getSurname());
            assertEquals("meynic", employee.getUsername());
            assertTrue(passwordEncoder.matches("meynic", employee.getPassword()));
            assertEquals(0, employee.getAuthorities().size());
        }

        @Test
        void findByNameAndSurnameNoMatch(){
            List<Employee> list = employeeRepository.search("No such name test", "There is no such surname", null, null, null, null);
            assertEquals(list.size(), 0);
        }

        @Test
        void findByNameAndSurnameOneMatch(){
            List<Employee> list = employeeRepository.search("Hania", "Black", null, null, null, null);
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
            List<Employee> list = employeeRepository.search("Heather", "Paterson", null, null, null, null);
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
            Employee employee = employeeRepository.get("baydan");
            assertNotNull(employee);

            assertEquals("Daniel", employee.getName());
            assertEquals("Bayne", employee.getSurname());
            assertEquals("baydan", employee.getUsername());
            assertTrue(passwordEncoder.matches("baydan", employee.getPassword()));
            assertEquals(1, employee.getAuthorities().size());
            List<GrantedAuthority> baydanAuth = new ArrayList<>(employee.getAuthorities());
            assertEquals("MANAGEMENT", baydanAuth.get(0).getAuthority());
        }

        @Test
        void findByEmail(){
            List<Employee> list = employeeRepository.search(null, null, null, "danica.landry@company.com", null, null);
            Employee employee = list.get(0);
            assertNotNull(employee);
        }

        @Test
        void findByEmailNoMatch(){
            List<Employee> list = employeeRepository.search(null, null, null, "There is no such email", null, null);
            assertEquals(0, list.size());
        }

        @Test
        void findByUsernameNoMatch(){
            Employee employee = employeeRepository.get("there is no such username test");
            assertNull(employee);
        }



    }





    @Test
    void changePassword(){
        employeeRepository.changePassword(1, "Testing");
        Employee employee = employeeRepository.get(1);
        assertEquals("Testing", employee.getPassword());
    }

}
