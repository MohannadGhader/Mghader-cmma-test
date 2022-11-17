package com.path.ws.omni.vo.common;

public class AddNewFactureRequest {
	
	
	private int Reservation_id;
	private int Reservation_list_id;
	private int amount;
	private String date_from;
	private String date_to;
	private int partial_payment;
	private int full_payment;
	private int receipt_driver;
	private int receipt_admin;
	
	public int getReservation_id() {
		return Reservation_id;
	}
	public void setReservation_id(int reservation_id) {
		Reservation_id = reservation_id;
	}
	public int getReservation_list_id() {
		return Reservation_list_id;
	}
	public void setReservation_list_id(int reservation_list_id) {
		Reservation_list_id = reservation_list_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDate_from() {
		return date_from;
	}
	public void setDate_from(String date_from) {
		this.date_from = date_from;
	}
	public String getDate_to() {
		return date_to;
	}
	public void setDate_to(String date_to) {
		this.date_to = date_to;
	}
	public int getPartial_payment() {
		return partial_payment;
	}
	public void setPartial_payment(int partial_payment) {
		this.partial_payment = partial_payment;
	}
	public int getFull_payment() {
		return full_payment;
	}
	public void setFull_payment(int full_payment) {
		this.full_payment = full_payment;
	}
	public int getReceipt_driver() {
		return receipt_driver;
	}
	public void setReceipt_driver(int receipt_driver) {
		this.receipt_driver = receipt_driver;
	}
}
