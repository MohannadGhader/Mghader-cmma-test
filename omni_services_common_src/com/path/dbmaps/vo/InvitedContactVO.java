package com.path.dbmaps.vo;

import java.util.Date;

import com.path.lib.vo.BaseVO;

public class InvitedContactVO extends BaseVO {

	private int invited_contact_id;
	private int client_id;
	private String inivited_mobile_number;
	private Date invited_date;

	public int getInvited_contact_id() {
		return invited_contact_id;
	}

	public void setInvited_contact_id(int invited_contact_id) {
		this.invited_contact_id = invited_contact_id;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public String getInivited_mobile_number() {
		return inivited_mobile_number;
	}

	public void setInivited_mobile_number(String inivited_mobile_number) {
		this.inivited_mobile_number = inivited_mobile_number;
	}

	public Date getInvited_date() {
		return invited_date;
	}

	public void setInvited_date(Date invited_date) {
		this.invited_date = invited_date;
	}

}
