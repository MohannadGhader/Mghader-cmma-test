package com.path.bo.common.impl;

import java.math.BigDecimal;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.util.Assert;

import com.path.bo.common.CommonMethods;
import com.path.bo.common.ConstantsCommon;
import com.path.bo.common.WebServiceCommonBO;
import com.path.dao.common.WebServiceCommonDAO;
//import com.path.dbmaps.vo.COMMON_PWS_MAPPING_DETAILSVO;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BOException;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.NumberUtil;
import com.path.lib.common.util.PathPropertyUtil;
import com.path.lib.common.util.StringUtil;
import com.path.lib.remote.LocalServiceCaller;
import com.path.lib.remote.RmiServiceCaller;
import com.path.vo.ws.CommonPwsMappingCO;
import com.path.vo.ws.CommonPwsReqRespLogCO;
import com.rits.cloning.Cloner;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import net.sf.json.JSONObject;

/**
 * This class house common web service/rmi call
 */
public class WebServiceCommonBOImpl extends BaseBO implements WebServiceCommonBO
{
    /**
     * Hold reference for WebServiceCommonDAO
     */
    private WebServiceCommonDAO webServiceCommonDAO;
    

    public static final String GRID_LIST_SEPERATOR = "_";
    
    public static final String UNIQUE_MAP_SEPERATOR = "####"; 
	

    
    @Override
    public void logRequest(HashMap<String, Object> hmRequest) throws BaseException
    {
	Long timeStamp = Calendar.getInstance().getTimeInMillis();
	// this is internal parameter to set request content, in case filled
	// need to remove and set with values as inside wrapper parameter
	hmRequest.remove("requestKernelDetails");
	String jsonData = prepareMapContent((new Cloner()).deepClone(hmRequest));

	// clone hashmap where we set needed parameters for logging without
	// changing the one already sent since it will be used in the response
	HashMap<String, Object> hm = (new Cloner()).deepClone(hmRequest);

	// set all properties from common objects in a hashmap as flat then add
	// them to main hashmap
	HashMap<String, Object> commonObj = new HashMap<>();

	if(hmRequest.get("requesterContext") != null)
	{
	    hm.putAll((HashMap<String, Object>) hmRequest.get("requesterContext"));
	}
	if(hmRequest.get("vendorContext") != null)
	{
	    hm.putAll((HashMap<String, Object>) hmRequest.get("vendorContext"));
	}
	if(hmRequest.get("requestContext") != null)
	{
	    hm.putAll((HashMap<String, Object>) hmRequest.get("requestContext"));
	}
	if(hmRequest.get("serviceContext") != null)
	{
	    hm.putAll((HashMap<String, Object>) hmRequest.get("serviceContext"));
	}
	if(hmRequest.get("account") != null)
	{
	    hm.putAll((HashMap<String, Object>) hmRequest.get("account"));
	    hm.put("cifNo", hm.get("cif"));
	}

//		hm.putAll(commonObj);
	// overriding content of the request in case coming from imco (xml soap
	// request) and replace it with hashmap values
	hm.put("requestKernelDetails", jsonData);
	// in case not from pws imco or in case requestID tag is not sent
	// specifically we will set it as default with timestamp
	if(hm.get("requestID") == null)
	{
	    HashMap<String, Object> hm1 = new HashMap<>();
	    hm1.put("coreRequestTimeStamp", new Date(timeStamp));
	    hm1.put("requestID", UUID.randomUUID().toString());
	    hmRequest.put("requestContext", hm1);
	    hm.putAll((HashMap<String, Object>) hmRequest.get("requestContext"));
//			hm.put("requestID", "PWS"+timeStamp);
//			hmRequest.put("requestID", "PWS"+timeStamp);
	}
//		hm.put("coreRequestTimeStamp", new Date(timeStamp));
//		hmRequest.put("coreRequestTimeStamp", new Date(timeStamp));
	hm = maskCardNo(hm);
	CommonPwsReqRespLogCO commonPwsReqRespLogCO = new CommonPwsReqRespLogCO();
	commonPwsReqRespLogCO = (CommonPwsReqRespLogCO) PathPropertyUtil.convertToObject(hm,
		CommonPwsReqRespLogCO.class);
	webServiceCommonDAO.logRequest(commonPwsReqRespLogCO);
    }

