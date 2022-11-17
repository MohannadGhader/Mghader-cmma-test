package com.path.dbmaps.vo;

import com.path.lib.vo.BaseVO;

public class DeviceDriverVO extends BaseVO {

	private int device_driver_id;
	private String device_name;
	private String device_UUID;
	private String device_token;
	private String secretKey;
	private String otp_secretKey;
	private String token;
	private int counterOTP;

	public int getDevice_driver_id() {
		return device_driver_id;
	}

	public void setDevice_driver_id(int device_driver_id) {
		this.device_driver_id = device_driver_id;
	}

	public String getDevice_UUID() {
		return device_UUID;
	}

	public void setDevice_UUID(String device_UUID) {
		this.device_UUID = device_UUID;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getDevice_token() {
		return device_token;
	}

	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getOtp_secretKey() {
		return otp_secretKey;
	}

	public void setOtp_secretKey(String otp_secretKey) {
		this.otp_secretKey = otp_secretKey;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getCounterOTP() {
		return counterOTP;
	}

	public void setCounterOTP(int counterOTP) {
		this.counterOTP = counterOTP;
	}

}
