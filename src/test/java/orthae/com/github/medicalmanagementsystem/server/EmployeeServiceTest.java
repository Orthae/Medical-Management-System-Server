package orthae.com.github.medicalmanagementsystem.server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employees.service.EmployeeService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Employee Service Tests")
class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;


    @Test
    void findById(){
        EmployeeDTO employee = employeeService.find(1);
        assertEquals(1, employee.getId());
        assertEquals("Daniel", employee.getName());
        assertEquals("Bayne", employee.getSurname());
        assertEquals("baydan", employee.getUsername());
    }

    @Test
    void findAll(){
        List<EmployeeDTO> list = employeeService.find(null, null);
        assertEquals(25, list.size());
    }






}
