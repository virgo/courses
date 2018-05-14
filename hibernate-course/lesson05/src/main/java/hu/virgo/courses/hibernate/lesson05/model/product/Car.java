package hu.virgo.courses.hibernate.lesson05.model.product;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("C")
@Table(name = "PRODUCT_CAR")
public class Car extends ProductBase {

	@Column(name = "CAPACITY", precision = 3)
	private float capacity;

	public float getCapacity() {
		return capacity;
	}

	public void setCapacity(float capacity) {
		this.capacity = capacity;
	}
}
