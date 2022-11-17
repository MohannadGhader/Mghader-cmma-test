package com.path.lib.common.util;

import com.path.bo.common.NumberInWordsConstantsCommon.Currency;
import com.path.vo.common.CurrencyToWordsCO;

class CurrencyInfo
{

    String[] englishOnes = { "" };
    String[] englishTens = {};
    String[] englishGroup = {};
    String[] arabicOnes = { "" };
    String[] arabicFeminineOnes = {};
    String[] arabicTens = {};
    String[] arabicHundreds = {};
    String[] arabicAppendedTwos = {};
    String[] arabicTwos = {};
    String[] arabicGroup = {};
    String[] arabicAppendedGroup = {};
    String[] arabicPluralGroups = {};

    Currency currencyID;
    String currencyCode;
    boolean isCurrencyNameFeminine;
    String englishCurrencyName;
    String englishPluralCurrencyName;
    String englishCurrencyPartName;
    String englishPluralCurrencyPartName;
    String arabic1CurrencyName;
    String arabic2CurrencyName;
    String arabic310CurrencyName;
    String arabic1199CurrencyName;
    String arabic1CurrencyPartName;
    String arabic2CurrencyPartName;
    String arabic310CurrencyPartName;
    String arabic1199CurrencyPartName;
    int partPrecision;
    boolean isCurrencyPartNameFeminine;
    String suffixToAdd; // suffix needed at the end of letter representation


    public Currency getCurrencyID()
    {
	return currencyID;
    }

    public void setCurrencyID(Currency currencyID)
    {
	this.currencyID = currencyID;
    }

    public String getCurrencyCode()
    {
	return currencyCode;
    }

    public void setCurrencyCode(String currencyCode)
    {
	this.currencyCode = currencyCode;
    }

    public boolean isCurrencyNameFeminine()
    {
	return isCurrencyNameFeminine;
    }

    public void setCurrencyNameFeminine(boolean isCurrencyNameFeminine)
    {
	this.isCurrencyNameFeminine = isCurrencyNameFeminine;
    }

    public String getEnglishCurrencyName()
    {
	return englishCurrencyName;
    }

    public void setEnglishCurrencyName(String englishCurrencyName)
    {
	this.englishCurrencyName = englishCurrencyName;
    }

    public String getEnglishPluralCurrencyName()
    {
	return englishPluralCurrencyName;
    }

    public void setEnglishPluralCurrencyName(String englishPluralCurrencyName)
    {
	this.englishPluralCurrencyName = englishPluralCurrencyName;
    }

    public String getEnglishCurrencyPartName()
    {
	return englishCurrencyPartName;
    }

    public void setEnglishCurrencyPartName(String englishCurrencyPartName)
    {
	this.englishCurrencyPartName = englishCurrencyPartName;
    }

    public String getEnglishPluralCurrencyPartName()
    {
	return englishPluralCurrencyPartName;
    }

    public void setEnglishPluralCurrencyPartName(String englishPluralCurrencyPartName)
    {
	this.englishPluralCurrencyPartName = englishPluralCurrencyPartName;
    }

    public String getArabic1CurrencyName()
    {
	return arabic1CurrencyName;
    }

    public void setArabic1CurrencyName(String arabic1CurrencyName)
    {
	this.arabic1CurrencyName = arabic1CurrencyName;
    }

    public String getArabic2CurrencyName()
    {
	return arabic2CurrencyName;
    }

    public void setArabic2CurrencyName(String arabic2CurrencyName)
    {
	this.arabic2CurrencyName = arabic2CurrencyName;
    }

    public String getArabic310CurrencyName()
    {
	return arabic310CurrencyName;
    }

    public void setArabic310CurrencyName(String arabic310CurrencyName)
    {
	this.arabic310CurrencyName = arabic310CurrencyName;
    }

    public String getArabic1199CurrencyName()
    {
	return arabic1199CurrencyName;
    }

    public void setArabic1199CurrencyName(String arabic1199CurrencyName)
    {
	this.arabic1199CurrencyName = arabic1199CurrencyName;
    }

    public String getArabic1CurrencyPartName()
    {
	return arabic1CurrencyPartName;
    }

    public void setArabic1CurrencyPartName(String arabic1CurrencyPartName)
    {
	this.arabic1CurrencyPartName = arabic1CurrencyPartName;
    }

    public String getArabic2CurrencyPartName()
    {
	return arabic2CurrencyPartName;
    }

    public void setArabic2CurrencyPartName(String arabic2CurrencyPartName)
    {
	this.arabic2CurrencyPartName = arabic2CurrencyPartName;
    }

