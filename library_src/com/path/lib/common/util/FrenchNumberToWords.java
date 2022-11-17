package com.path.lib.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;

import com.path.bo.common.CommonLibBO;
import com.path.bo.common.ConstantsCommon;
import com.path.bo.common.NumberInWordsConstantsCommon.Currency;
import com.path.dbmaps.vo.CURRENCIESVO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.log.Log;
import com.path.vo.common.CurrencyToWordsCO;

public final class FrenchNumberToWords {
	private final static Log log = Log.getInstance();
	private static String frenchSuffixText = "seulement.";

	/**
	 * private constructor to prevent the class being instantiated since all methods
	 * are static
	 */
	private FrenchNumberToWords() {
		Log.getInstance().error("This Class is utility class cannot be instantiated");
	}

	private static String convertZeroToHundred(int number, FrenchCurrencyInfo frenchCurrencyInfo) {

		int laDizaine = number / 10;
		int lUnite = number % 10;
		String resultat = "";

		switch (laDizaine) {
		case 1:
		case 7:
		case 9:
			lUnite = lUnite + 10;
			break;
		default:
		}

		// séparateur "-" "et" ""
		String laLiaison = "";
		if (laDizaine > 1) {
			laLiaison = "-";
		}
		// cas particuliers
		switch (lUnite) {
		case 0:
			laLiaison = "";
			break;
		case 1:
			if (laDizaine == 8) {
				laLiaison = "-";
			} else {
				laLiaison = " et ";
			}
			break;
		case 11:
			if (laDizaine == 7) {
				laLiaison = " et ";
			}
			break;
		default:
		}

		// dizaines en lettres
		switch (laDizaine) {
		case 0:
		case 1:
			resultat = frenchCurrencyInfo.uniteNames1[lUnite];
			break;
		case 8:
			if (lUnite == 0) {
				resultat = frenchCurrencyInfo.dizaineNames[laDizaine - 2];
			} else {
				resultat = frenchCurrencyInfo.dizaineNames[laDizaine - 2] + laLiaison
						+ frenchCurrencyInfo.uniteNames1[lUnite];
			}
			break;
		default:
			resultat = frenchCurrencyInfo.dizaineNames[laDizaine - 2] + laLiaison
					+ frenchCurrencyInfo.uniteNames1[lUnite];
			break;
		}
		return resultat;
	}

	private static String convertLessThanOneThousand(int number, FrenchCurrencyInfo frenchCurrencyInfo) {

		int lesCentaines = number / 100;
		int leReste = number % 100;
		String sReste = convertZeroToHundred(leReste, frenchCurrencyInfo);

		String resultat;
		switch (lesCentaines) {
		case 0:
			resultat = sReste;
			break;
		case 1:
			if (leReste > 0) {
				resultat = "Cent " + sReste;
			} else {
				resultat = "Cent";
			}
			break;
		default:
			if (leReste > 0) {
				resultat = frenchCurrencyInfo.uniteNames1[lesCentaines] + " Cent " + sReste;
			} else {
				resultat = frenchCurrencyInfo.uniteNames1[lesCentaines] + " Cents";
			}
			break;
		}
		return resultat;
	}

	public static String convert(BigDecimal number, String currencyCode) throws BaseException {
		return convert(number, currencyCode, null, ConstantsCommon.LANGUAGE_FRENCH);
	}

	public static String convert(BigDecimal number, String currencyCode, BigDecimal decDigits) throws BaseException {
		return convert(number, currencyCode, decDigits, ConstantsCommon.LANGUAGE_FRENCH);
	}

	/**
	 * Method that converts Number to French letters with provided decimal digit
	 * Number
	 * 
	 * @param number       Value to convert
	 * @param currencyCode ISO currency Code
	 * @param decDigits    number of decimal Digits
	 * @param langCode     Language Code which will return the DB details
	 * @return
	 */
	public static String convert(BigDecimal value, String currencyCode, BigDecimal decDigits, String langCode)
			throws BaseException {
		return convert(value, currencyCode, decDigits, ConstantsCommon.LANGUAGE_FRENCH, null);
	}

