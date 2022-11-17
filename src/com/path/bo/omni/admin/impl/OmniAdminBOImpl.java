package com.path.bo.omni.admin.impl;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import com.path.bo.omni.admin.OmniAdminBO;
import com.path.bo.omni.admin.UpdateFactureStatusResponse;
import com.path.bo.omni.common.OmniCommonBO;
import com.path.bo.omni.common.base.OmniBaseBO;
import com.path.dao.omni.admin.pull.OmniAdminPullDAO;
import com.path.dao.omni.admin.push.OmniAdminPushDAO;
import com.path.dao.omni.common.push.OmniCommonPushDAO;
import com.path.dbmaps.vo.AdminCO;
import com.path.dbmaps.vo.AdminVO;
import com.path.dbmaps.vo.Admin_deviceVO;
import com.path.dbmaps.vo.ClientCO;
import com.path.dbmaps.vo.ClientVO;
import com.path.dbmaps.vo.Clients_groupVO;
import com.path.dbmaps.vo.CompaniesCO;
import com.path.dbmaps.vo.CompaniesSC;
import com.path.dbmaps.vo.CompaniesVO;
import com.path.dbmaps.vo.DeviceKeysSC;
import com.path.dbmaps.vo.DeviceVO;
import com.path.dbmaps.vo.DriverCO;
import com.path.dbmaps.vo.DriverVO;
import com.path.dbmaps.vo.MachineCO;
import com.path.dbmaps.vo.MachineDriverSC;
import com.path.dbmaps.vo.MachineDriverVO;
import com.path.dbmaps.vo.MachineVO;
import com.path.dbmaps.vo.Machine_driverCO;
import com.path.dbmaps.vo.Machine_mntCO;
import com.path.dbmaps.vo.PropertiesSC;
import com.path.dbmaps.vo.PropertiesVO;
import com.path.dbmaps.vo.ReservationCO;
import com.path.dbmaps.vo.ReservationListVO;
import com.path.dbmaps.vo.ReservationSC;
import com.path.dbmaps.vo.User;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.SecurityUtilsExt;
import com.path.ws.omni.vo.common.AddAdminResponse;
import com.path.ws.omni.vo.common.AddCompanyResponse;
import com.path.ws.omni.vo.common.AddDriverResponse;
import com.path.ws.omni.vo.common.AddMachineDriverResponse;
import com.path.ws.omni.vo.common.AddMachineResponse;
import com.path.ws.omni.vo.common.AddMonthlyClientResponse;
import com.path.ws.omni.vo.common.AuthenticateAdminResponse;
import com.path.ws.omni.vo.common.CreateDriverResponse;
import com.path.ws.omni.vo.common.CreateGroupResponse;
import com.path.ws.omni.vo.common.CreateRecurringReservationAdminResponse;
import com.path.ws.omni.vo.common.KillDriverResponse;
import com.path.ws.omni.vo.common.LoginAdminResponse;
import com.path.ws.omni.vo.common.LogoutResponse;
import com.path.ws.omni.vo.common.Notification;
import com.path.ws.omni.vo.common.ReturnAllDriversResponse;
import com.path.ws.omni.vo.common.ReturnCompaniesListResponse;
import com.path.ws.omni.vo.common.ReturnListOfFacturesResponse;
import com.path.ws.omni.vo.common.ReturnListOfGroupsResponse;
import com.path.ws.omni.vo.common.ReturnListOfMachinesResponse;
import com.path.ws.omni.vo.common.ReturnListPropertiesResponse;
import com.path.ws.omni.vo.common.ReturnListReservationsAdminResponse;
import com.path.ws.omni.vo.common.ReturnMachineInfoResponse;
import com.path.ws.omni.vo.common.ReturnMaintenanceResponse;
import com.path.ws.omni.vo.common.UpdateDriverInfoResponse;
import com.path.ws.omni.vo.common.UpdateMachineInfoResponse;
import com.path.ws.omni.vo.common.UpdatePropertyRequest;
import com.path.ws.omni.vo.common.UpdatePropertyResponse;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

//@Monitor
public class OmniAdminBOImpl extends OmniBaseBO implements OmniAdminBO {

	protected OmniAdminPullDAO omniAdminPullDAO;
	protected OmniAdminPushDAO omniAdminPushDAO;
	protected OmniCommonBO omniCommonBO;
	protected OmniCommonPushDAO omniCommonPushDAO;

