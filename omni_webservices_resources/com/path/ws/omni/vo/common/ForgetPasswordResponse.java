package com.path.ws.omni.vo.common;

public class ForgetPasswordResponse extends ResponseBaseObject{

	private String mask;
	private String mobile_number;
	private String otp;

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

}
