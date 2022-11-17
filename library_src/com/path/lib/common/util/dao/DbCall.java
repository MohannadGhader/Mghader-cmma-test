package com.path.lib.common.util.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.path.lib.log.Log;

/**
 * 
 * Copyright 2012, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: deniskhaddad
 * 
 *          DbCall.java used for querying Statements and PreparedStatements
 */
public class DbCall {
	private final static Object toSynchronize = new Object();

	/**
	 * Free all resources that where used within the DbCall.
	 */
	public void freeResources() {
		Log.getInstance().entering(null);
		try {

			if (!theRs.isEmpty()) {
				Log.getInstance().trace("Closing " + theRs.size() + " instances of ResultSets");
				Iterator it = theRs.iterator();
				while (it.hasNext()) {
					ResultSet thisRs = (ResultSet) it.next();
					thisRs.close();
				}
				theRs.clear();
			}

			if (stmnt != null) {
				stmnt.close();
				stmnt = null;
			}

			if (theStmts != null && !theStmts.isEmpty()) {
				Iterator it = theStmts.iterator();
				while (it.hasNext()) {
					Statement thisStmt = (Statement) it.next();
					thisStmt.close();
				}
				theStmts.clear();
			}

			if (prepstmts != null) {
				Iterator it = prepstmts.iterator();
				while (it.hasNext()) {
					PreparedStatement thisPstmt = (PreparedStatement) it.next();
					thisPstmt.close();
				}
				prepstmts.clear();
			}

			if (callstmt != null) {
				callstmt.close();
				callstmt = null;
			}

			if (cnn != null) {
				cnn.close();
				cnn = null;
			}

			Log.getInstance().trace("DbCall: Freed Resources for " + dataSrcName);
		} catch (Exception ex) {
			Log.getInstance().error(ex, "Error in freeing resources");
		}
		Log.getInstance().exiting(null);
	}

	/**
	 * Returns the connection associated with this DbCall
	 * 
	 * @return Connection
	 * @throws java.lang.Exception
	 */
	public Connection getConnection() throws Exception {
		if (cnn == null) {
			cnn = dataSrc.getConnection();
		}

		return cnn;
	}

	/**
	 * dafault Constructor
	 */
	public DbCall() {
		try {
			context = new InitialContext();
		} catch (Exception e) {
			Log.getInstance().error(e, "Problem initializing DbCall default Constructor ");
		}
	}

	/**
	 * @param name the JNDI name of the DataSource to be connected to
	 * @return the DataSource instance
	 * @throws Exception
	 */
	public DataSource getDataSource(String name) throws Exception {
		Log.getInstance().debug("DbCall ==> In getDataSource(" + name + ") method.");

		if (dataSrcs.get(name) == null) {
			Log.getInstance().debug("DbCall ==> Creating a new datasource with name: " + name);
			Object objref = context.lookup(name);
			// DataSource ds = (DataSource) PortableRemoteObject.narrow(objref,
			// DataSource.class);
			DataSource ds = (DataSource) objref; // JDK 9+ fix
			dataSrcs.put(name, ds);
			return ds;
		} else {
			Log.getInstance().debug("DbCall ==> Getting the datasource with name: " + name + " from the cache");
			return (DataSource) dataSrcs.get(name);
		}
	}

	/**
	 * Constructor of the class that takes the data source jndi name
	 * 
	 * @param dsName the JNDI of reference name of the DataSource to be used
	 */
	public DbCall(String dsName) {
		try {
			this.dataSrcName = dsName;
			context = new InitialContext();
			dataSrc = getDataSource(dsName);
		} catch (Exception e) {
			Log.getInstance().error(e, "error caught in initializing DbCall class with datasource Name: " + dsName);
		}
	}

	/**
	 * Returns a Statement from the connection associated with the DbCall
	 * 
	 * @throws Exception
	 * @return Statement
	 */
	public Statement getStmt() throws Exception {
		if (cnn == null) {
			cnn = this.getConnection();
		}
		stmnt = cnn.createStatement();
		return stmnt;
	}

	/**
	 * Returns a CallableStatement from the connection associated with the DbCall
	 * 
	 * @param call String
	 * @throws Exception
	 * @return CallableStatement
	 */
	public CallableStatement getCallableStmt(String call) throws Exception {
		if (cnn == null) {
			cnn = this.getConnection();
		}
		callstmt = cnn.prepareCall(call);

		return callstmt;
	}

	/**
	 * Prepares a Statement using the String pQuery that is passed
	 * 
	 * @param pQuery String to be used in the preparattion of the statement
	 * @return the PreparedStatement
	 * @throws Exception
	 */
	public PreparedStatement getPreparedStmt(String pQuery) throws Exception {
		if (cnn == null) {
			cnn = this.getConnection();
		}

		if (prepstmts == null) {
			prepstmts = Collections.synchronizedList(new ArrayList());
		}
		Log.getInstance().debug("Prepared Statement Query:\n" + pQuery);
		prepstmts.add(cnn.prepareStatement(pQuery));
		return (PreparedStatement) prepstmts.get(prepstmts.size() - 1);
	}

