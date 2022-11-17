package com.path.ws.omni.vo.common;

public class returnListOfContactToInviteRequest extends OmniCommonRequest {

	private int client_id;
	private String listOfContact;

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public String getListOfContact() {
		return listOfContact;
	}

	public void setListOfContact(String listOfContact) {
		this.listOfContact = listOfContact;
	}

}
