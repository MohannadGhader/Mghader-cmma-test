package com.path.bo.common.audit;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 *
 * Copyright 2012, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 *
 * @author: RabihElKhatib
 *
 *          AuditConstant.java used to Store the constant values of Audit key
 *          (is_audit_key) by window (i.e keyRef, Operation type).
 */
public class AuditConstant
{
    /**
    *
    */
    public static final HashMap<String, String> keyRef = new HashMap<String, String>();

    static

    {

	keyRef.put("cifKey", "COMP_CODE:4,BRANCH_CODE:0,CIF_NO:8");
	keyRef.put("benefCoolingKey", "COMP_CODE:4,CODE:10");
	keyRef.put("cifCodeKey", "COMP_CODE:4,BRANCH_CODE:0,CIF_CODE:8");
	keyRef.put("fomFatcaDetailsKey", "COMP_CODE:4,CIF_NO:8,BRANCH_CODE:0");
	keyRef.put("amfKey", "COMP_CODE:4,BRANCH_CODE:4,CURRENCY_CODE:3,GL_CODE:6,CIF_SUB_NO:8,SL_NO:3");
	keyRef.put("linksKey", "COMP_CODE:4,BRANCH_CODE:0,LINK_CODE:4,LINK_SERIAL:8");
	keyRef.put("remrecKey", "COMP_CODE:4,BRANCH_CODE:4,TRS_NO:10");
	keyRef.put("memoDtlKey", "COMP_CODE:4,BRANCH_CODE:4,TRX_NO:10");
	keyRef.put("trxMgntKey", "COMP_CODE:4,BRANCH_CODE:4,TRS_TYPE:1,CB_IND:1,TRS_NO:12");
	keyRef.put("chqBkMgntKey", "COMP_CODE:4,BRANCH_CODE:4,CODE:10");
	keyRef.put("cardsMgntKey", "COMP_CODE:4,BRANCH_CODE:4,APPLICATION_ID:10");
	keyRef.put("transCashKey", "COMP_CODE:4,BRANCH_CODE:4,TRX_NO:10");
	keyRef.put("lostFoundKey", "COMP_CODE:4,BRANCH_CODE:4,TRX_NO:10");
	keyRef.put("safeBoxDefKey", "COMP_CODE:4,BRANCH_CODE:0,CODE:6");
	keyRef.put("incidentReportKey", "COMP_CODE:4,BRANCH_CODE:4,INCD_CODE:10");
	keyRef.put("dcheqsKey",
		"COMP_CODE:4,ACCOUNT_BR:4,ACCOUNT_CY:3,ACCOUNT_GL:6,ACCOUNT_CIF:8,ACCOUNT_SL:3,LINE_NBR:3");
	keyRef.put("passbooksStockDestroyKey", "COMP_CODE:4,BRANCH_CODE:4,BATCH_NO:12,LINE_NO:4");
	keyRef.put("passbooksStockRequestKey", "COMP_CODE:4,BRANCH_CODE:4,BATCH_NO:12,LINE_NO:4,REQUEST_TYPE:4");
	keyRef.put("SmsSubscriptionKey", "COMP_CODE:4,BRANCH_CODE:4,CODE:10,LINE_NO:4");
	keyRef.put("MerchantPosMgntkey", "COMP_CODE:4,BRANCH_CODE:4,CODE:6");

	keyRef.put("safeBoxMgmtKey", "COMP_CODE:4,BRANCH_CODE:4,TRX_NO:8");
	keyRef.put("safeBoxDefListKey", "COMP_CODE:0,BRANCH_CODE:0,TYPE:3");
	keyRef.put("certificateKey", "COMP_CODE:4,BRANCH:4,CERTIFICATE_CODE:10");
	keyRef.put("dynamicIntegrationKey", "COMP_CODE:4,TYPE_CODE:3,CODE:15");
	keyRef.put("ctsRequestKey", "COMP_CODE:4,BRANCH:4,REQ_NO:6");

	// Dynamic Template Convention
	keyRef.put("dynamicTemplateConvKey", "CONVENTION_NUMBER:4");

	// Dynamic Template
	keyRef.put("dynamicTemplateKey", "DYN_TEMP_ID:10,APP_NAME:4");

	/** Customer Segmentation **/
	keyRef.put("custSegmentationScreenKey", "COMP_CODE:4,SEGMENT_CODE:4");

	keyRef.put("custSegmentGroupingScreenKey", "COMP_CODE:4,SEGMENT_CODE:4");

	// Audit reporting application
	keyRef.put("templateKey", "COMP_CODE:4,BRANCH_CODE:0,CODE:4");
	keyRef.put("reportsKey", "COMP_CODE:0,BRANCH_CODE:0,REPORT_ID:10");
	keyRef.put("uploadDownloadKey", "COMP_CODE:0,BRANCH_CODE:0,REPORT_ID:10");
	keyRef.put("queriesKey", "COMP_CODE:0,BRANCH_CODE:0,QUERY_ID:10");
	keyRef.put("entitiesKey", "COMP_CODE:0,BRANCH_CODE:0,ENTITY_DB_NAME:50");
	keyRef.put("filterCriteriaKey", "COMP_CODE:4,BRANCH_CODE:0,CODE:4");
	keyRef.put("dynamicRepKey", "COMP_CODE:0,BRANCH_CODE:0,CODE:4");
	keyRef.put("foldersKey", "COMP_CODE:0,BRANCH_CODE:0,FOLDER_CODE:5");
	keyRef.put("snapshotsKey", "COMP_CODE:0,BRANCH_CODE:0,SNAPSHOT_ID:10");
	keyRef.put("schedEngKey",
		"COMP_CODE:0,BRANCH_CODE:0,REPORT_ID:16,SCHED_ID:12,START_DATE_STR:18,SCHEDULED_DATE_STR:18");
	keyRef.put("schedKey", "COMP_CODE:0,BRANCH_CODE:0,SCHED_ID:12");
	keyRef.put("colTemplateKey", "COMP_CODE:4,CODE:4,LINE_NBR:6");
	keyRef.put("hyperlinksKey", "COMP_CODE:0,BRANCH_CODE:0,REPORT_ID:10,FIELD_INDEX:20");
	keyRef.put("dbConnectionKey", "COMP_CODE:0,BRANCH_CODE:0,CONN_ID:8");
	keyRef.put("dbAppConnectionKey", "COMP_CODE:0,BRANCH_CODE:0,APP_NAME:4");
	keyRef.put("cbParamKey", "COMP_CODE:4,BRANCH_CODE:0,ENTITY_CODE:4,SUB_ENTITY_CODE:4,ENTITY_TYPE:2");
	keyRef.put("controlRecordKey", "COMP_CODE:4,BRANCH:0");
	keyRef.put("blackListKey", "COMP_CODE:4,BRANCH:0,CODE:8");
	keyRef.put("fixedGeneralKey", "COMP_CODE:4,BRANCH_CODE:4,CURRENCY_CODE:3,GL_CODE:6,CIF_SUB_NO:8,SL_NO:3");
	keyRef.put("passBookMgntKey", "COMP_CODE:4,BRANCH_CODE:4,CODE:10");
	keyRef.put("billRegistrationKey", "COMP_CODE:4,CIF_NO:8,REG_TYPE:1,LINE_NO:6");
	keyRef.put("templateHeaderKey", "COMP_CODE:0,BRANCH:0,CODE:4");
	keyRef.put("timeBucketsKey", "COMP_CODE:3,BRANCH_CODE:0,REP_REF:20,CURRENCY_CODE:3");
	keyRef.put("timeBucketsLineKey", "COMP_CODE:3,BRANCH_CODE:0,REP_REF:20,CURRENCY_CODE:3,LINE_NO:4");
	keyRef.put("mailServerConfig", "COMP_CODE:0,BRANCH_CODE:0,MAIL_SERVER_CODE:4");
	keyRef.put("userAccessKey", "COMP_CODE:0,BRANCH_CODE:0,USER_GRP_ID:8");
	keyRef.put("userAccessBranchKey", "COMP_CODE:0,BRANCH_CODE:0,USER_GRP_ID:8");
	keyRef.put("irpFileMainKey", "COMP_CODE:0,BRANCH_CODE:0,FILE_ID:10");

	// Audit TFA application
	keyRef.put("preSettlementScheduleKey", "COMP_CODE:4,BRANCH:4,TRX_TYPE:4,SCHED_NBR:12,SCHED_LINE_NO:6");
	keyRef.put("marginKey", "COMP_CODE:4,BRANCH:4,LC_TYPE:1,LC_YEAR:4,LC_NBR:10");
	keyRef.put("manualJVKey", "COMP_CODE:4,BRANCH_CODE:4,LINE_NO:6,OP_NO:9");
	keyRef.put("advicesKey", "COMP_CODE:4,BRANCH_CODE:4,SERIAL_NO:10");
	keyRef.put("domiciliationDocKey", "COMP_CODE:4,BRANCH_CODE:4,TRX_TYPE:4,TRX_NBR:12,DOC_SERIAL:6");
	keyRef.put("domiciliationKey", "COMP_CODE:4,BRANCH_CODE:4,REQUEST_NBR:10");
	keyRef.put("lcKey", "COMP_CODE:4,BRANCH:4,LC_TYPE:1,LC_YEAR:4,LC_NBR:10");
	keyRef.put("lcReqKey", "COMP_CODE:4,BRANCH:4,LC_TYPE:1,LC_YEAR:4,LC_NBR:10");
	keyRef.put("domiciliationStsKey", "COMP_CODE:4,BRANCH_CODE:4,TRX_TYPE:4,TRX_NBR:12,DOC_SERIAL:6");
	keyRef.put("lgKey", "COMP_CODE:4,BRANCH:4,REQUISITION_NBR:10");
	keyRef.put("lgTfsTrxKey", "COMP_CODE:4,BRANCH:4,TRX_TYPE:4,TRX_NBR:12,LC_TYPE:1");
	keyRef.put("billKey", "COMP_CODE:4,BRANCH:4,BILL_TYPE:1,BILL_NBR:10");
	keyRef.put("billTrxKey", "COMP_CODE:4,BRANCH:4,TRX_TYPE:4,TRX_NBR:12,LC_TYPE:1");
	keyRef.put("lcTrxKey", "COMP_CODE:4,BRANCH:4,TRX_TYPE:4,TRX_NBR:12,LC_TYPE:1");

	keyRef.put("otherCommissionsKey", "COMP_CODE:4,BRANCH:4,LC_TYPE:1,LC_YEAR:4,LC_NBR:10");
	keyRef.put("outsideChargesKey", "COMP_CODE:4,BRANCH:4,LC_TYPE:1,LC_YEAR:4,LC_NBR:10");
	keyRef.put("suspenseSettlementKey", "COMP_CODE:4,BRANCH:4,LC_TYPE:1,LC_YEAR:4,LC_NBR:10");
	keyRef.put("preStlmtSchedKey", "COMP_CODE:4,BRANCH:4,LC_TYPE:1,LC_YEAR:4,LC_NBR:10");
	keyRef.put("lgTextTypeKey", "COMP_CODE:4,CODE:9");
	keyRef.put("chargesAmendKey", "COMP_CODE:4,BRANCH:4,LC_TYPE:1,LC_YEAR:4,LC_NBR:10");

	keyRef.put("eformIssuanceExporterKey", "COMP_CODE:4,BRANCH_CODE:4,CIF_NO:8,CODE_NO:4");
	keyRef.put("eformCertificationKey", "COMP_CODE:4,BRANCH_CODE:4,CODE_NO:6");
	keyRef.put("advancePaymentKey", "COMP_CODE:4,BRANCH_CODE:4,DOC_TYPE:1,TRX_NBR:10");
	keyRef.put("advancePaymentTransactionsKey", "COMP_CODE:4,BRANCH:4,TRX_TYPE:4,TRX_NBR:12,LC_TYPE:1");

	// audit tfa param
	keyRef.put("TfaControlRecordKey", "COMP_CODE:4,BRANCH:4");
	keyRef.put("TfaServiceChargeKey", "COMP_CODE:4,TYPE:1");
	keyRef.put("TfaSwiftTxtKey", "COMP_CODE:4");
	keyRef.put("TfaLinkSchemaKey", "COMP_CODE:4");
	keyRef.put("TfaDefineChargesSpecial", "COMP_CODE:4,CODE:4");
	keyRef.put("tfsDoctypeKey", "COMP_CODE:4,CODE:4,DOC_TYPE:1");

	// Audit Fatca application
	keyRef.put("fatcaControlRecordKey", "COMP_CODE:4");
	keyRef.put("fatcaFormsKey", "COMP_CODE:4,FORM_CODE:4,TAX_REG_ID:10");
	keyRef.put("fatcaFinancialInstStsKey", "COMP_CODE:4,FINANCIAL_INS_CODE:4,TAX_REG_ID:10");
	keyRef.put("fatcaSubFinancialInstStsKey", "COMP_CODE:4,CODE:4,FINANCIAL_INS_CODE:4,TAX_REG_ID:10");
	keyRef.put("reasonsKey", "COMP_CODE:4,REASON_CODE:4,TAX_REG_ID:10");
	keyRef.put("cifTaxInfoKey", "COMP_CODE:4,CIF_NO:8,TAX_REG_ID:10,RECORD_STATUS:1");
	keyRef.put("chaptersKey", "COMP_CODE:4,CHAPTER_NO:3,TAX_REG_ID:10");
	keyRef.put("exemptionKey", "COMP_CODE:4,EXEMPTION_CODE:3,TAX_REG_ID:10");
	keyRef.put("incomeKey", "COMP_CODE:4,INCOME_CODE:3,TAX_REG_ID:10");

	// Audit FAS application
	keyRef.put("fasControlKey", "COMP_CODE:4");
	keyRef.put("fasLocationKey", "COMP_CODE:4,CODE:8");
	keyRef.put("fasPropertyKey", "COMP_CODE:4,PROPERTY_CODE:10");
	keyRef.put("fasCategoryKey", "COMP_CODE:4,CATEGORY_CODE:4");
	keyRef.put("fasClassKey", "COMP_CODE:4,CATEGORY_CODE:4,CODE:4");
	keyRef.put("fasMaintenanceKey", "COMP_CODE:4,CODE:4");
	keyRef.put("fasRetirementReasonsKey", "COMP_CODE:4,CODE:4");
	keyRef.put("fasCapitalExpenditureCategoryKey", "COMP_CODE:4,CODE:4");
	keyRef.put("fasSupplierKey", "COMP_CODE:4,SUPP_CODE:4");
	keyRef.put("fasVatRetentionKey", "COMP_CODE:4,VAT_RET_CODE:6,VAT_RET_TYPE:1");
	keyRef.put("fasAssetKey", "COMP_CODE:4,CATEGORY_CODE:4,CLASS_CODE:4,CODE:6");
	keyRef.put("fasProjectKey", "COMP_CODE:4,PROJECT_NO:12");
	keyRef.put("fasProjectTrxKey", "COMP_CODE:4,TRX_NBR:12");
	keyRef.put("fasStockCountKey", "COMP_CODE:4,TRX_TYPE:4,TRX_NBR:12,TRX_LINE_NBR:6");
	keyRef.put("fasTrxKey", "COMP_CODE:4,TRX_TYPE:4,DIV_CODE:4,DEPT_CODE:4,TRX_NBR:12");
	keyRef.put("fasAssetMulLocationKey", "COMP_CODE:4,TRANSACTION_NO:12");
	keyRef.put("fasLocationTypeKey", "COMP_CODE:4,CODE:8");
	keyRef.put("fasCyRateToleranceLevelKey", "COMP_CODE:4");
	keyRef.put("fasTrxAstKey", "COMP_CODE:4,TRX_NBR:12");
	keyRef.put("fasTrxAstSearchKey", "COMP_CODE:4,TRX_NBR:12");
	keyRef.put("fasTrxAstRequestKey", "COMP_CODE:4,TRX_NBR:12");
	keyRef.put("fasRequestStatusKey", "COMP_CODE:4,REQUEST_STATUS_CODE:4");
	keyRef.put("fasRequestTypeKey", "COMP_CODE:4,REQUEST_TYPE_CODE:4");

	// Audit STF application
	keyRef.put("stfSettlementKey", "COMP_CODE:4,BRANCH_CODE:4,SETTLEMENT_NO:10");
	keyRef.put("stfStockReleaseKey", "COMP_CODE:4,BRANCH_CODE:4,STOCK_RELEASE_TRX_NO:10");
	keyRef.put("stfStockKey", "COMP_CODE:4,BRANCH_CODE:4,STOCK_NO:10");
	keyRef.put("stfContributorSwitchingKey", "COMP_CODE:4,BRANCH_CODE:4,PARTICIPANT_SWITCH_NO:10");
	keyRef.put("stfSecurityAllocationKey", "COMP_CODE:4,BRANCH_CODE:4,ALLOCATION_NO:10");
	keyRef.put("stfShipmentRequestKey", "COMP_CODE:4,BRANCH_CODE:4,SHIPMENT_REQUEST_NO:10");
	keyRef.put("stfShipmentRequestAmndmntKey", "COMP_CODE:4,BRANCH_CODE:4,AMENDMENT_NO:10");
	keyRef.put("stfShipmentKey", "COMP_CODE:4,BRANCH_CODE:4,SHIPMENT_NO:10");
	keyRef.put("stfTypeParamKey", "COMP_CODE:4,BRANCH_CODE:0,TYPE_CODE:4");

	keyRef.put("stfPurchaseKey", "COMP_CODE:4,BRANCH_CODE:4,PURCHASE_NO:10");
	keyRef.put("stfSaleKey", "COMP_CODE:4,BRANCH_CODE:4,SALE_NO:10");
	keyRef.put("stfCommodityGradeKey", "COMP_CODE:4,BRANCH_CODE:0,COMMODITY_GRADE_CODE:3");
	keyRef.put("stfCommodityMarketPriceKey", "COMP_CODE:4,MARKET_INDEX_DATE:10:D");
	keyRef.put("stfWarehouse", "COMP_CODE:4,BRANCH_CODE:4,WAREHOUSE_NO:4");
	keyRef.put("stfOffTakersKey", "COMP_CODE:4,BRANCH_CODE:4,OFF_TAKERS_NO:10");
	keyRef.put("stfOffTakersDetailKey", "COMP_CODE:4,BRANCH_CODE:4,OFF_TAKERS_NO:10,LINE_NO:3");
	keyRef.put("stfTopupDepositKey", "COMP_CODE:4,BRANCH_CODE:4,TOPUP_DEPOSIT_NO:10");
	keyRef.put("stfWaiveWriteOffKey", "COMP_CODE:4,BRANCH_CODE:4,WAIVE_WRITEOFF_NO:10");
	keyRef.put("stfCategoryKey", "COMP_CODE:4,BRANCH_CODE:0,CODE:4,SUB_CATEGORY_YN:1,PARENT_CATEGORY_CODE:4");
	keyRef.put("stfCommodityKey", "COMP_CODE:4,BRANCH_CODE:0,CODE:4");
	keyRef.put("stfChargeKey", "COMP_CODE:4,BRANCH_CODE:0,CHARGE_CODE:6");

	// Audit IIS application

	keyRef.put("repaymentPlanKey", "COMP_CODE:4,BRANCH:4,PLAN_NBR:12,PLAN_SEQ:3");
	keyRef.put("iistradeDealKey", "COMP_CODE:4,BRANCH_CODE:4,REF_NO:12");
	keyRef.put("iisInvestmentDeal", "COMP_CODE:4,BRANCH_CODE:4,SERIAL_NO:12");
	keyRef.put("iisPromissoryFxDeal", "COMP_CODE:4,BRANCH_CODE:4,SERIAL_NO:12,PROMISSORY_FX_TYPE:1");
	keyRef.put("iisLimitManagement", "COMP_CODE:4,BRANCH_CODE:4,USER_ID:8");
	keyRef.put("iisCounterPartyLimit", "COMP_CODE:4,BRANCH_CODE:4,CIF:8");
	keyRef.put("iisInternalDealKey", "COMP_CODE:4,BRANCH_CODE:4,SERIAL_NO:12,FOREX_CREATION_TYPE:2");
	keyRef.put("iisSettlementKey", "COMP_CODE:4,BRANCH:4,SETTLEMENT_NBR:12");
	keyRef.put("iisVendorPaymentKey", "COMP_CODE:4,BRANCH_CODE:4,TRS_TYPE:1,CB_IND:1,TRS_NO:12");
	keyRef.put("iisTrsdealAdvanceInsuranceKey", "COMP_CODE:4,BRANCH_CODE:4,SERIAL_NO:12");
	keyRef.put("iisTrsInsuranceDealKey", "COMP_CODE:4,BRANCH_CODE:4,TRANSACTION_NO:12");
	keyRef.put("iisTrsTrackKey", "COMP_CODE:4,BRANCH:4,TRACK_NBR:12");
	keyRef.put("iisTrsProfitKey", "COMP_CODE:4,BRANCH:4,PROFIT_NBR:12");
	keyRef.put("iisTrsChargesReversalKey", "COMP_CODE:4,BRANCH_CODE:4,TRX_NBR:12");
	keyRef.put("iisTrsDealStructureKey", "COMP_CODE:4,BRANCH_CODE:4,RESTRUCTURE_NO:12");
	keyRef.put("iisTrsCertificateMgtKey", "COMP_CODE:4,BRANCH_CODE:4,TRS_NO:8");
	keyRef.put("iisTrsSetLmtKey", "COMP_CODE:4,BRANCH:4,SETTLEMENT_NBR:12");
	keyRef.put("iisTrsContribSwitchKey", "COMP_CODE:4,BRANCH_CODE:4,CODE:12,SERIAL_NO:12");
	keyRef.put("iisTrsProvisionCifTrx", "COMP_CODE:4,CIF_NO:8");
	keyRef.put("iisContractMgmtKey", "BRANCH_CODE:4,COMP_CODE:4,DOCUMENT_CODE:12");

	keyRef.put("iisAdvancePaymentKey", "COMP_CODE:4,BRANCH_CODE:4,PAYMENT_NO:12");
	keyRef.put("trsProductTypeKey", "COMP_CODE:4,TYPE_CODE:4");
	keyRef.put("forexBaseCyKey", "COMP_CODE:4,SOLD_CY:3,BOUGHT_CY:3,BASE_CY:3");
	keyRef.put("iborHdrKey", "COMP_CODE:4,CURRENCY_CODE:3,IBOR_CODE:2,LINE_NUMBER:3");
	keyRef.put("trsItemUnitCodeKey", "COMP_CODE:4,CODE:6");
	keyRef.put("trsLibraryKey", "COMP_CODE:4");
	keyRef.put("yieldRosterKey", "COMP_CODE:4,BRANCH_CODE:4,CODE:4");
	keyRef.put("trsPenaltyKey", "COMP_CODE:4,PENALTY_CODE:4");
	keyRef.put("yieldRegisterKey", "COMP_CODE:4,BRANCH_CODE:4,CODE:10");
	keyRef.put("trsTargetMaintenanceKey", "COMP_CODE:4,CLASS:4,COUNTRY:3,YEAR:4,MONTH:2");

	keyRef.put("trsCertificateTypesKey", "COMP_CODE:4,CERTIFICATE_CODE:4");
	keyRef.put("trsItemUnitPriceKey", "COMP_CODE:4,GOLD_TYPE_CODE:4,PRICE_DATE:10:D");
	keyRef.put("trsProvProductsKey", "COMP_CODE:4,PRODUCT_TYPE_CODE:4");
	keyRef.put("cyRateToleranceLevelKey", "COMP_CODE:4");
	keyRef.put("trsVenderIncentiveSchemeKey", "COMP_CODE:4,INCENTIVE_CODE:6");
	keyRef.put("trsReasonCodeKey", "COMP_CODE:4,REASON_CODE:4");
	keyRef.put("trsInsuranceTypesKey", "COMP_CODE:4,INSURANCE_TYPE:4");
	keyRef.put("trsInsuranceCompanieskey", "COMP_CODE:4,INSURANCE_CODE:12");
	keyRef.put("trsBoardMembersKey", "COMP_CODE:4,MEMBER_ID:8");
	keyRef.put("trsProductClassPortfolioAccountsKey",
		"COMP_CODE:4,CODE:4,PORTFOLIO_CIF:8,PORTFOLIO_SEQ:3,LINE_NO:3");

	keyRef.put("trsProvisionCategoryKey", "CODE:4");
	keyRef.put("trsProvisionGroupKey", "COMP_CODE:4,CODE:6");
	keyRef.put("trsItemCategoryKey", "COMP_CODE:4,CODE:4");
	keyRef.put("trsClassFundAccKey", "COMP_CODE:4,CODE:4,FUND_CODE:4,LINE_NO:3");
	keyRef.put("trsVendorKey", "COMP_CODE:4,BRANCH_CODE:4");
	keyRef.put("iisCalculator", "BRANCH:4,COMP_CODE:4,REF_NBR:12");

	// TP#192774; Ramesh; 10/07/2014 For Audit Functionality
	keyRef.put("iisCertificatesManagementKey", "COMP_CODE:4,BRANCH_CODE:4,TRS_NO:12");
	keyRef.put("iisFinanceAssetWriteOff", "COMP_CODE:4,BRANCH_CODE:4,TRS_NO:12");
	keyRef.put("iisProvisionAmendmentsKey", "COMP_CODE:4,BRANCH_CODE:4,TRX_DATE:10:D,TRX_NO:12");
	keyRef.put("iisProvisionAdjustmentKey", "COMP_CODE:4,BRANCH_CODE:4,TRX_NO:12");
	keyRef.put("iisDealClassifAmendmentKey", "BRANCH_CODE:4,COMP_CODE:4,TRX_NO:12");
	keyRef.put("iisProfitDeclarationKey", "BRANCH:4,COMP_CODE:4,PROFIT_NBR:12");
	keyRef.put("iisChargeReversalKey", "BRANCH_CODE:4,COMP_CODE:4,TRX_NBR:12");
	keyRef.put("iisIncidentalChargesKey", "BRANCH:4,COMP_CODE:4,TRACK_NBR:12");
	keyRef.put("iisAssetMatrixDet", "COMP_CODE:4,CODE:6");

	// TP#192774; Ramesh; 10/07/2014 For Audit Functionality
	keyRef.put("iisContributorSwitching", "BRANCH_CODE:4,COMP_CODE:4,CODE:12,SERIAL_NO:12");
	keyRef.put("iisReleaseContribBlocking", "BRANCH_CODE:4,COMP_CODE:4,LINE_NO:12,TRX_NBR:12");
	keyRef.put("iisWaiving", "BRANCH:4,COMP_CODE:4,SETTLEMENT_NBR:12");
	keyRef.put("iisDisbursementCancellation", "BRANCH_CODE:4,COMP_CODE:4,SERIAL_NO:12");
	keyRef.put("iisContributorLimit", "BRANCH_CODE:4,COMP_CODE:4,CONTRIBUTOR_CIF:12");
	keyRef.put("iisObligorLimit", "BRANCH_CODE:4,COMP_CODE:4,CIF:12");
	keyRef.put("iisEscrowAccTemplate", "BRANCH_CODE:4,COMP_CODE:4,TRX_NO:12");

	keyRef.put("iisCountryLimit", "BRANCH_CODE:4,COMP_CODE:4,COUNTRY:3,CURRENCY_CODE:3");
	keyRef.put("trsRevenuesExpensesAccountsDetailsKey", "COMP_CODE:4,CODE:6");
	keyRef.put("trsRepaymentPlanTemplateKey", "COMP_CODE:4,BRANCH_CODE:4,TEMPLATE_CODE:4");
	keyRef.put("trsThirdPartyAddressKey", "COMP_CODE:4,BRANCH_CODE:4,THIRD_PARTY_ADDRESS_CODE:4");
	keyRef.put("unitCodeSetupKey", "COMP_CODE:4,BRANCH_CODE:4");
	keyRef.put("reportTemplateKey", "COMP_CODE:4,BRANCH_CODE:4,CODE:4");
	keyRef.put("trsVATKey", "COMP_CODE:4,VAT_CODE:6");
	keyRef.put("trsSubPurposeCodekey", "COMP_CODE:4,SUB_PURPOSE_CODE:4");
	keyRef.put("trsDeviationTypekey", "COMP_CODE:4,DEVIATION_CODE:4");

	keyRef.put("trsCifCreditRatingKey", "COMP_CODE:4,CREDIT_CODE:4");
	keyRef.put("trsHijriSetupKey", "COMP_CODE:3,HIJRI_YEAR:3");
	keyRef.put("trsEmailDetailsKey", "COMP_CODE:4,BRANCH_CODE:4");

	keyRef.put("iisctrlKey", "COMP_CODE:4,BRANCH_CODE:4");
	keyRef.put("trsCagamasMainKey", "COMP_CODE:4,BRANCH_CODE:4,SERIAL_NO:12");
	keyRef.put("trsCyForwardFxRateKey", "COMP_CODE:4,CURRENCY_CODE:3,DATE_RATE:10:D,SETTLEMENT_DATE:10:D");
	keyRef.put("trsCyForwardRateKey",
		"COMP_CODE:4,CURRENCY_CODE:3,BASE_CY_CODE:3,DATE_RATE:10:D,SETTLEMENT_DATE:10:D");
	keyRef.put("trsABIScriptsKey", "COMP_CODE:4,ABI_NUMBER:30");
	keyRef.put("trsCIFCBNoKey", "COMP_CODE:4,BRANCH_CODE:4,TRS_NO:12");
	keyRef.put("trsDeskMgmtKey", "COMP_CODE:4,BRANCH_CODE:4,CODE:12");
	keyRef.put("trsItemSwiftCodeKey", "COMP_CODE:4,CODE:6");
	keyRef.put("trsBrokerPaymentsKey", "COMP_CODE:4,BRANCH_CODE:4,TRS_NO:12,TRS_TYPE:1");
	keyRef.put("trsAssetKey", "COMP_CODE:4,BRANCH:4,CODE:6");

	keyRef.put("trsItemPropertyKey", "PROPERTY_CODE:4,COMP_CODE:4");
    keyRef.put("trsItemMaintenanceKey", "COMP_CODE:4,CODE:6");
    keyRef.put("trsChargesKey", "COMP_CODE:4,CODE:6");
    keyRef.put("MudharabahKey", "COMP_CODE:4,RATE_CODE:2,LINE_NUMBER:3,CURRENCY_CODE:3,CIF_NO:8");
    
    keyRef.put("produtsubcategory", "APP_NAME:4,CATEGORY_CODE:2,SUBCATEGORY_CODE:2");

	// Audit PROF application
	keyRef.put("profDriverKeyRef", "COMP_CODE:4:N,DRIVER_CODE:4:N");
	keyRef.put("profAssetLiabKeyRef", "COMP_CODE:4:N,GL_LINE_ID:10:N");
	keyRef.put("profWeightEntityKey", "COMP_CODE:4:N,COST_TYPE_CODE:5:N,ENTITY_CLIENT_FLAG:1:N");

	// Audit RCSA application
	keyRef.put("rcsaFoldMgmtKeyRef", "COMP_CODE:4:N,ID:8:N");
	keyRef.put("rcsaRatingCtrlKeyRef", "COMP_CODE:4:N,ID:8:N");
	keyRef.put("rcsaFinNonFinImpactKeyRef", "COMP_CODE:4:N,ID:8:N");
	keyRef.put("rcsaFinImpactKeyRef", "COMP_CODE:4:N,ID:8:N");
	keyRef.put("rcsaEventTypeCategKeyRef", "COMP_CODE:4:N,ID:8:N");
	keyRef.put("rcsaRiskIdentificationKeyRef", "COMP_CODE:4:N,ID:8:N");
	keyRef.put("rcsaCtrlIdentificationKeyRef", "COMP_CODE:4:N,ID:8:N");
	keyRef.put("rcsaRiskAssessmenTKeyRef", "COMP_CODE:4:N,ID:8:N");
	keyRef.put("rcsaCtrlPlanKeyRef", "COMP_CODE:4:N,ID:8:N");
	keyRef.put("rcsaActionIdentificationKeyRef", "COMP_CODE:4:N,ID:8:N");
	keyRef.put("rcsaAlertAssignmentKeyRef", "FOLDER_CATEG_MGMT_ID:8:N,COMP_CODE:4:N,ID:8:N");

	// Audit FMS application
	keyRef.put("fmsApplKey", "COMP_CODE:4,BRANCH:4,CODE:10");
	keyRef.put("fmsFacilityKey", "COMP_CODE:4,BRANCH:4,CODE:10");
	keyRef.put("fmsDrawdownKey", "COMP_CODE:4,BRANCH:4,CODE:10");
	keyRef.put("fmsClosureKey", "COMP_CODE:4,BRANCH:4,CODE:10");
	keyRef.put("fmsFacilityBlockAMTKey", "COMP_CODE:4,BRANCH_CODE:4,CODE:10");
	keyRef.put("fmsFinancePaymentKey", "COMP_CODE:4,BRANCH:4,CODE:10");
	keyRef.put("fmsCustgradeKey", "COMP_CODE:4,BRANCH:4,APP_NAME:4,CODE:10");
	keyRef.put("fmsCollateralKey", "COMP_CODE:4,BRANCH:4,CODE:10");
	keyRef.put("fmsColatTypeKey", "COMP_CODE:4,CODE:4");
	keyRef.put("fmsColatTypeAttributes", "COMP_CODE:4,PROPERTY_CODE:4");
	keyRef.put("fmsDrawdownTypeParamKey", "COMP_CODE:4,CODE:4");
	keyRef.put("fmsIndicatorKey", "COMP_CODE:4,CODE:4");
	keyRef.put("custGradeScoreKey", "COMP_CODE:4,CODE:4");
	keyRef.put("FMSControlRecordKey", "COMP_CODE:4,BRANCH_CODE:4");
	keyRef.put("fmsApprovalCommities", "COMP_CODE:4,CODE:4");
	keyRef.put("fmsFacilityType", "COMP_CODE:4,CODE:4");
	keyRef.put("fmsCountryLimit", "COMP_CODE:4,COUNTRY_CODE:3");
	keyRef.put("fmsEstimator", "COMP_CODE:4,CODE:4");
	keyRef.put("fmsSpecFund", "COMP_CODE:4,CODE:3");
	keyRef.put("paramFacilityDocumentsKey", "COMP_CODE:4,CODE:4");
	keyRef.put("fmsSubmissionKey", "COMP_CODE:4,CODE:6");
	keyRef.put("fmsFoldersKey", "FOLDER_CODE:4,APP_NAME:4");
	keyRef.put("fmsCategoryKey", "COMP_CODE:4,CODE:4");
	keyRef.put("fmsParamAuditorsKey", "COMP_CODE:4,BRANCH_CODE:4,CODE:6");
	keyRef.put("fmsDevLimits", "COMP_CODE:4,DEV_CIF:8,LINE_NBR:3");
	keyRef.put("fmsSupplierBuyerCriteryaKey", "COMP_CODE:4,CODE:4");
	keyRef.put("fmsParamCommentsKey", "COMP_CODE:4,CODE:4");
	keyRef.put("fmsParamSuspendReasonsKey", "COMP_CODE:4,CODE:4");
	keyRef.put("fmsParamSolicitorsKey", "COMP_CODE:4,CODE:4");
	keyRef.put("fmsParamGradeEvalFactorsKey", "COMP_CODE:4,APP_NAME:4,CODE:6");
	keyRef.put("fmsParamDocChecklist", "COMP_CODE:4,CODE:4");
	keyRef.put("fmsParamAuditCorp", "COMP_CODE:4,CODE:10");
	keyRef.put("fmsExposureLimitByNationality", "COMP_CODE:4,CODE:4,LINE_NBR:3");
	keyRef.put("purpFinancingKey", "COMP_CODE:4,CODE:4");
	keyRef.put("counterPartyBranchesKey", "COMP_CODE:4,LINE_NBR:3,CIF:8");
	keyRef.put("parentCounterPartyBranchesKey", "COMP_CODE:4,LINE_NBR:3,PARENT_CIF:8");
	keyRef.put("fmshamishaljiddiyakey", "COMP_CODE:4,BRANCH:4,TRS_NO:10");
	// Jishnu Pg ; 24/01/2020
	keyRef.put("coverageTypeKey", "COMP_CODE:4,CODE:4");
	// Jishnu Pg ; 27/01/2020
	keyRef.put("reasonKeyRef", "COMP_CODE:4,CODE:6");
	// added by azhar; #391995 - ITFC130024
	keyRef.put("fundLimitKey", "COMP_CODE:4,FUND_CODE:8");
	// TP#392048 - ITFCI140020 ; Jishnu Pg
	keyRef.put("facilityChargeCollectionKeyRef", "COMPANY_CODE:4,BRANCH_CODE:4,REVERSAL_YN:1,TRANSACTION_NO:10");
	keyRef.put("facilityProjectionKey", "COMP_CODE:4,BRANCH:4,PROJECTION_CODE:8");
	keyRef.put("facilityCancellationKey", "COMP_CODE:4,BRANCH:4,CODE:10");
	keyRef.put("transferEntitykey", "COMP_CODE:4,BRANCH_CODE:4,BATCH_NO:8");
	
	
	// Audit PCS application
	keyRef.put("ProfitGroupManagement", "COMP_CODE:4,PROFIT_GROUP:4");
	keyRef.put("accountsType", "COMP_CODE:4,TYPE_CODE:6");
	keyRef.put("PCSControlRecordKey", "COMP_CODE:4");
	keyRef.put("generalLedger", "COMP_CODE:4,GL_CODE:6");
	// Fixed Maturity Account Screen
	keyRef.put("fixedMaturityAccountkey",
		"COMP_CODE:4,BRANCH_CODE:4,CURRENCY_CODE:3,GL_CODE:6,CIF_SUB_NO:8,SL_NO:3");
	keyRef.put("pcsCifType", "COMP_CODE:4,GROUP_CODE:4");
	keyRef.put("PCSInternalAccountsKey", "REFERENCE_KEY:50");
	keyRef.put("PCSAccountClassKey", "CLASS_CODE:3,COMP_CODE:4");
	keyRef.put("ratesManagementTypeKey", "COMP_CODE:4,GROUP_CODE:4");
	keyRef.put("generalLedgerStandAlone", "REFERENCE_KEY:50");
	// PCS StandAlone Fixed Maturity Account Screen
	keyRef.put("pcsStandAlonefixedMaturityAccountkey", "REFERENCE_KEY:50");
	keyRef.put("accountsTypeWhenStandAloneKey", "REFERENCE_KEY:50");
	keyRef.put("PCSMasterEquityRecordKey", "COMP_CODE:4");
	keyRef.put("pftadvancetrxkey",
		"COMP_CODE:4,BRANCH_CODE:0,LOG_NUMBER:6,ACC_CLASS:3,GL_CODE:6,CIF_TYPE:4,CURRENCY_CODE:3,CIF_SUB_NO:8,SL_NO:3");
	keyRef.put("updateStatistics", "LINE_NO:3");

	// Audit ESS application
	keyRef.put("essEmpRequestKey", "COMP_CODE:4,BRANCH_CODE:4,REQUEST_CODE:10,EMPLOYEE_ID:8,REQUEST_TYPE:3");
	keyRef.put("essEmpTrainingRequestKey", "COMP_CODE:4,BRANCH_CODE:4,TRS_NO:6,EMPLOYEE_ID:8");
	keyRef.put("essEmpLoanRequestKey", "COMP_CODE:4,BRANCH_CODE:4,LOAN_NO:10,PMT_NO:4");
	keyRef.put("trainingTypesKey", "COMP_CODE:4,TRAINING_TYPE_CODE:4");
	keyRef.put("trainingBudgetKey", "COMP_CODE:4,BUDGET_YEAR:4");
	keyRef.put("employeeRoleKey", "ROLE_CODE:4");
	keyRef.put("essEmpLeavePlanKey", "COMP_CODE:4,BRANCH_CODE:4,EMPLOYEE_ID:8,PLAN_CODE:10,FISCAL_YEAR:4");
	keyRef.put("essTravelRequestPaymentsKey", "COMP_CODE:4,BRANCH_CODE:4,EMPLOYEE_ID:8,TRX_NO:10");
	keyRef.put("essEmpAppraisalSetUpKey", "SETTING_CODE:8,PERIOD_FROM:10:D,PERIOD_TO:10:D");
	keyRef.put("essConpenciesLibraryKey", "GROUP_CODE:4");
	keyRef.put("competencyRatingKey", "RATING_CODE:4");
	keyRef.put("miscellaneousRequestTypesKey", "COMP_CODE:4,CODE:4");
	keyRef.put("essEmpMyProfileKey", "COMP_CODE:4,EMPLOYEE_ID:8");
	keyRef.put("essLeaveRequestKey", "COMP_CODE:4,DIV_CODE:3,DEPT_CODE:3,LEAVE_NO:10,EMPLOYEE_ID:8");
	keyRef.put("essDocumentTypesKey", "DOC_CODE:4");
	keyRef.put("employeeKpiKey", "KPI_CODE:8,PERIOD_FROM:10,PERIOD_TO:10:D");
	keyRef.put("employeeKpiKeyInit", "COMP_CODE:4,EMPLOYEE_ID:8,KPI_CODE:8,PERIOD_FROM:10:D,PERIOD_TO:10:D");
	keyRef.put("essAppraisalFormKey",
		"COMP_CODE:4,EMPLOYEE_ID:8,APPRAISAL_TRX_NO:8,PERIOD_FROM:10:D,PERIOD_TO:10:D");
	keyRef.put("appraisalFormSetupKey", "FORM_CODE:8");
	keyRef.put("essDocumentTemplateKey", "COMP_CODE:4,BRANCH_CODE:4,TEMPLATE_CODE:4");
	keyRef.put("essDocumentGenerateKey", "DOCUMENT_CODE:8");
	keyRef.put("attendannceViolationScoreKey", "COMP_CODE:4,DEDUCTION_CODE:8");
	keyRef.put("educationalInfoKey", "COMP_CODE:4,EDUC_CODE:3");
	keyRef.put("ppsBonusCodeKeyRef", "COMP_CODE:4,BONUS_CODE:3");
	keyRef.put("ppsGradesCodeKeyRef", "COMP_CODE:4,GRADE_CODE:3");
	keyRef.put("essBonusPaymentsKey", "COMP_CODE:4,TRX_NO:8");
	keyRef.put("ppsJobTitleCodeKeyRef", "COMP_CODE:4,JOB_TITLE_CODE:3");
	keyRef.put("ppsJobKeyRef", "COMP_CODE:4,JOB_CODE:3,DIV_CODE:3,DEPT_CODE:3");
	keyRef.put("ppsControlRecordKeyRef", "COMP_CODE:4,BRANCH_CODE:4");
	keyRef.put("ppsPerdiemCodeKeyRef", "COMP_CODE:4,BRANCH_CODE:4,PERDIEM_CODE:4");
	keyRef.put("ppsEmployeeMasterFileKeyRef", "COMP_CODE:4,EMPLOYEE_ID:8");
	keyRef.put("essModifyCalendarKey", "COMP_CODE:4,EMPLOYEE_ID:8");
	keyRef.put("ppsLoanTransactionKeyRef", "COMP_CODE:4,BRANCH_CODE:4,LOAN_NO:10,PMT_NO:4");
	keyRef.put("ppsCalendarKeyRef", "COMP_CODE:4,DAY_CATEGORY:3,CALENDAR_CODE:3,YEAR:4,SERIAL_NO:3");
	keyRef.put("employeeKpiProbKey", "COMP_CODE:4,EMPLOYEE_ID:8");
	keyRef.put("employeeJobDescKey", "COMP_CODE:4,BRANCH_CODE:4,JOB_DESC_CODE:4");
	keyRef.put("ppsProbationEvaluationKeyRef", "COMP_CODE:4,EMPLOYEE_ID:8");
	keyRef.put("ppsQuestionBankKeyRef", "COMP_CODE:4,CODE:3");
	keyRef.put("ppsPsychometricUserScreenKeyRef", "COMP_CODE:4,EMPLOYEE_ID:8,TEST_TRX_NO:8");
	keyRef.put("employeeSuccessionKey", "KEY_EMP_ID:4,KEY_EMP_COMP_CODE:4,,TRX_NO:8");
	keyRef.put("essModifyInfoKey", "COMP_CODE:4,BRANCH_CODE:4,TRS_NO:10");
	keyRef.put("essMultEmpModInfoKey", "COMP_CODE:4,BRANCH_CODE:4,TRS_CODE:3,TRS_NO:10");
	keyRef.put("successionActionPlanKey", "TRX_NO:8,SUCCESSOR_ID_TRX_NO:8");
	keyRef.put("essCompOffAccumulationKey", "COMP_CODE:4,EMPLOYEE_ID:8,REQUEST_NO:8");
	keyRef.put("essCompOffAccumulationMulEmpKey", "COMP_CODE:4,REQUEST_NO:8");
	keyRef.put("annualLeaveCarryForwardKeyRef", "COMP_CODE:4,BRANCH_CODE:4,TRS_NO:10");
	keyRef.put("essLeaveCodeKeyRef", "COMP_CODE:4,LEAVE_CODE:4");
	keyRef.put("majorInfoKey", "COMP_CODE:4,SPEC_CODE:4");
	keyRef.put("ppsLeaveCreditHeaderKeyRef", "COMP_CODE:4,BRANCH_CODE:4,TRS_NO:10");
	keyRef.put("partiesInstitutionsKey", "COMP_CODE:4,BRANCH_CODE:4,INSTITUTION_CODE:3,INSTITUTION_TYPE:2");
	keyRef.put("ppsLeaveCreditKeyRef", "COMP_CODE:4,BRANCH_CODE:4,EMPLOYEE_ID:8,TRS_CODE:3,LEAVE_NO:10");
	keyRef.put("endOfServiceReasonsKeyRef", "COMP_CODE:4,REASON_CODE:3");
	keyRef.put("PPSAssetsKeyRef", "COMP_CODE:4,ASSET_CODE:3");
	keyRef.put("ppsLoanTypesKey", "COMP_CODE:4,LOAN_CODE:3");
	keyRef.put("ppsRegionKey", "COMP_CODE:4,REGION_CODE:4");
	keyRef.put("transactionTypeCodesKey", "COMP_CODE:4,BRANCH_CODE:4,TRX_TYPE_CODE:3");
	keyRef.put("insuranceTypesKey", "COMP_CODE:4,TYPE_CODE:3");
	keyRef.put("PPSSubSectionsKeyRef", "COMP_CODE:3,DIV_CODE:3,DEPT_CODE:3,SECTION_CODE:3,SUB_SECTION_CODE:3");
	keyRef.put("essRemunerationBasisKey", "COMP_CODE:4,TYPE_CODE:3");
	keyRef.put("essCalendarCodeKey", "COMP_CODE:4,CALENDAR_CODE:3");
	// project finance application
	keyRef.put("DayCategoriesKeyRef", "COMP_CODE:4,CAT_CODE:3");

	// Contract Type
	keyRef.put("contractTypeKey", "COMP_CODE:4,BRANCH_CODE:4,CONTRACT_TYPE:4");
	keyRef.put("prfnTemplateKey", "COMP_CODE:4,BRANCH_CODE:4");

	// fund Allotment
	keyRef.put("prfnFundsAllotmentKey", "COMP_CODE:4,BRANCH_CODE:4,ALLOTMENT_CODE:8");
	// Project Contract
	keyRef.put("prfnProjectKey", "COMP_CODE:4,BRANCH_CODE:4,PROJECT_CONTRACT_CODE:9");
	// project settlement
	keyRef.put("prfnProjectSettlementKey", "COMP_CODE:4,BRANCH_CODE:4,SETTLEMENT_CODE:7");
	keyRef.put("ppsRelationTypeKeyRef", "COMP_CODE:4,RELATION_CODE:3");
	keyRef.put("indemnityTypesAndBasisKeyRef", "COMP_CODE:4,TYPE_CODE:3");
	keyRef.put("employeeBankKeyRef", "COMP_CODE:4,BANK_CODE:8");
	keyRef.put("deductionTypesKeyRef", "COMP_CODE:4,TYPE_CODE:3,TYPE:1");
	keyRef.put("allowanceTypesKeyRef", "COMP_CODE:4,TYPE_CODE:3,TYPE:1");
	keyRef.put("unemploymentDeductionCodesKeyRef", "COMP_CODE:4,UNEMPLOYMENT_CODE:3,BRANCH_CODE:4");
	keyRef.put("checklistKeyRef", "COMP_CODE:4,CHECKLIST_CODE:4");
	keyRef.put("gosiCodeKeyRef", "COMP_CODE:4,BRANCH_CODE:4,GOSI_CODE:3");
	keyRef.put("linebusinessKeyRef", "COMP_CODE:4,BUSINESS_CODE:3");
	keyRef.put("overtimeRateKeyRef", "COMP_CODE:4,CAT_CODE:3");
	keyRef.put("incomeTaxKeyRef", "COMP_CODE:4,BRANCH_CODE:4,TAX_CODE:4");
	keyRef.put("zakatKeyRef", "COMP_CODE:4,BRANCH_CODE:4,ZAKAT_CODE:4");
	keyRef.put("religionsKeyRef", "COMP_CODE:4,RELIGION_CODE:4");
	keyRef.put("nationalitiesKeyRef", "COMP_CODE:4,COUNTRY_CODE:3");
	keyRef.put("essExtraSalaryKey", "COMP_CODE:4,BRANCH_CODE:4,SALARY_CODE:3");
	keyRef.put("essCompanyBankKey", "COMP_CODE:4,BRANCH_CODE:4,BANK_CODE:3");
	keyRef.put("essEmployeeTypesKey", "COMP_CODE:4,EMP_TYPE_CODE:5");
	keyRef.put("essContinentKey", "COMP_CODE:4,CODE:3");
	keyRef.put("salaryIndexKeyRef", "COMP_CODE:4,INDEX_CODE:4,BRANCH_CODE:4");
	keyRef.put("ramadanPeriodKeyRef", "COMP_CODE:4,RAMADAN_YEAR:4");
	keyRef.put("loansettlementKeyRef", "COMP_CODE:4,BRANCH_CODE:4,DIV_CODE:3,DEPT_CODE:3,SETT_NO:10,PMT_NO:3");
	keyRef.put("endofServiceKeyRef", "COMP_CODE:4,TRS_NO:10");
	keyRef.put("essLoanWriteOffKey", "COMP_CODE:4,BRANCH_CODE:4,DIV_CODE:3,DEPT_CODE:3,SETT_NO:10,PMT_NO:3");
	keyRef.put("leaveencashmentpaymentKeyRef",
		"BRANCH_CODE:4,COMP_CODE:4,EMPLOYEE_ID:8,TRS_NO:10,REQUEST_TRS_CODE:4");
	keyRef.put("extraSalaryEligiblityKeyRef", "COMP_CODE:4,BRANCH_CODE:4,TRS_NO:10");
	keyRef.put("probationExtension", "COMP_CODE:4,BRANCH_CODE:4,TRS_NO:8,EMPLOYEE_ID:8");
	keyRef.put("GosiRegistrationKey", "COMP_CODE:4,BRANCH_CODE:4,REGISTRATION_NO:4");
    keyRef.put("salaryStructureKey", "COMP_CODE:4,STRUCTURE_CODE:3");
    keyRef.put("saveInvitationKey", "COMP_CODE:4,BRANCH_CODE:4,TRS_NO:8");
    keyRef.put("SuspendReinstateKey", "COMP_CODE:4,TRS_NO:10");
    keyRef.put("unemploymentKey", "COMP_CODE:4,BRANCH_CODE:4,REGISTRATION_NO:8");
	keyRef.put("jobCompeteKeyRef", "COMP_CODE:4,BRANCH_CODE:4,JOB_DESC_CODE:4,EMPLOYEE_ID:8,JOB_TITLE_CODE:3,SL_NO:3");
	
	// Audit Assets application

	// Audit Security Information File Screen
	keyRef.put("securityInfoFileKey", "COMP_CODE:4,BRANCH:4,CODE1:8,CODE2:8");
	// Audit Portfolio Information File Screen
	keyRef.put("portfolioInfoFileKey", "COMP_CODE:4,BRANCH:4,CIF:8,SEQ:3");
	// Audit Portfolio Template Screen
	keyRef.put("portfolioTemplateKey", "COMP_CODE:4,BRANCH_CODE:4,CODE:4");
	// Audit Revaluation Param Screen
	keyRef.put("revaluationparamKey", "COMP_CODE:4,BRANCH_CODE:4");
	// Audit Cash Trsansaction Screen
	keyRef.put("cashTransactionKey", "COMP_CODE:4,BRANCH:4,LINE_NBR:10,TRADE_SERIAL_NBR:10");
	// Audit Security Template Screen
	keyRef.put("securityTemplateKey", "COMP_CODE:4,BRANCH_CODE:0,CODE:4");
	// Audit Portfolio relation File Screen
	keyRef.put("portfolioRelationFileKey", "COMP_CODE:4,BRANCH:4,CIF:8,SEQ:3");
	// Audit Security Type Screen
	keyRef.put("securityTypeKey", "COMP_CODE:4,BRANCH_CODE:0,CODE:4");
	// Audit Transaction Template Screen
	keyRef.put("trxTemplateKey", "COMP_CODE:4,BRANCH_CODE:4,CODE:6,APP_NAME:4");
	// Audit CIF Screen
	keyRef.put("cifKey", "COMP_CODE:4,BRANCH_CODE:0,CIF_NO:8");
	// Aother Audit CIF Screen
	keyRef.put("cifCodeKey", "COMP_CODE:4,BRANCH_CODE:0,CIF_CODE:8");
	// Audit Scheduled Template Management Fees Screen
	keyRef.put("scheduledTemplateMgtFeesKey", "COMP_CODE:4,BRANCH_CODE:0,CODE:6");
	// Audit Good Will Adjustment Screen
	keyRef.put("goodWillAdjustmentKey", "COMP_CODE:4,BRANCH:4,TRS_NO:10");
	// Audit Repayment Plan Screen
	keyRef.put("repaymentPlanMaintKey", "COMP_CODE:4,BRANCH_CODE:4,TRADE_SERIAL_NBR:10");
	// Audit Repayment Plan Settlement Screen
	keyRef.put("repaymentPlanSettlementMaintKey", "COMP_CODE:4,BRANCH_CODE:4,SETTLEMENT_NBR:10,LINE_NO:10");
	// Audit Transfer Trsansaction Screen
	keyRef.put("transferTransactionKey", "COMP_CODE:4,BRANCH:4,LINE_NBR:10,TRADE_SERIAL_NBR:10");
	// Audit Commitment Screen
	keyRef.put("commitmentKey", "COMP_CODE:4,BRANCH_CODE:4,COMMITMENT_NO:6");
	// Audit CUSTODIAN TRANSACTION Screen
	keyRef.put("custodianTransactionKey", "COMP_CODE:4,BRANCH:4,TRADE_SERIAL_NBR:12,LINE_NBR:3");
	// Audit capital Distribution Screen
	keyRef.put("capitalDistributionKey", "COMP_CODE:4,BRANCH_CODE:4,TRX_NBR:10,TRX_TYPE:1");
	// audit exercise rights issue screen
	keyRef.put("exerciseRightsIssueKey", "COMP_CODE:4,BRANCH_CODE:4,EXERCISE_NO:10");
	// Audit Call Option Screen
	keyRef.put("callOptionKey", "COMP_CODE:4,BRANCH_CODE:4,CODE:10");
	// Certificates Management
	keyRef.put("certificateMgmtKey", "COMP_CODE:4,BRANCH_CODE:4,TRX_TYPE:1,TRX_NUMBER:10,TRX_LINE_NBR:3");
	// subscription redemption screen
	keyRef.put("subscriptionRedemptionKey", "COMP_CODE:4,BRANCH_CODE:4,TRX_NBR:10,TRX_LINE_NBR:3");
	// Audit Dividend Transactions Screen
	keyRef.put("dividendTransactionsKey", "COMP_CODE:4,BRANCH:4,TRADE_SERIAL_NBR:10");
	// Trade Screens
	keyRef.put("tradesKey", "COMP_CODE:4,BRANCH:4,TRADE_SERIAL_NBR:10");
	// Audit Return Distribution Screen
	keyRef.put("returnDistributionKey", "COMP_CODE:4,BRANCH_CODE:4,TRS_NO:10");
	// Transfer of Security Screen
	keyRef.put("transferOfSecKey", "COMP_CODE:4,BRANCH_CODE:4,TRX_NUMBER:10");
	// Settlement Screen
	keyRef.put("settlementKey", "COMP_CODE:4,BRANCH_CODE:4,TRS_NO:12,TRS_TYPE:1,CB_IND:1");
	// Settlement Screen
	keyRef.put("assetsUtilizationKey", "COMP_CODE:4,BRANCH_CODE:4,TRX_NBR:12");
	// audit security price screen
	keyRef.put("securityPriceKey", "COMP_CODE:4,BRANCH:4,MARKET_DATE:19:T");
	// Audit Transaction Type Template Screen
	keyRef.put("trxTypeTemplateKey", "COMP_CODE:4,BRANCH_CODE:4,SEC_TYPE:4,TRX_TYPE:3");
	// audit fund info file screen
	keyRef.put("fundInfoFileKey", "COMP_CODE:4,BRANCH_CODE:4,CIF_NO:8");
	// Audit Fund Template Screen
	keyRef.put("fundTemplateKey", "COMP_CODE:4,CODE:4");
	// audit cif template screen
	keyRef.put("cifTemplateKey", "COMP_CODE:4,CODE:4");
	// Audit Transaction Type
	keyRef.put("transactionTypeKey", "COMP_CODE:4,CODE:7");
	// Audit Expiry Param Screen
	keyRef.put("expiryParamKey", "COMP_CODE:4,BRANCH_CODE:4");
	// Audit Tax Settlement Screen
	keyRef.put("taxSettlementKey", "COMP_CODE:4,BRANCH_CODE:4,TRX_NBR:10,TRX_TYPE:1");
	// audit security expected loss screen
	keyRef.put("securityExpectedLossKey", "COMP_CODE:4,BRANCH:4,ECL_DATE:19:T");
	// Auit For Limits Screen
	keyRef.put("limitsKey", "COMP_CODE:4,BRANCH_CODE:4");
	// Brokerage Fees
	keyRef.put("brokerageFeesKey", "COMP_CODE:4,BRANCH_CODE:4,SECURITY_TYPE:4,CURRENCY:3");
	// swift message screen
	keyRef.put("swiftMessageKey", "COMP_CODE:4,BRANCH_CODE:4,REFERENCE:16");
	
	// Audit Tax Code
	keyRef.put("taxCodeKey", "COMP_CODE:4,CODE:7");
	// Audit Portfolio Manager
	keyRef.put("portfolioManagerKey", "COMP_CODE:4,CODE:7");
	// Audit Portfolio Setup
	keyRef.put("onlyGridScreenKey", "COMP_CODE:4,BRANCH_CODE:4");
	// Audit Ticket upload control
	keyRef.put("ticketUploadControlKey", "COMP_CODE:4");
	

	/** Special Conditions (SPCONDS) **/
	keyRef.put("SPCONDSKey", "COMP_CODE:4,BRANCH_CODE:4,LINE_NO:10");

	// Merchant Management
	keyRef.put("merchantMgntKey", "COMP_CODE:4,BRANCH_CODE:4,CODE:6"); // TP07995
	   // -
	   // Hala
	   // Al
	   // Sheikh
	// Visit Schedule - TP # 412662 Customer RelationShip Management
	keyRef.put("visitScheduleKey", "COMP_CODE:4,BRANCH_CODE:4,CODE:6");
	
	// Visit Schedule - TP # 412662 Customer RelationShip Management
	keyRef.put("customerVisitKey", "COMP_CODE:4,BRANCH_CODE:4,VISIT_NO:10");
	// CRM activities - TP # 456267 Customer RelationShip Management 
	keyRef.put("customerActivityKey", "COMP_CODE:4,BRANCH_CODE:4,CODE:6");

	// Merchant Definition
	keyRef.put("merchantDefinitionKey", "COMP_CODE:4,MERCHANT_CODE:6");

	/** Dues Management **/
	keyRef.put("duesManagementKey",
		"COMP_CODE:4,BRANCH_CODE:4,DR_AC_BR:4,DR_AC_CY:3,DR_AC_GL:6,DR_AC_CIF:8,DR_AC_SL:3,DUE_CODE:12");

	// BBRE150008 -- [Raed Saad]
	keyRef.put("amendChequeCardKey", "COMP_CODE:4,BRANCH_CODE:4,PRODUCT_TYPE:3,BATCH_NO:8,LINE_NO:6");

	/** Denomination Amend **/
	keyRef.put("denomAmendKey", "COMP_CODE:4,BRANCH_CODE:4,TRS_NO:10,LINE_NO:4");
	/** Bacth Process **/
	keyRef.put("batchProcessKey", "COMP_CODE:4,BRANCH_CODE:4,BATCH_NO:10,SERIAL_NO:6");
	// csv item - reporting team
	keyRef.put("csvItemsKey", "COMP_CODE:4,BRANCH_CODE:0,REP_REF:15");
	keyRef.put("blgRatesKey", "COMP_CODE:4,CURRENCY_CODE:3,VALUE_DATE:10:D");
	// snpashot parameter screen
	keyRef.put("snapshotInfoKey", "COMP_CODE:0,BRANCH_CODE:0,REP_SNAPSHOT_ID:15");
	keyRef.put("snapshotParamKey", "COMP_CODE:4,BRANCH_CODE:0,REP_ID:15");
	keyRef.put("mismatchParamKey", "COMP_CODE:4,BRANCH_CODE:0,REP_MISMATCH_ID:15");

	// Dynamic Client parameters screen
	keyRef.put("dynClientParamsKey", "GROUP_CODE:6,TABLE_NAME:30,COLUMN_NAME:30,CONTROL_TYPE:1");

	keyRef.put("avaPaymentKey", "COMP_CODE:4,PAYMENT_CODE:12");
	keyRef.put("avaFileKey", "COMP_CODE:4,CODE:12");

	keyRef.put("avaAllocationKey", "COMP_CODE:4,ALLOCATION_CODE:12");

	// STF Off takers
	keyRef.put("dynClientParamsKey", "GROUP_CODE:6,TABLE_NAME:30,COLUMN_NAME:30,CONTROL_TYPE:1");

	// SADS
	keyRef.put("branchKey", "COMP_CODE:4,BRANCH_CODE:4");
	// Added the EMP_COMP_CODE and EMP_BRANCH_CODE and the N so not to pad
	// the user_id as requested by Rita and Zalfa to be similar as PB
	// behavior
	keyRef.put("userKey", "EMP_COMP_CODE:0,EMP_BRANCH_CODE:0,USER_ID:8:N");
	keyRef.put("ctrlRecordKey", "EMP_COMP_CODE:0,EMP_BRANCH_CODE:0");
	keyRef.put("messagesKey", "CODE:6");
	keyRef.put("companyKey", "COMP_CODE:4");
	keyRef.put("applicationKey", "APP_NAME:4");
	keyRef.put("optKey", "APP_NAME:4, PROG_REF:15");
	keyRef.put("smartOptKey", "OPTION_CODE:3,OPTION_SERIAL:6");
	keyRef.put("accessPrivilegesKey", "USER_ID:8,COMP_CODE:4,BRANCH_CODE:4,PROG_REF:15,APP_NAME:4");
	keyRef.put("divisionKey", "COMP_CODE:4,DIVISION_CODE:3");
	keyRef.put("departmentKey", "COMP_CODE:4,DIVISION_CODE:3,DEPT_CODE:3");
	keyRef.put("regionMaintainKey", "REGION_CODE:4");
	keyRef.put("unitMaintainKey", "COMP_CODE:4,DIVISION_CODE:3,DEPT_CODE:3,UNIT_CODE:3");
	keyRef.put("customFieldsKey", "PROG_REFERENCE:15,COMP_CODE:4");
	keyRef.put("userShiftKey", "COMP_CODE:4,BRANCH_CODE:4,USER_ID:28,LINE:2,DAY_OF_WEEK:1");
	keyRef.put("groupShiftKey", "CODE:4,LINE_NBR:4");
	keyRef.put("userContactList", "ID:4");
	keyRef.put("employeeNewKey", "COMP_CODE:4,EMPLOYEE_ID:4");
	keyRef.put("companygroupKey", "FAMILY_CODE:4");
	keyRef.put("archiveBusAreaKey", "BUS_AREA_CODE:2");
	keyRef.put("templateModelKey", "MODEL_ID:25");
	keyRef.put("axsPrivilegesKeyRef", "GROUP_ID:10");
	keyRef.put("removeUsrAxsKeyRef", "User_id:8");
	keyRef.put("userGroupKey", "GROUP_ID:10,STATUS:1");
	keyRef.put("axsByUsrKeyRef", "User_id:8");
	// END SADS

	// IMCO
	keyRef.put("imApiChannelKey", "CHANNEL_ID:4");
	keyRef.put("imApiNewapiKey", "API_CODE:4");
	keyRef.put("entityKey", "ENTITY_CODE:4");
	keyRef.put("sytmsetKey", "BR_CODE:4");
	// Access by web service
	keyRef.put("imcoPwsTmpltMasterKey", "TEMPLATE_ID:8");

	// Audit Alert application

	// Audit Control record screen
	keyRef.put("alertControlRecordKey", "ProgRef:15:N");
	keyRef.put("alertSubscriberKey", "ID:12");
	keyRef.put("alertGroupKey", "GRP_ID:6");
	keyRef.put("alertEventKey", "COMP_CODE:0,BRANCH_CODE:0,EVT_ID:8");
	keyRef.put("alertPackageKey", "PKG_ID:6");
	keyRef.put("reportQueryKey", "REP_QUER_ID:10");
	keyRef.put("alertEmailTempKey", "TEMPLATE_ID:10");
	keyRef.put("alertSubEvtKey", "ID:16");
	keyRef.put("alertSubPkgKey", "LINE_NO:12,PKG_ID:6,SUB_ID:12");
	keyRef.put("alertEvtGrpKey", "LINE_NO:12,GRP_ID:6,EVT_ID:8");
	keyRef.put("alertGrpPkgKey", "LINE_NO:12,GRP_ID:6,PKG_ID:6");

	// CMS app
	keyRef.put("cmsForecastTypesKey", "COMPANY_ID:10,CODE:4");
	keyRef.put("cmsForecastSubTypesKey", "COMPANY_ID:10,CODE:4");
	keyRef.put("cmsForecastKey", "COMPANY_ID:10,FORECAST_NO:4");
	keyRef.put("cmsMatchingForecastKey", "COMPANY_ID:10");
	
	keyRef.put("signatureKey", "COMP_CODE:4,BRANCH_CODE:4,CIF_NO:8,OBJECT_SEQ:5,ACC_CY:3,ACC_GL:6,ACC_CIF:8,ACC_SL:3");

	// IFRS
	keyRef.put("templateCreationKey", "LIMIT_ATRX_COMP_CODE:50:N,CODE1:10,LIMIT_ATRX_REF_KEYS:100:N,LIMIT_CODE:8");
	keyRef.put("poolFactorsKey",
		"LIMIT_ATRX_COMP_CODE:50:N,CODE1:10,CODE2:10,LIMIT_ATRX_REF_KEYS:100:N,LIMIT_CODE:8");
	keyRef.put("bucketingKey", "LIMIT_ATRX_COMP_CODE:50:N,LIMIT_CODE:8");
	keyRef.put("poolingHeaderKey", "COMP_CODE:4:N,POOL_CODE:10");
	keyRef.put("poolingDetailKey",
		"COMP_CODE:4:N,POOL_CODE:10:N,MAIN_LINE_NO:10:N,POPUP_TYPE:3:N,POPUP_LINE_NO:10:N");
	keyRef.put("staticPoolKey", "COMP_CODE:4:N,SCREEN_ID:10");
	keyRef.put("bucketingIISKey",
		"COMP_CODE:4:N,STAGING_OVERRIDE_FLAG:510:,MAIN_LINE_NO:10,POPUP_LINE_NBR:10,POPUP_TYPE:3");
	keyRef.put("bucketingASSETSKey",
		"COMP_CODE:4:N,STAGING_OVERRIDE_FLAG:510:,MAIN_LINE_NO:10,POPUP_LINE_NBR:10,POPUP_TYPE:3");
	keyRef.put("ifrsControlRecordKey", "COMP_CODE:4");
	keyRef.put("ifrsMacEcoFacAdjKey", "COMP_CODE:4:N,POOL_CODE:10,ECO_FACTOR_CODE:10,LINE_NO:10,PANEL_TYPE:3");
	keyRef.put("ifrsScoringHeaderKey", "COMP_CODE:4:N,SCORECARD_CODE:10");
	keyRef.put("ifrsQFactorsKey",
		"COMP_CODE:4:N,SCREEN_ID:10,LINE_NO:10,POOL_SEC_TYPE_FLAG:10,CODE:10,Q_FACTORS:200");
	keyRef.put("ifrsHistoricalPDKey", "COMP_CODE:4:N,SCREEN_ID:10,POOL_CODE:10");
	keyRef.put("ifrsLGDKey", "COMP_CODE:4:N,LINE_NO:10,SECURE_UNSECURE_FLAG:200");

	// remittance stock
	keyRef.put("remittanceStockKey", "COMP_CODE:4,BRANCH_CODE:4,TRX_NO:10");
	keyRef.put("custKey", "PROG_REF:15,APP_NAME:4,FLD_IDENTIFIER:6,ENTITY_CODE:6,ENTITY_TYPE:10");

	// Currency Exchange
	keyRef.put("currencyExchangeKey", "COMP_CODE:4,BRANCH_CODE:4,SEQ_NO:30");

	// Entity Information
	keyRef.put("entityInfKeyRef", "COMP_CODE:4,ENTITY_ID:4");

	// Template Entries
	keyRef.put("templateEntryKeyRef", "TMPLT_ENTRY_ID:3");

	// Relation Details
	keyRef.put("relationDetailsKeyRef", "RELATION_ID:3");

	// GROUP
	keyRef.put("groupDefKey", "COMP_CODE:4,BRANCH_CODE:0,GROUP_ID:4");

	// COA
	keyRef.put("COAKey", "COMP_CODE:4,COA_ID:4");

	// UPLOAD TRIAL BALANCE
	keyRef.put("uploadTrialBalKey", "UPL_ID:4");

	// Mapping
	keyRef.put("MAPPINGKey", "COA_MAP_ID:4");

	// Fcs Exchange Rate
	keyRef.put("fcsExchRateKey", "COMP_CODE:4,DATE_RATE:10:D");

	// Transfer Accounts
	keyRef.put("transferAccountsKey", "COMP_CODE:4,BATCH_NO:12");
	keyRef.put("BasicScreenKey", "COMP_CODE:4,KEY_TABLE_BASIC:4");

	keyRef.put("TfsGoodsKey", "COMP_CODE:4,CODE:4");
	keyRef.put("serviceChargesKey", "COMP_CODE:4,CODE:6");
	keyRef.put("tfsDoctypeKey", "COMP_CODE:4,CODE:4,DOC_TYPE:1");
	keyRef.put("tfsDocReportKey", "COMP_CODE:4,PROG_REF:15,REP_ID:15");
	keyRef.put("tfsEformIssuanceBranch", "COMP_CODE:4,BRANCH_CODE:4");
	keyRef.put("tfsAddTableInfoKey", "ENTITY_NAME:30,COLUMN_NAME:30");
	keyRef.put("tfsdDocumentsKey", "COMP_CODE:04,DOC_CODE:06");

	// Card Integration
	keyRef.put("cardIntegKey", "COMP_CODE:4,BRANCH_CODE:4,REQUEST_CODE:12");
	keyRef.put("dmssearchindexkey", "ProgRef:15,AppName:4,ColumnName:250:N,IndexNb:6");

	// internalAccounts
	keyRef.put("internalAccountsTypeKey",
		"COMP_CODE:4,BRANCH_CODE:4,CURRENCY_CODE:3,GL_CODE:6,CIF_SUB_NO:8,SL_NO:3");

	// Transfer Bill
	keyRef.put("transferBillKey", "COMP_CODE:4,BRANCH_CODE:4,CODE:10");

	// SADS DMS
	keyRef.put("dmsUserMapKey", "USER_ID:8");
	keyRef.put("dmsAxsMapKey", "USER_ID:8,APP_NAME:4,COMP_CODE:4,BRANCH_CODE:4");

	// 592296 Operation type for audit on retrieving 360 DASHBOARD
	keyRef.put("dashboard360Key", "COMP_CODE:0,BRANCH_CODE:0,CIF_NO:8");

	// OADM
	keyRef.put("newsFeedDetailsKey", "COMP_CODE:4,NEWS_CODE:3");
	keyRef.put("newsFeedTypeKey", "COMP_CODE:4,TYPE_CODE:3");
	keyRef.put("adBannerDetailsKey", "COMP_CODE:3,AD_BANNER_CODE:3");
	keyRef.put("adBannerTypeKey", "COMP_CODE:4,TYPE_CODE:3");
	keyRef.put("businessProfileKey", "BUSINESS_PROFILE_ID:8");
	keyRef.put("securityQuestionKey", "SECURITY_ID:8");
	keyRef.put("adminParamterKey", "COMP_ID:4,GROUP_ID:3,PROG_REF:15");
	keyRef.put("omniUserKey", "USR_ID:8");
	keyRef.put("trainingKey", "TRAINING_ID:8");
	keyRef.put("lostReasonsKey", "REASON_ID:8");
	keyRef.put("atmBranchesKey", "MAP_ID:8");
	keyRef.put("trxtypeCountrykey", "COUNTRY_TRX_TYPE_ID:8");
	keyRef.put("messageTypeKey", "MESSAGE_TYPE_ID:8");
	keyRef.put("authenticationMatrixKey", "AUTH_MATRIX_ID:8");
	keyRef.put("operAppChnlKey", "OPER_APP_CHNL_ID:8");
	keyRef.put("createMessageKey", "COMP_CODE:4,MESSAGE_CODE:5");
	keyRef.put("limitsMgntKey", "LIMIT_PACKAGE_ID:8");
	keyRef.put("chargesMgmntKey", "CHARGE_PACKAGE_ID:8");
	keyRef.put("omniCustomerKey", "CUSTOMER_ID:8");
	keyRef.put("customerGroupKey", "GROUP_ID:8");
	keyRef.put("customerMatrixKey", "MATRIX_ID:8");
	keyRef.put("omniOperationEventsKey", "OPER_EVENT_ID:8");
	keyRef.put("cifAddressKey", "CIF_ADDRESS_ID:8");
	keyRef.put("cifOccupationKey", "CIF_OCCUPATION_ID:8");
	keyRef.put("bannersKey", "BANNER_ID:8");
	keyRef.put("beneficiariesKey", "BENEFICIARIES_ID:8");
	keyRef.put("cifGroupsKey", "CIF_GROUP_ID:8");
	keyRef.put("ocCategoriesKey", "CATEGORIES_ID:8");
	keyRef.put("productAndServiceKey", "PRODUCT_AND_SERVICE_ID:8,CATEGORIES_ID:8");
	keyRef.put("ocTransactionTypeNameskey", "TRANSACTION_TYPE_NAMES_ID:8");
	keyRef.put("transactionLimitApprovalKey", "USR_LIMITS_ID:8");
	keyRef.put("channelCommonKey", "USR_ID:8,OPER_ID:8");
	// 801694
	keyRef.put("swiftMsg_msg_code", "MSG_CODE:1");
	keyRef.put("swiftReportsKey", "REP_ID:1");

	// Audit Reference for Clubbed Acccount
	keyRef.put("clubbedAccountKey", "COMP_CODE:4,CODE:6");

	// ATM
	keyRef.put("atmAcquirerKey", "ACQUIRER_ID:16");
	keyRef.put("atmTerminalKey", "TERMINAL_ID:16");
	keyRef.put("atmISOMessagesDefinitionKey", "ISO_MSG_DEF_ID:16");

	// AML
	// added by nour for 885556
	keyRef.put("blackListSourceKey", "COMP_CODE:4,CODE:4");
	// added by nour for 885556
	keyRef.put("blackListTypeKey", "COMP_CODE:4,CODE:4");

	// added by nour for 864398
	keyRef.put("criteriaKey", "COMP_CODE:4,CRITERIA_CODE:10");
	// added by nour for 925559
	keyRef.put("kycManagementKey", "COMP_CODE:4,BRANCH_CODE:4,CODE:10");
	
	//Added By Lina for BUG#1063795
    keyRef.put("controlRecordEngineKey", "COMP_CODE:4"); 

	// Rania - ZKI190014
	keyRef.put("statementOfAccountKey", "COMP_CODE:4,ADDITIONAL_REFERENCE:35");
	// Zahid Indher
	keyRef.put("alertManagementKey", "COMP_CODE:4,BRANCH_CODE:4,BUSINESS_RULES_CODE:4,ALERT_ID:10,CIF_NO:8");
	keyRef.put("lawEnfAgencyKey", "COMP_CODE:4,AGENCY_CODE:3");
	keyRef.put("caseSubTypeKey", "COMP_CODE:4,SUB_TYPE_CODE:3");
	keyRef.put("amlEngParCtrlRcrdKey", "COMP_CODE:4,PRIORITY_ID:4,LINE_NUMBER:4");
	keyRef.put("caseTypeKey", "COMP_CODE:4,TYPE_CODE:3");
	keyRef.put("iisAmendRepaymentApportionment", "COMP_CODE:4,BRANCH_CODE:4,TRX_NO:12");
	
	// Audit QUALITY Screen
	keyRef.put("qualityKey", "COMP_CODE:4,CODE:3");
	
	// Audit PAY MODE Screen
	keyRef.put("payModeKey", "COMP_CODE:4,PAYMODE_CODE:3");
	
	// Audit CUSTODIAN Screen
	keyRef.put("custodianKey", "COMP_CODE:4,CODE:8");
	
	// Audit DEALER Screen
	keyRef.put("dealerKey", "COMP_CODE:4,BRANCH:4,CODE:4");
	
	// Audit Paying Agent Screen
	keyRef.put("payingAgentKey", "COMP_CODE:4,CODE:4");
	
	// Audit PMS_CHARGES Screen
	keyRef.put("pmschargesKey", "COMP_CODE:4,BRANCH:4,CODE:4");
	
	// Audit CASHTYPE Screen
	keyRef.put("cashtypeKey", "COMP_CODE:4,CODE:10");
	
	// Audit BROKER Screen
	keyRef.put("brokerKey", "COMP_CODE:4,CODE:4");
	// Audit DEPOSITORYAGENT Screen
	keyRef.put("depositoryAgentKey", "COMP_CODE:4,CODE:4");
	
	// Audit FEES TRENCHES Screen
	keyRef.put("feesTrenchesKey", "COMP_CODE:4,BRANCH_CODE:4,LINE_NO:10,TRENCH_CODE:10");
	
	 // Audit ISSUANCE LIMIT Screen
	keyRef.put("issuancelimitKey", "COMP_CODE:4,BRANCH_CODE:4,ISSUANCE:20,CIF_NO:8"); 	  	 
	// Audit GLTYPETEMPLATE Screen
	keyRef.put("gltypetemplateKey", "APP_NAME:4,COMP_CODE:4,BRANCH_CODE:4");
	
	// Audit Collateral Types Screen
	keyRef.put("collateralTypesKey", "COMP_CODE:4,CODE:4");
	
	// security class 
	keyRef.put("securityClasskey", "COMP_CODE:4,BRANCH_CODE:4,CLASS_CODE:4");
	
	// stock exchange
	keyRef.put("stockexchangekey","COMP_CODE:4,CODE:4");
	
	// fund Yield
	keyRef.put("fundYieldkey", "COMP_CODE:4,BRANCH_CODE:4,FUND_CODE:8");
	 	

	//Rania
	keyRef.put("exchOfficeAuthKey", "COMP_CODE:4,AUTH_NO:6");
	
	// index management
    keyRef.put("indexmanagementkey","COMP_CODE:4,CODE:8");
    
    // Audit MAINTAIN INDEX POINTS Screen
    keyRef.put("indexPointsKey", "COMP_CODE:4,BRANCH:4,MARKET_NUMBER:9");
    
    // Audit Reference for Credit Evaluation
 	keyRef.put("creditEvaluationKey", "COMP_CODE:4");
 	
 	// asset management
 	keyRef.put("assetManagementKey", "COMP_CODE:4,BRANCH_CODE:4,TRS_NO:12,TRS_TYPE:2");
 	
 	 // Counter Party Limit 
    keyRef.put("counterPartyLimitkey","COMP_CODE:4,BRANCH_CODE:4,CIF_NO:8");    
    
    // Counter Party Security Type Limit
    keyRef.put("counterPartySecurityTypekey","COMP_CODE:4,BRANCH:4,CIF_NO:8,SEC_TYPE:4");

    //intra day limit 
   	keyRef.put("intraDayLimitkey", "COMP_CODE:4,BRANCH:4");
   	
    // Parameter Settlement Policy Screen
   	keyRef.put("settlementPolicykey", "COMP_CODE:4,BRANCH_CODE:4,SP_NO:4");
   	
    }

