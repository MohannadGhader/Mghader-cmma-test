package com.path.vo.omni.common.base;

import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.path.bo.common.ConstantsCommon;
import com.path.struts2.lib.common.GridParamsSC;
import com.path.vo.common.SessionCO;

/**
 * 
 * Copyright 2017, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: ChadiAssaf
 *
 *          OmniBaseSC.java used to
 */
public class OmniBaseSC extends GridParamsSC
{
    private static final long serialVersionUID = 1L;
    private String language;
    private BigDecimal appId;
    private BigDecimal appCustomerId;// will be used for coporate customer Id
				     // and all apps that support multiple end
				     // user.
    private String appCustomerName;
    private BigDecimal channelId;
    private BigDecimal profileId;
    private BigDecimal originalCode;
    private BigDecimal tableRef; // OC_DATA_COL_TABLE
    private BigDecimal channelTableRef; // OC_DATA_COL_TABLE
    private BigDecimal appTableRef; // OC_DATA_COL_TABLE
    private String progRef; // Opt Reference
    private BigDecimal paramId; // parameter Id
    private BigDecimal paramGrpId; // parameter Group Id
    private String paramName; // parameter Name
    private String codesExclude;
    private String codesInclude;
    private String status;
    private String tableName;
    private String pkColName;
    private BigDecimal pkColValue;
    private BigDecimal refCode;
    private BigDecimal displayMsg;
    private String multipleUserApp;
    private String channelList;
    private String applicationList;
    private HashMap<String, Object> reportParametersList;
    private HashMap<String, Object> commonParametersList;
    private String coreReference;
    private String filterFromDate;
    private String filterToDate;
    private String actionType;
    private String name;
    private BigDecimal mappingId;
    private BigDecimal submitType;//added for submit multiple services with same oper
    private String filterByOper;
    private String idTypeList;
    /**
     * @author KhaledAlTimany
     */
    private String udid;
    /**
     * @author gilbertandary
     * 
     */
    private String userName;
    private String sessionId;
    private String serviceMethod; // this field is used to distinguish between
				  // which method in mappers we should be
				  // calling
    private String vsCode;// it is used to store string code
    private String tellerId; // added teller id in compliance with 13.5.5

    private String userCifNo;
    private String workingCif;
    private BigDecimal ocUserId;// to be used instead of userId; since userId is
				// deprecated from omni side
    private BigDecimal operId;

    /**
     * 
     * @author gilbertandary
     * 
     */
    private String deviceIp;// for encryption byIP

    private BigDecimal appChnlId;

    private String userType;

    private String appIdentifier;// this field will hold user identification
				 // credentials ex; facebook social ID ..

    private HashMap<String, HashMap<String, Object>> dynamicInsertQuery;

    private String agentAllowedCifNo; // this was added for agency flow, since
				      // we have 2 cifs, 1 for the agent and 1
				      // for the user that this agent is an
				      // agent for.
    private String vsBranchCode;

    private String listAllAllowedCifs; // this was added in order to have list of all allowed cifs for a specific agent
    
    
    /**
     * @author gilbertandary
     */
    private String cifBranchCode; // this was added in order to receive always the CIF branch code from the front end
    
    private String includeIds;
    private String includeCodes;
    private String fromTo;
    private String includeSubIds;
    private String type;
    private String appName;
    
    /* Muhammad.Asif - field added to validate business value*/
    private String businessValidationValue;
    private HashMap<String, String> adminParametersList;
    private String cifName;
    private String mobileNo;
    private BigDecimal cvAmount;
    private BigDecimal fcAmount;
    public OmniBaseSC()
    {
	super();
	if(ActionContext.getContext() != null && ServletActionContext.getRequest() != null)
	{
	    SessionCO sessionCO = (SessionCO) ServletActionContext.getRequest().getSession()
		    .getAttribute(ConstantsCommon.SESSION_VO_ATTR);
	    this.setCompCode(sessionCO.getCompanyCode());
	    this.setCurrAppName(sessionCO.getCurrentAppName());
	    this.setLanguage(sessionCO.getLanguage());
	    this.setUserId(sessionCO.getUserName());
	    this.setLanguage(sessionCO.getLanguage());
	    this.setPreferredLanguage(sessionCO.getPreferredLanguage());
	    this.setIsRTLDir(sessionCO.getIsRTLDir());
	}
    }

    public String getLanguage()
    {
	return language;
    }

    public void setLanguage(String language)
    {
	this.language = language;
    }

    public BigDecimal getAppId()
    {
	return appId;
    }

    public void setAppId(BigDecimal appId)
    {
	this.appId = appId;
    }

    public BigDecimal getChannelId()
    {
	return channelId;
    }

    public void setChannelId(BigDecimal channelId)
    {
	this.channelId = channelId;
    }

    public BigDecimal getProfileId()
    {
	return profileId;
    }

    public void setProfileId(BigDecimal profileId)
    {
	this.profileId = profileId;
    }

    public String getProgRef()
    {
	return progRef;
    }

    public void setProgRef(String progRef)
    {
	this.progRef = progRef;
    }

    public BigDecimal getTableRef()
    {
	return tableRef;
    }

