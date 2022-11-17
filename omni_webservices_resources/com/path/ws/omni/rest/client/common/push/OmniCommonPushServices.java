package com.path.ws.omni.rest.client.common.push;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.path.ws.omni.vo.common.OmniCommonResponse;
import com.path.ws.omni.vo.common.RequestBaseObject;

@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_HTML })
public interface OmniCommonPushServices
{
    /*@POST
    @Path("/widgets/")
   // public OmniWidgetsResponse customizeWidgets_service(OmniWidgetsRequest omniWidgetsRequest) throws BaseException;
*/
    
    @POST
    @Path("/pushNotification/")
    public OmniCommonResponse pushNotification_service(RequestBaseObject request);
   
}
