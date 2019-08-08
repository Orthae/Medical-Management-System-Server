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
import java.util.List;

@Repository
public class EmployeeHibernateRepository implements EmployeeRepository {
    private EntityManager entityManager;

    @Autowired
    public EmployeeHibernateRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Employee find(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Employee.class, id);
    }

    @Override
    public List<Employee> find(){
        return find(null, null);
    }

    public List<Employee> find(String name, String surname){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);
        List<Predicate> list = new ArrayList<>();
        if(name != null)
            list.add(builder.like(root.get("name"), name));
        if(surname != null)
            list.add(builder.like(root.get("surname"), surname));
        query.where(builder.and(list.toArray(new Predicate[0])));
        TypedQuery<Employee> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
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
    public Employee find(String username) {
        Session session = entityManager.unwrap(Session.class);
        Query<Employee> employeeQuery = session.createQuery("FROM Employee WHERE username = :username", Employee.class);
        employeeQuery.setParameter("username", username);
        return employeeQuery.uniqueResult();
    }

}
