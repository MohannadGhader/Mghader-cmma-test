package com.path.vo.common.audit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import com.path.lib.vo.BaseVO;

/**
 * 
 * Copyright 2012, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: RabihElKhatib
 *
 *          AuditRefCO.java used to store the callAudit method variables in
 *          AuditBO
 */
public class AuditRefCO extends BaseVO {
	private String appName; // The current application name
	private String progRef; // Window opt's reference
	private String operationType; // Audit operation type (update, insert, delete, retrieve ...)
	private String keyRef; // The constructed key of the window
	private Date runningDate; // The performed action date (running date)
	private String userID; // The current user logged in name
	private String machineID; // The current machine name
	private String trxNbr; // The Transaction Number of the window
	private Boolean auditEnabled; // The audit OPT checking
	// private ArrayList<AuditCO> auditCO; //Array list that contains the data to be
	// inserted into AUDIT tables
	private AuditCO auditCO; // Array list that contains the data to be inserted into AUDIT tables
	private Boolean disableSmart; // boolean whether not to Consider Smart in validation
	private Map reportArgs;
	private boolean isHashMap;
	private boolean forDesc;
	/**
	 * used to add more details about the field to know the parent related to it
	 */
	private String infoDetails;
	// 439073 ADD support for additional fields labels
	private BigDecimal addFieldType;

	private BigDecimal addFieldCode;

	private BigDecimal addFieldTypeColNbr;

	// 447471 Masking the info of VIP customers
	private String elementsFullReference; // The full reference of the provided VO (i.e. fomCO.cifVO)

	// Bug 570461 add special branch / company code
	private BigDecimal smartSpecCompCode;
	private BigDecimal smartSpecBranchCode;

	// 642699 Show the CIF instead of button clicked
	private String fieldAuditDetails;

	// 690933 Field & CIF Audit
	private List<BigDecimal> cifToAudit = new ArrayList<BigDecimal>();

	// 705733
//    private OC_S_AUDIT_ACTIONS_EXTENDEDVO ocExtendedVO;
	private String sessionID;
	private String serialAuditObj;

	// 768542 BB180246 - AUdit Trail report
	private BigDecimal branchCode;

	// TP 841913 Handle SMART auditing upon upload/delete attachments
	private String auditActionDate;

	// User Story #966771 Changes on the common audit management -To Show Custom
	// Description in Multi-Row Audit
	private String auditListDescField; // Comma separated string that contains Audit description fields
	private String auditTableDescField; // Audit related Grid/Table description/name
	private HashMap<String, String> auditDescFieldsMap = new HashMap<String, String>(); // Description columns
																						// translation keys
	private String fieldDescLang; // Translation language.

	// #996316 SBI200225 - StuckThread at Summit Bank
	private BigDecimal manualCommit;

	// #917822 RKIB190342 - SADS not showing audit report details in Access by user
	// screen
	private String manualTrxNo;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getProgRef() {
		return progRef;
	}

