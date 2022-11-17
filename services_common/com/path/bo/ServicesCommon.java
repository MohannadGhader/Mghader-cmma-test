package com.path.bo;

import java.util.TimeZone;

import com.path.bo.common.CommonMethods;
import com.path.bo.common.ConstantsCommon;
import com.path.dao.ServicesCommonDAO;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.NumberUtil;
import com.path.lib.common.util.PathPropertyUtil;
import com.path.lib.common.util.StringUtil;

public class ServicesCommon extends BaseBO implements DefaultServicesCommon {
	ServicesCommonDAO servicesCommonDAO;
	private static Object sqlSessionLock = new Object();
	private static Object trackChangesLock = new Object();
	private static Object clusteredOperationLock = new Object();

	/**
	 * method that set the db version in its corresponding constant
	 */
	public void initialize() throws BaseException {

		try {
			// set default timezone if exists as a property, otherwise machine timezone is
			// used.
			String defaultTimeZone = StringUtil
					.nullToEmpty(
							PathPropertyUtil.returnPathPropertyFromFile("PathRemoting.properties", "default.timezone"))
					.trim();
			if (!defaultTimeZone.isEmpty()) {
				TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZone));
//				TimeZone.setDefault(TimeZone.getTimeZone("EEST"));
			}
		} catch (Exception e) {
			log.error(e, "Error in Reading Property default.timezone from PathRemoting.properties");
		}

		// apply db version
//	servicesCommonDAO.applyDbVersion();

		// apply caching of most used expressions
		try {
		} catch (Exception e) {
			log.error(e, "Error calling expressions caching on startup");
		}

		synchronized (sqlSessionLock) {
			// setting flag for enabling SQL session trace
			try {
				String sqlSessionTraceProp = PathPropertyUtil.returnPathPropertyFromFile("PathRemoting",
						"database.sqlsession.trace.enabled");
				if (ConstantsCommon.ONE.equals(StringUtil.nullToEmpty(sqlSessionTraceProp).trim())) {
					ConstantsCommon.SQL_SESSION_TRACE_CODE = true;
					String sqlSessionTraceAllProp = PathPropertyUtil.returnPathPropertyFromFile("PathRemoting",
							"database.sqlsession.trace.all");
					if (ConstantsCommon.ONE.equals(StringUtil.nullToEmpty(sqlSessionTraceAllProp).trim())) {
						ConstantsCommon.SQL_SESSION_TRACE_ALL_CODE = true;
					}
				}
			} catch (Exception e) {
				log.error(e,
						"Error in Reading Property database.sqlsession.trace.enabled from PathRemoting.properties");
			}
			// setting Flag for SQL session termination enabling
			try {
				String sqlSessionTerminateProp = PathPropertyUtil.returnPathPropertyFromFile("PathRemoting",
						"database.sqlsession.termination.enabled");
				if (ConstantsCommon.ONE.equals(StringUtil.nullToEmpty(sqlSessionTerminateProp).trim())) {
					ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE = true;
				}
			} catch (Exception e) {
				log.error(e,
						"Error in Reading Property database.sqlsession.termination.enabled from PathRemoting.properties");
			}
		}

		// setting flag for enabling tracking of transactions' update after approve
		// changes
		synchronized (trackChangesLock) {
			String trackChangesEnabled = "0";
			try {
				trackChangesEnabled = StringUtil.nullEmptyToValue(
						PathPropertyUtil.returnPathPropertyFromFile("PathRemoting", "tracking.updates.changes.enabled"),
						"0").trim();
			} catch (Exception e) {
				trackChangesEnabled = "0";
				log.warning(" Reading Property tracking.updates.changes.enabled from PathRemoting.properties");
			}
			ConstantsCommon.ENABLE_TRACKING_CHANGES = NumberUtil.parseInt(trackChangesEnabled);
		}

		// setting flag for enabling control on clustered operations
		synchronized (clusteredOperationLock) {
			boolean globalClusterEnabled = false;
			try {
				globalClusterEnabled = Boolean.parseBoolean(
						PathPropertyUtil.returnPathPropertyFromFile("PathRemoting", "global.cluster.enabled"));
			} catch (Exception e) {
				globalClusterEnabled = false;
				log.warning(" Reading Property global.cluster.enabled from PathRemoting.properties");
			}
			ConstantsCommon.GLOBAL_CLUSTER_ENABLE = globalClusterEnabled;
			try {
				if (globalClusterEnabled) {
					clearClusterOperationRecords();
				}
			} catch (Exception e) {
				log.warning("Could not Clear Clustered Operations Records!");
			}
		}

		// setting the Group and decimal separators if defined at level of PathRemoting
		synchronized (sqlSessionLock) {
			CommonMethods.initializeGroupDecSeparators();
		}
	}

	/**
	 * method called upon undeploy of web application
	 */
	public void destroy() {
		try {
			com.path.lib.common.util.ThreadAttributes.removeThread();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearClusterOperationRecords() throws Exception {
		servicesCommonDAO.clearClusterControl();
	}

	/**
	 * 
	 * 
	 * @throws Exception
	 */

	/**
	 * 
	 * @return
	 */
	public ServicesCommonDAO getServicesCommonDAO() {
		return servicesCommonDAO;
	}

	public void setServicesCommonDAO(ServicesCommonDAO servicesCommonDAO) {
		this.servicesCommonDAO = servicesCommonDAO;
	}

}