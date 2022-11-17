package com.path.bo.common;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

import com.path.lib.common.exception.BOException;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.DateDeserializer;
import com.path.lib.common.util.NumberUtil;
import com.path.lib.common.util.PathPropertyNamingStrategy;
import com.path.lib.common.util.PathPropertyUtil;
import com.path.lib.common.util.SecurityUtils;
import com.path.lib.common.util.SecurityUtilsExt;
import com.path.lib.common.util.StringUtil;
import com.path.lib.log.Log;
import com.path.lib.vo.BaseVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: deniskhaddad
 * 
 *          CommonMethods.java used for Common Methods with No DB Access
 */
public final class CommonMethods
{
    /**
     * private constructor to prevent the class being instantiated since all
     * methods are static
     */
    private CommonMethods()
    {
	Log.getInstance().error("This Class is utility class cannot be instantiated");
    }
    /**
     * method that returns 1 if the current database has the same type as the
     * passed parameter
     * 
     * @param dbParam: parameter to check whether current database is correct
     *            (oracle, sybase)
     * @return :1,0
     * @throws BaseException
     */
    public static int getDBMSType(String dbParam)
    {
	if(ConstantsCommon.ORACLE_DBMS.equals(dbParam))
	{
	    return ConstantsCommon.CURR_DBMS_ORACLE;
	}// for both Syn=base and SQL server the return type is SYABSE
	else if(ConstantsCommon.SYBASE_DBMS.equals(dbParam) || ConstantsCommon.SQLSERVER_DBMS.equals(dbParam))
	{
	    return ConstantsCommon.CURR_DBMS_SYBASE;
	}
	else
	{
	    return 0;
	}
    }

    /**
     * Method Used to Format the Number give Number of Decimal digits
     * Powerbuilder function: addition to f_currency_mask
     * 
     * @param valueToFormat the value to be formated
     * @param cyDec number of digits after decimal
     * @return
     */
    public static String formatCurrency(BigDecimal valueToFormat, BigDecimal cyDec) throws BaseException
    {
	String theFormat = currencyMask(cyDec);
	return NumberUtil.format(valueToFormat, theFormat);
    }

    /**
     * Method Used to return the format given Number of Decimal digits
     * Powerbuilder function: f_currency_mask
     * 
     * @param cyDec number of digits after decimal
     * @return
     */
    public static String currencyMask(BigDecimal cyDec) throws BaseException
    {
	return NumberUtil.currencyMask(cyDec);
    }

    /**
     * corresponds to PB fill function that return a what String with nbTimes
     * 
     * @param what what to return
     * @param nbTimes how many times concatenated
     * @return
     */
    public static String fill(String what, BigDecimal nbTimes)
    {
	return NumberUtil.fill(what, nbTimes);
    }
    
    /**
     * a method that takes a list of maps and returns a string containing the hash code of elements combination
     * appended by the hash code of "_".
     * Requires: toGetHash not null
     * @param map
     * @return
     */
    public static String returnHashCode(List<Map<String, Object>> allRows)
    {
    	StringBuilder hashedCode = new StringBuilder();
    	// loop on all entries of List to get hashCode unique for the list as a whole
    	Map<String, Object> map;
	for(int i = 0; i < allRows.size(); i++)
	{
	    map = allRows.get(i);
	    // iterate over the entries of the map and get the hash code of each
	    // value corresponding to the entry
	    // append the hash code the "_" between each value (used for
	    // uniqueness)
	    for(Map.Entry<String, Object> e : map.entrySet())
	    {

		// to avoid appending the hashcode of "_" at the beginning of
		// the
		// empty hashedcode
		if(hashedCode.length() > 0)
		{
		    hashedCode.append("_");
		}
		if(e.getValue() == null)
		{
		    hashedCode.append("null");
		}
		else
		{
		    hashedCode.append(e.getValue().hashCode());
		}
	    }
	}
    	return hashedCode.toString();
    }

    /****
     * Method for checking the entered value is negative and throwing an
     * exception with
     */
    public static void checkValueNegative(BigDecimal theValue, String fieldNameKey) throws BaseException
    {
	BigDecimal value = NumberUtil.nullToZero(theValue);
	if(value.compareTo(BigDecimal.ZERO) <= 0)
	{
	    if(value.compareTo(ConstantsCommon.EMPTY_BIGDECIMAL_VALUE) == 0)
	    {
		throw new BOException(MessageCodes.INVALID_MISSING, new String[] { fieldNameKey.toString() });
	    }
	    else
	    {
		throw new BOException(MessageCodes.VALUE_CANNOT_BE_EQUAL_OR_LESS_THAN_ZERO,
			new String[] { fieldNameKey },true);
	    }
	}
    }

    /******
     * Method to validate maximum amount
     * 
     * @param value
     * @param fieldNameKey
     * @throws BaseException
     */
    public static void validateMaximumAmount(BigDecimal theEnteredAmount, String fieldNameKey) throws BaseException
    {
	BigDecimal enteredAmount = NumberUtil.nullToZero(theEnteredAmount);
	if(enteredAmount.compareTo(ConstantsCommon.MAXIMUM_AMOUNT) > 0)
	{
	    throw new BOException(MessageCodes.VALUE_MUST_BE_LESS_THAN_9999999999, new String[] { fieldNameKey
		    .toString() });
	}
    }

