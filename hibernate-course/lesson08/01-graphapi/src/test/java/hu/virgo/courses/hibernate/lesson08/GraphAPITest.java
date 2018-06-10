package hu.virgo.courses.hibernate.lesson08;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson08.model.Department;
import hu.virgo.courses.hibernate.lesson08.model.Employee;
import hu.virgo.courses.hibernate.lesson08.model.Employee_;
import hu.virgo.courses.hibernate.lesson08.model.Project;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Subgraph;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(JpaUnit.class)
public class GraphAPITest {

	@PersistenceContext(unitName = "course_test")
	private EntityManager em;

	@Test
	public void apiTest() {
		EntityGraph<Employee> graph = em.createEntityGraph(Employee.class);
		graph.addAttributeNodes("name", "salary");
		Subgraph<Department> department = graph.addSubgraph("department");

		Map<String, Object> hints = new HashMap<>();
		hints.put("javax.persistence.fetchgraph", graph);
		Employee e = em.find(Employee.class, 1, hints);
	}

	@Test
	public void apiWithMetamodel() {
		EntityGraph<Employee> graph = em.createEntityGraph(Employee.class);
		graph.addAttributeNodes(Employee_.name, Employee_.salary);

		Subgraph<Project> projects = graph.addSubgraph(Employee_.projects.getName());

		Map<String, Object> hints = new HashMap<>();
		hints.put("javax.persistence.fetchgraph", graph);

		Employee e = em.find(Employee.class, 1, hints);

	}
}
