package com.path.dbmaps.vo;

import java.sql.Date;

import com.path.lib.vo.BaseVO;

import jxl.write.DateTime;

public class DeviceSC extends BaseVO {

	private int client_id;
	private int driver_id;
	private String mobile_number;
	private Date date;
	private String otp_secretKey;
	private String device_UUID;
	private String device_token;
	private String otp;
	private int device_status;
	private String username;
	private int counterOTP;
	private String device_name;
	private int counter_generated_OTP;
	private String hash;
	private String inivited_mobile_number;
	/*
	 * this attribute is added to distinguish between client and driver request 0:
	 * client 1: driver
	 */
	private int app;

	/*
	 * m is the current number of trials to validate the otp
	 */
	private int m;

	private String secretKey;
	private int otp_status;
	private java.util.Date dateTime;
	private int counter_validation;

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getOtp_secretKey() {
		return otp_secretKey;
	}

	public void setOtp_secretKey(String otp_secretKey) {
		this.otp_secretKey = otp_secretKey;
	}

	public String getDevice_UUID() {
		return device_UUID;
	}

	public void setDevice_UUID(String device_UUID) {
		this.device_UUID = device_UUID;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public int getDevice_status() {
		return device_status;
	}

	public void setDevice_status(int device_status) {
		this.device_status = device_status;
	}

	public int getApp() {
		return app;
	}

	public void setApp(int app) {
		this.app = app;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCounterOTP() {
		return counterOTP;
	}

	public void setCounterOTP(int counterOTP) {
		this.counterOTP = counterOTP;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public int getCounter_generated_OTP() {
		return counter_generated_OTP;
	}

	public void setCounter_generated_OTP(int counter_generated_OTP) {
		this.counter_generated_OTP = counter_generated_OTP;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public int getOtp_status() {
		return otp_status;
	}

	public void setOtp_status(int otp_status) {
		this.otp_status = otp_status;
	}

	public java.util.Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(java.util.Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getDevice_token() {
		return device_token;
	}

	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}

	public int getCounter_validation() {
		return counter_validation;
	}

	public void setCounter_validation(int counter_validation) {
		this.counter_validation = counter_validation;
	}

	public String getInivited_mobile_number() {
		return inivited_mobile_number;
	}

	public void setInivited_mobile_number(String inivited_mobile_number) {
		this.inivited_mobile_number = inivited_mobile_number;
	}

}
