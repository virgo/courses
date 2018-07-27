package hu.virgo.courses.hibernate.lesson09;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson09.model.InSample;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@ExtendWith(JpaUnit.class)
public class InQueryTest {

	@PersistenceContext(unitName = "tricks")
	private EntityManager em;

	/*
	select * from Person where name = ?;
	 */
	@Test
	public void inClause() {
		System.out.println("valami");
//		getEntities(1, 2, 3);
//		getEntities(1, 2, 3, 4);
//		getEntities(1, 2, 3, 4, 5);
//		getEntities(1, 2, 3, 4, 5, 6);
		List<InSample> valami =
				getEntities(1, null, 3, 4, 5, 6, 7, 8, 9);
		System.out.println("valami");
	}

	private List<InSample> getEntities(Integer... ids) {
		return em.createQuery("select i from InSample i where i.id in :ids", InSample.class)
				.setParameter("ids", Arrays.asList(ids))
				.getResultList();

	}
}
