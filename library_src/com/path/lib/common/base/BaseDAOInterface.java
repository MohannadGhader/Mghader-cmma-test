package com.path.lib.common.base;

import java.sql.Connection;
import java.util.Date;

import com.path.lib.common.exception.DAOException;
import com.path.struts2.lib.common.BaseObject;

/**
 * 
 * Contains common CRUD methods that will be used once per DB Table. To be extended in any custom DAO 
 * which needs to apply CRUD. 
 */
public interface BaseDAOInterface
{
	/**
	 * 
	 * Used for  the deletion of a DB record.
	 * @param Type object is used to support all passed VOs
	 * @return number of records deleted
	 * @throws DAOException
	 */
	public Integer delete(Object obj) throws  DAOException;
	
	/**
	 * Applies update of a DB record
	 * @param obj Type object is used to support all passed VOs
	 * @return number of records updated
	 * @throws DAOException
	 */
	public Integer update(Object obj) throws  DAOException;

	/**
	 * Applies insertion of a DB record
	 * @param obj Type object is used to support all passed VOs
	 * @return	Primary key value in case of single PK
	 * @throws DAOException
	 */
	public Integer insert(Object obj) throws  DAOException;
	
	/**
	 * 
	 * Used for calls the insert query for temporary Session Details data inserting (Language of the User)
	 * CAlled before Procedure CAlling so that procedure can translated message to correct Language
	 * 
	 * @param language User Language
	 * @return
	 * @throws DAOException
	 */
	public Integer insertTempSessionDetails(String language) throws DAOException;
	/**
         * 
         * Used for calls the insert query for temporary Session Details data
         * inserting (Language of the User and userId)
         * 
         * @param language User Language
         * @param userId User ID
         * @return
         * @throws DAOException
         */
        public Integer insertTempSessionDetails(String language, String userId) throws DAOException;
	/**
         * 
         * Used for calls the insert query for temporary Session Details data
         * inserting (userId)
         * 
         * @param userId User ID
         * @return
         * @throws DAOException
         */
	public Integer insertTempSessDetUserOnly(String userId) throws DAOException;
	
	/**
	 * 
	 * Used for calls the insert query for temporary Session Details data inserting (Language of the User)
	 * CAlled before Procedure CAlling so that procedure can translated message to correct Language
	 * 
	 * @param language User Language
	 * @param runningDate User Running Date
	 * @return
	 * @throws DAOException
	 */
	public Integer insertTempSessionDetails(String language, Date runningDate) throws DAOException;
	/**
         * 
         * Used for calls the insert query for temporary Session Details data
         * inserting (language and running Date)
         * 
         * @param running Date User Running Date (Application Running Date if no
         *            User Specific Date assigned)
         * @param userId User being logged in.
         * @return
         * @throws DAOException
         */
	 public Integer insertTempSessionDetails(Date runningDate,String userId) throws DAOException;
	/**
         * 
         * Used for calls the insert query for temporary Session Details data
         * inserting (Language of the User and Running Date
         * 
         * @param language User Language
         * @param running Date User Running Date (Application Running Date if no
         *            User Specific Date assigned)
         * @param userId String User ID (User Id of the logged in application)
         * @return
         * @throws DAOException
         */
        public Integer insertTempSessionDetails(String language, Date runningDate, String userId) throws DAOException;
        /**
         * 
         * Used for calls the insert query for temporary Session Details data
         * inserting (Language of the User and Running Date User ID and Application NAme)
         * 
         * @param language User Language
         * @param running Date User Running Date (Application Running Date if no
         *            User Specific Date assigned)
         * @param userId String User ID (User Id of the logged in application)
         * @param appName String Application Name (Application Name that needed in the procedure to be read)
         * @return
         * @throws DAOException
         */
        public Integer insertTempSessionDetails(String language, Date runningDate, String userId, String appName) throws DAOException;
	
	/**
	 * Used for calls the insert query for temporary Session Details data
         * inserting (Language of the User and Running Date User ID and Application NAme)
         * 
         * @param language User Language
         * @param running Date User Running Date (Application Running Date if no
         *            User Specific Date assigned)
         * @param userId String User ID (User Id of the logged in application)
         * @param appName String Application Name (Application Name that needed in the procedure to be read)
	 * @param con Connection by which to execute the query use in reporting application specially for pre procedure execution
	 * 	Connection should be autocommit false to take effect.
	 * @return
	 * @throws DAOException
	 */
        public Integer insertTempSessionDetails(String language, Date runningDate, String userId, String appName, Connection con) throws DAOException;
	/**
	 * Applies selection of a DB record
	 * @param obj Type object is used to support all passed VOs
	 * @return	Primary key value in case of single PK
	 * @throws DAOException
	 */
	public Object selectByPK(Object obj) throws  DAOException;
	
        /**
         * 
         * Used for committing holder transactional SQL Session 
         * 
         * @throws DAOException
         */
        public void commitTransaction() throws DAOException;
        
        /**
         * 
         * Used for rollBack all applied Queries up to the point SQL Session 
         * 
         * @throws DAOException
         */
        public void rollBackTransaction() throws DAOException;
        
        /**
         * Used to initialize or extends the transaction timeout with the number of seconds passed in parameters
         * if the timeout is set on the method (method name ends with TimeOut) then it will extend it to x seconds
         * if the method doesn't end s with TimeOut, a timeout will be initialized when calling the below method
         * 2 timeout exceptions can be thrown when the timeout is reached : 
         * 1- java.sql.SQLTimeoutException : if the timeout is reached inside the statement
         * 2- org.springframework.transaction.TransactionTimedOutException : if the timeout is reached before starting the statement  
         * @param seconds
         */
        public void extendTransactionTimeout(int seconds);
        /**
         * method to trace SQL session in case of providing Connection of execution
         * 
         * @param parameter BaseObject that contain trace details, traceUserId,
         *            traceProgRef, traceAppName should be filled
         * @param queryIdentifier identifier for the Query that to be executed
         * @param con Connection of Execution
         * @throws DAOException
         */
        public void callSqlSessionTrace(BaseObject parameter, String queryIdentifier, Connection con) throws DAOException;

}
