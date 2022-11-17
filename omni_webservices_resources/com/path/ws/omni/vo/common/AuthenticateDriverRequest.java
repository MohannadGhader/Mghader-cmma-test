package com.path.ws.omni.vo.common;

import schemasMicrosoftComOfficeOffice.STInsetMode;

public class AuthenticateDriverRequest extends OmniCommonRequest {

	private String device_UUID;
	private int driver_id;
	private String frontEndPublicKey;

	public String getDevice_UUID() {
		return device_UUID;
	}

	public void setDevice_UUID(String device_UUID) {
		this.device_UUID = device_UUID;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public String getFrontEndPublicKey() {
		return frontEndPublicKey;
	}

	public void setFrontEndPublicKey(String frontEndPublicKey) {
		this.frontEndPublicKey = frontEndPublicKey;
	}

}
