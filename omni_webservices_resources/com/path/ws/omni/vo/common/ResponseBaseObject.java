package com.path.ws.omni.vo.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

//import com.path.vo.common.header.ServiceResponseVO;

public class ResponseBaseObject extends RequestBaseObject implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer errorCode;
	private String errorDesc;
	private String reference;
	private Integer outputCode;
	private String outputNotification;
	private String outputNotificationDetails;
	private String outputType;
	private Integer userIsBlocked;
	/**
	 * number of records of grid: Added by Richie for the ionic grid component
	 */
	private int totalNbRec;

	// Your result List
	private List gridModel;

	/**
	 * @author alishreif - add responseCode, responseDesc to distinguish between the
	 *         case of error where errorCode will be not null and the case of
	 *         success call where we need to return different codes for the success
	 *         with different messages.
	 * @return
	 */
	private int responseCode;
	private String responseDesc;

	private String briefDesc; // added to be used for columns having multi
	// langage values to return one value based on the
	// lang input.
	private String longDesc; // added to be used for columns having multi
	// langage values to return one value based on the
	// lang input.

	private String response_token;

	private String response;

	private String responseError;

	private String encryptedData;

	/**
	 * @author AliShreif adding service response common section to handle the error
	 *         message to be returned for the user
	 */
	// nabil feghali : when trying to call the OmniEventBO from RMI we faced an
	// error NotSerializableException in ServiceResponseVO because it is not
	// serializable, after adding serilaizable it was fixed.
	// then we found that this class is linked from digital_banking_layer and should
	// not be used here.
	// private ServiceResponseVO serviceResponse;

	private String alertType;
	private BigDecimal branchesId;

	/**
	 * @author Muhammad.Asif To set Translated Message Code Description in
	 *         outputNotification field by OmniServicesInterceptor.java
	 */
	private String[] messageCodeParam;

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public int getTotalNbRec() {
		return totalNbRec;
	}

	public void setTotalNbRec(int totalNbRec) {
		this.totalNbRec = totalNbRec;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDesc() {
		return responseDesc;
	}

	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}

	public List getGridModel() {
		return gridModel;
	}

	public void setGridModel(List gridModel) {
		this.gridModel = gridModel;
	}

	public String getBriefDesc() {
		return briefDesc;
	}

	public void setBriefDesc(String briefDesc) {
		this.briefDesc = briefDesc;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	/*
	 * public ServiceResponseVO getServiceResponse() { return serviceResponse; }
	 * 
	 * public void setServiceResponse(ServiceResponseVO serviceResponse) {
	 * this.serviceResponse = serviceResponse; }
	 */

	public String getResponse_token() {
		return response_token;
	}

	public void setResponse_token(String response_token) {
		this.response_token = response_token;
	}

	public Integer getOutputCode() {
		return outputCode;
	}

	public void setOutputCode(Integer outputCode) {
		this.outputCode = outputCode;
	}

	public String getOutputNotification() {
		return outputNotification;
	}

	public void setOutputNotification(String outputNotification) {
		this.outputNotification = outputNotification;
	}

	public String getOutputNotificationDetails() {
		return outputNotificationDetails;
	}

	public void setOutputNotificationDetails(String outputNotificationDetails) {
		this.outputNotificationDetails = outputNotificationDetails;
	}

	public String getOutputType() {
		return outputType;
	}

	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}

	public Integer getUserIsBlocked() {
		return userIsBlocked;
	}

	public void setUserIsBlocked(Integer userIsBlocked) {
		this.userIsBlocked = userIsBlocked;
	}

	public BigDecimal getBranchesId() {
		return branchesId;
	}

	public void setBranchesId(BigDecimal branchesId) {
		this.branchesId = branchesId;
	}

	public String[] getMessageCodeParam() {
		return messageCodeParam;
	}

	public void setMessageCodeParam(String[] messageCodeParam) {
		this.messageCodeParam = messageCodeParam;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getResponseError() {
		return responseError;
	}

	public void setResponseError(String responseError) {
		this.responseError = responseError;
	}

	public String getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}

}
