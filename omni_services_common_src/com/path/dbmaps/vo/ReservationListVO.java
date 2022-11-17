package com.path.dbmaps.vo;

import java.sql.Date;
import java.sql.Time;

public class ReservationListVO {

	private int reservation_list_id;
	private int reservation_id;
	private int machine_driver_id;
	private Date date;
	private Time time;
	private Time time_slot;
	private Date dateOfCreateReservation;
	private long timeOfCreateReservation;

	private int reservation_list_status;

	private double distance;

	private int nb_of_reservation;

	private int cancel;
	private int broker_id;
	private int service;

	/*
	 * 0 quick reservation start 1 quick reservation by phone 2 quick reservation
	 * tlubtuk
	 */
	private int quickReservationType;

	private double distanceStartFinishTestingDefault;

	public int getNb_of_reservation() {
		return nb_of_reservation;
	}

	public void setNb_of_reservation(int nb_of_reservation) {
		this.nb_of_reservation = nb_of_reservation;
	}

	public int getReservation_list_id() {
		return reservation_list_id;
	}

	public void setReservation_list_id(int reservation_list_id) {
		this.reservation_list_id = reservation_list_id;
	}

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

	public int getMachine_driver_id() {
		return machine_driver_id;
	}

	public void setMachine_driver_id(int machine_driver_id) {
		this.machine_driver_id = machine_driver_id;
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

	public Time getTime_slot() {
		return time_slot;
	}

	public void setTime_slot(Time time_slot) {
		this.time_slot = time_slot;
	}

	public int getReservation_list_status() {
		return reservation_list_status;
	}

	public void setReservation_list_status(int reservation_list_status) {
		this.reservation_list_status = reservation_list_status;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Date getDateOfCreateReservation() {
		return dateOfCreateReservation;
	}

	public void setDateOfCreateReservation(Date dateOfCreateReservation) {
		this.dateOfCreateReservation = dateOfCreateReservation;
	}

	public long getTimeOfCreateReservation() {
		return timeOfCreateReservation;
	}

	public void setTimeOfCreateReservation(long timeOfCreateReservation) {
		this.timeOfCreateReservation = timeOfCreateReservation;
	}

	public int getCancel() {
		return cancel;
	}

	public void setCancel(int cancel) {
		this.cancel = cancel;
	}

	public int getBroker_id() {
		return broker_id;
	}

	public void setBroker_id(int broker_id) {
		this.broker_id = broker_id;
	}

	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	public int getQuickReservationType() {
		return quickReservationType;
	}

	public void setQuickReservationType(int quickReservationType) {
		this.quickReservationType = quickReservationType;
	}

	public double getDistanceStartFinishTestingDefault() {
		return distanceStartFinishTestingDefault;
	}

	public void setDistanceStartFinishTestingDefault(double distanceStartFinishTestingDefault) {
		this.distanceStartFinishTestingDefault = distanceStartFinishTestingDefault;
	}

}
