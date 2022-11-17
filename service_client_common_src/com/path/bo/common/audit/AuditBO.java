package com.path.bo.common.audit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.path.dbmaps.vo.TRACK_CHANGES_DETAILSVO;
import com.path.lib.common.exception.BaseException;
import com.path.vo.common.audit.AuditRefCO;
//import com.path.vo.common.audit.AuditReportCO;
//import com.path.vo.common.audit.AuditReportSC;
//import com.path.vo.common.audit.TrackPropCO;
import com.path.vo.common.audit.TrackReportCO;

/**
 * 
 * Copyright 2012, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: raees / RabihElKhatib
 *
 *          AuditBO.java used to
 */
public interface AuditBO {
//	public ArrayList<AuditReportCO> returnAuditActionsList(AuditReportSC auditReportSC) throws BaseException;

//	public int returnAuditActionsCount(AuditReportSC auditReportSC) throws BaseException;

	public AuditRefCO conductorCallAudit(Object oldList, Object newList, AuditRefCO auditRefCO) throws BaseException;

	public void callAudit(Object oldList, Object newList, AuditRefCO auditRefCO) throws BaseException;

	public boolean checkAuditEnabled(AuditRefCO auditRefCO) throws BaseException;

	/**
	 * return OPT DETAILS
	 * 
	 * @param optVO
	 * @return
	 * @throws BaseException
	 */

	public String checkAuditKey(Object oldList, AuditRefCO auditRefCO) throws BaseException;

	public void insertAudit(AuditRefCO auditRefCO) throws BaseException;

	public boolean checkOPTAccess(AuditRefCO auditRefCO) throws BaseException;

	/**
	 * This method initiate tracking of the Approve/Reject process in order to store
	 * the amendments highlights.
	 * 
	 * @param oldCO
	 * @param newCO
	 * @param trackPropCO
	 * @throws BaseException
	 */
//	public void callTrackChanges(Object oldCO, Object newCO, TrackPropCO trackPropCO) throws BaseException;

	/**
	 * This method inserts tracking information master and details of the UPDATE
	 * after Approve/Reject process.
	 * 
	 * @param TrackPropCO
	 * @throws BaseException
	 */
//	public void insertTrackChanges(TrackPropCO trackPropCO) throws BaseException;

	/**
	 * This method return a hashMap of the changes highlights.
	 * 
	 * @param TRACK_CHANGES_DETAILSVO
	 * @throws BaseException
	 */
	public HashMap<String, HashMap<String, String>> returnChangesHighlights(TRACK_CHANGES_DETAILSVO trackDtlVO)
			throws BaseException;

	/**
	 * This method return an arrayList of tracked changes highlights.
	 * 
	 * @param AuditReportSC
	 * @return ArrayList<TrackReportCO>
	 * @throws BaseException
	 */
//	public ArrayList<TrackReportCO> returnChangesReport(AuditReportSC auditReportSC) throws BaseException;

	/**
	 * Return a copy of auditRefCO into TrackPropCO that contains the appName,
	 * progRef, operationType, keyRef, trxNbr
	 * 
	 * @param AuditRefCO
	 * @return copy of AuditRefCO as TrackPropCO
	 * @throws BaseException
	 * 
	 */
//	public TrackPropCO copyAuditProps(AuditRefCO auditRefCO) throws BaseException;

	/**
	 * This method prepares data to be inserted as audit details of each chosen
	 * field
	 * 
	 * @param auditReportSC
	 * @return
	 * @throws BaseException
	 */
//	public int insertAuditOnField(AuditReportSC auditReportSC) throws BaseException;

	/**
	 * 
	 * @param auditReportSC
	 * @return
	 * @throws BaseException
	 */
//	public List<SYS_PARAM_SCREEN_CIFSVO> returnScreenCIFsToAudit(AuditReportSC auditReportSC) throws BaseException;

	/**
	 * 
	 * @param auditCIFVOList
	 * @throws BaseException
	 */

	// TP 820336
	/**
	 * This method initialize auditCO and call insertAudit to insert audit field
	 * into DB
	 * 
	 * @param auditRefCO
	 * @param old_val    contains the old state of log level and null for clear
	 *                   cache
	 * @param new_val    contains new value of log level and null for clear cache
	 */
	public void specificCallAudit(AuditRefCO AuditRefCO, String old_val, String new_val) throws BaseException;

	/**
	 * 705733 Compare and insert audit records directly, simplified method for OMNI
	 * team
	 * 
	 * @param oldList
	 * @param newList
	 * @param auditRefCO
	 * @throws BaseException
	 */
	public void compareAndInsertAudit(Object oldList, Object newList, AuditRefCO auditRefCO) throws BaseException;

	/**
	 * 705733 copied from BaseAction to support OMNI Channel calls
	 * 
	 * @param auditKeyRef
	 * @param auditVO
	 * @param auditObject
	 * @param specRefCO
	 * @return
	 * @throws BaseException
	 */
	public AuditRefCO applyRetrieveAuditBO(String auditKeyRef, Object auditVO, Object auditObject, AuditRefCO specRefCO)
			throws BaseException;

	/**
	 * 705733 copied from BaseAction to support OMNI Channel calls
	 * 
	 * @param reportArgs
	 * @param opType
	 * @param menu
	 * @param auditAppName
	 * @param cifListToAudit
	 * @param specRefCO
	 * @throws BaseException
	 */
	public void applyReportAuditBO(Map reportArgs, String opType, String menu, String auditAppName,
			List<BigDecimal> cifListToAudit, AuditRefCO specRefCO) throws BaseException;

	/**
	 * This method initialize auditCO and call insertAudit to insert audit field
	 * into DB, similar to
	 * insertAuditOnFieldCIFs(ArrayList<S_AUDIT_ACTION_DTL_CIFVO> auditCIFVOList)
	 * but with multiple property changes
	 * 
	 * @param auditRefCO
	 * @param auditActionDtlList
	 * @throws BaseException
	 */
}
