package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.ParamLovSC;

public class ReturnListOfServicesResponse extends ResponseBaseObject {

	private List<ParamLovSC> list;

	public List<ParamLovSC> getList() {
		return list;
	}

	public void setList(List<ParamLovSC> list) {
		this.list = list;
	}

}
