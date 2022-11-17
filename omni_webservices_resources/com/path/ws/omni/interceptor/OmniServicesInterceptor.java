package com.path.ws.omni.interceptor;

import java.security.PublicKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.Logger;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.path.bo.omni.common.OmniCommonBO;
import com.path.bo.omni.common.base.OmniBaseBO;
import com.path.dao.omni.client.pull.OmniClientPullDAO;
import com.path.dao.omni.common.pull.OmniCommonPullDAO;
import com.path.dao.omni.common.push.OmniCommonPushDAO;
import com.path.dbmaps.vo.AdminCO;
import com.path.dbmaps.vo.AdminVO;
import com.path.dbmaps.vo.ClientCO;
import com.path.dbmaps.vo.ClientDeviceCO;
import com.path.dbmaps.vo.DeviceKeysSC;
import com.path.dbmaps.vo.DeviceSC;
import com.path.dbmaps.vo.DeviceVO;
import com.path.dbmaps.vo.DriverCO;
import com.path.dbmaps.vo.DriverVO;
import com.path.dbmaps.vo.EdKeysVO;
import com.path.dbmaps.vo.PropertiesVO;
import com.path.dbmaps.vo.UserOTP;
import com.path.dbmaps.vo.VersionCO;
import com.path.dbmaps.vo.VersionVO;
//import com.path.bo.omni.common.impl.ChannelLoginBOImpl;
import com.path.lib.common.util.SecurityUtilsExt;
import com.path.lib.log.Log;
import com.path.ws.omni.vo.common.HandShakeResponse;
import com.path.ws.omni.vo.common.OmniCommonRequest;
import com.path.ws.omni.vo.common.ResponseBaseObject;

public class OmniServicesInterceptor implements MethodInterceptor {

	protected static Logger pathlog;
	protected final static Log log = Log.getInstance();
	protected HandShakeResponse handShakeResponse;
	ResponseBaseObject res = new ResponseBaseObject();
	protected OmniClientPullDAO omniClientPullDAO;
	protected OmniCommonPullDAO omniCommonPullDAO;
	protected OmniCommonPushDAO omniCommonPushDAO;
	protected OmniCommonBO omniCommonBO;

	public void setOmniClientPullDAO(OmniClientPullDAO omniClientPullDAO) {
		this.omniClientPullDAO = omniClientPullDAO;
	}

	public void setOmniCommonPullDAO(OmniCommonPullDAO omniCommonPullDAO) {
		this.omniCommonPullDAO = omniCommonPullDAO;
	}

	public void setOmniCommonPushDAO(OmniCommonPushDAO omniCommonPushDAO) {
		this.omniCommonPushDAO = omniCommonPushDAO;
	}

	SecurityUtilsExt securityUtilsExt;
	OmniBaseBO omniBaseBO;
	ResponseBaseObject responseBaseObject;
	DeviceKeysSC deviceKeysSC;
	PublicKey frontEndPublicKey;
	String data;
	int i = 0;
	Properties prop = new Properties();
	int errorCodeValidateOTPBlocked = 9026;
	String token;
	String secretKeyToken;
	int deviceUnblocked = 0;
	int deviceBlocked = 1;
	int EXPIRED = 3;
	int INVALID = 1;
	int mendatory = 1;
	int oldDevice = 1;
	int unavailableDriver = 0;
	int admin_inactive = 0;
	int admin_active = 1;

	int client_app = 0;
	int driver_app = 1;
	int admin_app = 2;

	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	String numberOfInvalidOTP = "numberOfInvalidOTP";

	@SuppressWarnings("unused")
	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		Object response = null;

