package com.path.dbmaps.vo;

import java.sql.Date;
import java.sql.Time;

import com.path.lib.vo.BaseVO;

public class Sended_notificationVO extends BaseVO {

	private int sended_notification_id;
	private int machine_driver_location_id;
	private int notification_id;
	private Date date;
	private Time time;

	public int getSended_notification_id() {
		return sended_notification_id;
	}

	public void setSended_notification_id(int sended_notification_id) {
		this.sended_notification_id = sended_notification_id;
	}

	public int getMachine_driver_location_id() {
		return machine_driver_location_id;
	}

	public void setMachine_driver_location_id(int machine_driver_location_id) {
		this.machine_driver_location_id = machine_driver_location_id;
	}

	public int getNotification_id() {
		return notification_id;
	}

	public void setNotification_id(int notification_id) {
		this.notification_id = notification_id;
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

	public Sended_notificationVO(int machine_driver_location_id, int notification_id, Date date, Time time) {
		super();
		this.machine_driver_location_id = machine_driver_location_id;
		this.notification_id = notification_id;
		this.date = date;
		this.time = time;
	}

}
