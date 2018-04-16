package hu.virgo.courses.hibernate.lesson02;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson02.model.Person;
import hu.virgo.courses.hibernate.lesson02.model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testng.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(JpaUnit.class)
public class Person01Test {

	@PersistenceContext(unitName = "course_test")
	private EntityManager em;
	private long id;

	@Test
	public void adummy() {
		Person p = new Person();
		p.setName("Ference Hill");
		em.persist(p);

		Assert.assertNotNull(p.getId());
	}
}
