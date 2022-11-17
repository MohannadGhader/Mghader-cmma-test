package com.path.struts2.lib.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionContext;
import com.path.bo.common.BaseServices;
import com.path.bo.common.CachedConstantsCommon;
import com.path.bo.common.ConstantsCommon;
//import com.path.dbmaps.vo.SYS_PARAM_SCREEN_DISPLAYVO;
import com.path.lib.common.base.BaseServicesImpl;
import com.path.lib.common.util.ApplicationContextProvider;
import com.path.lib.common.util.DateUtil;
import com.path.lib.common.util.StringUtil;
import com.path.lib.log.Log;
import com.path.struts2.lib.common.base.BaseAction;
//import com.path.vo.common.RequiredFieldsSC;
import com.path.vo.common.SessionCO;

public final class RootUtil {
	private final static String noFormat = "false";
	private final static String groupSepa = ",";
	private final static String decimalSepa = ".";
	public static final String SUBMIT_ELEM_TYPE = "1";
	public static final String LABEL_ELEM_TYPE = "2";
	public static final String ANCHOR_ELEM_TYPE = "3";
	public static final String TAB_ELEM_TYPE = "4";
	public static final String COLLAPS_ELEM_TYPE = "5";

	/**
	 * Private constructor only to prevent instantiation in the class
	 */
	private RootUtil() {
		Log.getInstance().error("This Class Should not be Instantiated");
	}

	/**
	 * Sets the nbFormat and noFormat value according to the format set in USM
	 * 
	 * @param theNbFormat   String
	 * @param theNoFormat   String
	 * @param numberFormats HashMap
	 */
	public static HashMap manageNumberFormat(String theNbFormat, String theNoFormat, HashMap numberFormats) {

		HashMap formats = new HashMap();
		String nbFormat = theNbFormat;
		String noNbFormat = theNoFormat;
		if (numberFormats == null) {
			formats.put("noFormat", noNbFormat);
			formats.put("nbFormat", nbFormat);
			formats.put("decimalSepa", decimalSepa);
			formats.put("groupSepa", groupSepa);
		} else {
			Object tmpObj = null;
			if (nbFormat == null) {
				tmpObj = numberFormats.get("default");
				if (tmpObj == null) {
					nbFormat = "#,###.0#";
				} else {
					nbFormat = (String) tmpObj;
				}
			} else {
				if (nbFormat.indexOf("#") == -1 && nbFormat.indexOf("0") == -1) {
					tmpObj = numberFormats.get(nbFormat);
					if (tmpObj == null) {
						tmpObj = numberFormats.get("default");
						if (tmpObj == null) {
							nbFormat = "#,###.0#";
						} else {
							nbFormat = (String) tmpObj;
						}
					} else {
						nbFormat = (String) tmpObj;
					}
				}
			}
			if (noNbFormat == null) {
				noNbFormat = "false";
			}
			formats.put("noFormat", noNbFormat);
			formats.put("nbFormat", nbFormat);
			formats.put("decimalSepa", StringUtil.nullEmptyToValue(numberFormats.get("decimalSepa"), decimalSepa));
			formats.put("groupSepa", StringUtil.nullEmptyToValue(numberFormats.get("groupSepa"), groupSepa));
		}
		return formats;
	}

	/**
	 * Sets the numberFormats hashMap from the session and if it is null in the
	 * session set noFormat and nbFormat by the default values
	 * 
	 * @param pageContext
	 * @return
	 */
	public static HashMap returnNumberFormat(HttpSession session) {
		HashMap numberFormats = new HashMap();
		Object numFormatObj = null;
		SessionCO sesCO = (SessionCO) session.getAttribute(ConstantsCommon.SESSION_VO_ATTR);
		if (sesCO != null && sesCO.getUserNbFormats() != null) {
			numFormatObj = sesCO.getUserNbFormats();
		}

		if (numFormatObj == null) {
			numberFormats.put("noFormat", noFormat);
			numberFormats.put("groupSepa", groupSepa);
			numberFormats.put("decimalSepa", decimalSepa);
			numberFormats.put(DateUtil.DATE_MASK_ATTRIBUTE, DateUtil.DEFAULT_DATE_FORMAT);
		} else {
			numberFormats = (HashMap) numFormatObj;
		}
		return numberFormats;
	}

