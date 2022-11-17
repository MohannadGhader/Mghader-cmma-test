package com.path.bo.omni.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.path.bo.common.ConstantsCommon;

/**
 * 
 * Copyright 2017, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: ChadiAssaf
 *
 *          OmniCommonConstants.java used to
 */
public class OmniCommonConstants extends ConstantsCommon
{

    /******************************
     * Begin of core static values
     */
    public static final String GL_TYPE_N = "N";
    public static final String BS_IND_Y = "Y";
    public static final String BS_CONTRA_B = "B";
    public static final String AC_SIGN_C = "C";
    public static final String GL_CATEGORY_L = "L";

    public static final String NOSTRO_MVT_0 = "0";

    public static final String STATUS_A = "A";
    public static final String STATUS_O = "O";
    public static final String STATUS_P = "P";
    public static final String STATUS_R = "R";
    public static final String STATUS_I = "I";

    public static final String SYSTEM_AVAILABLE_YN_Y = "Y";

    public static final String CHNL_CODE_0 = "0";
    public static final String APP_CODE_0 = "0";

    public static final String APPLY_AUTHENTICATION_YN_Y = "Y";
    public static final String MULTIUSER_YN_Y = "Y";
    public static final String ENABLED_YN_Y = "Y";
    public static final String ENABLED_YN_N = "N";
    public static final String EDIT_USER = "edit_user";
    public static final String INSERT_USER = "add_user";

    /** @author alialmoussawi #674240 SMTP password not updating in table */
    public static final String SMTP_PASS_ENC_KEY = "P@thSOluti0ns123";
    /***/

    /**
     * @author Suhail.Shoukathali
     */
    public static final String UPDATE_VERIFICATION_IMAGE = "update_verification_image";
    public static final String STATUS_OPENED_YN_Y = "'Y'";
    public static final String STATUS_OPENED_YN_N = "'N'";
    public static final String STATUS_ACTIVE_YN_Y = "'Y'";
    public static final String STATUS_ACTIVE_YN_N = "'N'";
    public static final String STATUS_ACTIVE_A = "'A'";
    public static final String STATUS_DELETED_D = "'D'";
    public static final String STATUS_REJECTED_R = "'R'";
    public static final String BRODCASTED_YN_Y = "'Y'";
    public static final String BRODCASTED_YN_N = "'N'";
    public static final String SENDER_TYPE_A = "'A'";// ADMIN
    public static final String SENDER_TYPE_C = "'C'";// CHANNEL
    public static final String DEFAULT_NUMBER_OF_CONVERTATIONS = "20";// CHANNEL

    // 807922 mohammad shour
    public static final String tradeDate = "T";
    public static final String valueDate = "V";
    /*
     * End of core static values
     */

    /********************************
     * Begin of mappers static values
     */

    public static final String ARAB_LANGUAGE = "AR";
    public static final String ENGLISH_LANGUAGE = "EN";
    public static final String PREFERRED_LANGUAGE_ARABIC = "A";
    public static final String NA = "NA";

    /*
     * End of mappers static values
     */

    public static final String REFERENCE_UPDATE = "U";
    public static final String REFERENCE_APPROVE_REJECT = "V";
    public static final String REFERENCE_APPROVE = "P";
    public static final String REFERENCE_REJECT = "R";
    public static final String REFERENCE_DELETE = "D";
    public static final String REFERENCE_DELIVER = "L";
    public static final String REFERENCE_ACTIVATE = "A";
    public static final String REFERENCE_SUSPEND = "S";
    public static final String REFERENCE_REACTIVATE = "Z";

    public static final String REFERENCE_APPROVE_NEW = "PA";
    public static final String REFERENCE_APPROVE_TO_BE_DELETED = "PB";
    public static final String REFERENCE_APPROVE_TO_BE_DELIVERED = "PC";
    public static final String REFERENCE_APPROVE_TO_BE_ACTIVATED = "PD";
    public static final String REFERENCE_APPROVE_INACTIVE = "PE";
    public static final String REFERENCE_APPROVE_TO_BE_SUSPENDED = "PF";
    public static final String REFERENCE_APPROVE_TO_BE_REACTIVATED = "PG";

    public static final String REFERENCE_REJECT_NEW = "RA";
    public static final String REFERENCE_REJECT_TO_BE_DELETED = "RB";
    public static final String REFERENCE_REJECT_TO_BE_DELIVERED = "RC";
    public static final String REFERENCE_REJECT_TO_BE_ACTIVATED = "RD";
    public static final String REFERENCE_REJECT_INACTIVE = "RE";
    public static final String REFERENCE_REJECT_TO_BE_SUSPENDED = "RF";
    public static final String REFERENCE_REJECT_TO_BE_REACTIVATED = "RG";
    public static final String REFERENCE_RESET_PASSWORD = "REPASS";
    public static final String REFERENCE_RESET_PIN = "REPIN";
    public static final String ACCOUNT_OVERDRAWN = "Y";
    public static final String ACCOUNT_NOT_OVERDRAWN = "N";
    public static final String STATUS_NEW = "N";
    public static final String STATUS_INACTIVE = "I";
    public static final String STATUS_PENDING = "P";
    public static final String STATUS_TO_BE_DELETED = "T";
    public static final String STATUS_APPROVED = "A";
    public static final String STATUS_REJECTED = "R";
    public static final String STATUS_DELETED = "D";
    public static final String STATUS_BLOCKED = "B";
    public static final String STATUS_TO_BE_ACTIVATED = "TP";
    public static final String STATUS_ACTIVE = "ACT";
    public static final String STATUS_TO_BE_SUSPENDED = "TS";
    public static final String STATUS_SUSPENDED = "S";
    public static final String STATUS_TO_BE_REACTIVATED = "TA";
    public static final String STATUS_TO_BE_DELIVERD = "TDL";
    public static final String STATUS_DELIVERD = "DL";
    public static final String STATUS_DRAFT = "DR";
    public static final String STATUS_DRAFT_EDITED = "DRE";
    public static final String STATUS_SUBMITTED = "B";
    public static final String TRANSACTION_TYPE_D = "D";
    public static final String TRANSACTION_TYPE_SAS = "S";// submit a save
							  // request
    public static final String STATUS_PENDING_EDIT = "PE";

    public static final String STATUS_ENABLED = "E";
    public static final String STATUS_TO_BE_ENABLED = "TE";
    public static final String STATUS_DISABLED = "DS";
    public static final String STATUS_TO_BE_DISABLED = "TDS";
    public static final String STATUS_TO_BE_RECTIVATED = "TRA";
    public static final String STATUS_TO_BE_UPDATED = "TU";
    public static final String STATUS_UPDATED = "UP";
    /**
     * @author Suhail.Shoukathali TP : 683469
     */
    public static final String STATUS_REJECTED_EDITED = "RE";
    public static final String STATUS_DEACTIVE = "DE";
    public static final String VO_PACKAGE = "com.path.dbmaps.vo";

    public static List<String> getStatusFields()
    {
	ArrayList<String> statusFields = new ArrayList<>();
	statusFields.add("CREATED_BY," + STATUS_NEW + ",CREATED_DATE");
	statusFields.add("MODIFIED_BY," + STATUS_INACTIVE + ",MODIFIED_DATE");
	statusFields.add("DELETED_BY," + STATUS_TO_BE_DELETED + ",DELETED_DATE");
	statusFields.add("APPROVED_BY," + STATUS_APPROVED + ",APPROVED_DATE");
	statusFields.add("REJECTED_BY," + OmniCommonConstants.STATUS_REJECTED + ",REJECTED_DATE");
	return statusFields;

    }

    public static final String RECORD_ACTION_INSERT = "save";
    public static final String RECORD_ACTION_SAVE = "save";
    public static final String RECORD_ACTION_APPROVE = "approve";
    public static final String RECORD_ACTION_UPDATE = "update";
    public static final String RECORD_ACTION_REJECT = "reject";
    public static final String RECORD_ACTION_DELETE = "delete";
    public static final String RECORD_ACTION_DELIVER = "deliver";
    public static final String RECORD_ACTION_ACTIVATE = "activate";
    public static final String RECORD_ACTION_SUSPEND = "suspend";
    public static final String RECORD_ACTION_REACTIVATE = "reactivate";

    public static final String RECORD_ACTION_ENABLE = "enable";
    public static final String RECORD_ACTION_DISBALE = "disable";

    public static final String TYPE_REQUEST = "REQ";
    public static final String TYPE_SREQUEST = "SREQ";
    public static final String TYPE_REPORT = "REP";
    public static final String TYPE_SREPORT = "SREP";

    public static final String INCLUDED_OPER_IDS_EVENTS_LOOKUP = "863,1320";
    public static final String EXCLUDED_OPER_IDS_EVENTS_LOOKUP = "145,138,139,33,1165,658,659,810,856,867,1062,1108,1124,1189,1250,0,65,78,79,80,83,89,111,1544,1465,1466,1468,108,109,1558";

    /**
     * @author Suhail.Shoukathali
     */
    public static final String RECORD_ACTION_APPROVE_NEW = "approveNew";
    public static final String RECORD_ACTION_APPROVE_TO_BE_DELETED = "approveToBeDeleted";
    public static final String RECORD_ACTION_APPROVE_TO_BE_DELIVERED = "approveToBeDelivered";
    public static final String RECORD_ACTION_APPROVE_TO_BE_ACTIVATED = "approveToBeActivated";
    public static final String RECORD_ACTION_APPROVE_INACTIVE = "approveInactive";
    public static final String RECORD_ACTION_APPROVE_TO_BE_SUSPENDED = "approveToBeSuspended";
    public static final String RECORD_ACTION_APPROVE_TO_BE_REACTIVATED = "approveToBeReactivated";

    public static final String RECORD_ACTION_REJECT_NEW = "rejectNew";
    public static final String RECORD_ACTION_REJECT_TO_BE_DELETED = "rejectToBeDeleted";
    public static final String RECORD_ACTION_REJECT_TO_BE_DELIVERED = "rejectToBeDelivered";
    public static final String RECORD_ACTION_REJECT_TO_BE_ACTIVATED = "rejectToBeActivated";
    public static final String RECORD_ACTION_REJECT_INACTIVE = "rejectInactive";
    public static final String RECORD_ACTION_REJECT_TO_BE_SUSPENDED = "rejectToBeSuspended";
    public static final String RECORD_ACTION_REJECT_TO_BE_REACTIVATED = "rejectToBeReactivated";
    public static final String RECORD_ACTION_RESET_PASSWORD = "resetPassword";
    public static final String RECORD_ACTION_RESET_PIN = "resetPin";

    /**
     * @author Suhail.Shoukathali TP : 683469
     */
    public static final String RECORD_ACTION_EDIT_REJECTED = "editRejectedRecord";
    public static final String STATUS = "STATUS";

    public static final String TEXTFIELD_SYMBOL = "T";
    public static final String TEXTFIELD_NUMBER_SYMBOL = "N";
    public static final String PASSWORD_SYMBOL = "P";
    public static final String LIVESEARCH_SYMBOL = "LS";
    public static final String SELECTLIVESEARCH_SYMBOL = "SLS";
    public static final String MULTISELECT_SYMBOL = "MS";
    public static final String COMBO_SYMBOL = "DD";
    public static final String CHKBOX_SYMBOL = "CHK";
    public static final String DATE_SYMBOL = "D";
    public static final String TXTAREA_SYMBOL = "TA";
    public static final String LABEL_SYMBOL = "LBL";
    public static final String HIDDEN_SYMBOL = "HD";
    // #BUG 839849
    public static final String FILE_SYMBOL = "F";

    public static final String SUBMIT_ELEMENT = "submit";
    public static final String BUTTON_ELEMENT = "button";
    public static final String DIV_ELEMENT = "div";
    public static final String HIDDEN_ELEMENT = "hidden";
    public static final String LAYOUT_TYPE_SUBMIT = "submit";

    public static final String LAYOUT_TYPE_PASSWORD = "password";
    public static final String LAYOUT_TYPE_MULTISELECT = "multiSelect";
    public static final String LAYOUT_TYPE_SELECTLIVESEARCH = "selectlivesearch";
    // Added by Richie for #TP 660925
    public static final String LAYOUT_TYPE_TAG_INPUT = "tag-input";
    public static final String LAYOUT_TYPE_TOGGLE = "toggle";
    public static final String LAYOUT_TYPE_FORM_GRP = "formgrp";
    public static final String LAYOUT_TYPE_RANGE = "range";
    public static final String LAYOUT_TYPE_AMOUNT = "amount";
    public static final String LAYOUT_TYPE_NUMBER = "number";
    public static final String LAYOUT_TYPE_TEXT = "text";
    public static final String PROPERTY_CODE_TOGGLE = "toggle";
    public static final String PROPERTY_CODE_MIN_RANGE = "minRange";
    public static final String PROPERTY_CODE_MAX_RANGE = "maxRange";
    public static final String PROPERTY_CODE_DUAL_KNOBS = "dualKnobs";
    public static final String PROPERTY_CODE_RANGE_STEP = "rangeStep";
    // End Richie

    public static final Map<String, String> ELEMENT_DATA_TYPE_MAP = new HashMap<>();
    static
    {
	ELEMENT_DATA_TYPE_MAP.put(TEXTFIELD_SYMBOL, LAYOUT_TYPE_TEXTFIELD);
	ELEMENT_DATA_TYPE_MAP.put(TEXTFIELD_NUMBER_SYMBOL, LAYOUT_TYPE_TEXTFIELD);
	ELEMENT_DATA_TYPE_MAP.put(LIVESEARCH_SYMBOL, LAYOUT_TYPE_LIVESEARCH);
	ELEMENT_DATA_TYPE_MAP.put(COMBO_SYMBOL, LAYOUT_TYPE_COMBOBOX);
	ELEMENT_DATA_TYPE_MAP.put(CHKBOX_SYMBOL, LAYOUT_TYPE_CHECKBOX);
	ELEMENT_DATA_TYPE_MAP.put(DATE_SYMBOL, LAYOUT_TYPE_DATEPICKER);
	ELEMENT_DATA_TYPE_MAP.put(TXTAREA_SYMBOL, LAYOUT_TYPE_TEXTAREA);
	ELEMENT_DATA_TYPE_MAP.put(LABEL_SYMBOL, LAYOUT_TYPE_LABEL);
	ELEMENT_DATA_TYPE_MAP.put(PASSWORD_SYMBOL, LAYOUT_TYPE_PASSWORD);
	ELEMENT_DATA_TYPE_MAP.put(MULTISELECT_SYMBOL, LAYOUT_TYPE_MULTISELECT);
	ELEMENT_DATA_TYPE_MAP.put(SELECTLIVESEARCH_SYMBOL, LAYOUT_TYPE_SELECTLIVESEARCH);
	ELEMENT_DATA_TYPE_MAP.put(HIDDEN_SYMBOL, HIDDEN_ELEMENT);
	// #BUG 839849
	ELEMENT_DATA_TYPE_MAP.put(FILE_SYMBOL, LAYOUT_TYPE_FILE);
    }

    public static final int MINUS_ONE = -1;

    public static final String INTERNET_BANKING_RETAIL_APPLICATION = "1";
    public static final String INTERNET_BANKING_CORPORATE_APPLICATION = "2";
    public static final String MOBILE_RETAIL_APPLICATION = "3";
    public static final String MOBILE_CORPORATE_APPLICATION = "4";

    public static final String COMMA_REFERENCE = ",";
    public static final String UNDERSCORE_REFERENCE = "_";

    public static final BigDecimal COMMON_LOV_TYPE_ID = new BigDecimal(1046);

    public static final String STATUS_COLOR_CODE_RED = "R";
    public static final String STATUS_COLOR_CODE_GREEN = "G";
    public static final String STATUS_COLOR_CODE_BLUE = "B";

    public static final String SHA1 = "9";
    public static final String SHA2 = "F";
    public static final String VERIFY_TYPE_PIN = "PIN";
    public static final String VERIFY_TYPE_PASSWORD = "PASSWORD";
    public static final String VERIFY_TYPE_SMS_OTP = "SMSOTP";
    public static final String VERIFY_TYPE_EMAIL_OTP = "EMAILOTP";
    public static final String VERIFY_TYPE_LOGIN = "LOGIN";
    public static final String VERIFY_TYPE_SECURITY_QUESTION = "VerifySecurityQuestion";
    public static final String INVALID_SECURITY_QUESTION = "InvalidSecurityQuestion";

    public static final String CUSTOMER_EVENT = "C";
    public static final String SERVICE_EVENT = "S";
    public static final String FINANCIAL_OPER_TYPE = "Trans";
    public static final String PAYMENT_OPER_TYPE = "Pay";
    public static final String NON_FINANCIAL_OPER_TYPE = "Ntrans";

    public static final String REFERENCE_CODE = "REF_CODE";
    public static final String DATE_UPDATED_COLUMN = "DATE_UPDATED";
    // Added by Richie for Customization services
    public static final String DEFAULT_VO_CO_REFERENCE = "default";
    public static final String DEFAULT_VO_PROPERTY = "default1";
    public static final String STATUS_ACTIVE_ONLY = "A";
    public static final String STATUS_APPROVED_ONLY = "P";
    public static final String STATUS_BOTH_ACTIVE_APPROVED = "B";
    public static final String STATUS_NONE = "N";
    public static final String FROM_MAINTENANCE = "M";
    public static final String FROM_APPROVE = "A";
    public static final String FROM_VIEW = "V";
    public final static String APP_ID_EXP_VAR = "appId";
    public final static String CHNL_ID_EXP_VAR = "chnlId";
    public final static String CIF_EXP_VAR = "cif";
    public final static String CIF_TYPE_EXP_VAR = "cifType";
    public final static String USER_NAME_EXP_VAR = "userName";
    public final static String USER_ID_EXP_VAR = "userId";
    public final static String COMP_CODE_EXP_VAR = "companyCode";
    public final static String CUSTOMIZATION_BY_TYPE = "customizationByType";
    public final static String CUSTOMIZATION_BY_NAME = "customizationByName";
    public final static boolean AUTO_APPROVE_CUSTOMIZATION = true;
    public final static boolean EXCLUDE_COMPANY_COUNTRY = true;
    public final static List<Map<String, Object>> expBoolTestData = new ArrayList<>();
    static
    {
	Map<String, Object> dataForExpr = new HashMap<>();
	dataForExpr.put(APP_ID_EXP_VAR, null);
	dataForExpr.put(CHNL_ID_EXP_VAR, null);
	dataForExpr.put(CIF_EXP_VAR, null);
	dataForExpr.put(CIF_TYPE_EXP_VAR, null);
	dataForExpr.put(USER_NAME_EXP_VAR, null);
	dataForExpr.put(USER_ID_EXP_VAR, null);
	dataForExpr.put(COMP_CODE_EXP_VAR, null);
	expBoolTestData.add(dataForExpr);
    }
    // End Richie
    /**
     * @author gilbertandary
     */
    public static final String ONE = "1";
    public static final String ZERO = "0";
    public static final String STRING = "ST";
    public static final String INT = "IN";
    public static final String DOUBLE = "DB";
    public static final String DATE = "DT";

