package com.path.lib.common.util;

import java.util.HashMap;

import com.path.bo.common.NumberInWordsConstantsCommon;
import com.path.bo.common.NumberInWordsConstantsCommon.Currency;
import com.path.lib.log.Log;
import com.path.vo.common.CurrencyToWordsCO;

public class FrenchCurrencyInfo
{
    // frenchCurrencyInfo
    protected final static Log log = Log.getInstance();
    String currencyName;
    String currencyPlurName;
    String currencyDecName;
    String currencyPlurDecName;
    String currencyCode;

    String[] dizaineNames = {};
    String[] uniteNames1 = {};
    String[] uniteNames2 = {};
    String[] group = {};
    String[] pluralGroup = {};
    String[] currenciesDesc = new String[4];
    int partPrecision;
    public FrenchCurrencyInfo(CurrencyToWordsCO theCO, HashMap<String, String[]> hm )
    {
	uniteNames1 = hm.get(NumberInWordsConstantsCommon.ONES_KEY);
	dizaineNames = hm.get(NumberInWordsConstantsCommon.TENS_KEY);
	group = hm.get(NumberInWordsConstantsCommon.NUMGROUPS_KEY);
	pluralGroup = hm.get(NumberInWordsConstantsCommon.PLURALGROUPS_KEY);

	currenciesDesc[0] = currencyName = theCO.getCurrToWordsVO().getCURRENCY_NAME();
	currenciesDesc[1] = currencyDecName = theCO.getCurrToWordsVO().getDECIMAL_NAME();
	currenciesDesc[2] = currencyPlurName = theCO.getCurrToWordsVO().getPLURAL_CURRENCY_NAME();
	currenciesDesc[3] = currencyPlurDecName = theCO.getCurrToWordsVO().getPLURAL_DECIMAL_NAME();
	partPrecision = theCO.getCurrVO().getDECIMAL_POINTS() == null ? 2 : theCO.getCurrVO().getDECIMAL_POINTS().intValue();
    }

