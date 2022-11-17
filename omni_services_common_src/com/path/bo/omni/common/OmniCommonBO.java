package com.path.bo.omni.common;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.path.dbmaps.vo.ClientCO;
import com.path.dbmaps.vo.ClientVO;
import com.path.dbmaps.vo.Clients_groupVO;
import com.path.dbmaps.vo.DeviceKeysSC;
import com.path.dbmaps.vo.DeviceSC;
import com.path.dbmaps.vo.DriverCO;
import com.path.dbmaps.vo.DriverVO;
import com.path.dbmaps.vo.ErrorVO;
import com.path.dbmaps.vo.MachineCO;
import com.path.dbmaps.vo.Machine_driverCO;
import com.path.dbmaps.vo.NotificationVO;
import com.path.dbmaps.vo.PropertiesVO;
import com.path.dbmaps.vo.RateVO;
import com.path.dbmaps.vo.ReservationCO;
import com.path.dbmaps.vo.ReservationDetailsCO;
import com.path.dbmaps.vo.ReservationListVO;
import com.path.dbmaps.vo.ReservationSC;
import com.path.dbmaps.vo.StoreVO;
import com.path.dbmaps.vo.TemporaryReservationVO;
import com.path.dbmaps.vo.User;
import com.path.dbmaps.vo.WorkingHoursVO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;
import com.path.vo.omni.common.OmniCommonCO;
import com.path.vo.omni.common.base.OmniBaseSC;
import com.path.vo.omni.common.base.OmniBaseVO;
import com.path.vo.omni.common.omniuser.OmniCommonUserSC;
import com.path.ws.omni.vo.common.AddMonthlyClientResponse;
import com.path.ws.omni.vo.common.AddClientToGroupResponse;
import com.path.ws.omni.vo.common.AddNewFactureResponse;
import com.path.ws.omni.vo.common.AddStoreResponse;
import com.path.ws.omni.vo.common.CancelRecurringReservationResponse;
import com.path.ws.omni.vo.common.ChangeStringToDateRequest;
import com.path.ws.omni.vo.common.ChangeStringToDateResponse;
import com.path.ws.omni.vo.common.CheckTempRecurringReservationResponse;
import com.path.ws.omni.vo.common.ConfirmReservationResponse;
import com.path.ws.omni.vo.common.ConfirmTlubTukReservationResponse;
import com.path.ws.omni.vo.common.CostResponse;
import com.path.ws.omni.vo.common.CreateGroupResponse;
import com.path.ws.omni.vo.common.EncryptionRequest;
import com.path.ws.omni.vo.common.EncryptionResponse;
import com.path.ws.omni.vo.common.ForgetPasswordResponse;
import com.path.ws.omni.vo.common.GenerateFrontEndKeysResponse;
import com.path.ws.omni.vo.common.GenerateOTPResponse;
import com.path.ws.omni.vo.common.HandShakeRequest;
import com.path.ws.omni.vo.common.HandShakeResponse;
import com.path.ws.omni.vo.common.Notification;
import com.path.ws.omni.vo.common.RatingTripResponse;
import com.path.ws.omni.vo.common.RejectReservationResponse;
import com.path.ws.omni.vo.common.RenewMonthlyClientRequest;
import com.path.ws.omni.vo.common.RenewMonthlyClientResponse;
import com.path.ws.omni.vo.common.ReturnMonthlyClientDataRequest;
import com.path.ws.omni.vo.common.ReturnMonthlyClientDataResponse;
import com.path.ws.omni.vo.common.ReturnCostOfDistanceResponse;
import com.path.ws.omni.vo.common.ReturnCostProperties;
import com.path.ws.omni.vo.common.ReturnListOfGroupsResponse;
import com.path.ws.omni.vo.common.ReturnListOfMachinesNearestMostFarResponse;
import com.path.ws.omni.vo.common.ReturnNearestAvailableMachineResponse;
import com.path.ws.omni.vo.common.ReturnRecurringReservationListResponse;
import com.path.ws.omni.vo.common.ReturnCostOfDistance;
import com.path.ws.omni.vo.common.SaveTokenResponse;
import com.path.ws.omni.vo.common.SuspendMonthlyClientResponse;
import com.path.ws.omni.vo.common.SuspendMonthlyClientRequest;
import com.path.ws.omni.vo.common.UpdateDriverInfoResponse;
import com.path.ws.omni.vo.common.UpdateDriverWorkingHoursResponse;
import com.path.ws.omni.vo.common.UpdateMachineInfoResponse;
import com.path.ws.omni.vo.common.ValidateOTPResponse;
import com.path.ws.omni.vo.common.CreateRecurringReservationResponse;
import com.path.ws.omni.vo.common.CreateReservationOfflineResponse;
import com.path.ws.omni.vo.common.CreateReservationResponse;

