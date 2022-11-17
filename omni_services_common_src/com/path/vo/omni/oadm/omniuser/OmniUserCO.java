package com.path.vo.omni.oadm.omniuser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.path.dbmaps.vo.OC_USRVO;
import com.path.vo.omni.common.OmniCommonCO;
import com.path.vo.omni.common.base.OmniBaseVO;
import com.path.ws.security.common.hybridkey.co.HybridKeyLoginResponseInterface;

/**
 * 
 * Copyright 2018, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: Suhail.Shoukathali and Neena.David
 *
 *          OmniUserCO.java used to
 */
/* nabil feghali - OMNI common security  */
public class OmniUserCO extends OmniBaseVO implements HybridKeyLoginResponseInterface
{
    
    //Hisham Omar - add the co of the customer information 
    private String deleteMsg;
    private String statusDesc;
    private String statusColorCode;
    private BigDecimal businessProfileCode;
    private BigDecimal corporateBusinessProfileCode; // #TP 844503
    private String businessProfileName;
    private BigDecimal subProfileCode;
    private String subProfileName;
    private BigDecimal customerCode;
    private String customerName;
    private String cifName;
    private String customerCifNo;
    private OC_USRVO omniUserVO = new OC_USRVO();
    private List<OmniCommonCO> omniChannelList = new ArrayList<>();
    private List<OmniCommonCO> omniApplicationList = new ArrayList<>();
    private String appList;
    private String channelList;
    private Date systemDate;
    private String notificationMsg;
    private String multipleUserApp;
    private String message;
    private String reason;
    private Boolean isUpdate = false;
    private BigDecimal userCode;
    private String deliverOptionCode;
    private String deliveryOption;
    private BigDecimal userPkId;
    private BigDecimal customerId;
    private BigDecimal businessProfileId;
    private BigDecimal subProfileId;
    private String subLimitName;
    private BigDecimal subLimitId;
    private BigDecimal subLimitCode;
    private BigDecimal reasonForDeactivation;
    private String reasonForDeactivationDetail;
    private String showSubLimit;
    private String sadsUserFirstName;
    private String name;


    private String serverPublicKey;
    private String userTypeDesc;
    private Boolean isAdmin;
    private BigDecimal chargeAmout;
    private String operSrc;
    private BigDecimal totalBranchCharge;
    private BigDecimal exchangeRate;
    private String channelActivationChargesGridData;
    private List<OmniUserCO> channelActivationChargesGridDataList=new ArrayList<>();

    
    private String accountList;
    //key = app_id, value = channelIds separated by comma
    private Map<String,String> allowedAppChannels = new HashMap<String,String>();
    private String appChannelIds;
    private BigDecimal chargePackageId;
    
    private String verificationImageData;
    private BigDecimal businessProfileIdCorp;
    
    private String codesInclude;
    
    private BigDecimal limitPackageId;
    private String corporateId;
    private String securityQuestionId;
    private String securityQuestionDescription;
    
    /** Neena - 621272**/
    private Boolean isShowJoint = false;
    
    /** US - 800598 Alert Languages**/
    private String alertLanguageCode;
    private String alertLanguage;
    
    // #BUG 852895
    private String mobile;
    private String tel;
    private String branchCode;
    private BigDecimal oldPkId;
    private Integer userIsBlocked;
    private Integer remainingAttempt;
    private Integer outputCode;
    private String outputNotification;
    private String outputType;
    
    
    private Boolean isPasswordExpired;
    private BigDecimal cifBranch;
    private String profileImage;
    private HashMap<String, Object> subscriberInfo;
    
    /* nabil feghali - OMNI common security  */
    private String serverLoginToken;
    
    private String customerDetailName;
    private String deductChargeRequired;
    


    public Boolean getIsAdmin()
    {
	return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin)
    {
	this.isAdmin = isAdmin;
    }


    public String getUserTypeDesc()
    {
	return userTypeDesc;
    }

    public void setUserTypeDesc(String userTypeDesc)
    {
	this.userTypeDesc = userTypeDesc;
    }


    public String getDeleteMsg()
    {
	return deleteMsg;
    }

    public void setDeleteMsg(String deleteMsg)
    {
	this.deleteMsg = deleteMsg;
    }

    public String getStatusDesc()
    {
	return statusDesc;
    }

    public void setStatusDesc(String statusDesc)
    {
	this.statusDesc = statusDesc;
    }

