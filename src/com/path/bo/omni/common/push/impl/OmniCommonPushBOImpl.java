/**
 * Copyright 2018, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: gilbertandary
 *
 * OmniCommonPushBOImpl.java
 *
 * 
 */
package com.path.bo.omni.common.push.impl;

import com.path.bo.common.WebServiceCommonBO;
import com.path.bo.omni.common.base.OmniBaseBO;
import com.path.bo.omni.common.push.OmniCommonPushBO;
import com.path.dao.omni.common.pull.OmniCommonPullDAO;
import com.path.dao.omni.common.push.OmniCommonPushDAO;
import com.path.lib.common.util.ApplicationContextProvider;
import com.path.vo.omni.common.OmniCommonCO;
import com.path.vo.omni.common.base.OmniBaseSC;

public class OmniCommonPushBOImpl extends OmniBaseBO implements OmniCommonPushBO
{
    private OmniCommonPushDAO omniCommonPushDAO;
    private OmniCommonPullDAO omniCommonPullDAO;
    private static WebServiceCommonBO commonWebServiceBO = (WebServiceCommonBO) ApplicationContextProvider
		.getApplicationContext().getBean("webServiceCommonBO");

    


    public void setOmniCommonPushDAO(OmniCommonPushDAO omniCommonPushDAO)
    {
	this.omniCommonPushDAO = omniCommonPushDAO;
    }

    

    public void setOmniCommonPullDAO(OmniCommonPullDAO omniCommonPullDAO)
    {
	this.omniCommonPullDAO = omniCommonPullDAO;
    }



    @Override
    public OmniCommonCO pushNotification(OmniBaseSC sc)
    {
	// TODO Auto-generated method stub
	return null;
    }

   

}