    public static final String MODE_IS_SAVE = "SAVE";
    public static final String MODE_IS_SUBMIT = "SUBMIT";
    public static final String MODE_IS_EDIT = "EDIT";
    public static final String MOCK_DATA_FILE_LOCATION = "C:/omni/workspace/omni_services/Core Mock Data.txt";

    public static final String LOGIN_SERVICE_URL = "authenticateUser_service";// login_service
								   // name
    public static final String LOGOUT_SERVICE_URL = "logOut_service";// login_service
								     // name
    public static final String PRE_LOGIN = "PRE_LOGIN";// login_service name
    public static final String APPLICATION_JSON = "application/json";
    public static final String PRE_LOGIN_SERVICE = "preLogin";
    public static final String INIT_TWITTER_GET = "initTwitterListnerGET";
    public static final String TERMS_AND_CONDITION_SERVICE = "returnTermsAndCondition_service";
    public static final String OAUTH2_SERVICE = "handleTokenRequest";
    public static final String GRANT_CLIENT_CREDENTIALS = "client_credentials";
    public static final String GRANT_RESOURCE_OWNER = "password";
    public static final String OAUTH2_APPLICATION_NAME = "PathsSolutions";
    public static final String OAUTH2_WEB_URI = "http://www.pathsSolutions.com";
    public static final String OAUTH2_CLIENT_ID = "PathSolutions";
    public static final String OAUTH2_CLIENT_SECRET = "PathSolutions";
    public static final String RETURN_VERSION_DETAILS_SERVICE = "returnVersionDetails_service";
    public static final String GENERATE_OTP_SERVICE = "generateOTP_service";			

    public static final ArrayList<String> SERVICES_TO_EXCLUDE_FROM_INTERCEPTOR = new ArrayList<String>(
	    Arrays.asList(INIT_TWITTER_GET, PRE_LOGIN_SERVICE, OAUTH2_SERVICE,RETURN_VERSION_DETAILS_SERVICE));

    /* nabil feghali - OMNI common security  */
    
    public static final String RETURN_TRANSLATION_KEYS_SERVICE = "returnTranslationKeys_service";
    public static final String FETCH_PARAMETER_VALUES_SERVICE = "returnParameterValues_service";
    public static final String RETURN_ELM_CUSTOMIZATION_SERVICE = "returnElmCustomization_service";
    public static final String RETURN_ID_TYPES_SERVICE = "returnIdTypes_service";
    public static final String FORGOT_CREDENTIALS_SERVICE = "forgotCredentials_service";
    public static final String RETURN_SECURITY_QUESTION_LIST_SERVICE = "returnSecurityQuestionList";
    public static final String RETURN_ATM_BRANCH_DETAILS_SERVICE = "returnATMBranchDetails_service";
    public static final String RETURN_LOV_VALUES_SERVICE = "returnLovValues_service";
    public static final String RETURN_PRODUCT_CLASSES_SERVICE = "returnProductClasses_service";
    public static final String RETURN_CURRENCIES_LIST_SERVICE = "returnCurrenciesList_service";
    public static final String CREATE_IISISLAMIC_CALCULATOR_REQUEST_SERVICE = "createIisIslamicCalculatorRequest_service";
    public static final String ONLINE_REGISTRATION_SERVICE = "onlineRegistration_service";
    public static final String CHANGE_PIN_SERVICE = "changePin_service";				    
    public static final String CHANGE_PASSWORD_SERVICE = "changePassword_service";
    public static final String AUTHENTICATE_USER_SERVICE = "authenticateUser_service";
    public static final String LOGIN_USER_SERVICE = "loginUser_service";
    public static final String RETURN_AUTHENTICATIONS_REQUIRED_SERVICE ="returnAuthenticationsRequired_service";
    public static final String PRAYER_TIME_SERVICE = "returnPrayTime_service";
    public static final String FINANCIAL_CALCULATOR_SERVICE = "submitFinancialCalculator_service";
    public static final String PUSH_NOTIFICATION_SERVICE = "pushNotification_service";
    public static final String CREATE_NOTIFICATION_TOKEN = "createNotificationDeviceToken_service"; 
    public static final String VERIFY_PASWORD_SERVICE = "verifyPassword_service";
    public static final String VERIFY_PIN_PASWORD_SERVICE = "verifyPIN_service";
    public static final String VERIFY_SECURITY_QUESTION_SERVICE = "verifySecurityQuestion_service";
    public static final String VERIFY_OTP_SERVICE = "verifyOtp_service";
    public static final String SUBMIT_ACTION_SERVICE = "submitAction_service";
    public static final String GENERATE_REPORT_SERVICE = "generateReport_service";
    public static final String RETURN_COUNTRIES_SERVICE = "returnCountriesList_service";
    public static final String RETURN_COUNTRIES_REGION_SERVICE = "returnCountriesRegionsList_service";
    public static final String RETURN_CITIES_SERVICE = "returnCitiesList_service";
    public static final String RETURN_LANGUAGE_SERVICE = "returnLanguagesList_service";
    public static final String RETURN_NATIONALITIES_SERVICE = "returnNationalities_service";
    public static final String RETURN_LEGAL_STATUSES_SERVICE = "returnLegalStatuses_service";
    public static final String RETURN_POSTAL_CODE_LIST_SERVICE = "returnPostalCodeList_service";
    public static final String RETURN_POSITIONS_LIST_SERVICE = "returnPositionsList_service";
    public static final String SUBMIT_ACTION_OUTSIDE_SERVICE = "submitActionOutside_service";
    public static final String RETURN_ACCOUNT_TYPES_SERVICE = "returnAccountTypes_service";
    

    public static final ArrayList<String> SERVICES_TO_EXCLUDE_FROM_LOGINTOKEN = new ArrayList<String>(
	    Arrays.asList(PRE_LOGIN_SERVICE, 
		    AUTHENTICATE_USER_SERVICE, 
		    LOGIN_USER_SERVICE,
		    RETURN_TRANSLATION_KEYS_SERVICE, 
		    FETCH_PARAMETER_VALUES_SERVICE, 
		    RETURN_ELM_CUSTOMIZATION_SERVICE,
		    RETURN_ID_TYPES_SERVICE, 
		    FORGOT_CREDENTIALS_SERVICE, 
		    RETURN_SECURITY_QUESTION_LIST_SERVICE,
		    RETURN_ATM_BRANCH_DETAILS_SERVICE, 
		    RETURN_LOV_VALUES_SERVICE, 
		    RETURN_PRODUCT_CLASSES_SERVICE,
		    RETURN_CURRENCIES_LIST_SERVICE, 
		    CREATE_IISISLAMIC_CALCULATOR_REQUEST_SERVICE,
		    ONLINE_REGISTRATION_SERVICE, 
		    CHANGE_PIN_SERVICE, 
		    CHANGE_PASSWORD_SERVICE,
		    RETURN_AUTHENTICATIONS_REQUIRED_SERVICE,
		    PRAYER_TIME_SERVICE,
		    FINANCIAL_CALCULATOR_SERVICE,
		    PUSH_NOTIFICATION_SERVICE,
		    CREATE_NOTIFICATION_TOKEN,
		    GENERATE_REPORT_SERVICE,
		    RETURN_COUNTRIES_SERVICE,
		    RETURN_COUNTRIES_REGION_SERVICE,
		    RETURN_CITIES_SERVICE,
		    RETURN_VERSION_DETAILS_SERVICE,
		    RETURN_LANGUAGE_SERVICE,
		    RETURN_NATIONALITIES_SERVICE,
		    RETURN_LEGAL_STATUSES_SERVICE,
		    RETURN_POSTAL_CODE_LIST_SERVICE,
		    RETURN_POSITIONS_LIST_SERVICE,
		    SUBMIT_ACTION_OUTSIDE_SERVICE,
		    RETURN_ACCOUNT_TYPES_SERVICE,
		    //fix issue 997863 - authMatrix called before login
		    VERIFY_PASWORD_SERVICE,
		    VERIFY_OTP_SERVICE,
		    VERIFY_SECURITY_QUESTION_SERVICE,
		    GENERATE_OTP_SERVICE));
    
    public static final ArrayList<String> SERVICES_TO_INCLUDE_IN_AUTHTOKEN = new ArrayList<String>(Arrays.asList(AUTHENTICATE_USER_SERVICE,LOGIN_USER_SERVICE,VERIFY_PASWORD_SERVICE,VERIFY_OTP_SERVICE,VERIFY_SECURITY_QUESTION_SERVICE,SUBMIT_ACTION_SERVICE,VERIFY_PIN_PASWORD_SERVICE));

    /* nabil feghali - OMNI common security - end */
    
    public static final BigDecimal CHNL_ID_0 = new BigDecimal(0);
    public static final BigDecimal APP_ID_0 = new BigDecimal(0);

    public static final BigDecimal PRIMARY_COLOR_PERCENTAGE = new BigDecimal(2);
    public static final BigDecimal SECONDARY_COLOR_PERCENTAGE = new BigDecimal(100);
    public static final String ADMIN_USER = "A";
    public static final BigDecimal EMPTY_BIGDECIMAL_VALUE = BigDecimal.valueOf(-9999999);

    public static final String COUPOUN_PERIOD_DAY = "D";
    public static final String COUPOUN_PERIOD_HOUR = "H";
    public static final String COUPOUN_PERIOD_MONTH = "M";
    public static final String COUPOUN_PERIOD_MINUTES = "N";
    public static final String COUPOUN_PERIOD_SECONDS = "S";
    public static final String COUPOUN_PERIOD_WEEK = "W";
    public static final String COUPOUN_PERIOD_YEAR = "Y";

    public static final String STATUS_EXPIRED 	= "EX";
    public static final String STATUS_USED 		= "U";
    public static final String STATUS_REDEEMED 	= "RDM";

    public static final String DATE_FORMAT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_yyyy_MM_dd = "yyyy-MM-dd";
    public static final String DATE_FORMAT_yyyy_MM_dd_HH_mm_ss_ORACLE = "yyyy-mm-dd\"T\"hh24:mi:ss";
    public static final String TIME_FORMAT_HH_mm_ss = "HH:mm:ss";
    public static final String PENDING_FOR_APPROVAL = "PENDING";
    public static final String WAITING_FOR_PMT = "WAITING";
    public static final String STATUS_H = "H";
    public static final String SYS = "SYS";
    public static final String PMS = "PMS";
    public static final String PROG_REF_T0011071 = "T0011071";
    public static final String DOCUMENT = "DOCUMENT";

    public static final String CORE_EXPECTED_DATE_FORMAT = "yyyy-MM-dd";

    public static final String ACCOUNT_FORMAT_IS_IBAN = "1";
    public static final String ACCOUNT_FORMAT_IS_ADDITIONAL_REFERENCE = "2";
    public static final String ACCOUNT_FORMAT_IS_iMAL = "3";

    public static final String CIF_BRANCH = "-1";
    public static final String ACCOUNT_BRANCH = "-2";
    
    
    public static final String SOCIAL_INTERNAL_TRANSFER_OPERID = "1528";
    public static final String SOCIAL_LOCAL_TRANSFER_OPERID = "1529";
    public static final String SOCIAL_SWIFT_TRANSFER_OPERID = "1530";
    public static final BigDecimal LOGIN_OPERID = BigDecimal.valueOf(1544);
    public static final String NO_RECORDS_FOUND_ERROR = "-433";
    
    
    public static final String ONLINE_REGISTRATION_CHECK_USER_NAME = "1";
    public static final String ONLINE_REGISTRATION_CHECK_PASSWORD = "2";
    public static final String ONLINE_REGISTRATION_CHECK_PIN = "3";
    public static final String ONLINE_REGISTRATION_CHECK_SECURITY_QUESTION = "4";
    public static final String ONLINE_REGISTRATION_CHECK_PASS_PHRASE = "5";
    public static final String ONLINE_REGISTRATION_RETRIEVE_CUSTOMER_INFORMATION = "6";
    public static final String FORGOT_PASSWORD_CHECK_USER_NAME = "7";
    public static final String ONLINE_REGISTRATION_CHECK_CIF = "8";
    public static final String FORGOT_USERNAME_RETRIEVE_CUSTOMER_INFORMATION = "9";
    public static final String CHECK_CARD_NUMBER = "10";
    public static final String CHECK_ID_TYPE = "11";

    
    public static final String OTR = "OTR";
    public static final String Matured = "Matured";
    
    public static final String MULTIUSER_YN_N = "N";
    
    public static final String FORGOT_USERNAME_DELIVERY_BY_SMS = "S";
    public static final String FORGOT_USERNAME_DELIVERY_BY_EMAIL = "E";
    
    public static final String MAX_SQL_CHARCHTERS = "4000" /*"32767"*/;

    /**
     * END gilbertAndary
     */

    /**
     * @author KhaledAlTimany
     */
    public static final String OMNI_ALL_CHNL = "OMCH0";
    public static final String OMNI_INTERNET_BANKING = "OIBK";
    public static final String OMNI_MOBILE_BANKING = "OMBK";
    public static final String OMNI_FACEBOOK_BANKING = "OFBK";
    public static final String OMNI_TWITTER_BANKING = "OTBK";

    public static final int ALL_CHNL = 0;
    public static final int INTERNET_BANKING = 1;
    public static final int MOBILE_BANKING = 2;
    public static final int FACEBOOK_BANKING = 3;
    public static final int TWITTER_BANKING = 4;

    public static final int GLOBAL_TRANSLATION_OPER_ID = 2222;
    public static final String MAIN_MENU_M = "M";

    // 824425
    public static final String INBOX_LOV_CODE = "MSG";
    public static final String CIF_OPENING_CODE = "CIF";
    // #BUG 839849
    public static final String PARAMETER_LOV_CODE = "PRM";
    /**
     * @author Suhail.Shoukathali Business types constants
     */
    public static final String BUSINESS_TYPE_AMOUNT = "amount";
    public static final String BUSINESS_TYPE_CY_COMBINATION = "cyCombination";
    public static final String BUSINESS_TYPE_OWN_ACCOUNT = "ownAccount";

    public static final String SERVICE_TYPE_LOV = "1536";

    // For End User Screen
    public static final String APPLICATION_TYPE_AGENCY = "4";
    public static final String APPLICATION_TYPE_CORPORATE = "2";

    // auth type
    public static final String SMS_OTP = "OTP";
    public static final String EMAIL_OTP = "OTP";
    public static final String TRANSACTION_PASSWORD = "TransactionPass";
    public static final String CAPTCHA = "Captcha";
    public static final String SECURITY_QUESTION = "SecurityQuest";

    // oper constants
    public static final BigDecimal ACCOUNT_OPENING_OPER = new BigDecimal(27);
    public static final BigDecimal TERM_DEPOSIT_ACCOUNT_OPENING_REQ_OPER = new BigDecimal(1563);
    public static final BigDecimal INTERNAL_TRANSFER_MY_OWN_ACCOUNTS_OPER = new BigDecimal(33);
    public static final BigDecimal INTERNAL_TRANSFER_WITHIN_BANK_ACCOUNTS_OPER = new BigDecimal(39);
    public static final BigDecimal EXTERNAL_TRANSFER_LOCAL_OPER = new BigDecimal(24);
    public static final BigDecimal EXTERNAL_TRANSFER_SWIFT_OPER = new BigDecimal(24);
    public static final BigDecimal BANKERS_CHEQUE_TRANSFER_TO_OTHERS_REQUEST_OPER = new BigDecimal(116);
    public static final BigDecimal BANKERS_CHEQUE_TRANSFER_TO_FAVORITE_OPER = new BigDecimal(115);
    public static final BigDecimal DEMAND_DRAFT_TO_OTHERS_REQUEST_OPER = new BigDecimal(645);
    public static final BigDecimal DEMAND_DRAFT_TO_FAVORITE_OPER = new BigDecimal(644);
    public static final BigDecimal BILL_PAYMENT_TO_OTHERS_OPER = new BigDecimal(201);
    public static final BigDecimal BILL_PAYMENT_TO_FAVORITE_OPER = new BigDecimal(202);
    public static final BigDecimal UBPS_PAY_BILL_REQUEST_OPER = new BigDecimal(57);
    public static final BigDecimal IBFT_TRANSFER_TO_FAVORITE_REQUEST_OPER = new BigDecimal(54);
    public static final BigDecimal IBFT_TRANSFER_TO_OTHERS_REQUEST_OPER = new BigDecimal(55);
    public static final BigDecimal CHEQUEBOOK_REQUEST_OPER = new BigDecimal(25);
    public static final BigDecimal CARD_REQUEST_OPER = new BigDecimal(52);
    public static final BigDecimal INVESTMENT_DIVESTMENT_REQUEST_OPER = new BigDecimal(41);
    public static final BigDecimal AGENCY_COLLECTION_TO_OTHERS_OPER = new BigDecimal(75);
    public static final BigDecimal AGENCY_COLLECTION_TO_FAVORITE_OPER = new BigDecimal(76);
    public static final BigDecimal EDIT_ACCOUNT_NAME_PERSONALIZE_REQ_OPER = new BigDecimal(537);
    public static final BigDecimal REPORT_LOST_DOCUMENT_REQ_OPER = new BigDecimal(50);
    public static final BigDecimal EDIT_CARD_NAME_PERSONALIZE_REQ_OPER = new BigDecimal(1327);
    public static final BigDecimal ZAKAT_PAYMENT_REQ_OPER = new BigDecimal(1105);
    public static final BigDecimal RENEWAL_REQUEST_OPER = new BigDecimal(1462);
    public static final BigDecimal REGISTRATION_AND_CHANNEL_ACTIVATION = new BigDecimal(1502);
    public static final BigDecimal CHANGE_PASSWORD_OPER = new BigDecimal(78);
    public static final BigDecimal CHANGE_PIN_OPER = new BigDecimal(79);
    public static final BigDecimal END_USER_REGISTRATION_OPER = new BigDecimal(1522);
    public static final BigDecimal EDIT_END_USERS_REGISTRATION = new BigDecimal(1523);
    public static final BigDecimal DELETE_END_USERS_REGISTRATION = new BigDecimal(1250);
    public static final BigDecimal END_USERS_MANAGEMENT = new BigDecimal(1323);
    public static final BigDecimal SUSPEND_END_USERS_REGISTRATION = new BigDecimal(1560);
    public static final BigDecimal ACTIVATE_END_USERS_REGISTRATION = new BigDecimal(1561);
    public static final BigDecimal CIF_OPENING_OPER = new BigDecimal(1481);
    public static final BigDecimal SECURITY_MARKET_PRICES = new BigDecimal(56);
    public static final BigDecimal CHANGE_SECURITY_ANSWER_OPER = new BigDecimal(1321);
    public static final BigDecimal CHANGE_TRANSACTION_LIMIT = new BigDecimal(783);
    public static final Integer  ERROR_CODE_INCORRECT_PIN = 23;
    public static final BigDecimal INTERNAL_TRANSFER_REQ_OPER = new BigDecimal(1507);
    public static final BigDecimal EXTERNAL_TRANSFER_REQ_OPER = new BigDecimal(1505);
    public static final BigDecimal CREDIT_CARD_REQ_OPER = new BigDecimal(1564);
    public static final BigDecimal MY_PROFILE_UPDATE_OPER = new BigDecimal(1258);
    
