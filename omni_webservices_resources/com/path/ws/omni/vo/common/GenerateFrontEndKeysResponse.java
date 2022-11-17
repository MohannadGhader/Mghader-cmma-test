package com.path.ws.omni.vo.common;

public class GenerateFrontEndKeysResponse extends ResponseBaseObject {

	private String frontEndPublicKeyString;
	private String frontEndPrivateKeyString;
	private String secretKeyString;
	private String initVecString;

	public String getFrontEndPublicKeyString() {
		return frontEndPublicKeyString;
	}

	public void setFrontEndPublicKeyString(String frontEndPublicKeyString) {
		this.frontEndPublicKeyString = frontEndPublicKeyString;
	}

	public String getFrontEndPrivateKeyString() {
		return frontEndPrivateKeyString;
	}

	public void setFrontEndPrivateKeyString(String frontEndPrivateKeyString) {
		this.frontEndPrivateKeyString = frontEndPrivateKeyString;
	}

	public String getSecretKeyString() {
		return secretKeyString;
	}

	public void setSecretKeyString(String secretKeyString) {
		this.secretKeyString = secretKeyString;
	}

	public String getInitVecString() {
		return initVecString;
	}

	public void setInitVecString(String initVecString) {
		this.initVecString = initVecString;
	}

}
