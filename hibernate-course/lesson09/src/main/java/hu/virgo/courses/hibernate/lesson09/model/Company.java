package hu.virgo.courses.hibernate.lesson09.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
//@Table(name = "COMPANIES")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@NotNull
	@Size(max = 100)
	@Column(name = "NAME", length = 100)
	private String name;

	@NotNull
	@Size(max = 200)
	@Column(name = "LONGNAME", length = 200)
	private String longName;

	@NotNull
	@Size(max = 20)
	@Column(name = "REGISTRY_NUMBER", length = 20)
	private String registryNumber;

	@NotNull
	@Size(max = 20)
	@Column(name = "TAX_NUMBER", length = 20)
	private String taxNumber;

	@Embedded
	private Address registeredAddress;

	@Embedded
	private Address postalAddress;

}
