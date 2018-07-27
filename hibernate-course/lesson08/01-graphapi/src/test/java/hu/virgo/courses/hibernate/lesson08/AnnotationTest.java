package hu.virgo.courses.hibernate.lesson08;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson08.model.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ExtendWith(JpaUnit.class)
public class AnnotationTest {

	@PersistenceContext(name = "course_test")
	private EntityManager em;

	@Test
	public void immutableTest() {
		Department d = new Department();
		d.setLocation("Akarattya");
		em.persist(d);
		em.flush();
	}
}