    public FrenchCurrencyInfo(Currency currency, HashMap<String, String[]> hm )
    {
	uniteNames1 = hm.get(NumberInWordsConstantsCommon.ONES_KEY);
	dizaineNames = hm.get(NumberInWordsConstantsCommon.TENS_KEY);
	group = hm.get(NumberInWordsConstantsCommon.NUMGROUPS_KEY);
	pluralGroup = hm.get(NumberInWordsConstantsCommon.PLURALGROUPS_KEY);

	switch (currency)
	{
	    case KWD:
		currencyName = "Dinar Koweétien";
		currencyPlurName = "Dinars Koweétiens";
		currencyDecName = "Fils";
		currencyPlurDecName = "Fils";
		partPrecision = 3;
		break;
	    case USD:
		currencyName = "Dollar Américain";
		currencyPlurName = "Dollars Américains";
		currencyDecName = "Cent";
		currencyPlurDecName = "Cents";
		partPrecision = 2;
		break;
	    case TND:
		// WIFAK need the currency naming to be lower case which is standard.
		currencyName = "Dinar Tunisien";
		currencyPlurName = "Dinars Tunisien";
		currencyDecName = "millime";
		currencyPlurDecName = "millimes";
		break;    
	    case BHD:
		currencyName = "Dinar de Bahreïn";
		currencyPlurName = "Dinars de Bahreïn";
		currencyDecName = "Fils";
		currencyPlurDecName = "Fils";
		partPrecision = 3;
		break;
	    case SAR:
		currencyName = "Riyal Saoudien";
		currencyPlurName = "Riyals Saoudiens";
		currencyDecName = "Halala";
		currencyPlurDecName = "Halalas";
		partPrecision = 2;
		break;
	    case DZD:
		currencyName = "Dinar Algérien";
		currencyPlurName = "Dinars Algériens";
		currencyDecName = "Centime";
		currencyPlurDecName = "Centimes";
		partPrecision = 3;
		break;
	    case AUD:
		currencyName = "Dollar Australien";
		currencyPlurName = "Dollars Australiens";
		currencyDecName = "Centime";
		currencyPlurDecName = "Centimes";
		partPrecision = 3;
		break;
	    case BDT:
		currencyName = "Taka Bangladais";
		currencyPlurName = "Takas Bangladais";
		currencyDecName = "Poisha";
		currencyPlurDecName = "Poishas";
		partPrecision = 3;
		break;
	    case CAD:
		currencyName = "Dollar Canadien";
		currencyPlurName = "Dollars Canadiens";
		currencyDecName = "Centime";
		currencyPlurDecName = "Centimes";
		partPrecision = 3;
		break;
	    case LKR:
		currencyName = "Roupie Sri Lankaise";
		currencyPlurName = "Roupies Sri Lankaises";
		currencyDecName = "Centime";
		currencyPlurDecName = "Centimes";
		partPrecision = 3;
		break;
	    case CYP:
		currencyName = "Livre Chypriote";
		currencyPlurName = "Livres Chypriotes";
		currencyDecName = "Centime";
		currencyPlurDecName = "Centimes";
		partPrecision = 3;
		break;
	    case DKK:
		currencyName = "Couronne Danoise";
		currencyPlurName = "Couronnes Danoises";
		currencyDecName = "øre";
		currencyPlurDecName = "øres";
		partPrecision = 2;
		break;
	    case GNF:
		currencyName = "Franc Guinéen";
		currencyPlurName = "Francs Guinéens";
		currencyDecName = "Centime";
		currencyPlurDecName = "Centimes";
		partPrecision = 3;
		break;
	    case HKD:
		currencyName = "Dollar de Hong Kong";
		currencyPlurName = "Dollars de Hong Kong";
		currencyDecName = "Centime";
		currencyPlurDecName = "Centimes";
		partPrecision = 3;
		break;
	    case INR:
		currencyName = "Roupie Indienne";
		currencyPlurName = "Roupies Indiennes";
		currencyDecName = "Paisa";
		currencyPlurDecName = "Paisas";
		partPrecision = 3;
		break;
	    case IDR:
		currencyName = "Roupie Indonésienne";
		currencyPlurName = "Roupies Indonésiennes";
		currencyDecName = "Sen";
		currencyPlurDecName = "Sens";
		partPrecision = 3;
		break;
	    case IQD:
		currencyName = "Dinar Iraquien";
		currencyPlurName = "Dinars Iraquiens";
		currencyDecName = currencyPlurDecName = "Fils";
		partPrecision = 3;
		break;
	    case LBP:
		currencyName = "Livre Libanaise";
		currencyPlurName = "Livres Libanaises";
		currencyDecName = "Piastre";
		currencyPlurDecName = "Piastres";
		partPrecision = 3;
		break;
	    case MYR:
		currencyName = "Ringgit Malaisien";
		currencyPlurName = "Ringgits Malaisiens";
		currencyDecName = "Sen";
		currencyPlurDecName = "Sens";
		partPrecision = 3;
		break;
	    case MVR:
		currencyName = "Roupie Maldivienne";
		currencyPlurName = "Roupies Maldiviennes";
		currencyDecName = "Lari";
		currencyPlurDecName = "Laris";
		partPrecision = 3;
		break;
	    case MRO:
		currencyName = "Ouguiya Mauritanienne";
		currencyPlurName = "Ouguiyas Mauritaniennes";
		currencyDecName = currencyPlurDecName = "Khoums";
		partPrecision = 3;
		break;
	    case MXN:
		currencyName = "Peso Mexicain";
		currencyPlurName = "Pesos Mexicains";
		currencyDecName = "Centime";
		currencyPlurDecName = "Centimes";
		partPrecision = 3;
		break;
	    case OMR:
		currencyName = "Rial Omanais";
		currencyPlurName = "Rials Omanais";
		currencyDecName = "Baiza";
		currencyPlurDecName = "Baiza";
		partPrecision = 3;
		break;
	    case NZD:
		currencyName = "Dollar Néo-Zélandais";
		currencyPlurName = "Dollars Néo-Zélandais";
		currencyDecName = "Centime";
		currencyPlurDecName = "Centimes";
		partPrecision = 3;
		break;
	    case NOK:
		currencyName = "Couronne Norvégienne";
		currencyPlurName = "Couronnes Norvégiennes";
		currencyDecName = "øre";
		currencyPlurDecName = "øres";
		partPrecision = 3;
		break;
	    case PKR:
		currencyName = "Roupie Pakistanaise";
		currencyPlurName = "Roupies Pakistanaises";
		currencyDecName = "paisa";
		currencyPlurDecName = "paisas";
		partPrecision = 3;
		break;
	    case PHP:
		currencyName = "Peso Philippin";
		currencyPlurName = "Pesos Philippins";
		currencyDecName = "Centime";
		currencyPlurDecName = "Centimes";
		partPrecision = 3;
		break;
	    case QAR:
		currencyName = "Rial Qatarien";
		currencyPlurName = "Rials Qatariens";
		currencyDecName = "Dirham";
		currencyPlurDecName = "Dirhams";
		partPrecision = 3;
		break;
	    case SGD:
		currencyName = "Dollar Singapourien";
		currencyPlurName = "Dollars Singapouriens";
		currencyDecName = "Centime";
		currencyPlurDecName = "Centimes";
		partPrecision = 3;
		break;
	    case SEK:
		currencyName = "Couronne Suédoise";
		currencyPlurName = "Couronnes Suédoises";
		currencyDecName = "øres";
		currencyPlurDecName = "øres";
		partPrecision = 3;
		break;
	    case CHF:
		currencyName = "Franc Suisse";
		currencyPlurName = "Francs Suisses";
		currencyDecName = "Centime";
		currencyPlurDecName = "Centimes";
		partPrecision = 3;
		break;
	    case EGP:
		currencyName = "Livre égyptienne";
		currencyPlurName = "Livres égyptiennes";
		currencyDecName = "Piastre";
		currencyPlurDecName = "Piastres";
		partPrecision = 3;
		break;
	    case GBP:
		currencyName = "Livre Sterling";
		currencyPlurName = "Livres Sterling";
		currencyDecName = "Sou";
		currencyPlurDecName = "Sous";
		partPrecision = 3;
		break;
	    case YER:
		currencyName = "Rial Yéménite";
		currencyPlurName = "Rials Yéménites";
		currencyDecName = currencyPlurDecName = "Fils";
		partPrecision = 3;
		break;
	    case TRY:
		currencyName = "Livre Turque";
		currencyPlurName = "Livres Turques";
		currencyDecName = currencyPlurDecName = "Kurus";
		partPrecision = 3;
		break;
	    case XOF:
		currencyName = "Franc CFA Ouest Africain (XOF)";
		currencyPlurName = "Francs CFA Ouest Africain (XOF)";
		currencyDecName = "Centime";
		currencyPlurDecName = "Centimes";
		partPrecision = 3;
		break;
	    case EUR:
		currencyName = "Euro";
		currencyPlurName = "Euros";
		currencyDecName = "Centime";
		currencyPlurDecName = "Centimes";
		partPrecision = 2;
		break;
	    case MAD:
		  currencyName = "Dirham Marocain";
		  currencyPlurName = "Dirhams Marocains";
		  currencyDecName = "Centime";
		  currencyPlurDecName = "Centimes";
		  break;
	    default:
		currencyName = "Dollar Américain";
		currencyPlurName = "Dollars Américains";
		currencyDecName = "Cent";
		currencyPlurDecName = "Cents";
		partPrecision = 2;
		break;
	}
	currenciesDesc[0] = currencyName;
	currenciesDesc[1] = currencyDecName;
	currenciesDesc[2] = currencyPlurName;
	currenciesDesc[3] = currencyPlurDecName;
    }

