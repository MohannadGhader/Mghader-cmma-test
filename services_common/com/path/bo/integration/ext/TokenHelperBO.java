package com.path.bo.integration.ext;

import com.path.vo.integration.*;
//TP#330603 Rkhalil - token helper - 09/09/2015 
public interface TokenHelperBO {
	public TokenHelperResponse processTokenValidation( TokenHelperRequest req) throws Exception;
}
