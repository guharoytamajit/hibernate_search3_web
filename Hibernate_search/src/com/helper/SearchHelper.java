package com.helper;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import com.mylibrary.action.db.entitys.Author;
import com.mylibrary.action.db.entitys.Book;
import com.mylibrary.action.db.manger.SessionManger;

public class SearchHelper {
	private  Date getDate(int year)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		return calendar.getTime();
	}
	
	public void enterData() throws java.text.ParseException {
		Book book = new Book();
		book.setTitle("Five Point Someone What not to do at IIT");
		book.setDesc("Welcome to Five Point Someone. This is not a book to teach you how to get into IIT or even survive it. In fact, it describes how bad things can get if you don't think straight. Funny, dark and non-stop, Five Point Someone is the story of three friends whose measly five-point something GPAs come in the way of everything - their friendship, their love life, their future. Will they make it?");
		book.setIsbn("8129104598");
		book.setPublisher("RUPA & CO");
		book.setPrice((float) 91.00);
		book.setDateOfPublication(this.getDate(2003));
		Author author = new Author();
		author.setName("Chetan bhagat");
		Set authors = new HashSet();
		authors.add(author);
		book.setAuthors(authors);
		
		Book book1 = new Book();
		book1.setTitle("The Da Vinci Code");
		book1.setDesc("This book describes the attempts of Robert Langdon, Professor of Religious Symbology at Harvard University, to solve the murder of renowned curator Jacques Saunière of the Louvre Museum in Paris. A baffling cipher is found near his body. Saunière's granddaughter and Langdon attempt to sort out the bizarre riddles and are stunned to discover a trail of clues hidden in the works of Leonardo Da Vinci.The unraveling of the mystery requires solutions to a series of simple brain-teasers, including anagrams and number puzzles. The ultimate solution is found to be intimately connected with the possible location of the Holy Grail and to a mysterious society called the Priory of Sion, as well as to the Knights Templar. The story also involves the Roman Catholic organization Opus Dei.");
		book1.setIsbn("0-385-50420-9");
		book1.setPublisher("Doubleday Group");
		book1.setDateOfPublication(this.getDate(2004));
		book1.setPrice((float) 299.00);
		Author author1 = new Author();
		author1.setName("Dan Brown");
		Set authors1 = new HashSet();
		authors1.add(author1);
		book1.setAuthors(authors1);

		Session session = SessionManger.getCurrentSession();
		session.beginTransaction();
		session.save(book);
		session.save(book1);
		session.getTransaction().commit();
	}

	public static void main(String[] args) throws java.text.ParseException,
			org.apache.lucene.queryParser.ParseException {
		SearchHelper search = new SearchHelper();
		search.enterData();
	}
}