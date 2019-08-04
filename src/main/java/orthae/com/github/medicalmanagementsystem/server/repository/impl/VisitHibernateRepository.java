package orthae.com.github.medicalmanagementsystem.server.repository.impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import orthae.com.github.medicalmanagementsystem.server.entity.Visit;
import orthae.com.github.medicalmanagementsystem.server.repository.VisitRepository;

import javax.persistence.EntityManager;


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
    public void save(Visit visit) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(visit);
    }

}
