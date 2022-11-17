package com.path.ws.omni.vo.common;

public class ChangeProfileDriverRequest extends OmniCommonRequest {

	private int driver_id;
	private String profile;

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

}
