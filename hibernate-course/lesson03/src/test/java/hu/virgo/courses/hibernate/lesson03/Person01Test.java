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
	private boolean hasRan;
	private static final Phone otherPhone = new Phone(PhoneType.OTHER);

	@BeforeEach
	public void init() {
		if (hasRan) {
			return;
		}
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
		{
			Person p = new Person();
			p.setName("James Bond");
			p.setTasks(getTasks.get().stream().filter(t -> t.getDueDate().isAfter(LocalDateTime.now())).collect(Collectors.toList()));
			preparePhones.get().forEach(p::addPhone);
			p.addPhone(otherPhone);
			em.persist(p);
		}
		em.flush();
		em.clear();
		hasRan = true;
	}

	@Test
	public void firstQueryTest() {
		/*
Tábláink: PEOPLE, PEOPLE_TASKS, TASKS
Hibernate: create table PEOPLE
	(ID bigint not null,
	DATE_BIRTH binary(255),
	NAME varchar(255) not null,
	PHONE_NO varchar(50), primary key (ID))
Hibernate: create table PEOPLE_PHONES (
	PERSON_ID bigint not null,
	areaCode varchar(255),
	number varchar(255),
	type integer,
	PHONE_TYPE varchar(255) not null,
	primary key (PERSON_ID, PHONE_TYPE))
Hibernate: create table PEOPLE_TASKS
	(PERSON_ID bigint not null,
	description varchar(255),
	DUE_DATE binary(255),
	title varchar(255))

SELECT distinct p.id from PEOPLE p INNER JOIN PEOPLE_TASKS pt ON p.ID = pt.PERSON_ID
	WHERE pt.DUE_DATE < now()

select p* from PEOPLE p inner join PEOPLE_TASKS pt on p.ID=pt.PERSON_ID
	where pt.DUE_DATE<?

select distinct person0_.ID as ID1_0_, p.* from PEOPLE person0_ inner join PEOPLE_TASKS tasks1_ on person0_.ID=tasks1_.PERSON_ID where tasks1_.DUE_DATE<?
		 */
		List<Person> people = em.createQuery("select distinct p from Person p JOIN p.tasks pt where pt.dueDate < :dueBarmi", Person.class)
				.setParameter("dueBarmi", LocalDateTime.now())
				.getResultList();

		Assertions.assertEquals(1, people.size());
	}
}
