package com.path.bo.omni.common.base;

import java.security.Key;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.path.bo.omni.admin.OmniAdminBO;
import com.path.bo.omni.client.OmniClientBO;
import com.path.bo.omni.common.OmniCommonBO;
import com.path.bo.omni.driver.OmniDriverBO;
import com.path.dao.omni.client.pull.OmniClientPullDAO;
import com.path.dao.omni.client.push.OmniClientPushDAO;
import com.path.dao.omni.common.pull.OmniCommonPullDAO;
import com.path.dao.omni.common.push.OmniCommonPushDAO;
import com.path.dao.omni.driver.pull.OmniDriverPullDAO;
import com.path.dao.omni.driver.push.OmniDriverPushDAO;
import com.path.dbmaps.vo.ClientDeviceCO;
import com.path.dbmaps.vo.DeviceKeysSC;
import com.path.dbmaps.vo.ErrorVO;
import com.path.dbmaps.vo.Keys;
import com.path.dbmaps.vo.NotificationVO;
import com.path.dbmaps.vo.PropertiesVO;
import com.path.dbmaps.vo.UserOTP;
import com.path.dbmaps.vo.VersionCO;
import com.path.lib.log.Log;

/**
 * 
 * Copyright 2017, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: Alaa Al Hek
 *
 *          OmniBaseBO.java used to
 */
public class OmniBaseBO

{
	protected OmniCommonPullDAO omniCommonPullDAO;
	protected OmniCommonPushDAO omniCommonPushDAO;
	protected OmniDriverPullDAO omniDriverPullDAO;
	protected OmniDriverPushDAO omniDriverPushDAO;
	protected OmniClientPushDAO omniClientPushDAO;
	protected OmniClientPullDAO omniClientPullDAO;
	
	public static int[] dates_array;

	public static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static final String CANCEL_RESERVATION = "cancelReservation.properties";

	public static HashMap<String, Key> KeyHashMap = new HashMap<String, Key>();

	public static HashMap<String, DeviceKeysSC> clientKeysHashMap = new HashMap<String, DeviceKeysSC>();

	public static HashMap<String, DeviceKeysSC> driverKeysHashMap = new HashMap<String, DeviceKeysSC>();

	public static HashMap<String, DeviceKeysSC> adminKeysHashMap = new HashMap<String, DeviceKeysSC>();

	public static HashMap<PublicKey, Keys> backEndKeyHashMap = new HashMap<PublicKey, Keys>();

	public static HashMap<String, String> otpClientHashmap = new HashMap<String, String>();

	public static HashMap<String, UserOTP> blockClientHashmap = new HashMap<String, UserOTP>();

	public static HashMap<String, UserOTP> blockDriverHashmap = new HashMap<String, UserOTP>();

	public static HashMap<String, String> otpDriverHashmap = new HashMap<String, String>();

	public static HashMap<Integer, Long> cancelTimerHashMap = new HashMap<Integer, Long>();

	public static HashMap<Integer, HashMap<String, Integer>> cancelCounterHashMap = new HashMap<Integer, HashMap<String, Integer>>();

	public static HashMap<Integer, Integer> counterClientReservation = new HashMap();

	public static HashMap<String, VersionCO> versionHashMap = new HashMap<String, VersionCO>();

	public static HashMap<String, ClientDeviceCO> clientDeviceHashMap = new HashMap<String, ClientDeviceCO>();

	public static HashMap<Integer, Integer> driverAvailabilityHashMap = new HashMap<Integer, Integer>();

	public static HashMap<Integer, Integer> adminStatusHashMap = new HashMap<Integer, Integer>();

	public static List<ErrorVO> listErrors;

	public static List<NotificationVO> listNotifications;

	public static HashMap<Integer, HashMap<Integer, String>> languageErrorHashmap = new HashMap<Integer, HashMap<Integer, String>>();

