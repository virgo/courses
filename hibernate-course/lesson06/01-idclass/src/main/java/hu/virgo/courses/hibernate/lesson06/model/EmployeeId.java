package hu.virgo.courses.hibernate.lesson06.model;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String countryCode;

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

	public int getNumber() {
		return number;
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