    /****
     * Method will check the entered amount is valid or not (b/w 0 and
     * 99999999999.999
     * 
     * @param value
     * @param fieldNameKey
     * @throws BaseException
     */
    public static void validateAmount(BigDecimal value, String fieldNameKey) throws BaseException
    {
	checkValueNegative(value, fieldNameKey);
	validateMaximumAmount(value, fieldNameKey);
    }

    /**
     *  Returns amount depending on exchange rate and multiply/divide indicator
     * 
     * 	This is used for all conversions based on exchange rate other than cross rate
     *  and other than the rate that embeds the unit, such as used in Transaction screen.
     *  Converts the FC amount to CV and returns it.
     *  
     *  This function if primarily for conversion from FC to CV, however it can be used
     *  for the opposite, but taking care to invert the multiDivInd and use the appropriate
     *  unit and decimal points.
     *  
     * @param multiDivInd
     * @param fcAmount
     * @param exchRate
     * @param unit
     * @param decimalPoint
     * @return
     * @throws BaseException
     */
    public static BigDecimal multiplyDivideAmount(String theMultiDivInd, BigDecimal fcAmount, BigDecimal exchRate,
	    BigDecimal unit, BigDecimal theDecimalPoint) throws BaseException
    {
	BigDecimal cvAmount = null;
	BigDecimal decimalPoint = theDecimalPoint;
	String     multiDivInd  = theMultiDivInd;
	decimalPoint = NumberUtil.emptyDecimalToZero(decimalPoint);
	if(multiDivInd == null || "".equals(multiDivInd.trim()))
	{
	    multiDivInd = ConstantsCommon.MULTIPLY;
	}
	
	if(!NumberUtil.isEmptyDecimal(fcAmount))
	{
	    if(ConstantsCommon.MULTIPLY.equals(multiDivInd))
	    {
		if(!NumberUtil.isEmptyDecimal(exchRate) && !NumberUtil.isEmptyDecimal(unit) && unit.compareTo(BigDecimal.ZERO) != 0)
		{
		    cvAmount = fcAmount.multiply(exchRate).divide(unit, decimalPoint.intValue(),BigDecimal.ROUND_HALF_UP);
		}
	    }
	    else
	    {
		if(NumberUtil.isEmptyDecimal(unit) || NumberUtil.isEmptyDecimal(exchRate) || exchRate.compareTo(BigDecimal.ZERO) == 0)
		{
		    cvAmount = BigDecimal.ZERO;
		    
		}
		else
		{
		    cvAmount = fcAmount.multiply(unit).divide(exchRate, decimalPoint.intValue(),BigDecimal.ROUND_HALF_UP);
		}
	    }
	}
	return cvAmount;
    }

    /****
     * Method for generating the account number for displaying
     * 
     * @param accountNumber
     * @param fieldIndex (0- Branch ,1 - Currency, 2 - Gl, 3 - Cif, 4 - SlNo)
     * @param fieldValue
     * @return
     */
    public static String generateAccountNumberByField(String theAccountNumber, int fieldIndex, int fieldValue)
	    throws BaseException
    {
	String accountNumber = theAccountNumber;
	if(accountNumber == null || accountNumber.equals(""))
	{
	    accountNumber = "0000-000-000000-00000000-000"; // Branch-Currency-Gl-Cif-SlNo
	}
	int[] maxValues = { 9999, 999, 999999, 99999999, 999 }; // Branch-Currency-Gl-Cif-SlNo
	if(fieldValue > maxValues[fieldIndex])
	{
	    throw new BaseException(MessageCodes.INVALID_ENTRY);
	}
	int[] accountNumberDigit = { 4, 3, 6, 8, 3 }; // Branch-Currency-Gl-Cif-SlNo
	int accountElementIndex = accountNumberDigit[fieldIndex];
	String newAccountElement = String.format("%0" + accountElementIndex + "d", fieldValue);
	StringBuilder accountBuilder = new StringBuilder();
	String[] arrAccountNumber = accountNumber.split("-"); // Branch-Currency-Gl-Cif-SlNo
	for(int i = 0; i < 5; i++)
	{
	    if(i == fieldIndex)
	    {
		accountBuilder.append(newAccountElement);
		accountBuilder.append("-");
		continue;
	    }
	    accountBuilder.append(arrAccountNumber[i]);
	    accountBuilder.append("-");
	}
	accountBuilder.deleteCharAt(accountBuilder.length() - 1);
	return accountBuilder.toString();
    }

