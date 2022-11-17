package com.path.bo.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.path.dbmaps.vo.CURRENCIESVO;
import com.path.dbmaps.vo.CURRENCIESVOKey;
import com.path.dbmaps.vo.SYS_PARAM_LANGUAGESVO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;
import com.path.vo.common.CommonLibCO;
import com.path.vo.common.CommonLibSC;
import com.path.vo.common.CurrElementExpressionsCO;
import com.path.vo.common.CurrencyToWordsCO;
import com.path.vo.common.DateParamSC;
import com.path.vo.common.mandatorylanguage.MandatoryLanguage;
//import com.path.vo.common.select.SelectCO;
//import com.path.vo.common.select.selectsc;
//import com.path.vo.common.trshijridetailcalendar.TrsHijriDetailCalendarSC;
//import com.path.vo.common.yrt.YRTSC;

public interface CommonLibBO {

	/**
	 * return error message whether BO or DOA with verbose option and language
	 * provided
	 * 
	 * @param cause    Exception thrown
	 * @param language Language of message to be translated
	 * @param verbose  boolean to specify whether generic message to be displayed
	 *                 for no BOException
	 * @return array containing message, title, SQL error code
	 */
	String[] translateErrorMessage(Throwable cause, String language, boolean verbose);

	/**
	 * method to translate the Exceptions thrown in the application
	 * 
	 * @param cause Exception Occurred
	 * @return the translated Message
	 */
	String[] translateErrorMessage(BaseException cause, String language);

	/**
	 * returns the current DBMS if it is sybase then 1 otherwise 0
	 */
	int returnIsSybase() throws BaseException;

	/**
	 * returns the current DBMS if it is sybase then 1 otherwise 0 taken from
	 * different db
	 */

	/**
	 * 
	 * @param code
	 * @param language
	 * @return
	 * @throws BaseException
	 */
	String returnTranslErrorMessage(Integer code, String language) throws BaseException;

	/**
	 * 
	 * Used for returning Message given its code and list of Parameters
	 * 
	 * @param code     Message Code
	 * @param params   List of Parameters, Should be passed translated if needed
	 * @param language User Language
	 * @return
	 * @throws BaseException
	 */
	String returnTranslErrorMessage(Integer code, String[] params, String language) throws BaseException;

	/**
	 * Added by Charbel Obeidi to be used in SwiftOutwardBOImpl. Used for returning
	 * Message given its code and list of Parameters
	 * 
	 * @param code
	 * @param language
	 * @return
	 * @throws BaseException
	 */
	String[] returnTranslMessage(Integer code, String language) throws BaseException;

	/**
	 * 
	 * Used for returning translation Message Only without the Code inside MEssage
	 * text
	 * 
	 * @param code
	 * @param params
	 * @param language
	 * @return
	 * @throws BaseException
	 */
	String returnTranslMessageOnly(Integer code, String[] params, String language) throws BaseException;

	/**
	 * 
	 * Used for returning translation Message Only without the Code inside MEssage
	 * text
	 * 
	 * @param code
	 * @param language
	 * @return
	 * @throws BaseException
	 */
	String returnTranslMessageOnly(Integer code, String language) throws BaseException;

	/**
	 * 
	 * @param obj
	 * @param colList
	 * @return
	 * @throws BaseException
	 */
//	List generateStatusList(Object obj, List<String> colList, SelectSC lovCriteria) throws BaseException;

	/**
	 * 
	 * Used for to return the system date with Time from the database
	 * 
	 * @return
	 * @throws BaseException
	 */
	Date returnSystemDateWithTime() throws BaseException;

	/**
	 * 
	 * Used for return the system date without Time from the database
	 * 
	 * @return
	 * @throws BaseException
	 */
	Date returnSystemDateNoTime() throws BaseException;

	/**
	 * 
	 * Used for set System Database Time to the given Date
	 * 
	 * @param dateToSetTime Date to which system Time to be set
	 * @return
	 * @throws BaseException
	 */
	Date addSystemTimeToDate(Date dateToSetTime) throws BaseException;

	/**
	 * Used for returning Currency VO for given company and Currency From Cache if
	 * cache Enabled and already cashed else from database CURRENCIESVOKey currKey
	 * 
	 * @return
	 * @throws BaseException
	 */
	CURRENCIESVO returnCurrency(CURRENCIESVOKey currKey) throws BaseException;

