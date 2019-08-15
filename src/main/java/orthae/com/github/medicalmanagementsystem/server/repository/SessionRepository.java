package orthae.com.github.medicalmanagementsystem.server.repository;

import orthae.com.github.medicalmanagementsystem.server.entity.Session;

public interface SessionRepository {

        Session find(int id);
        Session find(String token);
        void save(Session session);
        void delete(Session session);


}