    @Override
    public void logResponse(HashMap<String, Object> hmResp) throws BaseException
    {
	Long timeStamp = Calendar.getInstance().getTimeInMillis();
	// this is internal parameter to set request content, in case filled
	// need to remove and set with new values
	hmResp.remove("requestKernelDetails");
	String jsonData = prepareMapContent((new Cloner()).deepClone(hmResp));

	HashMap<String, Object> hm = (new Cloner()).deepClone(hmResp);

	// set all properties from common objects in a hashmap as flat then add
	// put them in main hashmap
	HashMap<String, Object> commonObj = new HashMap<>();
	if(hmResp.get("serviceContextResp") == null && hmResp.get("serviceContext") != null)
	{
	    hmResp.put("serviceContextResp", hmResp.get("serviceContext"));
	}
	if(hmResp.get("serviceContextResp") != null)
	{
	    hm.putAll((HashMap<String, Object>) hmResp.get("serviceContextResp"));
	}

	if(hmResp.get("responseServiceContext") == null && hmResp.get("serviceContext") != null)
	{
	    hmResp.put("responseServiceContext", hmResp.get("serviceContext"));
	}
	if(hmResp.get("responseServiceContext") != null)
	{
	    hm.putAll((HashMap<String, Object>) hmResp.get("responseServiceContext"));
	}

	if(hmResp.get("requesterContext") != null)
	{
	    hm.putAll((HashMap<String, Object>) hmResp.get("requesterContext"));
	}
	if(hmResp.get("serviceResponse") != null)
	{
	    hm.putAll((HashMap<String, Object>) hmResp.get("serviceResponse"));
	}
	if(hmResp.get("account") != null)
	{
	    hm.putAll((HashMap<String, Object>) hmResp.get("account"));
	    hm.put("cifNo", hm.get("cif"));
	}
	if(hmResp.get("requestContext") != null)
	 {
	    hm.putAll((HashMap<String, Object>) hmResp.get("requestContext"));
//			hm.putAll(commonObj);
//			if(hm.get("responseContext")== null || (hm.get("responseID") == null))
//			{
	}

	// setting responseContext object as per request values
	HashMap<String, Object> hm1 = new HashMap<>();
	hm1.put("coreResponseTimeStamp", new Date(timeStamp));
	hm1.put("responseID", hm.get("requestID"));
	hm1.put("requestID", hm.get("requestID"));
	hm1.put("coreRequestTimeStamp", hm.get("coreRequestTimeStamp"));
	hmResp.put("responseContext", hm1);
	hm.putAll((HashMap<String, Object>) hmResp.get("responseContext"));
//			}
	// overriding content of the response in case coming from imco (xml soap
	// request) and replace it with hashmap values
	hm.put("requestKernelDetails", jsonData);
	hm = maskCardNo(hm);
	CommonPwsReqRespLogCO commonPwsReqRespLogCO = new CommonPwsReqRespLogCO();
	commonPwsReqRespLogCO = (CommonPwsReqRespLogCO) PathPropertyUtil.convertToObject(hm,
		CommonPwsReqRespLogCO.class);
	webServiceCommonDAO.logResponse(commonPwsReqRespLogCO);

    }
    /**
     * @description function to convert nested maps and lists to dot seperated
     * @param map
     * @param key
     * @param map1
     * @param listParentKey
     */
    public void processMap(Map<String,Object> map,String key,Map<String,Object> map1,Stack<String> listParentKey)
    {
    	Object mapValue = null;
    	Map<String,Object> tempMap = new HashMap<String,Object>();
    	List<Object> alst = new ArrayList<Object>();
		Set<String> keySet = map.keySet();
        String newKey = null;
		int kk = 0;
		if(null == listParentKey)
		{
			listParentKey = new Stack();
		}
		try{
    	if(null != map)
    	{
    		for(String k : keySet)
    		{
    			kk++;
    			mapValue = map.get(k);
        		if(mapValue instanceof Map<?,?>)
        		{
        			listParentKey.push(k);
        			key = listParentKey.toString().replace(",", ".").replace("[", "").replace("]", "").replace(" ","");
        			this.processMap((Map)mapValue,key.replace("null.", ""),map1,listParentKey);
        			listParentKey.pop();
        		}
        		else if(mapValue instanceof List<?>)
        		{
        			//list of strings,numbers.. not list of co/vo put  
        			if( ((List)mapValue).size()>0 && !(((List)mapValue).get(0) instanceof Map))
        			{
            			map1.put(k, (List)mapValue);
        			}
        			else{
        				int i=0;
        				listParentKey.push(k);
        				List<?> lst = ((List) mapValue);
        				tempMap = map1;
        				newKey = listParentKey.toString().replace(",", ".").replace("[", "").replace("]", "").replace(" ","");
        				for(Object obj : lst)
        				{
        					i++;
        					map1 = null;
        					map1 = new HashMap<String,Object>();
        					this.processMap((Map)obj,newKey,map1,listParentKey);
        					alst.add(map1);
        				}
        				map1 = null;
        				map1 = tempMap;
        				map1.put(k, alst);
        				alst = new ArrayList<>();
        				listParentKey.pop();
        			}
        		}
        		else if(!listParentKey.isEmpty()){        			
        			newKey = listParentKey.toString().replace(",", ".").replace("[", "").replace("]", "").replace(" ","")+"."+k;
        			map1.put(newKey, mapValue);
        		}
        		else{
        			newKey = k;
        			map1.put(newKey, mapValue);
        		}
    		}
    	}
		}
		catch(Exception e)
		{
			listParentKey.empty();
		}
    }
    
    /**
     * 
     * @param in
     * @param inRules
     * @return
     * @throws DAOException 
     * @throws Exceptino 
     */
//    public Map<String, String> updateRules(List<COMMON_PWS_MAPPING_DETAILSVO> lstCommonPwsMappingDetails,Map<String, String> rules) throws DAOException 
//    {
//    	Map<String,Object> uniqueParam = new HashMap<String,Object>();
//    	String[] x = null;
//    	String[] y = null;
//
//    	String mapAsString = this.mapToString(rules);
//    	for(COMMON_PWS_MAPPING_DETAILSVO o : lstCommonPwsMappingDetails)
//    	{
//        	mapAsString = mapAsString.replaceAll(GRID_LIST_SEPERATOR+o.getPARAM_NAME()+GRID_LIST_SEPERATOR+"1", "");
//    	}
//    	rules= null;
//    	if(null != mapAsString && !"{}".equals(mapAsString))
//    	{
//        	return (Map<String, String>) this.stringToMap(mapAsString);
//    	}
//    	else{
//    		return new HashMap<String,String>();
//    	}
//    }

