package com.path.ws.omni.vo.common;

import java.math.BigInteger;

public class AuthenticateClientRequest extends OmniCommonRequest {

	private int client_id;
	private String username;
	private String frontEndPublicKey;

	public String getFrontEndPublicKey() {
		return frontEndPublicKey;
	}

	public void setFrontEndPublicKey(String frontEndPublicKey) {
		this.frontEndPublicKey = frontEndPublicKey;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

}
