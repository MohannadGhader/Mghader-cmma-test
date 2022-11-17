package com.path.struts2.lib.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

public class GridParamsSC extends BaseSC implements Serializable {
	private String sortOrder, whereQuery, searchFilter,_search;

	/**
	 * will be filled in case of Single Form Search and it is the field name on which the search is happening
	 */
	private String searchField;
	/**
	 * will be filled in case of Single Form Search and it is the search operator
	 */
	private String searchOper;
	/**
	 * will be filled in case of Single Form Search and it is the search Value
	 */
	private String searchString;
	/**
	 * number of records of grid
	 */
	private int nbRec;
	/**
	 * nb of records to skip when using flippin
	 */
	private int recToskip;
	/**
	 * variable to identify only column search
	 */
	private Map srchMapFldsOnly;
	
	@JSON(serialize=false)
	public Map getSrchMapFldsOnly()
	{
	    return srchMapFldsOnly;
	}

	public void setSrchMapFldsOnly(Map srchMapFldsOnly)
	{
	    this.srchMapFldsOnly = srchMapFldsOnly;
	}
	/**
	 * Returns the number of record to be skipped before returning the records.
	 * It uses the following formula: 'getNbRec() * (getFlip() - 1)'
	 * 
	 * @return int
	 */

	public int getRecToskip() {
		return recToskip;
	}

	public void setRecToskip(int recToskip) {
		this.recToskip = recToskip;
	}

	/**
	 * sort order: asc,desc
	 */
	private String sord;
	
	private String sidx;

	private String searchQuery;
	private String colSearchQuery;
	
	private String filters; // multiple search filter
	
	/**
	 * array containing the column names in the toolbar search
	 */
	private String[] searchCols;
	
	private HashMap dateSearchCols;
	
	public HashMap getDateSearchCols() {
		return dateSearchCols;
	}

	public void setDateSearchCols(HashMap dateSearchCols) {
		this.dateSearchCols = dateSearchCols;
	}

	/**
	 * String comma separated containing the column names (index names) in the livesearch
	 */
	private String liveSearchCols;

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String[] getSearchCols() {
		return searchCols;
	}

	public void setSearchCols(String... searchCols) {
		this.searchCols = searchCols;
	}
	@JSON(serialize=false)
	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
	@JSON(serialize=false)
	public String getColSearchQuery() {
		return colSearchQuery;
	}

	public void setColSearchQuery(String colSearchQuery) {
		this.colSearchQuery = colSearchQuery;
	}

	/**
	 * Returns the search filter of the grid query.
	 * 
	 * @return String
	 */
	public String getWhereQuery() {
		return whereQuery;
	}

	/**
	 * Sets the sort Order for grid records
	 * 
	 * @param order
	 *            String
	 */
	public void setSortOrder(String order) {
		if (order != null && order.trim().length() != 0)
		{
			this.sortOrder = order;
		}
	}

	/**
	 * Used to get the Order close of the query.
	 * 
	 * @return String
	 */
	public String getSortOrder() {
		return sortOrder;
	}

	/**
	 * Sets the grid search filter
	 * 
	 * @param search
	 *            String
	 */
	public void setWhereQuery(String search) {
		if (search != null && search.trim().length() != 0)
		{
		   this.whereQuery = search;
		}
	}

	/**
	 * Returns the number of record per page.
	 * 
	 * @return int
	 */
	public int getNbRec() {
		return nbRec;
	}

	/**
	 * Sets the number of record per page.
	 * 
	 * @param nbRec
	 *            int
	 */
	public void setNbRec(int nbRec) {
		this.nbRec = nbRec;
	}

	public String getSearchFilter() {
		return searchFilter;
	}

	public void setSearchFilter(String searchFilter) {
		this.searchFilter = searchFilter;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public String get_search()
	{
	    return _search;
	}

	public void set_search(String search)
	{
	    _search = search;
	}

	public String getSearchField()
	{
	    return searchField;
	}

	public void setSearchField(String searchField)
	{
	    this.searchField = searchField;
	}

	public String getSearchOper()
	{
	    return searchOper;
	}

	public void setSearchOper(String searchOper)
	{
	    this.searchOper = searchOper;
	}

	public String getSearchString()
	{
	    return searchString;
	}

	public void setSearchString(String searchString)
	{
	    this.searchString = searchString;
	}

	public String getLiveSearchCols()
	{
	    return liveSearchCols;
	}

	public void setLiveSearchCols(String liveSearchCols)
	{
	    this.liveSearchCols = liveSearchCols;
	}
}
