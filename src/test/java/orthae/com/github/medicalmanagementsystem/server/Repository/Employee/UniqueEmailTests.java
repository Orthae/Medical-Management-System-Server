package orthae.com.github.medicalmanagementsystem.server.Repository.Employee;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.repository.EmployeeRepository;

@SpringBootTest
@Transactional
@DisplayName("Employee Repository UniqueEmail Tests")
class UniqueEmailTests {

    @Autowired
    private EmployeeRepository employeeRepository;


}
