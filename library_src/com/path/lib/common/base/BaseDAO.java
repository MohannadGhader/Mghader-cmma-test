package com.path.lib.common.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Map;

import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.jdbc.datasource.JdbcTransactionObjectSupport;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;

import com.path.bo.common.ConstantsCommon;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.StringUtil;
import com.path.lib.log.Log;
import com.path.struts2.lib.common.BaseObject;
import com.path.struts2.lib.common.BaseSC;
import com.path.vo.common.ProcArgSC;

/**
 * 
 * Copyright 2010, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: Denisk Haddad
 *
 *          BaseDAO.java provide the SqlMapClient that will be used by DAOs to
 *          call queries. Contains the implementation of the CRUD interface
 *          methods where the query names are extracted dynamically based on VO
 *          class name.
 */
public class BaseDAO implements BaseDAOInterface {
	protected static final Log log = Log.getInstance();
	protected String datasourceJNDI;
	protected IbatisSqlMapClient sqlMap;
	private Map sqlMaps;

	/**
	 * getSqlMap
	 *
	 * @return SqlMapClient
	 */
	public IbatisSqlMapClient getSqlMap() {
		return sqlMap;
	}

	public Map getSqlMaps() {
		return sqlMaps;
	}

	/**
	 *
	 * @param sqlMap SqlMapClient
	 */
	public void setSqlMap(IbatisSqlMapClient sqlMap) {
		this.sqlMap = sqlMap;
	}

	public void setSqlMaps(Map sqlMaps) {
		this.sqlMaps = sqlMaps;
	}

	/**
	 * retrieves the query name to be called related to CRUD. could be
	 * insert/update/delete using the VO simple class name and the action type
	 * 
	 * @param obj        VO
	 * @param actionType insert/update/delete
	 * @return Namespace.QueryName
	 * @throws DAOException
	 */
	private String getQueryName(Object obj, String actionType) throws DAOException {

		String className = obj.getClass().getSimpleName();
		String nameSpace;
		if (className.lastIndexOf("VO") < 0) {
			throw new DAOException("Class Name should be VO Object");
		} else {
			nameSpace = className.substring(0, className.lastIndexOf("VO"));
		}
		String queryName = nameSpace + "." + actionType + nameSpace;

		return queryName;
	}

	/**
	 * calls the Delete query return number of record deleted
	 */
	public Integer delete(Object obj) throws DAOException {
		return sqlMap.delete(getQueryName(obj, "delete"), obj);
	}

	/**
	 * calls the Select query
	 */
	public Object selectByPK(Object obj) throws DAOException {
		return sqlMap.queryForObject(getQueryName(obj, "select"), obj);
	}

	/**
	 * calls the update query return number of record updated
	 */
	public Integer update(Object obj) throws DAOException {
		return sqlMap.update(getQueryName(obj, "update"), obj);
	}

	/**
	 * calls the insert query
	 */
	public Integer insert(Object obj) throws DAOException {
		return (Integer) sqlMap.insert(getQueryName(obj, "insert"), obj);
	}

	/**
	 * 
	 * Used for calls the insert query for temporary Session Details data inserting
	 * (Language of the User
	 * 
	 * @param language User Language
	 * @return
	 * @throws DAOException
	 */
	public Integer insertTempSessionDetails(String language) throws DAOException {
		return insertTempSessionDetails(language, null, null);
	}

	/**
	 * 
	 * Used for calls the insert query for temporary Session Details data inserting
	 * (Language of the User and userId)
	 * 
	 * @param language User Language
	 * @param userId   User ID
	 * @return
	 * @throws DAOException
	 */
	public Integer insertTempSessionDetails(String language, String userId) throws DAOException {
		return insertTempSessionDetails(language, null, userId);
	}

	/**
	 * 
	 * Used for calls the insert query for temporary Session Details data inserting
	 * (userId)
	 * 
	 * @param userId User ID
	 * @return
	 * @throws DAOException
	 */
	public Integer insertTempSessDetUserOnly(String userId) throws DAOException {
		return insertTempSessionDetails(ConstantsCommon.LANGUAGE_ENGLISH, null, userId);
	}

	/**
	 * 
	 * Used for calls the insert query for temporary Session Details data inserting
	 * (language and running Date)
	 * 
	 * @param language User Language
	 * @param running  Date User Running Date (Application Running Date if no User
	 *                 Specific Date assigned)
	 * @return
	 * @throws DAOException
	 */
	public Integer insertTempSessionDetails(String language, Date runningDate) throws DAOException {
		return insertTempSessionDetails(language, runningDate, null);
	}

	/**
	 * 
	 * Used for calls the insert query for temporary Session Details data inserting
	 * (language and running Date)
	 * 
	 * @param running Date User Running Date (Application Running Date if no User
	 *                Specific Date assigned)
	 * @param userId  User being logged in.
	 * @return
	 * @throws DAOException
	 */
	public Integer insertTempSessionDetails(Date runningDate, String userId) throws DAOException {
		return insertTempSessionDetails(ConstantsCommon.LANGUAGE_ENGLISH, runningDate, userId);
	}

