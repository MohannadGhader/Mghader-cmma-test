package com.path.lib.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;

import com.path.lib.common.base.IbatisSqlMapClient;
import com.path.lib.common.exception.DAOException;
import com.path.lib.log.Log;
import com.path.struts2.lib.common.GridParamsSC;
 
/**
 * DENISK_LATEST_VERS_UPDATED
 * @author deniskhaddad
 *
 */
public final class DAOHelper 
{
    private final static Log log = Log.getInstance();
    private final static String MASK_PATTERN = "(?i)(cardNo|card_no)([a-zA-Z0-9\\)\\( ]*)(LIKE|=)([a-zA-Z0-9\\)\\( ]*'%?)([a-zA-Z0-9.\\-\\\\\\\\*\\\\,/@$ ]*)(%?')";

    /**
     * Private constructor only to prevent instantiation in the class
     */	
    private DAOHelper()
    {
	log.error("This Class Should not be Instantiated");
    }
   /**
    * 
    * @param quseryStr
    * @param ibatisMap
    * @return
    */
    private static String propertyToDbName(String quseryStr, HashMap ibatisMap)
    {
	if(quseryStr == null)
	{	    
	   return quseryStr;
	}
	String result  = quseryStr;
	String fromMap = null;
	String theGroup = null;
	/**
	 * [Marwan Maddah]: regular expression to catch the field name from the search filter String
	 * it is exclude all the technical names (and, or, upper,...)
	 * then catch all characters a-zA-Z ,'_' or '.' and that are not between quotation
	 * after that replace the detected group by the related value from the ibatisMap
	 * ex : queryString: upper(convert(varchar,linksCO.link_Code))like(upper(convert(varchar,'test')))
	 *      theGroup will be: linksCO.link_Code
	 *      result will be: upper(convert(varchar,LINK_CODE))like(upper(convert(varchar,'test')))
	 */
	String regEx ="\\b(?i)(?!AND|OR|NOT NULL|NULL|IS NULL|IS NOT NULL|like|NOT LIKE|upper|CONVERT|VARCHAR|DATE|TO_DATE)([a-zA-Z0-9_\\.]+)\\b(?=([^']*['][^']*['])*[^']*$)";
	
	Pattern r = Pattern.compile(regEx);
	Matcher m = r.matcher(quseryStr);
	List<String> lstGroups = new ArrayList<String>();
	while(m.find())
    	{
	    theGroup = m.group();
	    if(!lstGroups.contains(theGroup))
	    {
		fromMap = (String) ibatisMap.get(theGroup);
		if(fromMap != null)
		{
		    result = result.replaceAll("\\b(?i)(?!AND|OR|NOT NULL|NULL|IS NULL|IS NOT NULL|like|NOT LIKE|upper|CONVERT|VARCHAR|DATE|TO_DATE)"+Pattern.quote(theGroup)+"\\b(?=([^']*['][^']*['])*[^']*$)", fromMap);
		}
		lstGroups.add(theGroup);
	    }
    	}
	return result;
    }
	/**
	 * 
	 * @param gParams
	 * @param sqlMap
	 * @param rsltMapId
	 */
	public static void fixGridMaps(GridParamsSC gParams , IbatisSqlMapClient sqlMap, String rsltMapId ) throws DAOException
	 {

	  String colSearch = gParams.getColSearchQuery(); //getWhereQuery();
	  String search = gParams.getSearchQuery();
	  String sort = gParams.getSord();
	  String sidx = gParams.getSidx();
	  HashMap ibatisMap = new HashMap();
	  /**
	   *  check if result MAp provided, else add where condition regardless of result Map
	   */
	  if(!StringUtil.nullToEmpty(rsltMapId).isEmpty())
	  {
        	  ResultMap rs = sqlMap.getSqlSession().getConfiguration().getResultMap(rsltMapId);
        	  
        	  List<ResultMapping> resultMappings = rs.getResultMappings();
        	  
        	  ResultMapping rsm;
        	  String colName = null, propertyName = null;
        	 
        	  for(int i = 0; i < resultMappings.size(); i++)
        	  {
        		  rsm = resultMappings.get(i);
        		  colName = rsm.getColumn();
        		  propertyName = rsm.getProperty();
        
                	   if(log.isLoggable(Log.TRACE))
                	   {
                	       log.trace("column:" + colName + " property:" + propertyName);
                	   }
                
                	   if(colName != null && propertyName != null)
                	   {        	       
                	       ibatisMap.put(propertyName, colName);
                	   }
        	  }
	  }
	  colSearch = propertyToDbName(colSearch, ibatisMap);
	  search = propertyToDbName(search, ibatisMap);
	  sidx = propertyToDbName(sidx, ibatisMap);
	  /**
	   * [PathSolutions-MarwanMaddah]
	   * added to avoid the injections and any change on the parameters that will pass with the URL 
	   * for grid search & sort.
	   */
	  if(!StringUtil.nullToEmpty(sidx).isEmpty())
	  {	      
		  String specialCharPatrn = "[^a-zA-Z0-9,\\s+\\.\\_()]";
		  Pattern pattern = Pattern.compile(specialCharPatrn);
		  Matcher matcher = pattern.matcher(sidx);
		  if(matcher.find())
		  {
		      throw new DAOException("Special characters are not allowed, contact adminstrator, possible system abuse"); 
		  }
	  }
	  if(!StringUtil.nullToEmpty(sort).isEmpty())
	  {
	     Pattern ptrn = Pattern.compile("(?i)(desc|asc)");
	     Matcher matcher = ptrn.matcher(sort.trim());
	     if(!matcher.matches())
	     {
		throw new DAOException("the sort properties have been changed, contact adminstrator, possible system abuse");
	     }
	  }
	  /**
	   * 
	   */
	  // put back replaced Query in SC object might be used by Dev
	  if(search != null)
	  {
	      gParams.setSearchQuery(" "+search+" ");
	  }
	  
	  if(log.isLoggable(Log.TRACE))
	  {
	      String theColSearch = colSearch;
	      String theSearch = search;
	      if(theColSearch != null)
	      {
		  theColSearch = theColSearch.replaceAll(MASK_PATTERN,"$1$2$3$4<******>$6");
	      }
	      if(theSearch != null)
	      {
		  theSearch = theSearch.replaceAll(MASK_PATTERN,"$1$2$3$4<******>$6");
	      }
	      log.trace("colSearch:" + theColSearch + "\n" + "search:" + theSearch + "\n" + "sort:" + sort);
	  }

	  if(sort != null && sidx != null && !sidx.trim().isEmpty())
	  {	      
	      gParams.setSortOrder(" order by " + sidx +  " " +  sort + " ");
	  }
	  
	  if(colSearch != null)
	  {
	      gParams.setWhereQuery(" " + colSearch + " ");	      
	  }
	  
	  if(search != null)
	  {
		  if(gParams.getWhereQuery() == null || gParams.getWhereQuery().isEmpty())
		  {		      
		      gParams.setWhereQuery(" " + search + " ");
		  }
		  else
		  {		      
		      gParams.setWhereQuery(gParams.getWhereQuery() + " AND ("  + search + ") ");
		  }
	  }
    
	  if(log.isLoggable(Log.METHOD))
	  {	 
	      String thewhrQuery = gParams.getWhereQuery();
	      if(thewhrQuery != null)
	      {
		  thewhrQuery = thewhrQuery.replaceAll(MASK_PATTERN,"$1$2$3$4<******>$6");
	      }
	      log.exiting("Sort order:" + gParams.getSord() + " Where Query:" + thewhrQuery);
	  }
	   
	 }
}
