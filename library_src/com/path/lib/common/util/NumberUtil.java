package com.path.lib.common.util;


import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.path.bo.common.ConstantsCommon;
import com.path.lib.common.exception.BaseException;
import com.path.lib.log.Log;
import com.path.struts2.lib.common.BaseObject;
import com.path.struts2.lib.common.RootUtil;

/**
 * 
 * Copyright 2012, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: DeniskHaddad
 *
 * NumberUtil.java used to  manipulate number objects
 */
public final class NumberUtil
{
    /**
     * private constructor to prevent the class being instantiated since all
     * methods are static
     */
    private NumberUtil()
    {
	Log.getInstance().error("This Class is utility class cannot be instantiated");
    }
 private static final Log log = Log.getInstance();
 public volatile static HashMap<String,List<String>> objectBigDecimalPropNames = new HashMap<String, List<String>>();
 /**
  * check if is number and Ratio (between zero and 100)
  * @param val
  * @return boolean
  */
 public static boolean isNumberRatio(Object val) throws BaseException
 {
 	if(val instanceof Number && (NumberUtil.toDouble(val) >= 0 && NumberUtil.toDouble(val) <= 100))
 	{
 	    return true;
 	}
 	return false;
 }

 /**
  * 
  * Used for formating Number with multiple option
  * #,##0.00;(###.000);0;0.00
  * positiveFormat;negativeFormat;zeroFormat;nullFormat
  * @param theValue
  * @param format
  * @return
  * @throws BaseException
  */
 public static String multiFormatNumber(Number theValue, String format) throws BaseException
 {
     return multiFormatNumber(theValue, format, null, null);
 }
 /**
  * Used for formating Number with multiple option, and providing the decimal and/or group separator
  * default system/user group separator and decimal separator will be considered if not provided
  * #,##0.00;(###.000);0;0.00
  * @param theValue value to format
  * @param format the format pattern with standard group "," and decimal "." separators
  * @param groupSep group separator in output result
  * @param decimalSep decimal separator in output result
  * @return formated value
  * @throws BaseException
  */
 public static String multiFormatNumber(Number theValue, String format, String groupSep, String decimalSep) throws BaseException
 {
     try
     {
 	String result = null;
 	Number value = theValue;
 	DecimalFormat _df = new DecimalFormat();
 	_df.setRoundingMode(RoundingMode.HALF_UP);
 	if(format.indexOf(";") == -1)
 	{
 	   _df.applyPattern(format);
	    result = _df.format(value);
	    
 	}
 	else
 	{

 	    String[] formatting = null;
 	    String[] formatType = { "posFrmt", "negFrmt", "zeroFrmt", "nullFrmt" };
 	    HashMap<String,String> formatMap = new HashMap<String,String>();
 	    formatting = format.split(";");

 	    for(int i = 0; i < formatting.length; i++)
 	    {
 		formatMap.put(formatType[i], formatting[i]);
 	    }

 	  
 	    if(value == null)
 	    {
 		if(formatMap.get("nullFrmt") != null)
 		{
 		    _df.applyPattern(formatMap.get("nullFrmt"));
 		    result = _df.format(0);
 		}
 	    }
 	    else
 	    {
 		Double valDouble = value.doubleValue();
 		if(valDouble > 0)
 		{
 		    if(formatMap.get("posFrmt") != null)
 		    {
 			_df.applyPattern(formatMap.get("posFrmt"));
 			result = _df.format(value);
 		    }
 		}
 		else if(valDouble < 0)
 		{
 		    if(formatMap.get("negFrmt") != null)
 		    {
 			_df.applyPattern(formatMap.get("negFrmt"));
 			value = Math.abs(valDouble);
 			result = _df.format(value);
 		    }
 		}
 		else if(valDouble == 0)
 		{
 		    if(formatMap.get("zeroFrmt") == null)
 		    {
 			_df.applyPattern(formatMap.get("posFrmt"));
 		    }
 		    else
 		    {
 			_df.applyPattern(formatMap.get("zeroFrmt")); 			
 		    }
 		    result = _df.format(valDouble);
 		}
 	    }
 	}
 	
 	String returnFrmt = null;
 	if(result != null)
 	{
 	    String theGrpSep = groupSep;
 	    String theDecSep = decimalSep;
 	    if(theGrpSep == null || theGrpSep.isEmpty() || theDecSep == null || theDecSep.isEmpty())
 	    {
 		// return group and decimal separator from session if any
 		String[] grpDecArr = returnGroupDecimalSep();
 		if(theGrpSep == null || theGrpSep.isEmpty())
 		{
 		    theGrpSep = grpDecArr[0];
 		}
 		if(theDecSep == null || theDecSep.isEmpty())
 		{
 		    theDecSep = grpDecArr[1];
 		}
 	    }
 	    // need first to replace by #grpSep# so that in case the group separator is equal to dot . the replacement will be correct
 	    returnFrmt =  result.replace(",","#grp#");
 	    returnFrmt = returnFrmt.replace(".",theDecSep+"");
 	    returnFrmt = returnFrmt.replace("#grp#",theGrpSep+"");
 	}
	 
	 return returnFrmt;
     }
     catch(Exception e)
     {
 	  throw new BaseException(e);
     }
 }

