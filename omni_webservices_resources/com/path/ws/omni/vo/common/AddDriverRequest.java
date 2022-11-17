package com.path.ws.omni.vo.common;

import java.sql.Blob;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class AddDriverRequest extends OmniCommonRequest {

	private int driver_id;
	private String name;
	private String address;
	private String mobile_number;
	private int type;
	private int driver_status;
	private Date date_of_birth;
	private String profile;
	private String pic_of_id;
	private String ar_name;

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

	public int getDriver_status() {
		return driver_status;
	}

	public void setDriver_status(int driver_status) {
		this.driver_status = driver_status;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
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

	public String getAr_name() {
		return ar_name;
	}

	public void setAr_name(String ar_name) {
		this.ar_name = ar_name;
	}

}
