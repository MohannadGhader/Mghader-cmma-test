package com.path.ws.omni.vo.common;

import java.math.BigInteger;

public class HandShakeRequest extends OmniCommonRequest {

	private String frontEndPublicKey;

	public String getFrontEndPublicKey() {
		return frontEndPublicKey;
	}

	public void setFrontEndPublicKey(String frontEndPublicKey) {
		this.frontEndPublicKey = frontEndPublicKey;
	}

}
