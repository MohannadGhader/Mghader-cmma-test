package com.path.lib.common.util;

import java.math.BigDecimal;
import java.util.HashMap;

import com.path.bo.common.CommonLibBO;
import com.path.bo.common.ConstantsCommon;
import com.path.bo.common.NumberInWordsConstantsCommon;
import com.path.bo.common.NumberInWordsConstantsCommon.Currency;
import com.path.dbmaps.vo.CURRENCIESVO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.log.Log;
import com.path.vo.common.CurrencyToWordsCO;
public final class NumberToArabic
{
    private final static Log log = Log.getInstance();
    
    private static String englishPrefixText = "";
    private static String englishSuffixText = "only.";
    private static String arabicPrefixText = "";
    private static String arabicSuffixText = "فقط لا غير.";
    /**
     * Private constructor to prevent class from instantiation
     */
    private NumberToArabic()
    {
	Log.getInstance().warning("This class is utility and cannot be instantiated");
    }

    private static String getDecimalValue(String decimalPart,CurrencyInfo currencyInfo)
    {
	StringBuffer result;
	StringBuffer decimalPartBfr = new StringBuffer(decimalPart);
	if(currencyInfo.getPartPrecision() == decimalPartBfr.length())
	{
	    result = decimalPartBfr;
	}
	else
	{
	    int decimalPartLength = decimalPartBfr.length();

	    for(int i = 0; i < currencyInfo.getPartPrecision() - decimalPartLength; i++)
	    {
		decimalPartBfr.append("0"); // Fix for 1 number after decimal ( 10.5 ,1442.2 , 375.4 )
	    }

	    int dec = decimalPartBfr.length() <= currencyInfo.getPartPrecision() ? decimalPartBfr.length() : currencyInfo
		    .getPartPrecision();

	    result = new StringBuffer(decimalPartBfr.substring(0, dec));
	}

	for(int i = result.length(); i < currencyInfo.getPartPrecision(); i++)
	{
	    result.append("0");
	}

	return result.toString();
    }

    private static String processGroup(int groupNumber, CurrencyInfo currencyInfo)
    {
	int tens = groupNumber % 100;

	int hundreds = groupNumber / 100;

	String retVal = "";

	if(hundreds > 0)
	{
	    retVal = String.format("%s %s", currencyInfo.englishOnes[hundreds], currencyInfo.englishGroup[0]);
	}
	if(tens > 0)
	{
	    if(tens < 20)
	    {
		retVal = retVal.concat(((retVal.isEmpty()) ? "" : " ") + currencyInfo.englishOnes[tens]);
	    }
	    else
	    {
		int ones = tens % 10;

		tens = (tens / 10) - 2; // 20's offset

		retVal = retVal.concat(((retVal.isEmpty()) ? "" : " ") + currencyInfo.englishTens[tens]);

		if(ones > 0)
		{
		    retVal = retVal.concat(((retVal.isEmpty()) ? "" : " ") + currencyInfo.englishOnes[ones]);
		}
	    }
	}

	return retVal;
    }

