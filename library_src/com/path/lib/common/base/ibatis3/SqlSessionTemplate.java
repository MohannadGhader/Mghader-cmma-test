package com.path.lib.common.base.ibatis3;
/**DENISK_LATEST_VERS_UPDATED
 *    Copyright 2010-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

import static com.path.lib.common.base.ibatis3.SqlSessionUtils.closeSqlSession;
import static com.path.lib.common.base.ibatis3.SqlSessionUtils.getSqlSession;
import static com.path.lib.common.base.ibatis3.SqlSessionUtils.isSqlSessionTransactional;
import static java.lang.reflect.Proxy.newProxyInstance;
import static org.apache.ibatis.reflection.ExceptionUtil.unwrapThrowable;
import static org.springframework.util.Assert.notNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;

import com.path.bo.common.ConstantsCommon;
import com.path.lib.common.util.StringUtil;
import com.path.lib.log.Log;
import com.path.struts2.lib.common.BaseObject;
import com.path.struts2.lib.common.BaseSC;


/**
 * Thread safe, Spring managed, {@code SqlSession} that works with Spring
 * transaction management to ensure that that the actual SqlSession used is the
 * one associated with the current Spring transaction. In addition, it manages
 * the session life-cycle, including closing, committing or rolling back the
 * session as necessary based on the Spring transaction configuration.
 * <p>
 * The template needs a SqlSessionFactory to create SqlSessions, passed as a
 * constructor argument. It also can be constructed indicating the executor type
 * to be used, if not, the default executor type, defined in the session factory
 * will be used.
 * <p>
 * This template converts MyBatis PersistenceExceptions into unchecked
 * DataAccessExceptions, using, by default, a {@code MyBatisExceptionTranslator}.
 * <p>
 * Because SqlSessionTemplate is thread safe, a single instance can be shared
 * by all DAOs; there should also be a small memory savings by doing this. This
 * pattern can be used in Spring configuration files as follows:
 *
 * <pre class="code">
 * {@code
 * <bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
 *   <constructor-arg ref="sqlSessionFactory" />
 * </bean>
 * }
 * </pre>
 *
 * @author Putthibong Boonbong
 * @author Hunter Presnall
 * @author Eduardo Macarron
 * 
 * @see SqlSessionFactory
 * @see MyBatisExceptionTranslator
 * @version $Id$
 */
public class SqlSessionTemplate implements SqlSession {

  private final SqlSessionFactory sqlSessionFactory;

  private final ExecutorType executorType;

  private final SqlSession sqlSessionProxy;

  private final PersistenceExceptionTranslator exceptionTranslator;

