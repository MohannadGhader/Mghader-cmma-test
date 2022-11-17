package com.path.ws.omni.vo.common;

public class CreateRecurringReservationAdminResponse extends ResponseBaseObject{
	private int reservation_id;
	private int reservation_list_id;
	public int getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}
	public int getReservation_list_id() {
		return reservation_list_id;
	}
	public void setReservation_list_id(int reservation_list_id) {
		this.reservation_list_id = reservation_list_id;
	}
	
	
}