    public void setTableRef(BigDecimal tableRef)
    {
	this.tableRef = tableRef;
    }

    public BigDecimal getChannelTableRef()
    {
	return channelTableRef;
    }

    public void setChannelTableRef(BigDecimal channelTableRef)
    {
	this.channelTableRef = channelTableRef;
    }

    public BigDecimal getAppTableRef()
    {
	return appTableRef;
    }

    public void setAppTableRef(BigDecimal appTableRef)
    {
	this.appTableRef = appTableRef;
    }

    public BigDecimal getParamId()
    {
	return paramId;
    }

    public void setParamId(BigDecimal paramId)
    {
	this.paramId = paramId;
    }

    public BigDecimal getParamGrpId()
    {
	return paramGrpId;
    }

    public void setParamGrpId(BigDecimal paramGrpId)
    {
	this.paramGrpId = paramGrpId;
    }

    public String getParamName()
    {
	return paramName;
    }

    public void setParamName(String paramName)
    {
	this.paramName = paramName;
    }

    public String getCodesExclude()
    {
	return codesExclude;
    }

    public void setCodesExclude(String codesExclude)
    {
	this.codesExclude = codesExclude;
    }

    public String getCodesInclude()
    {
	return codesInclude;
    }

    public void setCodesInclude(String codesInclude)
    {
	this.codesInclude = codesInclude;
    }

    public String getStatus()
    {
	return status;
    }

    public void setStatus(String status)
    {
	this.status = status;
    }

    public BigDecimal getDisplayMsg()
    {
	return displayMsg;
    }

    public void setDisplayMsg(BigDecimal displayMsg)
    {
	this.displayMsg = displayMsg;
    }

    public String getTableName()
    {
	return tableName;
    }

    public void setTableName(String tableName)
    {
	this.tableName = tableName;
    }

    public String getPkColName()
    {
	return pkColName;
    }

    public void setPkColName(String pkColName)
    {
	this.pkColName = pkColName;
    }

    public BigDecimal getPkColValue()
    {
	return pkColValue;
    }

    public void setPkColValue(BigDecimal pkColValue)
    {
	this.pkColValue = pkColValue;
    }

    public BigDecimal getOriginalCode()
    {
	return originalCode;
    }

    public void setOriginalCode(BigDecimal originalCode)
    {
	this.originalCode = originalCode;
    }

    public String getMultipleUserApp()
    {
	return multipleUserApp;
    }

    public void setMultipleUserApp(String multipleUserApp)
    {
	this.multipleUserApp = multipleUserApp;
    }

    public String getChannelList()
    {
	return channelList;
    }

    public void setChannelList(String channelList)
    {
	this.channelList = channelList;
    }

    public String getApplicationList()
    {
	return applicationList;
    }

    public void setApplicationList(String applicationList)
    {
	this.applicationList = applicationList;
    }

    public BigDecimal getRefCode()
    {
	return refCode;
    }

    public void setRefCode(BigDecimal refCode)
    {
	this.refCode = refCode;
    }

    public String getServiceMethod()
    {
	return serviceMethod;
    }

    public void setServiceMethod(String serviceMethod)
    {
	this.serviceMethod = serviceMethod;
    }

    public String getUserName()
    {
	return userName;
    }

    public void setUserName(String userName)
    {
	this.userName = userName;
    }

    public String getSessionID()
    {
	return sessionId;
    }

    public void setSessionID(String sessionID)
    {
	this.sessionId = sessionID;
    }

    public String getVsCode()
    {
	return vsCode;
    }

    public void setVsCode(String vsCode)
    {
	this.vsCode = vsCode;
    }

    public String getTellerId()
    {
	return tellerId;
    }

    public void setTellerId(String tellerId)
    {
	this.tellerId = tellerId;
    }

    public String getSessionId()
    {
	return sessionId;
    }

    public void setSessionId(String sessionId)
    {
	this.sessionId = sessionId;
    }

    public String getUserCifNo()
    {
	return userCifNo;
    }

    public void setUserCifNo(String userCifNo)
    {
	this.userCifNo = userCifNo;
    }

    public BigDecimal getOcUserId()
    {
	return ocUserId;
    }

    public void setOcUserId(BigDecimal ocUserId)
    {
	this.ocUserId = ocUserId;
    }

    public BigDecimal getOperId()
    {
	return operId;
    }

    public void setOperId(BigDecimal operId)
    {
	this.operId = operId;
    }

    public String getDeviceIp()
    {
	return deviceIp;
    }

    public void setDeviceIp(String deviceIp)
    {
	this.deviceIp = deviceIp;
    }

    public BigDecimal getAppChnlId()
    {
	return appChnlId;
    }

    public void setAppChnlId(BigDecimal appChnlId)
    {
	this.appChnlId = appChnlId;
    }

    public BigDecimal getAppCustomerId()
    {
	return appCustomerId;
    }

    public void setAppCustomerId(BigDecimal appCustomerId)
    {
	this.appCustomerId = appCustomerId;
    }

    public String getUserType()
    {
	return userType;
    }

    public void setUserType(String userType)
    {
	this.userType = userType;
    }

