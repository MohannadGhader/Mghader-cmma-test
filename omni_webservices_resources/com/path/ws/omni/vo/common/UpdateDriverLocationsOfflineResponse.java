package com.path.ws.omni.vo.common;

import java.util.List;

public class UpdateDriverLocationsOfflineResponse extends ResponseBaseObject {

	private List<Integer> listOfNotInsertedFrLocation_id;
	private List<Integer> listOfInsertedFrLocation_id;

	public List<Integer> getListOfNotInsertedFrLocation_id() {
		return listOfNotInsertedFrLocation_id;
	}

	public void setListOfNotInsertedFrLocation_id(List<Integer> listOfNotInsertedFrLocation_id) {
		this.listOfNotInsertedFrLocation_id = listOfNotInsertedFrLocation_id;
	}

	public List<Integer> getListOfInsertedFrLocation_id() {
		return listOfInsertedFrLocation_id;
	}

	public void setListOfInsertedFrLocation_id(List<Integer> listOfInsertedFrLocation_id) {
		this.listOfInsertedFrLocation_id = listOfInsertedFrLocation_id;
	}

}
