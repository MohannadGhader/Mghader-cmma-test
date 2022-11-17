package com.path.dbmaps.vo;

import com.path.lib.vo.BaseVO;

public class DeviceAdminVO extends BaseVO {

	private int device_admin_id;
	private String device_UUID;
	private String device_name;
	private String token;
	private String device_token;

	public int getDevice_admin_id() {
		return device_admin_id;
	}

	public void setDevice_admin_id(int device_admin_id) {
		this.device_admin_id = device_admin_id;
	}

	public String getDevice_UUID() {
		return device_UUID;
	}

	public void setDevice_UUID(String device_UUID) {
		this.device_UUID = device_UUID;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDevice_token() {
		return device_token;
	}

	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}

}
