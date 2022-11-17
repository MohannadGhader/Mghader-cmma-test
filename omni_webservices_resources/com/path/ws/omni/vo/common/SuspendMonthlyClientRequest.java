package com.path.ws.omni.vo.common;

public class SuspendMonthlyClientRequest {
	private int client_id;
	private int reservation_id;
	private int clients_group_id;
	private String clients_group_name;

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

	public int getClients_group_id() {
		return clients_group_id;
	}

	public void setClients_group_id(int clients_group_id) {
		this.clients_group_id = clients_group_id;
	}

	public String getClients_group_name() {
		return clients_group_name;
	}

	public void setClients_group_name(String clients_group_name) {
		this.clients_group_name = clients_group_name;
	}
}
