package hu.virgo.courses.hibernate.lesson05;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson05.model.Color;
import hu.virgo.courses.hibernate.lesson05.model.product.Car;
import hu.virgo.courses.hibernate.lesson05.model.product.Shirt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ExtendWith(JpaUnit.class)
public class InheritanceTest {

	@PersistenceContext(unitName = "course_test")
	private EntityManager em;

	@Test
	public void simplePersistTest() {
		Shirt s = new Shirt();
		s.setName("Póló");
		s.setBrand("Nike");

		Color c0 = new Color();
		c0.setRed(0);
		c0.setGreen(66);
		c0.setBlue(123);

		s.setColor(c0);
		em.persist(s);
		em.flush();

		Car c = new Car();
		c.setName("Autó");
		c.setCapacity(1377.898F);

		em.persist(c);
		em.flush();
		em.clear();
		System.out.println("foo");

		Shirt s0 = em.find(Shirt.class, 1L);
		System.out.println("foo");

	}
}
