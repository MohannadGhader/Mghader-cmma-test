package com.path.dao.impl;

import com.path.bo.common.ConstantsCommon;
import com.path.dao.ServicesCommonDAO;
import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;
import com.path.struts2.lib.common.BaseObject;

public class ServicesCommonDAOImpl extends BaseDAO implements ServicesCommonDAO
{
    /**
     * dummy object to apply concurrent access from different threads
     */
    private static Object lockDbVersion = new Object();
    @Override
    public int returnDbVersionWithConn(BaseObject baseObj) throws BaseException
	{
		/*
		 * Long dbms = (Long)
		 * getSqlMap().queryForObject("servicesCommon.applyDbVersion", baseObj); if(dbms
		 * == null || dbms.equals(1L)) { return 0; } return 1;
		 */
    	return 3;
    }
    public Long applyDbVersion() throws BaseException
    {
//	Long dbms = (Long) getSqlMap().queryForObject("servicesCommon.applyDbVersion", null);
    	Long dbms = 3L;
	synchronized(lockDbVersion)
	{	    
	    if(dbms == null || dbms.equals(1L))
	    {
		ConstantsCommon.CURR_DBMS_ORACLE = 1;
		ConstantsCommon.CURR_DBMS_SYBASE = 0;
		ConstantsCommon.CURR_DBMS_SQLSERVER = 0;

	    }
	    else if(Long.valueOf(2L).equals(dbms))
	    {
		ConstantsCommon.CURR_DBMS_ORACLE = 0;
		ConstantsCommon.CURR_DBMS_SYBASE = 1;
		ConstantsCommon.CURR_DBMS_SQLSERVER = 0;
	    }// SQL server DBMS, the Sybase Flag also set since syntax is similar
	    else if(Long.valueOf(3L).equals(dbms))
	    {
		ConstantsCommon.CURR_DBMS_ORACLE = 0;
		ConstantsCommon.CURR_DBMS_SYBASE = 1;
		ConstantsCommon.CURR_DBMS_SQLSERVER = 1;
	    }
	}
	return dbms;
    }

    
	
	@Override
	public void clearClusterControl() throws DAOException
	{
	    getSqlMap().delete("servicesCommon.clearClusterControl", null);
	}
}