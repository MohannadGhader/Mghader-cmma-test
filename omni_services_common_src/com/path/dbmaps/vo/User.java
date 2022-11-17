package com.path.dbmaps.vo;

public class User {

	private int app;
	private String username;
	private String device_UUID;
	private String device_name;
	private int language_id;
	private String device_token;
	private String device_manufacturer;

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

	public int getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(int language_id) {
		this.language_id = language_id;
	}

	public User(int app, String username, String device_UUID, String device_name, int language_id,
			String device_manufacturer) {
		super();
		this.app = app;
		this.username = username;
		this.device_UUID = device_UUID;
		this.device_name = device_name;
		this.language_id = language_id;
		this.device_manufacturer = device_manufacturer;
	}

	public User() {
		super();
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

}
