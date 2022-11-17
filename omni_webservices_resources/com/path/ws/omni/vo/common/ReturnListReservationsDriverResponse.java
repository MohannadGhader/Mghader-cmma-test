package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.ReservationSC;

public class ReturnListReservationsDriverResponse extends ResponseBaseObject {

	private List<ReservationSC> list;

	public List<ReservationSC> getList() {
		return list;
	}

	public void setList(List<ReservationSC> list) {
		this.list = list;
	}

}
