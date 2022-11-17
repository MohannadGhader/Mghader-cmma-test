package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.Clients_groupVO;

public class ReturnListOfGroupsResponse extends ResponseBaseObject {

	private List<Clients_groupVO> listOfGroups;

	public List<Clients_groupVO> getListOfGroups() {
		return listOfGroups;
	}

	public void setListOfGroups(List<Clients_groupVO> listOfGroups) {
		this.listOfGroups = listOfGroups;
	}

}