 /**
  * Method that checks if the calling is at presentation side to return the Group and Decimal Separator of user
  * @return [group,decimal] array values
  */
 public static String[] returnGroupDecimalSep()
 {
     String[] returnVal = new String[]{",","."};
     // if at level of presentation level then take the separators from session
     if(ActionContext.getContext() != null && ServletActionContext.getRequest() != null)
     {
 	 HashMap<String , Object> theFormats = RootUtil.returnNumberFormat(ServletActionContext.getRequest().getSession());
 	 returnVal[0] = StringUtil.nullEmptyToValue(theFormats.get("groupSepa"), ",");
 	 returnVal[1] = StringUtil.nullEmptyToValue(theFormats.get("decimalSepa"), ".");
     }
     else
     {
 	// if there is not session then need to take the separators from initial load if available
 	if(ConstantsCommon.PATH_GROUP_SEPARATOR != null)
 	{
 	    returnVal[0] = ConstantsCommon.PATH_GROUP_SEPARATOR;
 	}
 	if(ConstantsCommon.PATH_DECIMAL_SEPARATOR != null)
 	{
 	    returnVal[1] = ConstantsCommon.PATH_DECIMAL_SEPARATOR;
 	}
     }
     return returnVal;
 }
 /**
  * Method Used to return the format given Number of Decimal digits
  * Powerbuilder function:  f_currency_mask
  * @param cyDec number of digits after decimal
  * @return
  */
 public static String currencyMask(BigDecimal cyDec) throws BaseException
 {
 String theFormat = "#,##0";
 if(NumberUtil.nullToZero(cyDec).intValue() > 0)
 {
     theFormat = theFormat.concat(".");
     String nbDecimalZeros = fill("0", cyDec);
     theFormat = theFormat.concat(nbDecimalZeros);
 }
 return theFormat;
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

     if(nbTimes == null || what == null)
     {
	 return what;
     }
     
     StringBuffer toRet = new StringBuffer();
     for(int i = 0; i < nbTimes.intValue(); i++)
     {
 	toRet.append(what);
     }
     // substring if constructed result length larger than nbTimes provided
     if(toRet.length() > nbTimes.intValue() && nbTimes.intValue() >= 0)
     {
	 toRet.setLength(nbTimes.intValue());
     }
     return toRet.toString();
 }

