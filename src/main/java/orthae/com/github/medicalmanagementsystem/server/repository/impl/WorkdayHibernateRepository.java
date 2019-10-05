package orthae.com.github.medicalmanagementsystem.server.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import orthae.com.github.medicalmanagementsystem.server.entity.employee.Workday;
import orthae.com.github.medicalmanagementsystem.server.repository.WorkdayRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class WorkdayHibernateRepository implements WorkdayRepository {

    private EntityManager entityManager;

    public WorkdayHibernateRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Workday> getAll() {
        Session session = entityManager.unwrap(Session.class);
        Query<Workday> query = session.createQuery("FROM Workday", Workday.class);
        return query.getResultList();
    }

    @Override
    public List<Workday> getByEmployeeId(int employeeId) {
        Session session = entityManager.unwrap(Session.class);
        Query<Workday> query = session.createQuery("FROM Workday w WHERE w.employee.id = :id", Workday.class);
        query.setParameter("id", employeeId);
        return query.getResultList();
    }

    @Override
    public void save(Workday workday) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(workday);
    }
}
