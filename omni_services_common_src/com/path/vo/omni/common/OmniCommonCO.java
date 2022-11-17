package com.path.vo.omni.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.path.vo.omni.common.base.OmniBaseVO;


/**
 * 
 * Copyright 2017, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: ChadiAssaf
 *
 * OmniCommonCO.java used to
 */
public class OmniCommonCO extends OmniBaseVO
{
    private BigDecimal compCode;
    private BigDecimal counterId;
    private BigDecimal channelId;
    private BigDecimal appId;
    private String omniChannelName;
    private String omniAppName;
    private String omniAppDesc;
    private String multipleUserApp;
    private String omniChannelDesc;
    private String packageId;
    private String maker;
    private String checker;
    private List customList;
    private HashMap<String, Object> commonResponseList = new HashMap<String, Object>();
    public BigDecimal getCounterId()
    {
        return counterId;
    }

    public void setCounterId(BigDecimal counterId)
    {
        this.counterId = counterId;
    }

    public BigDecimal getChannelId()
    {
        return channelId;
    }

    public void setChannelId(BigDecimal channelId)
    {
        this.channelId = channelId;
    }

    public BigDecimal getAppId()
    {
        return appId;
    }

    public void setAppId(BigDecimal appId)
    {
        this.appId = appId;
    }

    public String getOmniChannelName()
    {
        return omniChannelName;
    }

    public void setOmniChannelName(String omniChannelName)
    {
        this.omniChannelName = omniChannelName;
    }

    public String getOmniAppName()
    {
        return omniAppName;
    }

    public void setOmniAppName(String omniAppName)
    {
        this.omniAppName = omniAppName;
    }

    public String getOmniAppDesc()
    {
        return omniAppDesc;
    }

    public void setOmniAppDesc(String omniAppDesc)
    {
        this.omniAppDesc = omniAppDesc;
    }

    public String getOmniChannelDesc()
    {
        return omniChannelDesc;
    }

    public void setOmniChannelDesc(String omniChannelDesc)
    {
        this.omniChannelDesc = omniChannelDesc;
    }

    public String getMultipleUserApp()
    {
        return multipleUserApp;
    }

    public void setMultipleUserApp(String multipleUserApp)
    {
        this.multipleUserApp = multipleUserApp;
    }

    public BigDecimal getCompCode()
    {
	return compCode;
    }

    public void setCompCode(BigDecimal compCode)
    {
	this.compCode = compCode;
    }

    public String getMaker()
    {
        return maker;
    }

    public void setMaker(String maker)
    {
        this.maker = maker;
    }

    public String getChecker()
    {
        return checker;
    }

    public void setChecker(String checker)
    {
        this.checker = checker;
    }

    public List getCustomList()
    {
        return customList;
    }

    public void setCustomList(List customList)
    {
        this.customList = customList;
    }

	public HashMap<String, Object> getCommonResponseList() {
		return commonResponseList;
	}

	public void setCommonResponseList(HashMap<String, Object> commonResponseList) {
		this.commonResponseList = commonResponseList;
	}

	public String getPackageId()
	{
	    return packageId;
	}

	public void setPackageId(String packageId)
	{
	    this.packageId = packageId;
	}
    




    
    
}