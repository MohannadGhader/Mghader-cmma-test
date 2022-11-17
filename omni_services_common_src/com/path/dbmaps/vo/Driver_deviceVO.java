package com.path.dbmaps.vo;

public class Driver_deviceVO {

	private int driver_device_id;
	private int device_id;
	private int driver_id;
	private int status;
	private String driver_token;

	public int getDriver_device_id() {
		return driver_device_id;
	}

	public void setDriver_device_id(int driver_device_id) {
		this.driver_device_id = driver_device_id;
	}

	public int getDevice_id() {
		return device_id;
	}

	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDriver_token() {
		return driver_token;
	}

	public void setDriver_token(String driver_token) {
		this.driver_token = driver_token;
	}

}
