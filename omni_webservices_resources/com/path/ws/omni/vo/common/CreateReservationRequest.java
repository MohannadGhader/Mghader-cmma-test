package com.path.ws.omni.vo.common;

import java.sql.Date;
import java.sql.Time;

public class CreateReservationRequest extends OmniCommonRequest {

	private int client_id;
	private int driver_id;
	private String location_from;
	private double latitude_from;
	private double longitude_from;
	private String location_to;
	private double latitude_to;
	private double longitude_to;

	private int machine_driver_id;

	private Date date;
	private Time time;
	private Time time_slot;

	private int reservation_type;
	private int occurrence_type;
	private int nbOfClient;
	private double distanceFromTo;
	private double amount;
	private String description;

	private int quickReservationType;
	private String clientMobileNumber;

	private double kilometrageTake;
	private double kilometrageStart;

	private AddressFrom address_from;
	private AddressTo address_to;
	private int reservation_list_status_created;

	private int frReservation_id;
	

	
	
////////////////////**********Mohannad*****************////////////////
	
	private String mobile_number;
	private String username;
	private int gender;
	private String last_login_client;
	private int active;
	private int clients_group_id;
	/*
	 * private int group_id;
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * public int getGroup_id() { return group_id; }
	 * 
	 * public void setGroup_id(int group_id) { this.group_id = group_id; }
	 */

	public int getClients_group_id() {
		return clients_group_id;
	}

	public void setClients_group_id(int clients_group_id) {
		this.clients_group_id = clients_group_id;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getLast_login_client() {
		return last_login_client;
	}

	public void setLast_login_client(String last_login_client) {
		this.last_login_client = last_login_client;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	
	
	
	
////////////////////**********Mohannad*****************////////////////
	
	
	public AddressFrom getAddress_from() {
		return address_from;
	}

	public void setAddress_from(AddressFrom address_from) {
		this.address_from = address_from;
	}

	public AddressTo getAddress_to() {
		return address_to;
	}

	public void setAddress_to(AddressTo address_to) {
		this.address_to = address_to;
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

	public String getClientMobileNumber() {
		return clientMobileNumber;
	}

	public void setClientMobileNumber(String clientMobileNumber) {
		this.clientMobileNumber = clientMobileNumber;
	}

	public int getQuickReservationType() {
		return quickReservationType;
	}

	public void setQuickReservationType(int quickReservationType) {
		this.quickReservationType = quickReservationType;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public String getLocation_from() {
		return location_from;
	}

	public void setLocation_from(String location_from) {
		this.location_from = location_from;
	}

	public double getLatitude_from() {
		return latitude_from;
	}

	public void setLatitude_from(double latitude_from) {
		this.latitude_from = latitude_from;
	}

	public double getLongitude_from() {
		return longitude_from;
	}

	public void setLongitude_from(double longitude_from) {
		this.longitude_from = longitude_from;
	}

	public String getLocation_to() {
		return location_to;
	}

	public void setLocation_to(String location_to) {
		this.location_to = location_to;
	}

	public double getLatitude_to() {
		return latitude_to;
	}

	public void setLatitude_to(double latitude_to) {
		this.latitude_to = latitude_to;
	}

	public double getLongitude_to() {
		return longitude_to;
	}

	public void setLongitude_to(double longitude_to) {
		this.longitude_to = longitude_to;
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

	public int getReservation_type() {
		return reservation_type;
	}

	public void setReservation_type(int reservation_type) {
		this.reservation_type = reservation_type;
	}

	public int getOccurrence_type() {
		return occurrence_type;
	}

	public void setOccurrence_type(int occurrence_type) {
		this.occurrence_type = occurrence_type;
	}

	public int getMachine_driver_id() {
		return machine_driver_id;
	}

	public void setMachine_driver_id(int machine_driver_id) {
		this.machine_driver_id = machine_driver_id;
	}

	public int getNbOfClient() {
		return nbOfClient;
	}

	public void setNbOfClient(int nbOfClient) {
		this.nbOfClient = nbOfClient;
	}

	public double getDistanceFromTo() {
		return distanceFromTo;
	}

	public void setDistanceFromTo(double distanceFromTo) {
		this.distanceFromTo = distanceFromTo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public int getReservation_list_status_created() {
		return reservation_list_status_created;
	}

	public void setReservation_list_status_created(int reservation_list_status_created) {
		this.reservation_list_status_created = reservation_list_status_created;
	}

	public int getFrReservation_id() {
		return frReservation_id;
	}

	public void setFrReservation_id(int frReservation_id) {
		this.frReservation_id = frReservation_id;
	}

}
