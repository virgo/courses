package hu.virgo.courses.hibernate.lesson06;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson06.model.Department;
import hu.virgo.courses.hibernate.lesson06.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ExtendWith(JpaUnit.class)
public class ManyToOneTest {
	@PersistenceContext(unitName = "course_test")
	private EntityManager em;
	private boolean hasRan;

	Department main = new Department();
	Department rural = new Department();
	Department foreign = new Department();

	@BeforeEach
	public void init() {
		if (hasRan) {
			return;
		}
		main.setLocation("Budapest");
		em.persist(main);

		rural.setLocation("Szeged");
		em.persist(rural);

		foreign.setLocation("London");
		em.persist(foreign);

		{
			Employee emp = new Employee();
			emp.setName("Ference Hill");
			emp.setSalary(1500);
			emp.setDepartment(main);
			em.persist(emp);
		}
		{
			Employee emp = new Employee();
			emp.setName("Dub Spancer");
			emp.setSalary(2000);
			emp.setDepartment(main);
			em.persist(emp);
		}
		{
			Employee emp = new Employee();
			emp.setName("Dick Tracy");
			emp.setSalary(1000);
			emp.setDepartment(rural);
			em.persist(emp);
		}

		em.flush();
		em.clear();
		hasRan = true;
	}

	@Test
	public void selectTest() {
		List<Employee> emps = em.createQuery("select e from Employee e where e.salary >= :salary", Employee.class)
				.setParameter("salary", 1100L)
				.getResultList();

		System.out.println("valami");
	}

	@Test
	public void departmentTest() {
		List<Employee> emps = em.createQuery("select e from Person e where e.department = :department", Employee.class)
				.setParameter("department", main)
				.getResultList();

	}
}
