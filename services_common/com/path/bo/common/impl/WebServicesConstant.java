package com.path.bo.common.impl;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * Copyright 2019, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: Mohammad Mezzawi
 *
 * WebServicesConstant.java used to specify the mapping of the WEservices to their corresponding BO wrappers
 * needed to dynamically call a method from any webservice and to map the parameters dynamically
 */
public class WebServicesConstant 
{

	public static final Map<String, String> wsServiceBOMap = new HashMap<String, String>();
	public static final Map<String, String> wsOperationsMethodMap = new HashMap<String, String>();
	
	
	/**
	 * Arg status enumeration
	 */
	public enum ARG_STATUS {
		
		IN("I"), OUT("O"), INOUT("B");

		/**
		 * 
		 */
		private final String code;

		/**
		 * Return Enumeration Code
		 * @return
		 */
		public String getCode() {
			return code;
		}

		/**
		 * @param code
		 */
		private ARG_STATUS(final String code) {
			this.code = code;
		}

		/**
		 * Check if given value is equal to enum code
		 * @param value
		 * @return
		 */
		public boolean equals(final String value) {
			return code.equalsIgnoreCase(value);
		}

		
		/**
		 * Return Api Enumeration by code
		 * @param code
		 * @return
		 */
		public static ARG_STATUS getEnumByCode(String code) {
			for (ARG_STATUS e : ARG_STATUS.values()) {
				if (e.code.equals(code)) {
					return e;
				}
			}
			return null;
		}

	}

	
	/**
	 * Api Type Enumration
	 */
	public enum API_TYPE {
		
		PROCEDURE("P"), WEBSERVICE_SOAP("W"), 
		WEBSERVICE_REST("R"), BO_SERVICE("S"),
		RMI_BO_SERVICE("M"),REPORT("I"),
		CLIENT_SCRIPT("C"), SCREEN("D");
		
		/**
		 * Enumeration code
		 */
		private final String code;

		private API_TYPE(final String code) {
			this.code = code;
		}
		
		/**
		 * Check if given value is equal to enum code
		 * @param value
		 * @return
		 */
		public boolean equals(final String value) {
			return code.equalsIgnoreCase(value);
		}

		/**
		 * Return Api Enumeration by code
		 * @param code
		 * @return
		 */
		public static API_TYPE getEnumByCode(String code) {
			for (API_TYPE e : API_TYPE.values()) {
				if (e.code.equals(code)) {
					return e;
				}
			}
			return null;
		}

	}
	
	
	/**
	 * Data type enumeration
	 */
	public enum DATA_TYPE {
		JSON("application/json"), XML("application/xml");

		private final String code;

		private DATA_TYPE(final String code) {
			this.code = code;
		}