    public static final BigDecimal ON_BOARDING_OPER = new BigDecimal(182);
    public static final BigDecimal CIF_ON_BOARDING_OPER = new BigDecimal(1577);
    public static final BigDecimal CIF_ON_BOARDING_OPER_PRELOGIN = new BigDecimal(182);
    public static final String ON_BOARDING_PROFILE_ID_PARAM = "onBoardingProfileId";
    public static final String USER_CREATION_MODE_ON_BOARDING= "ONBOARDING";
    
    public static final String LEGAL_STATUS_PARAM = "LegalStatus";
    public static final String RANKING_PARAM = "Ranking";
    
    // @author Muhammad.Asif
    public static final BigDecimal CARD_MANAGEMENT_RENEW_OPER = new BigDecimal(1332);
    public static final BigDecimal CARD_MANAGEMENT_BLOCK_OPER = new BigDecimal(1333);
    public static final BigDecimal IPO_REQUEST_OPER = new BigDecimal(1463);

    // @author Rakesh.Kumar
    public static final BigDecimal LC_REQ_OPER = new BigDecimal(63);
    public static final BigDecimal LG_REQ_OPER = new BigDecimal(64);
    public static final BigDecimal OB_REQ_OPER = new BigDecimal(69);

    // approval oper
    public static final BigDecimal APPROVE_TRANSFER_MY_ACCOUNTS = new BigDecimal(675);
    public static final BigDecimal APPROVE_ACCOUNT_OPENING = new BigDecimal(533);
    public static final BigDecimal APPROVE_BANKERS_CHEQUE_REQ = new BigDecimal(674);
    public static final BigDecimal APPROVE_DEMAND_DRAFT_REQ = new BigDecimal(666);
    public static final BigDecimal APPROVE_ACCOUNT_NAME_PERSONALIZATION_REQ = new BigDecimal(538);
    public static final BigDecimal APPROVE_BILL_PAYMENTS_PAY_TO_FAVORITE_REQ = new BigDecimal(682);
    public static final BigDecimal APPROVE_BILL_PAYMENTS_PAY_TO_OTHERS_REQ = new BigDecimal(683);
    public static final BigDecimal APPROVE_SWEEPING_AND_POOLING = new BigDecimal(608);
    public static final BigDecimal SWEEPING_AND_POOLING_OPER = new BigDecimal(1264);
    public static final BigDecimal APPROVE_LOST_DOCUMENT_REQ = new BigDecimal(1331);
    public static final BigDecimal APPROVE_CARD_REQUEST_OPER = new BigDecimal(575);
    public static final BigDecimal APPROVE_CHEQUEBOOK_REQUEST_OPER = new BigDecimal(577);
    public static final BigDecimal APPROVE_INTERNAL_NONBENEFICIARY_TRANSFER_REQ = new BigDecimal(677);
    public static final BigDecimal APPROVE_CARD_NAME_PERSONALIZATION_REQ = new BigDecimal(1326);
    public static final BigDecimal APPROVE_ZAKAT_PAYMENT_OPER = new BigDecimal(1096);
    public static final BigDecimal APPROVE_RENEWAL_REQUEST_OPER = new BigDecimal(1482);
    public static final BigDecimal APPROVE_IPO_REQUEST_OPER = new BigDecimal(1549);
    public static final BigDecimal ADD_NEW_INTERNAL_BENEF_OPER = new BigDecimal(869);

    // Rakesh
    public static final BigDecimal APPROVE_LC_REQ_OPER = new BigDecimal(588);
    public static final BigDecimal APPROVE_LG_REQ_OPER = new BigDecimal(591);
    public static final BigDecimal APPROVE_OB_REQ_OPER = new BigDecimal(594);
    public static final BigDecimal CALCULATOR_RESULT_OPER = new BigDecimal(106);
    
    // Hisham
    public static final BigDecimal SECURITY_QUESTION_TABLE_CODE = new BigDecimal(508);

    /**
     * Added by @author anas.cholakkal
     */

    public static final BigDecimal APPROVE_FUNDS_SUBSCRIPTION = new BigDecimal(671);
    public static final BigDecimal FUNDS_SUBSCRIPTION_OPER = new BigDecimal(256);
    public static final BigDecimal APPROVE_FUNDS_REDEMPTION = new BigDecimal(669);
    public static final BigDecimal FUNDS_REDEMPTION_OPER = new BigDecimal(255);

    /**
     * @author Neena.David TP - 716242
     */
    public static final BigDecimal ONLINE_REGISTRATION = new BigDecimal(863);
    public static final BigDecimal FORGOT_PASSWORD = new BigDecimal(1465);
    public static final BigDecimal FORGOT_USERNAME = new BigDecimal(1466);
    public static final BigDecimal FINGER_PRINT_AUTHENTICATE = new BigDecimal(1467);
    public static final BigDecimal SECURITY_STATEMENT = new BigDecimal(1468);
    public static final BigDecimal TERMS_CONDITIONS_REQ = new BigDecimal(1469);
    public static final BigDecimal PRODUCT_SERVICES = new BigDecimal(1470);
    public static final BigDecimal BANK_CALL = new BigDecimal(1471);
    public static final BigDecimal REMEMBER_ME = new BigDecimal(1472);

    public static final BigDecimal RETAIL_APPID = new BigDecimal(1);
    public static final BigDecimal CORPORATE_APPID = new BigDecimal(2);
    public static final BigDecimal SOCIAL_APPID = new BigDecimal(3);
    public static final BigDecimal AGENT_APPID = new BigDecimal(4);

    // Hisham Omar BUG#821619 - second level approval is not done on cif opening
    // request
    public static final BigDecimal CIF_OPENING_REQUEST_OPER = new BigDecimal(1481);
    public static final BigDecimal APPROVE_CIF_OPENING_REQUEST_OPER = new BigDecimal(1519);

    /**
     * @author Vishnu.Ponnan
     */
    public static final String DATE_PARSE_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";
    public static final String DATE_FORMAT_DD_MMM_YYYY = "dd-MMM-yyyy";
    public static final String LIMIT_PACKAGE_TYPE = "L";
    public static final String SUB_LIMIT_PACKAGE_TYPE = "S";
    public static final String LIMIT_PACKAGE_OPT = "OADM10211";
    public static final String SUB_LIMIT_PACKAGE_OPT = "OADM10212";

    /**
     * @author Suhail.Shoukathali for Adminparameters dynamic query preparation
     */

    public static final String SYS_PARAM_LOV_TRANS_TABLE = "SYS_PARAM_LOV_TRANS";
    public static final String SYS_PARAM_LOV_TRANS_VALUE_CODE = "VALUE_CODE";

    /**
     * @author Suhail.Shoukathali For corporate approval flow Opers which are
     *         treated as transfers
     */
    public static final BigDecimal APPROVAL_LIST_REP_OPER = new BigDecimal(606);
    public static final BigDecimal SAVED_DRAFT_LIST_REP_OPER = new BigDecimal(1325);
    // opers considered for approval matrix
    public static List<BigDecimal> trasnferOpers = new ArrayList<>();
    static
    {
	trasnferOpers.add(INTERNAL_TRANSFER_MY_OWN_ACCOUNTS_OPER);
	trasnferOpers.add(INTERNAL_TRANSFER_WITHIN_BANK_ACCOUNTS_OPER);
	trasnferOpers.add(EXTERNAL_TRANSFER_LOCAL_OPER);
	trasnferOpers.add(EXTERNAL_TRANSFER_SWIFT_OPER);
	trasnferOpers.add(BILL_PAYMENT_TO_FAVORITE_OPER);
	trasnferOpers.add(BILL_PAYMENT_TO_OTHERS_OPER);
	trasnferOpers.add(AGENCY_COLLECTION_TO_OTHERS_OPER);
	trasnferOpers.add(AGENCY_COLLECTION_TO_FAVORITE_OPER);
	trasnferOpers.add(BANKERS_CHEQUE_TRANSFER_TO_OTHERS_REQUEST_OPER);
	trasnferOpers.add(BANKERS_CHEQUE_TRANSFER_TO_FAVORITE_OPER);
	trasnferOpers.add(DEMAND_DRAFT_TO_OTHERS_REQUEST_OPER);
	trasnferOpers.add(DEMAND_DRAFT_TO_FAVORITE_OPER);
	trasnferOpers.add(ZAKAT_PAYMENT_REQ_OPER);
	// TODO add additional opers
    }

    /**
     * @author Suhail.Shoukathali For corporate approval flow
     */
    public static HashMap<BigDecimal, List<Object>> operDetailsMap = new HashMap<>();
    static
    {
	// key = operId
	// value = List | 0th - approval oper, 1st - table name, 2nd - method
	// name return approval details, 3rd - method name returning screen
	// data. 4th - method name for submitting core request
	operDetailsMap.put(INTERNAL_TRANSFER_MY_OWN_ACCOUNTS_OPER,
		Arrays.asList(new Object[] { APPROVE_TRANSFER_MY_ACCOUNTS, "OC_TRANSFERS",
			"returnInternalTransferApprovalDetails", "returnInternalTransferScreenDetails",
			"submitInternalTransferCoreRequest" }));
	operDetailsMap.put(INTERNAL_TRANSFER_WITHIN_BANK_ACCOUNTS_OPER,
		Arrays.asList(new Object[] { APPROVE_INTERNAL_NONBENEFICIARY_TRANSFER_REQ, "OC_TRANSFERS",
			"returnInternalTransferApprovalDetails", "returnInternalTransferScreenDetails",
			"submitInternalTransferCoreRequest" }));
	operDetailsMap.put(EDIT_ACCOUNT_NAME_PERSONALIZE_REQ_OPER,
		Arrays.asList(new Object[] { APPROVE_ACCOUNT_NAME_PERSONALIZATION_REQ, "OC_AC_NAME_PERSONALIZATION",
			"returnAccountNamePersonalApprovalDetails", "returnAccountNamePersonalScreenDetails",
			"submitAccountNamePersonalCoreRequest" }));
	operDetailsMap.put(FUNDS_SUBSCRIPTION_OPER,
		Arrays.asList(new Object[] { APPROVE_FUNDS_SUBSCRIPTION, "OC_FUNDS",
			"returnFundsSubscriptionAndRedemptionApprovalDetails",
			"returnFundsSubscriptionAndRedemptionScreenDetails",
			"submitFundsSubscriptionAndRedemptionCoreRequest" }));
	operDetailsMap.put(FUNDS_REDEMPTION_OPER,
		Arrays.asList(new Object[] { APPROVE_FUNDS_REDEMPTION, "OC_FUNDS",
			"returnFundsSubscriptionAndRedemptionApprovalDetails",
			"returnFundsSubscriptionAndRedemptionScreenDetails",
			"submitFundsSubscriptionAndRedemptionCoreRequest" }));
	operDetailsMap.put(BANKERS_CHEQUE_TRANSFER_TO_OTHERS_REQUEST_OPER,
		Arrays.asList(new Object[] { APPROVE_BANKERS_CHEQUE_REQ, "OC_TRANSFERS",
			"returnBankersChequeTransferApprovalDetails", "returnBankersChequeTransferScreenDetails",
			"submitBankersChequeTransferCoreRequest" }));
	operDetailsMap.put(BANKERS_CHEQUE_TRANSFER_TO_FAVORITE_OPER,
		Arrays.asList(new Object[] { APPROVE_BANKERS_CHEQUE_REQ, "OC_TRANSFERS",
			"returnBankersChequeTransferApprovalDetails", "returnBankersChequeTransferScreenDetails",
			"submitBankersChequeTransferCoreRequest" }));
	operDetailsMap.put(DEMAND_DRAFT_TO_OTHERS_REQUEST_OPER,
		Arrays.asList(new Object[] { APPROVE_DEMAND_DRAFT_REQ, "OC_TRANSFERS",
			"returnBankersChequeTransferApprovalDetails", "returnBankersChequeTransferScreenDetails",
			"submitBankersChequeTransferCoreRequest" }));
	operDetailsMap.put(DEMAND_DRAFT_TO_FAVORITE_OPER,
		Arrays.asList(new Object[] { APPROVE_DEMAND_DRAFT_REQ, "OC_TRANSFERS",
			"returnBankersChequeTransferApprovalDetails", "returnBankersChequeTransferScreenDetails",
			"submitBankersChequeTransferCoreRequest" }));
	operDetailsMap.put(BILL_PAYMENT_TO_OTHERS_OPER,
		Arrays.asList(new Object[] { APPROVE_BILL_PAYMENTS_PAY_TO_OTHERS_REQ, "OC_TRANSFERS",
			"returnBankersChequeTransferApprovalDetails", "returnBankersChequeTransferScreenDetails",
			"submitBankersChequeTransferCoreRequest" }));
	operDetailsMap.put(BILL_PAYMENT_TO_FAVORITE_OPER,
		Arrays.asList(new Object[] { APPROVE_BILL_PAYMENTS_PAY_TO_FAVORITE_REQ, "OC_TRANSFERS",
			"returnBankersChequeTransferApprovalDetails", "returnBankersChequeTransferScreenDetails",
			"submitBankersChequeTransferCoreRequest" }));
	operDetailsMap.put(ACCOUNT_OPENING_OPER,
		Arrays.asList(new Object[] { APPROVE_ACCOUNT_OPENING, "OC_ACCOUNT_OPENING",
			"returnAccountOpeningApprovalDetails", "returnAccountOpeningScreenDetails",
			"submitAccountOpeningCoreRequest" }));
	operDetailsMap.put(SWEEPING_AND_POOLING_OPER,
		Arrays.asList(new Object[] { APPROVE_SWEEPING_AND_POOLING, "OC_SWEEPING",
			"returnSweepingAndPoolingApprovalDetails", "returnSweepingAndPoolingScreenDetails",
			"submitSweepingAndPoolingCoreRequest" }));
	operDetailsMap.put(REPORT_LOST_DOCUMENT_REQ_OPER,
		Arrays.asList(new Object[] { APPROVE_LOST_DOCUMENT_REQ, "OC_LOST_DOCUMENT",
			"returnLostDocumentApprovalDetails", "returnLostDocumentScreenDetails",
			"submitLostDocumentCoreRequest" }));
	operDetailsMap.put(CARD_REQUEST_OPER,
		Arrays.asList(
			new Object[] { APPROVE_CARD_REQUEST_OPER, "OC_CARD_REQUEST", "returnCardRequestApprovalDetails",
				"returnCardRequestScreenDetails", "submitCardRequestCoreRequest" }));
	operDetailsMap.put(CHEQUEBOOK_REQUEST_OPER,
		Arrays.asList(new Object[] { APPROVE_CHEQUEBOOK_REQUEST_OPER, "OC_CHEQUE_BOOK_REQUEST",
			"returnChequeBookApprovalDetails", "returnChequeBookScreenDetails",
			"submitChequeBookCoreRequest" }));
	operDetailsMap.put(EDIT_CARD_NAME_PERSONALIZE_REQ_OPER,
		Arrays.asList(new Object[] { APPROVE_CARD_NAME_PERSONALIZATION_REQ, "OC_CARD_NAME_PERSONALIZATION",
			"returnCardNamePersonalApprovalDetails", "returnCardNamePersonalScreenDetails",
			"submitCardNamePersonalCoreRequest" }));
	operDetailsMap.put(ZAKAT_PAYMENT_REQ_OPER,
		Arrays.asList(
			new Object[] { APPROVE_ZAKAT_PAYMENT_OPER, "OC_TRANSFERS", "returnZakatPaymentApprovalDetails",
				"returnZakatPaymentScreenDetails", "submitZakatPayment	CoreRequest" }));
	operDetailsMap.put(RENEWAL_REQUEST_OPER,
		Arrays.asList(new Object[] { APPROVE_RENEWAL_REQUEST_OPER, "OC_SECURITY_RENEWAL",
			"returnRenewalRequestApprovalDetails", "returnRenewalRequestScreenDetails",
			"submitRenewalRequestCoreRequest" }));
	/** @author Muhammad.Asif Start */
	operDetailsMap.put(CARD_MANAGEMENT_BLOCK_OPER,
		Arrays.asList(new Object[] { CARD_MANAGEMENT_BLOCK_OPER, "OC_CARD_MANAGEMENT",
			"returnBlockReissueApprovalDetails", "returnBlockReissueScreenDetails",
			"submitBlockReissueCoreRequest" }));
	operDetailsMap.put(CARD_MANAGEMENT_RENEW_OPER,
		Arrays.asList(new Object[] { CARD_MANAGEMENT_RENEW_OPER, "OC_CARD_MANAGEMENT",
			"returnRenewApprovalDetails", "returnRenewScreenDetails", "submitRenewCoreRequest" }));
	operDetailsMap.put(IPO_REQUEST_OPER, Arrays.asList(new Object[] { APPROVE_IPO_REQUEST_OPER, "OC_IPO_REQUEST",
		"returnIPORequestApprovalDetails", "returnIPORequestScreenDetails", "submitIPORequestCoreRequest" }));
	/** @author Muhammad.Asif END */
	operDetailsMap.put(EDIT_CARD_NAME_PERSONALIZE_REQ_OPER,
		Arrays.asList(new Object[] { APPROVE_CARD_NAME_PERSONALIZATION_REQ, "OC_CARD_NAME_PERSONALIZATION",
			"returnCardNamePersonalApprovalDetails", "returnCardNamePersonalScreenDetails",
			"submitCardNamePersonalCoreRequest" }));
	operDetailsMap.put(ZAKAT_PAYMENT_REQ_OPER,
		Arrays.asList(
			new Object[] { APPROVE_ZAKAT_PAYMENT_OPER, "OC_TRANSFERS", "returnZakatPaymentApprovalDetails",
				"returnZakatPaymentScreenDetails", "submitZakatPaymentCoreRequest" }));
	// Rakesh
	operDetailsMap.put(LC_REQ_OPER, Arrays.asList(new Object[] { APPROVE_LC_REQ_OPER, "OC_LC_REQUEST",
		"returnLcRequestApprovalDetails", "returnLcRequestScreenDetails", "submitLcRequestCoreRequest" }));
	operDetailsMap.put(LG_REQ_OPER, Arrays.asList(new Object[] { APPROVE_LG_REQ_OPER, "OC_LG_REQUEST",
		"returnLgRequestApprovalDetails", "returnLgRequestScreenDetails", "submitLgRequestCoreRequest" }));
	operDetailsMap.put(OB_REQ_OPER, Arrays.asList(new Object[] { APPROVE_OB_REQ_OPER, "OC_OB_REQUEST",
		"returnObRequestApprovalDetails", "returnObRequestScreenDetails", "submitObRequestCoreRequest" }));

	operDetailsMap.put(CIF_OPENING_OPER, Arrays.asList(new Object[] { null, "OC_CIF_OPENING_REQUEST",
		"returnCifOpeningApprovalDetails", "returnCifOpeningScreenDetails", "returnCifOpeningCoreRequest" }));

	// Hisham Omar BUG#821619 - second level approval is not done on cif
	// opening request
	operDetailsMap.put(CIF_OPENING_REQUEST_OPER,
		Arrays.asList(new Object[] { APPROVE_CIF_OPENING_REQUEST_OPER, "OC_CIF_OPENING_REQUEST",
			"returnCifOpeningRequestApprovalDetails", "returnCifOpeningRequestScreenDetails",
			"submitCifOpeningCoreRequest" }));

    }

