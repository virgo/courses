package hu.virgo.courses.hibernate.lesson09;

import eu.drus.jpa.unit.api.JpaUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ExtendWith(JpaUnit.class)
public class EmbeddedTest {

	@PersistenceContext(unitName = "tricks")
	private EntityManager em;

	@Test
	public void firstTest() {
		System.out.println("valami");
	}
}
