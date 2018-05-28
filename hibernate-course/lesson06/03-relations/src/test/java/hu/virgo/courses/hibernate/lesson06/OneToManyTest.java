package hu.virgo.courses.hibernate.lesson06;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson06.model.Address;
import hu.virgo.courses.hibernate.lesson06.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ExtendWith(JpaUnit.class)
public class OneToManyTest {
	@PersistenceContext(unitName = "course_test")
	private EntityManager em;
	private boolean hasRan;

	private Address main = new Address();
	private Address rural = new Address();
	private Address foreign = new Address();

	@BeforeEach
	public void init() {
		if (hasRan) {
			return;
		}
		main.setLocation("Budapest");

		rural.setLocation("Szeged");

		foreign.setLocation("London");

		{
			Person emp = new Person();
			emp.setName("Ference Hill");
//			emp.setAddress(main);
			em.persist(emp);
			main.addPerson(emp);
		}
		{
			Person emp = new Person();
			emp.setName("Dub Spancer");
//			emp.setAddress(main);
			em.persist(emp);
			main.addPerson(emp);
		}
		{
			Person emp = new Person();
			emp.setName("Dick Tracy");
//			emp.setAddress(rural);
			em.persist(emp);
			rural.addPerson(emp);
		}

		em.persist(main);
		em.persist(rural);
		em.persist(foreign);
		em.flush();
		em.clear();
		hasRan = true;
	}

	@Test
	public void selectAddressTest() {
		Address m = em.createQuery("select a from Address a where a.location = :location", Address.class)
				.setParameter("location", "Budapest")
				.getSingleResult();
		System.out.println("valami");
		System.out.println(m.getPeople().size());
	}

}
