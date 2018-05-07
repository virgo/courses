package hu.virgo.courses.hibernate.lesson04;

import hu.virgo.courses.hibernate.lesson04.model.Person;

import java.io.Serializable;

public class PersonCounter implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Person person;
	private final int counter;

	public PersonCounter(Person person, Integer counter) {
		this.person = person;
		this.counter = counter;
	}

	public Person getPerson() {
		return person;
	}

	public int getCounter() {
		return counter;
	}
}
