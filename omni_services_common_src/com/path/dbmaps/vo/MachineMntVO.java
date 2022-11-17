package com.path.dbmaps.vo;

import java.sql.Date;

public class MachineMntVO {

	private int machine_mnt_id;
	private int machine_id;
	private String issue_type;
	private int store_id;
	private Date date;
	private double cost;

	public int getMachine_mnt_id() {
		return machine_mnt_id;
	}

	public void setMachine_mnt_id(int machine_mnt_id) {
		this.machine_mnt_id = machine_mnt_id;
	}

	public int getMachine_id() {
		return machine_id;
	}

	public void setMachine_id(int machine_id) {
		this.machine_id = machine_id;
	}

	public String getIssue_type() {
		return issue_type;
	}

	public void setIssue_type(String issue_type) {
		this.issue_type = issue_type;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
