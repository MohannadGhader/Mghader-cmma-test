package com.path.ws.omni.vo.common;

import java.math.BigInteger;

public class RememberDeviceRequest extends OmniCommonRequest {

	private int client_id;
	private String device_name;
	private String device_manufacturer;

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getDevice_manufacturer() {
		return device_manufacturer;
	}

	public void setDevice_manufacturer(String device_manufacturer) {
		this.device_manufacturer = device_manufacturer;
	}

}