    public String getStatusColorCode()
    {
	return statusColorCode;
    }

    public void setStatusColorCode(String statusColorCode)
    {
	this.statusColorCode = statusColorCode;
    }

    public BigDecimal getBusinessProfileCode()
    {
	return businessProfileCode;
    }

    public void setBusinessProfileCode(BigDecimal businessProfileCode)
    {
	this.businessProfileCode = businessProfileCode;
    }

    public OC_USRVO getOmniUserVO()
    {
	return omniUserVO;
    }

    public void setOmniUserVO(OC_USRVO omniUserVO)
    {
	this.omniUserVO = omniUserVO;
    }


    public String getCifName()
    {
	return cifName;
    }

    public void setCifName(String cifName)
    {
	this.cifName = cifName;
    }

    public BigDecimal getSubProfileCode()
    {
	return subProfileCode;
    }

    public void setSubProfileCode(BigDecimal subProfileCode)
    {
	this.subProfileCode = subProfileCode;
    }

    public BigDecimal getCustomerCode()
    {
	return customerCode;
    }

    public void setCustomerCode(BigDecimal customerCode)
    {
	this.customerCode = customerCode;
    }

    public String getCustomerName()
    {
	return customerName;
    }

    public void setCustomerName(String customerName)
    {
	this.customerName = customerName;
    }

    public String getSubProfileName()
    {
	return subProfileName;
    }

    public void setSubProfileName(String subProfileName)
    {
	this.subProfileName = subProfileName;
    }

    public String getAppList()
    {
	return appList;
    }

    public void setAppList(String appList)
    {
	this.appList = appList;
    }

    public String getChannelList()
    {
	return channelList;
    }

    public void setChannelList(String channelList)
    {
	this.channelList = channelList;
    }

    public Date getSystemDate()
    {
	return systemDate;
    }

    public void setSystemDate(Date systemDate)
    {
	this.systemDate = systemDate;
    }

    public String getNotificationMsg()
    {
	return notificationMsg;
    }

    public void setNotificationMsg(String notificationMsg)
    {
	this.notificationMsg = notificationMsg;
    }

    public String getMessage()
    {
	return message;
    }

    public void setMessage(String message)
    {
	this.message = message;
    }

    public Boolean getIsUpdate()
    {
	return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate)
    {
	this.isUpdate = isUpdate;
    }

    public BigDecimal getUserCode()
    {
	return userCode;
    }

    public void setUserCode(BigDecimal userCode)
    {
	this.userCode = userCode;
    }

    public String getBusinessProfileName()
    {
	return businessProfileName;
    }

    public void setBusinessProfileName(String businessProfileName)
    {
	this.businessProfileName = businessProfileName;
    }

    public String getReason()
    {
	return reason;
    }

    public void setReason(String reason)
    {
	this.reason = reason;
    }

    public String getDeliverOptionCode()
    {
	return deliverOptionCode;
    }

    public void setDeliverOptionCode(String deliverOptionCode)
    {
	this.deliverOptionCode = deliverOptionCode;
    }

    public String getDeliveryOption()
    {
	return deliveryOption;
    }

    public void setDeliveryOption(String deliveryOption)
    {
	this.deliveryOption = deliveryOption;
    }

    public List<OmniCommonCO> getOmniChannelList()
    {
	return omniChannelList;
    }

    public void setOmniChannelList(List<OmniCommonCO> omniChannelList)
    {
	this.omniChannelList = omniChannelList;
    }

    public List<OmniCommonCO> getOmniApplicationList()
    {
	return omniApplicationList;
    }

    public void setOmniApplicationList(List<OmniCommonCO> omniApplicationList)
    {
	this.omniApplicationList = omniApplicationList;
    }

    public String getMultipleUserApp()
    {
	return multipleUserApp;
    }