	/**
	 * Returns the date mask in the pagecontext for the logged in user. If there is
	 * no date mask then the default date mask will be returned.
	 * 
	 * @param pageContext
	 * @return String
	 */
	public static String returnDateMask(HttpSession session) {
		Map userFormats = RootUtil.returnNumberFormat(session);
		String serverDateFormat = DateUtil.returnDateFormat(userFormats);
		// replacing the server format with characters accepted by Jquery
		// DatePicker
		serverDateFormat = serverDateFormat.split(" ")[0];
		String clientFormat = serverDateFormat.replace("MM", "mm");
		if (clientFormat.indexOf("yyyy") >= 0) {
			clientFormat = clientFormat.replace("yyyy", "yy");
		} else {
			clientFormat = clientFormat.replace("yy", "y");
		}
		return clientFormat;
	}

	/**
	 * Returns the date mask according to the grid fmatter.js for displaying
	 * formatted date on retrieved records
	 * 
	 * @param pageContext
	 * @return String
	 */
	public static String returnGridDateMask(HttpSession session) {
		Map userFormats = RootUtil.returnNumberFormat(session);
		String serverDateFormat = DateUtil.returnDateFormat(userFormats);
		// replacing the server format with characters accepted by Jquery grid
		serverDateFormat = serverDateFormat.split(" ")[0];
		String clientFormat = serverDateFormat.replace("MM", "m");
		clientFormat = clientFormat.replace("dd", "d");
		if (clientFormat.indexOf("yyyy") >= 0) {
			clientFormat = clientFormat.replace("yyyy", "Y");
		} else {
			clientFormat = clientFormat.replace("yy", "y");
		}
		return clientFormat;
	}

	/**
	 * Used for returning a tooltip of Particular Field/Element control on Screen
	 * 
	 * @param request
	 * @param elementName
	 * @param ElementId
	 * @return toolTIp
	 */
	public static String returnFieldToolTip(HttpServletRequest request, String elementName, String elemId) {
		return returnFieldToolTip(request, elementName, elemId, null);
	}

	/**
	 * TP #945072 - Adding tooltip option via SYS_PARAM_SCREEN_DISPLAY_VO Used for
	 * returning a tooltip of Particular Field/Element control on Screen if the
	 * tooltip not exist in theVO
	 * 
	 * @param request
	 * @param elementName
	 * @param elemId
	 * @param theVO
	 * @return
	 * @author HusseinZaraket
	 */
//	public static String returnFieldToolTipWithCustomization(HttpServletRequest request, String elementName,
//			String elemId, SYS_PARAM_SCREEN_DISPLAYVO theVO) {
//		if (null != theVO && null != theVO.getTooltip()) {
//			return theVO.getTooltip();
//		}
//		return returnFieldToolTip(request, elementName, elemId, null);
//	}

	/**
	 * 
	 * Used for returning a tooltip of Particular Field/Element control on Screen
	 * 
	 * @param request
	 * @param elementName
	 * @param ElementId
	 * @param element     Type
	 * @return
	 */
	public static String returnFieldToolTip(HttpServletRequest request, String elementName, String elemId,
			String elemType) {
		String currToolTip = null;
		// if tooltip not found in DB then put Element name and Id as ToolTip,
		// if Translation of tooltip Enabled
		if (currToolTip == null && 1 == ConstantsCommon.ENABLE_TOOLTIP_TRANS) {
			BaseAction baseAction = ((BaseAction) ActionContext.getContext().getActionInvocation().getAction());
			SessionCO sesCO = (SessionCO) request.getSession().getAttribute(ConstantsCommon.SESSION_VO_ATTR);
			// same SC that used in Required Fields is suitable here since it
			// contains all Fields
//			RequiredFieldsSC criteria = new RequiredFieldsSC();
//			criteria.setAppName(sesCO.getCurrentAppName());
//			String pageRef = request.getParameter("_pageRef");
//			criteria.setProgRef(pageRef);
//			criteria.setLoginUserId(sesCO.getUserName());
//			criteria.setLangCode(sesCO.getLanguage());
//			// set Element Id after removing progRef from it if Exists
//			String currElId = (pageRef == null || elemId == null ? elemId
//					: (elemId.endsWith("_" + pageRef) ? elemId.substring(0, elemId.lastIndexOf("_" + pageRef))
//							: elemId));
//			criteria.setElementId(currElId);
//			criteria.setElementName(elementName == null ? currElId : elementName);
//	    currToolTip = baseAction.returnToolTipTrans(criteria);

		}
		return currToolTip;
	}

