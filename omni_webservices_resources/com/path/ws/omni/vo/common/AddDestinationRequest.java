package com.path.ws.omni.vo.common;

public class AddDestinationRequest extends OmniCommonRequest {

	private int reservation_list_id;
	private String destination_name;

	public int getReservation_list_id() {
		return reservation_list_id;
	}

	public void setReservation_list_id(int reservation_list_id) {
		this.reservation_list_id = reservation_list_id;
	}

	public String getDestination_name() {
		return destination_name;
	}

	public void setDestination_name(String destination_name) {
		this.destination_name = destination_name;
	}

}
