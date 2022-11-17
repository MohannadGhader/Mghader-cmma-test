package com.path.ws.omni.vo.common;

public class ChangeStringToDateRequest {
	private String dates;
	private int times_per_day;

	public int getTimes_per_day() {
		return times_per_day;
	}

	public void setTimes_per_day(int times_per_day) {
		this.times_per_day = times_per_day;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

}
