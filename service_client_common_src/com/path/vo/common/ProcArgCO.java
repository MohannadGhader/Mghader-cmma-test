package com.path.vo.common;

import java.math.BigDecimal;
import java.util.Date;

import com.path.struts2.lib.common.BaseObject;

/**
 * 
 * Copyright 2016, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: RabihElKhatib
 * 
 * ProcArgCO.java used to store the SYS_RUNTIME_PROC_ARG_TMP values (in order to handle version different procedures' arguments)
 */
public class ProcArgCO extends BaseObject
{
    private String procName;
    private String argName;
    private BigDecimal argValNumber;
    private String argValString;
    private Date argValDate;
    
    public String getProcName()
    {
        return procName;
    }
    public void setProcName(String procName)
    {
        this.procName = procName;
    }
    public String getArgName()
    {
        return argName;
    }
    public void setArgName(String argName)
    {
        this.argName = argName;
    }
    public BigDecimal getArgValNumber()
    {
        return argValNumber;
    }
    public void setArgValNumber(BigDecimal argValNumber)
    {
        this.argValNumber = argValNumber;
    }
    public String getArgValString()
    {
        return argValString;
    }
    public void setArgValString(String argValString)
    {
        this.argValString = argValString;
    }
    public Date getArgValDate()
    {
        return argValDate;
    }
    public void setArgValDate(Date argValDate)
    {
        this.argValDate = argValDate;
    }
}
