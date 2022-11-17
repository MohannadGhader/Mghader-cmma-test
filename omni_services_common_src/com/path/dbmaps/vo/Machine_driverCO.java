package com.path.dbmaps.vo;

import java.sql.Date;
import java.sql.Time;

import com.path.lib.vo.BaseVO;

public class Machine_driverCO extends BaseVO {

	private int machine_driver_id;
	private int machine_id;
	private int driver_id;
	private String plate_number;
	private String style;
	private Date ownership_date;
	private String owners_name;
	private String driver_mobile_number;
	private String driver_name;
	private String driver_profile;
	private int machine_driver_status;
	private Time timeToReachClient;
	private double distance;
	private Time time;
	private Time time_slot;
	private Time nextPeriodTime;
	private double timeInSec;
	private String username;
	private String ar_name;
	private double latitude;
	private double longitude;
	private String device_token;
	/*
	 * 1 if on now 0 off
	 */
	private int on_off;

	private long timeOfCreateReservation;
	private Date dateOfCreateReservation;

	private String timeString;

	private Date date;

	private Time start_work_hour;
	private Time end_work_hour;
	private Time start_break_hour;
	private Time end_break_hour;

	private int day_of_week;
	private int start_day;
	private int end_day;
	private int is_holiday;
	private int is_weekend;
	private int weekEnd;
	private int driver_availability;

	private int nbOfClient;

	public Machine_driverCO() {
		super();
	}

	public Machine_driverCO(Machine_driverCO mc) {
		this.machine_driver_id = mc.getMachine_driver_id();
		this.ar_name = mc.getAr_name();
		this.driver_mobile_number = mc.getDriver_mobile_number();
		this.driver_name = mc.getDriver_name();
		this.end_break_hour = mc.getEnd_break_hour();
		this.start_break_hour = mc.getStart_break_hour();
		this.start_work_hour = mc.getStart_work_hour();
		this.end_work_hour = mc.getEnd_work_hour();
		this.time_slot = mc.getTime_slot();
		this.driver_profile = mc.getDriver_profile();
	}

	public String getPlate_number() {
		return plate_number;
	}

	public void setPlate_number(String plate_number) {
		this.plate_number = plate_number;
	}

	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}

	public String getDriver_mobile_number() {
		return driver_mobile_number;
	}

	public void setDriver_mobile_number(String driver_mobile_number) {
		this.driver_mobile_number = driver_mobile_number;
	}

	public String getDriver_profile() {
		return driver_profile;
	}

	public void setDriver_profile(String driver_profile) {
		this.driver_profile = driver_profile;
	}

	public int getMachine_driver_id() {
		return machine_driver_id;
	}

	public void setMachine_driver_id(int machine_driver_id) {
		this.machine_driver_id = machine_driver_id;
	}

	public int getMachine_id() {
		return machine_id;
	}

	public void setMachine_id(int machine_id) {
		this.machine_id = machine_id;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public int getMachine_driver_status() {
		return machine_driver_status;
	}

	public void setMachine_driver_status(int machine_driver_status) {
		this.machine_driver_status = machine_driver_status;
	}

	public Time getTimeToReachClient() {
		return timeToReachClient;
	}

	public void setTimeToReachClient(Time timeToReachClient) {
		this.timeToReachClient = timeToReachClient;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public double getTimeInSec() {
		return timeInSec;
	}

	public void setTimeInSec(double timeInSec) {
		this.timeInSec = timeInSec;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAr_name() {
		return ar_name;
	}

	public void setAr_name(String ar_name) {
		this.ar_name = ar_name;
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

	public long getTimeOfCreateReservation() {
		return timeOfCreateReservation;
	}

	public void setTimeOfCreateReservation(long timeOfCreateReservation) {
		this.timeOfCreateReservation = timeOfCreateReservation;
	}

	public Date getDateOfCreateReservation() {
		return dateOfCreateReservation;
	}

	public void setDateOfCreateReservation(Date dateOfCreateReservation) {
		this.dateOfCreateReservation = dateOfCreateReservation;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getNextPeriodTime() {
		return nextPeriodTime;
	}

	public void setNextPeriodTime(Time nextPeriodTime) {
		this.nextPeriodTime = nextPeriodTime;
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

	public Time getTime_slot() {
		return time_slot;
	}

	public void setTime_slot(Time time_slot) {
		this.time_slot = time_slot;
	}

	public int getOn_off() {
		return on_off;
	}

	public void setOn_off(int on_off) {
		this.on_off = on_off;
	}

	public int getNbOfClient() {
		return nbOfClient;
	}

	public void setNbOfClient(int nbOfClient) {
		this.nbOfClient = nbOfClient;
	}

	public String getDevice_token() {
		return device_token;
	}

	public void setDevice_token(String device_token) {
		this.device_token = device_token;
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

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Date getOwnership_date() {
		return ownership_date;
	}

	public void setOwnership_date(Date ownership_date) {
		this.ownership_date = ownership_date;
	}

	public String getOwners_name() {
		return owners_name;
	}

	public void setOwners_name(String owners_name) {
		this.owners_name = owners_name;
	}

}
