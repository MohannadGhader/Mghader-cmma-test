package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.MachineVO;

public class ReturnListOfMachinesResponse extends ResponseBaseObject {

	private List<MachineVO> listOfMachines;

	public List<MachineVO> getListOfMachines() {
		return listOfMachines;
	}

	public void setListOfMachines(List<MachineVO> listOfMachines) {
		this.listOfMachines = listOfMachines;
	}

}