	/**
	 * Author: Denisk Haddad Function Name: returnCTSCTR Powerbuilder function:
	 * f_get_ctsctr
	 * 
	 * @param companyCode COMPANY CODE
	 * @param branchCode  BRANCH CODE
	 * @param trsType     TRS TYPE
	 * @param cbType      CB TYPE
	 * @param appName     application Name calling the counter,used for tracing
	 *                    purpose
	 * @param usrName     user Name calling who need the counter,used for tracing
	 *                    purpose
	 * @param progRef     screen reference from which the counter is called,used for
	 *                    tracing purpose
	 * @return next ctsctr counter value.
	 */
	BigDecimal returnCTSCTR(BigDecimal companyCode, BigDecimal branchCode, String trsType, String cbType,
			String appName, String userName, String progRef) throws BaseException;

	/**
	 * return next value of counter table after locking the table, based on company
	 * code, branch code and transaction type
	 * 
	 * @param companyCode COMPANY CODE
	 * @param branchCode  BRANCH CODE
	 * @param trxType
	 * @param appName     application Name calling the counter,used for tracing
	 *                    purpose
	 * @param usrName     user Name calling who need the counter,used for tracing
	 *                    purpose
	 * @param progRef     screen reference from which the counter is called,used for
	 *                    tracing purpose
	 * @return next counter value
	 */
	public BigDecimal returnCOUNTER(BigDecimal companyCode, BigDecimal branchCode, BigDecimal trxType, String appName,
			String userName, String progRef) throws BaseException;

	/**
	 * method that returns counter for IBISCOUNTER Table according to give Company ,
	 * Branch and Transaction Type
	 * 
	 * @param companyCode company Code of the counter
	 * @param branchCode  branch Code of the counter
	 * @param trxType     transaction Type of the counter
	 * @param appName     application Name calling the counter,used for tracing
	 *                    purpose
	 * @param usrName     user Name calling who need the counter,used for tracing
	 *                    purpose
	 * @param progRef     screen reference from which the counter is called,used for
	 *                    tracing purpose
	 * @return next counter value
	 * @throws BaseException
	 */
	BigDecimal returnIBISCOUNTER(BigDecimal companyCode, BigDecimal branchCode, String trxType, String appName,
			String userName, String progRef) throws BaseException;

	/**
	 * 
	 * Used for returning list of Values from LOV table for select Boxes
	 * 
	 * @param selectSC
	 * @return
	 * @throws BaseException
	 */
//	List<SelectCO> returnLOV(SelectSC selectSC) throws BaseException;

	/**
	 * Performs checking for maker checker if the same
	 * 
	 * @param creator
	 * @param modifier
	 * @return
	 * @throws BaseException
	 */
	int validateMakerChecker(String creator, String modifier, String userID) throws BaseException;

//	Map<String, SelectCO> returnLOVMap(SelectSC selectSC) throws BaseException;

//	String returnSingleLOV(SelectSC selectSC) throws BaseException;

	/**
	 * Method to perform CheckREquired and different Customization defined to the
	 * elements on given screen
	 * 
	 * @param requiredInfos         Required CO object containing the details of the
	 *                              screen, application,Company, language
	 * @param toExcludeFromChecking List of Element Names/ids to Exclude from
	 *                              validation
	 * @throws BaseException
	 */

	/**
	 * returnRIFATTInfo.
	 * 
	 * @param rifAttVO
	 * @return RIFATTVO
	 * @throws BaseException
	 */
//    RIFATTVO returnRIFATTInfo(RIFATTVO rifAttVO) throws BaseException;
	// This Function Return 3 values
	// 1-language of the Client,
	// 2-englishMandatory(true or false)
	// 3-arabicDescriptionVisible(the arabic description visible or not)

	/**
	 * Converts from Gregorian to Hijri Dates and vis versa depending on asInto (G =
	 * grego, H = hijri) PB = f_convert_date_grego_hijiri
	 * 
	 * @param date
	 * @param asInto
	 * @return
	 * @throws BaseException
	 */
//	String returnConvertDateGregoHijri(TrsHijriDetailCalendarSC hijriDetailCalendarSC) throws BaseException;