    public static String convertToEnglish(BigDecimal value, String currencyCode)throws BaseException
    {
	return convertToEnglish(value, currencyCode, null,ConstantsCommon.LANGUAGE_ENGLISH);
    }
    public static String convertToEnglish(BigDecimal value, String currencyCode,BigDecimal decDigits)throws BaseException
    {
	return convertToEnglish(value, currencyCode, decDigits,ConstantsCommon.LANGUAGE_ENGLISH);
    }
    public static String convertToEnglish(BigDecimal value, String currencyCode,BigDecimal decDigits, String langCode)throws BaseException
    {
	return convertToEnglish(value, currencyCode, decDigits,ConstantsCommon.LANGUAGE_ENGLISH,null);
    }
    public static String convertToEnglish(BigDecimal value, String currencyCodeParam,BigDecimal decDigits, String langCode, String suffixToAdd)throws BaseException
    {
	String currencyCode = currencyCodeParam;
	if(StringUtil.nullToEmpty(currencyCode).isEmpty())
	{
	    currencyCode = ConstantsCommon.USD_ISO_CODE;
	}
	String theLang = langCode;
	if(StringUtil.nullToEmpty(theLang).isEmpty())
	{
	    theLang = ConstantsCommon.LANGUAGE_ENGLISH;
	}
	//initialize the hashmaps of corresponding numbers in word for this language (values are cached either from defaults or from db overridden values)
	CURRENCIESVO currVO = new CURRENCIESVO();
	currVO.setDECIMAL_POINTS(decDigits);
	currVO.setCY_ISO_CODE(currencyCode);
	CommonLibBO commonLibBO = (CommonLibBO) ApplicationContextProvider.getApplicationContext().getBean("commonLibBO");
	
	CurrencyInfo _currencyInfo;
	CurrencyToWordsCO theCO = commonLibBO.returnCurrencyToWordsCO(theLang, currVO);//(CurrencyToWordsCO)NumberInWordsConstantsCommon.currenciesMap.get(langCode.concat("_").concat(currencyCode));
	//if no data found in hm means record does not exist in db so take the static found in CurrencyInfo
	if(theCO == null)
	{
	    Currency _currency = null;
	    try
	    {
		_currency = Currency.valueOf(currencyCode);
	    }
	    catch(Exception e)
	    {
		_currency = Currency.valueOf(ConstantsCommon.USD_ISO_CODE);
		log.error("Missing Currency definition for "+currencyCode);
	    }
	    _currencyInfo = new CurrencyInfo(_currency);
	    if(decDigits != null)
	    {
		_currencyInfo.setPartPrecision(decDigits.intValue());
	    }
	}
	else
	{
	    if(theCO.getCurrVO().getDECIMAL_POINTS() == null )
	    {
		int partPrecision = 2;
		try
		{
		    Currency _currency = Currency.valueOf(currencyCode);
		    partPrecision = new CurrencyInfo(_currency).getPartPrecision();
		}
		catch(Exception e)
		{
		    //when currency does not exist
		    log.warning("[convertToEnglish]Currency "+currencyCode+" Does not Exists in defined enumiration precision Decimal points of 2 is defaulted");
		}
		theCO.getCurrVO().setDECIMAL_POINTS(new BigDecimal(partPrecision));
	    }
	    _currencyInfo = new CurrencyInfo(theCO);
	}
	
	if(suffixToAdd != null)
	{
	    _currencyInfo.setSuffixToAdd(suffixToAdd);
	}
	
	BigDecimal number = value.setScale(_currencyInfo.getPartPrecision(), BigDecimal.ROUND_HALF_DOWN);

	HashMap<String, String[]> hm = commonLibBO.returnNumInWordsByLangDefaultMap(theLang);// (HashMap<String, String[]>)NumberInWordsConstantsCommon.numInWordsByLangDefaultMap.get(langCode);
	    _currencyInfo.englishOnes = hm.get(NumberInWordsConstantsCommon.ONES_KEY);
	    _currencyInfo.englishTens = hm.get(NumberInWordsConstantsCommon.TENS_KEY);
	    _currencyInfo.englishGroup = hm.get(NumberInWordsConstantsCommon.NUMGROUPS_KEY);
	return convertToEnglish(number,_currencyInfo);
    }

    private static String convertToEnglish(BigDecimal number,CurrencyInfo currencyInfo)
    {
	BigDecimal tempNumber = number;

	if(tempNumber.compareTo(BigDecimal.ZERO) == 0)
	{
	    return "Zero";
	}

	String[] splits = number.toString().replaceAll(",", "").split("\\.");
	long _intergerValue = Long.parseLong(splits[0]);
	int _decimalValue = 0;
	if(splits.length > 1)
	{
	    _decimalValue = Integer.parseInt(getDecimalValue(splits[1],currencyInfo));
	}

	BigDecimal decTempNumber = BigDecimal.valueOf(_decimalValue);
	String decimalString = returnNumberInLetters(decTempNumber,currencyInfo);
	

	String retVal = "";

	if(tempNumber.compareTo(BigDecimal.ZERO) < 1)
	{
	    retVal = currencyInfo.englishOnes[0];
	}
	else
	{
	    retVal = returnNumberInLetters(tempNumber,currencyInfo);
	}
	
	String suffixToAdd = englishSuffixText;
	// check if suffix is provided BMOUPI170483
	if(currencyInfo.getSuffixToAdd() != null)
	{
	    suffixToAdd = currencyInfo.getSuffixToAdd();
	}

	StringBuffer formattedNumber = new StringBuffer();
	formattedNumber.append((englishPrefixText.isEmpty()) ? "" : String.format("%s ", englishPrefixText))
	.append((retVal.isEmpty()) ? "" : retVal)
	.append((retVal.isEmpty()) ? "" : (_intergerValue == 1 ? currencyInfo.englishCurrencyName
		: currencyInfo.englishPluralCurrencyName))
	.append((decimalString.isEmpty()) ? "" : (retVal.isEmpty() ? "" : " and "))
	.append((decimalString.isEmpty()) ? "" : decimalString.trim())
	.append((decimalString.isEmpty()) ? "" : " "+ (_decimalValue == 1 ? currencyInfo.englishCurrencyPartName : currencyInfo.englishPluralCurrencyPartName) )
		.append((suffixToAdd.isEmpty()) ? "" : String.format(" %s", suffixToAdd));

		return formattedNumber.toString();
    }

