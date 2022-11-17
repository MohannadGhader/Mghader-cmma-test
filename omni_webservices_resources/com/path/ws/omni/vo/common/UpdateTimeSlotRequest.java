package com.path.ws.omni.vo.common;

import java.sql.Time;

public class UpdateTimeSlotRequest extends OmniCommonRequest {

	private int reservation_id;
	private int reservation_list_id;
	private int client_id;
	private Time time_slot;

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

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public Time getTime_slot() {
		return time_slot;
	}

	public void setTime_slot(Time time_slot) {
		this.time_slot = time_slot;
	}

}