	public final static String ERROR = "SOMETHING IS WRONG";
	public int errorCodeAuthenticate = 9001;
	public int errorCodeValidateOTP = 9002;
	public int errorCodeCheckUsername = 9003;
	public int errorCodeLoginInvalidUsername = 9004;
	public int errorCodeLoginIncorrectPassword = 9005;
	public int errorCodeReturnDriverInfo = 9006;
	public int errorCodeIncorrectPass = 9008;
	public int errorCodeAddDriver = 9009;
	public int errorCodeAddMachine = 9010;
	public int errorCodeAddAdmin = 9011;
//	public int errorCodeReturnReservationListNoReservation = 9012;
	public int errorCodeCancelReservationNumber = 9015;
	public int errorCodeCancelReservationTooLate = 9016;
	public int errorCodeForgetPassword = 9017;
	public int errorCodeUpdateMachineInfo = 9018;
	public int errorCodeAddStore = 9020;
	public int errorCodeLogin = 9021;
	public int errorCodeRegisterUserInvalidUsername = 9022;
	public int errorCodeCreateReservationNumber = 9023;
	public int errorCodeLoginDriverIncorrectUsername = 9024;
	public int errorCodeCreateReservClientIsBlocked = 9025;
	public int errorCodeCreateReservLocationNull = 9026;
	public int errorCodeValidateOTPBlocked = 9027;
	public int errorUpdatingLocation = 9028;
	public int errorAddMachineDriverIncorrectUsername = 9029;
	public int errorAddMachineDriverIncorrectMachine = 9030;
	public int errorAddMachineDriverLocation = 9031;
	public int errorGenerateOTP = 9032;
	public int errorUpdateReservationListStatus = 9033;
	public int updateDeviceToken = 9034;
	public int errorTlubtukReservationCanceled = 9035;
	public int errorReferenceMobileNumber = 9036;
	public int errorEmptyListOfContacts = 9037;
	public int errorGroupNameExist = 10009;

	public static final int admin_inactive = 0;
	public static final int admin_active = 1;
	public static final int is_customer_care = 1;
	
	/**
	 * static final values for monthly, weekly and daily group reservation
	 */
	public static final int monthly = 1;
	public static final int weekly = 2;
	public static final int daily = 3;

	/*
	 * static value for facture status
	 */
	public static int factureNotPaid = 0;
	public static int faturePaid = 1;

	public int free = 1;
	public int notFree = 0;

	public int active = 0;
	public int inactive = 1;

	public int hasntBrokers = 0;
	public static int HASBROKERS = 1;

	/*
	 * status of client , driver and machine_driver , and device
	 */
	public int clientStatusLogin = 0;
	public int clientStatusLogout = 1;
	public int statusLogin = 0;
	public int statusLogout = 1;
	public int machineDriverStatusAvailable = 0;
	public int machineDriverStatusUnavailable = 1;
	public int machineDriverStatusprob = 2;

	public int deviceBlocked = 1;

	public int quick_reservation_driver = -1;

	/*
	 * constant for machine status
	 */
	public int MACHINE_AVAILABLE = 0;
	public int MACHINE_UNAVAILABLE = 1;
	public int MACHINE_INMAINTENANCE = 2;
	public int MACHINE_SUSPENDED = 3;

	/*
	 * constant for type of reservation
	 */
	public int reservationListStatusApproved = 0;
	public int reservationTypeTrip = 0;
	public int reservationTypeMachine = 1;
	public int reservationTypeTlubTuk = 2;
	public int occurrenceTypeImmediate = 0;
	public int occurrenceTypeFuture = 1;
	public int occurrenceTypeRecurring = 2;

	/*
	 * constant for msg
	 */
	public String noData = "No data found";
	public int responseCodeCombinationNotExist = 10001;
	public int reserveNewMachine = 10002;
	public int noReservationToChange = 10003;
	public int freeReservation = 10004;
	public int penaltyForReservation = 10005;
	public int multipleInvite = 10006;
	public int deviceMobilbeNumberExist = 10007;
	public int noRecurringReservation = 10008;
	/*
	 * status of reservation list
	 */
	public int PENDING = 0;
	public int TAKE_TRIP = 1;
	public int WAIT_USER = 2;
	public int START_TRIP = 3;
	public int FINISH_TRIP = 4;
	public int MISSED_TRIP = 5;
	public int UNAVAILABLE = 6;
	public int CHANGED = 7;
	public int CANCELED = 8;
	public int EXPIRED = 9;
	public int RESERVEDBEFOREORAFTER = 10;
	public int NOTRESERVEDRESERVATION = 11;
	public int NOTCONFIRMED = 12;