	/**
	 * Returns the next working date by adding holidays to the From Date
	 * 
	 * @return
	 */
	Date returnHolidayAddedNextWorkingDate(DateParamSC dateParamSC) throws BaseException;

	/****
	 * Method for getting the next working day by checking
	 * companies,branches,countries holidays
	 * 
	 * @param dateParamSC
	 * @return
	 * @throws BaseException
	 */
	Date getNextWrkingDate(DateParamSC dateParamSC) throws BaseException;

	/**
	 * 
	 * Used for clearing the objects from Cache, if null parameter passed all
	 * objects will be cleared otherwise specific object key will be cleared example
	 * currencies
	 * 
	 * @param specificKey clear specific Key
	 * @throws BaseException
	 */
	void clearCachedObject(String specificKey) throws BaseException;

	/**
	 * 
	 * Used for clearing Everything from Cache
	 * 
	 * @throws BaseException
	 */
	void clearCache() throws BaseException;

	/**
	 * This function will check if the date passed in parameter is a holiday in a
	 * specified country. PB = f_check_holiday_country()
	 * 
	 * @author nabil feghali
	 * @return -1 if the date is a holiday otherwise 1.
	 * @throws BaseException
	 */

	DateParamSC checkHolidayCountry(Date date, BigDecimal countryCode, BigDecimal companyCode, boolean withMessage)
			throws BaseException;

	/**
	 * This function will check if the date passed in parameter is a holiday in the
	 * specified branch. PB = f_check_holiday_comp_branch()
	 * 
	 * @author nabilfeghali
	 * @param date
	 * @param branchCode
	 * @param companyCode
	 * @param withMessage
	 * @return -1 if the date is a holiday otherwise 1.
	 * @throws BaseException
	 */
	DateParamSC checkHolidayCompBranch(Date date, BigDecimal branchCode, BigDecimal companyCode, boolean withMessage)
			throws BaseException;

	/****
	 * Method to check passed access privileges is present for the user
	 * 
	 * @param privilegesToCheck
	 * @param baseVO
	 * @return privilegesPresents
	 * @throws BaseException
	 */
	List<String> checkAccessPrivilegeForUser(CommonLibSC criteria) throws BaseException;

	/****
	 * Method for validating the passed date with Running Date
	 * 
	 * @param dateToValidate
	 * @param runningDate
	 * @param validateMethod
	 * @return true if the checking passed all validation /Throws Exceptions : Less
	 *         than Running Date, Greater than Running Date
	 * @throws BaseException
	 */
	Boolean validateDateWithRunningDate(Date dateToValidate, Date runningDate, String validateMethod)
			throws BaseException;

	/**
	 * This function returns from cache the OPT details
	 * 
	 * @param appName
	 * @param progRef
	 * @return
	 * @throws BaseException
	 */
	String[] returnOptDetailsList(String appName, String progRef) throws BaseException;

	/**
	 * 
	 * Used for returning all Languages for SYS_PARAM_LAnguages
	 * 
	 * @return
	 * @throws BaseException
	 */
//	List<SYS_PARAM_LANGUAGESVO> returnLanguages(SelectSC sc) throws BaseException;

	/**
	 * MEthod to return Language Object based on it LANGUAGE CODE
	 * 
	 * @param language language Code value
	 * @return Language VO Object
	 * @throws BaseException
	 */
	public SYS_PARAM_LANGUAGESVO returnLanguage(String language) throws BaseException;

	/**
	 * 
	 */
	/****
	 * Method for getting the computed CV amount
	 * 
	 * 
	 * /** return the country amount (balance) for use with the availment report
	 * f_get_country_amt
	 * 
	 * @param commonLibSC
	 * @return
	 * @throws BaseException
	 */
	BigDecimal returnCountryAmt(CommonLibSC commonLibSC) throws BaseException;

	/**
	 * Checks if the account is Unclaimed
	 * 
	 * @param accountSC
	 * @return
	 * @throws BaseException
	 */

	/**
	 * f_get_one_obligor
	 * 
	 * @param commonLibSC
	 * @param obligorLimit
	 * @return
	 * @throws BaseException
	 */
	BigDecimal returnOneObligor(CommonLibSC commonLibSC, BigDecimal obligorLimit) throws BaseException;