    // Audit Operation types
    public static final String UPDATE = "U";
    public static final String DELETE = "D";
    public static final String RETRIEVE = "R";
    public static final String VERIFY = "V";
    /**
     * public static final String REVERSE ="P"; public static final String
     * SUSPEND ="S"; public static final String REACTIVATE ="L";
     **/
    public static final String UPDATE_SMART = "M";
    public static final String RETRIEVE_SMART = "Q";
    public static final String CREATED = "C";
    public static final String UPDATE_BLOB = "UB";
    public static final String DETACH_BLOB = "DB";
    public static final String SCAN_BLOB = "SC";
    /*
     * Operation type for audit on printing report
     */
    public static final String REPORT_PRINT = "N";
    /*
     * Operation type for audit on retrieving report
     */
    public static final String REPORT_RETRIEVE = "E";
    /*
     * Operation type for audit on exporting report
     */
    public static final String REPORT_EXPORT = "X";
    /*
     * Operation type for audit/amendment on approve transaction
     */
    public static final String APPROVE = "A";
    /*
     * Operation type for audit/amendment on reject transaction
     */
    public static final String REJECT = "J";
    /*
     * Operation type for audit on cancel transaction
     */
    public static final String CANCEL = "L";

    // 592296 Operation type for audit on retrieving 360 DASHBOARD
    public static final String DASHBOARD_DETAILS = "T";

