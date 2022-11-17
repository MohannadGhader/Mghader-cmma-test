package com.path.dbmaps.vo;

import com.path.lib.vo.BaseVO;

public class NotificationVO extends BaseVO {

	private int notification_id;
	private String notification_code;
	private String notification_desc;

	public int getNotification_id() {
		return notification_id;
	}

	public void setNotification_id(int notification_id) {
		this.notification_id = notification_id;
	}

	public String getNotification_code() {
		return notification_code;
	}

	public void setNotification_code(String notification_code) {
		this.notification_code = notification_code;
	}

	public String getNotification_desc() {
		return notification_desc;
	}

	public void setNotification_desc(String notification_desc) {
		this.notification_desc = notification_desc;
	}

}
