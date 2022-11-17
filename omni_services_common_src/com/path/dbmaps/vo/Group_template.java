package com.path.dbmaps.vo;

import java.sql.Date;

public class Group_template {
	private int group_template_id;
	private int clients_group_id;
	private int monthly_weekly_daily;
	private Date start_date;
	private int number_of_repeating;
	private String dates_list;

	public int getGroup_template_id() {
		return group_template_id;
	}

	public void setGroup_template_id(int group_template_id) {
		this.group_template_id = group_template_id;
	}

	public int getClients_group_id() {
		return clients_group_id;
	}

	public void setClients_group_id(int clients_group_id) {
		this.clients_group_id = clients_group_id;
	}

	public int getMonthly_weekly_daily() {
		return monthly_weekly_daily;
	}

	public void setMonthly_weekly_daily(int monthly_weekly_daily) {
		this.monthly_weekly_daily = monthly_weekly_daily;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public int getNumber_of_repeating() {
		return number_of_repeating;
	}

	public void setNumber_of_repeating(int number_of_repeating) {
		this.number_of_repeating = number_of_repeating;
	}

	public String getDates_list() {
		return dates_list;
	}

	public void setDates_list(String dates_list) {
		this.dates_list = dates_list;
	}

}
