package com.path.bo.omni.driver.impl;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.path.bo.omni.common.OmniCommonBO;
import com.path.bo.omni.common.base.OmniBaseBO;
import com.path.bo.omni.common.impl.OmniCommonBOImpl;
import com.path.bo.omni.driver.OmniDriverBO;
import com.path.dao.omni.common.pull.OmniCommonPullDAO;
import com.path.dao.omni.common.push.OmniCommonPushDAO;
import com.path.dao.omni.driver.OmniDriverDAO;
import com.path.dao.omni.driver.pull.OmniDriverPullDAO;
import com.path.dao.omni.driver.push.OmniDriverPushDAO;
import com.path.dbmaps.vo.AdminVO;
import com.path.dbmaps.vo.ClientCO;
import com.path.dbmaps.vo.CompaniesSC;
import com.path.dbmaps.vo.DeviceAdminVO;
import com.path.dbmaps.vo.DeviceDriverVO;
import com.path.dbmaps.vo.DeviceKeysSC;
import com.path.dbmaps.vo.DeviceSC;
import com.path.dbmaps.vo.DeviceVO;
import com.path.dbmaps.vo.DriverCO;
import com.path.dbmaps.vo.DriverVO;
import com.path.dbmaps.vo.Driver_deviceCO;
import com.path.dbmaps.vo.Driver_deviceVO;
import com.path.dbmaps.vo.FactureVO;
import com.path.dbmaps.vo.MachineDriverLocationVO;
import com.path.dbmaps.vo.MachineDriverVO;
import com.path.dbmaps.vo.Machine_driverCO;
import com.path.dbmaps.vo.Machine_mntCO;
import com.path.dbmaps.vo.PropertiesVO;
import com.path.dbmaps.vo.ReservationCO;
import com.path.dbmaps.vo.ReservationDetailsCO;
import com.path.dbmaps.vo.ReservationListVO;
import com.path.dbmaps.vo.ReservationSC;
import com.path.dbmaps.vo.Sended_notificationVO;
import com.path.dbmaps.vo.User;
import com.path.lib.common.util.SecurityUtilsExt;
import com.path.ws.omni.vo.common.AddMaintenanceResponse;
import com.path.ws.omni.vo.common.AuthenticateDriverResponse;
import com.path.ws.omni.vo.common.ChangePasswordDriverResponse;
import com.path.ws.omni.vo.common.ChangeProfileDriverResponse;
import com.path.ws.omni.vo.common.CheckRecurringReservationResponse;
import com.path.ws.omni.vo.common.CheckingDriverLocationResponse;
import com.path.ws.omni.vo.common.CheckingFutureDriverResponse;
import com.path.ws.omni.vo.common.CostResponse;
import com.path.ws.omni.vo.common.ForgetPasswordResponse;
import com.path.ws.omni.vo.common.LoginDriverResponse;
import com.path.ws.omni.vo.common.LogoutDriverResponse;
import com.path.ws.omni.vo.common.Notification;
import com.path.ws.omni.vo.common.ResetPasswordResponse;
import com.path.ws.omni.vo.common.ReturnDriverInfoResponse;
import com.path.ws.omni.vo.common.ReturnDriverLocationResponse;
import com.path.ws.omni.vo.common.ReturnDriverStatusResponse;
import com.path.ws.omni.vo.common.ReturnListReservationsDriverResponse;
import com.path.ws.omni.vo.common.ReturnNearestAvailableMachineResponse;
import com.path.ws.omni.vo.common.ReturnRecurringClientsResponse;
import com.path.ws.omni.vo.common.SwitchingDriverAvailabilityResponse;
import com.path.ws.omni.vo.common.UpdateReservationInfosResponse;
import com.path.ws.omni.vo.common.UpdateDriverLocationResponse;
import com.path.ws.omni.vo.common.UpdateDriverLocationsOfflineRequest;
import com.path.ws.omni.vo.common.UpdateDriverLocationsOfflineResponse;
import com.path.ws.omni.vo.common.UpdateReservationStatusResponse;
import com.path.ws.omni.vo.common.UpdateTimeSlotResponse;

//@Monitor
//@Configuration
//@EnableAsync
@EnableScheduling
public class OmniDriverBOImpl extends OmniBaseBO implements OmniDriverBO {

	protected OmniDriverDAO dao;
	protected OmniCommonBO omniCommonBO;
	protected OmniCommonBOImpl omniCommonBOImpl;
	protected OmniDriverPullDAO omniDriverPullDAO;
	protected OmniDriverPushDAO omniDriverPushDAO;
	protected OmniCommonPushDAO omniCommonPushDAO;
	protected OmniCommonPullDAO omniCommonPullDAO;

	public OmniCommonBO getOmniCommonBO() {
		return omniCommonBO;
	}

	public void setOmniCommonBO(OmniCommonBO omniCommonBO) {
		this.omniCommonBO = omniCommonBO;
	}

	public OmniCommonBOImpl getOmniCommonBOImpl() {
		return omniCommonBOImpl;
	}

	public void setOmniCommonBOImpl(OmniCommonBOImpl omniCommonBOImpl) {
		this.omniCommonBOImpl = omniCommonBOImpl;
	}

	public OmniDriverPullDAO getOmniDriverPullDAO() {
		return omniDriverPullDAO;
	}

	public void setOmniDriverPullDAO(OmniDriverPullDAO omniDriverPullDAO) {
		this.omniDriverPullDAO = omniDriverPullDAO;
	}

	public OmniDriverPushDAO getOmniDriverPushDAO() {
		return omniDriverPushDAO;
	}

	public void setOmniDriverPushDAO(OmniDriverPushDAO omniDriverPushDAO) {
		this.omniDriverPushDAO = omniDriverPushDAO;
	}

	public void setOmniCommonPushDAO(OmniCommonPushDAO omniCommonPushDAO) {
		this.omniCommonPushDAO = omniCommonPushDAO;
	}

	public void setOmniCommonPullDAO(OmniCommonPullDAO omniCommonPullDAO) {
		this.omniCommonPullDAO = omniCommonPullDAO;
	}

	SecurityUtilsExt securityUtilsExt;
	Properties prop = new Properties();

