package com.path.ws.omni.vo.common;

import java.util.List;
import com.path.dbmaps.vo.ClientCO;

public class ReturnMonthlyClientDataResponse extends ResponseBaseObject {
	private List<ClientCO> listClientCO;

	public List<ClientCO> getListClientCO() {
		return listClientCO;
	}

	public void setListClientCO(List<ClientCO> listClientCO) {
		this.listClientCO = listClientCO;
	}

}