    /**
     * @author Suhail.Shoukathali For corporate approval flow Second level
     *         approval parameter names for opers
     */
    public static HashMap<BigDecimal, String> secondLevelParamMap = new HashMap<>();
    static
    {
	secondLevelParamMap.put(ACCOUNT_OPENING_OPER, "EnableAccountOpeningSecondLevelApproval");
	secondLevelParamMap.put(INTERNAL_TRANSFER_MY_OWN_ACCOUNTS_OPER,
		"EnableInternalTransferMyOwnAccountsSecondLevelApproval");
	secondLevelParamMap.put(INTERNAL_TRANSFER_WITHIN_BANK_ACCOUNTS_OPER,
		"EnableInternalTransferWithinBankAccountsSecondLevelApproval");
	secondLevelParamMap.put(EXTERNAL_TRANSFER_LOCAL_OPER, "EnableExternalTransferLocalSecondLevelApproval");
	secondLevelParamMap.put(EXTERNAL_TRANSFER_SWIFT_OPER, "EnableExternalTransferSwiftSecondLevelApproval");
	secondLevelParamMap.put(BANKERS_CHEQUE_TRANSFER_TO_FAVORITE_OPER, "EnableBankersChequeSecondLevelApproval");
	secondLevelParamMap.put(BANKERS_CHEQUE_TRANSFER_TO_OTHERS_REQUEST_OPER,
		"EnableBankersChequeSecondLevelApproval");
	secondLevelParamMap.put(DEMAND_DRAFT_TO_FAVORITE_OPER, "EnableDemandDraftSecondLevelApproval");
	secondLevelParamMap.put(DEMAND_DRAFT_TO_OTHERS_REQUEST_OPER, "EnableDemandDraftSecondLevelApproval");
	secondLevelParamMap.put(BILL_PAYMENT_TO_FAVORITE_OPER, "EnableBillPaymentSecondLevelApproval");
	secondLevelParamMap.put(BILL_PAYMENT_TO_OTHERS_OPER, "EnableBillPaymentSecondLevelApproval");
	secondLevelParamMap.put(UBPS_PAY_BILL_REQUEST_OPER, "EnableUBPSPaymentSecondLevelApproval");
	secondLevelParamMap.put(IBFT_TRANSFER_TO_FAVORITE_REQUEST_OPER, "EnableIBFTSecondLevelApproval");
	secondLevelParamMap.put(IBFT_TRANSFER_TO_OTHERS_REQUEST_OPER, "EnableIBFTSecondLevelApproval");
	secondLevelParamMap.put(CHEQUEBOOK_REQUEST_OPER, "EnableChequeBookRequestSecondLevelApproval");
	secondLevelParamMap.put(CARD_REQUEST_OPER, "EnableCardRequestSecondLevelApproval");
	secondLevelParamMap.put(INVESTMENT_DIVESTMENT_REQUEST_OPER, "EnableInvestmentDivestmentSecondLevelApproval");
	secondLevelParamMap.put(CIF_OPENING_OPER, "EnableCifOpeningRequestSecondLevelApproval");
	secondLevelParamMap.put(IPO_REQUEST_OPER, "EnableIPORequestSecondLevelApproval");
	secondLevelParamMap.put(RENEWAL_REQUEST_OPER, "EnableRenewalRequestSecondLevelApproval");

    }

    /**
     * 
     * 
     * @author: khaledaltimany
     *
     *
     */
    public enum HttpHeader
    {
	AUTHORIZATION("Authorization"), AUTHENTICATION_TYPE_BASIC("Basic"), X_AUTH_TOKEN(
		"X-AUTH-TOKEN"), WWW_Authenticate("WWW-Authenticate"), X_FORWARDED_FOR(
			"X-Forwarded-For"), PROXY_CLIENT_IP("Proxy-Client-IP"), WL_PROXY_CLIENT_IP(
				"WL-Proxy-Client-IP"), HTTP_CLIENT_IP(
					"HTTP_CLIENT_IP"), HTTP_X_FORWARDED_FOR("HTTP_X_FORWARDED_FOR");

	private String key;

	private HttpHeader(String key)
	{
	    this.key = key;
	}

	public String key()
	{
	    return this.key;
	}
    }

    /**
     * @author HishamOmar 0 indicates that the core system is off and a dummy
     *         data should be returned. 1 indicates that the core system is on
     *         and a service call from the core should be applied.
     */

    /** core service dynamic filter operators **/

    public static final String OPERATOR_CORE_SERVICE_DYN_FILTER_EQUAL = "service.core.dyn_filter.operator.equal";
    public static final String OPERATOR_CORE_SERVICE_DYN_FILTER_NOT_EQUAL = "service.core.dyn_filter.operator.notEqual";
    public static final String OPERATOR_CORE_SERVICE_DYN_FILTER_LESS_THAN = "service.core.dyn_filter.operator.lessThan";
    public static final String OPERATOR_CORE_SERVICE_DYN_FILTER_LESS_OR_EQUAL = "service.core.dyn_filter.operator.lessOrEqualTo";
    public static final String OPERATOR_CORE_SERVICE_DYN_FILTER_GREATER_THAN = "service.core.dyn_filter.operator.greaterThan";
    public static final String OPERATOR_CORE_SERVICE_DYN_FILTER_GREATER_OR_EQUAL = "service.core.dyn_filter.operator.greaterOrEqualTo";
    public static final String OPERATOR_CORE_SERVICE_DYN_FILTER_BEGIN_WITH = "service.core.dyn_filter.operator.beginsWith";
    public static final String OPERATOR_CORE_SERVICE_DYN_FILTER_IS_NULL = "service.core.dyn_filter.operator.isNull";
    public static final String OPERATOR_CORE_SERVICE_DYN_FILTER_IS_NOT_NULL = "service.core.dyn_filter.operator.isNotNull";
    public static final String OPERATOR_CORE_SERVICE_DYN_FILTER_NOT_BEGINS_WITH = "service.core.dyn_filter.operator.notBeginsWith";
    public static final String OPERATOR_CORE_SERVICE_DYN_FILTER_ENDS_WITH = "service.core.dyn_filter.operator.endsWith";
    public static final String OPERATOR_CORE_SERVICE_DYN_FILTER_NOT_ENDS_WITH = "service.core.dyn_filter.operator.notEndsWith";
    public static final String OPERATOR_CORE_SERVICE_DYN_FILTER_CONTAINS = "service.core.dyn_filter.operator.contains";
    public static final String OPERATOR_CORE_SERVICE_DYN_FILTER_NOT_CONTAINS = "service.core.dyn_filter.operator.notContains";
    public static final String OPERATOR_CORE_SERVICE_DYN_FILTER_IN = "service.core.dyn_filter.operator.in";

    /** end core service dynamic filter operators **/

    public static final String CORE_SYSTEM_SWITCH = "0";

    public static final String INTEGRATION_REMOTING = "PathOADMRemoting.properties";
    public static final String CSM_SERVICE_URL = "omni.csm.serviceURL";
    public static final String ASSETS_SERVICE_URL = "omni.assets.serviceURL";
    public static final String COMMON_SERVICE_URL = "omni.common.serviceURL";
    public static final String CRITICAL_SERVICE_URL = "omni.csm.criticalServices.serviceURL";
    public static final String ALERT_SERVICE_URL = "omni.alrt.serviceURL";

    public static final String CORE_INTEGRATION_PROPERTIES = "CoreServicesIntegration.properties";

    public static final String IS_ASSETS_INTEGRATED = "service.assets.integration";
    public static final String IS_CSM_INTEGRATED = "service.csm.integration";
    public static final String IS_COMMON_INTEGRATED = "service.common.integration";

    public static final String CORE_TYPE = "service.core.type";

    // Return the customer information details
    public static final String CORE_CUSTOMER_INFORMATION = "service.core.customerInformation";
    public static final String CORE_CUSTOMER_INFORMATION_BUSINESS_AREA = "service.core.customerInformation.businessArea";
    public static final String CORE_CUSTOMER_INFORMATION_BUSINESS_DOMAIN = "service.core.customerInformation.businessDomain";
    public static final String CORE_CUSTOMER_INFORMATION_OPERATION_NAME = "service.core.customerInformation.operationName";
    public static final String CORE_CUSTOMER_INFORMATION_SERVICE_DOMAIN = "service.core.customerInformation.serviceDomain";
    public static final String CORE_CUSTOMER_INFORMATION_SERVICE_ID = "service.core.customerInformation.serviceId";

    // Return the customer information List
    public static final String CORE_CUSTOMER_INFORMATION_LIST_OPERATION_NAME = "service.core.cifList.operationName";

    // Return the accounts list
    public static final String CORE_ACCOUNTS_LIST = "service.core.accountList";
    public static final String CORE_ACCOUNTS_LIST_BUSINESS_AREA = "service.core.accountList.businessArea";
    public static final String CORE_ACCOUNTS_LIST_BUSINESS_DOMAIN = "service.core.accountList.businessDomain";
    public static final String CORE_ACCOUNTS_LIST_OPERATION_NAME = "service.core.accountList.operationName";
    public static final String CORE_ACCOUNTS_LIST_SERVICE_DOMAIN = "service.core.accountList.serviceDomain";
    public static final String CORE_ACCOUNTS_LIST_SERVICE_ID = "service.core.accountList.serviceId";
    public static final String CORE_ACCOUNTS_LIST_DYN_FILTER_AMF_STATUS_CODE = "service.core.accountList.dyn_filter.amf_status_code";
    public static final String CORE_ACCOUNTS_LIST_DYN_FILTER_AMF_CURRENCY = "service.core.accountList.dyn_filter.currency";
    public static final String CORE_ACCOUNTS_LIST_DYN_FILTER_AMF_AFFECT = "service.core.accountList.dyn_filter.affect";
    public static final String CORE_ACCOUNTS_LIST_DYN_FILTER_CARD_AFFECT = "service.core.accountList.dyn_filter.affectCard";
    public static final String CORE_ACCOUNTS_LIST_DYN_FILTER_AMF_AFFECTTWO = "service.core.accountList.dyn_filter.affectTwo";
    public static final String CORE_ACCOUNTS_LIST_DYN_FILTER_CARD_AFFECTONE = "service.core.accountList.dyn_filter.affectOne";
    
    // Return the fixed maturity accounts list

    public static final String CORE_FIXED_MATURITY_ACCOUNTS_LIST_BUSINESS_AREA = "service.core.fmaAccountList.businessArea";
    public static final String CORE_FIXED_MATURITY_ACCOUNTS_LIST_BUSINESS_DOMAIN = "service.core.fmaAccountList.businessDomain";
    public static final String CORE_FIXED_MATURITY_ACCOUNTS_LIST_OPERATION_NAME = "service.core.fmaAccountList.operationName";
    public static final String CORE_FIXED_MATURITY_ACCOUNTS_LIST_SERVICE_DOMAIN = "service.core.fmaAccountList.serviceDomain";
    public static final String CORE_FIXED_MATURITY_ACCOUNTS_LIST_SERVICE_ID = "service.core.fmaAccountList.serviceId";
    public static final String CORE_FIXED_MATURITY_ACCOUNTS_LIST_DYN_FILTER_AMF_GL_CODE = "service.core.fmaAccountList.dyn_filter.amf_gl_code";

    // Return the fixed maturity accounts details
    public static final String CORE_FIXED_MATURITY_ACCOUNTS_DETAILS = "service.core.fmaAccountDetails";
    public static final String CORE_FIXED_MATURITY_ACCOUNTS_DETAILS_BUSINESS_AREA = "service.core.fmaAccountDetails.businessArea";
    public static final String CORE_FIXED_MATURITY_ACCOUNTS_DETAILS_BUSINESS_DOMAIN = "service.core.fmaAccountDetails.businessDomain";
    public static final String CORE_FIXED_MATURITY_ACCOUNTS_DETAILS_OPERATION_NAME = "service.core.fmaAccountDetails.operationName";
    public static final String CORE_FIXED_MATURITY_ACCOUNTS_DETAILS_SERVICE_DOMAIN = "service.core.fmaAccountDetails.serviceDomain";
    public static final String CORE_FIXED_MATURITY_ACCOUNTS_DETAILS_SERVICE_ID = "service.core.fmaAccountDetails.serviceId";
    
    // Return the expired holding list
    public static final String CORE_EXPIRED_HOLDIG_LIST = "service.core.expiredHoldingList";
    public static final String CORE_EXPIRED_HOLDIG_LIST_BUSINESS_AREA = "service.core.expiredHoldingList.businessArea";
    public static final String CORE_EXPIRED_HOLDIG_LIST_BUSINESS_DOMAIN = "service.core.expiredHoldingList.businessDomain";
    public static final String CORE_EXPIRED_HOLDIG_LIST_OPERATION_NAME = "service.core.expiredHoldingList.operationName";
    public static final String CORE_EXPIRED_HOLDIG_LIST_SERVICE_DOMAIN = "service.core.expiredHoldingList.serviceDomain";
    public static final String CORE_EXPIRED_HOLDIG_LIST_SERVICE_ID = "service.core.expiredHoldingList.serviceId";

    // Return the securityMarketPriceDetails
    public static final String CORE_SECURITY_MARKET_PRICE_DETAILS = "service.core.securityMarketPriceDetails";
    public static final String CORE_SECURITY_MARKET_PRICE_DETAILS_BUSINESS_AREA = "service.core.securityMarketPriceDetails.businessArea";
    public static final String CORE_SECURITY_MARKET_PRICE_DETAILS_BUSINESS_DOMAIN = "service.core.securityMarketPriceDetails.businessDomain";
    public static final String CORE_SECURITY_MARKET_PRICE_DETAILS_OPERATION_NAME = "service.core.securityMarketPriceDetails.operationName";
    public static final String CORE_SECURITY_MARKET_PRICE_DETAILS_SERVICE_DOMAIN = "service.core.securityMarketPriceDetails.serviceDomain";
    public static final String CORE_SECURITY_MARKET_PRICE_DETAILS_SERVICE_ID = "service.core.securityMarketPriceDetails.serviceId";

    // Return the PortfolioPosition
    public static final String CORE_PORTFOLIO_POSITION = "service.core.portfolioPosition";
    public static final String CORE_PORTFOLIO_POSITION_BUSINESS_AREA = "service.core.portfolioPosition.businessArea";
    public static final String CORE_PORTFOLIO_POSITION_BUSINESS_DOMAIN = "service.core.portfolioPosition.businessDomain";
    public static final String CORE_PORTFOLIO_POSITION_SERVICE_DOMAIN = "service.core.portfolioPosition.serviceDomain";
    public static final String CORE_PORTFOLIO_POSITION_OPERATION_NAME = "service.core.portfolioPosition.operationName";
    public static final String CORE_PORTFOLIO_POSITION_SERVICE_ID = "service.core.portfolioPosition.serviceId";

    // Return the PortfolioList
    public static final String CORE_PORTFOLIO_LIST = "service.core.portfolioList";
    public static final String CORE_PORTFOLIO_LIST_BUSINESS_AREA = "service.core.portfolioList.businessArea";
    public static final String CORE_PORTFOLIO_LIST_BUSINESS_DOMAIN = "service.core.portfolioList.businessDomain";
    public static final String CORE_PORTFOLIO_LIST_SERVICE_DOMAIN = "service.core.portfolioList.serviceDomain";
    public static final String CORE_PORTFOLIO_LIST_OPERATION_NAME = "service.core.portfolioList.operationName";
    public static final String CORE_PORTFOLIO_LIST_SERVICE_ID = "service.core.portfolioList.serviceId";
    public static final String CORE_PORTFOLIO_LIST_DYN_FILTER_CIF = "service.core.portfolioList.dyn_filter.cif";

    // Return the IPO List
    public static final String CORE_IPO_LIST = "service.core.ipoList";
    public static final String CORE_IPO_LIST_BUSINESS_AREA = "service.core.ipoList.businessArea";
    public static final String CORE_IPO_LIST_BUSINESS_DOMAIN = "service.core.ipoList.businessDomain";
    public static final String CORE_IPO_LIST_SERVICE_DOMAIN = "service.core.ipoList.serviceDomain";
    public static final String CORE_IPO_LIST_OPERATION_NAME = "service.core.ipoList.operationName";
    public static final String CORE_IPO_LIST_SERVICE_ID = "service.core.ipoList.serviceId";
    public static final String CORE_IPO_LIST_DYN_FILTER_PORTFOLIO_CIF = "service.core.ipoList.dyn_filter.portfolio_cif";
    public static final String CORE_IPO_LIST_DYN_FILTER_TRADE_DATE = "service.core.ipoList.dyn_filter.trade_date";
    public static final String CORE_IPO_LIST_DYN_FILTER_STATUS = "service.core.ipoList.dyn_filter.status";

    // Return the CIF List
    public static final String CORE_CIF_LIST = "service.core.cifList";
    public static final String CORE_CIF_LIST_BUSINESS_AREA = "service.core.cifList.businessArea";
    public static final String CORE_CIF_LIST_BUSINESS_DOMAIN = "service.core.cifList.businessDomain";
    public static final String CORE_CIF_LIST_OPERATION_NAME = "service.core.cifList.operationName";
    public static final String CORE_CIF_LIST_SERVICE_DOMAIN = "service.core.cifList.serviceDomain";
    public static final String CORE_CIF_LIST_SERVICE_ID = "service.core.cifList.serviceId";
    public static final String CORE_CIF_LIST_DYN_FILTER_DATE_CREATED = "service.core.cifList.dyn_filter.date_created";
    public static final String CORE_CIF_LIST_DYN_FILTER_CIF_STATUS = "service.core.cifList.dyn_filter.cif_status";
    public static final String CORE_CIF_LIST_DYN_FILTER_STATUS = "service.core.cifList.dyn_filter.status";
    public static final String CORE_CIF_LIST_DYN_FILTER_AGENT = "service.core.cifList.dyn_filter.agent";
    public static final String CORE_CIF_LIST_DYN_FILTER_CIF_NO = "service.core.cifList.dyn_filter.cif_no";
    public static final String CORE_CIF_LIST_DYN_FILTER_CIF_TYPE = "service.core.cifList.dyn_filter.cif_type";
    public static final String CORE_CIF_LIST_DYN_FILTER_ID_TYPE = "service.core.cifList.dyn_filter.id_type";
    public static final String CORE_CIF_LIST_DYN_FILTER_CIF_SHORT_NAME_ENG = "service.core.cifList.dyn_filter.short_name_eng";
    public static final String CORE_CIF_LIST_DYN_FILTER_CIF_TYPE_DESC  = "service.core.cifList.dyn_filter.cif_type_desc";

