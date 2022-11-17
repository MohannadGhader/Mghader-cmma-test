package com.path.vo.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

//import com.path.dbmaps.vo.CTSTELLERVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.path.bo.common.ConstantsCommon;

public class SessionCO implements Serializable {

	private String userName; // user name in SADS (gv_userid in Pbd)
	private String userFirstName; // user first name
	private String userLastName; // user last name
	private String usrPathSts; // user Path Status
	private String disablePrntScrn; // user disable print screen
	private String usrDefPrinter; // user default printer Name
	private String showPrintPreview; // TP317013 specify whether to show print preview upon report/advices printing

	private BigDecimal userCIFNo;// User CIF No filled on login from USR table
	private final HashMap<String, String> scannedImgMap = new HashMap<String, String>();// The list of scanned images
																						// (key= progref+popup name(ie.
																						// scan)+ _UniqueIdentifier +
																						// creation date in
																						// milliseconds, value = image
																						// name as saved on disk)

	private BigDecimal scannedCIFNo;// CIF No from IRIS Scanning (or Others, Customer Search), will be filled upon
									// iris scanned and need to be used
	// when CIF need to be filled automatically to screen
	private String scannedCIFName;// CIF Name that is used for this Session, from IRIS Scanning (or Others,
									// Customer Search)
	/**
	 * User Access Restricted Flag
	 */
	private String accessRestricted;
	private String localApproveUserName; // user Id of Manager (istr_alert.alerted_userid in Pbd)
	private String directMenuVar;// menuVar of the screen accessed directly from the Dashboard
	private String currentAppName;// current application logged in
	private String clientType;// client Type corresponds to Type Column in S_APP table
	private HashMap<String, Object> userNbFormats;// query date format for a user from database

	private BigDecimal companyCode; // Login Company Code
	private String companyName; // Login Company Description
	private String companyArabName; // Login Company Description
	private BigDecimal branchCode; // Login Branch Code
	private String branchName; // Login Branch Description
	private Date runningDateRET; // running Date of Logged In Application (only the Name contains RET)
//	private CTSTELLERVO ctsTellerVO; // teller details VO

	private BigDecimal baseCurrencyCode; // Base Currency of Login Company
	private String baseCurrencyName; // Base Currency of Login Company Name
	private BigDecimal baseCurrDecPoint; // Base Currency Decimal Point of base Currency
	private BigDecimal exposCurCode; // Exposure Currency Code
	private String exposCurName; // Exposure Currency Name
	private String showBranchesChar; // Show Branches Character filled from Show_branches of Companies Table
	private BigDecimal employeeId; // User related Employee Id
	private BigDecimal empCompCode; // Employee Company Code
	private BigDecimal empBranchCode; // Employee Branch Code

	private BigDecimal fiscalYear; // Fiscal Year
	private BigDecimal fiscalMonth; // Fiscal Year
	private String language;// language in which we are logged in
	private String preferredLanguage; // User preferred Language
	private String httpSessionID; // User http Session ID
	private String machineIp; // User Machine Name / IP ID

	private Object reportingAppDet;// details related to reporting application
	private Object consolidationAppDet; // details related to consolidation application

	private HashMap userAxsMap;// hashmap used in applying access privileges on submit buttons: key is user ID
								// and value is hashmap of progRef with its access rights

	/**
	 * HashMap used to store whether need to replace certain Element on screen by
	 * another Values, useful to prevent replacement of default values already set
	 * by certain dependency using another dependency, check Example in
	 * TrxMgntDependenciesAction Key is prog_ref concatenate by certain constant
	 * Value can be TableName.ColumnName
	 */
	private final HashMap<String, Object> checkRepValue = new HashMap<String, Object>();

	private Integer allowAccess;

	private boolean hideArabicColumns;// to hide the Arabic columns from the grid if PTHCTRL.LANGUAGE equal 1
	/**
	 * isRTLDir is used to specify whether right to left direction language chosen
	 * by User like Arabic, Farisi,.. 1 means RTL, otherwise LTR
	 */
	private int isRTLDir;

	private String favoriteId; // used in opening favorite screen from dashboard portal
	private String appId;// used in opening application from dashboard portal
	private String externalScreen; // OPT of the target external screen to be opened from current menu
	private String additionalParamsQueryString;// TP 475542 additional parameters that to be send to external Screen
	private String originalProgRef; // original OPT of the menu, from which a target external screen is opened
	private String originalAppName; // original application from which a target external screen is opened
	private String originalAppUrl; // original application URL from which a target external screen is opened
	private List<String> externalOpenedUrls; // coma separated list of external Application Names called from menu opt
	private String manualLogIn;// flag that specifies when user logs in manually using username/pwd (to
								// differentiate from auto logged in users)

