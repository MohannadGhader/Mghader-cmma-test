package com.path.dbmaps.vo;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

import com.path.lib.vo.BaseVO;

public class ReservationVO extends BaseVO {

	private int reservation_id;
	private int client_id;

	private int reservation_type;
	private int occurrence_type;
	private Date date;
	private Time time;
	private String location_from;
	private String location_to;
	private double latitude_from;
	private double latitude_to;

	private double longitude_from;
	private double longitude_to;
	private int reservation_status;

	private String filterDate;
	private int limit;

	private int velocity;
	private int recurring_type;
	private String eventName;

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

	public int getOccurrence_type() {
		return occurrence_type;
	}

	public void setOccurrence_type(int occurrence_type) {
		this.occurrence_type = occurrence_type;
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

	public int getReservation_status() {
		return reservation_status;
	}

	public void setReservation_status(int reservation_status) {
		this.reservation_status = reservation_status;
	}

	public int getReservation_type() {
		return reservation_type;
	}

	public void setReservation_type(int reservation_type) {
		this.reservation_type = reservation_type;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public String getLocation_from() {
		return location_from;
	}

	public void setLocation_from(String location_from) {
		this.location_from = location_from;
	}

	public String getLocation_to() {
		return location_to;
	}

	public void setLocation_to(String location_to) {
		this.location_to = location_to;
	}

	public double getLatitude_from() {
		return latitude_from;
	}

	public void setLatitude_from(double latitude_from) {
		this.latitude_from = latitude_from;
	}

	public double getLatitude_to() {
		return latitude_to;
	}

	public void setLatitude_to(double latitude_to) {
		this.latitude_to = latitude_to;
	}

	public double getLongitude_from() {
		return longitude_from;
	}

	public void setLongitude_from(double longitude_from) {
		this.longitude_from = longitude_from;
	}

	public double getLongitude_to() {
		return longitude_to;
	}

	public void setLongitude_to(double longitude_to) {
		this.longitude_to = longitude_to;
	}

	public Date getDate() {
		return date;
	}

	public String getFilterDate() {
		return filterDate;
	}

	public void setFilterDate(String filterDate) {
		this.filterDate = filterDate;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

}
