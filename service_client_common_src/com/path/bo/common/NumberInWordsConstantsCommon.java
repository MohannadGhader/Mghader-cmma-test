package com.path.bo.common;

import java.util.HashMap;
import java.util.Map;

import com.path.lib.log.Log;

public final class NumberInWordsConstantsCommon
{
    public static final String ONES_KEY = "ONES";
    public static final String TWOS_KEY = "TWOS";
    public static final String TENS_KEY = "TENS";
    public static final String HUNDREDS_KEY = "HUNDREDS";
    public static final String NUMGROUPS_KEY = "NUMGROUPS";
    public static final String PLURALGROUPS_KEY = "PLURALGROUPS";
    public static final String APPENDED_TWOS_KEY = "APPENDED_TWOS";
    public static final String APPENDED_GROUPS_KEY = "APPENDED_GROUPS";
    public static final String FEMININE_ONES_KEY = "FEMININE_ONES";

    // Default numbers in words EN/AR/FR
    private static String[] englishOnes = new String[] { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
	    "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
	    "Eighteen", "Nineteen" };
    private static String[] englishTens = new String[] { "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
	    "Ninety" };
    private static String[] englishGroup = new String[] { "Hundred", "Thousand", "Million", "Billion", "Trillion",
	    "Quadrillion", "Quintillion", "Sextillian", "Septillion", "Octillion", "Nonillion", "Decillion",
	    "Undecillion", "Duodecillion", "Tredecillion", "Quattuordecillion", "Quindecillion", "Sexdecillion",
	    "Septendecillion", "Octodecillion", "Novemdecillion", "Vigintillion", "Unvigintillion", "Duovigintillion",
	    "10^72", "10^75", "10^78", "10^81", "10^84", "10^87", "Vigintinonillion", "10^93", "10^96",
	    "Duotrigintillion", "Trestrigintillion" };

    private static String[] arabicOnes = new String[] { "", "واحد", "اثنان", "ثلاثة", "أربعة", "خمسة", "ستة", "سبعة", "ثمانية",
	    "تسعة", "عشرة", "أحد عشر", "اثنا عشر", "ثلاثة عشر", "أربعة عشر", "خمسة عشر", "ستة عشر", "سبعة عشر",
	    "ثمانية عشر", "تسعة عشر" };
    private static String[] arabicFeminineOnes = new String[] { "", "إحدى", "اثنتان", "ثلاث", "أربع", "خمس", "ست", "سبع",
	    "ثمان", "تسع", "عشر", "إحدى عشرة", "اثنتا عشرة", "ثلاث عشرة", "أربع عشرة", "خمس عشرة", "ست عشرة",
	    "سبع عشرة", "ثماني عشرة", "تسع عشرة" };
    private static String[] arabicTens = new String[] { "عشرون", "ثلاثون", "أربعون", "خمسون", "ستون", "سبعون", "ثمانون",
	    "تسعون" };
    static String[] arabicHundreds = new String[] { "", "مائة", "مئتان", "ثلاثمائة", "أربعمائة", "خمسمائة", "ستمائة",
	    "سبعمائة", "ثمانمائة", "تسعمائة" };
    private static String[] arabicAppendedTwos = new String[] { "مئتا", "ألفا", "مليونا", "مليارا", "تريليونا", "كوادريليونا",
	    "كوينتليونا", "سكستيليونا" };
    private static String[] arabicTwos = new String[] { "مئتان", "ألفان", "مليونان", "ملياران", "تريليونان", "كوادريليونان",
	    "كوينتليونان", "سكستيليونان" };
    private static String[] arabicGroup = new String[] { "مائة", "ألف", "مليون", "مليار", "تريليون", "كوادريليون", "كوينتليون",
	    "سكستيليون" };
    private static String[] arabicAppendedGroup = new String[] { "", "ألفاً", "مليوناً", "ملياراً", "تريليوناً",
	    "كوادريليوناً", "كوينتليوناً", "سكستيليوناً" };
    private static String[] arabicPluralGroups = new String[] { "", "آلاف", "ملايين", "مليارات", "تريليونات", "كوادريليونات",
	    "كوينتليونات", "سكستيليونات" };