    /*****
     * Generate the Account number by passing all or some elements in
     * sequential.
     * 
     * @param accountNumber Existing or null
     * @param arrFieldValues (Branch-Currency-Gl-Cif-SlNo) Eg-: If we pass
     *            arrFieldValues{1,2,3} means arrFieldValues{Branch-Currency-Gl)
     *            and the method will take like this arrFieldValues{1,2,3,0,0}
     * @return
     */
    public static String generateAccountNumber(String theAccountNumber, int... arrFieldValues) throws BaseException
    {
	String accountNumber = theAccountNumber;
	if(accountNumber == null || accountNumber.equals(""))
	{
	    accountNumber = "0000-000-000000-00000000-000"; // Branch-Currency-Gl-Cif-SlNo
	}
	if(arrFieldValues.length <= 0)
	{
	    return accountNumber;
	}
	int[] accountNumberDigit = { 4, 3, 6, 8, 3 }; // Branch-Currency-Gl-Cif-SlNo
	int[] maxValues = { 9999, 999, 999999, 99999999, 999 }; // Branch-Currency-Gl-Cif-SlNo
	StringBuilder accountBuilder = new StringBuilder();
	int arrFieldValueslength = arrFieldValues.length;
	int[] arrFieldValuesTemp = new int[5];
	if(arrFieldValueslength < 5)
	{
	    for(int i = 0; i < 5; i++)
	    {
		if(i <= arrFieldValueslength - 1)
		{
		    arrFieldValuesTemp[i] = arrFieldValues[i] > 0 ? arrFieldValues[i] : 0;
		}
		else
		{
		    arrFieldValuesTemp[i] = 0;
		}
	    }
	}
	else
	{
	    for(int i = 0; i < arrFieldValueslength; i++)
	    {
		arrFieldValuesTemp[i] = arrFieldValues[i] > 0 ? arrFieldValues[i] : 0;
	    }
	}
	for(int i = 0; i < 5; i++)
	{
	    if(arrFieldValuesTemp[i] > maxValues[i])
	    {
		throw new BaseException(MessageCodes.INVALID_ENTRY);
	    }
	    int accountElementIndex = accountNumberDigit[i];
	    String newAccountElement = String.format("%0" + accountElementIndex + "d", arrFieldValuesTemp[i]);
	    accountBuilder.append(newAccountElement);
	    if(i < 4)
	    {		
		accountBuilder.append("-");
	    }
	}
	return accountBuilder.toString();
    }

    /**
     * Return params with ~#~ separator
     * 
     * @author nabilfeghali
     * @param parmeters
     * @return
     */
    public static String generateReportParams(Object... parameters)
    {
	StringBuffer returnedParameters = new StringBuffer(); 
	if(parameters != null && parameters.length > 0)
	{
	    for(int i = 0; i < parameters.length; i++)
	    {

		if(i == 0)
		{
		    returnedParameters.append((parameters[i] == null ? "" : parameters[i].toString()));
		}
		else
		{
		    returnedParameters.append(ConstantsCommon.REPORT_ARGUMENT_SEPARATOR);
		    returnedParameters.append((parameters[i] == null ? "":parameters[i].toString()));
		}
	    }
	}
	return returnedParameters.toString();
    }

    /**
     * This function convert from the preffered language (L,A) to language
     * (EG,AR)
     * 
     * @author nabilfeghali
     * @param preferredLang
     * @return
     */
    public static String returnLanguageFromPreffered(String preferredLang)
    {
	String returnedLanguage = null;
	if(ConstantsCommon.PREFERED_LANG_ARABIC.equals(preferredLang))
	{
	    returnedLanguage = ConstantsCommon.LANGUAGE_ARABIC;
	}
	else if(ConstantsCommon.PREFERED_LANG_LATIN.equals(preferredLang))
	{
	    returnedLanguage = ConstantsCommon.LANGUAGE_ENGLISH;
	}
	return returnedLanguage;
    }

    /**
     * convert from List of Object of a JSON String
     * 
     * @author marwanmaddah
     * @date Jun 25, 2013
     * @return String
     * 
     */
    public static String convertListToJSONString(List<?> lst)
    {
	JSONArray jArr = (JSONArray) JSONSerializer.toJSON(lst);
	return jArr.toString();
    }

