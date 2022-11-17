package com.path.dbmaps.vo;

import com.path.lib.vo.BaseVO;

public class PropertiesSC extends BaseVO {
	private String property_id;
	private String property_name;
	private String property_value;
	private String property_description;
	private int category;
	private String key_value;

	public String getProperty_id() {
		return property_id;
	}

	public void setProperty_id(String property_id) {
		this.property_id = property_id;
	}

	public String getProperty_name() {
		return property_name;
	}

	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}

	public String getProperty_value() {
		return property_value;
	}

	public void setProperty_value(String property_value) {
		this.property_value = property_value;
	}

	public String getProperty_description() {
		return property_description;
	}

	public void setProperty_description(String property_description) {
		this.property_description = property_description;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getKey_value() {
		return key_value;
	}

	public void setKey_value(String key_value) {
		this.key_value = key_value;
	}

}
