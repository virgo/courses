package hu.virgo.courses.hibernate.lesson03;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson03.model.Person;
import hu.virgo.courses.hibernate.lesson03.model.Phone;
import hu.virgo.courses.hibernate.lesson03.model.PhoneType;
import hu.virgo.courses.hibernate.lesson03.model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(JpaUnit.class)
public class Person01Test {

	@PersistenceContext(unitName = "course_test")
	private EntityManager em;
	private static Supplier<List<Task>> getTasks = () -> {
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
		return tasks;
	};
	private static Supplier<List<Phone>> preparePhones = () -> Stream.of(PhoneType.values()[new Random().nextInt(PhoneType.values().length)])
			.map(Phone::new)
			.collect(Collectors.toList());

	@BeforeEach
	public void init() {

		{
			Person p = new Person();
			p.setName("Ference Hill");
			p.setTasks(getTasks.get());
			preparePhones.get().forEach(p::addPhone);
			em.persist(p);
		}
		{
			Person p = new Person();
			p.setName("Dub Spancer");
			p.setTasks(getTasks.get().stream().filter(t -> t.getDueDate().isAfter(LocalDateTime.now())).collect(Collectors.toList()));
			preparePhones.get().forEach(p::addPhone);
			em.persist(p);
		}
		em.flush();
	}

	@Test
	public void dummy() {
		List<Person> people = em.createQuery("<<<< HERE WE GO >>>>>", Person.class)
				.getResultList();

		Assertions.assertEquals(1, people.size());


	}
}
