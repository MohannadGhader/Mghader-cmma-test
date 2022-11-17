package com.path.dbmaps.vo;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.path.lib.vo.BaseVO;

//@NamedNativeQueries({ @NamedNativeQuery(name = "asd", query = "select * from device") })
public class DeviceVO extends BaseVO{

	/**
	 * This field corresponds to the database column device.id_device
	 */
	private int device_id;
	private String device_UUID;
	private String device_name;
	private String device_token;
	private String otp_secretKey;
	private String token;
	private String secretKey;
	private int counterOTP;

	public int getDevice_id() {
		return device_id;
	}

	public void setDevice_id(int device_id) {
		this.device_id = device_id;
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

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public int getCounterOTP() {
		return counterOTP;
	}

	public void setCounterOTP(int counterOTP) {
		this.counterOTP = counterOTP;
	}

}