    // arbitrary condition to skip checking on Auditing the Checking on SMART
    // details
    public static final String CHECK_SMART = "CS";

    public static final String LINKS_KEY_REF = "linksKey";
    public static final String REC_REM_KEY_REF = "remrecKey";
    public static final String MEMO_DTL_KEY_REF = "memoDtlKey";
    public static final String TRX_MGNT_KEY_REF = "trxMgntKey";
    public static final String CHQBK_MGNT_KEY_REF = "chqBkMgntKey";
    public static final String CARDS_MGNT_KEY_REF = "cardsMgntKey";
    public static final String TRANS_CHASH_KEY_REF = "transCashKey";
    public static final String LOST_FOUND_KEY_REF = "lostFoundKey";
    public static final String SAFE_BOX_DEF_KEY_REF = "safeBoxDefKey";
    public static final String SAFE_BOX_MGMT_KEY_REF = "safeBoxMgmtKey";
    public static final String SAFE_BOX_DEF_LIST_KEY_REF = "safeBoxDefListKey";
    public static final String BLACK_LIST_KEY_REF = "blackListKey";
    public static final String PASSBOOK_MGNT_KEY_REF = "passBookMgntKey";
    public static final String BILL_REGISTRATION_KEY_REF = "billRegistrationKey";
    public static final String INCD_REPORT_KEY_REF = "incidentReportKey";
    public static final String DCHEQS_KEY_REF = "dcheqsKey";
    public static final String PASSBOOK_STOCK_DESTROY_KEY_REF = "passbooksStockDestroyKey";
    public static final String PASSBOOK_STOCK_REQUEST_KEY_REF = "passbooksStockRequestKey";
    public static final String SMS_SUBSCRIPTION_KEY_REF = "SmsSubscriptionKey";
    public static final String MERCHANT_POS_MANAGEMENT_KEY_REF = "MerchantPosMgntkey";
    public static final String CERTIFICATE_KEY_REF = "certificateKey";
    public static final String DYNAMIC_INTEGRATION_KEY_REF = "dynamicIntegrationKey";
    public static final String CTS_REQUEST_KEY_REF = "ctsRequestKey";
    public static final String REMITTANCESTOCK_KEY_REF = "remittanceStockKey";
    public static final String FOM_KEY_REF = "cifKey";
    public static final String BENEF_COOLING_KEY_REF = "benefCoolingKey";
    public static final String FOM_CODE_KEY_REF = "cifCodeKey";
    public static final String FIXED_GENERAL_KEY_REF = "amfKey";
    public static final String CARD_INTEGRATION_KEY_REF = "cardIntegKey";

