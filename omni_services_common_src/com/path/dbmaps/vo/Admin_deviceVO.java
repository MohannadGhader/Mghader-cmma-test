package com.path.dbmaps.vo;

public class Admin_deviceVO {

	private int admin_device_id;
	private int admin_id;
	private int device_id;
	private String last_login;

	public int getAdmin_device_id() {
		return admin_device_id;
	}

	public void setAdmin_device_id(int admin_device_id) {
		this.admin_device_id = admin_device_id;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public int getDevice_id() {
		return device_id;
	}

	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}

	public String getLast_login() {
		return last_login;
	}

	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}

}
