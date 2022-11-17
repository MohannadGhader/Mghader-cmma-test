package com.path.ws.omni.vo.common;

import com.path.lib.vo.BaseVO;

public class Invited_contactSC extends BaseVO {

	private String mobile_number;
	/*
	 * flag to determine if the mobile number is already invited or no , 0 invited ,
	 * 1 not invited 2 has account
	 * 
	 */
	private int mobile_number_invitation_status;

	/*
	 * flag to determine how mony time the mobile number was invited
	 */
	private int count;

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public int getMobile_number_invitation_status() {
		return mobile_number_invitation_status;
	}

	public void setMobile_number_invitation_status(int mobile_number_invitation_status) {
		this.mobile_number_invitation_status = mobile_number_invitation_status;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
