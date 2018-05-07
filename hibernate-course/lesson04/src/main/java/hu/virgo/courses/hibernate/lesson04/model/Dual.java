package hu.virgo.courses.hibernate.lesson04.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name = "Dual")
@Table(name = "DUAL")
public class Dual implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DUMMY")
    private String dummy;

	public String getDummy() {
		return dummy;
	}

	public void setDummy(String dummy) {
		this.dummy = dummy;
	}
}