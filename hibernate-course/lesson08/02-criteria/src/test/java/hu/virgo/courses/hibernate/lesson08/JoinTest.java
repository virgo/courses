package hu.virgo.courses.hibernate.lesson08;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson08.model.Department_;
import hu.virgo.courses.hibernate.lesson08.model.Employee;
import hu.virgo.courses.hibernate.lesson08.model.Employee_;
import hu.virgo.courses.hibernate.lesson08.model.Project;
import hu.virgo.courses.hibernate.lesson08.model.Project_;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(JpaUnit.class)
public class JoinTest {

	@PersistenceContext(unitName = "course_test")
	private EntityManager em;

	@Test
	public void joinTest() {
		Map<String, Object> filters = new HashMap<>();
//		filters.put("fName", "zga");
		filters.put("fProjectName", "tás");
		filters.put("fLocation", "Szeged");

/*
Hibernate: select distinct employee0_.id as id1_1_, employee0_.department_id as departme4_1_,
	employee0_.name as name2_1_, employee0_.salary as salary3_1_
		from Employee employee0_
			left outer join Employee_Project projects1_ on employee0_.id=projects1_.employees_id
			left outer join Project project2_ on projects1_.projects_id=project2_.id
		where (employee0_.name like ?)
			and (project2_.name like ?)


Hibernate: select distinct employee0_.id as id1_1_, employee0_.department_id as departme4_1_,
	employee0_.name as name2_1_, employee0_.salary as salary3_1_
		from Employee employee0_
			left outer join Employee_Project projects1_ on employee0_.id=projects1_.employees_id
			left outer join Project project2_ on projects1_.projects_id=project2_.id
			cross join Department department3_
		where employee0_.department_id=department3_.id
			and (employee0_.name like ?)
			and department3_.location=?
			and (project2_.name like ?)
 */
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
		Root<Employee> emp = c.from(Employee.class);

		c.select(emp);
		c.distinct(true);

		List<Predicate> predicates = new ArrayList<>();
		{
			Object filter;
			if ((filter = filters.get("fName")) != null) {
				predicates.add(cb.like(emp.get(Employee_.name), "%" + filter + "%"));
			}
		}
		{
			Object filter;
			if ((filter = filters.get("fLocation")) != null) {
				predicates.add(cb.equal(emp.get(Employee_.department).get(Department_.location), filter));
			}
		}
		{
			Object filter;
			if ((filter = filters.get("fProjectName")) != null) {
				Join<Employee, Project> project = emp.join(Employee_.projects, JoinType.LEFT);
				predicates.add(cb.like(project.get(Project_.name), "%" + filter + "%"));
			}
		}

		if (predicates.size() == 1) {
			c.where(predicates.get(0));
		} else {
			c.where(cb.and(predicates.toArray(new Predicate[0])));
		}
		List<Employee> employees = em.createQuery(c).getResultList();
		System.out.println(employees);
	}

	@Test
	public void subQueryTest() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);

		Subquery<Long> sub = cq.subquery(Long.class);
		Root<Project> subRoot = sub.from(Project.class);
		Join<Project, Employee> subAuthors = subRoot.join(Project_.employees);
		sub.select(cb.count(subRoot.get(Project_.id)));
		sub.where(cb.equal(root.get(Employee_.id), subAuthors.get(Employee_.id)));

		cq.where(cb.greaterThanOrEqualTo(sub, 1L));

		TypedQuery query = em.createQuery(cq);
		List<Employee> employees = query.getResultList();
		System.out.println(employees);
	}
}
