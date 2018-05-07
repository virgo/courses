package hu.virgo.courses.hibernate.lesson04;

import hu.virgo.courses.hibernate.lesson04.model.Person;

public class PersonCollector {

	private final Person person;
	private final String nickname;
	private final Long personId;

	public PersonCollector(Person person, String nickname, Long personId) {
		this.person = person;
		this.nickname = nickname;
		this.personId = personId;
	}

	public Person getPerson() {
		return person;
	}

	public String getNickname() {
		return nickname;
	}

	public Long getPersonId() {
		return personId;
	}
}
