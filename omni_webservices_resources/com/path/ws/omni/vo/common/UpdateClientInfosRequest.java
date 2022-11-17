package com.path.ws.omni.vo.common;

public class UpdateClientInfosRequest extends OmniCommonRequest {

	private int client_id;
	private String name;
	
	
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
