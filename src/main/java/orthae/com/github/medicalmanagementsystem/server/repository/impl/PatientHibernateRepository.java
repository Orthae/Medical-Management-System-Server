package orthae.com.github.medicalmanagementsystem.server.repository.impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import orthae.com.github.medicalmanagementsystem.server.entity.Patient;
import orthae.com.github.medicalmanagementsystem.server.repository.PatientRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientHibernateRepository implements PatientRepository {

    private EntityManager entityManager;

    @Autowired
    public PatientHibernateRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Patient> search(String name, String surname, String birthdate, String email, String socialSecurity) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Patient> criteria = builder.createQuery(Patient.class);
        Root<Patient> patient = criteria.from(Patient.class);
        List<Predicate> list = new ArrayList<>();
        if (name != null)
            list.add(builder.like(patient.get("name"), name));
        if (surname != null)
            list.add(builder.like(patient.get("surname"), surname));
        if (birthdate != null)
            list.add(builder.like(patient.get("birthdate").as(String.class), birthdate));
        if (email != null)
            list.add(builder.like(patient.get("email"), email));
        if(socialSecurity != null)
            list.add(builder.like(patient.get("socialSecurity"), socialSecurity));

        criteria.where(builder.and(list.toArray(new Predicate[0])));
        TypedQuery<Patient> query = entityManager.createQuery(criteria);
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