    // Return the statement of account
    public static final String CORE_STATEMENT_OF_ACCOUNT = "service.core.statementOfAccount";
    public static final String CORE_STATEMENT_OF_ACCOUNT_BUSINESS_AREA = "service.core.statementOfAccount.businessArea";
    public static final String CORE_STATEMENT_OF_ACCOUNT_BUSINESS_DOMAIN = "service.core.statementOfAccount.businessDomain";
    public static final String CORE_STATEMENT_OF_ACCOUNT_OPERATION_NAME = "service.core.statementOfAccount.operationName";
    public static final String CORE_STATEMENT_OF_ACCOUNT_SERVICE_DOMAIN = "service.core.statementOfAccount.serviceDomain";
    public static final String CORE_STATEMENT_OF_ACCOUNT_SERVICE_ID = "service.core.statementOfAccount.serviceId";
    public static final String CORE_STATEMENT_OF_ACCOUNT_DYN_FILTER_DOF_JV_TYPE = "service.core.statementOfAccount.dyn_filter.dof_jv_type";
    public static final String CORE_STATEMENT_OF_ACCOUNT_DYN_FILTER_EX_REVERSAL_TRX = "service.core.statementOfAccount.dyn_filter.ex_reversal_trx";
    public static final String CORE_STATEMENT_OF_ACCOUNT_DYN_FILTER_DOF_TRX_TYPE = "service.core.statementOfAccount.dyn_filter.dof_trx_type";
    public static final String CORE_STATEMENT_OF_ACCOUNT_DYN_FILTER_DOF_CV_AMOUNT = "service.core.statementOfAccount.dyn_filter.dof_cv_amount";
    public static final String CORE_STATEMENT_OF_ACCOUNT_DYN_FILTER_TRS_TYPE_IND = "service.core.statementOfAccount.dyn_filter.ctstrs_trx_type_ind";
    public static final String CORE_STATEMENT_OF_ACCOUNT_DYN_FILTER_DOF_DESCRIPTION = "service.core.statementOfAccount.dyn_filter.dof_description";

    // Return the aggregated balance in statement of account
    public static final String CORE_AGGREGATED_BALANCE = "service.core.aggregatedBalance";
    public static final String CORE_AGGREGATED_BALANCE_BUSINESS_AREA = "service.core.aggregatedBalance.businessArea";
    public static final String CORE_AGGREGATED_BALANCE_BUSINESS_DOMAIN = "service.core.aggregatedBalance.businessDomain";
    public static final String CORE_AGGREGATED_BALANCE_OPERATION_NAME = "service.core.aggregatedBalance.operationName";
    public static final String CORE_AGGREGATED_BALANCE_SERVICE_DOMAIN = "service.core.aggregatedBalance.serviceDomain";
    public static final String CORE_AGGREGATED_BALANCE_SERVICE_ID = "service.core.aggregatedBalance.serviceId";
    
    // return statement of fixed maturity account
    public static final String CORE_FIXED_MATURITY_STATEMENT_OF_ACCOUNT = "service.core.fixedMaturityStatementOfAccount";
    public static final String CORE_FIXED_MATURITY_STATEMENT_OF_ACCOUNT_BUSINESS_AREA = "service.core.fixedMaturityStatementOfAccount.businessArea";
    public static final String CORE_FIXED_MATURITY_STATEMENT_OF_ACCOUNT_BUSINESS_DOMAIN = "service.core.fixedMaturityStatementOfAccount.businessDomain";
    public static final String CORE_FIXED_MATURITY_STATEMENT_OF_ACCOUNT_OPERATION_NAME = "service.core.fixedMaturityStatementOfAccount.operationName";
    public static final String CORE_FIXED_MATURITY_STATEMENT_OF_ACCOUNT_SERVICE_DOMAIN = "service.core.fixedMaturityStatementOfAccount.serviceDomain";
    public static final String CORE_FIXED_MATURITY_STATEMENT_OF_ACCOUNT_SERVICE_ID = "service.core.fixedMaturityStatementOfAccount.serviceId";

    // Return the account details
    public static final String CORE_ACCOUNT_DETAILS = "service.core.accountDetails";
    public static final String CORE_ACCOUNT_DETAILS_BUSINESS_AREA = "service.core.accountDetails.businessArea";
    public static final String CORE_ACCOUNT_DETAILS_BUSINESS_DOMAIN = "service.core.accountDetails.businessDomain";
    public static final String CORE_ACCOUNT_DETAILS_OPERATION_NAME = "service.core.accountDetails.operationName";
    public static final String CORE_ACCOUNT_DETAILS_SERVICE_DOMAIN = "service.core.accountDetails.serviceDomain";
    public static final String CORE_ACCOUNT_DETAILS_SERVICE_ID = "service.core.accountDetails.serviceId";

    // Create general account request
    public static final String CORE_CREATE_GENERAL_ACCOUNT = "service.core.createGeneralAccount";
    public static final String CORE_CREATE_GENERAL_ACCOUNT_BUSINESS_AREA = "service.core.createGeneralAccount.businessArea";
    public static final String CORE_CREATE_GENERAL_ACCOUNT_BUSINESS_DOMAIN = "service.core.createGeneralAccount.businessDomain";
    public static final String CORE_CREATE_GENERAL_ACCOUNT_OPERATION_NAME = "service.core.createGeneralAccount.operationName";
    public static final String CORE_CREATE_GENERAL_ACCOUNT_SERVICE_DOMAIN = "service.core.createGeneralAccount.serviceDomain";
    public static final String CORE_CREATE_GENERAL_ACCOUNT_SERVICE_ID = "service.core.createGeneralAccount.serviceId";
    
    // Create general account request
    public static final String CORE_CREATE_FIXED_MATURITY_ACCOUNT = "service.core.createFixedMaturityAccount";
    public static final String CORE_CREATE_FIXED_MATURITY_ACCOUNT_BUSINESS_AREA = "service.core.createFixedMaturityAccount.businessArea";
    public static final String CORE_CREATE_FIXED_MATURITY_ACCOUNT_BUSINESS_DOMAIN = "service.core.createFixedMaturityAccount.businessDomain";
    public static final String CORE_CREATE_FIXED_MATURITY_ACCOUNT_OPERATION_NAME = "service.core.createFixedMaturityAccount.operationName";
    public static final String CORE_CREATE_FIXED_MATURITY_ACCOUNT_SERVICE_DOMAIN = "service.core.createFixedMaturityAccount.serviceDomain";
    public static final String CORE_CREATE_FIXED_MATURITY_ACCOUNT_SERVICE_ID = "service.core.createFixedMaturityAccount.serviceId";
    
    // Return the sukuk list
    public static final String CORE_SUKUK_LIST = "service.core.sukukList";
    public static final String CORE_SUKUK_LIST_BUSINESS_AREA = "service.core.sukukList.businessArea";
    public static final String CORE_SUKUK_LIST_BUSINESS_DOMAIN = "service.core.sukukList.businessDomain";
    public static final String CORE_SUKUK_LIST_OPERATION_NAME = "service.core.sukukList.operationName";
    public static final String CORE_SUKUK_LIST_SERVICE_DOMAIN = "service.core.sukukList.serviceDomain";
    public static final String CORE_SUKUK_LIST_SERVICE_ID = "service.core.sukukList.serviceId";

    // Return the Holding renewal list
    public static final String CORE_HOLDING_RENEWAL_LIST = "service.core.holdingRenewalList";
    public static final String CORE_HOLDING_RENEWAL_LIST_BUSINESS_AREA = "service.core.holdingRenewalList.businessArea";
    public static final String CORE_HOLDING_RENEWAL_LIST_BUSINESS_DOMAIN = "service.core.holdingRenewalList.businessDomain";
    public static final String CORE_HOLDING_RENEWAL_LIST_OPERATION_NAME = "service.core.holdingRenewalList.operationName";
    public static final String CORE_HOLDING_RENEWAL_LIST_SERVICE_DOMAIN = "service.core.holdingRenewalList.serviceDomain";
    public static final String CORE_HOLDING_RENEWAL_LIST_SERVICE_ID = "service.core.holdingRenewalList.serviceId";
    public static final String CORE_HOLDING_RENEWAL_LIST_DYN_FILTER_STATUS = "service.core.holdingRenewalList.dyn_filter.status";
    public static final String CORE_HOLDING_RENEWAL_LIST_DYN_FILTER_REQ_DATE = "service.core.holdingRenewalList.dyn_filter.req_date";
    public static final String CORE_HOLDING_RENEWAL_LIST_DYN_FILTER_PORTFOLIO_CIF="service.core.holdingRenewalList.dyn_filter.portfolio_cif";
    public static final String CORE_HOLDING_RENEWAL_LIST_DYN_FILTER_SECURITY_TYPE_CODE="service.core.holdingRenewalList.dyn_filter.security_type_code";
    public static final String CORE_HOLDING_RENEWAL_LIST_DYN_FILTER_QUANTITY="service.core.holdingRenewalList.dyn_filter.quantity";
    public static final String CORE_HOLDING_RENEWAL_LIST_DYN_FILTER_QUANTITY_LIQUIDATION="service.core.holdingRenewalList.dyn_filter.quantity_liquidation";
    public static final String CORE_HOLDING_RENEWAL_LIST_DYN_FILTER_ISDARA_NUMBER="service.core.holdingRenewalList.dyn_filter.isdara_number";
    public static final String CORE_HOLDING_RENEWAL_LIST_DYN_FILTER_ISSUE_DATE="service.core.holdingRenewalList.dyn_filter.issue_date";
    public static final String CORE_HOLDING_RENEWAL_LIST_DYN_FILTER_NEW_ISDARA_NUMBER="service.core.holdingRenewalList.dyn_filter.new_isdara_number";
    public static final String CORE_HOLDING_RENEWAL_LIST_DYN_FILTER_SUKUK_TYPE="service.core.holdingRenewalList.dyn_filter.sukuk_type";
    // Create Holding renewal request
    public static final String CORE_CREATE_HOLDING_RENEWAL_REQUEST = "service.core.createHoldingRenewalRequest";
    public static final String CORE_CREATE_HOLDING_RENEWAL_REQUEST_BUSINESS_AREA = "service.core.createHoldingRenewalRequest.businessArea";
    public static final String CORE_CREATE_HOLDING_RENEWAL_REQUEST_BUSINESS_DOMAIN = "service.core.createHoldingRenewalRequest.businessDomain";
    public static final String CORE_CREATE_HOLDING_RENEWAL_REQUEST_OPERATION_NAME = "service.core.createHoldingRenewalRequest.operationName";
    public static final String CORE_CREATE_HOLDING_RENEWAL_REQUEST_SERVICE_DOMAIN = "service.core.createHoldingRenewalRequest.serviceDomain";
    public static final String CORE_CREATE_HOLDING_RENEWAL_REQUEST_SERVICE_ID = "service.core.createHoldingRenewalRequest.serviceId";

    // Create IPO request
    public static final String CORE_CREATE_IPO_REQUEST = "service.core.createIPORequest";
    public static final String CORE_CREATE_IPO_REQUEST_BUSINESS_AREA = "service.core.createIPORequest.businessArea";
    public static final String CORE_CREATE_IPO_REQUEST_BUSINESS_DOMAIN = "service.core.createIPORequest.businessDomain";
    public static final String CORE_CREATE_IPO_REQUEST_OPERATION_NAME = "service.core.createIPORequest.operationName";
    public static final String CORE_CREATE_IPO_REQUEST_SERVICE_DOMAIN = "service.core.createIPORequest.serviceDomain";
    public static final String CORE_CREATE_IPO_REQUEST_SERVICE_ID = "service.core.createIPORequest.serviceId";

    // Create Transaction List
    public static final String CORE_TRANSACTION_LIST = "service.core.transactionList";
    public static final String CORE_TRANSACTION_LIST_BUSINESS_AREA = "service.core.transactionList.businessArea";
    public static final String CORE_TRANSACTION_LIST_BUSINESS_DOMAIN = "service.core.transactionList.businessDomain";
    public static final String CORE_TRANSACTION_LIST_OPERATION_NAME = "service.core.transactionList.operationName";
    public static final String CORE_TRANSACTION_LIST_SERVICE_DOMAIN = "service.core.transactionList.serviceDomain";
    public static final String CORE_TRANSACTION_LIST_SERVICE_ID = "service.core.transactionList.serviceId";
    public static final String CORE_TRANSACTION_LIST_DYN_FILTER_CTSTRS_TRS_DATE = "service.core.transactionList.dyn_filter.ctstrs_trs_date";
    public static final String CORE_TRANSACTION_LIST_DYN_FILTER_CTSTRS_AMOUNT = "service.core.transactionList.dyn_filter.ctstrs_amount";
    public static final String CORE_TRANSACTION_LIST_DYN_FILTER_CTSTRS_TRS_AC_CIF = "service.core.transactionList.dyn_filter.ctstrs_trs_ac_cif";
    public static final String CORE_TRANSACTION_LIST_DYN_FILTER_CTSTRS_TRS_CY = "service.core.transactionList.dyn_filter.ctstrs_trs_cy";
    public static final String CORE_TRANSACTION_LIST_DYN_FILTER_CTSTRS_TRX_TYPE = "service.core.transactionList.dyn_filter.ctstrs_trx_type";
    public static final String CORE_TRANSACTION_LIST_DYN_FILTER_CTSTRS_CREATED_BY = "service.core.transactionList.dyn_filter.ctstrs_created_by";
    public static final String CORE_TRANSACTION_LIST_DYN_FILTER_STATUS = "service.core.transactionList.dyn_filter.status";
    public static final String CORE_TRANSACTION_LIST_DYN_FILTER_CTSTRS_TRS_NO = "service.core.transactionList.dyn_filter.ctstrs_trs_no";
    // Return the returnSecuritiesTrxList
    public static final String CORE_SECURITIES_TRANSACTION_LIST = "service.core.securitiesTrxList";
    public static final String CORE_SECURITIES_TRANSACTION_BUSINESS_AREA = "service.core.securitiesTrxList.businessArea";
    public static final String CORE_SECURITIES_TRANSACTION_BUSINESS_DOMAIN = "service.core.securitiesTrxList.businessDomain";
    public static final String CORE_SECURITIES_TRANSACTION_OPERATION_NAME = "service.core.securitiesTrxList.operationName";
    public static final String CORE_SECURITIES_TRANSACTION_SERVICE_DOMAIN = "service.core.securitiesTrxList.serviceDomain";
    public static final String CORE_SECURITIES_TRANSACTION_SERVICE_ID = "service.core.securitiesTrxList.serviceId";

    // Return the TransactioStatementList
    public static final String CORE_TRANSACTION_STATEMENT_LIST = "service.core.statementOfTransactionList";
    public static final String CORE_TRANSACTION_STATEMENT_BUSINESS_AREA = "service.core.statementOfTransactionList.businessArea";
    public static final String CORE_TRANSACTION_STATEMENT_BUSINESS_DOMAIN = "service.core.statementOfTransactionList.businessDomain";
    public static final String CORE_TRANSACTION_STATEMENT_OPERATION_NAME = "service.core.statementOfTransactionList.operationName";
    public static final String CORE_TRANSACTION_STATEMENT_SERVICE_DOMAIN = "service.core.statementOfTransactionList.serviceDomain";
    public static final String CORE_TRANSACTION_STATEMENT_SERVICE_ID = "service.core.statementOfTransactionList.serviceId";

    // Return the Exchange rate List
    public static final String CORE_EXCHANGE_RATE_LIST = "service.core.exchangeRateList";
    public static final String CORE_EXCHANGE_RATE_LIST_BUSINESS_AREA = "service.core.exchangeRateList.businessArea";
    public static final String CORE_EXCHANGE_RATE_LIST_BUSINESS_DOMAIN = "service.core.exchangeRateList.businessDomain";
    public static final String CORE_EXCHANGE_RATE_LIST_OPERATION_NAME = "service.core.exchangeRateList.operationName";
    public static final String CORE_EXCHANGE_RATE_LIST_SERVICE_DOMAIN = "service.core.exchangeRateList.serviceDomain";
    public static final String CORE_EXCHANGE_RATE_LIST_SERVICE_ID = "service.core.exchangeRateList.serviceId";

    // Create CIF Opening Request For Retail
    public static final String CORE_CREATE_CIF_OPENING_REQUEST = "service.core.createRetailCif";
    public static final String CORE_CREATE_CIF_OPENING_REQUEST_BUSINESS_AREA = "service.core.createRetailCif.bussinessArea";
    public static final String CORE_CREATE_CIF_OPENING_REQUEST_BUSINESS_DOMAIN = "service.core.createRetailCif.businessDomain";
    public static final String CORE_CREATE_CIF_OPENING_REQUEST_OPERATION_NAME = "service.core.createRetailCif.operationName";
    public static final String CORE_CREATE_CIF_OPENING_REQUEST_SERVICE_DOMAIN = "service.core.createRetailCif.serviceDomain";
    public static final String CORE_CREATE_CIF_OPENING_REQUEST_SERVICE_ID = "service.core.createRetailCif.serviceId";

    // Update CIF Details For Retail
    public static final String CORE_UPDATE_CIF_DETAILS = "service.core.updateRetailCif";
    public static final String CORE_UPDATE_CIF_DETAILS_BUSINESS_AREA = "service.core.updateRetailCif.bussinessArea";
    public static final String CORE_UPDATE_CIF_DETAILS_BUSINESS_DOMAIN = "service.core.updateRetailCif.businessDomain";
    public static final String CORE_UPDATE_CIF_DETAILS_OPERATION_NAME = "service.core.updateRetailCif.operationName";
    public static final String CORE_UPDATE_CIF_DETAILS_SERVICE_DOMAIN = "service.core.updateRetailCif.serviceDomain";
    public static final String CORE_UPDATE_CIF_DETAILS_SERVICE_ID = "service.core.updateRetailCif.serviceId";