     /**
      * @param amount = Amount to be validated
      * @param minAmount = Minimum amount of the range
      * @param maxAmount = Maximum amount of the range
      * @param includeEqualInValidation = Flag to consider Min and Max amount in
      *            the condition if the flag is TRUE checking as follows ( amount
      *            >= minAmount || amount <= MaxAmount ) if the flag is FALSE
      *            checking as follows ( amount > minAmount || amount < MaxAmount
      *            )
      * @return
      */
     public static boolean checkValueBetween(BigDecimal amount, BigDecimal minAmount, BigDecimal maxAmount,
 	    boolean includeEqualInValidation)
     {
 	if(includeEqualInValidation && amount.compareTo(minAmount) >= 0 && amount.compareTo(maxAmount) <=0 )
 	{
 	    return true;
 	}
 	else
 	if(!includeEqualInValidation && amount.compareTo(minAmount) > 0 && amount.compareTo(maxAmount) <0)
 	{
 		return true;
 	}
 	return false;
     }
 /**
  * Replaces the null with the zero
  */
 public static String nullToZero(String s)
 {
  if(s == null || s.equals("null") || s.trim().length() == 0)
  {
   return "0";
  }
  else
  {
   return s;
  }
 }

 /**
  * Replaces the null by Zero when provided with a BigDecimal
  * @param b BigDecimal
  * @return BigDecimal
  */
 public static BigDecimal nullToZero(BigDecimal b)
 {
  if(isEmptyDecimal(b))
  {
   return BigDecimal.ZERO;
  }
  else
  {
   return b;
  }
 }
 /**
  * 
  * Used for replacing the null by Given Big Decima
  * 
  * @param b BigDecimal Value to be Checked
  * @param theValue
  * @return BigDecimal Correct Value
  */
 public static BigDecimal nullEmptyToValue(BigDecimal b,BigDecimal theValue)
 {
     if(isEmptyDecimal(b))
     {
	 return theValue;
     }
     else
     {
	 return b;
     }
 }

 /**
  * Replaces the null by the Zero when provided with an Integer
  * @param i Integer
  * @return int
  */
 public static int nullToZero(Integer i)
 {
  if(i == null)
  {
   return 0;
  }
  else
  {
   return i.intValue();
  }
 }

 /**
  * Replaces the null by the Zero when provided with a Double
  * @param d Double
  * @return double
  */
 public static double nullToZero(Double d)
 {
  if(d == null)
  {
   return 0;
  }
  else
  {
   return d.doubleValue();
  }
 }

 /**
  * Replaces the null by the Zero when provided with a Float
  * @param f Float
  * @return float
  */
 public static float nullToZero(Float f)
 {
  if(f == null)
  {
   return 0;
  }
  else
  {
   return f.floatValue();
  }
 }
 
 /**
  * 
  * Used for add leading Zeros to any numeric Number, if Numeric is Decimal points then it will be truncated
  * 
  * @param numeric any numeric Object , decimal points will be truncated
  * @param nbLength Length of the result to be obtained with leading Zeros 
  * @return
  */
 public static String addLeadingZeros(Number numeric,int nbLength)
 {
    Double theNumerDbl = toDoubleObj(numeric);
    Integer theNumerInt  = theNumerDbl.intValue();
    String thePattern = "%0"+nbLength+"d";
    return String.format(thePattern, theNumerInt);
 }

 /**
  * Converts an object input to a double.
  * @param o Object
  * @return double
  */
 public static double toDouble(java.lang.Object o)
 {
  if(o == null)
  {
   return 0;
  }
  else if(o.toString().equals(""))
  {
    return 0;
  }
   else
   {
    return Double.parseDouble(o.toString());
   }
 }
 /**
  * Converts an object input to a Double.
  * @param o Object
  * @return Double
  */
 public static Double toDoubleObj(java.lang.Object o)
 {
	 if(o == null)
	 {
	     return Double.valueOf(0);
	 }
	 else if(o.toString().equals(""))
	 {
	     return Double.valueOf(0);
	 }
	 else
	 {
	     return(Double.valueOf(o.toString()));
	 }
 }