		try {
			Object[] test = method.getArguments();

			if (method.getMethod().getName() == "handShake_service" || method.getMethod().getName() == "test_service"
//					|| method.getMethod().getName() == "returnCostOfDistance_service"
//					|| method.getMethod().getName() == "killDriver_service"
//					|| method.getMethod().getName() == "updateCostByDriver_service"
					|| method.getMethod().getName() == "createReservation_service"
//					|| method.getMethod().getName() == "updateDriverLocationsOffline_service"
					|| method.getMethod().getName() == "createReservationOffline_service"
					|| method.getMethod().getName() == "returnListOfGroups_service"
					|| method.getMethod().getName() == "createGroup_service"
					|| method.getMethod().getName() == "addClientToGroup_Service"
					|| method.getMethod().getName() == "changeStringToDate_Service"
					|| method.getMethod().getName() == "generateOTP_service"
					|| method.getMethod().getName() == "createRecurringReservation_service"
					|| method.getMethod().getName() == "suspendMonthlyClient_service"
					|| method.getMethod().getName() == "retreiveMonthlyUserData_Service") {
				response = method.proceed();

			} else {

				OmniCommonRequest r = (OmniCommonRequest) test[0];
				String device_UUID = r.getDevice_UUID();
				String versionReq = r.getVersion();
				EdKeysVO edKeysVO = new EdKeysVO();
				int app = r.getApp();

				if (app == client_app) {
					if (method.getMethod().getName() != "handShake_service") {
						VersionVO versionVO = new VersionVO();
						versionVO.setApp(app);
						VersionVO versionVO2 = omniCommonPullDAO.returnVersion(versionVO);

						int versionResult = securityUtilsExt.getNewVersion(versionReq, versionVO2.getVersion());

						VersionCO versionCO = new VersionCO(app, versionVO2.getStatus(), versionResult);
						omniBaseBO.getVersionHashMap().put(device_UUID, versionCO);
						if (versionResult == -1 && versionVO2.getStatus() == mendatory) {
							return response;
						}

					}

				}

				if (app == driver_app) {
					if (r.getDriver_id() != 0) {
						if ((method.getMethod().getName() != "authenticateDriver_service" || r.getDriver_id() != 0)
								&& method.getMethod().getName() != "loginDriver_service") {

							DriverVO driverVO = new DriverVO();
							driverVO.setDriver_id(r.getDriver_id());

							DriverCO driverCO = omniCommonPullDAO.returnDriverAvailability(driverVO);
							if (driverCO != null && driverCO.getDriver_availability() == 1) {
								if (omniBaseBO.getDriverAvailabilityHashMap().containsKey(r.getDriver_id())) {
									if (omniBaseBO.getDriverAvailabilityHashMap().get(r.getDriver_id()) == 0) {
										omniBaseBO.getDriverAvailabilityHashMap().remove(r.getDriver_id());
									}
								}
							} else {

								omniBaseBO.getDriverAvailabilityHashMap().put(r.getDriver_id(), unavailableDriver);
								return response;
							}

						}
					}
				}

				if (app == admin_app) {
					if ((method.getMethod().getName() != "authenticateAdmin_service" || r.getAdmin_id() != 0)
							&& method.getMethod().getName() != "loginAdmin_service") {
						AdminVO adminVO = new AdminVO();
						adminVO.setAdmin_id(r.getAdmin_id());

						AdminCO adminCO = omniCommonPullDAO.returnAdminStatus(adminVO);
						if (adminCO != null && adminCO.getActive() == admin_active) {
							if (OmniBaseBO.getAdminStatusHashMap().containsKey(r.getAdmin_id())) {
								if (OmniBaseBO.getAdminStatusHashMap().get(r.getAdmin_id()) == 0) {
									OmniBaseBO.getAdminStatusHashMap().remove(r.getAdmin_id());
								}
							}
						} else {
							OmniBaseBO.getAdminStatusHashMap().put(r.getAdmin_id(), admin_inactive);
							return response;
						}

					}
				}

				if (method.getMethod().getName() != "handShake_service"
						&& method.getMethod().getName() != "authenticateClient_service"
						&& method.getMethod().getName() != "authenticateDriver_service"
						&& method.getMethod().getName() != "authenticateAdmin_service"
						&& method.getMethod().getName() != "registerClient_service"
						&& method.getMethod().getName() != "loginClient_service"
						&& method.getMethod().getName() != "loginDriver_service"
						&& method.getMethod().getName() != "loginAdmin_service"
						&& method.getMethod().getName() != "generateOTP_service"
						&& method.getMethod().getName() != "validateOTP_service"
						&& method.getMethod().getName() != "checkUsername_service"
						&& method.getMethod().getName() != "forgetPasswordClient_service"
						&& method.getMethod().getName() != "forgetPasswordDriver_service"
						&& method.getMethod().getName() != "resetPasswordClient_service"
						&& method.getMethod().getName() != "resetPasswordDriver_service") {
					if (app == client_app) {
						if (r.getClient_id() != 0) {
							ClientCO clientCO = new ClientCO();
							clientCO.setClient_id(r.getClient_id());
							clientCO.setDevice_UUID(r.getDevice_UUID());
							ClientDeviceCO clientDeviceCO = omniClientPullDAO
									.returnClientIdMobileNumberDevice(clientCO);
							if (clientDeviceCO == null) {
								ClientDeviceCO co = new ClientDeviceCO();
								co.setClient_id(r.getClient_id());
								co.setClient_status(oldDevice);
								omniBaseBO.getClientDeviceHashMap().put(r.getDevice_UUID(), co);
								return response;
							}
						}
					}
					DeviceSC deviceSC = new DeviceSC();
					deviceSC.setDevice_UUID(device_UUID);
					deviceSC.setApp(app);
					DeviceVO deviceVO = omniCommonPullDAO.returnSecretKeyAndToken(deviceSC);
					if (deviceVO != null) {
						int valid = SecurityUtilsExt.validateToken(deviceVO.getSecretKey(), deviceVO.getToken());
						if (valid == 1) {
							if (app == client_app) {
								deviceKeysSC.setStatus(INVALID);
								OmniBaseBO.getClientKeysHashMap().put(device_UUID, deviceKeysSC);
							}
							if (app == driver_app) {
								deviceKeysSC.setStatus(INVALID);
								OmniBaseBO.getDriverKeysHashMap().put(device_UUID, deviceKeysSC);
							}
						}
						if (valid == 3) {
							if (app == client_app) {
								deviceKeysSC = SecurityUtilsExt.refreshToken(device_UUID,
										omniBaseBO.getClientKeysHashMap(), EXPIRED);
							}

							if (app == driver_app) {
								deviceKeysSC = SecurityUtilsExt.refreshToken(device_UUID,
										omniBaseBO.getDriverKeysHashMap(), EXPIRED);
							}
							if (app == admin_app) {
								deviceKeysSC = SecurityUtilsExt.refreshToken(device_UUID,
										omniBaseBO.getAdminKeysHashMap(), EXPIRED);
							}
							deviceKeysSC.setDevice_UUID(device_UUID);
							omniCommonPushDAO.updateToken(deviceKeysSC);
						}
					}

				}
				PropertiesVO propertyVO = new PropertiesVO();
				propertyVO.setProperty_name(numberOfInvalidOTP);
				PropertiesVO propertyVO2 = omniCommonPullDAO.returnProperty(propertyVO);
//				String nString = omniBaseBO.getPropertiesHashMap().get(numberOfInvalidOTP).getProperty_value();
				int n = Integer.parseInt(propertyVO2.getProperty_value());
				java.sql.Date today = new java.sql.Date(System.currentTimeMillis());

				DeviceSC deviceSC = new DeviceSC();
				deviceSC.setApp(app);
				deviceSC.setDate(today);
				UserOTP userOTP = new UserOTP();
				deviceSC.setDevice_UUID(device_UUID);
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date d = new java.util.Date();
				String t = format.format(d);
				userOTP.setDate(t);
				log.info(" debut check block " + format1.format(new java.util.Date()));

				DeviceSC sc = omniCommonPullDAO.returnInvalidOTP(deviceSC);
				if (sc != null) {
					if (sc.getCounterOTP() >= n) {

						userOTP.setStatus(deviceBlocked);
						if (app == 0)
							OmniBaseBO.getBlockClientHashmap().put(deviceSC.getDevice_UUID(), userOTP);
						if (app == 1)
							omniBaseBO.getBlockDriverHashmap().put(deviceSC.getDevice_UUID(), userOTP);
						return response;
					}
				}
				log.info(" fin check block " + format1.format(new java.util.Date()));

				response = method.proceed();

//
			}
		} catch (Exception e) {
			log.info(e.getMessage());

			pathlog.info(e.getMessage());
		}
		return response;

	}
}