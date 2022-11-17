package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.ReservationSC;

public class ReturnListReservationsAdminResponse extends ResponseBaseObject {

	private List<ReservationSC> list;
	private int totalCost;
	private int totalDebit;

	public List<ReservationSC> getList() {
		return list;
	}

	public void setList(List<ReservationSC> list) {
		this.list = list;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public int getTotalDebit() {
		return totalDebit;
	}

	public void setTotalDebit(int totalDebit) {
		this.totalDebit = totalDebit;
	}

}