    // Dynamic Template Convention
    public static final String DYNAMIC_TEMPLATE_CONV_KEY_REF = "dynamicTemplateConvKey";

    // Dynamic Template
    public static final String DYNAMIC_TEMPLATE_KEY_REF = "dynamicTemplateKey";

    // Audit OPT flags
    public static final String AUDIT_SAVE_ENABLED = "1";
    public static final String AUDIT_DELETE_ENABLED = "1";
    public static final String AUDIT_RETRIEVE_ENABLED = "1";

    // Audit reporting application
    public static final String TEMPLATE_KEY_REF = "templateKey";
    public static final String REPORTS_KEY_REF = "reportsKey";
    public static final String UPLOAD_DOWNLOAD_KEY_REF = "uploadDownloadKey";
    public static final String QUERIES_KEY_REF = "queriesKey";
    public static final String ENTITIES_KEY_REF = "entitiesKey";
    public static final String FILTER_CRITERIA_KEY_REF = "filterCriteriaKey";
    public static final String DYNAMIC_REP_KEY_REF = "dynamicRepKey";
    public static final String FOLDERS_KEY_REF = "foldersKey";
    public static final String SNAPSHOTS_KEY_REF = "snapshotsKey";
    public static final String SCHED_ENG_KEY_REF = "schedEngKey";
    public static final String SCHED_KEY_REF = "schedKey";
    public static final String COL_TEMPL_KEY_REF = "colTemplateKey";
    public static final String HYPERLINKS_KEY_REF = "hyperlinksKey";
    public static final String DB_CONNECTION_KEY_REF = "dbConnectionKey";
    public static final String DB_APP_CONNECTION_KEY_REF = "dbAppConnectionKey";
    public static final String FTR_CB_CODE_REF = "cbParamKey";
    public static final String CONTROL_RECORD_KEY_REF = "controlRecordKey";
    public static final String TEMPLATE_HEADER_KEY_REF = "templateHeaderKey";
    public static final String TIME_BUCKETS_KEY_REF = "timeBucketsKey";
    public static final String TIME_BUCKETS_LINE_KEY_REF = "timeBucketsLineKey";
    public static final String MAIL_SERVER_CONFIG = "mailServerConfig";
    public static final String USER_ACCESS_KEY_REF = "userAccessKey";
    public static final String USER_ACCESS_BRANCH_KEY_REF = "userAccessBranchKey";
    public static final String IRP_FILE_MAIN_KEY_REF = "irpFileMainKey";

