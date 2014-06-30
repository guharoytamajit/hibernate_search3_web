import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mylibrary.action.db.entitys.Author;
import com.mylibrary.action.db.entitys.Book;


public class Test {
public static void main(String[] args) {
	
	
	SessionFactory factory=new Configuration().configure().buildSessionFactory();
	Session session=factory.openSession();
	session.beginTransaction();
	
Author author=new Author();
author.setId(2);
author.setName("rod johnson");

Book book=new Book(2, "spring in action", "1234", "xyz", "about spring framework", 333, new Date());
book.getAuthors().add(author);
Book book2=new Book(3, "struts in action", "1234", "abc", "struts2 details", 223, new Date());
book2.getAuthors().add(author);
//Book book3=new Book(4, "hibernate in action", "234", "xyz", "hibernate", 323, new Date());
//book3.getAuthors().add(author);
//book.setAuthors(authors);
session.merge(author);
session.merge(book);
session.merge(book2);
//session.merge(book3);
	
	session.getTransaction().commit();
	session.close();
}
}
	