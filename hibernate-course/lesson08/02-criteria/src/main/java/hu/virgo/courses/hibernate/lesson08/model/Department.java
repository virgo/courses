package hu.virgo.courses.hibernate.lesson08.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@NamedQueries({
		@NamedQuery(name = "findByLocation", query = "select d from Department d where d.location = :location"),
		@NamedQuery(name ="findByEmployeeName", query = "select d from Department d join Employee e where e.name = :employeeName"),
		@NamedQuery(name ="findByEmployeeNameAndLocation", query = "select d from Department d join Employee e where e.name = :employeeName and d.location = :location")
})
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String location;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Department{" +
				"id=" + id +
				", location='" + location + '\'' +
				'}';
	}
}