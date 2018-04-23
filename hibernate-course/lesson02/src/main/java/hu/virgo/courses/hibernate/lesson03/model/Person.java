package hu.virgo.courses.hibernate.lesson03.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "PEOPLE")
@SequenceGenerator(name = "person_seq", sequenceName = "person_sequence", initialValue = 10)
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

	@Embedded
	private Address mainAddress;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "country", column = @Column(name = "POSTAL_COUNTRY", length = 200)),
			@AttributeOverride(name = "zipcode", column = @Column(name = "POSTAL_ZIPCODE", length = 10)),
			@AttributeOverride(name = "city", column = @Column(name = "POSTAL_CITY", length = 255)),
			@AttributeOverride(name = "streetName", column = @Column(name = "POSTAL_STREET_NAME", length = 255)),
			@AttributeOverride(name = "streetType", column = @Column(name = "POSTAL_STREET_TYPE", length = 255)),
			@AttributeOverride(name = "houseNumber", column = @Column(name = "POSTAL_HOUSE_NUMBER", length = 15)),
			@AttributeOverride(name = "building", column = @Column(name = "POSTAL_BUILDING", length = 10)),
			@AttributeOverride(name = "staircase", column = @Column(name = "POSTAL_STAIRCASE", length = 10)),
			@AttributeOverride(name = "floor", column = @Column(name = "POSTAL_FLOOR", length = 10)),
			@AttributeOverride(name = "door", column = @Column(name = "POSTAL_DOOR", length = 10)),
			@AttributeOverride(name = "parcelNumber", column = @Column(name = "POSTAL_PARCEL_NUMBER", length = 20))
	})
	private Address postalAddress;

	@ElementCollection
	@CollectionTable(name = "PEOPLE_TASKS",
			joinColumns = {@JoinColumn(name = "PERSON_ID")})
	@OrderBy("dueDate DESC, title ASC")
	private List<Task> tasks;

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

	public Address getMainAddress() {
		return mainAddress;
	}

	public void setMainAddress(Address mainAddress) {
		this.mainAddress = mainAddress;
	}

	public Address getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(Address postalAddress) {
		this.postalAddress = postalAddress;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