 /**
  * Converts an object input to an int.
  * @param o Object
  * @return int
  */
 public static int toInt(java.lang.Object o)
 {
  if(o == null)
  {
   return 0;
  }
  else if(o.toString().equals(""))
  {
    return 0;
  }
   else
   {
    return Integer.parseInt(o.toString());
   }
 }
 /**
  * Converts an object input to an Integer.
  * @param o Object
  * @return int
  */
 public static Integer toInteger(java.lang.Object o)
 {
	 if(o == null)
	 {
	     return 0;
	 }
	 else if(o.toString().equals(""))
	 {
	     return 0;
	 }
	else
	{
	    return Integer.valueOf(o.toString());
	}
 }

 /**
  * Format the given output according to a given pattern
  * @param val Object
  * @param pattern String
  * @return String
  */
 /**
  * Format the given output according to a given pattern
  * @param val Object
  * @param pattern String
  * @return String
  */
 public static String format(Object val, String pattern)
 {
     try
     {  
	 return format(val, pattern, null, null);
     }
     catch(Exception ex)
     {
	 log.error(ex, "[NumberUtil] Error caught in method format");
	 return "";
     }
 }
 /**
  * Method for formatting the value based on pattern and provided group and decimal separator
  * @param val the value to format
  * @param pattern the standard pattern containing . as decimal separator and , as group separator
  * @param groupsepa group separator needed as output, if null/empty then system/user default will be considered
  * @param decimalsepa decimal separator needed as output,, if null/empty then system/user default will be considered
  * @return formatted value
  */
  public static String format(Object val, String pattern, String groupsepa, String decimalsepa)
  {
      try
      {
 	 String theGrpSep = groupsepa;
 	 String theDecSep = decimalsepa;
 	 if(theGrpSep == null || theGrpSep.isEmpty() || theDecSep == null || theDecSep.isEmpty())
 	 {
 	     // return group and decimal separator from session if any
 	     String[] grpDecArr = returnGroupDecimalSep();
 	     if(theGrpSep == null || theGrpSep.isEmpty())
 	     {
 		 theGrpSep = grpDecArr[0];
 	     }
 	     if(theDecSep == null || theDecSep.isEmpty())
 	     {
 		 theDecSep = grpDecArr[1];
 	     }
 	 }
 	 return format(val,pattern, theGrpSep.charAt(0), theDecSep.charAt(0));
      }
      catch(Exception e )
      {
 	 log.error(e, "[NumberUtil] Error caught in method format");
 	 return "";
      }
 }

 /**
  * Return a formated number using the group-separator and decimal-separator
  * @param val Object
  * @param pattern String
  * @param groupsepa String
  * @param decimalsepa String
  * @return String
  */
 public static String format(Object val, String pattern, char groupsepa, char decimalsepa)
 {
     if(val == null)
     {
	 return "";
     } 
     try
     {
	 DecimalFormat _df = new DecimalFormat();
	 _df.setRoundingMode(RoundingMode.HALF_UP);
	 _df.applyPattern(pattern);

	 String standardFrmt =  _df.format(val);
	 // need first to replace by #grpSep# so that in case the group separator is equal to dot . the replacement will be correct
	 String returnFrmt =  standardFrmt.replace(",","#grp#");
	 returnFrmt = returnFrmt.replace(".",decimalsepa+"");
	 returnFrmt = returnFrmt.replace("#grp#",groupsepa+"");
	 return returnFrmt;

     }
     catch(Exception ex)
     {
	 log.error(ex, "[NumberUtil] Error caught in method format");
	 return "";
     }
}

 /**
  * Return the int formatted according to the given pattern String
  * @param val int
  * @param pattern String
  * @return String
  */
 public static String format(int val, String pattern)
 {
  return format(Integer.valueOf(val), pattern);
 }

 /**
  * Return the double formatted according to the given pattern String
  * @param val double
  * @param pattern String
  * @return String
  */
 public static String format(double val, String pattern)
 {
  return format(Double.valueOf(val), pattern);
 }

 /**
  * Return the float formatted according to the given pattern String
  * @param val float
  * @param pattern String
  * @return String
  */
 public static String format(float val, String pattern)
 {
  return format(Float.valueOf(val), pattern);
 }

