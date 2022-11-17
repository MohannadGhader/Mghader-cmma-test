package com.path.struts2.lib.common;

import java.io.Serializable;

/**
 * CO used for getting new db connection and executing queries on this new
 * connection
 * 
 */
public class ConnectionCO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dbName; // data base name
	private String dbUserName; // db user name
	private String dbPassword; // db user password
	private String dbServerName; // db server name
	private String dbServerPort; // db server port
	private String dbJNDI; // datasource JNDI
	private String dbURL; // connection URL
	private String dbDriverType;// sybase/oracle
	private String charset; // character set

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUserName() {
		return dbUserName;
	}

	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getDbServerName() {
		return dbServerName;
	}

	public void setDbServerName(String dbServerName) {
		this.dbServerName = dbServerName;
	}

	public String getDbServerPort() {
		return dbServerPort;
	}

	public void setDbServerPort(String dbServerPort) {
		this.dbServerPort = dbServerPort;
	}

	public String getDbJNDI() {
		return dbJNDI;
	}

	public void setDbJNDI(String dbJNDI) {
		this.dbJNDI = dbJNDI;
	}

	public String getDbURL() {
		return dbURL;
	}

	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}

	public String getDbDriverType() {
		return dbDriverType;
	}

	public void setDbDriverType(String dbDriverType) {
		this.dbDriverType = dbDriverType;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

}