	public Statement getNewStmt() throws Exception {
		Log.getInstance().info("DbCall ==> getting getNewStmt stmt");
		if (theStmts == null) {
			theStmts = Collections.synchronizedList(new ArrayList());
		}
		if (cnn == null) {
			cnn = getConnection();
			theStmts.add(cnn.createStatement());
		} else {
			theStmts.add(cnn.createStatement());
		}

		return (Statement) theStmts.get(theStmts.size() - 1);
	}

	/**
	 * Executes the PreparedStatement that was prepared by getPreparedStmt method
	 * 
	 * @return the resulting ResultSet
	 * @throws Exception
	 */
	public ResultSet executeQuery() throws Exception {
		if (prepstmts == null || prepstmts.isEmpty()) {
			Log.getInstance().error("DbCall: cannot execute an empty query!! need to set the prepared statement");
			return null;
		}

		Log.getInstance().info("DbCall: Executed PreparedStatement .. :)");
		try {
			// use last created prepared statement and execute
			theRs.add(((PreparedStatement) prepstmts.get(prepstmts.size() - 1)).executeQuery());
			return (ResultSet) theRs.get(theRs.size() - 1);
		} catch (Exception e) {
			Log.getInstance().error("[DbCall]: Exception caught while calling method [executeQuery()]");
			return null;
		}
	}

	/**
	 * Executes a query with another Statement. This method is used to keep
	 * different Result sets opened
	 * 
	 * @param query query to be executed
	 * @return the resulting ResultSet
	 * @throws Exception
	 */
	private List theStmts;

	public ResultSet executeRecursiveQuery(String query) throws Exception {
		Log.getInstance().info("[DbCall] in method executeRecursiveQuery");
		// creating a new statement and placing it in the ArrayList
		if (theStmts == null) {
			theStmts = Collections.synchronizedList(new ArrayList());
		}
		if (cnn == null) {
			cnn = getConnection();
			theStmts.add(cnn.createStatement());
		} else {
			theStmts.add(cnn.createStatement());
		}
		try {
			theRs.add(((Statement) theStmts.get(theStmts.size() - 1)).executeQuery(query));
			Log.getInstance().info("[DbCall] executedRecursiveQuery: " + query);
			return (ResultSet) theRs.get(theRs.size() - 1);
		} catch (SQLException ex) {
			Log.getInstance().error(ex,
					"[DbCall]: Exception caught while calling method [executeRecursiveQuery(" + query + "]");
			throw ex;
		}
	}

	/**
	 * Same as executeRecursiveQuery(String) but returns a specified # of records.
	 * This method is used within the flipping fonctionality.
	 * 
	 * @param query query to be executed
	 * @return the resulting ResultSet
	 * @throws Exception
	 */

	public ResultSet executeRecursiveQuery(String query, int rowsLength) throws Exception {
		Log.getInstance().info("[DbCall] in method executeRecursiveQuery with max rows: " + rowsLength);
		if (theStmts == null) {
			theStmts = new ArrayList();
		}
		if (cnn == null) {
			cnn = getConnection();
			theStmts.add(cnn.createStatement());
		} else {
			theStmts.add(cnn.createStatement());
		}

		try {
			((Statement) theStmts.get(theStmts.size() - 1)).setMaxRows(rowsLength);
			theRs.add(((Statement) theStmts.get(theStmts.size() - 1)).executeQuery(query));
			Log.getInstance()
					.info("[DbCall] in method executedRecursiveQuery with max rows: " + rowsLength + " - " + query);
			return (ResultSet) theRs.get(theRs.size() - 1);
		} catch (SQLException ex) {
			Log.getInstance().error(ex,
					"[DbCall]: Exception caught while calling method [executeRecursiveQuery(" + query + "]");
			throw ex;
		}
	}

	public int executeUpdate() throws Exception {

		if (prepstmts == null || prepstmts.isEmpty()) {
			Log.getInstance().error("DbCall: cannot execute an empty update!! need to set the prepared statement");
			return 0;
		}
		try {
			int nb = 0;
			nb = ((PreparedStatement) prepstmts.get(prepstmts.size() - 1)).executeUpdate();
			Log.getInstance().info("Executed Update PreparedStatement .. :)");
			return nb;
		} catch (Exception e) {
			Log.getInstance().error("[DbCall]: Exception caught while calling method [executeUpdate()]");
			return 0;
		}
	}

	public int executeUpdate(String query) throws Exception {
		int nb = 0;

		if (stmnt == null) {
			stmnt = this.getStmt();

		}
		Log.getInstance().info("DbCall: Executing Update:" + query);

		try {
			nb = stmnt.executeUpdate(query);
			Log.getInstance().info("Update Executed Successfully .. :)");
			return nb;
		} catch (Exception e) {
			Log.getInstance().error(e, "Exception caught while calling method [executeUpdate(" + query + ")]");
			throw e;
		}
	}

	private final List theRs = Collections.synchronizedList(new ArrayList());

	/**
	 * Executes the query passed in the String argument
	 * 
	 * @param query String query
	 * @return the resulting ResultSet
	 * @throws Exception
	 */

