package orthae.com.github.medicalmanagementsystem.server.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.core.Employee;
import orthae.com.github.medicalmanagementsystem.core.EmployeeDAO;
import orthae.com.github.medicalmanagementsystem.server.entity.EmployeeDatabase;

import javax.persistence.EntityManager;

import java.util.List;

@Repository
public class EmployeeHibernateDAO implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeHibernateDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
   public List<Employee> getEmployee(){

        Session session = entityManager.unwrap(Session.class);

        Query<Employee> query = session.createQuery("from EmployeeDatabase", Employee.class);

    return query.getResultList();
   }
}
