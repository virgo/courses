package hu.virgo.courses.hibernate.lesson05.model;

import javax.persistence.PrePersist;

public class BrandNameListener02 {

	@PrePersist
	public void fixName(Shirt s) {
		if (null == s.getBrand()) {
			s.setBrand("NO-NAME02");
		}
	}
}
