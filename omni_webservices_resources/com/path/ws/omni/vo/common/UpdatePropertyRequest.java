package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.PropertiesCO;
import com.path.dbmaps.vo.PropertiesVO;

public class UpdatePropertyRequest extends OmniCommonRequest {

	private String listProperties;

	public String getListProperties() {
		return listProperties;
	}

	public void setListProperties(String listProperties) {
		this.listProperties = listProperties;
	}


}
