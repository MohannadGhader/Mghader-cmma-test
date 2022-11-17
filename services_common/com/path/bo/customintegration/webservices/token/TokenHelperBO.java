package com.path.bo.customintegration.webservices.token;

import java.util.HashMap;

import com.path.bo.customintegration.webservices.token.TokenHelperBO;
//TP#330603 Rkhalil - token helper - 09/09/2015 

/**
 * Copyright 2018, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author:Abdel Wahab El Farran
 * Date:25/09/2018
 * Reference: 
 * Description: copy from Integration R14.1 to CIS R14.1 + convert object to hashmap
 */

public interface TokenHelperBO {
	//public TokenHelperResponse processTokenValidation( TokenHelperRequest req) throws Exception;	
	public /*TokenHelperResponse*/ HashMap<String, Object> processTokenValidation(HashMap<String, Object> hashIn_TokenHelperRequest) throws Exception;
	
}
