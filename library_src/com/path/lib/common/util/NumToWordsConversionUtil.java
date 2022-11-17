package com.path.lib.common.util;

import java.math.BigDecimal;

import org.springframework.util.Assert;

import com.path.bo.common.CommonLibBO;
import com.path.bo.common.ConstantsCommon;
import com.path.lib.common.exception.BaseException;
import com.path.lib.log.Log;

public final class NumToWordsConversionUtil
{
    private static final Log log = Log.getInstance();
    
    private NumToWordsConversionUtil()
    {
        log.error("This Class Should not be Instantiated");
    }
    public static String convertToLang(String langCode, BigDecimal value, String currencyIsoCode, BigDecimal decDigits)
	    throws BaseException
    {
	Assert.notNull(langCode);
	Assert.notNull(value);
	Assert.notNull(currencyIsoCode);
	
	CommonLibBO commonLibBO = (CommonLibBO) ApplicationContextProvider.getApplicationContext().getBean(
	"commonLibBO");
	
	String convertMethodId = commonLibBO.returnConversionMethodId(langCode);    
	if(convertMethodId == null)
	{
	    if(ConstantsCommon.LANGUAGE_ARABIC.equals(langCode))
	    {
		return NumberToArabic.convertToArabic(value, currencyIsoCode, decDigits, langCode);
	    }
	    else if(ConstantsCommon.LANGUAGE_FRENCH.equals(langCode))
	    {
		return FrenchNumberToWords.convert(value, currencyIsoCode, decDigits, langCode);
	    }
	    return NumberToArabic.convertToEnglish(value, currencyIsoCode, decDigits);
	}
	
	if(ConstantsCommon.CONVERT_TO_ENGLISH.equals(convertMethodId))
	{
	    return NumberToArabic.convertToEnglish(value, currencyIsoCode, decDigits, langCode);
	}
	if(ConstantsCommon.CONVERT_TO_ARABIC.equals(convertMethodId))
	{
	    return NumberToArabic.convertToArabic(value, currencyIsoCode, decDigits, langCode);
	}
	if(ConstantsCommon.CONVERT_TO_FRENCH.equals(convertMethodId))
	{
	    return FrenchNumberToWords.convert(value, currencyIsoCode, decDigits, langCode);
	}
	return NumberToArabic.convertToEnglish(value, currencyIsoCode, decDigits, langCode);
    }
}
