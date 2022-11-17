package com.path.bo.common;

import java.math.BigDecimal;
import java.util.Date;

import com.path.lib.common.exception.BaseException;
import com.path.struts2.lib.common.ConnectionCO;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: deniskhaddad
 * 
 *          ReportsCommonBO.java used to for methods that is called by Report
 *          upon generation and need different Information from common Methods,
 *          It is a Wrapper to Existing Common Methods
 */
public interface ReportsCommonBO
{
    /**
     * Return Application Description
     * 
     * @param currentAppName Application Name
     * @return
     */
    public String returnF_GetApplication(String currentAppName, String langCode) throws BaseException;
    /**
     * returns application description taken from different database
     * @param currentAppName
     * @param langCode
     * @param conn 
     * @return
     * @throws BaseException
     */
    public String returnF_GetApplication(String currentAppName, String langCode, ConnectionCO conn) throws BaseException;

    /**
     * return arabic DEscription of given Currency Code
     * 
     * @param compCode
     * @param crrencyCode
     * @return
     * @throws BaseException
     */
    public String returnCurrencyArabName(BigDecimal compCode, BigDecimal crrencyCode) throws BaseException;
    
    /**
     * return arabic DEscription of given Currency Code taken from different database
     * 
     * @param compCode
     * @param crrencyCode
     * @return
     * @throws BaseException
     */
    public String returnCurrencyArabName(BigDecimal compCode, BigDecimal crrencyCode,ConnectionCO conn) throws BaseException;

    /**
     * return PTH control Check whether both English and Arabic or only Arabic
     * to be shown
     * 
     * @return
     * @throws BaseException
     */
    public String returnF_Get_Language() throws BaseException;
    /**
     * method that return whether provided language is RTL or not
     * @param language language to check it RTL direction
     * @param conn connection Object
     * @return 1 if RTL 0 otherwise 
     * @throws BaseException
     */
    public String returnF_Get_Rtl(String language, ConnectionCO conn) throws BaseException;
    /**
     * return PTH control Check whether both English and Arabic or only Arabic taken from different db
     * to be shown
     * 
     * @return
     * @throws BaseException
     */
    public String returnF_Get_Language(ConnectionCO conn) throws BaseException;
    /**
     * method that return ISO code for provided Language, ISO code located in SYS_PARAM_LANGUAGES table
     * @param language language to return its ISO Code
     * @param conn connection Object
     * @return ISO code for provided language
     * @throws BaseException
     */
    public String returnF_Get_ISO_Code(String language, ConnectionCO conn) throws BaseException;

    /**
     * return the country amount (balance) for use with the availment report 
     * f_get_country_amt
     * return Amount of Country for particular CIF
     * @param cifNo
     * @param companyCode
     * @param baseCurrencyCode
     * @return
     */
    public BigDecimal returnF_GET_COUNTRY_AMT(BigDecimal cifNo, BigDecimal companyCode, BigDecimal baseCurrencyCode)
	    throws BaseException;
    /**
     * return the country amount (balance) for use with the availment report taken from different db 
     * f_get_country_amt
     * return Amount of Country for particular CIF
     * @param cifNo
     * @param companyCode
     * @param baseCurrencyCode
     * @return
     */
    public BigDecimal returnF_GET_COUNTRY_AMT(BigDecimal cifNo, BigDecimal companyCode, BigDecimal baseCurrencyCode,ConnectionCO conn)
    throws BaseException;

    /**
     * return One Obligor Value for given CIF
     * 
     * @param cifNo
     * @param obligorLimit
     * @return
     */
    public BigDecimal returnF_GET_ONE_OBLIGOR(BigDecimal cifNo, BigDecimal obligorLimit, BigDecimal baseCurrencyCode,
	    BigDecimal compCode, BigDecimal currencyCode, Date runningDate) throws BaseException;
    
    /**
     * return One Obligor Value for given CIF taken from different db
     * @param cifNo
     * @param obligorLimit
     * @return
     */
    public BigDecimal returnF_GET_ONE_OBLIGOR(BigDecimal cifNo, BigDecimal obligorLimit, BigDecimal baseCurrencyCode,
	    BigDecimal compCode, BigDecimal currencyCode, Date runningDate, ConnectionCO conn) throws BaseException;

    /**
     * converts date to HIJIRI and VISE versa
     * 
     * @param dateToConv
     * @param type
     * @param companyCode
     * @return
     * @throws BaseException
     */
    public Date returnF_CONVERT_DATE_GREGO_HIJIRI(Date dateToConv, String type, BigDecimal companyCode)
	    throws BaseException;
    /**
     * converts date to HIJIRI and VISE versa taken from different db
     * @param dateToConv
     * @param type
     * @param companyCode
     * @return
     * @throws BaseException
     */
    public Date returnF_CONVERT_DATE_GREGO_HIJIRI(Date dateToConv, String type, BigDecimal companyCode, ConnectionCO conn)
    throws BaseException;