	@Override
	public AuthenticateDriverResponse authenticateDriver(Driver_deviceCO driver_deviceCO) throws Exception {
		AuthenticateDriverResponse response = new AuthenticateDriverResponse();
		try {
			Driver_deviceCO driver_deviceCO2 = omniDriverPullDAO.returnCombinationDriverDevice(driver_deviceCO);
			List<PropertiesVO> list = omniCommonPullDAO.returnListProperties(null);

			/*
			 * the combination exist in db and the hashmap contains the key,
			 */
			if (driver_deviceCO2 != null
//					&& backEndKeyHashMap.containsKey(frontEndPublicKey)
					&& driver_deviceCO2.getDriver_status() == statusLogin) {
				ClientCO clientCO = new ClientCO(driver_deviceCO2.getDevice_UUID(), driver_deviceCO2.getUsername(),
						driver_deviceCO.getFrontEndPublicKey());
//				User user = new User(clientCO.getUsername(), driver_deviceCO.getDevice_UUID(),
//						driver_deviceCO.getFrontEndPublicKey());
				DeviceKeysSC deviceKeysSC = omniCommonBO.getTokenAndKeysClient(driver_deviceCO.getDevice_UUID());
				deviceKeysSC.setDevice_UUID(driver_deviceCO.getDevice_UUID());
				omniCommonPushDAO.updateToken(deviceKeysSC);
//				driverKeysHashMap.put(driver_deviceCO.getDevice_UUID(), deviceKeysSC);
				DriverVO driverVO = new DriverVO();
				driverVO.setDriver_id(driver_deviceCO.getDriver_id());
				DriverVO driver = omniDriverPullDAO.returnDriverStatus(driverVO);
				response.setAvailability(driver.getOn_off());
				response.setToken(deviceKeysSC.getToken());
				response.setResponse("success");

			} else if (driver_deviceCO2 != null && driver_deviceCO2.getDriver_status() == statusLogout) {
				response.setErrorCode(errorCodeLogin);
				response.setErrorDesc(omniCommonBO.getError(driver_deviceCO.getLanguage_id(), response.getErrorCode())
						.get(0).getError_desc());

			}

			else {
				response.setErrorCode(errorCodeAuthenticate);
				response.setErrorDesc(omniCommonBO.getError(driver_deviceCO.getLanguage_id(), response.getErrorCode())
						.get(0).getError_desc());
			}
//			PropertyVO propertyVO = new PropertyVO();
//			propertyVO.setProperty_name(enableCrashlytics);
//			PropertyVO propertyVO2 = omniCommonPullDAO.returnProperty(propertyVO);
//			response.setEnableCrashlytics(propertyVO2.getProperty_value());

			String propertiesToFrontEnd = list.stream().filter(l -> l.getProperty_name().equals(propToFrontEndDriver))
					.collect(Collectors.toList()).get(0).getProperty_value().replaceAll("\\s", "");
			List<String> myList = new ArrayList<String>(Arrays.asList(propertiesToFrontEnd.split(";")));
			List<PropertiesVO> listProperties = new ArrayList<PropertiesVO>();
			for (String listprop : myList) {
				PropertiesVO e = new PropertiesVO(listprop,
						list.stream().filter(l -> l.getProperty_name().equals(listprop)).collect(Collectors.toList())
								.get(0).getProperty_value());
				listProperties.add(e);
			}
			response.setListProperties(listProperties);
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public LoginDriverResponse loginDriver(DriverCO driverCO) throws Exception {
		LoginDriverResponse response = new LoginDriverResponse();
		try {
			if (driverCO.getLongitude() == 0 || driverCO.getLatitude() == 0) {
				response.setErrorCode(errorUpdatingLocation);
				response.setErrorDesc(omniCommonBO.getError(driverCO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());
			}
			/*
			 * check if the username is correct or not
			 */
			DriverVO driverVO = omniDriverPullDAO.returnDriverUsername(driverCO);
			if (driverVO == null) { // incorrect username
				response.setErrorCode(errorCodeLoginDriverIncorrectUsername);
				response.setErrorDesc(omniCommonBO.getError(driverCO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());
			} else {
				/*
				 * validate the password then check if this the first time the driver login , if
				 * yes insert the combination device driver in db check if the device exist
				 */
				if (SecurityUtilsExt.validatePassword(driverCO.getPassword(), driverVO.getSalt(),
						driverVO.getPassword())) { // correct password
					driverCO.setDriver_id(driverVO.getDriver_id());
					User user = new User();
					user.setDevice_name(driverCO.getDevice_name());
					user.setDevice_UUID(driverCO.getDevice_UUID());
					user.setLanguage_id(driverCO.getLanguage_id());
					;
					DeviceVO deviceVO = omniCommonPullDAO.returnDevice(user);
//					driverCO.setDriver_status(statusLogin);
					driverCO.setLast_login_driver(new java.util.Date(System.currentTimeMillis()));
					if (deviceVO == null) {
//						omniCommonPushDAO.insertDeviceDriver(driverCO);
						ClientCO clientCO = new ClientCO();
						clientCO.setDevice_UUID(driverCO.getDevice_UUID());
						clientCO.setDevice_name(driverCO.getDevice_name());

						omniCommonPushDAO.insertDevice(user);
					}
					/*
					 * check if the device exist in driver_device table if no insert the combination
					 * , if yes update the combination
					 * 
					 */
					Driver_deviceVO driver_deviceVO = omniDriverPullDAO.returnDriverUsernameComb(driverCO);
					if (driver_deviceVO == null) {
						omniDriverPushDAO.insertDriverDeviceCombination(driverCO);
					} else {
						/*
						 * check if the combination exist
						 */
						Driver_deviceVO driver_deviceVO2 = omniDriverPullDAO.returnCombDriverDevice(driverCO);
						if (driver_deviceVO2 == null) { // if no , update the driverdevice combination
							driverCO.setDriver_device_id(driver_deviceVO.getDriver_device_id());
							omniDriverPushDAO.updateDriverDeviceCombination(driverCO);
						}

					}
					/*
					 * check if the combination exist if no , insert into driver_device
					 */
					/*
					 * update the status of driver put it "login" and the availability to 1
					 * available
					 */
					driverCO.setOn_off(on);
					omniDriverPushDAO.updateLastLoginDriver(driverCO);
					driverCO.setDriver_id(0);
					omniDriverPushDAO.updateDriverDeviceStatus(driverCO);
					/*
					 * generate the token then add to the response
					 */
					log.info("get token and keys");
//					User user = new User(driverCO.getUsername(), driverCO.getDevice_UUID(),
//							driverCO.getFrontEndPublicKey());
					DeviceKeysSC deviceKeysSC = omniCommonBO.getTokenAndKeysClient(driverCO.getDevice_UUID());
					deviceKeysSC.setDevice_UUID(driverCO.getDevice_UUID());
					deviceKeysSC.setApp(driverCO.getApp());
					omniCommonPushDAO.updateToken(deviceKeysSC);
					driverCO.setDriver_id(driverVO.getDriver_id());
//					omniDriverPushDAO.updateMachineLocation(driverCO);

					driverCO.setReservation_list_id(-1);
					omniDriverPushDAO.updateMachineDriverLocation(driverCO);
					omniDriverPushDAO.insertNewLocationDriver(driverCO);
//					driverKeysHashMap.put(driverCO.getDevice_UUID(), deviceKeysSC);
					DeviceSC deviceSC = new DeviceSC();
					deviceSC.setDevice_UUID(driverCO.getDevice_UUID());
					deviceSC.setLanguage_id(driverCO.getLanguage_id());
					deviceSC.setApp(driverCO.getApp());
					omniCommonPushDAO.updateDeviceLanguage(deviceSC);
					log.info("getlclientInfo");
					DriverVO driverInfo = omniDriverPullDAO.returnDriverInfo(driverVO);
					response.setUsername(driverInfo.getUsername());
					response.setProfile(driverInfo.getProfile());
					response.setLast_login_driver(format.format(driverInfo.getLast_login_driver()));
					response.setToken(deviceKeysSC.getToken());
					response.setResponse("success");
					response.setDriver_id(driverVO.getDriver_id());
					response.setAvailability(0);
				} else { // incorrect password
					response.setErrorCode(errorCodeIncorrectPass);
					response.setErrorDesc(omniCommonBO.getError(driverCO.getLanguage_id(), response.getErrorCode())
							.get(0).getError_desc());
				}
			}

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public LogoutDriverResponse logoutDriver(DriverCO driverCO) throws Exception {
		LogoutDriverResponse response = new LogoutDriverResponse();
		try {
//			PublicKey k = clientHashMap.get(driverCO.getDevice_UUID()).getFrontEndPublicKey();
//			backEndKeyHashMap.remove(k);
//			clientKeysHashMap.remove(driverCO.getDevice_UUID());
			driverCO.setDriver_status(statusLogout);
			omniDriverPushDAO.updateDriverDeviceStatus(driverCO);
			response.setResponse("logout successfuly");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public UpdateTimeSlotResponse updateTimeSlot(ReservationCO reservationCO) throws Exception {
		UpdateTimeSlotResponse response = new UpdateTimeSlotResponse();
		try {
			omniDriverPushDAO.updateTimeSlot(reservationCO);
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ReturnListReservationsDriverResponse returnlistReservationsDriver(DriverCO driverCO) throws Exception {
		ReturnListReservationsDriverResponse response = new ReturnListReservationsDriverResponse();
		try {
			PropertiesVO propertyVO = new PropertiesVO();
			propertyVO.setProperty_name(nb_of_free_reservation);
			PropertiesVO propertyVO2 = omniCommonPullDAO.returnProperty(propertyVO);
			int nbOfFreeReservation = Integer.parseInt(propertyVO2.getProperty_value());

			if (driverCO.getFilterDate() == null) {
				driverCO.setDate(new Date(System.currentTimeMillis()));
			} else {
				Date date = Date.valueOf(driverCO.getFilterDate());
				driverCO.setDate(date);
			}
			List<ReservationSC> listOfReservationsNotFinished = omniDriverPullDAO
					.returnListReservationsNotFinishedDriver(driverCO);
			List<ReservationSC> listOfFinishedReservations = omniDriverPullDAO
					.returnlistOfFinishedReservationsDriver(driverCO);

			List<ReservationSC> listOfFinishedReservationWithAddress = omniDriverPullDAO
					.returnListOfFinishedReservationsWithAddress(driverCO);

			List<ReservationSC> listOFNotFinishedReservationsWithAddress = omniDriverPullDAO
					.returnListOfNotFinishedReservationsWithAddress(driverCO);

			List<ReservationSC> listOfReservations = new ArrayList<ReservationSC>();
			listOfReservations.addAll(listOfReservationsNotFinished);
			listOfReservations.addAll(listOfFinishedReservations);
			listOfReservations.addAll(listOfFinishedReservationWithAddress);
			listOfReservations.addAll(listOFNotFinishedReservationsWithAddress);

			if (listOfReservations.isEmpty()) {
				response.setResponse(noData);
			} else {
				response.setList(listOfReservations);
			}

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ChangePasswordDriverResponse changePasswordDriver(DriverCO driverCO) throws Exception {

		ChangePasswordDriverResponse response = new ChangePasswordDriverResponse();
		try {

			// get the username and password from db for this user by id
			DriverVO driverVO = omniDriverPullDAO.returnUsernamePassDriverById(driverCO);
			// check if the old password in request is the same password in db
			if (SecurityUtilsExt.validatePassword(driverCO.getOldpassword(), driverVO.getSalt(),
					driverVO.getPassword())) {
				// hash the new password to save in db
				String salt = SecurityUtilsExt.getNextSalt();
				String hash = SecurityUtilsExt.hash(driverCO.getPassword(), salt);
				driverCO.setPassword(hash);
				driverCO.setSalt(salt);
				omniDriverPushDAO.updatePasswordDriver(driverCO);
				response.setResponse("password changed");
			} else {
				// incorrect password
				response.setErrorCode(errorCodeIncorrectPass);
				response.setErrorDesc(omniCommonBO.getError(driverCO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());
			}

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@SuppressWarnings("null")
	@Override
	public ForgetPasswordResponse forgetPasswordDriver(DriverCO driverCO) throws Exception {
		ForgetPasswordResponse response = new ForgetPasswordResponse();
		char[] mask;

		try {

			DriverVO driverVO2 = omniDriverPullDAO.returnMobileNumberDriver(driverCO);
			DeviceSC deviceSC = new DeviceSC();
			deviceSC.setDevice_UUID(driverCO.getDevice_UUID());
			deviceSC.setMobile_number(driverVO2.getMobile_number());
			deviceSC.setLanguage_id(driverCO.getLanguage_id());
			deviceSC.setApp(driverCO.getApp());
			if (driverVO2 == null) {
				response.setErrorCode(errorCodeForgetPassword);
				response.setErrorDesc(omniCommonBO.getError(driverCO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());

			} else {
				response = omniCommonBO.returnMaskMobileNumber(driverVO2.getMobile_number(), deviceSC);
			}

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ResetPasswordResponse resetPasswordDriver(DriverVO driverVO) throws Exception {
		ResetPasswordResponse response = new ResetPasswordResponse();
		try {
			String salt = SecurityUtilsExt.getNextSalt();
			driverVO.setSalt(salt);
			driverVO.setPassword(SecurityUtilsExt.hash(driverVO.getPassword(), salt));
			omniDriverPushDAO.resetPasswordDriver(driverVO);
			response.setResponse("done");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public UpdateReservationStatusResponse updateReservationStatus(DriverCO driverCO) throws Exception {
		UpdateReservationStatusResponse response = new UpdateReservationStatusResponse();
		try {
			if (driverCO.getLongitude() == 0 || driverCO.getLatitude() == 0) {
				response.setErrorCode(errorUpdatingLocation);
				response.setErrorDesc(omniCommonBO.getError(driverCO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());
				return response;
			}
			ReservationListVO reservationListVO = new ReservationListVO();
			reservationListVO.setReservation_list_id(driverCO.getReservation_list_id());
			ReservationCO reservationCO2 = omniCommonPullDAO.returnReservationDetails(reservationListVO);

			if (reservationCO2.getReservation_list_status() != CANCELED
					&& reservationCO2.getReservation_list_status() != EXPIRED) {
				ReservationCO reservationCO = new ReservationCO();
				reservationCO.setReservation_list_id(driverCO.getReservation_list_id());
				reservationCO.setReservation_id(reservationCO2.getReservation_id());
				reservationCO.setReservation_list_status(driverCO.getReservation_list_status());
				reservationCO.setOccurrence_type(reservationCO2.getOccurrence_type());
				reservationCO.setClient_id(reservationCO2.getClient_id());
				Notification notification = omniCommonBO.loadKeyAndUrl();
				String msg = null;
				String title = null;
				String penaltyMsg = null;
				List<PropertiesVO> listProperties = omniCommonPullDAO.returnListProperties(null);

				/*
				 * select from the db the device token of the client that create the reservation
				 */
				List<DeviceVO> listClietnDevices = omniCommonPullDAO.returnDeviceOfClient(reservationCO);

				/*
				 * \ if the driver take the resrevation , send notification to the client
				 */

				if (driverCO.getReservation_list_status() == TAKE_TRIP) {
//						msg = TAKE_TRIP_MSG;
//					reservationCO.setDistanceTakeWait(driverCO.getDistanceTakeWait());
//					omniDriverPushDAO.updateTakeWait(reservationCO);
					if (driverCO.getKilometrageTake() != 0) {
						reservationCO.setKilometrageTake(driverCO.getKilometrageTake());
						omniDriverPushDAO.updateKilometrage(reservationCO);
					}
					for (DeviceVO d : listClietnDevices) {
						msg = omniCommonBO.getNotification(d.getLanguage_id(), TAKE_TRIP_CODE).get(0)
								.getNotification_desc();
						title = omniCommonBO.getNotification(d.getLanguage_id(), RESERVATION_STATUS_TITLE_CODE).get(0)
								.getNotification_desc();
						try {
							SecurityUtilsExt.pushNotification(d.getDevice_token(), title, msg, notification, null,
									null);
						} catch (Exception e) {
							log.info(e.getMessage());
						}

					}

					response.setReservation_list_id(driverCO.getReservation_list_id());

				}
				if (driverCO.getReservation_list_status() == WAIT_USER) {
//						msg = WAIT_CLIENT_MSG;
					if (driverCO.getDistanceTakeWait() != 0) {
						reservationCO.setDistanceTakeWait(driverCO.getDistanceTakeWait());
						omniDriverPushDAO.updateTakeWait(reservationCO);
					}
					for (DeviceVO d : listClietnDevices) {
						msg = omniCommonBO.getNotification(d.getLanguage_id(), WAIT_CLIENT_CODE).get(0)
								.getNotification_desc();
						title = omniCommonBO.getNotification(d.getLanguage_id(), RESERVATION_STATUS_TITLE_CODE).get(0)
								.getNotification_desc();
						try {
							SecurityUtilsExt.pushNotification(d.getDevice_token(), title, msg, notification, null,
									null);
						} catch (Exception e) {
							log.info(e.getMessage());
						}
					}
//					omniDriverPushDAO.updateTakeWait(reservationCO);
				}
				if (driverCO.getReservation_list_status() == MISSED_TRIP) {

					if (reservationCO.getOccurrence_type() == occurrenceTypeRecurring) {
						List<ReservationCO> listDismissedReservation = omniCommonBO
								.checkDismissedAndCanceledReservation(reservationCO);
						String limitDismissedString = listProperties.stream()
								.filter(l -> l.getProperty_name().equals(limit_dismissed)).collect(Collectors.toList())
								.get(0).getProperty_value();

						int limitDismissed = Integer.parseInt(limitDismissedString);
						if (listDismissedReservation.size() >= limitDismissed) {
							omniCommonBO.cancelRecurringReservation(reservationCO);
							for (DeviceVO d : listClietnDevices) {
								msg = msg + omniCommonBO.getNotification(d.getLanguage_id(), RESERVATION_CANCELLED_CODE)
										.get(0).getNotification_desc() + driverCO.getCost();
								title = omniCommonBO
										.getNotification(d.getLanguage_id(), RESERVATION_CANCELLED_TITLE_CODE).get(0)
										.getNotification_desc();
								try {
									SecurityUtilsExt.pushNotification(d.getDevice_token(), title, msg, notification,
											null, null);
								} catch (Exception e) {
									log.info(e.getMessage());
								}

							}
						}
					}
					if (driverCO.getLongitude() == 0 && driverCO.getLatitude() == 0) {
						response.setErrorCode(errorUpdatingLocation);
						response.setErrorDesc(omniCommonBO.getError(driverCO.getLanguage_id(), response.getErrorCode())
								.get(0).getError_desc());
						return response;
					} else {
						omniDriverPushDAO.updateMachineDriverLocation(driverCO);
						Machine_driverCO mdCO = new Machine_driverCO();
						mdCO.setMachine_driver_id(reservationCO.getMachine_driver_id());
					}
					for (DeviceVO d : listClietnDevices) {
						msg = msg + omniCommonBO.getNotification(d.getLanguage_id(), MISSED_TRIP_CODE).get(0)
								.getNotification_desc() + driverCO.getCost();
						title = omniCommonBO.getNotification(d.getLanguage_id(), RESERVATION_STATUS_TITLE_CODE).get(0)
								.getNotification_desc();
						try {
							SecurityUtilsExt.pushNotification(d.getDevice_token(), title, msg, notification, null,
									null);

						} catch (Exception e) {
							log.info(e.getMessage());
						}
					}

				}
				if (driverCO.getReservation_list_status() == START_TRIP) {

					/*
					 * in the start of the trip convert the time from min string to sql time and
					 * update the timeSlot of the reservation in the db
					 */

//				String timeSlot = driverCO.getTime_slot_string().substring(0, 1);
//					String timeSlot = driverCO.getTime_slot_string();
//					SimpleDateFormat sdf = new SimpleDateFormat("mm");
//
//					java.util.Date dt = sdf.parse(timeSlot);
//					Time time = new Time(dt.getTime());
//					reservationCO.setTime_slot(time);
					DateTimeFormatter df = DateTimeFormatter.ofPattern("kk:mm");

					String maxTimeToReachDestinationString = listProperties.stream()
							.filter(l -> l.getProperty_name().equals(maxTimeToReachDestination))
							.collect(Collectors.toList()).get(0).getProperty_value();
					int maxTimeToReachDestination = Integer.parseInt(maxTimeToReachDestinationString);

					DateTimeFormatter dft = DateTimeFormatter.ofPattern("kk:mm");

					LocalTime localtime = LocalTime.parse(reservationCO2.getTime().toString());
					String tss = dft.format(localtime.plusMinutes(maxTimeToReachDestination)) + ":00";
					Time ts = Time.valueOf(tss);
//					String tss = df.format(localtime.plusMinutes(Integer.parseInt(driverCO.getTime_slot_string())))
//							+ ":00";
//					Time ts = Time.valueOf(tss);
					reservationCO.setTime_slot(ts);
					reservationCO.setTime(driverCO.getTime());
					omniDriverPushDAO.updateTimeSlot(reservationCO);
					if (driverCO.getKilometrageStart() != 0) {
						reservationCO.setKilometrageStart(driverCO.getKilometrageStart());
						omniDriverPushDAO.updateKilometrage(reservationCO);
					}

				}
				/*
				 * if the trip is done , consume the location of the machine then update in th
				 * db
				 */
				if (driverCO.getReservation_list_status() == FINISH_TRIP) {
					omniDriverPushDAO.updateNbOfClient(driverCO);
					/*
					 * return the discount of the client
					 */
					int costAfterDiscount;
					CompaniesSC companiesSC = omniDriverPullDAO.returnDiscountOfClient(driverCO);

//					reservationCO.setDistanceStartFinish(driverCO.getDistanceStartFinish());
					reservationCO.setClientMobileNumber(driverCO.getClientMobileNumber());
//					reservationCO.setDistanceStartFinishTesting(driverCO.getDistanceStartFinishTesting());
					reservationCO.setLocationsArray(driverCO.getLocationsArray());
//					reservationCO.setDistanceStartFinishTestingDefault(driverCO.getDistanceStartFinishTestingDefault());
					omniDriverPushDAO.updateDistanceStartFinish(reservationCO);
					if (driverCO.getKilometrageFinish() != 0) {
						reservationCO.setKilometrageFinish(driverCO.getKilometrageFinish());
						omniDriverPushDAO.updateKilometrage(reservationCO);
					} else if (driverCO.getDistanceStartFinish() != 0) {
						reservationCO.setDistanceStartFinish(driverCO.getDistanceStartFinish());
						omniDriverPushDAO.updateDistanceStartFinish(reservationCO);
					}

					if (driverCO.getLongitude() == 0 && driverCO.getLatitude() == 0) {
						response.setErrorCode(errorUpdatingLocation);
						response.setErrorDesc(omniCommonBO.getError(driverCO.getLanguage_id(), response.getErrorCode())
								.get(0).getError_desc());
						return response;
					} else {
						/*
						 * calculate the cost and push notification to driver and client
						 */
						reservationCO2.setLatitude_to(driverCO.getLatitude());
						reservationCO2.setLongitude_to(driverCO.getLongitude());
						omniDriverPushDAO.updateLocationToClient(reservationCO2);
						reservationCO.setReservation_list_id(driverCO.getReservation_list_id());
						reservationCO.setFree_status(reservationCO2.getFree_status());
						reservationCO.setDistanceTakeWait(reservationCO2.getDistanceTakeWait());
						reservationCO.setDistanceStartFinish(driverCO.getDistanceStartFinish());
						CostResponse costResponse = omniCommonBO.calculateCost(driverCO, reservationCO, listProperties);

						response.setResponseCode(costResponse.getResponseCode());
						msg = "Your trip will cost " + costResponse.getTotalCost();
						String currency;
						double totalCost = costResponse.getTotalCost();
						double costTesting = costResponse.getCostTesting();
						String costFormule = costResponse.getCostFormule();
						String costTestingFormule = costResponse.getCostTestingFormule();
						int totalCostByDriver = driverCO.getCost();
						if (companiesSC != null) {
							if (companiesSC.getDiscount() != 0) {
								float percentDiscount = (companiesSC.getDiscount() / 100.0f);
//							totalCost = totalCost -(totalCost * companiesSC.getDiscount());
								totalCostByDriver = (driverCO.getCost() + costResponse.getPenalty())
										- ((int) ((driverCO.getCost() + costResponse.getPenalty()) * percentDiscount));
							}
						}
						for (DeviceVO d : listClietnDevices) {
							if (d.getLanguage_id() == english || driverCO.getLanguage_id() == french) {
								currency = "L.L";
							} else
								currency = "ل.ل";
//							msg = omniCommonBO.getNotification(d.getLanguage_id(), COST_CODE).get(0)
//									.getNotification_desc() + " " + totalCostByDriver + " " + currency;
							msg = omniCommonBO.getNotification(d.getLanguage_id(), COST_CODE).get(0)
									.getNotification_desc() + " " + totalCost + " " + currency;
							if (costResponse.getPenalty() != 0) {
								penaltyMsg = omniCommonBO.getNotification(d.getLanguage_id(), PENALTY_CODE).get(0)
										.getNotification_desc() + costResponse.getPenalty() + " " + currency;
								msg = msg + penaltyMsg;
							}
							title = omniCommonBO.getNotification(d.getLanguage_id(), FACTURE_TITLE_CODE).get(0)
									.getNotification_desc();
							try {
								SecurityUtilsExt.pushNotification(d.getDevice_token(), title, msg, notification, null,
										null);
							} catch (Exception e) {
								log.info(e.getMessage());
							}

						}
//						int totalCost = (int) (costResponse.getTotalCost() + costResponse.getPenalty());

						FactureVO facture = new FactureVO(driverCO.getReservation_list_id(), totalCost,
								driverCO.getNbOfClient(), driverCO.getCost(), totalCostByDriver, costTesting,
								costFormule, costTestingFormule);
//						FactureVO facture = new FactureVO(driverCO.getReservation_list_id(), totalCost,
//								driverCO.getNumberOfClient(), driverCO.getCost(), totalCostByDriver, costFormule);

						facture.setPaid(factureNotPaid);
						omniDriverPushDAO.insertFacture(facture);

						DeviceDriverVO deviceDriverVO = omniDriverPullDAO.returnDriverToken(driverCO);
						if (driverCO.getLanguage_id() == english || driverCO.getLanguage_id() == french) {
							currency = "L.L";
						} else
							currency = "ل.ل";
//						msg = omniCommonBO.getNotification(driverCO.getLanguage_id(), COST_CODE).get(0)
//								.getNotification_desc() + "systemCost" + " " + totalCost + " " + currency + " \r\n "
//								+ " TotalCostByDriver " + driverCO.getCost() + " " + currency;
						msg = omniCommonBO.getNotification(driverCO.getLanguage_id(), COST_CODE).get(0)
								.getNotification_desc() + totalCost + " " + currency;
						if (costResponse.getPenalty() != 0) {
							penaltyMsg = omniCommonBO.getNotification(driverCO.getLanguage_id(), PENALTY_CODE).get(0)
									.getNotification_desc() + costResponse.getPenalty() + " " + currency;
							msg = msg + penaltyMsg;
						}
						title = omniCommonBO.getNotification(driverCO.getLanguage_id(), FACTURE_TITLE_CODE).get(0)
								.getNotification_desc();

						try {
							SecurityUtilsExt.pushNotification(deviceDriverVO.getDevice_token(), title, msg,
									notification, null, null);
						} catch (Exception e) {
							log.info(e.getMessage());
						}

						response.setCost((int) totalCost);
					}
				}

				omniCommonPushDAO.updateReservationListStatus(reservationCO);
				response.setReservation_list_status(driverCO.getReservation_list_status());
				response.setResponse("status updated");
//					response.setNotification(msg);
			} else {
				response.setErrorCode(errorUpdateReservationListStatus);
				response.setErrorDesc(omniCommonBO.getError(driverCO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ReturnDriverInfoResponse returnDriverInfo(DriverVO driverVO) throws Exception {
		ReturnDriverInfoResponse response = new ReturnDriverInfoResponse();
		try {
			DriverVO driverInfos = omniDriverPullDAO.returnDriverInfo(driverVO);
			if (driverInfos != null) {
				response.setUsername(driverInfos.getUsername());
				response.setProfile(driverInfos.getProfile());
				response.setLast_login_driver(format.format(driverInfos.getLast_login_driver()));

			} else {
				response.setErrorCode(errorCodeReturnDriverInfo);
				response.setErrorDesc(omniCommonBO.getError(driverVO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());

			}

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ChangeProfileDriverResponse changeProfileDriver(DriverVO driverVO) throws Exception {
		ChangeProfileDriverResponse response = new ChangeProfileDriverResponse();

		try {
			omniDriverPushDAO.updateProfileDriver(driverVO);
			response.setResponse("done");
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public UpdateDriverLocationResponse updateDriverLocation(DriverCO driverCO) throws Exception {
		UpdateDriverLocationResponse response = new UpdateDriverLocationResponse();
//		final double AVERAGE_RADIUS_OF_EARTH = 6371;
		List<Integer> listOfNotInsertedFrLocation_id = new ArrayList<Integer>();
		List<Integer> listOfInsertedFrLocation_id = new ArrayList<Integer>();
		try {

//			String longitudeString;
//			String latitudeString;
//			double latitude = 0;
//			double longitude = 0;
//			latitude = driverCO.getLocation().getCoords().getLatitude();
			double latitude = driverCO.getLatitude();
			double longitude = driverCO.getLongitude();
//			longitude = driverCO.getLocation().getCoords().getLongitude();
//			java.util.Date timestamp = driverCO.getLocation().getTimestamp();
//			java.sql.Date sqlDate = new Date(timestamp.getTime());
//			Time sqlTime = new Time(timestamp.getTime());
//			driverCO.setTime(sqlTime);
//			driverCO.setDate(sqlDate);

			if (latitude == 0 || longitude == 0) {
				response.setErrorCode(errorUpdatingLocation);
				response.setErrorDesc(omniCommonBO.getError(driverCO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());
				return response;
			} else {
				driverCO.setLatitude(latitude);
				driverCO.setLongitude(longitude);

				if (driverCO.getReservation_list_id() == 0) {
					driverCO.setReservation_location_status(0);
					driverCO.setReservation_list_id(-1);
				} else {
					driverCO.setReservation_location_status(1);
				}

//				if (driverCO.getOn_off() == on) {
				omniDriverPushDAO.insertNewLocationDriver(driverCO);

				omniDriverPushDAO.updateMachineDriverLocation(driverCO);
				response.setResponse("done");
//				} else {
//					response.setResponse("driver is off");
//				}
				if (driverCO.getFrLocation_id()!=0) {
					response.setInsertedFrLocation_id(driverCO.getFrLocation_id());
				}
			}
		} catch (Exception e) {
			response.setNotInsertedFrLocation_id(driverCO.getFrLocation_id());
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	public double deg2rad(double deg) {
		return deg * (Math.PI / 180);
	}

//	@Async
//	@Scheduled(fixedDelay = 300000)
////	@Scheduled
	public void checkingDriverLocation() throws Exception {
		CheckingDriverLocationResponse response = new CheckingDriverLocationResponse();
		try {

			int notification_id = 0;
			List<PropertiesVO> listProperties = omniCommonPullDAO.returnListProperties(null);
			String distanceForCheckingDriverLocationString = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(distance_for_checking_driver_location))
					.collect(Collectors.toList()).get(0).getProperty_value();
			double distanceForCheckingDriverLocation = Double.parseDouble(distanceForCheckingDriverLocationString);

			String distanceMovingDriverString = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(distance_moving_driver)).collect(Collectors.toList())
					.get(0).getProperty_value();
			double distanceMovingDriver = Double.parseDouble(distanceForCheckingDriverLocationString);

			String timeToCheckDriverLocationString = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(time_to_check_driver_location))
					.collect(Collectors.toList()).get(0).getProperty_value();

			long timeToTurnOffDriver = Long.parseLong(timeToCheckDriverLocationString);

//			double timeToCheckDriverLocation = Integer.parseInt(timeToCheckDriverLocationString);
			long timeToCheckDriverLocation = Long.parseLong(timeToCheckDriverLocationString);

			String timeToTurnOffDriverString = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(time_to_turn_off_driver)).collect(Collectors.toList())
					.get(0).getProperty_value();

			Notification notification = omniCommonBO.loadKeyAndUrl();
//			List<PropertiesVO> list = omniCommonPullDAO.returnListProperties(null);
			List<DeviceAdminVO> listadmin = omniCommonPullDAO.returnAdminToken(new AdminVO());

//			for (DeviceAdminVO l : listadmin) {
//				msg = "testing thread";
//				String title = "testing thread";
//				SecurityUtilsExt.pushNotification(l.getDevice_token(), title, msg, notification, null,
//						null);
//			}
			String gracePeriodToUpdateDriverLocationStr = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(grace_period_to_update_driver_location))
					.collect(Collectors.toList()).get(0).getProperty_value();
			int gracePeriod = Integer.parseInt(gracePeriodToUpdateDriverLocationStr);

			String midNightString = listProperties.stream().filter(l -> l.getProperty_name().equals(mid_night))
					.collect(Collectors.toList()).get(0).getProperty_value();

			String startOfWorkingDayString = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(start_of_working_day)).collect(Collectors.toList()).get(0)
					.getProperty_value();

			DriverCO driverCO = new DriverCO();
			driverCO.setDate(new Date(System.currentTimeMillis()));
			/*
			 * check if the date now is before midnight or after 6 am
			 */
			java.util.Date now = new java.util.Date();

			Time sqlTimeNow = new Time(System.currentTimeMillis());
//			String midNightString = "23:59:00";
//			String startOfWorkingDayString = "08:00:00";
			Date today = new Date(System.currentTimeMillis());
			/*
			 * convert midnight to java date
			 */
			LocalDateTime datetimeMidNight = omniCommonBO.ConvertTimeToJavaTime(Time.valueOf(midNightString), today);
			java.util.Date datetimeJavaMidNight = java.util.Date
					.from(datetimeMidNight.atZone(ZoneId.systemDefault()).toInstant());
			/*
			 * convert start working hour to java date
			 */
			LocalDateTime datetimestartOfWorkingDay = omniCommonBO
					.ConvertTimeToJavaTime(Time.valueOf(startOfWorkingDayString), today);
			java.util.Date datetimeJavastartOfWorkingDay = java.util.Date
					.from(datetimestartOfWorkingDay.atZone(ZoneId.systemDefault()).toInstant());

			if (now.before(datetimeJavaMidNight) && now.after(datetimeJavastartOfWorkingDay)) {
				/*
				 * return the last location of each driver
				 */
				List<MachineDriverLocationVO> listOfDriverLocations = omniDriverPullDAO
						.returnListOfDriverLocations(driverCO);

				if (!listOfDriverLocations.isEmpty()) {
					for (MachineDriverLocationVO driverLocation : listOfDriverLocations) {
						/*
						 * use function to convert sql time to java time
						 */
						Time newTime = new Time(driverLocation.getTime().getTime()
								- TimeUnit.MINUTES.toMillis(timeToCheckDriverLocation));
						DriverCO driverCO1 = new DriverCO();
						driverCO1.setDate(new Date(System.currentTimeMillis()));
						driverCO1.setTime(newTime);
						driverCO1.setDriver_id(driverLocation.getDriver_id());
						/*
						 * return the location of driver at time 5 min before
						 */
						MachineDriverLocationVO machineDriverLocationVO = omniDriverPullDAO
								.returnMachineDriverLocation(driverCO1);
						LocalDateTime datetime = omniCommonBO.ConvertTimeToJavaTime(driverLocation.getTime(),
								driverLocation.getDate());
						datetime = datetime.plusMinutes(gracePeriod);
						java.util.Date datetimeAsDate = java.util.Date
								.from(datetime.atZone(ZoneId.systemDefault()).toInstant());
						/*
						 * if the last location of driver is before 5 min that means the driver close
						 * the app
						 */
						LocalDateTime datetimeToTurnOfDriver = datetime.plusMinutes(timeToTurnOffDriver);
						java.util.Date datetimeToTurnOfDriverAsDate = java.util.Date
								.from(datetime.atZone(ZoneId.systemDefault()).toInstant());

						if (datetimeToTurnOfDriverAsDate.before(now)) {
							driverCO1.setOn_off(off);
							omniDriverPushDAO.updateDriverStatus(driverCO1);
							manageDriverStatus(driverCO1);
							for (DeviceAdminVO l : listadmin) {
								msg = omniCommonBO.getNotification(l.getLanguage_id(), DRIVER_AVAILIBILITY_CODE).get(0)
										.getNotification_desc() + " " + driverLocation.getName();
								String title = omniCommonBO
										.getNotification(l.getLanguage_id(), DRIVER_AVAILIBILITY_TITLE_CODE).get(0)
										.getNotification_desc();
								SecurityUtilsExt.pushNotification(l.getDevice_token(), title, msg, notification, null,
										null);

							}
							return;
						}

						if (datetimeAsDate.before(now)) {

							for (DeviceAdminVO l : listadmin) {
								msg = omniCommonBO.getNotification(l.getLanguage_id(), DRIVER_LOCATION_GPS_CODE).get(0)
										.getNotification_desc() + " " + driverLocation.getName();
								String title = omniCommonBO
										.getNotification(l.getLanguage_id(), DRIVER_LOCATION_GPS_TITLE_CODE).get(0)
										.getNotification_desc();
								SecurityUtilsExt.pushNotification(l.getDevice_token(), title, msg, notification, null,
										null);
								notification_id = omniCommonBO
										.getNotification(l.getLanguage_id(), DRIVER_LOCATION_GPS_CODE).get(0)
										.getNotification_id();
							}

							Sended_notificationVO sended_notificationVO = new Sended_notificationVO(
									driverLocation.getMachine_driver_location_id(), notification_id,
									new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()));
							omniDriverPushDAO.insertSendedNotification(sended_notificationVO);
						} else {
							if (machineDriverLocationVO != null) {
								if (machineDriverLocationVO.getReservation_list_id() == -1
										&& driverLocation.getReservation_list_id() == -1) {
									double distanceDriver = calculateDistancebetween2Locations(
											machineDriverLocationVO.getLatitude(), driverLocation.getLatitude(),
											machineDriverLocationVO.getLongitude(), driverLocation.getLongitude());
									if (distanceDriver > distanceForCheckingDriverLocation) {
										for (DeviceAdminVO l : listadmin) {
											msg = omniCommonBO.getNotification(l.getLanguage_id(), DRIVER_LOCATION_CODE)
													.get(0).getNotification_desc() + " "
													+ machineDriverLocationVO.getName();
											String title = omniCommonBO
													.getNotification(l.getLanguage_id(), DRIVER_LOCATION_TITLE_CODE)
													.get(0).getNotification_desc();
											SecurityUtilsExt.pushNotification(l.getDevice_token(), title, msg,
													notification, null, null);
											notification_id = omniCommonBO
													.getNotification(l.getLanguage_id(), DRIVER_LOCATION_CODE).get(0)
													.getNotification_id();
										}
										Sended_notificationVO sended_notificationVO = new Sended_notificationVO(
												driverLocation.getMachine_driver_location_id(), notification_id,
												new Date(System.currentTimeMillis()),
												new Time(System.currentTimeMillis()));
										omniDriverPushDAO.insertSendedNotification(sended_notificationVO);
									}
								}
							}
						}
					}
				} else {
					for (DeviceAdminVO l : listadmin) {
						msg = omniCommonBO.getNotification(l.getLanguage_id(), DRIVER_LOCATION_GPS_CODE).get(0)
								.getNotification_desc() + " " + "All";
						String title = omniCommonBO.getNotification(l.getLanguage_id(), DRIVER_LOCATION_GPS_TITLE_CODE)
								.get(0).getNotification_desc();
						SecurityUtilsExt.pushNotification(l.getDevice_token(), title, msg, notification, null, null);
						notification_id = omniCommonBO.getNotification(l.getLanguage_id(), DRIVER_LOCATION_GPS_CODE)
								.get(0).getNotification_id();
					}
					Sended_notificationVO sended_notificationVO = new Sended_notificationVO(0, notification_id,
							new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()));
					omniDriverPushDAO.insertSendedNotification(sended_notificationVO);
				}

			}
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
	}

	@Override
	public ReturnDriverLocationResponse returnDriverLocation(ReservationListVO reservationListVO) throws Exception {
		ReturnDriverLocationResponse response = new ReturnDriverLocationResponse();
		MachineDriverVO machineDriverVO2 = new MachineDriverVO();
		try {

			machineDriverVO2 = omniDriverPullDAO.returnDriverLocation(reservationListVO);
			response.setLatitude(machineDriverVO2.getLatitude());
			response.setLongitute(machineDriverVO2.getLongitude());

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public AddMaintenanceResponse addMaintenance(Machine_mntCO machine_mntCO) throws Exception {
		AddMaintenanceResponse response = new AddMaintenanceResponse();
		try {
			machine_mntCO.setDate(new Date(System.currentTimeMillis()));
			machine_mntCO.setTime(new Time(System.currentTimeMillis()));
			omniDriverPushDAO.insertMaintenance(machine_mntCO);
			response.setResponse("done");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public SwitchingDriverAvailabilityResponse switchingDriverStatus(DriverCO driverCO) throws Exception {
		SwitchingDriverAvailabilityResponse response = new SwitchingDriverAvailabilityResponse();

		try {
			driverCO.setDate(new Date(System.currentTimeMillis()));
			driverCO.setTime(new Time(System.currentTimeMillis()));
			omniDriverPushDAO.updateDriverStatus(driverCO);

			omniDriverPushDAO.insertTrackingAvailabily(driverCO);

			response.setResponse("done");
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ReturnDriverStatusResponse returnDriverStatus(DriverVO driverVO) throws Exception {
		ReturnDriverStatusResponse response = new ReturnDriverStatusResponse();
		try {

			DriverVO driver = omniDriverPullDAO.returnDriverStatus(driverVO);
			response.setResponse("done");
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public UpdateReservationInfosResponse updateReservationInfos(ReservationSC reservationSC) throws Exception {
		UpdateReservationInfosResponse response = new UpdateReservationInfosResponse();

		try {
			List<PropertiesVO> listProperties = omniCommonPullDAO.returnListProperties(null);

			if (reservationSC.getTotalCostByDriver() != 0) {
				ReservationListVO reservationListVO = new ReservationListVO();
				reservationListVO.setReservation_list_id(reservationSC.getReservation_list_id());
				ReservationCO reservation = omniCommonPullDAO.returnReservationDetails(reservationListVO);
				if (reservation.getReservation_list_status() == FINISH_TRIP) {
					omniDriverPushDAO.updateCostByDriver(reservationSC);

					Notification notification = omniCommonBO.loadKeyAndUrl();
					ReservationCO reservationCO = new ReservationCO();
					reservationCO.setReservation_id(reservationSC.getReservation_id());
					List<DeviceVO> listClientDevices = omniCommonPullDAO.returnDeviceOfClient(reservationCO);

					for (DeviceVO d : listClientDevices) {
						String currency;
						if (d.getLanguage_id() == english || reservationSC.getLanguage_id() == french) {
							currency = "L.L";
						} else
							currency = "ل.ل";
						msg = omniCommonBO.getNotification(d.getLanguage_id(), COST_CODE).get(0).getNotification_desc()
								+ " " + reservationSC.getTotalCostByDriver() + " " + currency;
						String title = omniCommonBO.getNotification(d.getLanguage_id(), FACTURE_TITLE_CODE).get(0)
								.getNotification_desc();
						try {
							SecurityUtilsExt.pushNotification(d.getDevice_token(), title, msg, notification, null,
									null);

						} catch (Exception e) {
							log.info(e.getMessage());
						}

					}
				}
			}
			if (reservationSC.getNbOfClient() != 0) {
				DriverCO driverCO = new DriverCO();
				driverCO.setReservation_list_id(reservationSC.getReservation_list_id());
				driverCO.setNbOfClient(reservationSC.getNbOfClient());
				omniDriverPushDAO.updateNbOfClient(driverCO);
			}
			response.setResponse("done");
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	public double calculateDistancebetween2Locations(double oldLatitude, double newLatitude, double oldLongitude,
			double newLongitude) {

		final double AVERAGE_RADIUS_OF_EARTH = 6371;

		double dist = org.apache.lucene.util.SloppyMath.haversinMeters(newLatitude, newLongitude, oldLatitude,
				oldLongitude);
		double latDistance = Math.toRadians(newLatitude - oldLatitude);
		double lngDistance = Math.toRadians(newLongitude - oldLongitude);

		double a = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) + (Math.cos(Math.toRadians(oldLatitude)))
				* (Math.cos(Math.toRadians(newLatitude))) * (Math.sin(lngDistance / 2)) * (Math.sin(lngDistance / 2));

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		int d = (int) (Math.round(AVERAGE_RADIUS_OF_EARTH * c));
		double distance = AVERAGE_RADIUS_OF_EARTH * c * 1000; // convert to meters
		return distance;

	}

	@Override
	public CheckingFutureDriverResponse checkingFutureDriver(ReservationCO reservationCO) throws Exception {
		CheckingFutureDriverResponse response = new CheckingFutureDriverResponse();
		try {
			/*
			 * calculate the distance between driver and client in km
			 */
			List<PropertiesVO> listProperties = omniCommonPullDAO.returnListProperties(null);
			String distanceBetweenDriverClientRecurringString = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(distance_between_driver_client_recurring))
					.collect(Collectors.toList()).get(0).getProperty_value();
			int distanceBetweenDriverClientRecurring = Integer.parseInt(distanceBetweenDriverClientRecurringString);

			String distanceBetweenDriverDriverRecurringString = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(distance_between_driver_driver_recurring))
					.collect(Collectors.toList()).get(0).getProperty_value();
			int distanceBetweenDriverDriverRecurring = Integer.parseInt(distanceBetweenDriverDriverRecurringString);

			ReservationListVO reservationListVO = new ReservationListVO();
			reservationListVO.setReservation_list_id(reservationCO.getReservation_list_id());

			ReservationCO reservationCO2 = omniCommonPullDAO.returnReservationDetails(reservationListVO);

			double distance = calculateDistancebetween2Locations(reservationCO.getLatitude(),
					reservationCO2.getLatitude_from(), reservationCO.getLongitude(),
					reservationCO2.getLongitude_from());

			/*
			 * if distance more than the property , return the available driver , then check
			 * the distance between them and driver
			 * 
			 */
			if (distance > distanceBetweenDriverClientRecurring) {
				reservationCO.setDate(reservationCO2.getDate());
				reservationCO.setTime(reservationCO2.getTime());
				reservationCO.setLongitude_from(reservationCO2.getLongitude_from());
				reservationCO.setLatitude_from(reservationCO2.getLatitude_from());
				ReturnNearestAvailableMachineResponse returnNearestAvailableMachine = omniCommonBO
						.returnNearestAvailableMachine(reservationCO);
				if (!returnNearestAvailableMachine.getListAvailable().isEmpty()) {
					ReservationListVO reservation = new ReservationListVO();
					reservation.setReservation_list_id(reservationCO.getReservation_list_id());
					/*
					 * return the location of nearest driver
					 */
					MachineDriverVO driverCO = omniDriverPullDAO.returnDriverLocation(reservationListVO);

					if (returnNearestAvailableMachine.getListAvailable().get(0).getDriver_id() != reservationCO
							.getDriver_id()) {
						double distanceDriverDriver = calculateDistancebetween2Locations(
								returnNearestAvailableMachine.getListAvailable().get(0).getLatitude(),
								driverCO.getLatitude(),
								returnNearestAvailableMachine.getListAvailable().get(0).getLongitude(),
								driverCO.getLongitude());
						/*
						 * if the distance between reserved driver and nearest driver , cancel the
						 * reserved driver, than create new reservation with nearest one
						 */
						if (distanceDriverDriver > distanceBetweenDriverClientRecurring) {

							reservationCO2.setMachine_driver_id(
									returnNearestAvailableMachine.getListAvailable().get(0).getMachine_driver_id());
							ReservationSC reservationSC = new ReservationSC();
							reservationSC.setReservation_list_id(reservationCO.getReservation_list_id());
							reservationSC.setKey_value(switchDriver);
							omniCommonPushDAO.updateCancelReservation(reservationSC);
							reservationCO.setReservation_list_status(CANCELED);
							omniCommonPushDAO.updateReservationListStatus(reservationCO);
							Machine_driverCO mdCO = new Machine_driverCO();
							mdCO.setMachine_driver_id(reservationCO2.getMachine_driver_id());
							DeviceDriverVO DeviceDriverVO = omniCommonPullDAO.returnDriverToken(mdCO);

							Notification notification = omniCommonBO.loadKeyAndUrl();
							msg = omniCommonBO
									.getNotification(DeviceDriverVO.getLanguage_id(), RESERVATION_CANCELLED_CODE).get(0)
									.getNotification_desc();
							String title = omniCommonBO
									.getNotification(DeviceDriverVO.getLanguage_id(), RESERVATION_CANCELLED_TITLE_CODE)
									.get(0).getNotification_desc();
							try {
								securityUtilsExt.pushNotification(DeviceDriverVO.getDevice_token(), title, msg,
										notification, null, null);
							} catch (Exception e) {
								log.info(e.getMessage());
							}
							omniCommonBO.createReservation(reservationCO2);
							response.setSwitch_status(switch_status);
						} else {
							response.setSwitch_status(no_switch_status);

						}
					}
				}
			} else {
				response.setSwitch_status(no_switch_status);

			}
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	public void manageDriverStatus(DriverCO driverCO) throws Exception {

		try {

			List<PropertiesVO> listProperties = omniCommonPullDAO.returnListProperties(null);

			String timeOfReservationInNearFutureString = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(time_of_reservation_in_near_future))
					.collect(Collectors.toList()).get(0).getProperty_value();
			long timeOfReservationInNearFuture = Long.parseLong(timeOfReservationInNearFutureString);

			String gracePeriodToUpdateDriverLocationStr = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(grace_period_to_update_driver_location))
					.collect(Collectors.toList()).get(0).getProperty_value();
			int gracePeriod = Integer.parseInt(gracePeriodToUpdateDriverLocationStr);

//			String timeToCheckDriverLocationString = listProperties.stream()
//					.filter(l -> l.getProperty_name().equals(time_to_check_driver_location))
//					.collect(Collectors.toList()).get(0).getProperty_value();
//			
//			long timeToTurnOffDriver = Long.parseLong(timeToCheckDriverLocationString);
			ReservationCO reservationCO2 = new ReservationCO();
			driverCO.setTime(new Time(System.currentTimeMillis()));
			List<ReservationCO> listOfFutureReservationOfDriver = omniDriverPullDAO
					.returnListOfFutureReservation(driverCO);
			if (!listOfFutureReservationOfDriver.isEmpty()) {
				for (ReservationCO futureReservation : listOfFutureReservationOfDriver) {
					LocalDateTime datetime = omniCommonBO.ConvertTimeToJavaTime(futureReservation.getTime(),
							futureReservation.getDate());
					datetime = datetime.plusMinutes(gracePeriod);
					java.util.Date datetimeAsDate = java.util.Date
							.from(datetime.atZone(ZoneId.systemDefault()).toInstant());
					/*
					 * if the last location of driver is before 5 min that means the driver close
					 * the app
					 */
					LocalDateTime datetimeToCheckNearFutureReservation = datetime
							.plusMinutes(timeOfReservationInNearFuture);
					java.util.Date datetimeToCheckNearFutureReservationAsDate = java.util.Date
							.from(datetime.atZone(ZoneId.systemDefault()).toInstant());
					java.util.Date now = new java.util.Date();
					if (datetimeToCheckNearFutureReservationAsDate.before(now)) {
						ReservationCO reservationCO = new ReservationCO();
						reservationCO.setDate(futureReservation.getDate());
						reservationCO.setTime(futureReservation.getTime());
						ReturnNearestAvailableMachineResponse driver = omniCommonBO
								.returnNearestAvailableMachine(reservationCO);
						futureReservation.setDriver_id(driver.getListAvailable().get(0).getDriver_id());
					} else {
						ReservationDetailsCO reservationDetailsCO = new ReservationDetailsCO();
						reservationDetailsCO.setDate(futureReservation.getDate());
						reservationDetailsCO.setTime(futureReservation.getTime());
						Machine_driverCO driverRandomly = omniCommonPullDAO
								.returnMachineDriverRandomly(reservationDetailsCO);
						futureReservation.setDriver_id(driverRandomly.getDriver_id());
					}
					ReservationCO cloneReservationCO = futureReservation.cloneReservationCO();
					cloneReservationCO.setReservation_list_id(0);
					cloneReservationCO.setReservation_id(0);
					cloneReservationCO.setReservation_list_status(PENDING);
					cloneReservationCO.setFromThread(fromThread);
					omniCommonBO.createReservation(cloneReservationCO);
					ReservationSC reservationSC = new ReservationSC();
					reservationSC.setMachine_driver_id(futureReservation.getMachine_driver_id());
					reservationSC.setReservation_list_id(futureReservation.getReservation_list_id());
					reservationSC.setKey_value(unavailableDriverCancelation);
					omniCommonPushDAO.updateCancelReservation(reservationSC);
					ReservationCO reservationCO3 = new ReservationCO();
					reservationCO3.setReservation_list_id(reservationSC.getReservation_list_id());
					reservationCO3.setReservation_list_status(CANCELED);
					omniCommonPushDAO.updateReservationListStatus(reservationCO3);
				}
			}

		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	

	@Override
	public List<ReturnRecurringClientsResponse> returnRecurringClients() throws Exception {
		try {
			List<ReturnRecurringClientsResponse> response = omniDriverPullDAO.returnRecurringClients();
			return response;
			
		} catch (Exception e) {
				log.info(e.getMessage());
				return null;
		}
	}

	@Override
	public CheckRecurringReservationResponse checkRecurringReservation(ReservationCO reservationCO) throws Exception {
		try {
			CheckRecurringReservationResponse response = new CheckRecurringReservationResponse();
			omniCommonBO.returnRecurringReservationList(reservationCO);
			if(response.getError() != null) {
				
				//////////////////////Need help!!!!
				response.setError("here we return error statement");
				return response;
			}else {
				return response;
			}
		}catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
		
		
		
	}

}
