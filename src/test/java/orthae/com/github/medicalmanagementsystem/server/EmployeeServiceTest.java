package orthae.com.github.medicalmanagementsystem.server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.EmployeeDetailsDto;
import orthae.com.github.medicalmanagementsystem.server.employees.service.EmployeeService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@DisplayName("Employee Service Tests")
class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    void findById(){
        EmployeeDetailsDto employee = employeeService.find(1);
        assertEquals(1, employee.getId());
        assertEquals("Daniel", employee.getName());
        assertEquals("Bayne", employee.getSurname());
        assertEquals("baydan", employee.getUsername());
    }

    @Test
    void findAll(){
        List<EmployeeDTO> list = employeeService.find(null, null, null, null);
        assertEquals(25, list.size());
    }

    @Test
    void createEmployee(){
        EmployeeDetailsDto dto = new EmployeeDetailsDto();
        dto.setName("TestName");
        dto.setSurname("TestSurname");
        dto.setUsername("TestUsername");
        dto.setEmail("TestEmail");
        dto.setPassword("TestPassword");
        employeeService.create(dto);

        List<EmployeeDTO> list = employeeService.find("TestName", "TestSurname", null,null);
        EmployeeDTO employeeDTO = list.get(0);
        assertEquals(dto.getName(), employeeDTO.getName());
        assertEquals(dto.getSurname(), employeeDTO.getSurname());
        assertEquals(dto.getUsername(), employeeDTO.getUsername());
        assertEquals(dto.getEmail(), employeeDTO.getEmail());
    }




}
