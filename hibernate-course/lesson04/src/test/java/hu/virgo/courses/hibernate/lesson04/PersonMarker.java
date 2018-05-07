package hu.virgo.courses.hibernate.lesson04;

import hu.virgo.courses.hibernate.lesson04.model.Person;

import java.io.Serializable;

public class PersonMarker implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Person person;
	private final String marker;

	public PersonMarker(Person person, String marker) {
		this.person = person;
		this.marker = marker;
	}

	public Person getPerson() {
		return person;
	}

	public String getMarker() {
		return marker;
	}
}
