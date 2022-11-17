package com.path.bo.customintegration.webservices.itr;

import java.util.HashMap;

//import com.path.vo.customintegration.webservices.itr.GetExtRunningDateRequest;
//import com.path.vo.customintegration.webservices.itr.GetExtRunningDateResponse;
//TP#339072 - HGhazal - Get external running date - 28/10/2015 .
/**
 * Copyright 2018, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author:Abdel Wahab El Farran
 * Date:25/09/2018
 * Reference: 
 * Description: copy from Integration R14.1 to CIS R14.1 + convert object to hashmap
 */

public interface ApiGetExtRunningDateBO {
	//public GetExtRunningDateResponse processGetExtRunDate( GetExtRunningDateRequest req) throws Exception;
	public HashMap<String, Object> processGetExtRunDate(HashMap<String, Object> HashiIn) throws Exception;

}