/**
 * 
 * this source code Copyright 2017, Path Solutions Path Solutions retains all
 * ownership rights to
 * 
 * @author: ChadiAssaf
 *
 *          OmniCommonBO.java used to
 */
public interface OmniCommonBO {

	/**
	 * method that cache the public key of frontEnd, and Pair Key of backEnd
	 * 
	 * @param frontEndPublicKey
	 * @return
	 * @throws Exception
	 */
	public HandShakeResponse handShake(HandShakeRequest handShakeRequest) throws Exception;

	/**
	 * method that generate the otp ,
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public GenerateOTPResponse generateOTP(DeviceSC deviceSC) throws Exception;

	/**
	 * method that check the validation of OTP the user ,
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ValidateOTPResponse validateOTP(DeviceSC deviceSC) throws Exception;

	public DeviceKeysSC getTokenAndKeysClient(String device_UUID) throws Exception;

	/**
	 * method that confirm the reservation
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ConfirmReservationResponse confirmReservation(ReservationCO reservationCO) throws Exception;

	/**
	 * method that return the list of temporary reservation
	 * 
	 * @param temporaryReservationVO
	 * @return
	 * @throws Exception
	 */
	public CheckTempRecurringReservationResponse checkTempRecurringReservation(
			TemporaryReservationVO temporaryReservationVO) throws Exception;

	/**
	 * method to rating the trip
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public RatingTripResponse ratingTrip(RateVO rateVO) throws Exception;

	/**
	 * method that delete from temporary_reservation table if the client reject the
	 * reservation
	 * 
	 * @param reservationCO
	 * @return
	 * @throws Exception
	 */
	public RejectReservationResponse rejectReservation(ReservationCO reservationCO) throws Exception;

	/**
	 * method that update the working hours of drivers
	 * 
	 * @param workingHoursVO
	 * @return
	 * @throws Exception
	 */
	public UpdateDriverWorkingHoursResponse updateDriverWorkingHours(WorkingHoursVO workingHoursVO) throws Exception;

	/**
	 * method to calculate the cost of reservation
	 * 
	 * @param reservationCO
	 * @return
	 * @throws Exception
	 */
	public CostResponse getCost(ReservationCO reservationCO, int cost_of_100_m) throws Exception;

	/**
	 * method to add a store
	 * 
	 * @param storeVO
	 * @return
	 * @throws Exception
	 */
	public AddStoreResponse addStore(StoreVO storeVO) throws Exception;

	/**
	 * method that return the mask of mobile number
	 * 
	 * @param mobileNumber
	 * @return
	 * @throws Exception
	 */
	public ForgetPasswordResponse returnMaskMobileNumber(String mobileNumber, DeviceSC deviceSC) throws Exception;

//	public String refreshToken(String device_UUID, HashMap<String, DeviceKeysSC> map, String responseData)
//			throws Exception;

	/**
	 * Method that returns Next value of sequence of a table
	 * 
	 * @param omniBaseSC
	 * @return
	 * @throws BaseException
	 */
	public BigDecimal returnNextValOfSequence(Object objParam) throws BaseException;

	/**
	 * return Sequence of Original Record
	 * 
	 * @param omniBaseSC
	 * @return
	 * @throws BaseException
	 */
	public BigDecimal returnRelatedSequence(Object objParam, String pkColName, BigDecimal codeRef) throws BaseException;

	/**
	 * fill User that apply the action And running Date
	 */
	public Object fillUserAndDate(OmniBaseVO omniBaseVO, Object objParam, String action) throws BaseException;

	/**
	 * update Ref Code of Table even if it's null
	 * 
	 * @param omniBaseSC
	 * @return
	 * @throws BaseException
	 */
	public int updateRefCode(Object objParam) throws BaseException;

	/*****************************
	 * END RICHIE FOR TESTING PURPOSE ONLY
	 ************************/

	/**
	 * returnOmniCounter
	 * 
	 * @param omniCommonCO
	 * @return
	 * @throws BaseException
	 */
	public BigDecimal returnOmniCounter(OmniCommonCO omniCommonCO) throws BaseException;

	/**
	 * return Application and Channel
	 * 
	 * @param omniBaseSC
	 * @return
	 * @throws BaseException
	 */
	public List<OmniCommonCO> returnAppChannels(OmniBaseSC omniBaseSC) throws BaseException;

	/**
	 * Added by NEENA Method that returns Application Count
	 * 
	 * @param criteria
	 * @return int number of records
	 * @throws BaseException
	 */
	public int returnApplicationListCount(OmniBaseSC omniBaseSC) throws BaseException;

