package com.path.lib.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Key;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Base64Utils;

import com.path.bo.common.ConstantsCommon;
import com.path.lib.common.exception.BaseException;
import com.path.lib.log.Log;
import com.path.vo.common.SessionCO;


/**
 * DENISK_LATEST_VERS_UPDATED
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: deniskhaddad
 * 
 *          SecurityUtils.java used to provide methods for security like
 *          encoding and decoding
 */
public final class SecurityUtils
{
    private final static String ALGORITHM = "AES";
    private final static String BLOWFISH_ALGORITHM = "Blowfish";
    
    
    
    private static final Log log = Log.getInstance();

    /**
     * Private constructor to prevent class from instantiation
     */
    private SecurityUtils()
    {
	log.warning("This class is utility and cannot be instantiated");
    }
    /**
     * 
     * Used for Base 64 encoding
     * 
     * @param theValue
     * @return
     */
    public static String encodeB64(String theValue) throws BaseException
    {
	String result = "";
	if(theValue != null)
	{
	    try
	    {
		result = new String(Base64Utils.encode(theValue.getBytes(FileUtil.DEFAULT_FILE_ENCODING)),FileUtil.DEFAULT_FILE_ENCODING);
	    }
	    catch(UnsupportedEncodingException e)
	    {
		log.error(e,"Error In Encoding encodeB64");
		throw new BaseException(e);
	    }
	}
	return result;
    }

    /**
     * 
     * Used for Base 64 encoding
     * 
     * @param theBytes
     * @return
     */
    public static String encodeB64(byte[] theBytes) throws BaseException
    {
	String result = "";
	if(theBytes != null)
	{
	    try
	    {
		result = new String(Base64Utils.encode(theBytes),FileUtil.DEFAULT_FILE_ENCODING);
	    }
	    catch(UnsupportedEncodingException e)
	    {
		log.error(e,"Error In Encoding encodeB64 bytes");
		throw new BaseException(e);
	    }
	}
	return result;
    }
    /**
     * 
     * Used for Base 64 encoding With no character encoding
     * 
     * @param theValue
     * @return
     */
    public static String encodeB64NoCharEncoding(byte[] theBytes) throws BaseException
    {
	String result = "";
	if(theBytes != null)
	{
	    try
	    {
		result = new String(Base64Utils.encode(theBytes));
	    }
	    catch(Exception e)
	    {
		log.error(e,"Error In Encoding encodeB64NoCharEncoding");
		throw new BaseException(e);
	    }
	}
	return result;
    }
    
    /**
     * 
     * Used for decoding with Base 64 decoding
     * 
     * @param theValue
     * @return
     */
    public static String decodeB64(String theValue) throws BaseException
    {
	String result = "";
	if(theValue != null)
	{
	    try
	    {
		result = new String(Base64Utils.decode(theValue.getBytes(FileUtil.DEFAULT_FILE_ENCODING)),FileUtil.DEFAULT_FILE_ENCODING);
	    }
	    catch(UnsupportedEncodingException e)
	    {
		log.error(e,"Error In Encoding decodeB64");
		throw new BaseException(e);
	    }
	}
	return result;
    }

    /**
     * 
     * Used for decoding with Base 64 decoding
     * 
     * @param theBytes
     * @return
     */
    public static String decodeB64(byte[] theBytes) throws BaseException
    {
	String result = "";
	if(theBytes != null)
	{
	    try
	    {
		result = new String(Base64Utils.decode(theBytes),FileUtil.DEFAULT_FILE_ENCODING);
	    }
	    catch(UnsupportedEncodingException e)
	    {
		log.error(e,"Error In Encoding decodeB64 bytes");
		throw new BaseException(e);
	    }
	}
	return result;
    }
    