    public String getArabic310CurrencyPartName()
    {
	return arabic310CurrencyPartName;
    }

    public void setArabic310CurrencyPartName(String arabic310CurrencyPartName)
    {
	this.arabic310CurrencyPartName = arabic310CurrencyPartName;
    }

    public String getArabic1199CurrencyPartName()
    {
	return arabic1199CurrencyPartName;
    }

    public void setArabic1199CurrencyPartName(String arabic1199CurrencyPartName)
    {
	this.arabic1199CurrencyPartName = arabic1199CurrencyPartName;
    }

    public int getPartPrecision()
    {
	return partPrecision;
    }

    public void setPartPrecision(int partPrecision)
    {
	this.partPrecision = partPrecision;
    }

    public boolean isCurrencyPartNameFeminine()
    {
	return isCurrencyPartNameFeminine;
    }

    public void setCurrencyPartNameFeminine(boolean isCurrencyPartNameFeminine)
    {
	this.isCurrencyPartNameFeminine = isCurrencyPartNameFeminine;
    }

    public CurrencyInfo(CurrencyToWordsCO currToWordsCO)
    {
	currencyCode = currToWordsCO.getIsoCurrVO().getCY_ISO_CODE();
	isCurrencyNameFeminine = ("1".equals(currToWordsCO.getIsoCurrVO().getIS_FEMALE_NOUN_YN()) ? true : false);
	englishCurrencyName = currToWordsCO.getCurrToWordsVO().getCURRENCY_NAME();
	englishPluralCurrencyName = currToWordsCO.getCurrToWordsVO().getPLURAL_CURRENCY_NAME();
	englishCurrencyPartName = currToWordsCO.getCurrToWordsVO().getDECIMAL_NAME();
	englishPluralCurrencyPartName = currToWordsCO.getCurrToWordsVO().getPLURAL_DECIMAL_NAME();
	arabic1CurrencyName = currToWordsCO.getCurrToWordsVO().getCURRENCY_NAME();
	arabic2CurrencyName = currToWordsCO.getCurrToWordsVO().getDUAL_CURRENCY_NAME();
	arabic310CurrencyName = currToWordsCO.getCurrToWordsVO().getPLURAL_CURRENCY_NAME();
	arabic1199CurrencyName = currToWordsCO.getCurrToWordsVO().getAPPENDED_CURRENCY_NAME();
	arabic1CurrencyPartName = currToWordsCO.getCurrToWordsVO().getDECIMAL_NAME();
	arabic2CurrencyPartName = currToWordsCO.getCurrToWordsVO().getDUAL_DECIMAL_NAME();
	arabic310CurrencyPartName = currToWordsCO.getCurrToWordsVO().getPLURAL_DECIMAL_NAME();
	arabic1199CurrencyPartName = currToWordsCO.getCurrToWordsVO().getAPPENDED_DECIMAL_NAME();
	isCurrencyPartNameFeminine = ("1".equals(currToWordsCO.getIsoCurrVO().getIS_DECIMAL_FEMALE_NOUN_YN()) ? true
		: false);
	partPrecision = currToWordsCO.getCurrVO().getDECIMAL_POINTS() == null ? 2 :currToWordsCO.getCurrVO().getDECIMAL_POINTS().intValue();
    }

