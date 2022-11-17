package com.path.dbmaps.vo;

import java.sql.Date;

import com.path.lib.vo.BaseVO;

public class MachineVO extends BaseVO {

	private int machine_id;
	private String plate_number;
	private String style;
	private Date ownership_date;
	private String owners_name;
	private int machine_status;

	public int getMachine_id() {
		return machine_id;
	}

	public void setMachine_id(int machine_id) {
		this.machine_id = machine_id;
	}

	public String getPlate_number() {
		return plate_number;
	}

	public void setPlate_number(String plate_number) {
		this.plate_number = plate_number;
	}

	public int getMachine_status() {
		return machine_status;
	}

	public void setMachine_status(int machine_status) {
		this.machine_status = machine_status;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Date getOwnership_date() {
		return ownership_date;
	}

	public void setOwnership_date(Date ownership_date) {
		this.ownership_date = ownership_date;
	}

	public String getOwners_name() {
		return owners_name;
	}

	public void setOwners_name(String owners_name) {
		this.owners_name = owners_name;
	}

}
