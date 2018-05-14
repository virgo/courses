package hu.virgo.courses.hibernate.lesson05;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson05.model.Color;
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
		s.setNetPrice(1000.0F);

		Color c = new Color();
		c.setRed(127);
		c.setGreen(255);
		c.setBlue(90);
		s.setColor(c);

		em.persist(s);
		em.flush();
		em.clear();
		Assertions.assertNotNull(s.getId());
		Assertions.assertEquals(BRAND, s.getBrand());

		Shirt s2 = em.find(Shirt.class, s.getId());
		Assertions.assertEquals(c, s2.getColor());
	}
}
