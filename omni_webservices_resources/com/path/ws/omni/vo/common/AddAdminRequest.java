package com.path.ws.omni.vo.common;

public class AddAdminRequest extends OmniCommonRequest {

	private String name;
	private String mobile_number;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

}
