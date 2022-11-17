package com.path.struts2.lib.common.base;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.JSONUtil;
import org.apache.struts2.json.annotations.JSON;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.springframework.security.core.context.SecurityContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.path.bo.common.BaseServices;
import com.path.bo.common.CachedConstantsCommon;
import com.path.bo.common.CommonMethods;
import com.path.bo.common.ConstantsCommon;
import com.path.bo.common.MessageCodes;
import com.path.bo.common.audit.AuditBO;
import com.path.bo.common.audit.AuditConstant;
import com.path.dbmaps.vo.TRACK_CHANGES_DETAILSVO;
import com.path.lib.common.exception.BOException;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.DateUtil;
import com.path.lib.common.util.NumberUtil;
import com.path.lib.common.util.PathPropertyNamingStrategy;
import com.path.lib.common.util.StringUtil;
import com.path.lib.log.Log;
import com.path.lib.vo.GridUpdates;
import com.path.struts2.lib.common.BaseObject;
import com.path.vo.common.CommonLibSC;
import com.path.vo.common.SessionCO;
import com.path.vo.common.audit.AuditRefCO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * 
 * Copyright 2010, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: Denisk Haddad
 * 
 *          BaseAction.java used to Extend struts 2 Action Support and to be
 *          extended from all application actions in order to get use of
 *          session, etc...
 */
