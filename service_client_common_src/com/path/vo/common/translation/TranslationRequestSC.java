package com.path.vo.common.translation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: rabihelkhatib
 * 
 *          TranslationRequestSC.java used to store the search criteria for Translation Web Service
 *          
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class TranslationRequestSC implements Serializable
{
    private String pageRef;
    private String appName;
    private String defaultAppName;
    private String selectedApp;
    private BigDecimal exportType;
    private String preferredLanguage;
    private String labelKey;
    private Date dateUpdated; 
    private String exportWithoutReports;
    private String overwriteLabel;
    private String overwriteGroup;
    private String overwriteClient;
    private String webServImportTrans;
    
	public String getPageRef() {
		return pageRef;
	}
	public void setPageRef(String pageRef) {
		this.pageRef = pageRef;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getDefaultAppName() {
		return defaultAppName;
	}
	public void setDefaultAppName(String defaultAppName) {
		this.defaultAppName = defaultAppName;
	}
	public String getSelectedApp() {
		return selectedApp;
	}
	public void setSelectedApp(String selectedApp) {
		this.selectedApp = selectedApp;
	}
	public BigDecimal getExportType() {
		return exportType;
	}
	public void setExportType(BigDecimal exportType) {
		this.exportType = exportType;
	}
	public String getPreferredLanguage() {
		return preferredLanguage;
	}
	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}
	public String getLabelKey() {
		return labelKey;
	}
	public void setLabelKey(String labelKey) {
		this.labelKey = labelKey;
	}
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public String getExportWithoutReports() {
		return exportWithoutReports;
	}
	public void setExportWithoutReports(String exportWithoutReports) {
		this.exportWithoutReports = exportWithoutReports;
	}
	public String getOverwriteLabel() {
		return overwriteLabel;
	}
	public void setOverwriteLabel(String overwriteLabel) {
		this.overwriteLabel = overwriteLabel;
	}
	public String getOverwriteGroup() {
		return overwriteGroup;
	}
	public void setOverwriteGroup(String overwriteGroup) {
		this.overwriteGroup = overwriteGroup;
	}
	public String getOverwriteClient() {
		return overwriteClient;
	}
	public void setOverwriteClient(String overwriteClient) {
		this.overwriteClient = overwriteClient;
	}
	public String getWebServImportTrans() {
		return webServImportTrans;
	}
	public void setWebServImportTrans(String webServImportTrans) {
		this.webServImportTrans = webServImportTrans;
	}
}
