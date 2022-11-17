package com.path.ws.omni.vo.common;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ChangeStringToDateResponse {
	private List<Date> listDate = new ArrayList<Date>();
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Date> getListDate() {
		return listDate;
	}

	public void setListDate(List<Date> listDate) {
		this.listDate = listDate;
	}
}