 /**
  * returns the int value of the input string.
  * If the input string is null or empty string it returns zero else it returns the value of Integer.parseInt call
  * @param val String
  * @return int
  */
 public static int parseInt(String val)
 {
  if(val == null || val.trim().length() == 0)
  {
   return 0;
  }
  else
  {
   return Integer.parseInt(val);
  }
 }

 /**
  * returns the double value of the input string.
  * If the input string is null or empty string it returns zero else it returns the value of Double.parseDouble call
  * @param val String
  * @return double
  */
 public static double parseDouble(String val)
 {
  if(val == null || val.trim().length() == 0)
  {
   return 0;
  }
  else
  {
   return Double.parseDouble(val);
  }
 }
/**
 * check if the given string is number or not
 * @param val
 * @return
 */
 public static boolean isNumber(String val)
 {
	 if(val == null || val.trim().isEmpty())
	 {
		 return false;
	 }
	 else
	 {
		 Pattern patternForNumber = Pattern.compile("^[+-]?(([0-9]+\\.?[0-9]+)|[0-9]+)");
		 return patternForNumber.matcher(val).matches();
	 }
 }
/**
 * 
 * Used for returning true if the number is empty value received from the client side or not available 
 * 
 * @param myNumber Number to check
 * @return true if BigDecimal is empty
 */
 public static boolean isEmptyDecimal(BigDecimal myNumber)
 {
     return (myNumber == null || ConstantsCommon.EMPTY_BIGDECIMAL_VALUE.equals(myNumber));
 }
 
 /**
  * 
  * Used for converting emptyBigDecimal to Null
  * 
  * @param myNumber Number to check
  * @return  BigDecimal / null is empty
  */
  public static BigDecimal emptyDecimalToNull(BigDecimal myNumber)
  {
      if(ConstantsCommon.EMPTY_BIGDECIMAL_VALUE.equals(myNumber))
      {
 	 return null;
      }
      else
      {
 	 return myNumber;
      }
  }
  
  /**
   * 
   * Used for converting emptyBigDecimal to Value
   * 
   * @param myNumber Number to check
   * @return  BigDecimal / zero is empty
   */
   public static BigDecimal emptyDecimalToZero(BigDecimal myNumber)
   {
       if(myNumber == null || ConstantsCommon.EMPTY_BIGDECIMAL_VALUE.equals(myNumber))
       {
  	 return BigDecimal.ZERO;
       }
       else
       {
  	 return myNumber;
       }
   }
 /**
 * Round a double value's decimal to specific no of decimal, Round UP is performed  
 * @param theVal
 * @param periods
 * @return double
 */
    public static double round(double theVal, int periods)
    {
	BigDecimal result = roundToBigDecimal(BigDecimal.valueOf(theVal),periods);
	return result.doubleValue();
    }
/**
 * Round a double value's decimal to specific no of decimal, Round up is applied
 * @param theVal
 * @param periods
 * @returndouble
 */
    public static Double round(Double theVal, Long periods)
    {
	Double val = theVal;
	if(val == null)
	{
	    return null;
	}
	BigDecimal result = roundToBigDecimal(BigDecimal.valueOf(theVal).doubleValue(),periods);
	return result.doubleValue();
    }

    /**
     * 
     * @author marwanmaddah
     * @date   Jul 1, 2014
     * @param theObj
     * @param list void
     *
     */
    private static void cachBigDecimalProp(Object obj,StringBuffer objPath,List<String> list,boolean isList)
    {
	try
	{
	    
	    /**
	     * get all the properties from theObj
	     */
	    
	    Object theObj = null;
	    /**
	     * isList : it is mean that the type of the Object is a list
	     */
	    if(isList)
	    {
		ArrayList<Object> lst  = (ArrayList<Object>)obj; 
		theObj = (lst).get(0);
		/**
		 * in case the object is a list will reset all the Objects that are exists inside the list 
		 * and in the first entry the properties will be added to the list to add them to the HashMap 
		 */
		for(int i=0;i<lst.size();i++)
		{
		  Object currObj = lst.get(i);
		  /**
		   * in case of first entry the properties will be added to the list to be cached 
		   * otherwise will not be added to avoid the duplication in the cached hashMap
		   */
		  boolean firstEntry = (i==0);
		  cachCurrenObj(currObj, objPath, list,firstEntry);
		}
	    }
	    else
	    {
		theObj = obj;
		cachCurrenObj(theObj, objPath, list,true);
	    }
	}
	catch(Exception ex)
	{
	    log.error(ex,"ERROR in cachBigDecimalProp NumberUtils "); 
	}
    }

