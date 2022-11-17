package com.path.dbmaps.vo;

import com.path.lib.vo.BaseVO;

public class ParamLovVO extends BaseVO {

	private int paramlov_id;
	private int lov_id;
	private String key_value;

	private int parent_id;
	private int broker_id;
	private String broker_name;
	private String broker_ar_name;
	private String broker_mobile_number;

	public int getParamlov_id() {
		return paramlov_id;
	}

	public void setParamlov_id(int paramlov_id) {
		this.paramlov_id = paramlov_id;
	}

	public int getLov_id() {
		return lov_id;
	}

	public void setLov_id(int lov_id) {
		this.lov_id = lov_id;
	}

	public String getKey_value() {
		return key_value;
	}

	public void setKey_value(String key_value) {
		this.key_value = key_value;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public int getBroker_id() {
		return broker_id;
	}

	public void setBroker_id(int broker_id) {
		this.broker_id = broker_id;
	}

	public String getBroker_name() {
		return broker_name;
	}

	public void setBroker_name(String broker_name) {
		this.broker_name = broker_name;
	}

	public String getBroker_ar_name() {
		return broker_ar_name;
	}

	public void setBroker_ar_name(String broker_ar_name) {
		this.broker_ar_name = broker_ar_name;
	}

	public String getBroker_mobile_number() {
		return broker_mobile_number;
	}

	public void setBroker_mobile_number(String broker_mobile_number) {
		this.broker_mobile_number = broker_mobile_number;
	}

}
