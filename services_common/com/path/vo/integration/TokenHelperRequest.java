package com.path.vo.integration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

//TP#330603 Rkhalil - token helper - 09/09/2015
@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class TokenHelperRequest  /*extends ImBaseRequest*/{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String asUser ; // The logged user name
	private String asPassword ;//the password
	
	public String getAsUser() {
		return asUser;
	}
	public void setAsUser(String asUser) {
		this.asUser = asUser;
	}
	public String getAsPassword() {
		return asPassword;
	}
	public void setAsPassword(String asPassword) {
		this.asPassword = asPassword;
	}// The logged password

}	
	