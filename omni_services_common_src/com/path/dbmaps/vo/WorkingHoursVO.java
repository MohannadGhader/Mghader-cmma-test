package com.path.dbmaps.vo;

import java.sql.Date;
import java.sql.Time;

public class WorkingHoursVO {

	private int working_hours_id;
	private int driver_id;
	private Date work_date;
	/**
	 * day of the week :1:mon, 2:tue, 3:wed, 4:thu, 5:fri, 6:sat, 7:sun
	 */
	private int day_of_week;
	private Time start_work_hour;
	private Time end_work_hour;
	/**
	 * 0: is holiday 1: not holiday
	 */
	private int is_holiday;
	/**
	 * 0: is weekend 1: not weekend
	 */
	private int is_weekend;

	public int getWorking_hours_id() {
		return working_hours_id;
	}

	public void setWorking_hours_id(int working_hours_id) {
		this.working_hours_id = working_hours_id;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public Date getWork_date() {
		return work_date;
	}

	public void setWork_date(Date work_date) {
		this.work_date = work_date;
	}

	public int getDay_of_week() {
		return day_of_week;
	}

	public void setDay_of_week(int day_of_week) {
		this.day_of_week = day_of_week;
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

}
