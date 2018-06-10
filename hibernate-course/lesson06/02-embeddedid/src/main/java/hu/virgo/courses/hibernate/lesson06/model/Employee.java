package hu.virgo.courses.hibernate.lesson06.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Employee implements Serializable {

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "number", column = @Column(name = "EMPLOYEE_NUM"))
	})
	private EmployeeId id;

	private String name;
	private long salary;

	public Employee() {
	}

	public Employee(EmployeeId id) {
		this.id = id;
	}

	public Employee(String countryCode, int employeeNumber) {
		this.id = new EmployeeId(countryCode, employeeNumber);
	}

	public EmployeeId getId() {
		return id;
	}

		public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}
}
