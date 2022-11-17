package com.path.ws.omni.vo.common;

public class SwitchingDriverAvailabilityRequest extends OmniCommonRequest {

	private int driver_id;
	
	/**
	 * 1 on, 0 off
	 */
	private int on_off;

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public int getOn_off() {
		return on_off;
	}

	public void setOn_off(int on_off) {
		this.on_off = on_off;
	}

}
