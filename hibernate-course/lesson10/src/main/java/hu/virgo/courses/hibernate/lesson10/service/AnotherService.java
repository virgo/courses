package hu.virgo.courses.hibernate.lesson10.service;

import hu.virgo.courses.hibernate.lesson10.model.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Stateless
public class AnotherService {

    @PersistenceContext(unitName = "course")
    EntityManager em;

    @Transactional
    public void anotsherMethod() {
        em.find(Employee.class, 1);
    }
}
