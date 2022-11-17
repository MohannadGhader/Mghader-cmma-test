package com.path.ws.omni.vo.common;

import java.sql.Date;

public class ReturnListReservationsAdminRequest extends OmniCommonRequest {

	private Date date;
	private Date date_from;
	private Date date_to;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate_from() {
		return date_from;
	}

	public void setDate_from(Date date_from) {
		this.date_from = date_from;
	}

	public Date getDate_to() {
		return date_to;
	}

	public void setDate_to(Date date_to) {
		this.date_to = date_to;
	}

}