  /**
   * Constructs a Spring managed SqlSession with the {@code SqlSessionFactory}
   * provided as an argument.
   *
   * @param sqlSessionFactory
   */
  public SqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    this(sqlSessionFactory, sqlSessionFactory.getConfiguration().getDefaultExecutorType());
  }

  /**
   * Constructs a Spring managed SqlSession with the {@code SqlSessionFactory}
   * provided as an argument and the given {@code ExecutorType}
   * {@code ExecutorType} cannot be changed once the {@code SqlSessionTemplate}
   * is constructed.
   *
   * @param sqlSessionFactory
   * @param executorType
   */
  public SqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType) {
    this(sqlSessionFactory, executorType,
        new MyBatisExceptionTranslator(
            sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(), true));
  }

  /**
   * Constructs a Spring managed {@code SqlSession} with the given
   * {@code SqlSessionFactory} and {@code ExecutorType}.
   * A custom {@code SQLExceptionTranslator} can be provided as an
   * argument so any {@code PersistenceException} thrown by MyBatis
   * can be custom translated to a {@code RuntimeException}
   * The {@code SQLExceptionTranslator} can also be null and thus no
   * exception translation will be done and MyBatis exceptions will be
   * thrown
   *
   * @param sqlSessionFactory
   * @param executorType
   * @param exceptionTranslator
   */
  public SqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType,
      PersistenceExceptionTranslator exceptionTranslator) {

    notNull(sqlSessionFactory, "Property 'sqlSessionFactory' is required");
    notNull(executorType, "Property 'executorType' is required");

    this.sqlSessionFactory = sqlSessionFactory;
    this.executorType = executorType;
    this.exceptionTranslator = exceptionTranslator;
    this.sqlSessionProxy = (SqlSession) newProxyInstance(
        SqlSessionFactory.class.getClassLoader(),
        new Class[] { SqlSession.class },
        new SqlSessionInterceptor());
  }

  public SqlSessionFactory getSqlSessionFactory() {
    return this.sqlSessionFactory;
  }

  public ExecutorType getExecutorType() {
    return this.executorType;
  }

  public PersistenceExceptionTranslator getPersistenceExceptionTranslator() {
    return this.exceptionTranslator;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T> T selectOne(String statement) {
    return this.sqlSessionProxy.<T> selectOne(statement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T> T selectOne(String statement, Object parameter) {
	if(ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE || ConstantsCommon.SQL_SESSION_TRACE_ALL_CODE)
  	{
  	    // SQL session Trace calling
  	    callSqlSessionTrace(statement, parameter);
  	}
    return this.sqlSessionProxy.<T> selectOne(statement, parameter);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
    return this.sqlSessionProxy.<K, V> selectMap(statement, mapKey);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
	if(ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE || ConstantsCommon.SQL_SESSION_TRACE_ALL_CODE)
  	{
  	    // SQL session Trace calling
  	    callSqlSessionTrace(statement, parameter);
  	}
    return this.sqlSessionProxy.<K, V> selectMap(statement, parameter, mapKey);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
	if(ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE || ConstantsCommon.SQL_SESSION_TRACE_ALL_CODE)
  	{
  	    // SQL session Trace calling
  	    callSqlSessionTrace(statement, parameter);
  	}
    return this.sqlSessionProxy.<K, V> selectMap(statement, parameter, mapKey, rowBounds);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <E> List<E> selectList(String statement) {
    return this.sqlSessionProxy.<E> selectList(statement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <E> List<E> selectList(String statement, Object parameter) {
	if(ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE || ConstantsCommon.SQL_SESSION_TRACE_ALL_CODE)
  	{
  	    // SQL session Trace calling
  	    callSqlSessionTrace(statement, parameter);
  	}
    return this.sqlSessionProxy.<E> selectList(statement, parameter);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
	if(ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE || ConstantsCommon.SQL_SESSION_TRACE_ALL_CODE)
  	{
  	    // SQL session Trace calling
  	    callSqlSessionTrace(statement, parameter);
  	}
    return this.sqlSessionProxy.<E> selectList(statement, parameter, rowBounds);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void select(String statement, ResultHandler handler) {
    this.sqlSessionProxy.select(statement, handler);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void select(String statement, Object parameter, ResultHandler handler) {
	if(ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE || ConstantsCommon.SQL_SESSION_TRACE_ALL_CODE)
  	{
  	    // SQL session Trace calling
  	    callSqlSessionTrace(statement, parameter);
  	}
    this.sqlSessionProxy.select(statement, parameter, handler);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
	if(ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE || ConstantsCommon.SQL_SESSION_TRACE_ALL_CODE)
  	{
  	    // SQL session Trace calling
  	    callSqlSessionTrace(statement, parameter);
  	}
    this.sqlSessionProxy.select(statement, parameter, rowBounds, handler);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int insert(String statement) {
    return this.sqlSessionProxy.insert(statement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int insert(String statement, Object parameter) {
	if(ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE || ConstantsCommon.SQL_SESSION_TRACE_ALL_CODE)
  	{
  	    // SQL session Trace calling
  	    callSqlSessionTrace(statement, parameter);
  	}
    return this.sqlSessionProxy.insert(statement, parameter);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int update(String statement) {
    return this.sqlSessionProxy.update(statement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int update(String statement, Object parameter) {
	// SQL session Trace calling
  	callSqlSessionTrace(statement, parameter);
    return this.sqlSessionProxy.update(statement, parameter);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int delete(String statement) {
    return this.sqlSessionProxy.delete(statement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int delete(String statement, Object parameter) {
	// SQL session Trace calling
	callSqlSessionTrace(statement, parameter);
    return this.sqlSessionProxy.delete(statement, parameter);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T> T getMapper(Class<T> type) {
    return getConfiguration().getMapper(type, this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void commit() {
    throw new UnsupportedOperationException("Manual commit is not allowed over a Spring managed SqlSession");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void commit(boolean force) {
    throw new UnsupportedOperationException("Manual commit is not allowed over a Spring managed SqlSession");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void rollback() {
    throw new UnsupportedOperationException("Manual rollback is not allowed over a Spring managed SqlSession");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void rollback(boolean force) {
    throw new UnsupportedOperationException("Manual rollback is not allowed over a Spring managed SqlSession");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void close() {
    throw new UnsupportedOperationException("Manual close is not allowed over a Spring managed SqlSession");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clearCache() {
    this.sqlSessionProxy.clearCache();
  }

  /**
   * {@inheritDoc}
   *
   */
  @Override
  public Configuration getConfiguration() {
    return this.sqlSessionFactory.getConfiguration();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Connection getConnection() {
    return this.sqlSessionProxy.getConnection();
  }

  /**
   * {@inheritDoc}
   *
   * @since 1.0.2
   *
   */
  @Override
  public List<BatchResult> flushStatements() {
    return this.sqlSessionProxy.flushStatements();
  }

  /**
   * Proxy needed to route MyBatis method calls to the proper SqlSession got
   * from Spring's Transaction Manager
   * It also unwraps exceptions thrown by {@code Method#invoke(Object, Object...)} to
   * pass a {@code PersistenceException} to the {@code PersistenceExceptionTranslator}.
   */
  private class SqlSessionInterceptor implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      SqlSession sqlSession = getSqlSession(
          SqlSessionTemplate.this.sqlSessionFactory,
          SqlSessionTemplate.this.executorType,
          SqlSessionTemplate.this.exceptionTranslator);
      try {
        Object result = method.invoke(sqlSession, args);
        if (!isSqlSessionTransactional(sqlSession, SqlSessionTemplate.this.sqlSessionFactory)) {
          // force commit even on non-dirty sessions because some databases require
          // a commit/rollback before calling close()
          sqlSession.commit(true);
        }
        return result;
      } catch (Throwable t) {
        Throwable unwrapped = unwrapThrowable(t);
        if (SqlSessionTemplate.this.exceptionTranslator != null && unwrapped instanceof PersistenceException) {
          // release the connection to avoid a deadlock if the translator is no loaded. See issue #22
          closeSqlSession(sqlSession, SqlSessionTemplate.this.sqlSessionFactory);
          sqlSession = null;
          Throwable translated = SqlSessionTemplate.this.exceptionTranslator.translateExceptionIfPossible((PersistenceException) unwrapped);
          if (translated != null) {
            unwrapped = translated;
          }
        }
        throw unwrapped;
      } finally {
        if (sqlSession != null) {
          closeSqlSession(sqlSession, SqlSessionTemplate.this.sqlSessionFactory);
        }
      }
    }
  }


  /**
   * method that performs tracing for SQL session for ability to debug any
   * possible lock.
   * 
   * @param parameter Parameter being passed to the SQL query that is
   *            executed.
   */
  private void callSqlSessionTrace(String currStatement, Object parameter)
	    throws DataAccessException
  {
	if(parameter instanceof BaseObject)
	{
	    BaseObject theBaseObj = (BaseObject) parameter;
	    if(ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE || ConstantsCommon.SQL_SESSION_TRACE_CODE)
	    {
		String sqlID = currStatement;
		String clientIdent = null, 
			moduleName = StringUtil.nullToEmpty(theBaseObj.getTraceAppName());
		if(ConstantsCommon.SQL_SESSION_TRACE_CODE)
		{
		    if(!StringUtil.nullToEmpty(theBaseObj.getTraceUserId()).isEmpty())
		    {
			moduleName = moduleName.concat(" U=" + theBaseObj.getTraceUserId());
		    }
		    if(!StringUtil.nullToEmpty(theBaseObj.getTraceProgRef()).isEmpty())
		    {
			moduleName = moduleName.concat(" S=" + theBaseObj.getTraceProgRef());
		    }
		    
		    // setting executed SQL id into this variable, removing name
		    // space of mybatis
		    int dotIndx = currStatement.lastIndexOf(".");
		    if(dotIndx > 0)
		    {
			sqlID = currStatement.substring(dotIndx + 1);
		    }
		    
		     moduleName = moduleName.concat(" QI="+sqlID);
		}
		// check if http session link to SQL session is enabled
		if(ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE)
		{
		    clientIdent = theBaseObj.getHttpSessionIdForLink();
		}
		// need to trace either if moduleName is not empty, of if http need to be traced
		 if(ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE || !moduleName.isEmpty())
		 {
			BaseSC traceSC = new BaseSC();
			
			traceSC.setSectionKey(clientIdent);
			traceSC.setIsOracle(ConstantsCommon.CURR_DBMS_ORACLE);
			
			if(ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE)
			{
			    if(ConstantsCommon.CURR_DBMS_ORACLE == 1) // Oracle
			    {
				// need to check if http session more than 64 characters then to be truncated and saved in 2 columns in 
				// v$session table since max length of single column is 64 characters
				if(clientIdent != null && clientIdent.length() > 64)
				{
				    traceSC.setSectionKey(clientIdent.substring(0,64));
				    traceSC.setCrudMode(clientIdent.substring(64));
				}
			    }
			    else // Sybase
			    {
				// for sybase the details are save into sysprocesses 2 columns clientname and clientappname each column can hold 
				// maximum of 30 characters
				if(clientIdent != null && clientIdent.length() > 30)
				{
				    traceSC.setSectionKey(clientIdent.substring(0,30));
				    traceSC.setCrudMode(clientIdent.substring(30));
				}
			    }
			}
			
			if(!moduleName.isEmpty())
			{
			    traceSC.setCurrAppName(moduleName);
			    // if HTTP session trace is not active in Sybase then use field clientname, clientpplname 
			    // in Details tracing also in case more than 30/60 characters
			    if(ConstantsCommon.CURR_DBMS_SYBASE == 1 && !ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE )
			    {
				// checking on 25 because "Java " prefix is added inside the procedure itself
				if(moduleName.length() > 25)
				{
				    // clientname
				    traceSC.setSectionKey(moduleName.substring(25));
				    if(moduleName.length() > 55)
				    {
					// clientapplname
					traceSC.setCrudMode(moduleName.substring(55));
				    }
				}
			    }
			}
			Log.getInstance().trace(
				"calling SQL session Trace for SQL_identifier parameter " + clientIdent + " moduleName ="
					+ moduleName + " SQL_id=" + sqlID);
			try
			{
			    this.sqlSessionProxy.update("commonProcedureMapper.callSQLSessionTrace", traceSC);
			}
			catch(Exception ex)
			{
			    Log.getInstance().error(ex,
				    "Error in calling SQLSessionTrace. The process will continue Normally");
			}
		    }
	    }
	}
  }

  /**
   * A Cursor offers the same results as a List, except it fetches data lazily using an Iterator.
   * Starting from myBatis 3.5.4
   * T - the returned cursor element type.
   * @param statement - Unique identifier matching the statement to use.
   * @return Cursor of mapped objects
   * @author HusseinZaraket
   */
  @Override
  public <T> Cursor<T> selectCursor(String statement) {
	// TODO Auto-generated method stub
	return this.sqlSessionProxy.<T> selectCursor(statement);
  }

  /**
   * A Cursor offers the same results as a List, except it fetches data lazily using an Iterator.
   * Starting from myBatis 3.5.4
   * (T) the returned cursor element type.
   * @param statement - Unique identifier matching the statement to use.
   * @param parameter - A parameter object to pass to the statement.
   * @return Cursor of mapped objects
   * @author HusseinZaraket
   */
  @Override
  public <T> Cursor<T> selectCursor(String statement, Object parameter) {
	// TODO Auto-generated method stub
	if(ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE || ConstantsCommon.SQL_SESSION_TRACE_ALL_CODE)
  	{
  	    // SQL session Trace calling
  	    callSqlSessionTrace(statement, parameter);
  	}
	return this.sqlSessionProxy.<T> selectCursor(statement, parameter);
  }

  /**
   * A Cursor offers the same results as a List, except it fetches data lazily using an Iterator.
   * Starting from myBatis 3.5.4
   * T - the returned cursor element type.
   * @param statement - Unique identifier matching the statement to use.
   * @param parameter - A parameter object to pass to the statement.
   * @param rowBounds - Bounds to limit object retrieval.
   * @return Cursor of mapped objects
   * @author HusseinZaraket
   */
  @Override
	public <T> Cursor<T> selectCursor(String statement, Object parameter, RowBounds rowBounds) {
	// TODO Auto-generated method stub
    if(ConstantsCommon.SQL_SESSION_HTTP_TRACE_CODE || ConstantsCommon.SQL_SESSION_TRACE_ALL_CODE)
  	{
  	    // SQL session Trace calling
  	    callSqlSessionTrace(statement, parameter);
  	}
	return this.sqlSessionProxy.<T> selectCursor(statement, parameter, rowBounds);
  }

}