	/**
	 * 
	 * Used for Dynamic Screen Display with element Type providing so that in case
	 * of submit default readonly will not be applied
	 * 
	 * @param request
	 * @param elementName
	 * @return
	 */

//	private static void applyStrongestManagement(SYS_PARAM_SCREEN_DISPLAYVO sysParamScreenDisplayVO,
//			SYS_PARAM_SCREEN_DISPLAYVO sysParamWithExpressionVO) {
//		if (sysParamScreenDisplayVO == null) {
//			sysParamScreenDisplayVO = sysParamWithExpressionVO;
//		} else {
//			if (sysParamScreenDisplayVO.getIS_MANDATORY() != null
//					&& !ConstantsCommon.REQUIRED_STRONG_VALUE.equals(sysParamScreenDisplayVO.getIS_MANDATORY())) {
//				if (sysParamWithExpressionVO.getIS_MANDATORY() != null) {
//					sysParamScreenDisplayVO.setIS_MANDATORY(sysParamWithExpressionVO.getIS_MANDATORY());
//				}
//			}
//			if (sysParamScreenDisplayVO.getIS_READONLY() != null
//					&& !ConstantsCommon.READONLY_STRONG_VALUE.equals(sysParamScreenDisplayVO.getIS_READONLY())) {
//				if (sysParamWithExpressionVO.getIS_READONLY() != null) {
//					sysParamScreenDisplayVO.setIS_READONLY(sysParamWithExpressionVO.getIS_READONLY());
//				}
//			}
//			if (sysParamScreenDisplayVO.getIS_VISIBLE() != null
//					&& !ConstantsCommon.VISIBLE_STRONG_VALUE.equals(sysParamScreenDisplayVO.getIS_VISIBLE())) {
//				if (sysParamWithExpressionVO.getIS_VISIBLE() != null) {
//					sysParamScreenDisplayVO.setIS_VISIBLE(sysParamWithExpressionVO.getIS_VISIBLE());
//				}
//			}
//			if (sysParamScreenDisplayVO.getDEFAULT_VALUE() == null
//					|| (BigDecimal.ONE.equals(sysParamScreenDisplayVO.getDFLT_VAL_EXPR_PRIORITY_YN())
//							&& sysParamWithExpressionVO.getDEFAULT_VALUE() != null)) {
//				sysParamScreenDisplayVO.setDEFAULT_VALUE(sysParamWithExpressionVO.getDEFAULT_VALUE());
//			}
//		}
//	}

	/**
	 * This function is used to return the HTML ELEMENT_IDs defined as mapping data
	 * of the custom buttons. That means all the html elements id are returned to be
	 * evaluated when clicking the custom button.
	 * 
	 * @param btnId
	 * @param mapType
	 * @return
	 */

	/**
	 * Dynamic Display Management ...
	 * 
	 * @param request
	 * @param elementName
	 * @return
	 */

	public static HashMap<String, HashMap<String, String>> returnChangesHighlightsForElt(HttpServletRequest request) {
		BaseAction baseAction = ((BaseAction) ActionContext.getContext().getActionInvocation().getAction());
		return baseAction.get_highlights();
	}

	public static String returnTransMsg(HttpServletRequest request, String text) {
		BaseAction baseAction = ((BaseAction) ActionContext.getContext().getActionInvocation().getAction());
		return baseAction.getText(text);
	}

	/**
	 * This function returns all the mapped parameters defined for a dynamic screen
	 * 
	 * @param criteria
	 * @throws DAOException
	 */
	/**
	 * return activities list for a custom element
	 * 
	 * @param theVO
	 * @return
	 */

	/**
	 * return activities list for a custom element
	 * 
	 * @param theVO
	 * @return
	 */

	/**
	 * This function is used to return the HTML ELEMENT_IDs defined as mapping data
	 * of the custom buttons. That means all the html elements id are returned to be
	 * evaluated when clicking the custom button.
	 * 
	 * @param customBtnDataMap
	 * @param activitiesVOList
	 * @return
	 */

