package com.path.lib.common.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.jasypt.properties.PropertyValueEncryptionUtils;

import com.path.bo.common.ConstantsCommon;
import com.path.lib.log.Log;

/**
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: marwanmaddah DecryptionUtil.java used to do the decryption features
 */

public final class DecryptionUtil
{
    /**
     * Private constructor to prevent class from instantiation
     */
    private DecryptionUtil()
    {
	Log.getInstance().warning("This class is utility and cannot be instantiated");
    }
    /**
     * 
     * @author marwanmaddah
     * @date Dec 2, 2013
     * @param file
     * @throws Exception void
     * 
     */
    private static final Integer ENCRYPTION_ITERATIONS = 50000;
    private static final String ENCRYPTION_ALGORITHM = "PBEWithMD5AndDES";

    public static InputStream decryptionManagement(InputStream inputStream)
    {
	InputStream result = null;
	Scanner scanner = null;
	try
	{
	    scanner = new Scanner(inputStream, ConstantsCommon.FILE_ENCODING);
	    String fileContent = scanner.useDelimiter("\\A").next();
	    String unencrypted_string = decryptedString(fileContent);
	    result = new ByteArrayInputStream(unencrypted_string.getBytes(ConstantsCommon.FILE_ENCODING));
	}
	catch(Exception ex)
	{
	    Log.getInstance().error(ex, "Error in decryption management");
	}
	finally
	{
	    if(scanner != null)
	    {
		scanner.close();
	    }
	}
	return result;
    }

    /**
     * 
     * @author marwanmaddah
     * @date Dec 3, 2013
     * @param encrypted_string
     * @return String
     * 
     */
    private static String decryptedString(String encrypted_string) throws Exception
    {

	String unencrypted_string = "";
	SimplePBEConfig config = new SimplePBEConfig();
	config.setAlgorithm(ENCRYPTION_ALGORITHM);
	config.setKeyObtentionIterations(ENCRYPTION_ITERATIONS);

	config.setPassword("path_" + ConstantsCommon.MAPPERS_ENCRYPTION_PASS + "_denwan");

	StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
	encryptor.setConfig(config);
	encryptor.initialize();

	unencrypted_string = PropertyValueEncryptionUtils.decrypt(encrypted_string, encryptor);
	return unencrypted_string;
    }

}
