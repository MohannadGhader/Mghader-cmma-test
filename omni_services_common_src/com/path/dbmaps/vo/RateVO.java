package com.path.dbmaps.vo;

public class RateVO {

	private int rate_id;
	private int reservation_id;
	private int machine_id;
	private int driver_id;
	private int machine_rating;
	private int driver_rating;
	private String machine_comment;
	private String driver_comment;
	
	
	public int getRate_id() {
		return rate_id;
	}
	public void setRate_id(int rate_id) {
		this.rate_id = rate_id;
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
	public int getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}
	public int getMachine_rating() {
		return machine_rating;
	}
	public void setMachine_rating(int machine_rating) {
		this.machine_rating = machine_rating;
	}
	public int getDriver_rating() {
		return driver_rating;
	}
	public void setDriver_rating(int driver_rating) {
		this.driver_rating = driver_rating;
	}
	public String getMachine_comment() {
		return machine_comment;
	}
	public void setMachine_comment(String machine_comment) {
		this.machine_comment = machine_comment;
	}
	public String getDriver_comment() {
		return driver_comment;
	}
	public void setDriver_comment(String driver_comment) {
		this.driver_comment = driver_comment;
	}
	
	
}
