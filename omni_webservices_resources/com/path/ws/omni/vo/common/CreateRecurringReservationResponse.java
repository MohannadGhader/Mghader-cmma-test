package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.Machine_driverCO;
import com.path.dbmaps.vo.TemporaryReservationVO;

public class CreateRecurringReservationResponse extends ResponseBaseObject {

	private int recurring_reservation_status;
	private int reservation_id;
	private String eventName;
	private int nbOFChangedReservation;

	public int getRecurring_reservation_status() {
		return recurring_reservation_status;
	}

	public void setRecurring_reservation_status(int recurring_reservation_status) {
		this.recurring_reservation_status = recurring_reservation_status;
	}

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public int getNbOFChangedReservation() {
		return nbOFChangedReservation;
	}

	public void setNbOFChangedReservation(int nbOFChangedReservation) {
		this.nbOFChangedReservation = nbOFChangedReservation;
	}

}
