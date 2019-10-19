package orthae.com.github.medicalmanagementsystem.server.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import orthae.com.github.medicalmanagementsystem.server.entity.employee.Employee;
import orthae.com.github.medicalmanagementsystem.server.entity.employee.Workday;
import orthae.com.github.medicalmanagementsystem.server.repository.WorkdayRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
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
    public List<Workday> getByEmployeeIdAndMonth(int employeeId, int month, int year) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Workday> cQuery = builder.createQuery(Workday.class);
        Root<Workday> workday = cQuery.from(Workday.class);
        Join<Workday, Employee> employee = workday.join("employee");
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(employee.get("id"), employeeId));
        predicates.add(builder.like(workday.get("date").as(String.class), year + "-" + month + "%"));
        cQuery.where(builder.and(predicates.toArray(new Predicate[0])));
        TypedQuery<Workday> tQuery = entityManager.createQuery(cQuery);
        return tQuery.getResultList();
    }

    @Override
    public List<Workday> getByEmployeeIdAndDate(int employeeId, LocalDate date) {
        Session session = entityManager.unwrap(Session.class);
        Query<Workday> query = session.createQuery("FROM Workday w WHERE w.employee.id = :id AND w.date = :date", Workday.class);
        query.setParameter("id", employeeId);
        query.setParameter("date", date);
        return query.getResultList();
    }



    @Override
    public List<Workday> searchByParams(String username, String from, String to) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Workday> cQuery = builder.createQuery(Workday.class);
        Root<Workday> workday = cQuery.from(Workday.class);
        Join<Workday, Employee> employee = workday.join("employee");
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(workday.get("employee"), employee));


        cQuery.where(builder.and(predicates.toArray(new Predicate[0])));
        TypedQuery<Workday> tQuery = entityManager.createQuery(cQuery);
        return tQuery.getResultList();
    }


    @Override
    public void save(Workday workday) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(workday);
    }
}
