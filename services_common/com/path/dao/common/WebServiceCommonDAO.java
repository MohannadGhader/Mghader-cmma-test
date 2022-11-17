package com.path.dao.common;

import com.path.lib.common.exception.DAOException;
import com.path.vo.ws.CommonPwsReqRespLogCO;

public interface WebServiceCommonDAO
{
	void logRequest(CommonPwsReqRespLogCO commonPwsRequestLogCO)throws DAOException;
	void logResponse(CommonPwsReqRespLogCO commonPwsRequestLogCO)throws DAOException;
	
	

	
}