    /**
     * method that return Decimal Point for Given Currency and Company
     * 
     * @param cyCode
     * @param companyCode
     * @return
     * @throws BaseException
     */
    public BigDecimal returnF_GET_DEC_POINT(BigDecimal cyCode, BigDecimal companyCode) throws BaseException;
    /**
     * method that return Decimal Point for Given Currency and Company taken from different db
     * @param cyCode
     * @param companyCode
     * @return
     * @throws BaseException
     */
    public BigDecimal returnF_GET_DEC_POINT(BigDecimal cyCode, BigDecimal companyCode, ConnectionCO conn) throws BaseException;

    /**
     * method return short Over DEtails for given Currency
     * f_get_short_over
     * @param cyCode
     * @param companyCode
     * @param branchCode
     * @param runningDateRET
     * @return
     * @throws BaseException
     */
    public BigDecimal returnF_GET_SHORT_OVER(BigDecimal cyCode, BigDecimal companyCode, BigDecimal branchCode,
	    Date runningDateRET, BigDecimal tellerCode) throws BaseException;
    BigDecimal returnF_GET_SHORT_OVER(BigDecimal cyCode, BigDecimal companyCode, BigDecimal branchCode,
	    Date runningDateRET, BigDecimal tellerCode,ConnectionCO conn) throws BaseException;
    /**
     * return unclaimed checked value of the Account
     * 
     * @param compCode
     * @param branchCode
     * @param cyCode
     * @param glCode
     * @param cifNo
     * @param slNo
     * @return 0 or 1
     * @throws BaseException
     */
    public BigDecimal returnF_CHECK_UNCLAIMED(BigDecimal compCode, BigDecimal branchCode, BigDecimal cyCode,
	    BigDecimal glCode, BigDecimal cifNo, BigDecimal slNo, String preferredLanguage) throws BaseException;
    /**
     * return unclaimed checked value of the Account taken from different DB
     * @param compCode
     * @param branchCode
     * @param cyCode
     * @param glCode
     * @param cifNo
     * @param slNo
     * @return 0 or 1
     * @throws BaseException
     */
    public BigDecimal returnF_CHECK_UNCLAIMED(BigDecimal compCode, BigDecimal branchCode, BigDecimal cyCode,
	    BigDecimal glCode, BigDecimal cifNo, BigDecimal slNo, String preferredLanguage, ConnectionCO conn) throws BaseException;

    /**
     * return Currency details as per provided Field
     * 
     * @param field
     * @param currencyCode
     * @param compCode
     * @return
     * @throws BaseException
     */
    public String returnF_CY_DETAILS(String field, BigDecimal currencyCode, BigDecimal compCode) throws BaseException;
    
    /**
     * return Currency details as per provided Field taken from different db
     * @param field
     * @param currencyCode
     * @param compCode
     * @return
     * @throws BaseException
     */
    public String returnF_CY_DETAILS(String field, BigDecimal currencyCode, BigDecimal compCode, ConnectionCO conn) throws BaseException;
    
    /**
     * return ctscontrol cheque Digit Numbers based on Company and Branch
     * @param compCode
     * @param branchCode
     * @return
     * @throws BaseException
     */
    public BigDecimal returnF_RETURN_CHEQUE_DIGITS(BigDecimal compCode, BigDecimal branchCode)throws BaseException;
    