    /**
     * returns json object from json string
     * @param objClass
     * @param jsonStr
     * @return
     */
    public static Object returnJsonObjectFromStr(Class<?> objClass, String jsonStr)
    {
	//Changed the JSON parsing method from JSONUTIL to JACKSON to prevent BigDecimal false rounding
	ObjectMapper mapper = new ObjectMapper();
	mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	//added Naming strategy and date format to handle OMNI webservices
	mapper.setPropertyNamingStrategy(PathPropertyNamingStrategy.KEEP_AS_IS);
	Version vers = new Version(0, 0, 0, null);
	SimpleModule module = new SimpleModule("dateModule", vers);
	module.addDeserializer(Date.class, new DateDeserializer());
	mapper.registerModule(module);
	mapper.getSerializationConfig().withDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZ"));
	try
	{
		return mapper.readValue(jsonStr, objClass);
	    
	}
	catch (Exception e) {
	    Log.getInstance().error(e,"[CommonMethods] ERROR returnJsonObjectFromStr");
	}
//	JSONObject jsonFilter = (JSONObject) JSONSerializer.toJSON(jsonStr);
//	// 't' lower case because the morpher methods converts coming date String to lower Case 
//	// this line is to convert the Object coming as JSON to Date Object
//	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd't'HH:mm:ss","yyyy-MM-dd'T'HH:mm:ss"}), true); 
//	return JSONObject.toBean(jsonFilter, objClass);
	return null;
    }
    
    
    /**
     * This function will remove the duplicate messages between currentMessage
     * and newMessage The result will be the concatenation of currentMessage and
     * newMessage
     * 
     * @author nabilfeghali
     * @param currentMessage
     * @param newMessage
     * @return
     */
    public static String removeDuplicateMessage(String theCurrentMessage, String theNewMessage)
    {
	final String messageSeparator = "\r\n";
        
	String currentMessage = StringUtil.nullToEmpty(theCurrentMessage);
	String newMessage     = StringUtil.nullToEmpty(theNewMessage);

	if(newMessage.isEmpty())
	{
	    if(currentMessage.isEmpty())
	    {
		currentMessage = currentMessage.concat(newMessage);
	    }
	    else
	    {
		currentMessage = currentMessage.concat( messageSeparator + newMessage);
	    }
	}
	else
	{
	    String[] messagesArray = newMessage.split(messageSeparator);
	    if(messagesArray != null && messagesArray.length > 0)
	    {
		for(String message : messagesArray)
		{
		    if(!currentMessage.contains(message))
		    {
			currentMessage= currentMessage.concat(messageSeparator + message);
		    }
		}
	    }
	}

	return currentMessage;
    }

    /**
     * This function will check whether the application is belongs to IIS
     * modules or not
     * 
     * @author mvalappil
     * @param appName
     * @return boolean (true /false)
     */
    public static boolean isIisApplication(String appName)
    {
	if(appName.equals(ConstantsCommon.ITRS_APP_NAME) || appName.equals(ConstantsCommon.IIS_APP_NAME)
		|| appName.equals(ConstantsCommon.ICOR_APP_NAME) || appName.equals(ConstantsCommon.IRET_APP_NAME)
		|| appName.equals(ConstantsCommon.LEND_APP_NAME) || appName.equals(ConstantsCommon.LCOR_APP_NAME)

		|| appName.equals(ConstantsCommon.LRET_APP_NAME) || appName.equals(ConstantsCommon.PROV_APP_NAME)

		|| appName.equals(ConstantsCommon.IVRL_APP_NAME) || appName.equals(ConstantsCommon.IMIG_APP_NAME)
		|| appName.equals(ConstantsCommon.IPRC_APP_NAME) || appName.equals(ConstantsCommon.IRVL_APP_NAME))
	{
	    return true;
	}
	return false;
    }
    
    /**
     * Method that fill Map record for validation of Expression with possible
     * parameters that can be provided, used in customization Expression
     * 
     * @return hashMap containing records of dummy data
     * @author HusseinZaraket
     */
    public static Map<String, Object> returnBoolExpressionDummyDataMap()
    {
    	final Map<String, Object> dataForExpr = new HashMap<String, Object>();
    	
    	dataForExpr.put(ConstantsCommon.USER_ID_EXP_VAR, "-1");
	    dataForExpr.put(ConstantsCommon.COMP_CODE_EXP_VAR, BigDecimal.valueOf(-1d));
	    dataForExpr.put(ConstantsCommon.COMP_NAME_EXP_VAR, "dummy");
	    dataForExpr.put(ConstantsCommon.BRANCH_CODE_EXP_VAR, BigDecimal.valueOf(-1d));
	    dataForExpr.put(ConstantsCommon.BRANCH_NAME_EXP_VAR, "dummy");
	    dataForExpr.put(ConstantsCommon.USER_FIRST_NAME_EXP_VAR, "dummy");
	    dataForExpr.put(ConstantsCommon.USER_LAST_NAME_EXP_VAR, "dummy");
	    dataForExpr.put(ConstantsCommon.BASE_CURR_NAME_EXP_VAR, "dummy");
	    dataForExpr.put(ConstantsCommon.IS_USER_TELLER_EXP_VAR, BigDecimal.valueOf(-1d));
	    dataForExpr.put(ConstantsCommon.RUNNING_DATE_VAR, new Date());
        
	    return dataForExpr;
    }

    
    
    /**
     * method return boolean expression syntax for provided expression
     * @param exprSyntax expression to be applied boolean on
     * @return boolean expression syntax
     */
    public static String returnBoolConSyntax(String exprSyntax)
    {
	return "IF(" + exprSyntax + ",1,0)";
    }

