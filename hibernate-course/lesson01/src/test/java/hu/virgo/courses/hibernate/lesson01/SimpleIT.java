package hu.virgo.courses.hibernate.lesson01;

import hu.virgo.courses.hibernate.lesson01.model.Book;
import hu.virgo.courses.hibernate.lesson01.model.Genre;
import hu.virgo.courses.hibernate.lesson01.service.BookManager;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;

public class SimpleIT extends Arquillian {
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addPackages(true, Book.class.getPackage())
				.addPackages(true, BookManager.class.getPackage())
				.addAsManifestResource("test_persistence.xml", "persistence.xml");
	}

	@Inject
	BookManager bm;

	@Test
	public void test01() {
		Assert.assertNull(bm.getBook(10));
	}

	@Test
	public void test02() {
		Book b = new Book();
		b.setTitle("Lechner Hibernate Kurzus");
		b.setGenre(Genre.SCIFI);
		bm.addBook(b);
		Assert.assertNotNull(b.getId());
		Assert.assertNotNull(bm.getBook(b.getId()));
	}

	@Test
	public void test03() {
		Book b = new Book();
		bm.addBook(b);
	}
}
