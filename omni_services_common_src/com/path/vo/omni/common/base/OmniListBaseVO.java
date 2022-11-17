package com.path.vo.omni.common.base;

import java.io.Serializable;
import java.util.List;


/**
 * 
 * Copyright 2019, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: AliShreif
 *
 * OmniListBaseVO.java used for any list in BO to implement the total number of records needed for pagination 
 */
public class OmniListBaseVO implements Serializable
{
    
    private List result;
    
    private int totalNbRec;
    
    private Integer outputCode;
    private String outputNotification;
    private String outputNotificationDetails;
    private String outputType;

        
    public OmniListBaseVO()
    {
	super();
    }

    public int getTotalNbRec()
    {
        return totalNbRec;
    }

    public List getResult()
    {
        return result;
    }

    public void setResult(List result)
    {
        this.result = result;
    }

    public void setTotalNbRec(int totalNbRec)
    {
        this.totalNbRec = totalNbRec;
    }

    public Integer getOutputCode()
    {
        return outputCode;
    }

    public void setOutputCode(Integer outputCode)
    {
        this.outputCode = outputCode;
    }

    public String getOutputNotification()
    {
        return outputNotification;
    }

    public void setOutputNotification(String outputNotification)
    {
        this.outputNotification = outputNotification;
    }

    public String getOutputNotificationDetails()
    {
        return outputNotificationDetails;
    }

    public void setOutputNotificationDetails(String outputNotificationDetails)
    {
        this.outputNotificationDetails = outputNotificationDetails;
    }

    public String getOutputType()
    {
        return outputType;
    }

    public void setOutputType(String outputType)
    {
        this.outputType = outputType;
    }
    
    
    

}