	/**
	 * 
	 * Used for calls the insert query for temporary Session Details data inserting
	 * (Language of the User and Running Date and user Id
	 * 
	 * @param language User Language
	 * @param running  Date User Running Date (Application Running Date if no User
	 *                 Specific Date assigned)
	 * @param userId   String User ID (User Id of the logged in application)
	 * @return
	 * @throws DAOException
	 */
	public Integer insertTempSessionDetails(String language, Date runningDate, String userId) throws DAOException {
		return insertTempSessionDetails(language, runningDate, userId, null);
	}

	/**
	 * 
	 * Used for calls the insert query for temporary Session Details data inserting
	 * (Language of the User and Running Date
	 * 
	 * @param language User Language
	 * @param running  (Optional can be null) Date User Running Date (Application
	 *                 Running Date if no User Specific Date assigned)
	 * @param userId   (Optional can be null) String User ID (User Id of the logged
	 *                 in application)
	 * @param appName  (Optional can be null) String application Name (application
	 *                 Name needed in the procedure)
	 * @return
	 * @throws DAOException
	 */
	public Integer insertTempSessionDetails(String language, Date runningDate, String userId, String appName)
			throws DAOException {
		return insertTempSessionDetails(language, runningDate, userId, appName, null);
	}

	/**
	 * 
	 * Used for calls the insert query for temporary Session Details data inserting
	 * (Language of the User and Running Date and user Id
	 * 
	 * @param language User Language
	 * @param running  Date User Running Date (Application Running Date if no User
	 *                 Specific Date assigned)
	 * @param userId   String User ID (User Id of the logged in application)
	 * @param appName  (Optional can be null) String application Name (application
	 *                 Name needed in the procedure)
	 * @param con      Connection connection by which to execute the insert, used
	 *                 from reporting application for pre-procedure execution
	 * @return
	 * @throws DAOException
	 */
	public Integer insertTempSessionDetails(String language, Date runningDate, String userId, String appName,
			Connection con) throws DAOException {
		BaseSC baseSc = new BaseSC();
		String theLang = language;
		if (theLang == null) {
			log.warning(
					"/*** \n Language provided Null in calling of insertTempSessionDetails, English Language will be Considered \n** /");
			theLang = ConstantsCommon.LANGUAGE_ENGLISH;
		}
		baseSc.setPreferredLanguage(theLang);
		baseSc.setRunningDate(runningDate);
		baseSc.setUserId(userId);
		baseSc.setCurrAppName(appName);
		if (con == null) {
			return (Integer) sqlMap.insert("commonLibMapper.insertTempSessionDetails", baseSc);
		} else {
			PreparedStatement stmt = sqlMap.returnReplacedSQLById("commonLibMapper.insertTempSessionDetails", baseSc,
					con);
			try {
				stmt.execute();
				return 1;
			} catch (Exception e) {
				throw new DAOException(e);
			}
		}
	}

	@Override
	public void callSqlSessionTrace(BaseObject parameter, String queryIdentifier, Connection con) throws DAOException {
		// check if trace enabled or SQL session HTTP Trace enabled
		if (ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE || ConstantsCommon.SQL_SESSION_TRACE_CODE) {
			Assert.notNull(parameter.getTraceAppName(), "TraceAppName cannot be null during Tracing");
			Assert.notNull(parameter.getTraceUserId(), "TraceUserId cannot be null during Tracing");
			Assert.notNull(queryIdentifier, "queryIdentifier cannot be null during Tracing");

			String sqlID = queryIdentifier;
			String clientIdent = null, moduleName = StringUtil.nullToEmpty(parameter.getTraceAppName());
			if (ConstantsCommon.SQL_SESSION_TRACE_CODE) {
				if (!StringUtil.nullToEmpty(parameter.getTraceUserId()).isEmpty()) {
					moduleName = moduleName.concat(" U=" + parameter.getTraceUserId());
				}
				if (!StringUtil.nullToEmpty(parameter.getTraceProgRef()).isEmpty()) {
					moduleName = moduleName.concat(" S=" + parameter.getTraceProgRef());
				}

				moduleName = moduleName.concat(" QI=" + sqlID);
			}
			// check if http session link to SQL session is enabled
			if (ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE) {
				clientIdent = parameter.getHttpSessionIdForLink();
			}
			// need to trace either if moduleName is not empty, of if http need
			// to be traced
			if (ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE || !moduleName.isEmpty()) {
				BaseSC traceSC = new BaseSC();

				traceSC.setSectionKey(clientIdent);
				traceSC.setIsOracle(ConstantsCommon.CURR_DBMS_ORACLE);

				if (ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE) {
					if (ConstantsCommon.CURR_DBMS_ORACLE == 1) // Oracle
					{
						// need to check if http session more than 64 characters
						// then to be truncated and saved in 2 columns in
						// v$session table since max length of single column is
						// 64 characters
						if (clientIdent != null && clientIdent.length() > 64) {
							traceSC.setSectionKey(clientIdent.substring(0, 64));
							traceSC.setCrudMode(clientIdent.substring(64));
						}
					} else
					// Sybase
					{
						// for sybase the details are save into sysprocesses 2
						// columns clientname and clientappname each column can
						// hold
						// maximum of 30 characters
						if (clientIdent != null && clientIdent.length() > 30) {
							traceSC.setSectionKey(clientIdent.substring(0, 30));
							traceSC.setCrudMode(clientIdent.substring(30));
						}
					}
				}

				if (!moduleName.isEmpty()) {
					traceSC.setCurrAppName(moduleName);
					// if HTTP session trace is not active in Sybase then use
					// field clientname, clientpplname
					// in Details tracing also in case more than 30/60
					// characters
					if (ConstantsCommon.CURR_DBMS_SYBASE == 1 && !ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE) {
						// checking on 25 because "Java " prefix is added inside
						// the procedure itself
						if (moduleName.length() > 25) {
							// clientname
							traceSC.setSectionKey(moduleName.substring(25));
							if (moduleName.length() > 55) {
								// clientapplname
								traceSC.setCrudMode(moduleName.substring(55));
							}
						}
					}
				}

				PreparedStatement stmt = sqlMap.returnReplacedSQLById("commonProcedureMapper.callSQLSessionTrace",
						traceSC, con);
				try {
					stmt.execute();
				} catch (Exception e) {
					Log.getInstance().error(e,
							"Error in calling callSqlSessionTrace. The process will continue Normally");
				}
			}
		}
	}

