package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.Machine_mntCO;

public class ReturnMaintenanceResponse extends ResponseBaseObject {

	private List<Machine_mntCO> list;

	public List<Machine_mntCO> getList() {
		return list;
	}

	public void setList(List<Machine_mntCO> list) {
		this.list = list;
	}

}