	/**
	 * Method that saves Application List
	 * 
	 * @param AuthenticationMatrixSC
	 * @return authenticationMatrixCO the record that saved
	 * @throws BaseException
	 */
	public List returnApplicationList(OmniBaseSC omniBaseSC) throws BaseException;

	/**
	 * check Concurrent Date Possibility for existing object
	 * 
	 * @param objParam
	 * @return
	 * @throws BaseException
	 */
	public Object checkConcurrentDate(Object objParam) throws BaseException;

	/**
	 * Method that do actions related to a page
	 * 
	 * @param object is the VO , and all actions
	 * @return int
	 * @throws BaseException
	 */
	public Object updateRecordScreen(Object obj, String action) throws BaseException;

	/**
	 * @author gilbertandary Method that saves service into designated table
	 * @throws Exception
	 * @throws BaseException
	 */
	public Object saveService(Object objToInsert) throws Exception;

	public Object updateRecordScreen(String recordActionSave, OmniBaseVO omniBaseVO, Object obj) throws BaseException;

	/**
	 * @author KhaledAlTimany
	 * @param obj
	 * @return
	 */
	public Boolean isAllowedChannelApp(OmniBaseSC omniBaseSc) throws BaseException;

	/**
	 * @author Suhail.Shoukathali
	 * @param creater
	 * @param modifier
	 * @param userId
	 * @throws BaseException
	 */
	public void applyMakerCheckerValidation(String creater, String modifier, String userId) throws BaseException;

	/**
	 * @author gilbertandary
	 * @throws DAOException
	 */
	public ArrayList<String> getVOAttributes(Object vo) throws DAOException;

	public Object insertIntoDB(Object vo) throws Exception;

	public HashMap<String, Object> returnETLCode(Object voName, String listOfIds, String codeColumnName)
			throws Exception;

	public Object selectRecord(Object vo, String... columnNames) throws BaseException;

	public OmniCommonCO returnLanguagesList(OmniBaseSC sc) throws BaseException;

	public OmniCommonCO executePWSService(OmniCommonUserSC sc) throws BaseException;

	/**
	 * method that return the error from db on startup server
	 * 
	 * @param language_id
	 * @param errorCode
	 * @return
	 */
	public List<ErrorVO> getError(int language_id, int errorCode);

	/**
	 * method that return the notification from db on startup server
	 * 
	 * @param language_id
	 * @param notificationCode
	 * @return
	 */
	public List<NotificationVO> getNotification(int language_id, String notificationCode);

	public Notification loadKeyAndUrl() throws IOException, DAOException;

	/**
	 * method that update the token of device
	 * 
	 * @param uesr
	 * @return
	 * @throws Exception
	 */
	public SaveTokenResponse saveToken(User uesr) throws Exception;

	/**
	 * method that cancel the recurring reservation
	 * 
	 * @param reservationCO
	 * @return
	 * @throws Exception
	 */
	public CancelRecurringReservationResponse cancelRecurringReservation(ReservationCO reservationCO) throws Exception;

	/**
	 * method that calculate the cost of trip
	 * 
	 * @param driverCO
	 * @param reservationCO
	 * @param listProperties
	 * @return
	 * @throws Exception
	 */
	public CostResponse calculateCost(DriverCO driverCO, ReservationCO reservationCO, List<PropertiesVO> listProperties)
			throws Exception;

	/**
	 * method that return the dismissed and cancel reservation
	 * 
	 * @param reservationCO
	 * @return
	 * @throws Exception
	 */
	public List<ReservationCO> checkDismissedAndCanceledReservation(ReservationCO reservationCO) throws Exception;

	/**
	 * method that reserve the nearest available machine
	 * 
	 * @param reservationCO
	 * @return
	 * @throws Exception
	 */
	public CreateReservationResponse createReservation(ReservationCO reservationCO) throws Exception;

	/**
	 * method that confirm the tlubtuk reservation
	 * 
	 * @param reservationCO
	 * @return
	 * @throws Exception
	 */
	public ConfirmTlubTukReservationResponse confirmTlubTukReservation(ReservationDetailsCO reservationDetailsCO)
			throws Exception;

	/**
	 * method that convert sql time to java time
	 * 
	 * @param time
	 * @param period
	 * @return
	 * @throws Exception
	 */
	public LocalDateTime ConvertTimeToJavaTime(Time time, Date date) throws Exception;

	/**
	 * method that calculate the cost of distance between two locations
	 * 
	 * @param reservationSC
	 * @return
	 * @throws Exception
	 */
	public ReturnCostOfDistanceResponse returnCostOfDistance(ReservationSC reservationSC) throws Exception;

