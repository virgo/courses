package hu.virgo.courses.hibernate.lesson06;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson06.model.Address;
import hu.virgo.courses.hibernate.lesson06.model.Employee;
import hu.virgo.courses.hibernate.lesson06.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@ExtendWith(JpaUnit.class)
public class RelationsQueryTest {
	@PersistenceContext(unitName = "course_test")
	private EntityManager em;
	private boolean hasRan;

	private Address main = new Address();
	private Address rural = new Address();
	private Address foreign = new Address();

//	@BeforeEach
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
	public void emptyTest() {
		Address m = em.createQuery("select a from Address a where a.people is empty", Address.class)
				.getSingleResult();

	}

	@Test
	public void inTest() {
		List<Address> addresses = em.createQuery("select a from Address a where a.location in (:locations)", Address.class)
				.setParameter("locations", Arrays.asList("Budapest", "Szeged", "Pannonhalma"))
				.getResultList();
		System.out.println(addresses.size());
	}

	@Test
	public void memberOfTest() {
		Person p = em.find(Person.class, 1L);

		List<Address> a = em.createQuery("select a from Address a where :person member of a.people", Address.class)
				.setParameter("person", p)
				.getResultList();
		System.out.println(a.size());
	}

	@Test
	public void softDelete() {
		List<Address> addresses = em.createQuery("select a from Address a", Address.class).getResultList();
		em.remove(addresses.get(0));
		em.flush();
		em.clear();
		List<Address> addresses2 = em.createQuery("select a from Address a", Address.class).getResultList();
	}

	@Test
	public void dynamicUpdate() {
		Employee e = new Employee();
		e.setName("Valaki");
		e.setSalary(1500);
		em.persist(e);
		em.flush();
		e.setName("Mikkamakka");
		em.merge(e);
		em.flush();
	}
/*
Hibernate: call next value for hibernate_sequence
Hibernate: insert into EMPLOJI (name, salary, id) values (?, ?, ?)
Hibernate: update EMPLOJI set name=?, salary=? where id=?


Hibernate: call next value for hibernate_sequence
Hibernate: insert into EMPLOJI (name, salary, id) values (?, ?, ?)
Hibernate: update EMPLOJI set name=? where id=?
*/

}
