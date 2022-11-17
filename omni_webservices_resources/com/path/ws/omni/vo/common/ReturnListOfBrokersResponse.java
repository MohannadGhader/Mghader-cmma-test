package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.DriverVO;
import com.path.dbmaps.vo.ParamLovVO;

public class ReturnListOfBrokersResponse extends ResponseBaseObject {

	private List<ParamLovVO> list;

	public List<ParamLovVO> getList() {
		return list;
	}

	public void setList(List<ParamLovVO> list) {
		this.list = list;
	}

}
