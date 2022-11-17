package com.path.lib.common.base;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;
import org.springframework.util.Assert;

import com.path.bo.common.ConstantsCommon;
import com.path.lib.common.base.ibatis3.SqlSessionFactProps;
import com.path.lib.common.base.ibatis3.SqlSessionFactoryBean;
import com.path.lib.common.base.ibatis3.SqlSessionTemplate;
import com.path.lib.common.base.ibatis3.support.SqlSessionDaoSupport;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.ApplicationContextProvider;
import com.path.lib.common.util.PathPropertyUtil;
import com.path.lib.common.util.StringUtil;
import com.path.lib.common.util.ThreadAttributes;
import com.path.lib.log.Log;
import com.path.struts2.lib.common.BaseObject;
import com.path.struts2.lib.common.ConnectionCO;

/**
 * DENISK_LATEST_VERS_UPDATED
 * 
 * @author deniskhaddad
 *
 */
public class IbatisSqlMapClient extends SqlSessionDaoSupport {
	private final static Log log = Log.getInstance();
	private Map sqlMaps;
	private String isSyb, isOra;
	private SqlSessionFactory sqlSessionFactory;

	/**
	 * Commit the current sqlMapClient transaction
	 * 
	 * @throws DAOException
	 */
	/*
	 * public void commitTransaction() throws PathDAOException { try {
	 * sqlSessionFactory.commitTransaction(); } catch(SQLException ex) { throw new
	 * PathDAOException(ex.getMessage(), ex); } }
	 */

