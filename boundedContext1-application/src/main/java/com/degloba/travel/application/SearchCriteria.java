package com.degloba.travel.application;

import java.io.Serializable;

/**
 * @category Encapsula el criteri necessari per realitzar la cerca d'un hotel.
 */
public class SearchCriteria implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * The user-provided search criteria for finding Hotels.
	 */
	private String searchString = "";

	/**
	 * El número màxim de pàgines de la llista de resultats d'hotels
	 */
	private int pageSize = 5;

	/**
	 * La pàgina actual en la que està l'usuari.
	 */
	private int currentPage = 1;


	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String toString() {
		return "[Search Criteria searchString = '" + searchString + "'";
	}

}