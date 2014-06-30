package com.mylibrary.action.services;

import java.io.File;

import org.apache.lucene.search.spell.PlainTextDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class SearchSugestionMgr {

	private static int SUGGESTION_NO = 3;

	private SpellChecker spellChecker;

	private static SearchSugestionMgr sugestionMgr = new SearchSugestionMgr();

	private SearchSugestionMgr() {
		try {

			File dir = new File(SearchSugestionMgr.class.getResource(
			"/data/indexFiles/").toURI());
			Directory directory = FSDirectory.open(dir);
			this.spellChecker = new SpellChecker(directory);
			this.spellChecker.indexDictionary(new PlainTextDictionary(new File(
					SearchSugestionMgr.class.getResource(
					"/data/dictionary/dictionary.txt").toURI())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SearchSugestionMgr getInstance() {
		return SearchSugestionMgr.sugestionMgr;
	}

	public String[] serachSuggetion(String searchKey) throws Exception {

		String[] suggestions = this.spellChecker.suggestSimilar(searchKey,
				SearchSugestionMgr.SUGGESTION_NO);
		return suggestions;
	}

}