	/**
	 * f_get_cif_amt
	 * 
	 * @param commonLibSC
	 * @return
	 * @throws BaseException
	 */
	BigDecimal returnCifAmt(CommonLibSC commonLibSC) throws BaseException;

	/**
	 * PB=f_checkaccess_without_message
	 * 
	 * @param alertsSC
	 * @return
	 * @throws BaseException
	 */
	Integer checkAccessByProgRef(CommonLibSC commonLibSC) throws BaseException;

	String returnKeyLabelTrans(CommonLibSC sc) throws BaseException;

	/**
	 * This method reads from SWIFT_PRINT_HDR table based on MODULE and PROG_REF
	 * fields
	 * 
	 * @author CharbelObeidi
	 * @param swiftOutwardSC
	 * @return 0 in case no record found or a value greater than 0 in case records
	 *         are found
	 * @throws BaseException
	 */

	BigDecimal checkIrisWindow(BigDecimal cifNo, Boolean confirmFlag, String afterConfirm) throws BaseException;

	Integer validateConfirmationReport(BigDecimal reportId) throws BaseException;

	String checkUserPrivileges(CommonLibSC commonLibSC) throws BaseException;

	Object executeExpression(String expCode, int rowIndex, List<Map<String, Object>> allRows, String serviceURL)
			throws BaseException;

	Object executeExpression(String expCode, int rowIndex, List<Map<String, Object>> allRows) throws BaseException;

	/**
	 * Method returns Minimum Currency Code for given Company and Profit Group from
	 * CTSCITDET2
	 * 
	 * @param companyCode Company Code
	 * @param profitGroup profit group Id
	 * @return currency Code
	 * @throws BaseException
	 */
	BigDecimal returnCtsCitDet2Currency(BigDecimal companyCode, BigDecimal profitGroup) throws BaseException;

	/**
	 * Method returns Minimum Currency Code for given Company and Profit Group from
	 * CTSCITDET2 with connection object set in vo
	 * 
	 * @param vo
	 * @return
	 * @throws BaseException
	 */

	/**
	 * method that checks whether single user mode can be activated or not (PB
	 * method f_singleusermode_noapp CHECK case adjusted to be dynamic)
	 * 
	 * @param companyCode Company Code
	 * @param userId      User Id logged in to the application
	 * @param branchCode  Branch Code, can be null if checking not require company
	 * @param appName     application name to which the check to be perform, can be
	 *                    null so checkign will be regardless of application
	 * @return 1 if the single user mode can be activated
	 * @throws BaseException
	 */
	int checkSingleUserMode(BigDecimal companyCode, String userId, BigDecimal branchCode, String appName)
			throws BaseException;

	/**
	 * method that activate single user mode (PB method f_singleusermode_noapp SET
	 * case adjusted to be dynamic)
	 * 
	 * @param companyCode company code (Mandatory)
	 * @param userId      user ID performing the operation (Mandatory)
	 * @param branchCode  branch Code (Optional can be null)
	 * @param appName     application Name (Optional can be null)
	 * @param machineIP   machine IP performing the operation (optional)
	 * @return 1 if activated successfully
	 * @throws BaseException
	 */
	int activateSingleUserMode(BigDecimal companyCode, String userId, BigDecimal branchCode, String appName,
			String machineIP) throws BaseException;

	/**
	 * method for deactivating single user mode, set all users to be Authorised,
	 * should be called after activateSingleUserMode (PB method
	 * f_singleusermode_noapp UNSET case adjusted to be dynamic)
	 * 
	 * @param companyCode company code (Mandatory)
	 * @param userId      user ID performing the operation (Mandatory)
	 * @param branchCode  branch Code (Optional can be null)
	 * @param appName     application Name (Optional can be null)
	 * @return 1 if deactivated successfully
	 * @throws BaseException
	 */
	int deActivateSingleUserMode(BigDecimal companyCode, String userId, BigDecimal branchCode, String appName)
			throws BaseException;

