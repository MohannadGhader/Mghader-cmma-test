package com.path.ws.omni.vo.common;

import java.io.Serializable;

public class AddressFrom implements Serializable {
	private String city_from;
	private String street_from;
	private String building_from;
	private int floor_from;
	private String second_mobile_number_from;

	public String getCity_from() {
		return city_from;
	}

	public void setCity_from(String city_from) {
		this.city_from = city_from;
	}

	public String getStreet_from() {
		return street_from;
	}

	public void setStreet_from(String street_from) {
		this.street_from = street_from;
	}

	public String getBuilding_from() {
		return building_from;
	}

	public void setBuilding_from(String building_from) {
		this.building_from = building_from;
	}

	public int getFloor_from() {
		return floor_from;
	}

	public void setFloor_from(int floor_from) {
		this.floor_from = floor_from;
	}

	public AddressFrom() {
		super();
	}

	public AddressFrom(String city_from, String street_from, String building_from, int floor_from) {
		super();
		this.city_from = city_from;
		this.street_from = street_from;
		this.building_from = building_from;
		this.floor_from = floor_from;

	}

	public AddressFrom(String s) {
		super();
	}

	public String getSecond_mobile_number_from() {
		return second_mobile_number_from;
	}

	public void setSecond_mobile_number_from(String second_mobile_number_from) {
		this.second_mobile_number_from = second_mobile_number_from;
	}

}
