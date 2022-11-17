package com.path.bo.omni.client.impl;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.path.bo.omni.client.OmniClientBO;
import com.path.bo.omni.common.OmniCommonBO;
import com.path.bo.omni.common.base.OmniBaseBO;
import com.path.dao.omni.client.pull.OmniClientPullDAO;
import com.path.dao.omni.client.push.OmniClientPushDAO;
import com.path.dao.omni.common.push.OmniCommonPushDAO;
import com.path.dao.omni.driver.OmniDriverDAO;
import com.path.dao.omni.driver.pull.OmniDriverPullDAO;
import com.path.dbmaps.vo.ClientCO;
import com.path.dbmaps.vo.ClientDeviceCO;
import com.path.dbmaps.vo.ClientVO;
import com.path.dbmaps.vo.Client_deviceVO;
import com.path.dbmaps.vo.DeviceDriverVO;
import com.path.dbmaps.vo.DeviceKeysSC;
import com.path.dbmaps.vo.DeviceSC;
import com.path.dbmaps.vo.DeviceVO;
import com.path.dbmaps.vo.DriverVO;
import com.path.dbmaps.vo.InvitedContactCO;
import com.path.dbmaps.vo.InvitedContactVO;
import com.path.dbmaps.vo.List_destinationsVO;
import com.path.dbmaps.vo.LovVO;
import com.path.dbmaps.vo.Machine_driverCO;
import com.path.dbmaps.vo.ParamLovSC;
import com.path.dbmaps.vo.ParamLovVO;
import com.path.dbmaps.vo.PropertiesVO;
import com.path.dbmaps.vo.ReservationCO;
import com.path.dbmaps.vo.ReservationDetailsVO;
import com.path.dbmaps.vo.ReservationListVO;
import com.path.dbmaps.vo.ReservationSC;
import com.path.dbmaps.vo.ReservationVO;
import com.path.dbmaps.vo.User;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.SecurityUtilsExt;
import com.path.ws.omni.vo.common.AddDestinationResponse;
import com.path.ws.omni.vo.common.AuthenticateClientResponse;
import com.path.ws.omni.vo.common.CancelReservationResponse;
import com.path.ws.omni.vo.common.ChangePasswordResponse;
import com.path.ws.omni.vo.common.ChangeProfileResponse;
import com.path.ws.omni.vo.common.CheckUsernameResponse;
import com.path.ws.omni.vo.common.ConfirmBrokerReservationResponse;
import com.path.ws.omni.vo.common.ContactUsRequest;
import com.path.ws.omni.vo.common.ContactUsResponse;
import com.path.ws.omni.vo.common.CreateNewUserResponse;
import com.path.ws.omni.vo.common.CreateRecurringReservationResponse;
import com.path.ws.omni.vo.common.ForgetPasswordResponse;
import com.path.ws.omni.vo.common.InviteContactResponse;
import com.path.ws.omni.vo.common.Invited_contactSC;
import com.path.ws.omni.vo.common.LoginClientResponse;
import com.path.ws.omni.vo.common.LogoutResponse;
import com.path.ws.omni.vo.common.Notification;
import com.path.ws.omni.vo.common.RegisterClientResponse;
import com.path.ws.omni.vo.common.RememberDeviceResponse;
import com.path.ws.omni.vo.common.ResetPasswordResponse;
import com.path.ws.omni.vo.common.ResponseBaseObject;
import com.path.ws.omni.vo.common.ReturnClientInfoResponse;
import com.path.ws.omni.vo.common.ReturnListDevicesResponse;
import com.path.ws.omni.vo.common.ReturnListOfBrokersResponse;
import com.path.ws.omni.vo.common.ReturnListOfDestinationsResponse;
import com.path.ws.omni.vo.common.ReturnListOfMachinesNearestMostFarResponse;
import com.path.ws.omni.vo.common.ReturnListOfServicesResponse;
import com.path.ws.omni.vo.common.ReturnNearestAvailableMachineResponse;
import com.path.ws.omni.vo.common.ReturnRecurringReservationListResponse;
import com.path.ws.omni.vo.common.ReturnReservationDetailsResponse;
import com.path.ws.omni.vo.common.ReturnReservationsListResponse;
import com.path.ws.omni.vo.common.ReturnTlubtukReservationDetailsResponse;
import com.path.ws.omni.vo.common.UnregisterDeviceResponse;
import com.path.ws.omni.vo.common.UpdateClientInfosResponse;
import com.path.ws.omni.vo.common.returnListOfContactToInviteResponse;

//import net.anotheria.moskito.aop.annotation.Monitor;

//@Monitor
public class OmniClientBOImpl extends OmniBaseBO implements OmniClientBO {

