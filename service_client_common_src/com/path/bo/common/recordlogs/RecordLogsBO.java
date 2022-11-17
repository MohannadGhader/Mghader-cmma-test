package com.path.bo.common.recordlogs;

import java.util.ArrayList;

import com.path.lib.common.exception.BaseException;
import com.path.vo.common.recordlogs.RecordLogsSC;
import com.path.vo.common.recordlogs.RecordUserMailListCO;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * RecordLogsBO.java used to
 */
public interface RecordLogsBO
{
    /**
     * Loads the record Log of a specified record (TRX_NBR)
     * 
     * @param recordSC
     * @return String
     * @throws BaseException
     */
    public String loadRecordLogs(RecordLogsSC recordSC) throws BaseException;

    /**
     * Save the record Log of a specified record (TRX_NBR)
     * 
     * @param recordSC
     * @throws BaseException
     */
    public void saveRecordLogs(RecordLogsSC recordSC) throws BaseException;

    /**
     * Returns the users e-mails list
     * 
     * @param recordSC
     * @return ArrayList<RecordUserMailListCO>
     * @throws BaseException
     */
    public ArrayList<RecordUserMailListCO> returnUserMailList(RecordLogsSC recordSC) throws BaseException;

    /**
     * Returns the users e-mails list count
     * 
     * @param recordSC
     * @return int
     * @throws BaseException
     */
    public int returnUserMailListCount(RecordLogsSC recordSC) throws BaseException;


    /**
     * returns the parent reference of a defined pageRef
     * 
     * @param recordSC
     * @return String
     * @throws BaseException
     */
    public String returnParentRef(RecordLogsSC recordSC) throws BaseException;
    
    /**
     * Process and send the e-mail
     * @param recordSC
     * @throws BaseException
     */
    public void sendMailTo(RecordLogsSC recordSC) throws BaseException;
}