package orthae.com.github.medicalmanagementsystem.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.employee.dao.EmployeeHibernateDAO;
import orthae.com.github.medicalmanagementsystem.server.employee.entity.EmployeeEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EmployeeHibernateDAOTest {

    @Autowired
    EmployeeHibernateDAO employeeDAO;

    @Test
    void getAllEmployeesTest() {
        List<EmployeeEntity> list = employeeDAO.getEmployee();
        assertNotNull(list);
        assertEquals(list.get(0).getName(), "Daniel");
        assertEquals(list.get(0).getSurname(), "Bayne");
        assertEquals(list.get(0).getUsername(), "baydan");
        assertEquals(list.get(0).getPassword(), "{bcrypt}$2y$05$HvEkcNAN5CVoCAX0lP9Jsu/oRFBU2pPhZ2pGKdHUGr4IXTl4FiTYm");
        assertEquals(list.get(0).getEmployeeRoles().size(), 2);
        assertEquals(list.get(0).getEmployeeRoles().get(0).getRole(), "ADMIN");
        assertEquals(list.get(0).getEmployeeRoles().get(1).getRole(), "USER");
        assertEquals(list.get(0).getPassword(), "{bcrypt}$2y$05$HvEkcNAN5CVoCAX0lP9Jsu/oRFBU2pPhZ2pGKdHUGr4IXTl4FiTYm");
        assertEquals(list.get(1).getName(), "Richard");
        assertEquals(list.get(1).getSurname(), "Morris");
        assertEquals(list.get(1).getUsername(), "morric");
        assertEquals(list.get(1).getPassword(), "{noop}admin");
        assertEquals(list.get(1).getEmployeeRoles().size(), 0);
    }

    @Test
    void getEmployeeByIdOne() {
        EmployeeEntity employee = employeeDAO.getEmployee(1);
        assertEquals(employee.getName(), "Daniel");
        assertEquals(employee.getSurname(), "Bayne");
        assertEquals(employee.getUsername(), "baydan");
        assertEquals(employee.getPassword(), "{bcrypt}$2y$05$HvEkcNAN5CVoCAX0lP9Jsu/oRFBU2pPhZ2pGKdHUGr4IXTl4FiTYm");
    }

    @Test
    void getEmployeeByIdTwo() {
        EmployeeEntity employee = employeeDAO.getEmployee(2);
        assertEquals(employee.getName(), "Richard");
        assertEquals(employee.getSurname(), "Morris");
        assertEquals(employee.getUsername(), "morric");
        assertEquals(employee.getPassword(), "{noop}admin");
    }

    @Test
    void getEmployeeByIdZero() {
        EmployeeEntity employee = employeeDAO.getEmployee(0);
        assertNull(employee);
    }

}
