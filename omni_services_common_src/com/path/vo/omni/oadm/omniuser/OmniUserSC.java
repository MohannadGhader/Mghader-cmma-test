package com.path.vo.omni.oadm.omniuser;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.path.vo.omni.common.base.OmniBaseSC;
import com.path.ws.security.common.hybridkey.co.HybridKeyLoginRequestInterface;
/**
 * 
 * Copyright 2018, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: Suhail.Shoukathali
 *
 * OmniUserSC.java used to
 */
public class OmniUserSC extends OmniBaseSC implements HybridKeyLoginRequestInterface
{
    private BigDecimal omniUserCode;
    private BigDecimal lovTypeId;
    private String statusCode;
    private String status;
    private String email;
    private String applicationCode;
    private BigDecimal cifTypeCode;
    private BigDecimal userCode;
    private BigDecimal businessProfileTableRef;
    private String password;
    private BigDecimal customerCode;
    private String verifyType;
    private BigDecimal userPkId;
    private BigDecimal customerId;
    private String verifyValue;
    private String publicKey; 
    private String adminUserId; 
    private String userType;
    private BigDecimal secQnId;
    private String secAnswer;
    private String corporateId;
    private Date dateUpdated;   
    private Date lastLoginDate;
    private String caseSensitive;
    private BigDecimal APP_ID;
    private Boolean isAdmin;
    private String AppList;
    private String securityType;
    private BigDecimal numberOfTrackedUpdates;
    private String securityAnswer;
    private BigDecimal limitPackageTableRef;
    // TP # 833558
    private String customerName;
    private String shortName;
    // TP # 833558
    // #BUG 847308
    private String showAllCorporateValue;
    private Integer autoLogin = 0;
    private Integer forceLogout = 0;
    private String rememberMe = "0";
    OmniUserCO omniUserCO = new OmniUserCO();
    private List<String> mobilePhonesList;
    private List<String> emailIdsList;
    private String subscriberId;
    private String packageId;
    
