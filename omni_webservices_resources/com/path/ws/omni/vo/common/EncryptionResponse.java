package com.path.ws.omni.vo.common;

public class EncryptionResponse extends ResponseBaseObject {

	private String encryptedData;
	private String encryptedInitVector;
	private String encryptedSecretKey;

	public String getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}

	public String getEncryptedInitVector() {
		return encryptedInitVector;
	}

	public void setEncryptedInitVector(String encryptedInitVector) {
		this.encryptedInitVector = encryptedInitVector;
	}

	public String getEncryptedSecretKey() {
		return encryptedSecretKey;
	}

	public void setEncryptedSecretKey(String encryptedSecretKey) {
		this.encryptedSecretKey = encryptedSecretKey;
	}

}
