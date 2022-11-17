package com.path.vo.common.translation;

import java.math.BigDecimal;
import java.util.Date;

import com.path.bo.common.ConstantsCommon;
import com.path.struts2.lib.common.GridParamsSC;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: rabihelkhatib
 * 
 *          TranslationSC.java used to store the search criteria for Translation
 *          Screen
 */

public class TranslationSC extends GridParamsSC
{
    private String pageRef;
    private String appName;
    private String keyLabelCode;
    private BigDecimal keyGroupID;
    private String langCode;
    private String transUpdate;
    private String transDeps;
    private String keyGroupDesc;
    private String keyGroupTrans;
    private String keyLabelTrans;
    private String defaultAppName = ConstantsCommon.IMAL_APP_NAME;
    private BigDecimal loadSelected;
    private String rootDesc;
    private Boolean globalRef;
    private String selectedApp;
    private BigDecimal exportType;
    private String labelKey;
    private String fromCust;
    private Date dateUpdated; 
    private BigDecimal lovTypeID;
    private String exportWithoutReports;

    public String getExportWithoutReports()
    {
        return exportWithoutReports;
    }

    public void setExportWithoutReports(String exportWithoutReports)
    {
        this.exportWithoutReports = exportWithoutReports;
    }

    public String getPageRef()
    {
	return pageRef;
    }

    public void setPageRef(String pageRef)
    {
	this.pageRef = pageRef;
    }

    public String getAppName()
    {
	return appName;
    }

    public void setAppName(String appName)
    {
	this.appName = appName;
    }

    public String getKeyLabelCode()
    {
	return keyLabelCode;
    }

    public void setKeyLabelCode(String keyLabelCode)
    {
	this.keyLabelCode = keyLabelCode;
    }

    public BigDecimal getKeyGroupID()
    {
	return keyGroupID;
    }

    public void setKeyGroupID(BigDecimal keyGroupID)
    {
	this.keyGroupID = keyGroupID;
    }

    public String getLangCode()
    {
	return langCode;
    }

    public void setLangCode(String langCode)
    {
	this.langCode = langCode;
    }

    public String getTransUpdate()
    {
	return transUpdate;
    }

    public void setTransUpdate(String transUpdate)
    {
	this.transUpdate = transUpdate;
    }

    public String getKeyGroupDesc()
    {
	return keyGroupDesc;
    }

    public void setKeyGroupDesc(String keyGroupDesc)
    {
	this.keyGroupDesc = keyGroupDesc;
    }

    public String getDefaultAppName()
    {
	return defaultAppName;
    }

    public void setDefaultAppName(String defaultAppName)
    {
	this.defaultAppName = defaultAppName;
    }

    public void setLoadSelected(BigDecimal loadSelected)
    {
	this.loadSelected = loadSelected;
    }

    public BigDecimal getLoadSelected()
    {
	return loadSelected;
    }

    public String getRootDesc()
    {
        return rootDesc;
    }

    public void setRootDesc(String rootDesc)
    {
        this.rootDesc = rootDesc;
    }

    public Boolean getGlobalRef()
    {
        return globalRef;
    }

    public void setGlobalRef(Boolean globalRef)
    {
        this.globalRef = globalRef;
    }

    public void setTransDeps(String transDeps)
    {
	this.transDeps = transDeps;
    }

    public String getTransDeps()
    {
	return transDeps;
    }

    public void setKeyGroupTrans(String keyGroupTrans)
    {
	this.keyGroupTrans = keyGroupTrans;
    }

    public String getKeyGroupTrans()
    {
	return keyGroupTrans;
    }

    public String getSelectedApp()
    {
        return selectedApp;
    }

    public void setSelectedApp(String selectedApp)
    {
        this.selectedApp = selectedApp;
    }

    public String getKeyLabelTrans()
    {
        return keyLabelTrans;
    }

    public void setKeyLabelTrans(String keyLabelTrans)
    {
        this.keyLabelTrans = keyLabelTrans;
    }

    /**
     * @return the labelKey
     */
    @Override
    public String getLabelKey()
    {
        return labelKey;
    }

    /**
     * @param labelKey the labelKey to set
     */
    @Override
    public void setLabelKey(String labelKey)
    {
        this.labelKey = labelKey;
    }

    /**
     * @return the exportType
     */
    public BigDecimal getExportType()
    {
        return exportType;
    }

    /**
     * @param exportType the exportType to set
     */
    public void setExportType(BigDecimal exportType)
    {
        this.exportType = exportType;
    }

    /**
     * @return the fromCust
     */
    public String getFromCust()
    {
        return fromCust;
    }

    /**
     * @param fromCust the fromCust to set
     */
    public void setFromCust(String fromCust)
    {
        this.fromCust = fromCust;
    }

    public Date getDateUpdated()
    {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated)
    {
        this.dateUpdated = dateUpdated;
    }

    public BigDecimal getLovTypeID()
    {
        return lovTypeID;
    }

    public void setLovTypeID(BigDecimal lovTypeID)
    {
        this.lovTypeID = lovTypeID;
    }
}
