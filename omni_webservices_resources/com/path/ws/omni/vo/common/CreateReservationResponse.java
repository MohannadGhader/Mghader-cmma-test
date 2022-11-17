package com.path.ws.omni.vo.common;

import java.sql.Time;

public class CreateReservationResponse extends ResponseBaseObject {

	private int machine_driver_id;
	private int machine_id;
	private int driver_id;
	private String plate_number;
	private String mobile_number;
	private String driver_name;
	private String driver_profile;
	private Time timeToReachClient;
	private double distance;
	private Time timeOfTrip;
	// 0 if free 1 if no
	private int freeReservation;

	private int reservation_id;
	private int reservation_list_id;

	private double costOfTripWithDestination;
	private double pinalty;

	private int insertedfrReservation_id;
	private int notInsertedfrReservation_id;

	public int getInsertedfrReservation_id() {
		return insertedfrReservation_id;
	}

	public void setInsertedfrReservation_id(int insertedfrReservation_id) {
		this.insertedfrReservation_id = insertedfrReservation_id;
	}

	public int getNotInsertedfrReservation_id() {
		return notInsertedfrReservation_id;
	}

	public void setNotInsertedfrReservation_id(int notInsertedfrReservation_id) {
		this.notInsertedfrReservation_id = notInsertedfrReservation_id;
	}

	public String getPlate_number() {
		return plate_number;
	}

	public void setPlate_number(String plate_number) {
		this.plate_number = plate_number;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}

	public String getDriver_profile() {
		return driver_profile;
	}

	public void setDriver_profile(String driver_profile) {
		this.driver_profile = driver_profile;
	}

	public int getMachine_driver_id() {
		return machine_driver_id;
	}

	public void setMachine_driver_id(int machine_driver_id) {
		this.machine_driver_id = machine_driver_id;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public int getMachine_id() {
		return machine_id;
	}

	public void setMachine_id(int machine_id) {
		this.machine_id = machine_id;
	}

	public Time getTimeToReachClient() {
		return timeToReachClient;
	}

	public void setTimeToReachClient(Time timeToReachClient) {
		this.timeToReachClient = timeToReachClient;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Time getTimeOfTrip() {
		return timeOfTrip;
	}

	public void setTimeOfTrip(Time timeOfTrip) {
		this.timeOfTrip = timeOfTrip;
	}

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

	public CreateReservationResponse(String plate_number, String mobile_number, String driver_name,
			String driver_profile, Time timeToReachClient, double distance, Time timeOfTrip) {
		super();
		this.plate_number = plate_number;
		this.mobile_number = mobile_number;
		this.driver_name = driver_name;
		this.driver_profile = driver_profile;
		this.timeToReachClient = timeToReachClient;
		this.distance = distance;
		this.timeOfTrip = timeOfTrip;
	}

	public CreateReservationResponse() {
		super();
	}

	public double getCostOfTripWithDestination() {
		return costOfTripWithDestination;
	}

	public void setCostOfTripWithDestination(double costOfTripWithDestination) {
		this.costOfTripWithDestination = costOfTripWithDestination;
	}

	public double getPinalty() {
		return pinalty;
	}

	public void setPinalty(double pinalty) {
		this.pinalty = pinalty;
	}

	public int getFreeReservation() {
		return freeReservation;
	}

	public void setFreeReservation(int freeReservation) {
		this.freeReservation = freeReservation;
	}

}
