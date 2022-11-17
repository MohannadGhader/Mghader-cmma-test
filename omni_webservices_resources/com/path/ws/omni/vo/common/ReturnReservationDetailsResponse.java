package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.ReservationCO;
import com.path.dbmaps.vo.ReservationListVO;

public class ReturnReservationDetailsResponse extends ResponseBaseObject {

	List<ReservationCO> listReservationsDetails;
	ReservationListVO reservationListVO;

	public List<ReservationCO> getListReservationsDetails() {
		return listReservationsDetails;
	}

	public void setListReservationsDetails(List<ReservationCO> listReservationsDetails) {
		this.listReservationsDetails = listReservationsDetails;
	}

	public ReservationListVO getReservationListVO() {
		return reservationListVO;
	}

	public void setReservationListVO(ReservationListVO reservationListVO) {
		this.reservationListVO = reservationListVO;
	}

	
}
