package com.path.ws.omni.vo.common;

public class LoginAdminResponse extends ResponseBaseObject {

	private String token;
	private int admin_id;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

}
