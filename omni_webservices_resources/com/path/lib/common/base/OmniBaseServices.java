package com.path.lib.common.base;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.path.bo.common.CommonLibBO;
import com.path.bo.omni.admin.OmniAdminBO;
import com.path.bo.omni.client.OmniClientBO;
import com.path.bo.omni.common.OmniCommonBO;
import com.path.bo.omni.common.OmniCommonConstants;
import com.path.bo.omni.driver.OmniDriverBO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.PathPropertyUtil;
import com.path.lib.common.util.StringUtil;
import com.path.lib.log.Log;
import com.path.vo.common.header.ServiceResponseVO;
import com.path.vo.omni.common.base.OmniBaseSC;
import com.path.ws.omni.vo.common.OmniCommonResponse;
import com.path.ws.omni.vo.common.ionicgrid.OmniGridParamRequest;

/**
 * Handler class for exceptions that will be thrown by all classes who use
 * BaseException
 * <p>
 * Copyright: Copyright (c) 2012
 * 
 * <p>
 * Company: PathSolutions
 */
public class OmniBaseServices extends WebServiceBaseBO {
	// protected CommonLibBO commonLibBO;
	protected OmniCommonBO omniCommonBO;
	protected OmniDriverBO omniDriverBO;
	protected OmniClientBO omniClientBO;
	protected OmniAdminBO omniAdminBO;

	private final static Log pathlog = Log.getInstance();

	public void setOmniDriverBO(OmniDriverBO omniDriverBO) {
		this.omniDriverBO = omniDriverBO;
	}

	public void setOmniClientBO(OmniClientBO omniClientBO) {
		this.omniClientBO = omniClientBO;
	}

	protected void copySearchProp(OmniGridParamRequest criteria, OmniBaseSC returnSC) throws Exception {
		String leftSideComp = "UPPER(";
		String endComp = ")";
		String startComp = "(";
		String andOperator = " AND ";

		int isSybase = returnIsSybase();

		String filtersw = "startsWith";
		String filterew = "endsWith";
		String filterc = "contains";
		String filternc = "notContains";
		String filterir = "inRange";
		String filterTypeNb = "number";
		String filterTypeTxt = "text";
		String filterTypeDate = "date";

		HashMap<String, String> operatorsMap = new HashMap<String, String>();
		operatorsMap.put("equals", " = ");
		operatorsMap.put("notEqual", " <> ");
		operatorsMap.put(filtersw, " LIKE ");
		operatorsMap.put(filterew, " LIKE ");
		operatorsMap.put(filterc, " LIKE ");
		operatorsMap.put(filternc, " NOT LIKE ");
		operatorsMap.put("lessThan", " < ");
		operatorsMap.put("lessThanOrEqual", " <= ");
		operatorsMap.put("greaterThan", " > ");
		operatorsMap.put("greaterThanOrEqual", " >= ");
		operatorsMap.put(filterir, " BETWEEN ");

		if (!(criteria.getFilterModel() == null || criteria.getFilterModel().isEmpty())) {
			StringBuffer searchQuery = new StringBuffer();
			for (int i = 0; i < criteria.getFilterModel().size(); i++) {
				String colName = criteria.getFilterModel().get(i).getColName().toUpperCase();
				int index = colName.lastIndexOf(".");
				if (index > -1) {
					colName = colName.substring(index + 1);
				}

				if (searchQuery.length() > 0) {
					String operator = criteria.getFilterModel().get(i).getOperator();
					if (StringUtil.isEmptyString(operator)) {
						searchQuery.append(andOperator);
					} else {
						searchQuery.append(" ");
						searchQuery.append(operator);
						searchQuery.append(" ");
					}
				}
				searchQuery.append(startComp);

				if (filterTypeTxt.equals(criteria.getFilterModel().get(i).getFilterOptions().getFilterType())) {
					searchQuery.append(leftSideComp);
					searchQuery.append(colName);
					searchQuery.append(endComp);
					searchQuery.append(operatorsMap.get(criteria.getFilterModel().get(i).getFilterOptions().getType()));
					searchQuery.append(leftSideComp);
					searchQuery.append("'");
					if (filterew.equals(criteria.getFilterModel().get(i).getFilterOptions().getType())
							|| filterc.equals(criteria.getFilterModel().get(i).getFilterOptions().getType())
							|| filternc.equals(criteria.getFilterModel().get(i).getFilterOptions().getType())) {
						searchQuery.append("%");
					}
					searchQuery.append(criteria.getFilterModel().get(i).getFilterOptions().getFilter());
					if (filtersw.equals(criteria.getFilterModel().get(i).getFilterOptions().getType())
							|| filterc.equals(criteria.getFilterModel().get(i).getFilterOptions().getType())
							|| filternc.equals(criteria.getFilterModel().get(i).getFilterOptions().getType())) {
						searchQuery.append("%");
					}
					searchQuery.append("'");
					searchQuery.append(endComp);
				} else if (filterTypeNb.equals(criteria.getFilterModel().get(i).getFilterOptions().getFilterType())) {
					searchQuery.append(colName);
					searchQuery.append(operatorsMap.get(criteria.getFilterModel().get(i).getFilterOptions().getType()));
					searchQuery.append(criteria.getFilterModel().get(i).getFilterOptions().getFilter());
					if (filterir.equals(criteria.getFilterModel().get(i).getFilterOptions().getType())) {
						searchQuery.append(andOperator);
						searchQuery.append(criteria.getFilterModel().get(i).getFilterOptions().getFilterTo());
					}
				} else if (filterTypeDate.equals(criteria.getFilterModel().get(i).getFilterOptions().getFilterType())) {
					String toDate = "to_date('";
					String endToDate = "','YYYY-MM-DD HH24:MI:SS')";
					if (isSybase == 1) {
						toDate = "CONVERT(DATETIME,'";
						endToDate = "')";
					}

					String dataValue = criteria.getFilterModel().get(i).getFilterOptions().getDateFrom();

					// removing the 'T' if exists
					dataValue = dataValue.replace('T', ' ');
					// removing the '.sssZ' (milliseconds) if exists
					if (dataValue.indexOf(".") > -1) {
						dataValue = dataValue.substring(0, dataValue.indexOf("."));
					}

					searchQuery.append(colName.toUpperCase());
					searchQuery.append(operatorsMap.get(criteria.getFilterModel().get(i).getFilterOptions().getType()));
					searchQuery.append(toDate + dataValue + endToDate);
					if (filterir.equals(criteria.getFilterModel().get(i).getFilterOptions().getType())) {
						searchQuery.append(andOperator);
						String dateTo = criteria.getFilterModel().get(i).getFilterOptions().getDateTo();
						// removing the 'T' if exists
						dateTo = dateTo.replace('T', ' ');
						// removing the '.sssZ' (milliseconds) if exists
						if (dateTo.indexOf(".") > -1) {
							dateTo = dateTo.substring(0, dateTo.indexOf("."));
						}
						searchQuery.append(toDate + dateTo + endToDate);
					}

				}
				searchQuery.append(endComp);
			}
			criteria.setWhereQuery(StringUtil.nullEmptyToValue(searchQuery, ""));
		}
		if (!(criteria.getSortModel() == null || criteria.getSortModel().isEmpty())) {
			StringBuffer sortQuery = new StringBuffer();
			sortQuery.append(" ORDER BY ");
			for (int i = 0; i < criteria.getSortModel().size(); i++) {
				if (i > 0) {
					sortQuery.append(", ");
				}
				String colName = criteria.getSortModel().get(i).getColId().toUpperCase();
				int index = colName.lastIndexOf(".");
				if (index > -1) {
					colName = colName.substring(index + 1);
				}
				sortQuery.append(colName);
				sortQuery.append(" ");
				sortQuery.append(criteria.getSortModel().get(i).getSort().toUpperCase());
			}
			criteria.setSortOrder(StringUtil.nullEmptyToValue(sortQuery, ""));
		}

		PathPropertyUtil.copyProperties(criteria, returnSC, "sortOrder", "whereQuery", "nbRec", "recToskip");

	}

