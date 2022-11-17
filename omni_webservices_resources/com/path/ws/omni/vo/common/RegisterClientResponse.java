package com.path.ws.omni.vo.common;

public class RegisterClientResponse extends ResponseBaseObject {

	private int client_id;
	private String response;
	private String token;
	private int gender;
	private String username;
	private String profile;
	private String last_login_client;
	private String companyMobile;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getLast_login_client() {
		return last_login_client;
	}

	public void setLast_login_client(String last_login_client) {
		this.last_login_client = last_login_client;
	}

	public String getCompanyMobile() {
		return companyMobile;
	}

	public void setCompanyMobile(String companyMobile) {
		this.companyMobile = companyMobile;
	}

}
