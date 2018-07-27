package hu.virgo.courses.hibernate.lesson10.service;

import hu.virgo.courses.hibernate.lesson10.model.Department;
import hu.virgo.courses.hibernate.lesson10.model.Employee;
import org.apache.commons.lang3.time.StopWatch;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Stateless
public class Lockservice implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Object lock = new Object();


	@PersistenceContext(unitName = "course")
	private EntityManager em;

	private Consumer<Integer> wait = (sec) -> {
		synchronized (lock) {
			try {
				TimeUnit.SECONDS.timedWait(lock, sec);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};


	public Employee lock(String newName, Integer waitSec, LockModeType lockType) {
		StopWatch w = new StopWatch();
		w.start();
		Employee e = em.find(Employee.class, 1, lockType);
		w.stop();
		System.out.println("[LOCK] Employee load time: " + w.getTime(TimeUnit.MILLISECONDS));

		Department d = em.find(Department.class, 2, lockType);
		System.out.println(e.getName() + " lockmode is: " + em.getLockMode(e) + " and version is: " + e.getVersion());
		e.setName(newName);
		e.setDepartment(d);
		wait.accept(waitSec);
		e = em.merge(e);
		em.flush();
		return e;
	}

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public Employee load(Integer waitSec, LockModeType lockType) {
		StopWatch w = new StopWatch();
		w.start();
		Employee e = em.find(Employee.class, 1, lockType);
		w.stop();
		System.out.println("[LOAD] Employee load time: " + w.getTime(TimeUnit.MILLISECONDS));
		System.out.println(em.getLockMode(e) + " acquired");
		wait.accept(waitSec);
		return e;
	}

}
