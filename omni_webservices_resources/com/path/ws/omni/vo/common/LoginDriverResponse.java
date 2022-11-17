package com.path.ws.omni.vo.common;

public class LoginDriverResponse extends ResponseBaseObject {

	private String token;
	private int driver_id;
	private String profile;
	private String username;
	private String last_login_driver;
	private int availability;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

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

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

}
