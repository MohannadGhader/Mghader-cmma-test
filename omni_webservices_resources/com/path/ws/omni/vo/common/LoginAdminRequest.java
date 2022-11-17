package com.path.ws.omni.vo.common;

public class LoginAdminRequest extends OmniCommonRequest {

	private String username;
	private String password;
	private String device_name;
	private String frontEndPublicKey;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getFrontEndPublicKey() {
		return frontEndPublicKey;
	}

	public void setFrontEndPublicKey(String frontEndPublicKey) {
		this.frontEndPublicKey = frontEndPublicKey;
	}

}
