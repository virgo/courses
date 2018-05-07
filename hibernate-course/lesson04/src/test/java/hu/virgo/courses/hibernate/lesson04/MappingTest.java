package hu.virgo.courses.hibernate.lesson04;
import eu.drus.jpa.unit.api.JpaUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static hu.virgo.courses.hibernate.lesson04.DataSupplier.getPeople;

@ExtendWith(JpaUnit.class)
public class MappingTest {
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

	@Test
	public void caseTest() {
		/*
		select * from A where
		CASE
			WHEN A.x = 'valami' THEN 'masvalami'
			WHEN A.x = 'foo' THEN 'bar'
		END

		select case
			when person0_.NAME='Dub Spancer' then 'Valami'
			when person0_.NAME='Ference Hill' then 'masvalami'
			else 'Nem ismert' end as col_0_0_
		from PEOPLE person0_
		 */
		List<PersonCollector> strings = em.createQuery("select " +
				"new hu.virgo.courses.hibernate.lesson04.PersonCollector(p, case " +
				"when p.name = 'Dub Spancer' then 'Valami'" +
				"when p.name = 'Ference Hill' then 'masvalami'" +
				"else 'Nem ismert' end, p.id) from Person p", PersonCollector.class).getResultList();
		System.out.println(strings);
	}

	@Test
	public void newMappingTest() {
/*
select person0_.ID as col_0_0_, count(tasks1_.PERSON_ID) as col_1_0_
from PEOPLE person0_, PEOPLE_TASKS tasks1_
where person0_.ID=tasks1_.PERSON_ID
group by person0_.ID
*/

		List<PersonCounter> o = em.createQuery(
"select NEW hu.virgo.courses.hibernate.lesson04.PersonCounter(p, size(p.tasks)) from Person p group by p", PersonCounter.class).getResultList();
		System.out.println(o);
	}

	@Test
	public void coalesceTest() {
		/*
		Bogyóka, Mária, Nagy => Bogyóka
		----, József, Kiss => József
		----, József, ----- => József
		----, ----, Kiss => Kiss
		----, ----, ----, => null
		select coalesce(a.nickname, a.firstname, a.lastname) from person
		 */
		List<Object> o = em.createQuery("select p, coalesce(p.phoneNumber, 'N/A') from Person p")
				.getResultList();
		System.out.println(o);
	}

	@Test
	public void concatTest() {
		/*
		select
			person0_.NAME as col_0_0_,
			person0_.PHONE_NO as col_1_0_,
			(person0_.NAME||person0_.PHONE_NO) as col_2_0_
		from PEOPLE person0_
		 */
		List<Object> o = em.createQuery("select p.name, concat(p.phoneNumber,p.name) from Person p")
		.getResultList();
		System.out.println(o);
	}

	@Test
	public void nullIfTest() {
		/*
		 select
		 	person0_.ID as col_0_0_,
		 	nullif(person0_.NAME, ?) as col_1_0_,
		 	person0_.ID as ID1_0_,
		 	person0_.DATE_BIRTH as DATE_BIR2_0_, person0_.NAME as NAME3_0_, person0_.PHONE_NO as PHONE_NO4_0_
		 	from PEOPLE person0_
		 */
		List<Object> o = em.createQuery("select p, nullif(p.name, :excludedName) from Person p")
			.setParameter("excludedName", "Ference Hill")
			.getResultList();
		System.out.println(o);

	}
}

/*

coalesce:

case
	when value1 is not null then value1
	when value2 is not null then value2
	when value2 is not null then value2
	else null
end

nullif:

case
	when value1 = value2 then null
	else value1
end
 */
