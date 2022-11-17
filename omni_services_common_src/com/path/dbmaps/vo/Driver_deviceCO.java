package com.path.dbmaps.vo;

import com.path.lib.vo.BaseVO;

public class Driver_deviceCO extends BaseVO {

	private int driver_device_id;
	private int device_id;
	private int driver_id;
	private String device_UUID;
	private String username;

	private int driver_status;

	private String frontEndPublicKey;

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public String getDevice_UUID() {
		return device_UUID;
	}

	public void setDevice_UUID(String device_UUID) {
		this.device_UUID = device_UUID;
	}

	public String getFrontEndPublicKey() {
		return frontEndPublicKey;
	}

	public void setFrontEndPublicKey(String frontEndPublicKey) {
		this.frontEndPublicKey = frontEndPublicKey;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getDriver_status() {
		return driver_status;
	}

	public void setDriver_status(int driver_status) {
		this.driver_status = driver_status;
	}

}
