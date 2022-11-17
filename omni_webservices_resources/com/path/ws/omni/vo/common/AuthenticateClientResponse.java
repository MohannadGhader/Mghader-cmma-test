package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.PropertiesVO;

public class AuthenticateClientResponse extends ResponseBaseObject {

	private String token;
	private String companyMobile;
	private String enableCrashlytics;
	private List<PropertiesVO> listProperties;
	private int free_trip;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCompanyMobile() {
		return companyMobile;
	}

	public void setCompanyMobile(String companyMobile) {
		this.companyMobile = companyMobile;
	}

	public String getEnableCrashlytics() {
		return enableCrashlytics;
	}

	public void setEnableCrashlytics(String enableCrashlytics) {
		this.enableCrashlytics = enableCrashlytics;
	}

	public List<PropertiesVO> getListProperties() {
		return listProperties;
	}

	public void setListProperties(List<PropertiesVO> listProperties) {
		this.listProperties = listProperties;
	}

	public int getFree_trip() {
		return free_trip;
	}

	public void setFree_trip(int free_trip) {
		this.free_trip = free_trip;
	}

}
