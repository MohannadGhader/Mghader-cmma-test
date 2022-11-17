package com.path.bo.omni.common.impl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.security.KeyPair;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.path.bo.common.MessageCodes;
import com.path.bo.omni.common.OmniCommonBO;
import com.path.bo.omni.common.OmniCommonConstants;
import com.path.bo.omni.common.OmniCommonMethods;
import com.path.bo.omni.common.base.OmniBaseBO;
import com.path.bo.omni.common.push.OmniCommonPushBO;
import com.path.dao.omni.common.OmniCommonDAO;
import com.path.dao.omni.common.pull.OmniCommonPullDAO;
import com.path.dao.omni.common.push.OmniCommonPushDAO;
import com.path.dbmaps.vo.AdminVO;
import com.path.dbmaps.vo.ClientCO;
import com.path.dbmaps.vo.ClientVO;
import com.path.dbmaps.vo.Clients_groupVO;
import com.path.dbmaps.vo.DeviceAdminVO;
import com.path.dbmaps.vo.DeviceDriverVO;
import com.path.dbmaps.vo.DeviceKeysSC;
import com.path.dbmaps.vo.DeviceSC;
import com.path.dbmaps.vo.DeviceVO;
import com.path.dbmaps.vo.DriverCO;
import com.path.dbmaps.vo.DriverVO;
import com.path.dbmaps.vo.EdKeysVO;
import com.path.dbmaps.vo.ErrorVO;
import com.path.dbmaps.vo.Keys;
import com.path.dbmaps.vo.Machine_driverCO;
import com.path.dbmaps.vo.NotificationVO;
import com.path.dbmaps.vo.PropertiesVO;
import com.path.dbmaps.vo.RateVO;
import com.path.dbmaps.vo.ReservationCO;
import com.path.dbmaps.vo.ReservationDetailsCO;
import com.path.dbmaps.vo.ReservationListVO;
import com.path.dbmaps.vo.ReservationSC;
import com.path.dbmaps.vo.ReservationVO;
import com.path.dbmaps.vo.StoreVO;
import com.path.dbmaps.vo.TemporaryReservationVO;
import com.path.dbmaps.vo.User;
import com.path.dbmaps.vo.WorkingHoursVO;
import com.path.lib.common.exception.BOException;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.PathPropertyUtil;
import com.path.lib.common.util.SecurityUtilsExt;
import com.path.lib.common.util.StringUtil;
import com.path.vo.omni.common.OmniCommonCO;
import com.path.vo.omni.common.base.OmniBaseSC;
import com.path.vo.omni.common.base.OmniBaseVO;
import com.path.vo.omni.common.omniuser.OmniCommonUserSC;
import com.path.ws.omni.vo.common.AddClientToGroupResponse;
import com.path.ws.omni.vo.common.AddMonthlyClientResponse;
import com.path.ws.omni.vo.common.AddNewFactureResponse;
import com.path.ws.omni.vo.common.AddStoreResponse;
import com.path.ws.omni.vo.common.CancelRecurringReservationResponse;
import com.path.ws.omni.vo.common.ChangeStringToDateRequest;
import com.path.ws.omni.vo.common.ChangeStringToDateResponse;
import com.path.ws.omni.vo.common.CheckRecurringReservationResponse;
import com.path.ws.omni.vo.common.CheckTempRecurringReservationResponse;
import com.path.ws.omni.vo.common.ConfirmReservationResponse;
import com.path.ws.omni.vo.common.ConfirmTlubTukReservationResponse;
import com.path.ws.omni.vo.common.CostResponse;
import com.path.ws.omni.vo.common.CreateGroupResponse;
import com.path.ws.omni.vo.common.CreateRecurringReservationResponse;
import com.path.ws.omni.vo.common.CreateReservationOfflineResponse;
import com.path.ws.omni.vo.common.CreateReservationResponse;
import com.path.ws.omni.vo.common.ForgetPasswordResponse;
import com.path.ws.omni.vo.common.GenerateOTPResponse;
import com.path.ws.omni.vo.common.GetDiscountResponse;
import com.path.ws.omni.vo.common.Group_template;
import com.path.ws.omni.vo.common.HandShakeRequest;
import com.path.ws.omni.vo.common.HandShakeResponse;
import com.path.ws.omni.vo.common.Notification;
import com.path.ws.omni.vo.common.RatingTripResponse;
import com.path.ws.omni.vo.common.RejectReservationResponse;
import com.path.ws.omni.vo.common.RenewMonthlyClientRequest;
import com.path.ws.omni.vo.common.RenewMonthlyClientResponse;
import com.path.ws.omni.vo.common.ReturnMonthlyClientDataRequest;
import com.path.ws.omni.vo.common.ReturnMonthlyClientDataResponse;
import com.path.ws.omni.vo.common.ReturnCostOfDistance;
import com.path.ws.omni.vo.common.ReturnCostOfDistanceResponse;
import com.path.ws.omni.vo.common.ReturnCostProperties;
import com.path.ws.omni.vo.common.ReturnListOfGroupsResponse;
import com.path.ws.omni.vo.common.ReturnListOfMachinesNearestMostFarResponse;
import com.path.ws.omni.vo.common.ReturnNearestAvailableMachineResponse;
import com.path.ws.omni.vo.common.ReturnRecurringClientsResponse;
import com.path.ws.omni.vo.common.ReturnRecurringReservationListResponse;
import com.path.ws.omni.vo.common.SaveTokenResponse;
import com.path.ws.omni.vo.common.SuspendMonthlyClientRequest;
import com.path.ws.omni.vo.common.SuspendMonthlyClientResponse;
import com.path.ws.omni.vo.common.UpdateDriverWorkingHoursResponse;
import com.path.ws.omni.vo.common.ValidateOTPResponse;

//import net.anotheria.moskito.aop.annotation.Monitor;
/**
 * 
 * @author Alaa Al Hek
 *
 *         OmniCommonBOImpl.java used to
 * 
 * 
 *
 * 
 */
//@Monitor
public class OmniCommonBOImpl extends OmniBaseBO implements OmniCommonBO {

	public final static String ERROR = "SOMETHING WENT WRONG";

	protected static Logger pathlog;

	protected OmniCommonDAO omniCommonDAO;
	protected OmniCommonPushBO omniCommonPushBO;

	protected OmniCommonPullDAO omniCommonPullDAO;
	protected OmniCommonPushDAO omniCommonPushDAO;
	protected OmniCommonMethods omniCommonMethods;

	static int VALID = 0;

	SecurityUtilsExt securityUtilsExt;
	Properties prop = new Properties();
	private static String PRODUCT_TABLE_NAME = "products";
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	DateTimeFormatter df = DateTimeFormatter.ofPattern("kk:mm");

	public void setOmniCommonPullDAO(OmniCommonPullDAO omniCommonPullDAO) {
		this.omniCommonPullDAO = omniCommonPullDAO;
	}

	public OmniCommonDAO getOmniCommonDAO() {
		return omniCommonDAO;
	}

	public void setOmniCommonDAO(OmniCommonDAO omniCommonDAO) {
		this.omniCommonDAO = omniCommonDAO;
	}

	public void setOmniCommonPushDAO(OmniCommonPushDAO omniCommonPushDAO) {
		this.omniCommonPushDAO = omniCommonPushDAO;
	}

	/**
	 * Method that returns Next value of sequence of a table
	 * 
	 * @param omniBaseSC
	 * @return
	 * @throws BaseException
	 */
	public BigDecimal returnNextValOfSequence(Object objParam) throws BaseException {
		String className = objParam.getClass().getSimpleName();
		if (className.lastIndexOf("VO") < 0) {
			throw new BOException("Class Name should be VO Object");
		}

		Field[] fields;
		if (objParam.getClass().getName().contains("WithBLOBs")) {
			fields = objParam.getClass().getSuperclass().getSuperclass().getDeclaredFields();
		} else {
			fields = objParam.getClass().getSuperclass().getDeclaredFields();
		}

		if (className.lastIndexOf("VOKey") != -1) {
			fields = objParam.getClass().getDeclaredFields();
		}
		if (fields.length != 1) {
			throw new BOException("Class Name should be one column and sequnetial Primary key");
		}
		Field pkField = fields[0];
		pkField.setAccessible(true);

		OmniBaseSC omniBaseSC = new OmniBaseSC();
		omniBaseSC.setPkColName(pkField.getName());
		omniBaseSC.setTableName(className.substring(0, className.lastIndexOf("VO")));
		return new BigDecimal(omniCommonDAO.returnNextValOfSequence(omniBaseSC));
	}

	/**
	 * return Sequence of Original Record
	 * 
	 * @param omniBaseSC
	 * @return
	 * @throws BaseException
	 */
	public BigDecimal returnRelatedSequence(Object objParam, String pkColName, BigDecimal refCode)
			throws BaseException {
		OmniBaseSC omniBaseSC = new OmniBaseSC();
		omniBaseSC.setPkColName(pkColName);
		String className = objParam.getClass().getSimpleName();
		if (className.lastIndexOf("VO") < 0) {
			throw new BOException("Class Name should be VO Object");
		}
		omniBaseSC.setTableName(className.substring(0, className.lastIndexOf("VO")));
		omniBaseSC.setRefCode(refCode);
		return new BigDecimal(omniCommonDAO.returnRelatedSequence(omniBaseSC));
	}

	/**
	 * update Ref Code of Table even if it's null
	 * 
	 * @param omniBaseSC
	 * @return
	 * @throws BaseException
	 */
	public int updateRefCode(Object objParam) throws BaseException {
		try {
			String className = objParam.getClass().getSimpleName();
			if (className.lastIndexOf("VO") < 0) {
				throw new BOException("Class Name should be VO Object");
			}

			Field[] fields;
			if (objParam.getClass().getName().contains("WithBLOBs")) {
				fields = objParam.getClass().getSuperclass().getSuperclass().getDeclaredFields();
			} else {
				fields = objParam.getClass().getSuperclass().getDeclaredFields();
			}
			if (className.lastIndexOf("VOKey") != -1) {
				fields = objParam.getClass().getDeclaredFields();
			}
			if (fields.length != 1) {
				throw new BOException("Class Name should be one column and sequnetial Primary key");
			}

			OmniBaseSC omniBaseSC = new OmniBaseSC();
			Field pkField = fields[0];
			pkField.setAccessible(true);
			omniBaseSC.setPkColName(pkField.getName());
			if (null != pkField.get(objParam)) {
				omniBaseSC.setPkColValue(new BigDecimal(StringUtil.nullEmptyToValue(pkField.get(objParam), "")));
			}

			Field refCodeField;
			if (objParam.getClass().getName().contains("WithBLOBs")) {
				refCodeField = objParam.getClass().getSuperclass().getDeclaredField(OmniCommonConstants.REFERENCE_CODE);
			} else {
				refCodeField = objParam.getClass().getDeclaredField(OmniCommonConstants.REFERENCE_CODE);
			}

			refCodeField.setAccessible(true);
			if (null != refCodeField.get(objParam)) {
				omniBaseSC.setRefCode(new BigDecimal(StringUtil.nullEmptyToValue(refCodeField.get(objParam), "")));
			}
			omniBaseSC.setTableName(className.substring(0, className.lastIndexOf("VO")));
			return omniCommonDAO.updateRefCode(omniBaseSC);
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException | SecurityException
				| NullPointerException e) {
			throw new BOException(MessageCodes.INVALID_ACTION);
		}
	}

	/*
	 * @Override public OmniCommonCO executePWSService(OmniCommonUserSC sc) throws
	 * BaseException { OmniCommonCO omniCommonCO =
	 * omniCommonPushBO.callingPWSService(sc); if
	 * (OmniCommonConstants.SUBMIT_ACTION_TYPE.equals(sc.getActionType()) ) {
	 * omniCommonCO = omniCommonPushBO.saveScreenFields(sc,omniCommonCO); } return
	 * omniCommonCO; }
	 */

	public void setOmniCommonPushBO(OmniCommonPushBO omniCommonPushBO) {
		this.omniCommonPushBO = omniCommonPushBO;
	}

