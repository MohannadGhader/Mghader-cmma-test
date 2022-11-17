package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.TemporaryReservationVO;

public class CheckTempRecurringReservationResponse extends ResponseBaseObject {

	List<TemporaryReservationVO> list ;

	public List<TemporaryReservationVO> getList() {
		return list;
	}

	public void setList(List<TemporaryReservationVO> list) {
		this.list = list;
	}
	
	
}
