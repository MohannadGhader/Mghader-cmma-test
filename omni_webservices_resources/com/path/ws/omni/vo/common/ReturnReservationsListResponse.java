package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.ReservationSC;


public class ReturnReservationsListResponse extends ResponseBaseObject {

	private List<ReservationSC> listOfReservations;

	public List<ReservationSC> getListOfReservations() {
		return listOfReservations;
	}

	public void setListOfReservations(List<ReservationSC> listOfReservations) {
		this.listOfReservations = listOfReservations;
	}

}