    private static final String valueSep = "!~!";
    private static final String keyValueSep = "~!~";
	public Map<String, ?> stringToMap(String mapAsString) 
	{
		mapAsString = mapAsString.substring(1,mapAsString.length()-1);
		Map<String, String> map = Arrays.stream(mapAsString.split(valueSep)).map(entry -> entry.split(keyValueSep)).collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));
		return map;
	}
	
	public String mapToString(Map<?, ?> map) 
	{
	    String mapAsString = map.keySet().stream()
	      .map(key -> key + keyValueSep + map.get(key))
	      .collect(Collectors.joining(valueSep+" ", "{", "}"));
	    return mapAsString;
	}
    /**
     * This method will execute a BO/rest/soap call based on MapId
     * 
     * @param mapId
     * @param mappingIdsMap
     * @return
     * @throws BaseException
     */
	@SuppressWarnings({ "unchecked", "incomplete-switch" })
    public HashMap<String, Object> callPWS(BigDecimal mapId, HashMap<String, Object> mapInputArgs)
                  throws BaseException {
		    return mapInputArgs;
		
           /*Map<String, Object> map = new HashMap<String,Object>();
           Map<String,Object> map1 = new HashMap<String,Object>();
           List<String> inList = new ArrayList<String>();
    	   List<String> outList = new ArrayList<String>();
    	
           this.processMap(mapInputArgs,null,map1,null); 
           // get the mapping details from DB
           CommonPwsMappingCO mappingCO = new CommonPwsMappingCO(); 
           mapInputArgs = null;
           mapInputArgs = (HashMap<String, Object>) map1;
           HashMap<String, Object> result = new HashMap<String, Object>();
           IM_IMAL_APIVO apiVO = null;
           COMMON_PWS_MAPPINGVO mappingVO = new COMMON_PWS_MAPPINGVO();
           mappingVO.setMAPPING_ID(mapId);
           mappingVO = (COMMON_PWS_MAPPINGVO)genericDAO.selectByPK(mappingVO);
           List<COMMON_PWS_MAPPING_DETAILSVO> lstCommonPwsMappingDetails = null;
           mappingCO.setMappingVO(mappingVO);
           lstCommonPwsMappingDetails = webServiceCommonDAO.loadCommonPWSMappingDetailsList(mappingCO);
           mappingCO.setMappingVO(mappingVO);
           if(null == mappingVO.getAPI_CODE())
           {
                  mappingCO.getApiDefinitionVO().setSERVICE_TYPE("M");
           }
           else{
                  mappingCO = webServiceCommonDAO.returnMappingDef(mappingCO); 
           }
           // get all mapping/arguemnt
           List<HashMap<String, Object>> inMapping = webServiceCommonDAO.returnPWSMappingParams(mappingCO);
           // filter in/out arguments
           HashMap<String, List<HashMap<String, Object>>> argumentByStatus = filterArgumentbyStatus(inMapping, inList,outList);
           
           *//**
            * inrules
            * key is: paramname concatenated with source_key concatenated with mapping_arg_value
				value is: paramname concatenated with destination concatenated with mapping_arg_value
            *//*
           HashMap<String, String> InRules = prepareMappingRule(argumentByStatus.get(WebServicesConstant.ARG_STATUS.IN.getCode()), false);
           
           *//**
            * outRules
            * key is: paramname concatenated with source_key concatenated with mapping_arg_value
				value is: paramname concatenated with destination concatenated with mapping_arg_value
            *//*
           HashMap<String, String> outRules = prepareMappingRule(argumentByStatus.get(WebServicesConstant.ARG_STATUS.OUT.getCode()), false);          
           
           //==same as inRules but also excluding list_ob and list_fi==========
           HashMap<String, String> NonListInRules = prepareNonListMappingRule(argumentByStatus.get(WebServicesConstant.ARG_STATUS.IN.getCode()), false);
           //==same as outRules but also excluding list_ob and list_fi
           HashMap<String, String> NonListOutRules = prepareNonListMappingRule(argumentByStatus.get(WebServicesConstant.ARG_STATUS.OUT.getCode()), false);          

         
           HashMap<String, Object> destination = new HashMap<String, Object>();
           HashMap<String, Object> resultMap = new HashMap<String, Object>();
           
           //contains names of the lists mapped as IN
           Map<String,String> inMapLst = this.returnListMap(argumentByStatus.get(WebServicesConstant.ARG_STATUS.IN.getCode()), "IN");
           //contains names of the lists mapped as OUT
           Map<String,String> outMapLst = this.returnListMap(argumentByStatus.get(WebServicesConstant.ARG_STATUS.OUT.getCode()), "OUT");
           
           
           *//**
           * Call Web Services
           *//*
           switch (WebServicesConstant.API_TYPE.getEnumByCode(mappingCO.getApiDefinitionVO().getSERVICE_TYPE())) {
           case RMI_BO_SERVICE:
                  *//**
                  * In Rmi case : 1- All sent arguments will be passed to the method
                  * taking into consideration the mapped keys
                  *//*
                  destination = prepareWsMappingArg(mapInputArgs, destination, InRules,NonListInRules, false, inList,outList,inMapLst,"IN");
                  result = callRmiWsService(mappingCO, destination);
                  map1 = new HashMap<String, Object>();
                  this.processMap(result,null,map1,null); 
                  result = null;
                  result = (HashMap<String, Object>) map1;
                  result.putAll(mapInputArgs);//to use input arguments in expressions inside response as well 
                  resultMap = prepareWsMappingArg(result, resultMap, outRules,NonListOutRules, false, inList,outList,outMapLst,"OUT");
                  break;
           case WEBSERVICE_REST:
                  // map argument
//                  destination = prepareWsMappingArg(mapInputArgs, destination, (HashMap<String,String>)InRules,NonListInRules, false, null,null,null,"IN");
        	   resultMap = callRestWebService(mapId, mapInputArgs);
//                  prepareWsMappingArg(result, resultMap, (HashMap<String,String>)outRules,NonListOutRules, false, null,null,null,"OUT");
                  break;
           case WEBSERVICE_SOAP:
                  // mapping will be done internally
                  // result = callSoapWebService(apiVO,destinationMap);
                  break;
           }
           return resultMap;*/
    }


	/**
     * Execute a Rest call
     * 
     * @param mappingCO
     * @param arguments
     * @throws BOException
     */
    @SuppressWarnings("unchecked")
    public HashMap<String, Object> callRestWebService(BigDecimal mapId, HashMap<String, Object> arguments)
	    throws BaseException
    {
	return arguments;

	/*String output = null;

	try
	{
		 CommonPwsMappingCO mappingCO = new CommonPwsMappingCO(); 
         COMMON_PWS_MAPPINGVO mappingVO = new COMMON_PWS_MAPPINGVO();
         mappingVO.setMAPPING_ID(mapId);
         mappingVO = (COMMON_PWS_MAPPINGVO)genericDAO.selectByPK(mappingVO);
         mappingCO.setMappingVO(mappingVO);
         mappingCO = webServiceCommonDAO.returnMappingDef(mappingCO); 

	    IM_IMAL_APIVO apiVO = mappingCO.getApiDefinitionVO();
	    if(apiVO.getWS_ENDPOINT() == null || apiVO.getWS_ENDPOINT().trim().isEmpty()
		    || apiVO.getWS_CONTENT_TYPE() == null || apiVO.getWS_CONTENT_TYPE().trim().isEmpty())
	    {
		throw new BOException("Config error : Endpoint and content type are mandatory");
	    }

	    // prepare the params
	    HashMap<String, Object> params = new HashMap<>();
	    params.put("Url", apiVO.getWS_ENDPOINT());
	    params.put("ContentType", apiVO.getWS_CONTENT_TYPE());
	    params.put("Accept", apiVO.getWS_ACCEPT_TYPE());
	    params.put("HttpMethod", "POST");

	    // append security info
	    IM_API_SECURITYVO apiSecurityVO = mappingCO.getApiSecurityVO();
	    params.put("AUTHENTICATION_TYPE", apiSecurityVO.getAUTHENTICATION_TYPE());
	    params.put("USER_NAME", apiSecurityVO.getUSER_NAME());
	    params.put("PASSWORD", apiSecurityVO.getPASSWORD());
	    params.put("TOKEN_REGISTRATION_URL", apiSecurityVO.getTOKEN_REGISTRATION_URL());
	    params.put("TOKEN_REGISTRATION_APP_PWD", apiSecurityVO.getTOKEN_REGISTRATION_APP_PWD());
	    params.put("TOKEN_REGISTRATION_APP_NAME", apiSecurityVO.getTOKEN_REGISTRATION_APP_NAME());
	    params.put("TOKEN_GENERATION_URL", apiSecurityVO.getTOKEN_GENERATION_URL());
	    params.put("TOKEN_AES_KEY", apiSecurityVO.getTOKEN_AES_KEY());
	    params.put("TOKEN_LIFETIME", apiSecurityVO.getTOKEN_LIFETIME());

	    // @todo until they fix it in the common
	    params.put("InputParameter", JSONObject.fromObject(arguments).toString());

	    // call the web service
	    output = WebServiceCaller.callRestWebService(params);

	    // @todo fix this convert to HashMap ( waiting for javateam )
	    if(WebServicesConstant.DATA_TYPE.XML.equals(apiVO.getWS_CONTENT_TYPE()))
	    {
		XStream xStream = new XStream();
		xStream.registerConverter(new MapEntryConverter());
		// xStream.alias("sendSMSResp", Map.class);
		return (HashMap<String, Object>) xStream.fromXML(output);
	    }

	}
	catch(Exception e)
	{
	    throw new BOException(e);

	}

	return (HashMap<String, Object>) CommonMethods.returnJsonObjectFromStr(HashMap.class, output);*/
    }

    /**
     * This method will execute an BO method mapped to a web service operation
     * using the web service name and operation
     * 
     * @param wsName
     * @param operationName
     * @param inputMap
     * @return
     * @throws BaseException
     */
    public HashMap<String, Object> executeWebServiceBoMethod(String wsName, String operationName,
	    HashMap<String, Object> inputMap) throws BaseException
    {
	// check needed arguments
	Assert.hasLength(wsName, "wsName can't be null");
	Assert.hasLength(operationName, "operationName can't be null");

	String boRef = WebServicesConstant.wsServiceBOMap.get(wsName);
	String operationKey = wsName.concat(".").concat(operationName);
	String boMethod = WebServicesConstant.wsOperationsMethodMap.get(operationKey);

	if(StringUtil.nullToEmpty(boRef).isEmpty() || StringUtil.nullToEmpty(boMethod).isEmpty())
	{
	    throw new BaseException("No matching BO found");
	}

	if(inputMap != null)
	{
	    inputMap.put("skipUsrAuth", "true"); //to be used for skipping user authentication in case of internal rmi call between imal modules 
	}
	// invoke Bo rmi method
	return (HashMap<String, Object>) LocalServiceCaller.executeLocalMethod(boRef, boMethod, inputMap,
		HashMap.class);
    }

    /**
     * Get DAO reference
     * 
     * @return
     */
    public WebServiceCommonDAO getWebServiceCommonDAO()
    {
	return webServiceCommonDAO;
    }

    /**
     * Set DAO reference
     * 
     * @param webServiceCommonDAO
     */
    public void setWebServiceCommonDAO(WebServiceCommonDAO webServiceCommonDAO)
    {
	this.webServiceCommonDAO = webServiceCommonDAO;
    }

    /**
     * This method will execute a remote BO method mapped to a web service
     * operation
     * 
     * @param mappingCO
     * @param destinationMap
     * @return
     * @throws BOException 
     */
