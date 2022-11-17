package com.path.lib.ws;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.cxf.common.util.Base64UrlUtility;
import org.apache.cxf.common.util.Base64Utility;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.w3c.dom.NodeList;

import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.SecurityUtils;
import com.path.lib.common.util.StringUtil;

public class SoapHttpUrlConnectionHandler
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
    private static final Logger logger = Logger.getLogger(SoapHttpUrlConnectionHandler.class.getName());
    private String tokenAesKey;
    
    public Map<String, String> executeSoapCall(Map<String, Object> paramMap) throws BaseException
    {

	if(paramMap == null || paramMap.isEmpty())
	{
	    return null;
	}

	String soapUrl = (String) paramMap.get("Url");
	String soapOperation = (String) paramMap.get("Operation");
	String soapOperationLocalPart = (String) paramMap.get("OperationLocalPart");
	String authType = (String) paramMap.get("AuthType");
	String soapPrefix = (String) paramMap.get("SoapMessagePrefix");
	String soapAction = (String) paramMap.get("SOAPAction");
	Map<String, String> nameSpaceMap = (Map<String, String>) paramMap.get("NameSpaceMap");
	Map<String, String> soapInParameterMap = (Map<String, String>) paramMap.get("InputParameter");
	Map<String, String> soapOutParameterMap = (Map<String, String>) paramMap.get("OutputParameter");
	String soapMessage = (String) paramMap.get("SoapMessage");
	//TP 880983 Based on Chadi Assaf Request to add timeout
	String theConnTimeout = (String) paramMap.get("ConnTimeout");
	int timeout = -1;
	
	HttpURLConnection conn = null;
	try
	{
	    logger.log(Level.INFO, "[SoapHttpUrlConnectionHandler] executeSoapCall ");
	    
	    //TP 880983
	    if(theConnTimeout != null && StringUtil.isNumeric(theConnTimeout, false))
	    {
		timeout = Integer.parseInt(theConnTimeout);
	    }
	    
	    URL url = new URL(soapUrl);
	    conn = (HttpURLConnection) url.openConnection();
	    conn.setDoOutput(true);
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type", "text/xml;");
	    conn.setRequestProperty("Accept", "*/*");
	    
	   //880983 check if there is timeout for the connection specified
	    if(timeout > 0)
	    {
		conn.setConnectTimeout(timeout);
	    }

	    if(soapAction != null && !soapAction.isEmpty())
	    {
		conn.setRequestProperty("SOAPAction", soapAction);
	    }

	    if("BASIC".equals(authType))
	    {
		String soapUsername = (String) paramMap.get("Username");
		String soapPassword = (String) paramMap.get("Password");
		String encoded = Base64Utility.encode((soapUsername + ":" + soapPassword).getBytes("UTF-8"));
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
		    logger.log(Level.INFO, "[SoapHttpUrlConnectionHandler] registrationResult " + registrationResult);

		    if(registrationResult != null && !registrationResult.isEmpty())
		    {
			HashMap<String, String> jsonResult = new ObjectMapper().readValue(registrationResult, new TypeReference<Map<String, String>>() {});//(HashMap<String, String>) JSONUtil.deserialize(registrationResult);
			String registrationId = jsonResult.get("id");
			String registrationSecret = jsonResult.get("secret");
			logger.log(Level.INFO, "[SoapHttpUrlConnectionHandler] registrationId " + registrationId);
			logger.log(Level.INFO, "[SoapHttpUrlConnectionHandler] registrationSecret "
				+ registrationSecret);

			String tokenResult = returnClientAccessToken(tokenGenerationURL, registrationId,
				registrationSecret, tokenAesKey);
			if(tokenResult != null)
			{
			    logger.log(Level.INFO, "[SoapHttpUrlConnectionHandler] tokenResult " + tokenResult);
			    HashMap<String, String> jsonTokenResult = new ObjectMapper().readValue(tokenResult, new TypeReference<Map<String, String>>() {});//(HashMap<String, String>) JSONUtil.deserialize(tokenResult);
			    String tokenKey = jsonTokenResult.get("access_token");
			    logger.log(Level.INFO, "[SoapHttpUrlConnectionHandler] tokenKey " + tokenKey);
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

	    String soapInputmessage = soapMessage;
	    if(soapInParameterMap != null && !soapInParameterMap.isEmpty())
	    {
		soapInputmessage = returnSoapInputMessage(soapInParameterMap, soapOperation, soapOperationLocalPart,
			    soapPrefix, nameSpaceMap);
		logger.log(Level.INFO, "[SoapHttpUrlConnectionHandler] soapInputmessage " + soapInputmessage);
	    }
	    if(soapInputmessage != null && !soapInputmessage.isEmpty())
	    {	
		OutputStream os = conn.getOutputStream();
		os.write(soapInputmessage.getBytes("UTF-8"));
		os.flush();
	    }
	    
	    
	    if(conn.getResponseCode() != 200)
	    {
		if(conn.getErrorStream() != null)
		{
		    Scanner errorScanner = new Scanner(conn.getErrorStream(), "UTF-8").useDelimiter("\\A");
		    String errorStreamString = errorScanner.next();
		    errorScanner.close();
		    soapOutParameterMap.put("error", errorStreamString);
		}
		else
		{
		    soapOutParameterMap.put("error", String.valueOf(conn.getResponseCode()));
		}
	    }
	    else
	    {
		Scanner inputScanner = new Scanner(conn.getInputStream(), "UTF-8").useDelimiter("\\A");
		String inputStreamString = inputScanner.next();
		logger.log(Level.INFO, "[SoapHttpUrlConnectionHandler] result  " + inputStreamString);
		if(soapOutParameterMap.containsKey("SOAP_XML_RESULT"))
		{
		    soapOutParameterMap.put("SOAP_XML_RESULT", inputStreamString);
		    return soapOutParameterMap;
		}
		else
		{
		    parseSoapOutputMessage(inputStreamString, soapOutParameterMap);
		    return soapOutParameterMap;
		}
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
	return null;
    }

    private String returnSoapInputMessage(Map<String, String> paramMap, String soapOperation,
	    String soapOperationLocalPart, String soapPrefix, Map<String, String> nameSpaceMap) throws Exception
    {

	try
	{
	    if(paramMap != null && !paramMap.isEmpty())
	    {
		MessageFactory myMsgFct = MessageFactory.newInstance(SOAPConstants.DEFAULT_SOAP_PROTOCOL);
		SOAPMessage soapMessage = myMsgFct.createMessage();
		SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();

		if(nameSpaceMap != null && !nameSpaceMap.isEmpty())
		{
		    for(String nameSpaceKey : nameSpaceMap.keySet())
		    {
			soapEnvelope.addNamespaceDeclaration(nameSpaceKey, nameSpaceMap.get(nameSpaceKey));
		    }
		}

		// se.removeNamespaceDeclaration("env");
		soapEnvelope.setPrefix(soapPrefix);
		SOAPBody soapBody = soapEnvelope.getBody();
		soapBody.setPrefix(soapPrefix);

		SOAPHeader soapHeader = soapEnvelope.getHeader();
		soapHeader.setPrefix(soapPrefix);

		// add an additional Element to the SOAP-Body - namespace url + local part
		SOAPBodyElement bodyElement = soapBody.addBodyElement(new QName("", soapOperation,
			soapOperationLocalPart));
		for(String key : paramMap.keySet())
		{

		    // simple flat argument
		    if(!key.contains("."))
		    {
			createSoapArgNode(key, paramMap.get(key), soapEnvelope, bodyElement, true);
		    }
		    // composite object argument
		    else
		    {
			String[] argNodesNameList = key.split("\\.");
			if(argNodesNameList != null && argNodesNameList.length > 0)
			{
			    SOAPElement parentArgElement = bodyElement;
			    for(int i = 0; i < argNodesNameList.length; i++)
			    {
				String argName = argNodesNameList[i];
				SOAPElement createdArgSoapElement = createSoapArgNode(argName, paramMap.get(key),
					soapEnvelope, parentArgElement, (i + 1 == argNodesNameList.length));
				parentArgElement = createdArgSoapElement;
			    }
			}
		    }

		}

		soapMessage.saveChanges();

		StringWriter soapStringMessage = new StringWriter();
		try
		{
		    TransformerFactory.newInstance().newTransformer().transform(
			    new DOMSource(soapMessage.getSOAPPart()), new StreamResult(soapStringMessage));
		}
		catch(TransformerException e)
		{
		    throw new RuntimeException(e);
		}
		return soapStringMessage.toString();
	    }

	}
	catch(Exception e)
	{
	    throw new Exception(e.getMessage());
	}

	return null;
    }

    private SOAPElement createSoapArgNode(String argNodeNameValue, String value, SOAPEnvelope soapEnvelope,
	    SOAPElement parentSoapElement, boolean addNodeValue) throws Exception
    {
	NodeList argNodeList = parentSoapElement.getElementsByTagName(argNodeNameValue);
	if(argNodeList == null || argNodeList.getLength() == 0)
	{

	    SOAPElement argNodeElement = null;
	    Pattern pattern = Pattern.compile("(.*):(.*)");
	    Matcher matcher = pattern.matcher(argNodeNameValue);
	    if(matcher.find())
	    {
		String prefix = matcher.group(1);
		String localName = matcher.group(2);
		QName q = new QName("", localName, prefix);
		argNodeElement = parentSoapElement.addChildElement(localName, prefix);
	    }
	    else
	    {
		Name argNodeName = soapEnvelope.createName(argNodeNameValue);
		argNodeElement = parentSoapElement.addChildElement(argNodeName);
	    }

	    if(addNodeValue)
	    {
		String argValue = value == null ? "" : value;
		if(!argValue.isEmpty())
		{
		    argNodeElement.addTextNode(argValue);
		}
	    }
	    return argNodeElement;
	}
	return (SOAPElement) argNodeList.item(0);
    }

    private void parseSoapOutputMessage(String soapOutputMessage, Map<String, String> wsOutputParamMap)
	    throws Exception
    {
	try
	{
	    if(soapOutputMessage != null && !soapOutputMessage.isEmpty() && wsOutputParamMap != null
		    && !wsOutputParamMap.isEmpty())
	    {
		InputStream is = new ByteArrayInputStream(soapOutputMessage.getBytes("UTF-8"));
		SOAPMessage sm = MessageFactory.newInstance(SOAPConstants.DEFAULT_SOAP_PROTOCOL).createMessage(
			new MimeHeaders(), is);
		SOAPBody soapBody = sm.getSOAPBody();

		for(String key : wsOutputParamMap.keySet())
		{
		    if(key.contains("."))
		    {
			SOAPElement parentNode = soapBody;
			String[] keyNodes = key.split("\\.");
			for(int i = 0; i < keyNodes.length; i++)
			{
			    String node = keyNodes[i];
			    NodeList argNodeList = parentNode.getElementsByTagName(node);
			    if(argNodeList != null && argNodeList.getLength() > 0)
			    {
				parentNode = (SOAPElement) argNodeList.item(0);
				if(i + 1 == keyNodes.length)
				{
				    SOAPElement nodeElement = (SOAPElement) argNodeList.item(0);
				    String outputValue = nodeElement.getTextContent();
				    wsOutputParamMap.put(key, outputValue);
				    break;
				}
			    }
			}
		    }
		    else
		    {
			NodeList argNodeList = soapBody.getElementsByTagName(key);

			if(argNodeList != null && argNodeList.getLength() > 0)
			{
			    SOAPElement nodeElement = (SOAPElement) argNodeList.item(0);
			    String outputValue =  nodeElement.getTextContent();
			    wsOutputParamMap.put(key, outputValue);

			}
		    }
		}
	    }
	}
	catch(Exception e)
	{
	    throw new Exception(e.getMessage());
	}
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
	    
	    return sendHttpRequest(actionURL, MediaType.APPLICATION_JSON.toString(), MediaType.APPLICATION_JSON.toString(), payload, null);
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
	    return sendHttpRequest(tokenUrl, "*/*", "application/x-www-form-urlencoded", "grant_type=client_credentials", authorizationBuilder.toString());
	    
	}
	else
	{
	    return null;
	}

    }
    
    private String sendHttpRequest(String connectionUrl, String accept, String contentType,
	    String payLoad, String authorization) throws Exception
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
		connection.setRequestMethod(HttpMethod.POST);
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
    
    
    
/**    
//    Example of calling SOAP Webservice Call    
//    public static void main(String args[])
//    {
//
//	SoapHttpUrlConnectionHandler p = new SoapHttpUrlConnectionHandler();
//
//	Map<String, Object> paramMap = new HashMap<String, Object>();
//	paramMap.put("Url", "https://test.cinetonline.com.kw/CinetWebAppPhase1/Services/ServiceProvider.svc");
//	paramMap.put("Operation", "GetLiveCIR");
//	paramMap.put("OperationLocalPart", "ns2");
//
//	paramMap.put("SoapMessagePrefix", "SOAP-ENV");
//	paramMap.put("SOAPAction", "http://tempuri.org/ILiveRequest/GetLiveCIR");
//
//	Map<String, String> nameSpaceMap = new HashMap<String, String>();
//	nameSpaceMap.put("ns2", "http://tempuri.org/");
//	nameSpaceMap.put("ns1", "http://schemas.datacontract.org/2004/07/SilverBladeWeb.Services");
//	paramMap.put("NameSpaceMap", nameSpaceMap);
//
//	LinkedHashMap<String, String> soapInParamMap = new LinkedHashMap<String, String>();
//	soapInParamMap.put("ns2:ReqLiveReport.ns1:UserName", "Utabk00005");
//	soapInParamMap.put("ns2:ReqLiveReport.ns1:Password", "pass@1234");
//	// soapInParamMap.put("ns2:ReqLiveReport.ns1:RequestXML",
//	// "<![CDATA[<REQUEST REQUEST_ID=\"1\">"+
//	// "<REQUEST_PARAMETERS>"+
//	// "<INQUIRY_PURPOSE CODE = \"1\" RELATIONTOACCOUNT = \"01\"/>"+
//	// "<LOANDETAILS PORTFOLIOTYPE=\"02\" AMOUNTOFFINANCE=\"12000\" DURATIONOFAGREEMENT=\"12\" DURATIONUNIT=\"2\" PAYMENTAMOUNT=\"1000\"  CREDITLIMIT=\"10\" PAYMENTFREQUENCY=\"01\"  OCCUPATION = \"01\" OTHERINCOME = \"3000\" MONTHLYSALARY = \"2000\" CULTURE_INFO=\"en-US\" RESPONSE_TYPE=\"1\"  />"+
//	// "<REPORT_PARAMETERS CIR_TYPE=\"14716\"/>"+
//	// "</REQUEST_PARAMETERS>"+
//	// "<SEARCH_PARAMETERS>"+
//	// "<IDENTIFIER>"+
//	// "<CIVILID>267042300848</CIVILID>"+
//	// "</IDENTIFIER>"+
//	// "</SEARCH_PARAMETERS>"+
//	// "</REQUEST> ]]>");
//
//	soapInParamMap
//		.put(
//			"ns2:ReqLiveReport.ns1:RequestXML",
//			"<![CDATA[<REQUEST REQUEST_ID='1'><REQUEST_PARAMETERS><INQUIRY_PURPOSE CODE = '1' RELATIONTOACCOUNT = '01'/><LOANDETAILS PORTFOLIOTYPE='02' AMOUNTOFFINANCE='12000' DURATIONOFAGREEMENT='12' DURATIONUNIT='2' PAYMENTAMOUNT='1000'  CREDITLIMIT='10' PAYMENTFREQUENCY='01'  OCCUPATION = '01' OTHERINCOME = '3000' MONTHLYSALARY = '2000' CULTURE_INFO='en-US' RESPONSE_TYPE='1'  /><REPORT_PARAMETERS CIR_TYPE='14716'/></REQUEST_PARAMETERS><SEARCH_PARAMETERS><IDENTIFIER><CIVILID>267042300848</CIVILID></IDENTIFIER></SEARCH_PARAMETERS></REQUEST>]]>");
//	// soapInParamMap.put("ns2:ReqLiveReport.ns1:RequestXML","<![CDATA[<REQUEST REQUEST_ID=\"1\"> <REQUEST_PARAMETERS> <INQUIRY_PURPOSE CODE = \"1\" RELATIONTOACCOUNT = \"01\"/> <LOANDETAILS PORTFOLIOTYPE=\"02\" AMOUNTOFFINANCE=\"12000\" DURATIONOFAGREEMENT=\"12\" DURATIONUNIT=\"2\" PAYMENTAMOUNT=\"1000\"  CREDITLIMIT=\"10\" PAYMENTFREQUENCY=\"01\"  OCCUPATION = \"01\" OTHERINCOME = \"3000\" MONTHLYSALARY = \"2000\" CULTURE_INFO=\"en-US\" RESPONSE_TYPE=\"1\"  /> <REPORT_PARAMETERS CIR_TYPE=\"14716\"/></REQUEST_PARAMETERS><SEARCH_PARAMETERS>  <IDENTIFIER> <CIVILID>267042300848</CIVILID>  </IDENTIFIER></SEARCH_PARAMETERS> </REQUEST>]]>"
//	// );
//	// soapInParamMap.put("ns2:ReqLiveReport.ns1:RequestXML","<![CDATA[ <REQUEST REQUEST_ID='1'><REQUEST_PARAMETERS><INQUIRY_PURPOSE CODE = '1' RELATIONTOACCOUNT = '01'/><LOANDETAILS PORTFOLIOTYPE='02' AMOUNTOFFINANCE='12000' DURATIONOFAGREEMENT='12' DURATIONUNIT='2' PAYMENTAMOUNT='1000'  CREDITLIMIT='10' PAYMENTFREQUENCY='01'  OCCUPATION = '01' OTHERINCOME = '3000' MONTHLYSALARY = '2000' CULTURE_INFO='en-US' RESPONSE_TYPE='1'  /><REPORT_PARAMETERS CIR_TYPE='14716'/></REQUEST_PARAMETERS><SEARCH_PARAMETERS><IDENTIFIER><CIVILID>267042300848</CIVILID></IDENTIFIER></SEARCH_PARAMETERS></REQUEST> ]]>"
//	// );
//	Map<String, String> soapOutParamMap = new HashMap<String, String>();
//	soapOutParamMap.put("GetLiveCIRResponse.GetLiveCIRResult.a:IsSuccess", null);
//	soapOutParamMap.put("GetLiveCIRResponse.GetLiveCIRResult.a:ResponseXML", null);
//
//	paramMap.put("InputParameter", soapInParamMap);
//	paramMap.put("OutputParameter", soapOutParamMap);
//
//	p.executeSoapCall(paramMap);
//
//	logger.log(Level.INFO, "SoapHttpUrlConnectionHandler soapOutParamMap " + soapOutParamMap);
//
//    }
**/
}