	private String branchIsClosedUserLogged; // specifies that branch is closed but user is allowed to log in a do non
												// transactional actions

	private String destinationScreenUrl; // the destination url of the external screen related to other applications
											// that will be opened inside an iframe.
	private String destinationProgRef; // the destination progRef of the external screen related to other applications
										// that will be opened inside an iframe.
	private HashMap<String, Object> additionalParamsMap; // the map of parameters to be passed with the
															// destinationScreenUrl to open the external screen of the
															// other application in an iframe.

	private BigDecimal tokenVerification;// flag to either show/hide token input for verification in comp/branch screen

	private String appLocationType;

	private String usrActvXSelectdPrntr; // 654601 string to store the local user selected printer name.

	// TP #933826 :ABSAI190603 - Cannot change iMAL password from Java applications
	// To know the user is logged in by iMal('I') or LDAP('L') credentials
	private String authenticatedBy;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
		clusterSessionReplication();
	}

	public String getDirectMenuVar() {
		return directMenuVar;
	}

	public void setDirectMenuVar(String directMenuVar) {
		this.directMenuVar = directMenuVar;
		clusterSessionReplication();
	}

	public String getCurrentAppName() {
		return currentAppName;
	}

	public void setCurrentAppName(String currentAppName) {
		this.currentAppName = currentAppName;
		clusterSessionReplication();
	}

	public HashMap<String, Object> getUserNbFormats() {
		return userNbFormats;
	}

	public void setUserNbFormats(HashMap<String, Object> userNbFormats) {
		this.userNbFormats = userNbFormats;
		clusterSessionReplication();
	}

	public BigDecimal getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(BigDecimal companyCode) {
		this.companyCode = companyCode;
		clusterSessionReplication();
	}

	public BigDecimal getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(BigDecimal branchCode) {
		this.branchCode = branchCode;
		clusterSessionReplication();
	}

	public Object getReportingAppDet() {
		return reportingAppDet;
	}

	public void setReportingAppDet(Object reportingAppDet) {
		this.reportingAppDet = reportingAppDet;
		clusterSessionReplication();
	}

	public Object getConsolidationAppDet() {
		return consolidationAppDet;
	}

	public void setConsolidationAppDet(Object consolidationAppDet) {
		this.consolidationAppDet = consolidationAppDet;
		clusterSessionReplication();
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
		clusterSessionReplication();
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
		clusterSessionReplication();
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
		clusterSessionReplication();
	}

	public Date getRunningDateRET() {
		return runningDateRET;
	}

	public void setRunningDateRET(Date runningDateRET) {
		this.runningDateRET = runningDateRET;
		clusterSessionReplication();
	}

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
		clusterSessionReplication();
	}

	public BigDecimal getBaseCurrencyCode() {
		return baseCurrencyCode;
	}

	public void setBaseCurrencyCode(BigDecimal baseCurrencyCode) {
		this.baseCurrencyCode = baseCurrencyCode;
		clusterSessionReplication();
	}

	public String getShowBranchesChar() {
		return showBranchesChar;
	}

	public void setShowBranchesChar(String showBranchesChar) {
		this.showBranchesChar = showBranchesChar;
		clusterSessionReplication();
	}

	public String getHttpSessionID() {
		return httpSessionID;
	}

	public void setHttpSessionID(String httpSessionID) {
		this.httpSessionID = httpSessionID;
		clusterSessionReplication();
	}

	public String getMachineIp() {
		return machineIp;
	}

	public void setMachineIp(String machineIp) {
		this.machineIp = machineIp;
		clusterSessionReplication();
	}

	public String getLocalApproveUserName() {
		return localApproveUserName;
	}

	public void setLocalApproveUserName(String localApproveUserName) {
		this.localApproveUserName = localApproveUserName;
		clusterSessionReplication();
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
		clusterSessionReplication();
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
		clusterSessionReplication();
	}

	public BigDecimal getBaseCurrDecPoint() {
		return baseCurrDecPoint;
	}

	public void setBaseCurrDecPoint(BigDecimal baseCurrDecPoint) {
		this.baseCurrDecPoint = baseCurrDecPoint;
		clusterSessionReplication();
	}

	public BigDecimal getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(BigDecimal fiscalYear) {
		this.fiscalYear = fiscalYear;
		clusterSessionReplication();
	}

	public BigDecimal getFiscalMonth() {
		return fiscalMonth;
	}

	public void setFiscalMonth(BigDecimal fiscalMonth) {
		this.fiscalMonth = fiscalMonth;
		clusterSessionReplication();
	}

	public Integer getAllowAccess() {
		return allowAccess;
	}

	public void setAllowAccess(Integer allowAccess) {
		this.allowAccess = allowAccess;
		clusterSessionReplication();
	}

	public BigDecimal getUserCIFNo() {
		return userCIFNo;
	}

	public void setUserCIFNo(BigDecimal userCIFNo) {
		this.userCIFNo = userCIFNo;
		clusterSessionReplication();
	}

	public String getAccessRestricted() {
		return accessRestricted;
	}

	public void setAccessRestricted(String accessRestricted) {
		this.accessRestricted = accessRestricted;
		clusterSessionReplication();
	}

	public String getCompanyArabName() {
		return companyArabName;
	}

	public void setCompanyArabName(String companyArabName) {
		this.companyArabName = companyArabName;
		clusterSessionReplication();
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
		clusterSessionReplication();
	}

	public String getBaseCurrencyName() {
		return baseCurrencyName;
	}

	public void setBaseCurrencyName(String baseCurrencyName) {
		this.baseCurrencyName = baseCurrencyName;
		clusterSessionReplication();
	}

	public BigDecimal getExposCurCode() {
		return exposCurCode;
	}

	public void setExposCurCode(BigDecimal exposCurCode) {
		this.exposCurCode = exposCurCode;
		clusterSessionReplication();
	}

	public String getExposCurName() {
		return exposCurName;
	}

	public void setExposCurName(String exposCurName) {
		this.exposCurName = exposCurName;
		clusterSessionReplication();
	}

	public boolean getHideArabicColumns() {
		return hideArabicColumns;
	}

	public void setHideArabicColumns(boolean hideArabicColumns) {
		this.hideArabicColumns = hideArabicColumns;
		clusterSessionReplication();
	}

	/**
	 * 
	 * Used for check value if Exists,return true /false if not exist which mean the
	 * key existing in stored HashMap
	 * 
	 * @param keyforCheckRep
	 * @return
	 */
	public boolean checkReplaceValue(String keyforCheckRep) {
		return this.checkRepValue.containsKey(keyforCheckRep);
	}

	/**
	 * 
	 * Used for return prevoiuse value if Exists,return null if not exist which mean
	 * the key existing in stored HashMap
	 * 
	 * @param keyforCheckRep
	 * @return
	 */
	public Object returnedReplaceValue(String keyforCheckRep) {
		return this.checkRepValue.get(keyforCheckRep);
	}

	/**
	 * 
	 * Used for return replaced Value Map which mean the key existing in stored
	 * HashMap
	 * 
	 * @return Map of replaced Values
	 */
	public HashMap<String, Object> returnedReplaceMap() {
		return this.checkRepValue;
	}

	/**
	 * 
	 * Used for adding the key to the replcaValue HashMAp to specify that
	 * replacement no feasible
	 * 
	 * @param keyforCheckRep
	 * @return
	 */
	public void addReplacedValue(String keyforCheckRep, Object value) {
		this.checkRepValue.put(keyforCheckRep, value);
		clusterSessionReplication();
	}

	/**
	 * 
	 * Used for adding parameter map to the replcaValue HashMAp to specify that
	 * replacement no feasible
	 * 
	 * @param keyforCheckRep
	 * @return
	 */
	public void addReplacedValue(HashMap<String, Object> mapToAdd) {
		this.checkRepValue.putAll(mapToAdd);
		clusterSessionReplication();
	}

	/**
	 * 
	 * Used for removing the key to the replcaValue HashMAp to specify that
	 * replacement is feasible
	 * 
	 * @param keyforCheckRep
	 * @return
	 */
	public void removeReplacedValue(String keyforCheckRep) {
		this.checkRepValue.remove(keyforCheckRep);
		clusterSessionReplication();
	}

	/**
	 * 
	 * Used for removing map to the replcaValue HashMAp to specify that replacement
	 * is feasible
	 * 
	 * @param keyforCheckRep
	 * @return
	 */
	public void removeReplacedValue(HashMap<String, Object> mapToRemove) {
		if (mapToRemove != null) {
			Iterator<String> it = mapToRemove.keySet().iterator();
			String currKey;
			while (it.hasNext()) {
				currKey = it.next();
				removeReplacedValue(currKey);
			}
		}
		clusterSessionReplication();
	}

	public BigDecimal getScannedCIFNo() {
		return scannedCIFNo;
	}

	public void setScannedCIFNo(BigDecimal scannedCIFNo) {
		this.scannedCIFNo = scannedCIFNo;
		clusterSessionReplication();
	}

	/**
	 * 
	 * Used for adding parameter map to the scannedImgMap HashMAp
	 * 
	 * @param mapToAdd
	 * @return
	 */
	public void addScannedImgMap(HashMap<String, String> mapToAdd) {
		this.scannedImgMap.putAll(mapToAdd);
		clusterSessionReplication();
	}

	/**
	 * 
	 * Used for removing a row from the scannedImgMap HashMAp
	 * 
	 * @param keyForScannedImg
	 * @return
	 */
	public void removeScannedImg(String keyForScannedImg) {
		this.scannedImgMap.remove(keyForScannedImg);
		clusterSessionReplication();
	}

	/**
	 * 
	 * Used for adding the key and value to the scannedImgMap HashMAp
	 * 
	 * @param keyForScannedImg, value
	 * @return
	 */
	public void addScannedImg(String keyForScannedImg, String value) {
		this.scannedImgMap.put(keyForScannedImg, value);
		clusterSessionReplication();
	}

	/**
	 * 
	 * Used for returning the value of the scannedImgMap HashMAp
	 * 
	 * @param keyForScannedImg
	 * @return String value of the Hashmap key
	 */
	public String returnScannedImg(String keyForScannedImg) {
		return this.scannedImgMap.get(keyForScannedImg);
	}

	public String getScannedCIFName() {
		return scannedCIFName;
	}

	public void setScannedCIFName(String scannedCIFName) {
		this.scannedCIFName = scannedCIFName;
		clusterSessionReplication();
	}

	public HashMap getUserAxsMap() {
		return userAxsMap;
	}

	public void setUserAxsMap(HashMap userAxsMap) {
		this.userAxsMap = userAxsMap;
		clusterSessionReplication();
	}

	/**
	 * 
	 * Used for returning Running Date, only changed the Name of the Method Similar
	 * to getRunningDateRET
	 * 
	 * @return
	 */
	public Date returnRunningDate() {
		return runningDateRET;
	}

	public int getIsRTLDir() {
		return isRTLDir;
	}

	public void setIsRTLDir(int isRTLDir) {
		this.isRTLDir = isRTLDir;
		clusterSessionReplication();
	}

	public String getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(String favoriteId) {
		this.favoriteId = favoriteId;
		clusterSessionReplication();
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
		clusterSessionReplication();
	}

	public String getExternalScreen() {
		return externalScreen;
	}

	public void setExternalScreen(String externalScreen) {
		this.externalScreen = externalScreen;
		clusterSessionReplication();
	}

	public String getOriginalProgRef() {
		return originalProgRef;
	}

	public void setOriginalProgRef(String originalProgRef) {
		this.originalProgRef = originalProgRef;
		clusterSessionReplication();
	}

	public String getOriginalAppName() {
		return originalAppName;
	}

	public void setOriginalAppName(String originalAppName) {
		this.originalAppName = originalAppName;
		clusterSessionReplication();
	}

	public String getUsrPathSts() {
		return usrPathSts;
	}

	public void setUsrPathSts(String usrPathSts) {
		this.usrPathSts = usrPathSts;
		clusterSessionReplication();
	}

	public String getBranchIsClosedUserLogged() {
		return branchIsClosedUserLogged;
	}

	public void setBranchIsClosedUserLogged(String branchIsClosedUserLogged) {
		this.branchIsClosedUserLogged = branchIsClosedUserLogged;
		clusterSessionReplication();
	}

	public String getDisablePrntScrn() {
		return disablePrntScrn;
	}

	public void setDisablePrntScrn(String disablePrntScrn) {
		this.disablePrntScrn = disablePrntScrn;
		clusterSessionReplication();
	}

	public String getOriginalAppUrl() {
		return originalAppUrl;
	}

	public void setOriginalAppUrl(String originalAppUrl) {
		this.originalAppUrl = originalAppUrl;
		clusterSessionReplication();
	}

	public String getManualLogIn() {
		return manualLogIn;
	}

	public void setManualLogIn(String manualLogIn) {
		this.manualLogIn = manualLogIn;
		clusterSessionReplication();
	}

	public List<String> getExternalOpenedUrls() {
		return externalOpenedUrls;
	}

	public void setExternalOpenedUrls(List<String> externalOpenedUrls) {
		this.externalOpenedUrls = externalOpenedUrls;
		clusterSessionReplication();
	}

	public String getDestinationScreenUrl() {
		return destinationScreenUrl;
	}

	public void setDestinationScreenUrl(String destinationScreenUrl) {
		this.destinationScreenUrl = destinationScreenUrl;
		clusterSessionReplication();
	}

	public String getDestinationProgRef() {
		return destinationProgRef;
	}

	public void setDestinationProgRef(String destinationProgRef) {
		this.destinationProgRef = destinationProgRef;
		clusterSessionReplication();
	}

	public HashMap<String, Object> getAdditionalParamsMap() {
		return additionalParamsMap;
	}

	public void setAdditionalParamsMap(HashMap<String, Object> additionalParamsMap) {
		this.additionalParamsMap = additionalParamsMap;
		clusterSessionReplication();
	}

	public String getUsrDefPrinter() {
		return usrDefPrinter;
	}

	public void setUsrDefPrinter(String usrDefPrinter) {
		this.usrDefPrinter = usrDefPrinter;
		clusterSessionReplication();
	}

	public BigDecimal getTokenVerification() {
		return tokenVerification;
	}

	public void setTokenVerification(BigDecimal tokenVerification) {
		this.tokenVerification = tokenVerification;
		clusterSessionReplication();
	}

	public String getShowPrintPreview() {
		return showPrintPreview;
	}

	public void setShowPrintPreview(String showPrintPreview) {
		this.showPrintPreview = showPrintPreview;
		clusterSessionReplication();
	}

	public BigDecimal getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(BigDecimal employeeId) {
		this.employeeId = employeeId;
		clusterSessionReplication();
	}

	public BigDecimal getEmpCompCode() {
		return empCompCode;
	}

	public void setEmpCompCode(BigDecimal empCompCode) {
		this.empCompCode = empCompCode;
		clusterSessionReplication();
	}

	public BigDecimal getEmpBranchCode() {
		return empBranchCode;
	}

	public void setEmpBranchCode(BigDecimal empBranchCode) {
		this.empBranchCode = empBranchCode;
		clusterSessionReplication();
	}

	private void clusterSessionReplication() {
		try {
			String clusterEnabled = (String) com.path.lib.common.util.PathPropertyUtil
					.getPathRemotingProp("PathRemoting", "global.cluster.enabled");
			if (Boolean.valueOf(clusterEnabled)) {
				// in case of service side and session CO is filled not to throw
				// nullPointerException
				if (com.opensymphony.xwork2.ActionContext.getContext() != null) {
					HttpServletRequest request = ServletActionContext.getRequest();
					if (request != null) {
						HttpSession session = request.getSession();
						if (session != null) {
							session.setAttribute(ConstantsCommon.SESSION_VO_ATTR, this);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "SessionCO userName = " + this.userName + " currentAppName = " + this.currentAppName + " companyCode = "
				+ this.companyCode + " branchCode = " + this.branchCode;
	}

	public String getAdditionalParamsQueryString() {
		return additionalParamsQueryString;
	}

	public void setAdditionalParamsQueryString(String additionalParamsQueryString) {
		this.additionalParamsQueryString = additionalParamsQueryString;
		clusterSessionReplication();
	}

	public String getAppLocationType() {
		return appLocationType;
	}

	public void setAppLocationType(String appLocationType) {
		this.appLocationType = appLocationType;
	}

	public String getUsrActvXSelectdPrntr() {
		return usrActvXSelectdPrntr;
	}

	public void setUsrActvXSelectdPrntr(String usrActvXSelectdPrntr) {
		this.usrActvXSelectdPrntr = usrActvXSelectdPrntr;
	}

	public String getAuthenticatedBy() {
		return authenticatedBy;
	}

	public void setAuthenticatedBy(String authenticatedBy) {
		this.authenticatedBy = authenticatedBy;
	}

}
