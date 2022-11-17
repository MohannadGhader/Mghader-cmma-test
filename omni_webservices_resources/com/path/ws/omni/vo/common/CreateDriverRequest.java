package com.path.ws.omni.vo.common;

import java.sql.Date;
import java.sql.Time;

public class CreateDriverRequest extends OmniCommonRequest {

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
	private int on_off;

	private int machine_driver_id;
	private int machine_id;

	private int day_of_week;
	private int start_day;
	private int end_day;
	private Time start_work_hour;
	private Time end_work_hour;
	private Time start_break_hour;
	private Time end_break_hour;
	private int is_holiday;
	private int is_weekend;
	private int weekEnd;
	private int driver_availability;

	private double latitude;
	private double longitude;
	private int create_update;

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

	public int getMachine_driver_id() {
		return machine_driver_id;
	}

	public void setMachine_driver_id(int machine_driver_id) {
		this.machine_driver_id = machine_driver_id;
	}

	public int getDay_of_week() {
		return day_of_week;
	}

	public void setDay_of_week(int day_of_week) {
		this.day_of_week = day_of_week;
	}

	public int getStart_day() {
		return start_day;
	}

	public void setStart_day(int start_day) {
		this.start_day = start_day;
	}

	public int getEnd_day() {
		return end_day;
	}

	public void setEnd_day(int end_day) {
		this.end_day = end_day;
	}

	public Time getStart_work_hour() {
		return start_work_hour;
	}

	public void setStart_work_hour(Time start_work_hour) {
		this.start_work_hour = start_work_hour;
	}

	public Time getEnd_work_hour() {
		return end_work_hour;
	}

	public void setEnd_work_hour(Time end_work_hour) {
		this.end_work_hour = end_work_hour;
	}

	public Time getStart_break_hour() {
		return start_break_hour;
	}

	public void setStart_break_hour(Time start_break_hour) {
		this.start_break_hour = start_break_hour;
	}

	public Time getEnd_break_hour() {
		return end_break_hour;
	}

	public void setEnd_break_hour(Time end_break_hour) {
		this.end_break_hour = end_break_hour;
	}

	public int getIs_holiday() {
		return is_holiday;
	}

	public void setIs_holiday(int is_holiday) {
		this.is_holiday = is_holiday;
	}

	public int getIs_weekend() {
		return is_weekend;
	}

	public void setIs_weekend(int is_weekend) {
		this.is_weekend = is_weekend;
	}

	public int getWeekEnd() {
		return weekEnd;
	}

	public void setWeekEnd(int weekEnd) {
		this.weekEnd = weekEnd;
	}

	public int getDriver_availability() {
		return driver_availability;
	}

	public void setDriver_availability(int driver_availability) {
		this.driver_availability = driver_availability;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getMachine_id() {
		return machine_id;
	}

	public void setMachine_id(int machine_id) {
		this.machine_id = machine_id;
	}

	public int getCreate_update() {
		return create_update;
	}

	public void setCreate_update(int create_update) {
		this.create_update = create_update;
	}

	public int getOn_off() {
		return on_off;
	}

	public void setOn_off(int on_off) {
		this.on_off = on_off;
	}

}
