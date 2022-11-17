package com.path.ws.omni.vo.common;

import java.math.BigInteger;

public class RegisterClientRequest extends OmniCommonRequest {

	private String device_name;
	private String device_manufacturer;
	private String mobile_number;

	private String username;
	private String password;
	private String confirm_password;
	private String name;
	private String address;

	private String frontEndPublicKey;

	private int client_status;
	private int gender;
	private String device_token;

	private String second_mobile_number;
	private String reference_name;

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFrontEndPublicKey() {
		return frontEndPublicKey;
	}

	public void setFrontEndPublicKey(String frontEndPublicKey) {
		this.frontEndPublicKey = frontEndPublicKey;
	}

	public int getClient_status() {
		return client_status;
	}

	public void setClient_status(int client_status) {
		this.client_status = client_status;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
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

	public String getDevice_manufacturer() {
		return device_manufacturer;
	}

	public void setDevice_manufacturer(String device_manufacturer) {
		this.device_manufacturer = device_manufacturer;
	}

	public String getSecond_mobile_number() {
		return second_mobile_number;
	}

	public void setSecond_mobile_number(String second_mobile_number) {
		this.second_mobile_number = second_mobile_number;
	}

	public String getReference_name() {
		return reference_name;
	}

	public void setReference_name(String reference_name) {
		this.reference_name = reference_name;
	}

}