    /**
     * return ctscontrol cheque Digit Numbers based on Company and Branch taken from different db
     * @param compCode
     * @param branchCode
     * @return
     * @throws BaseException
     */
    public BigDecimal returnF_RETURN_CHEQUE_DIGITS(BigDecimal compCode, BigDecimal branchCode, ConnectionCO conn)throws BaseException;
    /**
     * return CIF Address 1 for provide CIF according to the Language
     * @param companyCode
     * @param language
     * @param cifNo
     * @return
     */
    public String returnCifAddress1(BigDecimal companyCode, String language, BigDecimal cifNo)throws BaseException;
    /**
     * return CIF Address 1 for provide CIF according to the Language taken from different db
     * @param companyCode
     * @param language
     * @param cifNo
     * @return
     */
    public String returnCifAddress1(BigDecimal companyCode, String language, BigDecimal cifNo, ConnectionCO conn)throws BaseException;
    /**
     * return CIF Address 2 for provide CIF according to the Language
     * @param companyCode
     * @param language
     * @param cifNo
     * @return
     * @throws BaseException
     */
    public String returnCifAddress2(BigDecimal companyCode, String language, BigDecimal cifNo)throws BaseException;
    /**
     * return CIF Address 2 for provide CIF according to the Language taken from different db
     * @param companyCode
     * @param language
     * @param cifNo
     * @return
     * @throws BaseException
     */
    public String returnCifAddress2(BigDecimal companyCode, String language, BigDecimal cifNo, ConnectionCO conn)throws BaseException;
    /**
     * return CIF Address 3 for provide CIF according to the Language
     * @param companyCode
     * @param language
     * @param cifNo
     * @return
     * @throws BaseException
     */
    public String returnCifAddress3(BigDecimal companyCode, String language, BigDecimal cifNo)throws BaseException;
    /**
     * return CIF Address 3 for provide CIF according to the Language taken from different db
     * @param companyCode
     * @param language
     * @param cifNo
     * @return
     * @throws BaseException
     */
    public String returnCifAddress3(BigDecimal companyCode, String language, BigDecimal cifNo, ConnectionCO conn)throws BaseException;
    /**
     * return CIF ShortName for provide CIF according to the Language
     * @param companyCode
     * @param language
     * @param cifNo
     * @return
     * @throws BaseException 
     */
    public String returnCifShortName(BigDecimal companyCode, String language, BigDecimal cifNo) throws BaseException;
    /**
     * return CIF ShortName for provide CIF according to the Language taken from different db
     * @param companyCode
     * @param language
     * @param cifNo
     * @return
     * @throws BaseException 
     */
    public String returnCifShortName(BigDecimal companyCode, String language, BigDecimal cifNo, ConnectionCO conn) throws BaseException;
    /**
     * return Currency BRIEF_DESC_ENG according to the Language
     * @param companyCode
     * @param language
     * @param cyCode
     * @return
     * @throws BaseException 
     */
     public String returnCurrencyDesc(BigDecimal companyCode, String language,BigDecimal cyCode)throws BaseException;
     /**
      * return Country BRIEF_DESC_ENG or Arabic according to the Language
      * @param companyCode Company Code
      * @param countryCode Country Code
      * @param language Language
      * @param conn Connection Object
      * @return Country Description.
      * @throws BaseException
      */
     public String returnCountryDesc(BigDecimal companyCode, BigDecimal countryCode, String language, ConnectionCO conn)
		    throws BaseException;
     /**
      * return Currency BRIEF_DESC_ENG according to the Language taken from different db
      * @param companyCode
      * @param language
      * @param cyCode
      * @return
      * @throws BaseException 
      */
     public String returnCurrencyDesc(BigDecimal companyCode, String language,BigDecimal cyCode, ConnectionCO conn)throws BaseException;
     
     /**
      * return Currency Unit
      * @param companyCode
      * @param baseCurrencyCode
      * @param cyCode
      * @return
      * @throws BaseException
      */
     public BigDecimal returnF_GET_CY_UNIT(BigDecimal companyCode,BigDecimal baseCurrencyCode, BigDecimal cyCode) throws BaseException;
     /**
      * return Currency Unit taken from different db
      * @param companyCode
      * @param baseCurrencyCode
      * @param cyCode
      * @return
      * @throws BaseException
      */
     public BigDecimal returnF_GET_CY_UNIT(BigDecimal companyCode,BigDecimal baseCurrencyCode, BigDecimal cyCode, ConnectionCO conn) throws BaseException;
     /**
      * returns the difference between the limit defined at the level of CIF and utilized amount of the drawdown
      * @param cifNo
      * @param decCifLimit
      * @param compCode
      * @param currencyCode
      * @param baseCurrencyCode
      * @param runningDate
      * @return
      * @throws BaseException
      */
    public BigDecimal returnF_GET_CIF_AMT(BigDecimal cifNo, BigDecimal decCifLimit,BigDecimal compCode, BigDecimal currencyCode, BigDecimal baseCurrencyCode, Date runningDate) throws BaseException;
    /**
     * returns the difference between the limit defined at the level of CIF and utilized amount of the drawdown taken from different db
     * @param cifNo
     * @param decCifLimit
     * @param compCode
     * @param currencyCode
     * @param baseCurrencyCode
     * @param runningDate
     * @return
     * @throws BaseException
     */
    public BigDecimal returnF_GET_CIF_AMT(BigDecimal cifNo, BigDecimal decCifLimit,BigDecimal compCode, BigDecimal currencyCode, BigDecimal baseCurrencyCode, Date runningDate, ConnectionCO conn) throws BaseException;

