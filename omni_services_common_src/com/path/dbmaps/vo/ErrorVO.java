package com.path.dbmaps.vo;

public class ErrorVO {

	private int error_id;
	private int language_id;
	private int error_code;
	private String error_desc;

	public int getError_id() {
		return error_id;
	}

	public void setError_id(int error_id) {
		this.error_id = error_id;
	}

	public int getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(int language_id) {
		this.language_id = language_id;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public String getError_desc() {
		return error_desc;
	}

	public void setError_desc(String error_desc) {
		this.error_desc = error_desc;
	}

}
