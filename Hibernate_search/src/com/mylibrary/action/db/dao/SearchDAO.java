package com.mylibrary.action.db.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.lucene.queryParser.ParseException;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.mylibrary.action.db.entitys.Book;
import com.mylibrary.action.db.manger.SessionManger;

public class SearchDAO {

	private Date getDate(String year) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, Integer.parseInt(year));
			return calendar.getTime();
		} catch (NumberFormatException nfe) {
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Set doSearching(String searchKey) throws ParseException {
		System.out.println("searchKey : " + searchKey);
		Session session = SessionManger.openSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		final QueryBuilder b = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(Book.class).get();

		org.apache.lucene.search.Query luceneQuery = b.keyword()
				.onFields("title", "publisher", "desc", "isbn", "authors.name")
				.matching(searchKey).createQuery();
		org.hibernate.Query fullTextQuery = fullTextSession
				.createFullTextQuery(luceneQuery);
		List result = fullTextQuery.list();
		
		
		Date date = this.getDate(searchKey);
		List resultByDate = null;
			
		if (date != null) {
			luceneQuery = b.keyword().onField("dateOfPublication")
					.matching(this.getDate(searchKey)).createQuery();
			fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery);
			 resultByDate = fullTextQuery.list();
		}
		// Because od some reason, we are getting duplicate result.
		// This fix is just to get unique result.
		Set uniqueResult = new HashSet();
		if (!(result == null || result.size() == 0)) {
			uniqueResult.addAll(result);
		}
		if (!(resultByDate == null || resultByDate.size() == 0)) {
			uniqueResult.addAll(resultByDate);
		}
		return uniqueResult;
	}
	
}