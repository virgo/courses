package hu.virgo.courses.hibernate.lesson01;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson01.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testng.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ExtendWith(JpaUnit.class)
public class Person01Test {

	@PersistenceContext(unitName = "course_test")
	private EntityManager em;

	@Test
	public void dummy() {
		Person p = new Person();
		p.setName("Ference Hill");
		em.persist(p);
		Assert.assertNotNull(p.getId());
	}
}
