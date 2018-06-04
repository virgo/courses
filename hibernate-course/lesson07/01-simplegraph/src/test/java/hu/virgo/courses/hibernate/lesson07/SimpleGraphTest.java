package hu.virgo.courses.hibernate.lesson07;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson07.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(JpaUnit.class)
public class SimpleGraphTest {

	@PersistenceContext(unitName = "course_test")
	private EntityManager em;

	@Test
	public void graphTest() {
		Employee e0 = em.find(Employee.class, 1);
		em.clear();

		EntityGraph graph = em.getEntityGraph("Employee.withDepartment");

		Map<String, Object> hints = new HashMap<>();
		hints.put("javax.persistence.fetchgraph", graph);
		Employee e = em.find(Employee.class, 1, hints);
	}

	@Test
	public void graphTest2() {
		Employee e0 = em.find(Employee.class, 1);
		em.clear();

		EntityGraph graph = em.getEntityGraph("Employee.withProjects");

		Map<String, Object> hints = new HashMap<>();
		hints.put("javax.persistence.fetchgraph", graph);
		Employee e = em.find(Employee.class, 1, hints);

	}
}
