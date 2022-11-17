package com.path.ws.omni.vo.common;

import java.sql.Date;
import java.sql.Time;

public class AddClientToGroupRequest {

	private String mobile_number;
	private String clients_group_name;
	private int reservation_id;
	private int reservation_list_id;
	private int client_id;
	private int driver_id;
	private int machine_driver_id;
	private int reservation_type;
	private int occurrence_type;
	private Time time_slot;
	private Date date;
	private Time time;
	private Time endTime;
	private Time startTime;
	private Date dateOfCreateReservation;
	private long timeOfCreateReservation;
	private String location_from;
	private String location_to;
	private double latitude_from;
	private double longitude_from;
	private double latitude_to;
	private double longitude_to;
	private int reservation_status;
	private double latitude;
	private double longitude;
	private int periodicity_type;
	private Date start_date;
	private Date end_date;
	private int recur_count;
	private int reservation_list_status;
	private int reservation_time_status;
	private double cost;
	private double total_cost;
	private int discount;
	private double cost_after_discount;
	private int velocity;
	private String username;
	private String eventName;
	private String recurring_type;
	private double distanceStartFinishTesting;
	private String locationsArray;
	private double costTesting;
	private String costTestingFormule;
	private double distanceStartFinishTestingDefault;
	private double kilometrageTake;
	private double kilometrageStart;
	private double kilometrageFinish;
	private int reservation_list_status_created;
	private int location_type_from;
	private int location_type_to;
	private int frReservation_id;
	private int gender;
	private String last_login_client; // we can do it like we did in createUser(in implimentation)
	private int active;
	private int clients_group_id;

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getClients_group_name() {
		return clients_group_name;
	}

	public void setClients_group_name(String clients_group_name) {
		this.clients_group_name = clients_group_name;
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

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public int getMachine_driver_id() {
		return machine_driver_id;
	}

	public void setMachine_driver_id(int machine_driver_id) {
		this.machine_driver_id = machine_driver_id;
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

	public Time getTime_slot() {
		return time_slot;
	}

	public void setTime_slot(Time time_slot) {
		this.time_slot = time_slot;
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

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
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

	public String getLocation_from() {
		return location_from;
	}

	public void setLocation_from(String location_from) {
		this.location_from = location_from;
	}

	public String getLocation_to() {
		return location_to;
	}

	public void setLocation_to(String location_to) {
		this.location_to = location_to;
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

	public int getReservation_status() {
		return reservation_status;
	}

	public void setReservation_status(int reservation_status) {
		this.reservation_status = reservation_status;
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

	public int getPeriodicity_type() {
		return periodicity_type;
	}

	public void setPeriodicity_type(int periodicity_type) {
		this.periodicity_type = periodicity_type;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getRecur_count() {
		return recur_count;
	}

	public void setRecur_count(int recur_count) {
		this.recur_count = recur_count;
	}

	public int getReservation_list_status() {
		return reservation_list_status;
	}

	public void setReservation_list_status(int reservation_list_status) {
		this.reservation_list_status = reservation_list_status;
	}

	public int getReservation_time_status() {
		return reservation_time_status;
	}

	public void setReservation_time_status(int reservation_time_status) {
		this.reservation_time_status = reservation_time_status;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(double total_cost) {
		this.total_cost = total_cost;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public double getCost_after_discount() {
		return cost_after_discount;
	}

	public void setCost_after_discount(double cost_after_discount) {
		this.cost_after_discount = cost_after_discount;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getRecurring_type() {
		return recurring_type;
	}

	public void setRecurring_type(String recurring_type) {
		this.recurring_type = recurring_type;
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

	public int getReservation_list_status_created() {
		return reservation_list_status_created;
	}

	public void setReservation_list_status_created(int reservation_list_status_created) {
		this.reservation_list_status_created = reservation_list_status_created;
	}

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

	public int getFrReservation_id() {
		return frReservation_id;
	}

	public void setFrReservation_id(int frReservation_id) {
		this.frReservation_id = frReservation_id;
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

	public int getClients_group_id() {
		return clients_group_id;
	}

	public void setClients_group_id(int clients_group_id) {
		this.clients_group_id = clients_group_id;
	}

}