	public ResultSet executeQuery(String query) throws Exception {
		if (stmnt == null) {
			stmnt = this.getStmt();
		} else {
			if (stmnt.getResultSet() == null) {
				stmnt.setMaxRows(0);
			} else {
				if (stmnt.getResultSet().isAfterLast()) {
					stmnt.setMaxRows(0);
				} else {
					return executeRecursiveQuery(query);
				}
			}
		}

		Log.getInstance().info("DbCall: Executing Query: " + query);

		try {
			theRs.add(stmnt.executeQuery(query));
			return (ResultSet) theRs.get(theRs.size() - 1);
		} catch (Exception ex) {
			Log.getInstance().error(ex,
					"[DbCall]: Exception caught while calling method [executeQuery(" + query + ")]");
			throw ex;
		}
	}

	/**
	 * Executes the query passed in the String argument
	 * 
	 * @param query      String query
	 * @param rowsLength Nb of records to be retrieved from the DB
	 * @return the resulting ResultSet
	 * @throws Exception
	 */
	public ResultSet executeQuery(String query, int rowsLength) throws Exception {

		if (stmnt == null) {
			stmnt = this.getStmt();
			stmnt.setMaxRows(rowsLength);
		} else {
			if (stmnt.getResultSet() == null) {
				stmnt.setMaxRows(rowsLength);
			} else {
				if (stmnt.getResultSet().isAfterLast()) {
					stmnt.setMaxRows(rowsLength);
				} else {
					return executeRecursiveQuery(query, rowsLength);
				}
			}
		}

		Log.getInstance().info("DbCall: Executing Query with max number of rows = " + rowsLength + ": " + query);

		try {
			theRs.add(stmnt.executeQuery(query));
			return (ResultSet) theRs.get(theRs.size() - 1);
		} catch (Exception ex) {
			Log.getInstance().error(ex, "[DbCall]: Exception caught while calling method [executeQuery(" + query + ", "
					+ rowsLength + ")]");
			throw ex;
		}
	}

	/**
	 * Executes the query passed in the String argument Transforms the resultset
	 * 
	 * @param query String query
	 * @return the resulting ResultSet
	 * @throws Exception
	 */
	public ArrayList executeQueryToArrayL(String query) throws Exception {
		Log.getInstance().info("DbCall: In executeQueryToArrayL method. Executing query: " + query);

		ArrayList arrList = new ArrayList();
		HashMap record;
		try {
			ResultSet rs = executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();

			while (rs.next()) {
				record = new HashMap();
				for (int i = 1; i <= colCount; i++) {
					record.put(rsmd.getColumnName(i).toLowerCase(), rs.getObject(i));
				}
				arrList.add(record);
			}
			Log.getInstance().info("DbCall: Query Executed Successfully .. :)");
			return arrList;
		} catch (Exception ex) {
			Log.getInstance().error(ex,
					"[DbCall]: Exception caught while calling method [executeQuery(" + query + ")]");
			return null;
		}
	}

	/**
	 * Returns a new sequence value form the given sequence name.
	 * 
	 * @param sequenceName String
	 * @return int
	 */
	public int getSequenceValue(String sequenceName) {
		try {
			int key = 0;

			String qry = "select " + sequenceName + ".nextval key from dual";

			Log.getInstance().debug("Going to get nextSeqValue for:\n" + qry);
			ResultSet rs = this.executeQuery(qry);
			if (rs.next()) {
				key = rs.getInt(1);
			}

			if (Log.getInstance().isLoggable(Log.INFO)) {
				Log.getInstance().info("Got nextSeqValue[" + sequenceName + "]=" + key);
			}
			return key;
		} catch (Exception ex) {
			Log.getInstance().error(ex,
					" Exception caught while calling method getSequenceValue(" + sequenceName + ")");
			return 0;
		}
	}

	/**
	 * Returns a new sequence value given the sequence name and the datasource id.
	 * 
	 * @param sequenceName String
	 * @param fromDataSrc  String
	 * @return Integer
	 */
	public static Integer getSeqNextVal(String sequenceName, String fromDataSrc) {
		synchronized (toSynchronize) {
			if (Log.getInstance().isLoggable(Log.METHOD)) {
				Log.getInstance().entering(new Object[] { sequenceName, fromDataSrc });
			}
			DbCall dbinstance = new DbCall(fromDataSrc);
			Integer rtVal = null;
			try {
				rtVal = Integer.valueOf(dbinstance.getSequenceValue(sequenceName));
			} catch (Exception ex) {
				Log.getInstance().error(ex,
						" Exception caught while calling method getSeqNextVal(" + sequenceName + ")");
			} finally {
				dbinstance.freeResources();
				Log.getInstance().exiting(rtVal);
			}
			return rtVal;
		}
	}

	private InitialContext context;
	private DataSource dataSrc;
	private Connection cnn;
	private Statement stmnt;
	private CallableStatement callstmt;
	private List prepstmts;
	private String dataSrcName;
	private static Map dataSrcs = Collections.synchronizedMap(new HashMap());

}
