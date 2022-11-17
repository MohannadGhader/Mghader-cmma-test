package com.path.dbmaps.vo;

import java.sql.Blob;
import java.sql.Date;

import com.path.lib.vo.BaseVO;

public class DriverVO extends BaseVO {

	private int driver_id;
	private String name;
	private String address;
	private String mobile_number;
	private int type;
	private Date date_of_birth;
	private int driver_status;
	private String profile;
	private String pic_of_id;
	private String password;
	private String salt;
	private String username;
	private java.util.Date last_login_driver;
	private String ar_name;
	private int on_off;
	/*
	 * 0 it is a driver , 1 it is a broker
	 * 
	 */
	private int service_type;

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public int getDriver_status() {
		return driver_status;
	}

	public void setDriver_status(int driver_status) {
		this.driver_status = driver_status;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getPic_of_id() {
		return pic_of_id;
	}

	public void setPic_of_id(String pic_of_id) {
		this.pic_of_id = pic_of_id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public java.util.Date getLast_login_driver() {
		return last_login_driver;
	}

	public void setLast_login_driver(java.util.Date last_login_driver) {
		this.last_login_driver = last_login_driver;
	}

	public String getAr_name() {
		return ar_name;
	}

	public void setAr_name(String ar_name) {
		this.ar_name = ar_name;
	}

	public int getOn_off() {
		return on_off;
	}

	public void setOn_off(int on_off) {
		this.on_off = on_off;
	}

	public int getService_type() {
		return service_type;
	}

	public void setService_type(int service_type) {
		this.service_type = service_type;
	}

	public DriverVO(String name, String address, String mobile_number, int type, Date date_of_birth, int driver_status,
			String profile, String pic_of_id, String ar_name, int on_off) {
		super();
		this.name = name;
		this.address = address;
		this.mobile_number = mobile_number;
		this.type = type;
		this.date_of_birth = date_of_birth;
		this.driver_status = driver_status;
		this.profile = profile;
		this.pic_of_id = pic_of_id;
		this.ar_name = ar_name;
		this.on_off = on_off;
	}

	public DriverVO() {
		super();
	}

}
