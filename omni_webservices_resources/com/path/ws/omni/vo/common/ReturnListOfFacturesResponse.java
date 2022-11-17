package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.ReservationSC;

public class ReturnListOfFacturesResponse extends ResponseBaseObject {

	private List<ReservationSC> listOfFactureOfReservations;

	public List<ReservationSC> getListOfFactureOfReservations() {
		return listOfFactureOfReservations;
	}

	public void setListOfFactureOfReservations(List<ReservationSC> listOfFactureOfReservations) {
		this.listOfFactureOfReservations = listOfFactureOfReservations;
	}

}