	/**
	 * Method that converts Number to French letters with provided decimal digit
	 * Number
	 * 
	 * @param number       Value to convert
	 * @param currencyCode ISO currency Code
	 * @param decDigits    number of decimal Digits
	 * @param langCode     Language Code which will return the DB details
	 * @param suffixToAdd  suffix to add at end or returned result
	 * @return
	 */
	public static String convert(BigDecimal value, String currencyCodeParam, BigDecimal decDigits, String langCode,
			String suffixToAdd) throws BaseException {
		String currencyCode = currencyCodeParam;
		if (StringUtil.nullToEmpty(currencyCode).isEmpty()) {
			currencyCode = ConstantsCommon.USD_ISO_CODE;
		}
		String theLang = langCode;
		if (StringUtil.nullToEmpty(theLang).isEmpty()) {
			theLang = ConstantsCommon.LANGUAGE_FRENCH;
		}
		// initialize the hashmaps of corresponding numbers in word for this
		// language (values are cached either from defaults or from db
		// overridden values)
		CURRENCIESVO currVO = new CURRENCIESVO();
		currVO.setDECIMAL_POINTS(decDigits);
		currVO.setCY_ISO_CODE(currencyCode);
		CommonLibBO commonLibBO = (CommonLibBO) ApplicationContextProvider.getApplicationContext()
				.getBean("commonLibBO");
		CurrencyToWordsCO theCO = commonLibBO.returnCurrencyToWordsCO(theLang, currVO);
		HashMap<String, String[]> hm = commonLibBO.returnNumInWordsByLangDefaultMap(theLang);
		FrenchCurrencyInfo frenchCurrencyInfo;
		if (theCO == null) {
			Currency currency;
			try {
				currency = Currency.valueOf(currencyCode);
			} catch (Exception e) {
				currency = Currency.valueOf(ConstantsCommon.USD_ISO_CODE);
				log.error("Missing Currency definition for " + currencyCode);
			}
			// curr = (new FrenchNumberToWords()).returnCurrencyName(currency);
			frenchCurrencyInfo = new FrenchCurrencyInfo(currency, hm);
			if (decDigits != null) {
				frenchCurrencyInfo.partPrecision = decDigits.intValue();
			}
		} else {
			if (theCO.getCurrVO().getDECIMAL_POINTS() == null) {
				int partPrecision = 2;
				try {
					Currency _currency = Currency.valueOf(currencyCode);
					partPrecision = new CurrencyInfo(_currency).getPartPrecision();
				} catch (Exception e) {
					// when currency does not exist in currencyInfo as static use 2 as default
					log.warning("[convert]Currency " + currencyCode
							+ " Does not Exists in defined enumiration precision Decimal points of 2 is defaulted");
				}
				theCO.getCurrVO().setDECIMAL_POINTS(new BigDecimal(partPrecision));
			}
			frenchCurrencyInfo = new FrenchCurrencyInfo(theCO, hm);
		}

		String sufToAdd = frenchSuffixText;
		// check if suffix is provided BMOUPI170483
		if (suffixToAdd != null) {
			sufToAdd = suffixToAdd;
		}
		if (!sufToAdd.trim().isEmpty()) {
			sufToAdd = " " + sufToAdd;
		}

		BigDecimal number = value.setScale(frenchCurrencyInfo.partPrecision, BigDecimal.ROUND_HALF_DOWN);

		if (number.toString().indexOf(".") > -1) {
			long longPart = (long) Math.floor(number.doubleValue());
			int decPart = 0;
			String[] splits = number.toString().replaceAll(",", "").split("\\.");

			if (splits.length > 1) {
				String decValue = splits[1];
				// check decimal length to compare with needed one
				if (splits[1].length() > frenchCurrencyInfo.getPartPrecision()) {
					decValue = splits[1].substring(0, frenchCurrencyInfo.getPartPrecision());
				}
				decPart = Integer.parseInt(decValue);
			}
			// int decPart = (int)Math.floor( ( number.doubleValue() - longPart
			// ) * 100.0f ) ;
			return ((longPart > 0)
					? (FrenchNumberToWords.convert(longPart, frenchCurrencyInfo) + " "
							+ (longPart > 1 ? frenchCurrencyInfo.currenciesDesc[2]
									: frenchCurrencyInfo.currenciesDesc[0]))
					: "").concat(
							(decPart > 0 ? " et " + FrenchNumberToWords.convert(decPart, frenchCurrencyInfo).concat(" ")
									.concat((decPart > 1 ? frenchCurrencyInfo.currenciesDesc[3]
											: frenchCurrencyInfo.currenciesDesc[1]))
									: ""))
							.concat(sufToAdd);
		} else {
			return FrenchNumberToWords.convert(number.longValue(), frenchCurrencyInfo) + " "
					+ (number.longValue() > 1 ? frenchCurrencyInfo.currenciesDesc[2]
							: frenchCurrencyInfo.currenciesDesc[0])
					+ sufToAdd;
		}
	}

