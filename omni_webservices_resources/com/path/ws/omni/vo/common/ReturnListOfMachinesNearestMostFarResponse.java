package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.Machine_driverCO;

public class ReturnListOfMachinesNearestMostFarResponse extends ResponseBaseObject {

	private List<Machine_driverCO> list;

	public List<Machine_driverCO> getList() {
		return list;
	}

	public void setList(List<Machine_driverCO> list) {
		this.list = list;
	}

}
