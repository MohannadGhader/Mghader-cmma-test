package com.path.vo.common;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.path.dbmaps.vo.OPTVO;
import com.path.struts2.lib.common.BaseSC;

public class CommonLibSC extends BaseSC
{
    private BigDecimal code;
    private String language;

    private BigDecimal companyCode;
    private String appName;

    private Date selectedDate;
    private BigDecimal accCurrency;
    private BigDecimal requestId;
    private BigDecimal CIF_NO;
    private BigDecimal priorityCode;
    private String VIP_ALERT;
    private String flag;
    private String usrPathSts;
    private String progRef;
    // added by jihad to return the cv amount
    private BigDecimal bcaseCurrency;
    private BigDecimal currencyAmount;
    private String keyLabelCode;
    private List<String> privilegesToCheck;
    private String popProfModAccess;
    private String isBranch;
    private String optReference;
    private Boolean switchBranch;
    private String httpSessionId;
    private String hostName;
    private String overrideSession;
    private String fromForceLogout;
   
    
    /**
     * used in Object Columns ordering management
     * @author marwanmaddah
     * @date   Oct 22, 2014
     * @return BigDecimal
     */
    private String objectId;
    private String orderArr;
    private String objectType;
    private String profType;
    //Added to set default Columns ordering 
    private String isDefault;
    
    private BigDecimal fiscalYear;
    
    private String cyIsoCode;
    
    private String appLocationType;    
    /**
     * used in save as with series management
     */
//    private List<OPTVO> seriesLst;
    
    private BigDecimal reportId;
    
    private String pwdToken;
    /**
     * used to call the execution of Dynamic SQL in Expression evaluator
     */
    private String dynamicSQLSyntax;
    /**
     * Dynamic generated Screens
     */
    private List<String> elemIds;
    private BigDecimal   screenId;
    private String       elementId;
    private String       onLoadExp;
    private BigDecimal   currElementId;
    private Map<String,Object> elemHm = new HashMap<String,Object>();
    // variable used to pass to SQL to check if MEssageCodes in application is available in database, used in CommonLibBOImpl
    private List<BigDecimal> msgCodesLst;
    /**
     * used to parse the values that should be got from session for execute expression
     * inside dynamic screen dependency process
     */
    private Map<String,Object> hmSessionExpVals = new HashMap<String,Object>();
    private String elemFormat;
    
    private String currFieldName;
    private String currElementName;

    //440134 Translation Keys with Non Case Sensitivity BLME Issue
    private int caseSensitive;
    private BigDecimal accessNtfNbDays;
    private String pthCtrlSuspendPrdType;
    
    private BigDecimal noActiveTrans;
    
    /**
     * used in teller cash Balances checking 
     */
    private BigDecimal tellerCode;
    /**
     * 
     */
    
    public BigDecimal getCode()
    {
	return code;
    }

    public void setCode(BigDecimal code)
    {
	this.code = code;
    }

    public String getLanguage()
    {
	return language;
    }

    public void setLanguage(String language)
    {
	this.language = language;
    }

    public BigDecimal getCompanyCode()
    {
	return companyCode;
    }

    public void setCompanyCode(BigDecimal companyCode)
    {
	this.companyCode = companyCode;
    }

    public String getAppName()
    {
	return appName;
    }

    public void setAppName(String appName)
    {
	this.appName = appName;
    }

    public Date getSelectedDate()
    {
	return selectedDate;
    }

    public void setSelectedDate(Date selectedDate)
    {
	this.selectedDate = selectedDate;
    }

    public BigDecimal getAccCurrency()
    {
	return accCurrency;
    }

    public void setAccCurrency(BigDecimal accCurrency)
    {
	this.accCurrency = accCurrency;
    }

    public BigDecimal getCIF_NO()
    {
	return CIF_NO;
    }

    public void setCIF_NO(BigDecimal cIFNO)
    {
	CIF_NO = cIFNO;
    }

    public String getVIP_ALERT()
    {
	return VIP_ALERT;
    }

    public void setVIP_ALERT(String vIPALERT)
    {
	VIP_ALERT = vIPALERT;
    }

    public String getFlag()
    {
	return flag;
    }

    public void setFlag(String flag)
    {
	this.flag = flag;
    }

    public BigDecimal getPriorityCode()
    {
	return priorityCode;
    }

    public void setPriorityCode(BigDecimal priorityCode)
    {
	this.priorityCode = priorityCode;
    }

    public String getProgRef()
    {
	return progRef;
    }

    public void setProgRef(String progRef)
    {
	this.progRef = progRef;
    }

    public BigDecimal getBcaseCurrency()
    {
	return bcaseCurrency;
    }

    public void setBcaseCurrency(BigDecimal bcaseCurrency)
    {
	this.bcaseCurrency = bcaseCurrency;
    }

    public BigDecimal getCurrencyAmount()
    {
	return currencyAmount;
    }

    public void setCurrencyAmount(BigDecimal currencyAmount)
    {
	this.currencyAmount = currencyAmount;
    }

