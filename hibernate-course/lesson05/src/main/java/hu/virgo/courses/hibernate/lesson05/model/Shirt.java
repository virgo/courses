package hu.virgo.courses.hibernate.lesson05.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "SHIRTS")
@EntityListeners({BrandNameListener.class, BrandNameListener02.class})
public class Shirt extends Audited implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "BRAND", nullable = false)
	private String brand;

	@Column(name = "NET_PRICE", nullable = false, precision = 3)
	private float netPrice;

	@Column(name = "COLOR")
	private ColorAuto color;

	@ElementCollection
	@Convert(converter = ColorConverter.class)
	private List<Color> colors;

	@PrePersist
	public void fixBrand() {
		if (null == brand) {
			brand = "NO-NAME";
		}
	}

	@PostPersist
	public void postPersist() {
		System.out.println("Persisted");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public float getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(float netPrice) {
		this.netPrice = netPrice;
	}

	public ColorAuto getColor() {
		return color;
	}

	public void setColor(ColorAuto color) {
		this.color = color;
	}

	public List<Color> getColors() {
		return colors;
	}

	public void setColors(List<Color> colors) {
		this.colors = colors;
	}
}
