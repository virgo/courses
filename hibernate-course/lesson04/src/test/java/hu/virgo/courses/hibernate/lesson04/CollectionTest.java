package hu.virgo.courses.hibernate.lesson04;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson04.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static hu.virgo.courses.hibernate.lesson04.DataSupplier.getPeople;

@ExtendWith(JpaUnit.class)
public class CollectionTest {
	@PersistenceContext(unitName = "course_test")
	private EntityManager em;
	private boolean hasRan;

	@BeforeEach
	public void init() {
		if (hasRan) {
			return;
		}
		getPeople.get().forEach(p -> em.persist(p));
		em.flush();
		em.clear();
		hasRan = true;
	}

	/*

	select person0_.ID as ID1_0_,
		person0_.DATE_BIRTH as DATE_BIR2_0_,
		person0_.NAME as NAME3_0_,
		person0_.PHONE_NO as PHONE_NO4_0_

		from PEOPLE person0_
		where
			(select count(tasks1_.PERSON_ID)
				from PEOPLE_TASKS tasks1_
				where person0_.ID=tasks1_.PERSON_ID
			)=0
	 */
	@Test
	public void sizeTest() {
		List<Person> l = em.createQuery("select p from Person p where size(p.tasks) = 0", Person.class).getResultList();
		System.out.println(l);
	}

	@Test
	public void countTest() {
		Long count = em.createQuery("select count(p) from Person p join p.tasks where size(p.tasks)> 0", Long.class).getSingleResult();
		System.out.println(count);
	}
}
