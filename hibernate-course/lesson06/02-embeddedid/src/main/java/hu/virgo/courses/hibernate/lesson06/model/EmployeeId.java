package hu.virgo.courses.hibernate.lesson06.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeeId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String countryCode;

	@Column(name = "EMP_NUMBER")
	private int number;

	public EmployeeId() {
	}

	public EmployeeId(String countryCode, int number) {
		this.countryCode = countryCode;
		this.number = number;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof EmployeeId)) return false;
		EmployeeId that = (EmployeeId) o;
		return number == that.number &&
				Objects.equals(countryCode, that.countryCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(countryCode, number);
	}
}
