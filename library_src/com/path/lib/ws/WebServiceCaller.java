package com.path.lib.ws;

import java.util.Map;

import com.path.lib.common.exception.BaseException;

public class WebServiceCaller
{

    /**
     * Method used to call REST webservice using HttpUrlConnection without using the webservice interface
     * @param paramMap a map that should contain the below keys
     *  "Url" : define the webService URL , for example : "http://localhost:8050/imal_core_services/pathservices/RestBasicAuthService/returnCompany"
     *  "ContentType" : define the parameters type , for example : "application/json" or "application/xml"
     *  "Accept" : define the response format type, for example : "application/json" or "application/xml"
     *  "HttpMethod" : define the http method, for example "POST" or "GET". "POST" is used by default
     *  "InputParameter" : a string representing the input parameters value, for example "{\"compCode\":5}") in case of JSON
     *  "AuthType" : used to define the authetication type. it can be "BASIC" or "OAUTH2".
     *  "Username" : in case of BASIC authentication, we need to set the username 
     *  "Password" : in case of BASIC authentication, we need to set the password
     *  "TokenRegistrationURL" : in case of OAUTH2 authentication, we need to define the token registration url. for example, "http://localhost:8050/imal_core_services/pathservices/oauthRegistration/registerClient"
     *  "TokenRegistrationAppName" : in case of OAUTH2 authentication, we need to define the username for registration.for example, "CSM_NAME"
     *  "TokenRegistrationAppPwd": in case of OAUTH2 authentication, we need to define the password for registration, for example, "CSM_PWD"
     *  "TokenLifeTime", : in case of OAUTH2 authentication, we need to define the token life time in ms ,for example, "3600"
     *  "TokenGenerationURL", in case of OAUTH2 authentication, we need to define the url used to generate the token , for example, "http://localhost:8050/imal_core_services/pathservices/oauthToken/token"
     *  "ConnTimeout", timeout for the connection in milliseconds
     *  
     * @return
     */
    public static String callRestWebService(Map<String, Object> paramMap) throws BaseException
    {
	RestHttpUrlConnectionHandler handler = new RestHttpUrlConnectionHandler();
	return handler.executeRestCall(paramMap);
    }
    
    /**
     * Method used to call SOAP webservice using HttpUrlConnection without using the webservice interface
     * @param paramMap
     * 
     *  "Url" : used to define the URL of the SOAP webservice, for example, "http://localhost:8050/imal_core_services/pathservices/SoapBaseAuthService");
     *  "Operation" : used to define the operation or method that should be called, for example "returnCompDesc" 
     *  "OperationLocalPart" used to define the local part of the operation, for example, "ns2"
     *  "SoapMessagePrefix", used to define the soap message prefix, for example "SOAP-ENV" 
     *  "SOAPAction", used to define the SOAP action, for example "returnCompDesc"
     *  
     *  "NameSpaceMap", a map needed to define all namespaces in SOAPMessage , for example :
     *  	Map<String, String> nameSpaceMap = new HashMap<String, String>();
     *	    	nameSpaceMap.put("ns2", "http://impl.soap.demo_webservice.path.com/");
     *	    	paramMap.put("NameSpaceMap", nameSpaceMap);
     *          	    
     *  "InputParameter", used to define input parameters map, for example
     *  	  
     *            LinkedHashMap<String, String> soapInParamMap = new LinkedHashMap<String, String>();
     *            soapInParamMap.put("arg0.compCode", "1");
     *            paramMap3.put("InputParameter", soapInParamMap);
     *   
     *  "OutputParameter", used to define output parameters map, for example 
     *            
     *            Map<String, String> soapOutParamMap = new HashMap<String, String>();
     *            soapOutParamMap.put("return.compDesc", null);
     * 		  paramMap3.put("OutputParameter", soapOutParamMap);
     * 
     *  "AuthType" : used to define the authetication type. it can be "BASIC" or "OAUTH2".
     *  "Username" : in case of BASIC authentication, we need to set the username 
     *  "Password" : in case of BASIC authentication, we need to set the password
     *  "TokenRegistrationURL" : in case of OAUTH2 authentication, we need to define the token registration url. for example, "http://localhost:8050/imal_core_services/pathservices/oauthRegistration/registerClient"
     *  "TokenRegistrationAppName" : in case of OAUTH2 authentication, we need to define the username for registration.for example, "CSM_NAME"
     *  "TokenRegistrationAppPwd": in case of OAUTH2 authentication, we need to define the password for registration, for example, "CSM_PWD"
     *  "TokenLifeTime", : in case of OAUTH2 authentication, we need to define the token life time in ms ,for example, "3600"
     *  "TokenGenerationURL", in case of OAUTH2 authentication, we need to define the url used to generate the token , for example, "http://localhost:8050/imal_core_services/pathservices/oauthToken/token"
     *  "ConnTimeout", timeout for the connection in milliseconds
     * @return
     */
    public static Map<String, String> callSoapWebService(Map<String, Object> paramMap) throws BaseException
    {
	SoapHttpUrlConnectionHandler handler = new SoapHttpUrlConnectionHandler();
	return handler.executeSoapCall(paramMap);
    }
    
}
