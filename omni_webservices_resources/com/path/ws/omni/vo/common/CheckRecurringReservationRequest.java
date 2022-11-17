package com.path.ws.omni.vo.common;

import java.sql.Date;
import java.sql.Time;

public class CheckRecurringReservationRequest extends RequestBaseObject{
	private Date date;
	private	 Time time;
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