    // Audit TFA application
    public static final String PRE_SETTLEMENT_SCHEDULE_REF = "preSettlementScheduleKey";
    public static final String MARGIN_KEY_REF = "marginKey";
    public static final String MANUAL_JV_KEY_REF = "manualJVKey";
    public static final String ADVICES_KEY_REF = "advicesKey";
    public static final String DOMICILIATIONDOC_KEY_REF = "domiciliationDocKey";
    public static final String DOMICILIATION_KEY_REF = "domiciliationKey";
    public static final String LC_KEY_REF = "lcKey";
    public static final String LC_REQ_KEY_REF = "lcReqKey";

    public static final String DOMICILIATION_STS_KEY_REF = "domiciliationStsKey";
    public static final String LG_KEY_REF = "lgKey";
    public static final String LG_TFSTRX_KEY_REF = "lgTfsTrxKey";
    public static final String BILL_TFSTRX_KEY_REF = "billTrxKey";
    public static final String LC_TFSTRX_KEY_REF = "lcTrxKey";
    public static final String OUTSIDE_CHARGES_KEY_REF = "outsideChargesKey";
    public static final String BILL_KEY_REF = "billKey";
    public static final String OTHER_COMMISSIONS_KEY_REF = "otherCommissionsKey";
    public static final String SUSPENSE_SETTLEMENT_KEY_REF = "suspenseSettlementKey";
    public static final String PRESTLMT_SCHED_KEY_REF = "preStlmtSchedKey";
    public static final String LG_TEXT_TYPE_KEY_REF = "lgTextTypeKey";
    public static final String CHARGES_AMEND_KEY_REF = "chargesAmendKey";

    public static final String EFORM_ISSUANCE_EXPORTER_KEY_REF = "eformIssuanceExporterKey";
    public static final String EFORM_CERTIFICATION_KEY_REF = "eformCertificationKey";
    public static final String ADVANCE_PAYMENT_KEY_REF = "advancePaymentKey";
    public static final String ADVANCE_PAYMENT_TRANSACIONS_KEY_REF = "advancePaymentTransactionsKey";

    // audit tfa param
    public static final String TFA_CONTROL_RECORD_KEY_REF = "TfaControlRecordKey";
    public static final String TFA_SERVICE_CHARGE_KEY_REF = "TfaServiceChargeKey";
    public static final String TFA_SWIFT_TEXT_KEY_REF = "TfaSwiftTxtKey";
    public static final String TFA_LINK_SCHEMA_KEY_REF = "TfaLinkSchemaKey";
    public static final String TFA_DEFINE_CHARGES_SPECIAL_KEY_REF = "TfaDefineChargesSpecial";
    public static final String NATIONALITY_KEY = "TfsGoodsKey";
    public static final String TFSDOCTYPE_KEY_REF = "tfsDoctypeKey";
    public static final String TFSDOC_REPORTS_REF = "tfsDocReportKey";
    public static final String TFA_TFSEFORMISSUSANCEBRANCH_REF = "tfsEformIssuanceBranch";
    public static final String TFSADDTABLEINFO_REF = "tfsAddTableInfoKey";
    public static final String TFSDOCUMENTS_REF = "tfsdDocumentsKey";

