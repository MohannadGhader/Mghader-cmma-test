package com.path.ws.omni.vo.common;

public class ContactUsRequest extends OmniCommonRequest {

	private String email;
	private String msg;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