	/**
	 * activation process execution (PB method f_check_process SET case adjusted to
	 * be dynamic)
	 * 
	 * @param companyCode company code (Mandatory)
	 * @param userId      user ID performing the operation (Mandatory)
	 * @param processType type of the process Periodical or Posting
	 *                    ConstantCommon.PROCESS_PREIODICAL,
	 *                    ConstantCommon.PROCESS_POSTING
	 * @param branchCode  branch Code (Optional can be null)
	 * @param appName     application Name of the process (Mandatory)
	 * @return 1 if activated successfully
	 * @throws BaseException
	 */
	int activateProcessExec(BigDecimal companyCode, String userId, String processType, BigDecimal branchCode,
			String appName) throws BaseException;

	/**
	 * de-activation process execution (PB method f_check_process SET case adjusted
	 * to be dynamic)
	 * 
	 * @param companyCode company code (Mandatory)
	 * @param userId      user ID performing the operation (Mandatory)
	 * @param processType type of the process Periodical or Posting
	 *                    ConstantCommon.PROCESS_PREIODICAL,
	 *                    ConstantCommon.PROCESS_POSTING
	 * @param branchCode  branch Code (Optional can be null)
	 * @param appName     application Name of the process (Mandatory)
	 * @return 1 if activated successfully
	 * @throws BaseException
	 */
	int deActivateProcessExec(BigDecimal companyCode, String userId, String processType, BigDecimal branchCode,
			String appName) throws BaseException;

	/**
	 * Used to do the opening branch Management
	 * 
	 * @author marwanmaddah
	 * @date Jan 21, 2014
	 * @param criteria
	 * @throws BaseException void
	 *
	 */
	void openBranchMgnt(CommonLibSC criteria) throws BaseException;

	/**
	 * used to do the open branch management
	 * 
	 * @author marwanmaddah
	 * @date Feb 3, 2014
	 * @param criteria
	 * @throws BaseException void
	 *
	 */
	void openSessionMgnt(CommonLibSC criteria) throws BaseException;

	/**
	 * used to do the final sign off management
	 * 
	 * @author marwanmaddah
	 * @date Feb 17, 2014
	 * @param criteria
	 * @throws BaseException void
	 *
	 */
	void branchSessionChecking(CommonLibSC criteria) throws BaseException;

	/**
	 * 
	 * @author marwanmaddah
	 * @date Feb 19, 2014
	 * @param criteria
	 * @throws BaseException void
	 *
	 */
	void switchCompanyBranchMgnt(CommonLibSC criteria) throws BaseException;

	/**
	 * Method that checks whether expression syntax is correct , throws Exception if
	 * not
	 * 
	 * @param expCode Expression Syntax
	 * @return true is valid Expression
	 * @throws BaseException
	 */
	boolean checkExpressionSyntax(String expCode) throws BaseException;

	/**
	 * Method used to return DB session details of current session
	 * 
	 * @return CO containing session ID and DB Node
	 * @throws BaseException
	 */
	CommonLibCO returnDBSessionDetails() throws BaseException;

	/**
	 * Method used in IIS reports to return total amount of deals for specific CIF
	 * corresponds to PB Method f_get_mddr_utilized_iis_for_next (IIS Team: Fathima
	 * Reem Usman)
	 * 
	 * @param cifNo        CIF Number Mandatory
	 * @param runningDate  Running Data (Optional)
	 * @param branchCode   Branch Code Mandatory
	 * @param compCode     Company Code Mandatory
	 * @param baseCurrency Base Currency Code
	 * @return total Amount of Deals
	 * @throws BaseException
	 */
	BigDecimal returnMddrUtilizedIISForNext(BigDecimal cifNo, Date runningDate, BigDecimal branchCode,
			BigDecimal compCode, BigDecimal baseCurrencyCode) throws BaseException;