    public String getAppIdentifier()
    {
	return appIdentifier;
    }

    public void setAppIdentifier(String appIdentifier)
    {
	this.appIdentifier = appIdentifier;
    }

    public HashMap<String, HashMap<String, Object>> getDynamicInsertQuery()
    {
	return dynamicInsertQuery;
    }

    public void setDynamicInsertQuery(HashMap<String, HashMap<String, Object>> dynamicInsertQuery)
    {
	this.dynamicInsertQuery = dynamicInsertQuery;
    }

    public String getAgentAllowedCifNo()
    {
	return agentAllowedCifNo;
    }

    public void setAgentAllowedCifNo(String agentAllowedCifNo)
    {
	this.agentAllowedCifNo = agentAllowedCifNo;
    }

    public String getVsBranchCode()
    {
	return vsBranchCode;
    }

    public void setVsBranchCode(String vsBranchCode)
    {
	this.vsBranchCode = vsBranchCode;
    }

    public String getListAllAllowedCifs()
    {
        return listAllAllowedCifs;
    }

    public void setListAllAllowedCifs(String listAllAllowedCifs)
    {
        this.listAllAllowedCifs = listAllAllowedCifs;
    }

    public String getCifBranchCode()
    {
        return cifBranchCode;
    }

    public void setCifBranchCode(String cifBranchCode)
    {
        this.cifBranchCode = cifBranchCode;
    }

    public String getAppCustomerName()
    {
        return appCustomerName;
    }

    public void setAppCustomerName(String appCustomerName)
    {
        this.appCustomerName = appCustomerName;
    }

	public String getUdid() {
		return udid;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}

	public HashMap<String, Object> getReportParametersList()
	{
	    return reportParametersList;
	}

	public void setReportParametersList(HashMap<String, Object> reportParametersList)
	{
	    this.reportParametersList = reportParametersList;
	}

	public HashMap<String, Object> getCommonParametersList()
	{
	    return commonParametersList;
	}

	public void setCommonParametersList(HashMap<String, Object> commonParametersList)
	{
	    this.commonParametersList = commonParametersList;
	}

	public String getIncludeIds()
	{
	    return includeIds;
	}

	public void setIncludeIds(String includeIds)
	{
	    this.includeIds = includeIds;
	}

	public String getIncludeCodes()
	{
	    return includeCodes;
	}

	public void setIncludeCodes(String includeCodes)
	{
	    this.includeCodes = includeCodes;
	}

	public String getWorkingCif()
	{
	    return workingCif;
	}

	public void setWorkingCif(String workingCif)
	{
	    this.workingCif = workingCif;
	}
	public String getCoreReference()
	{
	    return coreReference;
	}

	public void setCoreReference(String coreReference)
	{
	    this.coreReference = coreReference;
	}

	public String getFilterFromDate()
	{
	    return filterFromDate;
	}

	public void setFilterFromDate(String filterFromDate)
	{
	    this.filterFromDate = filterFromDate;
	}

	public String getFilterToDate()
	{
	    return filterToDate;
	}

	public void setFilterToDate(String filterToDate)
	{
	    this.filterToDate = filterToDate;
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

	public String getFromTo()
	{
	    return fromTo;
	}

	public void setFromTo(String fromTo)
	{
	    this.fromTo = fromTo;
	}

	public String getIncludeSubIds()
	{
	    return includeSubIds;
	}

	public void setIncludeSubIds(String includeSubIds)
	{
	    this.includeSubIds = includeSubIds;
	}

	public String getType()
	{
	    return type;
	}

	public void setType(String type)
	{
	    this.type = type;
	}

	public BigDecimal getSubmitType()
	{
	    return submitType;
	}

	public void setSubmitType(BigDecimal submitType)
	{
	    this.submitType = submitType;
	}
	public String getAppName()
	{
	    return appName;
	}

	public void setAppName(String appName)
	{
	    this.appName = appName;
	}

	public String getBusinessValidationValue() {
		return businessValidationValue;
	}

	public void setBusinessValidationValue(String businessValidationValue) {
		this.businessValidationValue = businessValidationValue;
	}

	public HashMap<String, String> getAdminParametersList() {
		return adminParametersList;
	}

	public void setAdminParametersList(HashMap<String, String> adminParametersList) {
		this.adminParametersList = adminParametersList;
	}

	public String getFilterByOper() {
		return filterByOper;
	}

	public void setFilterByOper(String filterByOper) {
		this.filterByOper = filterByOper;
	}

	public String getCifName() {
		return cifName;
	}

	public void setCifName(String cifName) {
		this.cifName = cifName;
	}
	
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public BigDecimal getCvAmount() {
		return cvAmount;
	}

	public void setCvAmount(BigDecimal cvAmount) {
		this.cvAmount = cvAmount;
	}
	public BigDecimal getFcAmount() {
		return fcAmount;
	}
	public void setFcAmount(BigDecimal fcAmount) {
		this.fcAmount = fcAmount;
	}

	public String getIdTypeList() {
		return idTypeList;
	}

	public void setIdTypeList(String idTypeList) {
		this.idTypeList = idTypeList;
	}

}