    public void setMultipleUserApp(String multipleUserApp)
    {
	this.multipleUserApp = multipleUserApp;
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

    public BigDecimal getBusinessProfileId()
    {
	return businessProfileId;
    }

    public void setBusinessProfileId(BigDecimal businessProfileId)
    {
	this.businessProfileId = businessProfileId;
    }

    public BigDecimal getSubProfileId()
    {
	return subProfileId;
    }

    public void setSubProfileId(BigDecimal subProfileId)
    {
	this.subProfileId = subProfileId;
    }

    public String getCustomerCifNo()
    {
	return customerCifNo;
    }

    public void setCustomerCifNo(String customerCifNo)
    {
	this.customerCifNo = customerCifNo;
    }


    public String getServerPublicKey()
    {
	return serverPublicKey;
    }

    public void setServerPublicKey(String serverPublicKey)
    {
	this.serverPublicKey = serverPublicKey;
    }



    public String getSubLimitName()
    {
	return subLimitName;
    }

    public void setSubLimitName(String subLimitName)
    {
	this.subLimitName = subLimitName;
    }

    public BigDecimal getSubLimitId()
    {
	return subLimitId;
    }

    public void setSubLimitId(BigDecimal subLimitId)
    {
	this.subLimitId = subLimitId;
    }
    public BigDecimal getReasonForDeactivation() {
	return reasonForDeactivation;
        }
        public void setReasonForDeactivation(BigDecimal reasonForDeactivation) {
        	this.reasonForDeactivation = reasonForDeactivation;
        }
        public String getReasonForDeactivationDetail() {
        	return reasonForDeactivationDetail;
        }
        public void setReasonForDeactivationDetail(String reasonForDeactivationDetail) {
        	this.reasonForDeactivationDetail = reasonForDeactivationDetail;
        }

    public BigDecimal getChargeAmout()
    {
	return chargeAmout;
    }

    public void setChargeAmout(BigDecimal chargeAmout)
    {
	this.chargeAmout = chargeAmout;
    }

    public String getOperSrc()
    {
	return operSrc;
    }

    public void setOperSrc(String operSrc)
    {
	this.operSrc = operSrc;
    }

    public BigDecimal getTotalBranchCharge()
    {
	return totalBranchCharge;
    }

    public void setTotalBranchCharge(BigDecimal totalBranchCharge)
    {
	this.totalBranchCharge = totalBranchCharge;
    }


    public BigDecimal getExchangeRate()
    {
	return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate)
    {
	this.exchangeRate = exchangeRate;
    }

    public String getChannelActivationChargesGridData()
    {
        return channelActivationChargesGridData;
    }

    public void setChannelActivationChargesGridData(String channelActivationChargesGridData)
    {
        this.channelActivationChargesGridData = channelActivationChargesGridData;
    }

    public List<OmniUserCO> getChannelActivationChargesGridDataList()
    {
        return channelActivationChargesGridDataList;
    }

    public void setChannelActivationChargesGridDataList(List<OmniUserCO> channelActivationChargesGridDataList)
    {
        this.channelActivationChargesGridDataList = channelActivationChargesGridDataList;
    }


    public String getAccountList()
    {
        return accountList;
    }

    public void setAccountList(String accountList)
    {
        this.accountList = accountList;
    }

    public Map<String, String> getAllowedAppChannels()
    {
        return allowedAppChannels;
    }

    public void setAllowedAppChannels(Map<String, String> allowedAppChannels)
    {
        this.allowedAppChannels = allowedAppChannels;
    }

    public String getAppChannelIds()
    {
        return appChannelIds;
    }

    public void setAppChannelIds(String appChannelIds)
    {
        this.appChannelIds = appChannelIds;
    }





    public BigDecimal getChargePackageId()
    {
	return chargePackageId;
    }

    public void setChargePackageId(BigDecimal chargePackageId)
    {
	this.chargePackageId = chargePackageId;
    }



    public String getVerificationImageData()
    {
        return verificationImageData;
    }

    public void setVerificationImageData(String verificationImageData)
    {
        this.verificationImageData = verificationImageData;
    }

    public BigDecimal getBusinessProfileIdCorp()
    {
	return businessProfileIdCorp;
    }

    public void setBusinessProfileIdCorp(BigDecimal businessProfileIdCorp)
    {
	this.businessProfileIdCorp = businessProfileIdCorp;
    }

	public String getCodesInclude() {
		return codesInclude;
	}

	public void setCodesInclude(String codesInclude) {
		this.codesInclude = codesInclude;
	}

	public BigDecimal getLimitPackageId() {
		return limitPackageId;
	}

	public void setLimitPackageId(BigDecimal limitPackageId) {
		this.limitPackageId = limitPackageId;
	}

	public String getCorporateId()
	{
	    return corporateId;
	}

	public void setCorporateId(String corporateId)
	{
	    this.corporateId = corporateId;
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

	public String getShowSubLimit()	{
		return showSubLimit;
	}
	
	public void setShowSubLimit(String showSubLimit)	{
		this.showSubLimit = showSubLimit;
	}

	public String getSadsUserFirstName() {
		return sadsUserFirstName;
	}

	public void setSadsUserFirstName(String sadsUserFirstName) {
		this.sadsUserFirstName = sadsUserFirstName;
	}

	public BigDecimal getSubLimitCode() {
		return subLimitCode;
	}

	public void setSubLimitCode(BigDecimal subLimitCode) {
		this.subLimitCode = subLimitCode;
	}
	
	public BigDecimal getCorporateBusinessProfileCode(){
		return corporateBusinessProfileCode;
	}
	
	public void setCorporateBusinessProfileCode(BigDecimal corporateBusinessProfileCode){
		this.corporateBusinessProfileCode = corporateBusinessProfileCode;
	}

	public Boolean getIsShowJoint()
	{
	    return isShowJoint;
	}

	public void setIsShowJoint(Boolean isShowJoint)
	{
	    this.isShowJoint = isShowJoint;
	}

	public String getAlertLanguageCode() {
		return alertLanguageCode;
	}

	public void setAlertLanguageCode(String alertLanguageCode) {
		this.alertLanguageCode = alertLanguageCode;
	}

	public String getAlertLanguage() {
		return alertLanguage;
	}

	public void setAlertLanguage(String alertLanguage) {
		this.alertLanguage = alertLanguage;
	}
	public String getMobile()
	{
		return mobile;
	}
	
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getTel()
	{
		return tel;
	}
	
	public void setTel(String tel)
	{
		this.tel = tel;
	}
	
	public String getBranchCode()
	{
		return branchCode;
	}
	
	public void setBranchCode(String branchCode)
	{
		this.branchCode = branchCode;
	}

	public BigDecimal getOldPkId() {
		return oldPkId;
	}

	public void setOldPkId(BigDecimal oldPkId) {
		this.oldPkId = oldPkId;
	}

	public Integer getUserIsBlocked() {
		return userIsBlocked;
	}

	public void setUserIsBlocked(Integer userIsBlocked) {
		this.userIsBlocked = userIsBlocked;
	}

	public Integer getRemainingAttempt() {
		return remainingAttempt;
	}

	public void setRemainingAttempt(Integer remainingAttempt) {
		this.remainingAttempt = remainingAttempt;
	}
	
	public Boolean getIsPasswordExpired()
	{
	    return isPasswordExpired;
	}

	public void setIsPasswordExpired(Boolean isPasswordExpired)
	{
	    this.isPasswordExpired = isPasswordExpired;
	}

	public Integer getOutputCode()
	{
	    return outputCode;
	}

	public void setOutputCode(Integer outputCode)
	{
	    this.outputCode = outputCode;
	}

	public String getOutputNotification()
	{
	    return outputNotification;
	}

	public void setOutputNotification(String outputNotification)
	{
	    this.outputNotification = outputNotification;
	}

	public String getOutputType()
	{
	    return outputType;
	}

	public void setOutputType(String outputType)
	{
	    this.outputType = outputType;
	}





	public BigDecimal getCifBranch() {
		return cifBranch;
	}

	public void setCifBranch(BigDecimal cifBranch) {
		this.cifBranch = cifBranch;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getServerLoginToken()
	{
	    return serverLoginToken;
	}

	public void setServerLoginToken(String serverLoginToken)
	{
	    this.serverLoginToken = serverLoginToken;
	}

	/* nabil feghali - OMNI common security  */
	@Override
	public String getCIF_NO()
	{
	    if(omniUserVO != null)
	    {
		return omniUserVO.getCIF_NO();
	    }
	    return null;
	}

	public HashMap<String, Object> getSubscriberInfo() {
		return subscriberInfo;
	}

	public void setSubscriberInfo(HashMap<String, Object> subscriberInfo) {
		this.subscriberInfo = subscriberInfo;
	}

	public String getCustomerDetailName() {
		return customerDetailName;
	}

	public void setCustomerDetailName(String customerDetailName) {
		this.customerDetailName = customerDetailName;
	}

	public String getDeductChargeRequired() {
		return deductChargeRequired;
	}

	public void setDeductChargeRequired(String deductChargeRequired) {
		this.deductChargeRequired = deductChargeRequired;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
