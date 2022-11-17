package com.path.vo.integration;

import java.io.Serializable;
import java.util.HashMap;


//TP#259302 Rkhalil - Docuware DMS integration - 23/02/2016 
public class DmsDocuwareRequestSC  implements Serializable  /*extends ImBaseRequest*/{
	
	private int company ; 	// The logged in company code
	private int branch ;	// The logged In branch code
	private String	   userID; 		//User Id, 
	private String	   applicationName; //Application name,
	private String	   programReference; //prog Ref
	private String[] indexFieldName; //Index Name
	private String[] indexFieldValue; //Index Value 
	
	//ENH 514292 adding header to the index names and values in order to support multiple docuware for same screen 
	private HashMap<String,HashMap<String,String>> indexMap = new HashMap<String,HashMap<String,String>>();
	
	public int getCompany() {
		return company;
	}
	public void setCompany(int company) {
		this.company = company;
	}
	public int getBranch() {
		return branch;
	}
	public void setBranch(int branch) {
		this.branch = branch;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getProgramReference() {
		return programReference;
	}
	public void setProgramReference(String programReference) {
		this.programReference = programReference;
	}
	public String[] getIndexFieldName() {
		return indexFieldName;
	}
	public void setIndexFieldName(String[] indexFieldName) {
		this.indexFieldName = indexFieldName;
	}
	public String[] getIndexFieldValue() {
		return indexFieldValue;
	}
	public void setIndexFieldValue(String[] indexFieldValue) {
		this.indexFieldValue = indexFieldValue;
	}
	public HashMap<String, HashMap<String, String>> getIndexMap()
	{
	    return indexMap;
	}
	public void setIndexMap(HashMap<String, HashMap<String, String>> indexMap)
	{
	    this.indexMap = indexMap;
	}
	
}