    public String getCurrencyName()
    {
	return currencyName;
    }

    public void setCurrencyName(String currencyName)
    {
	this.currencyName = currencyName;
    }

    public String getCurrencyDecName()
    {
	return currencyDecName;
    }

    public void setCurrencyDecName(String currencyDecName)
    {
	this.currencyDecName = currencyDecName;
    }

    public String getCurrencyCode()
    {
	return currencyCode;
    }

    public void setCurrencyCode(String currencyCode)
    {
	this.currencyCode = currencyCode;
    }

    public String getCurrencyPlurName()
    {
	return currencyPlurName;
    }

    public void setCurrencyPlurName(String currencyPlurName)
    {
	this.currencyPlurName = currencyPlurName;
    }

    public String getCurrencyPlurDecName()
    {
	return currencyPlurDecName;
    }

    public void setCurrencyPlurDecName(String currencyPlurDecName)
    {
	this.currencyPlurDecName = currencyPlurDecName;
    }

    public String[] getDizaineNames()
    {
	return dizaineNames;
    }

    public void setDizaineNames(String... dizaineNames)
    {
	this.dizaineNames = dizaineNames;
    }

    public String[] getUniteNames1()
    {
	return uniteNames1;
    }

    public void setUniteNames1(String... uniteNames1)
    {
	this.uniteNames1 = uniteNames1;
    }

    public String[] getUniteNames2()
    {
	return uniteNames2;
    }

    public void setUniteNames2(String... uniteNames2)
    {
	this.uniteNames2 = uniteNames2;
    }

    public String[] getGroup()
    {
	return group;
    }

    public void setGroup(String... group)
    {
	this.group = group;
    }

    public String[] getPluralGroup()
    {
	return pluralGroup;
    }

    public void setPluralGroup(String... pluralGroup)
    {
	this.pluralGroup = pluralGroup;
    }

    public String[] getCurrenciesDesc()
    {
	return currenciesDesc;
    }

    public void setCurrenciesDesc(String... currenciesDesc)
    {
	this.currenciesDesc = currenciesDesc;
    }

    public int getPartPrecision()
    {
        return partPrecision;
    }

    public void setPartPrecision(int partPrecision)
    {
        this.partPrecision = partPrecision;
    }

}
