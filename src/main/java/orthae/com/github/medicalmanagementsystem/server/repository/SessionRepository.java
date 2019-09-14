package orthae.com.github.medicalmanagementsystem.server.repository;

import orthae.com.github.medicalmanagementsystem.server.entity.Session;

import java.util.List;

public interface SessionRepository {
        Session find(int id);
        List<Session> findEmployeeSessions(int employeeId);
        Session find(String token);
        void save(Session session);
        void delete(Session session);


}
