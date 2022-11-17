package com.path.ws.omni.vo.common;

public class ConfirmReservationResponse extends ResponseBaseObject {

	private String plate_number;
	private String driver_name;
	private String driver_mobile_number;
	private String driver_profile;

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

	public ConfirmReservationResponse(String plate_number, String driver_name, String i, String driver_profile) {
		super();
		this.plate_number = plate_number;
		this.driver_name = driver_name;
		this.driver_mobile_number = i;
		this.driver_profile = driver_profile;
	}

	public ConfirmReservationResponse() {
		super();
	}

}
