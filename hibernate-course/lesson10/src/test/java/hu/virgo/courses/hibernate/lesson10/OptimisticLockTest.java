package hu.virgo.courses.hibernate.lesson10;

import eu.drus.jpa.unit.api.JpaUnit;
import hu.virgo.courses.hibernate.lesson10.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@ExtendWith(JpaUnit.class)
public class OptimisticLockTest {

	@PersistenceUnit(unitName = "course_test")
	private EntityManagerFactory entityManagerFactory;


	@Test
	public void secondTest() {
		ExecutorService es = Executors.newFixedThreadPool(2);
		es.execute(() -> {
			try {
				updateEmployee1();
			} catch (Exception e) {
				System.out.println("-- exception thrown during update 1 --");
				e.printStackTrace();
			}
		});
		es.execute(() -> {
			try {
				updateEmployee2();
			} catch (Exception e) {
				System.out.println("-- exception thrown during update 2 --");
				e.printStackTrace();
			}
		});
		es.shutdown();
		//wait for the threads to finish
		try {
			es.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		loadEmployee();
	}

	private void loadEmployee() {
		EntityManager em = entityManagerFactory.createEntityManager();
		Employee employee = em.find(Employee.class, 1);
		System.out.println("Employee loaded: " + employee);
	}

	private void updateEmployee1() {
		System.out.println("Update 1 starts, changing dept to Sales");
		EntityManager em = entityManagerFactory.createEntityManager();
		Employee employee = em.find(Employee.class, 1);
		em.getTransaction().begin();
		System.out.println("Lock Mode for update 1: " + em.getLockMode(employee));
		employee.setName("Egyik");
		try {
			System.out.println("Pausing first transaction for 1 second");
			//wait for 1 sec before commit
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("committing first transaction");
		em.getTransaction().commit();
		em.close();
		System.out.println("Employee updated 1: " + employee);	}

	private void updateEmployee2() {
		System.out.println("Update 2 starts, changing dept to Admin");
		EntityManager em = entityManagerFactory.createEntityManager();
		Employee employee = em.find(Employee.class, 1);
		em.getTransaction().begin();
		System.out.println("Lock Mode for update 2: " + em.getLockMode(employee));
		employee.setName("Másik");
		em.getTransaction().commit();
		em.close();
		System.out.println("Employee updated 2: " + employee);
	}

}
