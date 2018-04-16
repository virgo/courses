package hu.virgo.courses.hibernate.lesson02.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "COMPANIES")
@SequenceGenerator(name = "company_seq", sequenceName = "company_sequence")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
	@Column(name = "ID")
	private Long id;

	@NotNull
	@Size(max = 100)
	@Column(name = "NAME", length = 100)
	private String name;

	@NotNull
	@Size(max = 200)
	@Column(name = "LONGNAME", length = 200)
	private String longname;

	@NotNull
	@Size(max = 20)
	@Column(name = "REGISTRY_NUMBER", length = 20)
	private String registryNumber;

	@NotNull
	@Size(max = 20)
	@Column(name = "TAX_NUMBER", length = 20)
	private String taxNumber;

	public Company() {
	}

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

	public String getLongname() {
		return longname;
	}

	public void setLongname(String longname) {
		this.longname = longname;
	}

	public String getRegistryNumber() {
		return registryNumber;
	}

	public void setRegistryNumber(String registryNumber) {
		this.registryNumber = registryNumber;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}
}
