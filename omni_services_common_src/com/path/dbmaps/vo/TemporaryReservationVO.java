package com.path.dbmaps.vo;

import java.sql.Date;
import java.sql.Time;

public class TemporaryReservationVO {

	private int temporary_reservation_id;
	private int temp_reservation_id;
	private int machine_driver_id;
	private int client_id;
	private Date date;
	private Time time;

	public int getTemporary_reservation_id() {
		return temporary_reservation_id;
	}

	public void setTemporary_reservation_id(int temporary_reservation_id) {
		this.temporary_reservation_id = temporary_reservation_id;
	}

	public int getMachine_driver_id() {
		return machine_driver_id;
	}

	public void setMachine_driver_id(int machine_driver_id) {
		this.machine_driver_id = machine_driver_id;
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

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public int getTemp_reservation_id() {
		return temp_reservation_id;
	}

	public void setTemp_reservation_id(int temp_reservation_id) {
		this.temp_reservation_id = temp_reservation_id;
	}



	
}
