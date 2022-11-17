package com.path.ws.omni.vo.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OmniUserResponse extends ResponseBaseObject
{
    private String status;
    
    private String channelName;
    
    private Date createdDate;
    
    private BigDecimal userCode;
    
    private String statusDesc;
    
    private Map<String,String> allowedAppChannels = new HashMap<String,String>();
    
    private String securityQuestionId;
    private String securityQuestionDescription;
    
    private BigDecimal ctrSecurityQuestionHit;
    private List<String> mobilePhonesList = new ArrayList<String>();
    private List<String> emailIdsList = new ArrayList<String>();
    private String briefName;
    private String longName;
    private String subscriberId;
    private HashMap<String, Object> subscriberInfo;
    private String subscriptionId;
    private Integer remainingAttempt;
    
    
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getChannelName()
    {
        return channelName;
    }

    public void setChannelName(String channelName)
    {
        this.channelName = channelName;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }

    public Map<String, String> getAllowedAppChannels()
    {
        return allowedAppChannels;
    }

    public void setAllowedAppChannels(Map<String, String> allowedAppChannels)
    {
        this.allowedAppChannels = allowedAppChannels;
    }

    public BigDecimal getUserCode()
    {
        return userCode;
    }

    public void setUserCode(BigDecimal userCode)
    {
        this.userCode = userCode;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc)
    {
        this.statusDesc = statusDesc;
    }

	public String getSecurityQuestionId() {
		return securityQuestionId;
	}

	public void setSecurityQuestionId(String securityQuestionId) {
		this.securityQuestionId = securityQuestionId;
	}

	public String getSecurityQuestionDescription() {
		return securityQuestionDescription;
	}

	public void setSecurityQuestionDescription(String securityQuestionDescription) {
		this.securityQuestionDescription = securityQuestionDescription;
	}

	public BigDecimal getCtrSecurityQuestionHit() {
		return ctrSecurityQuestionHit;
	}

	public void setCtrSecurityQuestionHit(BigDecimal ctrSecurityQuestionHit) {
		this.ctrSecurityQuestionHit = ctrSecurityQuestionHit;
	}

	public List<String> getMobilePhonesList()
	{
	    return mobilePhonesList;
	}

	public void setMobilePhonesList(List<String> mobilePhonesList)
	{
	    this.mobilePhonesList = mobilePhonesList;
	}

	public List<String> getEmailIdsList()
	{
	    return emailIdsList;
	}

	public void setEmailIdsList(List<String> emailIdsList)
	{
	    this.emailIdsList = emailIdsList;
	}

	public String getBriefName()
	{
	    return briefName;
	}

	public void setBriefName(String briefName)
	{
	    this.briefName = briefName;
	}

	public String getLongName()
	{
	    return longName;
	}

	public void setLongName(String longName)
	{
	    this.longName = longName;
	}

	public String getSubscriberId()
	{
	    return subscriberId;
	}

	public void setSubscriberId(String subscriberId)
	{
	    this.subscriberId = subscriberId;
	}

	public HashMap<String, Object> getSubscriberInfo() {
		return subscriberInfo;
	}

	public void setSubscriberInfo(HashMap<String, Object> subscriberInfo) {
		this.subscriberInfo = subscriberInfo;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public Integer getRemainingAttempt() {
		return remainingAttempt;
	}

	public void setRemainingAttempt(Integer remainingAttempt) {
		this.remainingAttempt = remainingAttempt;
	}
    
}
