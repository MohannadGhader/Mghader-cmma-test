package com.path.dbmaps.vo;

import com.path.lib.vo.BaseVO;

public class AdminVO extends BaseVO {

	private int admin_id;
	private String name;
	private String username;
	private String password;
	private String salt;
	private String mobile_number;
	private int active;
	private int is_customer_care;

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getIs_customer_care() {
		return is_customer_care;
	}

	public void setIs_customer_care(int is_customer_care) {
		this.is_customer_care = is_customer_care;
	}

}
