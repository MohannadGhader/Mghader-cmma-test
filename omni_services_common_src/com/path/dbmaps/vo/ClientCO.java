package com.path.dbmaps.vo;

import java.math.BigInteger;
import java.sql.Date;

import com.path.lib.vo.BaseVO;

public class ClientCO extends BaseVO {

	private String device_UUID;
	private String device_manufacturer;
	private String device_name;
	private int device_id;
	private String mobile_number;
	private int client_id;
	private String username;
	private String password;
	private String new_password;
	private String confirm_password;
	private String salt;
	private String name;
	private String address;
	private String frontEndPublicKey;
	private int client_status;
	private String device_token;
	private String profile;
	private int gender;
	private String oldpassword;
	private java.util.Date last_login_client;
	private java.util.Date last_login;
	private String last_loginString;
	private int free_trip;
	private int invite_client_id;
	private int active;
	private String second_mobile_number;
	private String reference_name;
	private int admin_id;
	private int driver_id;
	private int clients_group_id;
	private int created_by_client_id;
	private int created_by_driver_id;
	private int created_by_admin_id;
	private int recurring_type;
	private String dates_list;
	private String group_name;

	public int getRecurring_type() {
		return recurring_type;
	}

	public void setRecurring_type(int recurring_type) {
		this.recurring_type = recurring_type;
	}

	public String getDates_list() {
		return dates_list;
	}

	public void setDates_list(String dates_list) {
		this.dates_list = dates_list;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getLast_loginString() {
		return last_loginString;
	}

	public void setLast_loginString(String last_loginString) {
		this.last_loginString = last_loginString;
	}

	public String getDevice_UUID() {
		return device_UUID;
	}

	public void setDevice_UUID(String device_UUID) {
		this.device_UUID = device_UUID;
	}

	public int getClient_status() {
		return client_status;
	}

	public void setClient_status(int client_status) {
		this.client_status = client_status;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public ClientCO() {
		super();
	}

	public String getFrontEndPublicKey() {
		return frontEndPublicKey;
	}

	public void setFrontEndPublicKey(String frontEndPublicKey) {
		this.frontEndPublicKey = frontEndPublicKey;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
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

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public java.util.Date getLast_login_client() {
		return last_login_client;
	}

	public void setLast_login_client(java.util.Date last_login_client) {
		this.last_login_client = last_login_client;
	}

	public java.util.Date getLast_login() {
		return last_login;
	}

	public void setLast_login(java.util.Date last_login) {
		this.last_login = last_login;
	}

	public int getDevice_id() {
		return device_id;
	}

	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}

	public String getDevice_token() {
		return device_token;
	}

	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}

	public ClientCO(String device_UUID, String username, String frontEndPublicKey) {
		super();
		this.device_UUID = device_UUID;
		this.username = username;
		this.frontEndPublicKey = frontEndPublicKey;
	}

	public String getDevice_manufacturer() {
		return device_manufacturer;
	}

	public void setDevice_manufacturer(String device_manufacturer) {
		this.device_manufacturer = device_manufacturer;
	}

	public int getFree_trip() {
		return free_trip;
	}

	public void setFree_trip(int free_trip) {
		this.free_trip = free_trip;
	}

	public int getInvite_client_id() {
		return invite_client_id;
	}

	public void setInvite_client_id(int invite_client_id) {
		this.invite_client_id = invite_client_id;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public int getClients_group_id() {
		return clients_group_id;
	}

	public void setClients_group_id(int clients_group_id) {
		this.clients_group_id = clients_group_id;
	}

	public int getCreated_by_client_id() {
		return created_by_client_id;
	}

	public void setCreated_by_client_id(int created_by_client_id) {
		this.created_by_client_id = created_by_client_id;
	}

	public int getCreated_by_driver_id() {
		return created_by_driver_id;
	}

	public void setCreated_by_driver_id(int created_by_driver_id) {
		this.created_by_driver_id = created_by_driver_id;
	}

	public int getCreated_by_admin_id() {
		return created_by_admin_id;
	}

	public void setCreated_by_admin_id(int created_by_admin_id) {
		this.created_by_admin_id = created_by_admin_id;
	}

}
