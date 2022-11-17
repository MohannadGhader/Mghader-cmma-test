package com.path.ws.omni.vo.common;

public class UnregisterDeviceRequest extends OmniCommonRequest{

	private int client_id;
	private int device_id;

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

}
