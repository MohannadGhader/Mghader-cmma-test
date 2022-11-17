package com.path.ws.omni.vo.common;

public class ReturnListReservationsDriverRequest extends OmniCommonRequest {

	private int driver_id;
	private String filterDate;

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public String getFilterDate() {
		return filterDate;
	}

	public void setFilterDate(String filterDate) {
		this.filterDate = filterDate;
	}

}