	/**
	 * creates an instance slqmap for new connection (based on details in BaseObject
	 * ConnectionCO properties) and sets it in slqmpas map
	 */
	private IbatisSqlMapClient createNewSqlMap(BaseObject bo) throws Exception {
		if (sqlMaps == null) {
			sqlMaps = new HashMap();
		}

//	SqlSessionTemplate template = new SqlSessionTemplate();
		IbatisSqlMapClient sq = null;
		ConnectionCO connCO = bo.getConnCO();
		String theKey = connCO.getDbName();
		HashMap propsMap = new HashMap();
		SqlSessionFactProps props = (SqlSessionFactProps) ApplicationContextProvider.getApplicationContext()
				.getBean("sqlSessionFactProps");
		propsMap.put("sqlSessionFactProps", props);
		if (connCO.getDbJNDI() == null)// if no JNDI provided
		{

			// make sure username and password provided
			Assert.notNull(connCO.getDbUserName(), "DbUserName in ConnectionCO should not be null");
			Assert.notNull(connCO.getDbPassword(), "DbPassword in ConnectionCO should not be null");

			String URL = connCO.getDbURL();
			if (URL == null) {
				Assert.notNull(connCO.getDbName(), "DbName in ConnectionCO should not be null");
				Assert.notNull(connCO.getDbServerName(), "DbServerName in ConnectionCO should not be null");
				Assert.notNull(connCO.getDbServerPort(), "ServerPort in ConnectionCO should not be null");
				Assert.notNull(connCO.getDbDriverType(), "DbDriverType in ConnectionCO should not be null");
				theKey = connCO.getDbName().concat("_").concat(connCO.getDbServerName()).concat("_")
						.concat(connCO.getDbServerPort()).concat("_").concat(connCO.getDbDriverType());
			} else // full URL
			{
				theKey = URL + "_" + connCO.getDbUserName();
			}

			if (sqlMaps.containsKey(theKey)) // return sqlmapclient in case already created
			{
				log.debug("returning outside Connection from Cache for key =" + theKey);
				IbatisSqlMapClient instSq = (IbatisSqlMapClient) sqlMaps.get(theKey);
				bo.setIsOracle(Integer.parseInt(instSq.isOra));
				bo.setIsSybase(Integer.parseInt(instSq.isSyb));
				return instSq;
			}
			if (URL == null) {
				log.debug("creating outside Connection from details ");
				if (ConstantsCommon.SYBASE_DBMS.equals(connCO.getDbDriverType())) {
					URL = "jdbc:sybase:Tds:".concat(connCO.getDbServerName()).concat(":")
							.concat(connCO.getDbServerPort()).concat("/").concat(connCO.getDbName())
							.concat("?SERVER_INITIATED_TRANSACTIONS=false&amp;JCONNECT_VERSION=6.05&amp;")
							.concat(StringUtil.nullEmptyToValue(connCO.getCharset(), "charset=cp1256"));
				} else if (ConstantsCommon.ORACLE_DBMS.equals(connCO.getDbDriverType())) {
					URL = "jdbc:oracle:thin:@(description=(address_list=(load_balance=on)(failover=on)(address=(protocol=tcp)(host="
							.concat(connCO.getDbServerName()).concat(")(port=").concat(connCO.getDbServerPort())
							.concat("))(address=(protocol=tcp)(host=").concat(connCO.getDbServerName())
							.concat(")(port=").concat(connCO.getDbServerPort())
							.concat(")))(connect_data=(service_name=").concat(connCO.getDbName())
							.concat(")(failover_mode=(type=select)(method=basic))))");
				}
			}

			sq = (IbatisSqlMapClient) ApplicationContextProvider.getApplicationContext()
					.getBean("instanceSqlMapClient");

			// creating datasource from connection details.
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setUrl(URL);
			dataSource.setUsername(connCO.getDbUserName());
			dataSource.setPassword(connCO.getDbPassword());
			log.debug("creating outside Connection for DBMS: " + connCO.getDbDriverType());
			if (ConstantsCommon.SYBASE_DBMS.equals(connCO.getDbDriverType()) || URL.indexOf("sybase") > -1) {
				bo.setIsSybase(1);
				bo.setIsOracle(0);
				sq.setIsSyb("1");
				sq.setIsOra("0");
				dataSource.setDriverClassName("com.sybase.jdbc4.jdbc.SybDriver");// property file
			} else {
				bo.setIsSybase(0);
				bo.setIsOracle(1);
				sq.setIsSyb("0");
				sq.setIsOra("1");
				dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
			}
			propsMap.put("dataSource", dataSource);
//	    template.setDataSource(dataSource);
		} else // creating datasource from JNDI
		{
			theKey = "jndi_".concat(connCO.getDbJNDI());

			if (sqlMaps.containsKey(theKey)) {
				log.debug("returning JNDI outside Connection from cached Map");
				IbatisSqlMapClient instSq = (IbatisSqlMapClient) sqlMaps.get(theKey);
				bo.setIsOracle(Integer.parseInt(instSq.isOra));
				bo.setIsSybase(Integer.parseInt(instSq.isSyb));
				return instSq;
			}
			log.debug("creating JNDI outside Connection");
			JndiTemplate jndi = new JndiTemplate();
			DataSource dataSource = (DataSource) jndi.lookup(connCO.getDbJNDI());
			sq = (IbatisSqlMapClient) ApplicationContextProvider.getApplicationContext()
					.getBean("instanceSqlMapClient");
			// template.setDataSource(dataSource);
			propsMap.put("dataSource", dataSource);
		}
		/**
		 * [PathSolutions - MarwanMaddah]: synchronized added to avoid the multi load
		 * for the mappers in case of multi threads after synchronized the other threads
		 * will wait the first thread to load the mappers then the other will use the
		 * loaded mappers we did this behavior to avoid a performance and handing
		 * problem
		 */
		synchronized (sqlMaps) {
			if (sqlMaps.containsKey(theKey)) {
				log.debug("returning JNDI outside Connection from cached Map");
				IbatisSqlMapClient instSq = (IbatisSqlMapClient) sqlMaps.get(theKey);
				bo.setIsOracle(Integer.parseInt(instSq.isOra));
				bo.setIsSybase(Integer.parseInt(instSq.isSyb));
				return instSq;
			}

			SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean(propsMap);
			SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory.getObject());

			DefaultSqlSessionFactory sb = (DefaultSqlSessionFactory) ApplicationContextProvider.getApplicationContext()
					.getBean("sqlMapClientIbatisInst", new Object[] { propsMap });
			sq.setSqlSessionFactory(sb);
			// template.setSqlSessionFactory(sq.getSqlSessionFactory());
			sq.setSqlSessionTemplate(sessionTemplate);
			sqlSessionFactory.setDataSource((DataSource) propsMap.get("dataSource"));
			// sq.setDataSource(sessionTemplate.get);
			sqlMaps.put(theKey, sq);
		}
		if (connCO.getDbJNDI() != null) {
			// getsqlmap the new one and query the flags
			// IsOracle/IsSybase
			Long dbms;
			// TP 779937 if DB type not provided then read it from the database as PTH_CTRL
			// table
			if (connCO.getDbDriverType() == null) {
//		dbms = (Long) sq.queryForObject("servicesCommon.applyDbVersion", null);
				dbms = 3L;
			} else {

				if (connCO.getDbDriverType().toLowerCase().contains(ConstantsCommon.SYBASE_DBMS)) {
					dbms = 2L;
				} else if (connCO.getDbDriverType().toLowerCase().contains(ConstantsCommon.SQLSERVER_DBMS)) {
					dbms = 3L;
				} else {// oracle
					dbms = 1L;
				}
			}
			if (dbms == null || dbms.longValue() == 1) {
				sq.setIsSyb("0");
				sq.setIsOra("1");
				bo.setIsOracle(1);
				bo.setIsSybase(0);
			} else if (dbms.longValue() == 2 || dbms.longValue() == 3) {
				sq.setIsSyb("1");
				sq.setIsOra("0");
				bo.setIsOracle(0);
				bo.setIsSybase(1);
				if (dbms.longValue() == 3) {
					bo.setIsSQLServer(1);
				} else {
					bo.setIsSQLServer(0);
				}
			}
		}

