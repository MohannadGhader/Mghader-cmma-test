package com.path.dbmaps.vo;

import com.path.lib.vo.BaseVO;

public class ReservationDetailsVO extends BaseVO {

	private int reservation_details_id;
	private int reservation_list_id;
	private double amount;
	private String description;
	private String city;
	private String street;
	private String building;
	private int floor;
	private String second_mobile_number;
	private int paramlov_id;

	public int getReservation_details_id() {
		return reservation_details_id;
	}

	public void setReservation_details_id(int reservation_details_id) {
		this.reservation_details_id = reservation_details_id;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

}