    /**
     * @author marwanmaddah
     * @date   Jul 14, 2014
     * @param objPath
     * @param list
     * @param theObj
     * @param disc
     * @throws Exception
     * used to add the object in the list and after that will be added to the hashMap
     */
    private static void cachCurrenObj(Object theObj, StringBuffer objPath, List<String> list,boolean firstEntry)

    {
	StringBuffer currPath;
	try
	{
	    PropertyDescriptor[] disc = PropertyUtils.getPropertyDescriptors(theObj);
	    for(int i = 0; i < disc.length; i++)
	    {
		currPath = new StringBuffer(objPath);
		PropertyDescriptor di = disc[i];
		String propName = di.getName();
		if(!"class".equalsIgnoreCase(propName))
		{
		    Class theType = di.getPropertyType();
		    Object t = PropertyUtils.getProperty(theObj, propName);
		    /**
		     * in case the current property is an instance of Base
		     * Object a recursive call to get and cach all the
		     * properties from all the chain of Objects that are exists
		     * inside the Main Object
		     */
		    if(t instanceof BaseObject)
		    {
			if(StringUtil.nullToEmpty(currPath).isEmpty())
			{
			    currPath = new StringBuffer();
			    currPath.append(propName);
			}
			else
			{
			    currPath.append(".").append(propName);
			}
			/**
			 * to cach the object related to the current property in
			 * a separated entry in the hashMap
			 */
			List<String> crrObjList = new ArrayList<String>();
			cachPropOfCurrObj(t, new StringBuffer(), crrObjList);
			/**
			 * recursive call to cach the property that are exists
			 * inside the current Object
			 */
			cachCurrenObj(t, currPath, list,firstEntry);
		    }
		    else if(theType.isAssignableFrom(BigDecimal.class) && PropertyUtils.isWriteable(theObj, propName)
			    && PropertyUtils.isReadable(theObj, propName))
		    {
			/**
			 * if the currentPath is null, so the current property
			 * is a direct property inside the main Object
			 */
			if(StringUtil.nullToEmpty(currPath).isEmpty())
			{
			    currPath = new StringBuffer();
			    currPath.append(propName);
			}
			/**
			 * otherwise it is a property from other object that is
			 * exists inside the Main Object
			 */
			else
			{
			    currPath.append(".").append(propName);
			}
			if(firstEntry)
			{			    
			    list.add(currPath.toString());
			}
			if(ConstantsCommon.EMPTY_BIGDECIMAL_VALUE.equals(t))
			{
			    PropertyUtils.setProperty(theObj, propName, null);
			}
		    }
		}
	    }
	}
	catch(Exception ex)
	{
	    log.error(ex, "ERROR IN cachCURRENObj NumberUtils");
	}

    }
    /**
     * To cach the current object in a new entry inside the cached hashMap 
     * @author marwanmaddah
     * @date   Jul 7, 2014
     * @param theObj
     * @param objPath
     * @param list void
     *
     */
    private static void cachPropOfCurrObj(Object theObj,StringBuffer objPath,List<String> list)
    {
	StringBuffer currPath;
	try
	{
	    PropertyDescriptor[] disc = PropertyUtils.getPropertyDescriptors(theObj);
	    for(int i = 0; i < disc.length; i++)
	    {
		currPath = new StringBuffer(objPath);
		PropertyDescriptor di = disc[i];
		String propName = di.getName();
		if(!"class".equalsIgnoreCase(propName))
		{
		    Class theType = di.getPropertyType();
		    Object t = PropertyUtils.getProperty(theObj, propName);
		    if(t instanceof BaseObject)
		    {
			if(StringUtil.nullToEmpty(currPath).isEmpty())
			{
			    currPath = new StringBuffer();
			    currPath.append(propName);
			}
			else
			{
			    currPath.append(".").append(propName); 
			}
			cachPropOfCurrObj(t,currPath,list);
		    }
		    else if(theType.isAssignableFrom(BigDecimal.class)
			    && PropertyUtils.isWriteable(theObj, propName)
			    && PropertyUtils.isReadable(theObj, propName))
		    {
			
			if(StringUtil.nullToEmpty(currPath).isEmpty())
			{
			    currPath = new StringBuffer();
			    currPath.append(propName);
			}
			else
			{
			    currPath.append(".").append(propName); 
			}
			
			list.add(currPath.toString());
			if(ConstantsCommon.EMPTY_BIGDECIMAL_VALUE.equals(t))
			{
			    PropertyUtils.setProperty(theObj, propName, null);   
			}
		    }
		}
	    }
	    if(StringUtil.nullToEmpty(objPath).isEmpty() && !list.isEmpty())
	    {
		objectBigDecimalPropNames.put(theObj.getClass().getName(),list);
	    }
	}
	catch(Exception ex)
	{
	    log.error(ex,"ERROR in cachPropOfCurrObj NumberUtils "); 
	}
    }
    /**
     * 
     * Used for resting the Numeric Values set by the converter 
     * 
     * @param theObj
     */
    public static void resetEmptyValues(Object obj)
    {
	try
	{
	    Object theObj;
	    ArrayList<Object> lst = null;
	    boolean isList = false;
	    if(obj instanceof List)
	    {
		lst = (ArrayList) obj;
		theObj = (lst.isEmpty())?null:(lst.get(0));
		isList = true;
	    }
	    else
	    {
		theObj = obj;
	    }
	    if(theObj!=null)
	    {
		    String className = theObj.getClass().getName();
		    if(objectBigDecimalPropNames.containsKey(className))
		    {
			List<String> lstObjProp = objectBigDecimalPropNames.get(className);
			if(isList)
			{
			    /**
			     * In case the object is a List of Object we will reset all
			     * the empty values in all the Objects that are exists
			     * inside the list.
			     */
			    for(Object currObj : lst)
			    {
				resetCurrentObject(currObj, lstObjProp);
			    }
			}
			else
			{
			    resetCurrentObject(theObj, lstObjProp);
			}
		    }
		    else
		    {
			List<String> propList = new ArrayList<String>();
			cachBigDecimalProp(obj,new StringBuffer(),propList,isList);
			if(!propList.isEmpty())
			{
			    objectBigDecimalPropNames.put(className, propList);
			}
		    }
	    }
	}
	catch(Exception e)
	{
	    log.error(e, "ERROR in resetEmptyValues NumberUtils ");
	}
    }
    
