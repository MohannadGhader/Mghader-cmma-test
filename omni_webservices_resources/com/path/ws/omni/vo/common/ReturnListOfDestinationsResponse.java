package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.List_destinationsVO;

public class ReturnListOfDestinationsResponse extends ResponseBaseObject {

	private List<List_destinationsVO> list;

	public List<List_destinationsVO> getList() {
		return list;
	}

	public void setList(List<List_destinationsVO> list) {
		this.list = list;
	}
	
}
