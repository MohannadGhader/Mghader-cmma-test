package com.path.ws.omni.rest.client.common.pull;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.path.lib.common.exception.BaseException;
import com.path.ws.omni.vo.common.OmniCommonRequest;
import com.path.ws.omni.vo.common.OmniCommonResponse; 

@Consumes("application/json")
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_HTML })
public interface OmniCommonPullServices
{
    
    @POST
    @Path("/returnUnReadNotificationCount/")
    public OmniCommonResponse returnUnReadNotificationCount_service(OmniCommonRequest request) throws BaseException;
    
}