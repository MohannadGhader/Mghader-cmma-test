package com.path.dbmaps.vo;

import java.util.Date;

import com.path.lib.vo.BaseVO;

public class ClientVO extends BaseVO {

	private int client_id;
	private String name;
	private String address;
	private String mobile_number;
	private String username;
	private String password;
	private String salt;
	private String profile;
	private int gender;
	private Date last_login_client;
	private int free_trip;
	private int clients_group_id;
	private int created_by_client_id;
	private int created_by_driver_id;
	private int created_by_admin_id;
	private String device_token;
	public String getDevice_token() {
		return device_token;
	}

	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}

	/*
	 * 0 active , 1 inactive
	 */
	private int active;

	private String reference_name;

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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

	public Date getLast_login_client() {
		return last_login_client;
	}

	public void setLast_login_client(Date last_login_client) {
		this.last_login_client = last_login_client;
	}

	public int getFree_trip() {
		return free_trip;
	}

	public void setFree_trip(int free_trip) {
		this.free_trip = free_trip;
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