public class BaseAction extends ActionSupport implements SessionAware, ModelDriven, ServletResponseAware
// , Preparable
{
	protected final static Log log = Log.getInstance();
	public Map<String, Object> session;

	private String auditMode;
	private String auditTrxNbr;
	private String _auditJsonStr;
	private String _pageRef;
	private String _recReadOnly;// used to specify whether the retrieved record
	// readonly or not.
	private String _ignoreSpecificMenu; // used to ignore the specific progRef process and apply the _recReadOnly on
										// this screen.
	private String _searchGridId;// used to set the search grid Id for main
	// windows
	private String _showNewInfoBtn; // flag to show the New button in the Info bar
	private String _showSmartInfoBtn; // flag to show the Smart button in the Info bar,default true;
	private String _showPrintSwiftBtn; // flag to show the Print Swift button in the info bar,default false.
	private String _disRevertOldVal; // flag to disable of reverting Old Values in dependencies
	private String _forceAfterDepEvent;// flag to force after dependency event firing
	private String _preventAfterDepEvent;// flag to stop after dependency event firing even on success
	private String _showRecordLogsBtn;// flag to show the Records log button in the Info bar;
	private String expImpCustRight; // Access rights for export/import screen customization.

	private String _showTrackChngBtn; // flag to show the track changes report information

	private String _custScrElemProgRef; // Custom progRef, for static prefixed screen references to show the track
										// changes report information

	/**
	 * used to specify whether we need to overwrite SMART Readonly display in case
	 * of _recReadOnly is set
	 */
	private String _SMARTReadOnly;
	private boolean _enableAudit = true;
	protected BaseServices baseServices;
	protected AuditBO auditBO;

	private String _error; // error Message that will appear in a message box
	private String _errorFocusElem; // Element Id/name on which to focus in case of error, Missing Element
	// when using handleException
	private String _confirm; // confirm Message that will be filled in the bo
	// exception for Confirm messages
	private String _menuInfo; // info Message that will be filled to show message on menu opening when
								// restriction needed
	private String _warning; // Warning Message
	private String _addWarning; // warning message that are generated by the system to inform use on certain
								// event, should not be used by developer
	private String _msgTitle;// Title of message popup from table cts_message

	// to check inside
	// ajax Error function
	// to show msgbox

	private String _dependencyMsg; // Message that will appear after dependency

	private String _depMsgEltId; // html Id of the element for which the message
	// will appear on dependency

	private String iv_crud;

	private HashMap<String, HashMap<String, String>> _highlights;

	public static final String ERROR_ACTION = "ERROR_ACTION";

	private static final String entityType_key = "SYS_PRM_ENTITY_TYPE";

	private String objectId;
	private String objectType;
	private String orderArr;
	private String isDefault;

	private BigDecimal bpmTaskId; // the bpm task id used in case of getModel() is not defined at the level of the
									// action.it will be used to initialize the bpmTaskId in baseObject inside
									// initialiseBpmInfo().
	private Map<String, Object> hmDynElems = new HashMap<String, Object>();
	private String externalScreenYN;// flag passed in destinationScreenURL when opening a request screen to indicate
									// that it is an external screen

	// Bug 570461 add special barnch / company code for smart screen
	private BigDecimal smartSpecCompCode;
	private BigDecimal smartSpecBranchCode;

	// 642699 Show the CIF instead of button clicked
	private String fieldAuditDetails;

	private String _dependencyConfirmMsg; // TP#906700 to make message upon dependency before continue

	/*
	 * 953614 Specify Grid Filter Query Expression - Grid/Livesearch Customization
	 * Enhancements
	 */
	private String fltrExpValidateFlg;

	private Object customModelObj; // TP 1044445 used to set the custom Model object to be used in customization to
									// refer to screen CO to read element properties from (RootUtil)

	public String getFltrExpValidateFlg() {
		return fltrExpValidateFlg;
	}

	public void setFltrExpValidateFlg(String fltrExpValidateFlg) {
		this.fltrExpValidateFlg = fltrExpValidateFlg;
	}
	/* end: 953614 */

	@Override
	/**
	 * Method used to set Object values from request automatically, to be overridden
	 * in action when needed to catch the Object(form in struts1)
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method to get the Customized set Screen object in Actions before redirecting
	 * to JSP pages
	 * 
	 * @return the Object set CO screen object supposed to be
	 */
	@JSON(serialize = false)
	public Object getCustomModelObj() {
		return customModelObj;
	}

	public void setCustomModelObj(Object screenObj) {
		this.customModelObj = screenObj;
	}

	/**
	 * used in getModel to apply the tracing where needed
	 * 
	 * @param obj- model object in action
	 * @return
	 */
	public Object returnBaseModel(BaseObject obj) {
		if (ConstantsCommon.SQL_SESSION_TRACE_CODE) {
			SessionCO sessCO = returnSessionObject();
			if (sessCO != null) {
				obj.applyTraceProps(sessCO.getCurrentAppName(), sessCO.getUserName(), get_pageRef());
			}
		}
		return obj;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/**
	 * adding caching to response header for all dynamic pages
	 */
	public void setServletResponse(HttpServletResponse response) {
		/*
		 * PathSolutions - in Dispatcher.java of new Struts 2.5 the
		 * else{response.setLocale(locale);} statement is returning the local from the
		 * request and then causing to apply response.setLocale(locale); inside the
		 * prepare() method of the Dispatcher. This was causing to add
		 * charset=ISO-8859-1 to the response header and the arabic characters was
		 * displayed incorrectly in the reports. The reason is that when applying
		 * response.setLocal() in HttpServeletResponse without applying
		 * setCharacterEncoding() , the encoding is defaulted to ISO instead of UTF-8.
		 * The locale = request.getLocale(); is commented in getLocale() of the
		 * Dispatcher.java plus in BaseAction we added
		 * response.setCharacterEncoding('UTF-8') to prevent any future use of
		 * response.setLocale() without using the response.setCharacterEncoding() before
		 * it
		 */
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache,no-store,pre-check=0,post-check=0");
		response.setDateHeader("Expires", 0); // prevent cashing on proxy
		// preventing cross site scripting
		response.setHeader("X-XSS-Protection", "1; mode=block");
		// Clickjacking denying, disabling from opening screens in iframe,
		// cannot be enabled since our screens are loading in Iframe in case of opening
		// from other application
//	response.addHeader("X-Frame-Options","deny");
	}

	/**
	 * Method used to get sessions of type SessionCO
	 * 
	 */
	public SessionCO returnSessionObject() {
		SessionCO sessCO = null;
		if (session != null && !session.isEmpty() && session.containsKey(ConstantsCommon.SESSION_VO_ATTR)) {
			sessCO = (SessionCO) session.get(ConstantsCommon.SESSION_VO_ATTR);
		}
		return sessCO;
	}

	/*
	 * @Override
	 * 
	 * public void prepare() throws Exception { }
	 */
	/**
	 * Translation method,overridden to support getting translation from DB
	 */
	@Override
	public String getText(String aTextName) {
//	return super.getText(aTextName);
		return returnKeyTrans(aTextName);

	}

	/**
	 * Method used to escape special characters such as " ' and new line in order to
	 * have javascript variable correctly assigned. Used in JSP pages
	 * 
	 * @param aTextName key variable to be translated
	 * @return
	 */
	public String getEscText(String aTextName) {
		String lbl = getText(aTextName);
		lbl = lbl.replace("\"", "\\\"");
		lbl = lbl.replace("'", "\\'");
		lbl = lbl.replace("\n", "\\n");
		return lbl;
	}

	/**
	 * Translation method,overridden to support getting translation from DB
	 */
	@Override
	public String getText(String key, String defaultValue, List<?> args, ValueStack stack) {
//	return super.getText(key, defaultValue, args, stack);
		return returnKeyTrans(key);
	}

	@Override
	public String getText(String aTextName, String defaultValue, List<?> args) {
//	return super.getText(aTextName, defaultValue, args);
		return returnKeyTrans(aTextName);
	}

	/**
	 * Used for returning the login username
	 * 
	 * @return user name
	 */
	public String returnUserName() throws BaseException {

		try {
			SessionCO sessionCO = returnSessionObject();
			String userName;
			if (sessionCO != null && sessionCO.getUserName() != null) {
				userName = sessionCO.getUserName();
			} else {
				SecurityContext secCntxt = (SecurityContext) session.get("SPRING_SECURITY_CONTEXT");
				userName = (String) secCntxt.getAuthentication().getPrincipal();
			}
			return userName;
		} catch (Exception e) {
			log.error(e, "Error in BaseAction while retrieving user namefrom authentication");
			throw new BaseException("Error in retieving authenticated user name", e);
		}

	}

	/**
	 * 
	 * Used for to return request Machine Name or IP Address if Machine Name Not
	 * Specified
	 * 
	 * @return
	 */
	public String returnIP() {
		String ip;
		try {
			ip = returnMachineName();
		} catch (Exception e) {
			log.warning("Not Able To Get Machine Name Properly Ip will be Used");
			ip = ServletActionContext.getRequest().getRemoteAddr();
		}
		ip = StringUtil.nullToEmpty(ip);
		if (ip.length() > 50) {
			ip = ip.substring(0, 50);
		}
		return ip;
	}

	private String returnMachineName() throws UnknownHostException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			InetAddress inetAddress = InetAddress.getByName(ServletActionContext.getRequest().getRemoteHost());
//		ip = inetAddress.getHostName();//this is very slow response upon execution
			ip = inetAddress.toString();
			ip = ip.indexOf("/") > 0 ? ip.substring(0, ip.indexOf("/"))
					: ip.indexOf("/") == 0 ? ip.substring(1, ip.length()) : ip;
			// 0:0:0:0:0:0:0:1 is local Host on Tomcat7+Win7
			if (ConstantsCommon.LOCALHOST.equalsIgnoreCase(ip)
					|| ConstantsCommon.LOCALHOST_TOMCAT_WIN7.equalsIgnoreCase(ip)
					|| ConstantsCommon.LOCALHOST_IP.equalsIgnoreCase(ip)) {
				ip = InetAddress.getLocalHost().getCanonicalHostName();
			}

			String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
					+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
			Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
			Matcher matcher = pattern.matcher(ip);
			// remove . part if available in Machine Name
			if (!matcher.matches() && ip.indexOf(".") > 0) // value is not IP address
			{
				ip = ip.substring(0, ip.indexOf("."));
			}
		}

		return ip;
	}

	/**
	 * handles exception using a message string or a msgKey to be translated
	 * 
	 * @param e      exception
	 * @param msg    string containing a description of the error
	 * @param msgKey key of the message to be translated and displayed
	 */
	public void handleException(Exception exp, String msg, String msgKey) {
		try {
			Exception e = exp;
			HttpServletResponse response = ServletActionContext.getResponse();
			if (!(e instanceof BOException)) {
				response.setStatus(ConstantsCommon.PATHSOL_INTERNAL_ERROR);
			}
			SessionCO sessionCO = returnSessionObject();
			// if Path Solution Exception then add to Exception details about
			// the user
			if (e instanceof BaseException) {
				if (sessionCO != null) {
					((BaseException) e).setMsgUsr(sessionCO.getUserName());
					((BaseException) e).setMsgAppName(sessionCO.getCurrentAppName());
					((BaseException) e).setMsgCompCode(sessionCO.getCompanyCode());
					((BaseException) e).setMsgBranchCode(sessionCO.getBranchCode());
				}
				((BaseException) e).setMsgProgRef(get_pageRef());
				// check if the returned exception has an Focus Element reference to return to
				// the screen and focus
				checkToApplyFocus(((BaseException) e));
			}
			if (msg == null && msgKey == null) // case of Exception message with
			// Error code and BOException
			{
				e = translateMsgParams(e);
				/*
				 * 953614 Specify Grid Filter Query Expression - Grid/Livesearch Customization
				 * Enhancements
				 */
				String[] err_msg_array = null;
				String err_msg = null;
				String fltrFlag = this.getFltrExpValidateFlg();
				if (this.getFltrExpValidateFlg() != null
						&& (ConstantsCommon.ONE.equals(fltrFlag) || ConstantsCommon.TWO.equals(fltrFlag))) {
					response.setStatus(HttpServletResponse.SC_OK);
					String userVisibleFilterExpression = ServletActionContext.getRequest()
							.getParameter("userVisibleFilterExpression");
					if (e instanceof DAOException) {
						err_msg = getText("invalid_sql_syntax_key") + ": " + userVisibleFilterExpression;
					}

					// database not accessed case
					if (ConstantsCommon.TWO.equals(fltrFlag)) {
						err_msg = e.getMessage();
					}
					e = new BOException(MessageCodes.INVALID_EXPRESSION, new String[] { userVisibleFilterExpression },
							true);
					if (err_msg == null) {
						err_msg = err_msg_array[0];
					}
				} else {
					err_msg_array = baseServices.logError(e, sessionCO);
					err_msg = err_msg_array[0];
				}

				if (!StringUtil.nullToEmpty(err_msg_array[1]).isEmpty()) {
					_msgTitle = err_msg_array[1];
				}
				// sometimes when returning list of exception the focused element is inside each
				// exception and not available
				// in the wrapper BaseException containing the list of exception , like case of
				// checkRequiredData at server side
				if (err_msg_array.length > 2 && !StringUtil.nullToEmpty(err_msg_array[2]).isEmpty()) {
					_errorFocusElem = err_msg_array[2];
				}

				if (e instanceof BaseException) {
					BaseException baseEx = (BaseException) e;
					if (baseEx.getMsgType() == null) {
						// Added for SMART
						// Check in case of exception of mandatory fields missing then open the SMART
						// window
						if (ConstantsCommon.SMART_MAND_INFO_MSG.equals(baseEx.getMsgIdent())) {
							_showSmartInfoBtn = baseEx.getMsgIdent();// to identify to open smart window
						}
						_error = err_msg;

					} else {

						if (ConstantsCommon.CONFIRM_MSG_TYPE.equals(baseEx.getMsgType())) {
							_confirm = err_msg;
						} else if (ConstantsCommon.MENU_INFO_MSG_TYPE.equals(baseEx.getMsgType())) {
							_menuInfo = err_msg;
						} else if (ConstantsCommon.WARNING_INFO_MSG_TYPE.equals(baseEx.getMsgType())) {
							_warning = err_msg;
						}
					}
					// if message title not coming from errorCode and it is specified inside
					// BOException manually
					if (_msgTitle == null && baseEx.getMsgTitleKey() != null) {
						_msgTitle = getText(baseEx.getMsgTitleKey());
					}
				} else {
					_error = err_msg;
				}
			} else// case of exception with message only without error codes or BOException
			{
				_error = "";
				log.error(e, "[Exception]" + "'\n\r" + (msg == null ? msgKey : msg));
				if (msg != null) {
					_error += msg;
				}
				if (msgKey != null) {
					if (!_error.isEmpty()) {
						_error += "\r\n";
					}
					_error += getText(msgKey);
				}
				if (!_error.isEmpty()) {
					_error += "\r\n";
				}
				_error += e.getMessage();

				// if message title not coming from errorCode and it is specified inside
				// BOException manually
				if (e instanceof BaseException && ((BaseException) e).getMsgTitleKey() != null) {
					_msgTitle = getText(((BaseException) e).getMsgTitleKey());
				}
			}
			// add action error message so in case of redirect result and not json,
			// the action error will show
			if (!StringUtil.nullToEmpty(_error).isEmpty()) {

				// not BOException add details about use rand scren
				if (sessionCO != null && !(e instanceof BOException)) {
					boolean addDetails = true;
					// do not add the details to teh technical exception if there is a delimiter to
					// identify /business error coming from PRocedure or trigger
					if (e instanceof DAOException && e.getMessage() != null && e.getMessage().indexOf("~#12abc") >= 0) {
						addDetails = false;
					}
					if (addDetails) {
						_error = addUserDetailsToGenException(sessionCO.getUserName(), get_pageRef(),
								sessionCO.getCurrentAppName(), sessionCO.getCompanyCode(), sessionCO.getBranchCode(),
								_error);
					}
				}
				addActionError(_error);
			}
		} catch (Exception ex) {
			log.error(ex, "ERROR in handleException BaseAction");
		}
	}

	/**
	 * Method to set the Element on which to Focus in case Exception specifies the
	 * same
	 * 
	 * @param baseExc BaseEsception Object
	 */
	private void checkToApplyFocus(BaseException baseExc) {
		if (baseExc != null) {
			// check if the returned exception has an Focus Element reference to return to
			// the screen and focus
			Object retExpObj = ((BaseException) baseExc).getRetValue();
			if (retExpObj != null && retExpObj instanceof Map) {
				Map retValMap = (Map) retExpObj;
				if (retValMap.get("_focusElement") != null) {
					_errorFocusElem = retValMap.get("_focusElement").toString();
				}
			}
		}
	}

	/**
	 * Adding User , Application,Screen, Company/Branch Details to the message
	 * 
	 * @param userName       User Name
	 * @param screenRef      Screen Reference
	 * @param appName        Application Name
	 * @param compCode       Company Code
	 * @param branchCode     Branch Code
	 * @param theMainMessage Main Message to which the details to add to
	 * @return
	 */
	private String addUserDetailsToGenException(String userName, String screenRef, String appName, BigDecimal compCode,
			BigDecimal branchCode, String theMainMessage) {
		String newMessage = theMainMessage;
		if (newMessage != null) {

			if (branchCode != null) {
				newMessage = ("Branch Code :" + branchCode.toPlainString() + "\r\n\r\n").concat(newMessage);
			}
			if (compCode != null) {
				newMessage = ("Company Code :" + compCode.toPlainString() + ",").concat(newMessage);
			}
			if (screenRef != null) {
				newMessage = ("Page Reference :" + screenRef + ",").concat(newMessage);
			}
			if (appName != null) {
				newMessage = ("Application :" + appName + ",").concat(newMessage);
			}
			if (userName != null) {
				newMessage = ("User Name :" + userName + ", ").concat(newMessage);
			}
			newMessage = ("Application Version :" + ConstantsCommon.returnAppDisplayVersion() + "\r\n")
					.concat(newMessage);

			newMessage = DateUtil.format(Calendar.getInstance().getTime(), "dd/MM/yyyy HH:mm:ss")
					.concat("\r\n" + newMessage);
		}
		return newMessage;
	}

	/**
	 * adds a message to be displayed on dependency, to be used either in msgbox
	 * mode or labeling on input
	 * 
	 * @param dependencyMsg message to be show on dependency
	 * @param depMsgEltId   html id of the element where the labeling will appear
	 */
	public void addDependencyMsg(String dependencyMsg, String depMsgEltId) {
		if (dependencyMsg != null && !dependencyMsg.isEmpty()) {
			set_dependencyMsg(getText(dependencyMsg));
		}

		if (depMsgEltId != null && !depMsgEltId.isEmpty()) {
			set_depMsgEltId(depMsgEltId);
		}
	}

	/**
	 * returns the serialized string from the object without the arraylists or
	 * hashmaps
	 * 
	 * @param obj bean to be serialized
	 * @return serialized string
	 */
	protected String returnSerializedStrFromObj(Object obj) {
		String serializedStr = null;
		try {
			// Changed the JSON parsing method from JSONUTIL to JACKSON to prevent
			// BigDecimal false rounding
			List<String> excludeLsts = returnListsNames(obj);
			// add additional attributes existing in BaseObject that need to be excluded
			excludeLsts.add("isOracle");
			excludeLsts.add("isSybase");
			excludeLsts.add("isSQLServer");
			excludeLsts.add("emptyBigDecimalValue");
			excludeLsts.add("getEmptyBigDecimalValue");
			excludeLsts.add("httpSessionIdForLink");
			excludeLsts.add("traceUserId");
			excludeLsts.add("traceProgRef");
			excludeLsts.add("traceAppName");
			excludeLsts.add("bpmTaskId");
			excludeLsts.add("bpmUserName");
			excludeLsts.add("bpmUserPass");

			ObjectMapper mapper = new ObjectMapper();
			// ignore null attributes to reduce the generated JSON size
			mapper.setSerializationInclusion(Inclusion.NON_NULL);
			// added Naming strategy and date format to handle OMNI webservices
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.setPropertyNamingStrategy(PathPropertyNamingStrategy.KEEP_AS_IS);
			mapper.getSerializationConfig().withDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZ"));
			if (excludeLsts.isEmpty()) {
				// serializedStr = PathJSONUtil.strutsJsonSerialize(obj, null, null, false,
				// true);
				serializedStr = mapper.writeValueAsString(obj);
			} else {
				// use Introspector to exclude the attributes defined in excludeLsts, in this
				// way we are not forced
				// to add @JSONFilter on BaseObject. Noting that FilterProvider will not work
				// unless we add @JSONFilter.
				mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
					@Override
					public boolean hasIgnoreMarker(final AnnotatedMember m) {
						return excludeLsts.contains(m.getName()) || super.hasIgnoreMarker(m);
					}
				});

				serializedStr = mapper.writeValueAsString(obj);
//	    	 Collection excludeProperties = setExcludeProperties(excludeStr);
//	    	 serializedStr = PathJSONUtil.strutsJsonSerialize(obj, excludeProperties, null, false, true);
			}
		} catch (Exception e) {
			log.error(e, "Error in Base action , returnSerializedStrFromObj method");
		}
		return serializedStr;
	}

	/**
	 * returns the serialized string from the object without the arraylists or
	 * hashmap specified in excludeStr
	 * 
	 * @param audit bean object to be set in the _auditJsonStr void
	 *
	 */
	protected void putAuditObject(Object obj) {
		set_auditJsonStr(returnSerializedStrFromObj(obj));
	}

	private List<String> returnListsNames(Object obj) {
		List<String> names = new ArrayList<String>();
		try {
			if (obj != null && obj.getClass() != null && obj.getClass().getDeclaredFields() != null) {
				Field[] theFields = obj.getClass().getDeclaredFields();
				for (Field theField : theFields) {
					if (!theField.getType().isPrimitive() && PropertyUtils.isReadable(obj, theField.getName())) {
						Object attributeObject = PropertyUtils.getProperty(obj, theField.getName());
						// check if the property has getter
						if ((attributeObject instanceof List) || (attributeObject instanceof ArrayList)
								|| (attributeObject instanceof Map) || (attributeObject instanceof HashMap)) {
							names.add(theField.getName());
						} else {
							if (!(attributeObject instanceof String) && !(attributeObject instanceof Number)
									&& !(attributeObject instanceof Character) && !(attributeObject instanceof Date)
									&& !(attributeObject instanceof Boolean)) {
								Object instObj = obj.getClass()
										.getMethod("get" + StringUtil.setFirstCharUpper(theField.getName()), null)
										.invoke(obj, null);
								names.addAll(returnListsNames(instObj));
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			log.error(ex, "Error in baseAction returnListsNames function");
			handleException(ex, null, null);
		}
		return names;
	}

	/**
	 * transforms the string to pattern list
	 * 
	 * @param commaDelim
	 * @return Collection
	 *
	 */
	private Collection setExcludeProperties(String commaDelim) {
		Collection excludeProperties = null;
		Set excludePatterns = JSONUtil.asSet(commaDelim);
		if (excludePatterns != null) {
			excludeProperties = new ArrayList(excludePatterns.size());
			String pattern;
			for (Iterator i$ = excludePatterns.iterator(); i$.hasNext(); excludeProperties
					.add(Pattern.compile(pattern))) {
				pattern = (String) i$.next();
			}

		}
		return excludeProperties;
	}

	/**
	 * returns json object from json string
	 * 
	 * @param objClass object class to be mapped to
	 * @param objStr   string to be parsed
	 * @return
	 */
	protected Object returnJsonObjectFromStr(Class objClass, String jsonStr) {
		return CommonMethods.returnJsonObjectFromStr(objClass, jsonStr);
	}

	/**
	 * gets audit object from audit json string
	 * 
	 * @param objClass object class to be mapped to
	 * @return
	 */
	protected Object returnAuditObject(Class objClass) {
		return returnJsonObjectFromStr(objClass, _auditJsonStr);
	}

	/**
	 * 
	 * Used for initializing basic attributes of AuditCORef
	 * 
	 * @param objClass
	 * @return
	 */
	public AuditRefCO initAuditRefCO() {
		return initAuditRefCO(null);
	}

	public AuditRefCO initAuditRefCO(String appName) {
		AuditRefCO auditRefCO = new AuditRefCO();
		SessionCO sessionCO = returnSessionObject();
		String currentApp = sessionCO.getCurrentAppName();
		if (!StringUtil.nullToEmpty(appName).trim().isEmpty() && ConstantsCommon.REP_APP_NAME.equals(currentApp)) {
			currentApp = appName;
			log.debug("the application Name Used for AuditREfCO initilaizing [initAuditRefCO] is " + currentApp);
		}
		auditRefCO.setAppName(currentApp);
		auditRefCO.setProgRef(get_pageRef());
		auditRefCO.setRunningDate(sessionCO.getRunningDateRET());
		auditRefCO.setUserID(sessionCO.getUserName());
		// 768542 BB180246 - AUdit Trail report
		auditRefCO.setBranchCode(sessionCO.getBranchCode());

		// NABIL FEGHALI - Fix Issue #215182
		// In case of local approve from alert, set the branch manager user name
		String theMachId = sessionCO.getMachineIp();
		if (theMachId == null) {
			theMachId = returnIP();
		}
		auditRefCO.setMachineID(returnIP());
		// User Story #966771 Changes on the common audit management -To Show Custom
		// Description in Multi-Row Audit
		auditRefCO.setFieldDescLang(sessionCO.getLanguage());
		return auditRefCO;
	}

	public void setAuditInfo(String auditMode, Object... keys) {
		this.auditMode = auditMode;
		this.auditTrxNbr = generateTrxNbr(keys);

	}

	public Boolean checkAuditEnabled() {
		return checkAuditEnabled(null);
	}

	/**
	 * Method to check whether the screen is audit enabled
	 * 
	 * @return Boolean
	 */
	public Boolean checkAuditEnabled(String appName) {
		return checkAuditEnabled(appName, null);
	}

	/**
	 * Check if screen is audit enabled based on AppName and ProgRef
	 * 
	 * @return Boolean true if audit is enabled otherwise false
	 */
	public Boolean checkAuditEnabled(String appName, String progRef) {
		try {
			if (_enableAudit) {
				AuditRefCO auditRefCO = new AuditRefCO();
				// check if the appName is passed and not empty, and current application is
				// reporting
				// then consider that application in Audit Checking
				String currentApp = returnSessionObject().getCurrentAppName();
				if (!StringUtil.nullToEmpty(appName).trim().isEmpty()
						&& ConstantsCommon.REP_APP_NAME.equals(currentApp)) {
					currentApp = appName;
					log.debug("the application Name Used for Audit checking is " + currentApp);
				}
				auditRefCO.setAppName(currentApp);
				if (StringUtil.isEmptyString(progRef)) {
					auditRefCO.setProgRef(_pageRef);
				} else {
					auditRefCO.setProgRef(progRef);
				}
				return auditBO.checkAuditEnabled(auditRefCO);
			} else {
				return false;
			}
		} catch (BaseException e) {
			handleException(e, null, null);
		}
		return false;
	}

	public boolean isActionAuditable() {
		return is_enableAudit();

	}

	/**
	 * 
	 * @param primary keys of the table
	 * @return primary keys separated with '|' delimiter
	 */
	public String generateTrxNbr(Object... keys) {
		StringBuffer trxNbr = new StringBuffer();
		for (int i = 0; i < keys.length; i++) {
			trxNbr.append(keys[i]);
			trxNbr.append(i == keys.length - 1 ? "" : "|");
		}
		return trxNbr.toString();
	}

	/**
	 * Method to call audit for retrieve and set the audit trx number
	 * 
	 * @param auditKeyRef the keyref of the window to be audited
	 * @param auditVO     the window value object (row)fromBusiness value object
	 * @param auditObject Business value object from the corresponding java bean
	 * @param list        of properties to be excluded from serialization
	 */
	public void applyRetrieveAudit(String auditKeyRef, Object auditVO, Object auditObject) {
		applyRetrieveAudit(auditKeyRef, auditVO, auditObject, null);
	}

	/**
	 * Method to call audit for retrieve and set the audit trx number and special
	 * comp/branch codes
	 * 
	 * @param auditKeyRef the keyref of the window to be audited
	 * @param auditVO     the window value object (row)fromBusiness value object
	 * @param auditObject Business value object from the corresponding java bean
	 * @param specRefCO   Special AuditRefCO in case dev need to specify different
	 *                    comp/branch code
	 */
	public void applyRetrieveAudit(String auditKeyRef, Object auditVO, Object auditObject, AuditRefCO specRefCO) {
		try {
			putAuditObject(auditObject);
			AuditRefCO refCO = initAuditRefCO();
			refCO.setOperationType(AuditConstant.RETRIEVE);
			refCO.setKeyRef(auditKeyRef);
			if (specRefCO != null) {
				if (StringUtil.isNotEmpty(specRefCO.getFieldAuditDetails())) {
					setFieldAuditDetails(specRefCO.getFieldAuditDetails());
				}
				if (!NumberUtil.isEmptyDecimal(specRefCO.getSmartSpecCompCode())
						|| !NumberUtil.isEmptyDecimal(specRefCO.getSmartSpecBranchCode())) {
					setSmartSpecCompCode(specRefCO.getSmartSpecCompCode());
					setSmartSpecBranchCode(specRefCO.getSmartSpecBranchCode());
				}

				// 691148 Field & CIF Audit
				if (specRefCO.getCifToAudit() != null && specRefCO.getCifToAudit().size() > 0) {
					refCO.setCifToAudit(specRefCO.getCifToAudit());
				}
			}
			auditBO.callAudit(null, auditVO, refCO);
			setAuditTrxNbr(auditBO.checkAuditKey(auditVO, refCO));
		} catch (Exception e) {
			log.error(e, "ERROR in applyRetrieveAudit BaseAction");
			handleException(e, null, null);
		}

	}

	/**
	 * Method to call audit for reports (retrieve and export)
	 * 
	 * @param reportArgs the map that contains the report parameters
	 * @param opType     Operation type string (RETRIEVE/EXPORT)
	 * @param menu       String representing the report reference.
	 */
	public void applyReportAudit(Map reportArgs, String opType, String menu) {
		applyReportAudit(reportArgs, opType, menu, null);
	}

	/**
	 * Method to call audit for reports (retrieve and export)
	 * 
	 * @param reportArgs   the map that contains the report parameters
	 * @param opType       Operation type string (RETRIEVE/EXPORT)
	 * @param menu         String representing the report reference.
	 * @param auditAppName String application Name for audit, since reports is
	 *                     audited in their corresponding applications.
	 */
	public void applyReportAudit(Map reportArgs, String opType, String menu, String auditAppName) {
		applyReportAudit(reportArgs, opType, menu, auditAppName, null);
	}

	/**
	 * Method to call audit for reports (retrieve and export) // 691148 Field & CIF
	 * Audit
	 * 
	 * @param reportArgs     the map that contains the report parameters
	 * @param opType         Operation type string (RETRIEVE/EXPORT)
	 * @param menu           String representing the report reference.
	 * @param auditAppName   String application Name for audit, since reports is
	 *                       audited in their corresponding applciations.
	 * @param cifListToAudit List of CIFs to be audited.
	 */
	public void applyReportAudit(Map reportArgs, String opType, String menu, String auditAppName,
			List<BigDecimal> cifListToAudit) {
		try {
			AuditRefCO refCO = initAuditRefCO(auditAppName);
			refCO.setOperationType(opType);
			refCO.setKeyRef(AuditConstant.REPORTS_KEY_REF);
			refCO.setProgRef(menu);
			refCO.setTrxNbr(menu);
			HashMap<String, Object> newArgsMap = new HashMap<String, Object>();
			if (reportArgs != null) {
				reportArgs.remove("repParamsCO");
				newArgsMap = (HashMap<String, Object>) reportArgs;
			}
			if (cifListToAudit != null && cifListToAudit.size() > 0) {
				refCO.setCifToAudit(cifListToAudit);
			}
			auditBO.callAudit(AuditConstant.REPORTS_KEY_REF, newArgsMap, refCO);
		} catch (Exception e) {
			log.error(e, "ERROR in applyReportAudit BaseAction");
			handleException(e, null, null);
		}

	}

	/**
	 * fills from grid updates the lst of added / modified records each in its
	 * corresponding list
	 * 
	 * @param updates   json string of updates
	 * @param beanClass VO class to fill the arraylist with, the resulted list will
	 *                  be list of this class VO
	 * @return
	 */
	public GridUpdates getGridUpdates(String updates, Class beanClass) {
		return getGridUpdates(updates, beanClass, false);
	}

	/**
	 * 
	 * @param updates
	 * @param beanClass
	 * @param allGridData
	 * @return
	 */
	public GridUpdates getGridUpdates(String updates, Class beanClass, boolean allGridData) {
		GridUpdates gridUpdates = new GridUpdates();
		JSONObject jsonFilter = (JSONObject) JSONSerializer.toJSON(updates);
		JSONArray jsonModel = jsonFilter.getJSONArray("root");
		Object[] modelArr = jsonModel.toArray();
		JSONObject obj;
		HashMap<String, Object> hm;
		String keyVal = null;
		Class<?> propType;
		try {
			for (int i = 0; i < modelArr.length; i++) {
				obj = (JSONObject) modelArr[i];
				hm = (HashMap) JSONObject.toBean(obj, HashMap.class);
				Object mainObj = beanClass.newInstance();
				Object tempObj = mainObj;
				for (Map.Entry<String, Object> e : hm.entrySet()) {
					tempObj = mainObj;
					keyVal = e.getKey();
					String[] keyArr = keyVal.split("\\.");

					if (keyArr.length > 1) // nested objects
					{
						tempObj = null;
					}
					for (int k = 0; k < keyArr.length; k++) {
						if ((tempObj != null || keyArr.length == 1)
								&& PropertyUtils.isReadable(tempObj, keyArr[keyArr.length - 1])
								&& PropertyUtils.isWriteable(tempObj, keyArr[keyArr.length - 1])) {
							propType = PropertyUtils.getPropertyType(tempObj, keyArr[keyArr.length - 1]);
							// in case of empty values for non string types we
							// need to put manually null
							if (!propType.isPrimitive() && !propType.getCanonicalName().contains("String")
									&& (e.getValue()) instanceof String) {
								if (((String) (e.getValue())).trim().isEmpty()
										|| ((String) (e.getValue())).replaceAll(" ", "").isEmpty()) {
									// if numeric Field then need to set as EmptyBigDecimal
									if (propType.getCanonicalName().contains("BigDecimal")) {
										hm.put(keyVal, ConstantsCommon.EMPTY_BIGDECIMAL_VALUE);
									} else {
										hm.put(keyVal, null);
									}
								} else {
									// dates should be parsed using the standard date format from session
									if (propType.getCanonicalName().contains("Date")) {
										if (StringUtil.nullToEmpty(((String) e.getValue())).isEmpty()
												|| (((String) e.getValue()).length() == 1
														&& ((String) e.getValue()).codePointAt(0) == 160)) // empty gets
																											// encoded
																											// in the
																											// url from
																											// IE, use
																											// code 160
																											// instead
										{
											hm.put(keyVal, null);
										} else {
											Date d = DateUtil.returnDateObj((String) e.getValue(), session);
											hm.put(keyVal, d);
										}
									} else {
										hm.put(keyVal, propType.getConstructor(String.class)
												.newInstance((String) e.getValue()));
									}
								}

							}
							if (obj.get(keyVal).getClass().toString().contains("JSONObject")) {
								PropertyUtils.setProperty(tempObj, keyArr[keyArr.length - 1],
										obj.get(keyVal).toString());
							} else {
								// In case BigDecimal retrieved from js as number not as string
								if (!(e.getValue() instanceof String)
										&& propType.getCanonicalName().contains("BigDecimal")) {
									PropertyUtils.setProperty(tempObj, keyArr[keyArr.length - 1],
											new BigDecimal(e.getValue().toString()));
								} else {
									PropertyUtils.setProperty(tempObj, keyArr[keyArr.length - 1], e.getValue());
								}
							}
						} else if (PropertyUtils.isReadable(mainObj, keyArr[k])
								&& PropertyUtils.isWriteable(mainObj, keyArr[k])) {
							if (PropertyUtils.getProperty(mainObj, keyArr[k]) == null) {
								PropertyUtils.setProperty(mainObj, keyArr[k],
										PropertyUtils.getPropertyType(mainObj, keyArr[k]).newInstance());
							}
							tempObj = PropertyUtils.getProperty(mainObj, keyArr[k]);
						} else if (PropertyUtils.isReadable(tempObj, keyArr[k])
								&& PropertyUtils.isWriteable(tempObj, keyArr[k])) {
							if (PropertyUtils.getProperty(tempObj, keyArr[k]) == null) {
								PropertyUtils.setProperty(tempObj, keyArr[k],
										PropertyUtils.getPropertyType(tempObj, keyArr[k]).newInstance());
							}
							tempObj = PropertyUtils.getProperty(tempObj, keyArr[k]);
						}
					}
				}
				if (allGridData) {
					gridUpdates.getLstAllRec().add(mainObj);
				} else {

					if (hm.get("CHANGED") == null) {
						if (hm.get("ADDED") == null) {
							if (hm.get("DELETED") != null) {
								gridUpdates.getLstDelete().add(mainObj);
							}
						} else {
							gridUpdates.getLstAdd().add(mainObj);
						}
					} else {
						gridUpdates.getLstModify().add(mainObj);
					}
				}
			}
		} catch (Exception e) {
			log.error(e, "ERROR in getGridUpdates BaseAction");
			e.printStackTrace();
		}

		return gridUpdates;
	}

	/**
	 * Used for replacing the parameters of the message by their translation if
	 * exists
	 * 
	 * @param cause exception object
	 * @return
	 */

	private Exception translateMsgParams(Exception cause) throws Exception {
		if (cause instanceof BOException) {
			BaseException baseEx = (BaseException) cause;
			ArrayList<BaseException> exceptionsLst = baseEx.getExceptionsLst();
			// multiple exceptions
			if (exceptionsLst == null || exceptionsLst.isEmpty()) {
				if (baseEx.getParams() != null) {
					String[] msgParams = baseEx.getParams();
					for (int i = 0; i < msgParams.length; i++) {
						if (msgParams[i] != null) {
							if (msgParams[i].startsWith("[") && msgParams[i].endsWith("]")) {
								// check if the parameter enclosed with [] means that list of keys need to be
								// translated and concatinated
								// to be replaced in single parameter
								String paramKeys = msgParams[i].substring(1, msgParams[i].length() - 1);
								String[] parKeys = paramKeys.split(",");
								StringBuffer concatParams = new StringBuffer();
								for (int j = 0; j < parKeys.length; j++) {
									if (!parKeys[j].trim().isEmpty()) {
										if (!concatParams.toString().isEmpty()) {
											concatParams.append(", ");
										}
										concatParams.append(getText(parKeys[j].trim()));
									}
								}
								baseEx.getParams()[i] = concatParams.toString();
							} else {
								baseEx.getParams()[i] = getText(msgParams[i]);
							}
						}
					}
				}

			} else {
				for (int i = 0; i < exceptionsLst.size(); i++) {
					String[] msgParams = exceptionsLst.get(i).getParams();
					if (msgParams != null) {
						for (int j = 0; j < msgParams.length; j++) {
							if (msgParams[j] != null) {
								baseEx.getExceptionsLst().get(i).getParams()[j] = getText(msgParams[j]);
							}
						}
					}
				}
			}
			return baseEx;
		}
		return cause;
	}

	/**
	 * 
	 * Used for returning the business field translation , Tooltip to the element
	 * constructed on Page
	 * 
	 * @param reqFldSC
	 * @return
	 */
//    public String returnToolTipTrans(RequiredFieldsSC reqFldSC) 
//    {
//	String toolTip = null;
//	// if language not provided then Take "EN" as Default
//	String lang = StringUtil.nullEmptyToValue(reqFldSC.getLangCode(), ConstantsCommon.LANGUAGE_ENGLISH); 
//	String progRef = StringUtil.nullEmptyToValue(reqFldSC.getProgRef(),ConstantsCommon.PROGREF_ROOT);
//	String appName = StringUtil.nullEmptyToValue(reqFldSC.getAppName(),ConstantsCommon.IMAL_APP_NAME);
//	String elemId = StringUtil.nullToEmpty(reqFldSC.getElementId());
//	// resetting the parameters again in case of null
//	reqFldSC.setLangCode(lang);
//	reqFldSC.setAppName(appName);
//	reqFldSC.setProgRef(progRef);
//	reqFldSC.setElementId(elemId);
//	
//	String elemName = StringUtil.nullToEmpty(reqFldSC.getElementName());
////	HashMap<String,Map<String,FieldsBusTransCO>> transMap = CachedConstantsCommon.busTransInfo;
//	String theKey = lang+"_"+appName+"_"+progRef;
//	String curKey = theKey;
////	Map<String,FieldsBusTransCO> currTransMap = transMap.get(theKey);
////	FieldsBusTransCO curVO ;
//	if(currTransMap == null)
//	{
//	    Map<String, FieldsBusTransCO> elemTranMap = null;
//	    try
//	    {
//		transMap.put(curKey, elemTranMap);
//	    }
//	    catch(Exception e)
//	    {
//		log.error(e, "Error in returnToolTipTrans while retriveing Business Translation from DB");
//	    }
//	    finally
//	    {
//		// check if Application and ProgRef Translation Map Available, 
//		// technically should not come null from database
//		if(elemTranMap == null)
//		{
//		    transMap.put(curKey, new HashMap());
//		    
//		}
//		else
//		{
//
//		    // check if the key for Element Available in the Map
//		    curVO = transMap.get(curKey).get(elemName);
//		    if(curVO == null)
//		    {
//			curKey = lang + "_" + appName + "_" + ConstantsCommon.PROGREF_ROOT;
//			elemTranMap = transMap.get(curKey);
//			try
//			{
//				if(elemTranMap == null)
//				{
//				    // get the busTrans of ROOT OPT and specific Application
//				    reqFldSC.setProgRef(ConstantsCommon.PROGREF_ROOT);
//				    curKey = lang + "_" + appName + "_" + ConstantsCommon.PROGREF_ROOT;
//				    transMap.put(curKey, elemTranMap);
//				}
//			}
//			catch(Exception e)
//			{
//			    elemTranMap = null;
//			    log.error(e, "Error in returnToolTipTrans while retriveing Business Translation from DB of ROOT OPT for appName = "+appName);
//			}
//			finally
//			{
//			    if(elemTranMap == null)
//			    {
//				transMap.put(curKey, new HashMap());
//				
//			    }
//			    else
//			    {
//				// check if the key for Element Available in the Map of ROOT OPT
//				curVO = transMap.get(curKey).get(elemName);
//				if(curVO != null)
//				{
//				    toolTip = curVO.getTOOLTIP_VALUE();
//				}
//			    }
//			}
//			
//		    }
//		    else
//		    {
//			toolTip = curVO.getTOOLTIP_VALUE();
//		    }
//		}
//	    }
//	}
//	else
//	{
//	    curVO = currTransMap.get(elemName);
//    	    if(curVO != null)
//    	    {
//            	    toolTip = curVO.getTOOLTIP_VALUE();
//    	    }
//    	    // check if tooltip not found then try to check in global OPT
//    	    if(toolTip == null && !progRef.equals(ConstantsCommon.PROGREF_ROOT))
//    	    {
//    		theKey = lang + "_" + appName + "_" + ConstantsCommon.PROGREF_ROOT;
//    		currTransMap = transMap.get(theKey);
//    		if(currTransMap != null)
//    		{
//            		curVO = currTransMap.get(elemName);
//            		if(curVO != null)
//            		{
//            		    toolTip = curVO.getTOOLTIP_VALUE();
//            		}
//    		}
//    	    }
//    	    // check if tooltip not found then try to check in global
//    	    // application (IMAL) and global OPT
//    	    if(toolTip == null && !appName.equals(ConstantsCommon.IMAL_APP_NAME))
//    	    {
//    		theKey = lang + "_" + ConstantsCommon.IMAL_APP_NAME + "_" + ConstantsCommon.PROGREF_ROOT ;
//    		currTransMap = transMap.get(theKey);
//    		if(currTransMap != null)
//    		{
//            		curVO = currTransMap.get(elemName);
//            		if(curVO != null)
//            		{
//            		    toolTip = curVO.getTOOLTIP_VALUE();
//            		}
//    		}
//    	    }
//	}
//	
//	return toolTip;
//    }

	/**
	 * This function will translate all keys in a message like <key1>xxxx<key2>yyyyy
	 * The tanslation of the keys are taken from .properties files
	 * 
	 * @author nabilfeghali
	 * @param message
	 * @return
	 */
	public String translateMatchingMessage(String message) {
		String translatedResult = null;
		if (StringUtil.isNotEmpty(message)) { // Or use Pattern.compile("(~#~(.*?)~#~)(.)");
												// Pattern pattern = Pattern.compile("(<(.*?)>)(.*?)");
			Pattern pattern = Pattern.compile("(<(.*?)>)(.*?)");
			Matcher matcher = pattern.matcher(message);

			StringBuffer result = new StringBuffer();
			while (matcher.find()) {
				matcher.appendReplacement(result, getText(matcher.group(2)) + matcher.group(3));
			}
			matcher.appendTail(result);
			translatedResult = result.toString();
		}
		return translatedResult;
	}

	/**
	 * This function will translate all keys in a message like <key1>xxxx<key2>yyyyy
	 * by a specified language The tanslation of the keys are taken from .properties
	 * files
	 * 
	 * @author nabilfeghali
	 * @param message
	 * @return
	 */
	public String translateMatchingMessageByLang(String message, String language) {
		String translatedResult = null;

		if (message != null && language != null) {
			Locale requestedLocale = null;
			requestedLocale = new Locale(language.toLowerCase(Locale.ENGLISH));
			if (requestedLocale != null) {
				Locale oldLocale = ActionContext.getContext().getLocale();
				ActionContext.getContext().setLocale(requestedLocale);
				translatedResult = translateMatchingMessage(message);
				ActionContext.getContext().setLocale(oldLocale);
			}
		}
		return translatedResult;
	}

	/**
	 * this function will translate a key based on the language passed in parameters
	 * The language should be passed from ConstantsCommon like below :
	 * ConstantsCommon.LANGUAGE_ENGLISH ConstantsCommon.LANGUAGE_FRENCH
	 * ConstantsCommon.LANGUAGE_ARABIC
	 * 
	 * @author nabilfeghali
	 * @param messageKey
	 * @param requestedLocale
	 * @return
	 */
	public String translateKeyByLang(String messageKey, String language) {
		String translatedKey = null;
		if (messageKey != null && language != null) {
			Locale requestedLocale = null;
			requestedLocale = new Locale(language.toLowerCase(Locale.ENGLISH));
			if (requestedLocale != null) {
				Locale oldLocale = ActionContext.getContext().getLocale();
				ActionContext.getContext().setLocale(requestedLocale);
				translatedKey = getText(messageKey);
				ActionContext.getContext().setLocale(oldLocale);
			}
		}
		return translatedKey;
	}

	/**
	 * 
	 * Used for returning the Key label translation to the element constructed on
	 * Page
	 * 
	 * @param String key
	 * @return String the key translation
	 */
	public String returnKeyTrans(String key) {
		return returnKeyTrans(key, null, null);
	}

	/**
	 * Method to translate a key using provided application
	 * 
	 * @param key        the key to be translated
	 * @param theAppName application name
	 * @param theProgRef prog Reference for the translation
	 * @return
	 */
	protected String returnKeyTrans(String key, String theAppName, String theProgRef) {
		// 440134 Translation Keys with Non Case Sensitivity BLME Issue
		try {
			if (ConstantsCommon.DB_CASE_SENSITIVITY.equals(BigDecimal.TEN)) {
			}
		} catch (Exception e1) {
			log.error(e1, "Exception in returnKeyTrans: error getting PTH_CTRLVO object");
		}

		key = StringUtil.nullToEmpty(key);
		String originalKey = key;

		if (BigDecimal.ONE.equals(ConstantsCommon.DB_CASE_SENSITIVITY)) {
			key = key.toLowerCase();
		}
		String transVal = "", lang, progRef, appName;
		try {
			String language = null;
			String app = theAppName;
			try {
				SessionCO sessionCO = returnSessionObject();
				// if Session is null then get the default values
				if (sessionCO != null) {
					language = sessionCO.getLanguage();
					if (theAppName == null) {
						app = sessionCO.getCurrentAppName();
					}
				}
			} catch (Exception e) {
				log.warning(
						"Exception in returnKeyTrans: error in session object, Can Occur upon User Set Off [can be Ignored], Message "
								+ e.getMessage());
			}

			String currLocLang = getLocale().getLanguage();
			// if language in the session is null, then take the Locale_language
			if (language == null) {
				language = StringUtil.nullEmptyToValue(
						ActionContext.getContext().getLocale().getLanguage().toUpperCase(),
						ConstantsCommon.LANGUAGE_ENGLISH);
			}

			// temporary to change the language if locale changed for section
			// specific language translation, to be replaced with reading
			// language from Map of iso code and language from
			// SYS_PARAM_LANGUAGES table

			if (StringUtil.nullToEmpty(currLocLang).equals("ar") && !ConstantsCommon.LANGUAGE_ARABIC.equals(language)) {
				language = ConstantsCommon.LANGUAGE_ARABIC;
			}

			// if language not provided then Take "EN" as Default
			lang = StringUtil.nullEmptyToValue(language, ConstantsCommon.LANGUAGE_ENGLISH);
			progRef = StringUtil.nullEmptyToValue(theProgRef == null ? get_pageRef() : theProgRef,
					ConstantsCommon.PROGREF_ROOT);
			appName = StringUtil.nullEmptyToValue(app, ConstantsCommon.IMAL_APP_NAME);

			HashMap<String, HashMap> cashedMap = CachedConstantsCommon.keyLabelTransMap;

			String theKey;
			HashMap<String, String> currTransMap;

			// Check if a translation exist using the lang + appName + progRef
			theKey = lang + "_" + appName + "_" + progRef;
			currTransMap = cashedMap.get(theKey);
			if (currTransMap == null) {
				currTransMap = synchronizedLoadTrans(lang, appName, cashedMap, progRef);
			}
			if (currTransMap != null) {
				transVal = currTransMap.get(key);
				if (StringUtil.isNotEmpty(transVal)) {
					return transVal;
				}
			}
			String parentRef = "";
			String[] optDet;
			HashMap<String, String[]> optHM = CachedConstantsCommon.optHm;
			// check if not Root since it has no parent
			if (!ConstantsCommon.PROGREF_ROOT.equals(progRef)) {
				// Get the parentRef of the current progRef
				optDet = optHM.get(appName + "_" + progRef);

				if (optDet == null) {
					optHM.put(appName + "_" + progRef, optDet);
				}
				if (optDet != null) {
					parentRef = optDet[3];
				}

				if (StringUtil.isNotEmpty(parentRef)) {
					// Check if a translation exist using the lang + appName +
					// parentRef
					theKey = lang + "_" + appName + "_" + parentRef;
					currTransMap = cashedMap.get(theKey);
					if (currTransMap == null) {
						currTransMap = synchronizedLoadTrans(lang, appName, cashedMap, parentRef);
					}
					if (currTransMap != null) {
						transVal = currTransMap.get(key);
						if (StringUtil.isNotEmpty(transVal)) {
							return transVal;
						}
					}
				}

				// Check if a translation exist using the lang + appName + ROOT
				theKey = lang + "_" + appName + "_" + ConstantsCommon.PROGREF_ROOT;
				currTransMap = cashedMap.get(theKey);
				if (currTransMap == null) {
					currTransMap = synchronizedLoadTrans(lang, appName, cashedMap, ConstantsCommon.PROGREF_ROOT);

				}
				if (currTransMap != null) {
					transVal = currTransMap.get(key);
					if (StringUtil.isNotEmpty(transVal)) {
						return transVal;
					}
				}
			}
			// Check if a translation exist using the lang + IMAL + ROOT
			theKey = lang + "_" + ConstantsCommon.IMAL_APP_NAME + "_" + ConstantsCommon.PROGREF_ROOT;
			currTransMap = cashedMap.get(theKey);
			if (currTransMap == null) {
				currTransMap = synchronizedLoadTrans(lang, ConstantsCommon.IMAL_APP_NAME, cashedMap,
						ConstantsCommon.PROGREF_ROOT);
			}
			if (currTransMap != null) {
				transVal = currTransMap.get(key);
				if (StringUtil.isNotEmpty(transVal)) {
					return transVal;
				}
			}
		}

		catch (Exception e) {
			log.error(e, "Error in returnAllKeyLabelTrans while retrieving Key Label Translation from DB");
		}

		return originalKey;
	}

	/**
	 * Make the load of translation in synchronised way since it will be cached
	 * 
	 * @param lang
	 * @param appName
	 * @param cashedMap
	 * @param parentRef
	 * @return
	 * @throws BaseException
	 */
	private synchronized HashMap<String, String> synchronizedLoadTrans(String lang, String appName,
			HashMap<String, HashMap> cashedMap, String parentRef) throws BaseException {
		HashMap<String, String> currTransMap;
		String theKey = lang + "_" + appName + "_" + parentRef;
		currTransMap = cashedMap.get(theKey);
		if (currTransMap == null) {
			currTransMap = cashedMap.get(theKey);
		}
		return currTransMap;
	}

	/**
	 * Retrun entity Type Info and cached it on the client side
	 * 
	 * @author marwanmaddah
	 * @date Apr 23, 2014
	 * @param sysParamScreenEntityType
	 * @return
	 * @throws BaseException SYS_PARAM_SCREEN_ENTITY_TYPEVO
	 *
	 */
//	public List<SYS_PARAM_SCREEN_ENTITY_TYPEVO> returnScreenEntityTypeInfos(
//			SYS_PARAM_SCREEN_ENTITY_TYPEVO sysParamScreenEntityType) throws BaseException {
//		HashMap<String, HashMap> infoMap = CachedConstantsCommon.findInfo;
//		List<SYS_PARAM_SCREEN_ENTITY_TYPEVO> result = null;
//		String sysPrmScrnEntTypeMapKey = sysParamScreenEntityType.getAPP_NAME().concat("_")
//				.concat(sysParamScreenEntityType.getPROG_REF());
//		HashMap<String, Object> objectMap = infoMap.get(entityType_key);
//		if (objectMap == null || !objectMap.containsKey(sysPrmScrnEntTypeMapKey)) {
//			if (result != null) {
//				if (objectMap == null) {
//					objectMap = new HashMap<String, Object>();
//					objectMap.put(sysPrmScrnEntTypeMapKey, result);
//					infoMap.put(entityType_key, objectMap);
//				} else {
//					objectMap.put(sysPrmScrnEntTypeMapKey, result);
//				}
//			}
//		} else {
//			result = (List<SYS_PARAM_SCREEN_ENTITY_TYPEVO>) objectMap.get(sysPrmScrnEntTypeMapKey);
//		}
//		return result;
//	}

	/**
	 * 
	 * Used for checking user privileges for Label Translation window
	 *
	 */
	protected String checkLabelTrans() throws BaseException {
		return returnAccessRightByProgRef(ConstantsCommon.LABELING_CONFIG_OPT);
	}

	/**
	 * 
	 * Used for setting user privileges
	 *
	 */
	protected String checkUserPrivileges() throws BaseException {
		return returnAccessRightByProgRef(ConstantsCommon.SETTINGS_CONFIG_OPT);
	}

	/**
	 * setting commonLibBO once
	 * 
	 * @return
	 * @throws BaseException
	 */
	/**
	 * method to clear the variables in BaseServiceImpl portal class
	 * 
	 * @throws BaseException
	 */
	protected void clearBaseServiceCache() throws BaseException {
		baseServices.clearBaseServiceCache();
	}

	public void set_auditJsonStr(String _auditJsonStr) {
		this._auditJsonStr = _auditJsonStr;
	}

	public String get_auditJsonStr() {
		return _auditJsonStr;
	}

	public void set_pageRef(String _pageRef) {
		this._pageRef = _pageRef;
	}

	public String get_pageRef() {
		return _pageRef;
	}

	public void set_enableAudit(boolean _enableAudit) {
		this._enableAudit = _enableAudit;
	}

	public boolean is_enableAudit() {
		return _enableAudit;
	}

	public String get_error() {
		return _error;
	}

	public String get_dependencyMsg() {
		return _dependencyMsg;
	}

	public void set_dependencyMsg(String dependencyMsg) {
		_dependencyMsg = dependencyMsg;
	}

	public String get_depMsgEltId() {
		return _depMsgEltId;
	}

	public void set_depMsgEltId(String depMsgEltId) {
		_depMsgEltId = depMsgEltId;
	}

	public String getIv_crud() {
		return iv_crud;
	}

	public void setIv_crud(String ivCrud) {
		iv_crud = ivCrud;
	}

	public void setBaseServices(BaseServices baseServices) {
		this.baseServices = baseServices;
	}

	public void setAuditBO(AuditBO auditBO) {
		this.auditBO = auditBO;
	}

//    public GridUpdates getGridUpdates()
//    {
//	return gridUpdates;
//    }
//
//    public void setGridUpdates(GridUpdates gridUpdates)
//    {
//	this.gridUpdates = gridUpdates;
//    }

	public String getAuditMode() {
		return auditMode;
	}

	public void setAuditMode(String auditMode) {
		this.auditMode = auditMode;
	}

	public String getAuditTrxNbr() {
		return auditTrxNbr;
	}

	public void setAuditTrxNbr(String auditTrxNbr) {
		this.auditTrxNbr = auditTrxNbr;
	}

	public String get_recReadOnly() {
		return _recReadOnly;
	}

	public void set_recReadOnly(String recReadOnly) {
		_recReadOnly = recReadOnly;
	}

	public String get_searchGridId() {
		return _searchGridId;
	}

	public void set_searchGridId(String searchGridId) {
		_searchGridId = searchGridId;
	}

	public String get_confirm() {
		return _confirm;
	}

	public void set_confirm(String confirm) {
		_confirm = confirm;
	}

	public String get_menuInfo() {
		return _menuInfo;
	}

	public void set_menuInfo(String menuInfo) {
		_menuInfo = menuInfo;
	}

	public String get_showNewInfoBtn() {
		return _showNewInfoBtn;
	}

	public void set_showNewInfoBtn(String showNewInfoBtn) {
		_showNewInfoBtn = showNewInfoBtn;
	}

//	public HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO> getAdditionalScreenParams() {
//		return additionalScreenParams;
//	}

//	public void setAdditionalScreenParams(HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO> additionalScreenParams) {
//		// iterate to translate the Label key if available
//		HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO> theParams = additionalScreenParams;
//		if (theParams != null) {
//			for (Map.Entry<String, SYS_PARAM_SCREEN_DISPLAYVO> e : theParams.entrySet()) {
//				String key = e.getKey();
//				SYS_PARAM_SCREEN_DISPLAYVO currVO = theParams.get(key);
//				if (currVO != null && currVO.getLABEL_KEY() != null) {
//					currVO.setLabelKeyVal(currVO.getLABEL_KEY());
//					currVO.setLABEL_KEY(getText(currVO.getLABEL_KEY()));
//				}
//			}
//		}
//		this.additionalScreenParams = theParams;
//	}

//	public HashMap<String, SYS_PARAM_OBJ_DISPLAYVO> getAdditionalObjParams() {
//		return additionalObjParams;
//	}
//
//	public void setAdditionalObjParams(HashMap<String, SYS_PARAM_OBJ_DISPLAYVO> additionalObjParams) {
//		this.additionalObjParams = additionalObjParams;
//	}

	/**
	 * return access right for given ProgRef and current application of the logged
	 * in user, pay attention if inter application call done then destination
	 * application rights performed.
	 * 
	 * @param progRef
	 * @return
	 */
	public String returnAccessRightByProgRef(String progRef) {
		SessionCO sesCO = returnSessionObject();
		BigDecimal compCode = sesCO.getCompanyCode();
		BigDecimal branchCode = sesCO.getBranchCode();
		if (NumberUtil.isEmptyDecimal(compCode) || NumberUtil.isEmptyDecimal(branchCode)) {
			return null;
		} else {
			return returnAccessRightByProgRef(progRef, sesCO.getCurrentAppName());
		}
	}

	/**
	 * return access right for given ProgRef and Application Name, usefull when
	 * called between applications
	 * 
	 * @param progRef    prog reference
	 * @param theAppName application Name
	 * @return
	 */
	public String returnAccessRightByProgRef(String progRef, String theAppName) {
		String allowAccess = null;
		try {
			String[] oneProgRef = { progRef };
			SessionCO sesCO = returnSessionObject();
			String appName = StringUtil.nullEmptyToValue(theAppName, sesCO.getCurrentAppName());
			HashMap<String, String> allowedOPTs = returnAccessRightByProgRef(oneProgRef, appName);
			if (allowedOPTs.containsKey(progRef + "_" + appName)) {
				allowAccess = allowedOPTs.get(progRef + "_" + appName);
			}
		} catch (Exception e) {
			handleException(e, null, null);
		}
		return allowAccess;
	}

	/**
	 * return access right HashMap for given Array of ProgRef and Application Name
	 * 
	 * @param progRefList
	 * @param theAppName
	 * @return HashMap<String, String>
	 */
	protected HashMap<String, String> returnAccessRightByProgRef(String[] progRefList, String theAppName) {
		HashMap<String, String> axsPerUserHm = new HashMap<String, String>();
		try {
			// Get the cached data from the hashMap
			SessionCO sesCO = returnSessionObject();
			String appName = StringUtil.nullEmptyToValue(theAppName, sesCO.getCurrentAppName());
			HashMap axsHm = sesCO.getUserAxsMap() == null ? new HashMap() : sesCO.getUserAxsMap();
			HashMap<String, String> userOptsList = new HashMap<String, String>();
			axsPerUserHm = axsHm.get(sesCO.getUserName()) == null ? new HashMap<String, String>()
					: (HashMap<String, String>) axsHm.get(sesCO.getUserName());
			List<String> optsList = new ArrayList<String>();

			// Create a list of non existing progRef list
			for (String progRef : progRefList) {
				if (!axsPerUserHm.containsKey(progRef + "_" + appName)) {
					optsList.add(progRef);
				}
			}

			// If there are missing OPTs the get their privileges
			if (optsList.size() > 0) {
				CommonLibSC criteria = new CommonLibSC();
				criteria.setCompCode(sesCO.getCompanyCode());
				criteria.setBranchCode(sesCO.getBranchCode());
				criteria.setAppName(appName);
				criteria.setUserId(sesCO.getUserName());
				criteria.setPrivilegesToCheck(optsList);

				// Loop on the found privileges and fill the HashMap, put null
				// if the OPT have NO privilege
				for (String reqProgRef : optsList) {
				}

				// Put the new privileges into the Cached map
				axsPerUserHm.putAll(userOptsList);
				axsHm.put(sesCO.getUserName(), axsPerUserHm);
				sesCO.setUserAxsMap(axsHm);
			}
		} catch (Exception e) {
			handleException(e, null, null);
		}
		return axsPerUserHm;
	}

	/**
	 * Used to return grid Columns index onLoad
	 * 
	 * @author marwanmaddah
	 * @date Oct 23, 2014
	 * @throws BaseException void
	 *
	 */
	public String returnObjectColumnsOrder(String objectId, String objectType) {
		String result = null;
		try {
			SessionCO sessionCO = returnSessionObject();
			CommonLibSC criteria = new CommonLibSC();
			criteria.setObjectId(objectId);
			criteria.setObjectType(objectType);
			criteria.setUserId(sessionCO.getUserName());
			criteria.setAppName(sessionCO.getCurrentAppName());
			criteria.setProgRef(StringUtil.nullEmptyToValue(get_pageRef(), ConstantsCommon.DEFAULT_PROG_REF));
		} catch (Exception ex) {
			handleException(ex, null, null);
		}
		return result;
	}

	/**
	 * Used to save grid columns index on reordering
	 * 
	 * @author marwanmaddah
	 * @date Oct 22, 2014 void
	 *
	 */
	public String saveObjectColumnsOrder() {
		try {
			SessionCO sessionCO = returnSessionObject();
			CommonLibSC criteria = new CommonLibSC();
			criteria.setObjectId(objectId);
			criteria.setObjectType(objectType);
			criteria.setIsDefault(isDefault);
			criteria.setUserId(sessionCO.getUserName());
			criteria.setAppName(sessionCO.getCurrentAppName());
			criteria.setProgRef(StringUtil.nullEmptyToValue(get_pageRef(), ConstantsCommon.DEFAULT_PROG_REF));
			JSONObject userPrefObj = new JSONObject();
			userPrefObj.put("orderArr", orderArr);
			String userPrefStr = userPrefObj.toString();
			criteria.setOrderArr(userPrefStr);
		} catch (Exception ex) {
			handleException(ex, null, null);
		}
		return "jsonSuccess";
	}

	/**
	 * Used to reset Grid user Preferences
	 * 
	 * @author marwanmaddah
	 * @date Oct 29, 2014
	 * @throws BaseException void
	 *
	 */
	public String resetObjectColumnsOrder() {
		try {
			SessionCO sessionCO = returnSessionObject();
			CommonLibSC criteria = new CommonLibSC();
			criteria.setObjectId(objectId);
			criteria.setObjectType(objectType);
			criteria.setUserId(sessionCO.getUserName());
			criteria.setAppName(sessionCO.getCurrentAppName());
			criteria.setProgRef(StringUtil.nullEmptyToValue(get_pageRef(), ConstantsCommon.DEFAULT_PROG_REF));
		} catch (Exception ex) {
			handleException(ex, null, null);
		}
		return "jsonSuccess";
	}

	/**
	 * 
	 * marwanmaddah
	 */
//	public List<LinkDynScrTabCO> returnAdditionalObjElements(String objectId, String objectType) {
//		List<LinkDynScrTabCO> additionalElemList = new ArrayList<LinkDynScrTabCO>();
//		try {
//			ScreenGeneratorSC criteria = new ScreenGeneratorSC();
//			SessionCO sessCO = returnSessionObject();
//			criteria.setObjectCode(objectId);
//			criteria.setObjectType(objectType);
//			criteria.setProgRef(
//					(get_pageRef() == null || get_pageRef().isEmpty()) ? ConstantsCommon.PROGREF_ROOT : get_pageRef());
//		} catch (Exception ex) {
//			handleException(ex, null, null);
//		}
//		return additionalElemList;
//	}

	/**
	 * Method that set _highlights a HashMap of tracked changes to be highlighted.
	 * 
	 * @param auditRefCO (AppName, ProgRef, TrxNbr)
	 * @throws BaseException
	 */
	public void applyChangesHighlights(AuditRefCO auditRefCO) throws BaseException {
		TRACK_CHANGES_DETAILSVO trackDtlVO = new TRACK_CHANGES_DETAILSVO();
		trackDtlVO.setAPP_NAME(auditRefCO.getAppName());
		trackDtlVO.setPROG_REF(auditRefCO.getProgRef());
		trackDtlVO.setTRX_NBR(auditRefCO.getTrxNbr());
		_highlights = auditBO.returnChangesHighlights(trackDtlVO);
	}

	/**
	 * Method to check whether the Document Management feature is enabled.
	 * 
	 * @return Boolean
	 */
//	public Boolean showDocumentMngmntSys() {
//		try {
//			SessionCO sessCO = returnSessionObject();
//			DmsRequestSC commonSC = new DmsRequestSC();
//			if (sessCO != null) {
//				commonSC.setCompCode(sessCO.getCompanyCode());
//				commonSC.setBranchCode(sessCO.getBranchCode());
//				commonSC.setAppName(sessCO.getCurrentAppName());
//				commonSC.setUserId(sessCO.getUserName());
//			} else {
//				return false;
//			}
//		} catch (Exception e) {
//			handleException(e, null, null);
//		}
//		return false;
//	}

	public String get_warning() {
		return _warning;
	}

	public void set_warning(String warning) {
		_warning = warning;
	}

	public String get_showSmartInfoBtn() {
		return _showSmartInfoBtn;
	}

	public void set_showSmartInfoBtn(String showSmartInfoBtn) {
		_showSmartInfoBtn = showSmartInfoBtn;
	}

	public String get_disRevertOldVal() {
		return _disRevertOldVal;
	}

	public void set_disRevertOldVal(String disRevertOldVal) {
		_disRevertOldVal = disRevertOldVal;
	}

	public String get_msgTitle() {
		return _msgTitle;
	}

	public void set_msgTitle(String msgTitle) {
		_msgTitle = msgTitle;
	}

	public String get_forceAfterDepEvent() {
		return _forceAfterDepEvent;
	}

	public void set_forceAfterDepEvent(String forceAfterDepEvent) {
		_forceAfterDepEvent = forceAfterDepEvent;
	}

	/**
	 * @return the _showPrintSwiftBtn
	 */
	public String get_showPrintSwiftBtn() {
		return _showPrintSwiftBtn;
	}

	/**
	 * @param showPrintSwiftBtn the _showPrintSwiftBtn to set
	 */
	public void set_showPrintSwiftBtn(String showPrintSwiftBtn) {
		_showPrintSwiftBtn = showPrintSwiftBtn;
	}

	public String get_preventAfterDepEvent() {
		return _preventAfterDepEvent;
	}

	public void set_preventAfterDepEvent(String preventAfterDepEvent) {
		_preventAfterDepEvent = preventAfterDepEvent;
	}

	public String get_SMARTReadOnly() {
		return _SMARTReadOnly;
	}

	public void set_SMARTReadOnly(String _SMARTReadOnly) {
		this._SMARTReadOnly = _SMARTReadOnly;
	}

	public String get_showRecordLogsBtn() {
		return _showRecordLogsBtn;
	}

	public void set_showRecordLogsBtn(String showRecordLogsBtn) {
		_showRecordLogsBtn = showRecordLogsBtn;
	}

	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * @param objectType the objectType to set
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	/**
	 * @param orderArr the orderArr to set
	 */
	public void setOrderArr(String orderArr) {
		this.orderArr = orderArr;
	}

	@JSON(serialize = false)
	public HashMap<String, HashMap<String, String>> get_highlights() {
		return _highlights;
	}

	public void set_highlights(HashMap<String, HashMap<String, String>> highlights) {
		_highlights = highlights;
	}

	public String get_showTrackChngBtn() {
		return _showTrackChngBtn;
	}

	public void set_showTrackChngBtn(String showTrackChngBtn) {
		_showTrackChngBtn = showTrackChngBtn;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String get_custScrElemProgRef() {
		return _custScrElemProgRef;
	}

	public void set_custScrElemProgRef(String custScrElemProgRef) {
		_custScrElemProgRef = custScrElemProgRef;
	}

	public String get_addWarning() {
		return _addWarning;
	}

	public String getExpImpCustRight() {
		return expImpCustRight;
	}

	public void setExpImpCustRight(String expImpCustRight) {
		this.expImpCustRight = expImpCustRight;
	}

	public BigDecimal getBpmTaskId() {
		return bpmTaskId;
	}

	public void setBpmTaskId(BigDecimal bpmTaskId) {
		this.bpmTaskId = bpmTaskId;
	}

	public Map<String, Object> getHmDynElems() {
		return hmDynElems;
	}

	public void setHmDynElems(Map<String, Object> hmDynElems) {
		this.hmDynElems = hmDynElems;
	}

	public String getExternalScreenYN() {
		return externalScreenYN;
	}

	public void setExternalScreenYN(String externalScreenYN) {
		this.externalScreenYN = externalScreenYN;
	}

	public BigDecimal getSmartSpecCompCode() {
		return smartSpecCompCode;
	}

	public void setSmartSpecCompCode(BigDecimal smartSpecCompCode) {
		this.smartSpecCompCode = smartSpecCompCode;
	}

	public BigDecimal getSmartSpecBranchCode() {
		return smartSpecBranchCode;
	}

	public void setSmartSpecBranchCode(BigDecimal smartSpecBranchCode) {
		this.smartSpecBranchCode = smartSpecBranchCode;
	}

	/**
	 * @return the _ignoreSpecificMenu
	 */
	public String get_ignoreSpecificMenu() {
		return _ignoreSpecificMenu;
	}

	/**
	 * @param _ignoreSpecificMenu the _ignoreSpecificMenu to set
	 */
	public void set_ignoreSpecificMenu(String _ignoreSpecificMenu) {
		this._ignoreSpecificMenu = _ignoreSpecificMenu;
	}

	public String getFieldAuditDetails() {
		return fieldAuditDetails;
	}

	public void setFieldAuditDetails(String fieldAuditDetails) {
		this.fieldAuditDetails = fieldAuditDetails;
	}

	public String get_errorFocusElem() {
		return _errorFocusElem;
	}

	public String get_dependencyConfirmMsg() {
		return _dependencyConfirmMsg;
	}

	public void set_dependencyConfirmMsg(String _dependencyConfirmMsg) {
		this._dependencyConfirmMsg = _dependencyConfirmMsg;
	}
}
