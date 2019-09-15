package orthae.com.github.medicalmanagementsystem.server.repository;

import orthae.com.github.medicalmanagementsystem.server.entity.Session;

import java.util.List;

public interface SessionRepository {
        List<Session> findEmployeeSessions(int employeeId);
        List<Session> find();
        Session find(int id);
        Session find(String token);
        void save(Session session);
        void delete(Session session);


}