	/**
	 * Method used in IIS reports to return total amount of deals for specific CIF
	 * taken from different DB
	 * 
	 * @param cifNo        CIF Number Mandatory
	 * @param runningDate  Running Data (Optional)
	 * @param branchCode   Branch Code Mandatory
	 * @param compCode     Company Code Mandatory
	 * @param baseCurrency Base Currency Code
	 * @param conn         contains connection details
	 * @return total Amount of Deals
	 * @throws BaseException
	 */
	/**
	 * Method used in IIS reports to return total amount on specific date for
	 * specific CIF corresponds to PB Method f_get_mddr_utilized_report_iis(IIS
	 * Team: Fathima Reem Usman)
	 * 
	 * @param cifNo            CIF Number Mandatory
	 * @param adtDate          Date on which the amount to be returned
	 * @param compCode         Company Code Mandatory
	 * @param branchCode       Branch Code Mandatory
	 * @param runningDate      Running Data (Optional)
	 * @param baseCurrencyCode BaseCurrency Code (Optional)
	 * @return
	 * @throws BaseException
	 */
	BigDecimal returnMddrUtilizedReportIIS(BigDecimal cifNo, Date adtDate, BigDecimal compCode, BigDecimal branchCode,
			Date runningDate, BigDecimal baseCurrencyCode) throws BaseException;

	/**
	 * returns concatenated string of user profiles Ex prof1,Prof3,prof8...
	 * 
	 * @param commonLibSc
	 * @return
	 * @throws BaseException
	 */
	String returnUserProfiles(CommonLibSC commonLibSc) throws BaseException;

	/**
	 * returns result of SQL query which should be select query and not
	 * update/insert/delete
	 * 
	 * @param commonLibSc Object containing the SQL syntax in dynamicSQLSyntax
	 *                    property
	 * @return first row of SQL result as Map object
	 * @throws BaseException Error in case the SQL syntax not starts with SELECT, or
	 *                       error in execution
	 */
	Map<String, Object> returnSQLSyntaxResultFirstRow(CommonLibSC commonLibSc) throws BaseException;

	/**
	 * Method that called in IIS report to convert to forex currency amount into
	 * Base currency amount Based on PB method f_convert_to_cv (IIS Team: Fathima
	 * Reem Usman)
	 * 
	 * @param arAmount         amount to convert
	 * @param arFromcurr       from currency code
	 * @param arTocurr         to currency code
	 * @param arDate           data of conversion
	 * @param compCode         company code
	 * @param branchCode       branch Code
	 * @param baseCurrencyCode base Currency Code
	 * @return converted amount
	 * @throws BaseException
	 */
	BigDecimal returnConvertToCvIIS(BigDecimal arAmount, BigDecimal arFromcurr, BigDecimal arTocurr, Date arDate,
			BigDecimal compCode, BigDecimal branchCode, BigDecimal baseCurrencyCode) throws BaseException;

	/**
	 * Method that called in IIS report to convert to forex currency amount into
	 * Base currency amount taken from different DB
	 * 
	 * @param arAmount         amount to convert
	 * @param arFromcurr       from currency code
	 * @param arTocurr         to currency code
	 * @param arDate           data of conversion
	 * @param compCode         company code
	 * @param branchCode       branch Code
	 * @param baseCurrencyCode base Currency Code
	 * @param conn             contains connection details
	 * @return converted amount
	 * @throws BaseException
	 */
	/**
	 * This method will add number of days to aDate and returns new date, it will
	 * exclude the holidays.Based on IIS team request (IIS Team: Fathima Reem Usman)
	 * 
	 * @param alDays      number of days to Add
	 * @param aDate       Date to add the days to
	 * @param compCode    Company Code
	 * @param countryCode Country Code
	 * @return New Date added the provided days and counting holidays
	 * @throws BaseException
	 */
	Date returnNextWorkingDayByCountry(BigDecimal alDays, Date aDate, BigDecimal compCode, BigDecimal countryCode)
			throws BaseException;

	/**
	 * This method will add number of days to aDate and returns new date, it will
	 * exclude the holidays (taken from different db)
	 * 
	 * @param alDays      number of days to Add
	 * @param aDate       Date to add the days to
	 * @param compCode    Company Code
	 * @param countryCode Country Code
	 * @param conn        contains connection details
	 * @return New Date added the provided days and counting holidays
	 * @throws BaseException
	 */
	/**
	 * Used to return Opt Reference of the entered OPT
	 * 
	 * @author marwanmaddah
	 * @date Mar 3, 2014
	 * @param appName
	 * @param theProgRef
	 * @return
	 * @throws BaseException String
	 *
	 */
	String returnOrginProgRef(String appName, String theProgRef) throws BaseException;

