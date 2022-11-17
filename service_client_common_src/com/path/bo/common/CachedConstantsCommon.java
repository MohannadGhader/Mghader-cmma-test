package com.path.bo.common;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class CachedConstantsCommon
{
    public volatile static HashMap<String, String[]> optHm  = new HashMap<String, String[]>(); //each menuVar with its String[]{ optUrl,Window_name
    public volatile static HashMap<String, HashMap> findInfo  = new HashMap<String, HashMap>(); //hashMap for cashing of different Info like trx Type
    public volatile static HashMap<String, HashMap<String, Object>> expressionCodesMap ; //hashMap for caching parsed and Translated DW Expressions to Java
    public static final String DEFAULT_PTHCTRL_CACHE_KEY = "default_pthctrl";
    public static final String DYN_DYSPLAY_CACH_KEY = "dyb_disp_key";
        
    /**
     * HashMap for Caching the required fields ...
     */
    public volatile static HashMap<String, HashMap> keyLabelTransMap = new HashMap<String, HashMap>();
    public volatile static String apply_dynamic_screen = "1";
    
    /**
     * database Errors properties caching
     */
    public volatile static Properties databaseErrors;
    /**
     * HashMap for Caching the alerts parameters by appName and progRef ...
     */
    public volatile static Map<String, Map<String, Object>> alertsParamMap = new HashMap<String, Map<String,Object>>();
    
    /**
     * hashmap for caching maxlengths of additional Fields
     */
    public volatile static HashMap<String, HashMap<String, HashMap<String, BigDecimal>>> maxLengMap = new HashMap<String, HashMap<String, HashMap<String, BigDecimal>>>(); 
    
    
    
}