    /**
     * used to reset the current Object in case is cached in the hashMap
     * @author marwanmaddah
     * @date   Jul 15, 2014
     * @param theObj
     * @param lstObjProp
     * @throws Exception
     *
     */
    private static void resetCurrentObject(Object theObj, List<String> lstObjProp)
    {
	try
	{
	        /**
	         * loop on all the properties that are exists inside the Object 
	         */
		for(String currObj:lstObjProp)
		{
		    Object inlineObj    = theObj;
		    String theClassPath = currObj;
		    if(!StringUtil.nullToEmpty(theClassPath).isEmpty())
		    {			
			String[] arrObjNames = theClassPath.split("\\.");
			int arrSize = arrObjNames.length;
			String propName = arrSize > 0? arrObjNames[arrSize-1]:theClassPath; 
			int i = 0;
			while(i < arrSize-1)
			{
			    Object crrObj = inlineObj.getClass().getMethod("get"+ StringUtil.setFirstCharUpper(arrObjNames[i]), null).invoke(inlineObj,null);
			    inlineObj = crrObj;
			    /**
			     * in case any of the objects that are exists in the chain is null
			     * the loop will be stopped
			     */
			    if(crrObj==null)
			    {
				break;
			    }
			    i++;
			}
			/**
			 * in case the inlineObj is null no need to reset 
			 * the properties that are exists inside it
			 */
			if(inlineObj!=null)
			{			    
			    Object t = PropertyUtils.getProperty(inlineObj, propName);
			    if(ConstantsCommon.EMPTY_BIGDECIMAL_VALUE.equals(t))
			    {
				PropertyUtils.setProperty(inlineObj, propName, null);   
			    }
			}
		    }
		}
	}
	catch(Exception ex)
	{
	    log.error(ex, "ERROR in resetCurrentObject NumberUtils "); 
	}

    }
/**
 * Return Big decimal which is round the decimal of double to precision 
 * there was a issue when we convert the double to big decimal it is return 56.56499999999.....
 * @param unrounded
 * @param precision
 * @return
 */
public static BigDecimal roundToBigDecimal(double unrounded, Long precision) {
	int roundingMode = BigDecimal.ROUND_HALF_UP;
	int precisionInt = precision.intValue();
	BigDecimal bd = BigDecimal.valueOf(unrounded);
	return bd.setScale(precisionInt, roundingMode);
}
/**
 * Return Big decimal which is round the decimal of BigDecimal to precision 
 * there was a issue when we convert the double to big decimal it is return 56.56499999999.....
 * @param unrounded
 * @param precision
 * @return
 */
public static BigDecimal roundToBigDecimal(BigDecimal unrounded, int precision) {
    	BigDecimal bd = nullToZero(unrounded);
	return bd.setScale(precision, BigDecimal.ROUND_HALF_UP);
}

