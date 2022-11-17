package com.path.ws.omni.vo.common;

public class ReturnCostOfDistanceResponse extends ResponseBaseObject {

	private int client_cost;
	private int driver_cost;
	private int customer_care_cost;

	public int getClient_cost() {
		return client_cost;
	}

	public void setClient_cost(int client_cost) {
		this.client_cost = client_cost;
	}

	public int getDriver_cost() {
		return driver_cost;
	}

	public void setDriver_cost(int driver_cost) {
		this.driver_cost = driver_cost;
	}

	public int getCustomer_care_cost() {
		return customer_care_cost;
	}

	public void setCustomer_care_cost(int customer_care_cost) {
		this.customer_care_cost = customer_care_cost;
	}

}
