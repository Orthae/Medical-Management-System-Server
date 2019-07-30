package orthae.com.github.medicalmanagementsystem.server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.employee.repository.EmployeeHibernateRepository;
import orthae.com.github.medicalmanagementsystem.server.entity.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@DisplayName("Employee Repository Tests")
class EmployeeHibernateRepositoryTest {

    @Autowired
    EmployeeHibernateRepository employeeDAO;

    @Test
    void findAllEmployeesTest() {
        List<Employee> list = employeeDAO.findAllEmployees();
        assertNotNull(list);
        assertEquals(list.get(0).getName(), "Daniel");
        assertEquals(list.get(0).getSurname(), "Bayne");
        assertEquals(list.get(0).getUsername(), "baydan");
        assertEquals(list.get(0).getPassword(), "{bcrypt}$2y$05$HvEkcNAN5CVoCAX0lP9Jsu/oRFBU2pPhZ2pGKdHUGr4IXTl4FiTYm");
        assertEquals(list.get(0).getEmployeeRoles().size(), 2);
        assertEquals(list.get(0).getEmployeeRoles().get(0).getRole(), "ADMIN");
        assertEquals(list.get(0).getEmployeeRoles().get(1).getRole(), "USER");
        assertEquals(list.get(1).getName(), "Richard");
        assertEquals(list.get(1).getSurname(), "Morris");
        assertEquals(list.get(1).getUsername(), "morric");
        assertEquals(list.get(1).getPassword(), "{noop}admin");
    }

    @Test
    void findEmployeeById() {
        Employee employee = employeeDAO.findEmployeeById(1);
        assertEquals(employee.getName(), "Daniel");
        assertEquals(employee.getSurname(), "Bayne");
        assertEquals(employee.getUsername(), "baydan");
        assertEquals(employee.getPassword(), "{bcrypt}$2y$05$HvEkcNAN5CVoCAX0lP9Jsu/oRFBU2pPhZ2pGKdHUGr4IXTl4FiTYm");
    }

    @Test
    void findEmployeeByIdTwo() {
        Employee employee = employeeDAO.findEmployeeById(2);
        assertEquals(employee.getName(), "Richard");
        assertEquals(employee.getSurname(), "Morris");
        assertEquals(employee.getUsername(), "morric");
        assertEquals(employee.getPassword(), "{noop}admin");
    }

    @Test
    void findEmployeeByIdZero() {
        Employee employee = employeeDAO.findEmployeeById(0);
        assertNull(employee);
    }




}
