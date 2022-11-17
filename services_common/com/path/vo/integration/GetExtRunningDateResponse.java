package com.path.vo.integration;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)

//TP#339072 - HGhazal - Get external running date - 28/10/2015 
public class GetExtRunningDateResponse{
	private Date		odtRunDate; //Running Date
    private Integer 	olErrorCode;
    private String	 	osErrorDesc;
	public Integer getOlErrorCode() {
		return olErrorCode;
	}
	public void setOlErrorCode(Integer olErrorCode) {
		this.olErrorCode = olErrorCode;
	}
	public String getOsErrorDesc() {
		return osErrorDesc;
	}
	public void setOsErrorDesc(String osErrorDesc) {
		this.osErrorDesc = osErrorDesc;
	}
	public void setOdtRunDate(Date odtRunDate) {
		this.odtRunDate = odtRunDate;
	}
	public Date getOdtRunDate() {
		return odtRunDate;
	}
    
}