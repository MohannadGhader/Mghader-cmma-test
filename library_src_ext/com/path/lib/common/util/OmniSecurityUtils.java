package com.path.lib.common.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.codec.Base64;


/**
 * 
 * Copyright 2018, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: ChadiAssaf
 *
 * OmniSecurityUtils.java used to
 */
public final class OmniSecurityUtils
{
    public final static String ALGORITHM = "AES";
    public final static String DEFAULT_ENCODING = "UTF-8";
    public final static String SESSION_TOKEN_KEY = "PATH_SESSION_TOKEN";
    public final static String ALGORITHM_CBC_PKCS5PADDING = "AES/CBC/PKCS5Padding";
    public final static String PATH_ENC_PWD = "PATH_ENC_PWD";
    public static String IV_KEY = "";

    /**
     * Private constructor to prevent class from instantiation
     */
    private OmniSecurityUtils()
    {
	// no need to include anything since this class is used for PB to call
	// security encryption process
    }

    /**
     * 
     * Used for encryption using AES Java Algorithm
     * 
     * @param theKey
     * @param theValue
     * @return
     */
    public static String encryptAES(String theKey, String theValue, String encodeUTF8) throws Exception
    {
	if(theKey != null && theKey.length() != 16)
	{
	    throw new Exception("ERROR: Key should be exact of 16 Character Length");
	}
	Cipher c = Cipher.getInstance(ALGORITHM_CBC_PKCS5PADDING);
	Key aesKey = new SecretKeySpec(theKey.getBytes(DEFAULT_ENCODING), ALGORITHM);
	byte[] ivByte = IV_KEY.getBytes();
	IvParameterSpec initialisationVector = new IvParameterSpec(ivByte);
	c.init(Cipher.ENCRYPT_MODE, aesKey, initialisationVector);
	byte[] encVal = c.doFinal(theValue.getBytes(DEFAULT_ENCODING));
	return encodeB64(encVal);
    }

    /**
     * 
     * Used for Base 64 encoding
     * 
     * @param theBytes
     * @return
     */
    public static String encodeB64(byte[] theBytes) throws Exception
    {
	String result = "";
	if(theBytes != null)
	{
	    result = new String(Base64.encode(theBytes), DEFAULT_ENCODING);
	}
	return result;
    }

    /**
     * 
     * Used for decryption of encrypted Data using particular Key
     * 
     * @param theKey
     * @param encryptedData
     * @return
     * @throws Exception
     */
    public static String decryptAES(String theKey, String encryptedData) throws Exception
    {
	if(theKey != null && theKey.length() != 16)
	{
	    throw new Exception("ERROR: Key should be of 16 Character Length");
	}
	Key aesKey = new SecretKeySpec(theKey.getBytes(DEFAULT_ENCODING), ALGORITHM);
	Cipher c = Cipher.getInstance(ALGORITHM_CBC_PKCS5PADDING);
	byte[] ivByte = IV_KEY.getBytes();
	IvParameterSpec initialisationVector = new IvParameterSpec(ivByte);
	c.init(Cipher.DECRYPT_MODE, aesKey, initialisationVector);
	byte[] decodedValue = Base64.decode(encryptedData.getBytes(DEFAULT_ENCODING));
	byte[] decValue = c.doFinal(decodedValue);
	return new String(decValue, DEFAULT_ENCODING);
    }
    
}