	public void setOmniAdminPullDAO(OmniAdminPullDAO omniAdminPullDAO) {
		this.omniAdminPullDAO = omniAdminPullDAO;
	}

	public void setOmniAdminPushDAO(OmniAdminPushDAO omniAdminPushDAO) {
		this.omniAdminPushDAO = omniAdminPushDAO;
	}

	public void setOmniCommonBO(OmniCommonBO omniCommonBO) {
		this.omniCommonBO = omniCommonBO;
	}

	public void setOmniCommonPushDAO(OmniCommonPushDAO omniCommonPushDAO) {
		this.omniCommonPushDAO = omniCommonPushDAO;
	}

	SecurityUtilsExt securityUtilsExt;
	Properties prop = new Properties();
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public AddDriverResponse addDriver(DriverVO driverVO) throws Exception {
		AddDriverResponse response = new AddDriverResponse();
		DriverVO driver = new DriverVO();
		try {// check if the driver is already exist
			String username = driverVO.getName().replaceAll(" ", "");
			driverVO.setUsername(username);

			driver = omniAdminPullDAO.returnDriver(driverVO);
			if (driver == null) { // driver not exist
				/*
				 * salt and hash the pass based on username, password and value
				 */
//						+ propertiesHashMap.get("hashPassword").getProperty_value();
//				String ar_nameEndoded = Base64.getEncoder().encodeToString(driverVO.getAr_name().getBytes("UTF-8"));
//				driverVO.setAr_name(ar_nameEndoded);

//				String pass = driverVO.getUsername();
				String pass = username;
				String salt = SecurityUtilsExt.getNextSalt();
				String hash = SecurityUtilsExt.hash(pass, salt);
				driverVO.setPassword(hash);
				driverVO.setSalt(salt);
				omniAdminPushDAO.insertDriver(driverVO);
				response.setResponse("driver added successfuly");
				response.setPassword(pass);
				response.setUsername(username);
			} else {
				response.setErrorCode(errorCodeAddDriver);
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
	public AddMachineResponse addMachine(MachineVO machineVO) throws Exception {
		AddMachineResponse response = new AddMachineResponse();
		MachineVO machine = new MachineVO();
		try {
			// check if machine exist in db
			machine = omniAdminPullDAO.returnMachineByPlateNumber(machineVO);
			if (machine == null) { // machine not exist
				omniAdminPushDAO.insertMachine(machineVO);
				response.setResponse("machine added successfuly");
			} else {
				response.setErrorCode(errorCodeAddMachine);
				response.setErrorDesc(omniCommonBO.getError(machineVO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());

			}

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ReturnMachineInfoResponse returnMachineInfo(MachineVO machineVO) throws Exception {
		ReturnMachineInfoResponse response = new ReturnMachineInfoResponse();
		MachineVO vo = new MachineVO();
		try {
			vo = omniAdminPullDAO.returnMachineInfo(machineVO);
			response.setPlate_number(vo.getPlate_number());
			response.setMachine_status(vo.getMachine_status());
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;

	}

	@Override
	public AddAdminResponse addAdmin(AdminVO adminVO) throws Exception {
		AddAdminResponse response = new AddAdminResponse();
		AdminVO admin = new AdminVO();
		try {// check if the driver is already exist
			admin = omniAdminPullDAO.returnAdmin(adminVO);
			if (admin == null) { // driver not exist
				/*
				 * get from the properties file the value needed for password
				 */
//				prop = PropertiesLoaderUtils.loadAllProperties("HashPassword.properties");
//				@SuppressWarnings("unused")
//				String value = prop.getProperty("value");
				/*
				 * salt and hash the pass based on username, password and value
				 */
				String username = adminVO.getName().replaceAll(" ", "");
				adminVO.setUsername(username);
				String pass = adminVO.getUsername();
				String salt = SecurityUtilsExt.getNextSalt();
				String hash = SecurityUtilsExt.hash(pass, salt);
				adminVO.setPassword(hash);
				adminVO.setSalt(salt);
				omniAdminPushDAO.insertAdmin(adminVO);
				response.setResponse("driver added successfuly");
				response.setPassword(pass);
				response.setUsername(username);
			} else {
				response.setErrorCode(errorCodeAddAdmin);
				response.setErrorDesc(omniCommonBO.getError(adminVO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());
			}

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public LoginAdminResponse loginAdmin(AdminCO adminCO) throws Exception {
		LoginAdminResponse response = new LoginAdminResponse();
		try {
			/*
			 * check if the username is correct or not
			 */
			AdminVO adminVO = omniAdminPullDAO.returnAdminUsername(adminCO);
			if (adminVO == null) { // incorrect username
				response.setErrorCode(errorCodeLoginDriverIncorrectUsername);
				response.setErrorDesc(omniCommonBO.getError(adminCO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());
			} else {
				/*
				 * validate the password then check if this the first time the driver login , if
				 * yes insert the combination device driver in db check if the device exist
				 */
				if (SecurityUtilsExt.validatePassword(adminCO.getPassword(), adminVO.getSalt(),
						adminVO.getPassword())) { // correct password

//					DeviceAdminVO deviceAdminVO = omniAdminPullDAO.returnDeviceAdmin(adminCO);
					User user = new User();
					user.setDevice_UUID(adminCO.getDevice_UUID());
					user.setDevice_name(adminCO.getDevice_name());
					user.setLanguage_id(adminCO.getLanguage_id());
					user.setDevice_manufacturer(adminCO.getDevice_manufacturer());

					DeviceVO deviceVO = omniCommonPullDAO.returnDevice(user);
					adminCO.setLast_login_admin(new java.util.Date(System.currentTimeMillis()));
					if (deviceVO == null) {
//						omniAdminPushDAO.insertDeviceAdmin(adminCO);
						omniCommonPushDAO.insertDevice(user);
					}
					/*
					 * check if the device exist in driver_device table if no insert the combination
					 * , if yes update the combination
					 * 
					 */
					Admin_deviceVO admin_deviceVO = omniAdminPullDAO.returnAdminUsernameComb(adminCO);
					if (admin_deviceVO == null) {
						omniAdminPushDAO.insertAdminDeviceCombination(adminCO);
					} else {
						/*
						 * check if the combination exist
						 * 
						 */
						Admin_deviceVO admin_deviceVO2 = omniAdminPullDAO.returnCombAdminDevice(adminCO);
						if (admin_deviceVO2 == null) { // if no , update the driverdevice combination
							adminCO.setAdmin_device_id(admin_deviceVO.getAdmin_device_id());
							omniAdminPushDAO.updateAdminDeviceCombination(adminCO);
						}

					}
					/*
					 * check if the combination exist if no , insert into driver_device
					 */
					/*
					 * update the status of driver put it "login"
					 */
					omniAdminPushDAO.updateLastLoginAdmin(adminCO);
					/*
					 * generate the token then add to the response
					 */
//					User user = new User(adminCO.getUsername(), adminCO.getDevice_UUID(),
//							adminCO.getFrontEndPublicKey());
					DeviceKeysSC admin = omniCommonBO.getTokenAndKeysClient(adminCO.getDevice_UUID());
					admin.setDevice_UUID(adminCO.getDevice_UUID());
					admin.setApp(2);
					omniCommonPushDAO.updateToken(admin);

//					adminKeysHashMap.put(user.getDevice_UUID(), client);
					response.setToken(admin.getToken());
					response.setResponse("success");
					response.setAdmin_id(adminVO.getAdmin_id());
				} else { // incorrect password
					response.setErrorCode(errorCodeIncorrectPass);
					response.setErrorDesc(omniCommonBO.getError(adminCO.getLanguage_id(), response.getErrorCode())
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
	public UpdateDriverInfoResponse updateDriverInfo(Machine_driverCO machine_driverCO) throws Exception {
		UpdateDriverInfoResponse response = new UpdateDriverInfoResponse();
		MachineDriverVO machineDriverVO = new MachineDriverVO();
		try {
			DriverVO driverVO = new DriverVO();
			driverVO.setUsername(machine_driverCO.getUsername());
			DriverVO driverVO2 = omniAdminPullDAO.returnDriver(driverVO);
			machine_driverCO.setDriver_id(driverVO2.getDriver_id());
			omniAdminPushDAO.updateDriverInfos(machine_driverCO);
			if (machine_driverCO.getMachine_driver_status() == machineDriverStatusprob) {

				/*
				 * check the reservation of this machine in date and time after the time to
				 * change the machine status , reserve another one
				 */
				machineDriverVO.setDriver_id(driverVO2.getDriver_id());
				int machine = getNewMachine(machineDriverVO);

				if (machine == 2) {
					response.setResponseCode(reserveNewMachine);
					response.setResponseDesc("reserve a new machine");
				} else if (machine == 1) {
					response.setResponseCode(noReservationToChange);
					response.setResponseDesc("no reservation to change");
				}
			}

			response.setResponse("driver updated successfully");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public UpdateMachineInfoResponse updateMachineInfo(MachineCO machineCO) throws Exception {
		UpdateMachineInfoResponse response = new UpdateMachineInfoResponse();
		MachineDriverVO machineDriverVO = new MachineDriverVO();
		Machine_driverCO machineDriverCO = new Machine_driverCO();
		MachineVO machineVO = new MachineVO();
		try {
			machineVO.setPlate_number(machineCO.getPlate_number());
			MachineVO machine = omniAdminPullDAO.returnMachineByPlateNumber(machineVO);
			machineCO.setMachine_id(machine.getMachine_id());

			if (machineCO.getMachine_status() != 0 && machineCO.getMachine_status() != 1
					&& machineCO.getMachine_status() != 2 && machineCO.getMachine_status() != 3) {
				response.setErrorCode(errorCodeUpdateMachineInfo);
				response.setErrorDesc(omniCommonBO.getError(machineCO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());
				return response;
			}
//			omniAdminPushDAO.updateMachineInfos(machineCO);
			omniAdminPushDAO.updateMachineStatus(machineCO);

			/*
			 * check if the status is available
			 */
			if (machineCO.getMachine_status() == MACHINE_AVAILABLE) {
				machineDriverCO.setMachine_driver_status(machineDriverStatusAvailable);
			}
			/*
			 * if the inMaintenance
			 */
			if (machineCO.getMachine_status() == MACHINE_INMAINTENANCE) {
				omniAdminPushDAO.insertMachineMnt(machineCO);
				machineDriverCO.setMachine_driver_status(machineDriverStatusprob);

				/*
				 * check the reservation of this machine in date and time after the time to
				 * change the machine status
				 */
				machineDriverVO.setMachine_id(machine.getMachine_id());
				getNewMachine(machineDriverVO);
			}
//			omniCommonPushDAO.updateMachineDriverStatus(machineDriverCO);

			response.setResponse("machine updated successfully");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	public int getNewMachine(MachineDriverVO machineDriverVO) throws DAOException, IOException {

		ReservationCO reservationCO = new ReservationCO();
		try {
			List<ReservationListVO> list = checkMachineDriverReservation(machineDriverVO);
			Notification notification = omniCommonBO.loadKeyAndUrl();
			/**
			 * the list is not null, the machine has reservations
			 */
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					/**
					 * update the status of reservation list, set it 6 (the machine driver is
					 * unavailable)
					 */
					reservationCO.setReservation_list_status(CHANGED);
					reservationCO.setReservation_list_id(list.get(i).getReservation_list_id());
					omniCommonPushDAO.updateReservationListStatus(reservationCO);

					/*
					 * get the details as locations and type of each reservation
					 */
					reservationCO = omniCommonPullDAO.returnReservationDetails(list.get(i));

					/*
					 * search an available machine and insert into temporary reservation
					 */
//					reservationCO.setTime_slot(list.get(i).getTime_slot());
//					reservationCO.setMachine_driver_id(list.get(i).getMachine_driver_id());

					/*
					 * we have an available machine so auto confirm the reservation and insert into
					 * the main tables
					 */
					reservationCO.setReservation_list_status(PENDING);
					reservationCO.setTime(list.get(i).getTime());
					reservationCO.setTime_slot(null);
					Machine_driverCO machine_driverCO = omniCommonPullDAO.returnNearestAvailableMachine(reservationCO);
					reservationCO.setMachine_driver_id(machine_driverCO.getMachine_driver_id());
					omniCommonPushDAO.insertReservation(reservationCO);
					omniCommonPushDAO.insertReservationList(reservationCO);

					List<DeviceVO> listDevices = omniCommonPullDAO.returnDeviceOfClient(reservationCO);
					msg = omniCommonBO.getNotification(reservationCO.getLanguage_id(), NEW_TUKTUK_CODE).get(0)
							.getNotification_desc();
					String title = "reserve new machine";
					for (DeviceVO d : listDevices) {
						try {
							SecurityUtilsExt.pushNotification(d.getDevice_token(), title, msg, notification,
									reservationCO.getDate(), reservationCO.getTime());

						} catch (Exception e) {
							log.info(e.getMessage());
						}

					}

				}
				return 2;
			} else {
				return 1;
			}

		} catch (Exception e) {
			log.info(e.getMessage());
			return 0;
		}
	}

	public List<ReservationListVO> checkMachineDriverReservation(MachineDriverVO machineDriverVO) {
		ReservationListVO reservationListVO = new ReservationListVO();
		MachineDriverSC machineDriverSC = new MachineDriverSC();
		try {
			long now = System.currentTimeMillis();
			Date today = new Date(now);
			Time time = new Time(now);
			machineDriverSC.setMachine_id(machineDriverVO.getMachine_id());
			machineDriverSC.setDriver_id(machineDriverVO.getDriver_id());
			machineDriverSC.setDate(today);
			machineDriverSC.setTime(time);
			List<ReservationListVO> list = omniAdminPullDAO.returnIfMachineDriverIsReserved(machineDriverSC);
			return list;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	@Override
	public AddMachineDriverResponse addMachineDriver(Machine_driverCO machine_driverCO) throws Exception {
		AddMachineDriverResponse response = new AddMachineDriverResponse();

		try {
//			if (machine_driverCO.getLongitude() == 0 && machine_driverCO.getLatitude() == 0) {
//				response.setErrorCode(errorAddMachineDriverLocation);
//				response.setErrorDesc(omniCommonBO.getError(machine_driverCO.getLanguage_id(), response.getErrorCode())
//						.get(0).getError_desc());
//				return response;
//			}
			DriverVO driverVO = new DriverVO();
			driverVO.setUsername(machine_driverCO.getUsername());

			DriverVO driver = omniAdminPullDAO.returnDriver(driverVO);
			if (driver == null) {
				response.setErrorCode(errorAddMachineDriverIncorrectUsername);
				response.setErrorDesc(omniCommonBO.getError(machine_driverCO.getLanguage_id(), response.getErrorCode())
						.get(0).getError_desc());
				return response;
			}
			MachineVO machineVO = new MachineVO();
			machineVO.setPlate_number(machine_driverCO.getPlate_number());
			MachineVO machine = omniAdminPullDAO.returnMachineByPlateNumber(machineVO);
			if (machine == null) {
				response.setErrorCode(errorAddMachineDriverIncorrectMachine);
				response.setErrorDesc(omniCommonBO.getError(machine_driverCO.getLanguage_id(), response.getErrorCode())
						.get(0).getError_desc());
				return response;
			}
			if (machine != null && driver != null) {
				MachineDriverVO machineDriverVO = new MachineDriverVO();
				machine_driverCO.setDriver_id(driver.getDriver_id());
				machine_driverCO.setMachine_id(machine.getMachine_id());
				machine_driverCO.setMachine_driver_status(machineDriverStatusAvailable);
				omniAdminPushDAO.insertMachineDriver(machine_driverCO);
				response.setResponse("machineDriver added successfully");
			}
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public AuthenticateAdminResponse authenticateAdmin(AdminCO adminCO) throws Exception {
		try {
			AuthenticateAdminResponse response = new AuthenticateAdminResponse();
			AdminCO adminCO2 = omniAdminPullDAO.returnCombinationUsernameDeviceAdmin(adminCO);

			if (adminCO2 != null && adminCO2.getAdmin_status() == 0) {
//				User user = new User(adminCO2.getUsername(), adminCO.getDevice_UUID(), adminCO.getFrontEndPublicKey());
				DeviceKeysSC deviceKeysSC = omniCommonBO.getTokenAndKeysClient(adminCO.getDevice_UUID());

				deviceKeysSC.setDevice_UUID(adminCO.getDevice_UUID());
				omniCommonPushDAO.updateToken(deviceKeysSC);

				response.setToken(deviceKeysSC.getToken());
				response.setResponse("success");

			} else if (adminCO2 != null && adminCO2.getAdmin_status() == clientStatusLogout) {
				response.setErrorCode(errorCodeLogin);
				response.setErrorDesc(omniCommonBO.getError(adminCO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());

			} else {
				response.setErrorCode(errorCodeAuthenticate);
				response.setErrorDesc(omniCommonBO.getError(adminCO.getLanguage_id(), response.getErrorCode()).get(0)
						.getError_desc());
			}

			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	@Override
	public UpdatePropertyResponse updateProperty(UpdatePropertyRequest updatePropertyRequest) throws Exception {
		UpdatePropertyResponse response = new UpdatePropertyResponse();

		try {

//			JSONObject newJsonObject = new org.json.JSONObject(updatePropertyRequest.getListProperties());

//			String prop = updatePropertyRequest.getListProperties().replace("\"", "").replace("{", "").replace("}", "");
			String prop = updatePropertyRequest.getListProperties().replace("{", "").replace("}", "");

//			ObjectMapper oMapper = new ObjectMapper();
//			Map<String, Object> propertyMap = oMapper.convertValue(newJsonObject, Map.class);

//			Map<String, String> propertyMap = Arrays.stream(prop.split(",")).map(s -> s.split(":"))
//					.collect(Collectors.toMap(s -> s[0], s -> s[1]));
			Map<String, String> propertyMap = Arrays.stream(prop.split(",")).map(s -> s.split(":\""))
					.collect(Collectors.toMap(s -> s[0], s -> s[1]));

			Iterator iterator = propertyMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry me2 = (Map.Entry) iterator.next();

				if (me2.getValue() != "0") {
					PropertiesVO propertyVO = new PropertiesVO();
					propertyVO.setProperty_name(me2.getKey().toString().replace("\"", ""));
					propertyVO.setProperty_value(propertyMap.get(me2.getKey()).toString().replace("\"", ""));
					omniAdminPushDAO.updateProperty(propertyVO);
				}
			}
			response.setResponse("done");
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnListPropertiesResponse returnListProperties() {
		ReturnListPropertiesResponse response = new ReturnListPropertiesResponse();
		HashMap<String, String> listOfProperties = new HashMap<String, String>();
		try {
			List<PropertiesSC> list = omniAdminPullDAO.returnListPropertiesWithCategories(null);
//			for (PropertyVO l : list) {
//				listOfProperties.put(l.getProperty_name(), l.getProperty_value());
//			}
//			propertiesHashMap.clear();
			response.setListProperties(list);
//			response.setListOfProperties(listOfProperties);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public LogoutResponse logoutAdmin(AdminCO adminCO) throws Exception {
		LogoutResponse response = new LogoutResponse();
		try {
			adminCO.setAdmin_status(clientStatusLogout);
			omniAdminPushDAO.updateAdminStatus(adminCO);
			response.setResponse("logout successfuly");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ReturnListReservationsAdminResponse returnListReservationsAdmin(ReservationSC reservationSC)
			throws Exception {
		ReturnListReservationsAdminResponse response = new ReturnListReservationsAdminResponse();
		try {
			if (reservationSC.getDate() == null) {
				reservationSC.setDate(new Date(System.currentTimeMillis()));
			}
			if (reservationSC.getDate_from() == null && reservationSC.getDate() == null) {
				reservationSC.setDate_from(new Date(System.currentTimeMillis()));

			}
			if (reservationSC.getDate_to() == null && reservationSC.getDate() == null) {
				reservationSC.setDate_to(new Date(System.currentTimeMillis()));
			}
			if (reservationSC.getDate_from() == null && reservationSC.getDate() != null) {
				reservationSC.setDate_from(reservationSC.getDate());
			}
			if (reservationSC.getDate_to() == null && reservationSC.getDate() != null) {
				reservationSC.setDate_to(reservationSC.getDate());
			}
			List<ReservationSC> listNONFinish = omniAdminPullDAO.returnListReservationsDiffFinishAdmin(reservationSC);
			List<ReservationSC> returnListReservationsFinishAdmin = omniAdminPullDAO
					.returnListReservationsFinishAdmin(reservationSC);
			listNONFinish.addAll(returnListReservationsFinishAdmin);
			int totalCost = 0;
			int totalDebit = 0;
			for (ReservationSC l : returnListReservationsFinishAdmin) {
				if (l.getCost() >= 0) {
					if (l.getTotalCostByDriver() == 0) {
						totalCost = (int) (totalCost + l.getCost());
					} else {
						totalCost = (int) (totalCost + l.getTotalCostByDriver());
					}
				} else {
					if (l.getTotalCostByDriver() == 0) {
						totalDebit = (int) (totalDebit + l.getCost());
					} else {
						totalDebit = (int) (totalDebit + l.getTotalCostByDriver());
					}
				}
			}
			response.setTotalCost(totalCost);
			response.setList(listNONFinish);
			response.setTotalDebit(totalDebit);
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ReturnMaintenanceResponse returnMaintenance(Machine_mntCO machine_mntCO) throws Exception {
		ReturnMaintenanceResponse response = new ReturnMaintenanceResponse();
		try {
			if (machine_mntCO.getDate() == null) {
				machine_mntCO.setDate(new Date(System.currentTimeMillis()));
			}
			List<Machine_mntCO> list = omniAdminPullDAO.returnMaintenance(machine_mntCO);
			response.setList(list);
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public AddCompanyResponse addCompany(CompaniesCO companiesCO) throws Exception {
		AddCompanyResponse response = new AddCompanyResponse();
		try {
			CompaniesVO companiesVO = omniAdminPullDAO.returnCompany(companiesCO);
			if (companiesVO == null) {
				omniAdminPushDAO.insertCompany(companiesCO);
			} else {
				companiesCO.setCompany_id(companiesVO.getCompany_id());
			}
			addListOfMobileNumber(companiesCO);
//			File file = new File("E:\\test.xlsx");
//			byte[] bytes = org.apache.commons.codec.binary.Base64.decodeBase64(companiesCO.getFile());
//			FileUtils.writeByteArrayToFile(file, bytes);
//			FileInputStream file1 = new FileInputStream(new File("E:\\test.xlsx"));
//
//			// Create Workbook instance holding reference to .xlsx file
//			XSSFWorkbook workbook = new XSSFWorkbook(file);
//
//			// Get first/desired sheet from the workbook
//			XSSFSheet sheet = workbook.getSheetAt(0);
//
//			// Iterate through each rows one by one
//			Iterator<Row> rowIterator = sheet.iterator();
//			while (rowIterator.hasNext()) {
//				Row row = rowIterator.next();
//				// For each row, iterate through all the columns
//				Iterator<Cell> cellIterator = row.cellIterator();
//
//				while (cellIterator.hasNext()) {
//					Cell cell = cellIterator.next();
//					// Check the cell type and format accordingly
//					switch (cell.getCellType()) {
//					case Cell.CELL_TYPE_NUMERIC:
//						System.out.print(cell.getNumericCellValue() + "\t");
//						break;
//					case Cell.CELL_TYPE_STRING:
//						System.out.print(cell.getStringCellValue() + "\t");
////	                        omniClientPushDAO.insertUsernameMobileNumber(clientCO);
//						ClientCO clientCO = new ClientCO();
//						clientCO.setMobile_number(cell.getStringCellValue());
//						ClientVO clientVO = omniCommonPullDAO.returnMobileNumberIfExist(clientCO);
//						CompaniesSC companiesSC = new CompaniesSC();
//
//						companiesSC.setMobile_number(cell.getStringCellValue());
//						companiesSC.setCompany_id(companiesCO.getCompany_id());
//						if (clientVO == null) {
//
//							omniAdminPushDAO.insertMobileNumberComapany(companiesSC);
//						} else {
//							omniAdminPushDAO.updateMobileNumberCompany(companiesSC);
//						}
//						break;
//					}
//				}
//				System.out.println("");
//			}
			response.setResponse("done");
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	public void addListOfMobileNumber(CompaniesCO companiesCO) throws DAOException {
		List<String> myList = new ArrayList<String>(Arrays.asList(companiesCO.getListOfMobileNumber().split(",")));
		ClientCO clientCO = new ClientCO();
		CompaniesSC companiesSC = new CompaniesSC();
		companiesSC.setCompany_id(companiesCO.getCompany_id());
		for (String l : myList) {
			clientCO.setMobile_number(l);
			companiesSC.setMobile_number(l);
			ClientVO clientVO2 = omniCommonPullDAO.returnMobileNumberIfExist(clientCO);
			if (clientVO2 != null) {
				omniAdminPushDAO.updateMobileNumberCompany(companiesSC);
			} else {
				companiesSC.setUsername(companiesCO.getCompany_name());
				omniAdminPushDAO.insertMobileNumberCompany(companiesSC);
			}
		}
	}

	@Override
	public ReturnCompaniesListResponse returnCompaniesList() throws Exception {
		ReturnCompaniesListResponse response = new ReturnCompaniesListResponse();
		CompaniesCO co = new CompaniesCO();
		try {
			List<CompaniesVO> list = omniAdminPullDAO.returnCompaniesList(co);
			response.setList(list);
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ReturnListOfFacturesResponse returnListOfFactures(ReservationSC reservationSC) throws Exception {
		ReturnListOfFacturesResponse response = new ReturnListOfFacturesResponse();
		try {
			reservationSC.setPaid(factureNotPaid);
			List<ReservationSC> listOfFactures = omniAdminPullDAO.returnListOfFactures(reservationSC);
			response.setListOfFactureOfReservations(listOfFactures);
			;
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ReturnAllDriversResponse returnAllDrivers(ReservationCO reservationCO) throws Exception {
		ReturnAllDriversResponse response = new ReturnAllDriversResponse();
		try {
			reservationCO.setDate(new Date(System.currentTimeMillis()));
			reservationCO.setTime(new Time(System.currentTimeMillis()));
			List<Machine_driverCO> listAllDrivers = omniCommonPullDAO.returnAllDriver(reservationCO);
			if (!listAllDrivers.isEmpty()) {
				response.setListAllDrivers(listAllDrivers);
			} else {
				response.setResponse(noData);
			}

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public KillDriverResponse killDriver(DriverCO driverCO) throws Exception {
		KillDriverResponse response = new KillDriverResponse();

		try {
			driverCO.setDriver_availability(driver_availability_off);
			omniAdminPushDAO.updateDriverAvailabilty(driverCO);
			response.setResponse("done");
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public ReturnListOfMachinesResponse returnListOfMachines(MachineVO machineVO) throws Exception {
		ReturnListOfMachinesResponse response = new ReturnListOfMachinesResponse();

		try {
			List<MachineVO> listOfMachines = omniAdminPullDAO.returnListOfMachines(machineVO);
			response.setListOfMachines(listOfMachines);
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;

	}

	@Override
	public CreateDriverResponse createDriver(MachineDriverSC machineDriverSC) throws Exception {
		CreateDriverResponse response = new CreateDriverResponse();
		try {
			if (machineDriverSC.getCreate_update() == create_driver) {
				DriverVO driverVO = new DriverVO(machineDriverSC.getName(), machineDriverSC.getAddress(),
						machineDriverSC.getMobile_number(), machineDriverSC.getType(),
						machineDriverSC.getDate_of_birth(), machineDriverSC.getDriver_status(),
						machineDriverSC.getProfile(), machineDriverSC.getPic_of_id(), machineDriverSC.getAr_name(),
						machineDriverSC.getOn_off());
				AddDriverResponse driver = addDriver(driverVO);
				if (driver.getErrorCode() != null) {
					response.setErrorCode(driver.getErrorCode());
					response.setErrorDesc(driver.getErrorDesc());
					return response;
				}
				Machine_driverCO machine_driverCO = new Machine_driverCO();
				machine_driverCO.setDriver_id(driverVO.getDriver_id());
				machine_driverCO.setMachine_id(machineDriverSC.getMachine_id());
				machine_driverCO.setLatitude(machineDriverSC.getLatitude());
				machine_driverCO.setLongitude(machineDriverSC.getLongitude());

				omniAdminPushDAO.insertMachineDriver(machine_driverCO);
				machineDriverSC.setMachine_driver_id(machine_driverCO.getMachine_driver_id());
				omniAdminPushDAO.insertWorkingHours(machineDriverSC);
			} else if (machineDriverSC.getCreate_update() == update_driver) {
				omniAdminPushDAO.updateMachineDriver(machineDriverSC);
				omniAdminPushDAO.updateDriverWorkingHours(machineDriverSC);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			response.setResponseError(ERROR);
		}
		return response;
	}

	@Override
	public CreateRecurringReservationAdminResponse CreateRecurringReservationAdmin(ReservationCO reservationCO)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateFactureStatusResponse updateFactureStatus(ReservationCO reservationCO) throws Exception {
		UpdateFactureStatusResponse response = new UpdateFactureStatusResponse();
		try {
			omniAdminPushDAO.updateFactureReceipt(reservationCO);
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
		return response;
	}

	
// Redandant Service	
//	@Override
//	public CreateRecurringReservationAdminResponse CreateRecurringReservationAdmin(ReservationCO reservationCO)
//			throws Exception {
//		
//		return null;
//	}

}
