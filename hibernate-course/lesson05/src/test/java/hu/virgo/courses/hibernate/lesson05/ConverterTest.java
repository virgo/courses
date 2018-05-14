package hu.virgo.courses.hibernate.lesson05;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson05.model.Color;
import hu.virgo.courses.hibernate.lesson05.model.ColorAuto;
import hu.virgo.courses.hibernate.lesson05.model.Shirt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(JpaUnit.class)
public class ConverterTest {
	private static final String BRAND = "Fruit of the Loom";

	@PersistenceContext(unitName = "course_test")
	private EntityManager em;


	@Test
	public void colorsPersistTest() {
		Shirt s = new Shirt();
		s.setBrand("Fruit of the Loom");
		s.setNetPrice(1000.0F);

		List<Color> colors = new ArrayList<>();

		Color c = new Color();
		c.setRed(127);
		c.setGreen(255);
		c.setBlue(90);

		Color c0 = new Color();
		c0.setRed(0);
		c0.setGreen(66);
		c0.setBlue(123);
		colors.add(c);
		colors.add(c0);
		s.setColors(colors);

		em.persist(s);

		Assertions.assertNotNull(s.getId());
		Assertions.assertEquals(BRAND, s.getBrand());
	}


	@Test
	public void colorPersistAutoConvertTest() {
		Shirt s = new Shirt();
		s.setBrand("Fruit of the Loom");
		s.setNetPrice(1000.0F);
		ColorAuto c = new ColorAuto();
		c.setRed(127);
		c.setGreen(255);
		c.setBlue(90);
		s.setColor(c);
		em.persist(s);
		Assertions.assertNotNull(s.getId());
		Assertions.assertEquals(BRAND, s.getBrand());

		em.flush();
	}


}