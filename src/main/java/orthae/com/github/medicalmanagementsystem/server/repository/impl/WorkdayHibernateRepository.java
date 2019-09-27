package orthae.com.github.medicalmanagementsystem.server.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import orthae.com.github.medicalmanagementsystem.server.entity.employee.Workday;
import orthae.com.github.medicalmanagementsystem.server.repository.WorkdayRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
    public List<Workday> search(int employeeId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Workday> criteriaQuery = builder.createQuery(Workday.class);
        Root<Workday> root = criteriaQuery.from(Workday.class);
        List<Predicate> list = new ArrayList<>();
        criteriaQuery.where();
        TypedQuery<Workday> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }


}