	public static int TLUBTUKNOTCONFIRMED = 0;
	public static int TLUBTUKCONFIRMED = 1;
	/*
	 * quick reservation type
	 */
	public static int QUICK_RESERVATION_START = 0;
	public static int QUICK_RESERVATION_BY_PHONE = 1;
	public static int QUICK_RESERVATION_TLUBTUK = 2;

	public int ReservationAtTimeBeforeOrAfter = 1;
	public int reservationNotReserved = 2;

	public int penaltyPaid = 1;
	public int penaltyNotPaid = 0;
	public int INVITED = 0;
	public int NOTINVITED = 1;
	public int HASACCOUNT = 2;
	/*
	 * constant for language_id
	 */
	public int english = 1;
	public int french = 2;
	public int arabic = 3;

	/*
	 * msg for notification
	 */

	public String msg;

	public String TAKE_TRIP_CODE = "C001";
	public String WAIT_CLIENT_CODE = "C002";
	public String NEW_RESERVATION_CODE = "D001";
	public String RESERVATION_CANCELLED_CODE = "D002";
	public String TIME_REACH_CLIENT_CODE = "C003";
	public String COST_CODE = "C004";
	public String NEW_TUKTUK_CODE = "C005";
	public String EMAIL_SENDED_CODE = "C006";
	public String VALID_USERNAME_CODE = "C007";
	public String MISSED_TRIP_CODE = "C008";
	public String DRIVER_LOCATION_CODE = "A001";
	public String OTP_CODE = "C009";
	public String NEW_RESERVATION_TITLE_CODE = "D003";
	public String RESERVATION_STATUS_TITLE_CODE = "C009";
	public String DRIVER_LOCATION_TITLE_CODE = "A002";
	public String FACTURE_TITLE_CODE = "C010";
	public String RESERVATION_CANCELLED_TITLE_CODE = "D004";
	public String PENALTY_CODE = "C011";
	public String FREE_TRIP_TITLE_CODE = "C012";
	public String FREE_TRIP_CODE = "C013";
	public String INVITATION_CODE = "C014";
	public String NEW_TLUBTUK_RESERVATION_TITLE_CODE = "D005";
	public String NEW_TLUBTUK_RESERVATION_CODE = "D006";
	public String DRIVER_LOCATION_GPS_TITLE_CODE = "A003";
	public String DRIVER_LOCATION_GPS_CODE = "A004";
	public String DRIVER_AVAILIBILITY_TITLE_CODE = "A005";
	public String DRIVER_AVAILIBILITY_CODE = "A006";