    /**
     * Truncate the decimal
     * 
     * @param theVal
     * @param precision
     * @return
     */
    public static double truncate(double theVal, Long precision)
    {
	double val = theVal;
	DecimalFormat twoDForm = new DecimalFormat("#.############");
	val = Double.valueOf(twoDForm.format(val));
	double multiplier = Math.pow(10, precision);
	return Math.floor(multiplier * val) / multiplier;
    }

/**
 * check if is number and positive
 * @param val
 * @return boolean
 */
public static boolean isNumberPositive(Object val) throws BaseException
{
	if(val instanceof Number && NumberUtil.toDouble(val) > 0 )
	{
	    return true;
	}
	return false;
}
/**
 * check if is number and negative
 * @param val
 * @return boolean
 */
public static boolean isNumberNegative(Object val) throws BaseException
{
	if(val instanceof Number && NumberUtil.toDouble(val) < 0 )
	{
	    return true;
	}
	return false;
}

/**
 * Method that returns the Locale related to a user if found among available Locals fr, en considered for now
 * @return the Correct Locale Object if found, en Locale if not found
 */
public static Locale returnUserLocale()
{
    Locale currLocale = null;
    try
    {
	boolean notFound = true;
	// any number just to compare the formatted value
	BigDecimal value = new BigDecimal(1225.5);
	String neededFormat = format(value,"#,###.0");
	//French (space ,), English(,.), Spanish(.,)
	Locale[] availableLocales = new Locale[]{new Locale("fr"),new Locale("en"),new Locale("es")};
	for(int i = 0; i < availableLocales.length; i++)
	{
	    currLocale = availableLocales[i];
	    DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(currLocale);
	    String output = df.format(value);
	    // this is needed because the group separator character in French format behaves as space but it is in fact non-braking space
	    output = output.replace('\u00a0', ' ');
	    if(neededFormat.equals(output))
	    {
		notFound = false;
		break;
	    }
	}
	if(notFound)
	{
	    currLocale = new Locale("en");
	}
    }
    catch(Exception e)
    {
	log.error(e,"ERROR in ");
    }
    return currLocale;
}
}