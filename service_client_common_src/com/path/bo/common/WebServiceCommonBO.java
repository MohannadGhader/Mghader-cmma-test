package com.path.bo.common;

import java.math.BigDecimal;
import java.util.HashMap;

import com.path.lib.common.exception.BaseException;
//import com.path.vo.ws.WSGridFilterCommonSC;

public interface WebServiceCommonBO
{
    /**
     * method that logs webservice wrapper bo method call in the db 
     * @param hm parameters hashamap
     * @throws BaseException
     */
    void logRequest(HashMap<String, Object> hm)throws BaseException;
    /**
     * method that logs webservice wrapper bo method response in the db
     * @param hm response hashmap
     * @throws BaseException
     */
    void logResponse(HashMap<String, Object> hm)throws BaseException;
    

    /**
     * This method will execute a BO method mapped to a web service operation
     * using the web service name and operation
     * 
     * @param wsName
     * @param operationName
     * @param inputMap
     * @return
     * @throws BaseException
     */
    public HashMap<String, Object> executeWebServiceBoMethod(String wsName, String operationName,
			HashMap<String, Object> inputMap) throws BaseException;
    
    /**
     * this method executes a bo method remotely needed not to include the interface of the bean
     * @param boServiceName bo service
     * @param boMethodName function to be called
     * @param inputMap parameters hashamp
     * @return
     * @throws BaseException
     */
    public HashMap<String, Object> executeBoMethod(String boServiceName, String boMethodName,
    		HashMap<String, Object> inputMap) throws BaseException;
	
   
	/**
	 * This method will execute a BO/rest/soap call based on MapId
	 * 
	 * @param mapId
	 * @param mappingIdsMap
	 * @return
	 * @throws BaseException
	 */
	public HashMap<String, Object> callPWS(BigDecimal mapId, HashMap<String, Object> mappingIdsMap) throws BaseException;
	
	public HashMap<String, Object> callRestWebService(BigDecimal mapId, HashMap<String, Object> arguments)
		    throws BaseException;
}