    public String getKeyLabelCode()
    {
	return keyLabelCode;
    }

    public void setKeyLabelCode(String keyLabelCode)
    {
	this.keyLabelCode = keyLabelCode;
    }

    public BigDecimal getRequestId()
    {
	return requestId;
    }

    public void setRequestId(BigDecimal requestId)
    {
	this.requestId = requestId;
    }

    /**
     * @return the privilegesToCheck
     */
    public List<String> getPrivilegesToCheck()
    {
        return privilegesToCheck;
    }

    /**
     * @param privilegesToCheck the privilegesToCheck to set
     */
    public void setPrivilegesToCheck(List<String> privilegesToCheck)
    {
        this.privilegesToCheck = privilegesToCheck;
    }

    /**
     * @return the popProfModAccess
     */
    public String getPopProfModAccess()
    {
        return popProfModAccess;
    }

    /**
     * @param popProfModAccess the popProfModAccess to set
     */
    public void setPopProfModAccess(String popProfModAccess)
    {
        this.popProfModAccess = popProfModAccess;
    }

    /**
     * @return the isBranch
     */
    public String getIsBranch()
    {
        return isBranch;
    }

    /**
     * @param isBranch the isBranch to set
     */
    public void setIsBranch(String isBranch)
    {
        this.isBranch = isBranch;
    }

    /**
     * @return the usrPathSts
     */
    public String getUsrPathSts()
    {
        return usrPathSts;
    }

    /**
     * @param usrPathSts the usrPathSts to set
     */
    public void setUsrPathSts(String usrPathSts)
    {
        this.usrPathSts = usrPathSts;
    }

    public String getOptReference()
    {
        return optReference;
    }

    public void setOptReference(String optReference)
    {
        this.optReference = optReference;
    }

    /**
     * @return the switchBranch
     */
    public Boolean getSwitchBranch()
    {
        return switchBranch;
    }

    /**
     * @param switchBranch the switchBranch to set
     */
    public void setSwitchBranch(Boolean switchBranch)
    {
        this.switchBranch = switchBranch;
    }

    /**
     * @return the httpSessionId
     */
    public String getHttpSessionId()
    {
        return httpSessionId;
    }

    /**
     * @param httpSessionId the httpSessionId to set
     */
    public void setHttpSessionId(String httpSessionId)
    {
        this.httpSessionId = httpSessionId;
    }

    /**
     * @return the hostName
     */
    public String getHostName()
    {
        return hostName;
    }

    /**
     * @param hostName the hostName to set
     */
    public void setHostName(String hostName)
    {
        this.hostName = hostName;
    }

    /**
     * @return the overrideSession
     */
    public String getOverrideSession()
    {
        return overrideSession;
    }

    /**
     * @param overrideSession the overrideSession to set
     */
    public void setOverrideSession(String overrideSession)
    {
        this.overrideSession = overrideSession;
    }

    /**
     * @return the orderArr
     */
    public String getOrderArr()
    {
        return orderArr;
    }

    /**
     * @param orderArr the orderArr to set
     */
    public void setOrderArr(String orderArr)
    {
        this.orderArr = orderArr;
    }

    /**
     * @return the objectType
     */
    public String getObjectType()
    {
        return objectType;
    }

    /**
     * @param objectType the objectType to set
     */
    public void setObjectType(String objectType)
    {
        this.objectType = objectType;
    }

    /**
     * @return the objectId
     */
    public String getObjectId()
    {
        return objectId;
    }

    /**
     * @param objectId the objectId to set
     */
    public void setObjectId(String objectId)
    {
        this.objectId = objectId;
    }

    /**
     * @return the profType
     */
    public String getProfType()
    {
        return profType;
    }

    /**
     * @param profType the profType to set
     */
    public void setProfType(String profType)
    {
        this.profType = profType;
    }


    public BigDecimal getFiscalYear()
    {
        return fiscalYear;
    }

    public void setFiscalYear(BigDecimal fiscalYear)
    {
        this.fiscalYear = fiscalYear;
    }

    public String getCyIsoCode()
    {
        return cyIsoCode;
    }

    public void setCyIsoCode(String cyIsoCode)
    {
        this.cyIsoCode = cyIsoCode;
    }

    /**
     * @return the reportId
     */
    public BigDecimal getReportId()
    {
        return reportId;
    }

    /**
     * @param reportId the reportId to set
     */
    public void setReportId(BigDecimal reportId)
    {
        this.reportId = reportId;
    }

    public String getIsDefault()
    {
        return isDefault;
    }

    public void setIsDefault(String isDefault)
    {
        this.isDefault = isDefault;
    }

    public String getPwdToken()
    {
        return pwdToken;
    }

    public void setPwdToken(String pwdToken)
    {
        this.pwdToken = pwdToken;
    }

    /**
     * @return the elemIds
     */
    public List<String> getElemIds()
    {
        return elemIds;
    }