    private static String[] frenchTens = { "vingt", "trente", "quarante", "cinquante", "soixante", "soixante", "quatre-vingt",
	    "quatre-vingt" };
    private static String[] frenchOnes = { "", "un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf", "dix",
	    "onze", "douze", "treize", "quatorze", "quinze", "seize", "dix-sept", "dix-huit", "dix-neuf" };
    private static String[] frenchGroup = new String[] { "", "mille", "million", "milliard" };
    private static String[] frenchPluralGroup = new String[] { "", "milles", "millions", "milliards" };
    
    /**
     * private constructor to prevent the class being instantiated since all
     * methods are static
     */
    private NumberInWordsConstantsCommon()
    {
	Log.getInstance().error("This Class is utility class cannot be instantiated");
    }
    
    private static final Map<String, String[]> engHm = new HashMap<String, String[]>();
    static
    {
	engHm.put(ONES_KEY, englishOnes);
	engHm.put(TENS_KEY, englishTens);
	engHm.put(NUMGROUPS_KEY, englishGroup);
    };
    
    private static final Map<String, String[]> frHm = new HashMap<String, String[]>();
    static
    {
	frHm.put(ONES_KEY, frenchOnes);
	frHm.put(TENS_KEY, frenchTens);
	frHm.put(NUMGROUPS_KEY, frenchGroup);
	frHm.put(PLURALGROUPS_KEY, frenchPluralGroup);
    };

    private static final Map<String, String[]> arabHm = new HashMap<String, String[]>();
    static
    {
	arabHm.put(ONES_KEY, arabicOnes);
	arabHm.put(TENS_KEY, arabicTens);
	arabHm.put(NUMGROUPS_KEY, arabicGroup);
	arabHm.put(FEMININE_ONES_KEY, arabicFeminineOnes);
	arabHm.put(HUNDREDS_KEY, arabicHundreds);
	arabHm.put(APPENDED_TWOS_KEY, arabicAppendedTwos);
	arabHm.put(TWOS_KEY, arabicTwos);
	arabHm.put(APPENDED_GROUPS_KEY, arabicAppendedGroup);
	arabHm.put(PLURALGROUPS_KEY, arabicPluralGroups);
    };

    public volatile static Map<String, Object> numInWordsByLangDefaultMap = new HashMap<String, Object>();
    static
    {
	numInWordsByLangDefaultMap.put(ConstantsCommon.LANGUAGE_ENGLISH, engHm);
	numInWordsByLangDefaultMap.put(ConstantsCommon.LANGUAGE_FRENCH, frHm);
	numInWordsByLangDefaultMap.put(ConstantsCommon.LANGUAGE_ARABIC, arabHm);
    };
    
    public volatile static Map<String,Object> currenciesMap = new HashMap<String, Object>();
   
    public static enum Currency
    {
  	AED, SYP, SAR, TND, XAU, JOD, BHD, USD, KWD, DZD, AUD, BDT, CAD,LKR, CYP, DKK, GNF, HKD, INR,IDR , IQD, JPY, LBP, 
  	MYR, MVR, MRO, MXN,OMR, NZD, NOK, PKR,PHP ,QAR, SGD, SEK, CHF , EGP, GBP, YER, TRY, XOF, EUR, MAD
    }
    
    public static void resetNumInWordsByLangDefaultMap()
    {
	numInWordsByLangDefaultMap = new HashMap<String, Object>();
	numInWordsByLangDefaultMap.put(ConstantsCommon.LANGUAGE_ENGLISH, engHm);
	numInWordsByLangDefaultMap.put(ConstantsCommon.LANGUAGE_FRENCH, frHm);
	numInWordsByLangDefaultMap.put(ConstantsCommon.LANGUAGE_ARABIC, arabHm);
	currenciesMap = new HashMap<String, Object>();
	convertLikeLangMap = new HashMap<String, Map<String, String>>();
	convertLikeCurrencyMap = new HashMap<String, Map<String, String>>();
    }
    
    public volatile static Map<String, Map<String, String>> convertLikeLangMap = new HashMap<String, Map<String, String>>();
    public volatile static Map<String, Map<String, String>> convertLikeCurrencyMap = new HashMap<String, Map<String, String>>();
}