	/**
	 * return SQL query By Id
	 * 
	 * @param sqlId
	 * @param parameters
	 * @return
	 * @throws DAOException
	 */
	private PreparedStatement returnReplacedSQLById(String sqlId, Object parameters, Connection con)
			throws DAOException {
		return sqlMap.returnReplacedSQLById(sqlId, parameters, con);
	}

	/**
	 * 
	 * Used for commit the SQL Session in the Holder for current Transaction
	 * 
	 * @throws DAOException
	 */
	public void commitTransaction() throws DAOException {
		getSqlMap().commitConnection();
	}

	/**
	 * 
	 * Used for commit the SQL Session in the Holder for current Transaction
	 * 
	 * @throws DAOException
	 */
	public void rollBackTransaction() throws DAOException {
		getSqlMap().rollBackConnection();
	}

	/**
	 * Used to initialize or extends the transaction timeout with the number of
	 * seconds passed in parameters if the timeout is set on the method (method name
	 * ends with TimeOut) then it will extend it to x seconds if the method doesn't
	 * end s with TimeOut, a timeout will be initialized when calling the below
	 * method 2 timeout exceptions can be thrown when the timeout is reached : 1-
	 * java.sql.SQLTimeoutException : if the timeout is reached inside the statement
	 * 2- org.springframework.transaction.TransactionTimedOutException : if the
	 * timeout is reached before starting the statement
	 * 
	 * @param seconds
	 */
	public void extendTransactionTimeout(int seconds) {
		if (TransactionSynchronizationManager.isActualTransactionActive()) {
			DefaultTransactionStatus transactionStatus = (DefaultTransactionStatus) TransactionInterceptor
					.currentTransactionStatus();
			if (transactionStatus.getTransaction() instanceof JdbcTransactionObjectSupport) {
				JdbcTransactionObjectSupport transactionObject = (JdbcTransactionObjectSupport) transactionStatus
						.getTransaction();
				ConnectionHolder connectionHolder = transactionObject.getConnectionHolder();
				if (connectionHolder != null) {
					connectionHolder.setTimeoutInSeconds(seconds);
				}
			}
		}
	}

	public void setDatasourceJNDI(String datasourceJNDI) {
		this.datasourceJNDI = datasourceJNDI;
	}

	// 434132 Dynamic Parameters to Procedure Passing in Labeling Translation Import
	public void insertProcArg(ProcArgSC criteria) throws DAOException {
		if (ConstantsCommon.CURR_DBMS_SYBASE == 1) {
			String temTable = (String) getSqlMap().queryForObject("commonLibMapper.checkTempTableExistence",
					"#SYS_RUNTIME_PROC_ARG_TMP");
			if (StringUtil.isNotEmpty(temTable)) {
				try {
					getSqlMap().update("commonLibMapper.dropSYS_RUNTIME_PROC_ARG_TMP", new BaseObject());
				} catch (Exception e) {
					log.error(e, "Cannot drop Hash table; table do not exist yet!");
				}
			}
			getSqlMap().update("commonLibMapper.createSYS_RUNTIME_PROC_ARG_TMP", null);
		}
		sqlMap.insert("commonLibMapper.insertProcArg", criteria);
	}

	// 434132 Dynamic Parameters to Procedure Passing in Labeling Translation Import
	public void deleteProcArg(ProcArgSC criteria) throws DAOException {
		sqlMap.delete("commonLibMapper.deleteProcArg", criteria);
	}
}
