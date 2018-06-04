package hu.virgo.courses.hibernate.lesson07;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson07.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@ExtendWith(JpaUnit.class)
public class SubGraphTest {

	@PersistenceContext(unitName = "course_test")
	private EntityManager em;

	@Test
	public void subgraphTest() {
		Order o0 = em.find(Order.class, 1);
		em.clear();

		EntityGraph graph = em.getEntityGraph("graph.Order.items");

		Map<String, Object> hints = new HashMap<>();
		hints.put("javax.persistence.fetchgraph", graph);

		Order o = em.find(Order.class, 4, hints);
	}

	@Test
	public void queryTest() {
		String param = "6";

		EntityGraph graph = em.getEntityGraph("graph.Order.items");

		List<Order> orders = em.createQuery("select o from Order o where o.orderNumber like concat('%',:orderNo,'%')", Order.class)
				.setParameter("orderNo", param)
				.setHint("javax.persistence.fetchgraph", graph)
				.getResultList();

		System.out.println(orders);
	}
}