    // Audit Fatca
    public static final String FATCA_CONTROL_RECORD_KEY_REF = "fatcaControlRecordKey";
    public static final String FATCA_FORMS_KEY_REF = "fatcaFormsKey";
    public static final String FATCA_FINANCIAL_INST_KEY_REF = "fatcaFinancialInstStsKey";
    public static final String FATCA_SUB_FINANCIAL_INST_KEY_REF = "fatcaSubFinancialInstStsKey";
    public static final String FATCA_REASONS_REF = "reasonsKey";
    public static final String CIF_TAX_INFO_KEY_REF = "cifTaxInfoKey";
    public static final String CHAPTERS_KEY_REF = "chaptersKey";
    public static final String EXEMPTION_KEY_REF = "exemptionKey";
    public static final String INCOME_KEY_REF = "incomeKey";

    // Audit FAS application
    public static final String FAS_CONTROL_KEY_REF = "fasControlKey";
    public static final String FAS_LOCATION_KEY_REF = "fasLocationKey";
    public static final String FAS_PROPERTY_KEY_REF = "fasPropertyKey";
    public static final String FAS_CATEGORY_KEY_REF = "fasCategoryKey";
    public static final String FAS_CLASS_KEY_REF = "fasClassKey";
    public static final String FAS_MAINTENANCE_KEY_REF = "fasMaintenanceKey";
    public static final String FAS_RETIREMENT_REASONS_KEY_REF = "fasRetirementReasonsKey";
    public static final String FAS_CAPITAL_EXPENDITURE_CATEGORY_KEY_REF = "fasCapitalExpenditureCategoryKey";
    public static final String FAS_SUPPLIER_KEY_REF = "fasSupplierKey";
    public static final String FAS_VAT_RETENTION_KEY_REF = "fasVatRetentionKey";
    public static final String FAS_ASSET_KEY_REF = "fasAssetKey";
    public static final String FAS_PROJECT_KEY_REF = "fasProjectKey";
    public static final String FAS_PROJECT_TRX_KEY_REF = "fasProjectTrxKey";
    public static final String FAS_STOCK_COUNT_KEY_REF = "fasStockCountKey";
    public static final String FAS_TRX_KEY_REF = "fasTrxKey";
    public static final String FAS_ASSET_MUL_LOC_KEY_REF = "fasAssetMulLocationKey";
    public static final String FAS_LOCATION_TYPE_KEY_REF = "fasLocationTypeKey";
    public static final String FAS_CY_RATE_TOLERANCE_LEVEL_REF = "fasCyRateToleranceLevelKey";
    public static final String FAS_TRX_AST_KEY_REF = "fasTrxAstKey";
    public static final String FAS_TRX_AST_SEARCH_KEY_REF = "fasTrxAstSearchKey";
    public static final String FAS_TRX_AST_REQUEST_KEY_REF = "fasTrxAstRequestKey";
    public static final String FAS_REQUEST_STATUS_KEY_REF = "fasRequestStatusKey";
    public static final String FAS_REQUEST_TYPE_KEY_REF = "fasRequestTypeKey";

    // Audit STF application
    public static final String STF_SETTLEMENT_KEY_REF = "stfSettlementKey";
    public static final String STF_STOCK_KEY_REF = "stfStockKey";
    public static final String STF_STOCK_RELEASE_KEY_REF = "stfStockReleaseKey";
    public static final String STF_CONTRIBUTOR_SWITCHING_KEY_REF = "stfContributorSwitchingKey";
    public static final String STF_SECURITY_ALLOCATION_KEY_REF = "stfSecurityAllocationKey";
    public static final String STF_SHIPMENT_REQUEST_KEY_REF = "stfShipmentRequestKey";
    public static final String STF_SHIPMENT_REQUEST_AMNDMNT_KEY_REF = "stfShipmentRequestAmndmntKey";
    public static final String STF_SHIPMENT_KEY_REF = "stfShipmentKey";
    public static final String STF_TYPE_PARAM_KEY_REF = "stfTypeParamKey";
    public static final String STF_PURCHASE_KEY_REF = "stfPurchaseKey";
    public static final String STF_SALE_KEY_REF = "stfSaleKey";
    public static final String STF_COMMODITY_GRADE_KEY_REF = "stfCommodityGradeKey";
    public static final String STF_COMMODITY_MARKET_PRICE_KEY_REF = "stfCommodityMarketPriceKey";
    public static final String STF_WAREHOUSE_KEY_REF = "stfWarehouse";
    public static final String STF_OFFTAKERS_KEY_REF = "stfOffTakersKey";
    public static final String STF_OFFTAKERS_DETAIL_KEY_REF = "stfOffTakersDetailKey";
    public static final String STF_TOPUP_DEPOSIT_KEY_REF = "stfTopupDepositKey";
    public static final String STF_WAIVE_WRITE_OFF_KEY_REF = "stfWaiveWriteOffKey";
    public static final String STF_CATEGORY_KEY_REF = "stfCategoryKey";
    public static final String STF_COMMODITY_KEY_REF = "stfCommodityKey";
    public static final String STF_CHARGE_KEY_REF = "stfChargeKey";

    // Audit IIS application

    public static final String REPAYMENT_PLAN = "repaymentPlanKey";
    public static final String IIS_TRADE_DEAL = "iistradeDealKey";
    public static final String IIS_INVESTMENT_DEAL = "iisInvestmentDeal";
    public static final String IIS_PROMISSORY_FX_DEAL = "iisPromissoryFxDeal";
    public static final String IIS_LIMIT_MANAGEMENT = "iisLimitManagement";
    public static final String IIS_COUNTER_PARTY_LIMIT = "iisCounterPartyLimit";
    // TP#192774; Ramesh; 10/07/2014 For Audit Functionality
    public static final String IIS_CERTIFICATES_MANAGEMENT = "iisCertificatesManagementKey";
    public static final String IIS_FINANCE_ASSET_WRITE_OFF = "iisFinanceAssetWriteOff";
    public static final String IIS_PROVISION_AMENDMENTS = "iisProvisionAmendmentsKey";
    public static final String IIS_PROVISION_ADJUSTMENT = "iisProvisionAdjustmentKey";
    public static final String IIS_DEAL_CLASIF_AMENDMENTS = "iisDealClassifAmendmentKey";
    public static final String IIS_PROFIT_DECLARATION = "iisProfitDeclarationKey";
    public static final String IIS_CHARGE_REVERSAL = "iisChargeReversalKey";
    public static final String IIS_INCIDENTAL_CHARGES = "iisIncidentalChargesKey";
    public static final String IIS_ASSET_MATRIX_DET = "iisAssetMatrixDet";

    // TP#192774; Ramesh; 12/07/2014 For Audit Functionality
    public static final String IIS_CONTRIBUTOR_SWITCHING = "iisContributorSwitching";
    public static final String IIS_RELEASE_CONTRIB_BLOCKING = "iisReleaseContribBlocking";
    public static final String IIS_WAIVING = "iisWaiving";
    public static final String IIS_DISBURSEMENT_CANCELLATION = "iisDisbursementCancellation";
    public static final String IIS_CONTRIBUTOR_LIMIT = "iisContributorLimit";
    public static final String IIS_OBLIGOR_LIMIT = "iisObligorLimit";
    public static final String IIS_ESCROW_ACC_TEMPLATE = "iisEscrowAccTemplate";
    public static final String IIS_AMEND_REPAYMNET_APPORTIONMENT = "iisAmendRepaymentApportionment";

    public static final String IIS_COUNTRY_LIMIT = "iisCountryLimit";
    public static final String IIS_TRSPAYPLANTMPLT_REF = "trsRepaymentPlanTemplateKey";
    public static final String IIS_TRS_THIRD_PARTY_ADDRESS_REF = "trsThirdPartyAddressKey";
    public static final String IIS_TRSACCOUNT_UNIT_CODE_REF = "unitCodeSetupKey";
    public static final String IIS_TRSREPORT_TEMPLATE_REF = "reportTemplateKey";
    public static final String IIS_TRSVAT_REF = "trsVATKey";
    public static final String IIS_TRSSUB_PURPOSE_CODES_REF = "trsSubPurposeCodekey";
    public static final String IIS_TRSDEVIATION_TYPE_REF = "trsDeviationTypekey";
    public static final String IIS_TRSCIFCREDITRATING_REF = "trsCifCreditRatingKey";
    public static final String IIS_TRSHIJRISETUP_REF = "trsHijriSetupKey";
    public static final String IIS_TRSEMAILDETAILS_REF = "trsEmailDetailsKey";

    // public static final String IIS_COMMITMENT = "iisCommitmentKey";
    public static final String IIS_INTERNAL_DEAL = "iisInternalDealKey";
    public static final String IIS_SETTLEMENT = "iisSettlementKey";
    public static final String IIS_VENDORPAYMENT = "iisVendorPaymentKey";
    public static final String IIS_TRSDEAL_ADVANCE_INSURANCE = "iisTrsdealAdvanceInsuranceKey";
    public static final String IIS_TRSINSURANCE_DEAL = "iisTrsInsuranceDealKey";
    public static final String IIS_TRSTRACK = "iisTrsTrackKey";
    public static final String IIS_TRSPROFIT = "iisTrsProfitKey";
    public static final String IIS_TRSCHARGESREVERSAL = "iisTrsChargesReversalKey";
    public static final String IIS_TRSDEALRESTRUCTURE = "iisTrsDealStructureKey";
    public static final String IIS_TRSCERTIFICATEMGT = "iisTrsCertificateMgtKey";
    public static final String IIS_TRSSETLMT = "iisTrsSetLmtKey";
    public static final String IIS_TRSCONTRIB_SWITCH = "iisTrsContribSwitchKey";
    public static final String IIS_TRS_PROVISION_CIF_TRX = "iisTrsProvisionCifTrx";
    public static final String IIS_CONTRACT_MGMT = "iisContractMgmtKey";
    // Advance Payment
    public static final String IIS_ADVANCE_PAYMENT = "iisAdvancePaymentKey";

    public static final String IIS_TRSPRODUCT_TYPE_REF = "trsProductTypeKey";
    public static final String IIS_TRSFOREX_BASE_CY_REF = "forexBaseCyKey";
    public static final String IIS_TRS_EXPREV_REF = "trsRevenuesExpensesAccountsDetailsKey";
    public static final String IIS_TRSYIELDROSTER_REF = "yieldRosterKey";
    public static final String IIS_TRSPENALTY_REF = "trsPenaltyKey";
    public static final String IIS_TRSYIELDREGISTER_REF = "yieldRegisterKey";
    public static final String IIS_TRSTARGET_REF = "trsTargetMaintenanceKey";

    public static final String IIS_TRSCERTIFICATE_TYPES_REF = "trsCertificateTypesKey";
    public static final String IIS_TRSITEM_UNITPRICE_REF = "trsItemUnitPriceKey";
    public static final String IIS_TRS_PROV_PRODUCTS_REF = "trsProvProductsKey";
    public static final String IIS_TRSCY_RATE_TOLERANCE_LEVEL_REF = "cyRateToleranceLevelKey";
    public static final String IIS_TRS_VEND_INCENTIVE_REF = "trsVenderIncentiveSchemeKey";
    public static final String IIS_TRSREASON_REF = "trsReasonCodeKey";
    public static final String IIS_TRSINSURANCE_TYPES_REF = "trsInsuranceTypesKey";
    public static final String IIS_TRSINSURANCE_COMPANIES_REF = "trsInsuranceCompanieskey";
    public static final String IIS_TRSBOARD_MEMBERS_REF = "trsBoardMembersKey";
    public static final String IIS_TRSCLASS_PORTFOLIOACC_REF = "trsProductClassPortfolioAccountsKey";
    public static final String IIS_TRS_PROVISION_GROUP_REF = "trsProvisionGroupKey";
    public static final String IIS_TRS_PROVISION_CATEGORY_REF = "trsProvisionCategoryKey";
    public static final String IIS_TRS_ITEM_CATEGORY_REF = "trsItemCategoryKey";
    public static final String IIS_TRSCLASS_FUNDACC_REF = "trsClassFundAccKey";
    public static final String IIS_TRS_VENDOR_REF = "trsVendorKey";
    public static final String IIS_CALCULATOR = "iisCalculator";

    public static final String IIS_IISCTRL_REF = "iisctrlKey";
    public static final String IIS_TRS_CAGAMAS_MAIN_REF = "trsCagamasMainKey";
    public static final String IIS_TRS_CY_FORWARD_FX_RATE_REF = "trsCyForwardFxRateKey";
    public static final String IIS_TRS_CY_FORWARD_RATE_REF = "trsCyForwardRateKey";
    public static final String IIS_TRSABISCRIPTS_REF = "trsABIScriptsKey";
    public static final String IIS_TRSCIFCBNO_REF = "trsCIFCBNoKey";
    public static final String IIS_TRSDESKMGMT_REF = "trsDeskMgmtKey";
    public static final String IIS_TRS_ITEM_SWIFT_CODE_REF = "trsItemSwiftCodeKey";
    public static final String IIS_TRS_BROKER_PAYMENTS_REF = "trsBrokerPaymentsKey";
	public static final String IIS_TRSASSET_REF = "trsAssetKey";

	public static final String IIS_TRS_ITEM_PROPERTY_REF = "trsItemPropertyKey";
	public static final String IIS_TRS_ITEM_MAINTENANCE_REF = "trsItemMaintenanceKey";
	public static final String IIS_TRS_CHARGES_REF = "trsChargesKey";
	public static final String IIS_TRS_MUDARABAH_RATE_HDR_REF ="MudharabahKey";
	
    public static final String FMS_APPL_KEY_REF = "fmsApplKey";
    public static final String FMS_FACILITY_KEY_REF = "fmsFacilityKey";
    public static final String FMS_DRAWDOWN_KEY_REF = "fmsDrawdownKey";
    public static final String FMS_CLOSURE_KEY_REF = "fmsClosureKey";
    public static final String FMS_FACILITY_BLOCK_AMT_KEY_REF = "fmsFacilityBlockAMTKey";
    public static final String FMS_FINANCE_PAYMENT_KEY_REF = "fmsFinancePaymentKey";
    public static final String FMS_CUST_GRADE_KEY_REF = "fmsCustgradeKey";
    public static final String FMS_COLLATERAL_KEY_REF = "fmsCollateralKey";
    public static final String FMS_COLAT_TYPE = "fmsColatTypeKey";
    public static final String COLAT_TYPE_ATTRIBUTES = "fmsColatTypeAttributes";
    public static final String FMS_INDICATOR_KEY_REF = "fmsIndicatorKey";
    public static final String CUST_GRADE_SCORE_KEY_REF = "custGradeScoreKey";
    public static final String FMS_DRWDWNTYPE_KEY_REF = "fmsDrawdownTypeParamKey";
    public static final String FMS_CONTROL_RECORD = "FMSControlRecordKey";
    public static final String FMS_APPROVAL_COMMITTIES = "fmsApprovalCommities";
    public static final String FMS_FACILITY_TYPE = "fmsFacilityType";
    public static final String FMS_COUNTRY_LIMIT = "fmsCountryLimit";
    public static final String FMS_ESTIMATOR = "fmsEstimator";
    public static final String FMS_SPECFUND = "fmsSpecFund";
    public static final String FMS_PARAM_FACILITY_DOCUMENTS = "paramFacilityDocumentsKey";
    public static final String FMSSUBMISSION_KEY_REF = "fmsSubmissionKey";
    public static final String FMSFOLDERS_KEY_REF = "fmsFoldersKey";
    public static final String FMSCATEGORY_KEY_REF = "fmsCategoryKey";
    public static final String FMS_PARAM_AUDITORS = "fmsParamAuditorsKey";
    public static final String FMS_DEV_LIMITS = "fmsDevLimits";
    public static final String FMS_SUPPLIER_BUYER_CRITERYA = "fmsSupplierBuyerCriteryaKey";
    public static final String FMS_PARAM_COMMENTS = "fmsParamCommentsKey";
    public static final String FMS_PARAM_SUSPEND_REASONS = "fmsParamSuspendReasonsKey";
    public static final String FMS_PARAM_SOLICITORS = "fmsParamSolicitorsKey";
    public static final String FMS_PARAM_GRADE_EVAL_FACTORS = "fmsParamGradeEvalFactorsKey";
    public static final String FMS_DOCUMENT_CHECKLIST = "fmsParamDocChecklist";
    public static final String FMS_PARAM_CORP_PKG = "fmsParamAuditCorp";
    public static final String FMS_NATIONALITY_LIMIT = "fmsExposureLimitByNationality";
    public static final String PURPOSE_OF_FINANCING_KEY_REF = "purpFinancingKey";
    public static final String FMS_COUNTER_PARTY_BARNCHES = "counterPartyBranchesKey";
    public static final String FMS_PAPRENT_COUNTER_PARTY_BARNCHES = "parentCounterPartyBranchesKey";
    public static final String FMSHAMISHALJIDDIYA_KEY_REF = "fmshamishaljiddiyakey";
 // Jishnu Pg ; 24/01/2020
    public static final String COVERAGE_TYPE_KEY_REF = "coverageTypeKey";
    // Jishnu Pg ; 27/01/2020
    public static final String REASON_KEY_REF = "reasonKeyRef";

    // added by azhar; #391995 - ITFC130024
    public static final String FUND_LIMIT_KEY_REF = "fundLimitKey";
    // TP#392048 - ITFCI140020 ; Jishnu Pg
    public static final String CHARGE_COLLECTION_KEY_REF = "facilityChargeCollectionKeyRef";

    public static final String FACILITY_PROJECTION_KEY_REF = "facilityProjectionKey";
    public static final String FACILITY_CANCELLATION_KEY_REF = "facilityCancellationKey";
    
    
    // Audit PCS application
    public static final String PCS_CIF_TYPE = "pcsCifType";
    public static final String PCS_PROFIT_GROUP_MANAGEMENT = "ProfitGroupManagement";
    public static final String PCS_ACCOUNTS_TYPE = "accountsType";
    public static final String PCS_CONTROL_RECORD = "PCSControlRecordKey";
    public static final String PCS_GENRAL_LEDGER = "generalLedger";
    // Audit FixedMaturityAccount Screen
    public static final String FIXED_MATURITY_ACCOUNT_REF = "fixedMaturityAccountkey";
    public static final String PCS_INTERNAL_ACCOUNTS = "PCSInternalAccountsKey";
    public static final String PCS_ACCOUNT_CLASS = "PCSAccountClassKey";
    // RatesManagement parameter screen
    public static final String PCS_RATESMANAGEMENT_TYPE = "ratesManagementTypeKey";
    public static final String PCS_GENRAL_LEDGER_STANDALONE = "generalLedgerStandAlone";
    // PCS StandAlone Fixed Maturity Account Screen
    public static final String PCS_STAND_ALONE_FIXED_MATURITY_ACCOUNT_REF = "pcsStandAlonefixedMaturityAccountkey";
    public static final String PCS_ACCOUNTS_TYPE_WHEN_STAND_ALONE = "accountsTypeWhenStandAloneKey";
    public static final String PCS_MASTER_EQUITY = "PCSMasterEquityRecordKey";
    public static final String PFTADVANCETRX_KEY_REF = "pftadvancetrxkey";
    public static final String PCS_UPDATE_STATISTICS = "updateStatistics";

