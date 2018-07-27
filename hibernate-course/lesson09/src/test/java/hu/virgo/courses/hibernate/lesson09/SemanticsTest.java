package hu.virgo.courses.hibernate.lesson09;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson09.model.Child;
import hu.virgo.courses.hibernate.lesson09.model.Employee;
import hu.virgo.courses.hibernate.lesson09.model.Order;
import hu.virgo.courses.hibernate.lesson09.model.OrderItem;
import hu.virgo.courses.hibernate.lesson09.model.Parent;
import hu.virgo.courses.hibernate.lesson09.model.Project;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(JpaUnit.class)
public class SemanticsTest {

	@PersistenceContext(unitName = "tricks")
	private EntityManager em;


	@Test
	public void bagInsertTest() {
		Employee e = em.find(Employee.class, 1);
		Project p = em.find(Project.class, 5);
		e.addProject(p);
		em.persist(e);
		em.flush();
	}

	@Test
	public void bagRemoveTest() {
		Employee e = em.find(Employee.class, 1);
		e.getProjects().removeIf(p0 -> p0.getName().length() < 4);
		em.persist(e);
		em.flush();
	}

	private Parent fill() {
		List<Child> c = new ArrayList<>();
		{
			Child c0 = new Child();
			c0.setName("Kölök");
			em.persist(c0);
			c.add(c0);

		}
		{
			Child c0 = new Child();
			c0.setName("Aladár");
			em.persist(c0);
			c.add(c0);
		}
		{
			Child c0 = new Child();
			c0.setName("Kriszta");
			em.persist(c0);
			c.add(c0);
		}

		Parent p = new Parent();
		p.setName("Géza");
		p.setChildren(c);
		em.persist(p);
		em.flush();
		return p;
	}

	@Test
	public void listInsertTest() {
		Parent p = fill();
		Child c1 = new Child();
		c1.setName("Mikkamakka");
		p.addChild(c1);
		em.persist(c1);
		em.flush();
		em.persist(p);
		em.flush();
		em.clear();
	}

	@Test
	public void listRemoveTest() {
		Parent p = fill();
		p.getChildren().removeIf(c -> c.getName().contains("dár"));
		em.persist(p);
		em.flush();
	}

	@Test
	public void setInsertTest() {
		Order o = em.find(Order.class, 1);
		OrderItem i = new OrderItem();
		i.setProduct("Almapálinka");
		em.persist(i);
		o.addItem(i);
		em.persist(o);
		em.flush();
	}

	@Test
	public void setRemoveTest() {
		Order o = em.find(Order.class, 1);
		o.getItems().removeIf(i -> i.getQuantity() == 2);
		em.persist(o);
		em.flush();
	}
}
