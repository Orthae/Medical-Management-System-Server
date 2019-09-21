package orthae.com.github.medicalmanagementsystem.server.repository.impl;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import orthae.com.github.medicalmanagementsystem.server.entity.Employee;
import orthae.com.github.medicalmanagementsystem.server.repository.EmployeeRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class EmployeeHibernateRepository implements EmployeeRepository {
    private EntityManager entityManager;

    @Autowired
    public EmployeeHibernateRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Employee get(int id) {
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        return session.get(Employee.class, id);
    }

    public List<Employee> search(String name, String surname, String username, String email, Boolean active, Boolean enabled) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        List<Predicate> list = new ArrayList<>();
        List<Predicate> having = new ArrayList<>();
        if (name != null)
            list.add(builder.like(employee.get("name"), name));
        if (surname != null)
            list.add(builder.like(employee.get("surname"), surname));
        if (username != null)
            list.add(builder.like(employee.get("username"), username));
        if (email != null)
            list.add(builder.like(employee.get("email"), email));
        if (active != null) {
            if (active) {
                having.add(builder.greaterThan(builder.max(employee.join("sessions").get("sessionExpiry")).as(Date.class), new Date()));
            } else
                having.add(builder.lessThan(builder.max(employee.join("sessions").get("sessionExpiry")).as(Date.class), new Date()));
        }
        if (enabled != null) {
            if (enabled)
                list.add(builder.isTrue(employee.get("enabled")));
            else
                list.add(builder.isFalse(employee.get("enabled")));
        }
        criteria.where(builder.and(list.toArray(new Predicate[0]))).groupBy(employee.get("id")).having(having.toArray(new Predicate[0])).orderBy(builder.asc(employee.get("id")));
        TypedQuery<Employee> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }


    @Override
    public boolean isEmailUnique(int id, String email) {
        Session session = entityManager.unwrap(Session.class);
        Query<Employee> query = session.createQuery("FROM Employee WHERE email = :email AND NOT id = :id", Employee.class);
        query.setParameter("email", email);
        query.setParameter("id", id);
        Employee employee = query.getResultStream().findFirst().orElse(null);
        return employee == null;
    }

    @Override
    public boolean isUsernameUnique(int id, String username) {
        Session session = entityManager.unwrap(Session.class);
        Query<Employee> query = session.createQuery("FROM Employee WHERE username = :username AND NOT id = :id", Employee.class);
        query.setParameter("username", username);
        query.setParameter("id", id);
        Employee employee = query.getResultStream().findFirst().orElse(null);
        return employee == null;
    }

    @Override
    public void save(Employee employee){
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(employee);
    }

    @Override
    public void delete(Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(employee);
    }

    @Override
    public Employee get(String username) {
        Session session = entityManager.unwrap(Session.class);
        Query<Employee> employeeQuery = session.createQuery("FROM Employee WHERE username = :username", Employee.class);
        employeeQuery.setParameter("username", username);
        return employeeQuery.uniqueResult();
    }

    @Override
    public void changePassword(int id, String password) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("UPDATE Employee SET password = :password WHERE id = :id");
        query.setParameter("id", id);
        query.setParameter("password", password);
        query.executeUpdate();
    }

    @Override
    public void activate(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("UPDATE Employee SET enabled  = 1 WHERE id = :id ");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deactivate(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("UPDATE Employee SET enabled = 0 WHERE id = :id ");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
