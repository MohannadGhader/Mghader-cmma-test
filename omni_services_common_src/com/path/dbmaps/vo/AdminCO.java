package com.path.dbmaps.vo;

import java.util.Date;

import com.path.lib.vo.BaseVO;

public class AdminCO extends BaseVO {

	private int admin_id;
	private int admin_device_id;
	private int device_id;
	private String name;
	private String mobile_number;
	private String username;
	private String password;
	private String device_UUID;
	private String device_name;
	private String device_manufacturer;
	private String gender;
	private int active;

	private String frontEndPublicKey;
	private String device_token;

	private Date last_login_admin;

	private String new_password;
	private String confirm_password;
	private String salt;
	private int admin_status;

	public int getAdmin_device_id() {
		return admin_device_id;
	}

	public void setAdmin_device_id(int admin_device_id) {
		this.admin_device_id = admin_device_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFrontEndPublicKey() {
		return frontEndPublicKey;
	}

	public void setFrontEndPublicKey(String frontEndPublicKey) {
		this.frontEndPublicKey = frontEndPublicKey;
	}

	public String getDevice_token() {
		return device_token;
	}

	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}

	public Date getLast_login_admin() {
		return last_login_admin;
	}

	public void setLast_login_admin(Date last_login_admin) {
		this.last_login_admin = last_login_admin;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public int getDevice_id() {
		return device_id;
	}

	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}

	public String getNew_password() {
		return new_password;
	}

	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getAdmin_status() {
		return admin_status;
	}

	public void setAdmin_status(int admin_status) {
		this.admin_status = admin_status;
	}

	public String getDevice_manufacturer() {
		return device_manufacturer;
	}

	public void setDevice_manufacturer(String device_manufacturer) {
		this.device_manufacturer = device_manufacturer;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

}
