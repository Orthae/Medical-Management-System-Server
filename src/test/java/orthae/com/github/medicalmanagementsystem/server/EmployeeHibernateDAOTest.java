package orthae.com.github.medicalmanagementsystem.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.core.Employee;
import orthae.com.github.medicalmanagementsystem.core.EmployeeDAO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EmployeeHibernateDAOTest {

    @Autowired
    EmployeeDAO employeeDAO;

    @Test
    void getAllEmployeesTest() {
        List<Employee> list = employeeDAO.getEmployee();
        assertNotNull(list);
        assertEquals(list.get(0).getName(), "Daniel");
        assertEquals(list.get(0).getSurname(), "Bayne");
        assertEquals(list.get(0).getUsername(), "baydan");
        assertEquals(list.get(0).getPassword(), "randompassword");
        assertEquals(list.get(1).getName(), "Richard");
        assertEquals(list.get(1).getSurname(), "Morris");
        assertEquals(list.get(1).getUsername(), "morric");
        assertEquals(list.get(1).getPassword(), "passwordrandom");
    }

    @Test
    void getEmployeeByIdOne() {
        Employee employee = employeeDAO.getEmployee(1);
        assertEquals(employee.getName(), "Daniel");
        assertEquals(employee.getSurname(), "Bayne");
        assertEquals(employee.getUsername(), "baydan");
        assertEquals(employee.getPassword(), "randompassword");
    }

    @Test
    void getEmployeeByIdTwo() {
        Employee employee = employeeDAO.getEmployee(2);
        assertEquals(employee.getName(), "Richard");
        assertEquals(employee.getSurname(), "Morris");
        assertEquals(employee.getUsername(), "morric");
        assertEquals(employee.getPassword(), "passwordrandom");
    }

    @Test
    void getEmployeeByIdZero() {
        Employee employee = employeeDAO.getEmployee(0);
        assertNull(employee);
    }
}
