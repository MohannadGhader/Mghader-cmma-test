package com.path.dbmaps.vo;

import java.sql.Date;

public class UserOTP {

	/*
	 * this class is used in blockClientHashmap to dedicate if the client is blocked
	 * in this day or not
	 */

	private String date;
	/*
	 * 0 available 1 blocked
	 */
	private int status;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
