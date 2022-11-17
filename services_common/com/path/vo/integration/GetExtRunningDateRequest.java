package com.path.vo.integration;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

@SuppressWarnings("serial")
@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)

//TP#339072 - HGhazal - Get external running date - 28/10/2015 
public class GetExtRunningDateRequest /*extends ImBaseRequest*/{
	
	private BigDecimal alCompCode ; 	// The logged in company code
	private BigDecimal alBranchCode ;	// The logged In branch code
	private String	   asAppName; 		//Application name, ex: ITR
	
	@XmlSchemaType(name="int") 
	public BigDecimal getAlCompCode() {
		return alCompCode;
	}
	public void setAlCompCode(BigDecimal alCompCode) {
		this.alCompCode = alCompCode;
	}
	@XmlSchemaType(name="int") 
	public BigDecimal getAlBranchCode() {
		return alBranchCode;
	}
	public void setAlBranchCode(BigDecimal alBranchCode) {
		this.alBranchCode = alBranchCode;
	}
	public void setAsAppName(String asAppName) {
		this.asAppName = asAppName;
	}
	public String getAsAppName() {
		return asAppName;
	}

}