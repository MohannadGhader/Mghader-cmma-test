package com.path.dbmaps.vo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;

public class SimVO {

	/**
	 * This field corresponds to the database column sim.SIM_ID
	 */
	private int sim_id;
	/**
	 * This field corresponds to the database column sim.SIM_SERIAL
	 */
	private BigInteger sim_serial;
	/**
	 * This field corresponds to the database column sim.MOBILE_NUMBER
	 */
	private String mobile_number;

	private int country_id;
	private String country_name;

	public int getSim_id() {
		return sim_id;
	}

	public void setSim_id(int sim_id) {
		this.sim_id = sim_id;
	}

	public BigInteger getSim_serial() {
		return sim_serial;
	}

	public void setSim_serial(BigInteger sim_serial) {
		this.sim_serial = sim_serial;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

}
