package com.path.bo.omni.client;

import java.util.List;

import com.path.dbmaps.vo.ClientCO;
import com.path.dbmaps.vo.ClientVO;
import com.path.dbmaps.vo.DriverVO;
import com.path.dbmaps.vo.InvitedContactCO;
import com.path.dbmaps.vo.LovVO;
import com.path.dbmaps.vo.Machine_driverCO;
import com.path.dbmaps.vo.ParamLovVO;
import com.path.dbmaps.vo.ReservationCO;
import com.path.dbmaps.vo.ReservationDetailsCO;
import com.path.dbmaps.vo.ReservationDetailsVO;
import com.path.dbmaps.vo.ReservationSC;
import com.path.dbmaps.vo.ReservationVO;
import com.path.ws.omni.vo.common.AddDestinationResponse;
import com.path.ws.omni.vo.common.AuthenticateClientResponse;
import com.path.ws.omni.vo.common.CancelReservationResponse;
import com.path.ws.omni.vo.common.ChangePasswordResponse;
import com.path.ws.omni.vo.common.ChangeProfileResponse;
import com.path.ws.omni.vo.common.CheckUsernameResponse;
import com.path.ws.omni.vo.common.ConfirmBrokerReservationResponse;
import com.path.ws.omni.vo.common.ConfirmTlubTukReservationResponse;
import com.path.ws.omni.vo.common.ContactUsRequest;
import com.path.ws.omni.vo.common.ContactUsResponse;
import com.path.ws.omni.vo.common.CreateNewUserResponse;
import com.path.ws.omni.vo.common.CreateRecurringReservationResponse;
import com.path.ws.omni.vo.common.CreateReservationResponse;
import com.path.ws.omni.vo.common.ForgetPasswordResponse;
import com.path.ws.omni.vo.common.InviteContactResponse;
import com.path.ws.omni.vo.common.LoginClientResponse;
import com.path.ws.omni.vo.common.LogoutResponse;
import com.path.ws.omni.vo.common.RegisterClientResponse;
import com.path.ws.omni.vo.common.RememberDeviceResponse;
import com.path.ws.omni.vo.common.ResetPasswordResponse;
import com.path.ws.omni.vo.common.ReturnClientInfoResponse;
import com.path.ws.omni.vo.common.ReturnListDevicesResponse;
import com.path.ws.omni.vo.common.ReturnListOfBrokersResponse;
import com.path.ws.omni.vo.common.ReturnListOfDestinationsResponse;
import com.path.ws.omni.vo.common.returnListOfContactToInviteResponse;
import com.path.ws.omni.vo.common.ReturnListOfMachinesNearestMostFarResponse;
import com.path.ws.omni.vo.common.ReturnListOfServicesResponse;
import com.path.ws.omni.vo.common.ReturnNearestAvailableMachineResponse;
import com.path.ws.omni.vo.common.ReturnRecurringReservationListResponse;
import com.path.ws.omni.vo.common.ReturnReservationDetailsResponse;
import com.path.ws.omni.vo.common.ReturnReservationsListResponse;
import com.path.ws.omni.vo.common.ReturnTlubtukReservationDetailsResponse;
import com.path.ws.omni.vo.common.UnregisterDeviceResponse;
import com.path.ws.omni.vo.common.UpdateClientInfosResponse;

public interface OmniClientBO {
//
//	public ValidateOTPClientResponse validateOTPClient(DeviceSC deviceSC) throws Exception;
//
//	public GenerateOTPClientResponse generateOTPClient(DeviceSC deviceSC) throws Exception;

	/**
	 * method that check if the username is already exist , if exist send msg to
	 * choose another one
	 * 
	 * @param checkUsernameRequest
	 * @return
	 * @throws Exception
	 */
	public CheckUsernameResponse checkUsername(ClientVO clientVO) throws Exception;

	/**
	 * method that authenticate the user ,
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public AuthenticateClientResponse authenticateClient(ClientCO clientCO) throws Exception;

	/**
	 * method that get the username and password if valid return valid , if invalid
	 * return error
	 * 
	 * @param loginRequest
	 * @return
	 * @throws Exception
	 */
	public LoginClientResponse loginClient(ClientCO clientCO) throws Exception;

	/**
	 * method to save the username and password with this device
	 * 
	 * @param clientCO
	 * @return
	 * @throws Exception
	 */
	public RememberDeviceResponse rememberDevice(ClientCO clientCO) throws Exception;

	/**
	 * method that registre a client , insert the username and password in bd and
	 * mobile_number
	 * 
	 * @param registrationRequest
	 * @throws Exception
	 */
	public RegisterClientResponse registerClient(ClientCO clientCO) throws Exception;

//	/**
//	 * method that return the list of available machines from the nearest to the
//	 * most far
//	 * 
//	 * @param reservationCO
//	 * @return
//	 * @throws Exception
//	 */
//	public ReturnListOfMachinesResponse returnListOfMachinesNearestMostFar(ReservationCO reservationCO)
//			throws Exception;

	/**
	 * method to cancel the reservation
	 * 
	 * @param request
	 * @throws Exception
	 */
	public CancelReservationResponse cancelReservation(ReservationCO reservationCO) throws Exception;

	/**
	 * method to logout
	 * 
	 * @param deviceVO
	 * @return
	 * @throws Exception
	 */
	public LogoutResponse logout(ClientCO clientCO) throws Exception;

	/**
	 * method to get client infos
	 * 
	 * @param clientVO
	 * @return
	 * @throws Exception
	 */
	public ReturnClientInfoResponse returnClientInfo(ClientVO clientVO) throws Exception;

