package com.path.ws.omni.vo.common;

import java.sql.Date;
import java.sql.Time;

public class ReturnReservationDetailsRequest extends OmniCommonRequest {

	private int reservation_id;

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

}
