package com.path.ws.omni.vo.common;

import java.util.List;

public class UpdateDriverLocationResponse extends ResponseBaseObject {

	private int notInsertedFrLocation_id;
	private int insertedFrLocation_id;

	public int getNotInsertedFrLocation_id() {
		return notInsertedFrLocation_id;
	}

	public void setNotInsertedFrLocation_id(int notInsertedFrLocation_id) {
		this.notInsertedFrLocation_id = notInsertedFrLocation_id;
	}

	public int getInsertedFrLocation_id() {
		return insertedFrLocation_id;
	}

	public void setInsertedFrLocation_id(int insertedFrLocation_id) {
		this.insertedFrLocation_id = insertedFrLocation_id;
	}

}
