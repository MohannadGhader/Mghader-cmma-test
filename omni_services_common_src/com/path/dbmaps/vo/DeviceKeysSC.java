package com.path.dbmaps.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.SecretKey;

import com.path.lib.vo.BaseVO;

public class DeviceKeysSC extends BaseVO{

	private String device_UUID;
	private PublicKey frontEndPublicKey;
	private String secretKey;
	private String token;
	/*
	 * 1 invalid token 
	 * 2 expired token
	 * 
	 */
	private int status;

	public String getDevice_UUID() {
		return device_UUID;
	}

	public void setDevice_UUID(String device_UUID) {
		this.device_UUID = device_UUID;
	}

	public PublicKey getFrontEndPublicKey() {
		return frontEndPublicKey;
	}

	public void setFrontEndPublicKey(PublicKey frontEndPublicKey) {
		this.frontEndPublicKey = frontEndPublicKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

//	public DeviceKeysSC(PublicKey frontEndPublicKey, String secretKey, String token) {
//		super();
//		this.frontEndPublicKey = frontEndPublicKey;
//		this.secretKey = secretKey;
//		this.token = token;
//	}


	public DeviceKeysSC() {
		super();
	}

	public DeviceKeysSC(String secretKey, String token) {
		super();
		this.secretKey = secretKey;
		this.token = token;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
