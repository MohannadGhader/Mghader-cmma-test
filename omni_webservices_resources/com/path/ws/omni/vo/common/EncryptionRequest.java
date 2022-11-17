package com.path.ws.omni.vo.common;

public class EncryptionRequest {

	private String frontEndPublicKeyString;
	private String frontEndPrivateKeyString;

	private String backEndPublicKeyString;
	private String encryptedKey;
	private String initVector;
	private String data;

	private String dataToEncrypt;

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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getBackEndPublicKeyString() {
		return backEndPublicKeyString;
	}

	public void setBackEndPublicKeyString(String backEndPublicKeyString) {
		this.backEndPublicKeyString = backEndPublicKeyString;
	}

	public String getEncryptedKey() {
		return encryptedKey;
	}

	public void setEncryptedKey(String encryptedKey) {
		this.encryptedKey = encryptedKey;
	}

	public String getInitVector() {
		return initVector;
	}

	public void setInitVector(String initVector) {
		this.initVector = initVector;
	}

	public String getDataToEncrypt() {
		return dataToEncrypt;
	}

	public void setDataToEncrypt(String dataToEncrypt) {
		this.dataToEncrypt = dataToEncrypt;
	}

}
