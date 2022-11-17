package com.path.ws.omni.rest.services.common.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.path.bo.omni.admin.OmniAdminBO;
import com.path.bo.omni.client.OmniClientBO;
import com.path.bo.omni.common.ChannelLoginBO;
import com.path.bo.omni.common.OmniCommonBO;
import com.path.bo.omni.common.pull.OmniCommonPullBO;
import com.path.bo.omni.driver.OmniDriverBO;
import com.path.dbmaps.vo.AdminCO;
import com.path.dbmaps.vo.AdminVO;
import com.path.dbmaps.vo.ClientCO;
import com.path.dbmaps.vo.ClientVO;
import com.path.dbmaps.vo.Clients_groupVO;
import com.path.dbmaps.vo.CompaniesCO;
import com.path.dbmaps.vo.DeviceSC;
import com.path.dbmaps.vo.DriverCO;
import com.path.dbmaps.vo.DriverVO;
import com.path.dbmaps.vo.Driver_deviceCO;
import com.path.dbmaps.vo.InvitedContactCO;
import com.path.dbmaps.vo.LovVO;
import com.path.dbmaps.vo.MachineCO;
import com.path.dbmaps.vo.MachineDriverSC;
import com.path.dbmaps.vo.MachineMntVO;
import com.path.dbmaps.vo.MachineVO;
import com.path.dbmaps.vo.Machine_driverCO;
import com.path.dbmaps.vo.Machine_mntCO;
import com.path.dbmaps.vo.PropertiesCO;
import com.path.dbmaps.vo.RateVO;
import com.path.dbmaps.vo.ReservationCO;
import com.path.dbmaps.vo.ReservationDetailsCO;
import com.path.dbmaps.vo.ReservationDetailsVO;
import com.path.dbmaps.vo.ReservationListVO;
import com.path.dbmaps.vo.ReservationSC;
import com.path.dbmaps.vo.ReservationVO;
import com.path.dbmaps.vo.StoreVO;
import com.path.dbmaps.vo.TemporaryReservationVO;
import com.path.dbmaps.vo.User;
import com.path.dbmaps.vo.WorkingHoursVO;
import com.path.dbmaps.vo.ParamLovVO;
import com.path.lib.common.base.OmniBaseServices;
import com.path.lib.common.util.PathPropertyUtil;
import com.path.vo.omni.oadm.omniuser.OmniUserSC;
import com.path.ws.omni.rest.client.common.OmniCommonServices;
import com.path.ws.omni.vo.common.*;

//import net.anotheria.moskito.aop.annotation.Monitor;

//@Monitor
//OmniCommonWebServices.xml
public class OmniCommonServicesImpl extends OmniBaseServices implements OmniCommonServices {

	private ChannelLoginBO channelLoginBO;
	private OmniCommonBO omniCommonBO;
	private OmniDriverBO omniDriverBO;
	private OmniClientBO omniClientBO;
	private OmniAdminBO omniAdminBO;
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public void setOmniCommonBO(OmniCommonBO omniCommonBO) {
		this.omniCommonBO = omniCommonBO;
	}

	public void setOmniCommonPullBO(OmniCommonPullBO omniCommonPullBO) {
	}

	public void setChannelLoginBO(ChannelLoginBO channelLoginBO) {
		this.channelLoginBO = channelLoginBO;
	}

	public void setOmniDriverBO(OmniDriverBO omniDriverBO) {
		this.omniDriverBO = omniDriverBO;
	}

	public void setOmniClientBO(OmniClientBO omniClientBO) {
		this.omniClientBO = omniClientBO;
	}

	public void setOmniAdminBO(OmniAdminBO omniAdminBO) {
		this.omniAdminBO = omniAdminBO;
	}

