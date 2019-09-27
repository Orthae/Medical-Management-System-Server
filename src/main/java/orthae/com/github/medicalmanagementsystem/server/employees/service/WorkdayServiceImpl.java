package orthae.com.github.medicalmanagementsystem.server.employees.service;

import org.springframework.stereotype.Service;
import orthae.com.github.medicalmanagementsystem.server.aspects.Utility;
import orthae.com.github.medicalmanagementsystem.server.employees.dto.WorkdayDto;
import orthae.com.github.medicalmanagementsystem.server.repository.WorkdayRepository;

import java.util.List;

@Service
public class WorkdayServiceImpl implements WorkdayService {

    private Utility utility;
    private WorkdayRepository workdayRepository;

    public WorkdayServiceImpl(Utility utility, WorkdayRepository workdayRepository) {
        this.utility = utility;
        this.workdayRepository = workdayRepository;
    }

    @Override
    public List<WorkdayDto> getAllByEmployeeId(int employeeId) {
        return utility.mapAll(workdayRepository.search(employeeId), WorkdayDto.class);
    }
}
