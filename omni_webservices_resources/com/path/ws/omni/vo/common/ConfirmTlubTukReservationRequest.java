package com.path.ws.omni.vo.common;

import java.sql.Date;
import java.sql.Time;

public class ConfirmTlubTukReservationRequest extends OmniCommonRequest {

	private int reservation_list_id;
	private int reservation_id;
	private double amount;
	private String description;

	private String second_mobile_number;
	private int paramlov_id;
	private double longitude_from;
	private double latitude_from;
	private double latitude_to;
	private double longitude_to;

	private int broker_id;

	private Date date;
	private Time time;

	private java.util.Date dateTimeOfCreateReservation;

	private AddressFrom address_from;
	private AddressTo address_to;

	private int location_type_from;
	private int location_type_to;

	public int getLocation_type_from() {
		return location_type_from;
	}

	public void setLocation_type_from(int location_type_from) {
		this.location_type_from = location_type_from;
	}

	public int getLocation_type_to() {
		return location_type_to;
	}

	public void setLocation_type_to(int location_type_to) {
		this.location_type_to = location_type_to;
	}

	public AddressFrom getAddress_from() {
		return address_from;
	}

	public void setAddress_from(AddressFrom address_from) {
		this.address_from = address_from;
	}

	public AddressTo getAddress_to() {
		return address_to;
	}

	public void setAddress_to(AddressTo address_to) {
		this.address_to = address_to;
	}

	public int getReservation_list_id() {
		return reservation_list_id;
	}

	public void setReservation_list_id(int reservation_list_id) {
		this.reservation_list_id = reservation_list_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSecond_mobile_number() {
		return second_mobile_number;
	}

	public void setSecond_mobile_number(String second_mobile_number) {
		this.second_mobile_number = second_mobile_number;
	}

	public int getParamlov_id() {
		return paramlov_id;
	}

	public void setParamlov_id(int paramlov_id) {
		this.paramlov_id = paramlov_id;
	}

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

	public double getLongitude_from() {
		return longitude_from;
	}

	public void setLongitude_from(double longitude_from) {
		this.longitude_from = longitude_from;
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

	public double getLongitude_to() {
		return longitude_to;
	}

	public void setLongitude_to(double longitude_to) {
		this.longitude_to = longitude_to;
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

	public java.util.Date getDateTimeOfCreateReservation() {
		return dateTimeOfCreateReservation;
	}

	public void setDateTimeOfCreateReservation(java.util.Date dateTimeOfCreateReservation) {
		this.dateTimeOfCreateReservation = dateTimeOfCreateReservation;
	}

	public int getBroker_id() {
		return broker_id;
	}

	public void setBroker_id(int broker_id) {
		this.broker_id = broker_id;
	}

}
