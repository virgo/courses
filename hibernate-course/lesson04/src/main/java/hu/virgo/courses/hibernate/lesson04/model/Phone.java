package hu.virgo.courses.hibernate.lesson04.model;


import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
public class Phone implements Serializable {
	private static final long serialVersionUID = 1L;

	private PhoneType type;
	private String areaCode = "+36";
	private String number;

	public Phone() {
		number = RandomStringUtils.randomNumeric(7, 10);
	}

	public Phone(PhoneType type) {
		this();
		this.type = type;
	}

	@Column(name = "PHONE_TYPE")
	@Enumerated(EnumType.STRING)
	public PhoneType getType() {
		return type;
	}

	public void setType(PhoneType type) {
		this.type = type;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