	/**
	 * Used to process the closing of branch
	 * 
	 * @author RabihElKhatib
	 * @date Feb 03, 2014
	 * @param criteria
	 * @throws BaseException
	 */
	void closeBranchMgnt(CommonLibSC criteria) throws BaseException;

	/**
	 * return cross rate of given from currency and to currency, running date, base
	 * currency
	 * 
	 * @param compCode         Company Code
	 * @param fromCyCode       from Currency Code
	 * @param branchCode       Branch Code
	 * @param toCyCode         to Currency Code
	 * @param baseCurrencyCode Base Currency Code
	 * @param runningDate      Running Date
	 * @return
	 * @throws BaseException
	 */
	BigDecimal returnCrossRate(BigDecimal compCode, BigDecimal fromCyCode, BigDecimal branchCode, BigDecimal toCyCode,
			BigDecimal baseCurrencyCode, Date runningDate) throws BaseException;

	/**
	 * return cross rate of given from currency and to currency, running date, base
	 * currency taken from different db
	 * 
	 * @param compCode         Company Code
	 * @param fromCyCode       from Currency Code
	 * @param branchCode       Branch Code
	 * @param toCyCode         to Currency Code
	 * @param baseCurrencyCode Base Currency Code
	 * @param runningDate      Running Date
	 * @param conn             contains connection details
	 * @return
	 * @throws BaseException
	 */
	/**
	 * Check user validity to open/close session/branch for CSM application.
	 * 
	 * @param cyCode
	 * @param runningdate
	 * @param decimalPoint
	 * @return
	 * @throws BaseException
	 */
	public HashMap<String, String> checkUserValidity(CommonLibSC criteria) throws BaseException;

	/**
	 * method that return the series prog ref by opt reference
	 * 
	 * @author nabil feghali
	 * @param CommonLibSC
	 * @return
	 * @throws BaseException
	 *
	 */
	public String returnSeriesOptByRef(CommonLibSC commonLibSc) throws BaseException;

	/**
	 * returns the help_path from OPT table
	 * 
	 * @param commonLibSc
	 * @return
	 * @throws BaseException
	 */
	String returnHelpPathFromOpt(CommonLibSC commonLibSc) throws BaseException;

	/**
	 * used to check if the selected running date is great than system date in case
	 * is greater than , an exception will appear
	 * 
	 * @author marwanmaddah
	 * @date Sep 18, 2014
	 * @param newRunningDate
	 * @throws BaseException void
	 *
	 */
	public void checkRunningDate(Date newRunningDate) throws BaseException;

	/**
	 * Used to save Grid Column index
	 * 
	 * @author marwanmaddah
	 * @date Oct 22, 2014 void
	 *
	 */
	public void saveGridColumnsOrder(CommonLibSC criteria) throws BaseException;

	/**
	 * Used to reset grid user Preferences
	 * 
	 * @author marwanmaddah
	 * @date Oct 29, 2014
	 * @param criteria
	 * @throws BaseException void
	 *
	 */
	public void resetGridColumnsOrder(CommonLibSC criteria) throws BaseException;

	/**
	 * method to update log level at service layer
	 * 
	 * @param currLogLevel
	 * @throws BaseException
	 */
	void updateLogLevel(String currLogLevel) throws BaseException;

	/**
	 * returns for each language the convert_like_language
	 * 
	 * @return
	 * @throws BaseException
	 */
	Map<String, String> returnConvertLikeLangMap(String langCode) throws BaseException;

	/**
	 * return for specified language what is the conversion_method_id to be used
	 * 
	 * @param langCode
	 * @return
	 * @throws BaseException
	 */
	String returnConversionMethodId(String langCode) throws BaseException;

	/**
	 * returns currency names per language
	 * 
	 * @param langCode
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	CurrencyToWordsCO returnCurrencyToWordsCO(String langCode, CURRENCIESVO vo) throws BaseException;

	/**
	 * returns numbers in words values for specified language
	 * 
	 * @param langCode
	 * @return
	 * @throws BaseException
	 */
	HashMap<String, String[]> returnNumInWordsByLangDefaultMap(String langCode) throws BaseException;