    // Return List smart field Details
    public static final String COMMON_SMART_FIELD_DETAILS_BUSINESS_AREA = "service.common.smartFieldDetails.businessArea";
    public static final String COMMON_SMART_FIELD_DETAILS_BUSINESS_DOMAIN = "service.common.smartFieldDetails.businessDomain";
    public static final String COMMON_SMART_FIELD_DETAILS_OPERATION_NAME = "service.common.smartFieldDetails.operationName";
    public static final String COMMON_SMART_FIELD_DETAILS_SERVICE_DOMAIN = "service.common.smartFieldDetails.serviceDomain";
    public static final String COMMON_SMART_FIELD_DETAILS_SERVICE_ID = "service.common.smartFieldDetails.serviceId";

    // Return List smart field List
    public static final String COMMON_SMART_FIELD_LIST_BUSINESS_AREA = "service.common.smartFieldList.businessArea";
    public static final String COMMON_SMART_FIELD_LIST_BUSINESS_DOMAIN = "service.common.smartFieldList.businessDomain";
    public static final String COMMON_SMART_FIELD_LIST_OPERATION_NAME = "service.common.smartFieldList.operationName";
    public static final String COMMON_SMART_FIELD_LIST_SERVICE_DOMAIN = "service.common.smartFieldList.serviceDomain";
    public static final String COMMON_SMART_FIELD_LIST_SERVICE_ID = "service.common.smartFieldList.serviceId";

    // Send Alert Notifcation Constants
    public static final String CORE_SEND_ALERT_NOTIFICATION_BUSINESS_AREA = "service.core.sendAlertNotification.businessArea";
    public static final String CORE_SEND_ALERT_NOTIFICATION_BUSINESS_DOMAIN = "service.core.sendAlertNotification.businessDomain";
    public static final String CORE_SEND_ALERT_NOTIFICATION_OPERATION_NAME = "service.core.sendAlertNotification.operationName";
    public static final String CORE_SEND_ALERT_NOTIFICATION_SERVICE_DOMAIN = "service.core.sendAlertNotification.serviceDomain";
    public static final String CORE_SEND_ALERT_NOTIFICATION_SERVICE_ID = "service.core.sendAlertNotification.serviceId";

    // Return the Exchange rate List
    public static final String CORE_AVAILABLE_BALANCE = "service.core.availableBalance";
    public static final String CORE_AVAILABLE_BALANCE_BUSINESS_AREA = "service.core.availableBalance.businessArea";
    public static final String CORE_AVAILABLE_BALANCE_BUSINESS_DOMAIN = "service.core.availableBalance.businessDomain";
    public static final String CORE_AVAILABLE_BALANCE_OPERATION_NAME = "service.core.availableBalance.operationName";
    public static final String CORE_AVAILABLE_BALANCE_SERVICE_DOMAIN = "service.core.availableBalance.serviceDomain";
    public static final String CORE_AVAILABLE_BALANCE_SERVICE_ID = "service.core.availableBalance.serviceId";

    // Return the card list
    public static final String CORE_CARD_LIST = "service.core.cardList";
    public static final String CORE_CARD_LIST_BUSINESS_AREA = "service.core.cardList.businessArea";
    public static final String CORE_CARD_LIST_BUSINESS_DOMAIN = "service.core.cardList.businessDomain";
    public static final String CORE_CARD_LIST_OPERATION_NAME = "service.core.cardList.operationName";
    public static final String CORE_CARD_LIST_SERVICE_DOMAIN = "service.core.cardList.serviceDomain";
    public static final String CORE_CARD_LIST_SERVICE_ID = "service.core.cardList.serviceId";
    public static final String CORE_DEBIT_CARD_TYPE       = "service.core.cardList.debit_type";
    public static final String CORE_CREDIT_CARD_TYPE      = "service.core.cardList.credit_type";

    // Delete a Transaction
    public static final String CORE_DELETE_TRANSACTON = "service.core.deleteTransaction";
    public static final String CORE_DELETE_TRANSACTON_BUSINESS_AREA = "service.core.deleteTransaction.businessArea";
    public static final String CORE_DELETE_TRANSACTON_BUSINESS_DOMAIN = "service.core.deleteTransaction.businessDomain";
    public static final String CORE_DELETE_TRANSACTON_OPERATION_NAME = "service.core.deleteTransaction.operationName";
    public static final String CORE_DELETE_TRANSACTON_SERVICE_DOMAIN = "service.core.deleteTransaction.serviceDomain";
    public static final String CORE_DELETE_TRANSACTON_SERVICE_ID = "service.core.deleteTransaction.serviceId";

    // calculate Amount by Currency Code
    public static final String CORE_CALCULATE_AMOUNT_CURRENCY = "service.core.calculateAmountCurrency";
    public static final String CORE_CALCULATE_AMOUNT_CURRENCY_BUSINESS_AREA = "service.core.calculateAmountCurrency.businessArea";
    public static final String CORE_CALCULATE_AMOUNT_CURRENCY_BUSINESS_DOMAIN = "service.core.calculateAmountCurrency.businessDomain";
    public static final String CORE_CALCULATE_AMOUNT_CURRENCY_OPERATION_NAME = "service.core.calculateAmountCurrency.operationName";
    public static final String CORE_CALCULATE_AMOUNT_CURRENCY_SERVICE_DOMAIN = "service.core.calculateAmountCurrency.serviceDomain";
    public static final String CORE_CALCULATE_AMOUNT_CURRENCY_SERVICE_ID = "service.core.calculateAmountCurrency.serviceId";

    // Update General account
    public static final String CORE_UPDATE_GENERAL_ACCOUNT = "service.core.updateGeneralAccount";
    public static final String CORE_UPDATE_GENERAL_ACCOUNT_BUSINESS_AREA = "service.core.updateGeneralAccount.bussinessArea";
    public static final String CORE_UPDATE_GENERAL_ACCOUNT_BUSINESS_DOMAIN = "service.core.updateGeneralAccount.businessDomain";
    public static final String CORE_UPDATE_GENERAL_ACCOUNT_OPERATION_NAME = "service.core.updateGeneralAccount.operationName";
    public static final String CORE_UPDATE_GENERAL_ACCOUNT_SERVICE_DOMAIN = "service.core.updateGeneralAccount.serviceDomain";
    public static final String CORE_UPDATE_GENERAL_ACCOUNT_SERVICE_ID = "service.core.updateGeneralAccount.serviceId";

    // Return Smart Definition List
    public static final String COMMON_RETURN_SMART_DEFINITION_LIST = "service.core.returnSmartDefinitionList";
    public static final String COMMON_RETURN_SMART_DEFINITION_LIST_BUSINESS_AREA = "service.core.returnSmartDefinitionList.businessArea";
    public static final String COMMON_RETURN_SMART_DEFINITION_LIST_BUSINESS_DOMAIN = "service.core.returnSmartDefinitionList.businessDomain";
    public static final String COMMON_RETURN_SMART_DEFINITION_LIST_OPERATION_NAME = "service.core.returnSmartDefinitionList.operationName";
    public static final String COMMON_RETURN_SMART_DEFINITION_LIST_SERVICE_DEMAIN = "service.core.returnSmartDefinitionList.serviceDomain";
    public static final String COMMON_RETURN_SMART_DEFINITION_LIST_SERVICE_ID = "service.core.returnSmartDefinitionList.serviceId";
    public static final String COMMON_RETURN_SMART_DEFINITION_LIST_APPLICATION_NAME = "RET";
    public static final String COMMON_RETURN_SMART_DEFINITION_LIST_PROG_REFERENCE = "F00I1";

    // update Holding renewal request
    public static final String CORE_UPDATE_HOLDING_RENEWAL_REQUEST = "service.core.updateHoldingRenewalRequest";
    public static final String CORE_UPDATE_HOLDING_RENEWAL_REQUEST_BUSINESS_AREA = "service.core.updateHoldingRenewalRequest.businessArea";
    public static final String CORE_UPDATE_HOLDING_RENEWAL_REQUEST_BUSINESS_DOMAIN = "service.core.updateHoldingRenewalRequest.businessDomain";
    public static final String CORE_UPDATE_HOLDING_RENEWAL_REQUEST_OPERATION_NAME = "service.core.updateHoldingRenewalRequest.operationName";
    public static final String CORE_UPDATE_HOLDING_RENEWAL_REQUEST_SERVICE_DOMAIN = "service.core.updateHoldingRenewalRequest.serviceDomain";
    public static final String CORE_UPDATE_HOLDING_RENEWAL_REQUEST_SERVICE_ID = "service.core.updateHoldingRenewalRequest.serviceId";

    // delete Holding renewal request
    public static final String CORE_DELETE_HOLDING_RENEWAL_REQUEST = "service.core.deleteHoldingRenewalRequest";
    public static final String CORE_DELETE_HOLDING_RENEWAL_REQUEST_BUSINESS_AREA = "service.core.deleteHoldingRenewalRequest.businessArea";
    public static final String CORE_DELETE_HOLDING_RENEWAL_REQUEST_BUSINESS_DOMAIN = "service.core.deleteHoldingRenewalRequest.businessDomain";
    public static final String CORE_DELETE_HOLDING_RENEWAL_REQUEST_OPERATION_NAME = "service.core.deleteHoldingRenewalRequest.operationName";
    public static final String CORE_DELETE_HOLDING_RENEWAL_REQUEST_SERVICE_DOMAIN = "service.core.deleteHoldingRenewalRequest.serviceDomain";
    public static final String CORE_DELETE_HOLDING_RENEWAL_REQUEST_SERVICE_ID = "service.core.deleteHoldingRenewalRequest.serviceId";

    // Hold of fund
    public static final String CORE_HOLD_OF_FUND = "service.core.holdOfFund";
    public static final String CORE_HOLD_OF_FUND_BUSINESS_AREA = "service.core.holdOfFund.bussinessArea";
    public static final String CORE_HOLD_OF_FUND_BUSINESS_DOMAIN = "service.core.holdOfFund.businessDomain";
    public static final String CORE_HOLD_OF_FUND_OPERATION_NAME = "service.core.holdOfFund.operationName";
    public static final String CORE_HOLD_OF_FUND_SERVICE_DOMAIN = "service.core.holdOfFund.serviceDomain";
    public static final String CORE_HOLD_OF_FUND_SERVICE_ID = "service.core.holdOfFund.serviceId";

    // Create Transfer
    public static final String CORE_CREATE_TRANSFER = "service.core.createTransfer";
    public static final String CORE_CREATE_TRANSFER_BUSINESS_AREA = "service.core.createTransfer.businessArea";
    public static final String CORE_CREATE_TRANSFER_BUSINESS_DOMAIN = "service.core.createTransfer.businessDomain";
    public static final String CORE_CREATE_TRANSFER_OPERATION_NAME = "service.core.createTransfer.operationName";
    public static final String CORE_CREATE_TRANSFER_SERVICE_DOMAIN = "service.core.createTransfer.serviceDomain";
    public static final String CORE_CREATE_TRANSFER_SERVICE_ID = "service.core.createTransfer.serviceId";

    // delete IPO request
    public static final String CORE_DELETE_IPO_REQUEST = "service.core.deleteIPORequest";
    public static final String CORE_DELETE_IPO_REQUEST_BUSINESS_AREA = "service.core.deleteIPORequest.businessArea";
    public static final String CORE_DELETE_IPO_REQUEST_BUSINESS_DOMAIN = "service.core.deleteIPORequest.businessDomain";
    public static final String CORE_DELETE_IPO_REQUEST_OPERATION_NAME = "service.core.deleteIPORequest.operationName";
    public static final String CORE_DELETE_IPO_REQUEST_SERVICE_DOMAIN = "service.core.deleteIPORequest.serviceDomain";
    public static final String CORE_DELETE_IPO_REQUEST_SERVICE_ID = "service.core.deleteIPORequest.serviceId";

    // update IPO request
    public static final String CORE_UPDATE_IPO_REQUEST = "service.core.updateIPORequest";
    public static final String CORE_UPDATE_IPO_REQUEST_BUSINESS_AREA = "service.core.updateIPORequest.businessArea";
    public static final String CORE_UPDATE_IPO_REQUEST_BUSINESS_DOMAIN = "service.core.updateIPORequest.businessDomain";
    public static final String CORE_UPDATE_IPO_REQUEST_OPERATION_NAME = "service.core.updateIPORequest.operationName";
    public static final String CORE_UPDATE_IPO_REQUEST_SERVICE_DOMAIN = "service.core.updateIPORequest.serviceDomain";
    public static final String CORE_UPDATE_IPO_REQUEST_SERVICE_ID = "service.core.updateIPORequest.serviceId";

    // release hold of fund
    public static final String CORE_RELEASE_HOLD_OF_FUND = "service.core.releaseHoldOfFund";
    public static final String CORE_RELEASE_HOLD_OF_FUND_BUSINESS_AREA = "service.core.releaseHoldOfFund.businessArea";
    public static final String CORE_RELEASE_HOLD_OF_FUND_BUSINESS_DOMAIN = "service.core.releaseHoldOfFund.businessDomain";
    public static final String CORE_RELEASE_HOLD_OF_FUND_OPERATION_NAME = "service.core.releaseHoldOfFund.operationName";
    public static final String CORE_RELEASE_HOLD_OF_FUND_SERVICE_DOMAIN = "service.core.releaseHoldOfFund.serviceDomain";
    public static final String CORE_RELEASE_HOLD_OF_FUND_SERVICE_ID = "service.core.releaseHoldOfFund.serviceId";

    // create swift transfer
    public static final String CORE_CREATE_SWIFT_TRANSFER_ = "service.core.createSwiftTransfer";
    public static final String CORE_CREATE_SWIFT_TRANSFER_BUSINESS_AREA = "service.core.createSwiftTransfer.businessArea";
    public static final String CORE_CREATE_SWIFT_TRANSFER_BUSINESS_DOMAIN = "service.core.createSwiftTransfer.businessDomain";
    public static final String CORE_CREATE_SWIFT_TRANSFER_OPERATION_NAME = "service.core.createSwiftTransfer.operationName";
    public static final String CORE_CREATE_SWIFT_TRANSFER_SERVICE_DOMAIN = "service.core.createSwiftTransfer.serviceDomain";
    public static final String CORE_CREATE_SWIFT_TRANSFER_SERVICE_ID = "service.core.createSwiftTransfer.serviceId";

    // Return the IPO List
    public static final String CORE_CHARGE_DETAILS = "service.core.chargesDetails";
    public static final String CORE_CHARGE_DETAILS_BUSINESS_AREA = "service.core.chargesDetails.businessArea";
    public static final String CORE_CHARGE_DETAILS_BUSINESS_DOMAIN = "service.core.chargesDetails.businessDomain";
    public static final String CORE_CHARGE_DETAILS_SERVICE_DOMAIN = "service.core.chargesDetails.serviceDomain";
    public static final String CORE_CHARGE_DETAILS_OPERATION_NAME = "service.core.chargesDetails.operationName";
    public static final String CORE_CHARGE_DETAILS_SERVICE_ID = "service.core.chargesDetails.serviceId";

    public static String graphicalViewStatusColorCode(String statusCode)
    {
	String bgColor = null;
	switch (statusCode)
	{
	    case STATUS_NEW:
		bgColor = getStatusGreenColor();
		break;
	    case STATUS_INACTIVE:
		bgColor = getStatuslightBlueColor();
		break;
	    case STATUS_PENDING:
		bgColor = getStatusOrangeColor();
		break;
	    case STATUS_TO_BE_DELETED:
		bgColor = getStatusRedColor();
		break;
	    case STATUS_APPROVED:
		bgColor = getStatusBlueColor();
		break;
	    case STATUS_REJECTED:
		bgColor = getStatusRedColor();
		break;
	    case STATUS_DELETED:
		bgColor = getStatusRedColor();
		break;
	    default:
		bgColor = getStatusOrangeColor();
		break;
	}
	return bgColor;
    }

    public static String getStatusRedColor()
    {
	return "rgb(255,85,85)";
    }

    public static String getStatusBlueColor()

    {
	return "rgb(38,148,232)";
    }

    public static String getStatusGreenColor()
    {
	return "rgb(0,191,0)";
    }

    public static String getStatuslightBlueColor()
    {
	return "rgb(107,182,239)";
    }

    public static String getStatusOrangeColor()
    {
	return "rgb(232,122,38)";
    }

    /**
     * @author mohammadshour
     */
    public static final String equalOperation = "eq";
    public static final String inOperation = "in";
    public static final String greaterThanOperation = "gt";
    public static final String lessThanOperation = "lt";
    public static final String notEqualOperation = "ne";
    public static final String yesStatus = "Y";
    public static final String lessThanOrEqualOperation = "le";
    public static final String greaterThanOrEqualOperation = "ge";
    public static final String containOperation = "cn";
    //850487
    public static final String TRANSACTION_TYPE_A = "A";
    public static final String TRANSACTION_TYPE_C = "C";
    public static final String TRANSACTION_TYPE_W = "W";
    public static final String TRANSACTION_TYPE_Q = "Q";
    public static final String MESSAGE_TYPE_PARAM_CODE = "1111";
    //894958 issue#2
    public static final BigDecimal MESSAGES_TYPE_TABLE_CODE = new BigDecimal(3);
    //898901 Block Reasons
    public static final String INVALID_CAPTCHA_OTP = "InvalidCaptchaOtp";
    public static final String EXPIRED_SMS_OTP = "ExpiredSmsOtp";
    public static final String EXPIRED_EMAIL_OTP = "ExpiredEmailOtp";
    public static final String INVALID_SMS_OTP = "InvalidSmsOtp";
    public static final String INVALID_EMAIL_OTP = "InvalidEmailOtp";
    public static final String INVALID_PASSWORD = "InvalidPassword";

    /* Muhammad.Asif */
    public static final String USER_GUIDE_DISPLAY_CUSTOMER_YN_Y = "Y";

    /* Vishnu Ponnan For forgot Password */
    public static final String FORGOT_PASSWORD_FLAG = "FP";
    public static final String FORGOT_USERNAME_FLAG = "Fu";
    public static final String SYS_USER = "SYS";
    public static final String DEFAULT_YN_Y = "Y";
    public static final int TWO = 2;
    public static final String LASER_PIN_VAL = "3";
    public static final String SMS_VAL = "2";
    public static final String PIN_MAILER_VAL = "1";
    public static final String MULTI_APPROVE_PROG_REF = "OADM1022";
    // Hisham Omar
    public static final String NOT_AVAILABLE = "not available";
    public static final String PASSPORT_ID_TYPE_CODE = "3";

    /**
     * @author Rakesh
     */
    public static final String SUCCESSFULLY_ACCOUNT_DEACTIVE = "Account Succesufully Deactivated";
    public static final String FAIL_TO_ACCOUNT_DEACTIVE = "Sorry Your account is not Deactivated";
    public static final String STATUS_DEACTIVATED = "DE";

    public static final String PAYMENT_FROM_BRANCH = "B";
    public static final String PAYMENT_FROM_ONLINE = "O";

    public static final String TURN_CORE_AUTO_APPROVE = "1";
    public static final String TURN_OFF_CORE_AUTO_APPROVE = "0";

