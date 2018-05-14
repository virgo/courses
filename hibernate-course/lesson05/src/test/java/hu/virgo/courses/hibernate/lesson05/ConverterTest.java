package hu.virgo.courses.hibernate.lesson05;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson05.model.Shirt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ExtendWith(JpaUnit.class)
public class ConverterTest {
	private static final String BRAND = "Fruit of the Loom";

	@PersistenceContext(unitName = "course_test")
	private EntityManager em;


	@Test
	public void simplePersistTest() {
		Shirt s = new Shirt();
		s.setBrand("Fruit of the Loom");
		em.persist(s);
		Assertions.assertNotNull(s.getId());
		Assertions.assertEquals(BRAND, s.getBrand());
	}
}
