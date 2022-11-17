package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.ReservationListVO;
import com.path.dbmaps.vo.ReservationSC;
import com.path.dbmaps.vo.ReservationVO;

public class ReturnRecurringReservationListResponse extends ResponseBaseObject {

	List<ReservationSC> list;

	public List<ReservationSC> getList() {
		return list;
	}

	public void setList(List<ReservationSC> list) {
		this.list = list;
	}

}