    /**
     * 
     * Used for decoding with Base 64 decoding Without character encoding
     * 
     * @param theBytes
     * @return byte[]
     */
    public static byte[] decodeB64NoCharEncoding(byte[] theBytes) throws BaseException
    {
	byte[] result = null;
	if(theBytes != null)
	{
	    try
	    {
		result = Base64Utils.decode(theBytes);
	    }
	    catch(Exception e)
	    {
		log.error(e,"Error In Encoding decodeB64NoCharEncoding bytes");
		throw new BaseException(e);
	    }
	}
	return result;
    }

    /**
     * 
     * Used for encryption using AES Java Algorithm
     * 
     * @param theKey
     * @param theValue
     * @return
     */
    public static String encryptAES(String theKey, String theValue) throws BaseException
    {
	try
	{
	    return SecurityUtilsExt.encryptAES(theKey, theValue);
	}
	catch(Exception e)
	{
	    Log.getInstance().error(e,"ERROR in Encryption Process ");
	    throw new BaseException("ERROR in Encryption Process "+e.getMessage(),e);
	}
    }
    
    /**
     * 
     * Used for encryption using BlowFish Java Algorithm
     * 
     * @param theKey
     * @param theValue
     * @return
     */
    public static String encryptBlowFish(String passwrd, String theValue) throws BaseException
    {
	String theKey = passwrd;
	try
	{
	    if(theKey != null && theKey.length() > 16 )
	    {
		 theKey = theKey.substring(0,16);
	    }
	    Cipher c = Cipher.getInstance(BLOWFISH_ALGORITHM);
	    Key aesKey = new SecretKeySpec(theKey.getBytes(FileUtil.DEFAULT_FILE_ENCODING), BLOWFISH_ALGORITHM);
	    c.init(Cipher.ENCRYPT_MODE, aesKey);
	    byte[] encVal = c.doFinal(theValue.getBytes(FileUtil.DEFAULT_FILE_ENCODING));
	    return encodeB64(encVal);
	}
	catch(Exception e)
	{
	    Log.getInstance().error(e,"ERROR in encryptBlowFish Process ");
	    throw new BaseException("ERROR in encryptBlowFish Process ",e);
	}
    }
    /**
     * 
     * Used for decryption of encrypted Data using particular Key
     * 
     * @param theKey
     * @param encryptedData
     * @return
     * @throws BaseException
     */
    public static String decryptBlowFish(String passwrd, String encryptedData) throws BaseException
    {
	String theKey = passwrd;
	try
	{
	    if(theKey != null && theKey.length() > 16 )
	    {
		 theKey = theKey.substring(0,16);
	    }
	    Key aesKey = new SecretKeySpec(theKey.getBytes(FileUtil.DEFAULT_FILE_ENCODING), BLOWFISH_ALGORITHM);
	    Cipher c = Cipher.getInstance(BLOWFISH_ALGORITHM);
	    c.init(Cipher.DECRYPT_MODE, aesKey);
	    byte[] decodedValue = Base64Utils.decode(encryptedData.getBytes(FileUtil.DEFAULT_FILE_ENCODING));
	    byte[] decValue = c.doFinal(decodedValue);
	    return new String(decValue,FileUtil.DEFAULT_FILE_ENCODING);
	}
	catch(Exception e)
	{
	    throw new BaseException("ERROR in decryption Process BlowFish",e);
	}
    }
    
    /**
     * 
     * Used for decryption of encrypted Data using particular Key
     * 
     * @param theKey
     * @param encryptedData
     * @return
     * @throws BaseException
     */
    public static String decryptAES(String theKey, String encryptedData) throws BaseException
    {
	try
	{
	    return SecurityUtilsExt.decryptAES(theKey, encryptedData);
	}
	catch(Exception e)
	{
	    throw new BaseException("ERROR in decryption Process AES "+e.getMessage(),e);
	}
    }
    /**
     *  Returns a hashed byte[] of the received byte[] argument, based on the provided algorithm.
     * @param theByteToHash (the byte[] to hash)
     * @param encChar (the specified algorithm
     * @return
     * @throws BaseException
     */
    public static byte[] returnMd5Digest(byte[] theByteToHash, String encChar) throws BaseException
    {
	try
	{
	    MessageDigest msgDig = MessageDigest.getInstance(encChar);
	    msgDig.update(theByteToHash);
	    return msgDig.digest();
	}
	catch(Exception e)
	{
	    Log.getInstance().error(e, "ERROR in return MD5 digest ");
	    throw new BaseException("ERROR in return MD5 digest", e);
	}
    }
    
