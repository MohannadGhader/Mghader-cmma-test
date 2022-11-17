package com.path.ws.omni.vo.common;

public class CreateGroupResponse extends ResponseBaseObject {
	private int group_template_id;
	private int clients_group_id;

	public int getGroup_template_id() {
		return group_template_id;
	}

	public void setGroup_template_id(int group_template_id) {
		this.group_template_id = group_template_id;
	}

	public int getClients_group_id() {
		return clients_group_id;
	}

	public void setClients_group_id(int clients_group_id) {
		this.clients_group_id = clients_group_id;
	}

}
