package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.InvitedContactVO;

public class returnListOfContactToInviteResponse extends ResponseBaseObject {

	private List<Invited_contactSC> listOfContactToInvite;

	public List<Invited_contactSC> getListOfContactToInvite() {
		return listOfContactToInvite;
	}

	public void setListOfContactToInvite(List<Invited_contactSC> listOfContactToInvite) {
		this.listOfContactToInvite = listOfContactToInvite;
	}

}