		return sq;
	}

	/**
	 * Method to set details into Running Thread about user, application, and screen
	 * 
	 * @param parameter
	 */
	private void includeThreadInfo(Object parameter) {
		if (parameter instanceof BaseObject) {
			BaseObject theBaseObj = (BaseObject) parameter;
			// setting info into a running thread to be able to display in debugging info in
			// the log files
			if (theBaseObj.getTraceUserId() != null) {
				ThreadAttributes.set(ConstantsCommon.PATH_INFO_KEY,
						StringUtil.nullToEmpty(theBaseObj.getTraceAppName()).concat("  ")
								.concat(StringUtil.nullEmptyToValue(theBaseObj.getTraceUserId(), "UNKNOWN_USER"))
								.concat("  ")
								.concat(StringUtil.nullEmptyToValue(theBaseObj.getTraceProgRef(), "UNKNOWN_PROG_REF")));
				// add username defined in the provided trace object and set it inside
				// ThreadAttributes to be used later in Log.java to add logs by user
				ThreadAttributes.set(ConstantsCommon.PATH_LOG_USER_NAME_KEY, StringUtil
						.nullToEmpty(StringUtil.nullEmptyToValue(theBaseObj.getTraceUserId(), "UNKNOWN_USER")));

			} else {
				removeThreadInfo();
			}
		} else {
			removeThreadInfo();
		}
	}

	/**
	 * Method to removes the details that was added into Running Thread about user,
	 * application, and screen
	 * 
	 * @param parameter
	 */
	private void removeThreadInfo() {
		ThreadAttributes.set(ConstantsCommon.PATH_INFO_KEY, null);
		ThreadAttributes.set(ConstantsCommon.PATH_LOG_USER_NAME_KEY, null);
	}

	/**
	 * returns sqlsession template base on needed connection (old from singleton
	 * sqlmap or a new one from new created instance sqlmap)
	 * 
	 * @param bo
	 * @return
	 */
	private SqlSession returnCorrectSqlSession(Object boParam) throws DAOException {
		if (boParam instanceof BaseObject) {
			BaseObject bo = (BaseObject) boParam;
			if (bo.getConnCO() != null) {
				try {
					IbatisSqlMapClient instSq = createNewSqlMap(bo);
					return instSq.getSqlSession();
				} catch (Exception e) {
					log.error(e.getMessage());
					throw new DAOException("Error setting new SqlMap for dbName " + bo.getConnCO().getDbName(), e);
				}
			}
		}
		return getSqlSession(); // return template from singleton sqlmap
	}

	/**
	 * return SQL query By Id
	 * 
	 * @param sqlId      query Id which statement to return
	 * @param parameters parameter object accepted by the sql
	 * @return
	 * @throws DAOException
	 */
	public PreparedStatement returnReplacedSQLById(String sqlId, Object parameters, Connection con)
			throws DAOException {
		try {
			includeDbVersion(parameters);
			MappedStatement ms = getSqlSession().getConfiguration().getMappedStatement(sqlId);
			BoundSql boundSql = ms.getBoundSql(parameters);
			PreparedStatement stmt = con.prepareStatement(boundSql.getSql());
			List<ParameterMapping> theParamMaps = boundSql.getParameterMappings();
			Class paramType;
			Object paramValue;
			int i = 1;
			for (Iterator<ParameterMapping> iterator = theParamMaps.iterator(); iterator.hasNext();) {
				ParameterMapping parameterMapping = iterator.next();
				paramType = parameterMapping.getJavaType();
				paramValue = PathPropertyUtil.returnProperty(parameters, parameterMapping.getProperty());
				if (String.class.equals(paramType)) {
					if (paramValue == null) {
						stmt.setNull(i++, Types.VARCHAR);
					} else {
						stmt.setString(i++, (String) paramValue);
					}
				} else if (BigDecimal.class.equals(paramType)) {
					if (paramValue == null) {
						stmt.setNull(i++, Types.NUMERIC);
					} else {
						stmt.setBigDecimal(i++, (BigDecimal) paramValue);
					}
				} else if (Date.class.equals(paramType)) {
					if (paramValue == null) {
						stmt.setNull(i++, Types.DATE);
					} else {
						stmt.setDate(i++, new java.sql.Date(((Date) paramValue).getTime()));
					}
				} else {
					stmt.setObject(i++, paramValue);
				}
			}
			return stmt;
		} catch (Exception ex) {
			log.error(ex, "Error in returning replaced SQL by ID method returnReplacedSQLById");
			throw new DAOException(ex.getMessage(), ex);
		}
	}

	private void includeDbVersion(Object object) {
		if (object instanceof BaseObject) {
			((BaseObject) object).setIsSybase(ConstantsCommon.CURR_DBMS_SYBASE);
			((BaseObject) object).setIsOracle(ConstantsCommon.CURR_DBMS_ORACLE);
			((BaseObject) object).setIsSQLServer(ConstantsCommon.CURR_DBMS_SQLSERVER);
		}
	}

	public void setSqlMapClient(SqlSessionFactory sqlMapClient) {
		super.setSqlSessionFactory(sqlMapClient);
		// set private property for SqlSessionFactory to be used in case of Batch
		// SEssion operation needed in returnBatchSession
		this.sqlSessionFactory = sqlMapClient;
	}

	/**
	 * MEthod to return SQLSession of Batch Execution Type to used in bulk operation
	 * 
	 * @param object BaseObject can be null
	 * @return SqlSession Object
	 * @throws DAOException
	 */
	public SqlSession returnBatchSession(Object object) throws DAOException {
		try {
			log.debug("returnBatchSession returning Batch Session ");
			if (object != null) {
				includeDbVersion(object);
			}
			return sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		} catch (DataAccessException ex) {
			log.error(ex, ex.getMessage());
			throw new DAOException(ex.getMessage(), ex);
		}
	}

	/**
	 * Delete string from an object
	 * 
	 * @param string String
	 * @param object Object
	 * @return int
	 * @throws DAOException
	 */
	public int delete(String string, Object object) throws DAOException {

		try {
			includeThreadInfo(object);
			log.debug("Executing delete SQL_ID " + string);
			includeDbVersion(object);
			return returnCorrectSqlSession(object).delete(string, object);
//	    return getSqlSession().delete(string, object);
		} catch (DataAccessException ex) {
			log.error(ex, ex.getMessage());
			throw new DAOException(ex.getMessage(), ex);
		} finally {
			removeThreadInfo();
		}
	}

	/**
	 * End any transaction
	 * 
	 * @throws PathDAOException method
	 */
	/*
	 * public void endTransaction() throws PathDAOException { try {
	 * sqlSessionFactory.endTransaction(); } catch(SQLException ex) { throw new
	 * PathDAOException(ex.getMessage(), ex); } }
	 */
	/**
	 * performs a rollback of all statement performed in the current connection
	 * after last commit if exists
	 * 
	 * @throws DAOException
	 */
	public void rollBackConnection() throws DAOException {
		try {
			getSqlSession().getConnection().rollback();
//	    // SqlSessionUtils.getSqlSession(getSqlSessionFactory()).getConnection().rollback();
//	    SqlSessionHolder holder = (SqlSessionHolder) TransactionSynchronizationManager
//		    .getResource(getSqlSessionFactory());
//	    if(holder != null)
//	    {
//		log.info("Forcing to rollback Transactional SQL Session");
//		holder.getSqlSession().getConnection().rollback();
//	    }
		} catch (SQLException ex) {
			log.error(ex, ex.getMessage());
			throw new DAOException(ex.getMessage(), ex);
		}
	}

	/**
	 * set auto-commit flag in the current Connection
	 * 
	 * @throws DAOException
	 */
	public void setAutoCommit(boolean autoCommit) throws DAOException {
		try {
			getSqlSession().getConnection().setAutoCommit(autoCommit);
//	    SqlSessionHolder holder = (SqlSessionHolder) TransactionSynchronizationManager
//		    .getResource(getSqlSessionFactory());
//	    if(holder != null)
//	    {
//		// SqlSessionUtils.getSqlSession(getSqlSessionFactory()).getConnection().setAutoCommit(autoCommit);
//		log.info("setting autocommit to " + autoCommit + " for Transactional SQL Session");
//		holder.getSqlSession().getConnection().setAutoCommit(autoCommit);
//	    }

		} catch (SQLException ex) {
			log.error(ex, ex.getMessage());
			throw new DAOException(ex.getMessage(), ex);
		}
	}

	/**
	 * comits all the statements in the current Connection
	 * 
	 * @throws DAOException
	 */
	public void commitConnection() throws DAOException {
		try {
			// getSqlSession().getConnection().commit(); // this line not worked properly in
			// Reporting caused connection = null issue
			// Connection con =
			// getSqlSession().getConfiguration().getEnvironment().getDataSource().getConnection();
			Connection con = getSqlSession().getConnection();// reverting version number 108550, the method
			// getSqlSession().getConfiguration().getEnvironment().getDataSource().getConnection();
			// is creating a new connection
			// and not getting the current connection to be committed.
			if (con != null && !con.isClosed() && !con.getAutoCommit()) {
				con.commit();
			}
		} catch (Exception ex) {
			log.error(ex, ex.getMessage());
			throw new DAOException(ex.getMessage(), ex);
		}
	}

	/**
	 * Execute a batch process
	 * 
	 * @return int
	 * @throws PathDAOException
	 */
	/*
	 * public int executeBatch() throws PathDAOException { try { return
	 * sqlSessionFactory.executeBatch(); } catch(SQLException ex) { throw new
	 * PathDAOException(ex.getMessage(), ex); } }
	 * 
	 * public void startBatch(boolean openSession) throws PathDAOException { try {
	 * sqlSessionFactory.startTransaction(); sqlSessionFactory.startBatch(); }
	 * catch(SQLException ex) { try { sqlSessionFactory.endTransaction();
	 * }catch(SQLException e){} throw new PathDAOException(ex.getMessage(), ex); } }
	 * 
	 * public int executeBatch(boolean openSession) throws PathDAOException { try {
	 * return sqlSessionFactory.executeBatch(); } catch(SQLException ex) { throw new
	 * PathDAOException(ex.getMessage(), ex); }finally { try {
	 * sqlSessionFactory.endTransaction(); }catch(SQLException ex) { throw new
	 * PathDAOException(ex.getMessage(), ex); } }
	 * 
	 * }
	 */
	/**
	 * Flush all the data from sqlMapClient cache
	 * 
	 * @param string String
	 */
	/*
	 * public void flushDataCache(String string) {
	 * //sqlSessionFactory.flushDataCache(string);
	 * getSqlSessionTemplate().flushDataCache(); }
	 */

	/**
	 * Flush all data from sqlMapClient cache
	 */
	public void flushDataCache() {
		// sqlSessionFactory.flushDataCache();
//	getSqlSessionTemplate().flushDataCache();
	}

	/**
	 * Get the current connection
	 * 
	 * @return Connection
	 * @throws PathDAOException method
	 */
	/*
	 * public Connection getCurrentConnection() throws PathDAOException { try {
	 * return getSqlSessionTemplate().getCurrentConnection(); } catch(SQLException
	 * ex) { throw new PathDAOException(ex.getMessage(), ex); } }
	 */

	/**
	 * Get the current user's connection
	 * 
	 * @return Connection
	 * @throws PathDAOException
	 */
	/*
	 * public Connection getUserConnection() throws PathDAOException { try { return
	 * sqlSessionFactory.getCurrentConnection(); } catch(SQLException ex) { throw
	 * new PathDAOException(ex.getMessage(), ex); } }
	 */
	/**
	 * Insert String into an object
	 * 
	 * @param string String
	 * @param object Object
	 * @return Object
	 * @throws DAOException
	 */
	public Object insert(String string, Object object) throws DAOException {
		try {
			includeThreadInfo(object);
			log.debug("Executing insert SQL_ID " + string);
			includeDbVersion(object);
//	    return getSqlSession().insert(string,object);
			return returnCorrectSqlSession(object).insert(string, object);
		} catch (DataAccessException ex) {
			log.error(ex, ex.getMessage());
			throw new DAOException(ex.getMessage(), ex);
		} finally {
			removeThreadInfo();
		}
	}

	/**
	 * Open a session of the connection
	 * 
	 * @param connection Connection
	 * @return SqlMapSession
	 */
	public SqlSession openSession(Connection connection) {
		return getSqlSession();
//	return getSqlSessionFactory().openSession(connection);
	}

	/**
	 * Open a session of the connection when no connection is provided
	 * 
	 * @return SqlMapSession
	 */
