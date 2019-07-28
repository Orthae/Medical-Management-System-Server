package orthae.com.github.medicalmanagementsystem.server.employee.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import orthae.com.github.medicalmanagementsystem.server.employee.entity.EmployeeDatabaseEntity;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeHibernateDAO {
    private EntityManager entityManager;

    @Autowired
    public EmployeeHibernateDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<EmployeeDatabaseEntity> getEmployee() {
        Session session = entityManager.unwrap(Session.class);
        Query<EmployeeDatabaseEntity> query = session.createQuery("from EmployeeDatabaseEntity", EmployeeDatabaseEntity.class);
        return query.getResultList();
    }

    public List<EmployeeDatabaseEntity> getEmployee(String name, String surname) {
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
        Query<EmployeeDatabaseEntity> query = session.createQuery(hql.toString(), EmployeeDatabaseEntity.class);
        if (name != null)
            query.setParameter("name", name);
        if (surname != null)
            query.setParameter("surname", surname);
        return query.getResultList();
    }

    public EmployeeDatabaseEntity getEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(EmployeeDatabaseEntity.class, id);
    }

    public void saveEmployee(EmployeeDatabaseEntity employee){
    Session session = entityManager.unwrap(Session.class);
    session.saveOrUpdate(employee);
    }

    public void deleteEmployee(int id){
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("DELETE EmployeeDatabaseEntity where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public EmployeeDatabaseEntity getEmployeeByUserName(String username) {
        Session session = entityManager.unwrap(Session.class);
        Query<EmployeeDatabaseEntity> employeeQuery = session.createQuery("FROM EmployeeDatabaseEntity WHERE username = :username", EmployeeDatabaseEntity.class);
        employeeQuery.setParameter("username", username);
        return employeeQuery.uniqueResult();
    }

}
