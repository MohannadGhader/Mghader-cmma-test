package com.path.bo.integration.ext;

import java.util.HashMap;

import com.path.lib.common.exception.BaseException;

/**
 * this class will be called from outside Integration via rmi
 * 
 * @author awfarran
 *
 */
public interface ApiServicesExt {

	public HashMap<String, Object> processPullGEDFileLocation(HashMap<String, Object> hashIn) throws BaseException;
	
}
