package hu.virgo.courses.hibernate.lesson04;

import hu.virgo.courses.hibernate.lesson04.model.Person;
import hu.virgo.courses.hibernate.lesson04.model.Phone;
import hu.virgo.courses.hibernate.lesson04.model.PhoneType;
import hu.virgo.courses.hibernate.lesson04.model.Task;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataSupplier {
	private static final Phone otherPhone = new Phone(PhoneType.OTHER);

	static Supplier<List<Phone>> preparePhones = () -> Stream.of(PhoneType.values()[new Random().nextInt(PhoneType.values().length)])
			.map(Phone::new)
			.collect(Collectors.toList());

	static Supplier<List<Task>> getTasks = () -> {
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

	static Supplier<List<Person>> getPeople = () -> {
		List<Person> people = new ArrayList<>();
		{
			Person p = new Person();
			p.setName("Ference Hill");
			p.setTasks(getTasks.get());
			preparePhones.get().forEach(p::addPhone);
			people.add(p);
		}

		{
			Person p = new Person();
			p.setName("Dub Spancer");
			p.setTasks(getTasks.get().stream().filter(t -> t.getDueDate().isAfter(LocalDateTime.now())).collect(Collectors.toList()));
			preparePhones.get().forEach(p::addPhone);
			p.addPhone(otherPhone);
			people.add(p);
		}

		{
			Person p = new Person();
			p.setName("James Bond");
			p.setTasks(getTasks.get().stream().filter(t -> t.getDueDate().isAfter(LocalDateTime.now())).collect(Collectors.toList()));
			preparePhones.get().forEach(p::addPhone);
			p.addPhone(otherPhone);
			people.add(p);
		}

		{
			Person p = new Person();
			p.setName("Edward Norton");
			p.setPhoneNumber("+36 20/212-5956");
			people.add(p);
		}
		return people;
	};
}