	@Override
	public Object fillUserAndDate(OmniBaseVO omniBaseVO, Object objParam, String action) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal returnOmniCounter(OmniCommonCO omniCommonCO) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OmniCommonCO> returnAppChannels(OmniBaseSC omniBaseSC) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int returnApplicationListCount(OmniBaseSC omniBaseSC) throws BaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List returnApplicationList(OmniBaseSC omniBaseSC) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object checkConcurrentDate(Object objParam) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object updateRecordScreen(Object obj, String action) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object saveService(Object objToInsert) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object updateRecordScreen(String recordActionSave, OmniBaseVO omniBaseVO, Object obj) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isAllowedChannelApp(OmniBaseSC omniBaseSc) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void applyMakerCheckerValidation(String creater, String modifier, String userId) throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<String> getVOAttributes(Object vo) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object insertIntoDB(Object vo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Object> returnETLCode(Object voName, String listOfIds, String codeColumnName)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object selectRecord(Object vo, String... columnNames) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OmniCommonCO returnLanguagesList(OmniBaseSC sc) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OmniCommonCO executePWSService(OmniCommonUserSC sc) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * method that cache the frontEnd publicKey , generate and cache pair key ,
	 * 
	 * @param ClientRequest
	 * @return
	 * @throws Exception
	 * 
	 */

	@Override
	public HandShakeResponse handShake(HandShakeRequest handShakeRequest) throws Exception {
		HandShakeResponse response = new HandShakeResponse();
		try {
			log.info(" generate pair key " + format1.format(new java.util.Date()));

			KeyPair keyPair = SecurityUtilsExt.generatePairKey();

			// saving backEnd private and public key by frontEnd public Key
//			backEndKeyHashMap.put(SecurityUtilsExt.convertStringToPublicKey(handShakeRequest.getFrontEndPublicKey()),
//					keyPair);

			String str_key = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
//			byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
//			String privKeyStr = new String(Base64.getEncoder().encodeToString(privateKeyBytes));
			String privKeyStr = new String(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
//			int p = privKeyStr.length();
//			byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
//			String pubKeyStr = new String(Base64.getEncoder().encodeToString(publicKeyBytes));
			String pubKeyStr = new String(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
//			int q = pubKeyStr.length();
			EdKeysVO edKeysVO = new EdKeysVO();
			edKeysVO.setfPKey(handShakeRequest.getFrontEndPublicKey());
//			edKeysVO.setbPKey(pubKeyStr);
//			edKeysVO.setbPrKey(privKeyStr);
			log.info(" debut get frontend public key " + format1.format(new java.util.Date()));

//			EdKeysVO keysVO2 = omniCommonPullDAO.returnFrontEndPublicKey(edKeysVO);
			int i = omniCommonPullDAO.returnFrontEndPublicKey(edKeysVO);
			log.info(" fin get front end public key " + format1.format(new java.util.Date()));
			log.info(" debut insert or update " + format1.format(new java.util.Date()));
			edKeysVO.setbPKey(pubKeyStr);
			edKeysVO.setbPrKey(privKeyStr);
//			if (keysVO2 == null) {
			if (i == 0) {
				omniCommonPushDAO.insertKeys(edKeysVO);
			}
//			if (keysVO2 != null) {
			if (i == 1) {
				omniCommonPushDAO.updateKeys(edKeysVO);
			}
			log.info(" fin insert or update " + format1.format(new java.util.Date()));

//			SecretKey secretKey = securityUtilsExt.generateSecretKey();
//			String secretKeyString = Base64.getEncoder().encodeToString(secretKey.getEncoded());
			Keys keys = new Keys();
			keys.setBackEndPrivateKey(keyPair.getPrivate());
//			keys.setBackEndPublicKey(keyPair.getPublic());
			keys.setBackEndPublicKeyString(str_key);
//			keys.setSecretKey(secretKey);
//			keys.setSecretKeyString(secretKeyString);
			backEndKeyHashMap.put(SecurityUtilsExt.convertStringToPublicKey(handShakeRequest.getFrontEndPublicKey()),
					keys);
//			response.setBackEndPublicKey(keyPair.getPublic());
			response.setBackEndPublicKeyString(str_key);
//			response.setSecretKeyString(secretKeyString);
			log.info(" fin service " + format1.format(new java.util.Date()));

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public GenerateOTPResponse generateOTP(DeviceSC deviceSC) throws Exception {
		GenerateOTPResponse response = new GenerateOTPResponse();
		try {
			/*
			 * generate secretKey then generate totp
			 */
			Notification notification = omniCommonBO.loadKeyAndUrl();
			List<PropertiesVO> list = omniCommonPullDAO.returnListProperties(null);

//			prop = PropertiesLoaderUtils.loadAllProperties("Otp.properties");
//			String nString = prop.getProperty("numberOfOTP");
//			int n = Integer.parseInt(propertiesHashMap.get("numberOfOTP").getProperty_value());
//			PropertyVO propertyVO = new PropertyVO();
//			propertyVO.setProperty_name(numberOfOTP);
//			PropertyVO propertyVO2 = omniCommonPullDAO.returnProperty(propertyVO);

			int n = Integer.parseInt(list.stream().filter(l -> l.getProperty_name().equals(numberOfOTP))
					.collect(Collectors.toList()).get(0).getProperty_value());
			long numberOfSecond = Long
					.parseLong(list.stream().filter(l -> l.getProperty_name().equals(number_of_second))
							.collect(Collectors.toList()).get(0).getProperty_value());
			String user = list.stream().filter(l -> l.getProperty_name().equals(userProp)).collect(Collectors.toList())
					.get(0).getProperty_value();
			String pass = list.stream().filter(l -> l.getProperty_name().equals(passProp)).collect(Collectors.toList())
					.get(0).getProperty_value();
			String sid = list.stream().filter(l -> l.getProperty_name().equals(sidProp)).collect(Collectors.toList())
					.get(0).getProperty_value();
			String url = list.stream().filter(l -> l.getProperty_name().equals(urlProp)).collect(Collectors.toList())
					.get(0).getProperty_value();

			PropertiesVO propertyVO4 = new PropertiesVO();
			String hash = null;
			if (deviceSC.getApp() == 0)
//				propertyVO.setProperty_name(hashUser);
				hash = list.stream().filter(l -> l.getProperty_name().equals(hashUser)).collect(Collectors.toList())
						.get(0).getProperty_value();
			if (deviceSC.getApp() == 1)
//				propertyVO.setProperty_name(hashDriver);
				hash = list.stream().filter(l -> l.getProperty_name().equals(hashDriver)).collect(Collectors.toList())
						.get(0).getProperty_value();
			long now = System.currentTimeMillis();
			Date today = new Date(now);
			deviceSC.setDate(today);
			String otp_secretKey = SecurityUtilsExt.generateSecretKeyAsString();
			deviceSC.setSecretKey(otp_secretKey);

			String totp = SecurityUtilsExt.getTOTPCode(otp_secretKey, numberOfSecond);
			deviceSC.setOtp(totp);
			deviceSC.setDateTime(new java.util.Date(System.currentTimeMillis()));
//			String totp = null;
			if (deviceSC.getApp() == 0) {
				User user1 = new User();
				user1.setDevice_UUID(deviceSC.getDevice_UUID());
				user1.setDevice_name(deviceSC.getDevice_name());
				user1.setLanguage_id(deviceSC.getLanguage_id());
				DeviceVO deviceVO = omniCommonPullDAO.returnDevice(user1);
				if (deviceVO == null) {
					omniCommonPushDAO.insertDevice(user1);
//					String otp_secretKey = SecurityUtilsExt.generateSecretKeyAsString();
//					deviceSC.setOtp_secretKey(otp_secretKey);
//					totp = SecurityUtilsExt.getTOTPCode(otp_secretKey, numberOfSecond);
//					Device_otpCO device_otpCO = new Device_otpCO(deviceSC.getDevice_UUID(), totp, otp_secretKey,
//							new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), newOtp);
//					omniCommonPushDAO.insertNewOTPSecretKey(device_otpCO);
				}
//				otpClientHashmap.put(deviceSC.getDevice_UUID(), otp_secretKey);

			}
//			if (deviceSC.getApp() == 1) {
//				DriverCO driverCO = new DriverCO();
//				driverCO.setDevice_UUID(deviceSC.getDevice_UUID());
////				DeviceDriverVO deviceDriverVO = omniCommonPullDAO.returnDeviceDriver(driverCO);
//				dev
//				if (deviceDriverVO == null) {
//					omniCommonPushDAO.insertDeviceDriver(driverCO);
//				}
////				otpDriverHashmap.put(deviceSC.getDevice_UUID(), otp_secretKey);
//
//			}
			deviceSC.setDateTime(new java.util.Date(System.currentTimeMillis()));

			DeviceSC m = omniCommonPullDAO.returnNumberOfGeneratedOTP(deviceSC);
			if (m == null) {

				omniCommonPushDAO.insertNewOTP(deviceSC);
			} else {
				if (m.getCounter_generated_OTP() >= n - 1) {
					response.setErrorCode(errorGenerateOTP);
					response.setErrorDesc(omniCommonBO.getError(deviceSC.getLanguage_id(), response.getErrorCode())
							.get(0).getError_desc());
					return response;
				} else {
					/*
					 * if the number of generated otp is less than n , update the counter in the db
					 */
					deviceSC.setCounter_generated_OTP(m.getCounter_generated_OTP() + 1);
					if (m.getOtp() != null && m.getOtp_status() == newOtp) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(m.getDateTime());
						calendar.add(calendar.MINUTE, (int) (numberOfSecond / 60 / 1000));
						if (m.getDate().toString().equals(new Date(System.currentTimeMillis()).toString())
								&& new Date(System.currentTimeMillis()).before(calendar.getTime())) {
							DeviceSC deviceSC2 = omniCommonPullDAO.returnTokenDevice(deviceSC);
//							msg = omniCommonBO.getNotification(deviceSC.getLanguage_id(), OTP_CODE).get(0)
//									.getNotification_desc() + m.getOtp();
//							securityUtilsExt.pushNotification(deviceSC2.getDevice_token(), msg, notification, null,
//									null);
							securityUtilsExt.sendTotpSMS(deviceSC.getMobile_number(), m.getOtp(), user, pass, sid, url,
									hash);
							response.setOtp(m.getOtp());
							return response;

						}

					}
					omniCommonPushDAO.updateCounterGeneratedOTP(deviceSC);
				}
			}
//			omniCommonPushDAO.updateOTPSecretKey(deviceSC);

			// PropertyVO propertyVO3 = omniCommonPullDAO.returnProperty(propertyVO);
			securityUtilsExt.sendTotpSMS(deviceSC.getMobile_number(), totp, user, pass, sid, url, hash);
			response.setOtp(totp);
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	/**
	 * method that validate the OTP
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public ValidateOTPResponse validateOTP(DeviceSC deviceSC) throws Exception {
		ValidateOTPResponse response = new ValidateOTPResponse();
		if (isProbablyArabic(deviceSC.getOtp().toString())) {
			String engOtp = arabicToDecimal(deviceSC.getOtp().toString());
//			int intOtp = Integer.parseInt(engOtp);
			deviceSC.setOtp(engOtp);
		}
		String secretKey = null;
		List<PropertiesVO> list = omniCommonPullDAO.returnListProperties(null);
		int n = Integer.parseInt(list.stream().filter(l -> l.getProperty_name().equals(numberOfInvalidOTP))
				.collect(Collectors.toList()).get(0).getProperty_value());
		long numberOfSecond = Long.parseLong(list.stream().filter(l -> l.getProperty_name().equals(number_of_second))
				.collect(Collectors.toList()).get(0).getProperty_value());
		try {
			DeviceSC deviceSC3 = omniCommonPullDAO.returnCombinationMobileNumberDeviceUUID(deviceSC);
			if (deviceSC3 != null) {
				response.setResponseCode(deviceMobilbeNumberExist);
			}
//			if (deviceSC.getApp() == 0) {
////				ClientCO clientCO = new ClientCO();
////				clientCO.setDevice_UUID(deviceSC.getDevice_UUID());
////				DeviceVO deviceVO = omniCommonPullDAO.returnDevice(clientCO);
////				secretKey = otpClientHashmap.get(deviceSC.getDevice_UUID());
////				secretKey = deviceVO.getOtp_secretKey();
////				if (deviceVO == null) {
////					omniCommonPushDAO.insertDevice(clientCO);
////				}
//				
//			}
//			if (deviceSC.getApp() == 1) {
////				secretKey = otpDriverHashmap.get(deviceSC.getDevice_UUID());
//				DriverCO driverCO = new DriverCO();
//				driverCO.setDevice_UUID(deviceSC.getDevice_UUID());
//				DeviceDriverVO deviceDriverVO = omniCommonPullDAO.returnDeviceDriver(driverCO);
//				secretKey = deviceDriverVO.getOtp_secretKey();
////				}
//			}
			deviceSC.setDate(new Date(System.currentTimeMillis()));
			DeviceSC m = omniCommonPullDAO.returnNumberOfGeneratedOTP(deviceSC);
			secretKey = m.getSecretKey();
//			String secretKey = otpHashMap.get(validateOTPRequest.getMobile_number());
			boolean valid = SecurityUtilsExt.validateOTP(secretKey, deviceSC.getOtp(), numberOfSecond);
			/*
			 * n is the allowed number of time to validate the mobile number otp
			 */
			long now = System.currentTimeMillis();
			Date today = new Date(now);
			deviceSC.setDate(today);
			DeviceSC deviceSC2 = omniCommonPullDAO.returnInvalidOTP(deviceSC);
//			if (deviceSC2 == null) {
//				omniCommonPushDAO.insertNewInvalidOTP(deviceSC);
//			}
			if (valid) {
				deviceSC.setCounterOTP(unblockedDevice);
				deviceSC.setOtp_status(usedOtp);
				omniCommonPushDAO.updateOtpStatus(deviceSC);
				response.setM(0);
				response.setResponse("valid");
			}
			if (!valid) {
				if (deviceSC2 == null) {
					deviceSC.setCounterOTP(0);
				} else {
					deviceSC.setCounter_validation(deviceSC2.getCounter_validation() + 1);
				}
				response.setM(n - deviceSC.getCounter_validation());
				response.setErrorCode(errorCodeValidateOTP);
				response.setErrorDesc(
						getError(deviceSC.getLanguage_id(), response.getErrorCode()).get(0).getError_desc());
			}
			omniCommonPushDAO.updateCounterOTP(deviceSC);
//			deviceSC.setInivited_mobile_number(deviceSC.getMobile_number());
//			List<ClientVO> listOfinviteClient = omniCommonPullDAO.returnClientThatInviteTheNewMobileNumber(deviceSC);
//			if (listOfinviteClient.size() > 1) {
//				response.setResponseCode(multipleInvite);
//				response.setResponseDesc("your are invited by multiple client");
//
//				response.setListOfMobileNumber(listOfinviteClient);
//			}
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

//	private static final String arabic = "\u06f0\u06f1\u06f2\u06f3\u06f4\u06f5\u06f6\u06f7\u06f8\u06f9";

	private static String arabicToDecimal(String number) {
		char[] chars = new char[number.length()];
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch >= 0x0660 && ch <= 0x0669)
				ch -= 0x0660 - '0';
			else if (ch >= 0x06f0 && ch <= 0x06F9)
				ch -= 0x06f0 - '0';
			chars[i] = ch;
		}
		return new String(chars);
	}

	public static boolean isProbablyArabic(String s) {
		for (int i = 0; i < s.length();) {
			int c = s.codePointAt(i);
			if (c >= 0x0600 && c <= 0x06E0)
				return true;
			i += Character.charCount(c);
		}
		return false;
	}

	public DeviceKeysSC getTokenAndKeysClient(String device_UUID) throws Exception {
		String secretKey;
//		String existSecretKey = null;
		/*
		 * put in the hashmap the mobile_number , device_serial, frontEndPublicKey ,
		 * backEndPublicKey, backEndPrivateKey
		 */
//		PublicKey frontEndPublicKey = SecurityUtilsExt.convertStringToPublicKey(user.getFrontEndPublicKey());

		/*
		 * generate secretKey then generate token
		 */
		secretKey = SecurityUtilsExt.generateSecretKeyAsString();
		// to be sure each device has its own secret key , if the secret key exist in
		// the hashmap generate new one

//		if (user.getApp() == 0) {
//			ClientCO clientCO = new ClientCO();
//			clientCO.setDevice_UUID(user.getDevice_UUID());
//			DeviceVO d = omniCommonPullDAO.returnDevice(clientCO);
//			existSecretKey = d.getSecretKey();
//		}
//		if (user.getApp() == 1) {
//			DriverCO driverCO = new DriverCO();
//			driverCO.setDevice_UUID(user.getDevice_UUID());
//			DeviceDriverVO d = omniCommonPullDAO.returnDeviceDriver(driverCO);
//			existSecretKey = d.getSecretKey();
//		}
//		while (clientKeysHashMap.values().contains(secretKey)) {
//			secretKey = SecurityUtilsExt.generateSecretKeyAsString();
//		}
//		while (existSecretKey != null) {
//			secretKey = SecurityUtilsExt.generateSecretKeyAsString();
//		}
//		String token = SecurityUtilsExt.generateToken(secretKey, user.getDevice_UUID());
		String token = SecurityUtilsExt.generateToken(secretKey, device_UUID);

//		DeviceKeysSC deviceKeysSC = new DeviceKeysSC(frontEndPublicKey, secretKey, token);
//		return deviceKeysSC;
		return new DeviceKeysSC(secretKey, token);
	}

	public Notification loadKeyAndUrl() throws IOException, DAOException {
//		prop = PropertiesLoaderUtils.loadAllProperties("Note.properties");
		List<PropertiesVO> list = omniCommonPullDAO.returnListProperties(null);

		String API_URL_FCM = list.stream().filter(l -> l.getProperty_name().equals(api_url_fcm))
				.collect(Collectors.toList()).get(0).getProperty_value();
		String AUTH_KEY_FCM = list.stream().filter(l -> l.getProperty_name().equals(auth_key_fcm))
				.collect(Collectors.toList()).get(0).getProperty_value();
		;

		Notification note = new Notification(API_URL_FCM, AUTH_KEY_FCM);
		return note;
	}

//	@Override
//	public GetListOfMachinesFromNearestToMostFarResponse GetListOfMachinesFromNearestToMostFar(
//			ReservationCO reservationCO) {
//		GetListOfMachinesFromNearestToMostFarResponse response = new GetListOfMachinesFromNearestToMostFarResponse();
//		List<Machine_driverCO> listOfMachines;
//		try {
//			TimeZone zone = TimeZone.getDefault();
//			String name = zone.getDisplayName();
//			System.out.println("Display name for default time zone: " + name);
//
//			long d = System.currentTimeMillis();
//			java.util.Date date = new java.util.Date(d);
//			prop = PropertiesLoaderUtils.loadAllProperties("Velocity.properties");
//			String velocityString = prop.getProperty("velocity");
//			int velocity = Integer.parseInt(velocityString);
//			long now = System.currentTimeMillis();
//			Date today = new Date(now);
//			Time time = new Time(now);
//			reservationCO.setVelocity(velocity);
//
//			if (reservationCO.getReservation_type() == 0) {
//				listOfMachines = omniCommonPullDAO.returnListOfMachinesFromNearestToMostFarTrip(reservationCO);
//				response.setList(listOfMachines);
//			} else {
//				listOfMachines = omniCommonPullDAO.returnListOfMachinesFromNearestToMostFarMachine(reservationCO);
//				response.setList(listOfMachines);
//			}
//			List<Machine_driverCO> listAvailable = omniCommonPullDAO
//					.returnListAvailableMachinesNearestMostFar(reservationCO);
//			listAvailable.addAll(listOfMachines);
//			listAvailable.stream().sorted(Comparator.comparing(Machine_driverCO::getTimeToReachClient))
//					.collect(Collectors.toList()).forEach(l -> System.out.println(l));

//			final Map<String, Long> tableMap = new HashMap<String, Long>();
//			BinaryLogClient client = new BinaryLogClient("localhost", 3306, "root", "123456");
//
//			Pusher pusher = new Pusher("1166138", "044c0754bec6c190b7c6", "e8904c7eb0a8516bb970");
//			pusher.setCluster("eu");
//			pusher.setEncrypted(true);
//
//			client.registerEventListener(event -> {
//				EventData data = event.getData();
//				System.out.println(event);
//
//				if (data instanceof TableMapEventData) {
//					TableMapEventData tableData = (TableMapEventData) data;
//					if(tableData.getTable().equals("reservation_list") ) {
//						tableMap.put(tableData.getTable(), tableData.getTableId());
//					}
//				}
//				else if(data instanceof WriteRowsEventData) {
//                    WriteRowsEventData eventData = (WriteRowsEventData)data;
//                    
//                    if(eventData.getTableId() == tableMap.get(PRODUCT_TABLE_NAME)                 	                   	) {
//                        for(Object[] product: eventData.getRows()) {
//                            pusher.trigger(
//                               PRODUCT_TABLE_NAME, "insert", getProductMap(product)
//                            );
//                        }
//                    }
//                }
//			});
////			client.connect();
//			return response;
//		} catch (Exception e) {
//			log.info(e.getMessage());
//			System.out.println(e.getMessage());
//			return null;
//		}
//	}

	static Map<String, String> getProductMap(Object[] product) {
		Map<String, String> map = new HashMap<>();
		map.put("id", java.lang.String.valueOf(product[0]));
		map.put("name", java.lang.String.valueOf(product[1]));
		map.put("price", java.lang.String.valueOf(product[2]));

		return map;
	}

//	

	@SuppressWarnings("unused")
	@Override
	public ConfirmReservationResponse confirmReservation(ReservationCO reservationCO) throws Exception {
		ConfirmReservationResponse response = new ConfirmReservationResponse();
		try {

			/*
			 * if the reservation is one time now and one time in future
			 */
			if (reservationCO.getOccurrence_type() == 0 || reservationCO.getOccurrence_type() == 2) {
				/*
				 * insert into the reservation table the main details for reservation
				 */
//				omniCommonPushDAO.insertReservation(reservationCO);
				/*
				 * insert into the reservation_list table the details for reservation as date
				 * time and machine_driver_id
				 */
//				omniCommonPushDAO.insertReservationList(reservationCO);

				/*
				 * get the details of machine and driver reserved to return to the client
				 */
				Machine_driverCO machine = omniCommonPullDAO.returnMachineDriverDetails(reservationCO);
				response = new ConfirmReservationResponse(machine.getPlate_number(), machine.getDriver_name(),
						machine.getDriver_mobile_number(), machine.getDriver_profile());

				/*
				 * get the current time for reservation and cache it ,to use when the client
				 * want to cancel the reservation
				 */

				long timeReservation = System.currentTimeMillis();
				cancelTimerHashMap.put(reservationCO.getClient_id(), timeReservation);

			} else if (reservationCO.getOccurrence_type() == 1) { // recurring reservation
				/*
				 * insert into reservation table the main details for the reservation
				 */
//				omniCommonPushDAO.insertReservation(reservationCO);
				/*
				 * insert into recurring template the details for recurring reservation as
				 * peridicity , start and end date , and how many times repeat
				 */
				omniCommonPushDAO.insertRecurring_template(reservationCO);

				/*
				 * insert into the reservation_list table the list of reservations with date
				 * time and machine_driver_id , then delete from temporary_reservation
				 */
//				omniCommonPushDAO.insertListReservationList(reservationCO);
				omniCommonPushDAO.deleteTemporaryReservation(reservationCO);
				response.setResponse("the reservation is confirmed");

			}
			/*
			 * insert the facture in db
			 */
//			omniCommonPushDAO.insertFacture(reservationCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
			return response;
		}

	}

	@Override
	public CheckTempRecurringReservationResponse checkTempRecurringReservation(
			TemporaryReservationVO temporaryReservationVO) throws Exception {
		CheckTempRecurringReservationResponse response = new CheckTempRecurringReservationResponse();
		try {
			List<TemporaryReservationVO> listOfReservations = omniCommonPullDAO
					.returnListOfTmeporaryReservation(temporaryReservationVO);
			response.setList(listOfReservations);

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

//	@Override
//	public UpdateReservationStatusResponse updateReservationStatus(DriverCO driverCO) throws Exception {
//		UpdateReservationStatusResponse response = new UpdateReservationStatusResponse();
//		try {
//			omniCommonPushDAO.updateReservationStatus(driverCO);
//			if (driverCO.getReservation_list_status() == 2) {
//				omniCommonPushDAO.updateMachineLocation(driverCO);
//			}
//			response.setResponse("status updated");
//
//		} catch (Exception e) {
//			log.info(e.getMessage());
//			response.setResponseError(ERROR);
//		}
//		return response;
//	}

	@Override
	public RatingTripResponse ratingTrip(RateVO rateVO) throws Exception {
		RatingTripResponse response = new RatingTripResponse();
		try {
			omniCommonPushDAO.insertRating(rateVO);
			response.setResponse("thank you for using our app");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public RejectReservationResponse rejectReservation(ReservationCO reservationCO) throws Exception {
		RejectReservationResponse response = new RejectReservationResponse();
		try {
			omniCommonPushDAO.deleteTemporaryReservation(reservationCO);
			response.setResponse("reservation rejected");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public UpdateDriverWorkingHoursResponse updateDriverWorkingHours(WorkingHoursVO workingHoursVO) throws Exception {
		UpdateDriverWorkingHoursResponse response = new UpdateDriverWorkingHoursResponse();
		try {
			omniCommonPushDAO.updateDriverWorkingHours(workingHoursVO);
			response.setResponse("done");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	public int getTempReservationId(ReservationCO reservationCO) {
		try {
			/*
			 * get the temp_reservation_id from the cache value , if the server is reseted ,
			 * get the value from the temporary table , then add 1
			 *
			 */
			int r = getTemp_reservation_id();
			if (r == 0) {
				TemporaryReservationVO maxID = omniCommonPullDAO.returnMaxTemporaryReservation(reservationCO);
				/*
				 * if the value of reservation_id is null , so no reservation in the database ,
				 * this is the first one
				 */
				if (maxID == null) {
					setTemp_reservation_id(1);
				} else {
					int i = maxID.getTemp_reservation_id();
					setTemp_reservation_id(++i);
				}

			} else {

				setTemp_reservation_id(++r);
			}
			return temp_reservation_id;
		} catch (Exception e) {
			log.info(e.getMessage());
			return 0;
		}

	}

	@Override
	public CostResponse getCost(ReservationCO reservationCO, int cost_of_100_m) throws Exception {
		CostResponse response = new CostResponse();
		double totalCost = 0;
		GetDiscountResponse getDiscount;
		try {
//			prop = PropertiesLoaderUtils.loadAllProperties("Cost.properties");
//			String costOf100mString = prop.getProperty("Cost_of_100_m");
//			int costOf100m = Integer.parseInt(propertiesHashMap.get("CostOf100m").getProperty_value());

//			String costOf10minString = prop.getProperty("Cost_of_10_min");
//			int costOf10min = Integer.parseInt(costOf10minString);
//			HashMap<Date, Double> listOfCost = new HashMap<Date, Double>();

//			prop = PropertiesLoaderUtils.loadAllProperties("Discount.properties");
//			PropertyVO propertyVO = new PropertyVO();
//			propertyVO.setProperty_name(cost_of_100_m);
//			PropertyVO propertyVO2 = omniCommonPullDAO.returnProperty(propertyVO);
//			int cost_of_100_m = Integer.parseInt(propertyVO2.getProperty_value());
			double cost = 0;

			/**
			 * check if the type of reservation if trip or machine
			 */
//			if (reservationCO.getReservation_type() == 0) {
			/**
			 * the type is trip
			 */
			response.setCost((reservationCO.getDistance() * cost_of_100_m) / 100);
//				cost = (reservationCO.getDistance() * costOf100m) / 100;
//				if (reservationCO.getOccurrence_type() == occurrenceTypeImmediate
//						|| reservationCO.getOccurrence_type() == occ) {
//					response.setCost((reservationCO.getDistance() * costOf100m) / 100);
//				} else if (reservationCO.getOccurrence_type() == 1) {
//					response.setCostOfEachReserv(cost);
//					totalCost = cost * reservationCO.getRecur_count();
//					response.setTotalCost(cost * reservationCO.getRecur_count());
//					getDiscount = getDiscount(reservationCO.getRecur_count(), totalCost);
//					response.setDiscount(getDiscount.getDiscount());
//					response.setCostAfterDiscount(getDiscount.getCost_after_discount());
//				}
//			}
			/**
			 * type is machine
			 */
//			else if (reservationCO.getReservation_type() == 1) {
//				String stringDate = reservationCO.getTime_slot().toString();
//				String[] hAndm = stringDate.split(":");
//				int time_slot = Integer.parseInt(hAndm[0]) * 60 + Integer.parseInt(hAndm[1]);
//				cost = ((time_slot * costOf10min) / 10);
//				if (reservationCO.getOccurrence_type() == 0 || reservationCO.getOccurrence_type() == 2) {
//					response.setCost(cost);
//				} else if (reservationCO.getOccurrence_type() == 1) {
//					response.setCostOfEachReserv(cost);
//					totalCost = cost * reservationCO.getRecur_count();
//					response.setTotalCost(cost * reservationCO.getRecur_count());
//					getDiscount = getDiscount(reservationCO.getRecur_count(), totalCost);
//					response.setDiscount(getDiscount.getDiscount());
//					response.setCostAfterDiscount(getDiscount.getCost_after_discount());
//				}
//			}

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	public GetDiscountResponse getDiscount(int recur_count, double totalCost) throws Exception {
		GetDiscountResponse response = new GetDiscountResponse();
		double cost_after_discount = 0;
		int discount = 0;

//		prop = PropertiesLoaderUtils.loadAllProperties("Discount.properties");
//		if (recur_count <= 10) {
//			String costOf100mString = prop.getProperty("10");
//			discount = Integer.parseInt(costOf100mString);
//			cost_after_discount = totalCost - ((totalCost * discount) / 100);
//		} else if (recur_count <= 30 && recur_count >= 10) {
//			String costOf100mString = prop.getProperty("30");
//			discount = Integer.parseInt(costOf100mString);
//			cost_after_discount = totalCost - ((totalCost * discount) / 100);
//		} else if (recur_count <= 50 && recur_count >= 30) {
//			String costOf100mString = prop.getProperty("50");
//			discount = Integer.parseInt(costOf100mString);
//			cost_after_discount = totalCost - ((totalCost * discount) / 100);
//		} else if (recur_count <= 100 && recur_count >= 50) {
//			String costOf100mString = prop.getProperty("100");
//			discount = Integer.parseInt(costOf100mString);
//			cost_after_discount = totalCost - ((totalCost * discount) / 100);
//		}
//		response.setCost_after_discount(cost_after_discount);
//		response.setDiscount(discount);
		return response;
	}

	@Override
	public AddStoreResponse addStore(StoreVO storeVO) throws Exception {
		AddStoreResponse response = new AddStoreResponse();
		StoreVO vo = new StoreVO();
		try {
			vo = omniCommonPullDAO.returnStore(storeVO);
			if (vo == null) {
				omniCommonPushDAO.insertStore(storeVO);
				response.setResponse("store added successfully");
			} else if (vo != null) {
				response.setErrorCode(errorCodeAddStore);
				response.setErrorDesc(
						getError(storeVO.getLanguage_id(), response.getErrorCode()).get(0).getError_desc());
			}

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ForgetPasswordResponse returnMaskMobileNumber(String mobileNumber, DeviceSC deviceSC) throws Exception {
		char[] mask;
		ForgetPasswordResponse response = new ForgetPasswordResponse();
		if (mobileNumber.length() == 15) {
			mask = Arrays.copyOfRange(mobileNumber.toCharArray(), 9, mobileNumber.toCharArray().length);
		} else {
			mask = Arrays.copyOfRange(mobileNumber.toCharArray(), 8, mobileNumber.toCharArray().length);
		}
		for (int i = 0; i < mask.length - 2; i++) {
			mask[i] = '*';
		}
		GenerateOTPResponse generateOTPResponse = omniCommonBO.generateOTP(deviceSC);
		if (generateOTPResponse.getErrorCode() != null && generateOTPResponse.getErrorDesc() != null) {
			response.setErrorCode(generateOTPResponse.getErrorCode());
			response.setErrorDesc(generateOTPResponse.getErrorDesc());
		} else {
			response.setMask(String.valueOf(mask));
			response.setMobile_number(mobileNumber);
			response.setOtp(generateOTPResponse.getOtp());
		}
		return response;
	}

	@PostConstruct
	public void getErrors() throws Exception {
		ErrorVO errorVO = new ErrorVO();
		listErrors = omniCommonPullDAO.returnLanguageError(errorVO);
//		for (ErrorVO list : listErrors) {
//			System.out.println(list.getError_desc());
//		}
	}

	public List<ErrorVO> getError(int language_id, int errorCode) {
		List<ErrorVO> list = listErrors.stream().filter(l -> l.getError_code() == errorCode)
				.filter(l -> l.getLanguage_id() == language_id).collect(Collectors.toList());
		return list;
	}

	@PostConstruct
	public void getNotifications() throws DAOException {
		NotificationVO notificationVO = new NotificationVO();
		listNotifications = omniCommonPullDAO.returnLanguageNotification(notificationVO);
		for (NotificationVO list : listNotifications) {
			System.out.println(list.getNotification_desc());
		}
	}

	@Override
	public List<NotificationVO> getNotification(int language_id, String notificationCode) {
		List<NotificationVO> list = listNotifications.stream()
				.filter(l -> l.getNotification_code().equals(notificationCode))
				.filter(l -> l.getLanguage_id() == language_id).collect(Collectors.toList());
		return list;
	}

	@Override
	public SaveTokenResponse saveToken(User user) throws Exception {
		SaveTokenResponse response = new SaveTokenResponse();
		try {
			if (user.getDevice_token() == null) {
				response.setErrorCode(updateDeviceToken);
				response.setErrorDesc(
						omniCommonBO.getError(user.getLanguage_id(), response.getErrorCode()).get(0).getError_desc());
			} else {
				omniCommonPushDAO.updateDeviceToken(user);
				response.setResponse("done");
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public CancelRecurringReservationResponse cancelRecurringReservation(ReservationCO reservationCO) throws Exception {
		CancelRecurringReservationResponse response = new CancelRecurringReservationResponse();
		try {
			reservationCO.setReservation_list_status(CANCELED);
			reservationCO.setReservation_status(CANCELED);
			omniCommonPushDAO.updateRecurringReservationStatus(reservationCO);
			response.setResponse("reservation canceled");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	public CostResponse calculateCost(DriverCO driverCO, ReservationCO reservationCO, List<PropertiesVO> listProperties)
			throws Exception {
		CostResponse costResponse = new CostResponse();

		int fcost;
		int totalCostByDriver;
		int totalPenalty = 0;

		String penaltyString = listProperties.stream().filter(l -> l.getProperty_name().equals(penalty))
				.collect(Collectors.toList()).get(0).getProperty_value();

		int penalty = Integer.parseInt(penaltyString);

		String nbOfFreeReservationString = listProperties.stream()
				.filter(l -> l.getProperty_name().equals(nb_free_reservation)).collect(Collectors.toList()).get(0)
				.getProperty_value();

		int nbOfFreeReservation = Integer.parseInt(nbOfFreeReservationString);

		String discountAppReservationString = listProperties.stream()
				.filter(l -> l.getProperty_name().equals(discount_app_reservation)).collect(Collectors.toList()).get(0)
				.getProperty_value();

		int discountAppReservation = Integer.parseInt(discountAppReservationString);

		int totalCost = 0;
		if (reservationCO.getFree_status() == free) {
			costResponse.setTotalCost(totalCost);
			costResponse.setResponse("this trip is for free");
			costResponse.setResponseCode(freeReservation);
			return costResponse;
		}

		ReservationCO reservationCO3 = new ReservationCO();
		reservationCO3.setReservation_id(reservationCO.getReservation_id());
		reservationCO3.setReservation_list_id(reservationCO.getReservation_list_id());
		if (reservationCO.getFromThread() == notFromThread) {
			List<ReservationCO> dismissedReservationList = omniCommonBO
					.checkDismissedAndCanceledReservation(reservationCO3);
			if (!dismissedReservationList.isEmpty()) {
				totalPenalty = dismissedReservationList.size() * penalty;
				for (ReservationCO l : dismissedReservationList) {
					reservationCO3.setReservation_list_id(l.getReservation_list_id());
//						reservationCO3.setPenalty_status(penaltyPaid);
					reservationCO3.setPenalty_status(reservationCO.getReservation_list_id());
					omniCommonPushDAO.UpdatePenaltyStatus(reservationCO3);
				}

			}
		}
		reservationCO3.setPenalty_status(reservationCO.getReservation_list_id());
		reservationCO3.setReservation_list_id(reservationCO.getReservation_list_id());
		omniCommonPushDAO.UpdatePenaltyStatus(reservationCO3);

//			}
		String totalCostString = null;
		double costStartFinish = 0;
		double costTakeWait = 0;
		String costStartFinishString = null;
		double costStartFinishTesting = 0;
		String costStartFinishTestingString = null;
		int totalCostTesting = 0;
		ReturnCostProperties returnCostProperties = returnCostProperties(listProperties);
		double priceBenzin = returnCostProperties.getPriceBenzin();
		double kmTanaki = returnCostProperties.getKmTanaki();
		double paramForCost = returnCostProperties.getParamForCost();
		double paramCost4to7 = returnCostProperties.getParamCost4to7();
		double paramCost7to10 = returnCostProperties.getParamCost7to10();
		double paramcCostHigherThan10 = returnCostProperties.getParamcCostHigherThan10();
		double percentageCost = returnCostProperties.getPercentageCost();
		double costTakeStart = 0;
		if (driverCO.getDistanceFromTo() != 0) {
//			totalCost = (int) ((driverCO.getDistanceFromTo() / 1000) * (priceBenzin / kmTanaki) * paramForCost);
			ReturnCostOfDistance returnCostTakeStart = calculateCostOfDistance(driverCO.getDistanceFromTo(),
					priceBenzin, kmTanaki, paramForCost, paramCost4to7, paramCost7to10, paramcCostHigherThan10,
					driverCO.getNbOfClient(), percentageCost);
			totalCost = (int) returnCostTakeStart.getCostOfDistance();
		} else {
			ReservationCO reservationCO2Kilometrage = omniCommonPullDAO.returnKilometrage(reservationCO);
			double distanceTakeStart = 0;

			if (reservationCO.getDistanceTakeWait() != 0) {
				distanceTakeStart = reservationCO.getDistanceTakeWait();
			}

			double distanceStartFinish = 0;
			if (reservationCO2Kilometrage != null) {
				if (reservationCO2Kilometrage.getKilometrageStart() != 0
						&& reservationCO2Kilometrage.getKilometrageTake() != 0) {
					distanceTakeStart = calculateDistanceBetween2Kilometrage(
							reservationCO2Kilometrage.getKilometrageTake(),
							reservationCO2Kilometrage.getKilometrageStart());
				}
				if (reservationCO2Kilometrage.getKilometrageStart() != 0
						&& reservationCO2Kilometrage.getKilometrageFinish() != 0) {
					distanceStartFinish = calculateDistanceBetween2Kilometrage(
							reservationCO2Kilometrage.getKilometrageStart(),
							reservationCO2Kilometrage.getKilometrageFinish());
				}
			} else if (reservationCO.getDistanceStartFinish() != 0) {
				distanceStartFinish = reservationCO.getDistanceStartFinish();
			}
			ReturnCostOfDistance returnCostTakeStart = calculateCostOfDistance(distanceTakeStart, priceBenzin, kmTanaki,
					paramForCost, paramCost4to7, paramCost7to10, paramcCostHigherThan10, driverCO.getNbOfClient(),
					percentageCost);
			costTakeStart = returnCostTakeStart.getCostOfDistance();

			ReturnCostOfDistance returnCostStartFinish = calculateCostOfDistance(distanceStartFinish, priceBenzin,
					kmTanaki, paramForCost, paramCost4to7, paramCost7to10, paramcCostHigherThan10,
					driverCO.getNbOfClient(), percentageCost);
			costStartFinish = returnCostStartFinish.getCostOfDistance();

//			if (driverCO.getNbOfClient() != 0 && driverCO.getNbOfClient() != 1) {
//				costStartFinish = (costStartFinish * driverCO.getNbOfClient()) * (percentageCost / 100);
//			}
			totalCost = (int) Math.ceil((costTakeStart + costStartFinish) / 1000) * 1000;

		}

		if (reservationCO.getClient_id() != -1) {
			totalCost = (int) Math.ceil((totalCost - (totalCost * discountAppReservation / 100)) / 1000) * 1000;
		}
		costResponse.setTotalCost(totalCost);
		costResponse.setPenalty(totalPenalty);
		return costResponse;
	}

	public List<ReservationCO> checkDismissedAndCanceledReservation(ReservationCO reservationCO) {
		try {
			List<ReservationCO> dismissedAndCanceledReservationList = omniCommonPullDAO
					.returnDismissedAndCanceledReservation(reservationCO);
			return dismissedAndCanceledReservationList;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	int countStatus;

//	@SuppressWarnings({ "unused", "static-access" })
	@Override
	public CreateReservationResponse createReservation(ReservationCO reservationCO) throws Exception {
		CreateReservationResponse response = new CreateReservationResponse();
		List<PropertiesVO> list = omniCommonPullDAO.returnListProperties(null);
		String user = list.stream().filter(l -> l.getProperty_name().equals(userProp)).collect(Collectors.toList())
				.get(0).getProperty_value();
		String pass = list.stream().filter(l -> l.getProperty_name().equals(passProp)).collect(Collectors.toList())
				.get(0).getProperty_value();
		String sid = list.stream().filter(l -> l.getProperty_name().equals(sidProp)).collect(Collectors.toList()).get(0)
				.getProperty_value();
		String url = list.stream().filter(l -> l.getProperty_name().equals(urlProp)).collect(Collectors.toList()).get(0)
				.getProperty_value();
		String triptukURL = list.stream().filter(l -> l.getProperty_name().equals(triptukLink))
				.collect(Collectors.toList()).get(0).getProperty_value();

		/// ********************************Mohannad*****************************////////

		Notification notification = omniCommonBO.loadKeyAndUrl();
		/*
		 * * if (reservationCO.getClients_group_id() != 0) {
		 * 
		 * reservationCO.setClient_id(customerCareClient);
		 * 
		 * }
		 * 
		 * ClientCO client = new ClientCO(); if (reservationCO.getApp() ==
		 * adminCustomerCareApp) {
		 * 
		 * if (reservationCO.getClients_group_id() == 0) {
		 * 
		 * client.setMobile_number(reservationCO.getMobile_number());
		 * client.setActive(active);
		 * client.setLanguage_id(reservationCO.getLanguage_id());
		 * client.setLast_login_client(new java.util.Date(System.currentTimeMillis()));
		 * client.setMobile_number(reservationCO.getMobile_number());
		 * client.setUsername(reservationCO.getUsername()); // client.setLanguage_id(1);
		 * 
		 * ClientVO mobileNbExists =
		 * omniCommonPullDAO.returnMobileNumberIfExist(client);
		 * 
		 * if (mobileNbExists != null) {
		 * 
		 * reservationCO.setClient_id(mobileNbExists.getClient_id());
		 * 
		 * msg = omniCommonBO.getNotification(mobileNbExists.getLanguage_id(),
		 * NEW_RESERVATION_CODE).get(0) .getNotification_desc(); String title =
		 * omniCommonBO .getNotification(mobileNbExists.getLanguage_id(),
		 * NEW_RESERVATION_TITLE_CODE).get(0) .getNotification_desc(); try {
		 * securityUtilsExt.pushNotification(mobileNbExists.getDevice_token(), title,
		 * msg, notification, null, null); } catch (Exception e) {
		 * log.info(e.getMessage()); }
		 * 
		 * } else {
		 * 
		 * client.setActive(active);
		 * client.setLanguage_id(reservationCO.getLanguage_id());
		 * client.setLast_login_client(new java.util.Date(System.currentTimeMillis()));
		 * client.setMobile_number(reservationCO.getMobile_number());
		 * client.setUsername(reservationCO.getUsername()); client.setLanguage_id(1);
		 * 
		 * omniCommonPushDAO.insertUsernameMobileNumber(client);
		 * 
		 * reservationCO.setClient_id(client.getClient_id());
		 * 
		 * String sms =
		 * "Welcome to triptuk app, new user created for you , your username is " +
		 * reservationCO.getUsername() + " , download the app via" + triptukURL;
		 * 
		 * securityUtilsExt.sendSMS(client.getMobile_number(), user, pass, sid, url,
		 * sms); }
		 * 
		 * // !! what is url and sid ??
		 * 
		 * } }
		 */

		/// ********************************Mohannad*****************************////////

		/*
		 * check if the request is comming from client app or driver app and if the
		 * location is null return error no location
		 */
		if ((reservationCO.getApp() == clientApp || reservationCO.getApp() == driverApp)
				&& (reservationCO.getLatitude_from() == 0 && reservationCO.getLongitude_from() == 0)
				&& (reservationCO.getAddress_from() == null && reservationCO.getAddress_to() == null)) {
			response.setErrorCode(errorCodeCreateReservLocationNull);
			response.setErrorDesc(omniCommonBO.getError(reservationCO.getLanguage_id(), response.getErrorCode()).get(0)
					.getError_desc());
			return response;
		}
		/*
		 * if the request is comming from customer care app and location not null return
		 * the nearest available machine , if the address is not null , return rendomly
		 * machine driver
		 */
		if (reservationCO.getApp() == adminCustomerCareApp
				&& (reservationCO.getLatitude_from() != 0 && reservationCO.getLongitude_from() != 0)) {
			ReservationCO cloneReservationCO = reservationCO.cloneReservationCO();
			ReturnNearestAvailableMachineResponse driver = omniCommonBO
					.returnNearestAvailableMachine(cloneReservationCO);
			if (!driver.getListAvailable().isEmpty()) {
				reservationCO.setDriver_id(driver.getListAvailable().get(0).getDriver_id());
				reservationCO.setMachine_driver_id(driver.getListAvailable().get(0).getMachine_driver_id());
			} else if (!driver.getListNotAvailable().isEmpty()) {
				reservationCO.setDriver_id(driver.getListNotAvailable().get(0).getDriver_id());
				reservationCO.setMachine_driver_id(driver.getListNotAvailable().get(0).getMachine_driver_id());
			}

		} else if (reservationCO.getApp() == adminCustomerCareApp
				&& (reservationCO.getAddress_from().getBuilding_from() != null)) {
			ReservationDetailsCO reservationDetailsCO = new ReservationDetailsCO();
			if (reservationCO.getDate() == null) {
				reservationDetailsCO.setDate(new Date(System.currentTimeMillis()));
			} else {
				reservationDetailsCO.setDate(reservationCO.getDate());
			}
			if (reservationCO.getTime() == null) {
				reservationDetailsCO.setTime(new Time(System.currentTimeMillis()));
			} else {
				reservationDetailsCO.setTime(reservationCO.getTime());
			}
			Machine_driverCO driverRandomly = omniCommonPullDAO.returnMachineDriverRandomly(reservationDetailsCO);
			reservationCO.setMachine_driver_id(driverRandomly.getMachine_driver_id());
			reservationCO.setDriver_id(driverRandomly.getDriver_id());
		}

		DriverVO driverVO = omniCommonPullDAO.returnDriverName(reservationCO);

		List<ReservationCO> listLastReservations;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = new java.util.Date();
		String today = format.format(d);

		try {
			List<PropertiesVO> listProperties = omniCommonPullDAO.returnListProperties(null);

			String cancelCounter = listProperties.stream().filter(l -> l.getProperty_name().equals(cancel_counter))
					.collect(Collectors.toList()).get(0).getProperty_value();

			int cancelCounterPerDay = Integer.parseInt(cancelCounter);
			String numberOfReservations = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(number_of_reservations)).collect(Collectors.toList())
					.get(0).getProperty_value();
			int nb = Integer.parseInt(numberOfReservations);

			String maxTimeToReachDestinationStr = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(maxTimeToReachDestination)).collect(Collectors.toList())
					.get(0).getProperty_value();
			int maxTimeToReachDestination = Integer.parseInt(maxTimeToReachDestinationStr);

			String nbOfFreeReservationStr = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(nb_of_free_reservation)).collect(Collectors.toList())
					.get(0).getProperty_value();
			int nbOfFreeReservation = Integer.parseInt(nbOfFreeReservationStr);
			ReservationCO reservationCO2 = new ReservationCO();

			reservationCO2.setClient_id(reservationCO.getClient_id());
			int nbCancel = 0;
			if (reservationCO.getApp() == clientApp && reservationCO.getFromThread() == notFromThread) {
				nbCancel = omniCommonPullDAO.returnNumberOfCanceledAndMissed(reservationCO2);
			}
			if (cancelCounterPerDay <= nbCancel) {
				response.setErrorCode(errorCodeCreateReservClientIsBlocked);
				response.setErrorDesc(omniCommonBO.getError(reservationCO.getLanguage_id(), response.getErrorCode())
						.get(0).getError_desc());
				return response;
			}

			int count = 0;
			countStatus = 0;

			long now = System.currentTimeMillis();
			Time time = new Time(now);
			DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
			String output = outputformat.format(time);
			Time t = Time.valueOf(output);

			reservationCO2.setCounter(nb);

			Date todaydate = new Date(now);
			reservationCO.setDateOfCreateReservation(todaydate);
			reservationCO.setTimeOfCreateReservation(now);
			Machine_driverCO mdCO = new Machine_driverCO();
			mdCO.setMachine_driver_id(reservationCO.getMachine_driver_id());
			mdCO.setDriver_id(reservationCO.getDriver_id());

			/*
			 * check if the reservation is quick reservation set the correct values of quick
			 * reservation type then insert the reservation
			 */
			if (reservationCO.getClient_id() == quick_reservation_driver) {

				Machine_driverCO machine_driverCO = omniCommonPullDAO.returnMachineDriverId(reservationCO);
				reservationCO.setMachine_driver_id(machine_driverCO.getMachine_driver_id());
				if (reservationCO.getQuickReservationType() == QUICK_RESERVATION_BY_PHONE
						|| reservationCO.getQuickReservationType() == QUICK_RESERVATION_TLUBTUK) {
					reservationCO.setReservation_list_status(TAKE_TRIP);
					reservationCO.setReservation_list_status_created(TAKE_TRIP);
				} else if (reservationCO.getQuickReservationType() == QUICK_RESERVATION_START) {
					reservationCO.setReservation_list_status(START_TRIP);
					reservationCO.setReservation_list_status_created(START_TRIP);
				}

			}
			int r = 0;
			if (reservationCO.getApp() == clientApp && reservationCO.getFromThread() == notFromThread) {
				r = omniCommonPullDAO.returnNumberOfPendingReservation(reservationCO2);
			}
			if (reservationCO.getClient_id() != quick_reservation_driver) {
				if (r >= nb) {
					response.setErrorCode(errorCodeCreateReservationNumber);
					response.setErrorDesc(omniCommonBO.getError(reservationCO.getLanguage_id(), response.getErrorCode())
							.get(0).getError_desc());

				} else if (r < nb) {
					if (reservationCO.getReservation_type() == reservationTypeTlubTuk) {
						if (reservationCO.getConfirmReservation() == TLUBTUKNOTCONFIRMED) {
							reservationCO.setReservation_list_status(NOTCONFIRMED);
						}
						if (reservationCO.getConfirmReservation() == TLUBTUKCONFIRMED) {
							reservationCO.setReservation_list_status(PENDING);
						}
						reservationCO.setNbOfClient(1);

					}
					if (reservationCO.getClient_id() == quick_reservation_driver) {
						reservationCO.setReservation_list_status(START_TRIP);
					}
				}
			}
			DeviceDriverVO DeviceDriverVO = omniCommonPullDAO.returnDriverToken(mdCO);

			LocalTime localtime = LocalTime.parse(reservationCO.getTime().toString());
			String tss = df.format(localtime.plusMinutes(maxTimeToReachDestination)) + ":00";
			Time ts = Time.valueOf(tss);
			if (ts.getHours() > 24) {
				ts.setHours(reservationCO.getTime().getHours());
			}
			reservationCO.setTime_slot(ts);
			reservationCO.setPenalty_status(penaltyNotPaid);
			reservationCO.setDateTimeOfCreateReservation(new java.util.Date());
			omniCommonPushDAO.insertReservation(reservationCO);
			omniCommonPushDAO.insertReservationList(reservationCO);

			if (reservationCO.getReservation_type() == reservationTypeTlubTuk
					&& reservationCO.getConfirmReservation() == TLUBTUKNOTCONFIRMED) {
			} else {
				if (reservationCO.getClient_id() != quick_reservation_driver) {

					msg = omniCommonBO.getNotification(DeviceDriverVO.getLanguage_id(), NEW_RESERVATION_CODE).get(0)
							.getNotification_desc();
					String title = omniCommonBO
							.getNotification(DeviceDriverVO.getLanguage_id(), NEW_RESERVATION_TITLE_CODE).get(0)
							.getNotification_desc();
					try {
						securityUtilsExt.pushNotification(DeviceDriverVO.getDevice_token(), title, msg, notification,
								null, null);
					} catch (Exception e) {
						log.info(e.getMessage());
					}

					AdminVO adminVO = new AdminVO();
					adminVO.setActive(admin_active);
					adminVO.setIs_customer_care(is_customer_care);
					List<DeviceAdminVO> listDeviceAdminVO = omniCommonPullDAO.returnCustomerCareToken(adminVO);
					try {

						msg = driverVO.getName() + "has" + msg;
						for (DeviceAdminVO customerCare : listDeviceAdminVO) {
							securityUtilsExt.pushNotification(customerCare.getDevice_token(), title, msg, notification,
									null, null);
						}
					} catch (Exception e) {
						log.info(e.getMessage());
					}

				}
			}
			if (reservationCO.getReservation_type() == reservationTypeTlubTuk
					&& reservationCO.getConfirmReservation() == TLUBTUKCONFIRMED) {

				ReservationDetailsCO reservationDetailsCO = new ReservationDetailsCO(
						reservationCO.getReservation_list_id(), reservationCO.getAmount(),
						reservationCO.getDescription(), reservationCO.getSecond_mobile_number(),
						reservationCO.getParamlov_id(), reservationCO.getAddress_from(), reservationCO.getAddress_to(),
						reservationCO.getLocation_type_from(), reservationCO.getLocation_type_to());
				omniCommonPushDAO.insertReservationDetails(reservationDetailsCO);
			}

			response.setReservation_id(reservationCO.getReservation_id());
			response.setReservation_list_id(reservationCO.getReservation_list_id());

			if (reservationCO.getLatitude_to() != 0 && reservationCO.getLongitude_to() != 0) {
				DriverCO driverCO = new DriverCO();
				driverCO.setDistanceFromTo(reservationCO.getDistanceFromTo());
				CostResponse costResponse = omniCommonBO.calculateCost(driverCO, reservationCO, listProperties);
				omniCommonPushDAO.updateCostOfTripFromDestinationList(reservationCO);
				response.setCostOfTripWithDestination(costResponse.getTotalCost());
				response.setPinalty(costResponse.getPenalty());
			}

			if (reservationCO.getFrReservation_id() != 0) {
				response.setInsertedfrReservation_id(reservationCO.getFrReservation_id());
			}

		} catch (Exception e) {
			response.setNotInsertedfrReservation_id(reservationCO.getFrReservation_id());
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}

		/////////////////////////////////////////// Mohannad////////////////////////

		//// error from this point!!!!!!!!! -> SOlved
		/*
		 * if (reservationCO.getClients_group_id() == 0) {
		 * 
		 * String msg = omniCommonBO.getNotification(client.getLanguage_id(),
		 * NEW_RESERVATION_CODE).get(0) .getNotification_desc();
		 * 
		 * String title = omniCommonBO.getNotification(client.getLanguage_id(),
		 * NEW_RESERVATION_TITLE_CODE).get(0) .getNotification_desc();
		 * 
		 * try { securityUtilsExt.pushNotification(client.getDevice_token(), title, msg,
		 * notification, null, null); } catch (Exception e) { log.info(e.getMessage());
		 * } }
		 */
		/////////////////////////////////////////// Mohannad//////////////////////////
		response.setResponse("done");
		return response;
	}

	@Override
	public ConfirmTlubTukReservationResponse confirmTlubTukReservation(ReservationDetailsCO reservationDetailsCO)
			throws Exception {

		ConfirmTlubTukReservationResponse response = new ConfirmTlubTukReservationResponse();
		try {
			ReservationCO reservationCO = new ReservationCO();
			reservationCO.setDate(reservationDetailsCO.getDate());
			reservationCO.setTime(reservationDetailsCO.getTime());
			reservationCO.setDateTimeOfCreateReservation(reservationDetailsCO.getDateTimeOfCreateReservation());
			reservationCO.setLanguage_id(reservationDetailsCO.getLanguage_id());
			reservationCO.setLatitude_from(reservationDetailsCO.getLatitude_from());
			reservationCO.setLongitude_from(reservationDetailsCO.getLongitude_from());
			reservationCO.setLatitude_to(reservationDetailsCO.getLatitude_to());
			reservationCO.setLongitude_to(reservationDetailsCO.getLongitude_to());
			reservationCO.setAddress_from(reservationDetailsCO.getAddress_from());
			reservationCO.setAddress_to(reservationDetailsCO.getAddress_to());
			reservationCO.setParamlov_id(reservationDetailsCO.getParamlov_id());
			reservationCO.setAmount(reservationDetailsCO.getAmount());
			reservationCO.setDescription(reservationDetailsCO.getDescription());
			reservationCO.setLocation_type_from(reservationDetailsCO.getLocation_type_from());
			reservationCO.setLocation_type_to(reservationDetailsCO.getLocation_type_to());
			Notification notification = omniCommonBO.loadKeyAndUrl();

			ReservationListVO reservationListVO = new ReservationListVO();
			reservationListVO.setReservation_list_id(reservationDetailsCO.getReservation_list_id());
			DeviceDriverVO deviceDriverVO = new DeviceDriverVO();
			reservationCO.setClient_id(reservationDetailsCO.getClient_id());
			reservationCO.setReservation_type(reservationTypeTlubTuk);
			reservationCO.setConfirmReservation(TLUBTUKCONFIRMED);

			if (reservationDetailsCO.getReservation_list_id() != 0 && reservationDetailsCO.getBroker_id() == 0) {
				omniCommonPushDAO.updateLocations(reservationDetailsCO);
				reservationCO.setReservation_list_status(PENDING);
				reservationCO.setNbOfClient(1);
//				reservationCO.setReservation_list_id(reservationListVO.getReservation_list_id());
//				omniCommonPushDAO.updateReservationListStatus(reservationCO);
				ReservationCO machine_driver_id = omniCommonPullDAO.returnReservationDetails(reservationListVO);
				reservationCO.setMachine_driver_id(machine_driver_id.getMachine_driver_id());
			} else {
				if (reservationDetailsCO.getReservation_list_id() != 0 && reservationDetailsCO.getBroker_id() != 0) {
					Machine_driverCO machine_driverCO = omniCommonPullDAO.returnDriverOfBroker(reservationDetailsCO);
					deviceDriverVO.setLanguage_id(machine_driverCO.getLanguage_id());
					deviceDriverVO.setToken(machine_driverCO.getDevice_token());
					ReservationSC reservationSC = new ReservationSC();
					reservationSC.setMachine_driver_id(machine_driverCO.getMachine_driver_id());
					reservationSC.setReservation_list_id(reservationDetailsCO.getReservation_list_id());
					reservationSC.setKey_value(brokerReservationCancel);
					omniCommonPushDAO.updateCancelReservation(reservationSC);
					reservationCO.setReservation_list_id(reservationDetailsCO.getReservation_list_id());
					reservationCO.setClient_id(reservationDetailsCO.getClient_id());
					reservationCO.setReservation_id(reservationDetailsCO.getReservation_id());
					reservationCO.setMachine_driver_id(machine_driverCO.getMachine_driver_id());
				} else if (reservationDetailsCO.getReservation_list_id() == 0
						&& reservationDetailsCO.getBroker_id() == 0) {
//					reservationCO.setConfirmReservation(1);

					if (reservationDetailsCO.getDate() == null) {
						reservationDetailsCO.setDate(new Date(System.currentTimeMillis()));
					}
					if (reservationDetailsCO.getTime() == null) {
						reservationDetailsCO.setTime(new Time(System.currentTimeMillis()));
					}
					Machine_driverCO machine_driverCO = omniCommonPullDAO
							.returnMachineDriverRandomly(reservationDetailsCO);
					reservationCO.setMachine_driver_id(machine_driverCO.getMachine_driver_id());
				} else if (reservationDetailsCO.getReservation_list_id() == 0
						&& reservationDetailsCO.getBroker_id() != 0) {
					reservationCO.setConfirmReservation(1);
					Machine_driverCO machine_driverCO = omniCommonPullDAO.returnDriverOfBroker(reservationDetailsCO);
					reservationCO.setMachine_driver_id(machine_driverCO.getMachine_driver_id());
				}
			}
			CreateReservationResponse createReservationResponse = createReservation(reservationCO);
			reservationDetailsCO.setReservation_list_id(reservationCO.getReservation_list_id());
			if (createReservationResponse.getErrorCode() != null) {
				response.setErrorCode(createReservationResponse.getErrorCode());
				response.setErrorDesc(createReservationResponse.getErrorDesc());
				return response;
			}
//			if (reservationDetailsCO.getAddress_from() == null || reservationDetailsCO.getAddress_to() == null) {
//				omniCommonPushDAO.insertReservationDetails(reservationDetailsCO);
//			}
			reservationCO.setReservation_type(reservationTypeTlubTuk);
			response.setResponse("done");
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public LocalDateTime ConvertTimeToJavaTime(Time time, Date date) throws Exception {
		Calendar dateCal = Calendar.getInstance();
		dateCal.setTime(date);
		Calendar timeCal = Calendar.getInstance();
		timeCal.setTime(time);

		// Extract the time of the "time" object to the "date"
		dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
		dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
		dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));

		// Get the time value!
		java.util.Date reservationDate = dateCal.getTime();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		LocalDateTime datetime = LocalDateTime.parse(dateFormat.format(reservationDate), formatter);
//		datetime = datetime.plusMinutes(period);
//		java.util.Date datetimeAsDate = java.util.Date.from(datetime.atZone(ZoneId.systemDefault()).toInstant());
		return datetime;
	}

	@Override
	public ReturnCostOfDistanceResponse returnCostOfDistance(ReservationSC reservationSC) throws Exception {
		ReturnCostOfDistanceResponse response = new ReturnCostOfDistanceResponse();
		try {
			List<PropertiesVO> listProperties = omniCommonPullDAO.returnListProperties(null);
			ReturnCostProperties returnCostProperties = returnCostProperties(listProperties);

			ReturnCostOfDistance cost = calculateCostOfDistance(reservationSC.getDistance(),
					returnCostProperties.getPriceBenzin(), returnCostProperties.getKmTanaki(),
					returnCostProperties.getParamForCost(), returnCostProperties.getParamCost4to7(),
					returnCostProperties.getParamCost7to10(), returnCostProperties.getParamcCostHigherThan10(),
					reservationSC.getNbOfClient(), returnCostProperties.getPercentageCost());
//			double costp = cost.getCostOfDistance()
//					+ (cost.getCostOfDistance() * returnCostProperties.getAddedPercentageCost() / 100);
			int initial_cost = (int) Math.ceil((cost.getCostOfDistance()) / 1000) * 1000;

			int client_cost = (int) Math.ceil(
					(initial_cost - (initial_cost * returnCostProperties.getDiscountAppReservation() / 100)) / 1000)
					* 1000;

			int customer_care_cost = (int) Math.ceil(
					(initial_cost - (initial_cost * returnCostProperties.getDiscountAppReservation() / 2 / 100)) / 1000)
					* 1000;

			response.setClient_cost(client_cost);
			response.setDriver_cost(initial_cost);
			response.setCustomer_care_cost(customer_care_cost);

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ReturnCostOfDistance calculateCostOfDistance(double distance, double priceBenzin, double kmTanaki,
			double paramForCost, double paramCost4to7, double paramCost7to10, double paramcCostHigherThan10,
			int nbOfClient, double percentageCost) throws Exception {
		ReturnCostOfDistance response = new ReturnCostOfDistance();
		double costOfDistance = 0;
		String costOfDistanceString = null;
		double param = 0;
		if (distance <= 4000) {
			param = paramForCost;
		} else if (distance > 4000 && distance <= 7000) {
			param = paramCost4to7;
		} else if (distance > 7000 && distance <= 10000) {
			param = paramCost7to10;
		} else if (distance > 10000) {
			param = paramcCostHigherThan10;
		}
		costOfDistance = ((distance / 1000) * (priceBenzin / kmTanaki) * param);
		if (nbOfClient != 0 && nbOfClient != 1) {
			costOfDistance = (costOfDistance * nbOfClient) * (percentageCost / 100);
		}
		response.setCostOfDistance(costOfDistance);
		return response;

	}

	@Override
	public ReturnCostProperties returnCostProperties(List<PropertiesVO> listProperties) throws Exception {
		ReturnCostProperties returnCostProperties = new ReturnCostProperties();
		String priceBenzinString = listProperties.stream().filter(l -> l.getProperty_name().equals(price_benzin))
				.collect(Collectors.toList()).get(0).getProperty_value();
		double priceBenzin = Double.parseDouble(priceBenzinString);

		String kmTanakiString = listProperties.stream().filter(l -> l.getProperty_name().equals(km_tanaki))
				.collect(Collectors.toList()).get(0).getProperty_value();
		double kmTanaki = Double.parseDouble(kmTanakiString);

		String paramForCostString = listProperties.stream().filter(l -> l.getProperty_name().equals(param_for_cost))
				.collect(Collectors.toList()).get(0).getProperty_value();

		double paramForCost = Double.parseDouble(paramForCostString);

		String percentageCostString = listProperties.stream().filter(l -> l.getProperty_name().equals(percentage_cost))
				.collect(Collectors.toList()).get(0).getProperty_value();

		double percentageCost = Double.parseDouble(percentageCostString);

		String paramCost4to7String = listProperties.stream().filter(l -> l.getProperty_name().equals(param_cost4to7))
				.collect(Collectors.toList()).get(0).getProperty_value();

		double paramCost4to7 = Double.parseDouble(paramCost4to7String);

		String paramCost7to10String = listProperties.stream().filter(l -> l.getProperty_name().equals(param_cost7to10))
				.collect(Collectors.toList()).get(0).getProperty_value();

		double paramCost7to10 = Double.parseDouble(paramCost7to10String);

		String paramcCostHigherThan10String = listProperties.stream()
				.filter(l -> l.getProperty_name().equals(param_costHigherThan10)).collect(Collectors.toList()).get(0)
				.getProperty_value();

		double paramcCostHigherThan10 = Double.parseDouble(paramcCostHigherThan10String);

		String addedPercentageCostString = listProperties.stream()
				.filter(l -> l.getProperty_name().equals(added_percentage_cost)).collect(Collectors.toList()).get(0)
				.getProperty_value();

		double addedPercentageCost = Double.parseDouble(addedPercentageCostString);

		String discountAppReservationString = listProperties.stream()
				.filter(l -> l.getProperty_name().equals(discount_app_reservation)).collect(Collectors.toList()).get(0)
				.getProperty_value();

		double discountAppReservation = Double.parseDouble(addedPercentageCostString);

		returnCostProperties.setPriceBenzin(priceBenzin);
		returnCostProperties.setKmTanaki(kmTanaki);
		returnCostProperties.setParamForCost(paramForCost);
		returnCostProperties.setPercentageCost(percentageCost);
		returnCostProperties.setParamCost4to7(paramCost4to7);
		returnCostProperties.setParamCost7to10(paramCost7to10);
		returnCostProperties.setParamcCostHigherThan10(paramcCostHigherThan10);
		returnCostProperties.setAddedPercentageCost(addedPercentageCost);
		returnCostProperties.setDiscountAppReservation(discountAppReservation);
		return returnCostProperties;
	}

	@Override
	public double calculateDistanceBetween2Kilometrage(double kilometrage1, double kilometrage2) throws Exception {
		double distance = kilometrage2 - kilometrage1;
		return distance;
	}

	@Override
	public ReturnNearestAvailableMachineResponse returnNearestAvailableMachine(ReservationCO reservationCO)
			throws Exception {
		ReturnNearestAvailableMachineResponse response = new ReturnNearestAvailableMachineResponse();

		try {

			ReturnListOfMachinesNearestMostFarResponse res = new ReturnListOfMachinesNearestMostFarResponse();
			res = returnListOfMachinesNearestMostFar(reservationCO);
			List<Machine_driverCO> listAvailable = new ArrayList<Machine_driverCO>();
			List<Machine_driverCO> listNotAvailable = new ArrayList<Machine_driverCO>();

			if (res.getResponse() != null && res.getResponse().equals(noData)) {
				response.setResponse("No data");
			} else {
				for (Machine_driverCO l : res.getList()) {
					if (l.getOn_off() == on) {
						listAvailable.add(l);
					} else {
						listNotAvailable.add(l);
					}
				}
				response.setListAvailable(listAvailable);
				response.setListNotAvailable(listNotAvailable);
			}

		} catch (

		Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ReturnListOfMachinesNearestMostFarResponse returnListOfMachinesNearestMostFar(ReservationCO reservationCO)
			throws Exception {
		ReturnListOfMachinesNearestMostFarResponse response = new ReturnListOfMachinesNearestMostFarResponse();
		Machine_driverCO machine_driverCO = new Machine_driverCO();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		try {

			List<PropertiesVO> listProperties = omniCommonPullDAO.returnListProperties(null);
			String availabilityPeriodStre = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(availabilityPeriod)).collect(Collectors.toList()).get(0)
					.getProperty_value();
			int availabilityPeriod = Integer.parseInt(availabilityPeriodStre);

			long now = System.currentTimeMillis();
			Date today = new Date(now);
			Time timeNow = new Time(now);
			if (reservationCO.getDate() != null || reservationCO.getTime() != null) {
				reservationCO.setOccurrence_type(occurrenceTypeFuture);
			}

			if (reservationCO.getDate() == null && reservationCO.getTime() == null) {
				reservationCO.setTime(new Time(now));
				reservationCO.setDate(today);
				refreshData(reservationCO);
			}

			if (reservationCO.getTime() == null) {
//				Time time = new Time(now);
				reservationCO.setTime(timeNow);
				reservationCO.setReservation_type(reservationTypeTrip);
				reservationCO.setOccurrence_type(occurrenceTypeImmediate);
			}
			if (reservationCO.getDate() == null) {
//				Date today = new Date(now);
				reservationCO.setDate(today);
			}
			if (!reservationCO.getDate().toString().equals(today.toString())) {
				if (reservationCO.getDate().before(today)) {
					reservationCO.setDate(today);
					reservationCO.setTime(timeNow);
					reservationCO.setOccurrence_type(occurrenceTypeImmediate);
				}
			} else if (reservationCO.getDate().toString().equals(today.toString())) {
				if (reservationCO.getTime().getHours() < timeNow.getHours()
						|| (reservationCO.getTime().getHours() == timeNow.getHours()
								&& reservationCO.getTime().getMinutes() < timeNow.getMinutes())) {
					reservationCO.setTime(new Time(now));
					reservationCO.setOccurrence_type(occurrenceTypeImmediate);

				}
			}

			List<Machine_driverCO> list = omniCommonBO.returnList(reservationCO);

			if (!list.isEmpty()) {
				if (reservationCO.getLanguage_id() == arabic) {
					for (Machine_driverCO l : list) {
						l.setDriver_name(l.getAr_name());
					}
				}

				if (list.size() > availabilityPeriod) {
					list = list.subList(0, availabilityPeriod);
				}
				log.info(" fin service" + format1.format(new java.util.Date()));

				response.setList(list);
			} else {
				response.setResponse(noData);
			}

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	void refreshData(ReservationCO reservationCO) throws DAOException {
		log.info(" debut get old reservations " + format1.format(new java.util.Date()));

		List<ReservationListVO> list = omniCommonPullDAO.returnOldReservations(reservationCO);
		log.info(" debut get old reservations " + format1.format(new java.util.Date()));

		/*
		 * each reservation that have the date yesterday and not done , set the status
		 * as missed then update
		 */
//		if (!list.isEmpty()) {
//			for (ReservationListVO l : list) {
		setExpiredReservation(reservationCO, list);

		List<PropertiesVO> listProperties = omniCommonPullDAO.returnListProperties(null);

		String time_to_expireString = listProperties.stream().filter(l -> l.getProperty_name().equals(time_to_expire))
				.collect(Collectors.toList()).get(0).getProperty_value();
//		int velocity = Integer.parseInt(velocityString);
		Time timeToExpire = Time.valueOf(time_to_expireString);

		String time_to_expire_tlubtuk_reservationString = listProperties.stream()
				.filter(l -> l.getProperty_name().equals(time_to_expire_tlubtuk_reservation))
				.collect(Collectors.toList()).get(0).getProperty_value();
		int time_to_expire_tlubtuk_reservation = Integer.parseInt(time_to_expire_tlubtuk_reservationString);
//		PropertyVO propertyVO = new PropertyVO();
//		propertyVO.setProperty_name(time_to_expire);
//		PropertyVO propertyVO2 = omniCommonPullDAO.returnProperty(propertyVO);
		reservationCO.setTimeToExpire(timeToExpire);
		List<ReservationListVO> listOld = omniCommonPullDAO.returnOldReservationsToday(reservationCO);
		setExpiredReservation(reservationCO, listOld);

		List<ReservationCO> listnotConfirmedTlubtukReservations = omniCommonPullDAO
				.returnNotConfirmedTlubtukReservations(reservationCO);
		for (ReservationCO l : listnotConfirmedTlubtukReservations) {
		}
	}

	public void setExpiredReservation(ReservationCO reservationCO, List<ReservationListVO> list) throws DAOException {
		if (!list.isEmpty()) {
			reservationCO.setReservation_list_status(EXPIRED);
			for (ReservationListVO l : list) {
				reservationCO.setReservation_list_id(l.getReservation_list_id());
				omniCommonPushDAO.updateReservationListStatus(reservationCO);
			}
		}
	}

	public List<Machine_driverCO> returnList(ReservationCO reservationCO) {
		ReturnListOfMachinesNearestMostFarResponse response = new ReturnListOfMachinesNearestMostFarResponse();
		try {
			/*
			 * get all driver available today from db
			 */
			LocalTime localtime = reservationCO.getTime().toLocalTime();

			List<PropertiesVO> listProperties = omniCommonPullDAO.returnListProperties(null);
			List<Machine_driverCO> finalList = new ArrayList<Machine_driverCO>();

			/*
			 * get properties from db
			 */
			String velocityString = listProperties.stream().filter(l -> l.getProperty_name().equals(velocity))
					.collect(Collectors.toList()).get(0).getProperty_value();
			int velocity = Integer.parseInt(velocityString);
			String availabilityPeriodStre = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(availabilityPeriod)).collect(Collectors.toList()).get(0)
					.getProperty_value();
			int availabilityPeriod = Integer.parseInt(availabilityPeriodStre);
			String timeFromDestinationToClientStr = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(timeFromDestinationToClient)).collect(Collectors.toList())
					.get(0).getProperty_value();
			Time timeFromDestinationToClient = Time.valueOf(timeFromDestinationToClientStr);

			String userDriverDistanceString = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(user_driver_distance)).collect(Collectors.toList()).get(0)
					.getProperty_value();
			double userDriverDistance = Double.parseDouble(userDriverDistanceString);

			reservationCO.setEndTime(Time.valueOf(localtime.plusMinutes(availabilityPeriod * 30)
					.format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.US))));

			/*
			 * return all drivers
			 * 
			 */
			reservationCO.setUserDriverDistance(userDriverDistance);
			List<Machine_driverCO> listOfallDrivers = omniCommonPullDAO.returnAllDriver(reservationCO);
			Time t = reservationCO.getTime();
			int size;
			/*
			 * create array with size from now to 12 o'clock
			 * 
			 */
			size = (24 - t.getHours()) * 2;
			int[] base = new int[size];
			Arrays.fill(base, 0);
			if (!listOfallDrivers.isEmpty()) {
				/*
				 * create hashmap that have key machine_driver_id and value array on int that
				 * have size availability period , start with time , and have 0 1 as value m, 0
				 * available , 1 unavailable , each index refer to part of time
				 */
				HashMap<Integer, int[]> baseDriverHashmap = new HashMap<Integer, int[]>();
				HashMap<Integer, ArrayList<Time>> availableDriver = new HashMap<Integer, ArrayList<Time>>();
				for (Machine_driverCO l : listOfallDrivers) {

					baseDriverHashmap.put(l.getMachine_driver_id(), new int[size]);
				}

				/*
				 * for each driver , get the index of each time , and put it 1 if the driver is
				 * not available based on working hours table
				 */
				for (Machine_driverCO l : listOfallDrivers) {

					baseDriverHashmap.forEach((key, values) -> {
						if (key == l.getMachine_driver_id()) {
							int index = convertTimeToIndex(reservationCO.getTime(), l.getStart_break_hour());
							int index2 = convertTimeToIndex(reservationCO.getTime(), l.getEnd_break_hour());

							if (index >= 0 && index2 >= 0) {
								for (int i = index; i <= index2; i++) {
									values[i] = 3;
								}

							} else if (index <= 0 && index2 >= 0) {
								for (int i = 0; i <= index2; i++) {
									values[i] = 3;
								}
							}
							int index3 = convertTimeToIndex(reservationCO.getTime(), l.getStart_work_hour());
							int index4 = convertTimeToIndex(reservationCO.getTime(), l.getEnd_work_hour());
							if (index3 > 0) {
								for (int i = 0; i <= index3; i++) {
									values[i] = 3;
								}
							}
							if (index4 > 0) {
								for (int i = index4; i < values.length; i++) {
									values[i] = 3;
								}
							}
						}
					});
				}
				/*
				 * return the reservation for each driver , then for each driver , get the index
				 * of each time , and put it 1 if the driver is not available based on
				 * reservation
				 */
				List<Machine_driverCO> listReservation = omniCommonPullDAO.returnReservedDriver(reservationCO);
//
				for (Machine_driverCO l : listReservation) {
					baseDriverHashmap.forEach((key, values) -> {
						if (key == l.getMachine_driver_id()) {
							int index3 = convertTimeToIndex(reservationCO.getTime(), l.getTime());
							int index4 = convertTimeToIndex(reservationCO.getTime(), l.getTime_slot());
							if (index3 < 0)
								index3 = 0;
							for (int i = index3; i <= index4; i++) {
								values[i] = values[i] + l.getNbOfClient();
							}
//					values[index3] = 1;
						}
					});
				}
				baseDriverHashmap.forEach((key, values) -> {
					ArrayList<Time> array = convertArrayToTime(values, reservationCO.getTime());
					availableDriver.put(key, array);
				});
				availableDriver.forEach((key, values) -> {
					for (int i = 0; i < values.size(); i++) {
						Machine_driverCO machine_driverCO = new Machine_driverCO();
						machine_driverCO.setMachine_driver_id(key);
						String driverName = listOfallDrivers.stream().filter(l -> l.getMachine_driver_id() == key)
								.collect(Collectors.toList()).get(0).getDriver_name();
						machine_driverCO.setTime(values.get(i));
						machine_driverCO.setDriver_name(driverName);
						machine_driverCO.setTimeString(values.get(i).toString());
						machine_driverCO
								.setAr_name(listOfallDrivers.stream().filter(l -> l.getMachine_driver_id() == key)
										.collect(Collectors.toList()).get(0).getAr_name());
						machine_driverCO
								.setLongitude(listOfallDrivers.stream().filter(l -> l.getMachine_driver_id() == key)
										.collect(Collectors.toList()).get(0).getLongitude());
						machine_driverCO
								.setLatitude(listOfallDrivers.stream().filter(l -> l.getMachine_driver_id() == key)
										.collect(Collectors.toList()).get(0).getLatitude());
						if ((values.get(i).getHours() == new Time(System.currentTimeMillis()).getHours()
								&& reservationCO.getDate().toString()
										.equals(new Date(System.currentTimeMillis()).toString()))
								&& (values.get(i).getMinutes() <= new Time(System.currentTimeMillis()).getMinutes())) {
							machine_driverCO.setOn_off(on);
							machine_driverCO
									.setDriver_id(listOfallDrivers.stream().filter(l -> l.getMachine_driver_id() == key)
											.collect(Collectors.toList()).get(0).getDriver_id());
						}
						machine_driverCO.setDriver_mobile_number(
								listOfallDrivers.stream().filter(l -> l.getMachine_driver_id() == key)
										.collect(Collectors.toList()).get(0).getDriver_mobile_number());

						finalList.add(machine_driverCO);
					}
				});
				finalList.sort(Comparator.comparing(Machine_driverCO::getTime));
			}

			return finalList;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	public ArrayList<Time> convertArrayToTime(int[] values, Time startTime) {
		ArrayList<Time> array = new ArrayList<Time>();
		Time time = null;
		double value;
		for (int i = 0; i < values.length; i++) {
			value = startTime.getHours() + (0.5 * i);
			if (values[i] < 3) {
				if (startTime.getMinutes() < 30) {
					value = startTime.getHours() + (0.5 * i);
				}
				if (startTime.getMinutes() >= 30) {
					value = startTime.getHours() + (0.5 * (i + 1));
				}
				if (value % 1 == 0)
					time = new Time((int) value, 0, 0);
				else
					time = new Time((int) value, 30, 0);
				array.add(time);
			}
		}
//			if (i == 0) {
//				value = startTime.getHours() + (0.5 * i);
//				if (startTime.getMinutes() < 30) {
//					time = new Time((int) value, 0, 0);
//				} else {
//					time = new Time((int) value, 30, 0);
//				}
//			}
//			if (values[i] == 0) {
//				array.add(time);
//			}
//			time = time.valueOf(time.toLocalTime().plusMinutes(30));
//
//		}
		return array;

	}

	public int convertTimeToIndex(Time startTime, Time time) {
		int index = 0;
		if ((startTime.getMinutes() >= 30 && time.getMinutes() >= 30)
				|| (startTime.getMinutes() < 30 && time.getMinutes() < 30)) {
			index = (time.getHours() - startTime.getHours()) * 2;
		} else if (startTime.getMinutes() >= 30 && time.getMinutes() < 30) {
			index = (time.getHours() - startTime.getHours()) * 2 - 1;
		} else if (startTime.getMinutes() < 30 && time.getMinutes() >= 30) {
			index = (time.getHours() - startTime.getHours()) * 2 + 1;
		}
		return index;
	}

	@Override
	public CreateGroupResponse createGroup(Clients_groupVO clients_groupVO) throws Exception {
		CreateGroupResponse response = new CreateGroupResponse();

		try {
			Clients_groupVO clients_group = new Clients_groupVO();
			clients_group = omniCommonPullDAO.returnGroupifExist(clients_groupVO);
			if (clients_group != null) {
				response.setErrorCode(errorGroupNameExist);
				return response;
			}
			/// I have to ask rana to pass times as string

			// insert in clients_group
			omniCommonPushDAO.insertGroup(clients_groupVO);
			// insert in group_template
			omniCommonPushDAO.insertGroupTemplate(clients_groupVO);
			// if the id needed then will be returned
			response.setClients_group_id(clients_groupVO.getClients_group_id());
			// no need for group_template_id to be returned in response
			// response.setGroup_template_id(clients_groupVO.getGroup_template_id());
			response.setResponse("done");
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ReturnListOfGroupsResponse returnListOfGroups(Clients_groupVO clients_groupVO) throws Exception {
		ReturnListOfGroupsResponse response = new ReturnListOfGroupsResponse();
		try {
			// clientCO
			List<Clients_groupVO> listOfGroups = omniCommonPullDAO.returnListOfGroups(clients_groupVO);
			response.setListOfGroups(listOfGroups);
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public AddMonthlyClientResponse addMonthlyClient(ClientCO clientCO) throws Exception {
		AddMonthlyClientResponse response = new AddMonthlyClientResponse();

		try {
			// ClientCO clientCO2 = new ClientCO();
			clientCO.setMobile_number(clientCO.getMobile_number());
			ClientVO client = omniCommonPullDAO.returnMobileNumberIfExist(clientCO);
			ClientVO clientVO = new ClientVO();

			if (client != null) {
				response.setResponse("Client already exist");
//				for (ClientVO c : client) {
				clientVO.setClient_id(client.getClient_id());
				clientVO.setClients_group_id(clientCO.getClients_group_id());
				omniCommonPushDAO.updateClientGroup(clientVO);
			} else {
				clientVO.setCreated_by_admin_id(clientCO.getAdmin_id());
				clientVO.setCreated_by_driver_id(clientCO.getDriver_id());
				clientVO.setUsername(clientCO.getUsername());
				clientVO.setMobile_number(clientCO.getMobile_number());
				omniCommonPushDAO.addMonthlyClient(clientVO);
				response.setResponse("done");
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	public CreateRecurringReservationResponse createRecurringReservation(ReservationCO reservationCO)
			throws DAOException, IOException, ParseException {

		//////// added here\\\\\\\\\\\\\\\\
		PropertiesVO propertiesVO = new PropertiesVO();
		List<PropertiesVO> list = omniCommonPullDAO.returnListProperties(propertiesVO);
		String user = list.stream().filter(l -> l.getProperty_name().equals(userProp)).collect(Collectors.toList())
				.get(0).getProperty_value();
		String pass = list.stream().filter(l -> l.getProperty_name().equals(passProp)).collect(Collectors.toList())
				.get(0).getProperty_value();
		String sid = list.stream().filter(l -> l.getProperty_name().equals(sidProp)).collect(Collectors.toList()).get(0)
				.getProperty_value();
		String url = list.stream().filter(l -> l.getProperty_name().equals(urlProp)).collect(Collectors.toList()).get(0)
				.getProperty_value();
		String triptukURL = list.stream().filter(l -> l.getProperty_name().equals(triptukLink))
				.collect(Collectors.toList()).get(0).getProperty_value();

		Notification notification = omniCommonBO.loadKeyAndUrl();

		if (reservationCO.getClients_group_id() != 0) {

			reservationCO.setClient_id(customerCareClient);

		}

		ClientCO client = new ClientCO();
		if (reservationCO.getApp() == adminCustomerCareApp) {

			if (reservationCO.getClients_group_id() == 0 && reservationCO.getClient_id() == 0) {

				client.setMobile_number(reservationCO.getMobile_number());
				client.setActive(active);
				client.setLanguage_id(reservationCO.getLanguage_id());
				client.setLast_login_client(new java.util.Date(System.currentTimeMillis()));
				// client.setMobile_number(reservationCO.getMobile_number());
				client.setUsername(reservationCO.getUsername());
				client.setLanguage_id(reservationCO.getLanguage_id());

				ClientVO mobileNbExists = omniCommonPullDAO.returnMobileNumberIfExist(client);

				if (mobileNbExists != null) {

					reservationCO.setClient_id(mobileNbExists.getClient_id());

					msg = omniCommonBO.getNotification(mobileNbExists.getLanguage_id(), NEW_RESERVATION_CODE).get(0)
							.getNotification_desc();
					String title = omniCommonBO
							.getNotification(mobileNbExists.getLanguage_id(), NEW_RESERVATION_TITLE_CODE).get(0)
							.getNotification_desc();
					try {
						securityUtilsExt.pushNotification(mobileNbExists.getDevice_token(), title, msg, notification,
								null, null);
					} catch (Exception e) {
						log.info(e.getMessage());
					}

				} else {

					client.setActive(active);
					client.setLanguage_id(reservationCO.getLanguage_id());
					client.setLast_login_client(new java.util.Date(System.currentTimeMillis()));
					client.setMobile_number(reservationCO.getMobile_number());
					client.setUsername(reservationCO.getUsername());
					client.setLanguage_id(1);

					omniCommonPushDAO.insertUsernameMobileNumber(client);

					reservationCO.setClient_id(client.getClient_id());

					String sms = "Welcome to triptuk app, new user created for you , your username is "
							+ reservationCO.getUsername() + " , download the app via" + triptukURL;

					securityUtilsExt.sendSMS(client.getMobile_number(), user, pass, sid, url, sms);
				}

				// !! what is url and sid ??

			}
		}

		/////// added here\\\\\\\\\\\\\\\\\\\\
		Group_template group_template = new Group_template();
		if (reservationCO.getClients_group_name() != null) {
			addClientToGroup(reservationCO);
			Clients_groupVO client_group = new Clients_groupVO();

			/**
			 * problem: if there is no attribute called name in reservationCO so problem
			 * will occurs in omniCommonPullDAO.returnClientsGroup(reservationCO);
			 */
			client_group = omniCommonPullDAO.returnClientsGroup(reservationCO);

			group_template = omniCommonPullDAO.returnGroupTemplate(client_group);

			// String[] dates_list = (group_template.getDates_list().split(","));

			ChangeStringToDateRequest request = new ChangeStringToDateRequest();
			request.setDates(group_template.getDates_list());
			ChangeStringToDateResponse responseOfFunction = new ChangeStringToDateResponse();
			responseOfFunction = changeStringToDate(request);
			List<Date> dates = new ArrayList<Date>();
			dates = responseOfFunction.getListDate();
		}

		/////////////////////////////////////

		/*
		 * Clients_groupVO client_group = new Clients_groupVO();
		 * 
		 *//**
			 * problem: if there is no attribute called name in reservationCO so problem
			 * will occurs in omniCommonPullDAO.returnClientsGroup(reservationCO); /*
			 * client_group = omniCommonPullDAO.returnClientsGroup(reservationCO);
			 * Group_template group_template = new Group_template(); group_template =
			 * omniCommonPullDAO.returnGroupTemplate(client_group);
			 * 
			 * // String[] dates_list = (group_template.getDates_list().split(","));
			 * 
			 * ChangeStringToDateRequest request = new ChangeStringToDateRequest();
			 * request.setDates(group_template.getDates_list()); ChangeStringToDateResponse
			 * responseOfFunction = new ChangeStringToDateResponse(); responseOfFunction =
			 * changeStringToDate(request); List<Date> dates = new ArrayList<Date>(); dates
			 * = responseOfFunction.getListDate();
			 */
		/*
		 * int size = dates_list.length; int[] int_dates_list = new int[size]; for (int
		 * i = 0; i < size; i++) { int_dates_list[i] = Integer.parseInt(dates_list[i]);
		 * }
		 */

		/*
		 * List<Date> dateList = new ArrayList<Date>(); DateFormat dateFormat = new
		 * SimpleDateFormat("yyyy-mm-dd"); Date date1; for (int i = 0; i <
		 * dates_list.length; i++) { date1 = (Date) dateFormat.parse(dates_list[i]);
		 * dateList.add(date1); }
		 */

		/*
		 * int year,month,day;
		 * 
		 * String strDate = dateFormat.format(reservationCO.getStart_date()); String[]
		 * splittedStartDate = strDate.split("-"); for(int i =0 ;
		 * i<int_dates_list.length; i++) { switch
		 * (group_template.getMonthly_weekly_daily()) { case 1: break; case 2: break;
		 * case 3: break; }
		 * 
		 * 
		 * }
		 */

		//////////////////////////////////

		CreateRecurringReservationResponse response = new CreateRecurringReservationResponse();
		List<Machine_driverCO> listDriver = new ArrayList<Machine_driverCO>();
		List<Date> listDate = new ArrayList<Date>();
		List<Date> listNotReserved = new ArrayList<Date>();
		List<Date> listNotReservedBeforeAfter = new ArrayList<Date>();

		if (reservationCO.getLatitude_from() == 0 || reservationCO.getLongitude_from() == 0) {
			response.setErrorCode(errorUpdatingLocation);
			response.setErrorDesc(omniCommonBO.getError(reservationCO.getLanguage_id(), response.getErrorCode()).get(0)
					.getError_desc());
			return response;
		}

		try {

			List<String> listDateString = new ArrayList<String>(Arrays.asList(reservationCO.getListDate().split(",")));
			for (String l : listDateString) {
				Date date = Date.valueOf(l);
				listDate.add(date);
			}
			List<PropertiesVO> listProperties = omniCommonPullDAO.returnListProperties(null);

			String beforeOrAfterString = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(before_or_after)).collect(Collectors.toList()).get(0)
					.getProperty_value();
			int beforeOrAfter = Integer.parseInt(beforeOrAfterString);
			String maxTimeToReachDestinationStr = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(maxTimeToReachDestination)).collect(Collectors.toList())
					.get(0).getProperty_value();
			int maxTimeToReachDestination = Integer.parseInt(maxTimeToReachDestinationStr);
			String newTimeString = listProperties.stream().filter(l -> l.getProperty_name().equals(new_time))
					.collect(Collectors.toList()).get(0).getProperty_value();
			int newTime = Integer.parseInt(newTimeString);

			String newTimeIfNoAvailableDrivreString = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(new_time_if_noAvailable_driver))
					.collect(Collectors.toList()).get(0).getProperty_value();
			int newTimeIfNoAvailableDrivre = Integer.parseInt(newTimeString);
			LocalTime localtime = LocalTime.parse(reservationCO.getTime().toString());
			String tss = df.format(localtime.plusMinutes(maxTimeToReachDestination)) + ":00";
			Time ts = Time.valueOf(tss);
			reservationCO.setTime_slot(ts);
			reservationCO.setReservation_list_status(PENDING);
			reservationCO.setOccurrence_type(occurrenceTypeRecurring);
			reservationCO.setDateOfCreateReservation(new Date(System.currentTimeMillis()));
			reservationCO.setTimeOfCreateReservation(System.currentTimeMillis());
			reservationCO.setDate(listDate.get(0));
			reservationCO.setPenalty_status(penaltyNotPaid);
			/**
			 * 
			 */
			/* if(timesperday>1)for(time) */
			for (int i = 0; i < group_template.getTimes(); i++) {

				omniCommonPushDAO.insertReservation(reservationCO);

				for (Date l : listDate) {
					reservationCO.setDate(l);
					try {
						omniClientPushDAO.insertRecurringReservationEachDay(reservationCO);
					} catch (Exception e) {
						listNotReserved.add(l);
					}
				}
				if (!listNotReserved.isEmpty()) {
					getNewTime(reservationCO, beforeOrAfter, maxTimeToReachDestination, newTime);
					for (Date l : listNotReserved) {

						try {
							reservationCO.setDate(l);
//						reservationCO.setReservation_status(RESERVEDBEFOREORAFTER);
							reservationCO.setReservation_time_status(ReservationAtTimeBeforeOrAfter);
							omniClientPushDAO.insertRecurringReservationEachDay(reservationCO);
						} catch (Exception e) {
							listNotReservedBeforeAfter.add(l);
						}

					}
					if (!listNotReservedBeforeAfter.isEmpty()) {
						getNewTime(reservationCO, beforeOrAfter, maxTimeToReachDestination, newTimeIfNoAvailableDrivre);
						for (Date d : listNotReservedBeforeAfter) {
							try {
								reservationCO.setDate(d);
//							reservationCO.setReservation_list_status(NOTRESERVEDRESERVATION);
								reservationCO.setReservation_time_status(reservationNotReserved);

								omniClientPushDAO.insertRecurringReservationEachDay(reservationCO);

							} catch (Exception e) {
								response.setResponse("does not have availble driver in specific date");
							}
						}
					}
					response.setRecurring_reservation_status(notAllReserved);
					response.setNbOFChangedReservation(listNotReserved.size());
				} else {
					response.setRecurring_reservation_status(allReserved);

				}
				response.setReservation_id(reservationCO.getReservation_id());
				response.setEventName(reservationCO.getEventName());

				if (reservationCO.getClients_group_id() == 0) {

					String msg = omniCommonBO.getNotification(client.getLanguage_id(), NEW_RESERVATION_CODE).get(0)
							.getNotification_desc();

					String title = omniCommonBO.getNotification(client.getLanguage_id(), NEW_RESERVATION_TITLE_CODE)
							.get(0).getNotification_desc();

					try {
						securityUtilsExt.pushNotification(client.getDevice_token(), title, msg, notification, null,
								null);
					} catch (Exception e) {
						log.info(e.getMessage());
					}
				}
			}
			////// added here\\\\\\\\\\\\\\\\

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	public void getNewTime(ReservationCO reservationCO, int beforeOrAfter, int maxTimeToReachDestination, int newTime) {
		LocalTime localtime = LocalTime.parse(reservationCO.getTime().toString());

		String newTimeStr;
		String timeSlotStr;
		Time t;
		Time ts;
		if (beforeOrAfter == before) {
			newTimeStr = df.format(localtime.plusMinutes(newTime + 1)) + ":00";
			t = Time.valueOf(newTimeStr);
			timeSlotStr = df.format(localtime.parse(newTimeStr).plusMinutes(maxTimeToReachDestination)) + ":00";
			ts = Time.valueOf(timeSlotStr);

		} else {
			newTimeStr = df.format(localtime.minusMinutes(newTime + 1)) + ":00";
			t = Time.valueOf(newTimeStr);
			timeSlotStr = df.format(localtime.parse(newTimeStr).plusMinutes(maxTimeToReachDestination + 1)) + ":00";
			ts = Time.valueOf(timeSlotStr);

		}
		reservationCO.setTime(t);

		reservationCO.setTime_slot(ts);
//		return reservationCO;
	}

	@Override
	public ReturnRecurringReservationListResponse returnRecurringReservationList(ReservationCO reservationCO)
			throws Exception {
		ReturnRecurringReservationListResponse response = new ReturnRecurringReservationListResponse();
		try {
			reservationCO.setOccurrence_type(occurrenceTypeRecurring);
//			if (reservationCO.getFilterDate() == null) {
//				reservationCO.setDate(new Date(System.currentTimeMillis()));
//			} else {
//				reservationCO.setDate(Date.valueOf(reservationCO.getFilterDate()));
//			}
			if (reservationCO.getFilterDate() != null) {
				reservationCO.setDate(Date.valueOf(reservationCO.getFilterDate()));
			}
			List<ReservationSC> list = omniCommonPullDAO.returnRecurringReservation(reservationCO);
			if (!list.isEmpty()) {
//				reservationCO.setOccurrence_type(occurrenceTypeRecurring);
//				List<ReservationSC> listRecurringReservation = omniClientPullDAO.returnRecurringReservation(reservationCO);
				for (ReservationSC l : list) {
					int i = 0;
					ReservationVO reservationVO = new ReservationVO();
					reservationVO.setReservation_id(l.getReservation_id());
//					reservationVO.setClient_id(r.);
					// returnReservationsList should be moved to common
					List<ReservationSC> listReservationList = omniCommonPullDAO.returnReservationsList(reservationVO);
					for (ReservationSC rl : listReservationList) {
						if (rl.getReservation_list_status() == EXPIRED)
							i++;
					}
					if (listReservationList.size() == i && i != 0) {
						reservationVO.setReservation_status(EXPIRED);
						// updateReservationStatus should be moved to common
						omniCommonPushDAO.updateReservationStatus(reservationVO);

					}
				}
				response.setList(list);
			} else
				response.setResponse(noData);
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public AddNewFactureResponse addNewFacture(ReservationCO reservationCO) throws Exception {
		AddNewFactureResponse response = new AddNewFactureResponse();
		try {
			omniCommonPushDAO.insertNewFacture(reservationCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	public List<ReturnRecurringClientsResponse> returnRecurringClients() {

		try {
			////////////////////////////////// shouldn't be null
			List<ReturnRecurringClientsResponse> response = null;
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	public CheckRecurringReservationResponse checkRecurringReservationListResponse(ReservationCO reservationCO) {

		CheckRecurringReservationResponse response = new CheckRecurringReservationResponse();
		try {
			List<PropertiesVO> listProperties = omniCommonPullDAO.returnListProperties(null);

			String timeToCheckRecurringReservationString = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(time_to_check_recurring_reservation))
					.collect(Collectors.toList()).get(0).getProperty_value();

			int timeToCheckRecurringReservation = Integer.parseInt(timeToCheckRecurringReservationString);
			DateTimeFormatter df = DateTimeFormatter.ofPattern("kk:mm");
			LocalTime localtime = LocalTime.parse(reservationCO.getTime().toString());
			String tss = df.format(localtime.plusMinutes(timeToCheckRecurringReservation)) + ":00";
			Time ts = Time.valueOf(tss);
			reservationCO.setTime(ts);
			ReturnRecurringReservationListResponse responseList = returnRecurringReservationList(reservationCO);
			// response.setReservation_list_id(reservation_list_id);
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

		return response;
	}

	public AddClientToGroupResponse addClientToGroup(ReservationCO reservationCO) {
		AddClientToGroupResponse response = new AddClientToGroupResponse();

		try {
			List<PropertiesVO> list = omniCommonPullDAO.returnListProperties(null);
			String user = list.stream().filter(l -> l.getProperty_name().equals(userProp)).collect(Collectors.toList())
					.get(0).getProperty_value();
			String pass = list.stream().filter(l -> l.getProperty_name().equals(passProp)).collect(Collectors.toList())
					.get(0).getProperty_value();
			String sid = list.stream().filter(l -> l.getProperty_name().equals(sidProp)).collect(Collectors.toList())
					.get(0).getProperty_value();
			String url = list.stream().filter(l -> l.getProperty_name().equals(urlProp)).collect(Collectors.toList())
					.get(0).getProperty_value();
			String triptukURL = list.stream().filter(l -> l.getProperty_name().equals(triptukLink))
					.collect(Collectors.toList()).get(0).getProperty_value();

			Clients_groupVO clients_groupVO = new Clients_groupVO();
			Clients_groupVO clients_group = new Clients_groupVO();
			clients_groupVO.setClients_group_name(reservationCO.getClients_group_name());
			clients_group = omniCommonPullDAO.returnGroupifExist(clients_groupVO);

			if (clients_group == null) {
				response.setErrorCode(errorGroupNameExist);
				response.setResponse_msg("NO SUCH GROUP NAME!");
				return response;
			}
			ClientCO client = new ClientCO();
			client.setMobile_number(reservationCO.getMobile_number());
			ClientVO mobileNbExists = omniCommonPullDAO.returnMobileNumberIfExist1(client);
			if (mobileNbExists == null) {
				// is those parameters enough????
				// there is a need to create reservation?? or just linking this client to group
				// is enough??
				client.setActive(active);
				client.setLanguage_id(reservationCO.getLanguage_id());
				client.setLast_login_client(new java.util.Date(System.currentTimeMillis()));
				client.setUsername(reservationCO.getUsername());
				client.setClients_group_id(clients_group.getClients_group_id());
				client.setMobile_number(reservationCO.getMobile_number());
				omniCommonPushDAO.insertUsernameMobileNumber(client);
				String sms = "Welcome to triptuk app, new user created for you , your username is "
						+ reservationCO.getUsername() + " , download the app via" + triptukURL;

				securityUtilsExt.sendSMS(client.getMobile_number(), user, pass, sid, url, sms);
			} else {
				mobileNbExists.setClients_group_id(clients_group.getClients_group_id());
				omniCommonPushDAO.updateClientsGroupID(mobileNbExists);
			}

			response.setClient_id(mobileNbExists.getClient_id());
			response.setClients_group_id(clients_group.getClients_group_id());

			return response;

		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	public ChangeStringToDateResponse changeStringToDate(ChangeStringToDateRequest changeStringToDateRequest) {

		try {
			ChangeStringToDateResponse response = new ChangeStringToDateResponse();
			String[] dates_list = (changeStringToDateRequest.getDates().split(","));
			List<Date> dateList = new ArrayList<Date>();
			// DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			// Date date1=formatter1.parse(sDate1);
			// SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-mm-dd");
			String periods;
			Date date1;
			int times = changeStringToDateRequest.getTimes_per_day();
			for (int i = 0; i < dates_list.length; i++) {
				// date1 =(Date) dateFormat.parse(dates_list[i]);
				date1 = Date.valueOf(dates_list[i]);
				for (int j = 0; j < times; j++) {
					dateList.add(date1);

				}

			}

			response.setListDate(dateList);
			response.setMsg("done");
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public RenewMonthlyClientResponse renewMonthlyClient(ClientCO clientCO) throws Exception {
		try {
			RenewMonthlyClientResponse response = new RenewMonthlyClientResponse();
			ReservationCO reservationCO = omniCommonPullDAO.retrunReservationData(clientCO);
			reservationCO.setListDate(clientCO.getDates_list());
			CreateRecurringReservationResponse createRecurringReservationResponse = createRecurringReservation(
					reservationCO);
			PathPropertyUtil.copyProperties(createRecurringReservationResponse, response);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public SuspendMonthlyClientResponse suspendMonthlyClient(ReservationCO reservationCO) throws Exception {
		try {
			/*
			 * ReservationVO reservationVO = new ReservationVO();
			 * PathPropertyUtil.copyProperties(reservationCO, reservationVO);
			 * ReservationListVO reservationListVO = new ReservationListVO();
			 * List<ReservationVO> listReservationVO=
			 * omniCommonPullDAO.returnOneTimeReservationDetails(reservationVO);
			 * SuspendMonthlyClientResponse response = new SuspendMonthlyClientResponse();
			 * CancelRecurringReservationResponse response1 = new
			 * CancelRecurringReservationResponse(); if (reservationListVO == null) {
			 * response.setResponse("no such reservation id!"); } else {
			 */
			CancelRecurringReservationResponse response1 = new CancelRecurringReservationResponse();
			SuspendMonthlyClientResponse response = new SuspendMonthlyClientResponse();
			response1 = cancelRecurringReservation(reservationCO);
			PathPropertyUtil.copyProperties(response1, response);
			/* } */

			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnMonthlyClientDataResponse returnMonthlyClientData(
			ReturnMonthlyClientDataRequest retreiveMonthlyUserDataRequest) throws Exception {
		try {
			ReturnMonthlyClientDataResponse response = new ReturnMonthlyClientDataResponse();
			List<ClientCO> ClientsGroupList = omniCommonPullDAO.returnClientsGroupList(new ClientCO());
			List<ClientCO> MonthlyClientData = omniCommonPullDAO.returnMonthlyClientData(new ClientCO());
			ClientsGroupList.addAll(MonthlyClientData);
			response.setListClientCO(ClientsGroupList);

			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

}