	/*
	 * public void setCommonLibBO(CommonLibBO commonLibBO) { this.commonLibBO =
	 * commonLibBO; }
	 */
	public CommonLibBO returnCommonLibBO() {
		return commonLibBO;
	}

	public void setOmniCommonBO(OmniCommonBO omniCommonBO) {
		this.omniCommonBO = omniCommonBO;
	}

	public void setOmniAdminBO(OmniAdminBO omniAdminBO) {
		this.omniAdminBO = omniAdminBO;
	}

	/**
	 * returns 1 if DBMS is SYBASE 0 otherwise
	 */
	public int returnIsSybase() throws BaseException {
		return commonLibBO.returnIsSybase();
	}

	/**
	 * @author AliShreif this method is created to handle the translation of the
	 *         exception thrown by the BOs
	 */
	protected <Any> Any handleServiceException(Exception ex, Any objIn) {

		try {
			pathlog.error(ex, ex.getMessage());

			HashMap<String, Object> hashIn = PathPropertyUtil.convertToMap(objIn);
			HashMap<String, Object> hashOut = handleServiceException(ex, hashIn);

			objIn = (Any) PathPropertyUtil.convertToObject(hashOut, objIn.getClass());

			Integer errorCode = Integer.parseInt(
					StringUtil.nullEmptyToValue((((HashMap<String, Object>) hashOut.get(new String("serviceResponse")))
							.get(new String("statusCode"))), "-1"));

			String errorDesc = StringUtil
					.nullEmptyToValue((((HashMap<String, Object>) hashOut.get(new String("serviceResponse")))
							.get(new String("statusDesc"))), "Contact Administrator");

			java.lang.reflect.Method method1 = null;
			java.lang.reflect.Method method2 = null;

			try {
				method1 = objIn.getClass().getMethod("setErrorCode", Integer.class);
				method2 = objIn.getClass().getMethod("setErrorDesc", String.class);

			} catch (SecurityException e) {
				pathlog.error(e, e.getMessage());
			} catch (NoSuchMethodException e) {
				pathlog.error(e, e.getMessage());
			}

			try {
				if (method1 != null && method2 != null) {
					method1.invoke(objIn, errorCode);
					method2.invoke(objIn, errorDesc);
				}
			} catch (IllegalArgumentException e) {
				pathlog.error(e, e.getMessage());
			} catch (IllegalAccessException e) {
				pathlog.error(e, e.getMessage());
			} catch (InvocationTargetException e) {
				pathlog.error(e, e.getMessage());
			}

			// res.setServiceResponse(sres);
			/*
			 * sres.setStatusCode(hashOut.get("serviceResponse"));
			 * sres.setStatusDesc("Contact administrator"); res.setServiceResponse(sres);
			 */

			return objIn;
		} catch (Exception e) {
			OmniCommonResponse res = new OmniCommonResponse();
			ServiceResponseVO sres = new ServiceResponseVO();

			res.setOutputCode(OmniCommonConstants.EXCEPTION_OUTPUT_CODE);
			res.setOutputNotification(OmniCommonConstants.EXCEPTION_OUTPUT_DESC);
			res.setOutputType(OmniCommonConstants.FATAL_OUTPUT_TYPE);

			// sres.setStatusCode("-1");
			// sres.setStatusDesc("Contact administrator");
			// res.setServiceResponse(sres);
			pathlog.error(e, e.getMessage());
			return (Any) res;
		}

	}

}
