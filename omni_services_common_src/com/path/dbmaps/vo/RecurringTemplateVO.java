package com.path.dbmaps.vo;

import java.sql.Date;

public class RecurringTemplateVO {

	private int recurring_template_id;
	private int reservation_id;
	private int periodicity_type;
	private Date start_date;
	private Date end_date;
	private int recur_count;
	
	public int getRecurring_template_id() {
		return recurring_template_id;
	}
	public void setRecurring_template_id(int recurring_template_id) {
		this.recurring_template_id = recurring_template_id;
	}
	public int getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
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

	
	
}
