package com.mylibrary.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryParser.ParseException;

import com.mylibrary.action.db.dao.SearchDAO;
import com.mylibrary.action.services.SearchSugestionMgr;

@SuppressWarnings("serial")
public class SearchController extends HttpServlet {

	@Override
	public void init() throws ServletException {
		//SearchSugestionMgr.getInstance();
	}

	@SuppressWarnings("rawtypes")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String searchKey = request.getParameter("searchKey");
		Set result = null;
		try {
			SearchDAO searchDAO = new SearchDAO();
			result = searchDAO.doSearching(searchKey);
			System.out.println("size of result : " + result);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		request.setAttribute("result", result);
		request.setAttribute("searchKey", searchKey);

		this.addSearchSuggestion(request, searchKey);
		request.getRequestDispatcher("Results.jsp").forward(request, response);
	}

	private void addSearchSuggestion(HttpServletRequest request,
			String searchKey) {
		String[] suggestions = null;
		try {
			suggestions = SearchSugestionMgr.getInstance().serachSuggetion(
					searchKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("serachSuggestions", suggestions);
	}
}