	/**
	 * Used to check if the report has arguments in case it has the returned value
	 * will be greater than 0
	 * 
	 * @author marwanmaddah
	 * @date Mar 25, 2015
	 * @param criteria
	 * @return
	 * @throws BaseException int
	 *
	 */
	public int checkReportDisplayArgs(CommonLibSC criteria) throws BaseException;

	/**
	 * Method that will return Array of Application Version of Service Wars.
	 * 
	 * @return Array Containing [APP_VERION, APP_INTERNAL Build]
	 * @throws BaseException
	 */
	public String[] returnServiceAppVersions() throws BaseException;

	/**
	 * Used to return the Running date from an external source via a web services
	 * call.
	 * 
	 * @author marwanmaddah
	 * @date Oct 29, 2015
	 * @param criteria
	 * @return
	 * @throws BaseException Date
	 *
	 */
	public Date returnExternalRunningDate(CommonLibSC criteria) throws BaseException;

	/**
	 * Control Clustered operations to lock or synchronize a scheduled task
	 * operationName is the name of the task to be controled blocking when set to
	 * true the control will perform a pessimistic lock else optimistic lock and
	 * returns boolean value to allow/stop runing a task.
	 * 
	 * @param operationName
	 * @param blocking
	 * @return boolean
	 * @throws BaseException
	 */
	public boolean clusterOperationControl(String operationName, boolean blocking) throws BaseException;

	// TP 773207
	/**
	 * Method that will update the time in SYS_CLUSTER_OPERATION_CTRL, that could be
	 * used to identify which is the active server
	 * 
	 * @param operationName  Operation name on which the time to be updated
	 * @param timeDifference the milliseconds which to compare as difference between
	 *                       operation time and current time, if passed as null then
	 *                       value of property global.cluster.timespan from
	 *                       PathService.properties will be considered.
	 * @return true/ false whether the record is updated or not
	 * @throws BaseException
	 */
	public boolean clusterOperationControlTimeUpdate(String operationName, Long timeDifference) throws BaseException;

	/**
	 * When using clusterOperationControl with blocking set to true then this method
	 * must be called after the processing is complete in order to release locked
	 * threads/sessions.
	 * 
	 * @param operationName
	 * @return boolean
	 * @throws BaseException
	 */
	public boolean unlockClusterOperation(String operationName) throws BaseException;

	/**
	 * 
	 * marwanmaddah
	 */
	public List<CurrElementExpressionsCO> returnCurrElementExpression(CommonLibSC commonLibSC) throws BaseException;

	/**
	 * Method that return MessageCodes that are available in MessageCodes.java but
	 * not existing in CTSMESSAGES in the DB Methood used for debugging reason, and
	 * prior identification what is missing.
	 * 
	 * @return String of Message codes to be displayed
	 * @throws BaseException
	 */
	public String returnMissingMessageCodes() throws BaseException;

	/**
	 * Method that returns CTSMESSAGES that have empty desc or title in EN, FR or AR
	 * in the DB Method used for debugging reason, and prior identification what is
	 * empty.
	 * 
	 * @param languages
	 * @return String of Message codes, desc and titles to be displayed
	 * @throws BaseException
	 */
	public String returnEmptyCtsMessages(String languages) throws BaseException;

	/**
	 * Method that returns CTSMESSAGES that have corrupted desc or title in EN, FR
	 * or AR in the DB Method used for debugging reason, and prior identification
	 * what is corrupted.
	 * 
	 * @param languages
	 * @return String of Message codes, desc and titles to be displayed
	 * @throws BaseException
	 */
	public String returnCorruptedCtsMessages(String languages) throws BaseException;

	/**
	 * method used to populate printers found on server into table SYS_WEB_PRINTERS
	 * 
	 * @throws Exception
	 */
	public void populatePrinters() throws Exception;

	public void forceLogoutUsers(CommonLibSC commonLibSC) throws BaseException;

	/**
	 * enable / disable the log by user
	 * 
	 * @param userName
	 * @param enableFlag
	 * @throws BaseException
	 */
	public void updateLogByUser(String userName, boolean enableFlag) throws BaseException;

	/**
	 * @param afterExecParamsMap
	 * @throws BaseException
	 */
	public ArrayList<Map<String, Object>> globalActivityAfterExec(HashMap<String, Object> afterExecParamsMap)
			throws BaseException;

}
