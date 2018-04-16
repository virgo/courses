package hu.virgo.courses.hibernate.lesson02.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Table;
import java.io.Serializable;

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

	@Column(name = "ADDRESS")
	private String address;


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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
