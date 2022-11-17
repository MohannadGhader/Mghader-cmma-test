package com.path.ws.omni.vo.common;

import java.sql.Date;
import java.sql.Time;

public class AddMaintenanceRequest extends OmniCommonRequest {

	private int driver_id;
	private int machine_driver_id;
	private int mnt_type;
	private String description;
	private String pic_of_facture;
	private Date date;
	private Time time;
	private int amount;

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

	public int getMnt_type() {
		return mnt_type;
	}

	public void setMnt_type(int mnt_type) {
		this.mnt_type = mnt_type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPic_of_facture() {
		return pic_of_facture;
	}

	public void setPic_of_facture(String pic_of_facture) {
		this.pic_of_facture = pic_of_facture;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