		public boolean equals(final String value) {
			return code.equalsIgnoreCase(value);
		}
	}
	
	
	static 
	{
		//ALERT ENGINE
		wsServiceBOMap.put("notificationWs", "alertEngWebServiceWrapperBO");
		wsOperationsMethodMap.put("notificationWs.sendAlertNotification", "sendAlertNotification");
		
		wsServiceBOMap.put("SMSServiceWsBO", "smsServiceWsBO");
		wsOperationsMethodMap.put("SMSServiceWsBO.sendSMS", "sendSMS");
		//Alert subscriber
		wsServiceBOMap.put("subscriberWs", "alertWebServiceWrapperBO");
		wsOperationsMethodMap.put("subscriberWs.createSubscriber", "createSubscriber");
		wsOperationsMethodMap.put("subscriberWs.updateSubscriber", "updateSubscriber");
		wsOperationsMethodMap.put("subscriberWs.approveSubscriber", "approveSubscriber");
		wsOperationsMethodMap.put("subscriberWs.returnSubscriberDetails", "returnSubscriberDetails");
		
		//Alert subscription
		wsServiceBOMap.put("subscriptionWs", "alertWebServiceWrapperBO");
		wsOperationsMethodMap.put("subscriptionWs.createSingleSubscriptionSE", "createSingleSubscribtionSE");
		wsOperationsMethodMap.put("subscriptionWs.createSingleSubscriptionSP", "createSingleSubscribtionSP");
		wsOperationsMethodMap.put("subscriptionWs.createSingleSubscriptionGP", "createSingleSubscribtionGP");
		wsOperationsMethodMap.put("subscriptionWs.createSingleSubscriptionGE", "createSingleSubscribtionGE");
		wsOperationsMethodMap.put("subscriptionWs.approveSingleSubscription", "approveSingleSubscribtion");
		wsOperationsMethodMap.put("subscriptionWs.deleteSingleSubscription", "deleteSingleSubscribtion");
		wsOperationsMethodMap.put("subscriptionWs.suspendSingleSubscription", "suspandSingleSubscribtion");
		wsOperationsMethodMap.put("subscriptionWs.reactiveSingleSubscription", "reactiveSingleSubscribtion");
		wsOperationsMethodMap.put("subscriptionWs.approveDeleteSingleSubscription", "approveDeletedSingleSubscribtion");
		wsOperationsMethodMap.put("subscriptionWs.rejectDeleteSingleSubscription", "rejectDeletedSingleSubscription");
		wsOperationsMethodMap.put("subscriptionWs.returnSubscriptionList", "returnSubscriptionList");

		//Alert Event
        wsServiceBOMap.put("eventWs", "alertWebServiceWrapperBO");
        wsOperationsMethodMap.put("eventWs.returnEventDetails", "returnEventDetails");

		
		
		//Alert Custom Integration SMS
		wsServiceBOMap.put("alertCustomWs", "alertCustomBO");
		wsOperationsMethodMap.put("alertCustomWs.processAlertSms", "processAlertSms");
		
		//ASSETS
		wsServiceBOMap.put("fundSubscriptionWs", "assetsWebServiceWrapperBO");
		wsOperationsMethodMap.put("fundSubscriptionWs.deleteSubscription", "deleteSubscription");
		wsOperationsMethodMap.put("fundSubscriptionWs.approveSubscription", "approveSubscription");
		wsOperationsMethodMap.put("fundSubscriptionWs.reverseSubscription", "reverseSubscription");
		wsOperationsMethodMap.put("fundSubscriptionWs.returnSubscriptionList", "returnSubscriptionList");
		wsOperationsMethodMap.put("fundSubscriptionWs.returnSubscriptionDetails", "returnSubscriptionDetails");
		
		wsServiceBOMap.put("fundRedemptionWs", "assetsWebServiceWrapperBO");
		wsOperationsMethodMap.put("fundRedemptionWs.deleteRedemption", "deleteRedemption");
		wsOperationsMethodMap.put("fundRedemptionWs.approveRedemption", "approveRedemption");
		wsOperationsMethodMap.put("fundRedemptionWs.reverseRedemption", "reverseRedemption");
		wsOperationsMethodMap.put("fundRedemptionWs.returnRedemptionList", "returnRedemptionList");
		wsOperationsMethodMap.put("fundRedemptionWs.returnRedemptionDetails", "returnRedemptionDetails");
		
		wsServiceBOMap.put("paymentInstructionWs", "assetsWebServiceWrapperBO");
		wsOperationsMethodMap.put("paymentInstructionWs.deletePaymentInstruction", "deleteSettlement");
		wsOperationsMethodMap.put("paymentInstructionWs.approvePaymentInstruction", "approveSettlement");
		wsOperationsMethodMap.put("paymentInstructionWs.reversePaymentInstruction", "reverseSettlement");
		wsOperationsMethodMap.put("paymentInstructionWs.createPaymentInstruction", "createSettlement");
		wsOperationsMethodMap.put("paymentInstructionWs.updatePaymentInstruction", "updateSettlement");
		wsOperationsMethodMap.put("paymentInstructionWs.returnPaymentInstructionDetails", "returnSettlementDetails");
		wsOperationsMethodMap.put("paymentInstructionWs.returnPaymentInstructionList", "returnSettlementList");
		
		wsServiceBOMap.put("receivingInstructionWs", "assetsWebServiceWrapperBO");
		wsOperationsMethodMap.put("receivingInstructionWs.deleteReceivingInstruction", "deleteSettlement");
		wsOperationsMethodMap.put("receivingInstructionWs.approveReceivingInstruction", "approveSettlement");
		wsOperationsMethodMap.put("receivingInstructionWs.reverseReceivingInstruction", "reverseSettlement");
		wsOperationsMethodMap.put("receivingInstructionWs.createReceivingInstruction", "createSettlement");
		wsOperationsMethodMap.put("receivingInstructionWs.updateReceivingInstruction", "updateSettlement");
		wsOperationsMethodMap.put("receivingInstructionWs.returnReceivingInstructDetails", "returnSettlementDetails");
		wsOperationsMethodMap.put("receivingInstructionWs.returnReceivingInstructionList", "returnSettlementList");

		
		wsServiceBOMap.put("securityPurchaseWs", "assetsWebServiceWrapperBO");
		wsOperationsMethodMap.put("securityPurchaseWs.createSecurityTradesPurchase", "createTrade");
		wsOperationsMethodMap.put("securityPurchaseWs.deleteSecurityTradesPurchase", "deleteTrade");
		wsOperationsMethodMap.put("securityPurchaseWs.verifySecurityTradesPurchase", "verifyTrade");
		wsOperationsMethodMap.put("securityPurchaseWs.rejectSecurityTradesPurchase", "rejectTrade");
		wsOperationsMethodMap.put("securityPurchaseWs.approveSecurityTradesPurchase", "approveTrade");
		wsOperationsMethodMap.put("securityPurchaseWs.reverseSecurityTradesPurchase", "reverseTrade");
		wsOperationsMethodMap.put("securityPurchaseWs.approveRevSecTradesPurchase", "approveReverseTrade");
		wsOperationsMethodMap.put("securityPurchaseWs.returnSecurityTrdPurchasesList", "returnSecurityTrdPurchasesList");
		wsOperationsMethodMap.put("securityPurchaseWs.returnSecTrdPurchaseDetails", "returnSecTrdPurchaseDetails");
		
		wsServiceBOMap.put("securitySaleWs", "assetsWebServiceWrapperBO");
		wsOperationsMethodMap.put("securitySaleWs.createSecurityTradesSale", "createSaleTrade");
		wsOperationsMethodMap.put("securitySaleWs.deleteSecurityTradesSale", "deleteSaleTrade");
		wsOperationsMethodMap.put("securitySaleWs.verifySecurityTradesSale", "verifySaleTrade");
		wsOperationsMethodMap.put("securitySaleWs.rejectSecurityTradesSale", "rejectSaleTrade");
		wsOperationsMethodMap.put("securitySaleWs.approveSecurityTradesSale", "approveSaleTrade");
		wsOperationsMethodMap.put("securitySaleWs.reverseSecurityTradesSale", "reverseSaleTrade");
		wsOperationsMethodMap.put("securitySaleWs.approveReverseSecurityTradesSale", "approveReverseSaleTrade");
		wsOperationsMethodMap.put("securitySaleWs.returnSecurityTradesSaleList", "returnSecurityTrdSaleList");
		wsOperationsMethodMap.put("securitySaleWs.returnSecurityTradesSaleDetails", "returnSecTrdSaleDetails");
		
		wsServiceBOMap.put("requestsWs", "assetsWebServiceWrapperBO");
		wsOperationsMethodMap.put("requestsWs.createIPORequest", "createIPORequest");
		wsOperationsMethodMap.put("requestsWs.returnIPORequestList", "returnRequestIPOList");
		wsOperationsMethodMap.put("requestsWs.returnIPORequestDetailedList", "returnDetailedRequestIPOList");
		wsOperationsMethodMap.put("requestsWs.returnIPORequestDetails", "returnIPORequestDetails");
		wsOperationsMethodMap.put("requestsWs.updateIPORequest", "updateIPORequest");
		wsOperationsMethodMap.put("requestsWs.deleteIPORequest", "deleteIPORequest");
		
		wsServiceBOMap.put("certificateIssuanceWs", "assetsWebServiceWrapperBO");
		wsOperationsMethodMap.put("certificateIssuanceWs.generateIssue", "generateIssue");
		wsOperationsMethodMap.put("certificateIssuanceWs.approveIssue", "approveIssue");
		wsOperationsMethodMap.put("certificateIssuanceWs.deleteIssue", "deleteIssue");
		wsOperationsMethodMap.put("certificateIssuanceWs.submitIssue", "submitIssue");
		wsOperationsMethodMap.put("certificateIssuanceWs.cancelIssue", "cancelIssue");
		wsOperationsMethodMap.put("certificateIssuanceWs.returnIssueDetails", "returnIssueDetails");
		wsOperationsMethodMap.put("certificateIssuanceWs.returnIssueList", "returnIssueList");
		
		wsServiceBOMap.put("securityPriceWs", "assetsWebServiceWrapperBO");
		wsOperationsMethodMap.put("securityPriceWs.returnSecMarketPriceDetails", "returnSecurityMarketPriceDetails");
		wsOperationsMethodMap.put("securityPriceWs.uploadSecMarketPrice", "uploadSecurityMarketPrice");
		wsOperationsMethodMap.put("securityPriceWs.returnSecMarketPriceChanges", "returnSecMarketPriceChanges");
		
		wsServiceBOMap.put("portfolioManagementWs", "assetsWebServiceWrapperBO");
		wsOperationsMethodMap.put("portfolioManagementWs.returnPfStatementOfTrxList", "getPfStatementOfTrx");
		wsOperationsMethodMap.put("portfolioManagementWs.returnPortfolioPosition", "getPortfolioPositionDetails");
		wsOperationsMethodMap.put("portfolioManagementWs.returnPortfolioList", "returnPortfolioList");
		wsOperationsMethodMap.put("portfolioManagementWs.returnSecuritiesTrxList", "returnSecuritiesTrxList");
		

		wsServiceBOMap.put("holdingRenewalRequestWs", "assetsWebServiceWrapperBO");
		wsOperationsMethodMap.put("holdingRenewalRequestWs.createHoldingRenewalRequest", "createRenewalRequest");
		wsOperationsMethodMap.put("holdingRenewalRequestWs.returnHoldingRenewalReqDetails", "returnRenewalRequestDetails");
		wsOperationsMethodMap.put("holdingRenewalRequestWs.returnHoldingRenewalRequestList", "returnRenewalRequestList");
		wsOperationsMethodMap.put("holdingRenewalRequestWs.deleteHoldingRenewalRequest","deleteHoldingRenewalRequest");
		wsOperationsMethodMap.put("holdingRenewalRequestWs.updateHoldingRenewalRequest","updateHoldingRenewalRequest");

		wsServiceBOMap.put("iPOWs", "assetsWebServiceWrapperBO");
		wsOperationsMethodMap.put("iPOWs.returnIPOList", "returnIPOList");

		wsServiceBOMap.put("renewalWs", "assetsWebServiceWrapperBO");
		wsOperationsMethodMap.put("renewalWs.returnHoldingRenewalList", "returnHoldingRenewalList");

		wsServiceBOMap.put("sukukWs", "assetsWebServiceWrapperBO");
		wsOperationsMethodMap.put("sukukWs.returnSukukList", "returnSukukList");

		wsServiceBOMap.put("expiryWs", "assetsWebServiceWrapperBO");
		wsOperationsMethodMap.put("expiryWs.returnExpiredHoldingList", "returnExpiredHoldingList");

		wsServiceBOMap.put("sharesWs", "assetsWebServiceWrapperBO");
		wsOperationsMethodMap.put("sharesWs.returnSharesList", "returnSharesList");

		wsServiceBOMap.put("fundWs", "assetsWebServiceWrapperBO");
		wsOperationsMethodMap.put("fundWs.returnFundInformationFileList", "returnFundInfoFileList");

		
		
		
		//***********************************CSM********************************************//
		wsServiceBOMap.put("additionalFieldsWs", "retWebServiceWrapperPWSBO");
		wsOperationsMethodMap.put("additionalFieldsWs.addAdditionalFields" , "addAdditionalFields");
		
		wsServiceBOMap.put("cardsManagementWs", "retWebServiceWrapperPWSBO");
		wsOperationsMethodMap.put("cardsManagementWs.returnCardsList" , "returnCardsList");
		wsOperationsMethodMap.put("cardsManagementWs.returnCardDetails" , "returnCardDetails");
		wsOperationsMethodMap.put("cardsManagementWs.createCreditCard" , "createCreditCard");
		wsOperationsMethodMap.put("cardsManagementWs.createDebitCard" , "createDebitCard");
		wsOperationsMethodMap.put("cardsManagementWs.createPrepaidCard" , "createPrepaidCard");
		wsOperationsMethodMap.put("cardsManagementWs.updateCreditCard" , "updateCreditCard");
		wsOperationsMethodMap.put("cardsManagementWs.updateDebitCard" , "updateDebitCard");
		wsOperationsMethodMap.put("cardsManagementWs.updatePrepaidCard" , "updatePrepaidCard");
		wsOperationsMethodMap.put("cardsManagementWs.deleteCard" , "deleteCard");
		wsOperationsMethodMap.put("cardsManagementWs.applyCard" , "applyCard");
		wsOperationsMethodMap.put("cardsManagementWs.approveCard" , "approveCard");
		wsOperationsMethodMap.put("cardsManagementWs.issueCard" , "issueCard");
		wsOperationsMethodMap.put("cardsManagementWs.activateCard" , "activateCard");
		wsOperationsMethodMap.put("cardsManagementWs.cancelCard" , "cancelCard");
		wsOperationsMethodMap.put("cardsManagementWs.blockCard" , "blockCard");
		wsOperationsMethodMap.put("cardsManagementWs.amendCreditCard" , "amendCreditCard");
		wsOperationsMethodMap.put("cardsManagementWs.amendDebitCard" , "amendDebitCard");
		wsOperationsMethodMap.put("cardsManagementWs.amendPrepaidCard" , "amendPrepaidCard");
		wsOperationsMethodMap.put("cardsManagementWs.deactivateCard" , "deactivateCard");
		wsOperationsMethodMap.put("cardsManagementWs.renewCard" , "renewCard");
		
		wsServiceBOMap.put("chequeBookWs", "retWebServiceWrapperPWSBO");
		wsOperationsMethodMap.put("chequeBookWs.returnChequeBookChequesDetails" , "returnChequeBookChequesDetails");
		wsOperationsMethodMap.put("chequeBookWs.createChequebook" , "createUpdateChequebook");
		wsOperationsMethodMap.put("chequeBookWs.updateChequebook" , "createUpdateChequebook");
		wsOperationsMethodMap.put("chequeBookWs.returnChequebookListDetails" , "returnChequebookListDetails");
		wsOperationsMethodMap.put("chequeBookWs.approveChequebook" , "approveChequebook");
		wsOperationsMethodMap.put("chequeBookWs.deleteChequebook" , "deleteChequebook");
		wsOperationsMethodMap.put("chequeBookWs.returnChequebookList" , "returnChequebookList");
		
		wsServiceBOMap.put("cifManagementWs", "retWebServiceWrapperPWSBO");
		wsOperationsMethodMap.put("cifManagementWs.reinstateCif" , "reinstateCif");
		wsOperationsMethodMap.put("cifManagementWs.updateRetailCif" , "createUpdateCIf");
		wsOperationsMethodMap.put("cifManagementWs.createRetailCif" , "createUpdateCIf");
		wsOperationsMethodMap.put("cifManagementWs.approveCif" , "approveCif");
		wsOperationsMethodMap.put("cifManagementWs.returnCifList" , "retrieveCifList");
		wsOperationsMethodMap.put("cifManagementWs.returnCifDetails" , "retrieveCifDetails");
		wsOperationsMethodMap.put("cifManagementWs.updateCorporateCif" , "createUpdateCIf");
		wsOperationsMethodMap.put("cifManagementWs.deleteCif" , "deleteCif");
		wsOperationsMethodMap.put("cifManagementWs.createCorporateCif" , "createUpdateCIf");
		wsOperationsMethodMap.put("cifManagementWs.suspendCif" , "suspendCif");
		wsOperationsMethodMap.put("cifManagementWs.returnBillersList" , "returnBillersList");
		
		wsServiceBOMap.put("currencyExchangeWs", "retWebServiceWrapperPWSBO");
		wsOperationsMethodMap.put("currencyExchangeWs.returnExchangeRate" , "returnExchangeRateList");
		
		wsServiceBOMap.put("fixedMaturityAccountsWs", "retWebServiceWrapperPWSBO");
		wsOperationsMethodMap.put("fixedMaturityAccountsWs.returnFMAAccountDetails" , "returnFMAAccountDetails");
		wsOperationsMethodMap.put("fixedMaturityAccountsWs.createFixedMaturityAccount" , "createFixedMaturityAccount");
		wsOperationsMethodMap.put("fixedMaturityAccountsWs.returnFixedMaturityAccountList" , "returnFixedMaturityAccountList");
		wsOperationsMethodMap.put("fixedMaturityAccountsWs.closeFixedMaturityAccount" , "closeFixedMaturityAccount");
		wsOperationsMethodMap.put("fixedMaturityAccountsWs.authorizeCloseFMAAccount" , "authorizeCloseFMAAccount");
		wsOperationsMethodMap.put("fixedMaturityAccountsWs.updateFixedMaturityAccount" , "updateFixedMaturityAccount");
		wsOperationsMethodMap.put("fixedMaturityAccountsWs.deleteFixedMaturityAccount" , "deleteFixedMaturityAccount");
		wsOperationsMethodMap.put("fixedMaturityAccountsWs.authorizeFixedMaturityAccount" , "authorizeFixedMaturityAccount");
		
		wsServiceBOMap.put("generalAccountsWs", "retWebServiceWrapperPWSBO");
		wsOperationsMethodMap.put("generalAccountsWs.createGeneralAccount" , "createGeneralAccount");
		wsOperationsMethodMap.put("generalAccountsWs.updateGeneralAccount" , "updateGeneralAccount");
		wsOperationsMethodMap.put("generalAccountsWs.authorizeCloseGeneralAccount" , "authorizeCloseGeneralAccount");
		wsOperationsMethodMap.put("generalAccountsWs.returnGeneralAccountsList" , "returnGeneralAccountsList");
		wsOperationsMethodMap.put("generalAccountsWs.closeGeneralAccount" , "closeGeneralAccount");
		wsOperationsMethodMap.put("generalAccountsWs.authorizeGeneralAccount" , "authorizeGeneralAccount");
		wsOperationsMethodMap.put("generalAccountsWs.returnGeneralAccountDetails" , "returnGeneralAccountDetails");
		wsOperationsMethodMap.put("generalAccountsWs.deleteGeneralAccount" , "deleteGeneralAccount");
		
		wsServiceBOMap.put("journalVoucharWs", "retWebServiceWrapperPWSBO");
		wsOperationsMethodMap.put("journalVoucharWs.createJvTicket" , "createJvTicket");
		
		wsServiceBOMap.put("lostfoundmanagementWs", "retWebServiceWrapperPWSBO");
		wsOperationsMethodMap.put("lostfoundmanagementWs.updateLostFoundPassbook" , "updateLostFoundPassbook");
		wsOperationsMethodMap.put("lostfoundmanagementWs.returnLostAndFoundDetails" , "returnLostAndFoundDetails");
		wsOperationsMethodMap.put("lostfoundmanagementWs.approveCancelLostFound" , "approveCancelLostFound");
		wsOperationsMethodMap.put("lostfoundmanagementWs.updateLostFoundCard" , "updateLostFoundCard");
		wsOperationsMethodMap.put("lostfoundmanagementWs.updateLostFoundCheque" , "updateLostFoundCheque");
		wsOperationsMethodMap.put("lostfoundmanagementWs.createLostFoundChequebook" , "createLostFoundChequebook");
		wsOperationsMethodMap.put("lostfoundmanagementWs.approveLostFound" , "approveLostFound");
		wsOperationsMethodMap.put("lostfoundmanagementWs.updateLostFoundRemittance" , "updateLostFoundRemittance");
		wsOperationsMethodMap.put("lostfoundmanagementWs.createLostFoundRemittance" , "createLostFoundRemittance");
		wsOperationsMethodMap.put("lostfoundmanagementWs.createLostFoundCheque" , "createLostFoundCheque");
		wsOperationsMethodMap.put("lostfoundmanagementWs.updateLostFoundChequebook" , "updateLostFoundChequebook");
		wsOperationsMethodMap.put("lostfoundmanagementWs.createLostFoundPassbook" , "createLostFoundPassbook");
		wsOperationsMethodMap.put("lostfoundmanagementWs.deleteLostFound" , "deleteLostFound");
		wsOperationsMethodMap.put("lostfoundmanagementWs.createLostFoundOutwardCheque" , "createLostFoundOutwardCheque");
		wsOperationsMethodMap.put("lostfoundmanagementWs.cancelLostFound" , "cancelLostFound");
		wsOperationsMethodMap.put("lostfoundmanagementWs.createLostFoundCard" , "createLostFoundCard");
		wsOperationsMethodMap.put("lostfoundmanagementWs.updateLostFoundOutwardCheque" , "updateLostFoundOutwardCheque");
		wsOperationsMethodMap.put("lostfoundmanagementWs.returnLostAndFoundList" , "returnLostAndFoundList");
		
		wsServiceBOMap.put("memoWs", "retWebServiceWrapperPWSBO");
		wsOperationsMethodMap.put("memoWs.returnMemoList" , "returnMemoList");
		wsOperationsMethodMap.put("memoWs.returnMemoDetails" , "returnMemoDetails");
		wsOperationsMethodMap.put("memoWs.createMemo" , "createMemo");
		wsOperationsMethodMap.put("memoWs.updateMemo" , "updateMemo");
		
		wsServiceBOMap.put("signatureWs", "retWebServiceWrapperPWSBO");
		wsOperationsMethodMap.put("signatureWs.createSignature" , "createSignature");
		wsOperationsMethodMap.put("signatureWs.updateSignature" , "createSignature");
		
		wsServiceBOMap.put("smsSubscriptionWs", "retWebServiceWrapperPWSBO");
		wsOperationsMethodMap.put("smsSubscriptionWs.returnSmsSubscriptionList" , "returnSmsSubscriptionList");
		wsOperationsMethodMap.put("smsSubscriptionWs.suspendSmsSubscription" , "suspendSmsSubscription");
		wsOperationsMethodMap.put("smsSubscriptionWs.approveSmsSubscription" , "approveSmsSubscription");
		wsOperationsMethodMap.put("smsSubscriptionWs.returnSmsSubscriptionDetails" , "returnSmsSubscriptionDetails");
		wsOperationsMethodMap.put("smsSubscriptionWs.reactivateSmsSubscription" , "reactivateSmsSubscription");
		
		wsServiceBOMap.put("transactionsManagementWs", "retWebServiceWrapperPWSBO");
		wsOperationsMethodMap.put("transactionsManagementWs.returnTransactionsList" , "returnTransactionsList");
		wsOperationsMethodMap.put("transactionsManagementWs.returnTransactionsDetails" , "returnTransactionDetails");
		wsOperationsMethodMap.put("transactionsManagementWs.returnStandingOrdersList" , "returnStandingOrdersList");
		wsOperationsMethodMap.put("transactionsManagementWs.deleteTransaction" , "deleteTransaction");
		wsOperationsMethodMap.put("transactionsManagementWs.returnChargesDetails" , "returnChargesDetails");
		wsOperationsMethodMap.put("transactionsManagementWs.returnValueDate" , "returnValueDate");
		wsOperationsMethodMap.put("transactionsManagementWs.calculateAmountCurrency" , "calculateAmountCurrency");
		
		wsServiceBOMap.put("tellerWs", "retWebServiceWrapperPWSBO");
		wsOperationsMethodMap.put("tellerWs.returnTellersList" , "returnTellersList");
		wsOperationsMethodMap.put("tellerWs.returnTellerDetails" , "returnTellerDetails");
		
		wsServiceBOMap.put("transferWs", "retCriticalWebServiceWrapperBO");
		wsOperationsMethodMap.put("transferWs.createTransfer" , "createTransfer");
		wsOperationsMethodMap.put("transferWs.createBillPayment" , "createBillPayment");
		wsOperationsMethodMap.put("transferWs.createSwiftTransfer" , "createSwiftTransfer");
		
		wsServiceBOMap.put("holdOfFundWs", "retCriticalWebServiceWrapperBO");
		wsOperationsMethodMap.put("holdOfFundWs.createHoldOfFund" , "createHoldOfFund");
		wsOperationsMethodMap.put("holdOfFundWs.approveReleaseHoldOfFund" , "approveReleaseHoldOfFund");
		
		wsServiceBOMap.put("standingOrderWs", "retCriticalWebServiceWrapperBO");
		wsOperationsMethodMap.put("standingOrderWs.closeStandingOrder" , "closeStandingOrder");
		wsOperationsMethodMap.put("standingOrderWs.createStandingOrder" , "createStandingOrder");
		wsOperationsMethodMap.put("standingOrderWs.stopStandingOrder" , "stopStandingOrder");
		
		wsServiceBOMap.put("statementOfAccountWs", "retCriticalWebServiceWrapperBO");
		wsOperationsMethodMap.put("statementOfAccountWs.returnAggregatedBalance" , "returnAggregatedBalance");
		wsOperationsMethodMap.put("statementOfAccountWs.returnAvailableBalance" , "returnAvailableBalance");
		wsOperationsMethodMap.put("statementOfAccountWs.returnMiniStatement" , "returnMiniStatement");
		
		wsServiceBOMap.put("depositWs", "retCriticalWebServiceWrapperBO");
		wsOperationsMethodMap.put("depositWs.createCashDeposit" , "createCashDeposit");
		
		wsServiceBOMap.put("withdrawalWs", "retCriticalWebServiceWrapperBO");
		wsOperationsMethodMap.put("withdrawalWs.createCashWithdrawal" , "createCashWithdrawal");
		
		wsServiceBOMap.put("transactionWs", "retCriticalWebServiceWrapperBO");
		wsOperationsMethodMap.put("transactionWs.partialReversal" , "partialReversal");
		wsOperationsMethodMap.put("transactionWs.approveReverseTransaction" , "approveReverseTransaction");
		wsOperationsMethodMap.put("transactionWs.approveTransaction" , "approveTransaction");
		wsOperationsMethodMap.put("transactionWs.updateTransaction" , "updateTransaction");
		
		wsServiceBOMap.put("csmRequestWs", "retCriticalWebServiceWrapperBO");
		wsOperationsMethodMap.put("csmRequestWs.createCsmRequests" , "insertCsmRequest");
		
		//**********************************TFA *********************************//
		wsServiceBOMap.put("billWsBO", "tfaWebServiceWrapperBO");
		wsOperationsMethodMap.put("billWsBO.returnBillsForCollectionList", "returnBillsList");
		wsOperationsMethodMap.put("billWsBO.returnBillsForCollectionDetails", "returnBillsDetails");
		wsOperationsMethodMap.put("billWsBO.createBillCollection", "createBillRequest");
		wsOperationsMethodMap.put("billWsBO.returnBillCollectionSummary", "returnTfaDocumentSummary");
		
		wsServiceBOMap.put("letterCreditWsBO", "tfaWebServiceWrapperBO");
		wsOperationsMethodMap.put("letterCreditWsBO.returnLetterCreditsList", "returnLetterCreditsList");
		wsOperationsMethodMap.put("letterCreditWsBO.returnLetterCreditsDetails", "returnLetterCreditsDetails");
		wsOperationsMethodMap.put("letterCreditWsBO.createLetterCredit", "createLCRequest");
		wsOperationsMethodMap.put("letterCreditWsBO.returnLetterCreditSummary", "returnTfaDocumentSummary");
		
		wsServiceBOMap.put("letterGuaranteeWsBO", "tfaWebServiceWrapperBO");
		wsOperationsMethodMap.put("letterGuaranteeWsBO.createLetterGuarantee", "createLGRequest");
		wsOperationsMethodMap.put("letterGuaranteeWsBO.returnLetterGuaranteesList", "returnLetterOfGuaranteeList");
		wsOperationsMethodMap.put("letterGuaranteeWsBO.returnLetterGuaranteesDetails", "returnLetterOfGuaranteeDetails");
		wsOperationsMethodMap.put("letterGuaranteeWsBO.returnLetterGuaranteeSummary", "returnTfaDocumentSummary");
		//**********************************end TFA *********************************//
		
		// COMMON Management
		wsServiceBOMap.put("commonManagementWs", "commonWebServiceWrapperBO");
		wsOperationsMethodMap.put("commonManagementWs.returnRunningDate", "returnRunningDate");
		
		// SMART Management
		wsServiceBOMap.put("smartManagementWs","commonWebServiceWrapperBO");
		wsOperationsMethodMap.put("smartManagementWs.returnSmartList", "returnSmartList");
		wsOperationsMethodMap.put("smartManagementWs.returnSmartDefinitionList", "returnSmartDefinitionList");
		wsOperationsMethodMap.put("smartManagementWs.returnSmartDetails", "returnSmartDetails");
		
		
		// CBS Integration
		wsServiceBOMap.put("processCBSMessageWs","processCBSMessageBO");
		wsOperationsMethodMap.put("processCBSMessageWs.processCBSMessage", "processCBSMessage");
        
        		
		// Reporting Management
        	wsServiceBOMap.put("reportWs", "repWebServiceWrapperBO");
        	wsOperationsMethodMap.put("reportWs.generateReport", "generateReport");
		

       	//**********************************CIS *********************************//
        	wsServiceBOMap.put("summitWs", "cisSummitWebServiceWrapperBO");
        	wsOperationsMethodMap.put("summitWs.processChequeEnquiry" , "processChequeEnquiry");
        	wsOperationsMethodMap.put("summitWs.processStopCheque" , "processStopCheque");
        	wsOperationsMethodMap.put("summitWs.processAccountStatementRequest" , "processAccountStatementRequest");
        //**********************************end CIS *********************************//
	};
}
