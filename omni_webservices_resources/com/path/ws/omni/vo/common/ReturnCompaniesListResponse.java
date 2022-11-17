package com.path.ws.omni.vo.common;

import java.util.List;

import com.path.dbmaps.vo.CompaniesVO;

public class ReturnCompaniesListResponse extends ResponseBaseObject {

	private List<CompaniesVO> list;

	public List<CompaniesVO> getList() {
		return list;
	}

	public void setList(List<CompaniesVO> list) {
		this.list = list;
	}

}