    /**
     * Method Called from JSP to return IE version,
     * 
     * @param userAgent user Agent
     * @return null if browser is not IE
     */
    public static String returnIEVersion(String userAgent)
    {
	java.util.regex.Pattern p = java.util.regex.Pattern
		.compile("Trident/.*rv:([0-9]{1,}[\\.0-9]{0,})");
	java.util.regex.Matcher m = p.matcher(userAgent);
	String ieVerStr = null;
	if(m.find())
	{
	    ieVerStr = m.group(1);// IE browse with rv release
	}
	else
	{
	    p = java.util.regex.Pattern.compile("Trident/([0-9]{1,}[\\.0-9]{0,})");
	    m = p.matcher(userAgent);
	    if(m.find())
	    {
		ieVerStr = m.group(1);
		if(ieVerStr != null) // IE Browser since Trident exists
		{
        		// check trident version, > 4 means IE > 8
        		float tridentVer = Float.parseFloat(ieVerStr);
        		if(tridentVer > 4)
        		{
        		    ieVerStr = (tridentVer >= 6 ? ((tridentVer >= 7) ? "11" :"10" ):"9");
        		}
        		else
        		{
        		     ieVerStr = "8"; // IE <=8
        		}
		}
	    }
	    else
	    {
		    p = java.util.regex.Pattern.compile("MSIE ([0-9]{1,}[\\.0-9]{0,})");
		    m = p.matcher(userAgent);
		    if(m.find())
		    {
			ieVerStr = m.group(1);// IE < 11
		    }
	    }
	}
	return ieVerStr;
    }
    
    /**
     * return the specified server type in JVM property, TP 500032 Websphere automatic login Issue
     * @return
     */
    public static String returnServerType()
    {
	String serverType = "TOM";// Tomcat by default if JVM property not defined
	try 
	{
	    serverType = System.getProperty("path.server.type");
	    Log.getInstance().debug("returnServerType server returned from JVM propery path.server.type = "+serverType);
	    if(serverType == null)
	    {
		serverType = "TOM";
	    }
	    serverType = serverType.trim();
	}
	catch (Exception ex) 
	{
	    Log.getInstance().error(ex,"ERROR returnServerType");
	}
	return serverType;
    }

    /**
     * Method Called from JSP to check if device type is mobile,
     * 
     * @param userAgent user Agent
     * @return null if device is not mobile(phone, tablet).
     */
    public static String isMobileDevice(String userAgent)
    {
	java.util.regex.Pattern p = java.util.regex.Pattern
		.compile("/Mobile|iP(hone|od|ad)|Android|BlackBerry|IEMobile|Kindle|NetFront|Silk-Accelerated|(hpw|web)OS|Fennec|Minimo|Opera M(obi|ini)|Blazer|Dolfin|Dolphin|Skyfire|Zune/");
	java.util.regex.Matcher m = p.matcher(userAgent);
	String isMobile = null;
	if(m.find())
	{
	    isMobile = "true";
	}
	return isMobile;
    }
    
    /**
     * this method return true if a given String has the give type 
     * @param String str
     * @param String type
     * @return boolean
     */
    public static boolean checkIfContains(String str, String type) throws BaseException
    {
	boolean contain = false;
	if(ConstantsCommon.PREFERED_LANG_LATIN.equals(type))
	{
	    return StringUtil.isAlpha(str, contain);
	}
	else if(ConstantsCommon.PREFERED_LANG_ARABIC.equals(type))
	{
	    int ascii = 0;
	    int len = str.length();
	    for(int i = 0; i < len; ++i)
	    {
		ascii = str.charAt(i);
		if(ascii < 32 || ascii > 127)
		{
		    return true;
		}
	    }
	}
	else if(ConstantsCommon.SPECIAL_CHARACTERS.equals(type))
	{
	    int ascii = 0;
	    int len = str.length();
	    for(int i = 0; i < len; ++i)
	    {
		ascii = str.charAt(i);
		if((ascii < 65 || (ascii > 90 && ascii < 97) || ascii > 122 && (ascii < 1571 || ascii > 1594)
			&& (ascii < 1601 || ascii > 1610))
			&& (ascii != 1596) && (ascii != 1617) && (ascii != 126) && (ascii != 32))
		{
		    return true;
		}
	    }
	}
	return contain;
    }

    
    /**
     * Method to initialize the group and decimal separator from Property files, as System defaults
     */
    public static void initializeGroupDecSeparators()
    {
	// check if not already initiated
	if(ConstantsCommon.PATH_GROUP_SEPARATOR == null || ConstantsCommon.PATH_DECIMAL_SEPARATOR == null)
	{
	    String decimalSep = ".";
	    String groupSep = ",";
	    try
	    {
		decimalSep = StringUtil.nullEmptyToValue(PathPropertyUtil.returnPathPropertyFromFile("PathRemoting", "path.global.decimal.separator"),decimalSep);
		groupSep = PathPropertyUtil.returnPathPropertyFromFile("PathRemoting", "path.global.group.separator");
		if(groupSep == null)
		{
		    groupSep = ",";
		}
		else
		{
		    if("SPACE".equalsIgnoreCase(groupSep))
		    {
			groupSep = " ";
		    }
		    else
		    {
			groupSep = StringUtil.nullEmptyToValue(groupSep,",");
		    }
		}
		// check that both group and decimal separator do not have same value
		if(groupSep.equalsIgnoreCase(decimalSep))
		{
		    decimalSep = ".";
		    groupSep = ",";
		    Log.getInstance().error("/*******************ERROR***************************/\n\n" +
			    "Error in Reading Property path.global.decimal.separator or path.global.group.separator from PathRemoting.properties " +
			    "Both properties have the same value\n\n/**********************************************/");
		}
	    }
	    catch(Exception e)
	    {
		Log.getInstance().error(e,"Error in Reading Property path.global.decimal.separator or path.global.group.separator from PathRemoting.properties");
	    }
	    // take first character if more than one character specified
	    ConstantsCommon.PATH_GROUP_SEPARATOR = (groupSep.length() > 1 ? groupSep.substring(0, 1) : groupSep);
	    ConstantsCommon.PATH_DECIMAL_SEPARATOR = (decimalSep.length() > 1 ? decimalSep.substring(0, 1) : decimalSep);
	}
    }
    /**
     * method used to create a session token in the session if it is not yet defined
     */
    public static String returnUniqueSessionToken(HttpSession session)
    {
    	return returnUniqueSessionToken(session,false);
    }
    
