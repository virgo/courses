/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.virgo.courses.hibernate.lesson03.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static String HUNGARY = "Magyarország";

	@Size(max = 200)
	@Column(name="COUNTRY", length = 200)
	private String country;

	@Size(max = 10)
	@Column(name="ZIPCODE", length = 10)
	private String zipcode;

	@Size(max = 50)
	@Column(name="CITY", length = 50)
	private String city;

	@Size(max = 50)
	@Column(name="STREET", length = 50)
	private String streetName;

	@Size(max = 50)
	@Column(name="STREET_TYPE", length = 50)
	private String streetType;

	@Size(max = 15)
	@Column(name="HOUSE_NO", length = 15)
	private String houseNumber;

	@Size(max = 10)
	@Column(name="BUILDING", length = 10)
	private String building;

	@Size(max = 10)
	@Column(name="STAIRCASE", length = 10)
	private String staircase;

	@Size(max = 10)
	@Column(name="FLOOR", length = 10)
	private String floor;

	@Size(max = 10)
	@Column(name="DOOR", length = 10)
	private String door;

	@Size(max = 20)
	@Column(name="PARCELL_NUMBER", length = 20)
	private String parcelNumber;

	public Address() {
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetType() {
		return streetType;
	}

	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getStaircase() {
		return staircase;
	}

	public void setStaircase(String staircase) {
		this.staircase = staircase;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getDoor() {
		return door;
	}

	public void setDoor(String door) {
		this.door = door;
	}

	public String getParcelNumber() {
		return parcelNumber;
	}

	public void setParcelNumber(String parcelNumber) {
		this.parcelNumber = parcelNumber;
	}

}
