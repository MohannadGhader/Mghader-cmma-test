package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.ReservationDetailsVO;

public class ReturnTlubtukReservationDetailsResponse extends ResponseBaseObject {

	List<ReservationDetailsVO> list;

	public List<ReservationDetailsVO> getList() {
		return list;
	}

	public void setList(List<ReservationDetailsVO> list) {
		this.list = list;
	}

}
