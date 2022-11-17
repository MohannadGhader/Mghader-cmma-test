package com.path.ws.omni.vo.common;

import java.util.ArrayList;
import java.util.List;

public class CreateReservationOfflineResponse extends ResponseBaseObject {

	List<Integer> listOfInsertedFrReservation_id;
	List<Integer> listOfNotInsertedFrReservation_id;

	public List<Integer> getListOfInsertedFrReservation_id() {
		return listOfInsertedFrReservation_id;
	}

	public void setListOfInsertedFrReservation_id(List<Integer> listOfInsertedFrReservation_id) {
		this.listOfInsertedFrReservation_id = listOfInsertedFrReservation_id;
	}

	public List<Integer> getListOfNotInsertedFrReservation_id() {
		return listOfNotInsertedFrReservation_id;
	}

	public void setListOfNotInsertedFrReservation_id(List<Integer> listOfNotInsertedFrReservation_id) {
		this.listOfNotInsertedFrReservation_id = listOfNotInsertedFrReservation_id;
	}

}