    /**
     * returns letters construction from given Number
     * @author marwanmaddah
     * @date   Jan 14, 2015
     * @param decTempNumber
     * @return String
     *
     */
    private static String returnNumberInLetters(BigDecimal theValue,CurrencyInfo currencyInfo)
    {
	int group = 0;
	String retVal = "";
	BigDecimal value = theValue;
	// loop on the number and construct its corresponding letters
	while(value.compareTo(BigDecimal.ZERO) > 0)
	{
	    int numberToProcess = value.remainder(BigDecimal.valueOf(1000)).intValue();

	    value = value.divideToIntegralValue(BigDecimal.valueOf(1000));

	    String groupDescription = processGroup(numberToProcess,currencyInfo);

	    if(!groupDescription.isEmpty())
	    {
		if(group > 0)
		{
		    retVal = String.format("%s %s", currencyInfo.englishGroup[group], retVal);
		}

		retVal = String.format("%s %s", groupDescription, retVal);
	    }

	    group++;
	}
	return retVal;
    }

    private static String getDigitFeminineStatus(int digit, int groupLevel,CurrencyInfo currencyInfo)
    {
	if(groupLevel == -1)
	{ // if it is in the decimal part
	    if(currencyInfo.isCurrencyPartNameFeminine)
	    {
		return currencyInfo.arabicFeminineOnes[digit]; // use feminine field
	    }
	    else
	    {
		return currencyInfo.arabicOnes[digit];
	    }
	}
	else if(groupLevel == 0)
	{
	    if(currencyInfo.isCurrencyNameFeminine)
	    {
		return currencyInfo.arabicFeminineOnes[digit];// use feminine field
	    }
	    else
	    {
		return currencyInfo.arabicOnes[digit];
	    }
	}
	else
	{
	    return currencyInfo.arabicOnes[digit];
	}
    }

