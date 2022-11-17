package com.path.dbmaps.vo;

public class MachineDriverVO {

	private int machine_driver_id;
	private int machine_id;
	private int driver_id;

	private double latitude;
	private double longitude;

	private int machine_driver_status;

	public int getMachine_driver_id() {
		return machine_driver_id;
	}

	public void setMachine_driver_id(int machine_driver_id) {
		this.machine_driver_id = machine_driver_id;
	}

	public int getMachine_id() {
		return machine_id;
	}

	public void setMachine_id(int machine_id) {
		this.machine_id = machine_id;
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

	public int getMachine_driver_status() {
		return machine_driver_status;
	}

	public void setMachine_driver_status(int machine_driver_status) {
		this.machine_driver_status = machine_driver_status;
	}

}
