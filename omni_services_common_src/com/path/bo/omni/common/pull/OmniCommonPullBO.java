/**
 *
 * OmniCommonPullBO.java
 *
 * 
 */
package com.path.bo.omni.common.pull;

import java.math.BigDecimal;

import com.path.lib.common.exception.BaseException;
import com.path.vo.omni.common.OmniCommonCO;
import com.path.vo.omni.common.base.OmniBaseSC;
import com.path.vo.omni.common.omniuser.OmniCommonUserSC;

public interface OmniCommonPullBO
{

    public String returnAppCustomerStatus(OmniBaseSC criteria) throws BaseException;

    public String returnAppCustomerName(OmniBaseSC criteria) throws BaseException;


    public BigDecimal returnAppChnlId(OmniBaseSC omniBaseSC) throws BaseException;


    public OmniCommonCO returnCommonOperMappingId(OmniCommonUserSC mappingSC) throws BaseException;


    public BigDecimal returnUnReadNotificationCount(OmniBaseSC omniBaseSC) throws BaseException;
}
