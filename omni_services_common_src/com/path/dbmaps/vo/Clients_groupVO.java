package com.path.dbmaps.vo;

import java.sql.Date;
import java.sql.Time;

public class Clients_groupVO {

	private int group_template_id;
	private int clients_group_id;
	private String clients_group_name;
	private String location_to;
	private Time time;
	private int monthly_weekly_daily;
	private int times_per_day;
	private Date start_date;
	private String dates_list;
	private String periods;

	public String getClients_group_name() {
		return clients_group_name;
	}

	public void setClients_group_name(String clients_group_name) {
		this.clients_group_name = clients_group_name;
	}

	public String getPeriods() {
		return periods;
	}

	public void setPeriods(String periods) {
		this.periods = periods;
	}

	public String getDates_list() {
		return dates_list;
	}

	public void setDates_list(String dates_list) {
		this.dates_list = dates_list;
	}

	public int getTimes_per_day() {
		return times_per_day;
	}

	public void setTimes_per_day(int times_per_day) {
		this.times_per_day = times_per_day;
	}

	public int getGroup_template_id() {
		return group_template_id;
	}

	public void setGroup_template_id(int group_template_id) {
		this.group_template_id = group_template_id;
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

	public int getClients_group_id() {
		return clients_group_id;
	}

	public void setClients_group_id(int clients_group_id) {
		this.clients_group_id = clients_group_id;
	}

	public String getLocation_to() {
		return location_to;
	}

	public void setLocation_to(String location_to) {
		this.location_to = location_to;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

}
