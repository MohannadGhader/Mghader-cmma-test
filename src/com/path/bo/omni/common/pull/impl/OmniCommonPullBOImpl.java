/**
 * Copyright 2018, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: gilbertandary
 *
 * OmniCommonPullBOImpl.java
 *
 * 
 */
package com.path.bo.omni.common.pull.impl;

import java.math.BigDecimal;

import com.path.bo.common.WebServiceCommonBO;
import com.path.bo.omni.common.base.OmniBaseBO;
import com.path.bo.omni.common.pull.OmniCommonPullBO;
import com.path.dao.omni.common.pull.OmniCommonPullDAO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.ApplicationContextProvider;
import com.path.vo.omni.common.OmniCommonCO;
import com.path.vo.omni.common.base.OmniBaseSC;
import com.path.vo.omni.common.omniuser.OmniCommonUserSC;

public class OmniCommonPullBOImpl extends OmniBaseBO implements OmniCommonPullBO
{
	private OmniCommonPullDAO omniCommonPullDAO;
	// Added by Richie for #TP 660925
	// end Richie
	private static WebServiceCommonBO commonWebServiceBO = (WebServiceCommonBO) ApplicationContextProvider
		.getApplicationContext().getBean("webServiceCommonBO");


	
	public void setOmniCommonPullDAO(OmniCommonPullDAO omniCommonPullDAO)
	{
		this.omniCommonPullDAO = omniCommonPullDAO;
	}

	
	private String getFileExtension(String fileName)
	{
		int lastIndexOf = fileName.lastIndexOf(".");
		if(lastIndexOf == -1)
		{
			return "";
		}
		return fileName.substring(lastIndexOf);
	}

	



	@Override
	public String returnAppCustomerStatus(OmniBaseSC criteria) throws BaseException
	{
	    // TODO Auto-generated method stub
	    return null;
	}


	@Override
	public String returnAppCustomerName(OmniBaseSC criteria) throws BaseException
	{
	    // TODO Auto-generated method stub
	    return null;
	}


	@Override
	public BigDecimal returnAppChnlId(OmniBaseSC omniBaseSC) throws BaseException
	{
	    // TODO Auto-generated method stub
	    return null;
	}


	@Override
	public OmniCommonCO returnCommonOperMappingId(OmniCommonUserSC mappingSC) throws BaseException
	{
	    // TODO Auto-generated method stub
	    return null;
	}


	@Override
	public BigDecimal returnUnReadNotificationCount(OmniBaseSC omniBaseSC) throws BaseException
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	
	
}