    /**************************************************************************************************************************/
    
    /*Security Encryption for penetration test*/
    
    public static String decryptNoPadding(String message, String password) throws Exception 
    {   
	if(StringUtil.nullToEmpty(password).isEmpty() || !ConstantsCommon.SECURITY_ENCRYPTPARAMS_DYNAMIC_PWD)
	{
	    password = SecurityUtilsExt.returnAlgorithmCbcNopaddingPwd();
	}

        byte [] cipherBytes = Base64Utils.decode(message.getBytes(FileUtil.DEFAULT_FILE_ENCODING));
        byte [] iv = password.getBytes(FileUtil.DEFAULT_FILE_ENCODING);
        byte [] keyBytes = password.getBytes(FileUtil.DEFAULT_FILE_ENCODING);

        SecretKey aesKey = new SecretKeySpec(keyBytes, SecurityUtilsExt.ALGORITHM);

        Cipher cipher = Cipher.getInstance(SecurityUtilsExt.ALGORITHM_CBC_NOPADDING);
        cipher.init(Cipher.DECRYPT_MODE, aesKey, new IvParameterSpec(iv));

        byte[] result = cipher.doFinal(cipherBytes);
        return new String(result,FileUtil.DEFAULT_FILE_ENCODING);
    }

    public static String encryptNoPadding(String message, String password) throws Exception 
    {   
	if(StringUtil.nullToEmpty(password).isEmpty() || !ConstantsCommon.SECURITY_ENCRYPTPARAMS_DYNAMIC_PWD)
	{
	    password = SecurityUtilsExt.returnAlgorithmCbcNopaddingPwd();
	}

	byte [] iv = password.getBytes(FileUtil.DEFAULT_FILE_ENCODING);
        byte [] keyBytes = password.getBytes(FileUtil.DEFAULT_FILE_ENCODING);

        SecretKey aesKey = new SecretKeySpec(keyBytes, SecurityUtilsExt.ALGORITHM);

        Cipher cipher = Cipher.getInstance(SecurityUtilsExt.ALGORITHM_CBC_NOPADDING);
        cipher.init(Cipher.ENCRYPT_MODE, aesKey, new IvParameterSpec(iv));

        int blockSize = cipher.getBlockSize();
        byte[] dataBytes = message.getBytes();
        int plaintextLength = dataBytes.length;
        if (plaintextLength % blockSize != 0) {
            plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
        }

        byte[] plaintext = new byte[plaintextLength];
        System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
        
        byte[] result = cipher.doFinal(plaintext);
        String encryptedValue = new String(Base64Utils.encode(result),FileUtil.DEFAULT_FILE_ENCODING);
        return encryptedValue;
    }
    
