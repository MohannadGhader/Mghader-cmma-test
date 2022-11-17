package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.PropertiesVO;

public class AuthenticateDriverResponse extends ResponseBaseObject {

	private String token;
	private String enableCrashlytics;
	private List<PropertiesVO> listProperties;
	private int availability;

	public String getEnableCrashlytics() {
		return enableCrashlytics;
	}

	public void setEnableCrashlytics(String enableCrashlytics) {
		this.enableCrashlytics = enableCrashlytics;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<PropertiesVO> getListProperties() {
		return listProperties;
	}

	public void setListProperties(List<PropertiesVO> listProperties) {
		this.listProperties = listProperties;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

}