    /**
     * method used to create a session token in the session , force change flag used to change the token before and after login process
     */
    public static String returnUniqueSessionToken(HttpSession session,boolean forceChange)
    {
	String encryptedUuid = null;
	try
	{
	    String sessionTokenUuid = (String)session.getAttribute(SecurityUtilsExt.SESSION_TOKEN_KEY);
	    if(StringUtil.isNotEmpty(sessionTokenUuid) && !forceChange)
	    {
		encryptedUuid = SecurityUtils.encryptAES(SecurityUtilsExt.returnAlgorithmSessionTokenPwd(), sessionTokenUuid);
	    }
	    else
	    {
		String uuid = UUID.randomUUID().toString();
		encryptedUuid = SecurityUtils.encryptAES(SecurityUtilsExt.returnAlgorithmSessionTokenPwd(), uuid);
		session.setAttribute(SecurityUtilsExt.SESSION_TOKEN_KEY,uuid);
	    }
	}
	catch(Exception e)
	{
	    Log.getInstance().error(e, "[CommonMethods] error in returnUniqueSessionToken ");
	}
	return encryptedUuid;
    }
    
    
    /**
     * method used to create an encryption password and store it in the session if it is not yet defined
     */
    public static String returnEncryptionPassword(HttpSession session)
    {
	if(!ConstantsCommon.SECURITY_ENCRYPTPARAMS_DYNAMIC_PWD)
	{
	    return SecurityUtilsExt.returnAlgorithmCbcNopaddingPwd();
	}
	
	String encryptionPass = null;
	try
	{
	    String sessionEncPwd = (String)session.getAttribute(SecurityUtilsExt.PATH_ENC_PWD);
	    if(StringUtil.isNotEmpty(sessionEncPwd))
	    {
		encryptionPass = sessionEncPwd;
	    }
	    else
	    {
		encryptionPass = RandomStringUtils.randomAlphanumeric(16).toUpperCase();
		session.setAttribute(SecurityUtilsExt.PATH_ENC_PWD,encryptionPass);
	    }
	}
	catch(Exception e)
	{
	    Log.getInstance().error(e, "[CommonMethods] error in returnUniqueSessionToken ");
	}
	return encryptionPass;
    }
    
    /**
     * method used for base 64 encoding of a message 
     * @param message
     * @return
     * @throws BaseException
     */
    public static String encodeBase64(String message)
    {
	try
	{
	    return SecurityUtils.encodeB64(message);
	}
	catch(BaseException e)
	{
	    Log.getInstance().error(e, "[CommonMethods] error in encodeBase64, message= " + message);
	}
	return "";
    }
    
    /**
     * method used for base 64 encoding of a message 
     * @param message
     * @return
     * @throws BaseException
     */
    public static String decodeBase64(String message)
    {
	try
	{
	    return SecurityUtils.decodeB64(message);
	}
	catch(BaseException e)
	{
	    Log.getInstance().error(e, "[CommonMethods] error in decodeBase64, message= " + message);
	}
	return "";
    }
    
    public static String returnF12BlockingEnabled()
    {
	try
	{
	    return Boolean.toString(ConstantsCommon.ONE.equals(StringUtil.nullEmptyToValue(PathPropertyUtil.returnPathPropertyFromFile("PathRemoting","security.blockf12.enabled"),ConstantsCommon.ZERO).trim()));
	    
	}
	catch(Exception e)
	{
	    Log.getInstance().error(e, "[CommonMethods] error in returnF12BlockingEnabled");
	}
	return "";
    }
    