    public static String returnEncryptedNoPaddingParams(String params , boolean encodeURIComponent, boolean addParamKey, HttpSession session) throws Exception
    {
	if(StringUtil.isNotEmpty(params))
	{
	    int hashCode = params.hashCode();
	    params = params.concat("&hashcode="+hashCode);
	    String password = null;
	    if(session != null)
	    {
		 String sessionToken = (String)session.getAttribute(SecurityUtilsExt.SESSION_TOKEN_KEY);
		 if(StringUtil.isNotEmpty(sessionToken))
		 {    
		     String encryptedSessionToken = encryptAES(SecurityUtilsExt.returnAlgorithmSessionTokenPwd(), sessionToken);
		     params = params.concat("&sessionToken=" + encryptedSessionToken);
		 }
		 password = StringUtil.nullToEmpty(session.getAttribute(SecurityUtilsExt.PATH_ENC_PWD));
	    }
	    String paramKey = "";
	    if(addParamKey)
	    {
		paramKey = "PATHPARAM=";
	    }
	    
	    if(encodeURIComponent)
	    {
		return paramKey +  URLEncoder.encode(encryptNoPadding("#" + params + "#",password), SecurityUtilsExt.DEFAULT_ENCODING);
	    }
	    else
	    {
		return paramKey + encryptNoPadding("#" + params + "#",password);	
	    }
	}
	return params;
    }
    /**
     * returning user name for informative details
     * @param request
     * @return
     */
    private static String returnUserFromSession(HttpServletRequest request)
    {
    	String userName= "UNKNOWN";
    	try
    	{
    		SessionCO sessCO = (SessionCO) request.getSession().getAttribute(ConstantsCommon.SESSION_VO_ATTR);
    		if(sessCO != null)
    		{
    			userName = sessCO.getUserName();
    		}
    	}
    	catch(Exception e)
    	{
    		 log.error(e,"Error in Returning UserName from Session");
    	}
    	return userName;
    }
    
    
    public static Map<String, Object> returnDecryptedNoPaddingParams(HttpServletRequest request, String pathEncryptedParam ) throws Exception
    {
	Map<String, Object> parametersMap = new HashMap<String , Object>();
	
	if(pathEncryptedParam != null && !pathEncryptedParam.isEmpty() )
	{   
	    String paramPath = request.getParameter("PARAMPATH");
	    
	    // verify that the Session attributes are available, and not session has been invalidated, since this filter comes before Spring Security filter
	    // So if session attributes are null then decryption will be failed, before proceeding to security url verification by spring security
	    String dynDecrypKey = StringUtil.nullToEmpty(request.getSession().getAttribute(SecurityUtilsExt.PATH_ENC_PWD));
	    if((ConstantsCommon.SECURITY_ENCRYPTPARAMS_DYNAMIC_PWD && !dynDecrypKey.trim().isEmpty())
	    	|| !ConstantsCommon.SECURITY_ENCRYPTPARAMS_DYNAMIC_PWD || ConstantsCommon.ONE.equals(paramPath))
	    {
	    	String password = SecurityUtilsExt.returnAlgorithmCbcNopaddingPwd();
	    	if(!ConstantsCommon.ONE.equals(paramPath) && ConstantsCommon.SECURITY_ENCRYPTPARAMS_DYNAMIC_PWD)
	    	{
	    		password = dynDecrypKey;
	    	}
	    	String decryptedParam = decryptNoPadding(pathEncryptedParam,password);
	    	int countHashDelimiters = StringUtils.countMatches(decryptedParam, "#");
	    	boolean isUserAvailableInSessCO = false;
	    	//the decrypted parameters should contains at least 2 # delimiters, and # should be first character 
	    	if(countHashDelimiters < 2 || !StringUtil.nullToEmpty(decryptedParam).startsWith("#"))
	    	{
	    		/*StringBuffer logDetails = new StringBuffer();
                logDetails.append((new StringBuilder("[a] 1- [")).append(pathEncryptedParam).append("]").toString());
                logDetails.append((new StringBuilder("[a] 2- [")).append(password).append("]").toString());
                logDetails.append((new StringBuilder("[a] 3- [")).append(decryptedParam).append("]").toString());
                logDetails.append((new StringBuilder("[a] 4- [")).append(countHashDelimiters).append("]").toString());
                */
                HttpSession session = request.getSession();
                if(session != null)
                {
//                    logDetails.append("[a] 5- session not null");
                    SessionCO sessCO = (SessionCO)session.getAttribute("sesVO");
                    if(sessCO != null)
                    {
                       /* logDetails.append("[a] 6- sessionCO not null");
                        logDetails.append((new StringBuilder("[a] 7- [")).append(sessCO.getUserName()).append("]").toString());
                        logDetails.append((new StringBuilder("[a] 8- [")).append(sessCO.getHttpSessionID()).append("]").toString());
                        logDetails.append((new StringBuilder("[a] 9- [")).append(sessCO.getCompanyCode()).append("]").toString());
                        logDetails.append((new StringBuilder("[a] 10- [")).append(sessCO.getBranchCode()).append("]").toString());
                        */
                        // check if attributes, at least userName filled in sessionCO, means the login page is passed
                        if(sessCO.getUserName() != null)
                        {
                        	isUserAvailableInSessCO = true;
                        }
                        
                    }/* else
                    {
                        logDetails.append("[a] 11- sessionCO null");
                    }*/
                }/* else
                {
                    logDetails.append("[a] 12- session null");
                }
                logDetails.append((new StringBuilder("[a] 13-")).append(request.getRequestURI()).toString());
                log.error(logDetails.toString());
                */
                if(isUserAvailableInSessCO)
                {
                	throw new BaseException("Path Security Error . Invalid Encrypted Parameter, Decryption not valid, URI= " + request.getRequestURI()); 
                }
                else
                {
                	// just make debugging info so that the spring security intercepter must redirect to login page
                	log.error("[SecurityUtils Debugging Only] userName not Exists in SessionCO request User="+returnUserFromSession(request)+" URI: " + request.getRequestURI()+" possibile spring security session timeout/logout");
                }
	    	}
	    	log.debug("[SecurityUtils] request URI: " + request.getRequestURI());
	    	if(log.returnInfoLevel())
	    	{
	    	    String decryptedParamToTrace = decryptedParam; 
	    	    if(!StringUtil.nullToEmpty(decryptedParamToTrace).isEmpty())
	    	    {
	    		String thePattern ="(?i)(cardNo=|card_no=)([a-zA-Z0-9.\\-\\\\\\\\*\\\\,/@$ ]*)([&]?)";
	    		thePattern = StringUtil.nullEmptyToValue(log.returnSensetiveInfoPattern(), thePattern);
	    		try
	    		{
	    		    decryptedParamToTrace = decryptedParam.replaceAll(thePattern, "$1<*****>$3");
	    		}
	    		catch(Exception e)
	    		{
	    		    log.warning("Not able to replace sensitive data for regular expression thePattern="+thePattern+" errorMessage="+e.getMessage());
	    		}
	    	    }
	    	    log.debug("[SecurityUtils] returnDecryptedNoPaddingParams, decryptedParam:" + decryptedParamToTrace);
	    	}
	    	
	    	// check if decryption is performed correctly, means the number of Hashes n decryption is 2, since if isUserAvailableInSessCO no need to throw exception
	    	// and # should be first Character
	    	if(countHashDelimiters >=2  && StringUtil.nullToEmpty(decryptedParam).startsWith("#"))
	    	{
	    		decryptedParam = decryptedParam.substring(decryptedParam.indexOf("#")+1, decryptedParam.lastIndexOf("#"));

	    		//check if contains a hashcode. no need to check if it is a number because we are already indicating it in group (-?\\d*)
	    		Pattern pattern = Pattern.compile("(.*?)(&hashcode\\=)(-?\\d*)(&_tc=)?(&sessionToken\\=)(.*?)?$", Pattern.DOTALL);
	    		Matcher matcher = pattern.matcher(decryptedParam);
	    		if (!matcher.find())               
	    		{
	    			/*StringBuffer logDetails = new StringBuffer();
	    			logDetails.append((new StringBuilder("[b] 1- [")).append(pathEncryptedParam).append("]").toString());
	    			logDetails.append((new StringBuilder("[b] 2- [")).append(password).append("]").toString());
	    			logDetails.append((new StringBuilder("[b] 3- [")).append(decryptedParam).append("]").toString());
	    			HttpSession session = request.getSession();
	    			if(session != null)
	    			{
	    				logDetails.append("[b] 4- session not null");
	    				SessionCO sessCO = (SessionCO)session.getAttribute("sesVO");
	    				if(sessCO != null)
	    				{
	    					logDetails.append("[b] 5- sessionCO not null");
	    					logDetails.append((new StringBuilder("[b] 6- [")).append(sessCO.getUserName()).append("]").toString());
	    					logDetails.append((new StringBuilder("[b] 7- [")).append(sessCO.getHttpSessionID()).append("]").toString());
	    					logDetails.append((new StringBuilder("[b] 8- [")).append(sessCO.getCompanyCode()).append("]").toString());
	    					logDetails.append((new StringBuilder("[b] 9- [")).append(sessCO.getBranchCode()).append("]").toString());
	    				} else
	    				{
	    					logDetails.append("[b] 10- sessionCO null");
	    				}
	    			} else
	    			{
	    				logDetails.append("[b] 11- session null");
	    			}
	    			logDetails.append((new StringBuilder("[b] 12-")).append(request.getRequestURI()).toString());
	    			log.error(logDetails.toString());
	    			*/
	    			throw new BaseException("Path Security Error . Invalid Encrypted Parameter,User="+returnUserFromSession(request)+" URI= " + request.getRequestURI());
	    		}

	    		int hashCodeValue = NumberUtil.parseInt(matcher.group(3));
	    		String parametersValue = matcher.group(1);
	    		//check if hashcode is correct
	    		if(hashCodeValue != parametersValue.hashCode())
	    		{
	    			/*StringBuffer logDetails = new StringBuffer();
	    			logDetails.append((new StringBuilder("[c] 1- [")).append(pathEncryptedParam).append("]").toString());
	    			logDetails.append((new StringBuilder("[c] 2- [")).append(password).append("]").toString());
	    			logDetails.append((new StringBuilder("[c] 3- [")).append(decryptedParam).append("]").toString());
	    			logDetails.append((new StringBuilder("[c] 4- [")).append(hashCodeValue).append("]").toString());
	    			logDetails.append((new StringBuilder("[c] 5- [")).append(parametersValue.hashCode()).append("]").toString());
	    			HttpSession session = request.getSession();
	    			if(session != null)
	    			{
	    				logDetails.append("[c] 6- session not null");
	    				SessionCO sessCO = (SessionCO)session.getAttribute("sesVO");
	    				if(sessCO != null)
	    				{
	    					logDetails.append("[c] 7- sessionCO not null");
	    					logDetails.append((new StringBuilder("[c] 8- [")).append(sessCO.getUserName()).append("]").toString());
	    					logDetails.append((new StringBuilder("[c] 9- [")).append(sessCO.getHttpSessionID()).append("]").toString());
	    					logDetails.append((new StringBuilder("[c] 10- [")).append(sessCO.getCompanyCode()).append("]").toString());
	    					logDetails.append((new StringBuilder("[c] 11- [")).append(sessCO.getBranchCode()).append("]").toString());
	    				} else
	    				{
	    					logDetails.append("[c] 12- sessionCO null");
	    				}
	    			} else
	    			{
	    				logDetails.append("[c] 13- session null");
	    			}
	    			logDetails.append((new StringBuilder("[c] 14-")).append(request.getRequestURI()).toString());
	    			log.error(logDetails.toString());
	    			*/
	    			throw new BaseException("Path Security Error . Invalid Encrypted Parameter,User="+returnUserFromSession(request)+" URI= " + request.getRequestURI());
	    		}
	    		//check on session token
	    		String noTokenCheck = matcher.group(4);// if _tc exists means do not check token
	    		if(StringUtil.nullToEmpty(noTokenCheck).isEmpty() && StringUtil.isNotEmpty( matcher.group(5)) )
	    		{
	    			if(StringUtil.isNotEmpty( matcher.group(6)))
	    			{
	    				String sessionToken = StringUtil.nullToEmpty((String)request.getSession().getAttribute(SecurityUtilsExt.SESSION_TOKEN_KEY));
	    				if(!sessionToken.trim().isEmpty())
	    				{    
	    					String encryptedSessionToken = matcher.group(6);
	    					String decryptedSessionToken = SecurityUtils.decryptAES(SecurityUtilsExt.returnAlgorithmSessionTokenPwd(), encryptedSessionToken);

	    					if(!sessionToken.equals(decryptedSessionToken))
	    					{
	    						throw new BaseException("Path Security Error . Invalid Session Token User="+returnUserFromSession(request)+" URI="+request.getRequestURI());
	    					}
	    				}
	    			}
	    			else
	    			{
	    				throw new BaseException("Path Security Error . Empty Session Token inURL Params User="+returnUserFromSession(request)+" URI= "+request.getRequestURI());
	    			}
	    		}

	    		String splittedArr1[] = parametersValue.split("&");
	    		for(int i = 0; i <  splittedArr1.length ; i++)
	    		{
	    			String splittedArr2[] = splittedArr1[i].split("=");
	    			if(splittedArr2.length == 2)
	    			{
	    				handleRequestParameter(parametersMap, splittedArr2[0], splittedArr2[1]);
	    			}
	    			//needed to keep empty parameters
	    			else if(splittedArr2.length == 1 && splittedArr1[i].contains("="))
	    			{
	    				handleRequestParameter(parametersMap, splittedArr2[0], "");
	    			} 
	    		}
	    	}
	    }
	    else
	    {
	    	log.error("[SecurityUtils Debugging Info] No decryption password avilable in Session attributed when Dynamic Password Encryption enabled User="+returnUserFromSession(request)+" URL="+request.getRequestURI());
	    }
	}
	return parametersMap;
    }
    