    public CurrencyInfo(Currency currency)
    {
	switch (currency)
	{
	    case AED:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "UAE Dirham";
		englishPluralCurrencyName = "UAE Dirhams";
		englishCurrencyPartName = "Fils";
		englishPluralCurrencyPartName = "Fils";
		arabic1CurrencyName = "درهم إماراتي";
		arabic2CurrencyName = "درهمان  إماراتيان";
		arabic310CurrencyName = "دراهم إماراتية";
		arabic1199CurrencyName = "درهماً إماراتياً";
		arabic1CurrencyPartName = "فلس";
		arabic2CurrencyPartName = "فلسان";
		arabic310CurrencyPartName = "فلوس";
		arabic1199CurrencyPartName = "فلساً";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;
	    case BHD:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Bahraini Dinar";
		englishPluralCurrencyName = "Bahraini Dinars";
		englishCurrencyPartName = "Fils";
		englishPluralCurrencyPartName = "Fils";
		arabic1CurrencyName = "دينار بحريني";
		arabic2CurrencyName = "ديناران بحرينيان";
		arabic310CurrencyName = "دنانير بحرينية";
		arabic1199CurrencyName = "ديناراً بحرينياً";
		arabic1CurrencyPartName = "فلس";
		arabic2CurrencyPartName = "فلسان";
		arabic310CurrencyPartName = "فلوس";
		arabic1199CurrencyPartName = "فلساً";
		partPrecision = 3;
		isCurrencyPartNameFeminine = false;
		break;
	    case SAR:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Saudi Riyal";
		englishPluralCurrencyName = "Saudi Riyals";
		englishCurrencyPartName = "Halala";
		englishPluralCurrencyPartName = "Halalas";
		arabic1CurrencyName = "ريال سعودي";
		arabic2CurrencyName = "ريالان سعوديان";
		arabic310CurrencyName = "ريالات سعودية";
		arabic1199CurrencyName = "ريالاً سعودياً";
		arabic1CurrencyPartName = "هللة";
		arabic2CurrencyPartName = "هللتان";
		arabic310CurrencyPartName = "هللات";
		arabic1199CurrencyPartName = "هللة";
		partPrecision = 2;
		isCurrencyPartNameFeminine = true;
		break;
	    case SYP:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = true;
		englishCurrencyName = "Syrian Pound";
		englishPluralCurrencyName = "Syrian Pounds";
		englishCurrencyPartName = "Piaster";
		englishPluralCurrencyPartName = "Piasteres";
		arabic1CurrencyName = "ليرة سورية";
		arabic2CurrencyName = "ليرتان سوريتان";
		arabic310CurrencyName = "ليرات سورية";
		arabic1199CurrencyName = "ليرة سورية";
		arabic1CurrencyPartName = "قرش";
		arabic2CurrencyPartName = "قرشان";
		arabic310CurrencyPartName = "قروش";
		arabic1199CurrencyPartName = "قرشاً";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;
	    case TND:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Tunisian Dinar";
		englishPluralCurrencyName = "Tunisian Dinars";
		englishCurrencyPartName = "Milim";
		englishPluralCurrencyPartName = "Millimes";
		arabic1CurrencyName = "دينار تونسي";
		arabic2CurrencyName = "ديناران تونسيان";
		arabic310CurrencyName = "دنانير تونسية";
		arabic1199CurrencyName = "دينارا تونسياً";
		arabic1CurrencyPartName = "ملّيم";
		arabic2CurrencyPartName = "ملّيمان";
		arabic310CurrencyPartName = "ملّيم";
		arabic1199CurrencyPartName = "ملّيماً";
		partPrecision = 3;
		isCurrencyPartNameFeminine = false;
		break;
	    case JOD:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Jordanian Dinar";
		englishPluralCurrencyName = "Jordanian Dinars";
		englishCurrencyPartName = "Fils";
		englishPluralCurrencyPartName = "Fils";
		arabic1CurrencyName = "دينار أردني";
		arabic2CurrencyName = "ديناران أردنيان";
		arabic310CurrencyName = "دنانير أردنية";
		arabic1199CurrencyName = "ديناراً أردنياً";
		arabic1CurrencyPartName = "فلس";
		arabic2CurrencyPartName = "فلسان";
		arabic310CurrencyPartName = "فلس";
		arabic1199CurrencyPartName = "فلساً";
		partPrecision = 3;
		isCurrencyPartNameFeminine = false;
		break;

	    case KWD:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Kuwaiti Dinar";
		englishPluralCurrencyName = "Kuwaiti Dinars";
		englishCurrencyPartName = "Fils";
		englishPluralCurrencyPartName = "Fils";
		arabic1CurrencyName = "دينار كويتي";
		arabic2CurrencyName = "ديناران كويتيان";
		arabic310CurrencyName = "دنانير كويتية";
		arabic1199CurrencyName = "ًديناراً كويتيا";
		arabic1CurrencyPartName = "فلس";
		arabic2CurrencyPartName = "فلسان";
		arabic310CurrencyPartName = "فلس";
		arabic1199CurrencyPartName = "ًفلسا";
		partPrecision = 3;
		isCurrencyPartNameFeminine = false;
		break;

	    case DZD:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Algerian Dinar";
		englishPluralCurrencyName = "Algerian Dinars";
		englishCurrencyPartName = "Centime";
		englishPluralCurrencyPartName = "Centimes";
		arabic1CurrencyName = "دينار جزائري";
		arabic2CurrencyName = "ديناران جزائريان";
		arabic310CurrencyName = "دنانير جزائرية";
		arabic1199CurrencyName = "ًديناراً جزائريا";
		arabic1CurrencyPartName = "سنتيم";
		arabic2CurrencyPartName = "سنتيمان";
		arabic310CurrencyPartName = "سنتيم";
		arabic1199CurrencyPartName = "ًسنتيما";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;
	    case AUD:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "AUD";
		englishPluralCurrencyName = "AUD";
		englishCurrencyPartName = "Cent";
		englishPluralCurrencyPartName = "Cents";
		arabic1CurrencyName = "دولار أسترالي";
		arabic2CurrencyName = "دولاران أستراليان";
		arabic310CurrencyName = "دولارات أسترالية";
		arabic1199CurrencyName = "ًدولاراً أستراليا";
		arabic1CurrencyPartName = "سنت";
		arabic2CurrencyPartName = "سنتان";
		arabic310CurrencyPartName = "سنت";
		arabic1199CurrencyPartName = "سنتاً";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case BDT:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Bangladeshi Taka";
		englishPluralCurrencyName = "Bangladeshi Taka";
		englishCurrencyPartName = "Poisha";
		englishPluralCurrencyPartName = "Poisha";
		arabic1CurrencyName = "تاكا بنجلاديشي";
		arabic2CurrencyName = "تاكا بنجلاديشي";
		arabic310CurrencyName = "تاكا بنجلاديشي";
		arabic1199CurrencyName = "تاكا بنجلاديشي";
		arabic1CurrencyPartName = "poisha";
		arabic2CurrencyPartName = "poisha";
		arabic310CurrencyPartName = "poisha";
		arabic1199CurrencyPartName = "poisha";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case CAD:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Canadian Dollar";
		englishPluralCurrencyName = "Canadian Dollars";
		englishCurrencyPartName = "Cent";
		englishPluralCurrencyPartName = "Cents";
		arabic1CurrencyName = "دولار كندي";
		arabic2CurrencyName = "دولاران كنديان";
		arabic310CurrencyName = "دولارات كندية";
		arabic1199CurrencyName = "ًدولاراً كنديا";
		arabic1CurrencyPartName = "سنت";
		arabic2CurrencyPartName = "سنتان";
		arabic310CurrencyPartName = "سنتات";
		arabic1199CurrencyPartName = "ًسنتا";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case LKR:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = true;
		englishCurrencyName = "Sri Lanka Rupee";
		englishPluralCurrencyName = "Sri Lanka Rupees";
		englishCurrencyPartName = "Cent";
		englishPluralCurrencyPartName = "Cents";
		arabic1CurrencyName = "روبية سريلانكية";
		arabic2CurrencyName = "روبية سريلانكية";
		arabic310CurrencyName = "روبية سريلانكية";
		arabic1199CurrencyName = "روبية سريلانكية";
		arabic1CurrencyPartName = "سنت";
		arabic2CurrencyPartName = "سنتان";
		arabic310CurrencyPartName = "سنتات";
		arabic1199CurrencyPartName = "سنتاً";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case CYP:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Cyprus Pound";
		englishPluralCurrencyName = "Cyprus Pounds";
		englishCurrencyPartName = "Cent";
		englishPluralCurrencyPartName = "Cents";
		arabic1CurrencyName = "جنيه قبرصي";
		arabic2CurrencyName = "جنيهان قبرصيان";
		arabic310CurrencyName = "جنيهات قبرصية";
		arabic1199CurrencyName = "جنيهاً قبرصياً";
		arabic1CurrencyPartName = "سنت";
		arabic2CurrencyPartName = "سنتان";
		arabic310CurrencyPartName = "سنتات";
		arabic1199CurrencyPartName = "ًسنتا";
		partPrecision = 3;
		isCurrencyPartNameFeminine = false;
		break;

	    case GNF:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Guinean Franc";
		englishPluralCurrencyName = "Guinean Francs";
		englishCurrencyPartName = "Centime";
		englishPluralCurrencyPartName = "Centimes";
		arabic1CurrencyName = "فرنك غيني";
		arabic2CurrencyName = "فرنكان غينيان";
		arabic310CurrencyName = "فرنكات غينية";
		arabic1199CurrencyName = "ًفرنكاً غينيا";
		arabic1CurrencyPartName = "سنتيم";
		arabic2CurrencyPartName = "سنتيمان";
		arabic310CurrencyPartName = "سنتيمات";
		arabic1199CurrencyPartName = "سنتيماً";
		partPrecision = 3;
		isCurrencyPartNameFeminine = false;
		break;

	    case HKD:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Hong Kong Dollar";
		englishPluralCurrencyName = "Hong Kong Dollars";
		englishCurrencyPartName = "Cent";
		englishPluralCurrencyPartName = "Cents";
		arabic1CurrencyName = "دولار هونغ كونغ";
		arabic2CurrencyName = "دولار هونغ كونغ";
		arabic310CurrencyName = "دولار هونغ كونغ";
		arabic1199CurrencyName = "دولار هونغ كونغ";
		arabic1CurrencyPartName = "سنت";
		arabic2CurrencyPartName = "سنتان";
		arabic310CurrencyPartName = "سنتات";
		arabic1199CurrencyPartName = "ًسنتا";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case INR:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = true;
		englishCurrencyName = "Indian Rupee";
		englishPluralCurrencyName = "Indian Rupees";
		englishCurrencyPartName = "Paisa";
		englishPluralCurrencyPartName = "Paise";
		arabic1CurrencyName = "روبية هندية";
		arabic2CurrencyName = "روبية هندية";
		arabic310CurrencyName = "روبية هندية";
		arabic1199CurrencyName = "روبية هندية";
		arabic1CurrencyPartName = "paisa";
		arabic2CurrencyPartName = "paise";
		arabic310CurrencyPartName = "paise";
		arabic1199CurrencyPartName = "paisa";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case IDR:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = true;
		englishCurrencyName = "Indonesian Rupiah";
		englishPluralCurrencyName = "Indonesian Rupiahs";
		englishCurrencyPartName = "Sen";
		englishPluralCurrencyPartName = "Sen";
		arabic1CurrencyName = "روبية أندونيسية";
		arabic2CurrencyName = "روبية أندونيسية";
		arabic310CurrencyName = "روبية أندونيسية";
		arabic1199CurrencyName = "روبية أندونيسية";
		arabic1CurrencyPartName = "سنت";
		arabic2CurrencyPartName = "سنتان";
		arabic310CurrencyPartName = "سنتات";
		arabic1199CurrencyPartName = "سنتاً";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case IQD:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Iraqi Dinar";
		englishPluralCurrencyName = "Iraqi Dinars";
		englishCurrencyPartName = "Fils";
		englishPluralCurrencyPartName = "Fils";
		arabic1CurrencyName = "دينار عراقي";
		arabic2CurrencyName = "ديناران عراقيان";
		arabic310CurrencyName = "دنانير عراقية";
		arabic1199CurrencyName = "ديناراً عراقياً";
		arabic1CurrencyPartName = "فلس";
		arabic2CurrencyPartName = "فلسان";
		arabic310CurrencyPartName = "فلوس";
		arabic1199CurrencyPartName = "فلساً";
		partPrecision = 3;
		isCurrencyPartNameFeminine = false;
		break;

	    case LBP:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = true;
		englishCurrencyName = "Lebanese Pound";
		englishPluralCurrencyName = "Lebanese Pounds";
		englishCurrencyPartName = "Piastre";
		englishPluralCurrencyPartName = "Piastres";
		arabic1CurrencyName = "ليرة لبنانية";
		arabic2CurrencyName = "ليرتان لبنانيتان";
		arabic310CurrencyName = "ليرات لبنانية";
		arabic1199CurrencyName = "ليرة لبنانية";
		arabic1CurrencyPartName = "قرش";
		arabic2CurrencyPartName = "قرشان";
		arabic310CurrencyPartName = "قروش";
		arabic1199CurrencyPartName = "قرشاً";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case MYR:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Malaysian Ringgit";
		englishPluralCurrencyName = "Malaysian Ringgits";
		englishCurrencyPartName = "Sen";
		englishPluralCurrencyPartName = "Sen";
		arabic1CurrencyName = "رينغيت ماليزي";
		arabic2CurrencyName = "رينغيت ماليزي";
		arabic310CurrencyName = "رينغيت ماليزي";
		arabic1199CurrencyName = "رينغيت ماليزي";
		arabic1CurrencyPartName = "سنت";
		arabic2CurrencyPartName = "سنتان";
		arabic310CurrencyPartName = "سنتات";
		arabic1199CurrencyPartName = "سنتاً";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case MVR:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = true;
		englishCurrencyName = "Maldivian Rufiyaa";
		englishPluralCurrencyName = "Maldivian Rufiyaa";
		englishCurrencyPartName = "Lari";
		englishPluralCurrencyPartName = "Laris";
		arabic1CurrencyName = "روفيا مالديفية";
		arabic2CurrencyName = "روفيا مالديفية";
		arabic310CurrencyName = "روفيا مالديفية";
		arabic1199CurrencyName = "روفيا مالديفية";
		arabic1CurrencyPartName = "لاري";
		arabic2CurrencyPartName = "لاري";
		arabic310CurrencyPartName = "لاريان";
		arabic1199CurrencyPartName = "لاري";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case MRO:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = true;
		englishCurrencyName = "Mauritanian Ouguiya";
		englishPluralCurrencyName = "Mauritanian Ouguiya";
		englishCurrencyPartName = "Khoums";
		englishPluralCurrencyPartName = "Khoums";
		arabic1CurrencyName = "أوقية موريتانية";
		arabic2CurrencyName = "أوقيتان موريتانيتان";
		arabic310CurrencyName = "أوقيات موريتانية";
		arabic1199CurrencyName = "أوقية موريتانية";
		arabic1CurrencyPartName = "خمس";
		arabic2CurrencyPartName = "خمس";
		arabic310CurrencyPartName = "خمس";
		arabic1199CurrencyPartName = "ًخمسا";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case MXN:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Mexican Peso";
		englishPluralCurrencyName = "Mexican Pesos";
		englishCurrencyPartName = "Centavo";
		englishPluralCurrencyPartName = "Centavos";
		arabic1CurrencyName = "بيزو مكسيكي";
		arabic2CurrencyName = "بيزوان مكسيكيان";
		arabic310CurrencyName = "بيزوات مكسيكية";
		arabic1199CurrencyName = "بيزو مكسيكي";
		arabic1CurrencyPartName = "سنتيم";
		arabic2CurrencyPartName = "سنتيمان";
		arabic310CurrencyPartName = "سنتيمات";
		arabic1199CurrencyPartName = "سنتيماً";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case OMR:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Omani Rial";
		englishPluralCurrencyName = "Omani Rials";
		englishCurrencyPartName = "Baiza";
		englishPluralCurrencyPartName = "Baiza";
		arabic1CurrencyName = "ريال عماني";
		arabic2CurrencyName = "ريالان عمانيان";
		arabic310CurrencyName = "ريالات عمانية";
		arabic1199CurrencyName = "ريالاً عمانياً";
		arabic1CurrencyPartName = "baiza";
		arabic2CurrencyPartName = "baiza";
		arabic310CurrencyPartName = "baiza";
		arabic1199CurrencyPartName = "baiza";
		partPrecision = 3;
		isCurrencyPartNameFeminine = false;
		break;

	    case NZD:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "New Zealand Dollar";
		englishPluralCurrencyName = "New Zealand Dollars";
		englishCurrencyPartName = "Cent";
		englishPluralCurrencyPartName = "Cents";
		arabic1CurrencyName = "دولار نيوزيلاندي";
		arabic2CurrencyName = "دولاران نيوزيلانديان";
		arabic310CurrencyName = "دولارات نيوزيلاندية";
		arabic1199CurrencyName = "ًدولاراً نيوزيلانديا";
		arabic1CurrencyPartName = "سنت";
		arabic2CurrencyPartName = "سنتان";
		arabic310CurrencyPartName = "سنتات";
		arabic1199CurrencyPartName = "ًسنتا";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case NOK:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = true;
		englishCurrencyName = "Norwegian Krone";
		englishPluralCurrencyName = "Norwegian Krones";
		englishCurrencyPartName = "øre";
		englishPluralCurrencyPartName = "øre";
		arabic1CurrencyName = "كرونة نروجية";
		arabic2CurrencyName = "كرونة نروجية";
		arabic310CurrencyName = "كرونة نروجية";
		arabic1199CurrencyName = "كرونة نروجية";
		arabic1CurrencyPartName = "øre";
		arabic2CurrencyPartName = "øre";
		arabic310CurrencyPartName = "øre";
		arabic1199CurrencyPartName = "øre";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case PKR:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = true;
		englishCurrencyName = "Pakistani Rupee";
		englishPluralCurrencyName = "Pakistani Rupees";
		englishCurrencyPartName = "Paisa";
		englishPluralCurrencyPartName = "Paise";
		arabic1CurrencyName = "روبية باكستانية";
		arabic2CurrencyName = "روبية باكستانية";
		arabic310CurrencyName = "روبية باكستانية";
		arabic1199CurrencyName = "روبية باكستانية";
		arabic1CurrencyPartName = "paisa";
		arabic2CurrencyPartName = "paise";
		arabic310CurrencyPartName = "paise";
		arabic1199CurrencyPartName = "paisa";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case PHP:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Philippine Peso";
		englishPluralCurrencyName = "Philippine Pesos";
		englishCurrencyPartName = "Centavo";
		englishPluralCurrencyPartName = "Centavos";
		arabic1CurrencyName = "بيزو فلبيني";
		arabic2CurrencyName = "بيزوان فلبينيان";
		arabic310CurrencyName = "بيزوات فلبنية";
		arabic1199CurrencyName = "بيزو فلبيني";
		arabic1CurrencyPartName = "سنتيم";
		arabic2CurrencyPartName = "سنتيمان";
		arabic310CurrencyPartName = "سنتيمات";
		arabic1199CurrencyPartName = "سنتيماً";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case QAR:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Qatari Riyal";
		englishPluralCurrencyName = "Qatari Riyals";
		englishCurrencyPartName = "Dirham";
		englishPluralCurrencyPartName = "Dirhams";
		arabic1CurrencyName = "ريال قطري";
		arabic2CurrencyName = "ريالان قطريان";
		arabic310CurrencyName = "ريالات قطرية";
		arabic1199CurrencyName = "ًريالاً قطريا";
		arabic1CurrencyPartName = "درهم";
		arabic2CurrencyPartName = "درهمان";
		arabic310CurrencyPartName = "دراهم";
		arabic1199CurrencyPartName = "ًدرهما";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case SGD:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Singapore Dollar";
		englishPluralCurrencyName = "Singapore Dollars";
		englishCurrencyPartName = "Cent";
		englishPluralCurrencyPartName = "Cents";
		arabic1CurrencyName = "دولار سنغافوري";
		arabic2CurrencyName = "دولاران سنغافوريان";
		arabic310CurrencyName = "دولارات سنغافورية";
		arabic1199CurrencyName = "ًدولاراً سنغافوريا";
		arabic1CurrencyPartName = "سنت";
		arabic2CurrencyPartName = "سنتان";
		arabic310CurrencyPartName = "سنتات";
		arabic1199CurrencyPartName = "ًسنتا";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case SEK:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = true;
		englishCurrencyName = "Swedish Krona";
		englishPluralCurrencyName = "Swedish Krona";
		englishCurrencyPartName = "öre";
		englishPluralCurrencyPartName = "öre";
		arabic1CurrencyName = "كرونا سويدية";
		arabic2CurrencyName = "كرونا سويدية";
		arabic310CurrencyName = "كرونا سويدية";
		arabic1199CurrencyName = "كرونا سويدية";
		arabic1CurrencyPartName = "öre";
		arabic2CurrencyPartName = "öre";
		arabic310CurrencyPartName = "öre";
		arabic1199CurrencyPartName = "öre";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case CHF:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Swiss Franc";
		englishPluralCurrencyName = "Swiss Francs";
		englishCurrencyPartName = "Centime";
		englishPluralCurrencyPartName = "Centimes";
		arabic1CurrencyName = "فرنك سويسري";
		arabic2CurrencyName = "فرنكان سويسريان";
		arabic310CurrencyName = "فرنكات سويسرية";
		arabic1199CurrencyName = "فرنكاً سويسرياً";
		arabic1CurrencyPartName = "سنتيم";
		arabic2CurrencyPartName = "سنتيمان";
		arabic310CurrencyPartName = "سنتيمات";
		arabic1199CurrencyPartName = "ًسنتيما";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case EGP:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Egyptian Pound";
		englishPluralCurrencyName = "Egyptian Pounds";
		englishCurrencyPartName = "Piastre";
		englishPluralCurrencyPartName = "Piastres";
		arabic1CurrencyName = "جنيه مصرى";
		arabic2CurrencyName = "جنيهان مصريان";
		arabic310CurrencyName = "جنيهات مصرية";
		arabic1199CurrencyName = "جنيهاً مصرياً";
		arabic1CurrencyPartName = "قرش";
		arabic2CurrencyPartName = "قرشان";
		arabic310CurrencyPartName = "قروش";
		arabic1199CurrencyPartName = "قرشاً";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case GBP:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Pound Sterling";
		englishPluralCurrencyName = "Pound Sterling";
		englishCurrencyPartName = "Penny";
		englishPluralCurrencyPartName = "Pence";
		arabic1CurrencyName = "جنيه استرليني";
		arabic2CurrencyName = "جنيهان استرلينيان";
		arabic310CurrencyName = "جنيهات استرلينية";
		arabic1199CurrencyName = "جنيهاً استرلينياً";
		arabic1CurrencyPartName = "بنس";
		arabic2CurrencyPartName = "بنسان";
		arabic310CurrencyPartName = "بنسات";
		arabic1199CurrencyPartName = "ًبنسا";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case YER:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Yemeni Rial";
		englishPluralCurrencyName = "Yemeni Rials";
		englishCurrencyPartName = "Fils";
		englishPluralCurrencyPartName = "Fils";
		arabic1CurrencyName = "ريال يمني";
		arabic2CurrencyName = "ريالان يمنيان";
		arabic310CurrencyName = "ريالات يمنية";
		arabic1199CurrencyName = "ريالاً يمنياً";
		arabic1CurrencyPartName = "فلس";
		arabic2CurrencyPartName = "فلسان";
		arabic310CurrencyPartName = "فلوس";
		arabic1199CurrencyPartName = "فلساً";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case TRY:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = true;
		englishCurrencyName = "Trukish Lira";
		englishPluralCurrencyName = "Trukish Liras";
		englishCurrencyPartName = "Kurus";
		englishPluralCurrencyPartName = "Kurus";
		arabic1CurrencyName = "ليرة تركية";
		arabic2CurrencyName = "ليرتان تركيتان";
		arabic310CurrencyName = "ليرات تركية";
		arabic1199CurrencyName = "ليرة تركية";
		arabic1CurrencyPartName = "قرش";
		arabic2CurrencyPartName = "قرشان";
		arabic310CurrencyPartName = "قروش";
		arabic1199CurrencyPartName = "قرشاً";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case XOF:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "West African CFA Franc";
		englishPluralCurrencyName = "West African CFA Francs";
		englishCurrencyPartName = "Centime";
		englishPluralCurrencyPartName = "Centimes";
		arabic1CurrencyName = "فرنك غرب أفريقيا";
		arabic2CurrencyName = "فرنك غرب أفريقيا";
		arabic310CurrencyName = "فرنك غرب أفريقيا";
		arabic1199CurrencyName = "فرنك غرب أفريقيا";
		arabic1CurrencyPartName = "سنتيم";
		arabic2CurrencyPartName = "سنتيمان";
		arabic310CurrencyPartName = "سنتيمات";
		arabic1199CurrencyPartName = "سنتيماً";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case DKK:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = true;
		englishCurrencyName = "Danish Krone";
		englishPluralCurrencyName = "Danish Krones";
		englishCurrencyPartName = "øre";
		englishPluralCurrencyPartName = "øre";
		arabic1CurrencyName = "كرونة دانماركية";
		arabic2CurrencyName = "كرونة دانماركية";
		arabic310CurrencyName = "كرونة دانماركية";
		arabic1199CurrencyName = "كرونة دانماركية";
		arabic1CurrencyPartName = "øre";
		arabic2CurrencyPartName = "øre";
		arabic310CurrencyPartName = "øre";
		arabic1199CurrencyPartName = "øre";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;

	    case EUR:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "Euro";
		englishPluralCurrencyName = "Euros";
		englishCurrencyPartName = "Cent";
		englishPluralCurrencyPartName = "Cents";
		arabic1CurrencyName = "يورو";
		arabic2CurrencyName = "يورويان";
		arabic310CurrencyName = "يوروات";
		arabic1199CurrencyName = "يورو";
		arabic1CurrencyPartName = "سنت";
		arabic2CurrencyPartName = "سنتان";
		arabic310CurrencyPartName = "سنتات";
		arabic1199CurrencyPartName = "سنتاً";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;
	    case MAD:
		    currencyID = currency;
		    currencyCode = currency.toString();
		    isCurrencyNameFeminine = true;
		    englishCurrencyName = "Moroccan Dirham";
		    englishPluralCurrencyName = "Moroccan Dirhams";
		    englishCurrencyPartName = "Centime";
		    englishPluralCurrencyPartName = "Centimes";
		    arabic1CurrencyName = "درهم مغربي";
		    arabic2CurrencyName = "درهمان مغربيان";
		    arabic310CurrencyName = "دراهم مغربية";
		    arabic1199CurrencyName = "درهماً مغربياً";
		    arabic1CurrencyPartName = "سنتيم";
		    arabic2CurrencyPartName = "سنتيمان";
		    arabic310CurrencyPartName = "سنتيمات";
		    arabic1199CurrencyPartName = "سنتيماً";
		    partPrecision = 2;
		    isCurrencyPartNameFeminine = false;
		    break;

	    default:
		currencyID = currency;
		currencyCode = currency.toString();
		isCurrencyNameFeminine = false;
		englishCurrencyName = "U.S. Dollar";
		englishPluralCurrencyName = "U.S. Dollars";
		englishCurrencyPartName = "Cent";
		englishPluralCurrencyPartName = "Cents";
		arabic1CurrencyName = "دولار اميريكي";
		arabic2CurrencyName = "دولاران اميريكيان";
		arabic310CurrencyName = "دولارات اميريكية";
		arabic1199CurrencyName = "دولاراً اميريكياً";
		arabic1CurrencyPartName = "سنت";
		arabic2CurrencyPartName = "سنتان";
		arabic310CurrencyPartName = "سنتات";
		arabic1199CurrencyPartName = "سنتاً";
		partPrecision = 2;
		isCurrencyPartNameFeminine = false;
		break;
	}
    }

    public String getSuffixToAdd()
    {
        return suffixToAdd;
    }

    public void setSuffixToAdd(String suffixToAdd)
    {
        this.suffixToAdd = suffixToAdd;
    }
}
