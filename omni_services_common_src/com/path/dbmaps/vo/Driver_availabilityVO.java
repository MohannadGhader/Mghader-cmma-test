package com.path.dbmaps.vo;

import java.sql.Date;
import java.sql.Time;

public class Driver_availabilityVO {

	private int driver_availability_id;
	private int driver_id;
	private int availability;
	private Date date;
	private Time time;

	public int getDriver_availability_id() {
		return driver_availability_id;
	}

	public void setDriver_availability_id(int driver_availability_id) {
		this.driver_availability_id = driver_availability_id;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

}
