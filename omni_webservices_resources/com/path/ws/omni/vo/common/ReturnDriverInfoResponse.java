package com.path.ws.omni.vo.common;

import java.sql.Date;

public class ReturnDriverInfoResponse extends ResponseBaseObject {

	private String profile;
	private String username;
	private String last_login_driver;

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLast_login_driver() {
		return last_login_driver;
	}

	public void setLast_login_driver(String last_login_driver) {
		this.last_login_driver = last_login_driver;
	}

}
