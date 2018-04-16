package hu.virgo.courses.hibernate.lesson02;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson02.model.Address;
import hu.virgo.courses.hibernate.lesson02.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ExtendWith(JpaUnit.class)
public class Person01Test {

	@PersistenceContext(unitName = "course_test")
	private EntityManager em;

	@Test
	public void adummy() {
		Person p = new Person();
		p.setName("Ference Hill");
		em.persist(p);
		Assertions.assertNotNull(p.getId());
	}
	@Test
	public void withAddress() {
		Person p = new Person();
		p.setName("Ference Hill");

		Address mainAddress = new Address();
		mainAddress.setCity("Budapest");
		mainAddress.setStreetName("Hengermalom");

		p.setMainAddress(mainAddress);

		em.persist(p);
		List<Person> people = em.createQuery("from Person p where p.postalAddress.city = :city", Person.class)
				.setParameter("city", "Budapest")
				.getResultList();
		Assertions.assertNotNull(p.getId());
	}

}