    private static String processArabicGroup(int groupNumber, int groupLevel, BigDecimal remainingNumber,long _intergerValue,CurrencyInfo currencyInfo)
    {
	int tens = groupNumber % 100;

	int hundreds = groupNumber / 100;

	String retVal = "";

	if(hundreds > 0)
	{
	    if(tens == 0 && hundreds == 2)
	    {
		retVal = String.format("%s", currencyInfo.arabicAppendedTwos[0]);
	    }
	    else
	    {
		// الحالة العادية
		retVal = String.format("%s", currencyInfo.arabicHundreds[hundreds]);
	    }
	}

	if(tens > 0)
	{
	    if(tens < 20)
	    { // if we are processing under 20 numbers
		if(tens == 2 && hundreds == 0 && groupLevel > 0)
		{ // This is special case for number 2 when it comes alone in
		    // the group
		    if(_intergerValue == 2000 || _intergerValue == 2000000 || _intergerValue == 2000000000
			    || _intergerValue == 2000000000000L || _intergerValue == 2000000000000000L
			    || _intergerValue == 2000000000000000000L)
		    {
			retVal = String.format("%s", currencyInfo.arabicAppendedTwos[groupLevel]); // في
			// حالة
			// الاضافة
		    }
		    else
		    {
			retVal = String.format("%s", currencyInfo.arabicTwos[groupLevel]);// في
			// حالة
			// الافراد
		    }
		}
		else
		{ // General case
		    if(!retVal.isEmpty())
		    {
			retVal = retVal.concat(" و ");
		    }

		    if(tens == 1 && groupLevel > 0 && hundreds == 0)
		    {
			retVal = retVal.concat(" ");
		    }
		    else if((tens == 1 || tens == 2) && (groupLevel == 0 || groupLevel == -1) && hundreds == 0
			    && remainingNumber.compareTo(BigDecimal.ZERO) == 0)
		    {
			retVal = retVal.concat(""); // Special case for 1 and 2 numbers like:
			// ليرة سورية و ليرتان سوريتان
		    }
		    else
		    {
			//Get Feminine status for this digit
			retVal = retVal.concat(getDigitFeminineStatus(tens, groupLevel,currencyInfo));
		    }
		}
	    }
	    else
	    {
		int ones = tens % 10;
		tens = (tens / 10) - 2; // 20's offset

		if(ones > 0)
		{
		    if(!retVal.isEmpty())
		    {
			retVal = retVal.concat(" و ");
		    }

		    // Get Feminine status for this digit
		    retVal = retVal.concat(getDigitFeminineStatus(ones, groupLevel,currencyInfo));
		}

		if(!retVal.isEmpty())
		{
		    retVal = retVal.concat(" و ");
		}

		// Get Tens text
		retVal = retVal.concat(currencyInfo.arabicTens[tens]);
	    }
	}

	return retVal;
    }
    /**
     * Method converts number to Arabic Letters, based on Currency ISO decimal points
     * @param value
     * @param currencyCode
     * @return
     */
    public static String convertToArabic(BigDecimal value, String currencyCode)throws BaseException
    {
	return convertToArabic(value,currencyCode,null,ConstantsCommon.LANGUAGE_ARABIC);
    }
    public static String convertToArabic(BigDecimal value, String currencyCode, BigDecimal decDigits)throws BaseException
    {
	return convertToArabic(value,currencyCode,decDigits,ConstantsCommon.LANGUAGE_ARABIC);
    }
    /**
     * Method converts number to Arabic Letters, based on provided decimal points digits
     * @param value the value to convert
     * @param currencyCode Currency ISO Code
     * @param decDigits decimal digit number
     * @return
     */
    public static String convertToArabic(BigDecimal value, String currencyCode, BigDecimal decDigits, String langCode)throws BaseException
    {
	return convertToArabic(value,currencyCode,decDigits,ConstantsCommon.LANGUAGE_ARABIC,null);
    }
    /**
     * Method converts number to Arabic Letters, based on provided decimal points digits
     * @param value the value to convert
     * @param currencyCode Currency ISO Code
     * @param decDigits decimal digit number
     * @param langCode language Code
     * @param addSuffix suffix to add at end of result.
     * @return
     */
    public static String convertToArabic(BigDecimal value, String currencyCodeParam, BigDecimal decDigits, String langCode, String addSuffix)throws BaseException
    {
	String currencyCode = currencyCodeParam;
	if(StringUtil.nullToEmpty(currencyCode).isEmpty())
	{
	    currencyCode = ConstantsCommon.USD_ISO_CODE;
	}
	//initialize the hashmaps of corresponding numbers in word for this language (values are cached either from defaults or from db overridden values)
	CURRENCIESVO currVO = new CURRENCIESVO();
	currVO.setDECIMAL_POINTS(decDigits);
	currVO.setCY_ISO_CODE(currencyCode);
	CommonLibBO commonLibBO = (CommonLibBO) ApplicationContextProvider.getApplicationContext().getBean("commonLibBO");
	
	CurrencyInfo currencyInfo;
	CurrencyToWordsCO theCO = commonLibBO.returnCurrencyToWordsCO(langCode, currVO); //(CurrencyToWordsCO)NumberInWordsConstantsCommon.currenciesMap.get(langCode.concat("_").concat(currencyCode));
	//if no data found for this currency means no record exists in db so take the static found in CurrencyInfo
	if(theCO == null)
	{
	    Currency _currency = null;
	    try
	    {
		_currency = Currency.valueOf(currencyCode);
	    }
	    catch(Exception e)
	    {
		//next step take default from related currency if exists, otherwise from USD
		_currency = Currency.valueOf(ConstantsCommon.USD_ISO_CODE);
		log.error("Missing Currency definition for "+currencyCode);
	    }
	    currencyInfo = new CurrencyInfo(_currency);
	    // update the decimal Digits to the provided one
	    if(decDigits != null)
	    {
		currencyInfo.setPartPrecision(decDigits.intValue());
	    }
	}
	else
	{
	    if(theCO.getCurrVO().getDECIMAL_POINTS() == null )
	    {
		int partPrecision = 2;
		try
		{
		    Currency _currency = Currency.valueOf(currencyCode);
		    partPrecision = new CurrencyInfo(_currency).getPartPrecision();
		}
		catch(Exception e)
		{
		    //when currency does not exist
		    log.warning("[convertToArabic]Currency "+currencyCode+" Does not Exists in defined enumiration precision Decimal points of 2 is defaulted");
		}
		theCO.getCurrVO().setDECIMAL_POINTS(new BigDecimal(partPrecision));
	    }
	    currencyInfo = new CurrencyInfo(theCO);
	}
	
	if(addSuffix != null)
	{
	    currencyInfo.setSuffixToAdd(addSuffix);
	}

	BigDecimal number = value.setScale(currencyInfo.getPartPrecision(), BigDecimal.ROUND_HALF_DOWN);

//	commonLibBO.initializeNumInWordsByLang(langCode,currVO);
	//retrieve arrays of numbers in words from hm
	HashMap<String, String[]> hm =  commonLibBO.returnNumInWordsByLangDefaultMap(langCode);//(HashMap<String, String[]>)NumberInWordsConstantsCommon.numInWordsByLangDefaultMap.get(langCode);
	currencyInfo.arabicOnes = hm.get(NumberInWordsConstantsCommon.ONES_KEY);
	currencyInfo.arabicTens = hm.get(NumberInWordsConstantsCommon.TENS_KEY);
	currencyInfo.arabicGroup = hm.get(NumberInWordsConstantsCommon.NUMGROUPS_KEY);
	currencyInfo.arabicTwos = hm.get(NumberInWordsConstantsCommon.TWOS_KEY);
	currencyInfo.arabicAppendedTwos = hm.get(NumberInWordsConstantsCommon.APPENDED_TWOS_KEY);
	currencyInfo.arabicAppendedGroup = hm.get(NumberInWordsConstantsCommon.APPENDED_GROUPS_KEY);
	currencyInfo.arabicHundreds = hm.get(NumberInWordsConstantsCommon.HUNDREDS_KEY);
	currencyInfo.arabicFeminineOnes = hm.get(NumberInWordsConstantsCommon.FEMININE_ONES_KEY);
	currencyInfo.arabicPluralGroups = hm.get(NumberInWordsConstantsCommon.PLURALGROUPS_KEY);
	return convertToArabic(number,currencyInfo);
    }