	public boolean authenticateUser_service(ChannelLoginRequest request) {

		OmniUserSC omniUserSC = new OmniUserSC();
		boolean response = false;
		try {
			PathPropertyUtil.copyProperties(request, omniUserSC);
			response = channelLoginBO.authenticateUser(request);

			System.out.println(response);

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
		return response;
	}

	/**
	 * handShake service for exchanging the keys
	 */
//done
	public HandShakeResponse handShake_service(HandShakeRequest handShakeRequest) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		java.util.Date d = new java.util.Date();
		format.format(d);
		try {
			HandShakeResponse response = omniCommonBO.handShake(handShakeRequest);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	/**
	 * authenticate service that check if the combination of sim serial and device
	 * serial exist if not exist put in hashMap of frontEndPublicKey key , and
	 * values of sim id device id and backEnd keys
	 */
	// done
	public AuthenticateClientResponse authenticateClient_service(AuthenticateClientRequest authenticateRequest) {

		AuthenticateClientResponse response;

		try {
			ClientCO clientCO = new ClientCO();
			PathPropertyUtil.copyProperties(authenticateRequest, clientCO);
			response = omniClientBO.authenticateClient(clientCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	/**
	 * 
	 */
	@Override
	public GenerateOTPResponse generateOTP_service(GenerateOTPRequest generateOTPRequest) {
		GenerateOTPResponse response;
//		SimVO simVO = new SimVO();
		DeviceSC deviceSC = new DeviceSC();
		try {

			PathPropertyUtil.copyProperties(generateOTPRequest, deviceSC);
			response = omniCommonBO.generateOTP(deviceSC);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

//	@Override
//	public GenerateOTPClientResponse generateOTPClient_service(GenerateOTPClientRequest generateOTPClientRequest) {
//		GenerateOTPClientResponse response;
//		DeviceSC deviceSC = new DeviceSC();
//		try {
//
//			PathPropertyUtil.copyProperties(generateOTPClientRequest, deviceSC);
//			response = omniClientBO.generateOTPClient(deviceSC);
//			return response;
//		} catch (Exception e) {
//			log.info(e.getMessage());
//			return null;
//		}
//	}

	@Override // done
	public ValidateOTPResponse validateOTP_service(ValidateOTPRequest validateOTPRequest) {
		ValidateOTPResponse response;
		DeviceSC deviceSC = new DeviceSC();
		try {
			PathPropertyUtil.copyProperties(validateOTPRequest, deviceSC);
			response = omniCommonBO.validateOTP(deviceSC);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

//	@Override
//	public ValidateOTPClientResponse validateOTPClient_service(ValidateOTPClientRequest validateOTPClientRequest) {
//		ValidateOTPClientResponse response;
//		DeviceSC deviceSC = new  DeviceSC();
//		try {
//			PathPropertyUtil.copyProperties(validateOTPClientRequest, deviceSC);
//			response = omniClientBO.validateOTPClient(deviceSC);
//			return response;
//		} catch (Exception e) {
//			log.info(e.getMessage());
//			return null;
//		}
//	}

	@Override
	public CheckUsernameResponse checkUsername_service(CheckUsernameRequest checkUsernameRequest) {
		CheckUsernameResponse response = new CheckUsernameResponse();
		ClientVO clientVO = new ClientVO();
		try {
			log.info("debut service " + format.format(new java.util.Date()));

			PathPropertyUtil.copyProperties(checkUsernameRequest, clientVO);
			response = omniClientBO.checkUsername(clientVO);
			log.info("fin service" + format.format(new java.util.Date()));

			return response;

		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public RegisterClientResponse registerClient_service(RegisterClientRequest registerClientRequest) {

		RegisterClientResponse response;
		ClientCO clientCO = new ClientCO();
		try {
			PathPropertyUtil.copyProperties(registerClientRequest, clientCO);
			response = omniClientBO.registerClient(clientCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	@Override
	public SaveTokenResponse saveToken_service(SaveTokenRequest saveTokenRequest) {
		SaveTokenResponse response = new SaveTokenResponse();
//		ClientCO clientCO = new ClientCO();
		User user = new User();
		try {
			PathPropertyUtil.copyProperties(saveTokenRequest, user);
//				response = omniClientBO.saveClientToken(user);
			response = omniCommonBO.saveToken(user);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	/*
	 * @Override // pending public List<MachineVO>
	 * availableMachines_service(ClientRequest request) { List<MachineVO>
	 * machineList = new ArrayList<MachineVO>(); List<MachineVO> availableMachine =
	 * new ArrayList<MachineVO>(); try { Date date = request.getDate();
	 * System.out.println(date); // double latitude =
	 * request.getLocation().getLatitude(); // double longitude =
	 * request.getLocation().getLongitude(); machineList =
	 * omniCommonBO.returnListOfMachine(request); return machineList; } catch
	 * (Exception e) { System.out.println(e.getMessage()); return null; } }
	 */

	@Override // pending
	public ConfirmReservationResponse ConfirmReservation_service(ConfirmReservationRequest createReservationRequest) {
		ConfirmReservationResponse response = null;
		ReservationCO reservationCO = new ReservationCO();
		try {
			PathPropertyUtil.copyProperties(createReservationRequest, reservationCO);
			response = omniCommonBO.confirmReservation(reservationCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	@Override
	public ReturnListOfMachinesNearestMostFarResponse returnListOfMachinesNearestMostFar_service(
			ReturnListOfMachinesNearestMostFarRequest listOfMachinesRequest) {
		ReturnListOfMachinesNearestMostFarResponse response;
		ReservationCO reservationCO = new ReservationCO();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		try {
			log.info("debut service" + format.format(new java.util.Date()));
			PathPropertyUtil.copyProperties(listOfMachinesRequest, reservationCO);
//			response = omniClientBO.returnListOfMachinesNearestMostFar(reservationCO);
			response = omniCommonBO.returnListOfMachinesNearestMostFar(reservationCO);
			log.info("fin service" + format.format(new java.util.Date()));
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override // pending
	public CancelReservationResponse cancelReservation_service(CancelReservationRequest cancelReservationRequest) {
		CancelReservationResponse response;
		ReservationCO reservationCO = new ReservationCO();
		try {
			PathPropertyUtil.copyProperties(cancelReservationRequest, reservationCO);
			response = omniClientBO.cancelReservation(reservationCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	@Override // done
	public RatingTripResponse ratingTrip_service(RateRequest rateRequest) {
		RatingTripResponse response;
		RateVO rateVO = new RateVO();
		try {

			PathPropertyUtil.copyProperties(rateRequest, rateVO);
			response = omniCommonBO.ratingTrip(rateVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	@Override
	public LogoutResponse logout_service(LogoutRequest logoutRequest) {
		LogoutResponse response = new LogoutResponse();
		ClientCO clientCO = new ClientCO();
		try {
			PathPropertyUtil.copyProperties(logoutRequest, clientCO);
			response = omniClientBO.logout(clientCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	@Override
	public LoginClientResponse loginClient_service(LoginClientRequest loginClientRequest) {
		LoginClientResponse response = new LoginClientResponse();
		ClientCO clientCO = new ClientCO();

		try {
			PathPropertyUtil.copyProperties(loginClientRequest, clientCO);
			response = omniClientBO.loginClient(clientCO);
			return response;

		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public RememberDeviceResponse rememberDevice_service(RememberDeviceRequest rememberDeviceRequest) {
		RememberDeviceResponse response = new RememberDeviceResponse();
		ClientCO clientCO = new ClientCO();
		try {
			PathPropertyUtil.copyProperties(rememberDeviceRequest, clientCO);
			response = omniClientBO.rememberDevice(clientCO);

			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public AddDriverResponse addDriver_service(AddDriverRequest addDriverRequest) {
		AddDriverResponse response = new AddDriverResponse();
		DriverVO driverVO = new DriverVO();
		try {
			PathPropertyUtil.copyProperties(addDriverRequest, driverVO);
			response = omniAdminBO.addDriver(driverVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public AddMachineResponse addMachine_service(AddMachineRequest addMachineRequest) {
		AddMachineResponse response = new AddMachineResponse();
		MachineVO machineVO = new MachineVO();
		try {
			PathPropertyUtil.copyProperties(addMachineRequest, machineVO);
			response = omniAdminBO.addMachine(machineVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnDriverInfoResponse returnDriverInfo_service(ReturnDriverInfoRequest returnDriverInfoRequest) {
		ReturnDriverInfoResponse response = new ReturnDriverInfoResponse();
		DriverVO driverVO = new DriverVO();
		try {
			PathPropertyUtil.copyProperties(returnDriverInfoRequest, driverVO);
			response = omniDriverBO.returnDriverInfo(driverVO);
			return response;

		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public UpdateClientInfosResponse updateClientInfos_service(UpdateClientInfosRequest updateClientInfosRequest) {
		UpdateClientInfosResponse response = new UpdateClientInfosResponse();
		ClientVO clientVO = new ClientVO();
		try {
			PathPropertyUtil.copyProperties(updateClientInfosRequest, clientVO);
			response = omniClientBO.updateClientInfos(clientVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ChangePasswordResponse changePassword_service(ChangePasswordRequest changePasswordRequest) {
		ChangePasswordResponse response = new ChangePasswordResponse();
		ClientCO clientCO = new ClientCO();
		try {
			PathPropertyUtil.copyProperties(changePasswordRequest, clientCO);
			response = omniClientBO.changePassword(clientCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	@Override
	public ReturnMachineInfoResponse returnMachineInfo_service(ReturnMachineInfoRequest returnMachineInfoRequest) {
		ReturnMachineInfoResponse response = new ReturnMachineInfoResponse();
		MachineVO machineVO = new MachineVO();
		try {
			PathPropertyUtil.copyProperties(returnMachineInfoRequest, machineVO);
			response = omniAdminBO.returnMachineInfo(machineVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public UpdateMachineInfoResponse updateMachineInfo_service(UpdateMachineInfoRequest updateMachineInfoRequest) {
		UpdateMachineInfoResponse response = new UpdateMachineInfoResponse();
		MachineCO machineCO = new MachineCO();
		try {
			PathPropertyUtil.copyProperties(updateMachineInfoRequest, machineCO);
			response = omniAdminBO.updateMachineInfo(machineCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public UpdateDriverInfoResponse updateDriverInfo_service(UpdateDriverInfoRequest updateDriverInfoRequest) {
		UpdateDriverInfoResponse response = new UpdateDriverInfoResponse();
		Machine_driverCO machine_driverCO = new Machine_driverCO();
		try {
			PathPropertyUtil.copyProperties(updateDriverInfoRequest, machine_driverCO);
			response = omniAdminBO.updateDriverInfo(machine_driverCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnReservationsListResponse returnReservationsList_service(
			ReturnReservationsListRequest returnReservationsListRequest) {
		ReturnReservationsListResponse response = new ReturnReservationsListResponse();
		ReservationVO reservationVO = new ReservationVO();
		try {
			PathPropertyUtil.copyProperties(returnReservationsListRequest, reservationVO);
			response = omniClientBO.returnReservationsList(reservationVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnReservationDetailsResponse returnReservationDetails_service(
			ReturnReservationDetailsRequest returnReservationDetailsRequest) {
		ReturnReservationDetailsResponse response = new ReturnReservationDetailsResponse();
		ReservationVO reservationVO = new ReservationVO();
		try {
			PathPropertyUtil.copyProperties(returnReservationDetailsRequest, reservationVO);
			response = omniClientBO.returnReservationDetails(reservationVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	@Override
	public CreateRecurringReservationResponse createRecurringReservation_service(
			createRecurringReservationRequest createRecurringReservationRequest) {
		CreateRecurringReservationResponse response = new CreateRecurringReservationResponse();
		ReservationCO reservationCO = new ReservationCO();
		try {
			PathPropertyUtil.copyProperties(createRecurringReservationRequest, reservationCO);
			response = omniCommonBO.createRecurringReservation(reservationCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public CheckTempRecurringReservationResponse checkTempRecurringReservation_service(
			CheckTempRecurringReservationRequest checkTempRecurringReservationRequest) {
		CheckTempRecurringReservationResponse response = new CheckTempRecurringReservationResponse();
		TemporaryReservationVO temporaryReservationVO = new TemporaryReservationVO();
		try {
			PathPropertyUtil.copyProperties(checkTempRecurringReservationRequest, temporaryReservationVO);
			response = omniCommonBO.checkTempRecurringReservation(temporaryReservationVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	@Override
	public RejectReservationResponse rejectReservation_service(RejectReservationRequest rejectReservationRequest) {
		RejectReservationResponse response = new RejectReservationResponse();
		ReservationCO reservationCO = new ReservationCO();
		try {
			PathPropertyUtil.copyProperties(rejectReservationRequest, reservationCO);
			response = omniCommonBO.rejectReservation(reservationCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public UpdateDriverWorkingHoursResponse updateDriverWorkingHours_service(
			UpdateDriverWorkingHoursRequest driverWorkingHoursRequest) {
		UpdateDriverWorkingHoursResponse response = new UpdateDriverWorkingHoursResponse();
		WorkingHoursVO workingHoursVO = new WorkingHoursVO();
		try {
			PathPropertyUtil.copyProperties(driverWorkingHoursRequest, workingHoursVO);
			response = omniCommonBO.updateDriverWorkingHours(workingHoursVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public AddStoreResponse addStore_service(AddStoreRequest addStoreRequest) {
		AddStoreResponse response = new AddStoreResponse();
		StoreVO storeVO = new StoreVO();
		try {
			PathPropertyUtil.copyProperties(addStoreRequest, storeVO);
			response = omniCommonBO.addStore(storeVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnClientInfoResponse returnClientInfo_service(ReturnClientInfoRequest returnClientInfoRequest) {
		ReturnClientInfoResponse response;
		ClientVO clientVO = new ClientVO();
		try {
			PathPropertyUtil.copyProperties(returnClientInfoRequest, clientVO);
			response = omniClientBO.returnClientInfo(clientVO);
			return response;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public CreateReservationResponse createReservation_service(CreateReservationRequest createReservationRequest) {
		CreateReservationResponse response;
		ReservationCO reservationCO = new ReservationCO();
		try {
			PathPropertyUtil.copyProperties(createReservationRequest, reservationCO);
			response = omniCommonBO.createReservation(reservationCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

//	@Override
//	public GetListOfMachinesFromNearestToMostFarResponse GetListOfMachinesFromNearestToMostFar_service(
//			GetListOfMachinesFromNearestToMostFarRequest nearestToMostFarRequest) {
//		GetListOfMachinesFromNearestToMostFarResponse response;
//		ReservationCO reservationCO = new ReservationCO();
//		try {
//
//			PathPropertyUtil.copyProperties(nearestToMostFarRequest, reservationCO);
//			response = omniCommonBO.GetListOfMachinesFromNearestToMostFar(reservationCO);
//			return response;
//		} catch (Exception e) {
//			log.info(e.getMessage());
//			return null;
//		}
//	}

	@Override
	public AddDestinationResponse addDestination_service(AddDestinationRequest addDestinationRequest) {
		AddDestinationResponse response = new AddDestinationResponse();
		ReservationCO reservationCO = new ReservationCO();
		try {
			PathPropertyUtil.copyProperties(addDestinationRequest, reservationCO);
			response = omniClientBO.addDestination(reservationCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnListOfDestinationsResponse returnListOfDestinations_service(
			ReturnListOfDestinationsRequest returnListOfDestinationsRequest) {
		ReturnListOfDestinationsResponse response = new ReturnListOfDestinationsResponse();
		ReservationCO reservationCO = new ReservationCO();
		try {
			PathPropertyUtil.copyProperties(returnListOfDestinationsRequest, reservationCO);
			response = omniClientBO.returnListOfDestinations(reservationCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ChangeProfileResponse changeProfile_service(ChangeProfileRequest changeProfileRequest) {
		ChangeProfileResponse response = new ChangeProfileResponse();
		ClientVO clientVO = new ClientVO();
		try {
			PathPropertyUtil.copyProperties(changeProfileRequest, clientVO);
			response = omniClientBO.changeProfile(clientVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ForgetPasswordResponse forgetPasswordClient_service(ForgetPasswordRequest forgetPasswordRequest) {
		ForgetPasswordResponse response;
		ClientCO clientCO = new ClientCO();
		try {
			PathPropertyUtil.copyProperties(forgetPasswordRequest, clientCO);
			response = omniClientBO.forgetPasswordClient(clientCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ResetPasswordResponse resetPasswordClient_service(ResetPasswordRequest resetPasswordRequest) {
		ResetPasswordResponse response;
		ClientVO clientVO = new ClientVO();
		try {
			PathPropertyUtil.copyProperties(resetPasswordRequest, clientVO);
			response = omniClientBO.resetPasswordClient(clientVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnListDevicesResponse returnListDevices_service(ReturnListDevicesRequest returnListDevicesRequest) {
		ReturnListDevicesResponse response;
		ClientVO clientVO = new ClientVO();
		try {
			PathPropertyUtil.copyProperties(returnListDevicesRequest, clientVO);
			response = omniClientBO.returnListDevices(clientVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public UnregisterDeviceResponse unregitserDevice_service(UnregisterDeviceRequest unregisterDeviceRequest) {
		UnregisterDeviceResponse response;
		ClientCO clientCO = new ClientCO();
		try {
			PathPropertyUtil.copyProperties(unregisterDeviceRequest, clientCO);
			response = omniClientBO.unregisterDevice(clientCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}
//	@PersistenceContext
//	EntityManager em;
//
//	@Override
//	public List<DeviceVO> getDevicesTest() {
//		return em.createNamedQuery("asd", DeviceVO.class).getResultList();
//	}

	@Override
	public AuthenticateDriverResponse authenticateDriver_service(AuthenticateDriverRequest authenticateDriverRequest) {
		AuthenticateDriverResponse response;
		Driver_deviceCO driver_deviceCO = new Driver_deviceCO();
		try {
			PathPropertyUtil.copyProperties(authenticateDriverRequest, driver_deviceCO);
			response = omniDriverBO.authenticateDriver(driver_deviceCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	@Override
	public LoginDriverResponse loginDriver_service(LoginDriverRequest loginDriverRequest) {
		LoginDriverResponse response;
		DriverCO driverCO = new DriverCO();
		try {
			PathPropertyUtil.copyProperties(loginDriverRequest, driverCO);
			response = omniDriverBO.loginDriver(driverCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public LogoutDriverResponse logoutDriver_service(LogoutDriverRequest logoutDriverRequest) {
		LogoutDriverResponse response;
		DriverCO driverCO = new DriverCO();
		try {
			PathPropertyUtil.copyProperties(logoutDriverRequest, driverCO);
			response = omniDriverBO.logoutDriver(driverCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public UpdateTimeSlotResponse updateTimeSlot_service(UpdateTimeSlotRequest updateTimeSlotRequest) {
		UpdateTimeSlotResponse response;
		ReservationCO reservationCO = new ReservationCO();
		try {
			PathPropertyUtil.copyProperties(updateTimeSlotRequest, reservationCO);
			response = omniDriverBO.updateTimeSlot(reservationCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnListReservationsDriverResponse returnListReservationsDriver_service(
			ReturnListReservationsDriverRequest returnListReservationsDriverRequest) {
		ReturnListReservationsDriverResponse response;
		DriverCO driverCO = new DriverCO();
		try {
			PathPropertyUtil.copyProperties(returnListReservationsDriverRequest, driverCO);
			response = omniDriverBO.returnlistReservationsDriver(driverCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ChangePasswordDriverResponse changePasswordDriver_service(
			ChangePasswordDriverRequest changePasswordDriverRequest) {
		ChangePasswordDriverResponse response;
		DriverCO driverCO = new DriverCO();
		try {
			PathPropertyUtil.copyProperties(changePasswordDriverRequest, driverCO);
			response = omniDriverBO.changePasswordDriver(driverCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
//		return null;
	}

	@Override
	public ForgetPasswordResponse forgetPasswordDriver_service(ForgetPasswordRequest forgetPasswordRequest) {
		ForgetPasswordResponse response;
		DriverCO driverCO = new DriverCO();
		try {
			PathPropertyUtil.copyProperties(forgetPasswordRequest, driverCO);
			response = omniDriverBO.forgetPasswordDriver(driverCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ResetPasswordResponse resetPasswordDriver_service(ResetPasswordRequest resetPasswordDriverRequest) {
		ResetPasswordResponse response;
		DriverVO driverVO = new DriverVO();
		try {
			PathPropertyUtil.copyProperties(resetPasswordDriverRequest, driverVO);
			response = omniDriverBO.resetPasswordDriver(driverVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override // done
	public UpdateReservationStatusResponse updateReservationStatus_service(
			UpdateReservationStatusRequest reservationStatusRequest) {
		UpdateReservationStatusResponse response;
		try {
			DriverCO driverCO = new DriverCO();
			PathPropertyUtil.copyProperties(reservationStatusRequest, driverCO);
			response = omniDriverBO.updateReservationStatus(driverCO);
			return response;

		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	@Override
	public AddAdminResponse addAdmin_service(AddAdminRequest addAdminRequest) {
		AddAdminResponse response;
		AdminVO adminVO = new AdminVO();
		try {
			PathPropertyUtil.copyProperties(addAdminRequest, adminVO);
			response = omniAdminBO.addAdmin(adminVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public LoginAdminResponse loginAdmin_service(LoginAdminRequest loginAdminRequest) {
		LoginAdminResponse response;
		AdminCO adminCO = new AdminCO();
		try {
			PathPropertyUtil.copyProperties(loginAdminRequest, adminCO);
			response = omniAdminBO.loginAdmin(adminCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ChangeProfileDriverResponse changeProfileDriver_service(
			ChangeProfileDriverRequest changeProfileDriverRequest) {
		ChangeProfileDriverResponse response;
		DriverVO driverVO = new DriverVO();
		try {
			PathPropertyUtil.copyProperties(changeProfileDriverRequest, driverVO);
			response = omniDriverBO.changeProfileDriver(driverVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public AddMachineDriverResponse addMachineDriver_service(AddMachineDriverRequest addMachineDriverRequest) {
		AddMachineDriverResponse response;
		Machine_driverCO machine_driverCO = new Machine_driverCO();
		try {
			PathPropertyUtil.copyProperties(addMachineDriverRequest, machine_driverCO);
			response = omniAdminBO.addMachineDriver(machine_driverCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public String test_service() {
		try {
			File file = new File("test.txt");
			StringBuilder stringBuilder = new StringBuilder();

			boolean exists = file.exists();
			if (exists == true) {

				file.setExecutable(true);
				file.setReadable(true);
				file.setWritable(false);
				BufferedReader br = new BufferedReader(new FileReader(file));

				String st;
				while ((st = br.readLine()) != null) {
					System.out.println(st);
					stringBuilder.append(st);
				}
			}
			String str = stringBuilder.toString();
			return str;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public UpdateDriverLocationResponse updateDriverLocation_service(
			UpdateDriverLocationRequest updateDriverLocationRequest) {
		UpdateDriverLocationResponse response = new UpdateDriverLocationResponse();
		DriverCO driverCO = new DriverCO();
		try {
			PathPropertyUtil.copyProperties(updateDriverLocationRequest, driverCO);
			response = omniDriverBO.updateDriverLocation(driverCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnDriverLocationResponse returnDriverLocation_service(
			ReturnDriverLocationRequest returnDriverLocationRequest) {
		ReturnDriverLocationResponse response = new ReturnDriverLocationResponse();
		ReservationListVO reservationListVO = new ReservationListVO();
		try {
			PathPropertyUtil.copyProperties(returnDriverLocationRequest, reservationListVO);
			response = omniDriverBO.returnDriverLocation(reservationListVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public AuthenticateAdminResponse authenticateAdmin_service(AuthenticateAdminRequest authenticateAdminRequest) {
		AuthenticateAdminResponse response = new AuthenticateAdminResponse();
		AdminCO adminCO = new AdminCO();
		try {
			PathPropertyUtil.copyProperties(authenticateAdminRequest, adminCO);
			response = omniAdminBO.authenticateAdmin(adminCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ContactUsResponse contactUs_service(ContactUsRequest contactUsRequest) {
		ContactUsResponse response = new ContactUsResponse();
		try {
			response = omniClientBO.contactUs(contactUsRequest);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public UpdatePropertyResponse updateProperty_service(UpdatePropertyRequest propertyRequest) {
		UpdatePropertyResponse response = new UpdatePropertyResponse();
		new PropertiesCO();
		try {
//			PathPropertyUtil.copyProperties(propertyRequest, propertyCO);
			response = omniAdminBO.updateProperty(propertyRequest);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	@Override
	public ReturnListPropertiesResponse returnListProperties_service(
			ReturnListPropertiesRequest returnListPropertiesRequest) {
		ReturnListPropertiesResponse response = new ReturnListPropertiesResponse();
		new PropertiesCO();
		try {
//			PathPropertyUtil.copyProperties(returnListPropertiesRequest, propertyCO);
			response = omniAdminBO.returnListProperties();
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnNearestAvailableMachineResponse returnNearestAvailableMachine_service(
			ReturnNearestAvailableMachineRequest returnNearestAvailableMachineRequest) {
		ReturnNearestAvailableMachineResponse response = new ReturnNearestAvailableMachineResponse();
		ReservationCO reservationCO = new ReservationCO();
		try {
			PathPropertyUtil.copyProperties(returnNearestAvailableMachineRequest, reservationCO);
//			response = omniClientBO.returnNearestAvailableMachine(reservationCO);
			response = omniCommonBO.returnNearestAvailableMachine(reservationCO);

			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public LogoutResponse logoutAdmin_service(LogoutAdminRequest logoutAdminRequest) {
		LogoutResponse response = new LogoutResponse();
		AdminCO adminCO = new AdminCO();
		try {
			PathPropertyUtil.copyProperties(logoutAdminRequest, adminCO);
			response = omniAdminBO.logoutAdmin(adminCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnListReservationsAdminResponse returnListReservationsAdmin_service(
			ReturnListReservationsAdminRequest listReservationsAdminRequest) {
		ReturnListReservationsAdminResponse response = new ReturnListReservationsAdminResponse();
		ReservationSC reservationSC = new ReservationSC();
		try {
			PathPropertyUtil.copyProperties(listReservationsAdminRequest, reservationSC);
			response = omniAdminBO.returnListReservationsAdmin(reservationSC);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnRecurringReservationListResponse returnRecurringReservationList_service(
			ReturnRecurringReservationListRequest returnRecurringReservationListRequest) {
		ReturnRecurringReservationListResponse response = new ReturnRecurringReservationListResponse();
		ReservationCO reservationCO = new ReservationCO();
		try {
			PathPropertyUtil.copyProperties(returnRecurringReservationListRequest, reservationCO);
			response = omniCommonBO.returnRecurringReservationList(reservationCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public CancelRecurringReservationResponse cancelRecurringReservation_service(
			CancelRecurringReservationRequest cancelRecurringReservationRequest) {
		CancelRecurringReservationResponse response = new CancelRecurringReservationResponse();
		ReservationCO reservationCO = new ReservationCO();

		try {
			PathPropertyUtil.copyProperties(cancelRecurringReservationRequest, reservationCO);
			response = omniCommonBO.cancelRecurringReservation(reservationCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public AddMaintenanceResponse addMaintenance_service(AddMaintenanceRequest addMaintenanceRequest) {
		AddMaintenanceResponse response = new AddMaintenanceResponse();
		Machine_mntCO machine_mntCO = new Machine_mntCO();
		try {
			PathPropertyUtil.copyProperties(addMaintenanceRequest, machine_mntCO);
			response = omniDriverBO.addMaintenance(machine_mntCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnMaintenanceResponse returnMaintenance_service(ReturnMaintenanceRequest returnMaintenanceRequest) {
		ReturnMaintenanceResponse response = new ReturnMaintenanceResponse();
		Machine_mntCO machine_mntCO = new Machine_mntCO();
		try {
			PathPropertyUtil.copyProperties(returnMaintenanceRequest, machine_mntCO);
			response = omniAdminBO.returnMaintenance(machine_mntCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public AddCompanyResponse addCompany_service(AddCompanyRequest addCompanyRequest) {
		AddCompanyResponse response = new AddCompanyResponse();
		CompaniesCO companiesCO = new CompaniesCO();
		try {
			PathPropertyUtil.copyProperties(addCompanyRequest, companiesCO);
			response = omniAdminBO.addCompany(companiesCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnCompaniesListResponse returnCompaniesList_service(ReturnCompaniesListRequest comapniesListRequest) {
		ReturnCompaniesListResponse response = new ReturnCompaniesListResponse();

		try {
			response = omniAdminBO.returnCompaniesList();
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	@Override
	public ConfirmTlubTukReservationResponse confirmTlubtukReservation_service(
			ConfirmTlubTukReservationRequest confirmTlubTukReservationRequest) {

		ConfirmTlubTukReservationResponse response = new ConfirmTlubTukReservationResponse();
		ReservationDetailsCO reservationDetailsCO = new ReservationDetailsCO();

		try {
			PathPropertyUtil.copyProperties(confirmTlubTukReservationRequest, reservationDetailsCO);
			response = omniCommonBO.confirmTlubTukReservation(reservationDetailsCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	@Override
	public ReturnTlubtukReservationDetailsResponse returnTlubtukResrevationDetails_service(
			ReturnTlubtukReservationDetailsRequest returnTlubtukReservationDetailsRequest) {
		ReturnTlubtukReservationDetailsResponse response = new ReturnTlubtukReservationDetailsResponse();
		ReservationDetailsVO reservationDetailsVO = new ReservationDetailsVO();

		try {
			PathPropertyUtil.copyProperties(returnTlubtukReservationDetailsRequest, reservationDetailsVO);
			response = omniClientBO.returnTlubtukReservationDetails(reservationDetailsVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public SwitchingDriverAvailabilityResponse switchingDriverAvailability_service(
			SwitchingDriverAvailabilityRequest switchingDriverAvailabilityRequest) {
		SwitchingDriverAvailabilityResponse response = new SwitchingDriverAvailabilityResponse();
		DriverCO driverCO = new DriverCO();

		try {
			PathPropertyUtil.copyProperties(switchingDriverAvailabilityRequest, driverCO);
			response = omniDriverBO.switchingDriverStatus(driverCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	@Override
	public ReturnDriverStatusResponse returnDriverStatus_service(ReturnDriverStatusRequest returnDriverStatusRequest) {
		ReturnDriverStatusResponse response = new ReturnDriverStatusResponse();
		DriverVO driverVO = new DriverVO();

		try {
			PathPropertyUtil.copyProperties(returnDriverStatusRequest, driverVO);
			response = omniDriverBO.returnDriverStatus(driverVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public InviteContactResponse inviteContact_service(InviteContactRequest inviteContactRequest) {
		InviteContactResponse response = new InviteContactResponse();
		InvitedContactCO invitedContactCO = new InvitedContactCO();
		try {
			PathPropertyUtil.copyProperties(inviteContactRequest, invitedContactCO);
			response = omniClientBO.inviteContact(invitedContactCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public returnListOfContactToInviteResponse returnListOfContactToInvite_service(
			returnListOfContactToInviteRequest returnListOfInvitedContactRequest) {
		returnListOfContactToInviteResponse response = new returnListOfContactToInviteResponse();
		InvitedContactCO invitedContactCO = new InvitedContactCO();
		try {
			PathPropertyUtil.copyProperties(returnListOfInvitedContactRequest, invitedContactCO);
			response = omniClientBO.returnListOfContactToInvite(invitedContactCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnListOfBrokersResponse returnListOfBrokers_service(
			ReturnListOfBrokersRequest returnListOfBrokersRequest) {
		ReturnListOfBrokersResponse response = new ReturnListOfBrokersResponse();
		ParamLovVO paramLovVO = new ParamLovVO();
		try {
			PathPropertyUtil.copyProperties(returnListOfBrokersRequest, paramLovVO);
			response = omniClientBO.returnListOfBrokers(paramLovVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnListOfServicesResponse returnListOfServices_service(
			ReturnListOfServicesRequest returnListOfServicesRequest) {
		ReturnListOfServicesResponse response = new ReturnListOfServicesResponse();
		LovVO lovVO = new LovVO();
		try {
			PathPropertyUtil.copyProperties(returnListOfServicesRequest, lovVO);
			response = omniClientBO.returnListOfServices(lovVO);
			return response;

		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ConfirmBrokerReservationResponse confirmBrokerReservation_service(
			ConfirmBrokerReservationRequest confirmBrokerReservationRequest) {
		ConfirmBrokerReservationResponse response = new ConfirmBrokerReservationResponse();
		ReservationSC reservationSC = new ReservationSC();
		try {
			PathPropertyUtil.copyProperties(confirmBrokerReservationRequest, reservationSC);
			response = omniClientBO.confirmBrokerReservation(reservationSC);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public UpdateReservationInfosResponse updateReservationInfos_service(
			UpdateReservationInfosRequest updateReservationInfosRequest) {
		UpdateReservationInfosResponse response = new UpdateReservationInfosResponse();
		ReservationSC reservationSC = new ReservationSC();
		try {
			PathPropertyUtil.copyProperties(updateReservationInfosRequest, reservationSC);
			response = omniDriverBO.updateReservationInfos(reservationSC);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnListOfFacturesResponse returnListOfFactures_service(
			ReturnListOfFacturesRequest returnListOfFacturesRequest) {
		ReturnListOfFacturesResponse response = new ReturnListOfFacturesResponse();
		ReservationSC reservationSC = new ReservationSC();
		try {
			PathPropertyUtil.copyProperties(returnListOfFacturesRequest, reservationSC);
			response = omniAdminBO.returnListOfFactures(reservationSC);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnCostOfDistanceResponse returnCostOfDistance_service(
			ReturnCostOfDistanceRequest returnCostOfDistanceRequest) {
		ReturnCostOfDistanceResponse response = new ReturnCostOfDistanceResponse();
		ReservationSC reservationSC = new ReservationSC();
		try {
			PathPropertyUtil.copyProperties(returnCostOfDistanceRequest, reservationSC);
			response = omniCommonBO.returnCostOfDistance(reservationSC);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public CheckingFutureDriverResponse checkingFutureDriver_service(
			CheckingFutureDriverRequest checkingFutureDriverRequest) {
		CheckingFutureDriverResponse response = new CheckingFutureDriverResponse();
		ReservationCO reservationCO = new ReservationCO();
		try {
			PathPropertyUtil.copyProperties(checkingFutureDriverRequest, reservationCO);
			response = omniDriverBO.checkingFutureDriver(reservationCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	@Override
	public ReturnAllDriversResponse returnAllDrivers_service(ReturnAllDriversRequest allDriversRequest) {
		ReturnAllDriversResponse response = new ReturnAllDriversResponse();
		ReservationCO reservationCO = new ReservationCO();
		try {
			PathPropertyUtil.copyProperties(allDriversRequest, reservationCO);
			response = omniAdminBO.returnAllDrivers(reservationCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public KillDriverResponse killDriver_service(KillDriverRequest killDriverRequest) {
		KillDriverResponse response = new KillDriverResponse();
		DriverCO driverCO = new DriverCO();
		try {
			PathPropertyUtil.copyProperties(killDriverRequest, driverCO);
			response = omniAdminBO.killDriver(driverCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnListOfMachinesResponse returnListOfMachines_service(
			ReturnListOfMachinesRequest returnListOfMachinesRequest) {
		ReturnListOfMachinesResponse response = new ReturnListOfMachinesResponse();
		MachineVO machineVO = new MachineVO();
		try {
			response = omniAdminBO.returnListOfMachines(machineVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public CreateDriverResponse createDriver_service(CreateDriverRequest createDriverRequest) {
		CreateDriverResponse response = new CreateDriverResponse();
		MachineDriverSC machineDriverSC = new MachineDriverSC();
		try {
			PathPropertyUtil.copyProperties(createDriverRequest, machineDriverSC);
			response = omniAdminBO.createDriver(machineDriverSC);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public CreateGroupResponse createGroup_service(CreateGroupRequest createGroupRequest) {
		CreateGroupResponse response = new CreateGroupResponse();
		Clients_groupVO clients_groupVO = new Clients_groupVO();
		try {
			PathPropertyUtil.copyProperties(createGroupRequest, clients_groupVO);
			response = omniCommonBO.createGroup(clients_groupVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnListOfGroupsResponse returnListOfGroups_service(ReturnListOfGroupsRequest listOfGroupsRequest) {
		ReturnListOfGroupsResponse response = new ReturnListOfGroupsResponse();
		Clients_groupVO clients_groupVO = new Clients_groupVO();
		try {
			PathPropertyUtil.copyProperties(listOfGroupsRequest, clients_groupVO);
			response = omniCommonBO.returnListOfGroups(clients_groupVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public AddMonthlyClientResponse addMonthlyClient_service(AddMonthlyClientRequest addMonthlyClientRequest) {
		AddMonthlyClientResponse response = new AddMonthlyClientResponse();
		ClientCO clientCO = new ClientCO();
		try {
			PathPropertyUtil.copyProperties(addMonthlyClientRequest, clientCO);
			response = omniCommonBO.addMonthlyClient(clientCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public UpdateDriverLocationsOfflineResponse updateDriverLocationsOffline_service(
			UpdateDriverLocationsOfflineRequest updateDriverLocationsOfflineRequest) {
		UpdateDriverLocationsOfflineResponse response = new UpdateDriverLocationsOfflineResponse();
//		List<DriverCO> listDriverLocations = new ArrayList<DriverCO>();
		UpdateDriverLocationRequest updateDriverLocationRequest = new UpdateDriverLocationRequest();
		UpdateDriverLocationResponse response1 = new UpdateDriverLocationResponse();
		List<Integer> listOfInsertedFrLocation_id = new ArrayList<Integer>();
		List<Integer> listOfNotInsertedFrLocation_id = new ArrayList<Integer>();

		try {
			for (DriverCO list : updateDriverLocationsOfflineRequest.getListOfLocations()) {
				PathPropertyUtil.copyProperties(list, updateDriverLocationRequest);
				response1 = updateDriverLocation_service(updateDriverLocationRequest);
				listOfInsertedFrLocation_id.add(response1.getInsertedFrLocation_id());
				listOfNotInsertedFrLocation_id.add(response1.getNotInsertedFrLocation_id());
			}
			response.setListOfInsertedFrLocation_id(listOfInsertedFrLocation_id);
			response.setListOfNotInsertedFrLocation_id(listOfNotInsertedFrLocation_id);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public CreateReservationOfflineResponse createReservationOffline_service(
			CreateReservationOfflineRequest reservationOfflineRequest) {
		CreateReservationOfflineResponse response = new CreateReservationOfflineResponse();
		CreateReservationResponse response1 = new CreateReservationResponse();
		List<ReservationCO> list = new ArrayList<ReservationCO>();
		CreateReservationRequest createReservationRequest = new CreateReservationRequest();
		List<Integer> listOfInsertedFrReservation_id = new ArrayList<Integer>();
		List<Integer> listOfNotInsertedFrReservation_id = new ArrayList<Integer>();
		try {
			for (ReservationCO l : reservationOfflineRequest.getListOfflineReservation()) {
				PathPropertyUtil.copyProperties(l, createReservationRequest);

				response1 = createReservation_service(createReservationRequest);
				listOfInsertedFrReservation_id.add(response1.getInsertedfrReservation_id());
				listOfNotInsertedFrReservation_id.add(response1.getNotInsertedfrReservation_id());
			}
			response.setListOfInsertedFrReservation_id(listOfInsertedFrReservation_id);
			response.setListOfNotInsertedFrReservation_id(listOfNotInsertedFrReservation_id);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	@Override
	public CreateNewUserResponse createNewUser_Service(CreateNewUserRequest createNewUserRequest) {
		CreateNewUserResponse response = new CreateNewUserResponse();
		ClientVO clientVO = new ClientVO();

		try {
			PathPropertyUtil.copyProperties(clientVO, createNewUserRequest);
			response = omniClientBO.createNewUser(clientVO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	/**
	 * 
	 * 
	 * 
	 */
	@Override
	public AddNewFactureResponse addNewFatcure_Service(AddNewFactureRequest addNewFactureRequest) {

		try {
			AddNewFactureResponse response = new AddNewFactureResponse();
			ReservationCO reservationCO = new ReservationCO();
			PathPropertyUtil.copyProperties(reservationCO, response);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnRecurringClientsResponse returnRecurringClients_Service(
			ReturnRecurringClientsRequest returnRecurringClientsRequest) {
		try {
			ReturnRecurringClientsResponse response = new ReturnRecurringClientsResponse();
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public CheckRecurringReservationResponse checkRecurringReservation_Service(
			CheckRecurringReservationRequest checkRecurringReservationRequest) {
		CheckRecurringReservationResponse response = new CheckRecurringReservationResponse();
		ReservationCO reservationCO = new ReservationCO();
		try {
			PathPropertyUtil.copyProperties(reservationCO, checkRecurringReservationRequest);
			// response = omniCommonBO.checkRecurringReservation(reservationCO);
			return response;

		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	/*
	 * @Override public
	 * UpdateMachineDriverForReservationOfRecurringReservationResponse
	 * updateMachineDriverForReservationOfRecurringReservation_Se(
	 * UpdateMachineDriverForReservationOfRecurringReservationRequest
	 * updateMachineDriverForReservationOfRecurringReservationRequest) { // TODO
	 * Auto-generated method stub return null; }
	 */

	@Override
	public UpdateMachineDriverForReservationOfRecurringReservationResponse updateMachineDriverForReservationOfRecurringReservation_Service(
			UpdateMachineDriverForReservationOfRecurringReservationRequest updateMachineDriverForReservationOfRecurringReservationRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddClientToGroupResponse addClientToGroup_Service(AddClientToGroupRequest addClientToGroupRequest) {

		try {
			AddClientToGroupResponse response = new AddClientToGroupResponse();
			ReservationCO reservationCO = new ReservationCO();
			PathPropertyUtil.copyProperties(addClientToGroupRequest, reservationCO);
			response = omniCommonBO.addClientToGroup(reservationCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ChangeStringToDateResponse changeStringToDate_Service(ChangeStringToDateRequest changeStringToDateRequest) {

		try {
			ChangeStringToDateResponse response = new ChangeStringToDateResponse();
			response = omniCommonBO.changeStringToDate(changeStringToDateRequest);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public RenewMonthlyClientResponse renewMonthlyClient_Service(RenewMonthlyClientRequest renewMonthlyClientRequest) {
		try {
			RenewMonthlyClientResponse response = new RenewMonthlyClientResponse();
			ClientCO clientCO = new ClientCO();
			PathPropertyUtil.copyProperties(renewMonthlyClientRequest, clientCO);
			response = omniCommonBO.renewMonthlyClient(clientCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public SuspendMonthlyClientResponse suspendMonthlyClient_Service(
			SuspendMonthlyClientRequest suspendMonthlyClientRequest) {
		try {
			ReservationCO reservationCO = new ReservationCO();
			PathPropertyUtil.copyProperties(suspendMonthlyClientRequest, reservationCO);
			SuspendMonthlyClientResponse response = new SuspendMonthlyClientResponse();
			response = omniCommonBO.suspendMonthlyClient(reservationCO);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ReturnMonthlyClientDataResponse returnMonthlyClientData_Service(
			ReturnMonthlyClientDataRequest returnMonthlyClientDataRequest) {
		try {
			ReturnMonthlyClientDataResponse response = new ReturnMonthlyClientDataResponse();
			response = omniCommonBO.returnMonthlyClientData(returnMonthlyClientDataRequest);
			return response;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

//	@Override
//	public CreateRecurringReservationAdminResponse createRecurringReservationAdmin_service(
//			CreateRecurringReservationAdminRequest createRecurringReservationAdminRequest) {
//		CreateRecurringReservationAdminResponse response = new CreateRecurringReservationAdminResponse();
//		ReservationCO reservationCO = new ReservationCO();
//		
//		try {
//			PathPropertyUtil.copyProperties(createRecurringReservationAdminRequest, reservationCO);
//			response = omniAdminBO.CreateRecurringReservationAdmin(reservationCO);
//			return response;
//		} catch (Exception e) {
//			log.info(e.getMessage());
//			return null;
//		}
//
//	}

}
