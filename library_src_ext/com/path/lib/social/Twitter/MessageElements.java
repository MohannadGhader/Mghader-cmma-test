package com.path.lib.social.Twitter;


public class MessageElements  implements java.io.Serializable{

	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getOtpValue() {
		return otpValue;
	}
	public void setOtpValue(String otpValue) {
		this.otpValue = otpValue;
	}
	public String geteStatementFromDate() {
		return eStatementFromDate;
	}
	public void seteStatementFromDate(String eStatementFromDate) {
		this.eStatementFromDate = eStatementFromDate;
	}
	public String getEstatementToDate() {
		return estatementToDate;
	}
	public void setEstatementToDate(String estatementToDate) {
		this.estatementToDate = estatementToDate;
	}
	public String getReceiverAccount() {
		return receiverAccount;
	}
	public void setReceiverAccount(String receiverAccount) {
		this.receiverAccount = receiverAccount;
	}
	public String getTransferedAmount() {
		return transferedAmount;
	}
	public void setTransferedAmount(String transferedAmount) {
		this.transferedAmount = transferedAmount;
	}
	public String getLastTrsNumber() {
		return lastTrsNumber;
	}
	public void setLastTrsNumber(String lastTrsNumber) {
		this.lastTrsNumber = lastTrsNumber;
	}
	public String getRequireOtp() {
		return requireOtp;
	}
	public void setRequireOtp(String requireOtp) {
		this.requireOtp = requireOtp;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public String getOutputData() {
		return outputData;
	}
	public void setOutputData(String outputData) {
		this.outputData = outputData;
	}

	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getSenderAccount() {
		return senderAccount;
	}
	public void setSenderAccount(String senderAccount) {
		this.senderAccount = senderAccount;
	}

	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSocialMediaChannel() {
		return socialMediaChannel;
	}
	public void setSocialMediaChannel(String socialMediaChannel) {
		this.socialMediaChannel = socialMediaChannel;
	}

	public String getTransferLink() {
		return transferLink;
	}
	public void setTransferLink(String transferLink) {
		this.transferLink = transferLink;
	}



	private String action;
	private String mobileNumber;
	private String accountNumber;
	private String otpValue;
	private String eStatementFromDate;
	private String estatementToDate;
	private String receiverAccount;
	private String senderAccount;
	private String senderName;
	private String transferedAmount;
	private String lastTrsNumber;
	private String requireOtp;
	private String text;
	private String outputData;
	//private int messageCode;
	private String messageCode;
	private String socialMediaChannel;
	private String transferLink;

	
	
	
}
