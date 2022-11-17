package com.path.ws.omni.vo.common;

public class RenewMonthlyClientRequest extends OmniCommonRequest {
	private String username;
	private int client_id;
	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	private String mobile_number;
	private String dates_list;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getDates_list() {
		return dates_list;
	}

	public void setDates_list(String dates_list) {
		this.dates_list = dates_list;
	}

}