    /**
     * method to return shuffle Enabled
     */
    public static String returnShuffleEnabled() {
    	try 
    	{
			String enabled =  PathPropertyUtil.returnPathPropertyFromFile("PathRemoting","virtual.keyboard.shuffle.enable");
			if(enabled != null)
			{
				return enabled.trim();
			}
		} 
    	catch (Exception e) 
    	{
			Log.getInstance().error(e, "[CommonMethods] returnShuffleEnabled");
		}
    	return "";
    }
    /**
	method used to return  the activex download enabled flag
    */
    public static Boolean returnActivexDownloadEnabled()
    {
	try
	{
	    return ConstantsCommon.ONE.equals(StringUtil.nullEmptyToValue(PathPropertyUtil.returnPathPropertyFromFile("PathRemoting","activex.download.enabled"),ConstantsCommon.ONE).trim());
	    
	}
	catch(Exception e)
	{
	    Log.getInstance().error(e, "[CommonMethods] error in returnActivexDownloadEnabled");
	}
	return false;
    }
    
    
    /**
    	method used to escape string used as input hidden value in jsp.
    */
    public static String escapeHtmlString(String message)
    {
    	return StringEscapeUtils.escapeHtml(message);
    }
    /**
     * function that returns an object extracted from the json string even if
     * the object contains lists.
     * 
     * @author RichardZourob
     * @param objClass
     * @param jsonStr
     * @return
     */
    public static Object returnJsonObjectWithArrFromStr(Class<?> objClass, String jsonStr)
    {
       Object returnObj = new Object();
       try
       {
           returnObj = CommonMethods.returnJsonObjectFromStr(objClass, jsonStr);

           if(objClass != null && objClass.getDeclaredFields() != null)
           {
              Field[] theFields = objClass.getDeclaredFields();
              JSONObject jsonFilter = (JSONObject) JSONSerializer.toJSON(jsonStr);
              for(Field theField : theFields)
              {
                  if(!theField.getType().isPrimitive() && PropertyUtils.isReadable(returnObj, theField.getName()) && jsonFilter.get(theField.getName()) != null)
                  {
                     if((PropertyUtils.getProperty(returnObj, theField.getName()) instanceof List)
                           && jsonStr.indexOf(theField.getName()) > -1)
                     {
                         Type type = theField.getGenericType();
                         if(type instanceof ParameterizedType)
                         {
                           ParameterizedType pt = (ParameterizedType) type;
                           Class<?> objClassN = (Class<?>) pt.getActualTypeArguments()[0];
                           JSONArray jsonModel = jsonFilter.getJSONArray(theField.getName());
                           Object[] modelArr = jsonModel.toArray();
                           List<Object> listObj = new ArrayList<Object>();
                           for(Object model : modelArr)
                           {
                               Object obj = returnJsonObjectWithArrFromStr(objClassN, model.toString());
                               listObj.add(obj);
                           }

                           BeanUtils.setProperty(returnObj, theField.getName(), listObj);
                         }

                     }
                     else if(PropertyUtils.getProperty(returnObj, theField.getName()) instanceof BaseVO)
                     {
                         Object obj = returnJsonObjectWithArrFromStr(
                               PropertyUtils.getProperty(returnObj, theField.getName()).getClass(),
                               jsonFilter.get(theField.getName()).toString());
                         BeanUtils.setProperty(returnObj, theField.getName(), obj);
                     }
                  }
              }
           }
       }
       catch(Exception ex)
       {
           Log.getInstance().error(ex, "[CommonMethods] error in returnJsonObjectWithArrFromStr");
       }
       return returnObj;
    }

    
    public static String returnEncryptedJpassword(String passord) throws Exception
    {
	return SecurityUtils.encryptNoPadding(passord,SecurityUtilsExt.returnAlgorithmCbcNopaddingPwd());
    }
    /**
     * Method that adds the focus element to return Exception
     * @param elemIdOrName Id or Name of the element to Focus
     * @param baseEx Base Exception in which the return Object Map to set
     */
    public static void includeElemFocus(String elemIdOrName, BaseException baseEx)
    {
	if(baseEx != null)
	{
	    Map<String, String> elmntRefMap = new HashMap<String,String>();
	    if(!StringUtil.nullToEmpty(elemIdOrName).isEmpty())
	    {
		elmntRefMap.put("_focusElement", elemIdOrName);
	    }
	    baseEx.setRetValue(elmntRefMap);
	}
    }
    