    private static void handleRequestParameter(Map<String, Object> parametersMap, String key, Object value )
    {
	 if(parametersMap.containsKey(key))
	 {
		Object currentObj = parametersMap.get(key);
		if(currentObj instanceof String[])
		{
		    String[] currentArray = (String[])currentObj;
		    List<String> currentArrayList = Arrays.asList(currentArray);
		    List<String> st = new ArrayList<String>();
		    st.addAll(currentArrayList);
		    st.add(StringUtil.nullEmptyToValue(value, ""));
		    String[] newArray = st.toArray(new String[0]);
		    parametersMap.put(key,newArray);
		}
		else
		{
		    String[] newArray = {StringUtil.nullEmptyToValue(currentObj, "") , StringUtil.nullEmptyToValue(value, "")};
		    parametersMap.put(key, newArray);
		}
	 }
	 else
	 {
	     parametersMap.put(key, value);
	 }
    }
    
    public static String returnEncryptedNoPaddingUrl(String actionUrl, boolean encodeURIComponent, HttpSession session)throws Exception
    {
	if(StringUtil.isNotEmpty(actionUrl) && actionUrl.contains("?")) 
	{
	    String newActionURL = actionUrl.substring(0,actionUrl.indexOf("?"));
	    String paramsURL = actionUrl.substring(actionUrl.indexOf("?") +1,actionUrl.length());
	    String encryptedParam = returnEncryptedNoPaddingParams(paramsURL,encodeURIComponent,true,session);
	    actionUrl = newActionURL + "?" + encryptedParam;
	    return actionUrl;
	}
	return actionUrl;
    }
    
    public static String returnAutomaticLoginEncryptedParam(String parameters)throws Exception
    {
	StringBuffer paramBuffer = new StringBuffer(parameters);
	int hashCode = paramBuffer.toString().hashCode();
	paramBuffer.append("&hashcode=").append(hashCode);
	paramBuffer.append("&_tc=&sessionToken=");
	return "PATHPARAM=".concat(URLEncoder.encode(SecurityUtils.encryptNoPadding("#" + paramBuffer + "#", null),
		SecurityUtilsExt.DEFAULT_ENCODING));

    }
    
    /*Security Encryption for penetration test*/
    
    /**************************************************************************************************************************/
//    public static void main(String[] args)
//    {
//	try
//	{
//	String theName = "Deniskéم";
//	String enc = encryptAES("PathSolutions123", theName);
//	String dec = decryptAES("PathSolutions123", enc);
//	System.out.println("enc "+enc +"\n dec "+dec);
//	}
//	catch(Exception e)
//	{
//	    e.printStackTrace();
//	}
//    }
}
