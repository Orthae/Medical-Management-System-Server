package orthae.com.github.medicalmanagementsystem.server.repository.impl;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import orthae.com.github.medicalmanagementsystem.server.entity.Employee;
import orthae.com.github.medicalmanagementsystem.server.entity.EmployeeSession;
import orthae.com.github.medicalmanagementsystem.server.repository.SessionRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class SessionHibernateRepository implements SessionRepository {

    private EntityManager entityManager;

    @Autowired
    public SessionHibernateRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public EmployeeSession find(int id) {
        org.hibernate.Session hSession = entityManager.unwrap(org.hibernate.Session.class);
        return hSession.get(EmployeeSession.class, id);
    }

    @Override
    public List<EmployeeSession> findEmployeeSessions(int employeeId) {
        org.hibernate.Session hSession = entityManager.unwrap(org.hibernate.Session.class);
        Query<EmployeeSession> query = hSession.createQuery("FROM Session s WHERE s.employee.id = :employeeId", EmployeeSession.class);
        query.setParameter("employeeId", employeeId);
        return query.getResultList();
    }

    @Override
    public EmployeeSession find(String token) {
        org.hibernate.Session hSession = entityManager.unwrap(org.hibernate.Session.class);
        Query<EmployeeSession> query = hSession.createQuery("FROM Session WHERE sessionToken = :sessionToken", EmployeeSession.class);
        query.setParameter("sessionToken", token);
        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public void invalidateSession(int sessionId) {
        org.hibernate.Session hSession = entityManager.unwrap(org.hibernate.Session.class);
        Query query = hSession.createQuery("UPDATE Session SET sessionExpiry = UTC_TIMESTAMP WHERE id = :id");
        query.setParameter("id", sessionId);
        query.executeUpdate();
    }

    @Override
    public void invalidateEmployeeSessions(int employeeId) {
        org.hibernate.Session hSession = entityManager.unwrap(org.hibernate.Session.class);
        Query query = hSession.createQuery("UPDATE Session SET sessionExpiry = UTC_TIMESTAMP WHERE employee.id = :id");
        query.setParameter("id", employeeId);
        query.executeUpdate();
    }

    @Override
    public List<EmployeeSession> find(String username, String ipAddress, Boolean active, String created, String expiring){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EmployeeSession> criteria = builder.createQuery(EmployeeSession.class);
        Root<EmployeeSession> root = criteria.from(EmployeeSession.class);
        Join<EmployeeSession, Employee> join = root.join("employee");
        List<Predicate> list = new ArrayList<>();
        if(username != null)
            list.add(builder.like(join.get("username"), username));
        if (ipAddress != null)
            list.add(builder.like(root.get("ipAddress"), ipAddress));
        if(active != null){
            if(active)
                list.add(builder.greaterThan(root.get("sessionExpiry"), new Date()));
            else
                list.add(builder.lessThan(root.get("sessionExpiry"), new Date()));
        }
        if(created != null)
            list.add(builder.like(root.get("sessionCreation").as(String.class), created + "%"));
        if(expiring != null)
            list.add(builder.like(root.get("sessionExpiry").as(String.class), expiring + "%"));
        criteria.where(builder.and(list.toArray(new Predicate[0]))).orderBy(builder.asc(root.get("id")));
        TypedQuery<EmployeeSession> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }


    @Override
    public void save(EmployeeSession session) {
        org.hibernate.Session hSession = entityManager.unwrap(org.hibernate.Session.class);
        hSession.saveOrUpdate(session);
    }

    @Override
    public void delete(EmployeeSession session) {
        org.hibernate.Session hSession = entityManager.unwrap(org.hibernate.Session.class);
        hSession.delete(session);
    }
}
