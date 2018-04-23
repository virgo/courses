package hu.virgo.courses.hibernate.lesson03;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson03.model.Address;
import hu.virgo.courses.hibernate.lesson03.model.Person;
import hu.virgo.courses.hibernate.lesson03.model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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

	//	@Test
	public void adummy() {
		Person p = new Person();
		p.setName("Ference Hill");
		em.persist(p);
		Assertions.assertNotNull(p.getId());
	}

	//	@Test
	public void withAddress() {
		Person p = new Person();
		p.setName("Ference Hill");

		Address mainAddress = new Address();
		mainAddress.setCity("Budapest");
		mainAddress.setStreetName("Hengermalom");

//		p.setMainAddress(mainAddress);

		em.persist(p);
		List<Person> people = em.createQuery("from Person p where p.postalAddress.city = :city", Person.class)
				.setParameter("city", "Budapest")
				.getResultList();
		Assertions.assertNotNull(p.getId());
	}

	@Test
	public void taskTest() {
		Person p = new Person();
		p.setName("Ference Hill");

		List<Task> tasks = new ArrayList<>();
		{
			Task t = new Task();
			t.setTitle("Mosogatás");
			t.setDueDate(LocalDateTime.now());
			tasks.add(t);
		}
		{
			Task t = new Task();
			t.setTitle("Függöny mosás");
			t.setDueDate(LocalDateTime.now().plus(10, ChronoUnit.DAYS));
			tasks.add(t);
		}
		{
			Task t = new Task();
			t.setTitle("Porszívózás");
			t.setDueDate(LocalDateTime.now().plus(1, ChronoUnit.HOURS));
			tasks.add(t);
		}
		{
			Task t = new Task();
			t.setTitle("Fűnyírás");
			t.setDueDate(LocalDateTime.now().minus(10, ChronoUnit.HOURS));
			tasks.add(t);
		}
		p.setTasks(tasks);
		em.persist(p);
		Assertions.assertNotNull(p.getId());
	}

	@Test
	public void ztaskTest02() {
		Person p = em.find(Person.class, 10L);
		System.out.println("valami"+ p.getTasks());
	}
}