    /**
     * converts numbers to words in English
     * @param numberToConvert, number to convert 
     * @param currencyName, currency name
     * @return
     * @throws BaseException
     */
    public String returnF_NUM_TO_WORDS_ENGLISH(BigDecimal numberToConvert, String currencyName)throws BaseException;
    /**
     * converts numbers to words in English 
     * @param numberToConvert, number to convert
     * @param currencyName currency name 
     * @param addSuffix suffix to be added to the end of result, if null then default suffix is added.
     * @return
     * @throws BaseException
     */
    public String returnF_NUM_TO_WORDS_ENGLISH(BigDecimal numberToConvert, String currencyName, String addSuffix) throws BaseException;
    /**
     * converts numbers to words in English
     * @param numberToConvert, number to convert
     * @param compCode, company code
     * @param currencyCode, currency to be used in conversion
     * @param baseCurrencyCode, if no entry found for currency code use the baseCurrencyCode 
     * @return
     * @throws BaseException
     */
    public String returnF_NUM_TO_WORDS_ENGLISH(BigDecimal numberToConvert, BigDecimal compCode, BigDecimal currencyCode, BigDecimal baseCurrencyCode)throws BaseException;
    /**
     * converts numbers to words in English according to currency taken from different db
     * @param numberToConvert, number to convert
     * @param compCode, company code
     * @param currencyCode, currency to be used in conversion
     * @param baseCurrencyCode, if no entry found for currency code use the baseCurrencyCode 
     * @return
     * @throws BaseException
     */
    public String returnF_NUM_TO_WORDS_ENGLISH(BigDecimal numberToConvert, BigDecimal compCode, BigDecimal currencyCode, BigDecimal baseCurrencyCode, ConnectionCO conn)throws BaseException;
    /**
     * converts numbers to words in English according to currency taken from different db
     * @param numberToConvert, number to convert
     * @param compCode, company code
     * @param currencyCode, currency to be used in conversion
     * @param baseCurrencyCode, if no entry found for currency code use the baseCurrencyCode 
     * @param addSuffix, suffix to add at end of the result, in null default suffix is considered 
     * @param conn, Connection CO object in case other DB connection needed.
     * @return
     * @throws BaseException
     */
    public String returnF_NUM_TO_WORDS_ENGLISH(BigDecimal numberToConvert, BigDecimal compCode,
	    BigDecimal currencyCode, BigDecimal baseCurrencyCode,String addSuffix, ConnectionCO conn) throws BaseException;
 
    /**
     * converts numbers to words in Arabic 
     * @param numberToConvert, number to convert
     * @param currencyName currency name 
     * @return
     * @throws BaseException
     */
    public String returnF_NUM_TO_WORDS_ARAB_NO_ONLY(BigDecimal numberToConvert, String currencyName)throws BaseException;
    /**
     * converts numbers to words in Arabic 
     * @param numberToConvert, number to convert
     * @param currencyName currency name 
     * @param addSuffix suffix to be added to the end of result, if null then default suffix is added.
     * @return
     * @throws BaseException
     */
    public String returnF_NUM_TO_WORDS_ARAB_NO_ONLY(BigDecimal numberToConvert, String currencyName, String addSuffix) throws BaseException;
    /**
     * converts numbers to words in Arabic
     * @param numberToConvert, number to convert
     * @param compCode, company code
     * @param currencyCode, currency to be used in conversion
     * @param baseCurrencyCode, if no entry found for currency code use the baseCurrencyCode 
     * @return
     * @throws BaseException
     */
    public String returnF_NUM_TO_WORDS_ARAB_NO_ONLY(BigDecimal numberToConvert, BigDecimal compCode, BigDecimal currencyCode, BigDecimal baseCurrencyCode)throws BaseException;
    /**
     * converts numbers to words in Arabic with currency taken from different db
     * @param numberToConvert, number to convert
     * @param compCode, company code
     * @param currencyCode, currency to be used in conversion
     * @param baseCurrencyCode, if no entry found for currency code use the baseCurrencyCode 
     * @return
     * @throws BaseException
     */
    public String returnF_NUM_TO_WORDS_ARAB_NO_ONLY(BigDecimal numberToConvert, BigDecimal compCode, BigDecimal currencyCode, BigDecimal baseCurrencyCode, ConnectionCO conn)throws BaseException;
    /**
     * converts numbers to words in Arabic with currency taken from different db
     * @param numberToConvert, number to convert
     * @param compCode, company code
     * @param currencyCode, currency to be used in conversion
     * @param baseCurrencyCode, if no entry found for currency code use the baseCurrencyCode 
     * @param String currencySuffix suffix that to be added to the string provided
     * @param conn, Connection CO object in case other DB connection needed.
     * @return
     * @throws BaseException
     */
    public String returnF_NUM_TO_WORDS_ARAB_NO_ONLY(BigDecimal numberToConvert, BigDecimal compCode,BigDecimal currencyCode, BigDecimal baseCurrencyCode,String currencySuffix, ConnectionCO conn) throws BaseException;
 
    /**
     * returns a label translation
     * @param lang, language to be translated to
     * @param appName, application name
     * @param progRef, menu reference
     * @param labelKey, key to be translated
     * @return
     * @throws BaseException
     */
    public String returnKeyLabelTrans(String lang, String appName, String progRef, String labelKey)throws BaseException;
    /**
     * returns a label translation taken from different db
     * @param lang, language to be translated to
     * @param appName, application name
     * @param progRef, menu reference
     * @param labelKey, key to be translated
     * @return
     * @throws BaseException
     */
    public String returnKeyLabelTrans(String lang, String appName, String progRef, String labelKey, ConnectionCO conn)throws BaseException;
    
