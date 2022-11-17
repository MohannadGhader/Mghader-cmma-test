package com.path.bo.integration.ext;

import com.path.vo.integration.GetExtRunningDateRequest;
import com.path.vo.integration.GetExtRunningDateResponse;
//TP#339072 - HGhazal - Get external running date - 28/10/2015 
public interface ApiGetExtRunningDateBO {
	public GetExtRunningDateResponse processGetExtRunDate( GetExtRunningDateRequest req) throws Exception;
}