    public static String convertToArabic(BigDecimal number,CurrencyInfo currencyInfo)
    {
	BigDecimal tempNumber = number;

	if(tempNumber.compareTo(BigDecimal.ZERO) == 0)
	{
	    return "صفر";
	}

	String[] splits = number.toString().replaceAll(",", "").split("\\.");
	long _intergerValue = Long.parseLong(splits[0]);
	int _decimalValue = 0;
	if(splits.length > 1)
	{
	    _decimalValue = Integer.parseInt(getDecimalValue(splits[1],currencyInfo));
	}

	// Get Text for the decimal part
	BigDecimal decTempNumber = BigDecimal.valueOf(_decimalValue);
	String decimalString = returnNumberInArabicLetters(currencyInfo, decTempNumber, _intergerValue);

	String retVal = "";
	retVal = returnNumberInArabicLetters(currencyInfo, tempNumber, _intergerValue);

	StringBuffer formattedNumber = new StringBuffer();
	formattedNumber.append((arabicPrefixText.isEmpty()) ? "" : String.format("%s ", arabicPrefixText))
	.append((retVal.isEmpty()) ? "" : retVal);
	if(_intergerValue != 0)
	{ // here we add currency name depending on _intergerValue : 1 ,2 ,
	    // 3--->10 , 11--->99
	    int remaining100 = (int) (_intergerValue % 100);

	    if(remaining100 == 0)
	    {
		formattedNumber.append(currencyInfo.arabic1CurrencyName);
	    }
	    else if(remaining100 == 1)
	    {
		formattedNumber.append(currencyInfo.arabic1CurrencyName);
	    }
	    else if(remaining100 == 2)
	    {
		if(_intergerValue == 2)
		{
		    formattedNumber.append(currencyInfo.arabic2CurrencyName);
		}
		else
		{
		    formattedNumber.append(currencyInfo.arabic1CurrencyName);
		}
	    }
	    else if(remaining100 >= 3 && remaining100 <= 10)
	    {
		formattedNumber.append(currencyInfo.arabic310CurrencyName);
	    }
	    else if(remaining100 >= 11 && remaining100 <= 99)
	    {
		formattedNumber.append(currencyInfo.arabic1199CurrencyName);
	    }
	}
	formattedNumber.append((_decimalValue == 0) ? "" : (retVal.isEmpty() ? "" : " و "))
	.append((_decimalValue == 0) ? "" : decimalString);
	if(_decimalValue != 0)
	{ // here we add currency part name depending on _intergerValue : 1 ,2 ,
	    // 3--->10 , 11--->99
	    formattedNumber.append(" ");

	    int remaining100 = _decimalValue % 100;

	    if(remaining100 == 0)
	    {
		formattedNumber.append(currencyInfo.arabic1CurrencyPartName);
	    }
	    else if(remaining100 == 1)
	    {
		formattedNumber.append(currencyInfo.arabic1CurrencyPartName);
	    }
	    else if(remaining100 == 2)
	    {
		formattedNumber.append(currencyInfo.arabic2CurrencyPartName);
	    }
	    else if(remaining100 >= 3 && remaining100 <= 10)
	    {
		formattedNumber.append(currencyInfo.arabic310CurrencyPartName);
	    }
	    else if(remaining100 >= 11 && remaining100 <= 99)
	    {
		formattedNumber.append(currencyInfo.arabic1199CurrencyPartName);
	    }
	}
	

	String suffixToAdd = arabicSuffixText;
	// check if suffix is provided BMOUPI170483
	if(currencyInfo.getSuffixToAdd() != null)
	{
	    suffixToAdd = currencyInfo.getSuffixToAdd();
	}
	formattedNumber.append((suffixToAdd.isEmpty()) ? "" : String.format(" %s", suffixToAdd));
	return formattedNumber.toString();
    }

