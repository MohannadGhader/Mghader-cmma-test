package com.path.vo.integration;

import java.io.Serializable;


//TP#259302 Rkhalil - Docuware DMS integration - 23/02/2016 
public class DmsDocuwareResponseSC implements Serializable {
    private Integer 	errorCode ;
    private String	 	errorDescription;
	private String		url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

    
}