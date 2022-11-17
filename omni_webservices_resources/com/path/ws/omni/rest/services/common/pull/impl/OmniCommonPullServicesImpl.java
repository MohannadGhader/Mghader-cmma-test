package com.path.ws.omni.rest.services.common.pull.impl;

import com.path.bo.omni.common.pull.OmniCommonPullBO;
import com.path.lib.common.base.OmniBaseServices;
import com.path.lib.common.exception.BaseException;
import com.path.ws.omni.rest.client.common.pull.OmniCommonPullServices;
import com.path.ws.omni.vo.common.OmniCommonRequest;
import com.path.ws.omni.vo.common.OmniCommonResponse;

//OmniCommonPullWebServices.xml
public class OmniCommonPullServicesImpl extends OmniBaseServices implements OmniCommonPullServices
{

    private OmniCommonPullBO omniCommonPullBO;
    
   

    public void setOmniCommonPullBO(OmniCommonPullBO omniCommonPullBO)
    {
	this.omniCommonPullBO = omniCommonPullBO;
    }



    @Override
    public OmniCommonResponse returnUnReadNotificationCount_service(OmniCommonRequest request) throws BaseException
    {
	// TODO Auto-generated method stub
	return null;
    }


   
	
}