	public String numberOfOTP = "numberOfOTP";
	public String numberOfInvalidOTP = "numberOfInvalidOTP";
	public String api_url_fcm = "api_url_fcm";
	public String auth_key_fcm = "auth_key_fcm";
//	public String cost_of_100_m = "costOf100m";
//	public String cost_of_100_m_from_to_client = "costOf100mFromToClient";
	public String hash_password = "hashPassword";
	public String company_mobile = "companyMobile";
	public String cancel_counter = "cancelCounter";
	public String number_of_reservations = "numberOfReservations";
	public String maxTimeToReachDestination = "maxTimeToReachDestination";
	public String velocity = "velocity";
	public String availabilityPeriod = "availabilityPeriod";
	public String timeFromDestinationToClient = "timeFromDestinationToClient";
	public String cancelCounter = "cancelCounter";
	public String timeToCancel = "timeToCancel";
	public String limitReservations = "limitReservations";
	public String companyEmail = "companyEmail";
	public String companyEmailPass = "companyEmailPass";
	public String enableCrashlytics = "enableCrashlytics";
	public String userProp = "user";
	public String passProp = "pass";
	public String sidProp = "sid";
	public String urlProp = "url";
	public String time_to_expire = "timeToExpire";
	public String hashUser = "hashUser";
	public String hashDriver = "hashDriver";
	public String price_benzin = "priceBenzin";
	public String km_tanaki = "km/tanaki";
	public String number_of_second = "numberOfSecond";
	public String propToFrontEndClient = "propToFrontEndClient";
	public String propToFrontEndDriver = "propToFrontEndDriver";
	public String new_time = "newTime";
	public String before_or_after = "beforeOrAfter";
	public String new_time_if_noAvailable_driver = "newTimeIfNoAvailableDrivre";
	public String penalty = "penalty";
	public String limit_dismissed = "limitDismissed";
	public String grace_period = "gracePeriod";
	public String nb_free_reservation = "nbOfFreeReservation";
	public String time_to_expire_tlubtuk_reservation = "timeToExpireTlubtukReservation";
	public String nb_of_free_reservation = "nbOfFreeReservation";
	public String param_for_cost = "paramForCost";
	public String inviteSMS = "inviteSMS";
	public String user_driver_distance = "userDriverDistance";
	public String percentage_cost = "percentageCost";
	public String param_cost4to7 = "paramCost4to7";
	public String param_cost7to10 = "paramCost7to10";
	public String param_costHigherThan10 = "paramCostHigherThan10";
	public String triptukLink = "triptukLink";
	public String grace_period_to_update_driver_location = "gracePeriodToUpdateDriverLocation";
	public String distance_for_checking_driver_location = "distanceForCheckingDriverLocation";
	public String time_to_check_driver_location = "timeToCheckDriverLocation";
	public String distance_moving_driver = "distanceMovingDriver";
	public String mid_night = "midnight";
	public String start_of_working_day = "startOfWorkingDay";
	public String added_percentage_cost = "addedPercentageCost";
	public String distance_between_driver_client_recurring = "distanceBetweenDriverClientRecurring";
	public String distance_between_driver_driver_recurring = "distanceBetweenDriverDriverRecurring";
	public String time_to_turn_off_driver = "timeToTurnOffDriver";
	public String discount_app_reservation = "discountAppReservation";
	public String time_of_reservation_in_near_future = "timeOfReservationInNearFuture";
	public String time_to_check_recurring_reservation = "timeToCheckRecurringReservation";
	/*
	 * device blockage status
	 */
	public int unblockedDevice = 0;

	/*
	 * machine driver status now
	 */
	public int on = 1;
	public int off = 0;
	/*
	 * recurring reservation status , all reserved at the time needed = 0, we have
	 * some reservation reserved before or after = 1
	 */
	public int allReserved = 0;
	public int notAllReserved = 1;
	/*
	 * the new time needed is before =0 the new time needed is after =1
	 */
	public int before = 0;
	public int after = 1;
	/*
	 * status otp
	 */
	public int newOtp = 0;
	public int usedOtp = 1;
	/*
	 * machine driver status if it is in reservation or no
	 */
	public int inReservation = 1;
	public int notInReservation = 0;

	public static int temp_reservation_id = 0;

	public String brokerReservationCancel = "brokerReservationCancel";
	public String unavailableDriverCancelation = "unavailableDriverCancelation";
	public String switchDriver = "switchDriver";

	public static final int clientApp = 0;
	public static final int driverApp = 1;
	public static final int adminCustomerCareApp = 2;
	public static final int customerCareClient = -2;

	public static final int no_switch_status = 0;
	public static final int switch_status = 1;

	public static final int driver_availability_off = 0;
	public static final int driver_availability_on = 1;

	public static final int fromThread = 1;
	public static final int notFromThread = 0;

	public static final int create_driver = 0;
	public static final int update_driver = 1;

	protected final static Log log = Log.getInstance();

	protected OmniCommonBO omniCommonBO;
	protected OmniDriverBO omniDriverBO;
	protected OmniClientBO omniClientBO;
	protected OmniAdminBO omniAdminBO;

	public void setOmniAdminBO(OmniAdminBO omniAdminBO) {
		this.omniAdminBO = omniAdminBO;
	}

	public void setOccurrenceTypeImmediate(int occurrenceTypeImmediate) {
		this.occurrenceTypeImmediate = occurrenceTypeImmediate;
	}

