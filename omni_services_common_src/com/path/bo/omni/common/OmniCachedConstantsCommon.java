/**
 * Copyright 2019, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: gilbertandary
 *
 * OmniCachedConstantsCommon.java
 *
 * 
 */
package com.path.bo.omni.common;

import java.math.BigDecimal;
import java.util.HashMap;

public class OmniCachedConstantsCommon
{
    public volatile static HashMap<BigDecimal, HashMap<String, Object>> etlMap = new HashMap<BigDecimal, HashMap<String, Object>>();
}