	public static String convert(long number, FrenchCurrencyInfo frenchCurrencyInfo) {
		// 0 999 999 999 999
		if (number == 0) {
			return "zéro";
		}

		String snumber = Long.toString(number);

		// pad des "0"
		String mask = "000000000000";
		DecimalFormat df = new DecimalFormat(mask);
		snumber = df.format(number);

		// XXXnnnnnnnnn
		int lesMilliards = Integer.parseInt(snumber.substring(0, 3));
		// nnnXXXnnnnnn
		int lesMillions = Integer.parseInt(snumber.substring(3, 6));
		// nnnnnnXXXnnn
		int lesCentMille = Integer.parseInt(snumber.substring(6, 9));
		// nnnnnnnnnXXX
		int lesMille = Integer.parseInt(snumber.substring(9, 12));

		String tradMilliards;
		switch (lesMilliards) {
		case 0:
			tradMilliards = "";
			break;
		case 1:
			tradMilliards = convertLessThanOneThousand(lesMilliards, frenchCurrencyInfo) + " "
					+ frenchCurrencyInfo.group[3] + " ";
			break;
		default:
			tradMilliards = convertLessThanOneThousand(lesMilliards, frenchCurrencyInfo) + " "
					+ frenchCurrencyInfo.pluralGroup[3] + " ";
			break;
		}
		String resultat = tradMilliards;

		String tradMillions;
		switch (lesMillions) {
		case 0:
			tradMillions = "";
			break;
		case 1:
			tradMillions = convertLessThanOneThousand(lesMillions, frenchCurrencyInfo) + " "
					+ frenchCurrencyInfo.group[2] + " ";
			break;
		default:
			tradMillions = convertLessThanOneThousand(lesMillions, frenchCurrencyInfo) + " "
					+ frenchCurrencyInfo.pluralGroup[2] + " ";
			break;
		}
		resultat = resultat.concat(tradMillions);

		String tradCentMille;
		switch (lesCentMille) {
		case 0:
			tradCentMille = "";
			break;
		case 1:
			tradCentMille = frenchCurrencyInfo.group[1] + " ";
			break;
		default:
			tradCentMille = convertLessThanOneThousand(lesCentMille, frenchCurrencyInfo) + " "
					+ frenchCurrencyInfo.group[1] + " ";
			break;
		}
		resultat = resultat.concat(tradCentMille);

		String tradMille;
		tradMille = convertLessThanOneThousand(lesMille, frenchCurrencyInfo);
		resultat = resultat.concat(tradMille);

		return resultat;
	}

}
