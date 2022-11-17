package com.path.lib.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.path.struts2.lib.common.BaseObject;
import com.path.vo.common.audit.AuditRefCO;

public class BaseVO extends BaseObject {
	private BigDecimal baseCurrencyCode;
	Object auditObj;
	private AuditRefCO auditRefCO; // for audit reference setting to be common
	private transient Map<String, String> nullUpdMap; // map of Fields that need to set to null, needed for Date fields,
														// since String and bigdecimal can be managed by empty String
														// and emptyBigDecimal, But can be used for all types
	private String auditKey;// to compare audit lists base on key

	private int language_id;
	private int app;

	/**
	 * Method to add the null field to consider in VO MApper to test if field to set
	 * as null
	 * 
	 * @param fieldName String Field Name == Column Name
	 */
	public void addNullFldToMap(String fieldName) {
		if (nullUpdMap == null) {
			nullUpdMap = new HashMap<String, String>();
		}
		nullUpdMap.put(fieldName, null);
	}

	/**
	 * Method to remove the null field if added using addNullFldToMap
	 * 
	 * @param fieldName String Field Name == Column Name
	 */
	public void removeNullFldFromMap(String fieldName) {
		if (nullUpdMap != null) {
			nullUpdMap.remove(fieldName);
		}
	}

	/**
	 * Method to check the null field if added to the nullUpd Map
	 * 
	 * @param fieldName String Field Name == Column Name
	 */
	public boolean checkNullFldInMap(String fieldName) {
		if (nullUpdMap != null && fieldName != null && nullUpdMap.containsKey(fieldName)) {
			return true;
		}
		return false;
	}

	public Object getAuditObj() {
		return auditObj;
	}

	public void setAuditObj(Object auditObj) {
		this.auditObj = auditObj;
	}

	public BigDecimal getBaseCurrencyCode() {
		return baseCurrencyCode;
	}

	public void setBaseCurrencyCode(BigDecimal baseCurrencyCode) {
		this.baseCurrencyCode = baseCurrencyCode;
	}

	public AuditRefCO getAuditRefCO() {
		return auditRefCO;
	}

	public void setAuditRefCO(AuditRefCO auditRefCO) {
		this.auditRefCO = auditRefCO;
	}

	public String getAuditKey() {
		return auditKey;
	}

	public void setAuditKey(String auditKey) {
		this.auditKey = auditKey;
	}

	public int getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(int language_id) {
		this.language_id = language_id;
	}

	public int getApp() {
		return app;
	}

	public void setApp(int app) {
		this.app = app;
	}

}
