package hu.virgo.courses.hibernate.lesson05.model;

import java.io.Serializable;
import java.util.Objects;

public class Color implements Serializable {
	private static final long serialVersionUID = 1;
	private int red;
	private int green;
	private int blue;

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Color color = (Color) o;
		return red == color.red &&
				green == color.green &&
				blue == color.blue;
	}

	@Override
	public int hashCode() {

		return Objects.hash(red, green, blue);
	}
}
