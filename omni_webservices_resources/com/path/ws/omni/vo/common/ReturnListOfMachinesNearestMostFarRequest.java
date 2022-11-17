package com.path.ws.omni.vo.common;

import java.sql.Date;
import java.sql.Time;

public class ReturnListOfMachinesNearestMostFarRequest extends OmniCommonRequest {

	private double latitude_from;
	private double longitude_from;

//	private String time;
	private Time time;
	private Date date;
	private int occurrence_type;

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

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public int getOccurrence_type() {
		return occurrence_type;
	}

	public void setOccurrence_type(int occurrence_type) {
		this.occurrence_type = occurrence_type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
