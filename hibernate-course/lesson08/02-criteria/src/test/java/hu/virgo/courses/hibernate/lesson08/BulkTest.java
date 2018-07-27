package hu.virgo.courses.hibernate.lesson08;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson08.model.Employee;
import hu.virgo.courses.hibernate.lesson08.model.Employee_;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

@ExtendWith(JpaUnit.class)
public class BulkTest {
	@PersistenceContext(unitName = "course_test")
	private EntityManager em;


	@Test
	public void bulkUpdate() {
		em.createQuery("update Employee e set e.salary = 1500 where e.salary <= 1000").executeUpdate();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaUpdate<Employee> update = cb.createCriteriaUpdate(Employee.class);

		Root e = update.from(Employee.class);


		update.set(Employee_.salary, 1500f);
		update.where(cb.lessThanOrEqualTo(e.get(Employee_.salary), 1000L));

		em.createQuery(update).executeUpdate();
	}

	@Test
	public void bulkUpdateCalculated() {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaUpdate<Employee> update = cb.createCriteriaUpdate(Employee.class);

		Root e = update.from(Employee.class);
		float percent = 1f + (10f / 100f);
		update.set(Employee_.salary, cb.prod(e.get(Employee_.salary), percent));
		update.where(cb.lessThanOrEqualTo(e.get(Employee_.salary), 1000f));

		em.createQuery(update).executeUpdate();
	}

	@Test
	public void attach() {
		Employee e = new Employee();
		e.setName("Valaki");
		e.setSalary(10000F);
		em.persist(e);

		System.out.println("valami");
	}
	@Test
	public void bulkWithParameter() {
		Employee emp0 = em.find(Employee.class, 3L);
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaUpdate<Employee> update = cb.createCriteriaUpdate(Employee.class);

		Root e = update.from(Employee.class);
		float percent = 1f + (10f / 100f);
		update.set(Employee_.salary, cb.prod(e.get(Employee_.salary), percent));
		ParameterExpression<Float> p = cb.parameter(Float.class, "oldSalary");

		update.where(cb.lessThanOrEqualTo(e.get(Employee_.salary), p));

		em.createQuery(update)
				.setParameter(p, 1000F)
				.executeUpdate();
		em.flush();
		em.clear();
		Employee emp1 = em.find(Employee.class, 3L);
		System.out.println("valami");
	}
}