	public void setProgRef(String progRef) {
		this.progRef = progRef;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getKeyRef() {
		return keyRef;
	}

	public void setKeyRef(String keyRef) {
		this.keyRef = keyRef;
	}

	public void setRunningDate(Date runningDate) {
		this.runningDate = runningDate;
	}

	public Date getRunningDate() {
		return runningDate;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserID() {
		return userID;
	}

	public void setMachineID(String machineID) {
		this.machineID = machineID;
	}

	// penetration Testing BMOUPI170522, not to disclose IP to Response
	@JSON(serialize = false)
	public String getMachineID() {
		return machineID;
	}
	public AuditCO getAuditCO() {
		return auditCO;
	}

	public void setAuditCO(AuditCO auditCO) {
		this.auditCO = auditCO;
	}

	public void setTrxNbr(String trxNbr) {
		this.trxNbr = trxNbr;
	}

	public String getTrxNbr() {
		return trxNbr;
	}

	public void setAuditEnabled(Boolean auditEnabled) {
		this.auditEnabled = auditEnabled;
	}

	public Boolean getAuditEnabled() {
		return auditEnabled;
	}

	public Boolean getDisableSmart() {
		return disableSmart;
	}

	public void setDisableSmart(Boolean disableSmart) {
		this.disableSmart = disableSmart;
	}

	/**
	 * @return the infoDetails
	 */
	public String getInfoDetails() {
		return infoDetails;
	}

	/**
	 * @param infoDetails the infoDetails to set
	 */
	public void setInfoDetails(String infoDetails) {
		this.infoDetails = infoDetails;
	}

	public Map getReportArgs() {
		return reportArgs;
	}

	public void setReportArgs(Map reportArgs) {
		this.reportArgs = reportArgs;
	}

	public BigDecimal getAddFieldType() {
		return addFieldType;
	}

	public void setAddFieldType(BigDecimal addFieldType) {
		this.addFieldType = addFieldType;
	}

	public BigDecimal getAddFieldCode() {
		return addFieldCode;
	}

	public String getAuditActionDate() {
		return auditActionDate;
	}

	public void setAuditActionDate(String auditActionDate) {
		this.auditActionDate = auditActionDate;
	}

	public void setAddFieldCode(BigDecimal addFieldCode) {
		this.addFieldCode = addFieldCode;
	}

	public BigDecimal getAddFieldTypeColNbr() {
		return addFieldTypeColNbr;
	}

	public void setAddFieldTypeColNbr(BigDecimal addFieldTypeColNbr) {
		this.addFieldTypeColNbr = addFieldTypeColNbr;
	}

	public String getElementsFullReference() {
		return elementsFullReference;
	}

	public void setElementsFullReference(String elementsFullReference) {
		this.elementsFullReference = elementsFullReference;
	}

	public BigDecimal getSmartSpecCompCode() {
		return smartSpecCompCode;
	}

	public void setSmartSpecCompCode(BigDecimal smartSpecCompCode) {
		this.smartSpecCompCode = smartSpecCompCode;
	}

	public BigDecimal getSmartSpecBranchCode() {
		return smartSpecBranchCode;
	}

	public void setSmartSpecBranchCode(BigDecimal smartSpecBranchCode) {
		this.smartSpecBranchCode = smartSpecBranchCode;
	}

	public String getFieldAuditDetails() {
		return fieldAuditDetails;
	}

	public void setFieldAuditDetails(String fieldAuditDetails) {
		this.fieldAuditDetails = fieldAuditDetails;
	}

	public boolean isHashMap() {
		return isHashMap;
	}

	public void setHashMap(boolean isHashMap) {
		this.isHashMap = isHashMap;
	}

	public boolean isForDesc() {
		return forDesc;
	}

	public void setForDesc(boolean forDesc) {
		this.forDesc = forDesc;
	}

	public List<BigDecimal> getCifToAudit() {
		return cifToAudit;
	}

	public void setCifToAudit(List<BigDecimal> cifToAudit) {
		this.cifToAudit = cifToAudit;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getSerialAuditObj() {
		return serialAuditObj;
	}

	public void setSerialAuditObj(String serialAuditObj) {
		this.serialAuditObj = serialAuditObj;
	}

	public BigDecimal getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(BigDecimal branchCode) {
		this.branchCode = branchCode;
	}

	public String getAuditListDescField() {
		return auditListDescField;
	}

	public void setAuditListDescField(String auditListDescField) {
		this.auditListDescField = auditListDescField;
	}

	public String getAuditTableDescField() {
		return auditTableDescField;
	}

	public void setAuditTableDescField(String auditTableDescField) {
		this.auditTableDescField = auditTableDescField;
	}

	public HashMap<String, String> getAuditDescFieldsMap() {
		return auditDescFieldsMap;
	}

	public void setAuditDescFieldsMap(HashMap<String, String> auditDescFieldsMap) {
		this.auditDescFieldsMap = auditDescFieldsMap;
	}

	public String getFieldDescLang() {
		return fieldDescLang;
	}

	public void setFieldDescLang(String fieldDescLang) {
		this.fieldDescLang = fieldDescLang;
	}

	public BigDecimal getManualCommit() {
		return manualCommit;
	}

	public void setManualCommit(BigDecimal manualCommit) {
		this.manualCommit = manualCommit;
	}

	public String getManualTrxNo() {
		return manualTrxNo;
	}

	public void setManualTrxNo(String manualTrxNo) {
		this.manualTrxNo = manualTrxNo;
	}
}
