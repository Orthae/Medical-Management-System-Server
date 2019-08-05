package orthae.com.github.medicalmanagementsystem.server.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import orthae.com.github.medicalmanagementsystem.server.entity.Visit;
import orthae.com.github.medicalmanagementsystem.server.repository.VisitRepository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class VisitHibernateRepository implements VisitRepository {

    private EntityManager entityManager;

    @Autowired
    public VisitHibernateRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Visit findById(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Visit.class, id);
    }

    @Override
    public List<Visit> findByPatientId(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query<Visit> query = session.createQuery("FROM Visit WHERE patient_id = :id", Visit.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public void save(Visit visit) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(visit);
    }

}
