package hu.virgo.courses.hibernate.lesson04.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "PEOPLE")
@SequenceGenerator(name = "person_seq", sequenceName = "person_sequence", initialValue = 10)
@NamedQueries({
		@NamedQuery(name = "findAll", query = "select p from Person p"),
		@NamedQuery(name = "rudeBoys", query = "select distinct p from Person p JOIN p.tasks pt where pt.dueDate < :due")
})
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, updatable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "PHONE_NO", length = 50)
	private String phoneNumber;

	@Column(name = "DATE_BIRTH")
	private LocalDate birthDate = LocalDate.of(1977, 12, 31);

	@ElementCollection
	@CollectionTable(name = "PEOPLE_TASKS",
			joinColumns = {@JoinColumn(name = "PERSON_ID")})
	private List<Task> tasks;


	@ElementCollection
	@CollectionTable(name = "PEOPLE_PHONES", joinColumns = @JoinColumn(name = "PERSON_ID"))
	@MapKeyColumn(name = "PHONE_TYPE")
	@MapKeyEnumerated(EnumType.STRING)
	private Map<PhoneType, Phone> phones = new HashMap<>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Map<PhoneType, Phone> getPhones() {
		return phones;
	}

	public void setPhones(Map<PhoneType, Phone> phones) {
		this.phones = phones;
	}

	public void addPhone(Phone phone) {
		getPhones().put(phone.getType(), phone);
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				'}';
	}
}
