package com.path.dbmaps.vo;

import java.sql.Time;
import java.util.Date;

import com.path.lib.vo.BaseVO;

public class MachineDriverLocationVO extends BaseVO {

	private int machine_driver_location_id;
	private int machine_driver_id;
	private int reservation_list_id;
	private double longitude;
	private double latitude;
	private int reservation_location_status;
	private String name;
	private java.sql.Date date;
	private Time time;
	private int driver_id;

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public int getMachine_driver_location_id() {
		return machine_driver_location_id;
	}

	public void setMachine_driver_location_id(int machine_driver_location_id) {
		this.machine_driver_location_id = machine_driver_location_id;
	}

	public int getMachine_driver_id() {
		return machine_driver_id;
	}

	public void setMachine_driver_id(int machine_driver_id) {
		this.machine_driver_id = machine_driver_id;
	}

	public int getReservation_list_id() {
		return reservation_list_id;
	}

	public void setReservation_list_id(int reservation_list_id) {
		this.reservation_list_id = reservation_list_id;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public int getReservation_location_status() {
		return reservation_location_status;
	}

	public void setReservation_location_status(int reservation_location_status) {
		this.reservation_location_status = reservation_location_status;
	}

	public MachineDriverLocationVO(int machine_driver_id, int reservation_list_id, double longitude, double latitude,
			int reservation_location_status) {
		super();
		this.machine_driver_id = machine_driver_id;
		this.reservation_list_id = reservation_list_id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.reservation_location_status = reservation_location_status;
	}

	public MachineDriverLocationVO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

}
