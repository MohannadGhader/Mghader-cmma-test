package com.path.ws.omni.vo.common;

import java.security.PublicKey;

import javax.crypto.SecretKey;

public class HandShakeResponse extends ResponseBaseObject {

	private PublicKey backEndPublicKey;
	private String backEndPublicKeyString;

	public PublicKey getBackEndPublicKey() {
		return backEndPublicKey;
	}

	public void setBackEndPublicKey(PublicKey backEndPublicKey) {
		this.backEndPublicKey = backEndPublicKey;
	}

	public HandShakeResponse(PublicKey backEndPublicKey) {
		super();
		this.backEndPublicKey = backEndPublicKey;
	}

	public HandShakeResponse() {
		super();
	}

	public String getBackEndPublicKeyString() {
		return backEndPublicKeyString;
	}

	public void setBackEndPublicKeyString(String backEndPublicKeyString) {
		this.backEndPublicKeyString = backEndPublicKeyString;
	}

}
