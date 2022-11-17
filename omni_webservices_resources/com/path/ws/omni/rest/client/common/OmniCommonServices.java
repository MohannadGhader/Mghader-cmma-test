package com.path.ws.omni.rest.client.common;

import javax.persistence.PostRemove;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.security.access.method.P;
import org.springframework.web.multipart.MultipartFile;

import com.path.ws.omni.vo.common.*;

import oracle.jdbc.proxy.annotation.Post;

@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface OmniCommonServices {

	@POST
	@Path("/authenticateUser/")
	public boolean authenticateUser_service(ChannelLoginRequest request);

	@POST
	@Path("/handShake/")
	public HandShakeResponse handShake_service(HandShakeRequest handShakeRequest);

	@POST
	@Path("/authenticate/")
	public AuthenticateClientResponse authenticateClient_service(AuthenticateClientRequest authenticateRequest);

	@POST
	@Path("/generateOTP/")
	public GenerateOTPResponse generateOTP_service(GenerateOTPRequest registrationRequest);

	@POST
	@Path("/checkUsername/")
	public CheckUsernameResponse checkUsername_service(CheckUsernameRequest checkUsernameRequest);

	@POST
	@Path("/registerUser/")
	public RegisterClientResponse registerClient_service(RegisterClientRequest registerClientRequest);

	@POST
	@Path("/validateOTP/")
	public ValidateOTPResponse validateOTP_service(ValidateOTPRequest validationRequest);

//	@POST
//	@Path("/validateOTPClient/")
//	public ValidateOTPClientResponse validateOTPClient_service (ValidateOTPClientRequest validateOTPClientRequest);

	/**
	 * login service if the client want to login from another device
	 * 
	 * @param logoutRequest
	 * @return
	 */
	@POST
	@Path("/loginClient/")
	public LoginClientResponse loginClient_service(LoginClientRequest loginClientRequest);

	/**
	 * service that reserve the machine to the client
	 * 
	 * @param createReservationRequest
	 * @return
	 */
	@POST
	@Path("/createReservation/")
	public CreateReservationResponse createReservation_service(CreateReservationRequest createReservationRequest);

	/**
	 * service that return the list of available machines from the nearest to the
	 * most far one
	 * 
	 * @param availableMachinesNearestMostFarRequest
	 * @return
	 */
	@POST
	@Path("/returnListOfMachinesNearestMostFar/")
	public ReturnListOfMachinesNearestMostFarResponse returnListOfMachinesNearestMostFar_service(
			ReturnListOfMachinesNearestMostFarRequest returnListOfMachinesRequest);

	/**
	 * service that return list of machines from the nearest to the most far one
	 * 
	 * @param nearestToMostFarRequest
	 * @return
	 */
//	@POST
//	@Path("/GetListOfMachinesFromNearestToMostFar/")
//	public GetListOfMachinesFromNearestToMostFarResponse GetListOfMachinesFromNearestToMostFar_service(
//			GetListOfMachinesFromNearestToMostFarRequest nearestToMostFarRequest);

	/**
	 * service that reserve a machine and return to the client the model and plate
	 * number of machine and the name and mobile number of driver
	 * 
	 * @param request return list
	 * @return
	 */
	@POST
	@Path("/confirmReservation/")
	public ConfirmReservationResponse ConfirmReservation_service(ConfirmReservationRequest createReservationRequest);

	/**
	 * service to cancel the reservation
	 * 
	 * @param request
	 * @return
	 */
	@POST
	@Path("/cancelReservation/")
	public CancelReservationResponse cancelReservation_service(CancelReservationRequest cancelReservationRequest);

	/**
	 * service to track the reservation status , the driver change the status
	 * 
	 * @param driverRequest
	 * @return
	 */
	@POST
	@Path("/updateReservationStatus/")
	public UpdateReservationStatusResponse updateReservationStatus_service(
			UpdateReservationStatusRequest updateReservationStatusRequest);

	/**
	 * service to rating the trip
	 * 
	 * @param rateRequest
	 * @return
	 */
	@POST
	@Path("/rating/")
	public RatingTripResponse ratingTrip_service(RateRequest rateRequest);

	/**
	 * service to logout
	 * 
	 * @param logoutRequest
	 * @return
	 */
	@POST
	@Path("/logout/")
	public LogoutResponse logout_service(LogoutRequest logoutRequest);

	/**
	 * service that update the location of driver
	 * 
	 * @param driverLocationRequest
	 * @return
	 */
	@POST
//	@GET
	@Path("/updateDriverLocation/")
	public UpdateDriverLocationResponse updateDriverLocation_service(UpdateDriverLocationRequest driverLocationRequest);

	/**
	 * service that save the username and password and rememeber the device
	 * 
	 * @param rememberDeviceRequest
	 * @return
	 */
	@POST
	@Path("/rememberDevice/")
	public RememberDeviceResponse rememberDevice_service(RememberDeviceRequest rememberDeviceRequest);

	/**
	 * service to get all infos of driver
	 * 
	 * @param getDriverInfosRequest
	 * @return
	 */
	@POST
	@Path("/returnDriverInfo/")
	public ReturnDriverInfoResponse returnDriverInfo_service(ReturnDriverInfoRequest returDriverInfoRequest);

	/**
	 * service to get client infos
	 * 
	 * @param getDriverInfosRequest
	 * @return
	 */
	@POST
	@Path("/returnClientInfo/")
	public ReturnClientInfoResponse returnClientInfo_service(ReturnClientInfoRequest returnClientInfoRequest);

	/**
	 * service to get machine infos
	 * 
	 * @param getMachineInfosRequest
	 * @return
	 */
	@POST
	@Path("/returnMachineInfos/")
	public ReturnMachineInfoResponse returnMachineInfo_service(ReturnMachineInfoRequest returnMachineInfoRequest);

	/**
	 * service to add driver
	 * 
	 * @param addDriverRequest
	 * @return
	 */
	@POST
	@Path("/addDriver/")
	public AddDriverResponse addDriver_service(AddDriverRequest addDriverRequest);

	/**
	 * service to add new machine
	 * 
	 * @param addMachineRequest
	 * @return
	 */
	@POST
	@Path("/addMachine/")
	public AddMachineResponse addMachine_service(AddMachineRequest addMachineRequest);

	/**
	 * service to update the client infos
	 * 
	 * @param updateClientInfosRequest
	 * @return
	 */
	@POST
	@Path("/updateClientInfos/")
	public UpdateClientInfosResponse updateClientInfos_service(UpdateClientInfosRequest updateClientInfosRequest);

	/**
	 * service to change the password
	 * 
	 * @param changePasswordRequest
	 * @return
	 */
	@POST
	@Path("/changePassword/")
	public ChangePasswordResponse changePassword_service(ChangePasswordRequest changePasswordRequest);

	/**
	 * service that change the profile of client
	 * 
	 * @param changeProfileRequest
	 * @return
	 */
	@POST
	@Path("/changeProfile/")
	public ChangeProfileResponse changeProfile_service(ChangeProfileRequest changeProfileRequest);

	/**
	 * service to update the machine infos
	 * 
	 * @param updateMachineInfosRequest
	 * @return
	 */
	@POST
	@Path("/updateMachineInfo/")
	public UpdateMachineInfoResponse updateMachineInfo_service(UpdateMachineInfoRequest updateMachineInfoRequest);

	/**
	 * service to update the driver infos
	 * 
	 * @param updateDriverInfosRequest
	 * @return
	 */
	@POST
	@Path("/updateDriverInfo/")
	public UpdateDriverInfoResponse updateDriverInfo_service(UpdateDriverInfoRequest updateDriverInfoRequest);

	/**
	 * service to get the history of the client
	 * 
	 * @param reservationListRequest
	 * @return
	 */
	@POST
	@Path("/returnReservationsList/")
	public ReturnReservationsListResponse returnReservationsList_service(
			ReturnReservationsListRequest reservationListRequest);

	/**
	 * service return the details for reservation
	 * 
	 * @param returnReservationDetailsRequest
	 * @return
	 */
	@POST
	@Path("/returnReservationDetails/")
	public ReturnReservationDetailsResponse returnReservationDetails_service(
			ReturnReservationDetailsRequest returnReservationDetailsRequest);

	/**
	 * service that create recurring reservation
	 * 
	 * @param createRecurringReservationRequest
	 * @return
	 */
	@POST
	@Path("/createRecurringReservation/")
	public CreateRecurringReservationResponse createRecurringReservation_service(
			createRecurringReservationRequest createRecurringReservationRequest);

	/**
	 * service that return the list of temporary reservations
	 * 
	 * @param checkTempRecurringReservationRequest
	 * @return
	 */
	@POST
	@Path("/chechTempRecurringReservation/")
	public CheckTempRecurringReservationResponse checkTempRecurringReservation_service(
			CheckTempRecurringReservationRequest checkTempRecurringReservationRequest);

	/**
	 * if the client reject the reservation , this service delete the reservation
	 * from temporary_reservation table
	 * 
	 * @param rejectReservationRequest
	 * @return
	 */
	@POST
	@Path("/rejectReservation/")
	public RejectReservationResponse rejectReservation_service(RejectReservationRequest rejectReservationRequest);

	/**
	 * service to save the working hours for the drivers
	 * 
	 * @param driverWorkingHoursRequest
	 * @return
	 */
	@POST
	@Path("/updateDriverWorkingHours/")
	public UpdateDriverWorkingHoursResponse updateDriverWorkingHours_service(
			UpdateDriverWorkingHoursRequest driverWorkingHoursRequest);

	/**
	 * service to add store
	 * 
	 * @param addStoreRequest
	 * @return
	 */
	@POST
	@Path("/addStore/")
	public AddStoreResponse addStore_service(AddStoreRequest addStoreRequest);

	/**
	 * service to add destination
	 * 
	 * @param addDestinationRequest
	 * @return
	 */
	@POST
	@Path("/addDestination/")
	public AddDestinationResponse addDestination_service(AddDestinationRequest addDestinationRequest);

	/**
	 * service that return the saved destinations of client
	 * 
	 * @param listOfDestinationsRequest
	 * @return
	 */
	@POST
	@Path("returnlistOfDestinations")
	public ReturnListOfDestinationsResponse returnListOfDestinations_service(
			ReturnListOfDestinationsRequest returnListOfDestinationsRequest);

	/**
	 * service that reset the password
	 * 
	 * @param forgetPasswordRequest
	 * @return
	 */
	@POST
	@Path("/forgetPasswordClient/")
	public ForgetPasswordResponse forgetPasswordClient_service(ForgetPasswordRequest forgetPasswordRequest);

	/**
	 * service that reset the password
	 * 
	 * @param resetPasswordRequest
	 * @return
	 */
	@POST
	@Path("/resetPasswordClient/")
	public ResetPasswordResponse resetPasswordClient_service(ResetPasswordRequest resetPasswordRequest);

	/**
	 * service that return the list of devices that registred for a username
	 * 
	 * @param returnListDevicesRequest
	 * @return
	 */
	@POST
	@Path("/returnListDevices/")
	public ReturnListDevicesResponse returnListDevices_service(ReturnListDevicesRequest returnListDevicesRequest);

	/**
	 * service that unregister a device
	 * 
	 * @param unregisterDeviceRequest
	 * @return
	 */
	@POST
	@Path("/UnregisterDevice/")
	public UnregisterDeviceResponse unregitserDevice_service(UnregisterDeviceRequest unregisterDeviceRequest);

	@POST
	@Path("/contactUs/")
	public ContactUsResponse contactUs_service(ContactUsRequest contactUsRequest);

	/**
	 * service that authenticate the driver account
	 * 
	 * @param authenticateDriverRequest
	 * @return
	 */
	@POST
	@Path("/authenticateDriver/")
	public AuthenticateDriverResponse authenticateDriver_service(AuthenticateDriverRequest authenticateDriverRequest);

	/**
	 * login service for driver app
	 * 
	 * @param loginDriverRequest
	 * @return
	 */
	@POST
	@Path("/loginDriver/")
	public LoginDriverResponse loginDriver_service(LoginDriverRequest loginDriverRequest);

	/**
	 * logout for driver app
	 * 
	 * @param logoutDriverRequest
	 * @return
	 */
	@POST
	@Path("/logoutDriver/")
	public LogoutDriverResponse logoutDriver_service(LogoutDriverRequest logoutDriverRequest);

	/**
	 * service that allow the driver to set the time slot expected for the trip
	 * 
	 * @param setTimeSlotRequest
	 * @return
	 */
	@POST
	@Path("/updateTimeSlot/")
	public UpdateTimeSlotResponse updateTimeSlot_service(UpdateTimeSlotRequest updateTimeSlotRequest);

	/**
	 * service that return the list of reservations of the driver
	 * 
	 * @param returnListReservationsDriverRequest
	 * @return
	 */
	@POST
	@Path("/returnListReservationsDriver/")
	public ReturnListReservationsDriverResponse returnListReservationsDriver_service(
			ReturnListReservationsDriverRequest returnListReservationsDriverRequest);

	/**
	 * service that allow the driver to change his password
	 * 
	 * @param changePasswordDriverRequest
	 * @return
	 */
	@POST
	@Path("/changePasswordDriver/")
	public ChangePasswordDriverResponse changePasswordDriver_service(
			ChangePasswordDriverRequest changePasswordDriverRequest);

	/**
	 * service that get the device token
	 * 
	 * @param getTokenRequest
	 * @return
	 */
	@POST
	@Path("/saveToken/")
	public SaveTokenResponse saveToken_service(SaveTokenRequest saveTokenRequest);

	/**
	 * service that return the mobile number and musk and otp if the driver forget
	 * his pass
	 * 
	 * @param forgetPasswordDriverRequest
	 * @return
	 */
	@POST
	@Path("/forgetPasswordDriver/")
	public ForgetPasswordResponse forgetPasswordDriver_service(ForgetPasswordRequest forgetPasswordRequest);

	/**
	 * service that reset the password
	 * 
	 * @param resetPasswordDriverRequest
	 * @return
	 */
	@POST
	@Path("/resetPasswordDriver/")
	public ResetPasswordResponse resetPasswordDriver_service(ResetPasswordRequest resetPasswordDriverRequest);

	/**
	 * service that add admin
	 * 
	 * @param addAdminRequest
	 * @return
	 */
	@POST
	@Path("/addAdmin/")
	public AddAdminResponse addAdmin_service(AddAdminRequest addAdminRequest);

	/**
	 * service to login admin
	 * 
	 * @param loginAdminRequest
	 * @return
	 */
	@POST
	@Path("/loginAdmin/")
	public LoginAdminResponse loginAdmin_service(LoginAdminRequest loginAdminRequest);

	/**
	 * service that allow the admin to update a property
	 * 
	 * @param propertyRequest
	 * @return
	 */
	@POST
	@Path("/updateProperty/")
	public UpdatePropertyResponse updateProperty_service(UpdatePropertyRequest updatePropertyRequest);

	/**
	 * service that authenticate the combination of username and device for admin
	 * 
	 * @param authenticateAdminRequest
	 * @return
	 */
	@POST
	@Path("/authenticateAdmin/")
	public AuthenticateAdminResponse authenticateAdmin_service(AuthenticateAdminRequest authenticateAdminRequest);

	/**
	 * service that update the profile of driver
	 * 
	 * @param changeProfileDriverRequest
	 * @return
	 */
	@POST
	@Path("/changeProfileDriver/")
	public ChangeProfileDriverResponse changeProfileDriver_service(
			ChangeProfileDriverRequest changeProfileDriverRequest);

	/**
	 * service to insert the combinbation of machine and driver
	 * 
	 * @param addMachineDriverRequest
	 * @return
	 */
	@POST
	@Path("/addMachineDriver/")
	public AddMachineDriverResponse addMachineDriver_service(AddMachineDriverRequest addMachineDriverRequest);

	/**
	 * service that return list of properties
	 * 
	 * @param listPropertiesRequest
	 * @return
	 */
	@POST
	@Path("/returnListProperties/")
	public ReturnListPropertiesResponse returnListProperties_service(
			ReturnListPropertiesRequest returnListPropertiesRequest);

	/**
	 * service that return the current location of the driver and machine
	 * 
	 * @param returnDriverLocationRequest
	 * @return
	 */
	@POST
	@Path("/returnDriverLocation/")
	public ReturnDriverLocationResponse returnDriverLocation_service(
			ReturnDriverLocationRequest returnDriverLocationRequest);

	@POST
	@Path("/test/")
	public String test_service();

	/**
	 * service that return nearest available driver
	 * 
	 * @param returnNearestAvailableMachineRequest
	 * @return
	 */
	@POST
	@Path("/returnNearestAvailableMachine/")
	public ReturnNearestAvailableMachineResponse returnNearestAvailableMachine_service(
			ReturnNearestAvailableMachineRequest returnNearestAvailableMachineRequest);

	/**
	 * service that logout admin
	 * 
	 * @param logoutAdminRequest
	 * @return
	 */
	@POST
	@Path("/logoutAdmin/")
	public LogoutResponse logoutAdmin_service(LogoutAdminRequest logoutAdminRequest);

	/**
	 * service that return list of reservations in admin
	 * 
	 * @param listReservationsAdminRequest
	 * @return
	 */
	@POST
	@Path("/returnListReservationsAdmin/")
	public ReturnListReservationsAdminResponse returnListReservationsAdmin_service(
			ReturnListReservationsAdminRequest listReservationsAdminRequest);

	/**
	 * service that return the recurring reservations
	 * 
	 * @param returnRecurringReservationListRequest
	 * @return
	 */
	@POST
	@Path("/returnRecurringReservation/")
	public ReturnRecurringReservationListResponse returnRecurringReservationList_service(
			ReturnRecurringReservationListRequest returnRecurringReservationListRequest);

	/**
	 * service that allow the client to cancel the recurring reservation
	 * 
	 * @param cancelRecurringReservationRequest
	 * @return
	 */
	@POST
	@Path("/cancelRecurringReservation/")
	public CancelRecurringReservationResponse cancelRecurringReservation_service(
			CancelRecurringReservationRequest cancelRecurringReservationRequest);

	/**
	 * service that add maintenance
	 * 
	 * @param addMaintenanceRequest
	 * @return
	 */
	@POST
	@Path("/addMaintenance/")
	public AddMaintenanceResponse addMaintenance_service(AddMaintenanceRequest addMaintenanceRequest);

	/**
	 * service that return maintenance
	 * 
	 * @param returnMaintenanceRequest
	 * @return
	 */
	@POST
	@Path("/returnMaintenance/")
	public ReturnMaintenanceResponse returnMaintenance_service(ReturnMaintenanceRequest returnMaintenanceRequest);

	/**
	 * service that add company that can take advantage of discount
	 * 
	 * @param addCompanyRequest
	 * @return
	 */
	@POST
	@Path("/addCompany/")
	public AddCompanyResponse addCompany_service(AddCompanyRequest addCompanyRequest);

	/**
	 * service that return the list of companies that have discount
	 * 
	 * @param comapniesListRequest
	 * @return
	 */
	@POST
	@Path("/returnCompaniesList/")
	public ReturnCompaniesListResponse returnCompaniesList_service(ReturnCompaniesListRequest comapniesListRequest);

	/**
	 * service that confirm the tlubtuk reservation
	 * 
	 * @param confirmReservationRequest
	 * @return
	 */
	@POST
	@Path("/confirmTlubtukReservation/")
	public ConfirmTlubTukReservationResponse confirmTlubtukReservation_service(
			ConfirmTlubTukReservationRequest confirmTlubTukReservationRequest);

	/**
	 * service that return tlubtuk reservation details
	 * 
	 * @param returnTlubtukReservationDetailsRequest
	 * @return
	 */
	@POST
	@Path("/returnTlubtukResrevationDetails/")
	public ReturnTlubtukReservationDetailsResponse returnTlubtukResrevationDetails_service(
			ReturnTlubtukReservationDetailsRequest returnTlubtukReservationDetailsRequest);

	/**
	 * service that allow the driver to switch his status on/off
	 * 
	 * @param switchingDriverStatusRequest
	 * @return
	 */
	@POST
	@Path("switchingDriverAvailability")
	public SwitchingDriverAvailabilityResponse switchingDriverAvailability_service(
			SwitchingDriverAvailabilityRequest switchingDriverAvailabilityRequest);

	/**
	 * service that return the driver status
	 * 
	 * @param returnDriverStatusRequest
	 * @return
	 */
	@POST
	@Path("/returnDriverStatus/")
	public ReturnDriverStatusResponse returnDriverStatus_service(ReturnDriverStatusRequest returnDriverStatusRequest);

	/**
	 * service that invite contact
	 * 
	 * @param inviteContactRequest
	 * @return
	 */
	@POST
	@Path("/inviteContact/")
	public InviteContactResponse inviteContact_service(InviteContactRequest inviteContactRequest);

	/**
	 * service that return the list of invited contact
	 * 
	 * @param returnListOfInvitedContactRequest
	 * @return
	 */
	@POST
	@Path("/returnListOfContactToInvite/")
	public returnListOfContactToInviteResponse returnListOfContactToInvite_service(
			returnListOfContactToInviteRequest returnListOfContactToInviteRequest);

	/**
	 * service that return the list of brokers
	 * 
	 * @param returnListOfBrokersRequest
	 * @return
	 */
	@POST
	@Path("returnListOfBrokers")
	public ReturnListOfBrokersResponse returnListOfBrokers_service(
			ReturnListOfBrokersRequest returnListOfBrokersRequest);

	/**
	 * service that return the list of services
	 * 
	 * @param returnServicesRequest
	 * @return
	 */
	@POST
	@Path("/returnListOfServices/")
	public ReturnListOfServicesResponse returnListOfServices_service(
			ReturnListOfServicesRequest returnListOfServicesRequest);

	/**
	 * service that confirm the broker reservation
	 * 
	 * @param confirmBrokerReservationRequest
	 * @return
	 */
	@POST
	@Path("/confirmBrokerReservation/")
	public ConfirmBrokerReservationResponse confirmBrokerReservation_service(
			ConfirmBrokerReservationRequest confirmBrokerReservationRequest);

	/**
	 * service that allow the driver to update the cost in case if the cost by
	 * system is not valid
	 * 
	 * @param updateCostByDriverRequest
	 * @return
	 */
	@POST
	@Path("/updateReservationInfos/")
	public UpdateReservationInfosResponse updateReservationInfos_service(
			UpdateReservationInfosRequest updateReservationInfosRequest);

	/**
	 * method for admin that return list of reservations and there facture
	 * 
	 * @param istOfFacturesRequest
	 * @return
	 */
	@POST
	@Path("/returnListOfFactures/")
	public ReturnListOfFacturesResponse returnListOfFactures_service(
			ReturnListOfFacturesRequest returnListOfFacturesRequest);

	/**
	 * service that calculate the cost of distance
	 * 
	 * @param returnCostOfDistanceRequest
	 * @return
	 */
	@POST
	@Path("/returnCostOfDistance/")
	public ReturnCostOfDistanceResponse returnCostOfDistance_service(
			ReturnCostOfDistanceRequest returnCostOfDistanceRequest);

	/**
	 * service that check the reserved driver of a future reservation, if it is not
	 * the nearest one , switch to the nearest
	 * 
	 * @param checkingFutureDriverRequest
	 * @return
	 */
	@POST
	@Path("/checkingFutureDriver/")
	public CheckingFutureDriverResponse checkingFutureDriver_service(
			CheckingFutureDriverRequest checkingFutureDriverRequest);

	/**
	 * service that return all driver for admin screen
	 * 
	 * @param returnAllDriversRequest
	 * @return
	 */
	@POST
	@Path("/returnAllDrivers/")
	public ReturnAllDriversResponse returnAllDrivers_service(ReturnAllDriversRequest returnAllDriversRequest);

	/**
	 * service that kill driver
	 * 
	 * @param killDriverRequest
	 * @return
	 */
	@POST
	@Path("/killDriver/")
	public KillDriverResponse killDriver_service(KillDriverRequest killDriverRequest);

	/**
	 * service that return the list of machines
	 * 
	 * @param returnListOfMachinesRequest
	 * @return
	 */

	@POST
	@Path("/returnListOfMachines/")
	public ReturnListOfMachinesResponse returnListOfMachines_service(
			ReturnListOfMachinesRequest returnListOfMachinesRequest);

	/**
	 * service that create driver with combination driver machine , and working
	 * hours
	 * 
	 * @param createDriverRequest
	 * @return
	 */
	@POST
	@Path("/createDriver/")
	public CreateDriverResponse createDriver_service(CreateDriverRequest createDriverRequest);

	/**
	 * service that create group
	 * 
	 * @param createGroupRequest
	 * @return
	 */
	@POST
	@Path("/createGroup/")
	public CreateGroupResponse createGroup_service(CreateGroupRequest createGroupRequest);

	/**
	 * service that return the list of groups
	 * 
	 * @param listOfGroupsRequest
	 * @return
	 */
	@POST
	@Path("/returnListOfGroups/")
	public ReturnListOfGroupsResponse returnListOfGroups_service(ReturnListOfGroupsRequest listOfGroupsRequest);

	/**
	 * service that add new monthly client
	 * 
	 * @param addMonthlyClientRequest
	 * @return
	 */
	@POST
	@Path("/addMonthlyClient/")
	public AddMonthlyClientResponse addMonthlyClient_service(AddMonthlyClientRequest addMonthlyClientRequest);

	/**
	 * service that update driver locations offline
	 * 
	 * @param updateDriverLocationsOfflineRequest
	 * @return
	 */
	@POST
	@Path("/updateDriverLocationsOffline/")
	public UpdateDriverLocationsOfflineResponse updateDriverLocationsOffline_service(
			UpdateDriverLocationsOfflineRequest updateDriverLocationsOfflineRequest);

	/**
	 * 
	 * 
	 * 
	 * @param reservationOfflineRequest
	 * @return
	 */
	@POST
	@Path("/createReservationOffline/")
	public CreateReservationOfflineResponse createReservationOffline_service(
			CreateReservationOfflineRequest reservationOfflineRequest);

	/**
	 * MyFirstService
	 * 
	 * @param createNewUserRequest
	 * @return
	 */
	@POST
	@Path("/createUser/")
	public CreateNewUserResponse createNewUser_Service(CreateNewUserRequest createNewUserRequest);

	/**
	 * service that create new Facture
	 * 
	 * @param addNewFactureRequest
	 * @return
	 */
	@POST
	@Path("/addNewFacture/")
	public AddNewFactureResponse addNewFatcure_Service(AddNewFactureRequest addNewFactureRequest);

	/**
	 * 
	 * @param returnRecurringClientsRequest
	 * @return
	 */
	@POST
	@Path("/returnRecurringClients/")
	public ReturnRecurringClientsResponse returnRecurringClients_Service(
			ReturnRecurringClientsRequest returnRecurringClientsRequest);

	/**
	 * 
	 * @param checkRecurringReservationRequest
	 * @return
	 */
	@POST
	@Path("/checkRecurringReservation/")
	public CheckRecurringReservationResponse checkRecurringReservation_Service(
			CheckRecurringReservationRequest checkRecurringReservationRequest);

	/**
	 * 
	 * @param updateMachineDriverForReservationOfRecurringReservationRequest
	 * @return
	 */
	@POST
	@Path("/updateMachineDriverForReservationOfRecurringReservation/")
	public UpdateMachineDriverForReservationOfRecurringReservationResponse updateMachineDriverForReservationOfRecurringReservation_Service(
			UpdateMachineDriverForReservationOfRecurringReservationRequest updateMachineDriverForReservationOfRecurringReservationRequest);

	/**
	 * 
	 * @param addClientToGroupRequest
	 * @return
	 */
	@POST
	@Path("/addClientToGroup/")
	public AddClientToGroupResponse addClientToGroup_Service(AddClientToGroupRequest addClientToGroupRequest);

	/**
	 * 
	 * @param changeStringToDateRequest
	 * @return
	 */
	@POST
	@Path("/changeStringToDate/")
	public ChangeStringToDateResponse changeStringToDate_Service(ChangeStringToDateRequest changeStringToDateRequest);

	/**
	 * 
	 * @param renewMonthlyUserRequest
	 * @return
	 */
	@POST
	@Path("/renewMonthlyClient/")
	public RenewMonthlyClientResponse renewMonthlyClient_Service(RenewMonthlyClientRequest renewMonthlyClientRequest);

	@POST
	@Path("/suspendMonthlyClient/")
	public SuspendMonthlyClientResponse suspendMonthlyClient_Service(
			SuspendMonthlyClientRequest suspendMonthlyClientRequest);

	@POST
	@Path("/returnMonthlyClientData/")
	public ReturnMonthlyClientDataResponse returnMonthlyClientData_Service(
			ReturnMonthlyClientDataRequest retreiveMonthlyUserDataRequest);

//************Redandant Code**********************
//	
//	
//	/**
//	 * MY first service
//	 * @param createRecurringReservationAdminRequest
//	 * @return
//	 */
//	@POST
//	@Path("/createRecurringReservationAdmin/")
//	public CreateRecurringReservationAdminResponse createRecurringReservationAdmin_service(
//			CreateRecurringReservationAdminRequest createRecurringReservationAdminRequest);

}
