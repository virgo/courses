package hu.virgo.courses.hibernate.lesson07;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson07.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(JpaUnit.class)
public class SubGraphApiTest {

	@PersistenceContext(unitName = "course_test")
	private EntityManager em;

	@Test
	public void subTest() {
		EntityGraph<Order> graph = em.createEntityGraph(Order.class);
		graph.addAttributeNodes("items");
		graph.addSubgraph("items")
				.addAttributeNodes("product");
		Map<String, Object> hints = new HashMap<>();
		hints.put("javax.persistence.fetchgraph", graph);
		Order o = em.find(Order.class, 1, hints);
		System.out.println("valami");
	}
}
