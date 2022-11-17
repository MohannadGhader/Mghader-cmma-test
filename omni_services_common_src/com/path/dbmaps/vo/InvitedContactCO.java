package com.path.dbmaps.vo;

import com.path.lib.vo.BaseVO;

public class InvitedContactCO extends BaseVO {

	public int client_id;
	public String listOfInvitedContact;
	public String listOfContact;

	public String getListOfContact() {
		return listOfContact;
	}

	public void setListOfContact(String listOfContact) {
		this.listOfContact = listOfContact;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public String getListOfInvitedContact() {
		return listOfInvitedContact;
	}

	public void setListOfInvitedContact(String listOfInvitedContact) {
		this.listOfInvitedContact = listOfInvitedContact;
	}

}
