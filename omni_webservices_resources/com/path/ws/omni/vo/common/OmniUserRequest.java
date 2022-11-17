/**
 * Copyright 2018, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: gilbertandary
 *
 * OmniUserRequest.java
 */
package com.path.ws.omni.vo.common;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import com.path.vo.omni.oadm.omniuser.OmniUserCO;

public class OmniUserRequest extends RequestBaseObject
{
    OmniUserCO omniUserCO = new OmniUserCO();
    
    private HashMap<String, Object>  accountDeactivationReason;
    private String accountDeactivationReasonDetail;
    private Date updateDate;

    private String requestType;
    private BigDecimal securityQuestionId;
    private String securityQuestionAnswer;
    private String status;
    private BigDecimal tableRef;
    private String cardNumber;
    private Boolean forgotPassword;
    private Boolean forgotUsername;
    private Date dateOfBirthday;
    private Date expiryDate;
    private HashMap<String, Object> idType;
    private String idNumber;
    private String cardPin;
    /**
     *  @author Muhammad.Asif 
     *  BUG# 841830
     */
    private String publicKey;
    private String password; 
    private String confirmPassword; 
    
    /**
     * @author Suhail.Shoukathali
     */
    private BigDecimal verificationImageId;
    
    private String profilePicture;
    
    private String email;
    private String mobileNumber;
    private String subscriberId;
    
    /**
     * @author Adnan Ali
     */
    private String reasonComment;
    
    public OmniUserCO getOmniUserCO()
    {
        return omniUserCO;
    }

    public void setOmniUserCO(OmniUserCO omniUserCO)
    {
        this.omniUserCO = omniUserCO;
    }

    public String getRequestType()
    {
        return requestType;
    }

    public void setRequestType(String requestType)
    {
        this.requestType = requestType;
    }

	public String getAccountDeactivationReasonDetail() {
		return accountDeactivationReasonDetail;
	}

	public void setAccountDeactivationReasonDetail(String accountDeactivationReasonDetail) {
		this.accountDeactivationReasonDetail = accountDeactivationReasonDetail;
	}


	public Date getUpdateDate()
	{
	    return updateDate;
	}

	public void setUpdateDate(Date updateDate)
	{
	    this.updateDate = updateDate;
	}

	public BigDecimal getVerificationImageId()
	{
	    return verificationImageId;
	}

	public void setVerificationImageId(BigDecimal verificationImageId)
	{
	    this.verificationImageId = verificationImageId;
	}

	public BigDecimal getSecurityQuestionId() {
		return securityQuestionId;
	}

	public void setSecurityQuestionId(BigDecimal securityQuestionId) {
		this.securityQuestionId = securityQuestionId;
	}

	public String getSecurityQuestionAnswer() {
		return securityQuestionAnswer;
	}

	public void setSecurityQuestionAnswer(String securityQuestionAnswer) {
		this.securityQuestionAnswer = securityQuestionAnswer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTableRef() {
		return tableRef;
	}

	public void setTableRef(BigDecimal tableRef) {
		this.tableRef = tableRef;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCardNumber()
	{
	    return cardNumber;
	}

	public void setCardNumber(String cardNumber)
	{
	    this.cardNumber = cardNumber;
	}

	public String getConfirmPassword()
	{
	    return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword)
	{
	    this.confirmPassword = confirmPassword;
	}

	public Boolean getForgotPassword()
	{
	    return forgotPassword;
	}

	public void setForgotPassword(Boolean forgotPassword)
	{
	    this.forgotPassword = forgotPassword;
	}

	public Boolean getForgotUsername()
	{
	    return forgotUsername;
	}

	public void setForgotUsername(Boolean forgotUsername)
	{
	    this.forgotUsername = forgotUsername;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getReasonComment() {
		return reasonComment;
	}

	public void setReasonComment(String reasonComment) {
		this.reasonComment = reasonComment;
	}

	public Date getDateOfBirthday()
	{
	    return dateOfBirthday;
	}

	public void setDateOfBirthday(Date dateOfBirthday)
	{
	    this.dateOfBirthday = dateOfBirthday;
	}

	public Date getExpiryDate()
	{
	    return expiryDate;
	}

	public void setExpiryDate(Date expiryDate)
	{
	    this.expiryDate = expiryDate;
	}

	public String getIdNumber()
	{
	    return idNumber;
	}

	public void setIdNumber(String idNumber)
	{
	    this.idNumber = idNumber;
	}

	public String getCardPin()
	{
	    return cardPin;
	}

	public void setCardPin(String cardPin)
	{
	    this.cardPin = cardPin;
	}
	
	public HashMap<String, Object>  getAccountDeactivationReason() {
		return accountDeactivationReason;
	}

	public void setAccountDeactivationReason(HashMap<String, Object>  accountDeactivationReason) {
		this.accountDeactivationReason = accountDeactivationReason;
	}

	public String getSubscriberId()
	{
	    return subscriberId;
	}

	public void setSubscriberId(String subscriberId)
	{
	    this.subscriberId = subscriberId;
	}

	public HashMap<String, Object> getIdType()
	{
	    return idType;
	}

	public void setIdType(HashMap<String, Object> idType)
	{
	    this.idType = idType;
	}
	
	
	
    
 }