	/**
	 * 
	 * Used for Dynamic Screen Display with element Type providing so that in case
	 * of submit default readonly will not be applied
	 * 
	 * @param request
	 * @param elementName
	 * @return
	 */

//    public static SYS_PARAM_OBJ_DETAILS_DISPLAYVO returnParamObjDetailsDisplay(HttpServletRequest request, String gridId,
//	    String colName)
//    {
//	
//	SYS_PARAM_OBJ_DETAILS_DISPLAYVO sysParamObjDetailsDisplayVO = null;
//	// SYS_PARAM_SCREEN_DISPLAYVO sysParamScreenDisplayVOFromHm = null;
//	if("1".equals(CachedConstantsCommon.apply_dynamic_screen))
//	{
//	    BaseServices baseServices = (BaseServicesImpl) ApplicationContextProvider.getApplicationContext().getBean(
//		    "baseServices");
//	    RequiredFieldsSC criteria = new RequiredFieldsSC();
//	    SessionCO sesCO = (SessionCO) request.getSession().getAttribute(ConstantsCommon.SESSION_VO_ATTR);
//	    BaseAction baseAction = ((BaseAction) ActionContext.getContext().getActionInvocation().getAction());
//	    /*
//	     * variable from BaseAction Class on which the Dynamic details will
//	     * be dependent on if this variable true then if there is specific
//	     * progref in SREEEN_DEISPLAY table it will be considered otherwise
//	     * readonly will be true for the element (parent progref not
//	     * considered) if this variable is false then specific prog ref will
//	     * be considered , if not available then parent progref will be
//	     * considered
//	     */
//	    String _recReadOnly = StringUtil.nullToEmpty(baseAction.get_recReadOnly());
//	    String pageRef = request.getParameter("_pageRef");
//            /**
//             * [MarwanMaddah]: in case the progRef is empty, so it is a common screen(labeling, running date, saveAs....) 
//             * in this case we will get the display information based on SETTINGS_CONFIG_OPT
//             */
//	    if(StringUtil.nullToEmpty(pageRef).isEmpty())
//            {
//        	pageRef = ConstantsCommon.SETTINGS_CONFIG_OPT;
//            }
//	    
//	    // if not found by element Name in AdditionalScreenParams Map then
//	    // try by Id after removing _pageRef if Exists
//	    String progRef = pageRef;
//
//	    // do not proceed to the service layer if element is label or Anchor
//	    /**
//	     * the checking on company code has been added to avoid the entrance on display management 
//	     * on login screen 
//	     */
//	    if(sesCO != null && sesCO.getCompanyCode()!=null && pageRef != null )
//	    {
//		if(gridId != null)
//		{
//		    String currElId = (pageRef == null ? gridId:(gridId.endsWith("_" + pageRef) ? gridId.substring(0, gridId
//				.lastIndexOf("_" + pageRef)) : gridId));
//
//		    criteria.setElementId(currElId);
//		}
//		criteria.setElementName(colName);
//		criteria.setAppName(sesCO.getCurrentAppName());
//		criteria.setProgRef(progRef);
//		criteria.setCompCode(sesCO.getCompanyCode());
//		criteria.setLoginUserId(sesCO.getUserName());
//		
//		// in case of Expression the following values could be used
//		criteria.setBranchCode(sesCO.getBranchCode());
//		criteria.setBranchName(sesCO.getBranchName());
//		criteria.setCompanyName(sesCO.getCompanyName());
//		criteria.setUserFirstName(sesCO.getUserFirstName());
//		criteria.setUserLastName(sesCO.getUserLastName());
//		criteria.setBaseCurrencyName(sesCO.getBaseCurrencyName());
//		if(sesCO.getCtsTellerVO() != null && sesCO.getCtsTellerVO().getCODE() !=null)
//		{
//		    criteria.setIsTeller(BigDecimal.ONE);
//		}
//		
//		try
//		{
//		    /**
//		     * [MarwanMaddah]: In case the progReg is equals to SETTINGS_CONFIG_OPT, so it is mean the request is from translation management 
//		     * for that will the element name by the element Id to get the value from the cached hashmap.
//		     */
//                    if(ConstantsCommon.SETTINGS_CONFIG_OPT.equals(criteria.getProgRef()) && StringUtil.nullToEmpty(criteria.getElementName()).isEmpty())
//                    {
//                	criteria.setElementName(gridId);
//                    }
//		    // overwrite according to _recReadOnly parameter, check
//		    // comments above
//		    if(sysParamObjDetailsDisplayVO != null 
//		       && "true".equals(_recReadOnly))
//		    {
//			sysParamObjDetailsDisplayVO.setIS_READONLY(BigDecimal.ONE);
//		    }
//		
//		}
//		catch(Exception e)
//		{
//		    Log.getInstance().error(e, "Error in retrieving requiredData for tag");
//		}
//	    }
//	}
//	return sysParamObjDetailsDisplayVO;
//    }

	/**
	 * method used to escape special characters that may affect JS code when it
	 * contains struts properties ${} it will escape the property from special
	 * characters that might be used for security intrusion
	 * 
	 * @param property
	 * @return
	 */
	public static String escapeJS(String property) {
		if (StringUtil.isNotEmpty(property)) {
			return property.replaceAll("[{}'\"();,:/\\\\]", "");
		}
		return property;
	}
}
