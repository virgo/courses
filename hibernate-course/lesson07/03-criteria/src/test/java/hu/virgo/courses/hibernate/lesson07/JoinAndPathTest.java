package hu.virgo.courses.hibernate.lesson07;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson07.model.Department;
import hu.virgo.courses.hibernate.lesson07.model.Department_;
import hu.virgo.courses.hibernate.lesson07.model.Employee;
import hu.virgo.courses.hibernate.lesson07.model.Employee_;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(JpaUnit.class)
public class JoinAndPathTest {

	@PersistenceContext(unitName = "course_test")
	private EntityManager em;

	@Test
	public void pathQuery() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> q = cb.createQuery(Employee.class);

		Root<Employee> emp = q.from(Employee.class);

		q.select(emp)
				.where(cb.equal(emp.get(Employee_.department).get(Department_.location), "London"));

		List<Employee> emps = em.createQuery(q).getResultList();
		System.out.println(emps);
	}

	@Test
	public void multiRoot() {
/*
		SELECT DISTINCT d
		FROM Department d, Employee e
		WHERE d = e.department

*/
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Department> c = cb.createQuery(Department.class);
		Root<Department> dept = c.from(Department.class);
		Root<Employee> emp = c.from(Employee.class);
		c.select(dept)
				.distinct(true)
				.where(
						cb.and(
								cb.equal(dept, emp.get(Employee_.department)),
								cb.like(emp.get(Employee_.name), "%Mézga%")
						)
				);

		List<Department> deps = em.createQuery(c).getResultList();
		System.out.println(deps);
	}

	@Test
	public void dynamicQuery() {
		Map<String, Object> filters = new HashMap<>();
//		filters.put("fLocation", "Szeged");
		filters.put("fEmployeeName", "m");

		List<Predicate> predicates = new ArrayList<>();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Department> c = cb.createQuery(Department.class);
		Root<Department> dept = c.from(Department.class);
		c.select(dept);
		{
			Object filter;
			if ((filter = filters.get("fLocation")) != null) {
				predicates.add(cb.equal(dept.get(Department_.location), filter));
			}
		}
		{
			Object filter;
			if ((filter = filters.get("fEmployeeName")) != null) {
				c.distinct(true);
				Root<Employee> emp = c.from(Employee.class);
				predicates.add(
						cb.and(
								cb.equal(dept, emp.get(Employee_.department)),
								cb.like(emp.get(Employee_.name), "%" + filter + "%")
						)
				);
			}
		}

		if (predicates.size() == 1) {
			c.where(predicates.get(0));
		} else {
			c.where(cb.and(predicates.toArray(new Predicate[0])));
		}

		List<Department> departments = em.createQuery(c).getResultList();
		System.out.println(departments);
	}
}
