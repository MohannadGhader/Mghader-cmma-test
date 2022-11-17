package com.path.ws.omni.vo.common;

public class ReturnMachineInfoResponse extends ResponseBaseObject {

	private String plate_number;
	private String model;
	private int machine_status;

	public String getPlate_number() {
		return plate_number;
	}

	public void setPlate_number(String plate_number) {
		this.plate_number = plate_number;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getMachine_status() {
		return machine_status;
	}

	public void setMachine_status(int machine_status) {
		this.machine_status = machine_status;
	}

}
