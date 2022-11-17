package com.path.dbmaps.vo;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.path.lib.vo.BaseVO;
import com.path.ws.omni.vo.common.Location;

public class DriverCO extends BaseVO {

	private int driver_id;
	private int machine_driver_id;
	private int driver_device_id;
	private int reservation_id;
	private Integer reservation_list_id;
	private int reservation_list_status;
	private double latitude;
	private double longitude;
	private String username;
	private String password;
	private String device_UUID;
	private String device_name;
	private String frontEndPublicKey;
	private int driver_status;
	private Date date;
	private String device_token;
	private String filterDate;
	private Time time_slot;
	private Time time;
	private String time_slot_string;

	private String oldpassword;
	private String confirm_password;
	private String salt;

	private java.util.Date last_login_driver;
	private java.util.Date last_login;

	private int cost;
	private int nbOfClient;
	private String clientMobileNumber;
	private double distanceTakeWait;
	private double distanceStartFinish;

	private int reservation_location_status;
	private double distanceFromTo;
	private int on_off;

	private int service_type;

	private double distanceStartFinishTesting;
	private String locationsArray;
	private double costTesting;
	private String costTestingFormule;

	private Location location;

	private double distanceStartFinishTestingDefault;

	private double kilometrageTake;
	private double kilometrageStart;
	private double kilometrageFinish;

	private int driver_availability;

	private int frLocation_id;

	public int getFrLocation_id() {
		return frLocation_id;
	}

	public void setFrLocation_id(int frLocation_id) {
		this.frLocation_id = frLocation_id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getReservation_location_status() {
		return reservation_location_status;
	}

	public void setReservation_location_status(int reservation_location_status) {
		this.reservation_location_status = reservation_location_status;
	}

	public int getMachine_driver_id() {
		return machine_driver_id;
	}

	public void setMachine_driver_id(int machine_driver_id) {
		this.machine_driver_id = machine_driver_id;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public Integer getReservation_list_id() {
		return reservation_list_id;
	}

	public void setReservation_list_id(Integer reservation_list_id) {
		this.reservation_list_id = reservation_list_id;
	}

	public int getReservation_list_status() {
		return reservation_list_status;
	}

	public void setReservation_list_status(int reservation_list_status) {
		this.reservation_list_status = reservation_list_status;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFrontEndPublicKey() {
		return frontEndPublicKey;
	}

	public void setFrontEndPublicKey(String frontEndPublicKey) {
		this.frontEndPublicKey = frontEndPublicKey;
	}

	public String getDevice_UUID() {
		return device_UUID;
	}

	public void setDevice_UUID(String device_UUID) {
		this.device_UUID = device_UUID;
	}

	public int getDriver_status() {
		return driver_status;
	}

	public void setDriver_status(int driver_status) {
		this.driver_status = driver_status;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDevice_token() {
		return device_token;
	}

	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getDriver_device_id() {
		return driver_device_id;
	}

	public void setDriver_device_id(int driver_device_id) {
		this.driver_device_id = driver_device_id;
	}

	public String getFilterDate() {
		return filterDate;
	}

	public void setFilterDate(String filterDate) {
		this.filterDate = filterDate;
	}

	public java.util.Date getLast_login_driver() {
		return last_login_driver;
	}

	public void setLast_login_driver(java.util.Date last_login_driver) {
		this.last_login_driver = last_login_driver;
	}

	public java.util.Date getLast_login() {
		return last_login;
	}

	public void setLast_login(java.util.Date last_login) {
		this.last_login = last_login;
	}

	public Time getTime_slot() {
		return time_slot;
	}

	public void setTime_slot(Time time_slot) {
		this.time_slot = time_slot;
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

	public double getDistanceFromTo() {
		return distanceFromTo;
	}

	public void setDistanceFromTo(double distanceFromTo) {
		this.distanceFromTo = distanceFromTo;
	}

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

	public int getOn_off() {
		return on_off;
	}

	public void setOn_off(int on_off) {
		this.on_off = on_off;
	}

	public int getService_type() {
		return service_type;
	}

	public void setService_type(int service_type) {
		this.service_type = service_type;
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

	public int getDriver_availability() {
		return driver_availability;
	}

	public void setDriver_availability(int driver_availability) {
		this.driver_availability = driver_availability;
	}

}
