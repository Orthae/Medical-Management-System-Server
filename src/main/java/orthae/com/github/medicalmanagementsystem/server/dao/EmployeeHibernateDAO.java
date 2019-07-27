package orthae.com.github.medicalmanagementsystem.server.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import orthae.com.github.medicalmanagementsystem.core.Employee;
import orthae.com.github.medicalmanagementsystem.core.EmployeeDAO;
import orthae.com.github.medicalmanagementsystem.server.entity.EmployeeDatabaseEntity;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeHibernateDAO implements EmployeeDAO {
    private EntityManager entityManager;

    @Autowired
    public EmployeeHibernateDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> getEmployee() {
        Session session = entityManager.unwrap(Session.class);
        Query<Employee> query = session.createQuery("from EmployeeDatabaseEntity", Employee.class);
        return query.getResultList();
    }

    @Override
    public List<Employee> getEmployee(String name, String surname) {
//  Building a HQL query
        StringBuilder hql = new StringBuilder(50);
        hql.append("FROM EmployeeDatabaseEntity WHERE ");
        if (name != null) {
            hql.append("name = :name");
            if (surname != null)
                hql.append(" AND surname = :surname");
        } else {
            hql.append("surname = :surname");
        }
//  Creating query and executing
        Session session = entityManager.unwrap(Session.class);
        Query<Employee> query = session.createQuery(hql.toString(), Employee.class);
        if (name != null)
            query.setParameter("name", name);
        if (surname != null)
            query.setParameter("surname", surname);
        return query.getResultList();
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(EmployeeDatabaseEntity.class, id);
    }

    @Override
    public void saveEmployee(EmployeeDatabaseEntity employee){
    Session session = entityManager.unwrap(Session.class);
    session.saveOrUpdate(employee);
    }

}
