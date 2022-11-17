package com.path.vo.common.recordlogs;

import com.path.lib.vo.BaseVO;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * RecordLogsCO.java used to
 */
public class RecordUserMailListCO extends BaseVO
{
    private String USER_ID;
    private String USER_NAME;
    private String EMAIL_ID;
    private String EMAIL_SERVER;
    private String MAIL_TO;
    private String MAIL_RESULT;
    private String REPLY;
    
    
    public String getUSER_ID()
    {
        return USER_ID;
    }
    public void setUSER_ID(String uSERID)
    {
        USER_ID = uSERID;
    }
    public String getUSER_NAME()
    {
        return USER_NAME;
    }
    public void setFIRST_NAME(String uSERNAME)
    {
        USER_NAME = uSERNAME;
    }
    public String getEMAIL_ID()
    {
        return EMAIL_ID;
    }
    public void setEMAIL_ID(String eMAILID)
    {
        EMAIL_ID = eMAILID;
    }
    public String getEMAIL_SERVER()
    {
        return EMAIL_SERVER;
    }
    public void setEMAIL_SERVER(String eMAILSERVER)
    {
        EMAIL_SERVER = eMAILSERVER;
    }
    public String getMAIL_TO()
    {
        return MAIL_TO;
    }
    public void setMAIL_TO(String mAILTO)
    {
        MAIL_TO = mAILTO;
    }
    public String getMAIL_RESULT()
    {
        return MAIL_RESULT;
    }
    public void setMAIL_RESULT(String mAILRESULT)
    {
        MAIL_RESULT = mAILRESULT;
    }
    public String getREPLY()
    {
        return REPLY;
    }
    public void setREPLY(String rEPLY)
    {
        REPLY = rEPLY;
    }
}
