package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.ClientVO;

public class ValidateOTPResponse extends ResponseBaseObject {

	/*
	 * m is the number of current attemps of otp
	 */
	private int m;
	/*
	 * n is the number of allowed attemps of otp
	 */
	private int n;

	private List<ClientVO> listOfMobileNumber;

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public List<ClientVO> getListOfMobileNumber() {
		return listOfMobileNumber;
	}

	public void setListOfMobileNumber(List<ClientVO> listOfMobileNumber) {
		this.listOfMobileNumber = listOfMobileNumber;
	}

	

}
