package orthae.com.github.medicalmanagementsystem.server.employee.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.employee.dao.EmployeeHibernateDAO;
import orthae.com.github.medicalmanagementsystem.server.employee.dto.CreateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employee.dto.UpdateEmployeeDTO;
import orthae.com.github.medicalmanagementsystem.server.employee.entity.EmployeeEntity;

import java.util.List;

@Service
public class EmployeeServerService {

    private EmployeeHibernateDAO employeeDAO;

    @Autowired
    public EmployeeServerService(EmployeeHibernateDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Transactional
    public List<EmployeeEntity> getEmployee(String name, String surname) {
        if (name == null && surname == null)
            return employeeDAO.getEmployee();
        else
            return employeeDAO.getEmployee(name, surname);
    }

    @Transactional
    public EmployeeEntity getEmployee(int id) {
        return employeeDAO.getEmployee(id);
    }

    @Transactional
    public void createEmployee(CreateEmployeeDTO employeeDTO) {
        ModelMapper mapper = new ModelMapper();
        EmployeeEntity employeeEntity = mapper.map(employeeDTO, EmployeeEntity.class);
        employeeDAO.saveEmployee(employeeEntity);
    }

    @Transactional
    public void deleteEmployee(int id) {
        employeeDAO.deleteEmployee(id);
    }

    @Transactional
    public void updateEmployee(UpdateEmployeeDTO employeeDTO) {
        ModelMapper mapper = new ModelMapper();
        EmployeeEntity employeeEntity = mapper.map(employeeDTO, EmployeeEntity.class);
        employeeDAO.saveEmployee(employeeEntity);
    }
}
