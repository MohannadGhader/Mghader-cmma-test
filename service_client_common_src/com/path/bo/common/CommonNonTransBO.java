package com.path.bo.common;

import com.path.lib.common.exception.BaseException;
/**
 * 
 * Copyright 2016, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: deniskhaddad
 *
 * CommonNonTransBO.java used for non transaction Common methods interface
 */
public interface CommonNonTransBO
{
    /**
     * Method to terminate SQL session Ids linked to provided Http Session Id
     * @param httpSessionId Http Session Id whose SQL session to terminate
     * @throws BaseException
     */
    public void terminateSQLSession(String httpSessionId) throws BaseException;
}
