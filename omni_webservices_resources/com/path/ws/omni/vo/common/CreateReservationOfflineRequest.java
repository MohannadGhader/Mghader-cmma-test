package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.ReservationCO;

public class CreateReservationOfflineRequest extends OmniCommonRequest {

	private List<ReservationCO> listOfflineReservation;

	public List<ReservationCO> getListOfflineReservation() {
		return listOfflineReservation;
	}

	public void setListOfflineReservation(List<ReservationCO> listOfflineReservation) {
		this.listOfflineReservation = listOfflineReservation;
	}

}
