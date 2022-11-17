package com.path.dao.common.impl;

import java.util.HashMap;
import java.util.List;

import com.path.dao.common.WebServiceCommonDAO;
import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.DAOException;
import com.path.vo.ws.CommonPwsMappingCO;
import com.path.vo.ws.CommonPwsReqRespLogCO;

public class WebServiceCommonDAOImpl extends BaseDAO implements WebServiceCommonDAO
{
	@Override
	public void logRequest(CommonPwsReqRespLogCO commonPwsRequestLogCO) throws DAOException {
		// TODO Auto-generated method stub
		getSqlMap().insert("webServiceMapper.insertLogRequest", commonPwsRequestLogCO);
	}
	@Override
	public void logResponse(CommonPwsReqRespLogCO commonPwsRequestLogCO) throws DAOException {
		// TODO Auto-generated method stub
		getSqlMap().insert("webServiceMapper.insertLogResponse", commonPwsRequestLogCO);
	}	
	
	/**
	 * @param mappingCO
	 * @return
	 * @throws DAOException
	 */
	public CommonPwsMappingCO returnMappingDef(CommonPwsMappingCO mappingCO) throws DAOException{
		return (CommonPwsMappingCO) getSqlMap().queryForObject("webServiceMapper.returnMappingDef", mappingCO);
	} 
	
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> returnPWSMappingParams(CommonPwsMappingCO mappingCO) throws DAOException{
		return getSqlMap().queryForList("webServiceMapper.returnPWSMappingParams", mappingCO);
	}
	
	
	
}
