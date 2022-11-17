package com.path.ws.omni.vo.common;

public class ChangeProfileRequest extends OmniCommonRequest {

	private int client_id;
	private String profile;

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

}
