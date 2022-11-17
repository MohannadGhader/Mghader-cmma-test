package com.path.ws.omni.vo.common;

import java.util.Date;

public class ReturnClientInfoResponse extends ResponseBaseObject {

	private String username;
	private String profile;
	private int gender;
	private String last_login_client;
	private int free_trip;

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

	public ReturnClientInfoResponse(String username, String profile) {
		super();
		this.username = username;
		this.profile = profile;
	}

	public ReturnClientInfoResponse() {
		super();
	}

	public int getFree_trip() {
		return free_trip;
	}

	public void setFree_trip(int free_trip) {
		this.free_trip = free_trip;
	}

}
