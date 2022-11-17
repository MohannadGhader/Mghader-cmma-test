package com.path.dao;

import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;
import com.path.struts2.lib.common.BaseObject;

public interface ServicesCommonDAO
{
    Long applyDbVersion() throws BaseException;


    int returnDbVersionWithConn(BaseObject baseObj) throws BaseException;



    /**
     * Delete all rows from SYS_CLUSTER_OPERATION_CTRL table launched upon running the server 
     * @throws DAOException
     */
    public void clearClusterControl() throws DAOException;
}