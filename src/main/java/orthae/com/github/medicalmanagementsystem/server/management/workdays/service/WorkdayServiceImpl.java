package orthae.com.github.medicalmanagementsystem.server.management.workdays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.entity.employee.Employee;
import orthae.com.github.medicalmanagementsystem.server.entity.employee.Workday;
import orthae.com.github.medicalmanagementsystem.server.management.workdays.dto.WorkdayDto;
import orthae.com.github.medicalmanagementsystem.server.management.workdays.utility.WorkdayUtility;
import orthae.com.github.medicalmanagementsystem.server.repository.EmployeeRepository;
import orthae.com.github.medicalmanagementsystem.server.repository.WorkdayRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkdayServiceImpl implements WorkdayService {

    private EmployeeRepository employeeRepository;
    private WorkdayRepository workdayRepository;
    private WorkdayUtility workdayUtility;

    public WorkdayServiceImpl(EmployeeRepository employeeRepository, WorkdayRepository workdayRepository, WorkdayUtility workdayUtility) {
        this.employeeRepository = employeeRepository;
        this.workdayRepository = workdayRepository;
        this.workdayUtility = workdayUtility;
    }

    @Transactional
    @Override
    public List<WorkdayDto> getAll() {
        List<Workday> list = workdayRepository.getAll();
        return list.stream().map(workdayUtility::map).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<WorkdayDto> getByEmployeeId(int employeeId) {
        List<Workday> list = workdayRepository.getByEmployeeId(employeeId);
        return list.stream().map(workdayUtility::map).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void createWorkday(int employeeId, WorkdayDto dto) {
        Employee employee = employeeRepository.getById(employeeId);
        Workday workday = workdayUtility.map(dto);
        List<Workday> list = workdayRepository.getByEmployeeIdAndDate(employeeId, dto.getDate());
//  TODO Make proper exception
        for(Workday wday : list){
            if(workdayUtility.hourCollision(wday, dto))
                throw new RuntimeException("Hour collision");
        }
        workday.setEmployee(employee);
        workdayRepository.save(workday);
    }

//  TODO Make utility class




    
}