    /**
     * @author Suhail.Shoukathali
     */
    public static final String CIF_FILTER_STATUS_ACTIVE = "Active";
    public static final String ACCOUNT_FILTER_STATUS_ACTIVE = "Active";
    public static final String ACCOUNT_FILTER_STATUS_INACTIVE = "Inactive";

    /* Muhammad.Asif */
    public static final BigDecimal PIN_MIN_VALUE = new BigDecimal(4);
    public static final BigDecimal PIN_MAX_VALUE = new BigDecimal(10);

    /**
     * @author alialmoussawi #708454 Comments on delivery_settings_key screen
     */
    public static final BigDecimal PASSWORD_MIN_VAL = new BigDecimal(6);
    public static final BigDecimal PASSWORD_MAX_VAL = new BigDecimal(15);
    /** END */
    /**
     * @author Neena.David Bug - 708468
     */
    public static final BigDecimal LASERPIN_MIN_VAL = new BigDecimal(1);
    public static final BigDecimal LASERPIN_MAX_VAL = new BigDecimal(7);
    public static final BigDecimal LASERPINFONT_MIN_VAL = new BigDecimal(8);
    public static final BigDecimal LASERPINFONT_MAX_VAL = new BigDecimal(36);

    /** @author alialmoussawi #708465 Comments on management_key screen */
    public static final BigDecimal TRACK_HIS_PASSWORD_MIN_VAL = new BigDecimal(0);
    public static final BigDecimal TRACK_HIS_PASSWORD_MAX_VAL = new BigDecimal(12);
    public static final BigDecimal MANAGEMENT_PASSWORD_MIN_MIN_VAL = new BigDecimal(6);
    public static final BigDecimal MANAGEMENT_PASSWORD_MIN_MAX_VAL = new BigDecimal(8);
    public static final BigDecimal MANAGEMENT_PASSWORD_MAX_MIN_VAL = new BigDecimal(8);
    public static final BigDecimal MANAGEMENT_PASSWORD_MAX_MAX_VAL = new BigDecimal(15);
    /** END */

    /**
     * @author alialmoussawi #827499 The field "Maximum Number of last
     *         transaction" should be limited
     */
    public static final BigDecimal MIN_NUMBER_OF_LAST_TRANSACTION = new BigDecimal(1);
    public static final BigDecimal MAX_NUMBER_OF_LAST_TRANSACTION = new BigDecimal(15);
    /** END */

    public static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    /**
     * @author Suhail.Shoukathali
     */
    public static final String SUSPEND_REASON_CHANGE_PASSWORD = "Change Password";
    public static final String SUSPEND_REASON_CHANGE_PIN = "Change Pin";
    /**
     * @author Muhammad.Asif
     */
    public static final String LOV_TYPE_ORDER_BY_LOV_ORDER = "ORDER";

    public static final String RECORD_ACTION_UNBLOCK = "unBlock";
    public static final String RECORD_ACTION_APPROVE_TO_BE_UNBLOCK = "approveToBeUnblock";
    public static final String RECORD_ACTION_REJECT_TO_BE_UNBLOCK = "rejectToBeUnblock";
    public static final String STATUS_TO_BE_UNBLOCK = "TUB";

    public static final String REFERENCE_UNBLOCK = "UB";
    public static final String REFERENCE_APPROVE_TO_BE_UNBLOCK = "AUB";
    public static final String REFERENCE_REJECT_TO_BE_UNBLOCK = "RUB";
    public static final String INVALID_LOGIN = "InvalidLogin";
    public static final String INVALID_PIN = "InvalidPin";

    public static final BigDecimal AUTH_MATRIX_STATUS_LOV_TYPE_ID = new BigDecimal(1681);

    // TP 821683
    public static final BigDecimal OnlineRegistrationOperId = BigDecimal.valueOf(863);
    public static final BigDecimal ForgotPasswordOperId = BigDecimal.valueOf(1465);
    public static final BigDecimal ForgotUsernameOperId = BigDecimal.valueOf(1466);
    public static final BigDecimal IpoRequestOperId = BigDecimal.valueOf(1463);

    public static final String SUCCESS_STATUS_CODE = "0";
    public static final String ACCOUNTS_STATUS_CODE = "STATUS_CODE";
    
    
    //TP 818538 ALERT MESSAGE TYPE - OADM
    public static final String SYSTEM_SERVICE_MESSAGE_TYPE  = "S";
    public static final String MESSAGE_TYPE_ADMIN_KEY = "message_type_admin";
    public static final String MESSAGE_TYPE_SYSTEM_SERVICE_KEY = "message_type_system_service";
    public static final String MESSAGE_TYPE_CUSTOMER = "message_type_customer";
    
    public static final String SEND_EAMIL_EVENT_ID = "278";
    //868482
    public static final String H = "Requested";
    public static final String P = "Processed";
    public static final String S = "Approved";
    public static final String X = "Renewed ";
    
    // Constant to specify whether to Enable or Disable Cache on Omni
    public static final int ENABLE_CACHE = 0;
    
    public static final String FINANCIAL_FACILITY_REQUEST_CODE = "FFR";
    public static final String ACTIVITY_HISTORY_REGX = "\\$\\{value([0-9]+)\\}";
   
    public static final String PERSONAL_INFO_TYPE = "1103";
    public static final String MARITAL_STATUS_LOV_TYPE_ID = "36";
    public static final BigDecimal LOGOUT_OPER_ID = new BigDecimal(1559);
    
    public static final Integer WARNING_ERROR_ID = 1;
    public static final String SUCCESS = "Success";
    public static final String FAILURE_OUTPUT_TYPE = "F";
    public static final String INFORMATION_OUTPUT_TYPE = "I";
    public static final String SUCCESS_OUTPUT_TYPE = "S";
    public static final String FATAL_OUTPUT_TYPE = "E";
    public static final Integer SUCCESS_OUTPUT_CODE = 0;
    public static final String SUCCESS_OUTPUT_DESC = "Success";
    public static final Integer EXCEPTION_OUTPUT_CODE = -1;
    public static final String EXCEPTION_OUTPUT_DESC = "Exception";
    public static final Integer MISSING_VALUES_OUTPUT_CODE = -2;
    public static final String PATH_SEPARATOR = "\\";
    public static final Integer DISABLED_FLAG = 0;
    public static final Integer ENABLED_FLAG = 1;
    
    public static final String  SEC_ERR_OUTPUT_TYPE = "SEC_ERR";
    public static final Integer SEC_ERR_OUTPUT_CODE = -3;
    public static final String  SEC_ERR_OUTPUT_DESC = "Security Error";
   
    public static final String BASE64_DATA_IMAGE_STRING = "data:image/png;base64, ";
    
    public static final String PASSWORD_REQUEST_TYPE = "password";
    public static final String PIN_REQUEST_TYPE = "pin";
    public static final String SECURITY_ANSWER_REQUEST_TYPE = "securityAnswer";
    public static final String USER_NAME_REQUEST_TYPE = "userName";
    
    public static final String PASS = "PASS";
    public static final String PIN = "PIN";
    public static final String INVALID_CAPTCHA_OPT = "InvalidCaptchaOtp";
    public static final String OLD_PASSWORD = "Old Password";
   
    public static final String MAP_TYPE_ATM = "A";
    public static final String MAP_TYPE_BRANCH = "B";
    public static final String MAP_TYPE_ATM_DESC = "ATM";
    public static final String MAP_TYPE_BRANCH_DESC = "BRANCH";
    public static final String MAP_TYPE_CDM_DESC = "CDM";
    
    public static final String ACTION_TYPE_CHANGE="C";
    public static final String ACTION_TYPE_FORGOT="F";
    public static final String INVALID_IDENTIFICATION_DETAILS="Invalid identification details. Contact our Call Center";
    public static final String INVALID_AUTHENTICATION_DETAILS="Invalid authentication details. Contact our Call Center";
    public static final String CIF_IS_LINKED_TO_ANOTHER_ACTIVE_USER= "CIF is linked to another active user";
    public static final String EMAIL_ALREADY_REGISTERED_WITH_ANOTHER_USER= "Email already registered with another user";
    public static final String CIF_NOT_ALLOWED = "CIF not allowed";
    public static final String CIF_NOT_FOUND = "CIF not found";
    public static final String CIF_NOT_ACTIVE 	= "CIF not active";
    public static final String ACCOUNT_NOT_ACTIVE 	= "Account not active";
    public static final String INVALID_ACCOUNT_NUMBER = "Invalid account number";
    public static final String CIF_AND_ACCOUNT_ARE_NOT_RELATED = "CIF and Account not related";
    public static final String INVALID_CARD_NUMBER = "Invalid card number";
    public static final String INVALID_ID_TYPE_OR_NUMBER = "Invalid Id Type/Number";
    public static final String WRONG_EXPIRY_DATE = "Wrong Expiry Date";
    public static final String WRONG_BIRTH_DATE = "Wrong Date of Birthday";
    public static final String WRONG_MOBILE_NUMBER = "Wrong Mobile Number";
    public static final BigDecimal HOME_PAGE_OPER = new BigDecimal(19);
    public static final String PASSWORD_PREFIX = "Password";
    public static final String PIN_PREFIX = "Pin";
    public static final String SECURITY_QUESTION_TYPE = "SECURITY";
    public static final String SECURITY_QUESTION_PREFIX = "SecurityQuestion";
    public static final String ACCOUNT_NOT_ACTIVE_OR_INACTIVE 	= "Account not active or inactive";
    
    
    public static final BigDecimal OMNI_REPORT_SOURCE_ID = new BigDecimal(1);
    //reporting parameters:
    public static final String REPORTING_COMPCODE = "RA_COMP";
    public static final String REPORTING_CIF = "RA_CIF";
    public static final String REPORTING_USER_ID = "RA_USR_ID";
    public static final String REPORTING_LATIN_LANGUAGE = "L";
    public static final String REPORTING_ARABIC_LANGUAGE = "A";
    public static final String REPORTING_BOTH_LANGUAGE = "B";
    public static final String DEFAULT_HASHING_ALGO = "9";
    public static final String ALERT_SMS_TAG_EVENT = "USER_NAME";
    
    //added by Mohammad shour for alert services
    public static final String ALERT_USERNAME_EVENT_KEY 				= "userName";
    public static final String ALERT_BANK_PHONE_NUMBER_EVENT_KEY 	= "BankPhoneNumber"; /*should be verified*/
    public static final String ALERT_REQUEST_DATE_EVENT_KEY 		= "requestDate";
    public static final String ALERT_REQUEST_DATE_TIME_EVENT_KEY 	= "requestDateTime";
    public static final String ALERT_CIF_NAME_EVENT_KEY 		= "cifName";
    public static final String ALERT_OTP_EVENT_KEY 			= "OTP";
    public static final String ALERT_BLOCK_REASON_EVENT_KEY = "blockReason";
    public static final String ALERT_CORE_REF_EVENT_KEY 		= "coreReference";
    public static final String APP_ID_EVENT_KEY 				= "appId";
    public static final String CHANNEL_ID_EVENT_KEY 			= "channelId";
    public static final String ALERT_PASSWORD_EVENT_KEY 		= "password";
    public static final String ALERT_PIN_EVENT_KEY 		= "pin";

    public static final String ALERT_SUCCESSFULL_LOGIN_SERVICE_NAME 	= "Successful Login";
    public static final String ALERT_SEND_OTP_SERVICE_NAME 				= "Send OTP";
    public static final String ALERT_RESEND_USERNAME_SERVICE_NAME 		= "Resend Username";
    public static final String ALERT_FAILED_LOGIN_SERVICE_NAME 			= "Failed Login";
    public static final String ALERT_ONLINE_REG_AUTO_APRV_SERVICE_NAME 	= "Online Registration  (auto-approved)";
    public static final String ALERT_ONLINE_REG_NOT_APRV_SERVICE_NAME 	= "Online Registration  (not approved)";
    public static final String ALERT_LOGOUT_SERVICE_NAME 					= "Logout Alert";
    public static final String ALERT_FORCE_LOGOUT_SERVICE_NAME 			= "Force Logout";
    public static final String ALERT_BLOCK_END_USER_SERVICE_NAME 			= "Block end-user";
    
    public static final String ALLOWED_ACCOUNT_TYPE_PARAM		= "AllowedAccountType";
    public static final String ALLOWED_ACCOUNT_TYPE_FROM_PARAM		= "AllowedAccountTypeFrom";
    public static final String ALLOWED_ACCOUNT_TYPE_TO_PARAM			= "AllowedAccountTypeTo";
    public static final String ALLOWED_TYPE_FROM 							=  "from";
    public static final String ALLOWED_TYPE_TO								=  "to";
    public static final String ALLOWED_BRANCHES_PARAM 					= "allowedBranches";
    public static final String ALLOWED_CURRENCIES_PARAM 					= "AllowedCurrencies";
    public static final String ALLOWED_CURRENCIES_TO_PARAM 				= "AllowedCurrenciesTo";
    public static final String ALLOWED_CHEQUE_BOOK_TYPE_PARAM 			= "AllowedChequebookTypes";
    public static final String ALLOWED_CARD_TYPE_PARAM 					= "AllowedCardTypes";
    public static final String CARD_TYPE_COLUMN								= "CTSCARDSVO.ctsCardType";
    public static final String CREDIT_TYPE_PARAM							= "CreditType";
    public static final String CREDIT_CARD_TYPE_VALUE						= "Credit";
    public static final String DEBIT_CARD_TYPE_VALUE						= "Debit";
    public static final String CARD_STATUS_COLUMN							= "statusCode";
    public static final String BILL_TYPE_COLUMN								= "BILL_TYPE";
    public static final String ALLOWED_LIEN_INFO_LIST_PARAM 			= "LienInformationList";
    public static final String ALLOWED_CREDIT_FACILITY_TYPE_PARAM 	= "CreditFacilityTypes";
    public static final String ALLOWED_CIF_TYPES_PARAM 					= "AllowedCIFTypes";
    public static final String ALLOWED_PRODUCT_TYPES_PARAM 				= "ProductTypes";
    public static final String TELLER_USER_ID_PARAM 						= "TellerUserId";
    public static final String ECONOMIC_SECTORS_PARAM 						= "EconomicSectors";
    //bug 940638
    public static final String ALLOWED_ID_TYPES								= "AllowedIDTypes";
    public static final String TRANSACTION_TYPE								= "TransactionType";
    public static final String ALLOWED_DOCUMENT_TYPE						= "AllowedDocumentType";
    public static final String ALLOWED_LANGUAGES							= "AllowedLanguage";
    public static final String ALLOWED_LANGUAGES_LOGIN					= "AllowedLanguageLogin";
    public static final String ALLOWED_CURRENCIES_COMBINATIONS_PARAM = "CurrenciesCombinations";
    public static final String GENERAL_ACCOUNT_OPENING_BRANCHE_PARAM	= "AcntOpenBranchesGenAcc";
    public static final String FM_ACCOUNT_OPENING_BRANCHE_PARAM		= "AcntOpenBranchesMatAcc";
    public static final String CARDS_REQUEST_DESTINATION_BRANCH_PARAM= "RequestedCardsDestinationBranch";
    public static final String LOST_DOC_ALLOWED_REASONS_PARAM			= "LostDocumentsAllowedReasons";
    public static final String FM_ACCOUNT_OPENING_TRX_TYPE				= "AccountOpeningTRXType";
    public static final String MAXIMUM_INVALID_LOGIN_PARAM				= "MaxNumberOfInvalidLogin";
    public static final String PASSWORD_ATTEMPTS_PARAM					= "Password";
    public static final String ALLOWED_CREDIT_FACILITY_TYPES_PARAM	= "AllowedCreditFacilityTypes";
    public static final String ALLOWED_FACILITY_CURRENCY_PARAM			= "AllowedCurrencies";
    public static final String FACILITY_REQUEST_BRANCH_PARAM			= "FacilityRequestBranch";
    public static final String CREDIT_FACILITY_REQUEST_TYPE_PARAM		= "CreditFacilityRequestType";
    public static final String LOST_DOC_REQUEST_BRANCH_PARAM			= "LostDocumentsRequestBranch";
    public static final String REQUEST_BRANCH_PARAM						= "RequestBranch";
    public static final String ACCOUNT_FORMAT_PARAM						= "AccountsAsIBAN";
    public static final String DEFAULT_DATE_FORMAT_PARAM					= "DefaultDateFormat";
    public static final String DEFAULT_DATE_TIME_FORMAT_PARAM			= "DefaultDateTimeFormat";
    public static final String ALLOWED_DEBIT_CARDS_STATUSES_PARAM		= "DebitCardsStatus";
    public static final String ALLOWED_CREDIT_CARDS_STATUSES_PARAM	= "CreditCardsStatus";

    public static final BigDecimal DEFAULT_DATE_TIME_FORMAT_LOV_TYPE_ID = new BigDecimal(1029);
    public static final String ALLOWED_ACCOUNT_STATUSES_PARAM		= "AccountStatuses";
    public static final String ALLOWED_STATUSES_PARAM					= "AllowedStatuses";
    
    public static final String ENABLED_LOCAL_BENEFICIARY_APPROVAL	= "EnableLocalBeneficiaryApproval";
    public static final String ENABLED_INTERNATION_BENEFICIARY_APPROVAL		= "EnableInternationalBeneficiaryApproval";
    
    public static final String OTP_PERIODICITY_PARAM			= "OTPPeriodicity";
    public static final String OTP_PERIODICITY_TIME_PARAM		= "OTPPeriodicityTime";
    public static final String SYSTEM_HASHING_ALGO_PARAM		= "SystemHashingAlgorithm";
    public static final String OTP_GENERATION_LENGTH			= "OTPGenerationLength";
    public static final String OTP_ALLOWED_CHARACTERS			= "OTPAllowedCharacters";
    
    public static final String ALLOWED_BILL_TYPE_PARAM			= "AllowedBillType";
    public static final String ALLOWED_COMPANIES_PARAM			= "AllowedCompanies";
    