	public void setOccurrenceTypeFuture(int occurrenceTypeFuture) {
		this.occurrenceTypeFuture = occurrenceTypeFuture;
	}

	public void setOccurrenceTypeRecurring(int occurrenceTypeRecurring) {
		this.occurrenceTypeRecurring = occurrenceTypeRecurring;
	}

	public void setOmniClientBO(OmniClientBO omniClientBO) {
		this.omniClientBO = omniClientBO;
	}

	public void setOmniDriverBO(OmniDriverBO omniDriverBO) {
		this.omniDriverBO = omniDriverBO;
	}

	public void setOmniCommonPushDAO(OmniCommonPushDAO omniCommonPushDAO) {
		this.omniCommonPushDAO = omniCommonPushDAO;
	}

	public static HashMap<String, Key> getKeyHashMap() {
		return KeyHashMap;
	}

	public static void setKeyHashMap(HashMap<String, Key> keyHashMap) {
		KeyHashMap = keyHashMap;
	}

	public static HashMap<String, String> getOtpHashMap() {
		return otpClientHashmap;
	}

	public static HashMap<String, UserOTP> getBlockClientHashmap() {
		return blockClientHashmap;
	}

	public static void setBlockClientHashmap(HashMap<String, UserOTP> blockClientHashmap) {
		OmniBaseBO.blockClientHashmap = blockClientHashmap;
	}

	public static HashMap<String, UserOTP> getBlockDriverHashmap() {
		return blockDriverHashmap;
	}

	public static void setBlockDriverHashmap(HashMap<String, UserOTP> blockDriverHashmap) {
		OmniBaseBO.blockDriverHashmap = blockDriverHashmap;
	}

	public static void setOtpHashMap(HashMap<String, String> otpHashMap) {
		OmniBaseBO.otpClientHashmap = otpHashMap;
	}

	public static String getCancelReservation() {
		return CANCEL_RESERVATION;
	}

	public static HashMap<PublicKey, Keys> getBackEndKeyHashMap() {
		return backEndKeyHashMap;
	}

	public static void setBackEndKeyHashMap(HashMap<PublicKey, Keys> backEndKeyHashMap) {
		OmniBaseBO.backEndKeyHashMap = backEndKeyHashMap;
	}

	public static HashMap<Integer, Long> getCancelTimerHashMap() {
		return cancelTimerHashMap;
	}

	public static void setCancelTimerHashMap(HashMap<Integer, Long> cancelTimerHashMap) {
		OmniBaseBO.cancelTimerHashMap = cancelTimerHashMap;
	}

	public static HashMap<Integer, Integer> getCounterClientReservation() {
		return counterClientReservation;
	}

	public static void setCounterClientReservation(HashMap<Integer, Integer> counterClientReservation) {
		OmniBaseBO.counterClientReservation = counterClientReservation;
	}

	public static int getTemp_reservation_id() {
		return temp_reservation_id;
	}

	public static void setTemp_reservation_id(int temp_reservation_id) {
		OmniBaseBO.temp_reservation_id = temp_reservation_id;
	}

	public static Log getLog() {
		return log;
	}

	public OmniCommonBO getOmniCommonBO() {
		return omniCommonBO;
	}

	public void setOmniCommonBO(OmniCommonBO omniCommonBO) {
		this.omniCommonBO = omniCommonBO;
	}

	public static HashMap<Integer, HashMap<String, Integer>> getCancelCounterHashMap() {
		return cancelCounterHashMap;
	}

	public static void setCancelCounterHashMap(HashMap<Integer, HashMap<String, Integer>> cancelCounterHashMap) {
		OmniBaseBO.cancelCounterHashMap = cancelCounterHashMap;
	}

	public static List<ErrorVO> getListErrors() {
		return listErrors;
	}

	public static void setListErrors(List<ErrorVO> listErrors) {
		OmniBaseBO.listErrors = listErrors;
	}

	public static HashMap<Integer, HashMap<Integer, String>> getLanguageErrorHashmap() {
		return languageErrorHashmap;
	}