    /**
     * @param elemIds the elemIds to set
     */
    public void setElemIds(List<String> elemIds)
    {
        this.elemIds = elemIds;
    }

    /**
     * @return the screenId
     */
    public BigDecimal getScreenId()
    {
        return screenId;
    }

    /**
     * @param screenId the screenId to set
     */
    public void setScreenId(BigDecimal screenId)
    {
        this.screenId = screenId;
    }

    /**
     * @return the elementId
     */
    public String getElementId()
    {
        return elementId;
    }

    /**
     * @param elementId the elementId to set
     */
    public void setElementId(String elementId)
    {
        this.elementId = elementId;
    }

    /**
     * @return the elemHm
     */
    public Map<String, Object> getElemHm()
    {
        return elemHm;
    }

    /**
     * @param elemHm the elemHm to set
     */
    public void setElemHm(Map<String, Object> elemHm)
    {
        this.elemHm = elemHm;
    }

    public String getOnLoadExp()
    {
        return onLoadExp;
    }

    public void setOnLoadExp(String onLoadExp)
    {
        this.onLoadExp = onLoadExp;
    }

    public BigDecimal getCurrElementId()
    {
        return currElementId;
    }

    public void setCurrElementId(BigDecimal currElementId)
    {
        this.currElementId = currElementId;
    }

    public String getDynamicSQLSyntax()
    {
        return dynamicSQLSyntax;
    }

    public void setDynamicSQLSyntax(String dynamicSQLSyntax)
    {
        this.dynamicSQLSyntax = dynamicSQLSyntax;
    }
    
    public List<BigDecimal> getMsgCodesLst()
    {
        return msgCodesLst;
    }

    public void setMsgCodesLst(List<BigDecimal> msgCodesLst)
    {
        this.msgCodesLst = msgCodesLst;
    }

    public String getCurrFieldName()
    {
        return currFieldName;
    }

    public void setCurrFieldName(String currFieldName)
    {
        this.currFieldName = currFieldName;
    }

    public String getCurrElementName()
    {
        return currElementName;
    }

    public void setCurrElementName(String currElementName)
    {
        this.currElementName = currElementName;
    }

	public int getCaseSensitive() {
		return caseSensitive;
	}

	public void setCaseSensitive(int caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	/**
	 * @return the hmSessionExpVals
	 */
	public Map<String, Object> getHmSessionExpVals()
	{
	    return hmSessionExpVals;
	}

	/**
	 * @param hmSessionExpVals the hmSessionExpVals to set
	 */
	public void setHmSessionExpVals(Map<String, Object> hmSessionExpVals)
	{
	    this.hmSessionExpVals = hmSessionExpVals;
	}
	
	public String getAppLocationType()
	{
	    return appLocationType;
	}
	public void setAppLocationType(String appLocationType)
	{
	    this.appLocationType = appLocationType;
	}

	/**
	 * @return the accessNtfNbDays
	 */
	public BigDecimal getAccessNtfNbDays()
	{
	    return accessNtfNbDays;
	}

	/**
	 * @param accessNtfNbDays the accessNtfNbDays to set
	 */
	public void setAccessNtfNbDays(BigDecimal accessNtfNbDays)
	{
	    this.accessNtfNbDays = accessNtfNbDays;
	}

	/**
	 * @return the pthCtrlSuspendPrdType
	 */
	public String getPthCtrlSuspendPrdType()
	{
	    return pthCtrlSuspendPrdType;
	}

	/**
	 * @param pthCtrlSuspendPrdType the pthCtrlSuspendPrdType to set
	 */
	public void setPthCtrlSuspendPrdType(String pthCtrlSuspendPrdType)
	{
	    this.pthCtrlSuspendPrdType = pthCtrlSuspendPrdType;
	}

	public String getFromForceLogout()
	{
	    return fromForceLogout;
	}

	public void setFromForceLogout(String fromForceLogout)
	{
	    this.fromForceLogout = fromForceLogout;
	}
	

	    /**
	     * @return the noActiveTrans
	     */
	    public BigDecimal getNoActiveTrans()
	    {
	        return noActiveTrans;
	    }

	    /**
	     * @param noActiveTrans the noActiveTrans to set
	     */
	    public void setNoActiveTrans(BigDecimal noActiveTrans)
	    {
	        this.noActiveTrans = noActiveTrans;
	    }
	    /**
		 * @return the elemFormat
		 */
		public String getElemFormat()
		{
		    return elemFormat;
		}

		/**
		 * @param elemFormat the elemFormat to set
		 */
		public void setElemFormat(String elemFormat)
		{
		    this.elemFormat = elemFormat;
		}

		/**
		 * @return the tellerCode
		 */
		public BigDecimal getTellerCode()
		{
		    return tellerCode;
		}

		/**
		 * @param tellerCode the tellerCode to set
		 */
		public void setTellerCode(BigDecimal tellerCode)
		{
		    this.tellerCode = tellerCode;
		}
}
