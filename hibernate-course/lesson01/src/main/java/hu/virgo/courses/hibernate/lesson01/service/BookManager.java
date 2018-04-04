package hu.virgo.courses.hibernate.lesson01.service;

import hu.virgo.courses.hibernate.lesson01.model.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Stateless
public class BookManager implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "course")
	private EntityManager em;

	public Book getBook(long id) {
		return em.find(Book.class,id);
	}

	public Book addBook(Book b) {
		em.persist(b);
		return b;
	}
}
