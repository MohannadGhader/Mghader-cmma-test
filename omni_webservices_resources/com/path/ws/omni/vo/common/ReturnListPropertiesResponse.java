package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.PropertiesSC;

public class ReturnListPropertiesResponse extends ResponseBaseObject {

	private List<PropertiesSC> listProperties;

//	private HashMap<String, String> listOfProperties;

	public List<PropertiesSC> getListProperties() {
		return listProperties;
	}

	public void setListProperties(List<PropertiesSC> listProperties) {
		this.listProperties = listProperties;
	}


}
