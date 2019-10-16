package orthae.com.github.medicalmanagementsystem.server.repository;

import orthae.com.github.medicalmanagementsystem.server.entity.employee.Session;

import java.util.List;

public interface SessionRepository {
        List<Session> findEmployeeSessions(int employeeId);
        List<Session> find(String username, String ipAddress, Boolean active, String created, String expiring);
        Session find(String token);
        void invalidateSession(int sessionId);
        void invalidateEmployeeSessions(int employeeId);
        void save(Session session);

}
