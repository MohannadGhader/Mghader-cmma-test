package com.path.vo.common.audit;

import java.util.ArrayList;

//import com.path.dbmaps.vo.OC_S_AUDIT_ACTIONS_EXTENDEDVO;
//import com.path.dbmaps.vo.S_AUDIT_ACTIONSVO;
//import com.path.dbmaps.vo.S_AUDIT_ACTION_DTLVO;
import com.path.lib.vo.BaseVO;
/**
 * 
 * Copyright 2012, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: RabihElKhatib
 *
 * AuditCO.java used to store the AUDIT tables (header/details) VOs
 * AUDITVO is the header VO representing S_AUDIT_ACTIONS
 * AUDITDTLVO is the details VO representing S_AUDIT_ACTION_DTL
 */
public class AuditCO extends BaseVO
{

//    private S_AUDIT_ACTIONSVO auditVO; //header VO representing S_AUDIT_ACTIONS
//    private ArrayList<S_AUDIT_ACTION_DTLVO> auditDtlVO; //details VO representing S_AUDIT_ACTION_DTL
//    //705733
//    private OC_S_AUDIT_ACTIONS_EXTENDEDVO ocExtendedVO; //extended details of OMNI Channel
//
//    public AuditCO(S_AUDIT_ACTIONSVO auditVO, ArrayList<S_AUDIT_ACTION_DTLVO> auditDtlVO)
//    {
//	this.auditVO = auditVO;
//	this.auditDtlVO = auditDtlVO;
//
//    }
    // just empty Constructor to check if Bind Exception will not occur on Weblogic Deployment
    public AuditCO()
    {
	
    }
    

//    public S_AUDIT_ACTIONSVO getAuditVO()
//    {
//	return auditVO;
//    }
//
//    public void setAuditVO(S_AUDIT_ACTIONSVO auditVO)
//    {
//	this.auditVO = auditVO;
//    }
//
//    public ArrayList<S_AUDIT_ACTION_DTLVO> getAuditDtlVO()
//    {
//	return auditDtlVO;
//    }
//
//    public void setAuditDtlVO(ArrayList<S_AUDIT_ACTION_DTLVO> auditDtlVO)
//    {
//	this.auditDtlVO = auditDtlVO;
//    }
//    public OC_S_AUDIT_ACTIONS_EXTENDEDVO getOcExtendedVO()
//    {
//        return ocExtendedVO;
//    }
//    public void setOcExtendedVO(OC_S_AUDIT_ACTIONS_EXTENDEDVO ocExtendedVO)
//    {
//        this.ocExtendedVO = ocExtendedVO;
//    }

}
