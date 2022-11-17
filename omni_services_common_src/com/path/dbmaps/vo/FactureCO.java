package com.path.dbmaps.vo;

import java.sql.Date;
import java.sql.Time;

public class FactureCO {

	private int facture_id;
	private int reservation_id;
	private double cost;
	private int discount;
	private double cost_after_discount;

	private int client_id;
	private Date date;
	private Time time;
	/*
	 * 0: not paid 1: paid
	 */
	private int paid;

	public int getPaid() {
		return paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}

	public int getFacture_id() {
		return facture_id;
	}

	public void setFacture_id(int facture_id) {
		this.facture_id = facture_id;
	}

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
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

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
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

	public FactureCO(double cost, double cost_after_discount, int client_id, Date date, Time time) {
		super();
		this.cost = cost;
		this.cost_after_discount = cost_after_discount;
		this.client_id = client_id;
		this.date = date;
		this.time = time;
	}

	
}
