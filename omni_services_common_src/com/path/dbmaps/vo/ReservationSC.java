package com.path.dbmaps.vo;

import java.sql.Date;
import java.sql.Time;

import com.path.lib.vo.BaseVO;
import com.path.ws.omni.vo.common.AddressFrom;
import com.path.ws.omni.vo.common.AddressTo;

public class ReservationSC extends BaseVO {

	private int reservation_id;
	private int reservation_list_id;
	private String plate_number;
	private String driver_name;
	private String driver_mobile_number;
	private String driver_profile;
	private String ar_name;
	private int machine_driver_id;
	private Date date;
	private Time time;
	private Time timeToReachClient;
	private String timeString;
	private double distance;
	private double latitude_from;
	private double longitude_from;
	private double latitude_to;
	private double longitude_to;
	private int reservation_type;

	private int reservation_list_status;
	private int reservation_status;
	private int reservation_time_status;
	private int limit;

	private int occurrence_type;

	private double cost;
	private double costByDriver;
	private int numberOfClient;
	private int totalCostByDriver;
	private String username;
	private String eventName;
	private int recurring_type;
	private String destination_name;
	private int costOfTripWithDestination;
	private int paramlov_id;
	private String key_value;
	private int amount;

	private int broker_id;
	private int service;
	private int cancel;

	private int nbOfClient;

	private String mobile_number;

	private int quickReservationType;
	/*
	 * 0: not paid 1: paid
	 */
	private int paid;

	private double longitude;
	private double latitude;

	private AddressFrom addressFrom;
	private AddressTo addressTo;

	private String second_mobile_number;

	private String description;
	private int location_type_from;
	private int location_type_to;

	private Date date_from;
	private Date date_to;

	public int getLocation_type_from() {
		return location_type_from;
	}

	public void setLocation_type_from(int location_type_from) {
		this.location_type_from = location_type_from;
	}

	public int getLocation_type_to() {
		return location_type_to;
	}

	public void setLocation_type_to(int location_type_to) {
		this.location_type_to = location_type_to;
	}

	public String getSecond_mobile_number() {
		return second_mobile_number;
	}

	public void setSecond_mobile_number(String second_mobile_number) {
		this.second_mobile_number = second_mobile_number;
	}

	public AddressFrom getAddressFrom() {
		return addressFrom;
	}

	public void setAddressFrom(AddressFrom addressFrom) {
		this.addressFrom = addressFrom;
	}

	public int getPaid() {
		return paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}

	public int getQuickReservationType() {
		return quickReservationType;
	}

	public void setQuickReservationType(int quickReservationType) {
		this.quickReservationType = quickReservationType;
	}

	public int getNbOfClient() {
		return nbOfClient;
	}

	public void setNbOfClient(int nbOfClient) {
		this.nbOfClient = nbOfClient;
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

	public String getPlate_number() {
		return plate_number;
	}

	public void setPlate_number(String plate_number) {
		this.plate_number = plate_number;
	}

	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}

	public String getDriver_mobile_number() {
		return driver_mobile_number;
	}

	public void setDriver_mobile_number(String driver_mobile_number) {
		this.driver_mobile_number = driver_mobile_number;
	}

	public String getDriver_profile() {
		return driver_profile;
	}

	public void setDriver_profile(String driver_profile) {
		this.driver_profile = driver_profile;
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

	public int getReservation_list_status() {
		return reservation_list_status;
	}

	public void setReservation_list_status(int reservation_list_status) {
		this.reservation_list_status = reservation_list_status;
	}

	public String getAr_name() {
		return ar_name;
	}

	public void setAr_name(String ar_name) {
		this.ar_name = ar_name;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Time getTimeToReachClient() {
		return timeToReachClient;
	}

	public void setTimeToReachClient(Time timeToReachClient) {
		this.timeToReachClient = timeToReachClient;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getOccurrence_type() {
		return occurrence_type;
	}

	public void setOccurrence_type(int occurrence_type) {
		this.occurrence_type = occurrence_type;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getCostByDriver() {
		return costByDriver;
	}

	public void setCostByDriver(double costByDriver) {
		this.costByDriver = costByDriver;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getNumberOfClient() {
		return numberOfClient;
	}

	public void setNumberOfClient(int numberOfClient) {
		this.numberOfClient = numberOfClient;
	}

	public int getTotalCostByDriver() {
		return totalCostByDriver;
	}

	public void setTotalCostByDriver(int totalCostByDriver) {
		this.totalCostByDriver = totalCostByDriver;
	}

	public int getReservation_time_status() {
		return reservation_time_status;
	}

	public void setReservation_time_status(int reservation_time_status) {
		this.reservation_time_status = reservation_time_status;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public int getReservation_status() {
		return reservation_status;
	}

	public void setReservation_status(int reservation_status) {
		this.reservation_status = reservation_status;
	}

	public int getRecurring_type() {
		return recurring_type;
	}

	public void setRecurring_type(int recurring_type) {
		this.recurring_type = recurring_type;
	}

	public String getDestination_name() {
		return destination_name;
	}

	public void setDestination_name(String destination_name) {
		this.destination_name = destination_name;
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

	public int getCostOfTripWithDestination() {
		return costOfTripWithDestination;
	}

	public void setCostOfTripWithDestination(int costOfTripWithDestination) {
		this.costOfTripWithDestination = costOfTripWithDestination;
	}

	public int getParamlov_id() {
		return paramlov_id;
	}

	public void setParamlov_id(int paramlov_id) {
		this.paramlov_id = paramlov_id;
	}

	public String getKey_value() {
		return key_value;
	}

	public void setKey_value(String key_value) {
		this.key_value = key_value;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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

	public int getCancel() {
		return cancel;
	}

	public void setCancel(int cancel) {
		this.cancel = cancel;
	}

	public int getReservation_type() {
		return reservation_type;
	}

	public void setReservation_type(int reservation_type) {
		this.reservation_type = reservation_type;
	}

	public int getMachine_driver_id() {
		return machine_driver_id;
	}

	public void setMachine_driver_id(int machine_driver_id) {
		this.machine_driver_id = machine_driver_id;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public AddressTo getAddressTo() {
		return addressTo;
	}

	public void setAddressTo(AddressTo addressTo) {
		this.addressTo = addressTo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Date getDate_from() {
		return date_from;
	}

	public void setDate_from(Date date_from) {
		this.date_from = date_from;
	}

	public Date getDate_to() {
		return date_to;
	}

	public void setDate_to(Date date_to) {
		this.date_to = date_to;
	}

}
