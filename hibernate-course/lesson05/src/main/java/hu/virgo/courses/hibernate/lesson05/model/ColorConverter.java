package hu.virgo.courses.hibernate.lesson05.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Converter
public class ColorConverter implements AttributeConverter<Color, String> {

	private static final String SEPARATOR = ":";

	@Override
	public String convertToDatabaseColumn(Color attribute) {
		if (attribute == null) {
			return null;
		}
		return Stream.of(attribute.getRed(), attribute.getGreen(), attribute.getBlue())
				.map(String::valueOf)
				.collect(Collectors.joining(SEPARATOR));
	}

	@Override
	public Color convertToEntityAttribute(String dbData) {
		if (null == dbData) {
			return null;
		}
		Integer[] a = Stream.of(dbData.split(SEPARATOR, 3))
				.map(Integer::valueOf)
				.toArray(Integer[]::new);
		Color c = new Color();
		c.setRed(a[0]);
		c.setGreen(a[1]);
		c.setBlue(a[2]);
		return c;
	}
}