    /**
     * function that return a url for an automatic login to a specific screen in an application 
     * An example of the provided parameters :
     * 	    String username = "MODEL.B";
     *	    String password = "52c45f59267dafb40a58eb6fc1f65e88";
     *	    BigDecimal loginCompCode = BigDecimal.ONE;
     *	    BigDecimal loginBranchCode = BigDecimal.ONE;
     *	    String language = "EN";
     *	    String runningDate = "05/01/2018";
     *	    String extAppName = "RET";
     *	    String externalScreen = "RRTMT";
     *      String additionalParams = "recordOfRemittanceCO.remittanceRecordVO.VENDOR_NAME=ok&recordOfRemittanceCO.remittanceRecordVO.REFERENCE_NO=111";
     * 	    String destinationScreenUrl = null;
     * And in case we need to open a screen from direct screen url by providing the action of the screen, we need to pass the below additional parameters
     *      String destinationScreenUrl = "/path/recordOfRemittance/RecordOfRemittanceMaint_recordOfRemittanceGridDetail.action";     
     *      String additionalParams = "{\"trsNo\" : \"303\" , \"_pageRef\" : \"RRTMT\" , \"iv_crud\" : \"R\" }";  
     * returned URL will be like http://192.168.27.38:8040/imal_core_portal?PATHPARAM=Wp6Rq8b.... 
     * @throws Exception
     */
    public static String returnExternalScreenLoaderURL(String username, String password, BigDecimal loginCompCode, BigDecimal loginBranchCode, String language , String runningDate, String extAppName, String externalScreen, String additionalParams, String destinationScreenUrl ) throws Exception
    {
	StringBuffer paramBuffer = new StringBuffer();
	
	if(StringUtil.isNotEmpty(language))
	{
	    paramBuffer.append("language=").append(language);
	}
	else
	{
	    paramBuffer.append("language=").append(ConstantsCommon.LANGUAGE_ENGLISH);
	}
	if(StringUtil.isNotEmpty(runningDate))
	{
	    paramBuffer.append("&").append("runningDateRET=").append(runningDate);
	}
	if(StringUtil.isNotEmpty(username))
	{
	    paramBuffer.append("&").append("j_username=").append(username);
	}
	if(StringUtil.isNotEmpty(password))
	{
	    String credentials = CommonMethods.returnEncryptedJpassword(password);
	    paramBuffer.append("&").append("j_password=").append(credentials);
	}
	if(!NumberUtil.isEmptyDecimal(loginCompCode))
	{
	    paramBuffer.append("&").append("login_comp_code=").append(loginCompCode);
	}
	if(!NumberUtil.isEmptyDecimal(loginBranchCode))
	{
	    paramBuffer.append("&").append("login_bra_code=").append(loginBranchCode);
	}
	
	if(StringUtil.isNotEmpty(destinationScreenUrl))
	{
	    	if(StringUtil.isNotEmpty(extAppName))
		{
	    	    paramBuffer.append("&").append("destinationScreenUrl=").append(destinationScreenUrl);
		}
	    	if(StringUtil.isNotEmpty(extAppName))
    		{
	    	    paramBuffer.append("&").append("appName=").append(extAppName);
    		}
    		if(StringUtil.isNotEmpty(externalScreen))
    		{
    		    paramBuffer.append("&").append("destinationProgRef=").append(externalScreen);
    		}
    		
	}
	else
	{    
        	if(StringUtil.isNotEmpty(extAppName))
        	{
        	    paramBuffer.append("&").append("extAppName=").append(extAppName);
        	}
        	if(StringUtil.isNotEmpty(externalScreen))
        	{
        	    paramBuffer.append("&").append("externalScreen=").append(externalScreen);
        	}
       }
	if(StringUtil.isNotEmpty(additionalParams))
	{
	    paramBuffer.append("&").append("additionalParams=").append(URLEncoder.encode(additionalParams,SecurityUtilsExt.DEFAULT_ENCODING));
	}
	
	String encryptedParams = SecurityUtils.returnAutomaticLoginEncryptedParam(paramBuffer.toString());
	
	String extAppURL  = PathPropertyUtil.returnPathPropertyFromFile("PathRemoting.properties", "app."+extAppName+".url");
	if(StringUtil.isNotEmpty(extAppURL))
	{
	    if(extAppURL.endsWith("/"))
	    {
		extAppURL = extAppURL.substring(0, extAppURL.length() - 1);
	    }
	    if("WAS".equals(CommonMethods.returnServerType().toUpperCase()))
	    {
		extAppURL = extAppURL.concat("/path/AutoLoginAction");
	    }
	    extAppURL = extAppURL.concat("?").concat(encryptedParams+"&PARAMPATH=1");
	}
	return extAppURL;
    }
    
    
    /**
     * function used to return the list of network interface IP available on the server
     * @return
     */
    public static List<String> returnNetworkInterfaceIP()
    {
	List<String> resultIpList = null;
	try
	{
	    Enumeration<NetworkInterface> nicEnum = NetworkInterface.getNetworkInterfaces();
	    if(nicEnum != null)
	    {
		resultIpList = new ArrayList<String>();
		while(nicEnum.hasMoreElements())
		{
		    NetworkInterface nicElem = nicEnum.nextElement();

		    // skip loopback or if network is down or if it's a virtual network
		    if(nicElem == null || nicElem.isLoopback() || !nicElem.isUp() || nicElem.isVirtual())
		    {
			continue;
		    }

		    Enumeration<InetAddress> nicInetAddrEnum = nicElem.getInetAddresses();
		    if(nicInetAddrEnum != null)
		    {
			while(nicInetAddrEnum.hasMoreElements())
			{
			    {
				InetAddress inetAddress = nicInetAddrEnum.nextElement();
				if(inetAddress != null)
				{
				    resultIpList.add(inetAddress.getHostAddress());
				}
			    }
			}
		    }

		}
	    }
	}
	catch(Exception e)
	{
	    Log.getInstance().error(e,"[CommonMethods] in returnNetworkInterfaceIP() error returning network interface IP");
	}
	return resultIpList;
    }
    /**
     * @author Alim Khowaja TP 863259 
     * include ConnectionCO to SC required for retrieve data from other db
     * @param baseSC BaseSC object
     * @param jndiName JNDI reference from PathServices to get the connection JNDI name from
     */

}