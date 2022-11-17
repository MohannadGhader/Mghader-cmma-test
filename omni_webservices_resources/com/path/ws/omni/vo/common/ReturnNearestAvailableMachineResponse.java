package com.path.ws.omni.vo.common;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.path.dbmaps.vo.Machine_driverCO;

public class ReturnNearestAvailableMachineResponse extends ResponseBaseObject {

	private List<Machine_driverCO> listNotAvailable;
	private List<Machine_driverCO> listAvailable;

	public List<Machine_driverCO> getListNotAvailable() {
		return listNotAvailable;
	}

	public void setListNotAvailable(List<Machine_driverCO> listNotAvailable) {
		this.listNotAvailable = listNotAvailable;
	}

	public List<Machine_driverCO> getListAvailable() {
		return listAvailable;
	}

	public void setListAvailable(List<Machine_driverCO> listAvailable) {
		this.listAvailable = listAvailable;
	}

}