    // Audit ESS application
    public static final String ESS_REQUEST_REF = "essEmpRequestKey";
    public static final String ESS_TRAINING_REQ_REF = "essEmpTrainingRequestKey";
    public static final String ESS_LOAN_REQ_REF = "essEmpLoanRequestKey";
    // Audit Training Types Screen
    public static final String TRAINING_TYPES_KEY_REF = "trainingTypesKey";
    // Audit Training Budget Screen
    public static final String TRAINING_BUDGET_KEY_REF = "trainingBudgetKey";
    // Audit Annual Leave plan
    public static final String ESS_EMP_LEAVE_PLAN = "essEmpLeavePlanKey";
    // Audit TravelRequstPayment
    public static final String ESS_TRAVEL_REQUEST_PAYMENTS_REF = "essTravelRequestPaymentsKey";
    // Employee Role Screen
    public static final String EMPLOYEE_ROLE_KEY_REF = "employeeRoleKey";
    // Audit ESS Employee Appraisal Set up Screen
    public static final String EMPLOYEE_APPRAISAL_SETUP_REF = "essEmpAppraisalSetUpKey";
    // Audit for competencies Library
    public static final String ESS_COMPETENCIES_LIB = "essConpenciesLibraryKey";
    // Audit Competency Rating Screen
    public static final String COMPETENCY_RATING_KEY_REF = "competencyRatingKey";
    // Audit Miscellaneous Request types
    public static final String MISCELLANEOUS_REQUEST_TYPES_KEY_REF = "miscellaneousRequestTypesKey";
    public static final String ESS_MY_PROFILE_REF = "essEmpMyProfileKey";
    public static final String ESS_LEAVE_REQUEST_REF = "essLeaveRequestKey";
    // Audit Document Types Screen
    public static final String ESS_DOCUMENT_TYPES_KEY_REF = "essDocumentTypesKey";
    // Audit Appraisal Form Screen
    public static final String ESS_APPRAISAL_FORM_KEY_REF = "essAppraisalFormKey";
    public static final String EMPLOYEE_KPI_KEY_REF = "employeeKpiKey";
    public static final String EMPLOYEE_KPI_KEY_REF_INITIATE = "employeeKpiKeyInit";
    public static final String APPRAISAL_FORMSETUP_KEY_REF = "appraisalFormSetupKey";
    public static final String ESS_DOCUMENT_TEMPLATE_KEY_REF = "essDocumentTemplateKey";
    public static final String ESS_DOCUMENT_GENERATE_KEY_REF = "essDocumentGenerateKey";
    public static final String ATTENDANCE_VIOLATION_KEY_REF = "attendannceViolationScoreKey";
    public static final String PPS_EDUCATIONAL_INFO_KEY_REF = "educationalInfoKey";
    public static final String PPS_BONUS_CODE_KEY_REF = "ppsBonusCodeKeyRef";
    public static final String PPS_GRADES_KEY_REF = "ppsGradesCodeKeyRef";
    public static final String ESS_BONUS_PAYMENTS_KEY_REF = "essBonusPaymentsKey";
    public static final String PPS_JOB_TITLE_KEY_REF = "ppsJobTitleCodeKeyRef";
    public static final String PPS_JOB_KEY_REF = "ppsJobKeyRef";
    public static final String PPS_CONTROL_RECORD_KEY_REF = "ppsControlRecordKeyRef";
    public static final String PPS_PERDIEM_CODE_KEY_REF = "ppsPerdiemCodeKeyRef";
    public static final String ESS_MODIFY_CALENDAR_KEY_REF = "essModifyCalendarKey";
    public static final String PPS_EMPLOYEE_MASTERFILE_KEY_REF = "ppsEmployeeMasterFileKeyRef";
    public static final String PPS_LOAN_TRANSACTION_KEY_REF = "ppsLoanTransactionKeyRef";
    public static final String EMPLOYEE_KPI_PROB_REF_INITIATE = "employeeKpiProbKey";
    public static final String PPS_CALENDAR_KEY_REF = "ppsCalendarKeyRef";
    public static final String EMPLOYEE_JOB_DESC = "employeeJobDescKey";
    public static final String PPS_PROBATION_EVALUATION_KEY_REF = "ppsProbationEvaluationKeyRef";
    public static final String PPS_QUESTION_BANK_KEY_REF = "ppsQuestionBankKeyRef";
    public static final String PPS_PSYCHOMETRIC_USER_SCREEN_KEY_REF = "ppsPsychometricUserScreenKeyRef";
    public static final String EMPLOYEE_SUCCESSION_PLANNING = "employeeSuccessionKey";
    public static final String ESS_EMP_MOD_INFO_KEY_REF = "essModifyInfoKey";
    public static final String ESS_MULT_EMP_MOD_INFO_KEY_REF = "essMultEmpModInfoKey";
    public static final String SUCCESSION_ACTION_PLANNING = "successionActionPlanKey";
    public static final String ESS_COMP_OFF_ACC_KEY_REF = "essCompOffAccumulationKey";
    public static final String ESS_COMP_OFF_ACC_MUL_EMP_KEY_REF = "essCompOffAccumulationMulEmpKey";
    public static final String ANNUAL_LEAVE_CARRY_FORWARD_KEY_REF = "annualLeaveCarryForwardKeyRef";
    public static final String ESS_LEAVE_TYPE_KEY_REF = "essLeaveCodeKeyRef";
    public static final String PPS_MAJOR_INFO_KEY_REF = "majorInfoKey";
    public static final String PPS_LEAVE_CREDIT_HEADER_KEY_REF = "ppsLeaveCreditHeaderKeyRef";
    public static final String PPS_INSTITUTIONS_KEY_REF = "partiesInstitutionsKey";
    public static final String PPS_LEAVE_CREDIT_KEY_REF = "ppsLeaveCreditKeyRef";
    public static final String END_OF_SERVICE_REASONS_KEY_REF = "endOfServiceReasonsKeyRef";
    public static final String PPS_ASSETS_KEY_REF = "PPSAssetsKeyRef";
    public static final String ESS_PPSLOANTYPES_REF = "ppsLoanTypesKey";
    public static final String PPSREGION_REF = "ppsRegionKey";
    public static final String ESS_TRANSACTION_TYPE_CODES_REF = "transactionTypeCodesKey";
    public static final String ESS_INSURANCE_TYPES_REF = "insuranceTypesKey";
    public static final String PPS_SUB_SECTIONS_KEY_REF = "PPSSubSectionsKeyRef";
    public static final String ESS_REMUNERATION_BASIS_REF = "essRemunerationBasisKey";
    public static final String ESS_CALENDAR_CODE_REF = "essCalendarCodeKey";
    public static final String DAY_CATEGORIES_KEY_REF = "DayCategoriesKeyRef";
    public static final String PPS_RELATION_TYPE_KEY_REF = "ppsRelationTypeKeyRef";
    // Audit Project Finance
    // audit for contract type screen
    public static final String CONTRACT_TYPE_KEY_REF = "contractTypeKey";
    public static final String PRFN_TEMPLATE_KEY_REF = "prfnTemplateKey";

    // audit for funds allotment
    public static final String PRFN_FUNDS_ALLOTMENT_KEY = "prfnFundsAllotmentKey";
    // audit for Project Contract
    public static final String PRFN_PROJECT_KEY = "prfnProjectKey";
    // audit for project Settlment
    public static final String PRFN_PROJECT_SETTLEMENT_KEY = "prfnProjectSettlementKey";

    public static final String DEDUCTION_TYPE_KEY_REF = "deductionTypesKeyRef";
    public static final String OVERTIME_RATES_KEY_REF = "overtimeRateKeyRef";
    public static final String ALLOWANCE_TYPE_KEY_REF = "allowanceTypesKeyRef";
    public static final String UNEMPLOYMENT_DEDUCTION_CODES_KEY_REF = "unemploymentDeductionCodesKeyRef";
    public static final String CHECKLIST_KEY_REF = "checklistKeyRef";
    public static final String GOSI_CODE_KEY_REF = "gosiCodeKeyRef";
    public static final String LINE_OF_BUSINESS_KEY_REF = "linebusinessKeyRef";
    public static final String INDEMNITY_TYPES_AND_BASIS_KEY_REF = "indemnityTypesAndBasisKeyRef";
    public static final String EMPLOYEE_BANK_KEY_REF = "employeeBankKeyRef";
    public static final String INCOME_TAX_KEY_REF = "incomeTaxKeyRef";
    public static final String ZAKAT_KEY_REF = "zakatKeyRef";
    public static final String RELIGIONS_KEY_REF = "religionsKeyRef";
    public static final String NATIONALITIES_KEY_REF = "nationalitiesKeyRef";
    public static final String ESS_EXTRA_SALARY_REF = "essExtraSalaryKey";
    public static final String ESS_COMPANY_BANK_REF = "essCompanyBankKey";
    public static final String ESS_EMPLOYEE_TYPES_REF = "essEmployeeTypesKey";
    public static final String ESS_CONTINENT_REF = "essContinentKey";
    public static final String SALARY_INDEX_KEY_REF = "salaryIndexKeyRef";
    public static final String LEAVE_ENCASHMENT_PAYMENT_KEY_REF = "leaveencashmentpaymentKeyRef";
    public static final String ESS_LOAN_WRITE_OFF_REF = "essLoanWriteOffKey";
    public static final String END_OF_SERVICE_KEY_REF = "endofServiceKeyRef";
    public static final String LOAN_SETTLEMENT_KEY_REF = "loansettlementKeyRef";
    public static final String RAMADAN_PERIOD_KEY_REF = "ramadanPeriodKeyRef";
    public static final String EXTRA_SALARY_ELIGIBLITY_KEY_REF = "extraSalaryEligiblityKeyRef";
	public static final String PPS_PROBATION_KEY_REF = "probationExtension";
	public static final String GOSI_REGISTRATION_KEY_REF = "GosiRegistrationKey";
    public static final String SALARY_STRUCTURE_REF = "salaryStructureKey";
    public static final String PPS_SAVE_INVITATION_KEY_REF = "saveInvitationKey";
    public static final String PPS_UNEMPLOYMENT_KEY_REF = "unemploymentKey";
    public static final String SUSPEND_REINSTATE_KEY_REF = "SuspendReinstateKey";
    public static final String PRODUCT_SUB_CATEGORY="produtsubcategory";
	public static final String JOB_COMPETE_KEY_REF = "jobCompeteKeyRef";
	
    // Audit Assets application
    // Audit Security Screen
    public static final String SEC_INFO_FILE_REF_KEY = "securityInfoFileKey";
    // Audit Portfolio Information File Screen
    public static final String PORTFOLIO_INFO_FILE_KEY_REF = "portfolioInfoFileKey";
    // Audit Portfolio Template Screen
    public static final String PORTFOLIO_TEMPLATE_KEY_REF = "portfolioTemplateKey";
    // Audit Revaluation Param Screen
    public static final String REVALUATION_PARAM_KEY_REF = "revaluationparamKey";
    // Audit Cash Trsansaction Screen
    public static final String CASH_TRX_KEY_REF = "cashTransactionKey";
    // Audit Security Template Screen
    public static final String SEC_TEMP_REF_KEY = "securityTemplateKey";
    // Audit Portfolio Relation File Screen
    public static final String PORTFOLIO_Relation_FILE_KEY_REF = "portfolioRelationFileKey";
    // Audit Security Type Screen
    public static final String SECURITY_TYPE_KEY_REF = "securityTypeKey";
    // Audit Transaction Template Screen
    public static final String TRX_TEMPLATE_KEY_REF = "trxTemplateKey";
    // Audit Scheduled Template Management Fees Screen
    public static final String SCHEDULED_TEMPLATE_MANAGEMENT_FEES_KEY_REF = "scheduledTemplateMgtFeesKey";
    // Audit Good Will Adjustment Screen
    public static final String GOODWILL_ADJUSTMENT_KEY_REF = "goodWillAdjustmentKey";
    // Audit Repayment Plan Screen
    public static final String REPAYMENT_PLAN_MAINT_KEY_REF = "repaymentPlanMaintKey";
    // Audit Repayment Plan Settlement Screen
    public static final String REPAYMENT_PLAN_SETT_MAINT_KEY_REF = "repaymentPlanSettlementMaintKey";
    // Audit Transfer Trsansaction Screen
    public static final String TRANSFER_KEY_REF = "transferTransactionKey";
    // Audit Commitment Screen
    public static final String COMMITMENT_KEY_REF = "commitmentKey";
    // Audit CUSTODIAN TRANSACTION Screen
    public static final String CUSTODIAN_TRANSACTION_KEY_REF = "custodianTransactionKey";
    // Audit CAPITAL Distribution
    public static final String CAPITAL_DISTRIBUTION_KEY_REF = "capitalDistributionKey";
    // audit exercise issue screen
    public static final String EXERCISE_RIGHTS_ISSUE_KEY_REF = "exerciseRightsIssueKey";
    // Audit Call Option Screen
    public static final String CALL_OPTION_KEY_REF = "callOptionKey";
    // Audit Certificates management Screen
    public static final String CERTIFICATES_MNGMT_KEY_REF = "certificateMgmtKey";
    // subscription redemption screen
    public static final String SUBSCRIPTION_KEY = "subscriptionRedemptionKey";
    // Audit Dividend Transactions Screen
    public static final String DIVIDEND_TRANSACTIONS_KEY_REF = "dividendTransactionsKey";
    // Trade Screens
    public static final String TRADES_KEY_REF = "tradesKey";
    // Audit Return Distribution Screen
    public static final String RETURN_DISTRIBUTION_KEY_REF = "returnDistributionKey";
    // Audit for Transfer Of Security Screen
    public static final String TRANSFER_OF_SECURITY_KEY_REF = "transferOfSecKey";
    // Settlement Screen
    public static final String SETTLEMENT_KEY_REF = "settlementKey";
    // Settlement Screen
    public static final String ASSETS_UTIL_KEY_REF = "assetsUtilizationKey";
    // audit security price screen
    public static final String SECURITY_PRICE_KEY_REF = "securityPriceKey";
    // Audit Transaction Type Template Screen
    public static final String TRX_TYPE_TEMPLATE_KEY_REF = "trxTypeTemplateKey";
    // audit fund info file screen
    public static final String FUND_INFO_FILE_KEY_REF = "fundInfoFileKey";
    // Audit Fund Template Screen
    public static final String FUND_TEMPLATE_KEY_REF = "fundTemplateKey";
    // audit for cif template screen
    public static final String CIF_TEMPLATE_KEY_REF = "cifTemplateKey";
    // Audit Transaction Type Template Screen
    public static final String TRANSACTION_TYPE_KEY_REF = "transactionTypeKey";
    // Audit Revaluation Param Screen
    public static final String EXPIRY_PARAM_KEY_REF = "expiryParamKey";
    // Audit Tax Settlement Screen
    public static final String TAX_SETTLEMENT_KEY = "taxSettlementKey";
    // audit security EXPECTED LOSS screen
    public static final String SECURITY_EXPECTED_LOSS_KEY_REF = "securityExpectedLossKey";
    // Audit For Limits screen
    public static final String LIMITS_SCREEN_KEY_REF = "limitsKey";
    // Audit For Brokerage Fees screen
    public static final String BROKERAGE_FEES_KEY = "brokerageFeesKey";
    // Swift Free Text Message Parameters Screen
    public static final String SWIFT_FREE_TEXT_MESSAGE_KEY_REF = "swiftMessageKey";
    
    // Added by Mohamad for tp id #770497
    public static final String TAX_CODE_KEY = "taxCodeKey";
    // Added by Mohamad for tp id #781498
    public static final String PORTFOLIO_MANAGER_KEY = "portfolioManagerKey";
    // Added by Mohamad for tp id #155312
    public static final String ONLY_GRID_SCREEN_KEY = "onlyGridScreenKey";
    // Added by Mohamad for tp id #781480
    public static final String TICKET_UPLOAD_CONTROL_KEY = "ticketUploadControlKey";


    // FATCA Details
    public static final String FATCA_DETAILS_KEY_REF = "fomFatcaDetailsKey";

    /** Special Conditions (SPCONDS) **/
    public static final String SPCONDS_KEY_REF = "SPCONDSKey";

    // Merchant Management
    public static final String MERCHANTMGNT_KEY_REF = "merchantMgntKey";
    
    
    // visit Schedule
    public static final String VISIT_SCHEDULE_KEY_REF = "visitScheduleKey";
    
    // Customer Relation Mgnt Visits
    public static final String CUSTOMER_VISIT_KEY_REF = "customerVisitKey";
    // Customer Relation Mgnt Activities 
    public static final String  CUSTOMER_ACTIVITY_KEY_REF = "customerActivityKey";

    // Merchant Definition
    public static final String MERCHANT_DEFINITION_KEY_REF = "merchantDefinitionKey";

    // BBRE150008 -- [Raed Saad]
    public static final String AMENDCHEQUECARD_KEY_REF = "amendChequeCardKey";

    /** Dues Management **/
    public static final String DUES_MANAGEMENT_KEY_REF = "duesManagementKey";

    /** Denomination Amend **/
    public static final String DENOM_AMEND_KEY_REF = "denomAmendKey";

    /** Batch Process **/
    public static final String BATCH_PROCESS_KEY_REF = "batchProcessKey";

    // csv item - reporting team
    public static final String CSV_ITEMS_KEY_REF = "csvItemsKey";

