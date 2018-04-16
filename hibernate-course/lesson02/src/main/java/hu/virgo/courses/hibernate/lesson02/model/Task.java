package hu.virgo.courses.hibernate.lesson02.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

@Embeddable
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	private String title;
	private String description;
	@Column(name = "DUE_DATE")
	private LocalDateTime dueDate;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public Duration age() {
		return Duration.between(dueDate, LocalDateTime.now());
	}

	@Override
	public String toString() {
		return "Task{" +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", dueDate=" + dueDate +
				'}';
	}
}
