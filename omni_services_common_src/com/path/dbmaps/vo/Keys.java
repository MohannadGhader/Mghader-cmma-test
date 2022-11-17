package com.path.dbmaps.vo;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.SecretKey;

public class Keys {

	private PublicKey backEndPublicKey;
	private PrivateKey backEndPrivateKey;
	private String backEndPublicKeyString;
	private SecretKey secretKey;
	private String secretKeyString;
	private byte[] initVector;

	public PublicKey getBackEndPublicKey() {
		return backEndPublicKey;
	}

	public void setBackEndPublicKey(PublicKey backEndPublicKey) {
		this.backEndPublicKey = backEndPublicKey;
	}

	public PrivateKey getBackEndPrivateKey() {
		return backEndPrivateKey;
	}

	public void setBackEndPrivateKey(PrivateKey backEndPrivateKey) {
		this.backEndPrivateKey = backEndPrivateKey;
	}

	public String getBackEndPublicKeyString() {
		return backEndPublicKeyString;
	}

	public void setBackEndPublicKeyString(String backEndPublicKeyString) {
		this.backEndPublicKeyString = backEndPublicKeyString;
	}

	public SecretKey getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(SecretKey secretKey) {
		this.secretKey = secretKey;
	}

	public String getSecretKeyString() {
		return secretKeyString;
	}

	public void setSecretKeyString(String secretKeyString) {
		this.secretKeyString = secretKeyString;
	}

	public byte[] getInitVector() {
		return initVector;
	}

	public void setInitVector(byte[] initVector) {
		this.initVector = initVector;
	}

}