	protected OmniDriverDAO dao;
	protected OmniCommonBO omniCommonBO;
	protected OmniClientPullDAO omniClientPullDAO;
	protected OmniClientPushDAO omniClientPushDAO;
	protected OmniCommonPushDAO omniCommonPushDAO;
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	DateTimeFormatter df = DateTimeFormatter.ofPattern("kk:mm");

//	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void setOmniCommonBO(OmniCommonBO omniCommonBO) {
		this.omniCommonBO = omniCommonBO;
	}

	public void setOmniClientPullDAO(OmniClientPullDAO omniClientPullDAO) {
		this.omniClientPullDAO = omniClientPullDAO;
	}

	public void setOmniClientPushDAO(OmniClientPushDAO omniClientPushDAO) {
		this.omniClientPushDAO = omniClientPushDAO;
	}

	public void setOmniDriverPullDAO(OmniDriverPullDAO omniDriverPullDAO) {
		this.omniDriverPullDAO = omniDriverPullDAO;
	}

	public void setOmniCommonPushDAO(OmniCommonPushDAO omniCommonPushDAO) {
		this.omniCommonPushDAO = omniCommonPushDAO;
	}

	public int getCountStatus() {
		return countStatus;
	}

	public void setCountStatus(int countStatus) {
		this.countStatus = countStatus;
	}

	SecurityUtilsExt securityUtilsExt;
	Properties prop = new Properties();
	DateFormat timeFormat = new SimpleDateFormat("HH:mm:00");

	/**
	 * 
	 * method that check the combination of sim serial and device serial in th db
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public AuthenticateClientResponse authenticateClient(ClientCO clientCO) throws Exception { //
		String secretKey;
		AuthenticateClientResponse response = new AuthenticateClientResponse();
		try {
			// check the combination of username and device_UUID if exist login , if not
			// exist return error
			log.info(" debut get username device  " + format1.format(new java.util.Date()));

			ClientDeviceCO client_deviceCO = omniClientPullDAO.returnClientIdMobileNumberDevice(clientCO);
			log.info(" fin get username device  " + format1.format(new java.util.Date()));
			List<PropertiesVO> list = omniCommonPullDAO.returnListProperties(null);

			/*
			 * the combination exist in db and the hashmap contains the key,
			 */
			if (client_deviceCO != null) {

				DeviceKeysSC deviceKeysSC = omniCommonBO.getTokenAndKeysClient(clientCO.getDevice_UUID());

				deviceKeysSC.setDevice_UUID(clientCO.getDevice_UUID());

				omniCommonPushDAO.updateToken(deviceKeysSC);

				response.setToken(deviceKeysSC.getToken());
				response.setResponse("success");

				String companyMobile = list.stream().filter(l -> l.getProperty_name().equals(company_mobile))
						.collect(Collectors.toList()).get(0).getProperty_value();

				response.setCompanyMobile(companyMobile);
				response.setFree_trip(client_deviceCO.getFree_trip());
			}

			else {

				response.setErrorCode(errorCodeAuthenticate);
				response.setErrorDesc(omniCommonBO.getError(clientCO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());

			}
//			response.setEnableCrashlytics(list.stream().filter(l -> l.getProperty_name().equals(enableCrashlytics))
//					.collect(Collectors.toList()).get(0).getProperty_value());
			String propertiesToFrontEnd = list.stream().filter(l -> l.getProperty_name().equals(propToFrontEndClient))
					.collect(Collectors.toList()).get(0).getProperty_value().replaceAll("\\s", "");
			List<String> myList = new ArrayList<String>(Arrays.asList(propertiesToFrontEnd.split(";")));
			List<PropertiesVO> listPropeties = new ArrayList<PropertiesVO>();
			for (String listprop : myList) {
				PropertiesVO e = new PropertiesVO(listprop,
						list.stream().filter(l -> l.getProperty_name().equals(listprop)).collect(Collectors.toList())
								.get(0).getProperty_value());
				listPropeties.add(e);
			}
			response.setListProperties(listPropeties);
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public CheckUsernameResponse checkUsername(ClientVO clientVO) throws Exception {

		CheckUsernameResponse response = new CheckUsernameResponse();
		ClientVO client = new ClientVO();
		try {

			// check if the username exist in db or not , if not return valid
			client = omniClientPullDAO.returnUsername(clientVO);
			if (client == null) {
				String res = omniCommonBO.getNotification(clientVO.getLanguage_id(), VALID_USERNAME_CODE).get(0)
						.getNotification_desc();
				response.setResponse(res);
				// username exist , so put another one
			} else {
				response.setErrorCode(errorCodeCheckUsername);
				response.setErrorDesc(omniCommonBO.getError(clientVO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());
			}

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public RegisterClientResponse registerClient(ClientCO clientCO) throws Exception {
		RegisterClientResponse response = new RegisterClientResponse();
		String hashPassword = null;
		String companyMobile = null;
		try {
			if (clientCO.getMobile_number().equals(clientCO.getSecond_mobile_number())) {
				response.setErrorCode(errorReferenceMobileNumber);
				response.setErrorDesc(omniCommonBO.getError(clientCO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());
				return response;
			}
			Notification notification = omniCommonBO.loadKeyAndUrl();

			/*
			 * return list of properties
			 */
			List<PropertiesVO> listProperties = omniCommonPullDAO.returnListProperties(null);
			companyMobile = listProperties.stream().filter(l -> l.getProperty_name().equals(company_mobile))
					.collect(Collectors.toList()).get(0).getProperty_value();
			String free_tripString = listProperties.stream()
					.filter(l -> l.getProperty_name().equals(nb_free_reservation)).collect(Collectors.toList()).get(0)
					.getProperty_value();
			int freeTrip = Integer.parseInt(free_tripString);
			ClientVO clientVO = new ClientVO();
			clientVO.setUsername(clientCO.getUsername());
			/*
			 * check if the combination username mobile number exist in client table
			 */
			ClientVO combinationUsernameMobileNumber = omniClientPullDAO.returnUsernameMobileNumber(clientCO);

			/*
			 * check if the combination username mobile number and device exist in
			 * client_device table
			 */

			ClientDeviceCO combinationUsernameMobileNumberDeviceUUID = omniClientPullDAO
					.returnCombinationUsernameMobileNumberDevice(clientCO);

			/*
			 * if both exist return in the response the client id and token
			 */
			if (combinationUsernameMobileNumber != null && combinationUsernameMobileNumberDeviceUUID != null) {
				clientVO.setClient_id(combinationUsernameMobileNumber.getClient_id());
				clientCO.setClient_id(combinationUsernameMobileNumber.getClient_id());

				response.setClient_id(combinationUsernameMobileNumber.getClient_id());

			} else {
				/*
				 * check if the device has link with any client id
				 */
				Client_deviceVO deviceInCombinationClientDevice = omniClientPullDAO
						.returnIfDeviceExistInClientDevice(clientCO);

				User user = new User(clientCO.getApp(), clientCO.getUsername(), clientCO.getDevice_UUID(),
						clientCO.getUsername(), clientCO.getLanguage_id(), clientCO.getDevice_manufacturer());

				omniCommonPushDAO.updateDeviceManufacturer(user);
				/*
				 * check if the mobile number exist or no
				 */
				if (combinationUsernameMobileNumber == null) {
					ClientVO mobile_Number = omniCommonPullDAO.returnMobileNumberIfExist(clientCO);
					/*
					 * if the mobile number does not exist , insert username and mobile number ,
					 * then check if the new client enter a mobile number as reference , if yes ,
					 * add new free trip for the reference number and push notification or if the
					 * client enter a name as reference insert it
					 */
					if (mobile_Number == null) {
//						clientCO.setFree_trip(freeTrip);
					} else {
//						clientCO.setFree_trip(0);
//						for (ClientVO mobile_number : listOfMobileNumber) {
						mobile_Number.setActive(inactive);
//							clientCO.setFree_trip(mobile_number.getFree_trip());

//							mobile_number.setFree_trip(0);
							omniClientPushDAO.updateInactiveClient(mobile_Number);
						
					}
					clientCO.setActive(active);
					omniClientPushDAO.insertUsernameMobileNumber(clientCO);
					if (clientCO.getSecond_mobile_number() != null) {
						ClientCO clientCO2 = new ClientCO();
						clientCO2.setMobile_number(clientCO.getSecond_mobile_number());
						clientCO.setReference_name(clientCO.getSecond_mobile_number());
						omniClientPushDAO.updateReferenceName(clientCO);

					}
					if (clientCO.getReference_name() != null || clientCO.getSecond_mobile_number() != null) {
						omniClientPushDAO.updateReferenceName(clientCO);
					}
					/*
					 * check if the device has combination with client id , update the combination
					 * with new client , if no insert new combination
					 */
					if (deviceInCombinationClientDevice != null) {
						omniClientPushDAO.updateCombinationUsernameMobileNumberDevice(clientCO);
					} else {
						omniClientPushDAO.insertCombination(clientCO);
					}

				} else if (combinationUsernameMobileNumber != null && deviceInCombinationClientDevice != null) {
					clientCO.setClient_id(combinationUsernameMobileNumber.getClient_id());
					omniClientPushDAO.updateCombinationUsernameMobileNumberDevice(clientCO);

				} else if (combinationUsernameMobileNumber != null && deviceInCombinationClientDevice == null) {
					clientCO.setClient_id(combinationUsernameMobileNumber.getClient_id());
					omniClientPushDAO.insertCombination(clientCO);
				}

				clientVO.setClient_id(clientCO.getClient_id());

				response.setClient_id(clientCO.getClient_id());

			}
			/*
			 * put the client in the hashmap of keys
			 */
			DeviceKeysSC client = omniCommonBO.getTokenAndKeysClient(clientCO.getDevice_UUID());
			client.setDevice_UUID(clientCO.getDevice_UUID());
			omniCommonPushDAO.updateToken(client);
			clientCO.setLast_login_client(new java.util.Date(System.currentTimeMillis()));
			/*
			 * update the last login date in client table
			 */
			omniClientPushDAO.updateLastLoginClient(clientCO);
			/*
			 * update the last login for the combination client and device
			 */
			clientCO.setLast_login(new java.util.Date(System.currentTimeMillis()));
			omniClientPushDAO.updateLastLoginClientDevice(clientCO);
			/*
			 * return in the response the details of the account
			 */
			ClientVO cliVo = omniClientPullDAO.returnClientInfo(clientVO);
			DeviceSC deviceSC = new DeviceSC();
			deviceSC.setDevice_UUID(clientCO.getDevice_UUID());
			deviceSC.setLanguage_id(clientCO.getLanguage_id());
			deviceSC.setApp(clientCO.getApp());
			omniCommonPushDAO.updateDeviceLanguage(deviceSC);
			response.setLast_login_client(format.format(cliVo.getLast_login_client()));
			response.setUsername(cliVo.getUsername());
			response.setProfile(cliVo.getProfile());
//				response.setGender(cliVo.getGender());
			response.setToken(client.getToken());
			response.setCompanyMobile(companyMobile);

			/*
			 * if the new user is invited by an existing client , add one free reservation
			 * for the old client
			 */

			if (getClientDeviceHashMap().containsKey(clientCO.getDevice_UUID())) {
				if (getClientDeviceHashMap().get(clientCO.getDevice_UUID()).getClient_id() == clientCO.getClient_id()) {
					if (getClientDeviceHashMap().get(clientCO.getDevice_UUID()).getClient_status() == 1) {
//					clientDeviceCO.setClient_status(0);
						ClientDeviceCO clientDeviceCO2 = new ClientDeviceCO();
						clientDeviceCO2.setClient_status(1);
						clientDeviceCO2.setClient_id(response.getClient_id());
//						clientDeviceHashMap.replace(clientCO.getDevice_UUID(), clientDeviceCO);
						clientDeviceHashMap.remove(clientCO.getDevice_UUID());
					}
				}
			}

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);

		}
		return response;
	}

	@Override
	public LoginClientResponse loginClient(ClientCO clientCO) throws Exception {
		LoginClientResponse response = new LoginClientResponse();

		try {
			// return the password and salt from db for this username
			ClientVO clientVO = omniClientPullDAO.returnUsernamePassword(clientCO);
//			String hashPassword = propertiesHashMap.get("hashPassword").getProperty_value();
//			String companyMobile = propertiesHashMap.get("companyMobile").getProperty_value();
			List<PropertiesVO> listProperties = omniCommonPullDAO.returnListProperties(null);
			String hashPassword = listProperties.stream().filter(l -> l.getProperty_name().equals(hash_password))
					.collect(Collectors.toList()).get(0).getProperty_value();
			String companyMobile = listProperties.stream().filter(l -> l.getProperty_name().equals(company_mobile))
					.collect(Collectors.toList()).get(0).getProperty_value();

			/* check if the username exist */
			if (clientVO == null) { // the username is invalid , does not exist in bd
				response.setErrorCode(errorCodeLoginInvalidUsername);
				response.setErrorDesc(omniCommonBO.getError(clientCO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());
			} else { // username valid
				String pass = clientCO.getUsername() + clientCO.getPassword() + hashPassword;

				/* validate the requested password */
				if (SecurityUtilsExt.validatePassword(pass, clientVO.getSalt(), clientVO.getPassword())) {
					java.util.Date last_login = new java.util.Date(System.currentTimeMillis());

					/*
					 * check if the device exist if the db
					 */
					User user = new User(clientCO.getApp(), clientCO.getUsername(), clientCO.getDevice_UUID(),
							clientCO.getUsername(), clientCO.getLanguage_id(), clientCO.getDevice_manufacturer());
					DeviceVO device = omniCommonPullDAO.returnDevice(user);
					if (device == null) {
						omniCommonPushDAO.insertDevice(user);
					}

					// the password valid put the client in the hashmap
//					User user = new User(clientCO.getUsername(), clientCO.getDevice_UUID(),
//							clientCO.getFrontEndPublicKey());
					DeviceKeysSC deviceKeysSC = omniCommonBO.getTokenAndKeysClient(clientCO.getDevice_UUID());
					deviceKeysSC.setDevice_UUID(clientCO.getDevice_UUID());
					omniCommonPushDAO.updateToken(deviceKeysSC);
//					clientKeysHashMap.put(clientCO.getDevice_UUID(), deviceKeysSC);
					Client_deviceVO client_deviceVO = omniClientPullDAO.returnCombination(clientCO);

					// check the combination of username and device

					if (client_deviceVO == null) {
//						response.setResponseCode(responseCodeCombinationNotExist);
//						response.setResponseDesc("the combinatiom client device not exist");
					}
					/*
					 * if the combination exist in db set the client status 0 "login" and update the
					 * last login device and user
					 */
					else {
						clientCO.setClient_id(clientVO.getClient_id());
						clientCO.setClient_status(clientStatusLogin);
						omniClientPushDAO.updateClientStatus(clientCO);
						clientCO.setLast_login(last_login);
						omniClientPushDAO.updateLastLoginClientDevice(clientCO);

					}
					/*
					 * update last login of the account
					 */
					clientCO.setLast_login_client(last_login);
					omniClientPushDAO.updateLastLoginClient(clientCO);

					DeviceSC deviceSC = new DeviceSC();
					deviceSC.setDevice_UUID(clientCO.getDevice_UUID());
					deviceSC.setLanguage_id(clientCO.getLanguage_id());
					deviceSC.setApp(clientCO.getApp());
					omniCommonPushDAO.updateDeviceLanguage(deviceSC);
					/*
					 * return the client info username , profile , last_login and gender
					 */
					ClientVO cliVo = omniClientPullDAO.returnClientInfo(clientVO);
					response.setLast_login_client(format.format(cliVo.getLast_login_client()));
					response.setGender(cliVo.getGender());
					response.setClient_id(clientVO.getClient_id());
					response.setToken(deviceKeysSC.getToken());
					response.setUsername(cliVo.getUsername());
					response.setProfile(cliVo.getProfile());
					response.setResponse("done");

					response.setToken(deviceKeysSC.getToken());
					response.setResponse("success");
					response.setCompanyMobile(companyMobile);
				} else { // password invalid, return a msg the password is incorrect
					response.setErrorCode(errorCodeLoginIncorrectPassword);
					response.setErrorDesc(omniCommonBO.getError(clientCO.getLanguage_id(), response.getErrorCode())
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
	public RememberDeviceResponse rememberDevice(ClientCO clientCO) throws Exception {
		RememberDeviceResponse response = new RememberDeviceResponse();
		try {
			User user = new User(clientCO.getApp(), clientCO.getUsername(), clientCO.getDevice_UUID(),
					clientCO.getUsername(), clientCO.getLanguage_id(), clientCO.getDevice_manufacturer());
			DeviceVO deviceVO = omniCommonPullDAO.returnDevice(user);
			if (deviceVO == null) {
				omniCommonPushDAO.insertDevice(user);
			}
			omniClientPushDAO.insertCombination(clientCO);
			clientCO.setLast_login(new java.util.Date(System.currentTimeMillis()));
			omniClientPushDAO.updateLastLoginClientDevice(clientCO);
			response.setResponse("saved");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	int countStatus;

//	private boolean conflictTimes(Date startDate1)

//	@SuppressWarnings("unused")
//	@Override
//	public ReturnListOfMachinesResponse returnListOfMachinesNearestMostFar(ReservationCO reservationCO)
//			throws Exception {
//		ReturnListOfMachinesResponse response = new ReturnListOfMachinesResponse();
//		Machine_driverCO machine_driverCO = new Machine_driverCO();
//		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//
//		try {
//
//			List<PropertiesVO> listProperties = omniCommonPullDAO.returnListProperties(null);
//			String availabilityPeriodStre = listProperties.stream()
//					.filter(l -> l.getProperty_name().equals(availabilityPeriod)).collect(Collectors.toList()).get(0)
//					.getProperty_value();
//			int availabilityPeriod = Integer.parseInt(availabilityPeriodStre);
//
//			long now = System.currentTimeMillis();
//			Date today = new Date(now);
//			Time timeNow = new Time(now);
//			if (reservationCO.getDate() != null || reservationCO.getTime() != null) {
//				reservationCO.setOccurrence_type(occurrenceTypeFuture);
//			}
//
//			if (reservationCO.getDate() == null && reservationCO.getTime() == null) {
//				reservationCO.setTime(new Time(now));
//				reservationCO.setDate(today);
//				refreshData(reservationCO);
//			}
//
//			if (reservationCO.getTime() == null) {
////				Time time = new Time(now);
//				reservationCO.setTime(timeNow);
//				reservationCO.setReservation_type(reservationTypeTrip);
//				reservationCO.setOccurrence_type(occurrenceTypeImmediate);
//			}
//			if (reservationCO.getDate() == null) {
////				Date today = new Date(now);
//				reservationCO.setDate(today);
//			}
//			if (!reservationCO.getDate().toString().equals(today.toString())) {
//				if (reservationCO.getDate().before(today)) {
//					reservationCO.setDate(today);
//					reservationCO.setTime(timeNow);
//					reservationCO.setOccurrence_type(occurrenceTypeImmediate);
//				}
//			} else if (reservationCO.getDate().toString().equals(today.toString())) {
//				if (reservationCO.getTime().getHours() < timeNow.getHours()
//						|| (reservationCO.getTime().getHours() == timeNow.getHours()
//								&& reservationCO.getTime().getMinutes() < timeNow.getMinutes())) {
//					reservationCO.setTime(new Time(now));
//					reservationCO.setOccurrence_type(occurrenceTypeImmediate);
//
//				}
//			}
//
////			List<Machine_driverCO> list = new ArrayList<>();
////			reservationCO.setVelocity(velocity);
////			List<Machine_driverCO> listOfDriver;
////			log.info(" debut for loop  " + format1.format(new java.util.Date()));
////
////			for (int i = 0; i < availabilityPeriod; i++) {
////
////				if (i == 0 && reservationCO.getOccurrence_type() == occurrenceTypeImmediate) {
////
////					log.info(" debut i=0 list of available driver  " + format1.format(new java.util.Date()));
////
////					listOfDriver = omniClientPullDAO.returnListAvailableDriver(reservationCO);
////					log.info(" fin get available driver  " + format1.format(new java.util.Date()));
////					log.info(" debut set time to reach client " + format1.format(new java.util.Date()));
//////					Time timetoreach = Time.valueOf("00:10:00");
//////					listOfDriver = listOfDriver.stream().filter(l -> l.getTimeToReachClient().before(timetoreach))
//////							.collect(Collectors.toList());
////					if (listOfDriver != null) {
//////						listOfDriver = listOfDriver.stream().filter(l -> l.getTimeToReachClient().before(timetoreach))
//////								.collect(Collectors.toList());
////						for (Machine_driverCO l : listOfDriver) {
////							int timeToReachClient = (int) ((l.getDistance() / velocity) * 60);
////							String s = omniClientBO.setTimeToReachClient(timeToReachClient,
////									reservationCO.getLanguage_id());
////							l.setTime(reservationCO.getTime());
////							l.setDate(reservationCO.getDate());
//////							l.setTimeString(Integer.toString(t));
////							l.setTimeString(s);
////						}
////					}
////				} else {
////					if (i != 0) {
////						log.info(" debut add time " + format1.format(new java.util.Date()));
////						LocalTime localtime = reservationCO.getTime().toLocalTime().plusMinutes(30);
////						reservationCO.setTime(
////								Time.valueOf(localtime.format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.US))));
////						log.info(" fin add time" + format1.format(new java.util.Date()));
////
////					}
////
////					listOfDriver = omniClientPullDAO.returnListDriver(reservationCO);
////
////					if (listOfDriver != null) {
////						for (Machine_driverCO l : listOfDriver) {
////							l.setTimeString(timeFormat.format(reservationCO.getTime()));
////							l.setDate(reservationCO.getDate());
////						}
////					}
////					log.info(" fin list driver " + format1.format(new java.util.Date()));
////
////				}
////				list.addAll(listOfDriver);
////
////			}
//
////			List<Machine_driverCO> listDriver = omniClientPullDAO.returnListDriver(reservationCO);
//
////			reservationCO.setTimeFromDestinationToClient(timeFromDestinationToClient);
////			List<Machine_driverCO> unavList = omniClientPullDAO.returnListUnavailableMachines(reservationCO);
////			maList.addAll(unavList);
//
//			/*
//			 * if the list is not empty and if the language is arabic , set the name of
//			 * driver the arabic name
//			 */
//			List<Machine_driverCO> list = omniClientBO.returnList(reservationCO);
//			if (!list.isEmpty()) {
//				if (reservationCO.getLanguage_id() == arabic) {
//					for (Machine_driverCO l : list) {
//						l.setDriver_name(l.getAr_name());
//					}
//				}
//
//				if (list.size() > availabilityPeriod) {
//					list = list.subList(0, availabilityPeriod);
//				}
//				log.info(" fin service" + format1.format(new java.util.Date()));
//
//				response.setList(list);
//			} else {
//				response.setResponse(noData);
//			}
//
//		} catch (Exception e) {
//			log.info(e.getMessage());
//			response.setResponseError(ERROR);
//		}
//		return response;
//	}

	public int recursive(int x) {
		int result;
		if (x > 0) {
			result = x + recursive(x - 1);
			System.out.println(result);
		} else
			result = 0;
		return result;
	}

//	public List<Machine_driverCO> returnList(ReservationCO reservationCO) {
//		ReturnListOfMachinesResponse response = new ReturnListOfMachinesResponse();
//		try {
//			/*
//			 * get all driver available today from db
//			 */
//			LocalTime localtime = reservationCO.getTime().toLocalTime();
//
//			List<PropertiesVO> listProperties = omniCommonPullDAO.returnListProperties(null);
//			List<Machine_driverCO> finalList = new ArrayList<Machine_driverCO>();
//
//			/*
//			 * get properties from db
//			 */
//			String velocityString = listProperties.stream().filter(l -> l.getProperty_name().equals(velocity))
//					.collect(Collectors.toList()).get(0).getProperty_value();
//			int velocity = Integer.parseInt(velocityString);
//			String availabilityPeriodStre = listProperties.stream()
//					.filter(l -> l.getProperty_name().equals(availabilityPeriod)).collect(Collectors.toList()).get(0)
//					.getProperty_value();
//			int availabilityPeriod = Integer.parseInt(availabilityPeriodStre);
//			String timeFromDestinationToClientStr = listProperties.stream()
//					.filter(l -> l.getProperty_name().equals(timeFromDestinationToClient)).collect(Collectors.toList())
//					.get(0).getProperty_value();
//			Time timeFromDestinationToClient = Time.valueOf(timeFromDestinationToClientStr);
//
//			String userDriverDistanceString = listProperties.stream()
//					.filter(l -> l.getProperty_name().equals(user_driver_distance)).collect(Collectors.toList()).get(0)
//					.getProperty_value();
//			double userDriverDistance = Double.parseDouble(userDriverDistanceString);
//
//			reservationCO.setEndTime(Time.valueOf(localtime.plusMinutes(availabilityPeriod * 30)
//					.format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.US))));
//
//			/*
//			 * return all drivers
//			 * 
//			 */
//			reservationCO.setUserDriverDistance(userDriverDistance);
//			List<Machine_driverCO> listOfallDrivers = omniClientPullDAO.returnAllDriver(reservationCO);
//			Time t = reservationCO.getTime();
//			int size;
//			/*
//			 * create array with size from now to 12 o'clock
//			 * 
//			 */
//			size = (24 - t.getHours()) * 2;
//			int[] base = new int[size];
//			Arrays.fill(base, 0);
//			if (!listOfallDrivers.isEmpty()) {
//				/*
//				 * create hashmap that have key machine_driver_id and value array on int that
//				 * have size availability period , start with time , and have 0 1 as value m, 0
//				 * available , 1 unavailable , each index refer to part of time
//				 */
//				HashMap<Integer, int[]> baseDriverHashmap = new HashMap<Integer, int[]>();
//				HashMap<Integer, ArrayList<Time>> availableDriver = new HashMap<Integer, ArrayList<Time>>();
//				for (Machine_driverCO l : listOfallDrivers) {
////				int value[] = omniClientBO.updateAvailabilityPeriod(l, t, availabilityPeriod);
//
//					baseDriverHashmap.put(l.getMachine_driver_id(), new int[size]);
//				}
//
//				/*
//				 * for each driver , get the index of each time , and put it 1 if the driver is
//				 * not available based on working hours table
//				 */
//				for (Machine_driverCO l : listOfallDrivers) {
//
//					baseDriverHashmap.forEach((key, values) -> {
//						if (key == l.getMachine_driver_id()) {
//							int index = convertTimeToIndex(reservationCO.getTime(), l.getStart_break_hour());
//							int index2 = convertTimeToIndex(reservationCO.getTime(), l.getEnd_break_hour());
//
//							if (index >= 0 && index2 >= 0) {
//								for (int i = index; i <= index2; i++) {
//									values[i] = 3;
//								}
//
//							} else if (index <= 0 && index2 >= 0) {
//								for (int i = 0; i <= index2; i++) {
//									values[i] = 3;
//								}
//							}
//							int index3 = convertTimeToIndex(reservationCO.getTime(), l.getStart_work_hour());
//							int index4 = convertTimeToIndex(reservationCO.getTime(), l.getEnd_work_hour());
//							if (index3 > 0) {
//								for (int i = 0; i <= index3; i++) {
//									values[i] = 3;
//								}
//							}
//							if (index4 > 0) {
//								for (int i = index4; i < values.length; i++) {
//									values[i] = 3;
//								}
//							}
//						}
//					});
//				}
//				/*
//				 * return the reservation for each driver , then for each driver , get the index
//				 * of each time , and put it 1 if the driver is not available based on
//				 * reservation
//				 */
//				List<Machine_driverCO> listReservation = omniClientPullDAO.returnReservedDriver(reservationCO);
////
//				for (Machine_driverCO l : listReservation) {
//					baseDriverHashmap.forEach((key, values) -> {
//						if (key == l.getMachine_driver_id()) {
//							int index3 = convertTimeToIndex(reservationCO.getTime(), l.getTime());
//							int index4 = convertTimeToIndex(reservationCO.getTime(), l.getTime_slot());
//							if (index3 < 0)
//								index3 = 0;
//							for (int i = index3; i <= index4; i++) {
//								values[i] = values[i] + l.getNbOfClient();
//							}
////					values[index3] = 1;
//						}
//					});
//				}
//				baseDriverHashmap.forEach((key, values) -> {
//					ArrayList<Time> array = convertArrayToTime(values, reservationCO.getTime());
//					availableDriver.put(key, array);
//				});
//				availableDriver.forEach((key, values) -> {
//					for (int i = 0; i < values.size(); i++) {
//						Machine_driverCO machine_driverCO = new Machine_driverCO();
//						machine_driverCO.setMachine_driver_id(key);
//						String driverName = listOfallDrivers.stream().filter(l -> l.getMachine_driver_id() == key)
//								.collect(Collectors.toList()).get(0).getDriver_name();
//						machine_driverCO.setTime(values.get(i));
//						machine_driverCO.setDriver_name(driverName);
//						machine_driverCO.setTimeString(values.get(i).toString());
//						machine_driverCO
//								.setAr_name(listOfallDrivers.stream().filter(l -> l.getMachine_driver_id() == key)
//										.collect(Collectors.toList()).get(0).getAr_name());
//						machine_driverCO
//								.setLongitude(listOfallDrivers.stream().filter(l -> l.getMachine_driver_id() == key)
//										.collect(Collectors.toList()).get(0).getLongitude());
//						machine_driverCO
//								.setLatitude(listOfallDrivers.stream().filter(l -> l.getMachine_driver_id() == key)
//										.collect(Collectors.toList()).get(0).getLatitude());
//						if ((values.get(i).getHours() == new Time(System.currentTimeMillis()).getHours()
//								&& reservationCO.getDate().toString()
//										.equals(new Date(System.currentTimeMillis()).toString()))
//								&& (values.get(i).getMinutes() <= new Time(System.currentTimeMillis()).getMinutes())) {
//							machine_driverCO.setAvailable(available);
//						}
//						machine_driverCO.setDriver_mobile_number(
//								listOfallDrivers.stream().filter(l -> l.getMachine_driver_id() == key)
//										.collect(Collectors.toList()).get(0).getDriver_mobile_number());
//
//						finalList.add(machine_driverCO);
//					}
//				});
//				finalList.sort(Comparator.comparing(Machine_driverCO::getTime));
//			}
//
//			return finalList;
//		} catch (Exception e) {
//			log.info(e.getMessage());
//			return null;
//		}
//	}
//
//	public ArrayList<Time> convertArrayToTime(int[] values, Time startTime) {
//		ArrayList<Time> array = new ArrayList<Time>();
//		Time time = null;
//		double value;
//		for (int i = 0; i < values.length; i++) {
//			value = startTime.getHours() + (0.5 * i);
//			if (values[i] < 3) {
//				if (startTime.getMinutes() < 30) {
//					value = startTime.getHours() + (0.5 * i);
//				}
//				if (startTime.getMinutes() >= 30) {
//					value = startTime.getHours() + (0.5 * (i + 1));
//				}
//				if (value % 1 == 0)
//					time = new Time((int) value, 0, 0);
//				else
//					time = new Time((int) value, 30, 0);
//				array.add(time);
//			}
//		}
////			if (i == 0) {
////				value = startTime.getHours() + (0.5 * i);
////				if (startTime.getMinutes() < 30) {
////					time = new Time((int) value, 0, 0);
////				} else {
////					time = new Time((int) value, 30, 0);
////				}
////			}
////			if (values[i] == 0) {
////				array.add(time);
////			}
////			time = time.valueOf(time.toLocalTime().plusMinutes(30));
////
////		}
//		return array;
//
//	}

//	@Override
//	public int[] updateAvailabilityPeriod(Machine_driverCO machine_driverCO, Time startTime, int availabilityPeriod)
//			throws Exception {
//		int st = startTime.getHours();
//		int[] value = new int[availabilityPeriod];
//		Arrays.fill(value, 0);
//		Time start_work_hour = machine_driverCO.getStart_work_hour();
//		int start_work_hour_int = start_work_hour.getHours();
//		int index;
//		if (start_work_hour_int >= st
//				&& machine_driverCO.getStart_work_hour().getHours() <= (st + (availabilityPeriod / 2))) {
//			index = convertTimeToIndex(startTime, machine_driverCO.getStart_work_hour());
//			for (int i = 0; i <= index; i++) {
//				value[i] = 1;
//			}
//		}
//		if (machine_driverCO.getEnd_work_hour().getHours() <= (st + (availabilityPeriod / 2))
//				&& machine_driverCO.getEnd_work_hour().getHours() >= st) {
//			index = convertTimeToIndex(startTime, machine_driverCO.getEnd_work_hour());
//			for (int i = index; i <= value.length; i++) {
//				value[index] = 1;
//			}
//		}
//		if (machine_driverCO.getStart_break_hour().getHours() >= st
//				&& machine_driverCO.getStart_break_hour().getHours() <= (st + (availabilityPeriod / 2))
//				&& (machine_driverCO.getEnd_break_hour().getHours() <= (st + (availabilityPeriod / 2))
//						&& machine_driverCO.getEnd_break_hour().getHours() >= st)) {
//			int index1 = convertTimeToIndex(startTime, machine_driverCO.getStart_break_hour());
//			int index2 = convertTimeToIndex(startTime, machine_driverCO.getEnd_break_hour());
//			if (index1 == index2) {
//				value[index1] = 1;
//			} else {
//				for (int i = index1; i <= index2; i++) {
//					value[i] = 1;
//				}
//			}
//		}
//
//		if (machine_driverCO.getStart_break_hour().getHours() >= st
//				&& machine_driverCO.getStart_break_hour().getHours() <= (st + (availabilityPeriod / 2))
//				&& (machine_driverCO.getEnd_break_hour().getHours() > (st + (availabilityPeriod / 2)))) {
//			index = convertTimeToIndex(startTime, machine_driverCO.getStart_break_hour());
//			for (int i = index; i <= value.length; i++) {
//				value[i] = 1;
//			}
//		}
//		if (machine_driverCO.getEnd_break_hour().getHours() >= st
//				&& machine_driverCO.getEnd_break_hour().getHours() <= (st + (availabilityPeriod / 2))
//				&& (machine_driverCO.getStart_break_hour().getHours() < st)) {
//			index = convertTimeToIndex(startTime, machine_driverCO.getEnd_break_hour());
//			for (int i = 0; i < index; i++) {
//				value[i] = 1;
//			}
//		}
//		return value;
//	}

//	public int convertTimeToIndex(Time startTime, Time time) {
//		int index = 0;
//		if ((startTime.getMinutes() >= 30 && time.getMinutes() >= 30)
//				|| (startTime.getMinutes() < 30 && time.getMinutes() < 30)) {
//			index = (time.getHours() - startTime.getHours()) * 2;
//		} else if (startTime.getMinutes() >= 30 && time.getMinutes() < 30) {
//			index = (time.getHours() - startTime.getHours()) * 2 - 1;
//		} else if (startTime.getMinutes() < 30 && time.getMinutes() >= 30) {
//			index = (time.getHours() - startTime.getHours()) * 2 + 1;
//		}
//		return index;
//	}

//	void refreshData(ReservationCO reservationCO) throws DAOException {
//		log.info(" debut get old reservations " + format1.format(new java.util.Date()));
//
//		List<ReservationListVO> list = omniClientPullDAO.returnOldReservations(reservationCO);
//		log.info(" debut get old reservations " + format1.format(new java.util.Date()));
//
//		/*
//		 * each reservation that have the date yesterday and not done , set the status
//		 * as missed then update
//		 */
////		if (!list.isEmpty()) {
////			for (ReservationListVO l : list) {
//		setExpiredReservation(reservationCO, list);
//
//		List<PropertiesVO> listProperties = omniCommonPullDAO.returnListProperties(null);
//
//		String time_to_expireString = listProperties.stream().filter(l -> l.getProperty_name().equals(time_to_expire))
//				.collect(Collectors.toList()).get(0).getProperty_value();
////		int velocity = Integer.parseInt(velocityString);
//		Time timeToExpire = Time.valueOf(time_to_expireString);
//
//		String time_to_expire_tlubtuk_reservationString = listProperties.stream()
//				.filter(l -> l.getProperty_name().equals(time_to_expire_tlubtuk_reservation))
//				.collect(Collectors.toList()).get(0).getProperty_value();
//		int time_to_expire_tlubtuk_reservation = Integer.parseInt(time_to_expire_tlubtuk_reservationString);
////		PropertyVO propertyVO = new PropertyVO();
////		propertyVO.setProperty_name(time_to_expire);
////		PropertyVO propertyVO2 = omniCommonPullDAO.returnProperty(propertyVO);
//		reservationCO.setTimeToExpire(timeToExpire);
//		List<ReservationListVO> listOld = omniClientPullDAO.returnOldReservationsToday(reservationCO);
//		setExpiredReservation(reservationCO, listOld);
//
//		List<ReservationCO> listnotConfirmedTlubtukReservations = omniClientPullDAO
//				.returnNotConfirmedTlubtukReservations(reservationCO);
//		for (ReservationCO l : listnotConfirmedTlubtukReservations) {
////			Calendar dateCal = Calendar.getInstance();
////			dateCal.setTime(l.getDate());
////			Calendar timeCal = Calendar.getInstance();
////			timeCal.setTime(l.getTime());
////
////			// Extract the time of the "time" object to the "date"
////			dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
////			dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
////			dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));
////
////			// Get the time value!
////			java.util.Date reservationDate = dateCal.getTime();
////			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
////			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////
////			LocalDateTime datetime = LocalDateTime.parse(dateFormat.format(reservationDate), formatter);
//////				        datetime = datetime.minusMinutes(gracePeriod);
////			datetime = datetime.plusMinutes(time_to_expire_tlubtuk_reservation);
////			java.util.Date datetimeAsDate = java.util.Date.from(datetime.atZone(ZoneId.systemDefault()).toInstant());
////			if (datetimeAsDate.before(new java.util.Date())) {
//
//			/**
//			 * to be checked
//			 */
////			long timeInSecs = l.getDateTimeOfCreateReservation().getTime();
////			java.util.Date afterAddingGracePeriod = new java.util.Date(
////					timeInSecs + (time_to_expire_tlubtuk_reservation * 60 * 1000));
////			if (afterAddingGracePeriod.before(new java.util.Date())) {
////				omniClientPushDAO.deleteReservation(l);
////			}
//		}
//	}
//
//	public void setExpiredReservation(ReservationCO reservationCO, List<ReservationListVO> list) throws DAOException {
//		if (!list.isEmpty()) {
//			reservationCO.setReservation_list_status(EXPIRED);
//			for (ReservationListVO l : list) {
//				reservationCO.setReservation_list_id(l.getReservation_list_id());
//				omniCommonPushDAO.updateReservationListStatus(reservationCO);
////				Machine_driverCO machine_driverCO = new Machine_driverCO();
////				machine_driverCO.setMachine_driver_id(l.getMachine_driver_id());
////				machine_driverCO.setMachine_driver_status(machineDriverStatusAvailable);
////				omniCommonPushDAO.updateMachineDriverStatus(machine_driverCO);
//			}
//		}
////		reservationCO.setOccurrence_type(occurrenceTypeRecurring);
////		List<ReservationSC> listRecurringReservation = omniClientPullDAO.returnRecurringReservation(reservationCO);
////		for (ReservationSC r : listRecurringReservation) {
////			int i = 0;
////			ReservationVO reservationVO = new ReservationVO();
////			reservationVO.setReservation_id(r.getReservation_id());
//////			reservationVO.setClient_id(r.);
////			List<ReservationSC> listReservationList = omniClientPullDAO.returnReservationsList(reservationVO);
////			for (ReservationSC rl : listReservationList) {
////				if (rl.getReservation_list_status()==EXPIRED)
////					i++;
////			}
////			if (listReservationList.size() ==i ) {
////				reservationVO.setReservation_status(EXPIRED);
////				omniClientPushDAO.updateReservationStatus(reservationVO);
////			}
////		}
//	}

	public String setTimeToReachClient(int timeToReachClient, int language_id) {
		msg = omniCommonBO.getNotification(language_id, TIME_REACH_CLIENT_CODE).get(0).getNotification_desc();
		String s;
		if (language_id == arabic) {
			s = msg + " " + timeToReachClient + " دقيقة ";
		} else {
			s = timeToReachClient + " mins " + msg;
		}
//		l.setTimeString(s);
		log.info(" fin set time to reach client " + format1.format(new java.util.Date()));

		return s;
	}

//	@Override
//	public ReturnNearestAvailableMachineResponse returnNearestAvailableMachine(ReservationCO reservationCO)
//			throws Exception {
//		ReturnNearestAvailableMachineResponse response = new ReturnNearestAvailableMachineResponse();
//
//		try {
//
//			ReturnListOfMachinesResponse res = new ReturnListOfMachinesResponse();
//			res = returnListOfMachinesNearestMostFar(reservationCO);
//			List<Machine_driverCO> listAvailable = new ArrayList<Machine_driverCO>();
//			List<Machine_driverCO> listNotAvailable = new ArrayList<Machine_driverCO>();
//
//			if (res.getResponse() != null && res.getResponse().equals(noData)) {
//				response.setResponse("No data");
//			} else {
//				for (Machine_driverCO l : res.getList()) {
//					if (l.getAvailable() == available) {
//						listAvailable.add(l);
//					} else {
//						listNotAvailable.add(l);
//					}
//				}
//				response.setListAvailable(listAvailable);
//				response.setListNotAvailable(listNotAvailable);
//			}
//
//		} catch (
//
//		Exception e) {
//			log.info(e.getMessage());
//			response.setResponseError(ERROR);
//		}
//		return response;
//	}

	public CancelReservationResponse cancelReservation(ReservationCO reservationCO) throws Exception {
		CancelReservationResponse response = new CancelReservationResponse();
		try {
//			TimeZone.setDefault(TimeZone.getTimeZone("Asia/Beirut"));
			/*
			 * the number of allowed cancellation is stored in properties file , get it
			 */
			List<PropertiesVO> list = omniCommonPullDAO.returnListProperties(null);

//			String cancelCounterPerDayString = propertiesHashMap.get("CancelCounter").getProperty_value();
			String cancelCounterPerDayString = list.stream().filter(l -> l.getProperty_name().equals(cancelCounter))
					.collect(Collectors.toList()).get(0).getProperty_value();
			int cancelCounterPerDay = Integer.parseInt(cancelCounterPerDayString);
//			String time_to_cancelStr = propertiesHashMap.get("CancelCounter").getProperty_value();
			String time_to_cancelStr = list.stream().filter(l -> l.getProperty_name().equals(timeToCancel))
					.collect(Collectors.toList()).get(0).getProperty_value();
			int time_to_cancel = Integer.parseInt(time_to_cancelStr);
			String gracePeriodStr = list.stream().filter(l -> l.getProperty_name().equals(grace_period))
					.collect(Collectors.toList()).get(0).getProperty_value();
			int gracePeriod = Integer.parseInt(gracePeriodStr);
			long now = System.currentTimeMillis();
			Date today = new Date(now);
//			long nowPlusGraceTime = now + TimeUnit.MINUTES.toMillis(gracePeriod);

			/*
			 * get driver name
			 */
			DriverVO driverVO = omniCommonPullDAO.returnDriverName(reservationCO);
			/*
			 * get the date and time of create reservation
			 */
			ReservationListVO reservationListVO = new ReservationListVO();
			reservationListVO.setReservation_list_id(reservationCO.getReservation_list_id());
			ReservationCO r = omniCommonPullDAO.returnReservationDetails(reservationListVO);

			reservationCO.setDate(today);

			/*
			 * check if the date and time of now is less then the time to cancelation
			 */
			ReservationCO reservationCO2 = new ReservationCO();
			reservationCO2.setClient_id(reservationCO.getClient_id());
			reservationCO2.setReservation_list_status(CANCELED);
			reservationCO2.setDate(reservationCO.getDate());
			int nbCancel = omniClientPullDAO.returnNumberOfCancelation(reservationCO2);

			if (nbCancel == cancelCounterPerDay) {
				response.setErrorCode(errorCodeCancelReservationNumber);
				response.setErrorDesc(omniCommonBO.getError(reservationCO.getLanguage_id(), response.getErrorCode())
						.get(0).getError_desc());

				return response;
			} else if (nbCancel < cancelCounterPerDay) {

				Machine_driverCO machineDriverCO = new Machine_driverCO();
				machineDriverCO.setMachine_driver_status(machineDriverStatusAvailable);
				machineDriverCO.setMachine_driver_id(r.getMachine_driver_id());
				reservationCO.setReservation_list_status(CANCELED);
				omniCommonPushDAO.updateReservationListStatus(reservationCO);
				if (r.getFree_status() == free) {
					ClientCO clientCO = new ClientCO();
					clientCO.setMobile_number(r.getMobile_number());
					clientCO.setActive(active);
					omniClientPushDAO.addNbOfFreeTrip(clientCO);
					response.setFree_status(free);
				}

				DeviceDriverVO DeviceDriverVO = omniCommonPullDAO.returnDriverToken(machineDriverCO);
				Notification notification = omniCommonBO.loadKeyAndUrl();
				msg = omniCommonBO.getNotification(DeviceDriverVO.getLanguage_id(), RESERVATION_CANCELLED_CODE).get(0)
						.getNotification_desc();
				String title = omniCommonBO
						.getNotification(DeviceDriverVO.getLanguage_id(), RESERVATION_CANCELLED_TITLE_CODE).get(0)
						.getNotification_desc();
				try {
					SecurityUtilsExt.pushNotification(DeviceDriverVO.getDevice_token(), title, msg, notification,
							r.getDate(), r.getTime());
				} catch (Exception e) {
					log.info(e.getMessage());
				}

//				Calendar dateCal = Calendar.getInstance();
//				dateCal.setTime(r.getDate());
//				Calendar timeCal = Calendar.getInstance();
//				timeCal.setTime(r.getTime());
//
//				// Extract the time of the "time" object to the "date"
//				dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
//				dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
//				dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));
//
//				// Get the time value!
//				java.util.Date reservationDate = dateCal.getTime();
//				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//				LocalDateTime datetime = LocalDateTime.parse(dateFormat.format(reservationDate), formatter);
//				datetime = datetime.plusMinutes(gracePeriod);
//				java.util.Date datetimeAsDate = java.util.Date
//						.from(datetime.atZone(ZoneId.systemDefault()).toInstant());
				LocalDateTime datetime = omniCommonBO.ConvertTimeToJavaTime(r.getTime(), r.getDate());
				datetime = datetime.plusMinutes(gracePeriod);
				java.util.Date datetimeAsDate = java.util.Date
						.from(datetime.atZone(ZoneId.systemDefault()).toInstant());
				if (datetimeAsDate.before(new java.util.Date())) {
					reservationCO.setPenalty_status(penaltyNotPaid);
					omniCommonPushDAO.UpdatePenaltyStatus(reservationCO);
					response.setResponseCode(10005);
					response.setResponseDesc("to late to cancel , it will be charged");
				}
				response.setResponse("reservation canceled");
			}

			else {
				response.setErrorCode(errorCodeCancelReservationTooLate);
				response.setErrorDesc(omniCommonBO.getError(reservationCO.getLanguage_id(), response.getErrorCode())
						.get(0).getError_desc());

			}
//				}
//			}
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);

		}
		return response;
	}

	@Override
	public LogoutResponse logout(ClientCO clientCO) throws Exception {
		LogoutResponse response = new LogoutResponse();
		try {
//			PublicKey k = clientHashMap.get(clientCO.getDevice_UUID()).getFrontEndPublicKey();
//			backEndKeyHashMap.remove(k);
//			clientKeysHashMap.remove(clientCO.getDevice_UUID());
			clientCO.setClient_status(clientStatusLogout);
			omniClientPushDAO.updateClientStatus(clientCO);
			response.setResponse("logout successfuly");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ReturnClientInfoResponse returnClientInfo(ClientVO clientVO) throws Exception {
		ClientVO cliVo = new ClientVO();
		ReturnClientInfoResponse response = new ReturnClientInfoResponse();
		try {
			cliVo = omniClientPullDAO.returnClientInfo(clientVO);
			response.setProfile(cliVo.getProfile());
			response.setUsername(cliVo.getUsername());
			response.setLast_login_client(format.format(cliVo.getLast_login_client()));
			response.setGender(cliVo.getGender());

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public UpdateClientInfosResponse updateClientInfos(ClientVO clientVO) {
		UpdateClientInfosResponse response = new UpdateClientInfosResponse();
		try {
			/*
			 * update client infos
			 */
			omniClientPushDAO.updateClientInfos(clientVO);
			response.setResponse("done");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ChangePasswordResponse changePassword(ClientCO clientCO) throws Exception {
		ChangePasswordResponse response = new ChangePasswordResponse();
		ClientVO client = new ClientVO();
		ClientCO newClient = new ClientCO();

		try {
			// get the username and password from db for this user by id
			client = omniClientPullDAO.returnUsernamePasswordById(clientCO);

			// password hashing is on "concatenation and salting for username and
			// password and a static value to be defined in property file.
//			prop = PropertiesLoaderUtils.loadAllProperties("HashPassword.properties");
			PropertiesVO propertyVO = new PropertiesVO();
			propertyVO.setProperty_name("hashPassword");
			PropertiesVO propertyVO2 = omniCommonPullDAO.returnProperty(propertyVO);
			String value = propertyVO2.getProperty_value();

			String oldPass = client.getUsername() + clientCO.getOldpassword() + value;
//					+ propertiesHashMap.get("hashPassword").getProperty_value();
			String newPass = client.getUsername() + clientCO.getPassword() + value;
//					+ propertiesHashMap.get("hashPassword").getProperty_value();

			// check if the old password in request is the same password in db
			if (SecurityUtilsExt.validatePassword(oldPass, client.getSalt(), client.getPassword())) {
				// hash the new password to save in db
				String salt = SecurityUtilsExt.getNextSalt();
				String hash = SecurityUtilsExt.hash(newPass, salt);
				newClient.setPassword(hash);
				newClient.setSalt(salt);
				newClient.setClient_id(clientCO.getClient_id());
				omniClientPushDAO.updatePassword(newClient);
				response.setResponse("password changed");
				response.setClient_id(clientCO.getClient_id());
			} else {
				// incorrect password
				response.setErrorCode(errorCodeIncorrectPass);
				response.setErrorDesc(omniCommonBO.getError(clientCO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());

			}

		} catch (

		Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ChangeProfileResponse changeProfile(ClientVO clientVO) throws Exception {
		ChangeProfileResponse response = new ChangeProfileResponse();
		try {
			omniClientPushDAO.updateProfile(clientVO);
			response.setResponse("done");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ReturnReservationsListResponse returnReservationsList(ReservationVO reservationVO) throws Exception {
		ReturnReservationsListResponse response = new ReturnReservationsListResponse();

		try {
//			
			List<PropertiesVO> list = omniCommonPullDAO.returnListProperties(null);
			String limitReservationsStr = list.stream().filter(l -> l.getProperty_name().equals(limitReservations))
					.collect(Collectors.toList()).get(0).getProperty_value();
			int limitReservations = Integer.parseInt(limitReservationsStr);
			/*
			 * return the list of reservations for client
			 */
			if (reservationVO.getFilterDate() != null) {
				Date date = Date.valueOf(reservationVO.getFilterDate());
				reservationVO.setDate(date);

			} else if (reservationVO.getReservation_id() == 0) {
				reservationVO.setDate(new Date(System.currentTimeMillis()));
			}
			reservationVO.setLimit(limitReservations);
			String velocityString = list.stream().filter(l -> l.getProperty_name().equals(velocity))
					.collect(Collectors.toList()).get(0).getProperty_value();
			int velocity = Integer.parseInt(velocityString);
			reservationVO.setVelocity(velocity);
			List<ReservationSC> listOfReservations = omniCommonPullDAO.returnReservationsList(reservationVO);
			List<ReservationSC> listOfTlubtukReservations = omniClientPullDAO
					.returnListOfTlubtukReservation(reservationVO);
			listOfReservations.addAll(listOfTlubtukReservations);
			/*
			 * if list empty so return no reservation
			 */
			if (listOfReservations.isEmpty()) {
				response.setResponse(noData);

			} else {
				if (reservationVO.getLanguage_id() == arabic) {
					for (ReservationSC l : listOfReservations) {
						l.setDriver_name(l.getAr_name());
					}
				}

//				for (ReservationSC l : listOfReservations) {
//					if (l.getOccurrence_type() == occurrenceTypeImmediate) {
//						if (l.getReservation_list_status() != PENDING && l.getReservation_list_status() != TAKE_TRIP) {
//							l.setTimeToReachClient(l.getTime());
//						} else if (l.getReservation_list_status() == PENDING
//								|| l.getReservation_list_status() == TAKE_TRIP) {
//							int timeToReachClient = (int) ((l.getDistance() / velocity) * 60);
//							String s = omniClientBO.setTimeToReachClient(timeToReachClient,
//									reservationVO.getLanguage_id());
//							l.setTimeString(s);
//						}
//					} else if (l.getOccurrence_type() == 1) {
//						l.setTimeToReachClient(l.getTime());
//					}
//
//				}
				response.setListOfReservations(listOfReservations);
			}

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ReturnReservationDetailsResponse returnReservationDetails(ReservationVO reservationVO) throws Exception {
		ReturnReservationDetailsResponse response = new ReturnReservationDetailsResponse();
		ReservationVO reservationOccurrenceType = new ReservationVO();
		ReservationListVO reservationListVO = new ReservationListVO();
		try {
			/*
			 * check if the reservation is recurring or one time
			 */
			reservationOccurrenceType = omniCommonPullDAO.returnReservationOccurrenceType(reservationVO);
			/*
			 * the type of reservation is one time
			 */
			if (reservationOccurrenceType.getOccurrence_type() == occurrenceTypeImmediate) {
				/*
				 * return details of the reservation from reservation_list table
				 */
				reservationListVO = omniCommonPullDAO.returnOneTimeReservationDetails(reservationVO);
				response.setReservationListVO(reservationListVO);
			}
			/*
			 * the reservation is recurring
			 */
			else {
				/*
				 * return the details from reservation , reservation_list and recurring template
				 * tables
				 */
				List<ReservationCO> listOfReservations = omniCommonPullDAO
						.returnRecurringReservationDetails(reservationVO);
				response.setListReservationsDetails(listOfReservations);
			}

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override

	public AddDestinationResponse addDestination(ReservationCO reservationCO) throws Exception {
		AddDestinationResponse response = new AddDestinationResponse();
		try {
//			omniClientPushDAO.insertDestination(list_destinationsVO);
			omniClientPushDAO.updateDestinationName(reservationCO);
			response.setResponse("done");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ReturnListOfDestinationsResponse returnListOfDestinations(ReservationCO reservationCO) throws Exception {
		ReturnListOfDestinationsResponse response = new ReturnListOfDestinationsResponse();
		try {
			List<List_destinationsVO> list = omniClientPullDAO.returnListOfDestination(reservationCO);
			if (!list.isEmpty())
				response.setList(list);
			else
				response.setResponse(noData);

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ForgetPasswordResponse forgetPasswordClient(ClientCO clientCO) throws Exception {
		ForgetPasswordResponse response = new ForgetPasswordResponse();
		try {

			ClientVO clientVO2 = omniClientPullDAO.returnMobileNumber(clientCO);
			char[] mask;
			DeviceSC deviceSC = new DeviceSC();
			deviceSC.setDevice_UUID(clientCO.getDevice_UUID());
			deviceSC.setMobile_number(clientVO2.getMobile_number());
			deviceSC.setLanguage_id(clientCO.getLanguage_id());
			deviceSC.setApp(clientCO.getApp());
			if (clientVO2 == null) {
				response.setErrorCode(errorCodeForgetPassword);
				response.setErrorDesc(omniCommonBO.getError(clientCO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());
			} else {
				response = omniCommonBO.returnMaskMobileNumber(clientVO2.getMobile_number(), deviceSC);
			}

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ResetPasswordResponse resetPasswordClient(ClientVO clientVO) throws Exception {
		ResetPasswordResponse response = new ResetPasswordResponse();
		try {
//			prop = PropertiesLoaderUtils.loadAllProperties("HashPassword.properties");
//			String value = prop.getProperty("value");
			PropertiesVO propertyVO = new PropertiesVO();
			propertyVO.setProperty_name("hashPassword");
			PropertiesVO propertyVO2 = omniCommonPullDAO.returnProperty(propertyVO);
			String pass = clientVO.getUsername() + clientVO.getPassword() + propertyVO2.getProperty_value();
//					+ propertiesHashMap.get("hashPassword").getProperty_value();
			String salt = SecurityUtilsExt.getNextSalt();
			clientVO.setPassword(SecurityUtilsExt.hash(pass, salt));
			clientVO.setSalt(salt);
			omniClientPushDAO.resetPassword(clientVO);
			response.setResponse("done");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ReturnListDevicesResponse returnListDevices(ClientVO clientVO) throws Exception {
		ReturnListDevicesResponse response = new ReturnListDevicesResponse();
		try {
			List<ClientCO> list = omniClientPullDAO.returnListDevices(clientVO);
			for (ClientCO l : list) {
				String last_loginString = format.format(l.getLast_login());
				l.setLast_loginString(last_loginString);
			}
			response.setList(list);
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);

		}
		return response;
	}

	@Override
	public UnregisterDeviceResponse unregisterDevice(ClientCO clientCO) throws Exception {
		UnregisterDeviceResponse response = new UnregisterDeviceResponse();
		try {
			omniClientPushDAO.deleteCombinationDeviceUsername(clientCO);
			response.setResponse("done");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ContactUsResponse contactUs(ContactUsRequest contactUsRequest) throws Exception {
		ContactUsResponse response = new ContactUsResponse();
		try {
//			PropertyVO propertyVO = new PropertyVO();
//			propertyVO.setProperty_name("companyEmail");
//			PropertyVO propertyVO2 = omniCommonPullDAO.returnProperty(propertyVO);

			String host = "localhost";
			Properties emailProperties = System.getProperties();

			// Setup mail server
//			properties.setProperty("mail.smtp.host", host);
//			properties.put("mail.smtp.host", "smtp.gmail.com");
//			properties.put("mail.smtp.socketFactory.port", "465");
//			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//			properties.put("mail.smtp.auth", "true");
//			properties.put("mail.smtp.port", "465");
			emailProperties = System.getProperties();
			emailProperties.put("mail.smtp.port", "587");
			emailProperties.put("mail.smtp.auth", "true");
			emailProperties.put("mail.smtp.starttls.enable", "true");

			// Get the default Session object.
//			Session session = Session.getDefaultInstance(properties);
//			Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication("alaaalhek95@gmail.com", "AlaaAlHek1995");
//				}
//			});
			List<PropertiesVO> list = omniCommonPullDAO.returnListProperties(null);
			String email = list.stream().filter(l -> l.getProperty_name().equals(companyEmail))
					.collect(Collectors.toList()).get(0).getProperty_value();
			String pass = list.stream().filter(l -> l.getProperty_name().equals(companyEmailPass))
					.collect(Collectors.toList()).get(0).getProperty_value();
			Session session = Session.getDefaultInstance(emailProperties, null);

			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(contactUsRequest.getEmail()));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

			// Set Subject: header field
			message.setSubject("TRIPTUK report issues");

			// Now set the actual message
			message.setText(contactUsRequest.getEmail() + "\n body: \n" + contactUsRequest.getMsg());

			// Send message
			String emailHost = "smtp.gmail.com";
			String fromUser = email;
			String fromUserEmailPassword = pass;
			Transport transport = session.getTransport("smtp");
			transport.connect(emailHost, fromUser, fromUserEmailPassword);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

			msg = omniCommonBO.getNotification(contactUsRequest.getLanguage_id(), EMAIL_SENDED_CODE).get(0)
					.getNotification_desc();
			response.setResponse(msg);
		} catch (MessagingException mex) {
			mex.printStackTrace();
			response.setResponseError(ERROR);
		}
		return response;
	}

	
//	public void getNewTime(ReservationCO reservationCO, int beforeOrAfter, int maxTimeToReachDestination, int newTime) {
//		LocalTime localtime = LocalTime.parse(reservationCO.getTime().toString());
//
//		String newTimeStr;
//		String timeSlotStr;
//		Time t;
//		Time ts;
//		if (beforeOrAfter == before) {
//			newTimeStr = df.format(localtime.plusMinutes(newTime + 1)) + ":00";
//			t = Time.valueOf(newTimeStr);
//			timeSlotStr = df.format(localtime.parse(newTimeStr).plusMinutes(maxTimeToReachDestination)) + ":00";
//			ts = Time.valueOf(timeSlotStr);
//
//		} else {
//			newTimeStr = df.format(localtime.minusMinutes(newTime + 1)) + ":00";
//			t = Time.valueOf(newTimeStr);
//			timeSlotStr = df.format(localtime.parse(newTimeStr).plusMinutes(maxTimeToReachDestination + 1)) + ":00";
//			ts = Time.valueOf(timeSlotStr);
//
//		}
//		reservationCO.setTime(t);
//
//		reservationCO.setTime_slot(ts);
////		return reservationCO;
//	}

	/*
	 * @Override public ReturnRecurringReservationListResponse
	 * returnRecurringReservationList(ReservationCO reservationCO) throws Exception
	 * { ReturnRecurringReservationListResponse response = new
	 * ReturnRecurringReservationListResponse(); try {
	 * reservationCO.setOccurrence_type(occurrenceTypeRecurring); // if
	 * (reservationCO.getFilterDate() == null) { // reservationCO.setDate(new
	 * Date(System.currentTimeMillis())); // } else { //
	 * reservationCO.setDate(Date.valueOf(reservationCO.getFilterDate())); // } if
	 * (reservationCO.getFilterDate() != null) {
	 * reservationCO.setDate(Date.valueOf(reservationCO.getFilterDate())); }
	 * List<ReservationSC> list =
	 * omniCommonPullDAO.returnRecurringReservation(reservationCO); if
	 * (!list.isEmpty()) { //
	 * reservationCO.setOccurrence_type(occurrenceTypeRecurring); //
	 * List<ReservationSC> listRecurringReservation =
	 * omniClientPullDAO.returnRecurringReservation(reservationCO); for
	 * (ReservationSC l : list) { int i = 0; ReservationVO reservationVO = new
	 * ReservationVO(); reservationVO.setReservation_id(l.getReservation_id()); //
	 * reservationVO.setClient_id(r.); List<ReservationSC> listReservationList =
	 * omniClientPullDAO.returnReservationsList(reservationVO); for (ReservationSC
	 * rl : listReservationList) { if (rl.getReservation_list_status() == EXPIRED)
	 * i++; } if (listReservationList.size() == i && i != 0) {
	 * reservationVO.setReservation_status(EXPIRED);
	 * omniClientPushDAO.updateReservationStatus(reservationVO); } }
	 * response.setList(list); } else response.setResponse(noData); } catch
	 * (Exception e) { log.info(e.getMessage()); response.setResponseError(ERROR); }
	 * return response; }
	 */

	@Override
	public ReturnTlubtukReservationDetailsResponse returnTlubtukReservationDetails(
			ReservationDetailsVO reservationDetailsVO) throws Exception {
		ReturnTlubtukReservationDetailsResponse response = new ReturnTlubtukReservationDetailsResponse();
		try {
			List<ReservationDetailsVO> list = omniCommonPullDAO.returnTlubtukReservationDetails(reservationDetailsVO);
			response.setList(list);
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public InviteContactResponse inviteContact(InvitedContactCO invitedContactCO) throws Exception {

		InviteContactResponse response = new InviteContactResponse();
		try {
			List<PropertiesVO> listProperties = omniCommonPullDAO.returnListProperties(null);
			String user = listProperties.stream().filter(l -> l.getProperty_name().equals(userProp))
					.collect(Collectors.toList()).get(0).getProperty_value();
			String pass = listProperties.stream().filter(l -> l.getProperty_name().equals(passProp))
					.collect(Collectors.toList()).get(0).getProperty_value();
			String sid = listProperties.stream().filter(l -> l.getProperty_name().equals(sidProp))
					.collect(Collectors.toList()).get(0).getProperty_value();
			String url = listProperties.stream().filter(l -> l.getProperty_name().equals(urlProp))
					.collect(Collectors.toList()).get(0).getProperty_value();
			String sms = listProperties.stream().filter(l -> l.getProperty_name().equals(inviteSMS))
					.collect(Collectors.toList()).get(0).getProperty_value();
			String triptuk_link = listProperties.stream().filter(l -> l.getProperty_name().equals(triptukLink))
					.collect(Collectors.toList()).get(0).getProperty_value();
			sms = sms + "%0A" + triptuk_link;
			if (!invitedContactCO.getListOfInvitedContact().isEmpty()) {
				List<String> listOfContactToInvite = new ArrayList<String>(
						Arrays.asList(invitedContactCO.getListOfInvitedContact().split(",")));
//			List<String> listOfInvitedContact = new ArrayList<String>();
				InvitedContactVO invitedContactVO = new InvitedContactVO();
				invitedContactVO.setClient_id(invitedContactCO.getClient_id());
				invitedContactVO.setInvited_date(new java.util.Date(System.currentTimeMillis()));
//				ClientCO clientCO = new ClientCO();
//				clientCO.setClient_id(invitedContactCO.getClient_id());
//				ClientVO mobile_number = omniClientPullDAO.returnMobileNumberById(clientCO);
//				sms = mobile_number.getMobile_number() + " "+ sms;
				if (!listOfContactToInvite.isEmpty()) {
					for (String contact : listOfContactToInvite) {
						if (!contact.matches("(\\+961[\\s](01|1|04|4|05|5|06|6|07|7|08|8|09|9)[\\s]\\d{3}[\\s]\\d{3})")
								&& !contact.matches("((01|1|04|4|05|5|06|6|07|7|08|8|09|9)\\d{6})")
								&& !contact.matches("(\\+961(01|1|04|4|05|5|06|6|07|7|08|8|09|9)\\d{6})")
								&& !contact.matches("((01|1|04|4|05|5|06|6|07|7|08|8|09|9)[\\s]\\d{3}[\\s])\\d{3}")) {
							String invitedContact = contact;

							if (!contact.matches("(\\+961[\\s](3|03|70|71|76|78|79|81))[\\s]\\d{3}[\\s]\\d{3}")) {
								contact = checkingMobileNumberFormat(contact);
							}
							if (contact.matches("(\\+961[\\s](3|03|70|71|76|78|79|81))[\\s]\\d{3}[\\s]\\d{3}")) {
//							listOfInvitedContact.add(invitedContact);
								invitedContactVO.setInivited_mobile_number(contact);
								omniClientPushDAO.insertIntoInvitedContact(invitedContactVO);
								securityUtilsExt.sendSMS(contact, user, pass, sid, url, sms);
							}
						}
					}

				}
			} else {
				response.setErrorCode(errorEmptyListOfContacts);
				response.setErrorDesc(omniCommonBO.getError(invitedContactCO.getLanguage_id(), response.getErrorCode())
						.get(0).getError_desc());
			}
			response.setResponse("done");
		} catch (

		Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	public String checkingMobileNumberFormat(String contact) {

//		if (l.length() < 14) {
		String code = contact.substring(0, 4);
		if (code.startsWith("+961")) {
			String f = contact.substring(5, 7);
			if (f.startsWith("03")) {
				contact = contact.substring(5);
				contact = String.valueOf(contact).replaceFirst("^[+](\\d{3})(\\d{1})(\\d{3})(\\d{3})$", "+$1 $2 $3 $4");
			} else {
				if (f.startsWith("3")) {
					contact = String.valueOf(contact).replaceFirst("^[+](\\d{3})(\\d{1})(\\d{3})(\\d{3})$",
							"+$1 $2 $3 $4");
				} else {
					contact = String.valueOf(contact).replaceFirst("^[+](\\d{3})(\\d{2})(\\d{3})(\\d{3})$",
							"+$1 $2 $3 $4");
				}
			}
		} else {
//				String f = l.substring(0, 1);

			if (contact.startsWith("03")) {
				contact = contact.substring(1);
				contact = "+961 " + String.valueOf(contact).replaceFirst("^(\\d{1})(\\d{3})(\\d{3})$", "$1 $2 $3");
			} else {
				contact = "+961 " + String.valueOf(contact).replaceFirst("^(\\d{2})(\\d{3})(\\d{3})$", "$1 $2 $3");

			}

		}
		return contact;

	}

	@Override
	public returnListOfContactToInviteResponse returnListOfContactToInvite(InvitedContactCO invitedContactCO)
			throws Exception {
		returnListOfContactToInviteResponse response = new returnListOfContactToInviteResponse();
		try {
			if (!invitedContactCO.getListOfContact().isEmpty()) {
				List<String> listOfContact = new ArrayList<String>(
						Arrays.asList(invitedContactCO.listOfContact.split(",")));
				List<String> listOfInvitedContact = omniClientPullDAO.returnListOfInvitedContact(invitedContactCO);
//			listOfContact.removeIf(a -> listOfInvitedContact.contains(a));

				ClientCO clientCO = new ClientCO();
				Invited_contactSC invited_contactSC = new Invited_contactSC();
				List<Invited_contactSC> listToInvite = new ArrayList<Invited_contactSC>();
				if (!listOfContact.isEmpty()) {
					for (String contact : listOfContact) {
						if (contact != null) {
							if (!contact
									.matches("(\\+961[\\s](01|1|04|4|05|5|06|6|07|7|08|8|09|9)[\\s]\\d{3}[\\s]\\d{3})")
									&& !contact.matches("((01|1|04|4|05|5|06|6|07|7|08|8|09|9)\\d{6})")
									&& !contact.matches("(\\+961(01|1|04|4|05|5|06|6|07|7|08|8|09|9)\\d{6})")
									&& !contact
											.matches("((01|1|04|4|05|5|06|6|07|7|08|8|09|9)[\\s]\\d{3}[\\s])\\d{3}")) {
								String contactToInvite = contact;

								if (!contact.matches("(\\+961[\\s](3|03|70|71|76|78|79|81))[\\s]\\d{3}[\\s]\\d{3}")) {
									contact = checkingMobileNumberFormat(contact);
								}
								if (contact.matches("(\\+961[\\s](3|03|70|71|76|78|79|81))[\\s]\\d{3}[\\s]\\d{3}")) {
									clientCO.setMobile_number(contact);
									ClientVO contactHasAccount = omniCommonPullDAO
											.returnMobileNumberIfExist(clientCO);
									Invited_contactSC invited_contactSC2 = new Invited_contactSC();

									if (contactHasAccount == null) {
										invited_contactSC2.setMobile_number(contactToInvite);
										String s = contact;
										if (listOfInvitedContact.contains(contact)) {
											invited_contactSC2.setMobile_number_invitation_status(INVITED);
											int count = listOfInvitedContact.stream().filter(c -> c.equals(s))
													.collect(Collectors.toList()).size();
											invited_contactSC2.setCount(count);
											;

										} else {
											invited_contactSC2.setMobile_number_invitation_status(NOTINVITED);
										}
									} else {
										invited_contactSC2.setMobile_number(contactToInvite);
										invited_contactSC2.setMobile_number_invitation_status(HASACCOUNT);
									}
									listToInvite.add(invited_contactSC2);

								}
							}
						}
					}
				}
				response.setListOfContactToInvite(listToInvite);
			} else {
				response.setErrorCode(errorEmptyListOfContacts);
				response.setErrorDesc(omniCommonBO.getError(invitedContactCO.getLanguage_id(), response.getErrorCode())
						.get(0).getError_desc());
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ReturnListOfBrokersResponse returnListOfBrokers(ParamLovVO paramLovVO) throws Exception {
		ReturnListOfBrokersResponse response = new ReturnListOfBrokersResponse();
		try {
			List<ParamLovVO> listOfBrokers = omniClientPullDAO.returnListOfBrokers(paramLovVO);
			if (paramLovVO.getLanguage_id() == arabic) {
				for (ParamLovVO brocker : listOfBrokers) {
					brocker.setBroker_name(brocker.getBroker_ar_name());
				}
			}
			response.setList(listOfBrokers);
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ReturnListOfServicesResponse returnListOfServices(LovVO lovVO) throws Exception {
		ReturnListOfServicesResponse response = new ReturnListOfServicesResponse();
		try {

			List<ParamLovSC> listOfServices = omniClientPullDAO.returnListOfServices(lovVO);

			List<ParamLovSC> filterByParentIdList = listOfServices.stream().filter(a -> a.getParent_id() != null)
					.collect(Collectors.toList());

			List<Integer> paramIdslist = filterByParentIdList.stream().map(ParamLovSC::getParent_id).distinct()
					.collect(Collectors.toList());

			List<ParamLovSC> sendList = listOfServices.stream().filter(a -> a.getParent_id() == null)
					.collect(Collectors.toList());
			for (ParamLovSC lov : sendList) {
				if (paramIdslist.contains(lov.getParamlov_id())) {
					lov.setHasBrokers(HASBROKERS);
				}
			}

			response.setList(sendList);

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ConfirmBrokerReservationResponse confirmBrokerReservation(ReservationSC reservationSC) throws Exception {
		ConfirmBrokerReservationResponse response = new ConfirmBrokerReservationResponse();

		try {

			omniClientPushDAO.updateReservationListStatusToCanceledAndBrokerReservation(reservationSC);
			ReservationCO reservationCO = new ReservationCO();
			reservationCO.setService(reservationSC.getParamlov_id());
			reservationCO.setBroker_id(reservationSC.getBroker_id());
			reservationCO.setDate(reservationSC.getDate());
			reservationCO.setTime(reservationSC.getTime());
			reservationCO.setDateTimeOfCreateReservation(new java.util.Date());
			omniCommonPushDAO.insertReservation(reservationCO);
			omniCommonPushDAO.insertReservationList(reservationCO);
			response.setResponse("done");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public CreateNewUserResponse createNewUser(ClientVO clientVO) throws Exception {
		CreateNewUserResponse response = new CreateNewUserResponse();
		try {
			clientVO.setLast_login_client(new java.util.Date(System.currentTimeMillis()));
			omniClientPushDAO.addUser(clientVO);
			response.setResponse("done");
		} catch (Exception e) {
			log.info(e.getMessage());
			response .setResponseError(ERROR);
		}
		return response;
		
	}

}
//