package hu.virgo.courses.hibernate.lesson04;

import eu.drus.jpa.unit.api.JpaUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ExtendWith(JpaUnit.class)
public class FunctionTest {
	@PersistenceContext(unitName = "course_test2")
	private EntityManager em;


	@Test
	public void callFunction() {
		String r = em.createQuery("SELECT function('repeat', 'Hello bello ||', 4) from Dual ", String.class).getSingleResult();
		Integer r2 = em.createQuery("SELECT function('mod', 10, 3) from Dual ", Integer.class).getSingleResult();
		String r3 = em.createQuery("SELECT substring('asdf', 1, 3) from Dual ", String.class).getSingleResult();
		String r4 = em.createQuery("SELECT upper(substring('asdf', 1, 3)) from Dual ", String.class).getSingleResult();
		String r5 = em.createQuery("SELECT substring(upper('asdf'), 1, 3) from Dual ", String.class).getSingleResult();
		Integer r6 = em.createQuery("SELECT 10*2 from Dual ", Integer.class).getSingleResult();
		System.out.println(r6);
	}
}