	/**
	 * method that return the cost start finish double and string
	 * 
	 * @param driverCO
	 * @param priceBenzin
	 * @param kmTanaki
	 * @param paramForCost
	 * @param paramCost4to7
	 * @param paramCost7to10
	 * @param paramcCostHigherThan10
	 * @return
	 * @throws Exception
	 */
	public ReturnCostOfDistance calculateCostOfDistance(double distance, double priceBenzin, double kmTanaki,
			double paramForCost, double paramCost4to7, double paramCost7to10, double paramcCostHigherThan10,
			int nbOfClient, double percentageCost) throws Exception;

	/**
	 * method that return cost's properties
	 * 
	 * @param listProperties
	 * @return
	 * @throws Exception
	 */
	public ReturnCostProperties returnCostProperties(List<PropertiesVO> listProperties) throws Exception;

	/**
	 * method that calculate the distance between two kilometrages
	 * 
	 * @param kilometrage1
	 * @param kilometrage2
	 * @return
	 * @throws Exception
	 */
	public double calculateDistanceBetween2Kilometrage(double kilometrage1, double kilometrage2) throws Exception;

	/**
	 * method that return the nearest driver
	 * 
	 * @param reservationCO
	 * @return
	 * @throws Exception
	 */
	public ReturnNearestAvailableMachineResponse returnNearestAvailableMachine(ReservationCO reservationCO)
			throws Exception;

	/**
	 * method that return the list of available machines from the nearest to the
	 * most far
	 * 
	 * @param reservationCO
	 * @return
	 * @throws Exception
	 */
	public ReturnListOfMachinesNearestMostFarResponse returnListOfMachinesNearestMostFar(ReservationCO reservationCO)
			throws Exception;

	/**
	 * return list of machine
	 * 
	 * @param reservationCO
	 * @return
	 * @throws Exception
	 */
	public List<Machine_driverCO> returnList(ReservationCO reservationCO) throws Exception;

	/**
	 * method that return the list of groups
	 * 
	 * @param clients_groupVO
	 * @return
	 * @throws Exception
	 */
	public ReturnListOfGroupsResponse returnListOfGroups(Clients_groupVO clients_groupVO) throws Exception;

	/**
	 * method that add new monthly client with group
	 * 
	 * @param clientVO
	 * @return
	 * @throws Exception
	 */
	public AddMonthlyClientResponse addMonthlyClient(ClientCO clientCO) throws Exception;

	/**
	 * method that create new group
	 * 
	 * @param clients_groupVO
	 * @return
	 * @throws Exception
	 */
	public CreateGroupResponse createGroup(Clients_groupVO clients_groupVO) throws Exception;

	/**
	 * method that check the available machines at all date , and reserve them , if
	 * no reserve ealier or later temporarly
	 * 
	 * @param reservationCO
	 * @return
	 * @throws Exception
	 */
	public CreateRecurringReservationResponse createRecurringReservation(ReservationCO reservationCO) throws Exception;

	/////////////////// Mohannad//////////////

//	/**
//	 * Mohannad
//	 * A method that create a new user
//	 * 
//	 * @param phoneNb
//	 * @return
//	 * @throws Exception
//	 */
//	public String createNewUser(String phoneNb) throws Exception;

	/**
	 * method that return the list of recurring reservation
	 * 
	 * @param reservationVO
	 * @return
	 * @throws Exception
	 */
	public ReturnRecurringReservationListResponse returnRecurringReservationList(ReservationCO reservationCO)
			throws Exception;

	/**
	 * 
	 * 
	 * @param reservationCO
	 * @return
	 * @throws Exception
	 */
	public AddNewFactureResponse addNewFacture(ReservationCO reservationCO) throws Exception;

	/**
	 * 
	 * @param reservationCO
	 * @return
	 * @throws Exception
	 */
	public AddClientToGroupResponse addClientToGroup(ReservationCO reservationCO) throws Exception;

	/**
	 * 
	 * @param changeStringToDateRequest
	 * @return
	 * @throws Exception
	 */
	public ChangeStringToDateResponse changeStringToDate(ChangeStringToDateRequest changeStringToDateRequest)
			throws Exception;

	/**
	 * 
	 * @param renewMonthlyUserRequest
	 * @return
	 * @throws Exception
	 */
	public RenewMonthlyClientResponse renewMonthlyClient(ClientCO clientCO) throws Exception;

	/**
	 * 
	 * @param reservationCO
	 * @return
	 * @throws Exception
	 */
	public SuspendMonthlyClientResponse suspendMonthlyClient(ReservationCO reservationCO) throws Exception;

	/**
	 * 
	 * @param retreiveMonthlyUserDataRequest
	 * @return
	 * @throws Exception
	 */
	public ReturnMonthlyClientDataResponse returnMonthlyClientData(
			ReturnMonthlyClientDataRequest retreiveMonthlyUserDataRequest) throws Exception;

	//////////////// Mohannad///////////////////
}