    /**
     * converts numbers to words in french
     * @param numberToConvert, number to convert
     * @param compCode, company code
     * @param currencyCode, currency to be used in conversion
     * @param baseCurrencyCode, if no entry found for currency code use the baseCurrencyCode 
     * @return
     * @throws BaseException
     */
    public String returnF_NUM_TO_WORDS_FRENCH(BigDecimal numberToConvert, BigDecimal compCode, BigDecimal currencyCode, BigDecimal baseCurrencyCode)throws BaseException;
    /**
     * converts numbers to words in french with currency taken from different db
     * @param numberToConvert, number to convert
     * @param compCode, company code
     * @param currencyCode, currency to be used in conversion
     * @param baseCurrencyCode, if no entry found for currency code use the baseCurrencyCode 
     * @return
     * @throws BaseException
     */
    public String returnF_NUM_TO_WORDS_FRENCH(BigDecimal numberToConvert, BigDecimal compCode, BigDecimal currencyCode, BigDecimal baseCurrencyCode, ConnectionCO conn)throws BaseException;
    /**
     * converts numbers to words in french with currency taken from different db
     * @param numberToConvert, number to convert
     * @param compCode, company code
     * @param currencyCode, currency to be used in conversion
     * @param baseCurrencyCode, if no entry found for currency code use the baseCurrencyCode 
     * @param addSuffix, suffix to be added at end of result, if null then default suffix is considered
     * @param conn, Connection CO object in case other DB connection needed.
     * @return
     * @throws BaseException
     */
    public String returnF_NUM_TO_WORDS_FRENCH(BigDecimal numberToConvert, BigDecimal compCode, BigDecimal currencyCode,
	    BigDecimal baseCurrencyCode, String addSuffix, ConnectionCO conn) throws BaseException;

   /**
    * return currency Mask format for given currency Code
    * @param cyCode currency Code
    * @return
    * @throws BaseException
    */
     public String returnF_CURRENCY_MASK_CY(BigDecimal companyCode,BigDecimal cyCode)throws BaseException;
     /**
      * return currency Mask format for given currency Code taken from different db
      * @param cyCode currency Code
      * @return
      * @throws BaseException
      */
     public String returnF_CURRENCY_MASK_CY(BigDecimal companyCode,BigDecimal cyCode, ConnectionCO conn)throws BaseException;
     /**
      * To return the PCS Log Details by given Company code and Log Number
      * 
      * @param companyCode
      * @param logNumber
      * @param fromToPeriod (FROM or TO) what need to be returned from period or to period
      * @return Log Details - String
      */
     public String returnF_GetLogDetails(BigDecimal companyCode, BigDecimal logNumber, String fromToPeriod) throws BaseException;
     /**
      * To return the PCS Log Details by given Company code and Log Number taken from different db
      * @param companyCode
      * @param logNumber
      * @param fromToPeriod (FROM or TO) what need to be returned from period or to period
      * @return Log Details - String
      */
     public String returnF_GetLogDetails(BigDecimal companyCode, BigDecimal logNumber, String fromToPeriod, ConnectionCO conn) throws BaseException;
     /**
      * To return the Branch Description by given Branch Code, company and profitGroup
      * 
      * @param companyCode
      * @param branchCode
      * @param profitGroup
      * @param isRTL specifying RTL direction
      * @return Branch Description.
      */
     public String returnF_GET_BRANCH_DESC_PCS(BigDecimal companyCode, BigDecimal branchCode, BigDecimal profitGroup, int isRTL) throws BaseException;

    /**
     * Method that will return Branch Description according to branch ID company
     * id and language, possible usage for PB f_get_arab_branch_desc()
     * 
     * @param companyCode company Code
     * @param branchCode branch Code
     * @param language language
     * @param conn Connection Object to use for data fetching
     * @return Branch Description in required language
     * @throws BaseException
     */
    public String returnF_GET_BRANCH_DESC(BigDecimal companyCode, BigDecimal branchCode, String language,
	    ConnectionCO conn) throws BaseException;

     /**
      * To return the Branch Description by given Branch Code, company and profitGroup taken from different db
      * @param companyCode
      * @param branchCode
      * @param profitGroup
      * @param isRTL specifying RTL direction
      * @return Branch Description.
      */
     public String returnF_GET_BRANCH_DESC_PCS(BigDecimal companyCode, BigDecimal branchCode, BigDecimal profitGroup, int isRTL, ConnectionCO conn) throws BaseException;
     /**
      * To return the Profit Group description with group code by given Profit
      * group
      * 
      * @param companyCode Company Code
      * @param profitGroup profit Group Code
      * @param isRTL specify which direction 
      * @return Profit Group Description.
      */
     public String returnF_GET_PFTCALC_DESC(BigDecimal companyCode, BigDecimal profitGroup, int isRTL)
		    throws BaseException;
     /**
      * To return the Profit Group description with group code by given Profit taken from different db
      * group
      * @param companyCode Company Code
      * @param profitGroup profit Group Code
      * @param isRTL specify which direction 
      * @return Profit Group Description.
      */
     public String returnF_GET_PFTCALC_DESC(BigDecimal companyCode, BigDecimal profitGroup, int isRTL, ConnectionCO conn)
     throws BaseException;
     
