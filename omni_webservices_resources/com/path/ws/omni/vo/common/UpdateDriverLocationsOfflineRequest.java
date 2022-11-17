package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.DriverCO;
import com.path.dbmaps.vo.Machine_driverCO;

public class UpdateDriverLocationsOfflineRequest extends OmniCommonRequest {

	List<DriverCO> listOfLocations;

	public List<DriverCO> getListOfLocations() {
		return listOfLocations;
	}

	public void setListOfLocations(List<DriverCO> listOfLocations) {
		this.listOfLocations = listOfLocations;
	}

}
