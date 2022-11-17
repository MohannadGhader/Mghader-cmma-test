package com.path.ws.omni.vo.common;

public class SaveTokenRequest extends OmniCommonRequest {

	private int client_id;
	private int driver_id;
	private String device_token;
	private int admin_id;

	public int getClient_id() {
		return client_id;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public String getDevice_token() {
		return device_token;
	}

	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

}
