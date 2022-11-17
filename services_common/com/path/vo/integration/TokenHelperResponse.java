package com.path.vo.integration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

//TP#330603 Rkhalil - token helper - 09/09/2015
@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class TokenHelperResponse /*extends  UnicastRemoteObject*//*extends ImBaseResponse*/{
    
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
}