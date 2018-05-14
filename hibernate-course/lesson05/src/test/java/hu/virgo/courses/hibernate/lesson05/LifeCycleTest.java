package hu.virgo.courses.hibernate.lesson05;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson05.model.Shirt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ExtendWith(JpaUnit.class)
public class LifeCycleTest {

	@PersistenceContext(unitName = "course_test")
	private EntityManager em;


	@Test
	public void simplePersistTest() {
		Shirt s = new Shirt();
		s.setNetPrice(1000.0F);
		em.persist(s);
		Assertions.assertNotNull(s.getId());
		Assertions.assertEquals("NO-NAME", s.getBrand());
	}

	@Test
	public void auditTest() {
		Shirt s = new Shirt();
		s.setNetPrice(1000.0F);
		em.persist(s);
		Assertions.assertNotNull(s.getId());
		Assertions.assertEquals("NO-NAME", s.getBrand());
		Assertions.assertNotNull(s.getCreatedAt());
		Assertions.assertNull(s.getUpdatedAt());

		em.flush();
		s.setBrand("FOO");
		em.merge(s);
		em.flush();
		Assertions.assertNotNull(s.getUpdatedAt());
	}

}
