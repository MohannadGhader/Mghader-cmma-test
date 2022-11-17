package com.path.dbmaps.vo;

import java.math.BigInteger;

public class ClientDeviceCO {

	private int client_id;
	private int device_id;
	private int client_device_id;
	private String device_UUID;
	private String username;
	private String frontEndPublicKey;
	private int client_status;

	private int free_trip;

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public String getFrontEndPublicKey() {
		return frontEndPublicKey;
	}

	public void setFrontEndPublicKey(String frontEndPublicKey) {
		this.frontEndPublicKey = frontEndPublicKey;
	}

	public String getDevice_UUID() {
		return device_UUID;
	}

	public void setDevice_UUID(String device_UUID) {
		this.device_UUID = device_UUID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getDevice_id() {
		return device_id;
	}

	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}

	public int getClient_device_id() {
		return client_device_id;
	}

	public void setClient_device_id(int client_device_id) {
		this.client_device_id = client_device_id;
	}

	public int getClient_status() {
		return client_status;
	}

	public void setClient_status(int client_status) {
		this.client_status = client_status;
	}

	public int getFree_trip() {
		return free_trip;
	}

	public void setFree_trip(int free_trip) {
		this.free_trip = free_trip;
	}

}
