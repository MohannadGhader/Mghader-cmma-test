package com.path.ws.omni.vo.common;

import java.sql.Time;

public class UpdateReservationStatusRequest extends OmniCommonRequest {

	private int driver_id;
	private int machine_driver_id;
	private int reservation_list_id;
	private int reservation_list_status;
	private double latitude;
	private double longitude;
	private String time_slot_string;
	private Time time;
	private int cost;
	private int nbOfClient;
	private String clientMobileNumber;
	private double distanceTakeWait;
	private double distanceStartFinish;

	private double distanceStartFinishTesting;
	private String locationsArray;
	private double costTesting;
	private String costTestingFormule;
	private double distanceStartFinishTestingDefault;

	private double kilometrageTake;
	private double kilometrageStart;
	private double kilometrageFinish;

	public int getReservation_list_id() {
		return reservation_list_id;
	}

	public void setReservation_list_id(int reservation_list_id) {
		this.reservation_list_id = reservation_list_id;
	}

	public int getReservation_list_status() {
		return reservation_list_status;
	}

	public void setReservation_list_status(int reservation_list_status) {
		this.reservation_list_status = reservation_list_status;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getTime_slot_string() {
		return time_slot_string;
	}

	public void setTime_slot_string(String time_slot_string) {
		this.time_slot_string = time_slot_string;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getNbOfClient() {
		return nbOfClient;
	}

	public void setNbOfClient(int nbOfClient) {
		this.nbOfClient = nbOfClient;
	}

	public String getClientMobileNumber() {
		return clientMobileNumber;
	}

	public void setClientMobileNumber(String clientMobileNumber) {
		this.clientMobileNumber = clientMobileNumber;
	}

	public double getDistanceTakeWait() {
		return distanceTakeWait;
	}

	public void setDistanceTakeWait(double distanceTakeWait) {
		this.distanceTakeWait = distanceTakeWait;
	}

	public double getDistanceStartFinish() {
		return distanceStartFinish;
	}

	public void setDistanceStartFinish(double distanceStartFinish) {
		this.distanceStartFinish = distanceStartFinish;
	}

	public int getMachine_driver_id() {
		return machine_driver_id;
	}

	public void setMachine_driver_id(int machine_driver_id) {
		this.machine_driver_id = machine_driver_id;
	}

	public double getDistanceStartFinishTesting() {
		return distanceStartFinishTesting;
	}

	public void setDistanceStartFinishTesting(double distanceStartFinishTesting) {
		this.distanceStartFinishTesting = distanceStartFinishTesting;
	}

	public String getLocationsArray() {
		return locationsArray;
	}

	public void setLocationsArray(String locationsArray) {
		this.locationsArray = locationsArray;
	}

	public double getCostTesting() {
		return costTesting;
	}

	public void setCostTesting(double costTesting) {
		this.costTesting = costTesting;
	}

	public String getCostTestingFormule() {
		return costTestingFormule;
	}

	public void setCostTestingFormule(String costTestingFormule) {
		this.costTestingFormule = costTestingFormule;
	}

	public double getDistanceStartFinishTestingDefault() {
		return distanceStartFinishTestingDefault;
	}

	public void setDistanceStartFinishTestingDefault(double distanceStartFinishTestingDefault) {
		this.distanceStartFinishTestingDefault = distanceStartFinishTestingDefault;
	}

	public double getKilometrageTake() {
		return kilometrageTake;
	}

	public void setKilometrageTake(double kilometrageTake) {
		this.kilometrageTake = kilometrageTake;
	}

	public double getKilometrageStart() {
		return kilometrageStart;
	}

	public void setKilometrageStart(double kilometrageStart) {
		this.kilometrageStart = kilometrageStart;
	}

	public double getKilometrageFinish() {
		return kilometrageFinish;
	}

	public void setKilometrageFinish(double kilometrageFinish) {
		this.kilometrageFinish = kilometrageFinish;
	}

}
