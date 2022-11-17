package com.path.lib.ws;

import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.common.util.Base64UrlUtility;
import org.apache.cxf.common.util.Base64Utility;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.path.lib.common.converters.NestedMapEntryConverter;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.SecurityUtils;
import com.path.lib.common.util.StringUtil;
import com.path.struts2.json.PathJSONUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.CompactWriter;

public class RestHttpUrlConnectionHandler
{

    static
    {
	/* In case of HTTPS call, to avoid getting the exception:
	 * javax.net.ssl.SSLHandshakeException: java.security.cert.CertificateException: No subject alternative names present.
	 * We need to disable the hostname verification between the url and the certificate owner*/
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() 
        {
            public boolean verify(String hostname, SSLSession session)
            {
               return true;
            }
        });
    }
    
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(RestHttpUrlConnectionHandler.class.getName());
    private String tokenAesKey;
    
    public String executeRestCall(Map<String, Object> paramMap) throws BaseException
    {

	if(paramMap == null || paramMap.isEmpty())
	{
	    return null;
	}

	String restUrl = (String) paramMap.get("Url");
	String contentType = (String) paramMap.get("ContentType");
	String accept = (String) paramMap.get("Accept");
	String authType = (String) paramMap.get("AuthType");
	String httpMethod = (String) paramMap.get("HttpMethod");
	String xmlRoot = (String) paramMap.get("XMLRoot");
	String theConnTimeout = (String) paramMap.get("ConnTimeout");//TP 880983
	String outputParameter = null;
	HttpURLConnection conn = null;
	int timeout = -1;
	try
	{
	    logger.log(Level.INFO, "[PathRestHttpUrlConnectionHandler] executeSoapCall ");
	    
	    if(theConnTimeout != null && StringUtil.isNumeric(theConnTimeout, false))
	    {
		timeout = Integer.parseInt(theConnTimeout);
	    }
	    
	    String inputParameter = parseInputParameterObject(paramMap.get("InputParameter"), contentType, xmlRoot);
	    
	    URL url = new URL(restUrl);
	    conn = (HttpURLConnection) url.openConnection();
	    conn.setDoOutput(true);
	    //880983 check if there is timeout for the connection specified
	    if(timeout > 0)
	    {
		conn.setConnectTimeout(timeout);
	    }
	    conn.setRequestMethod(StringUtil.isNotEmpty(httpMethod) ? httpMethod : HttpMethod.POST);
	    if(StringUtil.isNotEmpty(contentType))
	    {
		conn.setRequestProperty("Content-Type", contentType);
	    }
	    if(StringUtil.isNotEmpty(accept))
	    {
		conn.setRequestProperty("Accept", accept);
	    }
	    
	    if("BASIC".equals(authType))
	    {
		String restUsername = (String) paramMap.get("Username");
		String restPassword = (String) paramMap.get("Password");
		String encoded = Base64Utility.encode((restUsername + ":" + restPassword).getBytes("UTF-8"));
		conn.setRequestProperty("Authorization", "Basic " + encoded);
	    }
	    else if("OAUTH2".equals(authType))
	    {
		String tokenRegistrationURL = (String) paramMap.get("TokenRegistrationURL");
		String tokenRegistrationAppName = (String) paramMap.get("TokenRegistrationAppName");
		String tokenRegistrationAppPwd = (String) paramMap.get("TokenRegistrationAppPwd");
		String tokenLifeTime = (String) paramMap.get("TokenLifeTime");
		String tokenGenerationURL = (String) paramMap.get("TokenGenerationURL");
		String tokenAesKey = (String) paramMap.get("TokenAesKey");
		setTokenAesKey(tokenAesKey);
		
		if(tokenRegistrationURL != null && !tokenRegistrationURL.isEmpty() && tokenRegistrationAppName != null
			&& !tokenRegistrationAppName.isEmpty() && tokenRegistrationAppPwd != null
			&& !tokenRegistrationAppPwd.isEmpty() && tokenAesKey != null && !tokenAesKey.isEmpty())
		{
		    String registrationResult = registerOAuth2Client(tokenRegistrationURL, tokenRegistrationAppName,
			    tokenRegistrationAppPwd, tokenLifeTime);
		    logger.log(Level.INFO, "[PathRestHttpUrlConnectionHandler] registrationResult " + registrationResult);

		    if(registrationResult != null && !registrationResult.isEmpty())
		    {
			HashMap<String, String> jsonResult = new ObjectMapper().readValue(registrationResult, new TypeReference<Map<String, String>>() {});//(HashMap<String, String>) JSONUtils.deserialize(registrationResult);
			String registrationId = jsonResult.get("id");
			String registrationSecret = jsonResult.get("secret");
			logger.log(Level.INFO, "[PathRestHttpUrlConnectionHandler] registrationId " + registrationId);
			logger.log(Level.INFO, "[PathRestHttpUrlConnectionHandler] registrationSecret "
				+ registrationSecret);

			String tokenResult = returnClientAccessToken(tokenGenerationURL, registrationId,
				registrationSecret, tokenAesKey);
			if(tokenResult != null)
			{
			    logger.log(Level.INFO, "[PathRestHttpUrlConnectionHandler] tokenResult " + tokenResult);
			    HashMap<String, String> jsonTokenResult = new ObjectMapper().readValue(tokenResult, new TypeReference<Map<String, String>>() {});//(HashMap<String, String>) JSONUtil.deserialize(tokenResult);
			    String tokenKey = jsonTokenResult.get("access_token");
			    logger.log(Level.INFO, "[PathRestHttpUrlConnectionHandler] tokenKey " + tokenKey);
			    conn.setRequestProperty("Authorization", "Bearer " + tokenKey);
			}
			else
			{
			    throw new Exception("Could not execute request , tokenResult is null");
			}

		    }
		    else
		    {
			throw new IllegalArgumentException("Could not execute request , registrationResult/TokenRegistrationAppName/TokenRegistrationAppPwd is null");
		    }
		}
		else
		{
		    throw new IllegalArgumentException("Could not execute request , tokenRegistrationURL is null ");
		}

	    }

	    //TP 960556  adding additional headers
	    Map<String, String> additionalHeaders = (Map<String, String>) paramMap.get("AdditionalHeaders");
	    if(additionalHeaders != null && !additionalHeaders.isEmpty())
	    {
		for(String headerName : additionalHeaders.keySet())
		{
		    if(StringUtil.isNotEmpty(headerName))
		    {
			conn.setRequestProperty(headerName, additionalHeaders.get(headerName));
		    }
		}
	    }
	    
	    if(StringUtil.isNotEmpty(inputParameter))
	    {	
		logger.log(Level.INFO, "[PathRestHttpUrlConnectionHandler] inputParameter " + inputParameter);
		OutputStream os = conn.getOutputStream();
		os.write(inputParameter.getBytes("UTF-8"));
		os.flush();
	    }
	    if(conn.getResponseCode() != 200)
	    {
		if(conn.getErrorStream() != null)
		{
		    Scanner errorScanner = new Scanner(conn.getErrorStream(), "UTF-8").useDelimiter("\\A");
		    String errorStreamString = errorScanner.next();
		    errorScanner.close();
		    outputParameter = "[PathRestHttpUrlConnectionHandler]Error, code = ".concat(String.valueOf(conn.getResponseCode())).concat(", reason = ").concat(errorStreamString);
		}
		else
		{
		    outputParameter = "[PathRestHttpUrlConnectionHandler]Error, code = ".concat(String.valueOf(conn.getResponseCode()));
		}
	    }
	    else
	    {
		Scanner inputScanner = new Scanner(conn.getInputStream(), "UTF-8").useDelimiter("\\A");
		String inputStreamString = inputScanner.next();
		logger.log(Level.INFO, "[PathRestHttpUrlConnectionHandler] result  " + inputStreamString);
		return inputStreamString;
	    }

	}
	catch(Exception e)
	{
	    e.printStackTrace();
	    throw new BaseException(e.getMessage());
	}

	finally
	{
	    if(conn != null)
	    {
		conn.disconnect();
	    }
	}
	return outputParameter;
    }
    
    private String parseInputParameterObject(Object inputParamObj, String contentType, String xmlRoot) throws Exception
    {
	String inputParam = null;
	if(inputParamObj instanceof String)
	{
	    inputParam = (String)inputParamObj;
	}
	else if(inputParamObj instanceof Map)
	{
	    if(DATA_TYPE.JSON.equals(contentType))
	    {
		inputParam = PathJSONUtil.strutsJsonSerialize(inputParamObj, null, null, false, true);
	    }
	    else if(DATA_TYPE.XML.equals(contentType))
	    {
		//xmlRoot is mandatory when we need to convert to XML
		xmlRoot = StringUtil.nullEmptyToValue(xmlRoot, "root");
		XStream xstream = new XStream();
		xstream.alias(xmlRoot, Map.class);
		xstream.registerConverter(new NestedMapEntryConverter());
		StringWriter stringWriter = new StringWriter();
		xstream.marshal(inputParamObj, new CompactWriter(stringWriter));
		inputParam = stringWriter.toString();;
	    }
	}
	
	return inputParam;
    }

    private String registerOAuth2Client(String actionURL, String appName, String appPwd, String tokenLifeTime)
	    throws Exception
    {
	
	if(StringUtil.isNotEmpty(actionURL))
	{
	    if(!actionURL.endsWith("/"))
	    {
		actionURL = actionURL.concat("/");
	    }
	    
	    String encryptedAppName = SecurityUtils.encryptAES(getTokenAesKey(), appName);
	    String encryptedAppPwd = SecurityUtils.encryptAES(getTokenAesKey(), appPwd);
	    
	    String payload = "{\"id\":null,\"secret\":null,\"applicationName\":\"".concat(encryptedAppName)
	    .concat("\",\"applicationPwd\":\"").concat(encryptedAppPwd)
	    .concat("\",\"applicationWebUri\":null,\"redirectUris\":null,\"tokenLifeTime\":").concat(tokenLifeTime)
	    .concat(",\"authenticated\":false}");
	    
	    return sendHttpRequest(actionURL, MediaType.APPLICATION_JSON.toString(), MediaType.APPLICATION_JSON.toString(), payload, null,null);
	}
	
	return null;
    }

    private String returnClientAccessToken(String tokenUrl, String registrationId, String registrationSecret,
	    String aesKey) throws Exception
    {
	if(tokenUrl != null && registrationId != null && registrationSecret != null && aesKey != null)
	{
	    
	    String newRegistrationId = SecurityUtils.decryptAES(getTokenAesKey(), new String(
			Base64UrlUtility.decode(registrationId)));
	    String newRegistrationSecret = SecurityUtils.decryptAES(getTokenAesKey(), new String(
			Base64UrlUtility.decode(registrationSecret)));
	    
	    StringBuilder authorizationBuilder = new StringBuilder();
	    authorizationBuilder.append("Basic ");
	    authorizationBuilder.append(Base64Utility.encode(newRegistrationId.concat(":").concat(newRegistrationSecret).getBytes("UTF-8")));
	    return sendHttpRequest(tokenUrl, "*/*", "application/x-www-form-urlencoded", "grant_type=client_credentials", authorizationBuilder.toString(),null);
	    
	}
	else
	{
	    return null;
	}

    }
    
    private String sendHttpRequest(String connectionUrl, String accept, String contentType,
	    String payLoad, String authorization, String httpMethod) throws Exception
    {
	if(StringUtil.isNotEmpty(connectionUrl))
	{

	    String result = null;
	    HttpURLConnection connection = null;
	    Scanner inputScanner = null;
	    Scanner errorScanner = null;
	    OutputStream payloadOutputStream = null;
	    try
	    {
		URL url = new URL(connectionUrl);
		connection = (HttpURLConnection) url.openConnection();

		connection.setDoOutput(true);
		connection.setRequestMethod(StringUtil.isNotEmpty(httpMethod) ? httpMethod : HttpMethod.POST);
		connection.setRequestProperty("Accept", accept);
		connection.setRequestProperty("Content-Type", contentType);
		
		if(authorization != null)
		{
		    connection.setRequestProperty("Authorization", authorization);
		}
		
		if(StringUtil.isNotEmpty(payLoad))
		{    
		    payloadOutputStream = connection.getOutputStream();
		    payloadOutputStream.write(payLoad.getBytes());
		    payloadOutputStream.flush();
		}
		
		if(connection.getResponseCode() != 200)
		{
		    String errorValue = null;
		    if(connection.getErrorStream() != null)
		    {

			errorScanner = new Scanner(connection.getErrorStream(), "UTF-8").useDelimiter("\\A");

			errorValue = errorScanner.next();

			errorScanner.close();

		    }
		    throw new Exception(errorValue);
		}
		else
		{

		    inputScanner = new Scanner(connection.getInputStream(), "UTF-8").useDelimiter("\\A");

		    String responseValue = inputScanner.next();

		    inputScanner.close();

		    result = responseValue;
		}

	    }
	    catch(Exception e)
	    {
		throw new Exception("Exception in sendHttpRequest " + e.getMessage());
	    }
	    finally
	    {
		if(inputScanner != null)
		{
		    inputScanner.close();
		    inputScanner = null;
		}

		if(errorScanner != null)
		{
		    errorScanner.close();
		    errorScanner = null;
		}
		if(payloadOutputStream != null)
		{
		    payloadOutputStream.close();
		    payloadOutputStream = null;
		}
		if(connection != null)
		{
		    connection.disconnect();
		    connection = null;
		}
	    }
	    return result;

	}
	else
	{
	    return null;
	}

    }

    /**
     * @return the tokenAesKey
     */
    public String getTokenAesKey()
    {
        return tokenAesKey;
    }

    /**
     * @param tokenAesKey the tokenAesKey to set
     */
    public void setTokenAesKey(String tokenAesKey)
    {
        this.tokenAesKey = tokenAesKey;
    }

    private enum DATA_TYPE
    {
	JSON("application/json"), 
	XML("application/xml");
		
	private final String code;
	private DATA_TYPE(final String code)
	{
	    this.code = code;
	}
	
	public boolean equals(final String value){
	    return code.equalsIgnoreCase(value);
	}
    }
    
    /*
    public static void main(String args[])
    {
	try
	{
	    RestHttpUrlConnectionHandler r = new RestHttpUrlConnectionHandler();
	    Map<String, Object> paramMap = new HashMap<String, Object>(); 
	    Map<String, Object> inputParameter = new HashMap<String, Object>();
	    Map<String, Object> innerInputParameter = new HashMap<String, Object>();
	    Map<String, Object> innerInnerInputParameter = new HashMap<String, Object>();
	    innerInnerInputParameter.put("phone", 5);
	    innerInnerInputParameter.put("title", "6");
	    
	    innerInputParameter.put("age", "3");
	    innerInputParameter.put("address", 4);
	    innerInputParameter.put("innerinner", innerInnerInputParameter);
	    
	    inputParameter.put("id", "1");
	    inputParameter.put("name", 2);
	    
	    inputParameter.put("inner", innerInputParameter);
	    
	    paramMap.put("InputParameter", inputParameter);
	    
	    System.out.println(r.parseInputParameterObject(paramMap.get("InputParameter"),"application/json",null));
	    System.out.println(r.parseInputParameterObject(paramMap.get("InputParameter"),"application/xml",""));
	    
	}
	catch(Exception e)
	{
	    e.printStackTrace();
	}
    }
    */
}