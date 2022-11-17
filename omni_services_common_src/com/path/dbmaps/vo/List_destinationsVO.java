package com.path.dbmaps.vo;

public class List_destinationsVO {

	private int List_destinations_id;
	private String nickname;
	private int client_id;
	private double latitude;
	private double longitude;

	public int getList_destinations_id() {
		return List_destinations_id;
	}

	public void setList_destinations_id(int list_destinations_id) {
		List_destinations_id = list_destinations_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
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

}