	public static void setLanguageErrorHashmap(HashMap<Integer, HashMap<Integer, String>> languageErrorHashmap) {
		OmniBaseBO.languageErrorHashmap = languageErrorHashmap;
	}

	public OmniCommonPullDAO getOmniCommonPullDAO() {
		return omniCommonPullDAO;
	}

	public void setOmniCommonPullDAO(OmniCommonPullDAO omniCommonPullDAO) {
		this.omniCommonPullDAO = omniCommonPullDAO;
	}

	public static HashMap<String, String> getOtpClientHashmap() {
		return otpClientHashmap;
	}

	public static void setOtpClientHashmap(HashMap<String, String> otpClientHashmap) {
		OmniBaseBO.otpClientHashmap = otpClientHashmap;
	}

	public static HashMap<String, String> getOtpDriverHashmap() {
		return otpDriverHashmap;
	}

	public static void setOtpDriverHashmap(HashMap<String, String> otpDriverHashmap) {
		OmniBaseBO.otpDriverHashmap = otpDriverHashmap;
	}

	public void setOmniDriverPullDAO(OmniDriverPullDAO omniDriverPullDAO) {
		this.omniDriverPullDAO = omniDriverPullDAO;
	}

	public void setOmniDriverPushDAO(OmniDriverPushDAO omniDriverPushDAO) {
		this.omniDriverPushDAO = omniDriverPushDAO;
	}

	public void setOmniClientPushDAO(OmniClientPushDAO omniClientPushDAO) {
		this.omniClientPushDAO = omniClientPushDAO;
	}

	public void setOmniClientPullDAO(OmniClientPullDAO omniClientPullDAO) {
		this.omniClientPullDAO = omniClientPullDAO;
	}

	public static HashMap<String, DeviceKeysSC> getClientKeysHashMap() {
		return clientKeysHashMap;
	}

	public static void setClientKeysHashMap(HashMap<String, DeviceKeysSC> clientKeysHashMap) {
		OmniBaseBO.clientKeysHashMap = clientKeysHashMap;
	}

	public static HashMap<String, DeviceKeysSC> getDriverKeysHashMap() {
		return driverKeysHashMap;
	}

	public static void setDriverKeysHashMap(HashMap<String, DeviceKeysSC> driverKeysHashMap) {
		OmniBaseBO.driverKeysHashMap = driverKeysHashMap;
	}

	public static HashMap<String, DeviceKeysSC> getAdminKeysHashMap() {
		return adminKeysHashMap;
	}

	public static void setAdminKeysHashMap(HashMap<String, DeviceKeysSC> adminKeysHashMap) {
		OmniBaseBO.adminKeysHashMap = adminKeysHashMap;
	}

	public static HashMap<String, VersionCO> getVersionHashMap() {
		return versionHashMap;
	}

	public static void setVersionHashMap(HashMap<String, VersionCO> versionHashMap) {
		OmniBaseBO.versionHashMap = versionHashMap;
	}

	public static List<NotificationVO> getListNotifications() {
		return listNotifications;
	}

	public static void setListNotifications(List<NotificationVO> listNotifications) {
		OmniBaseBO.listNotifications = listNotifications;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static HashMap<String, ClientDeviceCO> getClientDeviceHashMap() {
		return clientDeviceHashMap;
	}

	public static void setClientDeviceHashMap(HashMap<String, ClientDeviceCO> clientDeviceHashMap) {
		OmniBaseBO.clientDeviceHashMap = clientDeviceHashMap;
	}

	public static HashMap<Integer, Integer> getDriverAvailabilityHashMap() {
		return driverAvailabilityHashMap;
	}

	public static void setDriverAvailabilityHashMap(HashMap<Integer, Integer> driverAvailabilityHashMap) {
		OmniBaseBO.driverAvailabilityHashMap = driverAvailabilityHashMap;
	}

	public static HashMap<Integer, Integer> getAdminStatusHashMap() {
		return adminStatusHashMap;
	}

	public static void setAdminStatusHashMap(HashMap<Integer, Integer> adminStatusHashMap) {
		OmniBaseBO.adminStatusHashMap = adminStatusHashMap;
	}

}