//    public SqlSession openSession()
//    {
//	return getSqlSessionFactory().openSession();
//    }

	/**
	 * Query the map for list of objects satisfying the given criteria
	 * 
	 * @param string String
	 * @param object Object
	 * @param _int   int
	 * @param _int3  int
	 * @return List
	 * @throws DAOException
	 */
	public List queryForList(String string, Object object, int _int, int _int3) throws DAOException {
		try {
			includeThreadInfo(object);
			log.debug("Executing queryForList with FLIP SQL_ID " + string);
			includeDbVersion(object);
			RowBounds rb = new RowBounds(_int, _int3);
//	    return getSqlSession().selectList(string, object, rb);
			return returnCorrectSqlSession(object).selectList(string, object, rb);
		} catch (DataAccessException ex) {
			log.error(ex, ex.getMessage());
			throw new DAOException(ex.getMessage(), ex);
		} finally {
			removeThreadInfo();
		}
	}

	/**
	 * Query the sqlmap for list of objects when only a String an an object are
	 * provided
	 * 
	 * @param string String
	 * @param object Object
	 * @return List
	 * @throws DAOException
	 */
	public List queryForList(String string, Object object) throws DAOException {
		try {
			includeThreadInfo(object);
			log.debug("Executing queryForList SQL_ID " + string);
			includeDbVersion(object);
//	    return getSqlSession().selectList(string,object);
			return returnCorrectSqlSession(object).selectList(string, object);
		} catch (DataAccessException ex) {
			log.error(ex, ex.getMessage());
			throw new DAOException(ex.getMessage(), ex);
		} finally {
			removeThreadInfo();
		}
	}

	/**
	 * Query the sqlMap for a hashmap satisfying the given criteria
	 * 
	 * @param string  String
	 * @param object  Object
	 * @param string2 String
	 * @return Map
	 * @throws DAOException
	 */
	public Map queryForMap(String string, Object object, String string2) throws DAOException {
		try {
			includeThreadInfo(object);
			log.debug("Executing queryForMap SQL_ID " + string);
			includeDbVersion(object);
//	    return getSqlSession().selectMap(string, object, string2);
			return returnCorrectSqlSession(object).selectMap(string, object, string2);
		} catch (DataAccessException ex) {
			log.error(ex, ex.getMessage());
			throw new DAOException(ex.getMessage(), ex);
		} finally {
			removeThreadInfo();
		}
	}

	/**
	 * Query the sqlMap for an object satisfying the given criteria
	 * 
	 * @param string String
	 * @param object Object
	 * @return Object
	 * @throws DAOException
	 * @todo Implement this com.ibatis.sqlmap.client.SqlMapExecutor method
	 */
	public Object queryForObject(String string, Object object) throws DAOException {
		try {
			includeThreadInfo(object);
			log.debug("Executing queryForObject SQL_ID " + string);
			includeDbVersion(object);
			return returnCorrectSqlSession(object).selectOne(string, object);
//	    Object testObj = sq.selectOne(string, object);
//	    Object testObj = getSqlSession().selectOne(string, object);
//	    sq.flushDataCache();
//	    SqlSessionHolder holder = (SqlSessionHolder) TransactionSynchronizationManager.getResource(getSqlSessionFactory());
//	    if(holder != null)
//	    {
//		holder.getSqlSession().clearCache();
//	    }
//	    return testObj;
		} catch (DataAccessException ex) {
			log.error(ex, ex.getMessage());
			throw new DAOException(ex.getMessage(), ex);
		} finally {
			removeThreadInfo();
		}
	}

	/**
	 * Start the batch process for the sqlMapClient
	 * 
	 * @throws PathDAOException
	 */
	/*
	 * public void startBatch() throws PathDAOException { try {
	 * sqlSessionFactory.startBatch(); } catch(SQLException ex) { throw new
	 * PathDAOException(ex.getMessage(), ex); } }
	 */

	/**
	 * Start the transaction for the sqlMapClient
	 * 
	 * @throws PathDAOException
	 */
	/*
	 * public void startTransaction() throws PathDAOException { try {
	 * sqlSessionFactory.startTransaction(); } catch(SQLException ex) { throw new
	 * PathDAOException(ex.getMessage(), ex); } }
	 */

	/**
	 * Start the transaction for the sqlMapClient
	 * 
	 * @param _int int
	 * @throws PathDAOException
	 */
	/*
	 * public void startTransaction(int _int) throws PathDAOException { try {
	 * sqlSessionFactory.startTransaction(_int); } catch(SQLException ex) { throw
	 * new PathDAOException(ex.getMessage(), ex); } }
	 */
	/**
	 * Update the sqlMapClient using the given criteria
	 * 
	 * @param string String
	 * @param object Object
	 * @return int
	 * @throws DAOException
	 */
	public int update(String string, Object object) throws DAOException {
		try {
			includeThreadInfo(object);
			log.debug("Executing update SQL_ID " + string);
			includeDbVersion(object);
			return returnCorrectSqlSession(object).update(string, object);
//	    return getSqlSession().update(string, object);
		} catch (DataAccessException ex) {
			log.error(ex, ex.getMessage());
			throw new DAOException(ex.getMessage(), ex);
		} finally {
			removeThreadInfo();
		}
	}

	/**
	 * This method is used to return a connection from datasource without passing
	 * through mybatis sqlsession
	 */
	public Connection returnConnection() throws DAOException {
		try {
			return getSqlSession().getConfiguration().getEnvironment().getDataSource().getConnection();
		} catch (SQLException ex) {
			log.error(ex, ex.getMessage());
			throw new DAOException(ex.getMessage(), ex);
		}
	}

	public Map getSqlMaps() {
		return sqlMaps;
	}

	public void setSqlMaps(Map sqlMaps) {
		this.sqlMaps = sqlMaps;
	}

	public String getIsSyb() {
		return isSyb;
	}

	public void setIsSyb(String isSyb) {
		this.isSyb = isSyb;
	}

	public String getIsOra() {
		return isOra;
	}

	public void setIsOra(String isOra) {
		this.isOra = isOra;
	}

}
