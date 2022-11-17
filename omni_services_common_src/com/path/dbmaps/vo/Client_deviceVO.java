package com.path.dbmaps.vo;

public class Client_deviceVO {

	private int client_device_id;
	private int client_id;
	private int device_id;
	private int client_status;
	private String client_token;

	public int getClient_device_id() {
		return client_device_id;
	}

	public void setClient_device_id(int client_device_id) {
		this.client_device_id = client_device_id;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public int getDevice_id() {
		return device_id;
	}

	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}

	public int getClient_status() {
		return client_status;
	}

	public void setClient_status(int client_status) {
		this.client_status = client_status;
	}

	public String getClient_token() {
		return client_token;
	}

	public void setClient_token(String client_token) {
		this.client_token = client_token;
	}

}
