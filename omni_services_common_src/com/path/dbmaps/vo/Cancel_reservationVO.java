package com.path.dbmaps.vo;

import java.sql.Date;

public class Cancel_reservationVO {

	private int cancel_reservation_id;
	private int client_id;
	private Date date;

	public int getCancel_reservation_id() {
		return cancel_reservation_id;
	}

	public void setCancel_reservation_id(int cancel_reservation_id) {
		this.cancel_reservation_id = cancel_reservation_id;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