    public String getUserType()
    {
        return userType;
    }
    public void setUserType(String userType)
    {
        this.userType = userType;
    }
    public String getAdminUserId()
    {
        return adminUserId;
    }
    public void setAdminUserId(String adminUserId)
    {
        this.adminUserId = adminUserId;
    }
    public BigDecimal getOmniUserCode()
    {
        return omniUserCode;
    }
    public void setOmniUserCode(BigDecimal omniUserCode)
    {
        this.omniUserCode = omniUserCode;
    }
    public BigDecimal getLovTypeId()
    {
        return lovTypeId;
    }
    public void setLovTypeId(BigDecimal lovTypeId)
    {
        this.lovTypeId = lovTypeId;
    }
    public String getStatusCode()
    {
        return statusCode;
    }
    public void setStatusCode(String statusCode)
    {
        this.statusCode = statusCode;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public BigDecimal getCifTypeCode()
    {
        return cifTypeCode;
    }
    public void setCifTypeCode(BigDecimal cifTypeCode)
    {
        this.cifTypeCode = cifTypeCode;
    }
    public BigDecimal getUserCode()
    {
        return userCode;
    }
    public void setUserCode(BigDecimal userCode)
    {
        this.userCode = userCode;
    }
    public String getApplicationCode()
    {
        return applicationCode;
    }
    public void setApplicationCode(String applicationCode)
    {
        this.applicationCode = applicationCode;
    }
    public BigDecimal getBusinessProfileTableRef()
    {
        return businessProfileTableRef;
    }
    public void setBusinessProfileTableRef(BigDecimal businessProfileTableRef)
    {
        this.businessProfileTableRef = businessProfileTableRef;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

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
    public String getVerifyType()
    {
        return verifyType;
    }
    public void setVerifyType(String verifyType)
    {
        this.verifyType = verifyType;
    }
    public BigDecimal getUserPkId()
    {
        return userPkId;
    }
    public void setUserPkId(BigDecimal userPkId)
    {
        this.userPkId = userPkId;
    }
    public BigDecimal getCustomerId()
    {
        return customerId;
    }
    public void setCustomerId(BigDecimal customerId)
    {
        this.customerId = customerId;
    }
    public String getVerifyValue()
    {
        return verifyValue;
    }
    public void setVerifyValue(String verifyValue)
    {
        this.verifyValue = verifyValue;
    }
    public String getPublicKey()
    {
        return publicKey;
    }
    public void setPublicKey(String publicKey)
    {
        this.publicKey = publicKey;
    }    
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
    
    public String getCorporateId()
    {
        return corporateId;
    }
    public void setCorporateId(String corporateId)
    {
        this.corporateId = corporateId;
    }
    public BigDecimal getSecQnId()
    {
        return secQnId;
    }
    public void setSecQnId(BigDecimal secQnId)
    {
        this.secQnId = secQnId;
    }
    public String getSecAnswer()
    {
        return secAnswer;
    }
    public void setSecAnswer(String secAnswer)
    {
        this.secAnswer = secAnswer;
    }
    
    public Date getLastLoginDate()
    {
        return lastLoginDate;
    }
    public void setLastLoginDate(Date lastLoginDate)
    {
        this.lastLoginDate = lastLoginDate;
    }
    public String getCaseSensitive()
    {
	return caseSensitive;
    }
    public void setCaseSensitive(String caseSensitive)
    {
	this.caseSensitive = caseSensitive;
    }
    public BigDecimal getAPP_ID()
    {
        return APP_ID;
    }
    public void setAPP_ID(BigDecimal aPP_ID)
    {
        APP_ID = aPP_ID;
    }
    public Boolean getIsAdmin()
    {
        return isAdmin;
    }
    public void setIsAdmin(Boolean isAdmin)
    {
        this.isAdmin = isAdmin;
    }
    public String getAppList() 
   	{
   		return AppList;
   	}
   	public void setAppList(String appList)
   	{
   		AppList = appList;
   	}
	public String getSecurityType() {
		return securityType;
	}
	public void setSecurityType(String securityType) {
		this.securityType = securityType;
	}
	public BigDecimal getNumberOfTrackedUpdates() {
		return numberOfTrackedUpdates;
	}
	public void setNumberOfTrackedUpdates(BigDecimal numberOfTrackedUpdates) {
		this.numberOfTrackedUpdates = numberOfTrackedUpdates;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	public BigDecimal getLimitPackageTableRef() {
		return limitPackageTableRef;
	}
	public void setLimitPackageTableRef(BigDecimal limitPackageTableRef) {
		this.limitPackageTableRef = limitPackageTableRef;
	}
	public String getShowAllCorporateValue() {
		return showAllCorporateValue;
	}
	public void setShowAllCorporateValue(String showAllCorporateValue) {
		this.showAllCorporateValue = showAllCorporateValue;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getAutoLogin() {
		return autoLogin;
	}
	public void setAutoLogin(Integer autoLogin) {
		this.autoLogin = autoLogin;
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
	public OmniUserCO getOmniUserCO()
	{
	    return omniUserCO;
	}
	public void setOmniUserCO(OmniUserCO omniUserCO)
	{
	    this.omniUserCO = omniUserCO;
	}
	public String getSubscriberId()
	{
	    return subscriberId;
	}
	public void setSubscriberId(String subscriberId)
	{
	    this.subscriberId = subscriberId;
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
	public String getShortName()
	{
	    return shortName;
	}
	public void setShortName(String shortName)
	{
	    this.shortName = shortName;
	}
	public String getPackageId()
	{
	    return packageId;
	}
	public void setPackageId(String packageId)
	{
	    this.packageId = packageId;
	}
	@Override
	public String getDeviceIp()
	{
	    // TODO Auto-generated method stub
	    return null;
	}
	@Override
	public String getUdid()
	{
	    // TODO Auto-generated method stub
	    return null;
	}
	@Override
	public String getUserName()
	{
	    // TODO Auto-generated method stub
	    return null;
	}
	
   	
}
