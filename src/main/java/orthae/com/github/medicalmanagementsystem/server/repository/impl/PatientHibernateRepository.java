package orthae.com.github.medicalmanagementsystem.server.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import orthae.com.github.medicalmanagementsystem.server.entity.Patient;
import orthae.com.github.medicalmanagementsystem.server.repository.PatientRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PatientHibernateRepository implements PatientRepository {

    private EntityManager entityManager;

    @Autowired
    public PatientHibernateRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Patient> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query<Patient> query = session.createQuery("FROM Patient", Patient.class);
        return query.getResultList();
    }

    @Override
    public Patient findById(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Patient.class, id);
    }

    @Override
    public void save(Patient patient) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(patient);
    }


}
