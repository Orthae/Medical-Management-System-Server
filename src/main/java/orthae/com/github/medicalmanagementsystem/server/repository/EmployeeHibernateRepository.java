package orthae.com.github.medicalmanagementsystem.server.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import orthae.com.github.medicalmanagementsystem.server.entity.Employee;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeHibernateRepository implements EmployeeRepository {
    private EntityManager entityManager;

    @Autowired
    public EmployeeHibernateRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAllEmployees() {
        Session session = entityManager.unwrap(Session.class);
        Query<Employee> query = session.createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee findEmployeeById(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Employee.class, id);
    }

    @Override
    public List<Employee> findEmployeesByName(String name) {
        Session session = entityManager.unwrap(Session.class);
        Query<Employee> query = session.createQuery("FROM Employee WHERE name =: name", Employee.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Employee> findEmployeesBySurname(String surname) {
        Session session = entityManager.unwrap(Session.class);
        Query<Employee> query = session.createQuery("FROM Employee WHERE surname =: surname", Employee.class);
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    @Override
    public List<Employee> findEmployeesByNameAndSurname(String name, String surname) {
        Session session = entityManager.unwrap(Session.class);
        Query<Employee> query = session.createQuery("FROM Employee WHERE name =: name AND surname =: surname", Employee.class);
        return query.getResultList();
    }

    @Override
    public void saveEmployee(Employee employee){
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(employee);
    }

    @Override
    public Employee findEmployeeByUsername(String username) {
        Session session = entityManager.unwrap(Session.class);
        Query<Employee> employeeQuery = session.createQuery("FROM Employee WHERE username = :username", Employee.class);
        employeeQuery.setParameter("username", username);
        return employeeQuery.uniqueResult();
    }

}
