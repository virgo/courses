package hu.virgo.courses.hibernate.lesson06;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson06.model.Employee;
import hu.virgo.courses.hibernate.lesson06.model.EmployeeId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ExtendWith(JpaUnit.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IdClassTest {

	@PersistenceContext(unitName = "course_test")
	private EntityManager em;
	private boolean hasRan;

	@BeforeEach
	public void init() {
		if (hasRan) {
			return;
		}
		{
			Employee e = new Employee();
			e.setCountryCode("HUN");
			e.setNumber(1);
			e.setName("Dub Spancer");
			e.setSalary(1500);
			em.persist(e);
		}
		{
			Employee e = new Employee();
			e.setCountryCode("HUN");
			e.setNumber(2);
			e.setName("Trance Hill");
			e.setSalary(200);
			em.persist(e);
		}
		{
			Employee e = new Employee();
			e.setCountryCode("DEN");
			e.setNumber(1);
			e.setName("Pampalini");
			e.setSalary(2500);
			em.persist(e);
		}
		em.flush();
		em.clear();
		hasRan = true;
	}

	@Test
	public void findTest() {
		Employee e = em.find(Employee.class, new EmployeeId("HUN", 1));
		Assertions.assertNotNull(e);
	}

	@Test
	public void queryTest() {
		List<Employee> l = em.createQuery("select e from Employee e where e.countryCode = :code", Employee.class)
				.setParameter("code", "HUN")
				.getResultList();
		Assertions.assertEquals(2, l.size());
	}

}
