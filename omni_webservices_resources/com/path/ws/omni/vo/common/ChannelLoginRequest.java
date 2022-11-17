package com.path.ws.omni.vo.common;

import java.math.BigDecimal;

public class ChannelLoginRequest extends RequestBaseObject
{
    private String password;
    private BigDecimal customerCode;
    private String type;
    private String publicKey;
    private String corporateId;
    //the following if verify password is called from authentication matrix
    private String value;
    
    private Integer forceLogout = 0;
    private String rememberMe = "0";
    private Integer autoLogin = 0;
    
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public BigDecimal getCustomerCode()
    {
        return customerCode;
    }

    public void setCustomerCode(BigDecimal customerCode)
    {
        this.customerCode = customerCode;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getPublicKey()
    {
        return publicKey;
    }

    public void setPublicKey(String publicKey)
    {
        this.publicKey = publicKey;
    }

    public String getCorporateId()
    {
        return corporateId;
    }

    public void setCorporateId(String corporateId)
    {
        this.corporateId = corporateId;
    }

    public String getValue()
    {
	return value;
    }

    public void setValue(String value)
    {
	this.value = value;
    }

	public Integer getForceLogout() {
		return forceLogout;
	}

	public void setForceLogout(Integer forceLogout) {
		this.forceLogout = forceLogout;
	}

	public String getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

	public Integer getAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(Integer autoLogin) {
		this.autoLogin = autoLogin;
	}

    
}