    /**
     * To return the no of days in a processed month by given Log Number
     * 
     * @param companyCode Company Code
     * @param profitGroup profit Group id
     * @param logNumber log Number
     * @param baseCurrencyCode Base Currency Code
     * @return noOfDays number of days in month
     */
    public BigDecimal returnF_M_Day(BigDecimal companyCode, BigDecimal profitGroup, BigDecimal logNumber,
	    BigDecimal baseCurrencyCode) throws BaseException;
    /**
     * To return the no of days in a processed month by given Log Number taken from different db
     * @param companyCode Company Code
     * @param profitGroup profit Group id
     * @param logNumber log Number
     * @param baseCurrencyCode Base Currency Code
     * @return noOfDays number of days in month
     */
    public BigDecimal returnF_M_Day(BigDecimal companyCode, BigDecimal profitGroup, BigDecimal logNumber,
	    BigDecimal baseCurrencyCode, ConnectionCO conn) throws BaseException;
    /**
     * returns Show_Brief_Long_Name indicator for specific application
     * @param appName application name for which the indicator to be shown
     * @return indicator 'B' or 'L'
     * @throws BaseException
     */
    public String returnF_LONG_SHORD_DESC_IND(String appName) throws BaseException;
    /**
     * returns Show_Brief_Long_Name indicator for specific application taken from different db
     * @param appName application name for which the indicator to be shown
     * @return indicator 'B' or 'L'
     * @throws BaseException
     */
    public String returnF_LONG_SHORD_DESC_IND(String appName, ConnectionCO conn) throws BaseException;
    /**
     * return company phone, from Companies TEL_NO or TEL_NO_ARABIC
     * @param companyCode company Code 
     * @return phone number, empty string if no phone defined
     * @throws BaseException
     */
    public String returnF_GET_COMP_PHONE(BigDecimal companyCode) throws BaseException;
    /**
     * return company phone, from Companies TEL_NO or TEL_NO_ARABIC taken from different db
     * @param companyCode company Code 
     * @return phone number, empty string if no phone defined
     * @throws BaseException
     */
    public String returnF_GET_COMP_PHONE(BigDecimal companyCode, ConnectionCO conn) throws BaseException;

