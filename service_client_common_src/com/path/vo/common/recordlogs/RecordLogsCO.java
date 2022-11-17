package com.path.vo.common.recordlogs;

import java.util.ArrayList;

import com.path.lib.vo.BaseVO;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * RecordLogsCO.java used to
 */
public class RecordLogsCO extends BaseVO
{
    private ArrayList<RecordUserMailListCO>	mailList = new ArrayList<RecordUserMailListCO>();

    public ArrayList<RecordUserMailListCO> getMailList()
    {
        return mailList;
    }

    public void setMailList(ArrayList<RecordUserMailListCO> mailList)
    {
        this.mailList = mailList;
    }
}