    public static final String GENERAL_ACCOUNT_TYPE  		    	= "G";
    public static final String FIXED_MATURITY_ACCOUNT_TYPE  		= "F";
    public static final BigDecimal CARDS_LIST_MAPPING_ID = new BigDecimal(11);
    public static final BigDecimal CIF_DETAILS_MAPPING_ID = new BigDecimal(9);
    public static final BigDecimal REPORTS_MAPPING_ID = new BigDecimal(10);
    public static final BigDecimal GENERAL_ACCOUNTS_LIST_MAPPING_ID = new BigDecimal(12);
    public static final BigDecimal FIXED_MATURITY_ACCOUNTS_LIST_MAPPING_ID = new BigDecimal(13);
    public static final BigDecimal GENERAL_ACCOUNT_INFORMATION_MAPPING_ID = new BigDecimal(15);
    public static final BigDecimal FIXED_MATURITY_ACCOUNT_INFORMATION_MAPPING_ID = new BigDecimal(16);
    public static final BigDecimal CREATE_TRANSFER_MAPPING_ID 					 = new BigDecimal(24);
    public static final BigDecimal CREATE_SUBSCRIBER_MAPPING_ID 				 = new BigDecimal(29);
    public static final BigDecimal CHEQUE_BOOK_LIST_MAPPING_ID 				 = new BigDecimal(31);
    public static final BigDecimal EXPIRED_SECURITY_LIST_MAPPING_ID 			 = new BigDecimal(32);
    public static final BigDecimal RETURN_SUBSCRIBER_DETAILS_MAPPING_ID 	 = new BigDecimal(39);
    public static final BigDecimal UPDATE_SUBSCRIBER_MAPPING_ID			    = new BigDecimal(40);
    public static final BigDecimal CALCULATE_AMOUNT_CURRENCY_MAPPING_ID 	 = new BigDecimal(22);
    public static final BigDecimal CONVERT_FROM_TO_CURRENCY_MAPPING_ID 	 = new BigDecimal(81);
    public static final BigDecimal INTERNAL_TRANSFER_MAPPING_ID 				 = new BigDecimal(38);
    public static final BigDecimal LOCAL_TRANSFER_MAPPING_ID 					 = new BigDecimal(41);
    public static final BigDecimal EXTERNAL_TRANSFER_MAPPING_ID 				 = new BigDecimal(42);
    public static final BigDecimal SEND_ALRT_NOTIFICATION_MAPPING_ID 		 = new BigDecimal(50);
    public static final BigDecimal CREATE_SINGLE_SUBSCRIPTION_MAPPING_ID	 = new BigDecimal(54);
    public static final BigDecimal STANDING_ORDER_LOCAL_MAPPING_ID 			 = new BigDecimal(45);
    public static final BigDecimal STANDING_ORDER_EXTERTNAL_MAPPING_ID		 = new BigDecimal(46);
    public static final BigDecimal CORE_TRANSACTIONS_LIST_MAPPING_ID 		 = new BigDecimal(52);
    public static final BigDecimal CORE_TRANSACTIONS_DETAIL_MAPPING_ID 	    = new BigDecimal(73);
    public static final BigDecimal CORE_CIF_LIST_MAPPING_ID 					 = new BigDecimal(74);
    public static final BigDecimal CORE_BILLERS_LIST_MAPPING_ID 				 = new BigDecimal(76);
    public static final BigDecimal PORTFOLIO_LIST_MAPPING_ID = new BigDecimal(28);
    public static final BigDecimal REGISTRATION_CHARGES_MAPPING_ID 		= new BigDecimal(78);
    public static final BigDecimal REGISTRATION_CHARGES_OPER 			= new BigDecimal(1320);
    public static final BigDecimal CREATE_BILL_PAYMENT_MAPPING_ID 			 = new BigDecimal(80);
    public static final BigDecimal AMANA_ONLINE_BILL_PMT_OPER 			 = new BigDecimal(1504);
    
    public static final BigDecimal STANDING_ORDER_OWN_ACCOUNTS_OPER 				= new BigDecimal(92);
    public static final BigDecimal STANDING_ORDER_INTERNAL_WITHIN_BANK_ACCOUNTS_OPER = new BigDecimal(94);
    public static final BigDecimal STANDING_ORDER_LOCAL_OPER 						= new BigDecimal(1567);
    public static final BigDecimal STANDING_ORDER_EXTERNAL_OPER 					= new BigDecimal(96);
    public static final BigDecimal STOP_STANDING_ORDER_OPER 					= new BigDecimal(1265);
    public static final BigDecimal DELETE_STANDING_ORDER_OPER 					= new BigDecimal(1575);

    public static final String INVALID_USERNAME  			= "Invalid Username";
    public static final String USER_NOT_FOUND  				= "User not found";
    public static final String DEFAULT_BRANCH_CODE = "1";
    
    public static final BigDecimal DEAL_LIST_MAPPING_ID = new BigDecimal(21);
    public static final BigDecimal DEAL_AGGREGATED_DETAILS_MAPPING_ID = new BigDecimal(35);
    public static final BigDecimal FINANCIAL_CALCULATOR_MAPPING_ID 	= new BigDecimal(23);
    public static final BigDecimal IPO_ELIGIBLE_SECURITY_MAPPING_ID 	= new BigDecimal(33);
    public static final BigDecimal CREATE_IPO_REQUEST_MAPPING_ID 		= new BigDecimal(26);
    public static final BigDecimal HOLDING_RENEWAL_REQUEST_MAPPING_ID 	= new BigDecimal(27);
    public static final BigDecimal SMART_LIST_REQUEST_MAPPING_ID 		= new BigDecimal(36);
    public static final BigDecimal SMART_DETAILS_REQUEST_MAPPING_ID 	= new BigDecimal(37);    
    
    public static final String DATE_FORMAT_DD_MM_YYYY = "ddd/MM/yyyy";
    public static final String DATE_FORMAT_dd_MM_yyyy = "dd/MM/yyyy";
    public static final String PRAY_TIME_FORMAT = "HH:mm";
    public static final String PRAY_TIME_DEFAULT_CALCULATINO_METHOD= "MWL";
    
    public static final String DATE_FORMAT_HIJRI_LONG = "EEEE, d MMMM yyyy G";
    public static final String DATE_FORMAT_HIJRI_MEDIUM= "EEE, d MMM yyyy G";
    public static final String DATE_FORMAT_HIJRI_SHORT = "EEE, dd-MM-yyyy G";
    public static final String DATE_FORMAT_HIJRI_DD_MM_YYYY = "dd-MM-yyyy G";
    public static final String DATE_FORMAT_HIJRI_D_MMM_YYYY_G = "d MMMM yyyy G";
    public static final String USE_ACCOUNT= "1";
    public static final String USE_TO_ACCOUNT= "1";
    
    public static final BigDecimal CORE_SERVICE_COMBINED_DEAL_INFO 	= new BigDecimal(21);
    public static final String CORS_MAX_AGE = "84000";
    public static final String PERMITTED_OPER_LIST = "permittedOperList";
    public static final String SESSION_ID_ATTRIBUTE = "sessionId";
    public static final String PUSH_SERVICE_IDENTIFIER = "/omniCommonPush/";
    
    public static final String SUBMIT_ACTION_TYPE				= "Submit";
    public static final String SAVE_ACTION_TYPE				= "save";
    public static final String SAVE_DRAFT_DELETE_ACTION_TYPE= "saveDelete";
    public static final String APPROVE_ACTION_TYPE				= "approve";
    public static final String REJECT_ACTION_TYPE				= "reject";
    public static final String DELETE_ACTION_TYPE				= "delete";
    public static final String IN				= "IN";
    public static final String OUT				= "OUT";
    
    public static final String PUSH_NOTIFICATION_URL= "https://fcm.googleapis.com/fcm/send";
    public static final String METHOD_POST= "POST";
    public static final String PUSH_NOTIFICATION_AUTH_KEY = "pushNotifAuthorizationKey";
    public static final String OSRV_PROPERTIES = "PathOSRVRemoting.properties";
    public static final String PUSH_NOTIFICATION_SOUND = "default";
    public static final String PUSH_NOTIFICATION_CLICK_ACTION = "FCM_PLUGIN_ACTIVITY";
    public static final String PUSH_NOTIFICATION_ICON = "fcm_push_icon";
    public static final String PUSH_NOTIFICATION_PRIORITY =  "high";
    
    public static final String STATUS_READ =  "R";
    public static final String STATUS_UNREAD =  "U";
    public static final String STATUS_FAILED =  "F"; 
    
    public static final String CURRENCIES_COMBINATIONS_CV_CV = "1"; 
    public static final String CURRENCIES_COMBINATIONS_CV_FC ="2";
    public static final String CURRENCIES_COMBINATIONS_FC_CV ="3";
    public static final String CURRENCIES_COMBINATIONS_FC_FC_SAME="4";
    public static final String CURRENCIES_COMBINATIONS_FC_FC_DIFFERENT="5";
    
    public static final BigDecimal OC_CATEGORIES_TABLE_CODE = new BigDecimal(532);
    public static final BigDecimal OC_SUB_CATEGORIES_TABLE_CODE = new BigDecimal(535);
    public static final BigDecimal FINANCIAL_CALCULATOR_OPER 	= new BigDecimal(61);
    public static final BigDecimal ADD_LOCAL_BENEFICIARY_OPER 	= new BigDecimal(1514);
    public static final BigDecimal ADD_SWIFT_BENEFICIARY_OPER 	= new BigDecimal(868);
    public static final BigDecimal CHEQUEBOOK_REQ_OPER_ID = new BigDecimal(49);
    public static final BigDecimal LOCAL_TRANSFER_REQ_OPER = new BigDecimal(109);   
    
    public static final BigDecimal SERVICES_MENU_OPER_ID 			= new BigDecimal(102);
    public static final BigDecimal ACCOUNT_DEACTIVATION_OPER_ID 	= new BigDecimal(1459); 
    public static final BigDecimal UPDATE_FIXED_MATURITY_ACCOUNT_OPER_ID = new BigDecimal(1574);
    
    public static final BigDecimal SCHEDULED_TRANSFER_LIST_OPER_ID = new BigDecimal(1579);
    
    public static final String PERIODICITY = "periodicity";
    public static final String ITEM_VALUE = "itemValue";
    public static final String PERIODICITY_SINGLE_FUTURE_DATE = "S";
    public static final String PERIODICITY_WEEKLY = "W";
    public static final String PERIODICITY_MONTHLY = "M";    
    public static final HashMap<String, String> TRANSACTION_TYPE_PARAM = new HashMap<String, String>();
    static
    {
    	TRANSACTION_TYPE_PARAM.put(PERIODICITY_SINGLE_FUTURE_DATE	, 	"TransactionType");
    	TRANSACTION_TYPE_PARAM.put(PERIODICITY_WEEKLY				, 	"WeeklyTransactionType");
    	TRANSACTION_TYPE_PARAM.put(PERIODICITY_MONTHLY				, 	"MonthlyTransactionType");
    }
    
    public static final String UDID_WEB_IDENTIFIER = "web_";
    public static final String BANK = "Bank";
    public static final String TO_BENEFICIARY = "toBeneficiary";
    public static final String BENEF_BANK_ID = "benefBankId";
    public static final String BENEF_BRANCH_ID = "benefBranchId";

    public static final String ALLOWED_CHEQUEBOOK_LANGUAGES = "AllowedChequebookLanguage";
    public static final BigDecimal LANGUAGES_LOV_TYPE_ID = new BigDecimal(412); ;
    
    public static final String NULL_PARAMETER_VALUE = "-111";
    
    public static final String CREDENTIALS_PARAMETER_LIST = "'PasswordMinimumLength','PasswordMaximumLength',"
		    + "'PasswordRequireNumericCharacters','PasswordRequireLowerCaseCharacters',"
		    + "'PasswordRequireUpperCaseCharacters','PasswordRequireSpecialCharacters','PasswordRequireNoneConsecutiveCharacters',"
		    + "'PassNotSimilarToUserName',"
		    + "'PinMinimumLength','PinMaximumLength','PinRequireOnlyNumericCharacters','PinRequireNumericCharacters',"
		    + "'PinRequireLowerCaseCharacters','PinRequireUpperCaseCharacters','PinRequireSpecialCharacters','PinRequireNoneConsecutiveCharacters','PinNotSimilarToUserName',"
		    + "'SecurityAnswerMinimumNumberOfCharacters','SecurityAnswerIncludeNumericCharacters',"
		    + "'SecurityAnswerIncludeAlphanumericCharacters','SecurityAnswerIncludeSpecialCharacters',"
		    + "'SecurityAnswerMaximumNumberOfCharacters',"
		    + "'UserIDMinimumLengthUserIDManagement',"
		    + "'UserIDMaximumLengthUserIDManagement',"
		    + "'RequireNumericCharactersUserIDManagement',"
		    + "'RequireAlphabeticCharactersUserIDManagement',"
		    + "'RequireLowerCaseCharactersUserIDManagement',"
		    + "'RequireUpperCaseCharactersUserIDManagement',"
		    + "'RequireSpecialCharactersUserIDManagement',"
		    + "'CheckSpecialCharacters',"
		    + "'PasswordDeliveryChannel',"
		    + "'CustomerPasswordAllowedCharacters',"
		    + "'CustomerPINAllowedCharacters',"
		    + "'SystemHashingAlgorithm',"
		    + "'RequireTransactionPassword',"
		    + "'PasswordLength',"
		    + "'PINLength',"
		    + "'StartWithLetterUserIDManagement'";
    
    public static final String ADMIN_PARAMETER_LIST =    "'" + ALLOWED_ACCOUNT_TYPE_PARAM + "'," +
    													 "'" + ALLOWED_ACCOUNT_TYPE_FROM_PARAM + "'," +
    													 "'" + ALLOWED_ACCOUNT_TYPE_TO_PARAM + "'," +
    													 "'" + ALLOWED_BRANCHES_PARAM + "'," +
    													 "'" + ALLOWED_CURRENCIES_PARAM + "'," +
    													 "'" + ALLOWED_CURRENCIES_TO_PARAM + "'," +
    													 "'" + ALLOWED_CHEQUE_BOOK_TYPE_PARAM + "'," +
    													 "'" + ALLOWED_CARD_TYPE_PARAM + "'," +
    													 "'" + CREDIT_TYPE_PARAM + "'," +
    													 "'" + CARD_TYPE_COLUMN + "'," +
    													 "'" + ALLOWED_LIEN_INFO_LIST_PARAM + "'," +
    													 "'" + ALLOWED_CREDIT_FACILITY_TYPE_PARAM + "'," +
    													 "'" + ALLOWED_CIF_TYPES_PARAM + "'," +
    													 "'" + ALLOWED_PRODUCT_TYPES_PARAM + "'," +
    													 "'" + TELLER_USER_ID_PARAM + "'," +
    													 "'" + ALLOWED_ID_TYPES + "'," +
    													 "'" + TRANSACTION_TYPE + "'," +
    													 "'" + ALLOWED_DOCUMENT_TYPE + "'," +
    													 "'" + ALLOWED_LANGUAGES + "'," +
    													 "'" + ALLOWED_CHEQUEBOOK_LANGUAGES + "'," +
    													 "'" + ALLOWED_CURRENCIES_COMBINATIONS_PARAM + "'," +
    													 "'" + GENERAL_ACCOUNT_OPENING_BRANCHE_PARAM + "'," +
    													 "'" + FM_ACCOUNT_OPENING_BRANCHE_PARAM + "'," +
    													 "'" + LOST_DOC_ALLOWED_REASONS_PARAM + "'," +
    													 "'" + OTP_PERIODICITY_PARAM + "'," +
    													 "'" + OTP_PERIODICITY_TIME_PARAM + "'," +
    													 "'" + TRANSACTION_PASSWORD + "'," +
    													 "'" + SYSTEM_HASHING_ALGO_PARAM + "'," +
    													 "'" + VERIFY_TYPE_EMAIL_OTP + "'," +
    													 "'" + VERIFY_TYPE_SMS_OTP + "'," +
    													 "'" + SMS_OTP+ "'," +
    													 "'" + OTP_GENERATION_LENGTH+ "'," +
    													 "'" + OTP_ALLOWED_CHARACTERS+ "'," +
    													 "'" + SECURITY_QUESTION + "'," +
    													 "'" + MAXIMUM_INVALID_LOGIN_PARAM + "'," +
    													 "'" + PASSWORD_ATTEMPTS_PARAM + "'," +
    													 /*"'" + ALLOWED_FACILITY_CURRENCY_PARAM + "'," +    already defined in ALLOWED_CURRENCIES_PARAM    */
    													 "'" + FACILITY_REQUEST_BRANCH_PARAM + "'," + 
    													 "'" + CREDIT_FACILITY_REQUEST_TYPE_PARAM + "'," + 
    													 "'" + LOST_DOC_REQUEST_BRANCH_PARAM + "'," +
    													 "'" + FM_ACCOUNT_OPENING_TRX_TYPE + "'," +
    													 "'" + DEFAULT_DATE_FORMAT_PARAM + "'," +
    													 "'" + DEFAULT_DATE_TIME_FORMAT_PARAM + "'," +
    													 "'" + ALLOWED_DEBIT_CARDS_STATUSES_PARAM + "'," +
    													 "'" + ALLOWED_CREDIT_CARDS_STATUSES_PARAM + "'," +
    													 "'" + ACCOUNT_FORMAT_PARAM + "'," +
    												     "'" + ALERT_BANK_PHONE_NUMBER_EVENT_KEY + "'," +
    													 "'" + ALLOWED_CREDIT_FACILITY_TYPES_PARAM + "'," + 
    													 "'" + ALLOWED_BILL_TYPE_PARAM + "'," +
    													 "'" + ALLOWED_STATUSES_PARAM + "'," +
    													 "'" + ON_BOARDING_PROFILE_ID_PARAM + "'," +
    													 "'" + ENABLED_LOCAL_BENEFICIARY_APPROVAL + "'," +
    													 "'" + ENABLED_INTERNATION_BENEFICIARY_APPROVAL + "'," +
    													 "'" + RANKING_PARAM + "'," +
    													 "'" + LEGAL_STATUS_PARAM + "'," +
    													 "'" + ALLOWED_COMPANIES_PARAM + "'," +
    													 "'" + ECONOMIC_SECTORS_PARAM  + "'," +
    													 CREDENTIALS_PARAMETER_LIST
    													 ;
    public static final List<BigDecimal> SUBMIT_ACTION_OUTSIDE_ALLOWED_OPERS = Arrays.asList(FINANCIAL_CALCULATOR_OPER,
    																						ON_BOARDING_OPER);
    public static final String CURRENCY = "currency";
    public static final String TRANSATION_AMOUNT = "transactionAmount";
    public static final String SEND_PASSWORD_AND_PIN_EVENT="Send Password and PIN";
    public static final String SEND_PASSWORD_EVENT="Send Password";
    public static final String UPLOADED_FILES_ATTACHMENT		= "uploadedFiles";
    public static final String SELECTED_FILE_DATA_ATTACHMENT	= "selectedFileData";
    public static final String REASON_DETAILS					= "reasonDetails";
    
    public static final String INTERNAL_PASSWORD_DELIVERY_EVENT="Internal Password Delivery";
    public static final String INTERNAL_PIN_DELIVERY_EVENT="Internal PIN Delivery";
    public static final String INTERNAL_PASSWORD_AND_PIN_DELIVERY_EVENT="Internal Password and PIN Delivery";
    
    public static final String RETURN_PARAMETER_VALUES_GETBRANCHCODE= "RETURN_PARAMETER_VALUES_GETBRANCHCODE";
    public static final String RETURN_PARAMETER_VALUES_REQUESTECONTEXT= "RETURN_PARAMETER_VALUES_REQUESTECONTEXT";
    
   
}