    /**
     * method that masks card number to be displayed for the teller
     * 
     * @param companyCode company Code Mandatory
     * @param branchCode branch Code Mandatory
     * @param cardNb card Number Mandatory
     * @param userId user that requesting the mask operation
     * @return Masked card Number
     * @throws BaseException
     */
    public String returnF_SET_TELLER_MASK(BigDecimal companyCode, BigDecimal branchCode, String cardNb,
	    String userId) throws BaseException;
    /**
     * method that masks card number to be displayed for the teller taken from different db
     * 
     * @param companyCode company Code Mandatory
     * @param branchCode branch Code Mandatory
     * @param cardNb card Number Mandatory
     * @param userId user that requesting the mask operation
     * @return Masked card Number
     * @throws BaseException
     */
    public String returnF_SET_TELLER_MASK(BigDecimal companyCode, BigDecimal branchCode, String cardNb,
	    String userId, ConnectionCO conn) throws BaseException;
    /**
     * return PTH CTRL System Name
     * @return system name value , empty if value is null
     * @throws BaseException
     */
    public String returnSYSTEM_NAME() throws BaseException;
    /**
     * return PTH CTRL System Name taken from different db 
     * @return system name value , empty if value is null
     * @throws BaseException
     */
    public String returnSYSTEM_NAME(ConnectionCO conn) throws BaseException;
    /**
     * return application Type For Given Application
     * @param appName application Name
     * @return
     * @throws BaseException
     */
    public String returnAPP_TYPE(String appName) throws BaseException;
    /**
     * return application Type For Given Application taken from different db
     * @param appName application Name
     * @return
     * @throws BaseException
     */
    public String returnAPP_TYPE(String appName, ConnectionCO conn) throws BaseException;
    /**
     * Method used in IIS reports to return total amount of deals for specific CIF 
     * corresponds to PB Method f_get_mddr_utilized_iis_for_next (IIS Team: Fathima Reem Usman)
     * @param cifNo CIF Number Mandatory
     * @param runningDate Running Data (Optional)
     * @param branchCode Branch Code Mandatory
     * @param compCode Company Code Mandatory
     * @param baseCurrencyCode base currency Code Mandatory
     * @return total Amount of Deals 
     * @throws BaseException
     */
    public BigDecimal returnF_GET_MDDR_UTILIZED_IIS_FOR_NEXT(BigDecimal cifNo, Date runningDate, BigDecimal branchCode,
	    BigDecimal compCode, BigDecimal baseCurrencyCode) throws BaseException;
    /**
     * Method used in IIS reports to return total amount of deals for specific CIF taken from different db
     * corresponds to PB Method f_get_mddr_utilized_iis_for_next (IIS Team: Fathima Reem Usman)
     * @param cifNo CIF Number Mandatory
     * @param runningDate Running Data (Optional)
     * @param branchCode Branch Code Mandatory
     * @param compCode Company Code Mandatory
     * @param baseCurrencyCode base currency Code Mandatory
     * @return total Amount of Deals 
     * @throws BaseException
     */
    public BigDecimal returnF_GET_MDDR_UTILIZED_IIS_FOR_NEXT(BigDecimal cifNo, Date runningDate, BigDecimal branchCode,
	    BigDecimal compCode, BigDecimal baseCurrencyCode, ConnectionCO conn) throws BaseException;
    /**
     * Method used in IIS reports to return total amount on specific date for specific CIF 
     * corresponds to PB Method f_get_mddr_utilized_report_iis(IIS Team: Fathima Reem Usman)
     * @param cifNo  CIF Number Mandatory
     * @param adtDate Date on which the amount to be returned
     * @param compCode  Company Code Mandatory
     * @param branchCode Branch Code Mandatory
     * @param runningDate Running Data (Optional)
     * @param baseCurrencyCode base currency Code (Optional)
     * @return
     * @throws BaseException
     */
    public BigDecimal returnF_GET_MDDR_UTILIZED_REPORT_IIS(BigDecimal cifNo, Date adtDate, BigDecimal compCode,
	    BigDecimal branchCode, Date runningDate,BigDecimal baseCurrencyCode) throws BaseException;
    /**
     * Method used in IIS reports to return total amount on specific date for specific CIF taken from different db
     * corresponds to PB Method f_get_mddr_utilized_report_iis(IIS Team: Fathima Reem Usman)
     * @param cifNo  CIF Number Mandatory
     * @param adtDate Date on which the amount to be returned
     * @param compCode  Company Code Mandatory
     * @param branchCode Branch Code Mandatory
     * @param runningDate Running Data (Optional)
     * @param baseCurrencyCode base currency Code (Optional)
     * @return
     * @throws BaseException
     */
    public BigDecimal returnF_GET_MDDR_UTILIZED_REPORT_IIS(BigDecimal cifNo, Date adtDate, BigDecimal compCode,
	    BigDecimal branchCode, Date runningDate,BigDecimal baseCurrencyCode, ConnectionCO conn) throws BaseException;
    /**
     * Method that called in IIS report to convert to forex currency amount into Base currency amount
     * Based on PB method f_convert_to_cv (IIS Team: Fathima Reem Usman)
     * @param arAmount amount to convert
     * @param arFromcurr from currency code
     * @param arTocurr to currency code
     * @param arDate data of conversion
     * @param compCode company code
     * @param branchCode branch Code
     * @param baseCurrencyCode base Currency Code
     * @return converted amount
     * @throws BaseException
     */
    public BigDecimal returnF_CONVERT_TO_CV_IIS(BigDecimal arAmount, BigDecimal arFromcurr, BigDecimal arTocurr,
	    Date arDate, BigDecimal compCode, BigDecimal branchCode, BigDecimal baseCurrencyCode)
	    throws BaseException;
    /**
     * Method that called in IIS report to convert to forex currency amount into Base currency amount
     * Based on PB method f_convert_to_cv (IIS Team: Fathima Reem Usman)
     * @param arAmount amount to convert
     * @param arFromcurr from currency code
     * @param arTocurr to currency code
     * @param arDate data of conversion
     * @param compCode company code
     * @param branchCode branch Code
     * @param baseCurrencyCode base Currency Code
     * @return converted amount
     * @throws BaseException
     */
    public BigDecimal returnF_CONVERT_TO_CV_IIS(BigDecimal arAmount, BigDecimal arFromcurr, BigDecimal arTocurr,
	    Date arDate, BigDecimal compCode, BigDecimal branchCode, BigDecimal baseCurrencyCode, ConnectionCO conn)
    throws BaseException;

    /**
     * Method that returns date of work after adding provided amount of days and
     * counting Country and Company Holidays
     * 
     * @param alDays number of Days to Add
     * @param aDate Date to which add the days
     * @param compCode company Code
     * @param countryCode Country Code
     * @return
     * @throws BaseException
     */
    public Date returnF_GET_NEXT_WORKING_DAY_BY_COUNTRY(BigDecimal alDays, Date aDate, BigDecimal compCode,
	    BigDecimal countryCode) throws BaseException;
    /**
     * Method that returns date of work after adding provided amount of days and taken from different db
     * counting Country and Company Holidays
     * 
     * @param alDays number of Days to Add
     * @param aDate Date to which add the days
     * @param compCode company Code
     * @param countryCode Country Code
     * @return
     * @throws BaseException
     */
    public Date returnF_GET_NEXT_WORKING_DAY_BY_COUNTRY(BigDecimal alDays, Date aDate, BigDecimal compCode,
	    BigDecimal countryCode, ConnectionCO conn) throws BaseException;

