package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.DriverCO;
import com.path.dbmaps.vo.Machine_driverCO;

public class ReturnAllDriversResponse extends ResponseBaseObject {

	List<Machine_driverCO> listAllDrivers;

	public List<Machine_driverCO> getListAllDrivers() {
		return listAllDrivers;
	}

	public void setListAllDrivers(List<Machine_driverCO> listAllDrivers) {
		this.listAllDrivers = listAllDrivers;
	}

}
