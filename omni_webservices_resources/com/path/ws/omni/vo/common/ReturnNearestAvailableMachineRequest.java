package com.path.ws.omni.vo.common;

import java.sql.Date;
import java.sql.Time;

public class ReturnNearestAvailableMachineRequest extends OmniCommonRequest {

	private double latitude_from;
	private double longitude_from;
	private Date date;
	private Time time;

	public double getLatitude_from() {
		return latitude_from;
	}

	public void setLatitude_from(double latitude_from) {
		this.latitude_from = latitude_from;
	}

	public double getLongitude_from() {
		return longitude_from;
	}

	public void setLongitude_from(double longitude_from) {
		this.longitude_from = longitude_from;
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

}