   /**
    * return cross rate of given currency in running date
    * @param compCode Company Code
    * @param fromCyCode from Currency Currency
    * @param branchCode Branch Code
    * @param toCyCode To Currency Code
    * @param baseCurrencyCode Base Currency Code
    * @param runningDate Running date
    * @return
    * @throws BaseException
    */
    BigDecimal returnF_GET_CROSS_RATE(BigDecimal compCode, BigDecimal fromCyCode, BigDecimal branchCode, BigDecimal toCyCode, BigDecimal baseCurrencyCode, Date runningDate)
	    throws BaseException;
    /**
     * return cross rate of given currency in running date taken from different db
     * @param compCode Company Code
     * @param fromCyCode from Currency Currency
     * @param branchCode Branch Code
     * @param toCyCode To Currency Code
     * @param baseCurrencyCode Base Currency Code
     * @param runningDate Running date
     * @return
     * @throws BaseException
     */
    BigDecimal returnF_GET_CROSS_RATE(BigDecimal compCode, BigDecimal fromCyCode, BigDecimal branchCode, BigDecimal toCyCode, BigDecimal baseCurrencyCode, Date runningDate, ConnectionCO conn)
    throws BaseException;
    /**
     * Method that returns Branch Manager Name for Given Branch and Company the name is consist of first and last name separated by space
     * corresponds to PB method f_get_branch_manager_name
     * @param compCode Company Code
     * @param branchCode Branch Code
     * @param language Language in which the name to be returned
     * @param conn Connection CO for different report connections
     * @return Branch Manager first and last name concatenated
     * @throws BaseException
     */
    String returnF_GET_BRANCH_MANAGER_NAME(BigDecimal compCode, BigDecimal branchCode,String language, ConnectionCO conn) throws BaseException;
    
    /**
     * Method that returns Exchange Rate value for given company and currency
     * corresponds to PB method f_get_exchrate_new 
     * @param compCode Company Code
     * @param baseCurrencyCode Base Currency Code
     * @param currencyCode Currency Code
     * @param runningDate Running Date
     * @param conn Connection Object
     * @return Exchange Rate Value
     * @throws BaseException
     */
    BigDecimal returnF_GET_EXCHRATE_NEW(BigDecimal compCode, BigDecimal baseCurrencyCode, BigDecimal currencyCode,
	    Date runningDate, ConnectionCO conn) throws BaseException;

    /**
     * Method that returns translated single LOV value
     * 
     * @param lovTypeId LOV type Id from which the value to be returned
     * @param lovCode Single Code for which the description is needed, Do not
     *            delimit the code with any character specification like ' quote
     *            , since it will be automatically surrounded by quotes for
     *            character manipulation
     * @param language Language in which the description need to be returned.
     * @param conn (Optional) Connection details i report need to read from
     *            external DB.
     * @return Description of the code provided, lovCode will be returned if description not found.
     * @throws BaseException
     */
    String returnSingleLOV(BigDecimal lovTypeId, String lovCode, String language, ConnectionCO conn)
	    throws BaseException;
    /**
     * Method that returns Computed FC Amount for given company , currency and Division/Multiplication Rate
     * corresponds to PB method f_compute_fcamt 
     * @param companyCode Company Code
     * @param divRate Division Rate
     * @param fcAmount FC Amount
     * @param cyCode Currency Code
     * @param conn DAtabase Connection
     * @return Calculated FC amount
     * @throws BaseException Exception if error occurred.
     */
    public BigDecimal returnF_COMPUTE_FCAMT(BigDecimal companyCode,BigDecimal divRate, BigDecimal fcAmount ,BigDecimal cyCode,
	    ConnectionCO conn) throws BaseException;
   /**
    * Method check Account Restriction relative to PB method f_check_acc_restrictions_new
    * @param compCode Company Code
    * @param branchCode Branch Code
    * @param cyCode Currency Code
    * @param glCode GL Code
    * @param cifNo Cif Number
    * @param slNo Serial Number
    * @param appName application Name
    * @param progRef screen Reference
    * @param userId user Id
    * @param conn DAtabase Connection
    * @return  returns 1 if Account is not restricted and -1 otherwise 
    * @throws BaseException
    */
    public BigDecimal returnF_CHECK_ACC_RESTRICTION_NEWS(BigDecimal compCode, BigDecimal branchCode, BigDecimal cyCode,
	    BigDecimal glCode, BigDecimal cifNo, BigDecimal slNo, String appName, String progRef, String userId, ConnectionCO conn) throws BaseException;
    
    //TP 432958 
    /**
     * Method used to encrypt or decrypt card number according to provided parameters
     * @param encIndicator ENcryption / Decryption indicator E=Encrypt, D=Decrypt
     * @param cardNo Card No to decrypt or Encrypt
     * @param compCode Company Code which the card is related to
     * @param cifNo cifNo which is card is related to
     * @param maskCardPos The index of char where the masking will start
     * @param maskCardLength The total characters to be masked.
     * @param maskChar The char that will be replaced instead of the characters in card number
     * @return Encrypted / Decrypted value of Card
     * @throws BaseException
     */
    public String returnF_ENCRYPT_DECRYPT_CARD(String encIndicator,String cardNo,BigDecimal compCode, BigDecimal cifNo, BigDecimal maskCardPos,
	    BigDecimal maskCardLength, String maskChar) throws BaseException;
}


