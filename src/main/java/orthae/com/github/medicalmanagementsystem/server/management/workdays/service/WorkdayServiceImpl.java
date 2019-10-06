package orthae.com.github.medicalmanagementsystem.server.management.workdays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.entity.employee.Employee;
import orthae.com.github.medicalmanagementsystem.server.entity.employee.Workday;
import orthae.com.github.medicalmanagementsystem.server.management.workdays.dto.WorkdayDto;
import orthae.com.github.medicalmanagementsystem.server.repository.EmployeeRepository;
import orthae.com.github.medicalmanagementsystem.server.repository.WorkdayRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkdayServiceImpl implements WorkdayService {

    private EmployeeRepository employeeRepository;
    private WorkdayRepository workdayRepository;

    public WorkdayServiceImpl(EmployeeRepository employeeRepository, WorkdayRepository workdayRepository) {
        this.employeeRepository = employeeRepository;
        this.workdayRepository = workdayRepository;
    }

    @Override
    public List<WorkdayDto> getAll() {
        List<Workday> list = workdayRepository.getAll();
        return list.stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public List<WorkdayDto> getByEmployeeId(int employeeId) {
        List<Workday> list = workdayRepository.getByEmployeeId(employeeId);
        return list.stream().map(this::map).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void createWorkday(int employeeId, WorkdayDto dto) {
        Employee employee = employeeRepository.get(employeeId);
        Workday workday = map(dto);
        workday.setEmployee(employee);
        workdayRepository.save(workday);
    }

    private WorkdayDto map(Workday workday){
        WorkdayDto dto = new WorkdayDto();
        dto.setId(workday.getId());
        dto.setStartHour(workday.getStartHour());
        dto.setEndHour(workday.getEndHour());
        dto.setDate(workday.getDate());
        return dto;
    }

    private Workday map(WorkdayDto dto){
        Workday workday = new Workday();
        workday.setId(dto.getId());
        workday.setStartHour(dto.getStartHour());
        workday.setEndHour(dto.getEndHour());
        workday.setDate(dto.getDate().plusDays(1));
        return workday;
    }

}
