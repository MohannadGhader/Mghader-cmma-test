package com.path.dbmaps.vo;

import java.math.BigInteger;

public class ReservationDeviceCO {

	private int reservation_id;
	private BigInteger device_serial;
	private int client_id;

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

	public BigInteger getDevice_serial() {
		return device_serial;
	}

	public void setDevice_serial(BigInteger device_serial) {
		this.device_serial = device_serial;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

}