	/**
	 * method to update the client infos
	 * 
	 * @param updateClientInfosRequest
	 * @return
	 */
	public UpdateClientInfosResponse updateClientInfos(ClientVO clientVO) throws Exception;

	/**
	 * method to change the password
	 * 
	 * @param clientVO
	 * @return
	 * @throws Exception
	 */
	public ChangePasswordResponse changePassword(ClientCO clientCO) throws Exception;

	/**
	 * method to change the profile
	 * 
	 * @param clientVO
	 * @return
	 * @throws Exception
	 */
	public ChangeProfileResponse changeProfile(ClientVO clientVO) throws Exception;

	/**
	 * method that return the list of reservations
	 * 
	 * @return
	 * @throws Exception
	 */
	public ReturnReservationsListResponse returnReservationsList(ReservationVO reservationVO) throws Exception;

	/**
	 * method return reservation details
	 * 
	 * @param reservationVO
	 * @return
	 * @throws Exception
	 */
	public ReturnReservationDetailsResponse returnReservationDetails(ReservationVO reservationVO) throws Exception;

	/**
	 * method that save the destinations of client
	 * 
	 * @param list_destinationsVO
	 * @return
	 * @throws Exception
	 */
	public AddDestinationResponse addDestination(ReservationCO reservationCO) throws Exception;

	/**
	 * method that return the destinations of client
	 * 
	 * @param list_destinationsVO
	 * @return
	 * @throws Exception
	 */
	public ReturnListOfDestinationsResponse returnListOfDestinations(ReservationCO reservationCO) throws Exception;

	/**
	 * method that return the masked mobile number for the requested username and
	 * generate otp
	 * 
	 * @param clientVO
	 * @return
	 * @throws Exception
	 */
	public ForgetPasswordResponse forgetPasswordClient(ClientCO clientCO) throws Exception;

	/**
	 * method that reset the password
	 * 
	 * @param clientVO
	 * @return
	 * @throws Exception
	 */
	public ResetPasswordResponse resetPasswordClient(ClientVO clientVO) throws Exception;

	/**
	 * method that return the list of devices
	 * 
	 * @param clientVO
	 * @return
	 * @throws Exception
	 */
	public ReturnListDevicesResponse returnListDevices(ClientVO clientVO) throws Exception;

	/**
	 * method that unregister device
	 * 
	 * @param deviceVO
	 * @return
	 * @throws Exception
	 */
	public UnregisterDeviceResponse unregisterDevice(ClientCO clientCO) throws Exception;

	/**
	 * service that send email from client to the company
	 * 
	 * @param contactUsRequest
	 * @return
	 * @throws Exception
	 */
	public ContactUsResponse contactUs(ContactUsRequest contactUsRequest) throws Exception;

	public String setTimeToReachClient(int timeToReachClient, int language_id) throws Exception;

//	/**
//	 * return list of machine
//	 * 
//	 * @param reservationCO
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Machine_driverCO> returnList(ReservationCO reservationCO) throws Exception;

	/**
	 * method that return array of int , have the availability period of a machine
	 * driver
	 * 
	 * @param machine_driverCO
	 * @return
	 * @throws Exception
	 */
//	public int[] updateAvailabilityPeriod(Machine_driverCO machine_driverCO, Time startTime, int availabilityPeriod)
//			throws Exception;

	/**
	 * 
	 * removed and to be added in omnicommonBO
	 * 
	 * method that return the list of recurring reservation
	 * 
	 * @param reservationVO
	 * @return
	 * @throws Exception
	 *//*
		 * public ReturnRecurringReservationListResponse
		 * returnRecurringReservationList(ReservationCO reservationCO) throws Exception;
		 */

	/**
	 * method that return the tlubtuk resrevation details
	 * 
	 * @param reservationDetailsVO
	 * @return
	 * @throws Exception
	 */
	public ReturnTlubtukReservationDetailsResponse returnTlubtukReservationDetails(
			ReservationDetailsVO reservationDetailsVO) throws Exception;

	/**
	 * method that invite contact
	 * 
	 * @param inviteContactRequest
	 * @return
	 * @throws Exception
	 */
	public InviteContactResponse inviteContact(InvitedContactCO invitedContactCO) throws Exception;

	/**
	 * method that return the list of invited contact
	 * 
	 * @param invitedContactCO
	 * @return
	 * @throws Exception
	 */
	public returnListOfContactToInviteResponse returnListOfContactToInvite(InvitedContactCO invitedContactCO)
			throws Exception;

	/**
	 * method that return the list of brokers
	 * 
	 * @param clientVO
	 * @return
	 * @throws Exception
	 */
	public ReturnListOfBrokersResponse returnListOfBrokers(ParamLovVO paramLovVO) throws Exception;

	/**
	 * method that return list of services
	 * 
	 * @param lovVO
	 * @return
	 * @throws Exception
	 */
	public ReturnListOfServicesResponse returnListOfServices(LovVO lovVO) throws Exception;

	/**
	 * method that confirm the broker reservation
	 * 
	 * @param reservationSC
	 * @return
	 * @throws Exception
	 */
	public ConfirmBrokerReservationResponse confirmBrokerReservation(ReservationSC reservationSC) throws Exception;

//	/**
//	 * method that return the nearest driver
//	 * 
//	 * @param reservationCO
//	 * @return
//	 * @throws Exception
//	 */
//	public ReturnNearestAvailableMachineResponse returnNearestAvailableMachine(ReservationCO reservationCO)
//			throws Exception;
	public CreateNewUserResponse createNewUser(ClientVO clientVO) throws Exception;
}
