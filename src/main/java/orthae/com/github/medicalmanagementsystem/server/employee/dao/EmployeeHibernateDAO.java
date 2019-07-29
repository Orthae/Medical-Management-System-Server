package orthae.com.github.medicalmanagementsystem.server.employee.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import orthae.com.github.medicalmanagementsystem.server.employee.entity.EmployeeEntity;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeHibernateDAO {
    private EntityManager entityManager;

    @Autowired
    public EmployeeHibernateDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<EmployeeEntity> getEmployee() {
        Session session = entityManager.unwrap(Session.class);
        Query<EmployeeEntity> query = session.createQuery("from EmployeeEntity", EmployeeEntity.class);
        return query.getResultList();
    }

    public List<EmployeeEntity> getEmployee(String name, String surname) {
//  Building a HQL query
        StringBuilder hql = new StringBuilder(50);
        hql.append("FROM EmployeeEntity WHERE ");
        if (name != null) {
            hql.append("name = :name");
            if (surname != null)
                hql.append(" AND surname = :surname");
        } else {
            hql.append("surname = :surname");
        }
//  Creating query and executing
        Session session = entityManager.unwrap(Session.class);
        Query<EmployeeEntity> query = session.createQuery(hql.toString(), EmployeeEntity.class);
        if (name != null)
            query.setParameter("name", name);
        if (surname != null)
            query.setParameter("surname", surname);
        return query.getResultList();
    }

    public EmployeeEntity getEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(EmployeeEntity.class, id);
    }

    public void saveEmployee(EmployeeEntity employee){
    Session session = entityManager.unwrap(Session.class);
    session.saveOrUpdate(employee);
    }

    public void deleteEmployee(int id){
        Session session = entityManager.unwrap(Session.class);
        EmployeeEntity entity = session.get(EmployeeEntity.class, id);
        session.delete(entity);
    }

    public EmployeeEntity getEmployeeByUserName(String username) {
        Session session = entityManager.unwrap(Session.class);
        Query<EmployeeEntity> employeeQuery = session.createQuery("FROM EmployeeEntity WHERE username = :username", EmployeeEntity.class);
        employeeQuery.setParameter("username", username);
        return employeeQuery.uniqueResult();
    }

}
