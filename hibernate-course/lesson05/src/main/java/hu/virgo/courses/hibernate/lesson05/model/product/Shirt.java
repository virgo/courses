package hu.virgo.courses.hibernate.lesson05.model.product;

import hu.virgo.courses.hibernate.lesson05.model.Color;
import hu.virgo.courses.hibernate.lesson05.model.ColorConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("S")
@Table(name = "PRODUCT_SHIRT")
public class Shirt extends ProductBase {

	@Column(name = "BRAND")
	private String brand;

	@Column(name = "COLOR")
	@Convert(converter = ColorConverter.class)
	private Color color;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
