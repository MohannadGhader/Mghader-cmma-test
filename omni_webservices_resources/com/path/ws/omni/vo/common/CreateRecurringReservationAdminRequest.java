package com.path.ws.omni.vo.common;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class CreateRecurringReservationAdminRequest extends OmniCommonRequest {
	private int client_id;
	private int machine_driver_id;
	private int reservation_type;
	private int occurrence_type;
	private Time time_slot;
	private Date date;
	private Time time;
	private String location_from;
	private String location_to;
	private double latitude_from;
	private double longitude_from;
	private double latitude_to;
	private double longitude_to;
//	private List<ReservationListVO> listDate;

	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
//	private List<Date> listDate;

	private int periodicity_type;
	private Date start_date;
	private Time start_time;
	private Date end_date;
	private int recur_count;
	private String eventName;
	private String recurring_type;

	/*
	 * if no available machine in time mentioned , we can reserve another one ealier
	 * or later the mentioned time , so 0 if the client need before, 1 if after
	 */
	private int new_time;
	private int temp_reservation_id;

	private int nbOfClient;

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public int getMachine_driver_id() {
		return machine_driver_id;
	}

	public void setMachine_driver_id(int machine_driver_id) {
		this.machine_driver_id = machine_driver_id;
	}

	public int getReservation_type() {
		return reservation_type;
	}

	public void setReservation_type(int reservation_type) {
		this.reservation_type = reservation_type;
	}

	public int getOccurrence_type() {
		return occurrence_type;
	}

	public void setOccurrence_type(int occurrence_type) {
		this.occurrence_type = occurrence_type;
	}

	public Time getTime_slot() {
		return time_slot;
	}

	public void setTime_slot(Time time_slot) {
		this.time_slot = time_slot;
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

	public double getLongitude_from() {
		return longitude_from;
	}

	public void setLongitude_from(double longitude_from) {
		this.longitude_from = longitude_from;
	}

	public double getLatitude_to() {
		return latitude_to;
	}

	public void setLatitude_to(double latitude_to) {
		this.latitude_to = latitude_to;
	}

	public double getLongitude_to() {
		return longitude_to;
	}

	public void setLongitude_to(double longitude_to) {
		this.longitude_to = longitude_to;
	}

	public int getPeriodicity_type() {
		return periodicity_type;
	}

	public void setPeriodicity_type(int periodicity_type) {
		this.periodicity_type = periodicity_type;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Time getStart_time() {
		return start_time;
	}

	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getRecur_count() {
		return recur_count;
	}

	public void setRecur_count(int recur_count) {
		this.recur_count = recur_count;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getRecurring_type() {
		return recurring_type;
	}

	public void setRecurring_type(String recurring_type) {
		this.recurring_type = recurring_type;
	}

	public int getNew_time() {
		return new_time;
	}

	public void setNew_time(int new_time) {
		this.new_time = new_time;
	}

	public int getTemp_reservation_id() {
		return temp_reservation_id;
	}

	public void setTemp_reservation_id(int temp_reservation_id) {
		this.temp_reservation_id = temp_reservation_id;
	}

	public int getNbOfClient() {
		return nbOfClient;
	}

	public void setNbOfClient(int nbOfClient) {
		this.nbOfClient = nbOfClient;
	}

}
