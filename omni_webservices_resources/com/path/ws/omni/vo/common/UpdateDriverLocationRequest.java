package com.path.ws.omni.vo.common;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class UpdateDriverLocationRequest extends OmniCommonRequest {

	private int driver_id;
	private double longitude;
	private double latitude;
	private Integer reservation_list_id;
	private Date date;
	private Time time;
	private int on_off;
	private int frLocation_id;

//	public List<String> location;

	public Location location;

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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
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

	public Integer getReservation_list_id() {
		return reservation_list_id;
	}

	public void setReservation_list_id(Integer reservation_list_id) {
		this.reservation_list_id = reservation_list_id;
	}

	public int getOn_off() {
		return on_off;
	}

	public void setOn_off(int on_off) {
		this.on_off = on_off;
	}

	public UpdateDriverLocationRequest(int driver_id, double longitude, double latitude, Integer reservation_list_id,
			Location location) {
		super();
		this.driver_id = driver_id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.reservation_list_id = reservation_list_id;
		this.location = location;
	}

	public UpdateDriverLocationRequest() {
		super();
	}

	public int getFrLocation_id() {
		return frLocation_id;
	}

	public void setFrLocation_id(int frLocation_id) {
		this.frLocation_id = frLocation_id;
	}

}