//    private HashMap<String, Object> callRmiWsService(CommonPwsMappingCO mappingCO,
//    	    HashMap<String, Object> destinationMap) throws BOException
//        {
//
//    	String wsName = mappingCO.getMappingVO().getWS_NAME();
//    	String operName = mappingCO.getMappingVO().getOPER_NAME();
//    	HashMap<String, Object> result = new HashMap<String, Object>();
//
//    	// rmi
//    	String serviceUrl = null;
//    	String appName = mappingCO.getMappingVO().getSERVICE_APP_NAME();
//
//    	try
//    	{
//    	    serviceUrl = PathPropertyUtil.returnPathPropertyFromFile("PathDBLRemoting.properties","integration." + appName + ".serviceURL");
//
//    	    // call the ws through rmi
//    	    WebServiceCommonBO webServiceCommonBO = (WebServiceCommonBO) RmiServiceCaller.returnRmiInterface(serviceUrl, WebServiceCommonBO.class,"webServiceCommonBOService");
//    	   result = webServiceCommonBO.executeWebServiceBoMethod(wsName, operName, destinationMap);
//
//    	}
//    	catch(Exception exp)
//    	{
//    	    log.error(exp, "Error in callRmiWsService");
//    	    throw new BOException(exp);
//    	}
//    	return result;
//        }


    /**
     * This method will filter the argument based on ARG status ( IN / OUT /
     * INOUT )
     * 
     * @param argumentMapping
     * @return
     */
    private HashMap<String, List<HashMap<String, Object>>> filterArgumentbyStatus(
	    List<HashMap<String, Object>> argumentMapping,List<String> inList ,List<String> outList )
    {

	List<HashMap<String, Object>> inArguments = new ArrayList<HashMap<String, Object>>();
	List<HashMap<String, Object>> outArguments = new ArrayList<HashMap<String, Object>>();
	
	
	HashMap<String, List<HashMap<String, Object>>> arguments = new HashMap<String, List<HashMap<String, Object>>>();
	
	for(HashMap<String, Object> argument : argumentMapping)
	{
	    switch (WebServicesConstant.ARG_STATUS.getEnumByCode((String) argument.get("STATUS")))
	    {
		case IN:
		    inArguments.add(argument);
		    if("IS_LIST".equals(argument.get("MAPPING_ARG_TYPE"))||"IS_LIST_SI".equals(argument.get("MAPPING_ARG_TYPE")))
		    {
		    	inList.add((String) argument.get("PARAM_NAME"));
		    }
		    break;
		case INOUT:
		    inArguments.add(argument);
		    outArguments.add(argument);
		    break;
		case OUT:
		    outArguments.add(argument);
		    if("IS_LIST".equals(argument.get("MAPPING_ARG_TYPE"))||"IS_LIST_SI".equals(argument.get("MAPPING_ARG_TYPE")))
		    {
		    	outList.add((String) argument.get("PARAM_NAME"));
		    }
		    break;
	    }
	}

	arguments.put(WebServicesConstant.ARG_STATUS.IN.getCode(), inArguments);
	arguments.put(WebServicesConstant.ARG_STATUS.OUT.getCode(), outArguments);

	return arguments;
    }
    
   

    /**
     * This method will mask the card number in the request and the response log
     * 
     * @param hm
     * @return
     */
    private String prepareMapContent(HashMap<String, Object> hm)
    {
	hm = maskCardNo(hm);
	// setting json string representing the hashmap content as value in the
	// hashmap to be logged
	JSONObject jsonObj = JSONObject.fromObject(hm);
	// formatting the json to readable format
	String jsonData = jsonObj.toString(3);
	try
	{
	    String encodingType = StringUtil
		    .nullToEmpty(PathPropertyUtil.getPathRemotingProp("PathRemoting", "default.database.encoding"));
	    if(ConstantsCommon.ENCODING_TYPE_ASCII.equals(encodingType))
	    {
		jsonData = new String(jsonData.getBytes(encodingType), encodingType);
	    }
	}
	catch(Exception e)
	{
	    // TODO Auto-generated catch block
	    log.error(e, "Error in prepareMapContent when encoding");
	}
	return jsonData;
    }

    private HashMap<String, Object> maskCardNo(HashMap<String, Object> hm)
    {
	// masking card number in case available
	String cardNo = (String) hm.get("cardNo");
	if(!StringUtil.nullToEmpty(cardNo).isEmpty() && cardNo.length() > 10)
	{
	    String tempToReplace = cardNo.substring(6, cardNo.length() - 4);
	    tempToReplace = tempToReplace.replaceAll(".", "X");
	    cardNo = cardNo.substring(0, 6) + tempToReplace + cardNo.substring(cardNo.length() - 4);
	    hm.put("cardNo", cardNo);
	}
	return hm;
    }

    /**
     * Prepare the Mapping rule HashMap
     * 
     * @param mapsRule
     * @return
     */
    private HashMap<String, String> prepareMappingRule(List<HashMap<String, Object>> mapsRule, boolean inverseRules)
    {

       /**
       * [MAPPING_ID => , SOURCE_KEY => ,DESTINATION_KEY => ,ARG_NAME => ,
       * ARG_TYPE => ]
       */
       String sourceKey, destinationkey;
       String mappingArgValue = null;
       HashMap<String, String> rules = new HashMap<String, String>();
       HashMap<String, Object> argumentInfo = new HashMap<String, Object>();

       for(int i = 0; i < mapsRule.size(); i++)
       {
           argumentInfo = mapsRule.get(i);

           sourceKey = (String) argumentInfo.get("SOURCE_KEY");
           destinationkey = (String) argumentInfo.get("DESTINATION_KEY");
           
           mappingArgValue = StringUtil.nullEmptyToValue(argumentInfo.get("MAPPING_ARG_VALUE"), "");
           String mappingArgType = StringUtil.nullEmptyToValue(argumentInfo.get("MAPPING_ARG_TYPE"), "");
           // it mean : move the content under the same key
           if(destinationkey == null)
           {
              destinationkey = sourceKey;
           }
           int index = 0;
           // inverse key
           if(inverseRules)
           {
              index = destinationkey.indexOf(UNIQUE_MAP_SEPERATOR);
              if(mappingArgValue.length()>0)
              {
                    destinationkey = new StringBuilder(destinationkey).insert(index+4, "").append("").toString();
              }
              if(!"IS_LIST".equals(mappingArgType) && !"IS_WRAPPER".equals(mappingArgType))
              {
            	  rules.put(destinationkey,sourceKey);
              }
           }
           else
           {
              index = sourceKey.indexOf(UNIQUE_MAP_SEPERATOR);
              if(mappingArgValue.length()>0 && !"IS_MAPPING_EXPRESSION".equals(mappingArgValue))
                  {
            	  	sourceKey = sourceKey.replace(mappingArgValue, "'"+mappingArgValue+"'");
//                    sourceKey = new StringBuilder(sourceKey).insert(index+4, "'").append("'").toString();
                  }
              if(!"IS_LIST".equals(mappingArgType) && !"IS_WRAPPER".equals(mappingArgType))
              {
                  rules.put(sourceKey, destinationkey);
              }           
             }
       }
        return rules;
    }
    
    private HashMap<String, String> prepareNonListMappingRule(List<HashMap<String, Object>> mapsRule, boolean inverseRules)
    {

       /**
       * [MAPPING_ID => , SOURCE_KEY => ,DESTINATION_KEY => ,ARG_NAME => ,
       * ARG_TYPE => ]
       */
       String sourceKey, destinationkey;
       String mappingArgValue = null;
       HashMap<String, String> rules = new HashMap<String, String>();
       HashMap<String, Object> argumentInfo = new HashMap<String, Object>();

       for(int i = 0; i < mapsRule.size(); i++)
       {
           argumentInfo = mapsRule.get(i);

           sourceKey = (String) argumentInfo.get("SOURCE_KEY");
           destinationkey = (String) argumentInfo.get("DESTINATION_KEY");
           
           mappingArgValue = StringUtil.nullEmptyToValue(argumentInfo.get("MAPPING_ARG_VALUE"), "");
           String mappingArgType = StringUtil.nullEmptyToValue(argumentInfo.get("MAPPING_ARG_TYPE"), "");
           // it mean : move the content under the same key
           if(destinationkey == null)
           {
              destinationkey = sourceKey;
           }
           int index = 0;
           // inverse key
           if(inverseRules)
           {
              index = destinationkey.indexOf(UNIQUE_MAP_SEPERATOR);
              if(mappingArgValue.length()>0)
              {
                    destinationkey = new StringBuilder(destinationkey).insert(index+4, "").append("").toString();
              }
              if(!"IS_LIST".equals(mappingArgType) && !"IS_WRAPPER".equals(mappingArgType) && !"IS_LIST_OB".equals(mappingArgType) && !"IS_LIST_FI".equals(mappingArgType))
              {
            	  rules.put(destinationkey,sourceKey);
              }
           }
           else
           {
              index = sourceKey.indexOf(UNIQUE_MAP_SEPERATOR);
              if(mappingArgValue.length()>0 && !"IS_MAPPING_EXPRESSION".equals(mappingArgValue))
                  {
            	  	sourceKey = sourceKey.replace(mappingArgValue, "'"+mappingArgValue+"'");
//                    sourceKey = new StringBuilder(sourceKey).insert(index+4, "'").append("'").toString();
                  }
              if(!"IS_LIST".equals(mappingArgType) && !"IS_WRAPPER".equals(mappingArgType) && !"IS_LIST_OB".equals(mappingArgType) && !"IS_LIST_FI".equals(mappingArgType))
              {
                  rules.put(sourceKey, destinationkey);
              }           
            }
       }
        return rules;
    }

    
    public Map<String,String> returnListMap(List<HashMap<String, Object>> mapsRule,String type)
    {
    	String paramName;
    	String destinationKey;
    	String sourceKey;
    	Map<String,String> outLstMap = new HashMap<String,String>();
    	String mapKey = null;
    	String mapValue = null;
    	for(Map map : mapsRule)
    	{
    		if("IS_LIST".equals(map.get("MAPPING_ARG_TYPE")) || "IS_LIST_SI".equals(map.get("MAPPING_ARG_TYPE")))
    		{
    			paramName = map.get("PARAM_NAME").toString();
    			destinationKey = map.get("DESTINATION_KEY").toString();
    			sourceKey = map.get("SOURCE_KEY").toString();
    			if(destinationKey.indexOf(paramName ) > -1)
			{
			    destinationKey = destinationKey.substring(destinationKey.indexOf(paramName)+paramName.length(),destinationKey.length());
			}
    			//getting destination name as it is in mapping details table
    			destinationKey = destinationKey.replaceAll("####", "").replace("IS_MAPPING_EXPRESSION", "");
    			
    			//getting source as it is in the mapping details table
    			if(sourceKey.indexOf(paramName ) > -1)
			{
			    sourceKey = sourceKey.substring(sourceKey.indexOf(paramName)+paramName.length(),sourceKey.length());
			}
    			sourceKey = sourceKey.replaceAll("####", "").replace("IS_MAPPING_EXPRESSION", "");

    			if(null != destinationKey && destinationKey.length()>0)
    			{
    				mapKey = sourceKey.split("\\.")[sourceKey.split("\\.").length-1];
    				mapValue = destinationKey.split("\\.")[destinationKey.split("\\.").length-1];
    				outLstMap.put(mapKey, mapValue);    				
    			}
    			else
    			{
    				return outLstMap;
    			}
    		}
    	}
    	return outLstMap;
    }
    /**
     * This method will map the content of source to a new hashMap based on
     * mapping key rules
     * 
     * @param mappingDetailsList
     * @param mappingIdsMap
     * @return
     * @throws BaseException
     */
	@SuppressWarnings({ "unused", "unchecked" })
	private HashMap<String, Object> prepareWsMappingArg(HashMap<String, Object> inputArguments,
			HashMap<String, Object> destination, HashMap<String, String> rules,HashMap<String, String>NonListRules, boolean mergeAllInputArgs,List<String> inList,List<String>outList,Map<String,String> outListMap,String inout)throws BaseException {
		return this.prepareWsMappingArg(inputArguments,destination,rules,NonListRules,mergeAllInputArgs,null, inList, outList,outListMap,inout);
	}
	
	 /**
     * This method will map the content of source to a new hashMap based on
     * mapping key rules
     * 
     * @param mappingDetailsList
     * @param mappingIdsMap
     * @return
     * @throws BaseException
     */
	@SuppressWarnings({ "unused", "unchecked" })
	private HashMap<String, Object> prepareWsMappingArg(HashMap<String, Object> inputArguments,
			HashMap<String, Object> destination, HashMap<String, String> rules,HashMap<String,String> NonListRules, boolean mergeAllInputArgs,String type,List<String> inList,List<String> outList,Map<String,String> listMap,String inout)
			throws BaseException {

		// merge all sent arguments to the destination
		if (mergeAllInputArgs) {
			destination = (HashMap<String, Object>) CommonMethods.returnJsonObjectFromStr(HashMap.class,
					JSONObject.fromObject(inputArguments).toString());
		}
		List aList = new ArrayList<>();
		aList.add(inputArguments);
		String value = null;
		Object expressionValue = null;
		String key = null;
		Map<String, Object> mappedDestination = new HashMap<String, Object>();
		String entK = null;
		Map<String,Object> tempMap = new HashMap<>();
		String s = null;
		List<Object>l = new ArrayList<>();
		List aLst = null;
		List aList1 = new ArrayList<>();
		Map mapList = new HashMap<String,Object>();
		Map<String,Object> flatMap = new HashMap<String,Object>();
		String lstKey = null;
		Map tempLstMap = null;
		Map<String,Object> lstMap = null;
//		if("IN".equals(inout))
//		{
//			tempLstMap = listMap;
//		}
		for(Object map : aList)
		{
			for(Object obj : ((Map)map).keySet())
			{
				if((((Map)map).get(obj) instanceof List))
				{
					aLst = (List) ((Map)map).get(obj);
					lstKey = obj.toString();
					key = lstKey;
					if(null != listMap && listMap.size()>0)
					{
					    lstKey = listMap.get(lstKey);
					    if (null != lstKey)
					    {
							lstKey = lstKey.replaceAll("\\[", "").replaceAll("\\]", ""); //RELATED TO MAPPING OF VARIABLES(EXPRESSIONS) WITH SYNTAX [VAR]
							outList.add(lstKey);						
						    if(null != aLst)
						    {
							    for(Object obj1 : aLst)
							    {
							    	aList1 = new ArrayList<>();
							    	aList1.add(obj1); //send the list of variables as each item in the initial list 
							    	if(obj1 instanceof Map)
							    	{
							    		this.callExecuteExpression(obj1, destination, rules, mergeAllInputArgs, aList1,lstKey,tempLstMap,inList,outList);
							    		continue;							    		
							    	}
							    	else{ //in case list of string, numbers not list of co/vo
							    		lstMap = new HashMap<String,Object>();
							    		lstMap.put(lstKey, aLst);
							    		this.callExecuteExpression(lstMap, destination, rules, mergeAllInputArgs, aList1,lstKey,tempLstMap,inList,outList);
							    		break;
							    	}
							    }
						    }
					    }
					}
				}
//				else{
//					tempMap.put(obj.toString(), ((Map)map).get(obj));
//				}
			}
		}
//		aList1.add(tempMap);
//		this.callExecuteExpression(inputArguments, destination, rules, mergeAllInputArgs, aList1,null,listMap,inList,null);
		this.callExecuteExpression(inputArguments, destination, rules, mergeAllInputArgs, aList,null,listMap,inList,null);
		return destination;
	}

	/**
	 * @param inputArguments
	 * @param destination
	 * @param rules
	 * @param mergeAllInputArgs
	 * @param aList
	 */
	public HashMap<String, Object> callExecuteExpression(Object input, HashMap<String, Object> destination,
            HashMap<String, String> rules, boolean mergeAllInputArgs, List aList,String lstName,Map<String, String> lstMap,List<String> inList,List<String> outList) {
     Object expressionValue;
     String entK, isMappingExp;
     /**
     * [ {source:destination},{source:destination},{source:destination}] 
      */
     String mapKey = null;
     String mapValue = null;
     Map tempMap = new HashMap();

     
     HashMap<String, Object> inputArguments = (HashMap<String,Object>)input;
     if(null != lstName)
     {
    	 tempMap = (new Cloner()).deepClone(inputArguments);
     }
     else if(aList.size()>0)
     {
     	tempMap = (Map) aList.get(0);
     }
     
     for(Entry<String, String> entry : rules.entrySet())
     {
    	 	String[] keyArr = entry.getKey().split(UNIQUE_MAP_SEPERATOR);//param_name+source_key+mapping_arg_value
    	 	String[] valArr = entry.getValue().split(UNIQUE_MAP_SEPERATOR);//param_name+destination_key+mapping_arg_value
            mapKey = keyArr[0];//parameter name
            mapValue = valArr[1];          
            entK = keyArr[1];//src in case of IN dest in case of OUT will contain expression
            isMappingExp = keyArr.length > 2 ? keyArr[2] : "";
//            mapValue = (entry.getValue().split(UNIQUE_MAP_SEPERATOR))[(entry.getValue().split(UNIQUE_MAP_SEPERATOR)).length - 1];
//            entK = (entry.getKey().split(UNIQUE_MAP_SEPERATOR))[(entry.getKey().split(UNIQUE_MAP_SEPERATOR)).length - 1];//src in case of IN dest in case of OUT will contain expression
                        
            SimpleEntry<String, Object> nodeElement = getNodeElement(mapKey, inputArguments);
//            HashMap<String, Object> hm = new HashMap<String, Object>();
            try {  
                   expressionValue = null;
                   if(aList.size()>0 /*&& ((HashMap<String, Object>)aList.get(0)).get(entK) != null*/)
                   {
                         if("IS_MAPPING_EXPRESSION".equals(isMappingExp) )
                         {
                        	 expressionValue = tempMap.get(entK.replaceAll("\\[", "").replaceAll("\\]", ""));
                         }
                         else if(NumberUtil.isNumber(entK)) //not mapping expression as number
                         {
                                expressionValue = new BigDecimal(entK) ;
                         }
                         else if(entK.matches("^'[^']*'$")) //not mapping, expression as string
                         {
                        	 expressionValue = entK.replaceAll("'", ""); 
                         }
                         else //
                         {
                             expressionValue = tempMap.get(entK);
                             
                        	 if(null == expressionValue && !entK.trim().equals(mapValue) )//src different than destination means expression
	                         {
//                        		 expressionValue = tempMap.get(entK.replaceAll("\\[", "").replaceAll("\\]", ""));//to be replaced by checking on is_mapping_expression
//                        		 if(expressionValue == null)
//                        		 {
	                                try
	                                {
	                                    expressionValue = commonLibBO.executeExpression(entK, 0, aList);
	                                }
	                                catch(Exception e)
	                                {
	                                    expressionValue = null;
	                                }
//                        		 }
	                         }

                        	 if(expressionValue == null) //src = dest could be that no mapping was done only choosing the element as checked, possibly sending/receiving same property name
	                         {
	                                Iterator<String> it = tempMap.keySet().iterator();
	                                HashMap<String, Object> testHm = new HashMap<>();
	                                while(it.hasNext())
	                                {
	                                    String key = it.next();
	                                    if(key.contains(entK.concat(".")))//check if element 
	                                    {
	                                       String[] arr = key.split("\\.");
	                                       testHm.put(arr[1], tempMap.get(key));
	                                    }
	                                }
	                                if(testHm.size() > 0)
					{
					    expressionValue = testHm;
					}
	                         }
                         }
                         nodeElement.setValue(expressionValue);
                         if (mergeAllInputArgs) {
                                removeNode(destination, entK.replace("'", ""));
                         }
//                         if(null != expressionValue) //only include properties with non null values
//                         {
                        	 writeValue(destination, mapValue, expressionValue,lstName, lstMap, inList,outList);                       	 
//                         }
                   }
            }
         catch(Exception e)
         {
         }
     }
     return destination;
}


    /**
     * 
     * Remove node from nested hashMap
     * 
     * @param destination
     * @param key
     */
    private void removeNode(HashMap<String, Object> destination, String sourceKey)
    {
		// Source: S -> A.B.C
		String[] keys = sourceKey.split("\\.");
		String lastNodeKey = keys[keys.length - 1];
	
		Object nodeValue = destination;
		String nodeKey = null;

		// go in deep
		for(int i = 0; i < keys.length - 1; i++)
		{
		    // if hashmap
		    if(nodeValue instanceof HashMap)
		    {
			nodeValue = ((HashMap) nodeValue).get(keys[i]);
			continue;
		    }
	
		    // if value null
		    nodeValue = null;
		    break;
		}
	
		// currenlty we will handle only the hashMap case
		if(nodeValue != null && nodeValue instanceof HashMap)
		{
		    ((HashMap) nodeValue).remove(lastNodeKey);
		}
    }

    /**
     * This helper method will put the content of "Value" in the destination
     * under the given Key and create the nested needed level
     * 
     * @param destination
     * @param destinationkey
     * @param value
     */
    @SuppressWarnings("unchecked")
    private void writeValue(HashMap<String, Object> destination, String destinationkey, Object value,String lstName,Map<String, String> lstMapIn,List<String> inList,List<String> outList)
    {
	    //Destination: D -> A.B.C
		destinationkey = destinationkey.replaceAll("\\[", "").replaceAll("\\]", "");
		String[] keys = destinationkey.split("\\.");
		HashMap<String, Object> node = destination;
		boolean lstFound = false;
		boolean exists;
		List<HashMap<String, Object>> lst = null;
		// go in deep
		for (int i = 0; i < keys.length; i++)
		{
			String key = keys[i];
			// set value
			if (i == keys.length - 1) 
			{
				if (( keys.length == 1 && destination.get(key) == null) ||
						(node.get(key) == null && value != null))//when same key outside and nested
				{
					node.put(key, value);
				}
				continue;
			}

			// create node
			if (node.get(key) == null)
			 {
				// check if key is a list or not
				if (lstMapIn != null && lstMapIn.get(key) == null && !lstFound)
				{
				    node.put(key, new HashMap<String, Object>());
				}
				else if (lstMapIn != null && lstMapIn.get(key) != null) {
					lstFound = true;
					lst = new ArrayList<HashMap<String, Object>>();
					lst.add(new HashMap<String, Object>());
					node.put(key, lst);
				} 
				else if (((inList != null && inList.contains(key)) || (outList != null && outList.contains(key)))) {
					lstFound = true;
					lst = new ArrayList<HashMap<String, Object>>();
					lst.add(new HashMap<String, Object>());
					node.put(key, lst);
				} 
				else {
					// element IS OBJECT
					node.put(key, new HashMap<String, Object>());
				}
			}

			// go in deep
			if (lstMapIn != null && lstMapIn.get(key) == null)
			{
			    node = (HashMap<String, Object>) node.get(key);
			}
			else 
			{
				if (node.get(key) instanceof List) 
				{
					lst = (ArrayList<HashMap<String, Object>>) node.get(key);
					if (lst != null && lst.size() > 0 && (null != inList && inList.size() > 0 || null != outList && outList.size() > 0)) 
					{
						node = lst.get(lst.size() - 1);
						   // (issue omni mapping id 68) key is the list meaning i+1 is an object, get that object and see if it is hashmap
							if(node.get(keys[i+1]) instanceof HashMap)
							{
								exists = ((HashMap)node.get(keys[i+1])).containsKey(keys[keys.length - 1]) ;//this hashmap already contains this property means another item to be added to the list (another map)
							}
							else
							{
							    exists = node.containsKey(keys[keys.length - 1]);
							}
						if (exists && value != null) //found already an element with same name means another item to be added to the list (another map) 
						{
//							node = null;
							node = new HashMap<String, Object>();
							lst.add(node);
						}
					}
					else 
					{
						node = (HashMap<String, Object>) node.get(key);
					}
				}
				else
				{
				    node = (HashMap<String, Object>) node.get(key);
				}
			}
		}
    	
    }
    /**
     * This helper method will return the node element based on a given key
     * 
     * @param sourceKey
     * @param source
     * @return
     */
    private SimpleEntry<String, Object> getNodeElement(String sourceKey, HashMap<String, Object> source)
    {

	// Source: S -> A.B.C
	String[] keys = sourceKey.split("\\.");
	Object nodeValue = source;
	String nodeKey = null;

	// go in deep
	for(String key : keys)
	{

	    nodeKey = key;

	    // if hashmap
	    if(nodeValue instanceof HashMap)
	    {
		nodeValue = ((HashMap) nodeValue).get(key);
		continue;
	    }

	    // if value null
	    nodeValue = null;
	    break;
	}

	return new SimpleEntry<String, Object>(nodeKey, nodeValue);
    }

    /**
     * Private Xml connverter
     * 
     * @author MohammadAliMezzawi
     *
     */
    private class MapEntryConverter implements Converter
    {

	public boolean canConvert(final Class clazz)
	{
	    return Map.class.isAssignableFrom(clazz);
	}

	@Override
	public void marshal(final Object value, final HierarchicalStreamWriter writer, final MarshallingContext context)
	{
	    Map<String, Object> map = (Map<String, Object>) value;
	    for(Map.Entry<String, Object> entry : map.entrySet())
	    {
		if(entry.getValue() instanceof Map)
		{
		    writer.startNode(entry.getKey());
		    context.convertAnother(entry.getValue());
		    writer.endNode();
		}
		else
		{
		    writer.startNode(entry.getKey());
		    writer.setValue(entry.getValue().toString());
		    writer.endNode();
		}
	    }
	}

	@Override
	public Object unmarshal(final HierarchicalStreamReader reader, final UnmarshallingContext context)
	{
	    Map<String, Object> map = new HashMap<String, Object>();
	    while(reader.hasMoreChildren())
	    {
		reader.moveDown();
		if(reader.hasMoreChildren())
		{
		    Map<String, Object> childMap = (Map<String, Object>) context
			    .convertAnother(new HashMap<String, Object>(), Map.class);
		    map.put(reader.getNodeName(), childMap);
		}
		else
		{
		    map.put(reader.getNodeName(), reader.getValue());
		}

		reader.moveUp();
	    }
	    return map;
	}
    }

	public HashMap<String, Object> executeBoMethod(String boServiceName, String boMethodName,
			HashMap<String, Object> inputMap) throws BaseException 
	{
		Assert.hasLength(boServiceName, "boServiceName can't be null");
		Assert.hasLength(boMethodName, "boMethodName can't be null");
		if(StringUtil.nullToEmpty(boServiceName).isEmpty() || StringUtil.nullToEmpty(boMethodName).isEmpty())
		{
		    throw new BaseException("No matching BO/method found");
		}

		// invoke Bo rmi method
		return (HashMap<String, Object>) LocalServiceCaller.executeLocalMethod(boServiceName, boMethodName, inputMap,HashMap.class);
	}
}