    public static final String BLG_RATES_KEY_REF = "blgRatesKey";
    // snapshot parameter screen
    public static final String SNAPSHOT_INFO_KEY_REF = "snapshotInfoKey";
    public static final String SNAPSHOT_PARAM_KEY_REF = "snapshotParamKey";
    public static final String MISMATCH_PARAM_KEY_REF = "mismatchParamKey";

    // Dynamic Client parameters screen
    public static final String DYNAMIC_CLIENT_PARAMS_KEY_REF = "dynClientParamsKey";

    public static final String AVA_FILE_KEY_REF = "avaFileKey";
    public static final String AVA_ALLOCATION_KEY_REF = "avaAllocationKey";
    public static final String AVA_PAYMENT_KEY_REF = "avaPaymentKey";

    // Track Changes constants
    public static final String MAIN_PROP_REF = "Main";

    // 439073 ADD support for additional fields labels
    public static final BigDecimal ADDITIONAL_FIELDS = new BigDecimal("2");
    public static final BigDecimal SMART_FIELDS = new BigDecimal("3");
    public static final BigDecimal CUSTOM_FIELDS = BigDecimal.ONE;

    // SADS
    public static final String BRANCH_KEY_REF = "branchKey";
    public static final String USER_KEY_REF = "userKey";
    public static final String MESSAGES_KEY_REF = "messagesKey";
    public static final String COMPANY_KEY_REF = "companyKey";
    public static final String APPLICATION_KEY_REF = "applicationKey";
    public static final String OPT_KEY_REF = "optKey";
    public static final String SMART_OPTIONS_KEY_REF = "smartOptKey";
    public static final String ACCESS_PRIVILEGES_KEY_REF = "accessPrivilegesKey";
    public static final String CTRL_RECORD_KEY_REF = "ctrlRecordKey";
    public static final String DIVISION_KEY_REF = "divisionKey";
    public static final String DEPARTMENT_KEY_REF = "departmentKey";
    public static final String REGIONS_KEY_REF = "regionMaintainKey";
    public static final String UNITS_KEY_REF = "unitMaintainKey";
    public static final String CUSTOM_FIELDS_KEY = "customFieldsKey";
    public static final String USR_SHIFT_KEY = "userShiftKey";
    public static final String GROUP_SHIFT_KEY = "groupShiftKey";
    public static final String USER_CONTACT_KEY = "userContactList";
    public static final String EMPLOYEE_NEW_KEY = "employeeNewKey";
    public static final String COMPANYGROUP_KEY_REF = "companygroupKey";
    public static final String ARCHIVED_BUS_AREA_KEY_REF = "archiveBusAreaKey";
    public static final String TEMPLATE_MODEL_KEY_REF = "templateModelKey";
    public static final String AXS_PRIVILEGES_KEY_REF = "axsPrivilegesKeyRef";
    public static final String REMOVE_USR_AXS_KEY_REF = "removeUsrAxsKeyRef";
    public static final String USER_GROUP_KEY = "userGroupKey";
    public static final String AXS_BY_USR_KEY_REF = "axsByUsrKeyRef";
    // END SADS

    // IMCO
    public static final String IM_API_CHANNEL_KEY = "imApiChannelKey";
    public static final String IM_API_NEWAPI_KEY = "imApiNewapiKey";
    public static final String SYNC_ENTITY_KEY = "entityKey";
    public static final String SYNC_BRANCH_KEY = "sytmsetKey";
    public static final String IMCO_PWS_TMPLT_MASTER = "imcoPwsTmpltMasterKey";

    // Account Charges
    public static final String ACCOUNT_CHARGES_KEY_REF = "accountChargesKey";

    // Currency Exchange
    public static final String CURRENCY_EXCHANGE_KEY_REF = "currencyExchangeKey";

    // ALERT APP
    public static final String ALERT_CTRL_RECORD_KEY_REF = "alertControlRecordKey";
    public static final String ALERT_SUBSCRIBER_KEY_REF = "alertSubscriberKey";
    public static final String ALERT_GROUP_KEY_REF = "alertGroupKey";
    public static final String ALERT_EVENT_KEY_REF = "alertEventKey";
    public static final String ALERT_PACKAGE_KEY_REF = "alertPackageKey";
    public static final String REPORT_QUERY_KEY_REF = "reportQueryKey";
    public static final String ALERT_SUB_EVT_KEY_REF = "alertSubEvtKey";
    public static final String ALERT_SUB_PKG_KEY_REF = "alertSubPkgKey";
    public static final String ALERT_EVT_GRP_KEY_REF = "alertEvtGrpKey";
    public static final String ALERT_GRP_PKG_KEY_REF = "alertGrpPkgKey";
    public static final String ALERT_EMAIL_TEMPLATE_KEY_REF = "alertEmailTempKey";

    // CMS APP
    public static final String CMS_FORECAST_TYPES_KEY_REF = "cmsForecastTypesKey";
    public static final String CMS_FORECAST_SUB_TYPES_KEY_REF = "cmsForecastSubTypesKey";
    public static final String CMS_FORECAST_KEY_REF = "cmsForecastKey";
    public static final String CMS_MATCHING_FORECAST_KEY_REF = "cmsMatchingForecastKey";

    /** Customer segmentation **/
    public static final String CUST_SEGMENTATION_SCREEN_KEY_REF = "custSegmentationScreenKey";
    public static final String CTS_SEGMENT_GROUPING_KEY_REF = "custSegmentGroupingScreenKey";

    // IFRS
    public static final String IFRS_TEMPLATE_CREATION = "templateCreationKey";
    public static final String IFRS_POOL_FACTORS = "poolFactorsKey";
    public static final String IFRS_BUCKETING = "bucketingKey";
    public static final String IFRS_POOLING_HEADER = "poolingHeaderKey";
    public static final String IFRS_POOLING_DETAIL = "poolingDetailKey";
    public static final String IFRS_STATIC_POOL = "staticPoolKey";
    public static final String IFRS_BUCKETING_IIS_KEY = "bucketingIISKey";
    public static final String IFRS_BUCKETING_ASSETS_KEY = "bucketingASSETSKey";
    public static final String IFRS_CONTROL_RECORD_KEY = "ifrsControlRecordKey";
    public static final String IFRS_MAC_ECO_FAC_ADJ_KEY = "ifrsMacEcoFacAdjKey";
    public static final String IFRS_SCORING_HEADER_KEY = "ifrsScoringHeaderKey";
    public static final String IFRS_QFACTORS_KEY = "ifrsQFactorsKey";
    public static final String IFRS_LGD_KEY = "ifrsLGDKey";
    public static final String IFRS_HISTORICAL_PD = "ifrsHistoricalPDKey";

    public static final String IIS_IBOR_HDR_REF = "iborHdrKey";
    public static final String IIS_TRSITEMUNITCODE_REF = "trsItemUnitCodeKey";
    public static final String IIS_TRSLIBRARY_REF = "trsLibraryKey";
    public static final String CUST_KEY = "custKey";
    // Consolidation FCSR
    public static final String ENTITY_INF_KEY_REF = "entityInfKeyRef";
    public static final String TEMPLATE_ENTRY_KEY_REF = "templateEntryKeyRef";
    public static final String RELATION_DETAILS_KEY_REF = "relationDetailsKeyRef";
    public static final String GROUP_DEF_KEY_REF = "groupDefKey";
    public static final String COA_KEY_REF = "COAKey";
    public static final String UPLOAD_TRIAL_BAL_KEY_REF = "uploadTrialBalKey";
    public static final String MAPPING_KEY_REF = "MAPPINGKey";
    public static final String FCS_EXCH_RATE_REF = "fcsExchRateKey";
    // Transfer Accounts
    public static final String TRANSFER_ACCOUNTS = "transferAccountsKey";

    public static final String BASIC_SCREEN_KEY = "BasicScreenKey";
    public static final String TFS_GOODS_KEY = "TfsGoodsKey";

    public static final String DMS_SEARCH_INDEX_KEY_REF = "dmssearchindexkey";
    public static final String SERVICE_CHARGES_KEY = "serviceChargesKey";

    // Transfer Bill
    public static final String TRANSFER_BILL_KEY_REF = "transferBillKey";

    /// Internal Accounts
    public static final String INTERNAL_ACCOUNTS_KEY_REF = "internalAccountsTypeKey";

    // SWIFT Application
    public static final String SWIFT_REPORTS_KEY = "swiftReportsKey";

    /*
     * Operation type for audit on suspend
     */
    public static final String SUSPEND = "S";

    /*
     * Operation type for audit on closed
     */
    public static final String CLOSED = "C";
    /*
     * Operation type for audit on REINSTATE
     */
    public static final String REINSTATE = "RI";

    // SADS DMS
    public static final String DMS_USER_MAP_KEY = "dmsUserMapKey";
    public static final String DMS_AXS_MAP_KEY = "dmsAxsMapKey";

    // 592296 Operation type for audit on retrieving 360 DASHBOARD
    public static final String DASHBOARD_360 = "dashboard360Key";

    // OADM
    public static final String NEWS_FEED_DETAILS_KEY_REF = "newsFeedDetailsKey";
    public static final String NEWS_FEED_TYPE_KEY_REF = "newsFeedTypeKey";
    public static final String AD_BANNER_KEY_REF = "adBannerDetailsKey";
    public static final String AD_BANNER_TYPE_KEY_REF = "adBannerTypeKey";
    public static final String BUSINESS_PROFILE_KEY_REF = "businessProfileKey";
    public static final String SECURITY_QUESTION_REF = "securityQuestionKey";
    public static final String ADMIN_PARAMTER_KEY = "adminParamterKey";
    public static final String CORPORATE_USER_ROLES_KEY_REF = "corporateUserRolesKey";
    public static final String LIST_MODES_KEY_REF = "listModesKey";
    public static final String CORPORATE_USERS_REF = "corporateUserKey";
    public static final String CORPORATE_CUSTOMER_KEY_REF = "corporateCustomerKey";
    public static final String SLIDER_IMAGES_KEY_REF = "sliderImagesKey";
    public static final String CREATE_USER_KEY_REF = "omniUserKey";
    public static final String ATM_BRANCHES_KEY_REF = "atmBranchesKey";
    public static final String TRX_TYPE_COUNTRY_REF = "trxtypeCountrykey";
    public static final String TRAINING_KEY_REF = "trainingKey";
    public static final String LOST_REASONS_REF = "lostReasonsKey";
    public static final String MESSAGE_TYPE_KEY_REF = "messageTypeKey";
    public static final String AUTHENTICATION_MATRIX_REF = "authenticationMatrixKey";
    public static final String OPER_APP_CHNL_REF = "operAppChnlKey";
    public static final String MESSAGE_KEY_REF = "createMessageKey";
    public static final String LIMITS_MGNT_KEY_REF = "limitsMgntKey";
    public static final String CHARGES_MNGMT_REF = "chargesMgmntKey";
    public static final String OMNI_CUSTOMER_REF = "omniCustomerKey";
    public static final String CUSTOMER_GROUP_REF = "customerGroupKey";
    public static final String CUSTOMER_MATRIX_REF = "customerMatrixKey";
    public static final String OPERATION_EVENTS_KEY_REF = "omniOperationEventsKey";
    public static final String CIF_ADDRESS_REF = "cifAddressKey";
    public static final String CIF_OCCUPATION_REF = "cifOccupationKey";
    public static final String BANNERS_REF = "bannersKey";
    public static final String OC_BENEFICIARIES_REF = "beneficiariesKey";
    public static final String CIF_GROUPS_KEY_REF = "cifGroupsKey";
    public static final String OC_CATEGORIES_REF = "ocCategoriesKey";
    public static final String PRODUCT_AND_SERVICE_REF = "productAndServiceKey";
    public static final String OC_TRANSACTION_TYPE_NAMES_REF = "ocTransactionTypeNameskey";
    public static final String TRANSACTION_LIMIT_APPROVAL_REF = "transactionLimitApprovalKey";
    public static final String CHANNEL_COMMON_REF = "channelCommonKey";
    // 801694
    public static final String SWIFT_MSG_REF = "swiftMsg_msg_code";
    // Clubbed Accounts
    public static final String CLUBBED_ACCOUNT_KEY_REF = "clubbedAccountKey";

    // PROF
    public static final String PROF_DRIVER_KEYREF = "profDriverKeyRef";
    public static final String PROF_ASSETLIAB_KEY = "profAssetLiabKeyRef";
    public static final String PROF_WEIGHT_ENTITY_KEY = "profWeightEntityKey";

    // RCSA
    public static final String RCSA_FOLDER_MGMT_KEYREF = "rcsaFoldMgmtKeyRef";
    public static final String RCSA_RATING_CTRL_KEYREF = "rcsaRatingCtrlKeyRef";
    public static final String RCSA_FIN_NONFIN_IMPACT_KEYREF = "rcsaFinNonFinImpactKeyRef";
    public static final String RCSA_FIN_IMPACT_KEYREF = "rcsaFinImpactKeyRef";
    public static final String RCSA_EVENT_TYPE_CATEG_REF = "rcsaEventTypeCategKeyRef";
    public static final String RCSA_RISK_IDENTIFICATION_KEYREF = "rcsaRiskIdentificationKeyRef";
    public static final String RCSA_CONTROL_IDENTIFICATION_KEYREF = "rcsaCtrlIdentificationKeyRef";
    public static final String RCSA_RISK_ASSESSMENT_KEYREF = "rcsaRiskAssessmenTKeyRef";
    public static final String RCSA_CONTROL_PLAN_KEYREF = "rcsaCtrlPlanKeyRef";
    public static final String RCSA_ACTION_IDENTIFICATION_KEYREF = "rcsaActionIdentificationKeyRef";
    public static final String RCSA_ALERT_ASSIGNMENT_KEYREF = "rcsaAlertAssignmentKeyRef";

    // ATM
    public static final String atmAcquirerKey = "atmAcquirerKey";
    public static final String atmTerminalKey = "atmTerminalKey";

    // AML
    // added by nour for #885556
    public static final String BLACK_LIST_SOURCE_KEY_REF = "blackListSourceKey";
    // added by nour for #885556
    public static final String BLACK_LIST_TYPE_KEY_REF = "blackListTypeKey";

    // Rania - ZKI190014
    public static final String STATEMENT_OF_ACCOUNT_KEY = "statementOfAccountKey";

    public static final String atmISOMessagesDefinitionKey = "atmISOMessagesDefinitionKey";

    // added by nour for 864398 : audit for criteria screen
    public static final String CRITERIA_KEY = "criteriaKey";

    // added by nour for 925559
    public static final String KYC_MANAGEMENT_KEY = "kycManagementKey";

    // added by Zahid indher
    public static final String ALRT_MGNT_KEY_REF = "alertManagementKey";

    public static final String LAW_ENF_AGENCY_KEY = "lawEnfAgencyKey";
    public static final String CASE_SUB_TYPE_KEY = "caseSubTypeKey";
    public static final String AML_ENG_PAR_CTRL_RECRD_KEY = "amlEngParCtrlRcrdKey";
    public static final String CASE_TYPE_KEY_REF = "caseTypeKey";
    
    //Added By Bilal for BUG#1063795
    public static final String CONTROL_RECORD_ENGINE_KEY_REF = "controlRecordEngineKey";
	
	//Audit QUALITY Screen
    public static final String QUALITY_KEY_REF = "qualityKey";// Audit quality Screen
    
    // Audit PAY MAODE Screen
    public static final String PAY_MODE_KEY_REF = "payModeKey";// Audit payment mode Screen

    public static final String CUSTODIAN_KEY_REF = "custodianKey";// Audit CUSTODIAN Screen
    
	public static final String DEALER_KEY_REF = "dealerKey";// Audit DEALER Screen
	
	public static final String PAYING_AGENT_KEY_REF = "payingAgentKey"; // Audit paying agent Screen
	
	public static final String PMSCHARGERS_KEY_REF = "pmschargesKey"; // Audit CHARGES Screen
	
	public static final String CASH_TYPE_KEY_REF = "cashtypeKey"; // Audit cash type Screen
	
    public static final String BROKER_KEY_REF = "brokerKey"; // Audit BROKER Screen
    
    public static final String DEPOSITORY_AGENT_KEY_REF = "depositoryAgentKey"; // Audit DEPOSITORY AGENT Screen
    
    public static final String FEES_TRENCHES_KEY_REF = "feesTrenchesKey"; // Audit FEES TRENCHES Screen

  //#756390 - GAB180087 - Hala Al Sheikh
    public static final String SIGNATURE_KEY_REF = "signatureKey"; 
    
    public static final String EXCH_OFFIICE_AUTH_KEY = "exchOfficeAuthKey";
    
    public static final String ISSUANCE_LIMIT_KEY_REF = "issuancelimitKey";//Audit ISSUANCE LIMIT Screen
       
    public static final String GL_TYPE_TEMPLATE_KEY_REF = "gltypetemplateKey"; // Audit GL TYPE TEMPLATE Screen
                   
    public static final String COLLATERAL_TYPES_KEY_REF = "collateralTypesKey"; // Audit COLLATERAL TYPES Screen        		

    public static final String SECURITY_CLASS_KEY_REF="securityClasskey";//audit for security class screen

    public static final String STOCK_EXCHANGE_KEY_REF="stockexchangekey";//audit for stock exchange screen

    public static final String FUND_YIELD_KEY_REF="fundYieldkey";//audit for fund yield screen

    public static final String  INDEX_MANAGEMENT_KEY_REF="indexmanagementkey";//audit index screen
    

    public static final String TRANSFER_ENTITY_KEY_REF = "transferEntitykey";
    public static final String INDEX_POINTS_KEY_REF = "indexPointsKey";//audit maintain index points
    
    public static final String COUNTER_PARTY_LIMIT="counterPartyLimitkey";// Audit counter party Screen            
    
    // Credit Evaluation
    public static final String CREDIT_EVALUATION_KEY_REF = "creditEvaluationtKey";

    // Jishnu PG ; BAJI200020 ; 18/08/2020
    public static final String IIS_ASSET_MANAGEMENT_KEY_REF = "assetManagementKey";

    public static final String COUNTER_PARTY_SECURITY_TYPE_LIMIT="counterPartySecurityTypekey";//audit cunter party security type limit screen

    public static final String INTRA_DAY_LIMIT_KEY_REF = "intraDayLimitkey";//audit for intraday limit screen
    
    public static final String IIS_SETTLEMENT_POLICY_KEY_REF = "settlementPolicykey"; // Parameter Settlement Policy Screen
   
}


