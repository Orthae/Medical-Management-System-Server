package orthae.com.github.medicalmanagementsystem.server.repository;

import orthae.com.github.medicalmanagementsystem.server.entity.EmployeeSession;

import java.util.List;

public interface SessionRepository {
        List<EmployeeSession> findEmployeeSessions(int employeeId);
        List<EmployeeSession> find(String username, String ipAddress, Boolean active, String created, String expiring);
        EmployeeSession find(int id);
        EmployeeSession find(String token);
        void invalidateSession(int sessionId);
        void invalidateEmployeeSessions(int employeeId);
        void save(EmployeeSession session);
        void delete(EmployeeSession session);


}
