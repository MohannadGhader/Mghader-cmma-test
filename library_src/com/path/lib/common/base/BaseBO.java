package com.path.lib.common.base;

import java.util.HashMap;

import com.path.bo.common.CommonLibBO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.log.Log;

public class BaseBO {
	protected final static Log log = Log.getInstance();

	// common bean to be used in all BOImpl where we need to call the common CRUD
	// methods
	protected BaseDAOInterface genericDAO;

	// common bean to be used in all BOImpl where we need to call general common
	// methods
	protected CommonLibBO commonLibBO;

	public void setGenericDAO(BaseDAOInterface genericDAO) {
		this.genericDAO = genericDAO;
	}

	public void setCommonLibBO(CommonLibBO commonLibBO) {
		this.commonLibBO = commonLibBO;
	}

}
