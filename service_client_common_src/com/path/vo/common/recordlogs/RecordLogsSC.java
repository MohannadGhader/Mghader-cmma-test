package com.path.vo.common.recordlogs;

import java.math.BigDecimal;
import java.util.Date;

import com.path.struts2.lib.common.GridParamsSC;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * RecordLogsSC.java used to
 */
public class RecordLogsSC extends GridParamsSC
{
    private String theRecord;
    private String pageRef;
    private String appName;
    private String trxNbr;
    private String mailTO;
    private StringBuffer msgHead;
    private String recordsType;
    private String attachmentDetails; 
    
    
    private BigDecimal lineNo;
    private Date       serverDate;

    public String getTheRecord()
    {
        return theRecord;
    }

    public void setTheRecord(String theRecord)
    {
        this.theRecord = theRecord;
    }

    public void setPageRef(String pageRef)
    {
	this.pageRef = pageRef;
    }

    public String getPageRef()
    {
	return pageRef;
    }

    public void setAppName(String appName)
    {
	this.appName = appName;
    }

    public String getAppName()
    {
	return appName;
    }

    public void setTrxNbr(String trxNbr)
    {
	this.trxNbr = trxNbr;
    }

    public String getTrxNbr()
    {
	return trxNbr;
    }

    public String getMailTO()
    {
        return mailTO;
    }

    public void setMailTO(String mailTO)
    {
        this.mailTO = mailTO;
    }

    public StringBuffer getMsgHead()
    {
        return msgHead;
    }

    public void setMsgHead(StringBuffer msgHead)
    {
        this.msgHead = msgHead;
    }

    public String getRecordsType()
    {
        return recordsType;
    }

    public void setRecordsType(String recordsType)
    {
        this.recordsType = recordsType;
    }

    /**
     * @return the attachmentDetails
     */
    public String getAttachmentDetails()
    {
        return attachmentDetails;
    }

    /**
     * @param attachmentDetails the attachmentDetails to set
     */
    public void setAttachmentDetails(String attachmentDetails)
    {
        this.attachmentDetails = attachmentDetails;
    }

    /**
     * @return the lineNo
     */
    public BigDecimal getLineNo()
    {
        return lineNo;
    }

    /**
     * @param lineNo the lineNo to set
     */
    public void setLineNo(BigDecimal lineNo)
    {
        this.lineNo = lineNo;
    }

    /**
     * @return the serverDate
     */
    public Date getServerDate()
    {
        return serverDate;
    }

    /**
     * @param serverDate the serverDate to set
     */
    public void setServerDate(Date serverDate)
    {
        this.serverDate = serverDate;
    }

}
