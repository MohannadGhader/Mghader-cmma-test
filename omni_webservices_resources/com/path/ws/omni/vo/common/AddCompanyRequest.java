package com.path.ws.omni.vo.common;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class AddCompanyRequest extends OmniCommonRequest {

	private String company_name;
	private String mobile_number;
	private String email;
	private String logo;
	private String listOfMobileNumber;

	private String file;
	private String discount;

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getListOfMobileNumber() {
		return listOfMobileNumber;
	}

	public void setListOfMobileNumber(String listOfMobileNumber) {
		this.listOfMobileNumber = listOfMobileNumber;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

}
