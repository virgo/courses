package hu.virgo.courses.hibernate.lesson08;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson08.model.Department;
import hu.virgo.courses.hibernate.lesson08.model.Employee;
import hu.virgo.courses.hibernate.lesson08.model.Employee_;
import hu.virgo.courses.hibernate.lesson08.model.Project;
import org.hibernate.criterion.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Subgraph;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(JpaUnit.class)
public class GraphAPITest {

	@PersistenceContext(unitName = "course_test")
	private EntityManager em;

	@Test
	public void apiTest() {
		EntityGraph<Employee> graph = em.createEntityGraph(Employee.class);
		graph.addAttributeNodes("projects", "department");
//		Subgraph<Department> department = graph.addSubgraph("department");

		Map<String, Object> hints = new HashMap<>();
		hints.put("javax.persistence.fetchgraph", graph);
		Employee e = em.find(Employee.class, 1, hints);

		EntityGraph<Order> graph2 = em.createEntityGraph(Order.class);
		graph.addAttributeNodes("projects", "department");

	}

	@Test
	public void immutableTest() {
		Employee e = new Employee();
		e.setName("Valaki");
		em.persist(e);

		Employee e2 = new Employee();
		e2.setName("Valaki2");
		em.persist(e2);

		Department d = new Department();
		d.setLocation("Akarattya");
		d.setEmployees(Arrays.asList(e));
		em.persist(d);
		em.flush();

		d.setLocation("Füred");
		Employee tmp = d.getEmployees().get(0);
		tmp.setName("Foo");
		em.persist(tmp);
//		List<Employee> employeeList = d.getEmployees();
//		employeeList.add(e2);
//		d.setEmployees(employeeList);
		em.merge(d);
		em.flush();

		em.remove(d);
		em.flush();
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
