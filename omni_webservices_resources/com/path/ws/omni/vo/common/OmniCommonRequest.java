package com.path.ws.omni.vo.common;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class OmniCommonRequest extends RequestBaseObject {
	private String parameterId;
	private String parameterName;
	private String paramNameList; // comma separated parameter names
	private String parameterGrp;
	private String crc_token;
	private BigDecimal groupCode;
	private String actionType;
	private String name;
	private BigDecimal mappingId;
	private BigDecimal dataSaveId;
	private String appName;
	private String status;
	private int language_id;
	private String device_UUID;
	private int client_id;
	private int driver_id;
	private int admin_id;

	private String encrypted;
	private String encryptedData;
	private String encryptedInitVector;
	private String encryptedSecretKey;

	/*
	 * attribute added to distinguish the user if it is client or driver 0 : client
	 * 1: driver 2: admin
	 */
	private int app;
	private String version;
	private String frontEndPublicKey;
	private Date date;
	private Time time;
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}

	public String getEncryptedInitVector() {
		return encryptedInitVector;
	}

	public void setEncryptedInitVector(String encryptedInitVector) {
		this.encryptedInitVector = encryptedInitVector;
	}

	public String getEncryptedSecretKey() {
		return encryptedSecretKey;
	}

	public void setEncryptedSecretKey(String encryptedSecretKey) {
		this.encryptedSecretKey = encryptedSecretKey;
	}

	public String getFrontEndPublicKey() {
		return frontEndPublicKey;
	}

	public void setFrontEndPublicKey(String frontEndPublicKey) {
		this.frontEndPublicKey = frontEndPublicKey;
	}

	public String getParameterId() {
		return parameterId;
	}

	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getParameterGrp() {
		return parameterGrp;
	}

	public void setParameterGrp(String parameterGrp) {
		this.parameterGrp = parameterGrp;
	}

	public String getParamNameList() {
		return paramNameList;
	}

	public void setParamNameList(String paramNameList) {
		this.paramNameList = paramNameList;
	}

	public String getCrc_token() {
		return crc_token;
	}

	public void setCrc_token(String crc_token) {
		this.crc_token = crc_token;
	}

	public BigDecimal getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(BigDecimal groupCode) {
		this.groupCode = groupCode;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getMappingId() {
		return mappingId;
	}

	public void setMappingId(BigDecimal mappingId) {
		this.mappingId = mappingId;
	}

	public BigDecimal getDataSaveId() {
		return dataSaveId;
	}

	public void setDataSaveId(BigDecimal dataSaveId) {
		this.dataSaveId = dataSaveId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(int language_id) {
		this.language_id = language_id;
	}

	public String getDevice_UUID() {
		return device_UUID;
	}

	public void setDevice_UUID(String device_UUID) {
		this.device_UUID = device_UUID;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public int getApp() {
		return app;
	}

	public void setApp(int app) {
		this.app = app;
	}

	public String getEncrypted() {
		return encrypted;
	}

	public void setEncrypted(String encrypted) {
		this.encrypted = encrypted;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

}