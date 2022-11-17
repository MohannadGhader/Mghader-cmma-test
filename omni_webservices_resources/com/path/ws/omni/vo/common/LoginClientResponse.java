package com.path.ws.omni.vo.common;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.path.dbmaps.vo.ClientCO;

public class LoginClientResponse extends ResponseBaseObject {

	private String token;
	private String checkCombination;
	private int client_id;
	private int gender;
	private String last_login_client;
	private String username;
	private String profile;
	private String companyMobile;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCheckCombination() {
		return checkCombination;
	}

	public void setCheckCombination(String checkCombination) {
		this.checkCombination = checkCombination;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getLast_login_client() {
		return last_login_client;
	}

	public void setLast_login_client(String last_login_client) {
		this.last_login_client = last_login_client;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getCompanyMobile() {
		return companyMobile;
	}

	public void setCompanyMobile(String companyMobile) {
		this.companyMobile = companyMobile;
	}

}
