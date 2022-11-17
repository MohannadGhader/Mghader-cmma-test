package com.path.lib.common.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import com.path.bo.common.ConstantsCommon;
//import com.path.dbmaps.vo.S_APPVO;
import com.path.lib.common.exception.BOException;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.StringUtil;
import com.path.vo.common.CommonLibSC;

public class WebServiceBaseBO extends BaseBO {
	/**
	 * this method only to initialize the common outputs for all services
	 * 
	 * @param hashIn HashMap of the parameter passed to include the output into
	 * @return same hashIn but with additional serviceResponse key with HashMap
	 *         inside {statusCode and statusDesc}.
	 */
	protected HashMap<String, Object> initializeServiceCall(HashMap<String, Object> hashIn) {
		HashMap<String, Object> serviceResponseHm = new HashMap<>();
		serviceResponseHm.put("statusCode", 0);
		serviceResponseHm.put("statusDesc", "Success");
		hashIn.put("serviceResponse", serviceResponseHm);

		Level logLevel = log.getLogDriver().getLevel();
		if (logLevel.equals(Level.ALL) || logLevel.equals(Level.INFO)) {
			final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
			String methodName = ste[2].getMethodName();
			log.debug("[CommonWebServiceWrapperBOImpl initializeServiceCall] hashIn: " + methodName + " " + hashIn);
		}
		return hashIn;
	}

	/**
	 * this method will handle the exception for every service calling
	 * 
	 * @return HashMap Containing the statusCode and statusDesc in serviceResponse
	 *         key of the Nap
	 * @param ex     Exception raised
	 * @param hashIn HashMap object to Add the Exception details to.
	 */
	protected HashMap<String, Object> handleServiceException(Exception ex, HashMap<String, Object> hashIn)
			throws BaseException {
		// info log used for trace log files (on production)
		log.error(ex, "Error in Service Call, performing HandleException method handleServiceException CurrentApp="
				+ ConstantsCommon.returnCurrentAppName() + " version=" + ConstantsCommon.returnAppVersion());
		String errorDesc = "";
		Integer errorCode = -1;
		if (ex instanceof BOException) {
			ArrayList<BaseException> exceptionList = ((BOException) ex).getExceptionsLst();
			if (exceptionList != null && !exceptionList.isEmpty()) {
				for (BaseException baseEx : exceptionList) {
					Object[] errorObject = handleErrorException(baseEx);
					errorCode = (Integer) errorObject[0];
					errorDesc += (String) errorObject[1];
				}
			} else {
				Object[] errorObject = handleErrorException(ex);
				errorCode = (Integer) errorObject[0];
				errorDesc = (String) errorObject[1];
			}
		} else if (ex instanceof BaseException) {
			Object[] errorObject = handleErrorException(ex);
			errorCode = (Integer) errorObject[0];
			errorDesc = (String) errorObject[1];
		} else {
			Object[] errorObject = handleErrorException(ex);
			errorCode = (Integer) errorObject[0];
			errorDesc = (String) errorObject[1];
		}

		HashMap<String, Object> serviceResponseHm = new HashMap<>();
		serviceResponseHm.put("statusCode", errorCode);
		serviceResponseHm.put("statusDesc", errorDesc);
		hashIn.put("serviceResponse", serviceResponseHm);
		log.debug("[CommonWebServiceWrapperBOImpl] handleServiceException serviceResponse statusCode=" + errorCode
				+ " / statusDesc=" + errorDesc);
		return hashIn;
	}

	private Object[] handleErrorException(Exception ex) throws BaseException {
		String msg = ex.getMessage();
		String[] msgParams = null;
		if (ex instanceof BaseException || ex instanceof BOException) {
			msgParams = ((BaseException) ex).getParams();
		}
		Integer errorCode = -1;
		String errorMsg = "";

		// Message could be returned from procedure that have ~#~ delimeter
		String[] msgArr = msg.split("~#~");
		// in case when the error msg is returned from a procedure
		if (msgArr.length > 1) {
			errorCode = Integer.valueOf(msgArr[0]);
			errorMsg = msgArr[1];
		}
		// otherwise in case of normal msg exception
		else {
			if (ex instanceof BaseException || ex instanceof BOException) {
				errorCode = ((BaseException) ex).getErrorCode();
			}
			errorMsg = msg;
		}
		//
		if (null == errorCode) {
			errorCode = -1;
		}
		// in case when msg is not translated we should translate it
		if (errorMsg != null && errorMsg.contains("Message To be Retrieved from CTS Messages")) {
//	    S_APPVO sappVO = new S_APPVO();
//			sappVO.setAPP_NAME(ConstantsCommon.returnCurrentAppName());
			String language = ConstantsCommon.LANGUAGE_ENGLISH;
//			if (sappVO != null) {
//				language = StringUtil.nullEmptyToValue(sappVO.getENABLE_LANGUAGE(), ConstantsCommon.LANGUAGE_ENGLISH);
//			}
			String label = "";

			if (msgParams != null && msgParams.length > 0) {
				String[] labels = new String[msgParams.length];
				for (int i = 0; i < msgParams.length; i++) {
					CommonLibSC sc = new CommonLibSC();
					sc.setAppName(ConstantsCommon.IMAL_APP_NAME);
					sc.setProgRef(ConstantsCommon.PROGREF_ROOT);
					sc.setLanguage(language);
					sc.setKeyLabelCode(msgParams[i]);
					// translate the label key in case the message code hold
					// parameter in description
					labels[i] = label;
				}
			} else {
			}
		}

		// errorCode should be always sent as negative
		if (errorCode > 0) {
			errorCode = errorCode * -1;
		}
		return new Object[] { errorCode, errorMsg };
	}
}
