package com.path.ws.omni.vo.common;

public class AddMonthlyClientRequest extends OmniCommonRequest {

	private String username;
	private String mobile_number;
	private int created_by_client_id;
	private int created_by_driver_id;
	private int created_by_admin_id;
	private int clients_group_id;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public int getClients_group_id() {
		return clients_group_id;
	}

	public void setClients_group_id(int clients_group_id) {
		this.clients_group_id = clients_group_id;
	}

	public int getCreated_by_client_id() {
		return created_by_client_id;
	}

	public void setCreated_by_client_id(int created_by_client_id) {
		this.created_by_client_id = created_by_client_id;
	}

	public int getCreated_by_driver_id() {
		return created_by_driver_id;
	}

	public void setCreated_by_driver_id(int created_by_driver_id) {
		this.created_by_driver_id = created_by_driver_id;
	}

	public int getCreated_by_admin_id() {
		return created_by_admin_id;
	}

	public void setCreated_by_admin_id(int created_by_admin_id) {
		this.created_by_admin_id = created_by_admin_id;
	}

}
