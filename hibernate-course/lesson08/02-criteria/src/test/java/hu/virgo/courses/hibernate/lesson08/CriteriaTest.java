package hu.virgo.courses.hibernate.lesson07;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson07.model.Product;
import hu.virgo.courses.hibernate.lesson07.model.Product_;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@ExtendWith(JpaUnit.class)
public class CriteriaTest {

	@PersistenceContext(unitName = "course_test")
	private EntityManager em;

	@Test
	public void firstQuery() {
		List<Product> p0 = em.createQuery("select p from Product p", Product.class).getResultList();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> q = cb.createQuery(Product.class);

		Root<Product> emp = q.from(Product.class);
		q.select(emp);
		List<Product> p1 = em.createQuery(q).getResultList();
	}

	@Test
	public void whereQuery() {
		final String productName = "Kenyér";
		List<Product> p0 = em.createQuery("select p from Product p where p.name = :prodcutName", Product.class)
				.setParameter("prodcutName", productName)
				.getResultList();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> q = cb.createQuery(Product.class);

		Root<Product> emp = q.from(Product.class);
		ParameterExpression<String> param = cb.parameter(String.class);

		q.select(emp)
				.where(cb.equal(emp.get("name"), param));
		List<Product> p1 = em.createQuery(q)
				.setParameter(param, productName)
				.getResultList();

		Assertions.assertEquals(p0, p1);
	}

	@Test
	public void simplierWhereQuery() {
		final String productName = "Kenyér";
		List<Product> p0 = em.createQuery("select p from Product p where p.name = :prodcutName", Product.class)
				.setParameter("prodcutName", productName)
				.getResultList();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> q = cb.createQuery(Product.class);

		Root<Product> emp = q.from(Product.class);
		q.select(emp)
				.where(cb.equal(emp.get("name"), productName));

		List<Product> p1 = em.createQuery(q)
				.getResultList();
	}

	@Test
	public void typeSafeQuery() {
		final String productName = "Kenyér";
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> q = cb.createQuery(Product.class);

		Root<Product> emp = q.from(Product.class);
		q.select(emp)
				.where(cb.equal(emp.get(Product_.name), productName));

		List<Product> p1 = em.createQuery(q)
				.getResultList();
	}
}
