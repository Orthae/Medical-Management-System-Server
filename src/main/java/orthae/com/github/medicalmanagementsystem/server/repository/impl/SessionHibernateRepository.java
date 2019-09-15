package orthae.com.github.medicalmanagementsystem.server.repository.impl;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import orthae.com.github.medicalmanagementsystem.server.entity.Session;
import orthae.com.github.medicalmanagementsystem.server.repository.SessionRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class SessionHibernateRepository implements SessionRepository {

    private EntityManager entityManager;

    @Autowired
    public SessionHibernateRepository(EntityManager entityManager ){
        this.entityManager = entityManager;
    }

    @Override
    public Session find(int id) {
        org.hibernate.Session hSession = entityManager.unwrap(org.hibernate.Session.class);
        return hSession.get(Session.class, id);
    }

    @Override
    public List<Session> findEmployeeSessions(int employeeId) {
        org.hibernate.Session hSession = entityManager.unwrap(org.hibernate.Session.class);
        Query<Session> query = hSession.createQuery("FROM Session s WHERE s.employee.id = :employeeId", Session.class);
        query.setParameter("employeeId", employeeId);
        return query.getResultList();
    }

    @Override
    public List<Session> find() {
        org.hibernate.Session hSession = entityManager.unwrap(org.hibernate.Session.class);
        Query<Session> query = hSession.createQuery("FROM Session", Session.class);
        return query.getResultList();
    }

    @Override
    public Session find(String token) {
        org.hibernate.Session hSession = entityManager.unwrap(org.hibernate.Session.class);
        Query<Session> query = hSession.createQuery("FROM Session WHERE sessionToken = :sessionToken", Session.class);
        query.setParameter("sessionToken", token);
        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public void save(Session session) {
        org.hibernate.Session hSession = entityManager.unwrap(org.hibernate.Session.class);
        hSession.saveOrUpdate(session);
    }

    @Override
    public void delete(Session session) {
        org.hibernate.Session hSession = entityManager.unwrap(org.hibernate.Session.class);
        hSession.delete(session);
    }
}
