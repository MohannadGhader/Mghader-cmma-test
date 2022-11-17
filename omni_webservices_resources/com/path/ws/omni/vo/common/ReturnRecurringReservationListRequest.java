package com.path.ws.omni.vo.common;

import java.sql.Date;

public class ReturnRecurringReservationListRequest extends OmniCommonRequest {

	private int reservation_id;
	private String filterDate;

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

	public String getFilterDate() {
		return filterDate;
	}

	public void setFilterDate(String filterDate) {
		this.filterDate = filterDate;
	}

}
