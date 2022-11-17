package com.path.ws.omni.vo.common;

public class UpdateReservationStatusResponse extends ResponseBaseObject {

	private int reservation_list_status;
	private int cost;
	private int reservation_list_id;
	private String notification;

	public int getReservation_list_status() {
		return reservation_list_status;
	}

	public void setReservation_list_status(int reservation_list_status) {
		this.reservation_list_status = reservation_list_status;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getReservation_list_id() {
		return reservation_list_id;
	}

	public void setReservation_list_id(int reservation_list_id) {
		this.reservation_list_id = reservation_list_id;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

}