    /**
     * returns arabic letters construction from given Number
     * @author marwanmaddah
     * @date   Jan 14, 2015
     * @param currencyInfo
     * @param tempNumber
     * @param _intergerValue
     * @return String
     *
     */
    private static String returnNumberInArabicLetters(CurrencyInfo currencyInfo, BigDecimal theTempNumber,
	    long _intergerValue)
    {
	int group = 0;
	String retVal = "";
	BigDecimal tempNumber = theTempNumber;
	while(tempNumber.compareTo(BigDecimal.ZERO) > 0)
	{
	    // seperate number into groups
	    int numberToProcess = tempNumber.remainder(BigDecimal.valueOf(1000)).intValue();

	    tempNumber = tempNumber.divideToIntegralValue(BigDecimal.valueOf(1000));

	    // convert group into its text
	    String groupDescription = processArabicGroup(numberToProcess, group, BigDecimal.valueOf(Math.floor(tempNumber
		    .doubleValue())),_intergerValue,currencyInfo);

	    if(!groupDescription.isEmpty())
	    { // here we add the new converted group to the previous
		// concatenated text
		if(group > 0)
		{
		    if(!retVal.isEmpty())
		    {
			retVal = String.format("%s %s", "و", retVal);
		    }

		    if(numberToProcess != 2)
		    {
			if(numberToProcess % 100 == 1)
			{
			    // normal case
			    retVal = String.format("%s %s", currencyInfo.arabicGroup[group], retVal); 
			}
			else
			{
			    if(numberToProcess >= 3 && numberToProcess <= 10)
			    {
				//numbers between 3 and 9 we use plural name
				retVal = String.format("%s %s", currencyInfo.arabicPluralGroups[group], retVal);
			    }
			    else
			    {
				if(retVal.isEmpty())
				{
				    // normal case
				    retVal = String.format("%s %s", currencyInfo.arabicGroup[group], retVal); 
				    
				}
				else
				{
				    retVal = String.format("%s %s", currencyInfo.arabicAppendedGroup[group], retVal);
				}
			    }
			}
		    }
		}

		retVal = String.format("%s %s", groupDescription, retVal);
	    }

	    group++;
	}
	return retVal;
    }
}

